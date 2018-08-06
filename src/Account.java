
public class Account {

	// Atributi

	private int id = (int) (Math.random() * 100000);
	private String name;
	private double balance;

	// Konstruktor

	public Account() {

	}

	// getters

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}

	// setters

	public void setName(String name) {
		this.name = name;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// ponasanja

	public void povecajBalance(double iznos) {
		this.balance += iznos;
	}

	public void smanjiBalance(double iznos) {
		this.balance -= iznos;
	}
	
	public void info() {
		System.out.println("ID: " + this.id);
		System.out.println("Ime: " + getName());
		System.out.println("Stanje novca: " + this.balance + " KM");
	}

}
