package virtusa.modern.dto;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import virtusa.modern.entity.ProductCategory;
import virtusa.modern.entity.Tag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateTagDTO {
        private Integer tagId;
        private String tagName;

}
