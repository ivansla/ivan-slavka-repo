package my.first.game.views.inventory;

import my.first.game.beans.CharacterScreenBean;
import my.first.game.beans.EquipableItemBean;
import my.first.game.controller.MainController;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class CharacterStatsView extends RelativeLayout{

	private MainController mainController = MainController.getInstance();

	public CharacterStatsView(Context context, AttributeSet attrs){
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.character_stats_view, this);

	}

	public void showCharacterStatsData(EquipableItemBean equipableItem){

		CharacterScreenBean tempCharacterScreen = this.mainController.previewToggleEquipItem(equipableItem);

		TextView characterStrengthTextView = (TextView) this.findViewById(R.id.strength_value);
		this.assignValueToTextView(characterStrengthTextView, this.mainController.getCharacterScreenBean().getStrength(), tempCharacterScreen.getStrength());
		TextView characterDexterityTextView = (TextView) this.findViewById(R.id.dexterity_value);
		this.assignValueToTextView(characterDexterityTextView, this.mainController.getCharacterScreenBean().getDexterity(), tempCharacterScreen.getDexterity());
		TextView characterVitalityTextView = (TextView) this.findViewById(R.id.vitality_value);
		this.assignValueToTextView(characterVitalityTextView, this.mainController.getCharacterScreenBean().getVitality(), tempCharacterScreen.getVitality());
		TextView characterMagicTextView = (TextView) this.findViewById(R.id.magic_value);
		this.assignValueToTextView(characterMagicTextView, this.mainController.getCharacterScreenBean().getMagic(), tempCharacterScreen.getMagic());
		TextView characterMinDamageTextView = (TextView) this.findViewById(R.id.min_damage_value);
		this.assignValueToTextView(characterMinDamageTextView, this.mainController.getCharacterScreenBean().getMinDamage(), tempCharacterScreen.getMinDamage());
		TextView characterMaxDamageTextView = (TextView) this.findViewById(R.id.max_damage_value);
		this.assignValueToTextView(characterMaxDamageTextView, this.mainController.getCharacterScreenBean().getMaxDamage(), tempCharacterScreen.getMaxDamage());
		TextView characterArmorTextView = (TextView) this.findViewById(R.id.armor_value);
		this.assignValueToTextView(characterArmorTextView, this.mainController.getCharacterScreenBean().getArmorClass(), tempCharacterScreen.getArmorClass());
		TextView characterHealthTextView = (TextView) this.findViewById(R.id.health_value);
		this.assignValueToTextView(characterHealthTextView, this.mainController.getCharacterScreenBean().getTotalHitPoints(), tempCharacterScreen.getTotalHitPoints());
		TextView characterManaTextView = (TextView) this.findViewById(R.id.mana_value);
		this.assignValueToTextView(characterManaTextView, this.mainController.getCharacterScreenBean().getTotalMana(), tempCharacterScreen.getTotalMana());
		TextView characterChanceToHitTextView = (TextView) this.findViewById(R.id.chance_to_hit_value);
		this.assignValueToTextView(characterChanceToHitTextView, this.mainController.getCharacterScreenBean().getChanceToHit(), tempCharacterScreen.getChanceToHit());

	}

	private int checkAttributeModification(int originalAttributeValue, int modifiedAttributeValue){
		if(originalAttributeValue < modifiedAttributeValue){
			return 1;
		} else if (originalAttributeValue == modifiedAttributeValue){
			return 0;
		} else {
			return -1;
		}
	}

	private void assignValueToTextView(TextView textView, int originalAttributeValue, int modifiedAttributeValue){
		int modificationColor = this.checkAttributeModification(originalAttributeValue, modifiedAttributeValue);
		switch(modificationColor){
		case -1:
			textView.setTextColor(Color.RED);
			break;
		case 1:
			textView.setTextColor(Color.GREEN);
			break;
		default:
			textView.setTextColor(Color.BLACK);
			break;
		}
		textView.setText(String.valueOf(modifiedAttributeValue));
	}

	public void resetTextColor(){
		TextView characterStrengthTextView = (TextView) this.findViewById(R.id.strength_value);
		characterStrengthTextView.setTextColor(Color.BLACK);
		TextView characterDexterityTextView = (TextView) this.findViewById(R.id.dexterity_value);
		characterDexterityTextView.setTextColor(Color.BLACK);
		TextView characterVitalityTextView = (TextView) this.findViewById(R.id.vitality_value);
		characterVitalityTextView.setTextColor(Color.BLACK);
		TextView characterMagicTextView = (TextView) this.findViewById(R.id.magic_value);
		characterMagicTextView.setTextColor(Color.BLACK);
		TextView characterMinDamageTextView = (TextView) this.findViewById(R.id.min_damage_value);
		characterMinDamageTextView.setTextColor(Color.BLACK);
		TextView characterMaxDamageTextView = (TextView) this.findViewById(R.id.max_damage_value);
		characterMaxDamageTextView.setTextColor(Color.BLACK);
		TextView characterArmorTextView = (TextView) this.findViewById(R.id.armor_value);
		characterArmorTextView.setTextColor(Color.BLACK);
		TextView characterHealthTextView = (TextView) this.findViewById(R.id.health_value);
		characterHealthTextView.setTextColor(Color.BLACK);
		TextView characterManaTextView = (TextView) this.findViewById(R.id.mana_value);
		characterManaTextView.setTextColor(Color.BLACK);
		TextView characterChanceToHitTextView = (TextView) this.findViewById(R.id.chance_to_hit_value);
		characterChanceToHitTextView.setTextColor(Color.BLACK);
	}
}