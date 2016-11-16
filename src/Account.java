public class Account {

	private double balance;
	
	private final String 	login;
	private final String	firstname;
	private final String	lastname;
	private final String	currency;
	
	public Account(String login, double balance, String firstname, String lastname,String currency) {
		this.balance = balance;
		this.login = login;
		this.firstname = firstname;
		this.lastname = lastname;
		this.currency = currency;
	}
	
	public double getBalance(){
		return this.balance;
	}
	
	public void creditAccount(double val){
		this.balance += val;
	}
	
	public boolean debitAccount(double val){
		if(enoughtFund(val)){
			this.balance -= val;
			return true;
		}
		return false;
	}
	
	public String getCurrency(){
		return this.currency;
	}

	public boolean enoughtFund(double val){
		return this.balance > val;
	}
	
}
