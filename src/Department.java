import java.util.ArrayList;
import java.util.List;

public class Department {
    private int departmentId;
    private String departmentName;
    private Manager departmentHead;
    private List<Employee> employees;

    public Department(int departmentId, String departmentName, Manager departmentHead) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentHead = departmentHead;
        this.employees = new ArrayList<>();
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Manager getDepartmentHead() {
        return departmentHead;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDepartmentHead(Manager departmentHead) {
        this.departmentHead = departmentHead;
    }

    public boolean addEmployee(Employee employee) {
        if (employees.contains(employee)) {
            System.out.println("Employee with ID " + employee.getID() + " already exists in the department.");
            return false;
        }
        employees.add(employee);
        System.out.println("Employee added successfully to the department: " + employee.getName());
        return true;
    }

    public boolean removeEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            System.out.println("Employee with ID " + employee.getID() + " does not exist in the department.");
            return false;
        }
        employees.remove(employee);
        System.out.println("Employee removed successfully from the department: " + employee.getName());
        return true;
    }
}
