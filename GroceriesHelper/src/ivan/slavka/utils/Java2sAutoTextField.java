package ivan.slavka.utils;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Java2sAutoTextField extends JTextField {
	class AutoDocument extends PlainDocument {

		@Override
		public void replace(int i, int j, String s, AttributeSet attributeset)
				throws BadLocationException {
			super.remove(i, j);
			this.insertString(i, s, attributeset);
		}

		@Override
		public void insertString(int i, String s, AttributeSet attributeset)
				throws BadLocationException {
			if (s == null || "".equals(s))
				return;
			String s1 = this.getText(0, i);
			String s2 = Java2sAutoTextField.this.getMatch(s1 + s);
			int j = (i + s.length()) - 1;
			if (Java2sAutoTextField.this.isStrict && s2 == null) {
				s2 = Java2sAutoTextField.this.getMatch(s1);
				j--;
			} else if (!Java2sAutoTextField.this.isStrict && s2 == null) {
				super.insertString(i, s, attributeset);
				return;
			}
			if (Java2sAutoTextField.this.autoComboBox != null && s2 != null)
				Java2sAutoTextField.this.autoComboBox.setSelectedValue(s2);
			super.remove(0, this.getLength());
			super.insertString(0, s2, attributeset);
			Java2sAutoTextField.this.setSelectionStart(j + 1);
			Java2sAutoTextField.this.setSelectionEnd(this.getLength());
		}

		@Override
		public void remove(int i, int j) throws BadLocationException {
			int k = Java2sAutoTextField.this.getSelectionStart();
			if (k > 0)
				k--;
			String s = Java2sAutoTextField.this.getMatch(this.getText(0, k));
			if (!Java2sAutoTextField.this.isStrict && s == null) {
				super.remove(i, j);
			} else {
				super.remove(0, this.getLength());
				super.insertString(0, s, null);
			}
			if (Java2sAutoTextField.this.autoComboBox != null && s != null)
				Java2sAutoTextField.this.autoComboBox.setSelectedValue(s);
			try {
				Java2sAutoTextField.this.setSelectionStart(k);
				Java2sAutoTextField.this.setSelectionEnd(this.getLength());
			} catch (Exception exception) {
			}
		}

	}

	public Java2sAutoTextField() {
		this.isCaseSensitive = false;
		this.isStrict = true;
		this.autoComboBox = null;
		//this.init();
	}

	/*
	public Java2sAutoTextField(List<String> list) {
		this.isCaseSensitive = false;
		this.isStrict = true;
		this.autoComboBox = null;
		if (list == null) {
			throw new IllegalArgumentException("values can not be null");
		} else {
			this.dataList = list;
			//this.init();
			return;
		}
	}

	Java2sAutoTextField(List<String> list, Java2sAutoComboBox b) {
		this.isCaseSensitive = false;
		this.isStrict = true;
		this.autoComboBox = null;
		if (list == null) {
			throw new IllegalArgumentException("values can not be null");
		} else {
			this.dataList = list;
			this.autoComboBox = b;
			//this.init();
			return;
		}
	}
	 */
	private void init() {
		this.setDocument(new AutoDocument());
		if (this.isStrict && this.dataList.size() > 0)
			this.setText(this.dataList.get(0).toString());
	}

	public void init(List<String> list) {
		this.dataList = list;
		this.init();
	}

	private String getMatch(String s) {
		for (int i = 0; i < this.dataList.size(); i++) {
			String s1 = this.dataList.get(i).toString();
			if (s1 != null) {
				if (!this.isCaseSensitive
						&& s1.toLowerCase().startsWith(s.toLowerCase()))
					return s1;
				if (this.isCaseSensitive && s1.startsWith(s))
					return s1;
			}
		}

		return null;
	}

	@Override
	public void replaceSelection(String s) {
		AutoDocument _lb = (AutoDocument) this.getDocument();
		if (_lb != null)
			try {
				int i = Math.min(this.getCaret().getDot(), this.getCaret().getMark());
				int j = Math.max(this.getCaret().getDot(), this.getCaret().getMark());
				_lb.replace(i, j - i, s, null);
			} catch (Exception exception) {
			}
	}

	public boolean isCaseSensitive() {
		return this.isCaseSensitive;
	}

	public void setCaseSensitive(boolean flag) {
		this.isCaseSensitive = flag;
	}

	public boolean isStrict() {
		return this.isStrict;
	}

	public void setStrict(boolean flag) {
		this.isStrict = flag;
	}

	public List<String> getDataList() {
		return this.dataList;
	}

	public void setDataList(List<String> list) {
		if (list == null) {
			throw new IllegalArgumentException("values can not be null");
		} else {
			this.dataList = list;
			return;
		}
	}

	private List<String> dataList;

	private boolean isCaseSensitive;

	private boolean isStrict;

	private Java2sAutoComboBox autoComboBox;
}