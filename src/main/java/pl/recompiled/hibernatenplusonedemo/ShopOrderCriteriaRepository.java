package pl.recompiled.hibernatenplusonedemo;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ShopOrderCriteriaRepository {
    List<ShopOrder> findAllByClientId(UUID clientId, Pageable pageable);
}
