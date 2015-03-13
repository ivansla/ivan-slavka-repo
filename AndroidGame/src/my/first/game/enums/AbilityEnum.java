package my.first.game.enums;

public enum AbilityEnum {

	BASIC_ATTACK(0),
	FIREBALL(1),
	IMMOLATION(2),
	FROSTBOLT(3),
	HEAL(4),
	CRIPPLE(5),
	METEOR_SHOWER(6),

	// TODO: toto casom musi ist odtialto prec
	HEALTH_POTION(7),
	MANA_POTION(8);

	int value;

	private AbilityEnum(int value){
		this.value = value;
	}

	@Override
	public String toString(){
		switch(this.value){
		case 0:
			return "Attack";
		case 1:
			return "Fireball";
		case 2:
			return "Immolation";
		case 3:
			return "Frostbolt";
		case 4:
			return "Heal";
		case 5:
			return "Cripple";
		case 6:
			return "Meteor Shower";
		case 7:
			return "Health Potion";
		case 8:
			return "Mana Potion";
		default:
			return "Attack";
		}
	}

	public static AbilityEnum toEnum(String stringToEnum){

		if(stringToEnum.equals("Fireball")){
			return FIREBALL;
		} else if(stringToEnum.equals("Immolation")){
			return IMMOLATION;
		} else if(stringToEnum.equals("Frostbolt")){
			return FROSTBOLT;
		} else if(stringToEnum.equals("Heal")){
			return HEAL;
		} else if(stringToEnum.equals("Cripple")){
			return CRIPPLE;
		} else if(stringToEnum.equals("Meteor Shower")){
			return METEOR_SHOWER;
		} else if(stringToEnum.equals("Health Potion")){
			return HEALTH_POTION;
		} else if(stringToEnum.equals("Mana Potion")){
			return MANA_POTION;
		} else {
			return BASIC_ATTACK;
		}
	}
}
