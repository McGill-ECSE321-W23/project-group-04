package ca.mcgill.ecse321.parkinglotbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.parkinglotbackend.dao.TimeSlotRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;
import ca.mcgill.ecse321.parkinglotbackend.service.TimeSlotService;

/**
 * @author Qin Xuan Xu
 * using template from tutorials
 */
@ExtendWith(MockitoExtension.class)
public class TestTimeSlotService {
    @Mock
    private TimeSlotRepository timeSlotRepository;
    @InjectMocks
    private TimeSlotService timeSlotService;

    // Data for testing
    private static final StaffAccount staffAccount1 = new StaffAccount();

    private static final long timeSlotId1 = 1L;
    private static final DayOfWeek dayOfTheWeek1 = DayOfWeek.MONDAY;
    private static final LocalTime startTime1 = LocalTime.of(8, 0);
    private static final LocalTime endTime1 = LocalTime.of(16, 0);

    private static final long timeSlotId2 = 2L;
    private static final DayOfWeek dayOfTheWeek2 = DayOfWeek.TUESDAY;
    private static final LocalTime startTime2 = LocalTime.of(8, 0);
    private static final LocalTime endTime2 = LocalTime.of(16, 0);

    private static final long timeSlotId3 = 3L;
    private static final DayOfWeek dayOfTheWeek3 = DayOfWeek.TUESDAY;
    private static final LocalTime startTime3 = LocalTime.of(18, 0);
    private static final LocalTime endTime3 = LocalTime.of(20, 0);

    private static final ParkingLotSoftwareSystem parkingLotSoftwareSystem1 = new ParkingLotSoftwareSystem();

    private static final long timeSlotId4 = 4L;
    private static final DayOfWeek dayOfTheWeek4 = DayOfWeek.THURSDAY;
    private static final LocalTime startTime4 = LocalTime.of(8, 0);
    private static final LocalTime endTime4 = LocalTime.of(16, 0);

    private static final long timeSlotId5 = 5L;
    private static final DayOfWeek dayOfTheWeek5 = DayOfWeek.THURSDAY;
    private static final LocalTime startTime5 = LocalTime.of(18, 0);
    private static final LocalTime endTime5 = LocalTime.of(19, 0);

    private static final DayOfWeek dayOfTheWeekNew = DayOfWeek.SATURDAY;
    private static final LocalTime startTimeNew = LocalTime.of(9, 0);
    private static final LocalTime endTimeNew = LocalTime.of(17, 0);

    private static final LocalTime startTimeOverlap = LocalTime.of(5, 0);
    private static final LocalTime endTimeOverlap = LocalTime.of(22, 0);

    private static final long timeSlotIdNonexistent = 69L;

    // Setup mock repository
    @BeforeEach
    public void setMockOutput() {
        lenient().when(timeSlotRepository.findTimeSlotByTimeSlotID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(timeSlotId1)) {
                return makeStaffTimeSlot(timeSlotId1, dayOfTheWeek1, startTime1, endTime1, staffAccount1);
            } else if (invocation.getArgument(0).equals(timeSlotId2)) {
                return makeStaffTimeSlot(timeSlotId2, dayOfTheWeek2, startTime2, endTime2, staffAccount1);
            } else if (invocation.getArgument(0).equals(timeSlotId3)) {
                return makeStaffTimeSlot(timeSlotId3, dayOfTheWeek3, startTime3, endTime3, staffAccount1);
            } else if (invocation.getArgument(0).equals(timeSlotId4)) {
                return makeOpenHours(timeSlotId4, dayOfTheWeek4, startTime4, endTime4, parkingLotSoftwareSystem1);
            } else if (invocation.getArgument(0).equals(timeSlotId5)) {
                return makeOpenHours(timeSlotId5, dayOfTheWeek5, startTime5, endTime5, parkingLotSoftwareSystem1);
            } else {
                return null;
            }
        });

        lenient().when(timeSlotRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
            timeSlots.add(makeStaffTimeSlot(timeSlotId1, dayOfTheWeek1, startTime1, endTime1, staffAccount1));
            timeSlots.add(makeStaffTimeSlot(timeSlotId2, dayOfTheWeek2, startTime2, endTime2, staffAccount1));
            timeSlots.add(makeStaffTimeSlot(timeSlotId3, dayOfTheWeek3, startTime3, endTime3, staffAccount1));
            timeSlots.add(makeOpenHours(timeSlotId4, dayOfTheWeek4, startTime4, endTime4, parkingLotSoftwareSystem1));
            timeSlots.add(makeOpenHours(timeSlotId5, dayOfTheWeek5, startTime5, endTime5, parkingLotSoftwareSystem1));
            return timeSlots;
        });

        lenient().when(timeSlotRepository.findTimeSlotByStaffAccountAccountID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(staffAccount1.getAccountID())) {
                List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
                timeSlots.add(makeStaffTimeSlot(timeSlotId1, dayOfTheWeek1, startTime1, endTime1, staffAccount1));
                timeSlots.add(makeStaffTimeSlot(timeSlotId2, dayOfTheWeek2, startTime2, endTime2, staffAccount1));
                timeSlots.add(makeStaffTimeSlot(timeSlotId3, dayOfTheWeek3, startTime3, endTime3, staffAccount1));
                return timeSlots;
            } else {
                return null;
            }
        });

        lenient().when(timeSlotRepository.findTimeSlotByStaffAccount(null)).thenAnswer((InvocationOnMock invocation) -> {
            List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
            timeSlots.add(makeOpenHours(timeSlotId4, dayOfTheWeek4, startTime4, endTime4, parkingLotSoftwareSystem1));
            timeSlots.add(makeOpenHours(timeSlotId5, dayOfTheWeek5, startTime5, endTime5, parkingLotSoftwareSystem1));
            return timeSlots;
        });

        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
        lenient().when(timeSlotRepository.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
    }

    // Helper function to create time slot with staff account
    private static TimeSlot makeStaffTimeSlot(long id, DayOfWeek dayOfTheWeek, LocalTime startTime, LocalTime endTime, StaffAccount staffAccount) {
        TimeSlot timeSlot = new TimeSlot(id, dayOfTheWeek, startTime, endTime);
        timeSlot.setStaffAccount(staffAccount);
        return timeSlot;
    }

    // Helper function to create time slot with system
    private static TimeSlot makeOpenHours(long id, DayOfWeek dayOfTheWeek, LocalTime startTime, LocalTime endTime, ParkingLotSoftwareSystem system) {
        TimeSlot timeSlot = new TimeSlot(id, dayOfTheWeek, startTime, endTime);
        timeSlot.setSystem(system);
        return timeSlot;
    }

    // Test creation of time slot for staff account
    @Test
    public void testCreateStaffTimeSlot() {
        TimeSlot timeSlot = null;
        try {
            timeSlot = timeSlotService.createTimeSlot(dayOfTheWeekNew, startTimeNew, endTimeNew, null, staffAccount1);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(timeSlot);
        assertEquals(dayOfTheWeekNew, timeSlot.getDayOfTheWeek());
        assertEquals(startTimeNew, timeSlot.getStartTime());
        assertEquals(endTimeNew, timeSlot.getEndTime());
        assertEquals(staffAccount1.getAccountID(), timeSlot.getStaffAccount().getAccountID());
        assertNull(timeSlot.getSystem());
    }

    // Test creation of time slot for system (open hours)
    @Test
    public void testCreateOpenHours() {
        TimeSlot timeSlot = null;
        try {
            timeSlot = timeSlotService.createTimeSlot(dayOfTheWeekNew, startTimeNew, endTimeNew, parkingLotSoftwareSystem1, null);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(timeSlot);
        assertEquals(dayOfTheWeekNew, timeSlot.getDayOfTheWeek());
        assertEquals(startTimeNew, timeSlot.getStartTime());
        assertEquals(endTimeNew, timeSlot.getEndTime());
        assertNull(timeSlot.getStaffAccount());
        assertEquals(parkingLotSoftwareSystem1.getParkingLotSoftwareSystemID(), timeSlot.getSystem().getParkingLotSoftwareSystemID());
    }

    // Test creation of time slot with end time before start time
    @Test
    public void testCreateTimeSlotWithEndTimeBeforeStartTime() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.createTimeSlot(dayOfTheWeekNew, endTimeNew, startTimeNew, parkingLotSoftwareSystem1, null);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("End time cannot be before start time!", error);
    }

    // Test creation of time slot with both staff account and system
    @Test
    public void testCreateTimeSlotWithBothStaffAccountAndSystem() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.createTimeSlot(dayOfTheWeekNew, startTimeNew, endTimeNew, parkingLotSoftwareSystem1, staffAccount1);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("TimeSlot must be associated with either a ParkingLotSoftwareSystem or a StaffAccount!", error);
    }

    // Test creation of time slot with neither staff account nor system
    @Test
    public void testCreateTimeSlotWithNeitherStaffAccountNorSystem() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.createTimeSlot(dayOfTheWeekNew, startTimeNew, endTimeNew, null, null);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("TimeSlot must be associated with either a ParkingLotSoftwareSystem or a StaffAccount!", error);
    }

    // Test creation of time slot for staff account with overlapping time slot
    @Test
    public void testCreateTimeSlotWithOverlappingTimeSlotForStaffAccount() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.createTimeSlot(dayOfTheWeek1, startTimeOverlap, endTimeOverlap, null, staffAccount1);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("TimeSlot overlaps with existing TimeSlot for this staff!", error);
    }

    // Test creation of time slot for system (open hours) with overlapping time slot
    @Test
    public void testCreateTimeSlotWithOverlappingTimeSlotForSystem() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.createTimeSlot(dayOfTheWeek4, startTimeOverlap, endTimeOverlap, parkingLotSoftwareSystem1, null);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("TimeSlot overlaps with existing TimeSlot for open hours!", error);
    }
    
    // Test get time slot by ID
    @Test
    public void testGetTimeSlot() {
        TimeSlot timeSlot = null;
        try {
            timeSlot = timeSlotService.getTimeSlot(timeSlotId1);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(timeSlot);
        assertEquals(timeSlotId1, timeSlot.getTimeSlotID());
        assertEquals(dayOfTheWeek1, timeSlot.getDayOfTheWeek());
        assertEquals(startTime1, timeSlot.getStartTime());
        assertEquals(endTime1, timeSlot.getEndTime());
        assertEquals(staffAccount1.getAccountID(), timeSlot.getStaffAccount().getAccountID());
        assertNull(timeSlot.getSystem());
    }

    // Test get time slot with invalid ID
    @Test
    public void testGetTimeSlotWithInvalidID() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.getTimeSlot(timeSlotIdNonexistent);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("No TimeSlot with this id exists!", error);
    }

    // Test get all time slots
    @Test
    public void testGetAllTimeSlots() {
        List<TimeSlot> timeSlots = null;
        try {
            timeSlots = timeSlotService.getAllTimeSlots();
        } catch (Exception e) {
            fail();
        }
        assertNotNull(timeSlots);
        assertEquals(5, timeSlots.size());
    }

    // Test get all time slots with staff account ID
    @Test
    public void testGetAllTimeSlotsWithStaffAccountID() {
        List<TimeSlot> timeSlots = null;
        try {
            timeSlots = timeSlotService.getTimeSlotsByStaffAccountID(staffAccount1.getAccountID());
        } catch (Exception e) {
            fail();
        }
        assertNotNull(timeSlots);
        assertEquals(3, timeSlots.size());
    }

    // Test get all time slots with invalid staff account ID
    @Test
    public void testGetAllTimeSlotsWithInvalidStaffAccountID() {
        List<TimeSlot> timeSlots = null;
        String error = null;
        try {
            timeSlots = timeSlotService.getTimeSlotsByStaffAccountID(staffAccount1.getAccountID()-69L);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlots);
        assertEquals("No StaffAccount with this id exists!", error);
    }

    // Test get all time slots with null staff account (open hours)
    @Test
    public void testGetAllTimeSlotsWithNullStaffAccount() {
        List<TimeSlot> timeSlots = null;
        try {
            timeSlots = timeSlotService.getAllOpenHours();
        } catch (Exception e) {
            fail();
        }
        assertNotNull(timeSlots);
        assertEquals(2, timeSlots.size());
    }

    // Test update time slot
    @Test
    public void testUpdateTimeSlot() {
        TimeSlot timeSlot = null;
        try {
            timeSlot = timeSlotService.updateTimeSlot(timeSlotId1, dayOfTheWeekNew, startTimeNew, endTimeNew);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(timeSlot);
        assertEquals(timeSlotId1, timeSlot.getTimeSlotID());
        assertEquals(dayOfTheWeekNew, timeSlot.getDayOfTheWeek());
        assertEquals(startTimeNew, timeSlot.getStartTime());
        assertEquals(endTimeNew, timeSlot.getEndTime());
        assertNull(timeSlot.getSystem());
        assertEquals(staffAccount1.getAccountID(), timeSlot.getStaffAccount().getAccountID());
    }

    // Test update time slot with invalid ID
    @Test
    public void testUpdateTimeSlotWithInvalidID() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.updateTimeSlot(timeSlotIdNonexistent, dayOfTheWeekNew, startTimeNew, endTimeNew);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("No TimeSlot with this id exists!", error);
    }

    // Test update time slot with end time before start time
    @Test
    public void testUpdateTimeSlotWithEndTimeBeforeStartTime() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.updateTimeSlot(timeSlotId1, dayOfTheWeekNew, endTimeNew, startTimeNew);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("End time cannot be before start time!", error);
    }

    // Test update staff time slot with overlapping time slot
    @Test
    public void testUpdateTimeSlotWithOverlappingTimeSlotForStaffAccount() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.updateTimeSlot(timeSlotId2, dayOfTheWeek2, startTimeOverlap, endTimeOverlap);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("TimeSlot overlaps with existing TimeSlot for this staff!", error);
    }

    // Test update open hours time slot with overlapping time slot
    @Test
    public void testUpdateTimeSlotWithOverlappingTimeSlotForSystem() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.updateTimeSlot(timeSlotId4, dayOfTheWeek4, startTimeOverlap, endTimeOverlap);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("TimeSlot overlaps with existing TimeSlot for open hours!", error);
    }

    // Test delete time slot
    @Test
    public void testDeleteTimeSlot() {
        TimeSlot timeSlot = null;
        try {
            timeSlot = timeSlotService.deleteTimeSlot(timeSlotId1);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(timeSlot);
        assertEquals(timeSlotId1, timeSlot.getTimeSlotID());
        assertEquals(dayOfTheWeek1, timeSlot.getDayOfTheWeek());
        assertEquals(startTime1, timeSlot.getStartTime());
        assertEquals(endTime1, timeSlot.getEndTime());
        assertEquals(staffAccount1.getAccountID(), timeSlot.getStaffAccount().getAccountID());
        assertNull(timeSlot.getSystem());
    }

    // Test delete time slot with invalid ID
    @Test
    public void testDeleteTimeSlotWithInvalidID() {
        TimeSlot timeSlot = null;
        String error = null;
        try {
            timeSlot = timeSlotService.deleteTimeSlot(timeSlotIdNonexistent);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(timeSlot);
        assertEquals("No TimeSlot with this id exists!", error);
    }

}
