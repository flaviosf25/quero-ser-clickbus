package com.apiplaces.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.apiplaces.model.Place;

@Repository
public class PlaceRespositoryImpl implements PlaceRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Place> searchPlace(Place place) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Place> criteria = builder.createQuery(Place.class);
		Root<Place> root = criteria.from(Place.class);
		Predicate[] predicates = createRestriction(place, builder, root);
		criteria.where(predicates);
		TypedQuery<Place> query = manager.createQuery(criteria);
		
		return query.getResultList();
	}
	
	private Predicate[] createRestriction(Place place, CriteriaBuilder builder, Root<Place> root) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!StringUtils.isEmpty(place.getName())) {
			predicates.add(builder.like(builder.lower(root.get("name")), place.getName().toLowerCase()));
		}
		
		if(!StringUtils.isEmpty(place.getCity().getName())) {
			predicates.add(builder.like(builder.lower(root.get("city").get("name")), place.getCity().getName().toLowerCase()));
		}
		
		if(!StringUtils.isEmpty(place.getState().getName())) {
			predicates.add(builder.like(builder.lower(root.get("state").get("name")), place.getState().getName().toLowerCase()));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
