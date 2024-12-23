
public class HourlyEmployee extends Employee 
{

	private double hourlyRate;
	private double hoursWorked;
	
	
	public HourlyEmployee(int id, String name, double hourlyRate, double hoursWorked) 
	{
		super(id,name);
		this.hourlyRate = hourlyRate;
		this.hoursWorked = hoursWorked;
	}
	
	public double calculateSalary() 
	{
		System.out.println("calculate Salary...");
		System.out.println("hourlyRate is:" + hourlyRate);
		System.out.println("hoursWorked is:" + hoursWorked);
		double totalSalary = hourlyRate * hoursWorked;
		System.out.print("total salary is:"+ totalSalary);
		
		return totalSalary ;
	}
	
	
}