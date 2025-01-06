package virtusa.modern.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import virtusa.modern.entity.ProductCategory;
import virtusa.modern.repository.ProductCategoryRepository;

@Repository
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public void createProductCategory(ProductCategory productCategory) {
		entityManager.persist(productCategory);
	}

	@Override
	public ProductCategory updateProductCategory(Integer categoryId, ProductCategory productCategory) {
		ProductCategory productCategoryEntity = entityManager.find(ProductCategory.class, categoryId);
		if(productCategoryEntity != null) {
			productCategoryEntity.setCategoryName(productCategory.getCategoryName());
			productCategoryEntity.setProducts(productCategory.getProducts());
			return productCategoryEntity;
		}
		return null;
	}

	@Override
	public ProductCategory getProductCategoryById(Integer categoryId) {
		return entityManager.find(ProductCategory.class, categoryId);
	
	}

	@Override
	public List<ProductCategory> getAllProductCategory() {
		
		TypedQuery<ProductCategory> query = entityManager.createQuery("SELECT category FROM ProductCategory category" , ProductCategory.class);
		return query.getResultList();
	}

	@Override
	public void deleteProductCategory(Integer categoryId) {
		ProductCategory productCategoryEntity = entityManager.find(ProductCategory.class, categoryId);
		if(productCategoryEntity != null) {
			entityManager.remove(productCategoryEntity);
		}
	}

}
