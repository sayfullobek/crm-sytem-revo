package it.revo.revoservice.service;

import it.revo.revoservice.entity.User;
import it.revo.revoservice.repository.RoleRepository;
import it.revo.revoservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("getUser"));

    }

    public UserDetails getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
    }

    public User getOneUser(UUID id) {
        try {
            Optional<User> byId = userRepository.findById(id);
            return byId.orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

//    public ApiResponse editFullName(UUID id, ReqRegister reqRegister) {
//        try {
//            Optional<User> byId = userRepository.findById(id);
//            if (byId.isPresent()) {
//                User user = byId.get();
//                switch (reqRegister.getMalumot()) {
//                    case "name and surname":
//                        user.setFirstName(reqRegister.getFirstName());
//                        user.setLastName(reqRegister.getLastName());
//                        break;
//                    case "email":
//                        user.setEmail(reqRegister.getEmail());
//                        break;
//                    case "phoneNumber":
//                        user.setPhoneNumber(reqRegister.getPhoneNumber());
//                        break;
//                    case "password":
//                        if (reqRegister.getPassword().equals(reqRegister.getPrePassword())) {
//                            user.setPassword(passwordEncoder().encode(reqRegister.getPassword()));
//                            user.setCode(reqRegister.getPassword());
//                        }
//                        break;
//                    case "image":
//                        user.setImg(reqRegister.getImg());
//                        break;
//                }
//                userRepository.save(user);
//                return new ApiResponse("successfully edited fullName", true);
//            } else {
//                return new ApiResponse("bunday user mavjud emas", false);
//            }
//        } catch (Exception e) {
//            return new ApiResponse("xatolik", false);
//        }
//    }
}
