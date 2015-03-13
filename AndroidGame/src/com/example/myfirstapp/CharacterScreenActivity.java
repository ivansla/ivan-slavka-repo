package com.example.myfirstapp;

import my.first.game.beans.CharacterScreenBean;
import my.first.game.controller.MainController;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class CharacterScreenActivity extends Activity {

	private MainController controller = MainController.getInstance();

	private CharacterScreenBean mainCharacter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_character_screen);

		this.mainCharacter = this.controller.getCharacterScreenBean();

		TextView characterClassTextView = (TextView) this.findViewById(R.id.characterClassValue);
		characterClassTextView.setText(this.mainCharacter.getCharacterClass());
		TextView characterNameTextView = (TextView) this.findViewById(R.id.character_name_value);
		characterNameTextView.setText(this.mainCharacter.getCharacterName());
		TextView characterLevelTextView = (TextView) this.findViewById(R.id.character_level_value);
		characterLevelTextView.setText(String.valueOf(this.mainCharacter.getLevel()));
		TextView experienceGainedTextView = (TextView) this.findViewById(R.id.experience_gained_value);
		experienceGainedTextView.setText(String.valueOf(this.mainCharacter.getExperienceGained()));
		TextView experienceNeededTextView = (TextView) this.findViewById(R.id.experience_needed_value);
		experienceNeededTextView.setText(String.valueOf(this.mainCharacter.getExperienceNeeded()));
		TextView strengthTextView = (TextView) this.findViewById(R.id.strength_value);
		strengthTextView.setText(String.valueOf(this.mainCharacter.getStrength()));
		TextView dexterityTextView = (TextView) this.findViewById(R.id.dexterity_value);
		dexterityTextView.setText(String.valueOf(this.mainCharacter.getDexterity()));
		TextView vitalityTextView = (TextView) this.findViewById(R.id.vitality_value);
		vitalityTextView.setText(String.valueOf(this.mainCharacter.getVitality()));
		TextView magicTextView = (TextView) this.findViewById(R.id.magic_value);
		magicTextView.setText(String.valueOf(this.mainCharacter.getMagic()));
		TextView minDamageTextView = (TextView) this.findViewById(R.id.min_damage_value);
		minDamageTextView.setText(String.valueOf(this.mainCharacter.getMinDamage()));
		TextView maxDamageTextView = (TextView) this.findViewById(R.id.max_damage_value);
		maxDamageTextView.setText(String.valueOf(this.mainCharacter.getMaxDamage()));
		TextView armorTextView = (TextView) this.findViewById(R.id.armor_value);
		armorTextView.setText(String.valueOf(this.mainCharacter.getArmorClass()));
		TextView healthCurrentTextView = (TextView) this.findViewById(R.id.health_current_value);
		healthCurrentTextView.setText(String.valueOf(this.mainCharacter.getCurrentHitPoints()));
		TextView healthTotalTextView = (TextView) this.findViewById(R.id.health_total_value);
		healthTotalTextView.setText(String.valueOf(this.mainCharacter.getTotalHitPoints()));
		TextView manaCurrentTextView = (TextView) this.findViewById(R.id.mana_current_value);
		manaCurrentTextView.setText(String.valueOf(this.mainCharacter.getCurrentMana()));
		TextView manaTotalTextView = (TextView) this.findViewById(R.id.mana_total_value);
		manaTotalTextView.setText(String.valueOf(this.mainCharacter.getTotalMana()));
		TextView chanceToHitTextView = (TextView) this.findViewById(R.id.chance_to_hit_value);
		chanceToHitTextView.setText(String.valueOf(this.mainCharacter.getChanceToHit()));

		/*
		Intent intent = this.getIntent();
		CharacterClassEnum characterType = (CharacterClassEnum) intent.getSerializableExtra(MainActivity.CHARACTER_CLASS);

		//characterClassTextView.setTextSize(10);


		String characterName = intent.getStringExtra(MainActivity.CHARACTER_NAME);
		TextView characterNameTextView = (TextView) this.findViewById(R.id.character_name_value);
		//characterNameTextView.setTextSize(10);
		characterNameTextView.setText(characterName);
		 */

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.character_screen, menu);
		return true;
	}

	public void startBattle(View view){
		Intent intent = new Intent(this, BattleScreenActivity.class);
		this.startActivity(intent);
	}
}
