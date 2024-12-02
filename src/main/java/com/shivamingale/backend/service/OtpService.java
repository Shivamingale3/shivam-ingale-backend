package com.shivamingale.backend.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {

    private final Map<String, OtpDetails> otpStore = new HashMap<>(); // Temporary in-memory store

    public String generateOtp(String userEmail) {
        SecureRandom random = new SecureRandom();
        String otp = String.format("%06d", random.nextInt(1000000)); // 6-digit OTP

        OtpDetails otpDetails = new OtpDetails(otp, LocalDateTime.now().plusMinutes(5));
        otpStore.put(userEmail, otpDetails); // Store OTP with expiry

        return otp;
    }

    public boolean validateOtp(String userEmail, String inputOtp) {
        OtpDetails otpDetails = otpStore.get(userEmail);

        if (otpDetails == null || otpDetails.getExpiry().isBefore(LocalDateTime.now())) {
            return false; // OTP expired or not found
        }

        boolean isValid = otpDetails.getOtp().equals(inputOtp);
        if (isValid) {
            otpStore.remove(userEmail); // Remove OTP after successful validation
        }
        return isValid;
    }

    @Getter
    private static class OtpDetails {
        private final String otp;
        private final LocalDateTime expiry;

        public OtpDetails(String otp, LocalDateTime expiry) {
            this.otp = otp;
            this.expiry = expiry;
        }

    }

}
