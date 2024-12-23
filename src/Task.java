
public class Task
{
	
	private int taskId;
	private int priority;
	private String description;
	
	public Task (int taskId, int priority, String description) {
		this.taskId = taskId;
		this.priority = priority;
		this.description = description;
	}
	
	
	public int getTaskId() {
		return taskId;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	
}