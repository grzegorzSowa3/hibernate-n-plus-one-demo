package pl.recompiled.hibernatenplusonedemo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class ShopOrderCriteriaRepositoryImpl implements ShopOrderCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ShopOrder> findAllByClientId(UUID clientId, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        final List<Long> ids = getIdsPage(pageable, builder);

        CriteriaQuery<ShopOrder> query = builder.createQuery(ShopOrder.class);

        final Root<ShopOrder> root = query.from(ShopOrder.class);
        root.fetch("positions", JoinType.LEFT)
                .fetch("product", JoinType.LEFT);

        query.select(root).where(root.get("id").in(ids));
        return entityManager.createQuery(query).getResultList();
    }

    private List<Long> getIdsPage(Pageable pageable, CriteriaBuilder builder) {
        CriteriaQuery<Long> idQuery = builder.createQuery(Long.class);
        Root<ShopOrder> idQueryRoot = idQuery.from(ShopOrder.class);

        idQuery.select(idQueryRoot.get("id"));
        idQuery.orderBy(QueryUtils.toOrders(pageable.getSort(), idQueryRoot, builder));

        TypedQuery<Long> idQueryTyped = entityManager.createQuery(idQuery);
        idQueryTyped.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        idQueryTyped.setMaxResults(pageable.getPageSize());

        return idQueryTyped.getResultList();
    }
}
