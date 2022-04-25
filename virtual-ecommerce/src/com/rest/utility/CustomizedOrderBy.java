package com.rest.utility;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;

public class CustomizedOrderBy extends Order {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sqlExpression;
 
    protected CustomizedOrderBy(String sqlExpression) {
        super(sqlExpression, true);
        this.sqlExpression = sqlExpression;
    }
 
 
    public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
        return sqlExpression;
    }
 
    public static Order sqlFormula(String sqlFormula) {
        return new CustomizedOrderBy(sqlFormula);
    }
 
    public String toString() {
        return sqlExpression;
    }
 
}

/*
 * Criteria criteria = hbSession.getPublishModeSession().createCriteria("User_" + table.getGeneratedID());
if (sortBy != null){
    if (sortDir.equalsIgnoreCase("asc")){
        if (sortBy.getType().equalsIgnoreCase("date")){
            criteria.addOrder(CustomizedOrderBy.sqlFormula("convert( datetime, "+sortBy.getInnerName()+"  , 101) "+ sortDir));
        } else {
            criteria.addOrder(Order.asc(sortBy.getInnerName()));
        }
 
    } else if (sortDir.equalsIgnoreCase("desc")) {
        if (sortBy.getType().equalsIgnoreCase("date")){
            criteria.addOrder(CustomizedOrderBy.sqlFormula("convert( datetime, "+sortBy.getInnerName()+"  , 101) "+ sortDir));
        } else {
            criteria.addOrder(Order.desc(sortBy.getInnerName()));
        }
 
    }
}
 */
