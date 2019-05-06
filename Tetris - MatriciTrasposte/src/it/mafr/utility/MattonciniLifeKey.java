package it.mafr.utility;

public enum MattonciniLifeKey
{
	ELLE("101011");
	private String lifeKey;

	private MattonciniLifeKey(String lifeKey)
	{
		this.lifeKey = lifeKey;

	}

	public String getLifeKey()
	{
		return lifeKey;
	}

	public void setLifeKey(String lifeKey)
	{
		this.lifeKey = lifeKey;
	}

}
