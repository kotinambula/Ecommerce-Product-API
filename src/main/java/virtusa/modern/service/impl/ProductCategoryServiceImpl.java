package virtusa.modern.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import virtusa.modern.entity.ProductCategory;
import virtusa.modern.repository.ProductCategoryRepository;
import virtusa.modern.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private ProductCategoryRepository categoryRepository;

	public ProductCategoryServiceImpl(ProductCategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void createProductCategory(ProductCategory productCategory) {

		categoryRepository.createProductCategory(productCategory);
	}

	@Override
	public ProductCategory updateProductCategory(Integer categoryId, ProductCategory productCategory) {
		return categoryRepository.updateProductCategory(categoryId, productCategory);
	}

	@Override
	public ProductCategory getProductCategoryById(Integer categoryId) {
		return categoryRepository.getProductCategoryById(categoryId);
	}

	@Override
	public List<ProductCategory> getAllProductCategory() {
		return categoryRepository.getAllProductCategory();
	}

	@Override
	public void deleteProductCategory(Integer categoryId) {

		categoryRepository.deleteProductCategory(categoryId);
	}

}
