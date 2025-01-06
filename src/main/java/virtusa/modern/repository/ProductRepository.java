package virtusa.modern.repository;

import java.util.List;

import virtusa.modern.dto.CreateProductDTO;
import virtusa.modern.dto.ProductResponseDTO;
import virtusa.modern.entity.Product;
import virtusa.modern.entity.Tag;

public interface ProductRepository {
	
	public Product createProduct(CreateProductDTO productDTO);
	public Product updateProduct(Integer productId,Product product);
	public ProductResponseDTO getProductById(Integer productId);
	public List<ProductResponseDTO> getAllProducts();
	public void deleteProduct(Integer productId);
}
