/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;
import java.util.*;

// line 36 "../../../../../ParkingLot.ump"
public class EmployeeRole extends Role
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //EmployeeRole Attributes
  private float salary;

  //EmployeeRole Associations
  private List<TimeSlot> schedule;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public EmployeeRole(float aSalary)
  {
    super();
    salary = aSalary;
    schedule = new ArrayList<TimeSlot>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSalary(float aSalary)
  {
    boolean wasSet = false;
    salary = aSalary;
    wasSet = true;
    return wasSet;
  }

  public float getSalary()
  {
    return salary;
  }
  /* Code from template association_GetMany */
  public TimeSlot getSchedule(int index)
  {
    TimeSlot aSchedule = schedule.get(index);
    return aSchedule;
  }

  public List<TimeSlot> getSchedule()
  {
    List<TimeSlot> newSchedule = Collections.unmodifiableList(schedule);
    return newSchedule;
  }

  public int numberOfSchedule()
  {
    int number = schedule.size();
    return number;
  }

  public boolean hasSchedule()
  {
    boolean has = schedule.size() > 0;
    return has;
  }

  public int indexOfSchedule(TimeSlot aSchedule)
  {
    int index = schedule.indexOf(aSchedule);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSchedule()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addSchedule(TimeSlot aSchedule)
  {
    boolean wasAdded = false;
    if (schedule.contains(aSchedule)) { return false; }
    schedule.add(aSchedule);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSchedule(TimeSlot aSchedule)
  {
    boolean wasRemoved = false;
    if (schedule.contains(aSchedule))
    {
      schedule.remove(aSchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addScheduleAt(TimeSlot aSchedule, int index)
  {  
    boolean wasAdded = false;
    if(addSchedule(aSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSchedule()) { index = numberOfSchedule() - 1; }
      schedule.remove(aSchedule);
      schedule.add(index, aSchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveScheduleAt(TimeSlot aSchedule, int index)
  {
    boolean wasAdded = false;
    if(schedule.contains(aSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSchedule()) { index = numberOfSchedule() - 1; }
      schedule.remove(aSchedule);
      schedule.add(index, aSchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addScheduleAt(aSchedule, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    schedule.clear();
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "salary" + ":" + getSalary()+ "]";
  }
}