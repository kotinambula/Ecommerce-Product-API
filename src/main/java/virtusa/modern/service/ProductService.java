package virtusa.modern.service;

import java.util.List;

import virtusa.modern.dto.CreateProductDTO;
import virtusa.modern.dto.ProductResponseDTO;
import virtusa.modern.entity.Product;

public interface ProductService {
	
	public Product createProduct(CreateProductDTO productDTO);
	public Product updateProduct(Integer productId,Product product);
	public ProductResponseDTO getProductById(Integer productId);
	public List<ProductResponseDTO> getAllProducts();
	public void deleteProduct(Integer productId);
}
