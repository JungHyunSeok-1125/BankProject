package kr.ac.kopo.service;

public class CustomerServiceFactory {

	private static CustomerService service = new CustomerService();
	
	public static CustomerService getInstance() {
		return service;
	}
}
