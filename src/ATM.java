import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class ATM {
	static Scanner input = new Scanner(System.in); // skener
	static Scanner inputString = new Scanner(System.in);// skener za string
	static ArrayList<Account> users = new ArrayList<>(); // baza podataka

	public static double isDouble() { // handle exception za double brojeve
		while (true) {
			try {
				return input.nextDouble();
			} catch (InputMismatchException e) {
				input.next();
				System.out.println("Vas unos nije dobar. Probajte ponovo: ");
			}
		}
	}

	public static int isInteger() { // handle exception za integer brojeve
		while (true) {
			try {
				return input.nextInt();
			} catch (InputMismatchException e) {
				input.next();
				System.out.println("Vas unos nije dobar. Probajte ponovo: ");
			}
		}
	}

	public static boolean exist(int id) {
		boolean exist = false;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				exist = true;
				break;
			}
		}
		return exist;
	}

	public static void main(String[] args) throws Exception {

		int sourceAccount = -1;
		int targetAccount = -1;
		int opcija;
		File kartica = new File("/home/bega/eclipse-workspace/ATM/Kartice/");
		do { // GIU
			System.out.println("1 - Registracija korisnika, \n2 - Prijava korisnika \n0 - Izlaz \nIzaberite opciju: ");
			opcija = isInteger();

			switch (opcija) {
			case 0:
				break;
			case 1:
				Account account;
				do {
					account = new Account();
				} while (exist(account.getId()));
				System.out.println("Vas ID je: " + account.getId());
				System.out.println("Unsite vase ime: ");
				String ime = input.next();
				account.setName(ime);
				System.out.println("Unesite svotu novca koju uplacujete na racun: ");
				double novac = isDouble();
				account.setBalance(novac);
				System.out.println("Komentar: ");
				String komentar = inputString.nextLine();

				File karton = new File("/home/bega/eclipse-workspace/ATM/Kartoni/" + account.getName()); // pravljenje
																											// korisnikovog
																											// kartona
				PrintWriter output = new PrintWriter(karton);
				output.println("Karton korisnika " + account.getName());
				output.println();
				output.println("ID: " + account.getId());
				output.println("Pocetno stanje racuna: " + account.getBalance());
				output.println("Komentar: " + "\n" + komentar);
				output.close();

				kartica = new File("/home/bega/eclipse-workspace/ATM/Kartice/" + account.getName());
				PrintWriter outputKartica = new PrintWriter(kartica);
				outputKartica.println(account.getId());
				outputKartica.println("-----------------------");
				outputKartica.println("\t\t" + account.getName());
				outputKartica.println("-----------------------");
				outputKartica.close();
				users.add(account);
				break;
			case 2:
				Account source = users.get(0);
				Account target = users.get(0);
				System.out.println("1 - Prijava preko ID broja, \n2 - Prijava preko kartice ");
				int opcijaPrijave = isInteger();
				if (opcijaPrijave == 1) {
					do {
						System.out.println("Upisite svoj ID: ");
						sourceAccount = isInteger();
						if (exist(sourceAccount) == false) {
							System.out.println("Korisnik sa unijetim ID brojem ne postoji!");
						}
					} while (exist(sourceAccount) == false);
				}else if (opcijaPrijave == 2) {
					// Simulirat cemo ubacivanje kartice u bankomat tako sto ce korisnik upisati svoje ime, pa cemo po tome naci karticu
					System.out.println("Upisite svoje ime: ");
					String imeKorisnika = inputString.next();
					File korisnik = new File("/home/bega/eclipse-workspace/ATM/Kartice/" + imeKorisnika);
					Scanner inputKartica = new Scanner(korisnik);
					while (inputKartica.hasNext()) {
					sourceAccount = inputKartica.nextInt();
					break;
					}
					inputKartica.close();
				}else {
					System.out.println("Opcija nije dostupna.");
				}
				if (exist(sourceAccount)) {
					for (int i = 0; i < users.size(); i++) {
						if (users.get(i).getId() == sourceAccount) {
							source = users.get(i);
						}
					}
				}else {
					System.out.println("Korisnik ne postoji.");
				}
				System.out.println("1 - Transfer novca na drugi racun, \n2 - Uplata na racun, \n3 - Podizanje novca sa racuna, \n4 - Informacije o korisniku, \n5 - Isprintaj stanje racuna");
				int opcijeUnutarRacuna = isInteger();
				switch (opcijeUnutarRacuna) {
				case 1 : 
					do {
						System.out.println("Unesite ID racuna koji prima novac: ");
						targetAccount = isInteger();
						if (exist(targetAccount) == false) {
							System.out.println("Korisnik sa unijetim ID brojem ne postoji!");

						}
					} while (exist(targetAccount) == false);
					double money = 0;
					for (int i = 0; i < users.size(); i++) {
						if (users.get(i).getId() == targetAccount) {
							target = users.get(i);
						}
					}
					do {
						System.out.println("Koliko novca saljete: ");
						money = isDouble();
						if (money > source.getBalance()) {
							System.out.println("Nemate dovoljno sredstava na racunu, probajte sa nekim manjim iznosom!");
						}
					} while (money > source.getBalance());
					source.smanjiBalance(money);
					target.povecajBalance(money);
					break;
				
				case 2: 
					System.out.println("Koliko novca uplacujete: ");
					double uplata = isDouble();
					source.povecajBalance(uplata);
					System.out.println("Uspjesno ste uplatili " + uplata + " KM.");
					break;
					
				case 3:
					System.out.println("Koliko novca podizete: ");
					double isplata = isDouble();
					source.smanjiBalance(isplata);
					break;
					
				case 4:
					source.info();
					break;
				
				case 5: 
					source.ispisStanjaRacuna();
					break;
				default:
					System.out.println("Unos opcije nije dobar.");
					
				}
			}

		} while (opcija != 0);

		input.close();

		}
	}




