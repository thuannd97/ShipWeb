package com.thuannd.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.thuannd.dao.UserDAO;
import com.thuannd.entity.User;
import com.thuannd.model.SearchUserDTO;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;;

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}

	@Override
	public void deleteUser(User user) {
		entityManager.remove(user);
	}

	@Override
	public User getUserById(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User getUserByUsername(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);

		criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.lower(root.get("email")), "email"));
		TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery.select(root).distinct(true));
		return typedQuery.getSingleResult();
	}

	@Override
	public List<User> findUser(SearchUserDTO searchUserDTO) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (searchUserDTO.getEmail() != null) {
			Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
					"%" + searchUserDTO.getEmail() + "%");
			predicates.add(predicate);
		}

		if (searchUserDTO.getId() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("id"), searchUserDTO.getId());
			predicates.add(predicate);
		}

		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
		typedQuery.setFirstResult(searchUserDTO.getStart());
		typedQuery.setMaxResults(searchUserDTO.getLength());
		return typedQuery.getResultList();
	}

	@Override
	public Long countUser(SearchUserDTO searchUserDTO) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<User> root = criteriaQuery.from(User.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (searchUserDTO.getEmail() != null) {
			Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("email")),
					"%" + searchUserDTO.getEmail() + "%");
			predicates.add(predicate);
		}

		if (searchUserDTO.getId() != null) {
			Predicate predicate = criteriaBuilder.equal(root.get("id"), searchUserDTO.getId());
			predicates.add(predicate);
		}

		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)));

		return typedQuery.getSingleResult();
	}

	@Override
	public User findByEmail(String email) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);

		criteriaQuery.where(criteriaBuilder.equal(criteriaBuilder.lower(root.get("email")), email));

		TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
		System.out.println(typedQuery.getSingleResult().getEmail());
		return typedQuery.getSingleResult();
	}

}
