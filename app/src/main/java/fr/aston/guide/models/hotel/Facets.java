package fr.aston.guide.models.hotel;

public class Facets {
    public String name;
    public String path;
    public String count;
    public String state;

    @Override
    public String toString() {
        return "Facets{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", count='" + count + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
