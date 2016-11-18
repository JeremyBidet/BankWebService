import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.HashMap;

import javax.xml.rpc.ServiceException;

public class Bank {
	
	private HashMap<String, Account> accounts;
	
	public Bank() {
		this.accounts = new HashMap<String, Account>();
		// service account
		this.addAccount("UPEM", 0, "UPEM", "UPEM", "EUR");
		// users account
		this.addAccount("jbidet@etud.u-pem.fr", 10000, "Jeremy", "Bidet", "EUR");
		this.addAccount("eramos@etud.u-pem.fr", 5000, "Enzo", "Ramos", "RON");
	}
	
	public void addAccount(String login, double balance,String firstname, String lastname, String currency){
		accounts.put(login,new Account(login, balance, firstname, lastname, currency));
	}
	
	public void deleteAccount(String login){
		accounts.remove(login);
	}
	
	public boolean creditAccount(String login, String currency, double amount) throws ServiceException, RemoteException{
		Account current = accounts.get(login);
		if(current == null ){
			return false;
		}	
		current.creditAccount(RESTConverter.getRateExchange(currency, current.getCurrency()) * amount);

		return true;
	}
	
	
	public boolean debitAccount(String login, String currency, double amount) throws ServiceException, RemoteException{
		Account current = accounts.get(login);
		
		if(current == null){
			return false;
		}
		return current.debitAccount(RESTConverter.getRateExchange(currency, current.getCurrency()) * amount);
	}
	
	public double getBalance(String login, String currency) throws RemoteException, ServiceException{
		Account current = accounts.get(login);		
		return RESTConverter.getRateExchange(current.getCurrency(), currency) * current.getBalance();	
	}
	
	public String[] getCurrencies() {
		return RESTConverter.getCurrencies();
	}
	
	public boolean currencyExists(String currency) {
		return Arrays.asList(RESTConverter.getCurrencies()).contains(currency);
	}

}
