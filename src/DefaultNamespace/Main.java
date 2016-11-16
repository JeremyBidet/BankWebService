package DefaultNamespace;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public class Main {

	public static void main(String[] args) throws ServiceException, RemoteException {
		Bank service = new BankServiceLocator().getBank();
		((BankSoapBindingStub) service).setMaintainSession(true);
		service.addAccount("jbidet", 20000, "Bidet", "Jeremy", "EUR");
		System.out.println(service.getBalance("jbidet", "EUR"));
		System.out.println(service.getBalance("jbidet", "GBP"));
	}

}
