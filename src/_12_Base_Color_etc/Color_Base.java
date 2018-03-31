package _12_Base_Color_etc;

import _42_Utility.util;

abstract public class Color_Base
{
//	float fColor[][] = 
//	{{0.2f, 0.2f, 2.0f},
//	{2.0f, 0.2f, 0.2f},
//	{1.0f, 0.0f, 0.2f}};

	protected String  m_name;
	protected double[] m_color = {0.0f, 0.0f, 0.0f};

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	protected Color_Base()
	{		
	}

	//************************************************************************//
	/**
	*	getter
	*/
	//************************************************************************//
	public double[] get_color(){return m_color;}
	public String  get_name(){return m_name;}
	
	//************************************************************************//
	/**
	*	ask
	*/
	//************************************************************************//
	public float[] ask_color_float()
	{
		float[] fBuf = {(float)m_color[0], (float)m_color[1], (float)m_color[2] };
		return fBuf;
	}
	//************************************************************************//
	/**
	*	toString
	*/
	//************************************************************************//
	public String  toString(){return m_name;}
	
	//************************************************************************//
	/**
	*	同じ色か比較する
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Boolean compare(Color_Base color)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
			//色の成分が全て同じなら、同じ色とみなす。
			double[] colorBuf = color.get_color();
			for(int ic=0; ic<3; ic++)
			{
				if(colorBuf[ic] != m_color[ic])
				{
					return false;
				}
			}
			return true;
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
		
		return false;
	}
}
