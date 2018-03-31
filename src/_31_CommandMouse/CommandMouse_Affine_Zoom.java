package _31_CommandMouse;
import _10_Base_Model_etc.Camera_Base;
import _10_Base_Model_etc.Model_Base;
import java.awt.Point;
import java.util.ArrayList;

//import moredyn.Main_Frame;
//import moredyn.View_Base;

import _42_Utility.util;


public class CommandMouse_Affine_Zoom
	implements CommandMouse_Imp
{

	Model_Base m_model_base = null;
	double[] s_eye = {0.0, 0.0, 0.0};
	int startx = 0;
	int starty = 0;

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public CommandMouse_Affine_Zoom(
		Model_Base model
	)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
			m_model_base = model;
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//
	/**
	*	pressed
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	@Override
	public void pressed(Point start_point)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());

			//値を保持
			startx = start_point.x;
			starty = start_point.y;

		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//
	/**
	*	dragged
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void dragged(Point end_point)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());

			int nZoom = end_point.y - starty;

			//Drag中の始点となる
			starty = end_point.y;

			//ツールコマンドを作成
			//クラス名をコマンド名とできるように TODO
			String sCommand = "$" + this.getClass().getName() + ",";
			sCommand += "0" + ",";
			sCommand += nZoom + ",";
			sCommand += end_point.x + ",";
			sCommand += end_point.y;

			//実行
			this.performTool(sCommand);
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//
	/**
	*	clicked
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	@Override
	public void clicked(Point end_point)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//
	/**
	*	performTool
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void performTool(
		String sStatement	//命令文
	)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());

			System.out.println(sStatement);
			//文字列を解釈する
			ArrayList<String> sData = new ArrayList<String>();
			util.string_factorize1(sStatement, ",", sData);

			//フォーマット
			//ViewPort(int), MoveX(int), MoveY(int)
			int nViewPort = util.change_String_To_Integer(sData.get(1));	//TODO
			int nZoom     = util.change_String_To_Integer(sData.get(2));
			int nPosX     = util.change_String_To_Integer(sData.get(3));	//TODO カーソルの選択点を中心にズーム
			int nPosY     = util.change_String_To_Integer(sData.get(4));	//TODO

			//実行
			//現在のCameraを取得
			//ここはビューポート番号からカメラを取得できるように。TODO
			//Object_Camera camera = m_MainFrame.get_OpenGL_Play().get_camera();
//			View_Base view = this.m_model_base.get_OpenGL_Play().get_view(nViewPort);
			Camera_Base camera = this.m_model_base.getViewCurrent().getCameraCurrent();

			//ズーム
			camera.Zoom(nPosX, nPosY, nZoom);

			//再描画
//			m_model_base.Update();
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

}
