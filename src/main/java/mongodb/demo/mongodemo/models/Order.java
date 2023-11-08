package mongodb.demo.mongodemo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "order")
public class Order {
    @Id
    private String id;
    @Field(name = "buyer_id")
    private String buyerId;
    private List<String> productIds;

    public Order(String buyerId, List<String> productIds) {
        this.buyerId = buyerId;
        this.productIds = productIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", productIds=" + productIds +
                '}';
    }
}
