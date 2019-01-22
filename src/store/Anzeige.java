package store;

import java.io.Serializable;
@Deprecated
public class Anzeige implements Serializable {
	private String title;
	private float price;
	private String description;
	private String category;
	
	
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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


	public Anzeige(String title, float price, String description, String category) {
		this.title=title;
		this.price=price;
		this.description=description;
		this.category=category;
	}
}
