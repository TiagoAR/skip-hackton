package br.com.sd.skipthedishes.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sd.skipthedishes.model.Cousine;
import br.com.sd.skipthedishes.model.Product;
import br.com.sd.skipthedishes.service.CousineService;

@RestController
public class CousineController {

	@Autowired
	CousineService cousineService;
	
	@RequestMapping(value="/Cousine", method = RequestMethod.GET)
	List<Cousine> getAll() {
        return this.cousineService.getAll();
    }
	
	@RequestMapping(value="/Cousine/search/{searchText}", method = RequestMethod.GET)
	List<Cousine> search(@PathVariable("searchText") String searchText) {
        return this.cousineService.search(searchText);
    }
	
	@RequestMapping(value="Cousine/{cousineId}/stores", method = RequestMethod.GET)
	Product getProduct(@PathVariable("productId") Integer productId) {
        return this.cousineService.getById(productId);
    }

	public CousineService getCousineService() {
		return cousineService;
	}

	public void setCousineService(CousineService cousineService) {
		this.cousineService = cousineService;
	}

}
