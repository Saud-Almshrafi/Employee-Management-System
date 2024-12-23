import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeManagementSystem {
    private HashMap<Integer, Department> departments = new HashMap<>();

    public boolean addEmployeeToDepartment(int departmentID, Employee employee) {
        if (!departments.containsKey(departmentID)) {
            System.out.println("Department with ID " + departmentID + " does not exist.");
            return false; // Return false if department does not exist
        }
        Department department = departments.get(departmentID);
        return department.addEmployee(employee); // Add the employee to the department
    }

    public boolean addDepartment(int departmentID, String departmentName, Manager departmentHead) {
        if (departments.containsKey(departmentID)) {
            System.out.println("Department with ID " + departmentID + " already exists.");
            return false; // Return false if department already exists
        }
        departments.put(departmentID, new Department(departmentID, departmentName, departmentHead));
        return true;
    }

    public List<Employee> getEmployeesByDepartment(int departmentID) {
        if (!departments.containsKey(departmentID)) {
            System.out.println("Department with ID " + departmentID + " does not exist.");
            return new ArrayList<>(); // Return an empty list if department does not exist
        }
        return departments.get(departmentID).getEmployees(); // Return employees in the department
    }

    public void listDepartments() {
        if (departments.isEmpty()) {
            System.out.println("No departments available.");
            return; // Exit if there are no departments
        }
        for (int departmentID : departments.keySet()) {
            Department dept = departments.get(departmentID);
            System.out.println("ID: " + departmentID + ", Name: " + dept.getDepartmentName());
        }
    }

    public double calculateTotalSalary() {
        double totalSalary = 0.0;
        for (Department dept : departments.values()) {
            for (Employee emp : dept.getEmployees()) {
                totalSalary += emp.calculateSalary(); // Sum up salaries for all employees
            }
        }
        return totalSalary; // Return total salary
    }

    public double getTotalSalaryByDepartment(int departmentID) {
        double totalSalary = 0.0;
        if (departments.containsKey(departmentID)) {
            for (Employee emp : departments.get(departmentID).getEmployees()) {
                totalSalary += emp.calculateSalary(); // Sum up salaries for the specific department
            }
        }
        return totalSalary; // Return total salary for the department
    }

    public boolean assignTaskToEmployee(int employeeID, Task task) {
        for (Department dept : departments.values()) {
            for (Employee emp : dept.getEmployees()) {
                if (emp.getID() == employeeID) {
                    emp.assignTask(task); // Assign task to employee if found
                    return true;
                }
            }
        }
        return false; // Return false if employee not found
    }

    public List<Task> getTasksForEmployee(int employeeID) {
        for (Department dept : departments.values()) {
            for (Employee emp : dept.getEmployees()) {
                if (emp.getID() == employeeID) {
                    return emp.getTasks(); // Return the tasks of the employee if found
                }
            }
        }
        return new ArrayList<>(); // Return an empty list if employee not found
    }

    public boolean removeEmployeeFromDepartment(int departmentID, int employeeID) {
        if (!departments.containsKey(departmentID)) {
            System.out.println("Department with ID " + departmentID + " does not exist.");
            return false;
        }
    
        Department department = departments.get(departmentID);
        
        for (Employee emp : department.getEmployees()) {
            if (emp.getID() == employeeID) {
                department.removeEmployee(emp);
                return true; 
            }
        }
    
        System.out.println("Employee with ID " + employeeID + " not found in the department.");
        return false;  
    }
}
