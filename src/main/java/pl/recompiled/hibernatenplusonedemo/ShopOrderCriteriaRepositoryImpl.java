package pl.recompiled.hibernatenplusonedemo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.UUID;

@Repository
public class ShopOrderCriteriaRepositoryImpl implements ShopOrderCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ShopOrder> findAllByClientId(UUID clientId, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ShopOrder> query = criteriaBuilder.createQuery(ShopOrder.class);
        Root<ShopOrder> shopOrder = query.from(ShopOrder.class);
        shopOrder.fetch("positions", JoinType.LEFT)
                .fetch("product", JoinType.LEFT);

        query.select(shopOrder);
        query.orderBy(QueryUtils.toOrders(pageable.getSort(), shopOrder, criteriaBuilder));

        TypedQuery<ShopOrder> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        return typedQuery.getResultList();
    }
}
