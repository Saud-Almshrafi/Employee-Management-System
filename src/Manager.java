
public class Manager extends SalariedEmployee
{
	
	private double bonus;
	private int teamSize;
	
	public Manager(int id , String name ,double baseSalary ,double bonus ,int teamSize)
	{
		super(id,name,baseSalary);
		this.bonus = bonus;
		this.teamSize = teamSize;
	}
	
	public double calculateSalary()
	{
		
		double baseSalary = super.calculateSalary();
		System.out.println("Adding bonus for manager...");
		return baseSalary + bonus;
		
	}
	
}