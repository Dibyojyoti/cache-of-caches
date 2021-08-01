import com.google.common.cache.Cache;
import EmployeeCacheManager.Employee;
public class EmployeeCacheManager extends CacheManager<String, Employee> {
 
  public String getEmployeeAddress(String managerId, String employeeId) {
 
    Cache< String, Employee > employeeCache = getEntityCache(managerId);
    Employee employee = getEmployee(employeeCache, employeeId);
    String address = getEmployeeAddress(employee);
    return address; 
  }
  public void setEmployeeAddress(String managerId, String employeeId, String address) {
 
    Cache< String, Employee > employeeCache = getEntityCache(managerId);
    Employee employee = getEmployee(employeeCache, employeeId);
    setEmployeeAddress(employee, address);
  }
  public Employee getEmployee(Cache< String, Employee > employeeCache, 
     String employeeId) {
    Employee employee =  employeeCache.getIfPresent( employeeId );
    if( employee == null) {
      synchronized( employee ) {
         employee =  employeeCache.getIfPresent( employeeId );
          if( employee == null) {
            employee = new Employee(employeeId);
            employeeCache.put( employeeId , employee);
         }
      }
     }
     return employee;
  } 
  public String getEmployeeAddress(Employee employee) { 
return employee.getAddress();
  }
  public void setEmployeeAddress( Employee employee, String address ) {
employee.setAddress(address);
}
 
  public class Employee {
    String name;
    String address;
    String id;
    public class Employee(id) { this.id = id;}
     public String getName() {return this.name;}
     public String getAddress() {return this.address;}
     public String getId() {this.id;}
     public void setName(String name) {this.name = name;}
     public void setAddress(String address) {this.address = address;}
  }
}
