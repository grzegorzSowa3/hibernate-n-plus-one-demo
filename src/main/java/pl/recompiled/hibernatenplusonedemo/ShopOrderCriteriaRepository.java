package pl.recompiled.hibernatenplusonedemo;

import java.util.Set;
import java.util.UUID;

public interface ShopOrderCriteriaRepository {
    Set<ShopOrder> findAllByClientId(UUID clientId);
}
