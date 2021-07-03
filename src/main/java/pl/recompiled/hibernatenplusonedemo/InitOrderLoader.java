package pl.recompiled.hibernatenplusonedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
class InitOrderLoader implements ApplicationListener<ApplicationStartedEvent> {

    private final ShopOrderRepository repository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        UUID clientId = UUID.fromString("75c17c3b-5be4-4d1d-88b2-8bf4074167b7");

        ShopOrder order1 = ShopOrder.of(clientId,
                OrderPosition.of("Milk", 2),
                OrderPosition.of("Butter", 1),
                OrderPosition.of("Egg", 10),
                OrderPosition.of("Paprika", 2),
                OrderPosition.of("Cucumber", 1),
                OrderPosition.of("Banana", 5));

        ShopOrder order2 = ShopOrder.of(clientId,
                OrderPosition.of("Beer", 6),
                OrderPosition.of("Crisps", 2),
                OrderPosition.of("Peanuts", 2));

        repository.save(order1);
        repository.save(order2);
    }

}
