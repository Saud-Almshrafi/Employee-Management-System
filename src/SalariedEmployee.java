
public class SalariedEmployee extends Employee 
{

	private double baseSalary;
	
	public SalariedEmployee(int id , String name ,double baseSalary)
	{
		super(id,name);
		this.baseSalary = baseSalary;
	}
	
	
	

	public double calculateSalary()
	{
		return baseSalary;	
	}

	
	
}