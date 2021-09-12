package ru.pronichev.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private User owner;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "PRICE")
    private Integer price;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updated;

    @OneToMany(mappedBy = "orderItemKey.order")
    private Collection<OrderItem> orderItems;
}