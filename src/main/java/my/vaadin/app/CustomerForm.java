package my.vaadin.app;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;

public class CustomerForm extends CustomerFormModel {
	
private CustomerService service=CustomerService.getInstance();
private Customer customer;
private MyUI myUI;
private Binder<Customer> binder=new Binder<>(Customer.class);

public CustomerForm(MyUI myUI) {
	super();
	this.myUI=myUI;
	status.setItems(CustomerStatus.values());
	save.setClickShortcut(KeyCode.ENTER);
	save.addClickListener(e ->this.save());
	delete.addClickListener(e -> this.delete());
	binder.bindInstanceFields(this);
	
	
}

public void setCustomer(Customer customer) {
	this.customer = customer;
	binder.setBean(customer);
	
	delete.setVisible(customer.isPersisted());
	setVisible(true);
	firstName.selectAll();
}


private void delete() {
	service.delete(customer);
	myUI.updatedList();
	setVisible(false);
	
}

private void save() {
	service.save(customer);
	myUI.updatedList();
	setVisible(false);
}

}
