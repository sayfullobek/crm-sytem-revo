//package it.revo.revoservice.component;
//
//import it.revo.revoservice.entity.User;
//import org.javers.spring.auditable.AuthorProvider;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SpringAuthorProvider implements AuthorProvider {
//    @Override
//    public String provide() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication() == null ? null : SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal == null || principal == "anonymousUser") {
//            return "System";
//        }
//        User user = (User) principal;
//        return user.getId().toString();
//    }
//}
