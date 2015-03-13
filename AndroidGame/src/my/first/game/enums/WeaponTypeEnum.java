package my.first.game.enums;

public enum WeaponTypeEnum {

	DAGGER(1),
	SHORT_SWORD(2),
	SABRE(3),
	SMALL_AXE(4),
	AXE(5),
	LARGE_AXE(6),
	BROAD_AXE(7),
	BATTLE_AXE(8),
	GREAT_AXE(9),
	CLUB(10),
	SPIKED_CLUB(11),
	MACE(12),
	MORNING_STAR(13),
	FLAIL(14),
	WAR_HAMMER(15),
	MAUL(16),
	SCIMITAR(17),
	BLADE(18),
	FALCHION(19),
	LONG_SWORD(20),
	CLAYMORE(21),
	BROAD_SWORD(22),
	BASTARD_SWORD(23),
	TWO_HANDED_SWORD(24),
	GREAT_SWORD(25),
	SHORT_STAFF(26),
	LONG_STAFF(27),
	COMPOSITE_STAFF(28),
	QUARTER_STAFF(29),
	WAR_STAFF(30);

	private int value;

	private WeaponTypeEnum(int value){
		this.value = value;
	}
}
