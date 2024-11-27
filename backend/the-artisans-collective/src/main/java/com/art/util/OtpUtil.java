package com.art.util;

import java.util.Random;

public class OtpUtil {

    public static String generateOtp() {
        int otpLength = 6;  // Length of the OTP
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10));  // Appends a random digit (0-9)
        }
        return otp.toString();
    }
}
