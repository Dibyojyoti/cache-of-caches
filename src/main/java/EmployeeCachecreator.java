public class EmployeeCachecreator {
 
  public static void main(String[] args) {
 
    EmployeeCacheManager  cacheManager = new  EmployeeCacheManager();
    cacheManager.setEmployeeAddres("M111", "E111", "adress");
    String address =  cacheManager.getEmployeeAddres("M111", "E111");
  }
}
