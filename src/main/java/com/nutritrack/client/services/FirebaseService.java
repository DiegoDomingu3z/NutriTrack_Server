package com.nutritrack.client.services;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@SuppressWarnings("unchecked")
@Service
public class FirebaseService {
    public static String verifyToken(String idToken) throws Exception {
        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
        return decodedToken.getUid(); // Extract the UID
    }
}
