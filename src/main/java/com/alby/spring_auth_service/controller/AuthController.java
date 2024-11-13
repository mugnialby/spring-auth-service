package com.alby.spring_auth_service.controller;

import com.alby.spring_auth_service.dto.request.LoginRequest;
import com.alby.spring_auth_service.dto.response.LoginResponse;
import com.alby.spring_auth_service.dto.response.WebResponse;
import com.alby.spring_auth_service.service.AuthService;
import com.alby.spring_auth_service.service.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final ValidationService validationService;

    private final AuthService authService;

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        validationService.validate(request);
        return authService.login(request);
    }
}
