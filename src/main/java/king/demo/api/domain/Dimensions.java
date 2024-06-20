package king.demo.api.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Dimensions {

	private Float width;
	private Float height;
	private Float depth;
	
	public Dimensions() {}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getDepth() {
		return depth;
	}

	public void setDepth(Float depth) {
		this.depth = depth;
	}
}
