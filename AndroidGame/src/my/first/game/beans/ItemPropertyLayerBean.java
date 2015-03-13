package my.first.game.beans;

public class ItemPropertyLayerBean {

	private int strength;
	private int dexterity;
	private int vitality;
	private int magic;
	private int life;
	private int mana;
	private int armorClass;
	private int armorClassFromProperty;
	private int toHit;
	private int damageDone;
	private int damageDonePct;
	private int damageDonePts;
	private int stealLife;
	private int stealMana;
	private int resistMagic;
	private int resistFire;
	private int resistLightning;
	private int penetrateArmor;
	private int minDamage;
	private int maxDamage;


	public ItemPropertyLayerBean(){
		this.strength = 0;
		this.dexterity = 0;
		this.vitality = 0;
		this.magic = 0;
		this.life = 0;
		this.mana = 0;
		this.armorClass = 0;
		this.armorClassFromProperty = 0;
		this.toHit = 0;
		this.damageDonePct = 0;
		this.damageDonePts = 0;
		this.stealLife = 0;
		this.stealMana = 0;
		this.resistMagic = 0;
		this.resistLightning = 0;
		this.resistFire = 0;
		this.penetrateArmor = 0;
		this.minDamage = 0;
		this.maxDamage = 0;
	}

	public void resetItemPropertyLayer(){
		this.strength = 0;
		this.dexterity = 0;
		this.vitality = 0;
		this.magic = 0;
		this.life = 0;
		this.mana = 0;
		this.armorClass = 0;
		this.armorClassFromProperty = 0;
		this.toHit = 0;
		this.damageDonePct = 0;
		this.damageDonePts = 0;
		this.stealLife = 0;
		this.stealMana = 0;
		this.resistMagic = 0;
		this.resistLightning = 0;
		this.resistFire = 0;
		this.penetrateArmor = 0;
		this.minDamage = 0;
		this.maxDamage = 0;
	}

	public void assignItemMagicAttributes(EquipableItemBean item){

		this.armorClassFromProperty = 0;
		this.minDamage = 0;
		this.maxDamage = 0;

		if(item.getPrefix() != null){
			this.assignPropertyAttributes(item.getPrefix());
		}
		if(item.getSuffix() != null){
			this.assignPropertyAttributes(item.getSuffix());
		}

		switch(item.getEquipableItemType()){
		case ARMOR:
		case HELM:
		case SHIELD:
			this.armorClass += ((ArmorItemBean)item).getArmorClass() + (((ArmorItemBean)item).getArmorClass() * (double)(this.armorClassFromProperty / 100));
			break;
		case JEWELRY:
			break;
		default:
			this.minDamage += (((WeaponItemBean)item).getMinDamage() + (((WeaponItemBean)item).getMinDamage() * (double)(this.damageDonePct / 100)) + this.damageDonePts);
			this.maxDamage += (((WeaponItemBean)item).getMaxDamage() + (((WeaponItemBean)item).getMaxDamage() * (double)(this.damageDonePct / 100)) + this.damageDonePts);
			break;
		}
	}

	private void assignPropertyAttributes(ItemPropertyBean itemProperty){

		switch(itemProperty.getProperty()){
		case STRENGTH:
			this.strength += itemProperty.getActualValue1();
			break;
		case DEXTERITY:
			this.dexterity += itemProperty.getActualValue1();
			break;
		case VITALITY:
			this.vitality += itemProperty.getActualValue1();
			break;
		case MAGIC:
			this.magic += itemProperty.getActualValue1();
			break;
		case ALL_ATTRIBUTES:
			this.strength += itemProperty.getActualValue1();
			this.dexterity += itemProperty.getActualValue1();
			this.vitality += itemProperty.getActualValue1();
			this.magic += itemProperty.getActualValue1();
			break;
		case LIFE:
			this.life += itemProperty.getActualValue1();
			break;
		case MANA:
			this.mana += itemProperty.getActualValue1();
			break;
		case ARMOR_CLASS:
			this.armorClassFromProperty += itemProperty.getActualValue1();
			break;
		case TO_HIT:
			this.toHit += itemProperty.getActualValue1();
			break;
		case TO_HIT_DAMAGE_DONE:
			this.toHit += itemProperty.getActualValue1();
			this.damageDonePct += itemProperty.getActualValue2();
			break;
		case DAMAGE_DONE_PERCENT:
			this.damageDonePct += itemProperty.getActualValue1();
			break;
		case DAMAGE_DONE:
			this.damageDonePts += itemProperty.getActualValue1();
			break;
		case STEAL_LIFE:
			this.stealLife += itemProperty.getActualValue1();
			break;
		case STEAL_MANA:
			this.stealMana += itemProperty.getActualValue1();
			break;
		case RESIST_MAGIC:
			this.resistMagic += itemProperty.getActualValue1();
			break;
		case RESIST_FIRE:
			this.resistFire += itemProperty.getActualValue1();
			break;
		case RESIST_LIGHTNING:
			this.resistLightning += itemProperty.getActualValue1();
			break;
		case RESIST_ALL:
			this.resistFire += itemProperty.getActualValue1();
			this.resistMagic += itemProperty.getActualValue1();
			this.resistLightning += itemProperty.getActualValue1();
			break;
		}
	}



	public int getStrength() {
		return this.strength;
	}

	public void setStrength(int strength) {
		this.strength += strength;
	}

	public int getDexterity() {
		return this.dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity += dexterity;
	}

	public int getVitality() {
		return this.vitality;
	}

	public void setVitality(int vitality) {
		this.vitality += vitality;
	}

	public int getMagic() {
		return this.magic;
	}

	public void setMagic(int magic) {
		this.magic += magic;
	}

	public int getLife() {
		return this.life;
	}

	public void setLife(int life) {
		this.life += life;
	}

	public int getMana() {
		return this.mana;
	}

	public void setMana(int mana) {
		this.mana += mana;
	}

	public int getArmorClass() {
		return this.armorClass;
	}

	public void setArmorClass(int armorClass) {
		this.armorClass += armorClass;
	}

	public int getToHit() {
		return this.toHit;
	}

	public void setToHit(int toHit) {
		this.toHit += toHit;
	}

	public int getDamageDone() {
		return this.damageDone;
	}

	public void setDamageDone(int damageDone) {
		this.damageDone += damageDone;
	}

	public int getDamageDonePct() {
		return this.damageDonePct;
	}

	public void setDamageDonePct(int damageDonePct) {
		this.damageDonePct += damageDonePct;
	}

	public int getDamageDonePts() {
		return this.damageDonePts;
	}

	public void setDamageDonePts(int damageDonePts) {
		this.damageDonePts += damageDonePts;
	}

	public int getStealLife() {
		return this.stealLife;
	}

	public void setStealLife(int stealLife) {
		this.stealLife += stealLife;
	}

	public int getStealMana() {
		return this.stealMana;
	}

	public void setStealMana(int stealMana) {
		this.stealMana += stealMana;
	}

	public int getResistMagic() {
		return this.resistMagic;
	}

	public void setResistMagic(int resistMagic) {
		this.resistMagic += resistMagic;
	}

	public int getResistFire() {
		return this.resistFire;
	}

	public void setResistFire(int resistFire) {
		this.resistFire += resistFire;
	}

	public int getResistLightning() {
		return this.resistLightning;
	}

	public void setResistLightning(int resistLightning) {
		this.resistLightning += resistLightning;
	}

	public int getPenetrateArmor() {
		return this.penetrateArmor;
	}

	public void setPenetrateArmor(int penetrateArmor) {
		this.penetrateArmor += penetrateArmor;
	}

	public int getMinDamage() {
		return this.minDamage;
	}

	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	public int getMaxDamage() {
		return this.maxDamage;
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}
}
