//package com.example.securityjwtpracticespring.user;
//
//import com.example.securityjwtpracticespring.auth.AuthenticationService;
//import com.example.securityjwtpracticespring.auth.RegisterRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/v1/auth/user")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//    @PostMapping("/update")
//    public String register(
//            @RequestBody UserUpdateRequest request
//    ) {
//        return userService.update(request);
//    }
//}
