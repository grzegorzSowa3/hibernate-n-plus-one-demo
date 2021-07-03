package pl.recompiled.hibernatenplusonedemo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ShopOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID clientId;

    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Set<OrderPosition> positions;

    static ShopOrder newInstance() {
        final ShopOrder shopOrder = new ShopOrder();
        shopOrder.positions = new HashSet<>();
        return shopOrder;
    }

    static ShopOrder of(UUID clientId, OrderPosition... positions) {
        final ShopOrder shopOrder = newInstance();
        shopOrder.clientId = clientId;
        shopOrder.createdAt = LocalDateTime.now();
        for (OrderPosition position : positions) {
            shopOrder.addPosition(position);
        }
        return shopOrder;
    }

    void addPosition(OrderPosition orderPosition) {
        this.positions.add(orderPosition);
    }

    Set<OrderPosition> getPositions() {
        return Collections.unmodifiableSet(positions);
    }

}
