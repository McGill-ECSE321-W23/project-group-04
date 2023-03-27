package ca.mcgill.ecse321.parkinglotbackend.service;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.parkinglotbackend.dao.CarRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;

@ExtendWith(MockitoExtension.class)
public class TestCarService {
    
    @Mock
    private CarRepository carDao;

    @InjectMocks
    private CarService service;

    private static final Long CAR_KEY = (long) 1234;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(carDao.findCarByCarID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CAR_KEY)) {
                Car car = new Car();
                car.setCarID(CAR_KEY);
                return car;
            } else {
                return null;
            }
        });
    }
}
