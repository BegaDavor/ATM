package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import atm.Account;

public class AccountTest {

	Account account;
	double balance;
	
	@Before
	public void setUp() {
		this.account = new Account();
		account.setBalance(100);
		this.balance = 50;
	}
	
	@Test
	public void whenThisBalanceIs50AccountBalanceShouldBe150() {
		account.povecajBalance(balance);
		assertEquals(150, account.getBalance(), Math.abs(150 - account.getBalance()));
	}
	
	@Test
	public void whenThisBalanceIs50AccountBalanceShouldBe50() {
		account.smanjiBalance(balance);
		assertEquals(50, account.getBalance(), Math.abs(50 - account.getBalance()));
	}
	
	// test for getters and setters
	
	@Test
	public void setNameTest(){
		account.setName("Zgembo");
		String name = account.getName();
		assertEquals("Zgembo", name);
	}
	
}
