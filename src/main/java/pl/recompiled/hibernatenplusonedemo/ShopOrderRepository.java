package pl.recompiled.hibernatenplusonedemo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;
import java.util.UUID;

interface ShopOrderRepository extends CrudRepository<ShopOrder, Long> {

    @Query("""
            SELECT o FROM ShopOrder o
            LEFT JOIN FETCH o.positions
            """)
    Set<ShopOrder> findAllByClientId(UUID clientId);

}
