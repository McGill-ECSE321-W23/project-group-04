package ca.mcgill.ecse321.parkinglotbackend.service;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.security.Provider.Service;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.cache.interceptor.AbstractCacheResolver;

import ca.mcgill.ecse321.parkinglotbackend.dao.ServiceAppointmentRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment.AppointmentStatus;

@ExtendWith(MockitoExtension.class)
public class TestServiceAppointmentService {
        
    @Mock
    private ServiceAppointmentRepository aRepository;

    @InjectMocks
    private ServiceAppointmentService service;
    
        //Mock DB
        private static final Person P1 = new Person(1, "1111", "Person 1");
        private static final Person P2 = new Person(2, "2222", "Person 2");
        private static final Person P3 = new Person(3, "3333", "Person 3");
        
        private static final Car C1 = new Car (1, "A11 AAA", "M1", "Mo1", P1);
        private static final Car C2 = new Car (2, "B22 BBB", "M2", "Mo2", P2);
        private static final Car C3 = new Car (3, "C33 CCC", "M3", "Mo3", P3);

        private static final Garage G1 = new Garage(1, 1);
        private static final Garage G2 = new Garage (2, 2);
        private static final Garage G3 = new Garage (3, 3);

        private static final OfferedService O1 = new OfferedService(1, "Thing 1", 1, 1);
        private static final OfferedService O2 = new OfferedService (2, "Thing 2", 2, 2);
        private static final OfferedService O3 = new OfferedService (3, "Thing 3", 3, 3);
    
        private static final ServiceAppointment A1 = new ServiceAppointment(1, LocalDateTime.of(1, 1, 1, 1, 1, 1), AppointmentStatus.Ready,O1, G1, C1);
        private static final ServiceAppointment A2 = new ServiceAppointment (2, LocalDateTime.of(2, 2, 2, 2, 2, 2), AppointmentStatus.InProgress,O2, G2, C2);
        private static final ServiceAppointment A3 = new ServiceAppointment (3, LocalDateTime.of(3, 3, 3, 3, 3, 3),AppointmentStatus.Completed,O3, G3, C3);
    
        // Test data
        private static final long APP_ID = 4;
        private static final LocalDateTime APP_TIME = LocalDateTime.of(4, 4, 4, 4, 4, 4);
        private static final AppointmentStatus APP_STATUS = AppointmentStatus.Completed;
        private static final Person CAR_OWNER = new Person (4, "4444", "Person 4");
        private static final Car APP_CAR =  new Car (4, "D44 DDD", "M4", "Mo4", CAR_OWNER);
        private static final Garage APP_GARAGE = new Garage (4, 4);
        private static final OfferedService APP_SERVICE = new OfferedService(4, "Thing 4", 4, 4);

        @BeforeEach
        public void setMockOutput() {
            lenient().when(aRepository.findAppointmentByServiceAppointmentID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
                if(invocation.getArgument(0).equals(A1.getServiceAppointmentID())) {
                    return A1;
                } else if (invocation.getArgument(0).equals(A2.getServiceAppointmentID())){
                    return A2;
                } else if (invocation.getArgument(0).equals(A3.getServiceAppointmentID())){
                    return A3;
                } else {
                    return null;
                }
            });

            lenient().when(aRepository.findAppointmentByCar_CarID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
                if(invocation.getArgument(0).equals(A1.getCar().getCarID())) {
                    return A1;
                } else if (invocation.getArgument(0).equals(A2.getCar().getCarID())) {
                    return A2;
                } else if (invocation.getArgument(0).equals(A3.getCar().getCarID())) {
                    return A3;
                } else {
                    return null;
                }
            });

            lenient().when(aRepository.findAppointmentByService_ServiceID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
                if(invocation.getArgument(0).equals(A1.getService().getServiceID())) {
                    return A1;
                } else if (invocation.getArgument(0).equals(A2.getService().getServiceID())) {
                    return A2;
                } else if (invocation.getArgument(0).equals(A3.getService().getServiceID())) {
                    return A3;
                } else {
                    return null;
                }
            });

            lenient().when(aRepository.save(any(ServiceAppointment.class))).thenAnswer((InvocationOnMock invocation) -> {
                return invocation.getArgument(0);
            });    
        }


    @AfterEach
    public void resetMockDB() {

        A1.setServiceAppointmentID(1);
        A1.setStartTime(LocalDateTime.of(1, 1, 1, 1, 1, 1));
        A1.setAppointmentStatus(AppointmentStatus.Ready);
        A1.setCar(C1);
        A1.setService(O1);
        A1.setGarage(G1);

        A2.setServiceAppointmentID(2);
        A2.setStartTime(LocalDateTime.of(2,2,2,2,2,2));
        A2.setAppointmentStatus(AppointmentStatus.InProgress);
        A2.setCar(C2);
        A2.setService(O2);
        A2.setGarage(G2);

        A3.setServiceAppointmentID(3);
        A3.setStartTime(LocalDateTime.of(3, 3, 3, 3, 3, 3));
        A3.setAppointmentStatus(AppointmentStatus.Completed);
        A3.setCar(C3);
        A3.setService(O3);
        A3.setGarage(G3);
    }

    @Test
    public void testCreateAppointment_Success() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.createAppointment(APP_GARAGE, APP_SERVICE, APP_CAR);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("", eMSG);
        assertNotNull(appointment);
        //assertEquals(APP_ID, appointment.getServiceAppointmentID());
        //assertNull(appointment.getStartTime());
        assertEquals(AppointmentStatus.InProgress, appointment.getAppointmentStatus());
        assertEquals(CAR_OWNER.getPersonID(), appointment.getCar().getOwner().getPersonID());
        assertEquals(APP_CAR.getCarID(), appointment.getCar().getCarID());
        assertEquals(APP_GARAGE.getGarageID(), appointment.getGarage().getGarageID());
        assertEquals(APP_SERVICE.getServiceID(), appointment.getService().getServiceID());

    }

    @Test
    public void testCreateAppointment_NullGarage() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.createAppointment(null, APP_SERVICE, APP_CAR);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        assertEquals("Garage cannot be empty", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testCreateAppointment_NullService() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.createAppointment(APP_GARAGE, null, APP_CAR);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        assertEquals("Service cannot be empty", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testCreateAppointment_NullCar() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.createAppointment(APP_GARAGE, APP_SERVICE, null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        assertEquals("Car cannot be empty", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testFindAppointmentByID_Success() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.findAppointmentByID(A1.getServiceAppointmentID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("", eMSG);
        assertNotNull(appointment);
        assertEquals(A1, appointment);
        assertEquals(A1.getServiceAppointmentID(), appointment.getServiceAppointmentID());    
    }

    @Test
    public void testFindAppointmentByID_InvalidID() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.findAppointmentByID(APP_ID);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("No appointment with this ID exists", eMSG);
        assertNull(appointment);   
    }

    @Test
    public void testFindAppointmentByID_NullID() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.findAppointmentByID(null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("No appointment with this ID exists", eMSG);
        assertNull(appointment);   
    }

    @Test
    public void testGetAppointmentByCarID_Success() {
        String eMSG = "";
        List <ServiceAppointment> appointments = null;

        try {
            appointments = service.getAppointmentsByCarID(A1.getCar().getCarID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("", eMSG);
        assertNotNull(appointments);
        assertNotNull(appointments.get(0));
        assertEquals(A1, appointments.get(0));
        assertEquals(A1.getServiceAppointmentID(), appointments.get(0).getServiceAppointmentID());
        assertEquals(A1.getCar().getCarID(), appointments.get(0).getCar().getCarID());
    }


    @Test
    public void testGetAppointmentByCarID_InvalidID() {
        String eMSG = "";
        List <ServiceAppointment> appointments = null;

        try {
            appointments = service.getAppointmentsByCarID(APP_CAR.getCarID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("No car with this ID exists", eMSG);
        assertNull(appointments);
    }


    @Test
    public void testGetAppointmentByCarID_NullID() {
        String eMSG = "";
        List <ServiceAppointment> appointments = null;

        try {
            appointments = service.getAppointmentsByCarID(null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("No car with this ID exists", eMSG);
        assertNull(appointments);
    }


    @Test
    public void testGetAppointmentByServiceID_Success() {
        String eMSG = "";
        List <ServiceAppointment> appointments = null;

        try {
            appointments = service.getAppointmentsByServiceID(A1.getService().getServiceID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("", eMSG);
        assertNotNull(appointments);
        assertNotNull(appointments.get(0));
        assertEquals(A1, appointments.get(0));
        assertEquals(A1.getServiceAppointmentID(), appointments.get(0).getServiceAppointmentID());
        assertEquals(A1.getService().getServiceID(), appointments.get(0).getService().getServiceID());
    }


    @Test
    public void testGetAppointmentByServiceID_InvalidID() {
        String eMSG = "";
        List <ServiceAppointment> appointments = null;

        try {
            appointments = service.getAppointmentsByServiceID(APP_SERVICE.getServiceID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("No appointment with this ID exists", eMSG);
        assertNull(appointments);
    }


    @Test
    public void testGetAppointmentByServiceID_NullID() {
        String eMSG = "";
        List <ServiceAppointment> appointments = null;

        try {
            appointments = service.getAppointmentsByServiceID(null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("No appointment with this ID exists", eMSG);
        assertNull(appointments);
    }

    @Test
    public void testGetAllAppointments() {
        List<ServiceAppointment> appointments = service.getAllAppointments();
        assertEquals(3, appointments.size());
        assertEquals(A1, appointments.get(0));
        assertEquals(A2, appointments.get(1));
        assertEquals(A3, appointments.get(2));
    }

    @Test
    public void testDeleteAppointment_Success() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.deleteAppointment(A1.getServiceAppointmentID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("", eMSG);
        assertNotNull(appointment);
        assertEquals(A1, appointment);
    }

    @Test
    public void testDeleteAppointment_InvalidID() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.deleteAppointment(APP_ID);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("No appointment with this ID exists", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testDeleteAppointment_NullID() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.deleteAppointment(null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("No appointment with this ID exists", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testUpdateAppointment_Success() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.updateAppointment(A1.getServiceAppointmentID(), APP_TIME, APP_STATUS, APP_GARAGE, APP_SERVICE, APP_CAR);
                    } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("", eMSG);
        assertNotNull(appointment);
        assertEquals(A1.getServiceAppointmentID(), appointment.getServiceAppointmentID());
        assertEquals(APP_TIME, appointment.getStartTime());
        assertEquals(APP_STATUS, appointment.getAppointmentStatus());
        assertEquals(APP_GARAGE.getGarageID(), appointment.getGarage().getGarageID());
        assertEquals(APP_SERVICE.getServiceID(), appointment.getService().getServiceID());
        assertEquals(APP_CAR.getCarID(), appointment.getCar().getCarID());
    }

    @Test
    public void testUpdateAppointment_NullID() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.updateAppointment(null, APP_TIME, APP_STATUS, APP_GARAGE, APP_SERVICE, APP_CAR);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("No appointment with this ID exists", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testUpdateAppointment_InvalidID() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.updateAppointment(APP_ID, APP_TIME, APP_STATUS, APP_GARAGE, APP_SERVICE, APP_CAR);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("No appointment with this ID exists", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testUpdateAppointment_NullStartTime() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.updateAppointment(A1.getServiceAppointmentID(), null, APP_STATUS, APP_GARAGE, APP_SERVICE, APP_CAR);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Start time cannot be empty", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testUpdateAppointment_NullStatus() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.updateAppointment(A1.getServiceAppointmentID(), APP_TIME, null, APP_GARAGE, APP_SERVICE, APP_CAR);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Status cannot be empty", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testUpdateAppointment_NullGarage() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.updateAppointment(A1.getServiceAppointmentID(), APP_TIME, APP_STATUS, null, APP_SERVICE, APP_CAR);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Garage cannot be empty", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testUpdateAppointment_NullService() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.updateAppointment(A1.getServiceAppointmentID(), APP_TIME, APP_STATUS, APP_GARAGE, null, APP_CAR);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Service cannot be empty", eMSG);
        assertNull(appointment);
    }

    @Test
    public void testUpdateAppointment_NullCar() {
        String eMSG = "";
        ServiceAppointment appointment = null;

        try {
            appointment = service.updateAppointment(A1.getServiceAppointmentID(), APP_TIME, APP_STATUS, APP_GARAGE, APP_SERVICE, null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Car cannot be empty", eMSG);
        assertNull(appointment);
    }


}
