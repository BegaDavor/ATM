import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		ArrayList<Account> users = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		String opcija = new String();

		do {
			System.out.println(
					"1 - Registracija korisnika, \n2 - Transfer novca, \n3 - Informacije o korisniku, \n0 - Izlaz \nIzaberite opciju: ");
			opcija = input.next();

			switch (Integer.parseInt(opcija)) {
			case 0:
				break;
			case 1:
				Account account = new Account();
				System.out.println("Vas ID je: " + account.getId());
				System.out.println("Unsite vase ime: ");
				String ime = input.next();
				account.setName(ime);
				System.out.println("Unesite svotu novca koju uplacujete na racun: ");
				double novac = input.nextDouble();
				account.setBalance(novac);
				users.add(account);
				break;
			case 2:
				System.out.println("Unsite ID racuna koji salje novac: ");
				int sourceAccount = input.nextInt();
				System.out.println("Unesite ID racuna koji prima novac: ");
				int targetAccount = input.nextInt();
				System.out.println("Koliko novca saljete: ");
				double money = input.nextDouble();
				Account source = users.get(0);
				Account target = users.get(0);
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getId() == sourceAccount) {
						source = users.get(i);
					} else if (users.get(i).getId() == targetAccount) {
						target = users.get(i);
					}
				}
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
		} while (Integer.parseInt(opcija) != 0);

		input.close();

	}

}
