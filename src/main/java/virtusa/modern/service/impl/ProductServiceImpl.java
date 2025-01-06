package virtusa.modern.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import virtusa.modern.dto.CreateProductDTO;
import virtusa.modern.dto.ProductResponseDTO;
import virtusa.modern.entity.Product;
import virtusa.modern.repository.ProductRepository;
import virtusa.modern.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product createProduct(CreateProductDTO productDTO) {
        logger.info("Initiating product creation with details: {}", productDTO);

        try {
            Product product = productRepository.createProduct(productDTO);
            logger.info("Product successfully created with details: {}", productDTO);
            return product;
        } catch (Exception e) {
            logger.error("Error occurred while creating product with details: {}", productDTO, e);
        }
        return null;
    }

    @Override
    @Transactional
    public Product updateProduct(Integer productId, Product product) {
        logger.info("Initiating product update for product ID: {}", productId);

        try {
            Product updatedProduct = productRepository.updateProduct(productId, product);
            if (updatedProduct != null) {
                logger.info("Successfully updated product with ID: {}", productId);
                return updatedProduct;
            } else {
                logger.warn("Product with ID {} not found for update", productId);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error occurred while updating product with ID: {}", productId, e);
            return null;
        }
    }

    @Override
    public ProductResponseDTO getProductById(Integer productId) {
        logger.info("Fetching product details for product ID: {}", productId);

        try {
        	ProductResponseDTO productResponse = productRepository.getProductById(productId);
            if (productResponse != null) {
                logger.info("Successfully fetched product with ID: {}", productId);
                return productResponse;
            } else {
                logger.warn("Product with ID {} not found", productId);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error occurred while fetching product with ID: {}", productId, e);
            return null;
        }
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        logger.info("Fetching all products from the database");

        try {
            List<ProductResponseDTO> productsResponse = productRepository.getAllProducts();
            logger.info("Successfully retrieved {} products", productsResponse.size());
            return productsResponse;
        } catch (Exception e) {
            logger.error("Error occurred while fetching all products", e);
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Integer productId) {
        logger.info("Initiating deletion of product with ID: {}", productId);

        try {
            productRepository.deleteProduct(productId);
            logger.info("Successfully deleted product with ID: {}", productId);
        } catch (Exception e) {
            logger.error("Error occurred while deleting product with ID: {}", productId, e);
        }
    }
}
