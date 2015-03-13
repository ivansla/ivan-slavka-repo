package ivan.slavka.utils;
import java.awt.event.ItemEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class Java2sAutoComboBox extends JComboBox {
	private class AutoTextFieldEditor extends BasicComboBoxEditor {

		private Java2sAutoTextField getAutoTextFieldEditor() {
			return (Java2sAutoTextField) this.editor;
		}

		AutoTextFieldEditor(java.util.List list) {
			//this.editor = new Java2sAutoTextField(list, Java2sAutoComboBox.this);
		}
	}

	public Java2sAutoComboBox(java.util.List list) {
		this.isFired = false;
		this.autoTextFieldEditor = new AutoTextFieldEditor(list);
		this.setEditable(true);
		this.setModel(new DefaultComboBoxModel(list.toArray()) {

			@Override
			protected void fireContentsChanged(Object obj, int i, int j) {
				if (!Java2sAutoComboBox.this.isFired)
					super.fireContentsChanged(obj, i, j);
			}

		});
		this.setEditor(this.autoTextFieldEditor);
	}

	public boolean isCaseSensitive() {
		return this.autoTextFieldEditor.getAutoTextFieldEditor().isCaseSensitive();
	}

	public void setCaseSensitive(boolean flag) {
		this.autoTextFieldEditor.getAutoTextFieldEditor().setCaseSensitive(flag);
	}

	public boolean isStrict() {
		return this.autoTextFieldEditor.getAutoTextFieldEditor().isStrict();
	}

	public void setStrict(boolean flag) {
		this.autoTextFieldEditor.getAutoTextFieldEditor().setStrict(flag);
	}

	public java.util.List getDataList() {
		return this.autoTextFieldEditor.getAutoTextFieldEditor().getDataList();
	}

	public void setDataList(java.util.List list) {
		this.autoTextFieldEditor.getAutoTextFieldEditor().setDataList(list);
		this.setModel(new DefaultComboBoxModel(list.toArray()));
	}

	void setSelectedValue(Object obj) {
		if (this.isFired) {
			return;
		} else {
			this.isFired = true;
			this.setSelectedItem(obj);
			this.fireItemStateChanged(new ItemEvent(this, 701, this.selectedItemReminder,
					1));
			this.isFired = false;
			return;
		}
	}

	@Override
	protected void fireActionEvent() {
		if (!this.isFired)
			super.fireActionEvent();
	}

	private AutoTextFieldEditor autoTextFieldEditor;

	private boolean isFired;

}