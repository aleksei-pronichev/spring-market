package ru.pronichev.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDERS_ITEMS")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemKey orderItemKey;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE_PER_PRODUCT")
    private Integer pricePerProduct;

    @Column(name = "PRICE")
    private Integer price;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @Embeddable
    public static class OrderItemKey implements Serializable {

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "ORDER_ID")
        private Order order;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "PRODUCT_ID")
        private Product product;
    }
}