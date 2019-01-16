package store;

import java.io.Serializable;

public class Anzeige implements Serializable {
	private String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
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

	private int price;
	private String description;
	private String category;
	
	public Anzeige(String title, int price, String description, String category) {
		this.title=title;
		this.price=price;
		this.description=description;
		this.category=category;
	}
}
