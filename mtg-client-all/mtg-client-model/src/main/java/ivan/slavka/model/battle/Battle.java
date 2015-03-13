package ivan.slavka.model.battle;

import ivan.slavka.model.interfaces.IBattle;
import ivan.slavka.utils.beans.CardBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Battle implements IBattle{

	private static Battle battle = null;

	private final HashMap<CardBean, List<CardBean>> battleMap;

	private Battle(){
		this.battleMap = new HashMap<CardBean, List<CardBean>>();
	}

	public Battle getInstance(){
		if(battle == null){
			battle = new Battle();
		}
		return battle;
	}

	private void endBattle(){
		battle = null;
	}

	@Override
	public void addAttackCard(CardBean attackCard) {
		this.battleMap.put(attackCard, new ArrayList<CardBean>());
	}

	@Override
	public void addDefendCard(CardBean attackCard, CardBean defendCard) {
		this.battleMap.get(attackCard).add(defendCard);
	}

	@Override
	public void removeAttackCard(CardBean attackCard) {
		this.battleMap.remove(attackCard);
	}

	@Override
	public void removeDefendCard(CardBean attackCard, CardBean defendCard) {
		this.battleMap.get(attackCard).remove(defendCard);
	}

	@Override
	public void startBattle() {
		// TODO Auto-generated method stub

	}
}
