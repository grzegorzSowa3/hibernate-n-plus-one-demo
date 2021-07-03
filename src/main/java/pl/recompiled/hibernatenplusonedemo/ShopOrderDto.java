package pl.recompiled.hibernatenplusonedemo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class ShopOrderDto {

    private Long id;

    private Set<OrderPositionDto> positions;

    static ShopOrderDto of(ShopOrder shopOrder) {
        final ShopOrderDto dto = new ShopOrderDto();
        dto.positions = new HashSet<>();
        dto.id = shopOrder.getId();
        dto.positions.addAll(
                shopOrder.getPositions()
                        .stream()
                        .map(position -> new OrderPositionDto(position.getId(), position.getProduct(), position.getQuantity()))
                        .collect(Collectors.toSet()));
        return dto;
    }

    static record OrderPositionDto(UUID id, String product, Integer quantity) {
    }

}
