package _10_Base_Model_etc;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.SubScene;

//************************************************************************//
/**
 *	画面のカメラ、光源、フレームを設定する
*/
//************************************************************************//
public class View_Base
{
	//************************************************************************//
	/**
	 *	フィールド
	*/
	//************************************************************************//
	private Camera_Base				m_camera_current;		//現在のカメラ
	private ArrayList<Light_Base>	m_arry_light_current;	//現在の光源
	private ArrayList<Camera_Base>	m_arry_camera;			//カメラ
	private ArrayList<Light_Base>	m_arry_light;			//光源

	//************************************************************************//
	/**
	 *	コンストラクタ
	 *
	 *	@param
	 *	@return
	 *	@version
	*/
	//************************************************************************//
	public View_Base(
//		SubScene	subscene_fx,	//JavaFXの画面
//		Group		group_fx		//JavaFXの画面に描画するオブジェクト、ライト格納
	)
	{
		///// 初期化 /////
		m_camera_current		= new Camera_Base();
		m_arry_light_current	= new ArrayList<Light_Base>();

		m_arry_light_current.add(new Light_Base());
//		m_frame			= new Frame_Base();

        
//		//////////////////
//		//	カメラを設定
//		//////////////////
//		suscene_fx.setCamera(this.m_camera.getCamera());
//
//		//////////////////
//		//	光源を追加
//		//////////////////
//		for(Light_Base light : m_arry_light)
//		{
//			subscene_fx.add(light.getLight());
//		}
//
//		//////////////////
//		//	画面のフレームを設定
//		//////////////////
//		subscene_fx.setHeight(this.m_frame.getHeight());
//		subscene_fx.setWidth(this.m_frame.getWeight());
	}

	//************************************************************************//
	/**
	 *	getter
	*/
	//************************************************************************//
	public Camera_Base				getCameraCurrent()	{return this.m_camera_current;}
	public ArrayList<Light_Base>	getLightCurrent()	{return this.m_arry_light_current;}
}
