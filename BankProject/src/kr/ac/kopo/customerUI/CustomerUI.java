package kr.ac.kopo.customerUI;

import kr.ac.kopo.service.CustomerService;
import kr.ac.kopo.service.CustomerServiceFactory;
import kr.ac.kopo.ui.BaseUI;

public abstract class CustomerUI extends BaseUI{

	protected CustomerService service;
	
	
	public CustomerUI() {
		service = CustomerServiceFactory.getInstance();
	}

	
	
	
}
