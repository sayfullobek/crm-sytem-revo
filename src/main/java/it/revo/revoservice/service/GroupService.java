package it.revo.revoservice.service;

import it.revo.revoservice.entity.*;
import it.revo.revoservice.entity.enums.LidStatus;
import it.revo.revoservice.payload.*;
import it.revo.revoservice.repository.*;
import it.revo.revoservice.utils.Exception;
import it.revo.revoservice.utils.groupFull.GroupLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GroupService implements GroupLogic {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final WeekDayRepository weekDayRepository;
    private final RoleRepository roleRepository;
    private final AttendanceRepository attendanceRepository;
    private final StatisticsRepository statisticsRepository;

    @Override
    public ApiResponse addGroup(GroupDto groupDto) {
        Optional<Course> byId = courseRepository.findById(groupDto.getCourseId());
        if (byId.isPresent()) {
            Optional<User> teachIs = userRepository.findById(groupDto.getTeacherId());
            if (teachIs.isPresent()) {
                List<WeekDays> weekDays = new ArrayList<>();
                for (int i = 1; i <= 7; i++) {
                    if (groupDto.getWeekDay().equals("juft") && i % 2 == 0) {
                        weekDays.add(weekDayRepository.findById(i).orElseThrow(() -> new ResourceNotFoundException("getWeekDays")));
                    } else if (groupDto.getWeekDay().equals("toq") && i % 2 != 0 && i != 7) {
                        weekDays.add(weekDayRepository.findById(i).orElseThrow(() -> new ResourceNotFoundException("getWeekDays")));
                    } else if (groupDto.getWeekDay().equals("bootcamp") && i != 7) {
                        weekDays.add(weekDayRepository.findById(i).orElseThrow(() -> new ResourceNotFoundException("getWeekDays")));
                    }
                }
                String hours = groupDto.getStartHours().concat("->").concat(groupDto.getEndHours());
                Group build = Group.builder()
                        .course(byId.get())
                        .teacher(teachIs.get())
                        .weekDays(weekDays)
                        .hours(hours)
                        .build();
                build.setName(groupDto.getName());
                groupRepository.save(build);
                return new ApiResponse(Exception.SUCCESS, true);
            }
            return new ApiResponse(Exception.NOT_FOUND, false);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }

    @Override
    public ApiResponse editGroup(Integer id, GroupDto groupDto) {
        return null;
    }

    @Override
    public ApiResponse deleteGroup(Integer id) {
        return null;
    }

    @Override
    public ResGroup getOneGroup(Integer id) {
        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            Group group = byId.get();
            return ResGroup.builder()
                    .id(group.getId())
                    .name(group.getName())
                    .course(group.getCourse())
                    .teacher(group.getTeacher())
                    .pupils(group.getPupils())
                    .weekDays(group.getWeekDays())
                    .hours(group.getHours())
                    .profit(group.getPupils().size() * group.getCourse().getPrice())
                    .pupilSize(group.getPupils().size())
                    .profitPupil(0)
                    .monthProfit(0)
                    .build();
        }
        return null;
    }

    @Override
    public List<User> getPupil(Integer id) {
        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            Group group = byId.get();
            Integer courseId = group.getCourse().getId();
            List<User> all = userRepository.findAll();
            List<User> pupils = new ArrayList<>();
            for (User user : all) {
                if (user.getRole().equals(roleRepository.findById(3).orElseThrow(() -> new ResourceNotFoundException("getRole")))) {
                    for (Course cours : user.getCourses()) {
                        if (cours.getId().equals(courseId)) {
                            if (!groupRepository.existsGroupByPupilsId(user.getId())) {
                                pupils.add(user);
                            }
                        }
                    }
                }
            }
            return pupils;
        }
        return null;
    }

    @Override
    public ApiResponse addPupil(Integer id, ReqRegister reqRegister) {
        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            Optional<User> userOptional = userRepository.findById(reqRegister.getId());
            if (userOptional.isPresent()) {
                Group group = byId.get();
                Date date = new Date();
                List<Attendance> attendances = timeReposrt(group, date);
                double price = 0;
                for (Attendance attendance : attendances) {
                    price = attendance.getSum() + price;
                }
                Statistics statistics = statisticsRepository.save(Statistics.builder()
                        .whenMonth(date)
                        .attendances(attendances)
                        .allSum(price)
                        .history(false)
                        .build());
                User user = userOptional.get();
                user.getStatistics().add(statistics);
                userRepository.save(user);
                group.getPupils().add(user);
                groupRepository.save(group);
                return new ApiResponse(Exception.SUCCESS, true);
            }
            return new ApiResponse(Exception.NOT_FOUND, false);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }

    @Override
    public ApiResponse addPupils(Integer id, ReqRegister reqRegister) {
        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            for (UUID reqRegisterId : reqRegister.getIds()) {
                Optional<User> userOptional = userRepository.findById(reqRegisterId);
                if (userOptional.isPresent()) {
                    Group group = byId.get();
                    Date date = new Date();
                    List<Attendance> attendances = timeReposrt(group, date);
                    double price = 0;
                    for (Attendance attendance : attendances) {
                        price = attendance.getSum() + price;
                    }
                    Statistics statistics = statisticsRepository.save(Statistics.builder()
                            .whenMonth(date)
                            .attendances(attendances)
                            .allSum(price)
                            .history(false)
                            .build());
                    User user = userOptional.get();
                    user.getStatistics().add(statistics);
                    user.setLidStatus(LidStatus.STUDY);
                    userRepository.save(user);
                    group.getPupils().add(user);
                    groupRepository.save(group);
                }
            }
            return new ApiResponse(Exception.SUCCESS, true);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }


    public List<Attendance> timeReposrt(Group group, Date date) {
        List<Attendance> attendances = new ArrayList<>();
        List<WeekDays> weekDays = group.getWeekDays();
        String choiceDayName = choiceDayName(weekDays);
        Calendar cal = Calendar.getInstance();
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //ushbu oyda necha kun borligi
        double price = group.getCourse().getPrice();//ushbu kursning narxi
        int sana = date.getDate() - 1;
        int kun = date.getDay() - 1;
        int tr = 1;
        if (choiceDayName.equals("bootcamp")) {
            for (int i = sana; i < maxDay; i++) {
                if (i == sana && kun != 0) {
                    attendances.add(addAttandance(kun, sana, false));
                    tr++;
                }
                date.setDate(i + 1);
                sana = date.getDate();
                kun = date.getDay();
            }
        } else {
            for (int i = sana; i < maxDay; i++) {
                if (choiceDayName.equals("toq") && i == sana && kun % 2 == 1) {
                    attendances.add(addAttandance(kun, sana, false));
                    tr++;
                } else if (choiceDayName.equals("juft") && i == sana && kun % 2 == 0 && kun != 0) {
                    attendances.add(addAttandance(kun, sana, false));
                    tr++;
                }
                date.setDate(i + 1);
                sana = date.getDate();
                kun = date.getDay();
            }
        }
        double oneLessonPrice = price / tr;
        for (Attendance attendance : attendances) {
            attendance.setSum(oneLessonPrice);
            attendanceRepository.save(attendance);
        }
        return attendances;
    }

    public Attendance addAttandance(int weekDay, Integer date, boolean active) {
        Attendance attendance = new Attendance();
        attendance.setWeekDays(weekDayRepository.findById(weekDay).orElseThrow(() -> new ResourceNotFoundException("getWeekDays")));
        attendance.setDate(date);
        attendance.setActive(active);
        return attendance;
    }

    public String choiceDayName(List<WeekDays> weekDays) {
        String dayName = "";
        if (weekDays.size() == 6) {
            dayName = "bootcamp";
        } else {
            for (WeekDays weekDay : weekDays) {
                if (weekDay.getId() % 2 == 1) {
                    dayName = "toq";
                } else if (weekDay.getId() % 2 == 0) {
                    dayName = "juft";
                }
            }
        }
        return dayName;
    }

    @Override
    public HttpEntity<?> getAttendanceByGroup(Integer id) {
        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            Group group = byId.get();
            Date date = new Date();
            int sana = date.getDate() - 1;
            List<ResAttendanceByGroup> resAttendanceByGroups = new ArrayList<>();
            for (User pupil : group.getPupils()) {
                for (Statistics statistic : pupil.getStatistics()) {
                    for (Attendance attendance : statistic.getAttendances()) {
                        if (attendance.getDate().equals(sana)) {
                            resAttendanceByGroups.add(ResAttendanceByGroup.builder()
                                    .active(attendance.isActive())
                                    .pupil(pupil)
                                    .build());
                        }
                    }
                }
            }
            return ResponseEntity.ok().body(resAttendanceByGroups);
        }
        return ResponseEntity.ok().body(new ApiResponse(Exception.NOT_FOUND, false));
    }

    @Override
    public HttpEntity<?> AttendanceByGroup(Integer id, ResAttendanceByGroup pupilId) {
        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            Group group = byId.get();
            Date date = new Date();
            int sana = date.getDate() - 1;
            int tr = 0;
            for (User user : group.getPupils()) {
                if (user.getId().equals(pupilId.getPupilId())) {
                    for (Statistics statistic : user.getStatistics()) {
                        for (Attendance attendance : statistic.getAttendances()) {
                            if (attendance.getDate().equals(sana)) {
                                attendance.setActive(!attendance.isActive());
                                attendanceRepository.save(attendance);
                                statisticsRepository.save(statistic);
                                userRepository.save(user);
                                groupRepository.save(group);
                                return ResponseEntity.ok().body(new ApiResponse(Exception.SUCCESS, true));
                            }
                        }
                    }
                }
            }
            return ResponseEntity.ok().body(new ApiResponse(Exception.SUCCESS, false));
        }
        return ResponseEntity.ok().body(new ApiResponse(Exception.NOT_FOUND, false));
    }
}
