import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
	
	public static void main(String[] args) {
		ArrayList<Account> users = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		String opcija = new String();
		
		do {
			System.out.println("1 - Registracija korisnika, \n2 - Transfer novca, \n3 - Informacije o korisniku, \n0 - Izlaz \nIzaberite opciju: ");
			opcija = input.next();
			
			switch(Integer.parseInt(opcija)) {
			case 0 : break;
			case 1 : 
				Account account = new Account();
				System.out.println("Vas ID je: " + account.getId());
				System.out.println("Unsite vase ime: ");
				String ime = input.nextLine();
				input.next();
				account.setName(ime);
				System.out.println("Unesite svotu novca koju uplacujete na racun: ");
				double novac = input.nextDouble();
				account.setBalance(novac);
				users.add(account);
				break;
			}
		}while(Integer.parseInt(opcija) != 0);
		
		

	}

}
