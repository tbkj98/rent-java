package com.tbkj.rent.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Bean
    FirebaseApp initFirebase() throws IOException {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);
        log.info("Firebase initialized");
        return app;
    }

    @Bean
    FirebaseAuth initFirebaseAuth(FirebaseApp app) {
        return FirebaseAuth.getInstance(app);
    }
}
