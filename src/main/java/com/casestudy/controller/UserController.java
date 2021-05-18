package com.casestudy.controller;

import com.casestudy.model.Role;
import com.casestudy.model.RoleName;
import com.casestudy.model.User;
import com.casestudy.model.UserForm;
import com.casestudy.service.user.AppUserService;
import com.casestudy.service.user.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private AppUserService userService;

    @Autowired
    RoleServiceImpl roleService;

    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping("/create-user")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/views/simple-signup");
//        modelAndView.addObject("user", new User());
        modelAndView.addObject("user", new UserForm());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView saveUser(@Validated @ModelAttribute("user") UserForm userRequest, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            System.out.println("fail validate 1111111111111111111111111111111");
            return new ModelAndView("/views/simple-signup");
        }

        if (userService.existsByUsername(userRequest.getUsername())) {
            ModelAndView modelAndView = new ModelAndView("/views/simple-signup");
            modelAndView.addObject("error", "Username exist!!!");
            return modelAndView;
        }

        MultipartFile multipartFile = userRequest.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(userRequest.getAvatar().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Creating user's account
        User user;
        if (fileName.equals("")) {
            user = new User(userRequest.getUsername(), userRequest.getPassword(), userRequest.getFullName());
        } else {
            user = new User(userRequest.getUsername(), userRequest.getPassword(), userRequest.getFullName(), fileName);
        }

//        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

//        strRoles.forEach(role -> {
//            switch (role) {
//                case "admin":
//                    Role adminRole = roleService.findByName(RoleName.ADMIN)
//                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                    roles.add(adminRole);
//
//                    break;
//                case "pm":
//                    Role pmRole = roleService.findByName(RoleName.PM)
//                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                    roles.add(pmRole);
//
//                    break;
//                default:
//                    Role userRole = roleService.findByName(RoleName.USER)
//                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                    roles.add(userRole);
//            }
//        });

//        Role userRole = roleService.findByName(RoleName.ROLE_MEMBER)
//                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        Role userRole = new Role(2L, "ROLE_MEMBER");
//        userRole.setName("ROLE_MEMBER");
//        userRole.setId(2L);
        roles.add(userRole);
        user.setRoles(roles);
        userService.save(user);

        System.out.println("fail gan end 111111111111111111111111");
//        userService.save(userRequest);
//        ModelAndView modelAndView = new ModelAndView("redirect:home");
        ModelAndView modelAndView = new ModelAndView("/views/simple-signup");
//        modelAndView.addObject("city", new City());
        modelAndView.addObject("success", "New user created successfully!");
//        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
