package virtusa.modern.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import virtusa.modern.dto.CreateProductDTO;
import virtusa.modern.dto.ProductResponseDTO;
import virtusa.modern.entity.Product;
import virtusa.modern.entity.ProductCategory;
import virtusa.modern.entity.Tag;
import virtusa.modern.repository.ProductCategoryRepository;
import virtusa.modern.repository.ProductRepository;
import virtusa.modern.service.impl.ProductServiceImpl;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);

	private ProductCategoryRepository categoryRepository;

	public ProductRepositoryImpl(EntityManager entityManager, ProductCategoryRepository categoryRepository) {
		super();
		this.entityManager = entityManager;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void createProduct(CreateProductDTO productDTO) {
		logger.info("Saving product with details: {}", productDTO);

		try {
			Product product = new Product();
			product.setProductDescription(productDTO.getProductDescription());
			product.setProductImageUrl(productDTO.getProductImageUrl());
			product.setProductName(productDTO.getProductName());
			product.setProductPrice(productDTO.getProductPrice());
			product.setProductQuantity(productDTO.getProductQuantity());

			logger.info("Fetching product category with ID: {}", productDTO.getCategoryId());
			ProductCategory productCategoryById = categoryRepository.getProductCategoryById(productDTO.getCategoryId());
			product.setCategory(productCategoryById);

			logger.info("Fetching tags for product with IDs: {}", productDTO.getTagIds());
			List<Tag> listOfTags = getTagsByIds(productDTO.getTagIds());
			product.setTags(listOfTags);

			entityManager.persist(product);
			logger.info("Product successfully saved with ID: {}", product.getProductId());
		} catch (Exception e) {
			logger.error("Error occurred while saving product with details: {}", productDTO, e);
		}
	}

	private List<Tag> getTagsByIds(List<Integer> tagIds) {
		logger.info("Fetching tags for IDs: {}", tagIds);
		try {
			TypedQuery<Tag> listOfTags = entityManager.createQuery("SELECT t FROM Tag t WHERE t.tagId IN :tagIds",
					Tag.class);
			List<Tag> tags = listOfTags.setParameter("tagIds", tagIds).getResultList();
			logger.info("Successfully fetched {} tags", tags.size());
			return tags;
		} catch (Exception e) {
			logger.error("Error occurred while fetching tags for IDs: {}", tagIds, e);
			return new ArrayList<>();
		}
	}

	@Override
	public Product updateProduct(Integer productId, Product product) {
		logger.info("Updating product with ID: {}", productId);

		try {
			Product productEntity = entityManager.find(Product.class, productId);
			if (productEntity != null) {
				productEntity.setCategory(product.getCategory());
				productEntity.setProductDescription(product.getProductDescription());
				productEntity.setProductImageUrl(product.getProductImageUrl());
				productEntity.setProductName(product.getProductName());
				productEntity.setProductPrice(product.getProductPrice());
				productEntity.setProductQuantity(product.getProductQuantity());
				productEntity.setTags(product.getTags());
				logger.info("Product with ID: {} successfully updated", productId);
				return productEntity;
			} else {
				logger.warn("Product with ID: {} not found for update", productId);
				return null;
			}
		} catch (Exception e) {
			logger.error("Error occurred while updating product with ID: {}", productId, e);
			return null;
		}
	}

	@Override
	public ProductResponseDTO getProductById(Integer productId) {
		logger.info("Fetching product with ID: {}", productId);

		try {
			Product product = entityManager.find(Product.class, productId);
			if (product != null) {
				logger.info("Product found with ID: {}", productId);
				ProductResponseDTO responseDto = new ProductResponseDTO();
				responseDto.setCategoryName(product.getCategory().getCategoryName());
				responseDto.setDateCreated(product.getDateCreated());

				responseDto.setLastUpdated(product.getLastUpdated());

				responseDto.setProductDescription(product.getProductDescription());

				responseDto.setProductId(product.getProductId());

				responseDto.setProductImageUrl(product.getProductImageUrl());

				responseDto.setProductName(product.getProductName());

				responseDto.setProductPrice(product.getProductPrice());

				responseDto.setProductQuantity(product.getProductQuantity());

				responseDto.setTagNames(product.getTags().stream().map(Tag::getTagName).collect(Collectors.toList()));


				/*
				 * Sure! Let’s break down the line of code:
				 * 
				 * ```java
				 * productDTO.setTagNames(product.getTags().stream().map(Tag::getTagName).
				 * collect(Collectors.toList())); ```
				 * 
				 * ### Explanation:
				 * 
				 * 1. **`product.getTags()`**: - This part calls the `getTags()` method on the
				 * `product` object, which returns a `List<Tag>`. Each `Tag` represents a tag
				 * associated with the product. So, the result is a collection of all tags
				 * associated with this specific product.
				 * 
				 * 2. **`stream()`**: - The `stream()` method converts the `List<Tag>` into a
				 * stream of `Tag` objects. A **stream** allows us to perform functional
				 * operations on the collection, such as filtering, mapping, and collecting
				 * results.
				 * 
				 * 3. **`map(Tag::getTagName)`**: - The `map()` function transforms each element
				 * in the stream. Here, we are using a method reference `Tag::getTagName`, which
				 * is shorthand for `tag -> tag.getTagName()`. - This operation extracts the
				 * `tagName` property from each `Tag` object in the list and creates a new
				 * stream consisting of just the tag names (strings) rather than the full `Tag`
				 * objects.
				 * 
				 * 4. **`collect(Collectors.toList())`**: - The `collect()` method is a terminal
				 * operation that transforms the stream back into a collection—in this case, a
				 * `List<String>`. - The `Collectors.toList()` is a predefined collector that
				 * collects the results into a `List` of tag names. - So, after the `map()`
				 * operation, it collects all the tag names into a `List<String>`.
				 * 
				 * 5. **`productDTO.setTagNames(...)`**: - Finally, the `setTagNames()` method
				 * of the `ProductDTO` is called, which assigns the list of tag names
				 * (`List<String>`) to the `tagNames` field of the `ProductDTO` object.
				 * 
				 * ### Summary:
				 * 
				 * This line of code extracts the tag names associated with the product and
				 * converts them from a list of `Tag` objects into a list of strings (tag
				 * names). The result is then assigned to the `tagNames` field of the
				 * `ProductDTO` object. This allows the API response to include a clean list of
				 * tag names, rather than full `Tag` objects, which helps avoid unnecessary
				 * complexity and prevents circular references in the JSON response.
				 */

				return responseDto;
			} else {
				logger.warn("Product with ID: {} not found", productId);
				return null;
			}
		} catch (Exception e) {
			logger.error("Error occurred while fetching product with ID: {}", productId, e);
			return null;
		}
	}

	@Override
	public List<Product> getAllProducts() {
		logger.info("Fetching all products from database");

		try {
			String jpql = "SELECT p FROM Product p";
			TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
			List<Product> products = query.getResultList();
			logger.info("Successfully fetched {} products", products.size());
			return products;
		} catch (Exception e) {
			logger.error("Error occurred while fetching all products", e);
			return new ArrayList<>();
		}
	}

	@Override
	public void deleteProduct(Integer productId) {
		logger.info("Deleting product with ID: {}", productId);

		try {
			Product productEntity = entityManager.find(Product.class, productId);
			if (productEntity != null) {
				entityManager.remove(productEntity);
				logger.info("Successfully deleted product with ID: {}", productId);
			} else {
				logger.warn("Product with ID: {} not found for deletion", productId);
			}
		} catch (Exception e) {
			logger.error("Error occurred while deleting product with ID: {}", productId, e);
		}
	}
}
