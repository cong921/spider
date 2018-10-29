package lcw.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CrawlerXqExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CrawlerXqExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOredCriteria(List<Criteria> oredCriteria) {
		this.oredCriteria = oredCriteria;
	}

	public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andXqIdIsNull() {
            addCriterion("XQ_ID is null");
            return (Criteria) this;
        }

        public Criteria andXqIdIsNotNull() {
            addCriterion("XQ_ID is not null");
            return (Criteria) this;
        }

        public Criteria andXqIdEqualTo(Long value) {
            addCriterion("XQ_ID =", value, "xqId");
            return (Criteria) this;
        }

        public Criteria andXqIdNotEqualTo(Long value) {
            addCriterion("XQ_ID <>", value, "xqId");
            return (Criteria) this;
        }

        public Criteria andXqIdGreaterThan(Long value) {
            addCriterion("XQ_ID >", value, "xqId");
            return (Criteria) this;
        }

        public Criteria andXqIdGreaterThanOrEqualTo(Long value) {
            addCriterion("XQ_ID >=", value, "xqId");
            return (Criteria) this;
        }

        public Criteria andXqIdLessThan(Long value) {
            addCriterion("XQ_ID <", value, "xqId");
            return (Criteria) this;
        }

        public Criteria andXqIdLessThanOrEqualTo(Long value) {
            addCriterion("XQ_ID <=", value, "xqId");
            return (Criteria) this;
        }

        public Criteria andXqIdIn(List<Long> values) {
            addCriterion("XQ_ID in", values, "xqId");
            return (Criteria) this;
        }

        public Criteria andXqIdNotIn(List<Long> values) {
            addCriterion("XQ_ID not in", values, "xqId");
            return (Criteria) this;
        }

        public Criteria andXqIdBetween(Long value1, Long value2) {
            addCriterion("XQ_ID between", value1, value2, "xqId");
            return (Criteria) this;
        }

        public Criteria andXqIdNotBetween(Long value1, Long value2) {
            addCriterion("XQ_ID not between", value1, value2, "xqId");
            return (Criteria) this;
        }

        public Criteria andXqUrlIsNull() {
            addCriterion("XQ_URL is null");
            return (Criteria) this;
        }

        public Criteria andXqUrlIsNotNull() {
            addCriterion("XQ_URL is not null");
            return (Criteria) this;
        }

        public Criteria andXqUrlEqualTo(String value) {
            addCriterion("XQ_URL =", value, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlNotEqualTo(String value) {
            addCriterion("XQ_URL <>", value, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlGreaterThan(String value) {
            addCriterion("XQ_URL >", value, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlGreaterThanOrEqualTo(String value) {
            addCriterion("XQ_URL >=", value, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlLessThan(String value) {
            addCriterion("XQ_URL <", value, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlLessThanOrEqualTo(String value) {
            addCriterion("XQ_URL <=", value, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlLike(String value) {
            addCriterion("XQ_URL like", value, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlNotLike(String value) {
            addCriterion("XQ_URL not like", value, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlIn(List<String> values) {
            addCriterion("XQ_URL in", values, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlNotIn(List<String> values) {
            addCriterion("XQ_URL not in", values, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlBetween(String value1, String value2) {
            addCriterion("XQ_URL between", value1, value2, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlNotBetween(String value1, String value2) {
            addCriterion("XQ_URL not between", value1, value2, "xqUrl");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5IsNull() {
            addCriterion("XQ_URL_MD5 is null");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5IsNotNull() {
            addCriterion("XQ_URL_MD5 is not null");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5EqualTo(String value) {
            addCriterion("XQ_URL_MD5 =", value, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5NotEqualTo(String value) {
            addCriterion("XQ_URL_MD5 <>", value, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5GreaterThan(String value) {
            addCriterion("XQ_URL_MD5 >", value, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5GreaterThanOrEqualTo(String value) {
            addCriterion("XQ_URL_MD5 >=", value, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5LessThan(String value) {
            addCriterion("XQ_URL_MD5 <", value, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5LessThanOrEqualTo(String value) {
            addCriterion("XQ_URL_MD5 <=", value, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5Like(String value) {
            addCriterion("XQ_URL_MD5 like", value, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5NotLike(String value) {
            addCriterion("XQ_URL_MD5 not like", value, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5In(List<String> values) {
            addCriterion("XQ_URL_MD5 in", values, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5NotIn(List<String> values) {
            addCriterion("XQ_URL_MD5 not in", values, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5Between(String value1, String value2) {
            addCriterion("XQ_URL_MD5 between", value1, value2, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqUrlMd5NotBetween(String value1, String value2) {
            addCriterion("XQ_URL_MD5 not between", value1, value2, "xqUrlMd5");
            return (Criteria) this;
        }

        public Criteria andXqTitleIsNull() {
            addCriterion("XQ_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andXqTitleIsNotNull() {
            addCriterion("XQ_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andXqTitleEqualTo(String value) {
            addCriterion("XQ_TITLE =", value, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleNotEqualTo(String value) {
            addCriterion("XQ_TITLE <>", value, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleGreaterThan(String value) {
            addCriterion("XQ_TITLE >", value, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleGreaterThanOrEqualTo(String value) {
            addCriterion("XQ_TITLE >=", value, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleLessThan(String value) {
            addCriterion("XQ_TITLE <", value, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleLessThanOrEqualTo(String value) {
            addCriterion("XQ_TITLE <=", value, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleLike(String value) {
            addCriterion("XQ_TITLE like", value, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleNotLike(String value) {
            addCriterion("XQ_TITLE not like", value, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleIn(List<String> values) {
            addCriterion("XQ_TITLE in", values, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleNotIn(List<String> values) {
            addCriterion("XQ_TITLE not in", values, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleBetween(String value1, String value2) {
            addCriterion("XQ_TITLE between", value1, value2, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqTitleNotBetween(String value1, String value2) {
            addCriterion("XQ_TITLE not between", value1, value2, "xqTitle");
            return (Criteria) this;
        }

        public Criteria andXqPudateIsNull() {
            addCriterion("XQ_PUDATE is null");
            return (Criteria) this;
        }

        public Criteria andXqPudateIsNotNull() {
            addCriterion("XQ_PUDATE is not null");
            return (Criteria) this;
        }

        public Criteria andXqPudateEqualTo(Date value) {
            addCriterionForJDBCDate("XQ_PUDATE =", value, "xqPudate");
            return (Criteria) this;
        }

        public Criteria andXqPudateNotEqualTo(Date value) {
            addCriterionForJDBCDate("XQ_PUDATE <>", value, "xqPudate");
            return (Criteria) this;
        }

        public Criteria andXqPudateGreaterThan(Date value) {
            addCriterionForJDBCDate("XQ_PUDATE >", value, "xqPudate");
            return (Criteria) this;
        }

        public Criteria andXqPudateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("XQ_PUDATE >=", value, "xqPudate");
            return (Criteria) this;
        }

        public Criteria andXqPudateLessThan(Date value) {
            addCriterionForJDBCDate("XQ_PUDATE <", value, "xqPudate");
            return (Criteria) this;
        }

        public Criteria andXqPudateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("XQ_PUDATE <=", value, "xqPudate");
            return (Criteria) this;
        }

        public Criteria andXqPudateIn(List<Date> values) {
            addCriterionForJDBCDate("XQ_PUDATE in", values, "xqPudate");
            return (Criteria) this;
        }

        public Criteria andXqPudateNotIn(List<Date> values) {
            addCriterionForJDBCDate("XQ_PUDATE not in", values, "xqPudate");
            return (Criteria) this;
        }

        public Criteria andXqPudateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("XQ_PUDATE between", value1, value2, "xqPudate");
            return (Criteria) this;
        }

        public Criteria andXqPudateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("XQ_PUDATE not between", value1, value2, "xqPudate");
            return (Criteria) this;
        }

        public Criteria andZdNameIsNull() {
            addCriterion("ZD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andZdNameIsNotNull() {
            addCriterion("ZD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andZdNameEqualTo(String value) {
            addCriterion("ZD_NAME =", value, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameNotEqualTo(String value) {
            addCriterion("ZD_NAME <>", value, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameGreaterThan(String value) {
            addCriterion("ZD_NAME >", value, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameGreaterThanOrEqualTo(String value) {
            addCriterion("ZD_NAME >=", value, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameLessThan(String value) {
            addCriterion("ZD_NAME <", value, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameLessThanOrEqualTo(String value) {
            addCriterion("ZD_NAME <=", value, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameLike(String value) {
            addCriterion("ZD_NAME like", value, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameNotLike(String value) {
            addCriterion("ZD_NAME not like", value, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameIn(List<String> values) {
            addCriterion("ZD_NAME in", values, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameNotIn(List<String> values) {
            addCriterion("ZD_NAME not in", values, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameBetween(String value1, String value2) {
            addCriterion("ZD_NAME between", value1, value2, "zdName");
            return (Criteria) this;
        }

        public Criteria andZdNameNotBetween(String value1, String value2) {
            addCriterion("ZD_NAME not between", value1, value2, "zdName");
            return (Criteria) this;
        }

        public Criteria andLmNameIsNull() {
            addCriterion("LM_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLmNameIsNotNull() {
            addCriterion("LM_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLmNameEqualTo(String value) {
            addCriterion("LM_NAME =", value, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameNotEqualTo(String value) {
            addCriterion("LM_NAME <>", value, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameGreaterThan(String value) {
            addCriterion("LM_NAME >", value, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameGreaterThanOrEqualTo(String value) {
            addCriterion("LM_NAME >=", value, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameLessThan(String value) {
            addCriterion("LM_NAME <", value, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameLessThanOrEqualTo(String value) {
            addCriterion("LM_NAME <=", value, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameLike(String value) {
            addCriterion("LM_NAME like", value, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameNotLike(String value) {
            addCriterion("LM_NAME not like", value, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameIn(List<String> values) {
            addCriterion("LM_NAME in", values, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameNotIn(List<String> values) {
            addCriterion("LM_NAME not in", values, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameBetween(String value1, String value2) {
            addCriterion("LM_NAME between", value1, value2, "lmName");
            return (Criteria) this;
        }

        public Criteria andLmNameNotBetween(String value1, String value2) {
            addCriterion("LM_NAME not between", value1, value2, "lmName");
            return (Criteria) this;
        }

        public Criteria andSjflIsNull() {
            addCriterion("SJFL is null");
            return (Criteria) this;
        }

        public Criteria andSjflIsNotNull() {
            addCriterion("SJFL is not null");
            return (Criteria) this;
        }

        public Criteria andSjflEqualTo(String value) {
            addCriterion("SJFL =", value, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflNotEqualTo(String value) {
            addCriterion("SJFL <>", value, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflGreaterThan(String value) {
            addCriterion("SJFL >", value, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflGreaterThanOrEqualTo(String value) {
            addCriterion("SJFL >=", value, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflLessThan(String value) {
            addCriterion("SJFL <", value, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflLessThanOrEqualTo(String value) {
            addCriterion("SJFL <=", value, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflLike(String value) {
            addCriterion("SJFL like", value, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflNotLike(String value) {
            addCriterion("SJFL not like", value, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflIn(List<String> values) {
            addCriterion("SJFL in", values, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflNotIn(List<String> values) {
            addCriterion("SJFL not in", values, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflBetween(String value1, String value2) {
            addCriterion("SJFL between", value1, value2, "sjfl");
            return (Criteria) this;
        }

        public Criteria andSjflNotBetween(String value1, String value2) {
            addCriterion("SJFL not between", value1, value2, "sjfl");
            return (Criteria) this;
        }

        public Criteria andLoadTimeIsNull() {
            addCriterion("LOAD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLoadTimeIsNotNull() {
            addCriterion("LOAD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLoadTimeEqualTo(Date value) {
            addCriterion("LOAD_TIME =", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeNotEqualTo(Date value) {
            addCriterion("LOAD_TIME <>", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeGreaterThan(Date value) {
            addCriterion("LOAD_TIME >", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LOAD_TIME >=", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeLessThan(Date value) {
            addCriterion("LOAD_TIME <", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeLessThanOrEqualTo(Date value) {
            addCriterion("LOAD_TIME <=", value, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeIn(List<Date> values) {
            addCriterion("LOAD_TIME in", values, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeNotIn(List<Date> values) {
            addCriterion("LOAD_TIME not in", values, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeBetween(Date value1, Date value2) {
            addCriterion("LOAD_TIME between", value1, value2, "loadTime");
            return (Criteria) this;
        }

        public Criteria andLoadTimeNotBetween(Date value1, Date value2) {
            addCriterion("LOAD_TIME not between", value1, value2, "loadTime");
            return (Criteria) this;
        }

        public Criteria andXqAurhorIsNull() {
            addCriterion("XQ_AURHOR is null");
            return (Criteria) this;
        }

        public Criteria andXqAurhorIsNotNull() {
            addCriterion("XQ_AURHOR is not null");
            return (Criteria) this;
        }

        public Criteria andXqAurhorEqualTo(String value) {
            addCriterion("XQ_AURHOR =", value, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorNotEqualTo(String value) {
            addCriterion("XQ_AURHOR <>", value, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorGreaterThan(String value) {
            addCriterion("XQ_AURHOR >", value, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorGreaterThanOrEqualTo(String value) {
            addCriterion("XQ_AURHOR >=", value, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorLessThan(String value) {
            addCriterion("XQ_AURHOR <", value, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorLessThanOrEqualTo(String value) {
            addCriterion("XQ_AURHOR <=", value, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorLike(String value) {
            addCriterion("XQ_AURHOR like", value, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorNotLike(String value) {
            addCriterion("XQ_AURHOR not like", value, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorIn(List<String> values) {
            addCriterion("XQ_AURHOR in", values, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorNotIn(List<String> values) {
            addCriterion("XQ_AURHOR not in", values, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorBetween(String value1, String value2) {
            addCriterion("XQ_AURHOR between", value1, value2, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqAurhorNotBetween(String value1, String value2) {
            addCriterion("XQ_AURHOR not between", value1, value2, "xqAurhor");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneIsNull() {
            addCriterion("XQ_EXTEND_ONE is null");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneIsNotNull() {
            addCriterion("XQ_EXTEND_ONE is not null");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneEqualTo(String value) {
            addCriterion("XQ_EXTEND_ONE =", value, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneNotEqualTo(String value) {
            addCriterion("XQ_EXTEND_ONE <>", value, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneGreaterThan(String value) {
            addCriterion("XQ_EXTEND_ONE >", value, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneGreaterThanOrEqualTo(String value) {
            addCriterion("XQ_EXTEND_ONE >=", value, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneLessThan(String value) {
            addCriterion("XQ_EXTEND_ONE <", value, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneLessThanOrEqualTo(String value) {
            addCriterion("XQ_EXTEND_ONE <=", value, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneLike(String value) {
            addCriterion("XQ_EXTEND_ONE like", value, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneNotLike(String value) {
            addCriterion("XQ_EXTEND_ONE not like", value, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneIn(List<String> values) {
            addCriterion("XQ_EXTEND_ONE in", values, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneNotIn(List<String> values) {
            addCriterion("XQ_EXTEND_ONE not in", values, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneBetween(String value1, String value2) {
            addCriterion("XQ_EXTEND_ONE between", value1, value2, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendOneNotBetween(String value1, String value2) {
            addCriterion("XQ_EXTEND_ONE not between", value1, value2, "xqExtendOne");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoIsNull() {
            addCriterion("XQ_EXTEND_TWO is null");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoIsNotNull() {
            addCriterion("XQ_EXTEND_TWO is not null");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoEqualTo(String value) {
            addCriterion("XQ_EXTEND_TWO =", value, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoNotEqualTo(String value) {
            addCriterion("XQ_EXTEND_TWO <>", value, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoGreaterThan(String value) {
            addCriterion("XQ_EXTEND_TWO >", value, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoGreaterThanOrEqualTo(String value) {
            addCriterion("XQ_EXTEND_TWO >=", value, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoLessThan(String value) {
            addCriterion("XQ_EXTEND_TWO <", value, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoLessThanOrEqualTo(String value) {
            addCriterion("XQ_EXTEND_TWO <=", value, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoLike(String value) {
            addCriterion("XQ_EXTEND_TWO like", value, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoNotLike(String value) {
            addCriterion("XQ_EXTEND_TWO not like", value, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoIn(List<String> values) {
            addCriterion("XQ_EXTEND_TWO in", values, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoNotIn(List<String> values) {
            addCriterion("XQ_EXTEND_TWO not in", values, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoBetween(String value1, String value2) {
            addCriterion("XQ_EXTEND_TWO between", value1, value2, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andXqExtendTwoNotBetween(String value1, String value2) {
            addCriterion("XQ_EXTEND_TWO not between", value1, value2, "xqExtendTwo");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNull() {
            addCriterion("KEY_WORD is null");
            return (Criteria) this;
        }

        public Criteria andKeyWordIsNotNull() {
            addCriterion("KEY_WORD is not null");
            return (Criteria) this;
        }

        public Criteria andKeyWordEqualTo(String value) {
            addCriterion("KEY_WORD =", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotEqualTo(String value) {
            addCriterion("KEY_WORD <>", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThan(String value) {
            addCriterion("KEY_WORD >", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_WORD >=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThan(String value) {
            addCriterion("KEY_WORD <", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLessThanOrEqualTo(String value) {
            addCriterion("KEY_WORD <=", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordLike(String value) {
            addCriterion("KEY_WORD like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotLike(String value) {
            addCriterion("KEY_WORD not like", value, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordIn(List<String> values) {
            addCriterion("KEY_WORD in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotIn(List<String> values) {
            addCriterion("KEY_WORD not in", values, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordBetween(String value1, String value2) {
            addCriterion("KEY_WORD between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andKeyWordNotBetween(String value1, String value2) {
            addCriterion("KEY_WORD not between", value1, value2, "keyWord");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleIsNull() {
            addCriterion("IS_SIMTITLE is null");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleIsNotNull() {
            addCriterion("IS_SIMTITLE is not null");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleEqualTo(Integer value) {
            addCriterion("IS_SIMTITLE =", value, "isSimtitle");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleNotEqualTo(Integer value) {
            addCriterion("IS_SIMTITLE <>", value, "isSimtitle");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleGreaterThan(Integer value) {
            addCriterion("IS_SIMTITLE >", value, "isSimtitle");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_SIMTITLE >=", value, "isSimtitle");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleLessThan(Integer value) {
            addCriterion("IS_SIMTITLE <", value, "isSimtitle");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleLessThanOrEqualTo(Integer value) {
            addCriterion("IS_SIMTITLE <=", value, "isSimtitle");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleIn(List<Integer> values) {
            addCriterion("IS_SIMTITLE in", values, "isSimtitle");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleNotIn(List<Integer> values) {
            addCriterion("IS_SIMTITLE not in", values, "isSimtitle");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleBetween(Integer value1, Integer value2) {
            addCriterion("IS_SIMTITLE between", value1, value2, "isSimtitle");
            return (Criteria) this;
        }

        public Criteria andIsSimtitleNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_SIMTITLE not between", value1, value2, "isSimtitle");
            return (Criteria) this;
        }

        public Criteria andIsExteurlIsNull() {
            addCriterion("IS_EXTEURL is null");
            return (Criteria) this;
        }

        public Criteria andIsExteurlIsNotNull() {
            addCriterion("IS_EXTEURL is not null");
            return (Criteria) this;
        }

        public Criteria andIsExteurlEqualTo(Integer value) {
            addCriterion("IS_EXTEURL =", value, "isExteurl");
            return (Criteria) this;
        }

        public Criteria andIsExteurlNotEqualTo(Integer value) {
            addCriterion("IS_EXTEURL <>", value, "isExteurl");
            return (Criteria) this;
        }

        public Criteria andIsExteurlGreaterThan(Integer value) {
            addCriterion("IS_EXTEURL >", value, "isExteurl");
            return (Criteria) this;
        }

        public Criteria andIsExteurlGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_EXTEURL >=", value, "isExteurl");
            return (Criteria) this;
        }

        public Criteria andIsExteurlLessThan(Integer value) {
            addCriterion("IS_EXTEURL <", value, "isExteurl");
            return (Criteria) this;
        }

        public Criteria andIsExteurlLessThanOrEqualTo(Integer value) {
            addCriterion("IS_EXTEURL <=", value, "isExteurl");
            return (Criteria) this;
        }

        public Criteria andIsExteurlIn(List<Integer> values) {
            addCriterion("IS_EXTEURL in", values, "isExteurl");
            return (Criteria) this;
        }

        public Criteria andIsExteurlNotIn(List<Integer> values) {
            addCriterion("IS_EXTEURL not in", values, "isExteurl");
            return (Criteria) this;
        }

        public Criteria andIsExteurlBetween(Integer value1, Integer value2) {
            addCriterion("IS_EXTEURL between", value1, value2, "isExteurl");
            return (Criteria) this;
        }

        public Criteria andIsExteurlNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_EXTEURL not between", value1, value2, "isExteurl");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}