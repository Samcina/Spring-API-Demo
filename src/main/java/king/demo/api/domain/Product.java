package king.demo.api.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;
    private String title;
    private String description;
    private String category;
    private Float price;
    private Float discountPercentage;
    private Float rating;
    private Integer stock;
    private String[] tags;
    private String brand;
    private String sku;
    private Integer weight;
    
	@Embedded
    private Dimensions dimensions;
    
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    
    @Embedded
    private Review[] reviews;
    
    private String returnPolicy;
    private Integer minimumOrderQuantity;
    
    @Embedded
    private Meta meta;
    
    private String[] images;
    private String thumbnail;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	public String getWarrantyInformation() {
		return warrantyInformation;
	}

	public void setWarrantyInformation(String warrantyInformation) {
		this.warrantyInformation = warrantyInformation;
	}

	public String getShippingInformation() {
		return shippingInformation;
	}

	public void setShippingInformation(String shippingInformation) {
		this.shippingInformation = shippingInformation;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public Review[] getReviews() {
		return reviews;
	}

	public void setReviews(Review[] reviews) {
		this.reviews = reviews;
	}

	public String getReturnPolicy() {
		return returnPolicy;
	}

	public void setReturnPolicy(String returnPolicy) {
		this.returnPolicy = returnPolicy;
	}

	public Integer getMinimumQuantityOrder() {
		return minimumOrderQuantity;
	}

	public void setMinimumOrderQuantity(Integer minimumOrderQuantity) {
		this.minimumOrderQuantity = minimumOrderQuantity;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public ProductDTO getProjection() {
		return new ProductDTO(this.title, this.description, this.price, this.thumbnail);
	}
	
	public static List<ProductDTO> getProjectionFromList(List<Product> products) {
		List<ProductDTO> productsProjection = new ArrayList<ProductDTO>();
		products.forEach((e) -> productsProjection.add(e.getProjection()));
		return productsProjection;
	}
	

    public Product() {}
	
}
