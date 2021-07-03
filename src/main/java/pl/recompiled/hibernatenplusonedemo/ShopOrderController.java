package pl.recompiled.hibernatenplusonedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
class ShopOrderController {

    private final ShopOrderRepository repository;
    private final Integer PAGE_SIZE = 2;

    @GetMapping("/clients/{id}/orders")
    ResponseEntity<List<ShopOrderDto>> getOrderWithId(@PathVariable UUID id, @RequestParam Integer page) {
        final List<ShopOrderDto> result = repository.findAllByClientId(id,
                PageRequest.of(page, PAGE_SIZE).withSort(Sort.by("createdAt").ascending()))
                .stream()
                .map(ShopOrderDto::of)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

}
