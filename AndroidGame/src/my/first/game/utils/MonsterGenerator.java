package my.first.game.utils;

import java.util.ArrayList;
import java.util.Random;

import my.first.game.beans.MonsterBean;
import my.first.game.enums.MagicResistenceEnum;
import my.first.game.enums.MonsterEnum;
import my.first.game.enums.MonsterTypeEnum;

public class MonsterGenerator {

	private static MonsterGenerator monsterGenerator = null;
	private Random randomMonsterPicker = GenericRandomGenerator.getInstance();

	private MonsterBean[] monsterList = {
			new MonsterBean(MonsterEnum.ZOMBIE, MonsterTypeEnum.UNDEAD, 1, 2, 1, 2, 3, 5, 10, 2, 5, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 54, 2000, 799),
			new MonsterBean(MonsterEnum.GHOUL, MonsterTypeEnum.UNDEAD, 2, 3, 2, 3, 5, 10, 10, 3, 10, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 58, 1700, 799),
			new MonsterBean(MonsterEnum.ROTTING_CARCASS, MonsterTypeEnum.UNDEAD, 2, 4, 4, 7, 12, 15, 25, 5, 15, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 136, 1200, 799),
			new MonsterBean(MonsterEnum.BLACK_DEATH, MonsterTypeEnum.UNDEAD, 3, 5, 6, 12, 20, 20, 30, 6, 22, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 240, 900, 799),

			new MonsterBean(MonsterEnum.FALLEN_ONE, MonsterTypeEnum.ANIMAL, 1, 3, 1, 1, 2, 0, 15, 1, 3, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 46, 900, 799),
			new MonsterBean(MonsterEnum.CARVER, MonsterTypeEnum.ANIMAL, 2, 3, 3, 2, 4, 5, 20, 2, 5, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 80, 900, 799),
			new MonsterBean(MonsterEnum.DEVIL_KIN, MonsterTypeEnum.ANIMAL, 2, 4, 5, 6, 12, 10, 25, 3, 7, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, 155, 900, 799),
			new MonsterBean(MonsterEnum.DARK_ONE, MonsterTypeEnum.ANIMAL, 3, 5, 7, 10, 18, 15, 30, 4, 8, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 255, 900, 799),

			new MonsterBean(MonsterEnum.SKELETON, MonsterTypeEnum.UNDEAD, 1, 2, 1, 1, 2, 0, 20, 1, 4, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 64, 900, 799),
			new MonsterBean(MonsterEnum.CORPSE_AXE, MonsterTypeEnum.UNDEAD, 2, 3, 2, 2, 3, 0, 25, 3, 5, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 68, 900, 799),
			new MonsterBean(MonsterEnum.BURNING_DEAD, MonsterTypeEnum.UNDEAD, 2, 4, 4, 4, 6, 5, 30, 3, 7, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.IMMUNITY, 154, 900, 799),
			new MonsterBean(MonsterEnum.HORROR, MonsterTypeEnum.UNDEAD, 3, 5, 6, 6, 10, 15, 35, 4, 9, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 264, 900, 799),

			new MonsterBean(MonsterEnum.SKELETON_CAPTAIN, MonsterTypeEnum.UNDEAD, 1, 3, 2, 1, 3, 10, 20, 2, 7, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 90, 900, 799),
			new MonsterBean(MonsterEnum.CORPSE_CAPTAIN, MonsterTypeEnum.UNDEAD, 2, 4, 4, 6, 10, 5, 30, 3, 9, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 200, 900, 799),
			new MonsterBean(MonsterEnum.BURNING_DEAD_CAPTAIN, MonsterTypeEnum.UNDEAD, 3, 5, 6, 8, 15, 15, 35, 4, 10, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.IMMUNITY, 393, 900, 799),
			new MonsterBean(MonsterEnum.HOROR_CAPTAIN, MonsterTypeEnum.UNDEAD, 4, 6, 8, 17, 25, 30, 40, 5, 14, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 604, 900, 799),

			new MonsterBean(MonsterEnum.SCAVANGER, MonsterTypeEnum.ANIMAL, 1, 3, 2, 1, 3, 10, 20, 1, 5, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 80, 900, 799),
			new MonsterBean(MonsterEnum.PLAGUE_EATER, MonsterTypeEnum.ANIMAL, 2, 4, 4, 6, 12, 20, 30, 1, 18, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 188, 900, 799),
			new MonsterBean(MonsterEnum.SHADOW_BEAST, MonsterTypeEnum.ANIMAL, 3, 5, 6, 12, 18, 25, 35, 3, 12, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 375, 900, 799),
			new MonsterBean(MonsterEnum.BONE_GASHER, MonsterTypeEnum.ANIMAL, 4, 6, 8, 14, 20, 30, 35, 5, 15, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 552, 900, 799),

			new MonsterBean(MonsterEnum.FIEND, MonsterTypeEnum.ANIMAL, 2, 3, 3, 1, 3, 0, 35, 1, 6, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 102, 900, 799),
			new MonsterBean(MonsterEnum.BLINK, MonsterTypeEnum.ANIMAL, 3, 5, 7, 6, 14, 15, 45, 1, 8, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 340, 900, 799),
			new MonsterBean(MonsterEnum.GLOOM, MonsterTypeEnum.ANIMAL, 4, 6, 9, 14, 18, 35, 70, 4, 12, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 509, 900, 799),
			new MonsterBean(MonsterEnum.FAMILIAR, MonsterTypeEnum.DEMON, 6, 8, 13, 10, 17, 35, 50, 4, 16, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 448, 900, 799),

			new MonsterBean(MonsterEnum.HIDDEN, MonsterTypeEnum.DEMON, 2, 5, 5, 4, 12, 25, 35, 3, 6, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 278, 900, 799),
			new MonsterBean(MonsterEnum.STALKER, MonsterTypeEnum.DEMON, 5, 7, 9, 15, 22, 30, 40, 8, 16, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 630, 900, 799),
			new MonsterBean(MonsterEnum.UNSEEN, MonsterTypeEnum.DEMON, 6, 8, 11, 17, 25, 30, 45, 12, 20, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 935, 900, 799),
			new MonsterBean(MonsterEnum.ILLUSION_WEAVER, MonsterTypeEnum.DEMON, 8, 10, 13, 20, 30, 30, 60, 16, 25, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.RESISTENCE, 1500, 900, 799),

			new MonsterBean(MonsterEnum.FLESH_CLAN, MonsterTypeEnum.DEMON, 4, 6, 8, 15, 22, 40, 50, 4, 10, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 460, 900, 799),
			new MonsterBean(MonsterEnum.STONE_CLAN, MonsterTypeEnum.DEMON, 5, 7, 10, 20, 27, 40, 60, 6, 12, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 685, 900, 799),
			new MonsterBean(MonsterEnum.FIRE_CLAN, MonsterTypeEnum.DEMON, 6, 8, 12, 25, 32, 45, 70, 8, 16, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, 906, 900, 799),
			new MonsterBean(MonsterEnum.NIGHT_CLAN, MonsterTypeEnum.DEMON, 7, 9, 14, 27, 35, 50, 80, 10, 20, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 1190, 900, 799),

			new MonsterBean(MonsterEnum.OVERLORD, MonsterTypeEnum.DEMON, 5, 7, 10, 30, 40, 55, 55, 6, 12, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 635, 900, 799),
			new MonsterBean(MonsterEnum.MUD_MAN, MonsterTypeEnum.DEMON, 7, 9, 14, 50, 62, 60, 60, 8, 16, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 1165, 900, 799),
			new MonsterBean(MonsterEnum.TOAD_DEMON, MonsterTypeEnum.DEMON, 8, 10, 16, 67, 80, 65, 70, 8, 16, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 1380, 900, 799),
			new MonsterBean(MonsterEnum.FLAYED_ONE, MonsterTypeEnum.DEMON, 10, 12, 20, 80, 100, 70, 85, 10, 20, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, 2058, 900, 799),

			new MonsterBean(MonsterEnum.WINGED_DEMON, MonsterTypeEnum.DEMON, 5, 7, 9, 22, 30, 45, 50, 10, 16, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.IMMUNITY, 662, 900, 799),
			new MonsterBean(MonsterEnum.GARGOYLE, MonsterTypeEnum.DEMON, 7, 9, 13, 30, 45, 45, 65, 10, 16, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 1205, 900, 799),
			new MonsterBean(MonsterEnum.BLOOD_CLAW, MonsterTypeEnum.DEMON, 9, 11, 19, 37, 62, 50, 80, 14, 22, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.IMMUNITY, 1873, 900, 799),
			new MonsterBean(MonsterEnum.DEATH_WING, MonsterTypeEnum.DEMON, 10, 12, 23, 45, 75, 60, 95, 16, 28, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 2278, 900, 799),

			new MonsterBean(MonsterEnum.MAGMA_DEMON, MonsterTypeEnum.DEMON, 8, 9, 13, 25, 35, 45, 45, 2, 10, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.IMMUNITY, 1076, 900, 799),
			new MonsterBean(MonsterEnum.BLOOD_STONE, MonsterTypeEnum.DEMON, 8, 10, 14, 28, 37, 45, 50, 2, 12, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.IMMUNITY, 1309, 900, 799),
			new MonsterBean(MonsterEnum.HELL_STONE, MonsterTypeEnum.DEMON, 9, 11, 16, 30, 40, 50, 60, 2, 20, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.IMMUNITY, 1680, 900, 799),
			new MonsterBean(MonsterEnum.LAVA_LORD, MonsterTypeEnum.DEMON, 9, 11, 18, 35, 42, 60, 75, 4, 24, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.IMMUNITY, 2124, 900, 799),

			new MonsterBean(MonsterEnum.HORNED_DEMON, MonsterTypeEnum.ANIMAL, 7, 9, 13, 20, 40, 40, 60, 2, 16, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 1172, 900, 799),
			new MonsterBean(MonsterEnum.MUD_RUNNER, MonsterTypeEnum.ANIMAL, 8, 10, 15, 25, 45, 45, 70, 6, 18, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 1404, 900, 799),
			new MonsterBean(MonsterEnum.FROST_CHARGER, MonsterTypeEnum.ANIMAL, 9, 11, 17, 30, 50, 50, 80, 8, 20, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 1720, 900, 799),
			new MonsterBean(MonsterEnum.OBSIDIAN_LORD, MonsterTypeEnum.ANIMAL, 10, 12, 19, 35, 55, 55, 90, 10, 22, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 1809, 900, 799),

			new MonsterBean(MonsterEnum.ACID_BEAST, MonsterTypeEnum.ANIMAL, 6, 8, 11, 20, 33, 30, 40, 4, 12, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 846, 900, 799),
			new MonsterBean(MonsterEnum.POISON_SPITTER, MonsterTypeEnum.ANIMAL, 8, 10, 15, 30, 42, 30, 45, 4, 16, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 1248, 900, 799),
			new MonsterBean(MonsterEnum.PIT_BEAST, MonsterTypeEnum.ANIMAL, 10, 12, 21, 40, 55, 35, 55, 8, 18, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 2060, 900, 799),
			new MonsterBean(MonsterEnum.LAVA_MAW, MonsterTypeEnum.ANIMAL, 12, 14, 25, 50, 75, 35, 65, 10, 20, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, 2940, 900, 799),

			new MonsterBean(MonsterEnum.RED_STORM, MonsterTypeEnum.DEMON, 9, 11, 18, 27, 55, 30, 80, 8, 18, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 2160, 900, 799),
			new MonsterBean(MonsterEnum.STORM_RIDER, MonsterTypeEnum.DEMON, 10, 12, 20, 30, 60, 30, 80, 8, 18, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 2391, 900, 799),
			new MonsterBean(MonsterEnum.STORM_LORD, MonsterTypeEnum.DEMON, 11, 13, 22, 37, 67, 35, 85, 12, 24, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 2775, 900, 799),
			new MonsterBean(MonsterEnum.MAELSTORM, MonsterTypeEnum.DEMON, 12, 14, 24, 45, 75, 40, 90, 12, 28, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 3177, 900, 799),

			new MonsterBean(MonsterEnum.SLAYER, MonsterTypeEnum.DEMON, 10, 12, 20, 60, 70, 60, 100, 12, 20, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, 2300, 900, 799),
			new MonsterBean(MonsterEnum.GUARDIAN, MonsterTypeEnum.DEMON, 11, 13, 22, 70, 80, 65, 110, 14, 22, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, 2714, 900, 799),
			new MonsterBean(MonsterEnum.VORTEX_LORD, MonsterTypeEnum.DEMON, 12, 14, 24, 80, 90, 70, 120, 18, 24, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, 3252, 900, 799),
			new MonsterBean(MonsterEnum.BALROG, MonsterTypeEnum.DEMON, 13, 15, 26, 90, 100, 75, 130, 22, 30, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, 3643, 900, 799),

			new MonsterBean(MonsterEnum.CAVE_VIPER, MonsterTypeEnum.DEMON, 11, 13, 21, 50, 75, 60, 90, 8, 20, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 2725, 900, 799),
			new MonsterBean(MonsterEnum.FIRE_DRAKE, MonsterTypeEnum.DEMON, 12, 14, 23, 60, 85, 65, 105, 12, 24, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.IMMUNITY, 3139, 900, 799),
			new MonsterBean(MonsterEnum.GOLD_VIPER, MonsterTypeEnum.DEMON, 13, 14, 25, 70, 80, 70, 120, 15, 26, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, MagicResistenceEnum.IMMUNITY, 3484, 900, 799),
			new MonsterBean(MonsterEnum.AZURE_DRAKE, MonsterTypeEnum.DEMON, 15, 15, 27, 80, 100, 75, 130, 18, 30, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, 3791, 900, 799),

			new MonsterBean(MonsterEnum.SUCCUBUS, MonsterTypeEnum.DEMON, 12, 14, 24, 60, 75, 60, 100, 1, 20, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 3696, 900, 799),
			new MonsterBean(MonsterEnum.SNOW_WITCH, MonsterTypeEnum.DEMON, 13, 15, 26, 67, 87, 65, 110, 1, 24, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, MagicResistenceEnum.NONE, 4084, 900, 799),
			new MonsterBean(MonsterEnum.HELL_SPAWN, MonsterTypeEnum.DEMON, 14, 15, 28, 75, 100, 75, 115, 1, 30, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 4480, 900, 799),
			new MonsterBean(MonsterEnum.SOUL_BURNER, MonsterTypeEnum.DEMON, 15, 15, 30, 70, 112, 85, 120, 1, 35, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, 4644, 900, 799),

			new MonsterBean(MonsterEnum.BLACK_KNIGHT, MonsterTypeEnum.DEMON, 12, 14, 24, 75, 75, 75, 110, 15, 20, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, 3360, 900, 799),
			new MonsterBean(MonsterEnum.DOOM_GUARD, MonsterTypeEnum.DEMON, 13, 15, 26, 82, 82, 75, 130, 18, 25, MagicResistenceEnum.NONE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.RESISTENCE, 3650, 900, 799),
			new MonsterBean(MonsterEnum.STEEL_LORD, MonsterTypeEnum.DEMON, 14, 15, 28, 90, 90, 80, 120, 20, 30, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, 4252, 900, 799),
			new MonsterBean(MonsterEnum.BLOOD_KNIGHT, MonsterTypeEnum.DEMON, 13, 16, 30, 100, 100, 85, 130, 25, 35, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.IMMUNITY, 5130, 900, 799),

			new MonsterBean(MonsterEnum.COUNSELOR, MonsterTypeEnum.DEMON, 13, 14, 25, 35, 35, 0, 90, 8, 20, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.RESISTENCE, 3876, 900, 799),
			new MonsterBean(MonsterEnum.MAGISTRATE, MonsterTypeEnum.DEMON, 14, 15, 27, 42, 42, 0, 100, 10, 24, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, 4478, 900, 799),
			new MonsterBean(MonsterEnum.CABALIST, MonsterTypeEnum.DEMON, 15, 15, 29, 60, 60, 0, 110, 14, 30, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.RESISTENCE, 4929, 900, 799),
			new MonsterBean(MonsterEnum.ADVOCATE, MonsterTypeEnum.DEMON, 15, 16, 30, 72, 72, 0, 120, 15, 25, MagicResistenceEnum.IMMUNITY, MagicResistenceEnum.RESISTENCE, MagicResistenceEnum.IMMUNITY, 4968, 900, 799)
	};

	private ArrayList<MonsterBean> selectedMonstersList = new ArrayList<MonsterBean>();
	private ArrayList<MonsterBean> possibleMonstersList = new ArrayList<MonsterBean>();

	private MonsterGenerator(){	}

	public static MonsterGenerator getInstance(){
		if(MonsterGenerator.monsterGenerator == null){
			MonsterGenerator.monsterGenerator = new MonsterGenerator();
		}
		return MonsterGenerator.monsterGenerator;
	}

	public MonsterBean getMonster(MonsterEnum monster){
		return this.monsterList[monster.ordinal()].copyMonster();
	}

	public MonsterBean generateMonster(int dungeonLvl){
		this.possibleMonstersList.clear();
		this.selectedMonstersList.clear();

		int totalMonsterSize = 4000;
		totalMonsterSize -= 386; // Subtracting Golem size

		for(MonsterBean monster : this.monsterList){
			if((monster.getMinDungLvl() <= dungeonLvl)
					&& (monster.getMaxDungLvl() > dungeonLvl)){
				this.possibleMonstersList.add(monster);
			}
		}

		while(!this.possibleMonstersList.isEmpty()){
			int randomSelectMonster = this.randomMonsterPicker.nextInt(this.possibleMonstersList.size());
			MonsterBean monster = this.possibleMonstersList.get(randomSelectMonster);
			if((totalMonsterSize - monster.getMonsterSize()) >= 0){
				totalMonsterSize -= monster.getMonsterSize();
				this.selectedMonstersList.add(monster);
			}
			this.possibleMonstersList.remove(randomSelectMonster);
		}

		// From possible monsters return a copy of one.
		int randomSelectMonster = this.randomMonsterPicker.nextInt(this.selectedMonstersList.size());
		return this.selectedMonstersList.get(randomSelectMonster).copyMonster();
	}
}
