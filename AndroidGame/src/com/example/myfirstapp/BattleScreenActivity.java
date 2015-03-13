package com.example.myfirstapp;

import java.util.HashMap;

import my.first.game.adapters.CharacterStatusArrayAdapter;
import my.first.game.beans.AbilityBean;
import my.first.game.beans.AbstractSheetBean;
import my.first.game.controller.BattleController;
import my.first.game.enums.AbilityEnum;
import my.first.game.enums.BattleControllerEventEnum;
import my.first.game.interfaces.IBattleScreen;
import my.first.game.utils.ViewHandler;
import my.first.game.views.ActionBarView;
import my.first.game.views.CharacterStatusView;
import my.first.game.views.CombatLogView;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

public class BattleScreenActivity extends Activity implements IBattleScreen{

	private BattleController battleController = BattleController.getInstance();

	private CharacterStatusArrayAdapter monsterStatusArrayAdapter;
	private CharacterStatusView mainCharacterStatusView;
	private ListView monsterStatusListView;
	private ActionBarView actionBarView;

	private ViewHandler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_battle_screen);

		this.actionBarView = (ActionBarView) this.findViewById(R.id.action_bar_view);
		HashMap<AbilityEnum, AbilityBean> characterAbilities = this.battleController.getMainCharacter().getCharacterAbilities();
		int position = 0;
		for(AbilityBean ability : characterAbilities.values()){
			this.actionBarView.setActionBarButton(position, ability.getAbilityName());
			position++;
		}

		this.mHandler = new ViewHandler(){

			@Override
			public void handleMessage(Message inputMessage){
				BattleScreenActivity activity = (BattleScreenActivity) this.getActivity();
				activity.refreshBattleData();

				switch (inputMessage.what) {
				// Battle Log Message
				case 0:
					CombatLogView combatLogTextView = (CombatLogView) activity.findViewById(R.id.combat_log_view);
					combatLogTextView.logEvent((String) inputMessage.obj);
					break;
					// Monster Died
				case 1:
					activity.enableBackToLocationButton();
					break;
				default:
					break;
				}
			}
		};

		this.mHandler.setActivity(this);

		this.battleController.setBattleScreen(this);
		this.battleController.startBattle();

		this.monsterStatusArrayAdapter = new CharacterStatusArrayAdapter(this, R.layout.battle_status_view, this.battleController.getMonsterList());
		this.monsterStatusListView = (ListView) this.findViewById(R.id.monster_info_list);
		this.monsterStatusListView.setAdapter(this.monsterStatusArrayAdapter);
		this.monsterStatusListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);


		this.mainCharacterStatusView = (CharacterStatusView) this.findViewById(R.id.player_info);
		this.mainCharacterStatusView.setCharacterSheet(this.battleController.getMainCharacter());

		this.refreshBattleData();
	}

	private void refreshBattleData(){

		this.mainCharacterStatusView.updateData();
		this.monsterStatusArrayAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.getMenuInflater().inflate(R.menu.battle_screen, menu);
		return true;
	}

	@Deprecated
	public void battleEvent(String message){

		this.refreshBattleData();
		//Combat log
		CombatLogView combatLogTextView = (CombatLogView) this.findViewById(R.id.combat_log_view);
		combatLogTextView.logEvent(message);
	}

	public void performAbility(View view){
		Button abilityButton = (Button) view;

		int checkedItemPosition = this.monsterStatusListView.getCheckedItemPosition();
		if(checkedItemPosition == -1){
			checkedItemPosition = 0;
		}

		this.battleController.performPlayerAbility(abilityButton.getText().toString(), (AbstractSheetBean) this.monsterStatusListView.getItemAtPosition(checkedItemPosition));
	}

	public void attackMonster(View view){

		int checkedItemPosition = this.monsterStatusListView.getCheckedItemPosition();
		if(checkedItemPosition == -1){
			checkedItemPosition = 0;
		}
		boolean allMonstersKilled =	this.battleController.performPlayerAttack((AbstractSheetBean) this.monsterStatusListView.getItemAtPosition(checkedItemPosition));

		if(allMonstersKilled){
			Button attackMonsterButton = (Button) this.findViewById(R.id.button_action_bar_7);
			attackMonsterButton.setText("Return to Character Screen");
			attackMonsterButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					NavUtils.navigateUpFromSameTask(BattleScreenActivity.this);
				}
			});
		}
	}

	private void enableBackToLocationButton(){

		boolean allMonstersKilled =	this.battleController.isAllMonstersDead();

		if(allMonstersKilled){
			Button attackMonsterButton = (Button) this.findViewById(R.id.button_action_bar_7);
			attackMonsterButton.setText("Return to Character Screen");
			attackMonsterButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					NavUtils.navigateUpFromSameTask(BattleScreenActivity.this);
				}
			});
		}
	}

	@Override
	public void logBattleEvent(String message) {

		Message completeMessage = this.mHandler.obtainMessage(0, message);
		completeMessage.sendToTarget();
	}

	@Override
	public void processBattleControllerEvent(BattleControllerEventEnum battleControllerEvent) {
		switch(battleControllerEvent){
		case MONSTER_DIED:
			Message completeMessage = this.mHandler.obtainMessage(1);
			completeMessage.sendToTarget();
			break;
		default:
			break;
		}

	}
}
