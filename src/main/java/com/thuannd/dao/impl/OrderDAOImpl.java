package com.thuannd.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thuannd.dao.OrderDAO;
import com.thuannd.entity.User;
import com.thuannd.entity.UserOrder;
import com.thuannd.model.SearchOrderDTO;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public void addOrder(UserOrder order) {
		entityManager.persist(order);
	}

	@Override
	public void updateOrder(UserOrder order) {
		entityManager.merge(order);
	}

	@Override
	public void deleteOrder(UserOrder order) {
		entityManager.remove(order);
	}

	@Override
	public UserOrder getOrderById(Long id) {
		return entityManager.find(UserOrder.class, id);
	}

	@Override
	public List<UserOrder> findOrder(SearchOrderDTO searchOrderDTO) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserOrder> criteriaQuery = builder.createQuery(UserOrder.class);
		Root<UserOrder> root = criteriaQuery.from(UserOrder.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (searchOrderDTO.getFromAdd() != null) {
			Predicate predicate1 = builder.like(builder.lower(root.get("fromAdd")),
					"%" + searchOrderDTO.getFromAdd() + "%");
			predicates.add(predicate1);
		}

		if (searchOrderDTO.getToAdd() != null) {
			Predicate predicate1 = builder.like(builder.lower(root.get("toAdd")),
					"%" + searchOrderDTO.getToAdd() + "%");
			predicates.add(predicate1);
		}

		if (searchOrderDTO.getCreatedBy() != null) {
			Join<UserOrder, User> createdBy = root.join("createdBy", JoinType.INNER);
			predicates.add(builder.equal(createdBy.get("id"), searchOrderDTO.getCreatedBy()));
		}

		if (searchOrderDTO.getStatus() != null) {
			predicates.add(builder.equal(root.get("status"), searchOrderDTO.getStatus()));
		}

		if (searchOrderDTO.getShipperId() != null) {
			Join<UserOrder, User> shipper = root.join("shipper", JoinType.INNER);
			predicates.add(builder.equal(shipper.get("id"), searchOrderDTO.getShipperId()));
		}

		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<UserOrder> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
		typedQuery.setFirstResult(searchOrderDTO.getStart());
		typedQuery.setMaxResults(searchOrderDTO.getLength());

		return typedQuery.getResultList();
	}

	@Override
	public Long countOrder(SearchOrderDTO searchOrderDTO) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<UserOrder> root = criteriaQuery.from(UserOrder.class);

		if (searchOrderDTO.getFromAdd() != null) {
			builder.like(root.get("fromAdd"), "%" + searchOrderDTO.getFromAdd() + "%");
		}

		if (searchOrderDTO.getToAdd() != null) {
			builder.like(root.get("toAdd"), "%" + searchOrderDTO.getToAdd() + "%");
		}

		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(builder.count(root)));
		return typedQuery.getSingleResult();
	}

}
