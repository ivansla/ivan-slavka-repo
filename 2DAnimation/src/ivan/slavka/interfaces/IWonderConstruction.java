package ivan.slavka.interfaces;

public interface IWonderConstruction {

	public int getBuilders();
	public float getCompletedPct();
	public void performConstruction();

	public float getStoneNeeded();
	public float getWoodNeeded();

	public float getStoneStored();
	public float getWoodStored();
}
