import java.util.ArrayList;
import java.util.List;

public abstract class Employee {
    private int id;
    private String name;
    private List<Task> tasks; 

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void assignTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public abstract double calculateSalary();
}
