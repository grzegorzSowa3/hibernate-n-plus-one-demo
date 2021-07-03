package pl.recompiled.hibernatenplusonedemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ShopOrderCriteriaRepositoryImpl implements ShopOrderCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<ShopOrder> findAllByClientId(UUID clientId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ShopOrder> query = criteriaBuilder.createQuery(ShopOrder.class);
        Root<ShopOrder> shopOrder = query.from(ShopOrder.class);
        shopOrder.fetch("positions", JoinType.LEFT);
        query.select(shopOrder);

        TypedQuery<ShopOrder> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultStream()
                .collect(Collectors.toSet());
    }
}
