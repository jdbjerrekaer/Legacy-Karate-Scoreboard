package Fighter;

public class Temp
{
	int redpoint = 0;
	int redcat1 = 0;
	int redcat2 = 0;
	
	int blupoint = 0;
	int blucat1 = 0;
	int blucat2 = 0;	
	
	public Temp( int red1, int rcat1, int rcat2, int blu1, int bcat1, int bcat2 )
	{
		redpoint = red1;
		redcat1 = rcat1;
		redcat2 = rcat2;
		///////////////
		blupoint = blu1;
		blucat1 = bcat1;
		blucat2 = bcat2;
	}	
	
	public int getRedpoint()
	{
		return redpoint;
	}

	public void setRedpoint(int redpoint)
	{
		this.redpoint = redpoint;
	}

	public int getRedcat1()
	{
		return redcat1;
	}

	public void setRedcat1(int redcat1)
	{
		this.redcat1 = redcat1;
	}

	public int getRedcat2()
	{
		return redcat2;
	}

	public void setRedcat2(int redcat2)
	{
		this.redcat2 = redcat2;
	}

	///////////////////////////////////
	
	public int getBlupoint()
	{
		return blupoint;
	}

	public void setBlupoint(int blupoint)
	{
		this.blupoint = blupoint;
	}

	public int getBlucat1()
	{
		return blucat1;
	}

	public void setBlucat1(int blucat1)
	{
		this.blucat1 = blucat1;
	}

	public int getBlucat2()
	{
		return blucat2;
	}

	public void setBlucat2(int blucat2)
	{
		this.blucat2 = blucat2;
	}
	
}
