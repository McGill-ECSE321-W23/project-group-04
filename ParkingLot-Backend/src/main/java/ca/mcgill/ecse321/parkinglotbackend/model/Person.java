/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse321.parkinglotbackend.model;
import java.util.*;

// line 16 "../../../../../ParkingLot.ump"
public class Person
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Person> personsByPhoneNumber = new HashMap<String, Person>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String phoneNumber;
  private String name;

  //Person Associations
  private List<Role> roles;
  private List<Car> cars;
  private List<ServiceRequest> requests;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aPhoneNumber, String aName, Role... allRoles)
  {
    name = aName;
    if (!setPhoneNumber(aPhoneNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate phoneNumber. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    roles = new ArrayList<Role>();
    boolean didAddRoles = setRoles(allRoles);
    if (!didAddRoles)
    {
      throw new RuntimeException("Unable to create Person, must have 1 to 2 roles. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    cars = new ArrayList<Car>();
    requests = new ArrayList<ServiceRequest>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    String anOldPhoneNumber = getPhoneNumber();
    if (anOldPhoneNumber != null && anOldPhoneNumber.equals(aPhoneNumber)) {
      return true;
    }
    if (hasWithPhoneNumber(aPhoneNumber)) {
      return wasSet;
    }
    phoneNumber = aPhoneNumber;
    wasSet = true;
    if (anOldPhoneNumber != null) {
      personsByPhoneNumber.remove(anOldPhoneNumber);
    }
    personsByPhoneNumber.put(aPhoneNumber, this);
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  /* Code from template attribute_GetUnique */
  public static Person getWithPhoneNumber(String aPhoneNumber)
  {
    return personsByPhoneNumber.get(aPhoneNumber);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithPhoneNumber(String aPhoneNumber)
  {
    return getWithPhoneNumber(aPhoneNumber) != null;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template association_GetMany */
  public Role getRole(int index)
  {
    Role aRole = roles.get(index);
    return aRole;
  }

  public List<Role> getRoles()
  {
    List<Role> newRoles = Collections.unmodifiableList(roles);
    return newRoles;
  }

  public int numberOfRoles()
  {
    int number = roles.size();
    return number;
  }

  public boolean hasRoles()
  {
    boolean has = roles.size() > 0;
    return has;
  }

  public int indexOfRole(Role aRole)
  {
    int index = roles.indexOf(aRole);
    return index;
  }
  /* Code from template association_GetMany */
  public Car getCar(int index)
  {
    Car aCar = cars.get(index);
    return aCar;
  }

  public List<Car> getCars()
  {
    List<Car> newCars = Collections.unmodifiableList(cars);
    return newCars;
  }

  public int numberOfCars()
  {
    int number = cars.size();
    return number;
  }

  public boolean hasCars()
  {
    boolean has = cars.size() > 0;
    return has;
  }

  public int indexOfCar(Car aCar)
  {
    int index = cars.indexOf(aCar);
    return index;
  }
  /* Code from template association_GetMany */
  public ServiceRequest getRequest(int index)
  {
    ServiceRequest aRequest = requests.get(index);
    return aRequest;
  }

  public List<ServiceRequest> getRequests()
  {
    List<ServiceRequest> newRequests = Collections.unmodifiableList(requests);
    return newRequests;
  }

  public int numberOfRequests()
  {
    int number = requests.size();
    return number;
  }

  public boolean hasRequests()
  {
    boolean has = requests.size() > 0;
    return has;
  }

  public int indexOfRequest(ServiceRequest aRequest)
  {
    int index = requests.indexOf(aRequest);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRoles()
  {
    return 1;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfRoles()
  {
    return 2;
  }
  /* Code from template association_AddUnidirectionalMN */
  public boolean addRole(Role aRole)
  {
    boolean wasAdded = false;
    if (roles.contains(aRole)) { return false; }
    if (numberOfRoles() < maximumNumberOfRoles())
    {
      roles.add(aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean removeRole(Role aRole)
  {
    boolean wasRemoved = false;
    if (!roles.contains(aRole))
    {
      return wasRemoved;
    }

    if (numberOfRoles() <= minimumNumberOfRoles())
    {
      return wasRemoved;
    }

    roles.remove(aRole);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMN */
  public boolean setRoles(Role... newRoles)
  {
    boolean wasSet = false;
    ArrayList<Role> verifiedRoles = new ArrayList<Role>();
    for (Role aRole : newRoles)
    {
      if (verifiedRoles.contains(aRole))
      {
        continue;
      }
      verifiedRoles.add(aRole);
    }

    if (verifiedRoles.size() != newRoles.length || verifiedRoles.size() < minimumNumberOfRoles() || verifiedRoles.size() > maximumNumberOfRoles())
    {
      return wasSet;
    }

    roles.clear();
    roles.addAll(verifiedRoles);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRoleAt(Role aRole, int index)
  {  
    boolean wasAdded = false;
    if(addRole(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoleAt(Role aRole, int index)
  {
    boolean wasAdded = false;
    if(roles.contains(aRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRoles()) { index = numberOfRoles() - 1; }
      roles.remove(aRole);
      roles.add(index, aRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoleAt(aRole, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCars()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addCar(Car aCar)
  {
    boolean wasAdded = false;
    if (cars.contains(aCar)) { return false; }
    cars.add(aCar);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCar(Car aCar)
  {
    boolean wasRemoved = false;
    if (cars.contains(aCar))
    {
      cars.remove(aCar);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCarAt(Car aCar, int index)
  {  
    boolean wasAdded = false;
    if(addCar(aCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCars()) { index = numberOfCars() - 1; }
      cars.remove(aCar);
      cars.add(index, aCar);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCarAt(Car aCar, int index)
  {
    boolean wasAdded = false;
    if(cars.contains(aCar))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCars()) { index = numberOfCars() - 1; }
      cars.remove(aCar);
      cars.add(index, aCar);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCarAt(aCar, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRequests()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addRequest(ServiceRequest aRequest)
  {
    boolean wasAdded = false;
    if (requests.contains(aRequest)) { return false; }
    requests.add(aRequest);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRequest(ServiceRequest aRequest)
  {
    boolean wasRemoved = false;
    if (requests.contains(aRequest))
    {
      requests.remove(aRequest);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRequestAt(ServiceRequest aRequest, int index)
  {  
    boolean wasAdded = false;
    if(addRequest(aRequest))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRequests()) { index = numberOfRequests() - 1; }
      requests.remove(aRequest);
      requests.add(index, aRequest);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRequestAt(ServiceRequest aRequest, int index)
  {
    boolean wasAdded = false;
    if(requests.contains(aRequest))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRequests()) { index = numberOfRequests() - 1; }
      requests.remove(aRequest);
      requests.add(index, aRequest);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRequestAt(aRequest, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    personsByPhoneNumber.remove(getPhoneNumber());
    roles.clear();
    cars.clear();
    requests.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "name" + ":" + getName()+ "]";
  }
}