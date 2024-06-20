package king.demo.api.domain;

public class ProductDTO {

    private String title;
    private String description;
    private Float price;
    private String thumbnail;

	public String getTitle() {
		return title;
	}


	public String getDescription() {
		return description;
	}


	public Float getPrice() {
		return price;
	}


	public String getThumbnail() {
		return thumbnail;
	}
	
    
	public ProductDTO(String title, String description, Float price, String thumbnail) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.thumbnail = thumbnail;
	}
}
