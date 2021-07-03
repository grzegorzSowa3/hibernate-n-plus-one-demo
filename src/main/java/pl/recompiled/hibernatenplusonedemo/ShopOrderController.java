package pl.recompiled.hibernatenplusonedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
class ShopOrderController {

    private final ShopOrderRepository repository;

    @GetMapping("/clients/{id}/orders")
    ResponseEntity<Set<ShopOrderDto>> getOrderWithId(@PathVariable UUID id) {
        final Set<ShopOrderDto> result = repository.findAllByClientId(id)
                .stream()
                .map(ShopOrderDto::of)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(result);
    }

}
