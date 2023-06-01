package com.auth.service;

import com.auth.payload.LoginDto;
import com.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
