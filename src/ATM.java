import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
	static Scanner input = new Scanner(System.in); // skener
	static ArrayList<Account> users = new ArrayList<>(); // baza podataka
	
	public static double isDouble() { // handle exception za double brojeve
		while (true) {
			try {
				return input.nextDouble();
			}
			catch (InputMismatchException e) {
				input.next();
				System.out.println("Vas unos nije dobar. Probajte ponovo: ");
			}
		}
	}
	
	public static int isInteger() { // handle exception za integer brojeve
		while (true) {
			try {
				return input.nextInt();
			}
			catch (InputMismatchException e) {
				input.next();
				System.out.println("Vas unos nije dobar. Probajte ponovo: ");
			}
		}
	}
	
	public static boolean exist(int id) {
		boolean exist = false;
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getId() == id) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	public static void main(String[] args) {
		
		int sourceAccount = -1;
		int targetAccount = -1;
		int opcija;
		do { // GIU
			System.out.println(
					"1 - Registracija korisnika, \n2 - Transfer novca, \n3 - Informacije o korisniku, \n0 - Izlaz \nIzaberite opciju: ");
			opcija = isInteger();

			switch (opcija) {
			case 0:
				break;
			case 1:
				Account account;
				do {
				account = new Account();
				} while(exist(account.getId()));
				System.out.println("Vas ID je: " + account.getId());
				System.out.println("Unsite vase ime: ");
				String ime = input.next();
				account.setName(ime);
				System.out.println("Unesite svotu novca koju uplacujete na racun: ");
				double novac = isDouble();
				account.setBalance(novac);
				users.add(account);
				break;
			case 2:
				do {
				System.out.println("Unsite ID racuna koji salje novac: ");
				sourceAccount = isInteger();
				if (exist(sourceAccount) == false) {
					System.out.println("Korisnik sa unijetim ID brojem ne postoji!");
				}
				} while (exist(sourceAccount) == false);
				do {
				System.out.println("Unesite ID racuna koji prima novac: ");
				targetAccount = isInteger();
				if (exist(targetAccount) == false) {
					System.out.println("Korisnik sa unijetim ID brojem ne postoji!");
	
				}
				} while (exist(targetAccount) == false);
				Account source = users.get(0);
				Account target = users.get(0);
				double money = 0;
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getId() == sourceAccount) {
						source = users.get(i);
					} else if (users.get(i).getId() == targetAccount) {
						target = users.get(i);
					}
				}
				do {
				System.out.println("Koliko novca saljete: ");
				money = isDouble();
				if (money > source.getBalance()) {
					System.out.println("Nemate dovoljno sredstava na racunu, probajte sa nekim manjim iznosom!");
				}
				}while (money > source.getBalance()); 
				source.smanjiBalance(money);
				target.povecajBalance(money);
				break;
			case 3:
				System.out.println("Upisite svoj ID: ");
				int idTemp = isInteger();
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getId() == idTemp) {
						users.get(i).info();
						break;
					} else {
						System.out.println("Korisnik sa unijetim ID ne postoji.");
					}
				}
				break;
			default: 
				System.out.println("Unos opcije nije dobar.");
			}
			
		} while (opcija != 0);

		input.close();

	}

}
