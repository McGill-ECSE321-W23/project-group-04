package ca.mcgill.ecse321.parkinglotbackend;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

@Configuration
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Autowired
    MySessionListener mySessionListener;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(mySessionListener);
    }
}