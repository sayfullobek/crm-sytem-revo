package it.revo.revoservice.service;

import it.revo.revoservice.entity.Course;
import it.revo.revoservice.entity.Group;
import it.revo.revoservice.entity.User;
import it.revo.revoservice.entity.WeekDays;
import it.revo.revoservice.payload.ApiResponse;
import it.revo.revoservice.payload.GroupDto;
import it.revo.revoservice.repository.CourseRepository;
import it.revo.revoservice.repository.GroupRepository;
import it.revo.revoservice.repository.UserRepository;
import it.revo.revoservice.repository.WeekDayRepository;
import it.revo.revoservice.utils.Exception;
import it.revo.revoservice.utils.groupFull.GroupLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService implements GroupLogic {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final WeekDayRepository weekDayRepository;

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
                    } else if (groupDto.getWeekDay().equals("toq") && i % 2 != 0) {
                        weekDays.add(weekDayRepository.findById(i).orElseThrow(() -> new ResourceNotFoundException("getWeekDays")));
                    } else if (groupDto.getWeekDay().equals("bootcamp")) {
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
}
