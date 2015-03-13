package my.first.game.views.inventory;

import my.first.game.beans.ArmorItemBean;
import my.first.game.beans.EquipableItemBean;
import my.first.game.beans.ItemBean;
import my.first.game.beans.WeaponItemBean;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class ItemPropertyView extends RelativeLayout{

	private ItemBean selectedItem;
	private CharacterStatsView characterStatsView;

	public ItemPropertyView(Context context, AttributeSet attrs){
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.item_property_view, this);

	}

	public void showItemProperty(EquipableItemBean item){
		Log.v("ItemPropertyView", "showItemProperty");

		this.selectedItem = item;
		this.characterStatsView.showCharacterStatsData(item);

		TextView itemValueLabelTextView = (TextView) this.findViewById(R.id.item_value_label);
		TextView itemValueTextView = (TextView) this.findViewById(R.id.item_value);

		switch(item.getEquipableItemType()){
		case ARMOR:
		case HELM:
		case SHIELD:
			itemValueLabelTextView.setText("AC: ");
			itemValueTextView.setText(String.valueOf(((ArmorItemBean)item).getArmorClass()));
			break;
		case AXE:
		case BOW:
		case CLUB:
		case STAFF:
		case SWORD:
			itemValueLabelTextView.setText("DMG: ");
			itemValueTextView.setText(((WeaponItemBean)item).getMinDamage() + " - " + ((WeaponItemBean)item).getMaxDamage());
			break;
		}

		TextView itemNameTextView = (TextView) this.findViewById(R.id.item_name_label);
		itemNameTextView.setText(item.getItemName());
		TextView itemDurabilityLabelTextView = (TextView) this.findViewById(R.id.item_durability_label);
		itemDurabilityLabelTextView.setText("Durability: ");
		TextView itemDurabilityValueTextView = (TextView) this.findViewById(R.id.item_durability_value);
		itemDurabilityValueTextView.setText(item.getActualDurability() + " / " + item.getMaxDurability());

		if(item.getPrefix() != null){
			TextView itemProperty1TextView = (TextView) this.findViewById(R.id.item_property_1);
			itemProperty1TextView.setText("+" + item.getPrefix().getActualValue1() + " " + item.getPrefix().getProperty().toString());
		}

		if(item.getSuffix() != null){
			TextView itemProperty2TextView = (TextView) this.findViewById(R.id.item_property_2);
			itemProperty2TextView.setText("+" + item.getSuffix().getActualValue1() + " " + item.getPrefix().getProperty().toString());
		}
		TextView itemRequirementsTextView = (TextView) this.findViewById(R.id.item_requirements);
		itemRequirementsTextView.setText(item.getRequirements());
	}

	public ItemBean getSelectedItem() {
		return this.selectedItem;
	}

	public CharacterStatsView getCharacterStatsView() {
		return this.characterStatsView;
	}

	public void setCharacterStatsView(CharacterStatsView characterStatsView) {
		this.characterStatsView = characterStatsView;
	}
}
