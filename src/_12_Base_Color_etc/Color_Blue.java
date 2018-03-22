package _12_Base_Color_etc;

import utility.util;

public class Color_Blue extends Color_Base
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
	public Color_Blue()
	{
		try
		{
			//System.out.println(util.debug_ask_class_method_name());
			String name = "Blue";
			float[] color = {0.2f, 0.2f, 2.0f};
			
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
