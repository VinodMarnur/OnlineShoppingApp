package com.candidjava.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidjava.common.model.City;
import com.candidjava.common.model.Country;
import com.candidjava.common.model.State;

@Service
public class CommonDaoImpl implements CommonDao{
	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	SessionFactory sessionFactory;
	
	JSONObject obj=null;
	
	@Override
	public List<Country> getContry() {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Country.class);
		List<Country> countryList=criteria.list();
		return countryList;
	}

	@Override
	public List<State> getStates(String str) throws JSONException {
		long countryId=0;
		obj=new JSONObject(str);
		if(!obj.isNull("countryId")) {
			countryId=obj.getLong("countryId");
		}
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(State.class);
		Criteria critCountry = criteria.createCriteria("country",JoinType.INNER_JOIN);
		critCountry.add(Restrictions.eq("countryId", countryId));
		return criteria.list();
	}

	@Override
	public List<City> getCitites(String str)  throws JSONException{
		long stateId=0;
		obj=new JSONObject(str);
		
		if(!obj.isNull("stateId")) {
			stateId=obj.getLong("stateId");
		}
		
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(City.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		Criteria critCountry = criteria.createCriteria("state",JoinType.INNER_JOIN);
		critCountry.add(Restrictions.eq("stateId", stateId));
		return criteria.list();
	}

}
