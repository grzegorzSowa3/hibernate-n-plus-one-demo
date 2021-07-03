package pl.recompiled.hibernatenplusonedemo;

import org.springframework.data.repository.CrudRepository;

interface ShopOrderRepository extends CrudRepository<ShopOrder, Long>, ShopOrderCriteriaRepository {

}
