package my.first.game.beans;

import java.util.List;

import my.first.game.enums.ItemPropertyEnum;

public class ItemPropertyBean {

	private String name;
	private boolean isPrefix;
	private ItemPropertyEnum property;
	private int minValue;
	private int maxValue;
	private String occurrence;
	private int qualityLevel;
	private int steps;
	private int base;
	private int max;
	private int multiplier;
	private int minValue2;
	private int maxValue2;

	private int actualValue1;
	private int actualValue2;

	public ItemPropertyBean(){}

	public ItemPropertyBean(List<ItemPropertyBean> itemPropertyList, String name, boolean isPrefix, ItemPropertyEnum property, int minValue,
			int maxValue, String occurrence, int qualityLevel, int steps, int base, int max, int multiplier){

		this.name = name;
		this.isPrefix = isPrefix;
		this.property = property;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.occurrence = occurrence;
		this.qualityLevel = qualityLevel;
		this.steps = steps;
		this.base = base;
		this.max = max;
		this.multiplier = multiplier;
		this.minValue2 = 0;
		this.maxValue2 = 0;

		itemPropertyList.add(this);
	}

	public ItemPropertyBean(List<ItemPropertyBean> itemPropertyList, String name, boolean isPrefix, ItemPropertyEnum property, int minValue,
			int maxValue, int minValue2, int maxValue2, String occurrence, int qualityLevel, int steps, int base, int max, int multiplier){

		this.name = name;
		this.isPrefix = isPrefix;
		this.property = property;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.occurrence = occurrence;
		this.qualityLevel = qualityLevel;
		this.steps = steps;
		this.base = base;
		this.max = max;
		this.multiplier = multiplier;
		this.minValue2 = minValue2;
		this.maxValue2 = maxValue2;

		itemPropertyList.add(this);
	}

	public ItemPropertyBean copy(int actualValue1, int actualValue2){
		ItemPropertyBean itemProperty = new ItemPropertyBean();
		itemProperty.name = this.name;
		itemProperty.isPrefix = this.isPrefix;
		itemProperty.property = this.property;
		itemProperty.minValue = this.minValue;
		itemProperty.maxValue = this.maxValue;
		itemProperty.occurrence = this.occurrence;
		itemProperty.qualityLevel = this.qualityLevel;
		itemProperty.steps = this.steps;
		itemProperty.base = this.base;
		itemProperty.max = this.max;
		itemProperty.multiplier = this.multiplier;
		itemProperty.minValue2 = this.minValue2;
		itemProperty.maxValue2 = this.maxValue2;

		itemProperty.actualValue1 = actualValue1;
		itemProperty.actualValue2 = actualValue2;

		return itemProperty;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ItemPropertyEnum getProperty() {
		return this.property;
	}
	public void setProperty(ItemPropertyEnum property) {
		this.property = property;
	}
	public int getMinValue() {
		return this.minValue;
	}
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}
	public int getMaxValue() {
		return this.maxValue;
	}
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	public String getOccurrence() {
		return this.occurrence;
	}
	public void setOccurrence(String occurrence) {
		this.occurrence = occurrence;
	}
	public int getQualityLevel() {
		return this.qualityLevel;
	}
	public void setQualityLevel(int qualityLevel) {
		this.qualityLevel = qualityLevel;
	}
	public int getSteps() {
		return this.steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	public int getBase() {
		return this.base;
	}
	public void setBase(int base) {
		this.base = base;
	}
	public int getMax() {
		return this.max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMultiplier() {
		return this.multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	public boolean isPrefix() {
		return this.isPrefix;
	}

	public void setPrefix(boolean isPrefix) {
		this.isPrefix = isPrefix;
	}

	public int getMinValue2() {
		return this.minValue2;
	}

	public void setMinValue2(int minValue2) {
		this.minValue2 = minValue2;
	}

	public int getMaxValue2() {
		return this.maxValue2;
	}

	public void setMaxValue2(int maxValue2) {
		this.maxValue2 = maxValue2;
	}

	public int getActualValue1() {
		return this.actualValue1;
	}

	public void setActualValue1(int actualValue1) {
		this.actualValue1 = actualValue1;
	}

	public int getActualValue2() {
		return this.actualValue2;
	}

	public void setActualValue2(int actualValue2) {
		this.actualValue2 = actualValue2;
	}
}
