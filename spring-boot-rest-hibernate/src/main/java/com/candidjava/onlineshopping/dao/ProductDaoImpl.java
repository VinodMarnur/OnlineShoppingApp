package com.candidjava.onlineshopping.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidjava.onlineshopping.model.Cart;
import com.candidjava.onlineshopping.model.Product;


@Service
public class ProductDaoImpl implements ProductDao {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	OnlineShoppingUserDao onlineShoppingUserDao;
		
	@Autowired
	ProductTypeDaoImpl productTypeDaoImpl;

	JSONObject obj = null;

	Map map=null;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> getAllProducts() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Product.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Product> products = criteria.list();
		return products;
	}

	@Override
	public Product getProductById(long productId) {
		Session session = sessionFactory.getCurrentSession();
		Product product = session.get(Product.class, productId);
		return product;
	}

	@Override
	public List<Product> getSearchProducts(String serchProduct) {
		String productName = "";
		try {
			obj = new JSONObject(serchProduct);
			if (!obj.isNull("productName")) {
				productName = obj.getString("productName");
			} 
			// TODO Auto-generated method stub
		} catch (Exception e) {

		}
		String searchKeywords[] = productName.split(" ");
		String serachWords="";
		for(String str:searchKeywords) {
			serachWords+="product.productName like '%"+str+"%' or ";
		}
		
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Product as product ";
		if(productName.length()>0) { 
//			serachWords=serachWords.substring(0, serachWords.length()-3);
			hql+=" where "+serachWords.substring(0,serachWords.length()-3);
		}
//		hql+= " order by product.price desc";

		List<Product> list = session.createQuery(hql).list();
		return list;
	}

	@Override
	public List<Map> getAllProductsFiter(String str) throws JSONException {
		// TODO Auto-generated method stub
		int pageSize = 0, length = 0, pageIndex = 0;
		String order = "asc";
		String orderName = "productName";
		String query="";
		String priceRange = "";
		obj = new JSONObject(str);
		if (!obj.isNull("pageSize")) {
			pageSize = obj.getInt("pageSize");
		} else {
			pageSize = 10;
		}

		if (!obj.isNull("pageIndex")) {
			pageIndex = obj.getInt("pageIndex") - 1;
		}

		if (!obj.isNull("order")) {
			order = obj.getString("order");
		}

		if (!obj.isNull("orderName")) {
			orderName = obj.getString("orderName");
		}

		Session session = sessionFactory.getCurrentSession();
		//String hql = " from Product as product " + " limit " + pageIndex + "," + pageSize + "";

		Criteria productlistCriteria = session.createCriteria(Product.class, "product");
		Criteria productlistCriterialength = session.createCriteria(Product.class, "product");
		productlistCriteria.setFirstResult(pageIndex * pageSize);
		productlistCriteria.setMaxResults(pageSize);
		if (!obj.isNull("priceRange")) {
			System.out.println("priceRange" + priceRange);
			String prices[] = obj.getString("priceRange").split("-");
			productlistCriteria.add(Restrictions.between("price", Double.parseDouble(prices[0]), Double.parseDouble(prices[1])));
			productlistCriterialength.add(Restrictions.between("price", Double.parseDouble(prices[0]), Double.parseDouble(prices[1])));
		}

		if (order.toLowerCase().equals("desc")) {
			productlistCriteria.addOrder(Order.desc(orderName));
		} else {
			productlistCriteria.addOrder(Order.asc(orderName));
		}

		if (!obj.isNull("productType")) {
			// criteria.add(Restrictions.eq("product.productType.productTypeName",
			// obj.getString("productType")));
			// criteria.createCriteria("product.productType.productTypeName",
			// "productTypeName", JoinType.INNER_JOIN, Restrictions.eq("productTypeName",
			// obj.getString("productType")));
		}

		HashMap map = new HashMap();
		map.put("data", productlistCriteria.list());
		HashMap meta = new HashMap();
		meta.put("pageSize", pageSize);
		meta.put("length", productlistCriterialength.list().size());
		meta.put("pageIndex", pageIndex + 1);
		map.put("meta", meta);
		LinkedList listData = new LinkedList();
		listData.add(map);
		return listData;
	}


	@Override
	public Map addProduct(String str) throws JSONException {
		Product product = new Product();
		String errormsg = "";
		obj = new JSONObject(str);
		map = new HashMap();
		
		if (!obj.isNull("productName")) {
			product.setProductName(obj.getString("productName"));
		} else {
			errormsg += "Enter Product Name,";
		}
		
		if (!obj.isNull("price")) {
			product.setPrice(obj.getDouble("price"));
		} else {
			errormsg += "Enter Price,";
		}
		
		if (!obj.isNull("productType")) {
			product.setProductType(productTypeDaoImpl.getProductType(obj.getLong("productType")));
		}
			
		if (errormsg.equals("")) {
			Session session = sessionFactory.getCurrentSession();
			session.save(product);
			map.put("msg","Product Added Successfully");
		} else {
			map.put("errormsg", errormsg.substring(0, errormsg.length() - 1));
		}
		return map;
	}

	@Override
	public long getCartCount(String str) throws JSONException {
		long userId=1;
		obj=new JSONObject(str);
		Session session = sessionFactory.getCurrentSession();
		
		if(!obj.isNull("userId")) {
			userId=obj.getLong("userId");
		}
		
		Criteria crit = session.createCriteria(Cart.class);
		Criteria companyCriteria = crit.createCriteria("user",JoinType.INNER_JOIN);
        companyCriteria.add(Restrictions.eq("userId", userId));
		crit.setProjection(Projections.rowCount());
		long count = (Long)crit.uniqueResult();
		return count;
	}

	@Override
	public List<Cart> getCartProducts(String str) throws JSONException {
		long userId=1;
		map=new HashMap();
		obj=new JSONObject(str);
		Session session = sessionFactory.getCurrentSession();
		
		if(!obj.isNull("userId")) {
			userId=obj.getLong("userId");
		}
		
		Criteria crit = session.createCriteria(Cart.class);
		Criteria companyCriteria = crit.createCriteria("user",JoinType.INNER_JOIN);
		companyCriteria.add(Restrictions.eq("userId", userId));
		List<Cart> cartlist=crit.list();
		
		return cartlist;
	}

	@Override
	public Map incrementDecrementProductCount(String str) throws JSONException {
		long productId=0,userId=1;
		obj=new JSONObject(str);
		map=new HashMap();
		String actionType="";
		Session session = sessionFactory.getCurrentSession();
		if(!obj.isNull("productId"))
		{
			productId=obj.getLong("productId");
		}
		if(!obj.isNull("userId"))
		{
			userId=obj.getLong("userId");
		}
		
		if(!obj.isNull("actionType")) {
			actionType=obj.getString("actionType");
		}
		Criteria criteria=session.createCriteria(Cart.class);
		Criteria userCriteria = criteria.createCriteria("user",JoinType.INNER_JOIN);
		userCriteria.add(Restrictions.eq("userId", userId));
        Criteria productCriteria = criteria.createCriteria("product",JoinType.INNER_JOIN);
        productCriteria.add(Restrictions.eq("productId", productId));
        List<Cart> cartList=criteria.list();
        if(cartList.size()!=0 && actionType.equals("increment")) {
        	Cart cart=cartList.get(0);
        	cart.setCartProductCount(1+cart.getCartProductCount());
        	session.update(cart);
        	map.put("msg", "product incremented by  1 successfylly");
        }else {
        	Cart cart=cartList.get(0);
        	if(cart.getCartProductCount()>1) {
        		cart.setCartProductCount(cart.getCartProductCount()-1);
        		session.update(cart);
        	      map.put("msg", "product decremented by  1 successfylly");
        	}else {
        		   map.put("errorMsg", "product length atleast 1");
        	}
        }
    		
		return map;
	}

	@Override
	public Map addToCart(String str) throws JSONException {
		long productId=0,userId=0;
		obj=new JSONObject(str);
		map=new HashMap();
		String actionType="";
		Session session = sessionFactory.getCurrentSession();
		if(!obj.isNull("productId"))
		{
			productId=obj.getLong("productId");
		}
		
		if(!obj.isNull("userId"))
		{
			userId=obj.getLong("userId");
		}
			
		Criteria criteria=session.createCriteria(Cart.class);
		Criteria userCriteria = criteria.createCriteria("user",JoinType.INNER_JOIN);
		userCriteria.add(Restrictions.eq("userId", userId));
        Criteria productCriteria = criteria.createCriteria("product",JoinType.INNER_JOIN);
        productCriteria.add(Restrictions.eq("productId", productId));
        List<Cart> cartList=criteria.list();
        if(cartList.size()!=0) {
        	Cart cart=cartList.get(0);
        	cart.setCartProductCount(1+cart.getCartProductCount());
        	session.update(cart);
        	map.put("msg", "product added to cart");
        	map.put("status", "exist");
        }else {
        	Cart cart=new Cart();
        	cart.setCartProductCount(1);
        	cart.setProduct(productDao.getProductById(productId));
        	cart.setUser(onlineShoppingUserDao.getUserByUserId(userId));
        	session.save(cart);
        	map.put("status", "new");
        }
    	return map;
	}

	@Override
	public Map deletePrdouctFromCart(String str) throws JSONException {
		long cartId=0;
		obj=new JSONObject(str);
		map=new HashMap();
		String actionType="";
		Session session = sessionFactory.getCurrentSession();
		if(!obj.isNull("cartId")) {
			cartId=obj.getLong("cartId");
			Cart cart=new Cart();
			cart.setCartId(cartId);
			session.delete(cart);
			map.put("msg", "Product Removed From Cart");
		}else {
			map.put("msg", "Some thing went Wrong");
		}
		return map;
	}

	@Override
	public List<Cart> getCartProducts(int userId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Cart.class);
		Criteria userCriteria = criteria.createCriteria("user",JoinType.INNER_JOIN);
		userCriteria.add(Restrictions.eq("userId", userId));
        List<Cart> cartList=criteria.list();
		return cartList;
	}

}
