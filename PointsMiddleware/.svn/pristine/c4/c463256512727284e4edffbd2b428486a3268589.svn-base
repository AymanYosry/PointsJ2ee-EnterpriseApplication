package com.ewhale.points.model.qualifiers;

import com.ewhale.points.model.entity.Agent;
import com.ewhale.points.model.entity.AgentRate;
import com.ewhale.points.model.entity.Branch;
import com.ewhale.points.model.entity.Category;
import com.ewhale.points.model.entity.Contract;
import com.ewhale.points.model.entity.Country;
import com.ewhale.points.model.entity.Currency;
import com.ewhale.points.model.entity.District;
import com.ewhale.points.model.entity.FunctionType;
import com.ewhale.points.model.entity.ItemStatus;
import com.ewhale.points.model.entity.Login;
import com.ewhale.points.model.entity.MessageCenter;
import com.ewhale.points.model.entity.PaymentMethod;
import com.ewhale.points.model.entity.Point;
import com.ewhale.points.model.entity.PointsExchange;
import com.ewhale.points.model.entity.Product;
import com.ewhale.points.model.entity.Profile;
import com.ewhale.points.model.entity.Promotion;
import com.ewhale.points.model.entity.Purchase;
import com.ewhale.points.model.entity.Role;
import com.ewhale.points.model.entity.State;
import com.ewhale.points.model.entity.Status;
import com.ewhale.points.model.entity.SysEvent;
import com.ewhale.points.model.entity.SysInvoice;
import com.ewhale.points.model.entity.SysPayment;

public enum EntityClassEnum
{
	AGENT(Agent.class), AGENTRATE(AgentRate.class), BRANCH(Branch.class), CATEGORY(Category.class), CONTRACT(Contract.class), COUNTRY(Country.class),
	CURRENCY(Currency.class), DISTRICT(District.class), FUNCTIONTYPE(FunctionType.class), ITEMSTATUS(ItemStatus.class),
	MESSAGECENTER(MessageCenter.class), PAYMENTMETHOD(PaymentMethod.class), POINT(Point.class), POINTEXCHANGE(PointsExchange.class),
	PRODUCT(Product.class), PROFILE(Profile.class), PROMOTION(Promotion.class), PURCHASE(Purchase.class), ROLE(Role.class),
	STATE(State.class), STATUS(Status.class), SYSEVENT(SysEvent.class), SYSINVOICE(SysInvoice.class), SYSPAYMENT(SysPayment.class),
	LOGIN(Login.class);

	@SuppressWarnings("rawtypes")
	public Class entityClass;

	@SuppressWarnings("rawtypes")
	private EntityClassEnum(Class entityClass)
	{
		this.entityClass = entityClass;
	}
}
