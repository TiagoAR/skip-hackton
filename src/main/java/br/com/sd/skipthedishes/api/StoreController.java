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
public class StoreController {

	@Autowired
	CousineService storeService;
	
	@RequestMapping(value="/Store", method = RequestMethod.GET)
	List<Cousine> getAll() {
        return this.storeService.getAll();
    }
	
	@RequestMapping(value="/Store/search/{searchText}", method = RequestMethod.GET)
	List<Cousine> search(@PathVariable("searchText") String searchText) {
        return this.storeService.search(searchText);
    }
	
	@RequestMapping(value="Store/{storeId}/products", method = RequestMethod.GET)
	List<Product> getProduct(@PathVariable("storeId") Integer storeId) {
        return this.storeService.getProducts(storeId);
    }

	public CousineService getStoreService() {
		return storeService;
	}

	public void setStoreService(CousineService storeService) {
		this.storeService = storeService;
	}
	

}
