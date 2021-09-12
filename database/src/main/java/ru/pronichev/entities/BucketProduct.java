package ru.pronichev.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BUCKETS_PRODUCTS")
public class BucketProduct implements Serializable {

    @EmbeddedId
    private BucketKey bucketKey;

    @Column(name = "COUNT")
    private Integer count;

    @Data
    @NoArgsConstructor
    @Embeddable
    public static class BucketKey implements Serializable {
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "USER_ID")
        private User user;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "PRODUCT_ID")
        private Product product;
    }
}