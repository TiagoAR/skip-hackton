package br.com.sd.skipthedishes.api;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.sd.skipthedishes.model.Customer;
import br.com.sd.skipthedishes.service.CustomerService;

@RestController
public class CustomerController {
	//TODO ACESSS FILTER
	public static final String jwtSecret = "GepENAmyxdaTeB90MEsEtud?3Acr";
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value="/Customer", method = RequestMethod.POST)
	String authenticate(@RequestParam("email") String email,
						@RequestParam("password") String password
					) throws IllegalArgumentException, UnsupportedEncodingException {
		Customer customer = this.customerService.findUser(email, password);
		if (customer == null) {
			//TODO user not find
		} else {
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(new Date());
			calendario.add(Calendar.DAY_OF_MONTH, +27);
			Builder jwt = JWT.create().withIssuer("vanhack-saopaulo-fair-api")
					.withAudience("vanhack-saopaulo-fair-api")
					.withClaim("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name", customer.getName())
					.withClaim("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/emailaddress",customer.getEmail())
					.withClaim("http://schemas.microsoft.com/ws/2008/06/identity/claims/userdata",customer.getId().toString())
					.withNotBefore(new Date())
					.withExpiresAt(calendario.getTime());
			Algorithm algorithm;
				algorithm = Algorithm.HMAC256(CustomerController.jwtSecret);
				String token = jwt.sign(algorithm);
				return token;
		}
		return null;
        //return this.productService.search(searchText);
    }
	
	@RequestMapping(value="/Customer/auth", method = RequestMethod.POST)
	Boolean insert(@RequestParam("id") Integer id,
					@RequestParam("email") String email,
					@RequestParam("address") String address,
					@RequestParam("creation") Date creation,
					@RequestParam("password") String password
					) {
		if ( id == 0) {
			Customer customer = new Customer();
			customer.setEmail(email);
			customer.setAddress(address);
			customer.setCreation(new Date());
			customer.setPassword(password);
			return this.customerService.insert(customer);
		} else {
			Customer customer = this.customerService.getById(id);
			customer.setEmail(email);
			customer.setAddress(address);
			customer.setCreation(new Date());
			customer.setPassword(password);
			return this.customerService.update(customer);
		}
    }

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
}
