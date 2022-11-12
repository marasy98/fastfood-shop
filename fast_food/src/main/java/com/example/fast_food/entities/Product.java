package com.example.fast_food.entities;

        import com.example.fast_food.util.ImageUtils;
        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import com.fasterxml.jackson.annotation.JsonManagedReference;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import org.springframework.web.multipart.MultipartFile;

        import javax.persistence.*;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;
    private String description;
    private int quantity;
    @Column(unique = true)
    private String productName;
    private int price;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails=new ArrayList<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<PromotionProduct> promotionProducts=new ArrayList<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<CartItem> cartItems=new ArrayList<>();
    @OneToOne(mappedBy = "product",cascade = {CascadeType.ALL})
    @JsonManagedReference
    private ImageProduct imageProduct;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public Product(String description, String productName, int price, MultipartFile file, long categoryId,int quantity) throws IOException {
        this.quantity=quantity;
        this.description=description;
        this.productName=productName;
        this.price=price;
        this.category= new Category(categoryId);
        ImageProduct imageData = ImageProduct.builder()
                .imageName(productName)
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).product(this).build();
        this.imageProduct=imageData;
    }
}
