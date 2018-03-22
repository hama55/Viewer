package _12_Base_Color_etc;

import utility.util;

public class Color_Red extends Color_Base
{
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Color_Red()
	{
		try
		{
			//System.out.println(util.debug_ask_class_method_name());
			String name = "Red";
			float[] color = {3.0f, 0.0f, 0.0f};
			
			//Color_Baseに設定
			super.m_name = name;
			super.m_color[0] = color[0];
			super.m_color[1] = color[1];
			super.m_color[2] = color[2];
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}
}
