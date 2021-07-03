package pl.recompiled.hibernatenplusonedemo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OrderPosition implements Persistable<UUID> {

    @Id
    private UUID id;
    @Transient
    private boolean isNew;
    private String product;
    private Integer quantity;

    static OrderPosition of(String product, Integer quantity) {
        final OrderPosition position = new OrderPosition();
        position.id = UUID.randomUUID();
        position.isNew = true;
        position.product = product;
        position.quantity = quantity;
        return position;
    }

}
