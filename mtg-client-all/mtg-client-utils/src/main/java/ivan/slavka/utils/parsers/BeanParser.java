package ivan.slavka.utils.parsers;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.beans.RosterBean;
import ivan.slavka.utils.beans.RuleBean;
import ivan.slavka.utils.enums.InvitationAnswerEnum;
import ivan.slavka.utils.enums.InvitationTypeEnum;
import ivan.slavka.utils.enums.LoginTypeEnum;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.utils.exceptions.ParseEnumException;
import ivan.slavka.utils.jaxb.beans.invitation.Invite;
import ivan.slavka.utils.jaxb.beans.login.Login;
import ivan.slavka.utils.jaxb.beans.mtg.card.Rule;
import ivan.slavka.utils.jaxb.beans.mtg.card.Rulelist;
import ivan.slavka.utils.jaxb.beans.mtg.card.Typelist;
import ivan.slavka.utils.jaxb.beans.play.card.Card;
import ivan.slavka.utils.jaxb.beans.roster.Roster;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanParser {

	private static final Log logger = LogFactory.getLog(BeanParser.class);
	private static BeanParser beanParser = null;

	private BeanParser(){}

	public static BeanParser getInstance(){
		if(beanParser == null){
			beanParser = new BeanParser();
		}
		return beanParser;
	}

	public static LoginBean jaxbToBean(Login jaxbBean){

		LoginBean bean = new LoginBean();
		try{
			bean.setLoginType(LoginTypeEnum.parse(jaxbBean.getType()));
			bean.setUsername(jaxbBean.getUsername());
		} catch(ParseEnumException e){
			logger.error(e.getMessage(), e);
		}
		return bean;
	}

	public static InviteBean jaxbToBean(Invite jaxbBean){

		InviteBean bean = new InviteBean();
		try{
			bean.setFrom(jaxbBean.getFrom());
			bean.setTo(jaxbBean.getTo());
			if(jaxbBean.getAnswer() != null && !jaxbBean.getAnswer().isEmpty()){
				bean.setAnswer(InvitationAnswerEnum.parse(jaxbBean.getAnswer()));
			}
			if(jaxbBean.getType() != null && !jaxbBean.getType().isEmpty()){
				bean.setType(InvitationTypeEnum.parse(jaxbBean.getType()));
			}
			if(jaxbBean.getSession() != null && !jaxbBean.getSession().isEmpty()){
				bean.setSessionId(jaxbBean.getSession());
			}
			if(jaxbBean.getTurnOwner() != null && !jaxbBean.getTurnOwner().isEmpty()){
				bean.setTurnOwner(PlayerEnum.parse(jaxbBean.getTurnOwner()));
			}
		} catch(ParseEnumException e){
			logger.error(e.getMessage(), e);
		}
		return bean;
	}

	public static Invite beanToJaxb(InviteBean bean){

		Invite jaxbBean = new Invite();
		jaxbBean.setFrom(bean.getFrom());
		jaxbBean.setTo(bean.getTo());
		jaxbBean.setType(bean.getType().toString());
		if(bean.getAnswer() != null){
			jaxbBean.setAnswer(bean.getAnswer().toString());
		}
		if(bean.getSessionId() != null && !bean.getSessionId().isEmpty()){
			jaxbBean.setSession(bean.getSessionId());
		}
		if(bean.getTurnOwner() != null){
			jaxbBean.setTurnOwner(bean.getTurnOwner().toString());
		}
		return jaxbBean;
	}

	public static Login beanToJaxb(LoginBean bean){

		Login jaxbBean = new Login();
		jaxbBean.setUsername(bean.getUsername());
		jaxbBean.setType(bean.getLoginType().toString());
		return jaxbBean;
	}

	public static Roster beanToJaxb(RosterBean bean){

		Roster jaxbBean = new Roster();
		for(String username : bean.getRoster()){
			jaxbBean.getName().add(username);
		}
		return jaxbBean;
	}

	public static RosterBean jaxbToBean(Roster jaxbBean){

		RosterBean bean = new RosterBean();
		for(String username : jaxbBean.getName()){
			bean.getRoster().add(username);
		}
		return bean;
	}

	public static Card beanToJaxb(CardBean bean){

		Card card = new Card();

		ivan.slavka.utils.jaxb.beans.mtg.card.Card jaxbBean = new ivan.slavka.utils.jaxb.beans.mtg.card.Card();
		jaxbBean.setCost(bean.getManaCost());
		jaxbBean.setHand(bean.getHand());
		jaxbBean.setLife(bean.getLife());
		jaxbBean.setLoyalty(bean.getLoyalty());
		jaxbBean.setName(bean.getName());
		jaxbBean.setPow(bean.getPower());

		Rulelist ruleList = new Rulelist();
		Rule rule;
		for(RuleBean ruleBean : bean.getRuleList()){
			rule = new Rule();
			rule.setValue(ruleBean.getRule());
			rule.setReminder(ruleBean.getReminder());
			ruleList.getRule().add(rule);
		}

		jaxbBean.setTgh(bean.getToughness());

		Typelist typeList = new Typelist();
		for(String type : bean.getTypeList()){
			typeList.getType().add(type);
		}

		card.setCard(jaxbBean);
		card.setType("REQUEST");

		return card;
	}

	public static CardBean jaxbToBean(Card jaxbBean){

		CardBean cardBean = new CardBean();
		ivan.slavka.utils.jaxb.beans.mtg.card.Card card = jaxbBean.getCard();

		cardBean.setHand(card.getHand());
		cardBean.setLife(card.getLife());
		cardBean.setLoyalty(card.getLoyalty());
		cardBean.setManaCost(card.getCost());
		cardBean.setName(card.getName());
		cardBean.setPower(card.getPow());
		cardBean.setToughness(card.getTgh());

		RuleBean ruleBean;
		for(Object o : card.getRulelist().getRule()) {
			Rule rule = (Rule) o;
			ruleBean  = new RuleBean();
			ruleBean.setRule(rule.getValue());
			ruleBean.setReminder(rule.getReminder());
			cardBean.getRuleList().add(ruleBean);
		}

		for(String type : card.getTypelist().getType()){
			cardBean.getTypeList().add(type);
		}

		// TODO: Add support for Multi.

		return cardBean;
	}
}
