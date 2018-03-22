package _31_CommandMouse;
import _10_Base_Model_etc.Camera_Base;
import _10_Base_Model_etc.Model_Base;
import java.awt.Point;
import java.util.ArrayList;

//import moredyn.Main_Frame;
//import moredyn.View_Base;




import utility.util;


public class CommandMouse_Affine_Pan
	implements CommandMouse_Imp
{

	Model_Base m_model_base = null;
	int s_tranx = 0;
	int s_trany = 0;
//	int m_nViewNumber = 0;

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public CommandMouse_Affine_Pan(
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
			s_tranx = start_point.x;
			s_trany = start_point.y;

//			m_nViewNumber = m_MainFrame.get_OpenGL_Play().askView(start_point);
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

//	public void pressed(Point start_point, OpenGL_Play ogl){
//		System.out.println(this.getClass().getName() + "/" + Thread.currentThread().getStackTrace()[1].getMethodName());
//		//値を保持
//		s_tranx = start_point.x;
//		s_trany = start_point.y;
//	}

	//************************************************************************//
	/**
	*	dragged
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	@Override
	public void dragged(Point end_point)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());

			//mouseの移動距離を算出
			int nMoveX = (end_point.x - s_tranx);
			int nMoveY = (end_point.y - s_trany);

			//値を保持（Drag中はこれが開始点）
			s_tranx = end_point.x;
            s_trany = end_point.y;


			//ツールコマンドを作成
			//クラス名をコマンド名とできるように TODO
			String sCommand = "$" + this.getClass().getName() + ",";
			sCommand += "0" + ",";
			sCommand += nMoveX + ",";
			sCommand += nMoveY;

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
	*	ドラッグで生成されるアフィン変換のパンのコマンド文を受け取り、解釈、実行する。
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void performTool(String sStatement)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());

			//コマンド文
			System.out.println(sStatement);

			//文字列を解釈する
			ArrayList<String> sData = new ArrayList<String>();
			util.string_factorize1(sStatement, ",", sData);

			//フォーマット
			//ViewPort(int), MoveX(int), MoveY(int)
			int nViewPort = util.change_String_To_Integer(sData.get(1));	//TODO
			int nMoveX    = util.change_String_To_Integer(sData.get(2));
			int nMoveY    = util.change_String_To_Integer(sData.get(3));

			//実行
			//Cameraを取得
			//ここはビューポート番号からカメラを取得できるように。TODO
//			View_Base view = m_MainFrame.get_OpenGL_Play().get_view(nViewPort);
			//ここはビューポート番号からheightとwidthを取得できるように。TODO
			int nViewportHeight = (int)this.m_model_base.getHeight();
			int nViewportWidth  = (int)this.m_model_base.getWidth();
			//値をset
//			view.get_Camera().calc_translate_camera(nMoveX, nMoveY, nViewportHeight, nViewportWidth);
//			//再描画
//			m_MainFrame.Redisplay();


			Camera_Base camera = this.m_model_base.getViewCurrent().getCameraCurrent();

			//回転
			camera.Pan(nMoveX, nMoveY, nViewportHeight, nViewportWidth);

			//再描画
//			m_model_base.Update();
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}
}
