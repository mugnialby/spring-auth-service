package com.alby.spring_auth_service.service;

import com.alby.spring_auth_service.dto.request.LoginRequest;
import com.alby.spring_auth_service.dto.request.VerifyTokenRequest;
import com.alby.spring_auth_service.dto.response.LoginResponse;
import com.alby.spring_auth_service.dto.response.WebResponse;

public interface AuthService {

    WebResponse<LoginResponse> login(LoginRequest loginRequest);

    boolean verify(VerifyTokenRequest request);
}
