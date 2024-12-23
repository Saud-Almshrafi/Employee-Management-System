import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class App {
    private EmployeeManagementSystem managementSystem;
    private JFrame frame;

    public App() {
        managementSystem = new EmployeeManagementSystem();
        frame = new JFrame("Employee and Department Management");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JButton addEmployeeButton = new JButton("Add Employee");
        JButton viewEmployeesButton = new JButton("View Employees");
        JButton removeEmployeeButton = new JButton("Remove Employee");
        JButton addDepartmentButton = new JButton("Add Department");
        JButton viewDepartmentsButton = new JButton("View Departments");
        JButton assignTaskButton = new JButton("Assign Task");
        JButton viewTasksButton = new JButton("View Tasks");
        JButton totalSalaryButton = new JButton("Total Salary");
        JButton departmentSalaryButton = new JButton("Department Salary");

        frame.getContentPane().setBackground(Color.decode("#F5F5F5"));
        panel.setBackground(Color.decode("#F5F5F5"));

        Color buttonColor = Color.decode("#4CAF50");
        Color textColor = Color.decode("#FFFFFF");

        JButton[] buttons = {addEmployeeButton, viewEmployeesButton, removeEmployeeButton, addDepartmentButton,
                             viewDepartmentsButton, assignTaskButton, viewTasksButton, totalSalaryButton, departmentSalaryButton};

        for (JButton button : buttons) {
            button.setBackground(buttonColor);
            button.setForeground(textColor);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setFocusPainted(false);
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);

        addEmployeeButton.addActionListener(e -> showAddEmployeeDialog());
        viewEmployeesButton.addActionListener(e -> showEmployeesInDepartment());
        removeEmployeeButton.addActionListener(e -> showRemoveEmployeeDialog());
        addDepartmentButton.addActionListener(e -> showAddDepartmentDialog());
        viewDepartmentsButton.addActionListener(e -> showDepartments());
        assignTaskButton.addActionListener(e -> showAssignTaskDialog());
        viewTasksButton.addActionListener(e -> showViewTasksDialog());
        totalSalaryButton.addActionListener(e -> showTotalSalary());
        departmentSalaryButton.addActionListener(e -> showDepartmentSalary());

        frame.setVisible(true);
    }
    

    private void showAddEmployeeDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField salaryField = new JTextField();
        JTextField departmentIDField = new JTextField();

        Object[] message = {
            "Employee ID:", idField,
            "Employee Name:", nameField,
            "Employee Type (Hourly/Salaried):", typeField,
            "Salary or Hourly Rate:", salaryField,
            "Department ID:", departmentIDField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String type = typeField.getText().toLowerCase();
                double salary = Double.parseDouble(salaryField.getText());
                int departmentID = Integer.parseInt(departmentIDField.getText());

                Employee employee;
                if (type.equals("hourly")) {
                    employee = new HourlyEmployee(id, name, salary, 40);
                } else if (type.equals("salaried")) {
                    employee = new SalariedEmployee(id, name, salary);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Employee Type!");
                    return;
                }

                if (managementSystem.addEmployeeToDepartment(departmentID, employee)) {
                    JOptionPane.showMessageDialog(frame, "Employee added successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add employee. Check department ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid values.");
            }
        }
    }

    private void showEmployeesInDepartment() {
        String departmentIDStr = JOptionPane.showInputDialog(frame, "Enter Department ID:");
        if (departmentIDStr != null) {
            try {
                int departmentID = Integer.parseInt(departmentIDStr);
                List<Employee> employees = managementSystem.getEmployeesByDepartment(departmentID);
                if (employees.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No employees found in this department.");
                } else {
                    StringBuilder employeeList = new StringBuilder("Employees in Department " + departmentID + ":\n");
                    for (Employee emp : employees) {
                        employeeList.append("ID: ").append(emp.getID())
                                .append(", Name: ").append(emp.getName())
                                .append(", Salary: ").append(emp.calculateSalary())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, employeeList.toString(), "Employee List", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid Department ID.");
            }
        }
    }

    private void showRemoveEmployeeDialog() {
        JTextField departmentIDField = new JTextField();
        JTextField employeeIDField = new JTextField();

        Object[] message = {
            "Department ID:", departmentIDField,
            "Employee ID:", employeeIDField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Remove Employee", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int departmentID = Integer.parseInt(departmentIDField.getText());
                int employeeID = Integer.parseInt(employeeIDField.getText());
                if (managementSystem.removeEmployeeFromDepartment(departmentID, employeeID)) {
                    JOptionPane.showMessageDialog(frame, "Employee removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to remove employee. Check department and employee ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid values.");
            }
        }
    }

    private void showAddDepartmentDialog() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField managerIDField = new JTextField();
        JTextField managerNameField = new JTextField();
        JTextField managerSalaryField = new JTextField();

        Object[] message = {
            "Department ID:", idField,
            "Department Name:", nameField,
            "Manager ID:", managerIDField,
            "Manager Name:", managerNameField,
            "Manager Salary:", managerSalaryField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Add Department", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int managerID = Integer.parseInt(managerIDField.getText());
                String managerName = managerNameField.getText();
                double managerSalary = Double.parseDouble(managerSalaryField.getText());

                Manager manager = new Manager(managerID, managerName, managerSalary, 1000, 10);
                if (managementSystem.addDepartment(id, name, manager)) {
                    JOptionPane.showMessageDialog(frame, "Department added successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to add department. Department ID already exists.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid values.");
            }
        }
    }

    private void showDepartments() {
        managementSystem.listDepartments();
    }

    private void showAssignTaskDialog() {
        JTextField employeeIDField = new JTextField();
        JTextField taskIDField = new JTextField();
        JTextField priorityField = new JTextField();
        JTextField descriptionField = new JTextField();

        Object[] message = {
            "Employee ID:", employeeIDField,
            "Task ID:", taskIDField,
            "Priority:", priorityField,
            "Description:", descriptionField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Assign Task", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                int employeeID = Integer.parseInt(employeeIDField.getText());
                int taskID = Integer.parseInt(taskIDField.getText());
                int priority = Integer.parseInt(priorityField.getText());
                String description = descriptionField.getText();

                Task task = new Task(taskID, priority, description);
                if (managementSystem.assignTaskToEmployee(employeeID, task)) {
                    JOptionPane.showMessageDialog(frame, "Task assigned successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to assign task. Check Employee ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter valid values.");
            }
        }
    }

    private void showViewTasksDialog() {
        String employeeIDStr = JOptionPane.showInputDialog(frame, "Enter Employee ID:");
        if (employeeIDStr != null) {
            try {
                int employeeID = Integer.parseInt(employeeIDStr);
                List<Task> tasks = managementSystem.getTasksForEmployee(employeeID);
                if (tasks.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No tasks found for this employee.");
                } else {
                    StringBuilder taskList = new StringBuilder("Tasks for Employee " + employeeID + ":\n");
                    for (Task task : tasks) {
                        taskList.append("Task ID: ").append(task.getTaskId())
                                .append(", Priority: ").append(task.getPriority())
                                .append(", Description: ").append(task.getDescription())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, taskList.toString(), "Task List", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid Employee ID.");
            }
        }
    }

    private void showTotalSalary() {
        double totalSalary = managementSystem.calculateTotalSalary();
        JOptionPane.showMessageDialog(frame, "Total Salary for All Departments: $" + totalSalary);
    }

    private void showDepartmentSalary() {
        String departmentIDStr = JOptionPane.showInputDialog(frame, "Enter Department ID:");
        if (departmentIDStr != null) {
            try {
                int departmentID = Integer.parseInt(departmentIDStr);
                double totalSalary = managementSystem.getTotalSalaryByDepartment(departmentID);
                JOptionPane.showMessageDialog(frame, "Total Salary for Department " + departmentID + ": $" + totalSalary);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid Department ID.");
            }
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
