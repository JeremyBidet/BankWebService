package DefaultNamespace;

import java.rmi.RemoteException;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			Bank service = new BankServiceLocator().getBank();
			((BankSoapBindingStub) service).setMaintainSession(true);
			service.addAccount("jbidet", 20000, "Bidet", "Jeremy", "EUR");
			System.out.println(service.getBalance("jbidet", "EUR"));
			System.out.println(service.getBalance("jbidet", "GBP"));
			
			// TODO: load Observer notification
			
			Main.startTests(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void startTests(boolean ignit) throws RemoteException {
		if(!ignit) {
			return;
		}		
	}

}
