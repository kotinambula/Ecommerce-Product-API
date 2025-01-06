package virtusa.modern.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import virtusa.modern.entity.ProductCategory;
import virtusa.modern.service.ProductCategoryService;

@RestController
@RequestMapping("/category")
public class categoryRestController {

	private ProductCategoryService categoryService;

	public categoryRestController(ProductCategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping("/save")
	@Operation(summary = "Create a new product category", description = "Add a new product category to the database with the provided details.")
	public void createProductCategory(@RequestBody ProductCategory productCategory) {

		categoryService.createProductCategory(productCategory);
	}

	@PutMapping("/update")
	@Operation(summary = "Update a product category", description = "Update an existing product category with new data based on the provided ID.")
	public ResponseEntity<ProductCategory> updateProductCategory(@PathVariable Integer categoryId,
			@RequestBody ProductCategory productCategory) {

		return new ResponseEntity<ProductCategory>(categoryService.updateProductCategory(categoryId, productCategory),
				HttpStatus.OK);

	}

	@GetMapping("/{categoryId}")
	@Operation(summary = "Get product category by ID", description = "Retrieve a single product category by its unique identifier.")
	public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Integer categoryId) {

		return new ResponseEntity<ProductCategory>(categoryService.getProductCategoryById(categoryId), HttpStatus.OK);
	}

	@GetMapping("/")
	@Operation(summary = "Get all product categories", description = "Retrieve a list of all product categories available in the database.")
	public ResponseEntity<List<ProductCategory>> getAllProductCategory() {
		return new ResponseEntity<List<ProductCategory>>(categoryService.getAllProductCategory(), HttpStatus.OK);

	}

	@DeleteMapping("/{categoryId}")
	@Operation(summary = "Delete a product category", description = "Delete a product category from the database using its unique identifier.")
	public void deleteProductCategory(@PathVariable Integer categoryId) {
		categoryService.deleteProductCategory(categoryId);
	}
}
