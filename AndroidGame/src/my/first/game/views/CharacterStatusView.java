package my.first.game.views;

import my.first.game.beans.AbstractSheetBean;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class CharacterStatusView extends RelativeLayout {

	private AbstractSheetBean characterSheet;

	public CharacterStatusView(Context context, AttributeSet attrs){
		super(context, attrs);

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.battle_status_view, this);

	}

	public void updateData(){

		TextView characterNameTextView = (TextView) this.findViewById(R.id.battle_status_view_name_value);
		characterNameTextView.setText(this.characterSheet.getCharacterName());
		TextView characterCurrentHitPointsTextView = (TextView) this.findViewById(R.id.battle_status_view_health_current_value);
		characterCurrentHitPointsTextView.setText(String.valueOf(this.characterSheet.getCurrentHitPoints()));
		TextView characterTotalHitPointsTextView = (TextView) this.findViewById(R.id.battle_status_view_health_total_value);
		characterTotalHitPointsTextView.setText(String.valueOf(this.characterSheet.getTotalHitPoints()));

		TextView characterCurrentManaTextView = (TextView) this.findViewById(R.id.battle_status_view_mana_current_value);
		characterCurrentManaTextView.setText(String.valueOf(this.characterSheet.getCurrentMana()));
		TextView characterTotalManaTextView = (TextView) this.findViewById(R.id.battle_status_view_mana_total_value);
		characterTotalManaTextView.setText(String.valueOf(this.characterSheet.getTotalMana()));
	}

	public AbstractSheetBean getCharacterSheet() {
		return this.characterSheet;
	}

	public void setCharacterSheet(AbstractSheetBean characterSheet) {
		this.characterSheet = characterSheet;
	}
}
