package fr.aston.guide.models.restaurant;

public class Restaurant {
    public String name;
    public String category;
    public String email;
    public String phone;
    public String url;
    public String image;

    public Restaurant(String name, String category, String email, String phone, String url, String image) {
        this.name = name;
        this.category = category;
        this.email = email;
        this.phone = phone;
        this.url = url;
        this.image = image;
    }
}
