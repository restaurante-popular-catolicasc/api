package br.org.restaurantepopular.entity;

public class ProductType {

    private String id;
    private String name;

    public ProductType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class ProductTypeBuilder {

        private String id;
        private String name;

        public ProductTypeBuilder() {
            
        }

        public ProductTypeBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public ProductTypeBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public ProductType build() {
            return new ProductType(id, name);
        }
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
