package pl.recompiled.hibernatenplusonedemo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OrderPosition implements Persistable<UUID> {

    @Id
    private UUID id;
    @Transient
    private boolean isNew;
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
    private Integer quantity;

    static OrderPosition of(Product product, Integer quantity) {
        final OrderPosition position = new OrderPosition();
        position.id = UUID.randomUUID();
        position.isNew = true;
        position.product = product;
        position.quantity = quantity;
        return position;
    }

}
