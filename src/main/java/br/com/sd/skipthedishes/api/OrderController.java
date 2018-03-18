package br.com.sd.skipthedishes.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.sd.skipthedishes.model.Order;
import br.com.sd.skipthedishes.model.OrderItem;
import br.com.sd.skipthedishes.service.CustomerService;
import br.com.sd.skipthedishes.service.OrderService;

public class OrderController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/Order/{orderId}", method = RequestMethod.GET)
	Order getProduct(@PathVariable("orderId") Integer orderId) {
        return this.orderService.getById(orderId);
    }
	
	@RequestMapping(value="/Order/auth", method = RequestMethod.POST)
	Boolean insert(@RequestParam("id") Integer id,
					@RequestParam("date") Date date,
					@RequestParam("customerId") Integer customerId,
					@RequestParam("contact") String contact,
					@RequestParam("storeId") Long storeId,
					@RequestParam("orderItems") List<OrderItem> orderItems,
					@RequestParam("total") Double total,
					@RequestParam("status") String status,
					@RequestParam("lastUpdate") Date lastUpdate
					) {
		if ( id == 0) {
			Order order = new Order();
			order.setDate(date);
			order.setCustomer(this.customerService.getById(customerId));
			order.setContact(contact);
			order.setStoreId(storeId);
			order.setOrderItems(orderItems);
			order.setTotal(total);
			order.setStatus(status);
			return this.orderService.insert(order);
		} else {
			Order order = this.orderService.getById(id);
			order.setDate(date);
			order.setCustomer(this.customerService.getById(customerId));
			order.setContact(contact);
			order.setStoreId(storeId);
			order.setOrderItems(orderItems);
			order.setTotal(total);
			order.setStatus(status);
			return this.orderService.update(order);
		}
    }
	
	///Order/customer
	List<Order> getCustomerOrders() {
		return null;
    }
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
}