package com.ewhale.points.model.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ewhale.points.common.exception.EntityException;
import com.ewhale.points.common.util.EntityConstants;

/**
 * The persistent class for the profile database table.
 */
@Entity
@Table(name = "profile")
@NamedQuery(name = "Profile.findAll", query = "SELECT p,s FROM Profile p join p.status s")
@PrimaryKeyJoinColumn(name = "profile_id", referencedColumnName = "login_id")
public class Profile extends Login implements Serializable
{
	private static final long serialVersionUID = 1L;

	public static String[] entityIdNames = new String[]
		{ EntityConstants.Profile.profileId };

	@Column(name = "profile_id", unique = true, nullable = false, insertable = false, updatable = false)
	private long profileId;

	@Column(length = 255)
	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date")
	private Date birthDate;

	@Column(length = 50)
	private String email;

	@Column(name = "first_name", nullable = false, length = 25)
	private String firstName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "insert_date", nullable = false)
	private Date insertDate;

	@Column(name = "last_name", nullable = false, length = 25)
	private String lastName;

	@Column(name = "mid_name", nullable = true, length = 25)
	private String midName;

	@Column(length = 20)
	private String tel;

	@Column
	private Character gender;

	// bi-directional many-to-one association to AgentRate
	@OneToMany(mappedBy = "profile")
	private List<AgentRate> agentRates;

	// bi-directional many-to-one association to Contract
	@OneToMany(mappedBy = "insEmp")
	private List<Contract> addedContracts;

	// bi-directional many-to-one association to Contract
	@OneToMany(mappedBy = "updateEmp")
	private List<Contract> updatedContracts;

	// bi-directional many-to-one association to ItemStatus
	@OneToMany(mappedBy = "updateStatusEmp")
	private List<ItemStatus> itemStatuses;

	// bi-directional many-to-one association to MessageCenter
	@OneToMany(mappedBy = "requestAgentEmp")
	private List<MessageCenter> requestedMessages;

	// bi-directional many-to-one association to MessageCenter
	@OneToMany(mappedBy = "responseEmp")
	private List<MessageCenter> responsedMessages;

	// bi-directional many-to-one association to Point
	@OneToMany(mappedBy = "insEmp")
	private List<Point> insertedPoints;

	// bi-directional many-to-one association to Point
	@OneToMany(mappedBy = "pointsProfile")
	private List<Point> points;

	// bi-directional many-to-one association to PointsExchange
	@OneToMany(mappedBy = "profile")
	private List<PointsExchange> pointsExchanges;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "insEmp")
	private List<Product> insertedProducts;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "updateEmp")
	private List<Product> updatedProducts;

	// bi-directional many-to-one association to Agent
	@ManyToOne
	@JoinColumn(name = "profile_agent_id")
	private Agent agent;

	// bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name = "profile_role_id", nullable = false)
	private Role role;

	// bi-directional many-to-one association to District
	@ManyToOne
	@JoinColumn(name = "profile_district_id", referencedColumnName = "district_id", nullable = false)
	private District district;

	// bi-directional many-to-one association to State
	@ManyToOne
	@JoinColumn(name = "profile_state_id", referencedColumnName = "state_id", nullable = false)
	private State state;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "profile_country_id", referencedColumnName = "country_id", nullable = false)
	private Country country;

	// bi-directional many-to-one association to Promotion
	@OneToMany(mappedBy = "insEmp")
	private List<Promotion> insertedPromotions;

	// bi-directional many-to-one association to Promotion
	@OneToMany(mappedBy = "updateEmp")
	private List<Promotion> updatedPromotions;

	@OneToMany
	@JoinTable(name = "points", joinColumns =
		{ @JoinColumn(name = "ins_emp_id", nullable = false) }, inverseJoinColumns =
		{ @JoinColumn(name = "purchase_id", nullable = false) })
	private List<Purchase> insertedPurchases;

	@OneToMany
	@JoinTable(name = "points", joinColumns =
		{ @JoinColumn(name = "ins_emp_id", nullable = false) }, inverseJoinColumns =
		{ @JoinColumn(name = "points_exchange_id", nullable = false) })
	private List<PointsExchange> insertedPointsExchange;

	// bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy = "profile")
	private List<Purchase> purchases;

	// bi-directional many-to-one association to SysEvent
	@OneToMany(mappedBy = "insEmp")
	private List<SysEvent> insertedSysEvents;

	// bi-directional many-to-one association to SysEvent
	@OneToMany(mappedBy = "updateEmp")
	private List<SysEvent> updatedSysEvents;

	// bi-directional many-to-one association to SysPayment
	@OneToMany(mappedBy = "insEmp")
	private List<SysPayment> insertedSysPayments;

	public Profile()
	{
	}

	public long getProfileId()
	{
		return this.profileId;
	}

	public void setProfileId(long profileId)
	{
		this.profileId = profileId;
		setLoginId(profileId);
	}

	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public Date getBirthDate()
	{
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getEmail()
	{
		return this.email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Object getFullName()
	{
		return firstName + " " + (midName != null ? midName + " " : "") + lastName;
	}

	public String getFirstName()
	{
		return this.firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public Date getInsertDate()
	{
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate)
	{
		this.insertDate = insertDate;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getMidName()
	{
		return this.midName;
	}

	public void setMidName(String midName)
	{
		this.midName = midName;
	}

	public String getTel()
	{
		return this.tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public Character getGender()
	{
		return gender;
	}

	public void setGender(Character gender)
	{
		this.gender = gender;
	}

	public List<AgentRate> getAgentRates()
	{
		return this.agentRates;
	}

	public void setAgentRates(List<AgentRate> agentRates)
	{
		this.agentRates = agentRates;
	}

	public AgentRate addAgentRate(AgentRate agentRate)
	{
		getAgentRates().add(agentRate);
		agentRate.setProfile(this);

		return agentRate;
	}

	public AgentRate removeAgentRate(AgentRate agentRate)
	{
		getAgentRates().remove(agentRate);
		agentRate.setProfile(null);

		return agentRate;
	}

	public List<Contract> getAddedContracts()
	{
		return this.addedContracts;
	}

	public void setAddedContracts(List<Contract> addedContracts)
	{
		this.addedContracts = addedContracts;
	}

	public Contract addAddedContract(Contract addedContract)
	{
		getAddedContracts().add(addedContract);
		addedContract.setInsEmp(this);

		return addedContract;
	}

	public Contract removeAddedContract(Contract addedContract)
	{
		getAddedContracts().remove(addedContract);
		addedContract.setInsEmp(null);

		return addedContract;
	}

	public List<Contract> getUpdatedContracts()
	{
		return this.updatedContracts;
	}

	public void setUpdatedContracts(List<Contract> updatedContracts)
	{
		this.updatedContracts = updatedContracts;
	}

	public Contract addUpdatedContract(Contract updatedContract)
	{
		getUpdatedContracts().add(updatedContract);
		updatedContract.setUpdateEmp(this);

		return updatedContract;
	}

	public Contract removeUpdatedContract(Contract updatedContract)
	{
		getUpdatedContracts().remove(updatedContract);
		updatedContract.setUpdateEmp(null);

		return updatedContract;
	}

	public List<ItemStatus> getItemStatuses()
	{
		return this.itemStatuses;
	}

	public void setItemStatuses(List<ItemStatus> itemStatuses)
	{
		this.itemStatuses = itemStatuses;
	}

	public ItemStatus addItemStatus(ItemStatus itemStatus)
	{
		getItemStatuses().add(itemStatus);
		itemStatus.setUpdateStatusEmp(this);

		return itemStatus;
	}

	public ItemStatus removeItemStatus(ItemStatus itemStatus)
	{
		getItemStatuses().remove(itemStatus);
		itemStatus.setUpdateStatusEmp(null);

		return itemStatus;
	}

	public List<MessageCenter> getRequestedMessages()
	{
		return this.requestedMessages;
	}

	public void setRequestedMessages(List<MessageCenter> requestedMessages)
	{
		this.requestedMessages = requestedMessages;
	}

	public MessageCenter addRequestedMessage(MessageCenter requestedMessage)
	{
		getRequestedMessages().add(requestedMessage);
		requestedMessage.setRequestAgentEmp(this);

		return requestedMessage;
	}

	public MessageCenter removeRequestedMessage(MessageCenter requestedMessage)
	{
		getRequestedMessages().remove(requestedMessage);
		requestedMessage.setRequestAgentEmp(null);

		return requestedMessage;
	}

	public List<MessageCenter> getResponsedMessages()
	{
		return this.responsedMessages;
	}

	public void setResponsedMessages(List<MessageCenter> responsedMessages)
	{
		this.responsedMessages = responsedMessages;
	}

	public MessageCenter addResponsedMessage(MessageCenter responsedMessage)
	{
		getResponsedMessages().add(responsedMessage);
		responsedMessage.setResponseEmp(this);

		return responsedMessage;
	}

	public MessageCenter removeResponsedMessage(MessageCenter responsedMessage)
	{
		getResponsedMessages().remove(responsedMessage);
		responsedMessage.setResponseEmp(null);

		return responsedMessage;
	}

	public List<Point> getInsertedPoints()
	{
		return this.insertedPoints;
	}

	public void setInsertedPoints(List<Point> insertedPoints)
	{
		this.insertedPoints = insertedPoints;
	}

	public Point addInsertedPoint(Point insertedPoint)
	{
		getInsertedPoints().add(insertedPoint);
		insertedPoint.setInsEmp(this);

		return insertedPoint;
	}

	public Point removeInsertedPoint(Point insertedPoint)
	{
		getInsertedPoints().remove(insertedPoint);
		insertedPoint.setInsEmp(null);

		return insertedPoint;
	}

	public List<Point> getPoints()
	{
		return this.points;
	}

	public void setPoints(List<Point> points)
	{
		this.points = points;
	}

	public Point addPoint(Point point)
	{
		getPoints().add(point);
		point.setPointsProfile(this);

		return point;
	}

	public Point removePoint(Point point)
	{
		getPoints().remove(point);
		point.setPointsProfile(null);

		return point;
	}

	public List<PointsExchange> getPointsExchanges()
	{
		return this.pointsExchanges;
	}

	public void setPointsExchanges(List<PointsExchange> pointsExchanges)
	{
		this.pointsExchanges = pointsExchanges;
	}

	public PointsExchange addPointsExchange(PointsExchange pointsExchange)
	{
		getPointsExchanges().add(pointsExchange);
		pointsExchange.setProfile(this);

		return pointsExchange;
	}

	public PointsExchange removePointsExchange(PointsExchange pointsExchange)
	{
		getPointsExchanges().remove(pointsExchange);
		pointsExchange.setProfile(null);

		return pointsExchange;
	}

	public List<Product> getInsertedProducts()
	{
		return this.insertedProducts;
	}

	public void setInsertedProducts(List<Product> insertedProducts)
	{
		this.insertedProducts = insertedProducts;
	}

	public Product addInsertedProduct(Product insertedProduct)
	{
		getInsertedProducts().add(insertedProduct);
		insertedProduct.setInsEmp(this);

		return insertedProduct;
	}

	public Product removeInsertedProduct(Product insertedProduct)
	{
		getInsertedProducts().remove(insertedProduct);
		insertedProduct.setInsEmp(null);

		return insertedProduct;
	}

	public List<Product> getUpdatedProducts()
	{
		return this.updatedProducts;
	}

	public void setUpdatedProducts(List<Product> updatedProducts)
	{
		this.updatedProducts = updatedProducts;
	}

	public Product addUpdatedProduct(Product updatedProduct)
	{
		getUpdatedProducts().add(updatedProduct);
		updatedProduct.setUpdateEmp(this);

		return updatedProduct;
	}

	public Product removeUpdatedProduct(Product updatedProduct)
	{
		getUpdatedProducts().remove(updatedProduct);
		updatedProduct.setUpdateEmp(null);

		return updatedProduct;
	}

	public Agent getAgent()
	{
		return this.agent;
	}

	public void setAgent(Agent agent)
	{
		this.agent = agent;
	}

	public Role getRole()
	{
		return this.role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public District getDistrict()
	{
		return this.district;
	}

	public void setDistrict(District district)
	{
		this.district = district;
	}

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public Country getCountry()
	{
		return country;
	}

	public void setCountry(Country country)
	{
		this.country = country;
	}

	public List<Promotion> getInsertedPromotions()
	{
		return this.insertedPromotions;
	}

	public void setInsertedPromotions(List<Promotion> insertedPromotions)
	{
		this.insertedPromotions = insertedPromotions;
	}

	public Promotion addInsertedPromotion(Promotion insertedPromotion)
	{
		getInsertedPromotions().add(insertedPromotion);
		insertedPromotion.setInsEmp(this);

		return insertedPromotion;
	}

	public Promotion removeInsertedPromotion(Promotion insertedPromotion)
	{
		getInsertedPromotions().remove(insertedPromotion);
		insertedPromotion.setInsEmp(null);

		return insertedPromotion;
	}

	public List<Promotion> getUpdatedPromotions()
	{
		return this.updatedPromotions;
	}

	public void setUpdatedPromotions(List<Promotion> updatedPromotions)
	{
		this.updatedPromotions = updatedPromotions;
	}

	public Promotion addUpdatedPromotion(Promotion updatedPromotion)
	{
		getUpdatedPromotions().add(updatedPromotion);
		updatedPromotion.setUpdateEmp(this);

		return updatedPromotion;
	}

	public Promotion removeUpdatedPromotion(Promotion updatedPromotion)
	{
		getUpdatedPromotions().remove(updatedPromotion);
		updatedPromotion.setUpdateEmp(null);

		return updatedPromotion;
	}

	public List<Purchase> getInsertedPurchases()
	{
		return this.insertedPurchases;
	}

	public void setInsertedPurchases(List<Purchase> insertedPurchases)
	{
		this.insertedPurchases = insertedPurchases;
	}

	public List<PointsExchange> getInsertedPointsExchange()
	{
		return this.insertedPointsExchange;
	}

	public void setInsertedPointsExchange(List<PointsExchange> insertedPointsExchange)
	{
		this.insertedPointsExchange = insertedPointsExchange;
	}

	public List<Purchase> getPurchases()
	{
		return this.purchases;
	}

	public void setPurchases(List<Purchase> purchases)
	{
		this.purchases = purchases;
	}

	public Purchase addPurchas(Purchase purchas)
	{
		getPurchases().add(purchas);
		purchas.setProfile(this);

		return purchas;
	}

	public Purchase removePurchas(Purchase purchas)
	{
		getPurchases().remove(purchas);
		purchas.setProfile(null);

		return purchas;
	}

	public List<SysEvent> getInsertedSysEvents()
	{
		return this.insertedSysEvents;
	}

	public void setInsertedSysEvents(List<SysEvent> insertedSysEvents)
	{
		this.insertedSysEvents = insertedSysEvents;
	}

	public SysEvent addInsertedSysEvent(SysEvent insertedSysEvent)
	{
		getInsertedSysEvents().add(insertedSysEvent);
		insertedSysEvent.setInsEmp(this);

		return insertedSysEvent;
	}

	public SysEvent removeInsertedSysEvent(SysEvent insertedSysEvent)
	{
		getInsertedSysEvents().remove(insertedSysEvent);
		insertedSysEvent.setInsEmp(null);

		return insertedSysEvent;
	}

	public List<SysEvent> getUpdatedSysEvents()
	{
		return this.updatedSysEvents;
	}

	public void setUpdatedSysEvents(List<SysEvent> updatedSysEvents)
	{
		this.updatedSysEvents = updatedSysEvents;
	}

	public SysEvent addUpdatedSysEvent(SysEvent updatedSysEvent)
	{
		getUpdatedSysEvents().add(updatedSysEvent);
		updatedSysEvent.setUpdateEmp(this);

		return updatedSysEvent;
	}

	public SysEvent removeUpdatedSysEvent(SysEvent updatedSysEvent)
	{
		getUpdatedSysEvents().remove(updatedSysEvent);
		updatedSysEvent.setUpdateEmp(null);

		return updatedSysEvent;
	}

	public List<SysPayment> getInsertedSysPayments()
	{
		return this.insertedSysPayments;
	}

	public void setInsertedSysPayments(List<SysPayment> insertedSysPayments)
	{
		this.insertedSysPayments = insertedSysPayments;
	}

	public SysPayment addInsertedSysPayment(SysPayment insertedSysPayment)
	{
		getInsertedSysPayments().add(insertedSysPayment);
		insertedSysPayment.setInsEmp(this);

		return insertedSysPayment;
	}

	public SysPayment removeInsertedSysPayment(SysPayment insertedSysPayment)
	{
		getInsertedSysPayments().remove(insertedSysPayment);
		insertedSysPayment.setInsEmp(null);

		return insertedSysPayment;
	}

	@Override
	public Map<String, Object> transformToMap()
	{
		Map<String, Object> map = transformMainDataToMap();
		return map;
	}

	@Override
	public Map<String, Object> transformMainDataToMap()
	{
		Map<String, Object> map = super.transformMainDataToMap();
		map.put(EntityConstants.Profile.profileId, profileId);
		map.put(EntityConstants.Profile.fullName, getFullName());
		map.put(EntityConstants.Profile.firstName, firstName);
		map.put(EntityConstants.Profile.midName, midName);
		map.put(EntityConstants.Profile.lastName, lastName);
		map.put(EntityConstants.Profile.password, getPassword());
		map.put(EntityConstants.Profile.email, email);
		map.put(EntityConstants.Profile.address, address);
		map.put(EntityConstants.Profile.tel, tel);
		map.put(EntityConstants.Profile.gender, gender);
		map.put(EntityConstants.Profile.mobile, getMobile());
		map.put(EntityConstants.Profile.insertDate, getStringFromDate(insertDate));
		map.put(EntityConstants.Profile.birthDate, getStringFromDate(birthDate));
		map.put(EntityConstants.Profile.agentId, agent != null ? agent.getAgentId() : 0);
		map.put(EntityConstants.Profile.roleId, role.getRoleId());
		map.put(EntityConstants.Profile.role, role.transformMainDataToMap());
		map.put(EntityConstants.Profile.districtId, district != null ? district.getDistrictId() : 0);
		map.put(EntityConstants.Profile.district, district != null ? district.transformMainDataToMap() : null);
		map.put(EntityConstants.Profile.stateId, state != null ? state.getStateId() : 0);
		map.put(EntityConstants.Profile.state, state != null ? state.transformMainDataToMap() : null);
		map.put(EntityConstants.Profile.countryId, country.getCountryId());
		map.put(EntityConstants.Profile.country, country != null ? country.transformMainDataToMap() : null);
		return map;
	}

	@Override
	public void setEntityData(Map<String, Object> data, boolean useId) throws EntityException
	{
		super.setEntityData(data, useId);

		setFirstName((String) data.get(EntityConstants.Profile.firstName));
		setMidName((String) data.get(EntityConstants.Profile.midName));
		setLastName((String) data.get(EntityConstants.Profile.lastName));
		setEmail((String) data.get(EntityConstants.Profile.email));
		setAddress((String) data.get(EntityConstants.Profile.address));
		setTel((String) data.get(EntityConstants.Profile.tel));
		setMobile((String) data.get(EntityConstants.Profile.mobile));
		// setInsertDate((Date) data.get(EntityConstants.Profile.insertDate));
		setBirthDate((Date) data.get(EntityConstants.Profile.birthDate));
		// Agent agent = new Agent();
		// agent.setAgentId(((Number) data.get(EntityConstants.Profile.agentId)).longValue());
		// setAgent(agent);
		// Role role = new Role();
		// role.setRoleId(((Number) data.get(EntityConstants.Profile.roleId)).intValue());
		// setRole(role);
		// DistrictPK districtPK = new DistrictPK();
		// districtPK.setDistrictId(((Number) data.get(EntityConstants.Profile.districtId)).intValue());
		// districtPK.setStateId(((Number) data.get(EntityConstants.Profile.stateId)).intValue());
		// districtPK.setCountryId(((Number) data.get(EntityConstants.Profile.countryId)).byteValue());
		// District district = new District();
		// district.setId(districtPK);
		// setDistrict(district);
	}

	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery,
			Root<T> criteriaRoot, Map<String, Object> criteria) throws EntityException
	{
		return super.setCriteria(criteriaBuilder, criteriaQuery, criteriaRoot, criteria);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public <T extends AbsoluteEntity> List<Predicate> setCriteria(final CriteriaBuilder criteriaBuilder, final Root<T> criteriaRoot,
			Map<String, Object> criteria) throws EntityException
	{
		List<Predicate> predicateList = super.setCriteria(criteriaBuilder, criteriaRoot, criteria);
		Number profileId = (Number) criteria.get(EntityConstants.Profile.profileId);
		if (profileId != null)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Profile.profileId), profileId.longValue()));
		String mobile = (String) criteria.get(EntityConstants.Profile.mobile);
		if (mobile != null&& mobile.trim().length() > 0)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Profile.mobile), mobile));
		String firstName = (String) criteria.get(EntityConstants.Profile.firstName);
		if (firstName != null&& firstName.trim().length() > 0)
			predicateList.add(criteriaBuilder.like(criteriaRoot.get(EntityConstants.Profile.firstName), firstName + "%"));
		String lastName = (String) criteria.get(EntityConstants.Profile.lastName);
		if (lastName != null&& lastName.trim().length() > 0)
			predicateList.add(criteriaBuilder.like(criteriaRoot.get(EntityConstants.Profile.lastName), lastName + "%"));
		String fullName = (String) criteria.get(EntityConstants.Profile.fullName);
		if (fullName != null && fullName.trim().length() > 0)
		{
			Expression<String> firstNameExp = criteriaBuilder.concat(criteriaRoot.get(EntityConstants.Profile.firstName), " ");
			Expression<String> midNameExp = criteriaBuilder.concat(criteriaRoot.get(EntityConstants.Profile.midName), " ");
			Expression<String> firstMidNameExp = criteriaBuilder.concat(firstNameExp, midNameExp);
			Expression<String> fullNameExp = criteriaBuilder.concat(firstMidNameExp, criteriaRoot.get(EntityConstants.Profile.lastName));

			predicateList.add(criteriaBuilder.like(fullNameExp, fullName + "%"));
		}
		byte[] password = (byte[]) criteria.get(EntityConstants.Profile.password);
		if (password != null && password.length > 0)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Profile.password), password));
		String email = (String) criteria.get(EntityConstants.Profile.email);
		if (email != null&& email.trim().length() > 0)
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Profile.email), email));
		Long birthDateSeLong = (Long) criteria.get(EntityConstants.Profile.birthDate);
		if (birthDateSeLong != null)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(birthDateSeLong);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.HOUR, 0);
			predicateList.add(criteriaBuilder.equal(criteriaRoot.get(EntityConstants.Profile.birthDate), cal.getTime()));
		}
		Number agentId = (Number) criteria.get(EntityConstants.Profile.agentId);
		if (agentId != null)
		{
			Join<Profile, Agent> profileAgentJoin = criteriaRoot.join(EntityConstants.Profile.agent);
			predicateList.add(criteriaBuilder.equal(profileAgentJoin.get(EntityConstants.Agent.agentId), agentId.longValue()));
		}

		Long insertDateFrom = (Long) criteria.get(EntityConstants.Profile.insertDate_From_Search);
		if (insertDateFrom != null)
			predicateList
					.add(criteriaBuilder.greaterThanOrEqualTo(criteriaRoot.get(EntityConstants.Profile.insertDate), getDateFromLong(insertDateFrom)));
		Long insertDateTo = (Long) criteria.get(EntityConstants.Profile.insertDate_To_Search);
		if (insertDateTo != null)
			predicateList.add(criteriaBuilder.lessThanOrEqualTo(criteriaRoot.get(EntityConstants.Profile.insertDate), getDateFromLong(insertDateTo)));
		Number roleId = (Number) criteria.get(EntityConstants.Profile.roleId);
		if (roleId != null)
		{
			Join<Profile, Role> profileRoleJoin = criteriaRoot.join(EntityConstants.Profile.role);
			predicateList.add(criteriaBuilder.equal(profileRoleJoin.get(EntityConstants.Role.roleId), roleId.intValue()));
		}

		List roleIds = (List) criteria.get(EntityConstants.Profile.roleIds);
		if (roleIds != null && !roleIds.isEmpty())
		{
			Join<Profile, Role> profileRoleJoin = criteriaRoot.join(EntityConstants.Profile.role);
			predicateList.add(profileRoleJoin.get(EntityConstants.Role.roleId).in(roleIds));
		}
		return predicateList;
	}

	@Override
	protected void setEntityId(Map<String, Object> data)
	{
		long id = ((Number) data.get(EntityConstants.Profile.profileId)).longValue();
		setItemId(id);
		setLoginId(id);
		setProfileId(id);
	}

	@Override
	public void setEntityId(Object idObj)
	{
		long id = ((Number) idObj).longValue();
		setItemId(id);
		setLoginId(id);
		setProfileId(id);
	}

	@Override
	public Object getEntityId()
	{
		return getProfileId();
	}

}
