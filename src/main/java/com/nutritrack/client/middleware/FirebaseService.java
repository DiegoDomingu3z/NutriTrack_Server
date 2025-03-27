package com.nutritrack.client.middleware;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

/**
 * @author Diego Dominguez
 * @date: Feb 16 2025
 * @version 1.0
 * @description: Service class for Firebase authentication and app initialization.
 *               This class configures Firebase and provides token verification functionality.
 */
@SuppressWarnings("unchecked")
@Service
public class FirebaseService {

    /**
     * Initializes and configures Firebase using the service account credentials.
     *
     * @return FirebaseApp The initialized Firebase application instance.
     * @throws IOException If an error occurs while reading the service account credentials.
     */
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        if (FirebaseApp.getApps().isEmpty()){
            FileInputStream serviceAccount = new FileInputStream("./nutritrack-firebase-adminsdk.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            return FirebaseApp.initializeApp(options);
        } else {
            return FirebaseApp.getInstance();
        }
    }

    /**
     * Verifies a Firebase ID token and extracts the corresponding UID.
     *
     * @param idToken The Firebase ID token provided by the client.
     * @return String The UID associated with the verified token.
     * @throws Exception If the token is invalid or verification fails.
     */
    public static String verifyToken(String idToken) throws Exception {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            System.out.println("TOKEN VERIFIED: " + decodedToken.getUid());
            System.out.println(idToken);
            return decodedToken.getUid(); // Extract the UID
        } catch (Exception e) {
            System.out.println("CURCIAL ERROR:" + e);
            throw e;
        }
    }
}
