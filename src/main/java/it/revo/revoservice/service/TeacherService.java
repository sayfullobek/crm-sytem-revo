package it.revo.revoservice.service;

import it.revo.revoservice.entity.*;
import it.revo.revoservice.entity.enums.LidStatus;
import it.revo.revoservice.entity.enums.RoleName;
import it.revo.revoservice.payload.*;
import it.revo.revoservice.repository.*;
import it.revo.revoservice.utils.Exception;
import it.revo.revoservice.utils.teacherFull.TeacherLogic;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeacherService implements TeacherLogic {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ContactRepository contactRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final WorkProgressRepository workProgressRepository;

    @Override
    public List<User> getAbout(String api) {
        List<User> users = new ArrayList<>();
        List<User> all = userRepository.findAll();
        for (User user : all) {
            if (api.equals("pupil") && user.getRole().getRoleName().equals(RoleName.PUPIL)) {
                User build = User.builder().firstName(user.getFirstName()).lastName(user.getLastName()).phoneNumber(user.getPhoneNumber()).birthDate(user.getBirthDate()).lidStatus(user.getLidStatus()).parentNumber(user.getParentNumber()).build();
                build.setId(user.getId());
                users.add(build);
            } else if (api.equals("teacher") && user.getRole().getRoleName().equals(RoleName.TEACHER)) {
                User build = User.builder().firstName(user.getFirstName()).lastName(user.getLastName()).phoneNumber(user.getPhoneNumber()).birthDate(user.getBirthDate()).build();
                build.setId(user.getId());
                users.add(build);
            }
        }
        return users;
    }

    @Override
    public ApiResponse addTeacher(ReqRegister reqRegister) {
        Optional<Course> byId = courseRepository.findById(reqRegister.getCourseId());
        if (byId.isPresent()) {
            User user = new User(
                    reqRegister.getFirstName(),
                    reqRegister.getLastName(),
                    reqRegister.getPhoneNumber(),
                    roleRepository.findById(reqRegister.getApi().equals("pupil") ? 3 : 2).orElseThrow(() -> new ResourceNotFoundException("getRole")),
                    Collections.singletonList(courseRepository.findById(reqRegister.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("getCourse"))),
                    reqRegister.getBirthDate(),
                    reqRegister.getParentNumber()
            );
            if (reqRegister.getApi().equals("pupil")) {
                user.setLidStatus(LidStatus.REGISTER);
            }
            Contact contact = new Contact(
                    "https://bootdey.com/img/Content/avatar/avatar7.png", "addressni to'g'rilang"
            );
            contact.setName("lavozimni to'g'irlang");
            Contact save = contactRepository.save(contact);
            if (reqRegister.getApi().equals("teacher")) {
                user.setPassword(passwordEncoder.encode(reqRegister.getPassword()));
                user.setCode(reqRegister.getPassword());
                user.setContact(save);
            }
            userRepository.save(user);
            return new ApiResponse(Exception.SUCCESS, true);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }

    @Override
    public ApiResponse editTeacher(UUID id, ReqRegister reqRegister) {
        return null;
    }

    @Override
    public ApiResponse deleteTeacher(UUID id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            userRepository.deleteById(id);
            return new ApiResponse(Exception.SUCCESS, true);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }

    @Override
    public TeacherAndPupilAndGroupDtoRes getOne(UUID id) {
        try {
            Optional<User> byId = userRepository.findById(id);
            if (byId.isPresent()) {
                User user = byId.get();
                return TeacherAndPupilAndGroupDtoRes.builder().user(user).build();
            }
            return null;
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ApiResponse addAccount(UUID id, AccountDto accountDto) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            Accounts accounts = new Accounts(
                    accountDto.getLink(), accountDto.getLogo()
            );
            accounts.setName(accountDto.getName());
            Accounts save = accountRepository.save(accounts);
            user.getAccaunts().add(save);
            userRepository.save(user);
            return new ApiResponse(Exception.SUCCESS, true);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }

    @Override
    public ApiResponse addWorkProgress(UUID id, WorkProgressDto workProgressDto) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            WorkProgress workProgress = new WorkProgress();
            workProgress.setName(workProgressDto.getName());
            workProgress.setProgress(workProgressDto.getProgress());
            WorkProgress save = workProgressRepository.save(workProgress);
            user.getWorkProgresses().add(save);
            userRepository.save(user);
            return new ApiResponse(Exception.SUCCESS, true);
        }
        return new ApiResponse(Exception.NOT_FOUND, false);
    }
}
