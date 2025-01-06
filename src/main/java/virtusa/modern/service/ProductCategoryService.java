package virtusa.modern.service;

import java.util.List;

import virtusa.modern.entity.Product;
import virtusa.modern.entity.ProductCategory;

public interface ProductCategoryService {

	public void createProductCategory(ProductCategory productCategory);
	public ProductCategory updateProductCategory(Integer categoryId,ProductCategory productCategory);
	public ProductCategory getProductCategoryById(Integer categoryId);
	public List<ProductCategory> getAllProductCategory();
	public void deleteProductCategory(Integer categoryId);
}
