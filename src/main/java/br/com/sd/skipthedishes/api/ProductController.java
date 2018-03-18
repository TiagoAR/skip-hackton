package br.com.sd.skipthedishes.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sd.skipthedishes.model.Product;
import br.com.sd.skipthedishes.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/Product", method = RequestMethod.GET)
	List<Product> getAll() {
        return this.productService.getAll();
    }
	
	@RequestMapping(value="/Product/search/{searchText}", method = RequestMethod.GET)
	List<Product> search(@PathVariable("searchText") String searchText) {
        return this.productService.search(searchText);
    }
	
	@RequestMapping(value="/Product/{productId}", method = RequestMethod.GET)
	Product getProduct(@PathVariable("productId") Integer productId) {
        return this.productService.getById(productId);
    }
	

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


}
