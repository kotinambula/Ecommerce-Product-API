package virtusa.modern.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

@Entity
@Setter
@Getter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	private String productName;
	private Double productPrice;
	@Lob
	private String productDescription;
	private Integer productQuantity;
	private String productImageUrl;
	@CreationTimestamp
	@Column(name = "date_created", updatable = false)
	private LocalDate dateCreated;

	@UpdateTimestamp
	@Column(name = "last_updated", insertable = false)
	private LocalDate lastUpdated;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@JsonIgnoreProperties("products")  // Ignore 'products' during serialization
	private ProductCategory category;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "product_tag-table", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags = new ArrayList<>();

	

	/*
	 * 
	 * Many-to-Many Relationship with Tag:
	 * 
	 * A product can have multiple tags, and a tag can be associated with multiple
	 * products. This is a many-to-many relationship.
	 * 
	 * @ManyToMany indicates that a product can have multiple tags and vice versa.
	 * 
	 * @JoinTable is used to specify the join table (product_tag) that links
	 * products and tags. The joinColumns specify the column in the join table that
	 * refers to the Product, and inverseJoinColumns specify the column that refers
	 * to the Tag.
	 */
	
	

}
