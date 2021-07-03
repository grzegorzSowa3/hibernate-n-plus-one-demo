package pl.recompiled.hibernatenplusonedemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
class HibernateNPlusOneDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShopOrderRepository repository;

    @Autowired
    private CountingQueryExecutionListener queryExecutionListener;

    private final UUID clientId = UUID.randomUUID();

    @Test
    void shouldLoadOrdersWithOneQuery() throws Exception {

        clientWithTwoOrders();
        queryExecutionListener.resetCount();

        getOrders()
                .andExpect(status().isOk());

        assertEquals(1, queryExecutionListener.getCount());

    }

    void clientWithTwoOrders() {

        List<ShopOrder> orders = new ArrayList<>();

        orders.add(ShopOrder.of(clientId,
                OrderPosition.of("Milk", 2),
                OrderPosition.of("Butter", 1),
                OrderPosition.of("Egg", 10),
                OrderPosition.of("Paprika", 2),
                OrderPosition.of("Cucumber", 1),
                OrderPosition.of("Banana", 5)));

        orders.add(ShopOrder.of(clientId,
                OrderPosition.of("Beer", 6),
                OrderPosition.of("Crisps", 2),
                OrderPosition.of("Peanuts", 2)));

        repository.saveAll(orders);

    }

    ResultActions getOrders() throws Exception {
        return mockMvc.perform(
                MockMvcRequestBuilders.get(String.format("/clients/%s/orders?page=%d", clientId, 0))
        );
    }

}
