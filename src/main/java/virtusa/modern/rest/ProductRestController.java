package virtusa.modern.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
import virtusa.modern.dto.CreateProductDTO;
import virtusa.modern.dto.ProductResponseDTO;
import virtusa.modern.entity.Product;
import virtusa.modern.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);

    private ProductService productService;

    public ProductRestController(ProductService productService) {
        super();
        this.productService = productService;
    }

    @PostMapping("/save")
    @Operation(summary = "Create a new product", description = "Add a new product to the database with the provided details.")
    public void createProduct(@RequestBody CreateProductDTO productDTO) {
        logger.info("Received request to create a new product: {}", productDTO);
        try {
            productService.createProduct(productDTO);
            logger.info("Product successfully created: {}", productDTO);
        } catch (Exception e) {
            logger.error("Error occurred while creating product: {}", productDTO, e);
        }
    }

    @PutMapping("/update/{productId}")
    @Operation(summary = "Update a product", description = "Update an existing product with new data based on the provided ID.")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody Product product) {
        logger.info("Received request to update product with ID: {}", productId);
        try {
            Product updatedProduct = productService.updateProduct(productId, product);
            logger.info("Successfully updated product with ID: {}", productId);
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            logger.error("Error occurred while updating product with ID: {}", productId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get product by ID", description = "Retrieve a single product by its unique identifier.")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Integer productId) {
        logger.info("Received request to fetch product by ID: {}", productId);
        try {
        	ProductResponseDTO productResponse = productService.getProductById(productId);
            if (productResponse != null) {
                logger.info("Successfully retrieved product with ID: {},{}", productId,productResponse);
                return ResponseEntity.ok(productResponse);
            } else {
                logger.warn("Product with ID {} not found", productId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching product with ID: {}", productId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/products")
    @Operation(summary = "Get all products", description = "Retrieve a list of all products available in the database.")
    public ResponseEntity<List<Product>> getAllProducts() {
        logger.info("Received request to fetch all products");
        try {
            List<Product> products = productService.getAllProducts();
            logger.info("Successfully retrieved {} products", products.size());
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            logger.error("Error occurred while fetching all products", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(summary = "Delete a product", description = "Delete a product from the database using its unique identifier.")
    public void deleteProduct(@PathVariable Integer productId) {
        logger.info("Received request to delete product with ID: {}", productId);
        try {
            productService.deleteProduct(productId);
            logger.info("Successfully deleted product with ID: {}", productId);
        } catch (Exception e) {
            logger.error("Error occurred while deleting product with ID: {}", productId, e);
        }
    }
}
