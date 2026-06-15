package tn.esprit.stageete.service;

import tn.esprit.stageete.dto.JwtResponse;
import tn.esprit.stageete.dto.LoginRequest;
import tn.esprit.stageete.dto.RegisterRequest;

public interface AuthService {
    JwtResponse login(LoginRequest request);
    JwtResponse register(RegisterRequest request);  // ← JwtResponse pas String
}