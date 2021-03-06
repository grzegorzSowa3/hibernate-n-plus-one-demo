package pl.recompiled.hibernatenplusonedemo;

import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

interface ShopOrderRepository extends CrudRepository<ShopOrder, Long> {

    Set<ShopOrder> findAllByClientId(UUID clientId);

}
