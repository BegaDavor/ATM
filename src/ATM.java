import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
	static Scanner input = new Scanner(System.in);
	
	public static double isDouble() {
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
	
	public static int isInteger() {
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

	public static void main(String[] args) {
		ArrayList<Account> users = new ArrayList<>();
		
		int opcija;
		do {
			System.out.println(
					"1 - Registracija korisnika, \n2 - Transfer novca, \n3 - Informacije o korisniku, \n0 - Izlaz \nIzaberite opciju: ");
			opcija = isInteger();

			switch (opcija) {
			case 0:
				break;
			case 1:
				Account account = new Account();
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
				System.out.println("Unsite ID racuna koji salje novac: ");
				int sourceAccount = input.nextInt();
				System.out.println("Unesite ID racuna koji prima novac: ");
				int targetAccount = input.nextInt();
				
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
				money = input.nextDouble();
				if (money > source.getBalance()) {
					System.out.println("Nemate dovoljno sredstava na racunu, probajte sa nekim manjim iznosom!");
				}
				}while (money > source.getBalance()); 
				source.smanjiBalance(money);
				target.povecajBalance(money);
				break;
			case 3:
				System.out.println("Upisite svoj ID: ");
				int idTemp = input.nextInt();
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getId() == idTemp) {
						users.get(i).info();
						break;
					} else {
						System.out.println("Korisnik sa unijetim ID ne postoji.");
					}
				}
				break;
			}
		} while (opcija != 0);

		input.close();

	}

}
