package my.first.game.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import my.first.game.beans.ArmorItemBean;
import my.first.game.beans.EquipableItemBean;
import my.first.game.beans.ItemBean;
import my.first.game.beans.ItemPropertyBean;
import my.first.game.beans.SpecialItemBean;
import my.first.game.beans.WeaponItemBean;
import my.first.game.enums.ArmorTypeEnum;
import my.first.game.enums.EquipableItemEnum;
import my.first.game.enums.ItemPropertyEnum;
import my.first.game.enums.LootSourceEnum;
import my.first.game.enums.SpecialItemTypeEnum;
import my.first.game.enums.WeaponTypeEnum;

public class ItemGenerator {

	private static ItemGenerator itemGenerator = null;

	private Random randomGenerator = new Random();
	private List<ItemPropertyBean> itemPropertyBeanList = new ArrayList<ItemPropertyBean>();
	private HashMap<String, EquipableItemBean>baseItemBeanList = new HashMap<String, EquipableItemBean>();
	private List<ItemBean> specialItemBeanList = new ArrayList<ItemBean>();

	private ItemGenerator(){

		ItemPropertyBean itemProperty;
		// Strength
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Frailty", false, ItemPropertyEnum.STRENGTH, -10, -6, "ASWJ", 3, 0, 0, 0, -3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Weakness", false, ItemPropertyEnum.STRENGTH, -5, -1, "ASWJ", 1, 0, 0, 0, -2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Strength", false, ItemPropertyEnum.STRENGTH, 1, 5, "ASWJ", 1, 200, 1000, 0, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Might", false, ItemPropertyEnum.STRENGTH, 6, 10, "ASWJ", 5, 4, 1200, 2000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Power", false, ItemPropertyEnum.STRENGTH, 11, 15, "ASWJ", 11, 4, 2200, 3000, 4);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Giants", false, ItemPropertyEnum.STRENGTH, 16, 20, "ASWJ", 17, 4, 3200, 5000, 7);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Titans", false, ItemPropertyEnum.STRENGTH, 21, 30, "WJ", 23, 9, 5200, 10000, 10);

		// Magic
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Fool", false, ItemPropertyEnum.MAGIC, -10, -6, "ASWJ", 3, 0, 0, 0, -3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Dyslexia", false, ItemPropertyEnum.MAGIC, -5, -1, "ASWJ", 1, 0, 0, 0, -2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Magic", false, ItemPropertyEnum.MAGIC, 1, 5, "ASWJ", 1, 4, 200, 1000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Mind", false, ItemPropertyEnum.MAGIC, 6, 10, "ASWJ", 5, 4, 1200, 2000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Brilliance", false, ItemPropertyEnum.MAGIC, 11, 15, "ASWJ", 11, 4, 2200, 3000, 4);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Sorcery", false, ItemPropertyEnum.MAGIC, 16, 20, "ASWJ", 17, 4, 3200, 5000, 7);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Wizardry", false, ItemPropertyEnum.MAGIC, 21, 30, "WJ", 23, 9, 5200, 10000, 10);

		// Dexterity
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Paralysis", false, ItemPropertyEnum.DEXTERITY, -10, -6, "ASWJ", 3, 0, 0, 0, -3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Atrophy", false, ItemPropertyEnum.DEXTERITY, -5, -1, "ASWJ", 1, 0, 0, 0, -2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Dexterity", false, ItemPropertyEnum.DEXTERITY, 1, 5, "ASWJ", 1, 4, 200, 1000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Skill", false, ItemPropertyEnum.DEXTERITY, 6, 10, "ASWJ", 5, 4, 1200, 2000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Accuracy", false, ItemPropertyEnum.DEXTERITY, 11, 15, "ASWJ", 11, 4, 2200, 3000, 4);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Precision", false, ItemPropertyEnum.DEXTERITY, 16, 20, "ASWJ", 17, 4, 3200, 5000, 7);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Perfection", false, ItemPropertyEnum.DEXTERITY, 21, 30, "WJ", 23, 9, 5200, 10000, 10);

		// Vitality
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Illness", false, ItemPropertyEnum.VITALITY, -10, -6, "ASWJ", 3, 0, 0, 0, -3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Disease", false, ItemPropertyEnum.VITALITY, -5, -1, "ASWJ", 1, 0, 0, 0, -2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Vitality", false, ItemPropertyEnum.VITALITY, 1, 5, "ASWJ", 1, 4, 200, 1000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Zest", false, ItemPropertyEnum.VITALITY, 6, 10, "ASWJ", 5, 4, 1200, 2000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Vim", false, ItemPropertyEnum.VITALITY, 11, 15, "ASWJ", 11, 4, 2200, 3000, 4);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Vigor", false, ItemPropertyEnum.VITALITY, 16, 20, "ASWJ", 17, 4, 3200, 5000, 7);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Life", false, ItemPropertyEnum.VITALITY, 21, 30, "J", 23, 9, 5200, 2000, 10);

		// All Attributes
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Trouble", false, ItemPropertyEnum.ALL_ATTRIBUTES, -10, -6, "ASWJ", 12, 0, 0, 0, -10);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Pit", false, ItemPropertyEnum.ALL_ATTRIBUTES, -5, -1, "ASWJ", 5, 0, 0, 0, -5);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Sky", false, ItemPropertyEnum.ALL_ATTRIBUTES, 1, 3, "ASWJ", 5, 2, 800, 4000, 5);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Moon", false, ItemPropertyEnum.ALL_ATTRIBUTES, 4, 7, "ASWJ", 11, 3, 4800, 8000, 10);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Stars", false, ItemPropertyEnum.ALL_ATTRIBUTES, 8, 11, "ASWJ", 17, 3, 8800, 12000, 15);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Heavens", false, ItemPropertyEnum.ALL_ATTRIBUTES, 12, 15, "WJ", 25, 3, 12800, 20000, 20);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Zodiac", false, ItemPropertyEnum.ALL_ATTRIBUTES, 16, 20, "J", 30, 4, 20800, 40000, 30);

		// Life
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Vulture", false, ItemPropertyEnum.LIFE, -25, -11, "ASJ", 4, 0, 0, 0, -4);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Jackal", false, ItemPropertyEnum.LIFE, -10, -1, "ASJ", 1, 0, 0, 0, -2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Fox", false, ItemPropertyEnum.LIFE, 10, 15, "ASJ", 1, 5, 100, 1000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Jaguar", false, ItemPropertyEnum.LIFE, 16, 20, "ASJ", 5, 4, 1100, 2000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Eagle", false, ItemPropertyEnum.LIFE, 21, 30, "ASJ", 9, 9, 2100, 4000, 5);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Wolf", false, ItemPropertyEnum.LIFE, 31, 40, "ASJ", 15, 10, 4100, 6000, 7);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Tiger", false, ItemPropertyEnum.LIFE, 41, 50, "ASJ", 21, 9, 6100, 10000, 9);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Lion", false, ItemPropertyEnum.LIFE, 51, 60, "AJ", 27, 9, 10100, 15000, 11);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Mammoth", false, ItemPropertyEnum.LIFE, 61, 80, "A", 35, 19, 15100, 19000, 12);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Whale", false, ItemPropertyEnum.LIFE, 81, 100, "A", 60, 19, 19100, 30000, 13);

		// Mana
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Corruption", false, ItemPropertyEnum.MANA, Integer.MAX_VALUE, Integer.MAX_VALUE, "ASW", 5, 0, 0, 0, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Hyena's", true, ItemPropertyEnum.MANA, -25, -11, "WJ", 4, 14, 100, 1000, -2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Frog's", true, ItemPropertyEnum.MANA, -10, -1, "WJ", 1, 0, 0, 0, -2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Spider's", true, ItemPropertyEnum.MANA, 10, 15, "WJ", 1, 5, 500, 1000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Raven's", true, ItemPropertyEnum.MANA, 16, 20, "WJ", 5, 5, 1100, 2000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Snake's", true, ItemPropertyEnum.MANA, 21, 30, "WJ", 9, 9, 2100, 4000, 5);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Serpent's", true, ItemPropertyEnum.MANA, 31, 40, "WJ", 15, 10, 4100, 6000, 7);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Drake's", true, ItemPropertyEnum.MANA, 41, 50, "WJ", 21, 9, 6100, 10000, 9);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Dragon's", true, ItemPropertyEnum.MANA, 51, 60, "WJ", 27, 9, 10100, 15000, 11);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Wyrm's", true, ItemPropertyEnum.MANA, 61, 80, "WJ", 35, 19, 15100, 19000, 12);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Hydra's", true, ItemPropertyEnum.MANA, 81, 100, "WJ", 60, 19, 19100, 30000, 13);

		// Armor Class
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Vulnerable", true, ItemPropertyEnum.ARMOR_CLASS, -100, -51, "AS", 3, 0, 0, 0, -3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Rusted", true, ItemPropertyEnum.ARMOR_CLASS, -50, -25, "AS", 1, 0, 0, 0, -2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Fine", true, ItemPropertyEnum.ARMOR_CLASS, 20, 30, "AS", 1, 10, 20, 100, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Strong", true, ItemPropertyEnum.ARMOR_CLASS, 31, 40, "AS", 3, 9, 120, 200, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Grand", true, ItemPropertyEnum.ARMOR_CLASS, 41, 55, "AS", 6, 14, 220, 300, 5);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Valiant", true, ItemPropertyEnum.ARMOR_CLASS, 56, 70, "AS", 10, 14, 320, 400, 7);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Glorious", true, ItemPropertyEnum.ARMOR_CLASS, 71, 90, "AS", 14, 19, 420, 600, 9);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Blessed", true, ItemPropertyEnum.ARMOR_CLASS, 91, 110, "AS", 19, 19, 620, 800, 11);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Saintly", true, ItemPropertyEnum.ARMOR_CLASS, 111, 130, "AS", 24, 19, 820, 1200, 13);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Awesome", true, ItemPropertyEnum.ARMOR_CLASS, 131, 150, "AS", 28, 19, 1220, 2000, 15);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Holy", true, ItemPropertyEnum.ARMOR_CLASS, 151, 170, "AS", 35, 19, 5200, 6000, 17);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Godly", true, ItemPropertyEnum.ARMOR_CLASS, 171, 200, "AS", 60, 29, 6200, 7000, 20);

		// To Hit
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Tin", true, ItemPropertyEnum.TO_HIT, -10, -6, "WJ", 3, 0, 0, 0, -3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Brass", true, ItemPropertyEnum.TO_HIT, -5, -1, "WJ", 1, 0, 0, 0, -2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Bronze", true, ItemPropertyEnum.TO_HIT, 1, 5, "WJ", 1, 4, 100, 500, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Iron", true, ItemPropertyEnum.TO_HIT, 6, 10, "WJ", 4, 4, 600, 1000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Steel", true, ItemPropertyEnum.TO_HIT, 11, 15, "WJ", 6, 4, 1100, 1500, 5);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Silver", true, ItemPropertyEnum.TO_HIT, 16, 20, "WJ", 9, 4, 1600, 2000, 7);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Gold", true, ItemPropertyEnum.TO_HIT, 21, 30, "WJ", 12, 9, 2100, 3000, 9);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Platinum", true, ItemPropertyEnum.TO_HIT, 31, 40, "W", 16, 9, 3100, 4000, 11);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Mithril", true, ItemPropertyEnum.TO_HIT, 41, 60, "W", 20, 19, 4100, 6000, 13);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Meteoric", true, ItemPropertyEnum.TO_HIT, 61, 80, "W", 23, 19, 6100, 10000, 15);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Weird", true, ItemPropertyEnum.TO_HIT, 81, 100, "W", 35, 19, 10100, 14000, 17);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Strange", true, ItemPropertyEnum.TO_HIT, 101, 150, "W", 60, 49, 14100, 20000, 20);

		// To Hit Damage Done
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Clumsy", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, -10, -6, -75, -50, "W", 5, 0, 0, 0, -7);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Dull", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, -5, -1, -45, -25, "W", 1, 0, 0, 0, -5);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Sharp", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, 1, 5, 20, 35, "W", 1, 15, 350, 950, 5);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Fine", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, 6, 10, 36, 50, "W", 6, 14, 1100, 1700, 7);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Warrior's", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, 11, 15, 51, 65, "W", 10, 14, 1850, 2450, 13);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Soldier's", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, 16, 20, 66, 80, "W", 15, 14, 2600, 3950, 17);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Lord's", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, 21, 30, 81, 95, "W", 19, 14, 4100, 5950, 21);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Knight's", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, 31, 40, 96, 110, "W", 23, 14, 6100, 8450, 26);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Master's", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, 41, 50, 111, 125, "W", 28, 14, 8600, 13000, 30);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Champion's", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, 51, 75, 126, 150, "W", 40, 24, 15200, 24000, 33);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "King's", true, ItemPropertyEnum.TO_HIT_DAMAGE_DONE, 76, 100, 151, 175, "W", 28, 24, 24100, 35000, 38);

		// Damage Done Percent
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Useless", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, -100, -100, "W", 5, 0, 0, 0, -8);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Bent", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, -75, -50, "W", 3, 0, 0, 0, -4);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Weak", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, -45, -25, "W", 1, 0, 0, 0, -3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Jagged", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, 20, 35, "W", 4, 15, 250, 450, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Deadly", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, 36, 50, "W", 6, 14, 500, 700, 4);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Heavy", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, 51, 65, "W", 9, 14, 750, 950, 5);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Vicious", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, 66, 80, "W", 12, 14, 1000, 1450, 8);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Brutal", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, 81, 95, "W", 16, 14, 1500, 1950, 10);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Massive", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, 96, 110, "W", 20, 14, 2000, 2450, 13);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Savage", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, 111, 125, "W", 23, 14, 2500, 3000, 15);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Ruthless", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, 126, 150, "W", 35, 24, 10100, 15000, 17);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Merciless", true, ItemPropertyEnum.DAMAGE_DONE_PERCENT, 151, 175, "W", 60, 24, 15000, 20000, 20);

		// Damage Done
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Quality", false, ItemPropertyEnum.DAMAGE_DONE, 1, 2, "W", 2, 1, 100, 200, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Maiming", false, ItemPropertyEnum.DAMAGE_DONE, 3, 5, "W", 7, 2, 1300, 1500, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Slaying", false, ItemPropertyEnum.DAMAGE_DONE, 6, 8, "W", 15, 2, 2600, 3000, 5);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Gore", false, ItemPropertyEnum.DAMAGE_DONE, 9, 12, "W", 25, 3, 4100, 5000, 8);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Carnage", false, ItemPropertyEnum.DAMAGE_DONE, 13, 16, "W", 35, 3, 5100, 10000, 10);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Slaughter", false, ItemPropertyEnum.DAMAGE_DONE, 17, 20, "W", 60, 3, 10100, 15000, 13);

		// Steal Life
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Leech", false, ItemPropertyEnum.STEAL_LIFE, 3, 3, "W", 8, 0, 7500, 7500, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Blood", false, ItemPropertyEnum.STEAL_LIFE, 5, 5, "W", 19, 0, 15000, 15000, 3);

		// Steal Mana
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "the Bat", false, ItemPropertyEnum.STEAL_MANA, 3, 3, "W", 8, 0, 7500, 7500, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Vampires", false, ItemPropertyEnum.STEAL_MANA, 5, 5, "W", 19, 0, 15000, 15000, 3);

		// Resist Magic
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "White", true, ItemPropertyEnum.RESIST_MAGIC, 10, 20, "ASWJ", 4, 10, 500, 1500, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Pearl", true, ItemPropertyEnum.RESIST_MAGIC, 21, 30, "ASWJ", 10, 9, 2100, 3000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Ivory", true, ItemPropertyEnum.RESIST_MAGIC, 31, 40, "ASWJ", 16, 9, 3100, 4000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Crystal", true, ItemPropertyEnum.RESIST_MAGIC, 41, 50, "ASWJ", 20, 9, 8000, 12000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Diamond", true, ItemPropertyEnum.RESIST_MAGIC, 51, 60, "ASWJ", 26, 9, 17100, 20000, 5);

		// Resist Fire
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Red", true, ItemPropertyEnum.RESIST_FIRE, 10, 20, "ASWJ", 4, 10, 500, 1500, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Crimson", true, ItemPropertyEnum.RESIST_FIRE, 21, 30, "ASWJ", 10, 9, 2100, 3000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Crimson", true, ItemPropertyEnum.RESIST_FIRE, 31, 40, "ASWJ", 16, 9, 3100, 4000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Garnet", true, ItemPropertyEnum.RESIST_FIRE, 41, 50, "ASWJ", 20, 9, 8000, 12000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Ruby", true, ItemPropertyEnum.RESIST_FIRE, 51, 60, "ASWJ", 26, 9, 17100, 20000, 5);

		// Resist Lightning
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Blue", true, ItemPropertyEnum.RESIST_LIGHTNING, 10, 20, "ASWJ", 4, 10, 500, 1500, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Azure", true, ItemPropertyEnum.RESIST_LIGHTNING, 21, 30, "ASWJ", 10, 9, 2100, 3000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Lapis", true, ItemPropertyEnum.RESIST_LIGHTNING, 31, 40, "ASWJ", 16, 9, 3100, 4000, 2);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Cobalt", true, ItemPropertyEnum.RESIST_LIGHTNING, 41, 50, "ASWJ", 20, 9, 8000, 12000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Sapphire", true, ItemPropertyEnum.RESIST_LIGHTNING, 51, 60, "ASWJ", 26, 9, 17100, 20000, 5);

		// Resist All
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Topaz", true, ItemPropertyEnum.RESIST_ALL, 10, 15, "ASWJ", 8, 5, 2000, 5000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Amber", true, ItemPropertyEnum.RESIST_ALL, 16, 20, "ASWJ", 12, 4, 7400, 10000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Jade", true, ItemPropertyEnum.RESIST_ALL, 21, 30, "ASWJ", 18, 9, 11000, 15000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Obsidian", true, ItemPropertyEnum.RESIST_ALL, 31, 41, "ASWJ", 24, 9, 24000, 40000, 4);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Emerald", true, ItemPropertyEnum.RESIST_ALL, 41, 50, "SW", 31, 9, 61000, 75000, 7);

		// Penetrate Armor
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Piercing", false, ItemPropertyEnum.PENETRATE_ARMOR, 25, 25, "W", 1, 0, 1000, 1000, 3);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Puncturing", false, ItemPropertyEnum.PENETRATE_ARMOR, 50, 50, "W", 9, 0, 2000, 2000, 6);
		itemProperty = new ItemPropertyBean(this.itemPropertyBeanList, "Bashing", false, ItemPropertyEnum.PENETRATE_ARMOR, 75, 75, "W", 17, 0, 4000, 4000, 12);

		// Base Items
		for(ArmorTypeEnum armorEnum : ArmorTypeEnum.values()){
			this.baseItemBeanList.put(armorEnum.toString(), new ArmorItemBean(armorEnum));
		}

		for(WeaponTypeEnum weaponEnum : WeaponTypeEnum.values()){
			this.baseItemBeanList.put(weaponEnum.toString(), new WeaponItemBean(weaponEnum));
		}

		// Special Items
		for(SpecialItemTypeEnum specialEnum : SpecialItemTypeEnum.values()){
			this.specialItemBeanList.add(new SpecialItemBean(specialEnum));
		}
	}

	public static ItemGenerator getInstance(){
		if(ItemGenerator.itemGenerator == null){
			ItemGenerator.itemGenerator = new ItemGenerator();
		}
		return ItemGenerator.itemGenerator;
	}

	public List<ItemBean> getLoot(LootSourceEnum lootSource, int itemLevel){
		List<ItemBean> lootItemList = new ArrayList<ItemBean>();
		switch(lootSource){
		case SMALL_CHEST:
			this.lootItemsFromChest(1, itemLevel, lootItemList, lootSource);
			break;
		case CHEST:
			this.lootItemsFromChest(2, itemLevel, lootItemList, lootSource);
			break;
		case LARGE_CHEST:
			this.lootItemsFromChest(3, itemLevel, lootItemList, lootSource);
			break;
		case BARREL:
			break;
		case DECAPITATED_BODY:
			break;
		case SARCOPHAGUS:
			break;
		case WEAPON_RACK:
			break;
		case ARMOR_RACK:
			break;
		case BOOKCASE:
			break;
		case MONSTER:
			this.lootItemsFromMonster(itemLevel, lootItemList);
			break;
		case UNIQUE_MONSTER:
			break;
		}
		return lootItemList;
	}

	private void lootItemsFromMonster(int monsterLevel, List<ItemBean> lootItemList){
		int randomNumber = this.randomGenerator.nextInt(1000);
		// Gold
		if(randomNumber < 303){
			lootItemList.add(this.generateGold(monsterLevel));
			// Item
		} else if ((randomNumber >= 303) && (randomNumber < 410)){
			lootItemList.add(this.generateItem(monsterLevel, LootSourceEnum.MONSTER));
		} else {
			// Nothing
		}
	}

	private ItemBean generateGold(int itemLevel){
		int goldRange = (15 * itemLevel) - (5 * itemLevel) - 1;
		return new ItemBean(this.randomGenerator.nextInt(goldRange) + (5 * itemLevel));
	}

	/*
	 * This method is used only for testing purposes.
	 */
	public EquipableItemBean generateTestItem(String property, EquipableItemEnum itemType){
		EquipableItemBean item = null;

		switch(itemType){
		case ARMOR:
		case HELM:
		case SHIELD:
			item = new ArmorItemBean(ArmorTypeEnum.CLOAK);
			break;
		case AXE:
		case BOW:
		case CLUB:
		case STAFF:
		case SWORD:
			item = new WeaponItemBean(WeaponTypeEnum.SHORT_SWORD);
			break;
		}

		item.setMagical(true);
		for(ItemPropertyBean p : this.itemPropertyBeanList){
			if(p.getName().equals(property)){
				item.setSuffix(p.copy(30, 0));
				break;
			}
		}

		return item;
	}

	private ItemBean generateItem(int itemLevel, LootSourceEnum lootSource){
		EquipableItemBean item = this.getEquipableItemFromMap(itemLevel);

		switch(item.getEquipableItemType()){
		case ARMOR:
		case HELM:
		case SHIELD:
			ArmorItemBean armorItem = (ArmorItemBean) item;
			item = armorItem.copy();
			break;
		case AXE:
		case BOW:
		case CLUB:
		case STAFF:
		case SWORD:
			WeaponItemBean weaponItem = (WeaponItemBean) item;
			item = weaponItem.copy();
			break;
		}

		if(this.isItemMagical(lootSource, itemLevel)){
			item.setMagical(true);
			this.assignPropertiesToItem(itemLevel, item);
		}

		return item;
	}

	public EquipableItemBean generateBaseItem(String equipableItemEnum){
		EquipableItemBean baseItem = this.baseItemBeanList.get(equipableItemEnum);
		switch(baseItem.getEquipableItemType()){
		case ARMOR:
		case HELM:
		case SHIELD:
			ArmorItemBean armorItem = (ArmorItemBean) baseItem;
			baseItem = armorItem.copy();
			break;
		case AXE:
		case STAFF:
		case SWORD:
		case BOW:
		case CLUB:
			baseItem = (WeaponItemBean) baseItem.copy(baseItem);
			break;
		}
		return baseItem;
	}

	private EquipableItemBean getEquipableItemFromMap(int itemLevel){
		List<EquipableItemBean> possibleItemsList = new ArrayList<EquipableItemBean>();
		for(EquipableItemBean item : this.baseItemBeanList.values()){
			if(item.getQualityLevel() <= itemLevel){
				possibleItemsList.add(item);
			}
		}

		EquipableItemBean item = possibleItemsList.get(this.randomGenerator.nextInt(possibleItemsList.size()) + 1);
		return item;
	}

	private void lootItemsFromChest(int possibleNumberOfItems, int dungeonLevel, List<ItemBean> lootItemList, LootSourceEnum lootSource){
		int numberOfItemsInChest = this.randomGenerator.nextInt(possibleNumberOfItems + 1);
		if(numberOfItemsInChest == 1){
			int itemProbability = this.randomGenerator.nextInt(1000);
			if(itemProbability < 125){
				SpecialItemBean item = (SpecialItemBean) this.getSpecialItemFromList(dungeonLevel);
				lootItemList.add(item.copy());
			} else {
				int goldProbability = this.randomGenerator.nextInt(100);
				if(goldProbability < 75){
					lootItemList.add(this.generateGold(dungeonLevel));
				} else {
					lootItemList.add(this.generateItem(dungeonLevel, lootSource));
				}
			}
		}
	}

	private boolean isItemMagical(LootSourceEnum lootSource, int dungeonLevel){
		boolean isMagical = false;
		switch(lootSource){
		case UNIQUE_MONSTER:
		case WEAPON_RACK:
			isMagical = true;
			break;
		case ARMOR_RACK:
			if((dungeonLevel == 5) || ((dungeonLevel <= 15) && (dungeonLevel >= 13))){
				isMagical = true;
			} else if ((dungeonLevel >= 6) && (dungeonLevel <= 9)){
				int isMagicProbabilityNumber = (int) ((55.5 + (0.445 * ((2 * dungeonLevel) + 1))) * 1000);
				int randomNumber = this.randomGenerator.nextInt(100000);
				if(randomNumber < isMagicProbabilityNumber){
					isMagical = true;
				}
			} else {
				int isMagicProbabilityNumber = (int) ((11 + (0.89 * ((2 * dungeonLevel) + 1))) * 1000);
				int randomNumber = this.randomGenerator.nextInt(100000);
				if(randomNumber < isMagicProbabilityNumber){
					isMagical = true;
				}
			}
			break;
		case MONSTER:
		case CHEST:
		case LARGE_CHEST:
		case SMALL_CHEST:
		case SARCOPHAGUS:
		case DECAPITATED_BODY:
		case BARREL:
			int isMagicProbabilityNumber = (int) ((11 + (0.89 * ((2 * dungeonLevel) + 1))) * 1000);
			int randomNumber = this.randomGenerator.nextInt(100000);
			if(randomNumber < isMagicProbabilityNumber){
				isMagical = true;
			}
			break;
		}
		return isMagical;
	}

	private void assignPropertiesToItem(int itemLevel, EquipableItemBean itemBean){

		int generatedActualValue = 0;
		int generatedActualValue2 = 0;

		int minItemLevel = (itemLevel / 2);
		if(minItemLevel > 25){
			minItemLevel = 25;
		}

		List<ItemPropertyBean> possiblePropertyList = new ArrayList<ItemPropertyBean>();
		int randomNumber = this.randomGenerator.nextInt(1000);
		// Prefix Only
		if(randomNumber <208){
			for(ItemPropertyBean property : this.itemPropertyBeanList){
				if((property.getQualityLevel() >= minItemLevel) && (property.getQualityLevel() <= itemLevel) && property.isPrefix()){
					possiblePropertyList.add(property);
				}
			}

			ItemPropertyBean itemProperty = possiblePropertyList.get(this.randomGenerator.nextInt(possiblePropertyList.size()));
			generatedActualValue = this.randomGenerator.nextInt(itemProperty.getMaxValue() - itemProperty.getMinValue()) + itemProperty.getMinValue() + 1;
			generatedActualValue2 = 0;
			if(itemProperty.getMinValue2() != 0){
				generatedActualValue2 = this.randomGenerator.nextInt(itemProperty.getMaxValue2() - itemProperty.getMinValue2()) + itemProperty.getMinValue2() + 1;
			}
			itemBean.setPrefix(itemProperty.copy(generatedActualValue, generatedActualValue2));

			// Suffix Only
		} else if((randomNumber >= 208) && (randomNumber < 833)){

			for(ItemPropertyBean property : this.itemPropertyBeanList){
				if((property.getQualityLevel() >= minItemLevel) && (property.getQualityLevel() <= itemLevel) && !property.isPrefix()){
					possiblePropertyList.add(property);
				}
			}

			ItemPropertyBean itemProperty = possiblePropertyList.get(this.randomGenerator.nextInt(possiblePropertyList.size()));
			generatedActualValue = this.randomGenerator.nextInt(itemProperty.getMaxValue() - itemProperty.getMinValue()) + itemProperty.getMinValue() + 1;
			generatedActualValue2 = 0;
			if(itemProperty.getMinValue2() != 0){
				generatedActualValue2 = this.randomGenerator.nextInt(itemProperty.getMaxValue2() - itemProperty.getMinValue2()) + itemProperty.getMinValue2() + 1;
			}
			itemBean.setSuffix(itemProperty.copy(generatedActualValue, generatedActualValue2));

			// Prefix and Suffix
		} else {

			for(ItemPropertyBean property : this.itemPropertyBeanList){
				if((property.getQualityLevel() >= minItemLevel) && (property.getQualityLevel() <= itemLevel) && property.isPrefix()){
					possiblePropertyList.add(property);
				}
			}

			ItemPropertyBean itemProperty = possiblePropertyList.get(this.randomGenerator.nextInt(possiblePropertyList.size()));
			generatedActualValue = this.randomGenerator.nextInt(itemProperty.getMaxValue() - itemProperty.getMinValue()) + itemProperty.getMinValue() + 1;
			generatedActualValue2 = 0;
			if(itemProperty.getMinValue2() != 0){
				generatedActualValue2 = this.randomGenerator.nextInt(itemProperty.getMaxValue2() - itemProperty.getMinValue2()) + itemProperty.getMinValue2() + 1;
			}
			itemBean.setPrefix(itemProperty.copy(generatedActualValue, generatedActualValue2));

			possiblePropertyList.clear();
			for(ItemPropertyBean property : this.itemPropertyBeanList){
				if((property.getQualityLevel() >= minItemLevel) && (property.getQualityLevel() <= itemLevel) && !property.isPrefix()){
					possiblePropertyList.add(property);
				}
			}

			itemProperty = possiblePropertyList.get(this.randomGenerator.nextInt(possiblePropertyList.size()));
			generatedActualValue = this.randomGenerator.nextInt(itemProperty.getMaxValue() - itemProperty.getMinValue()) + itemProperty.getMinValue() + 1;
			generatedActualValue2 = 0;
			if(itemProperty.getMinValue2() != 0){
				generatedActualValue2 = this.randomGenerator.nextInt(itemProperty.getMaxValue2() - itemProperty.getMinValue2()) + itemProperty.getMinValue2() + 1;
			}

			itemBean.setSuffix(itemProperty.copy(generatedActualValue, generatedActualValue2));
		}
	}

	private ItemBean getSpecialItemFromList(int itemLevel){
		List<ItemBean> possibleItemsList = new ArrayList<ItemBean>();
		for(ItemBean item : this.specialItemBeanList){
			if(item.getQualityLevel() <= itemLevel){
				possibleItemsList.add(item);
			}
		}

		ItemBean item = possibleItemsList.get(this.randomGenerator.nextInt(possibleItemsList.size()) + 1);

		return item;
	}
}
