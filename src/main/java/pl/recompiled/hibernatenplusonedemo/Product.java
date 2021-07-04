package pl.recompiled.hibernatenplusonedemo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Product {

    @Id
    private UUID id;
    @Transient
    private boolean isNew;
    private String name;

    static Product of(String name) {
        final Product product = new Product();
        product.id = UUID.randomUUID();
        product.isNew = true;
        return product;
    }

}
