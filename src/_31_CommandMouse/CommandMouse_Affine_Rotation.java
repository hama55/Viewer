package _31_CommandMouse;
import _10_Base_Model_etc.Camera_Base;
import _10_Base_Model_etc.Model_Base;
import java.awt.Point;
import java.util.ArrayList;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.transform.Rotate;

//import object.Object_Camera;
//import moredyn.Main_Frame;
//import moredyn.View_Base;
import _42_Utility.util;


public class CommandMouse_Affine_Rotation
	implements CommandMouse_Imp
{

	Model_Base m_model_base = null;
	int s_rotx = 0;
	int s_roty = 0;
//	int m_nViewNumber = 0;
	int m_RotMode = 0;

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public CommandMouse_Affine_Rotation(
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
			s_rotx = start_point.x;
			s_roty = start_point.y;

//			m_nViewNumber = m_MainFrame.get_OpenGL_Play().askView(start_point);

//			//画面上部を選択した場合は、回転軸を奥行きベクトルとする。
//			int canvas_height = m_MainFrame.get_OpenGL_Play().get_height();
//			int canvas_width  = m_MainFrame.get_OpenGL_Play().get_width();
//			Point canvas_point  = m_MainFrame.get_OpenGL_Play().get_mouse_position();
	//		System.out.printf("start_point.y = %d, height = %d, height/8 = %d\n", start_point.y, height, height/8);
	//		System.out.printf("start_point.x = %d,  width = %d,  width/8 = %d\n", start_point.x,  width,  width/8);
	//		System.out.printf("point.y = %d, height = %d, height/8 = %d\n", point.y, height, height/8);
	//		System.out.printf("point.x = %d,  width = %d,  width/8 = %d\n", point.x,  width,  width/8);


//			if(canvas_point.y < canvas_height/10)
//			{
//				m_RotMode = 1;
//				System.out.printf("************** RotMode=1 **************\n");
//			}
//			else if(canvas_point.x > 7*canvas_width/10)
//			{
//				m_RotMode = 2;
//				System.out.printf("************** RotMode=2 **************\n");
//			}
//			else if(canvas_point.y > 7*canvas_height/10)
//			{
//				m_RotMode = 3;
//				System.out.printf("************** RotMode=3 **************\n");
//			}
//			else
//			{
//				m_RotMode = 0;
//				System.out.printf("************** RotMode=0 **************\n");
//			}

			if(start_point.y < 50)
			{
				m_RotMode = 1;
				System.out.printf("************** RotMode=1 **************\n");
			}
//			else if(canvas_point.x > 7*canvas_width/10)
//			{
//				m_RotMode = 2;
//				System.out.printf("************** RotMode=2 **************\n");
//			}
//			else if(canvas_point.y > 7*canvas_height/10)
//			{
//				m_RotMode = 3;
//				System.out.printf("************** RotMode=3 **************\n");
//			}
			else
			{
				m_RotMode = 0;
				System.out.printf("************** RotMode=0 **************\n");
			}
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
	@Override
	public void dragged(Point end_point)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());

			//mouseの移動距離を算出
			int rotx = (end_point.x - s_rotx);
			int roty = (end_point.y - s_roty);

			//値を保持（Drag中はこれが開始点）
			s_roty = end_point.y;
			s_rotx = end_point.x;

			int nRotX = 0;
			int nRotY = 0;
			int nRotZ = 0;

			///// Mouse移動量0の場合は、回転を行わない。
			if(rotx ==0 && roty == 0){
				return;
			}

			///// RotModeによって、回転の中心軸を変える。 /////
			//通常の動き。
			if(m_RotMode == 0)
			{
				//値をset
	//			camera.calc_rotation_camera(rotx, roty, 0, dAt);
				nRotX = rotx;
				nRotY = roty;
				nRotZ = 0;
			}
			//回転軸は、奥行きベクトル固定
			else if(m_RotMode == 1)
			{
				//値をset(軸は奥行きだが、xの動きによって回転量を決める)
	//			camera.calc_rotation_camera(0, 0, -rotx, dAt);
				nRotX = 0;
				nRotY = 0;
				nRotZ = -rotx;
			}
			//回転軸は、横軸ベクトル固定
			else if(m_RotMode == 2)
			{
				//値をset
	//			camera.calc_rotation_camera(0, roty, 0, dAt);
				nRotX = 0;
				nRotY = roty;
				nRotZ = 0;
			}
			//固定軸は、縦軸ベクトル固定
			else if(m_RotMode == 3)
			{
				//値をset
	//			camera.calc_rotation_camera(rotx, 0, 0, dAt);
				nRotX = rotx;
				nRotY = 0;
				nRotZ = 0;
			}

			//現在のCameraを取得
//			Object_Camera camera = m_MainFrame.get_OpenGL_Play().get_view(this.m_nViewNumber).get_Camera();
			Camera_Base camera = this.m_model_base.getViewCurrent().getCameraCurrent();

			//ツールコマンドを作成
			//クラス名をコマンド名とできるように TODO
//			double eye_position[] = new double[3];
            double at_position[] = new double[3];
//			double z_near;
//            camera.get_at_position(at_position);
//			camera.get_eye_position(eye_position);
			camera.get_at_position(at_position);
//			z_near = camera.get_ZNear();
//			double vector_eye_to_at[] = new double[3];
			
//			mathmatic.Mathmatic.calc_vector_minus_vector(at_position, eye_position, vector_eye_to_at);
//			mathmatic.Mathmatic.calc_vector_unit(vector_eye_to_at, vector_eye_to_at.length, vector_eye_to_at);
//			mathmatic.Mathmatic.calc_vector_times_scalar(vector_eye_to_at, z_near, vector_eye_to_at);
//			mathmatic.Mathmatic.calc_vector_plus_vector(vector_eye_to_at, eye_position, vector_eye_to_at);
            
			String sCommand = "$" + this.getClass().getName() + ",";
			sCommand += "0" + ",";
			sCommand += nRotX + ",";
			sCommand += nRotY + ",";
			sCommand += nRotZ + ",";
//			sCommand += camera.get_AtX() + ",";
//			sCommand += camera.get_AtY() + ",";
//			sCommand += camera.get_AtZ() ;
			sCommand += at_position[0] + ",";
			sCommand += at_position[1] + ",";
			sCommand += at_position[2] ;
//			sCommand += "0" + ",";
//			sCommand += "0" + ",";
//			sCommand += "0" ;
            
//			sCommand += vector_eye_to_at[0] + ",";
//			sCommand += vector_eye_to_at[1] + ",";
//			sCommand += vector_eye_to_at[2] ;
			
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
	public void performTool(String sStatement)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());

			//コマンド文
			System.out.println(sStatement);

			//文字列を解釈する
			ArrayList<String> sData = new ArrayList<>();
			util.string_factorize1(sStatement, ",", sData);

			//フォーマット
			//ViewPort(int), MoveX(int), MoveY(int)
			int nViewPort = util.change_String_To_Integer(sData.get(1));	//TODO
			int nRotX    = util.change_String_To_Integer(sData.get(2));
			int nRotY    = util.change_String_To_Integer(sData.get(3));
			int nRotZ    = util.change_String_To_Integer(sData.get(4));
			double dAtX    = util.change_String_To_Double(sData.get(5));
			double dAtY    = util.change_String_To_Double(sData.get(6));
			double dAtZ    = util.change_String_To_Double(sData.get(7));

			double[] dAt = {dAtX, dAtY, dAtZ};


			//回転
			Camera_Base camera = this.m_model_base.getViewCurrent().getCameraCurrent();
			camera.Rotation(nRotX, nRotY, nRotZ, dAt);

			//再描画
//			m_model_base.Update();
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}
}
