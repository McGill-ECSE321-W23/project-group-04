package ca.mcgill.ecse321.parkinglotbackend;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;

@Component
public class MySessionListener implements HttpSessionListener {

    private int activeSessions = 0;

    public void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
        System.out.println("Session created. Active sessions: " + activeSessions);
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        activeSessions--;
        System.out.println("Session destroyed. Active sessions: " + activeSessions);
    }
}
