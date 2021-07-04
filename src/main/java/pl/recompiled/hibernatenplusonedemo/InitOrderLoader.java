package pl.recompiled.hibernatenplusonedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class InitOrderLoader implements ApplicationListener<ApplicationStartedEvent> {

    private final ShopOrderRepository repository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        UUID clientId = UUID.fromString("75c17c3b-5be4-4d1d-88b2-8bf4074167b7");

        List<ShopOrder> orders = new ArrayList<>();

        orders.add(ShopOrder.of(clientId,
                OrderPosition.of(Product.of("Milk"), 2),
                OrderPosition.of(Product.of("Butter"), 1),
                OrderPosition.of(Product.of("Egg"), 10),
                OrderPosition.of(Product.of("Paprika"), 2),
                OrderPosition.of(Product.of("Cucumber"), 1),
                OrderPosition.of(Product.of("Banana"), 5)));

        orders.add(ShopOrder.of(clientId,
                OrderPosition.of(Product.of("Beer"), 6),
                OrderPosition.of(Product.of("Crisps"), 2),
                OrderPosition.of(Product.of("Peanuts"), 2)));

        orders.add(ShopOrder.of(clientId,
                OrderPosition.of(Product.of("Toothpaste"), 1),
                OrderPosition.of(Product.of("Deodorant"), 1)));

        repository.saveAll(orders);
    }

}
