
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	public void info() throws FileNotFoundException, IOException {
		System.out.println("ID: " + this.id);
		System.out.println("Ime: " + getName());
		System.out.println("Stanje novca: " + this.balance + " KM");

		String komentar = Files.readAllLines(Paths.get("/home/bega/eclipse-workspace/ATM/Kartoni/" + getName())).get(5);

		System.out.println("Komentar: \n" + komentar);

	}

	public void ispisStanjaRacuna() throws Exception {
		File racun = new File("/home/bega/eclipse-workspace/ATM/Racun");
		PrintWriter output = new PrintWriter(racun);
		output.println("Stanje racuna");
		output.println("--------------------------");
		output.println("ID: " + getId());
		output.println("Ime: " + getName());
		output.println("Stanje racuna: " + getBalance());
		output.close();
	}

}
