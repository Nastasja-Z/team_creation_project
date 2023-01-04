//package ua.com.alevel.facade.impl;
//
//import org.springframework.stereotype.Service;
//import ua.com.alevel.facade.RegistrationFacade;
//import ua.com.alevel.persistence.entity.RoleType;
//import ua.com.alevel.persistence.entity.User;
//import ua.com.alevel.service.UserService;
//import ua.com.alevel.web.dto.request.register.AuthDto;
//
//@Service
//public class RegistrationFacadeImpl implements RegistrationFacade {
//
//    private final UserService userService;
//
//    public RegistrationFacadeImpl(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public void registration(AuthDto dto) {
//        User personal = new User();
//        personal.setUsername(dto.getUsername());
//        personal.setPassword(dto.getPassword());
//        personal.setRoleType(RoleType.valueOf(dto.getRole()));
//        userService.create(personal);
//    }
//}
