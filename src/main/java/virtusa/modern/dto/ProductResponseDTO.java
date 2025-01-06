package virtusa.modern.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ProductResponseDTO {

	private Integer productId;
    private String productName;
    private Double productPrice;
    private String productDescription;
    private Integer productQuantity;
    private String productImageUrl;
    private LocalDate dateCreated;
    private LocalDate lastUpdated;
    private String categoryName;
    private List<String> tagNames;
}
