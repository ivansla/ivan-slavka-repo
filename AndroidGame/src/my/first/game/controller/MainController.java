package my.first.game.controller;

import java.util.ArrayList;
import java.util.List;

import my.first.game.beans.CharacterScreenBean;
import my.first.game.beans.EquipableItemBean;
import my.first.game.beans.WarriorBean;
import my.first.game.beans.WorldGenerator;
import my.first.game.enums.CharacterClassEnum;
import my.first.game.enums.LocationEnum;
import my.first.game.utils.ViewHandler;

public class MainController {

	private static MainController controller = null;
	private BattleController battleController = BattleController.getInstance();
	private WorldGenerator world = WorldGenerator.getInstance();

	private CharacterScreenBean tempMainCharacter;
	private CharacterScreenBean mainCharacter;
	private List<String> possibleLocationList = new ArrayList<String>();

	private MainController(){
	}

	public static MainController getInstance(){
		if(MainController.controller == null){
			MainController.controller = new MainController();
		}
		return MainController.controller;
	}

	public void createCharacter(String characterName, CharacterClassEnum characterClass){

		switch (characterClass) {
		case WARRIOR:
			this.mainCharacter = new CharacterScreenBean(new WarriorBean(characterName, this.world.getLocation(LocationEnum.RUZINOV_SALEZIANI)));
			break;
		case MAGE:
			break;
		case ROGUE:
			break;
		default:
			//gsdthis.mainCharacter = new CharacterScreenBean(new WarriorBean(characterName));
			break;
		}

		this.battleController.setMainCharacter(this.mainCharacter);
	}

	public void attackMonster(){

	}

	public CharacterScreenBean getCharacterScreenBean(){
		return this.mainCharacter;
	}

	public List<String> getPossibleLocationList(){
		this.refreshPossibleLocationList();
		return this.possibleLocationList;
	}

	public void goToOtherLocation(LocationEnum location, ViewHandler handler){
		this.mainCharacter.changeLocation(this.world.getLocation(location));
		this.refreshPossibleLocationList();
		//Message completeMessage = handler.obtainMessage();
		//completeMessage.sendToTarget();
	}

	private void refreshPossibleLocationList(){
		this.possibleLocationList.clear();
		for(LocationEnum location :	this.mainCharacter.getCharacterBean().getCurrentLocation().getPossibleLocationList()){
			this.possibleLocationList.add(location.toString());
		}
	}

	public CharacterScreenBean previewToggleEquipItem(EquipableItemBean item){
		if(this.tempMainCharacter == null){
			this.tempMainCharacter = this.mainCharacter.copy();
		}

		//CharacterScreenBean tempCharacterScreen = this.tempMainCharacter.copy();
		
		if(item != null){
			if(item.isTempEquiped()){
				this.tempMainCharacter.tempUnequipItem(item);
				//tempCharacterScreen.tempUnequipItem(item);
			} else {
				this.tempMainCharacter.tempEquipItem(item);
				//tempCharacterScreen.tempEquipItem(item);
			}
		}
		
		
		return this.tempMainCharacter;
	}

	public void toggleEquipItem(){
		this.mainCharacter.equipItems();
	}

	public void resetTempEquip(){
		this.mainCharacter.resetTempEquipItems();
	}
}
