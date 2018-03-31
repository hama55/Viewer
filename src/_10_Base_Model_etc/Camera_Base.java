package _10_Base_Model_etc;

import _20_Object_Template.Entity_Directory;
import _41_Mathatic.Mathmatic;
//import _41_Mathatic.*;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Translate;

import javafx.geometry.Point3D;
import javafx.scene.DepthTest;
import javafx.scene.transform.Rotate;

//import mathmatic.Mathmatic;
import _42_Utility.util;


//************************************************************************//
/**
 *	カメラクラス
 *	参考：
 *	http://yucchi.jp/blog/?p=1832
 *
*/
//************************************************************************//
public class Camera_Base
{
	//************************************************************************//
	/**
	 *	フィールド
	*/
	//************************************************************************//
    private final static double PI = 3.14159265358979;
    private final static double ZERO_TOL = 1e-8;
    private final static double m_eyeup_direction_def[]     = {0.0, -1.0, 0.0};
    private final static double m_camera_direction_def[]	= {0.0, 0.0, 1.0};

	private String	m_name;			//名前。重複不可
	private Camera	m_camera_fx;	//JavaFXのカメラ

	//カメラ位置、注目点、上向き
    private Translate m_position;
    private Rotate m_rotation;

    
	private double[] m_eye_position = new double[3];		//カメラの位置
	private double[] m_eyeup_direction = new double[3];		//カメラの上方向
	private double[] m_at_position = new double[3];		//カメラの中心に映す注目点の位置
    private double m_angle_f;                         //パースペクティブビューの設定：
	private double m_z_clip_near;					//パースペクティブビューの設定：表示範囲（手前）
	private double m_z_clip_far;                 //パースペクティブビューの設定：表示範囲（奥）

	///// 算出されるデータ /////
//	private double m_dEyeHorizon[] = {0.0, 0.0, 0.0};	//視点の水平方向
//	private double m_dEyeToAt[] = {0.0, 0.0, 0.0};		//視点から注目点の方向
//	private double[] m_dMatrix =   {0.0, 0.0, 0.0,
//									0.0, 0.0, 0.0,
//									0.0, 0.0, 0.0};
//	private double[] m_dAxisX = {0.0, 0.0, 0.0};
//	private double[] m_dAxisY = {0.0, 0.0, 0.0};
//	private double[] m_dAxisZ = {0.0, 0.0, 0.0};

	//回転軸表示用のグループ
//	Group m_group_sphere_rot_axis = new Group();
//	Group_Base m_group_spere_rot_axis = new Group_Base("回転軸");
	Entity_Directory m_group_spere_rot_axis = Entity_Directory.factory_or_search("回転軸");

	//************************************************************************//
	/**
	 *	Constructor
	 *
	 *	@return
	 */
	//************************************************************************//
	public Camera_Base()
	{
        double position[]           = {0.0, 0.0, 500.0};
        double at_position[]        = {0.0, 0.0, 0.0};
        double eyeup_direction[]    = {0.0, -1.0, 0.0};
        double angle_f              = 60.0;
        double z_clip_near          = 1.0;
        double z_clip_far           = 10000.0;

        this.initialize(
			"デフォルトカメラ",		//I		名前
            position,               //I		カメラの位置X,Y,Z
            at_position,            //I		カメラの中心に移す注目点の位置X,Y,Z
			eyeup_direction,		//I		カメラの上方向X,Y,Z
			angle_f,				//I		パースペクティブビューの設定：
			z_clip_near,			//I		パースペクティブビューの設定：表示範囲（手前）
			z_clip_far  			//I		パースペクティブビューの設定：表示範囲（奥）
		);
	}

	//************************************************************************//
	/**
	 *	Constructor
	 *
	 *	@return
	 */
	//************************************************************************//
	public Camera_Base(
        String sName,               //I		名前
        double position[],          //I		カメラの位置X,Y,Z
        double at_position[],       //I		カメラの中心に移す注目点の位置X,Y,Z
        double eyeup_direction[],   //I		カメラの上方向X,Y,Z
        double angle_f,             //I		パースペクティブビューの設定：
        double z_clip_near,         //I		パースペクティブビューの設定：表示範囲（手前）
        double z_clip_far           //I		パースペクティブビューの設定：表示範囲（奥）
	)
	{
		System.out.println(util.debug_ask_class_method_name());

		this.initialize(
			sName,                  //I		名前
            position,               //I		カメラの位置X,Y,Z
            at_position,            //I		カメラの中心に移す注目点の位置X,Y,Z
			eyeup_direction,		//I		カメラの上方向X,Y,Z
			angle_f,				//I		パースペクティブビューの設定：
			z_clip_near,			//I		パースペクティブビューの設定：表示範囲（手前）
			z_clip_far  			//I		パースペクティブビューの設定：表示範囲（奥）
		);
	}

	//************************************************************************//
	/**
	 *	initialize
	 *
	 *	@return
	 */
	//************************************************************************//
	private void initialize(
		String  sName,                  //I		名前
		double  position[],             //I		カメラの位置X,Y,Z
		double  at_position[],          //I		カメラの中心に移す注目点の位置X,Y,Z
		double  eyeup_direction[],		//I		カメラの上方向X,Y,Z
		double  angle_f,				//I		パースペクティブビューの設定：
		double  z_clip_near,			//I		パースペクティブビューの設定：表示範囲（手前）
		double  z_clip_far  			//I		パースペクティブビューの設定：表示範囲（奥）	
	)
	{
		System.out.println(util.debug_ask_class_method_name());
        
        
		///////////////////////////////////
		//
		//	JavaFXのカメラを設定
		//
		///////////////////////////////////
		this.m_camera_fx = new PerspectiveCamera(true);
//        // 直投影カメラ
//        this.m_camera_fx = new ParallelCamera();
        

        m_position = new Translate();
        m_rotation = new Rotate();
//        m_rotation = new Rotate(45, new Point3D(1, 0, 0));    
        
        
        m_camera_fx.setDepthTest(DepthTest.ENABLE);
        
        ///// カメラの位置 /////
		this.m_camera_fx.getTransforms().add(m_position);

        ///// カメラの回転 /////
        this.m_camera_fx.getTransforms().add(m_rotation);        
        
		///////////////////////////////////
		//
		//	値を保存
		//
		///////////////////////////////////
		this.set_name(sName);
        this.set_eye_position(position);
        this.set_at_position(at_position);
        this.set_eyeup_direction(eyeup_direction);
        this.set_ZFar(z_clip_far);
        this.set_ZNear(z_clip_near);
        this.set_AngleF(angle_f);
        
		///////////////////////////////////
		//
		//	カメラを更新
		//
		///////////////////////////////////
        this.check_and_fix_eyeup_direction();

        this.update();


	}

	//************************************************************************//
	/**
	 *	setter
	 *
	 */
	//************************************************************************//
	public void set_AngleF(double angle){m_angle_f = angle;};
	public void set_ZNear(double znear){m_z_clip_near = znear;};
	public void set_ZFar(double zfar){m_z_clip_far = zfar;};    
    public void set_eyeup_direction(double[] d) {Mathmatic.copy_vector(3, d, m_eyeup_direction);}
    public void set_at_position(double[] d)     {Mathmatic.copy_vector(3, d, m_at_position);}
    public void set_eye_position(double[] d)     {Mathmatic.copy_vector(3, d, m_eye_position);}
    public void set_name(String s)                  {m_name = s;}

	//************************************************************************//
	/**
	 *	getter
	 *
	 *	@return
	 */
	//************************************************************************//
	public String get_Name(){return this.m_name;}
	public double get_Angle()				{return m_angle_f;}
	public double get_ZNear()				{return m_z_clip_near;}
	public double get_ZFar()				{return m_z_clip_far;}
	public Camera getCamera()				{return m_camera_fx;}
    public void get_eye_position(double d[])    {Mathmatic.copy_vector(3, m_eye_position, d);}
    public void get_at_position(double d[])     {Mathmatic.copy_vector(3, m_at_position, d);}
    public void get_eyeup_direction(double d[]){Mathmatic.copy_vector(3, m_eyeup_direction, d);}
	public Entity_Directory get_Rotate_Axis()	{return m_group_spere_rot_axis;}
	
    //************************************************************************//
	/**
	*	Cameraの座標系
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void askCameraMatrix(
		double[] camera_matrix    //O     カメラの座標系
	)
    {
        camera_matrix[0] = this.m_rotation.getMxx();
        camera_matrix[1] = this.m_rotation.getMxy();
        camera_matrix[2] = this.m_rotation.getMxz();
        camera_matrix[3] = this.m_rotation.getMyx();
        camera_matrix[4] = this.m_rotation.getMyy();
        camera_matrix[5] = this.m_rotation.getMyz();
        camera_matrix[6] = this.m_rotation.getMzx();
        camera_matrix[7] = this.m_rotation.getMzy();
        camera_matrix[8] = this.m_rotation.getMzz();
    }
       
    
    //************************************************************************//
	/**
	*	Cameraの回転
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void Rotation(
		int rotx,       //I     回転軸の成分X
		int roty,       //I     回転軸の成分Y
		int rotz,       //I     回転軸の成分Z
		double[] rotation_origin    //I     回転中心
	)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
            
//			Mathmatic.output_Value("●常に表示1：m_eyeup_direction", m_eyeup_direction);			
//			Mathmatic.output_Value("●常に表示1：m_eye_position", m_eye_position);			

			///// Mouse移動量0の場合は、回転を行わない。/////
			if(rotx ==0 && roty == 0 && rotz == 0){
				return;
			}
            
			//この座標系で、マウスの動きに対応した回転を行う。
			double[] rotation_axis = {-roty, rotx, rotz};
            double rotation_degree = Mathmatic.calc_length_vector(3, rotation_axis);
			Mathmatic.calc_vector_unit(rotation_axis, 3, rotation_axis);
//            System.out.println(String.format("Rotation内の回転軸、変換前： %f,   %f,   %f", rotation_axis[0], rotation_axis[1], rotation_axis[2]));

			//絶対座標系上の回転軸に変換する。
            double camera_matrix[] = new double[9];
            this.askCameraMatrix(camera_matrix);
			Mathmatic.calc_multi_matrix_vector(3, camera_matrix, rotation_axis, rotation_axis);
            Mathmatic.calc_vector_unit(rotation_axis, 3, rotation_axis);
//            System.out.println(String.format("Rotation内の回転軸、変換後： %f,   %f,   %f", rotation_axis[0], rotation_axis[1], rotation_axis[2]));

            
			//eyeupの回転前の上の点
			double[] eyeup_point = new double[3];
//			Mathmatic.calc_vector_unit(m_eyeup_direction, 3, m_eyeup_direction);
			Mathmatic.calc_vector_plus_vector(m_eyeup_direction, m_eye_position, eyeup_point);
//			Mathmatic.output_Value("●常に表示1：eyeup_point", eyeup_point);

			//絶対座標形上の回転軸を使用して、Eyeを回転する。
			double[] dResultS = {0.0, 0.0, 0.0};
            double[] rotation_second_point = {0.0, 0.0, 0.0};
            Mathmatic.calc_vector_plus_vector(rotation_axis, rotation_origin, rotation_second_point);
			Mathmatic.calc_rotate_around_axis2(rotation_origin, rotation_second_point, m_eye_position, rotation_degree, dResultS);
            Mathmatic.copy_vector(3, dResultS, m_eye_position);
//            System.out.println(String.format("Rotation内の回転中心： %f,   %f,   %f", rotation_origin[0], rotation_origin[1], rotation_origin[2]));
            
 			/// 回転後のEyeUpの上の点を変更する /////
//			Mathmatic.calc_ro
			Mathmatic.calc_rotate_around_axis2(rotation_origin, rotation_second_point, eyeup_point, rotation_degree, dResultS);
            Mathmatic.copy_vector(3, dResultS, eyeup_point);
			Mathmatic.calc_vector_minus_vector(eyeup_point, m_eye_position, m_eyeup_direction);

//			Mathmatic.output_Value("●常に表示2：m_eyeup_direction", m_eyeup_direction);			
//			Mathmatic.output_Value("●常に表示2：m_eye_position", m_eye_position);			
//			Mathmatic.output_Value("●常に表示2：eyeup_point", eyeup_point);
//			Mathmatic.output_Value("●●常に表示2：rotation_axis", rotation_axis);			
//			Mathmatic.output_Value("●●常に表示2：rotation_origin", rotation_origin);			
//			Mathmatic.output_Value("●●常に表示2：rotation_second_point", rotation_second_point);			

//			Mathmatic.calc_rotate_around_axis(rotation_origin, rotation_second_point, m_eyeup_direction, rotation_degree, dResultS);
//            Mathmatic.copy_vector(3, dResultS, m_eyeup_direction);

//			//////////////////////////
//			//	画面上に回転軸を表示
//			//////////////////////////
//			m_group_spere_rot_axis.clear();
//			double[] rot_axis_for_view = new double[3];
//			Mathmatic.calc_vector_minus_vector(rotation_second_point, rotation_origin, rot_axis_for_view);
//			Mathmatic.calc_vector_unit(rot_axis_for_view, 3, rot_axis_for_view);
//			final double length = 150;
//			final double radius = 10.0;
//			final int max_number = 10;
//			for(int nc=0; nc<max_number; nc++)
//			{
//				//＋側の回転軸の点
//				double[] buf_center1 = {
//					(rotation_origin[0] + rot_axis_for_view[0]*length)*nc/max_number,
//					(rotation_origin[1] + rot_axis_for_view[1]*length)*nc/max_number,
//					(rotation_origin[2] + rot_axis_for_view[2]*length)*nc/max_number
//				};
//				Object_04_Sphere sphere1 = new Object_04_Sphere(String.format("%d", nc), buf_center1, radius);
//				m_group_spere_rot_axis.add_object(sphere1, Boolean.TRUE);
//
//				//－側の回転軸の点
//				double[] buf_center2 = {
//					(rotation_origin[0] + rot_axis_for_view[0]*-1*length)*nc/max_number,
//					(rotation_origin[1] + rot_axis_for_view[1]*-1*length)*nc/max_number,
//					(rotation_origin[2] + rot_axis_for_view[2]*-1*length)*nc/max_number
//				};
//				Object_04_Sphere sphere2 = new Object_04_Sphere(String.format("-%d", nc), buf_center2, radius);
//				m_group_spere_rot_axis.add_object(sphere2, Boolean.TRUE);
//
//			}
//			
//			//登録
//		
//			//回転軸を登録
//			Main.get_model_base().Update();
			

			//更新
            this.update();
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//
	/**
	*	Cameraのパン
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void Pan(
		int tranx,
		int trany,
		int height,
		int width
	)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
            
			double dtranx = (double)tranx;
			double dtrany = (double)trany;
			System.out.printf("Mouse tranx=%f, trany=%f\n", dtranx, dtrany);

			///// 画面サイズと空間サイズ(空間上の小窓)の割合を算出[式2参照] /////
			double dHeight = m_z_clip_near * Math.tan(m_angle_f*Math.PI/180.0*0.5) * 2.0;
			double dAspect = (double)width / (double)height;
			double dWidth = dHeight * dAspect;
			double dRatioWidth = dWidth / (double)width;
			double dRatioHeight = dHeight / (double)height;

			//eye->atまでの距離
			double sum2 = 0.0;
            double eye_position[] = {0.0, 0.0, 0.0};
            double at_position[] = {0.0, 0.0, 0.0};
            this.get_eye_position(eye_position);
            this.get_at_position(at_position);
            
            double dLengthEyeAt = Mathmatic.calc_length_between_point_point(eye_position, at_position);

			//eye->ZNearまでの距離
			double dLengthEyeZNear = m_z_clip_near;

			//ZNearとAtの倍率
			double dRationZNearAt = dLengthEyeAt/dLengthEyeZNear;

			//パンのベクトル（変換前）
			double dTrans[] = {
				-dtranx*dRatioWidth*dRationZNearAt,
				-dtrany*dRatioHeight*dRationZNearAt,
				0.0
			};
			//パンのベクトル（変換後）
			double dVecT[] = {0.0, 0.0, 0.0};
            double camera_matrix[] = new double[9];
            this.askCameraMatrix(camera_matrix);
			Mathmatic.calc_multi_matrix_vector(3, camera_matrix, dTrans, dVecT);

			//EyeとAtを並行移動
//			for(int ic=0; ic<3; ic++)
//			{
//				eye_position[ic] += dVecT[ic];
//				at_position[ic] += dVecT[ic];
//			}
            Mathmatic.calc_vector_plus_vector(eye_position, dVecT, this.m_eye_position);
            Mathmatic.calc_vector_plus_vector(at_position, dVecT, this.m_at_position);
            
            this.update();
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//
	/**
	*	Cameraのズーム
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void Zoom(
        int nPosX,
        int nPosY,
        int nZoom
    )
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());

            double eye_position[]        = {0.0, 0.0, 0.0};
            double at_position[]         = {0.0, 0.0, 0.0};
            double buffer[]              = {0.0, 0.0, 0.0};
 
            this.get_eye_position(eye_position);
            this.get_at_position(at_position);
            
			//glScaledと似た考えを採用した。シンプル且つ強力[式1参照]
			double zoom = 1.0 - 0.01*(double)nZoom;
            
            Mathmatic.calc_vector_minus_vector(eye_position, at_position, buffer);
            Mathmatic.calc_vector_times_scalar(buffer, zoom, buffer);
            Mathmatic.calc_vector_plus_vector(buffer, at_position, this.m_eye_position);
            
            //カメラの値を更新
            this.update();
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}


	//************************************************************************//
	/**
	*	現在のView(Eye, At, EyeUp)からAxis(X,Y,Z), Matrixを算出
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void calc_current_view_matrix_axis()
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());

			///// 今見ている画面の、上方向、右方向に合わせた座標系を算出する /////

            //現在位置Eyeの取得
//            m_dEye[0] = this.m_camera_fx.getTranslateX();
//            m_dEye[1] = this.m_camera_fx.getTranslateY();
//            m_dEye[2] = this.m_camera_fx.getTranslateZ();
              double eye_position[] = {0.0, 0.0, 0.0};
              this.get_eye_position(eye_position);
            

//            for(int ic=0; ic<3; ic++)
//			{
//				//Z軸（奥行き方向）
//				m_dAxisZ[ic] = m_dEye[ic] - m_dAt[ic];
//				//Y軸(上方向)
//				m_dAxisY[ic] = m_dEyeUp[ic]; //+ dRo[ic];
//			}
//			//Z,Y軸をユニット化
//			Mathmatic.calc_vector_unit(m_dAxisZ, 3, m_dAxisZ);
//			Mathmatic.calc_vector_unit(m_dAxisY, 3, m_dAxisY);
            
//            //Z軸（奥行き方向）
//            Mathmatic.calc_vector_between_point_point_unit(m_dAt, m_dEye, m_dAxisZ);
//            //Y軸(上方向)
//            Mathmatic.copy_vector(m_dEyeUp, m_dAxisY);
            
            
//			//X軸（右方向）
//			Mathmatic.calc_OuterProduct_unit(m_dAxisY, m_dAxisZ, m_dAxisX);
//			for(int ic=0; ic<3; ic++)
//			{
//				m_dMatrix[ic*3+0] = m_dAxisX[ic];
//				m_dMatrix[ic*3+1] = m_dAxisY[ic];
//				m_dMatrix[ic*3+2] = m_dAxisZ[ic];
//			}

            
        
        }
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//
	/**
	*	変換行列、視点ベクトル、水平ベクトルを更新
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void update()
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
            
            this.check_and_fix_eyeup_direction();
    
            double eye_position[]   = {0.0, 0.0, 0.0};
            double at_position[]    = {0.0, 0.0, 0.0};
            double up_direction[]	= {0.0, 0.0, 0.0};
            
            //値を取得(うっかりメンバ変数を変更しないようにコピーして使う)
            this.get_eye_position(eye_position);
            this.get_at_position(at_position);
            this.get_eyeup_direction(up_direction);

			/////////////////////////////////////////////////////////////////
            //
            //	Translate設定
            //
            /////////////////////////////////////////////////////////////////
            this.m_position.setX(eye_position[0]);
            this.m_position.setY(eye_position[1]);
            this.m_position.setZ(eye_position[2]);
            
            /////////////////////////////////////////////////////////////////
            //
            //	Rotateのための回転軸、回転量を算出
            //
            /////////////////////////////////////////////////////////////////

            //カメラの向いている方向を算出
            double camera_direction[] = new double[3];
            Mathmatic.calc_vector_minus_vector(at_position, eye_position, camera_direction);
            Mathmatic.calc_vector_unit(camera_direction, 3, camera_direction);
            
            ///////////////////////////
            //  回転軸と回転量の算出
            //  最初に回転軸と回転量(rot_asix_a)を求める。
			//	これは、カメラのデフォルト向きから見たい位置に変換するもの。
            //  ただしこれを作用させてもカメラの上向きは合わない状態。
            ///////////////////////////
            double rot_axis_a[] = new double[4];
            
            ///// 回転軸と回転量(rot_asix_a)を求める /////
            this.calc_rotate_A(
                camera_direction,
                rot_axis_a
            );
            
            ///// 回転軸と回転量(rot_asix_a)を求める /////
            double[] rot_axis_b = new double[4];
            this.calc_rotate_B(
                up_direction,
                rot_axis_a,
                rot_axis_b
            );

            //回転軸A,Bを合成
//            double rot_axis_a_reverse[] = new double[4];
//            double rot_axis_b_reverse[] = new double[4];
//            double rot_axis_result[] = new double[4];
//            Mathmatic.copy_vector(4, rot_axis_a, rot_axis_a_reverse);
//            Mathmatic.copy_vector(4, rot_axis_b, rot_axis_b_reverse);
//            //回転角度を逆転させる。
//            rot_axis_a_reverse[3] *= -1.0;
//            rot_axis_b_reverse[3] *= -1.0;
//            Mathmatic.calc_rotation_unite(rot_axis_a_reverse, rot_axis_b_reverse, rot_axis_result);
            double rot_axis_result[] = new double[4];
            Mathmatic.calc_rotation_unite(rot_axis_b, rot_axis_a, rot_axis_result);


            //Rotateに回転軸、回転量を設定
            Point3D point3d = new Point3D(rot_axis_result[0], rot_axis_result[1], rot_axis_result[2]);
//            m_rotation.setAngle(rot_axis_result[3]);
            m_rotation.setAngle(-rot_axis_result[3]);
            m_rotation.setAxis(point3d);
			
            
            
//            double matrix[] = new double[9];
//            this.askCameraMatrix(matrix);
//            System.out.println(String.format("マトリックス: %f,   %f,   %f,   %f,   %f,   %f,   %f,   %f,   %f", matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5], matrix[6], matrix[7], matrix[8] ));
//
//            double vec_direc[] = new double[3];
//            Mathmatic.calc_vector_minus_vector(at_position, eye_position, vec_direc);
//            Mathmatic.calc_vector_unit(vec_direc, 3, vec_direc);
//            System.out.println(String.format("目の位置: %f,   %f,   %f", eye_position[0], eye_position[1], eye_position[2] ));
//            System.out.println(String.format("見る位置: %f,   %f,   %f", at_position[0], at_position[1], at_position[2] ));
//            System.out.println(String.format("視線ベク: %f,   %f,   %f", vec_direc[0], vec_direc[1], vec_direc[2] ));
//            System.out.println(String.format("上方向:   %f,   %f,   %f", up_direction[0], up_direction[1], up_direction[2] ));  
            
            /////////////////////////////////////////////////////////////////
            //
            //	その他設定
            //
            /////////////////////////////////////////////////////////////////
            this.m_camera_fx.setFarClip(m_z_clip_far);
            this.m_camera_fx.setNearClip(m_z_clip_near);
            
//            double eye_at_direction[] = new double[3];
//            Mathmatic.calc_vector_minus_vector(this.m_at_position, this.m_eye_position, eye_at_direction);
//
//            
//            Mathmatic.calc_vector_unit(eye_at_direction, 3, eye_at_direction);
//            double inner_product = Mathmatic.calc_InnerProduct(3, eye_at_direction, m_eyeup_direction);
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}
	//************************************************************************//
	/**
	*	カメラを更新
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
    public void update(
        double eye_position[],      //I     カメラの位置
        double at_position[],       //I     カメラの注目点
        double eyeup_direction[],   //I     カメラの上方向
        double z_clip_near,         //I     直近クリップ
        double z_clip_far,          //I     遠方クリップ
        double angle                //I     アングル
    )
    {
        //●●●、上の値からTranslateとRotateを計算する。●●●
    }
    
	//************************************************************************//
	/**
	*	EyeUpがちゃんとベクトル（Eye->At）に垂直になっているかチェック。<br>
    *   なっていない場合は修正する。
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
    private void check_and_fix_eyeup_direction()
    {
		//ベクトル(Eye→At) 
        double eye_direction[] = new double[3];
        Mathmatic.calc_vector_minus_vector(this.m_at_position, this.m_eye_position, eye_direction);
        Mathmatic.calc_vector_unit(eye_direction, 3, eye_direction);
        
        //ベクトル(Eye→At)とEyeUpの為す角度を取得
        double thete = Mathmatic.calc_angle_begween_vector_vector(eye_direction, this.m_eyeup_direction);
        double thete_degree = thete*180.0/PI;
        
        //もし90度であれば問題ない。そのまま返す。
        if(Math.abs(thete_degree - 90.0) <= ZERO_TOL)
        {
            return;
        }
        //もし0度か180度の場合は平行のため問題あり。新たな軸を作成する。
        else if(Math.abs(thete_degree) <= ZERO_TOL || Math.abs(thete_degree - 180.0) <= ZERO_TOL)
        {
			System.out.println("●●●カメラ上方向を修正1●●●");
			//新しい軸をつくる。ベクトル（Eye→At）のX成分とY成分を逆転したベクトルであれば平行とならない。
            this.m_eyeup_direction[0] = eye_direction[1];
            this.m_eyeup_direction[1] = eye_direction[0];
            this.m_eyeup_direction[2] = eye_direction[2];

            //2回目の計算で直交化される。
            this.check_and_fix_eyeup_direction();            

       }
        else
        {
 			System.out.println("●●●カメラ上方向を修正2●●●");
//			Mathmatic.output_Value("thete_degree", thete_degree);
//			Mathmatic.output_Value("eye_direction", eye_direction);
//			Mathmatic.output_Value("m_eyeup_direction", m_eyeup_direction);			
			
            //EyeUpを修正する。グラムシュミットの直交化法を使用。
            Mathmatic.calc_vector_unit(this.m_eyeup_direction, 3, this.m_eyeup_direction);
            Mathmatic.calc_Gram_Schmidt_vector(this.m_eyeup_direction, eye_direction, this.m_eyeup_direction);
//            Mathmatic.calc_vector_unit(this.m_eyeup_direction, 3, this.m_eyeup_direction);
			
//			//テスト：チェック
//			//ベクトル(Eye→At) 
////			 double eye_direction[] = new double[3];
//			 Mathmatic.calc_vector_minus_vector(this.m_at_position, this.m_eye_position, eye_direction);
//			 Mathmatic.calc_vector_unit(eye_direction, 3, eye_direction);
//
//			 //ベクトル(Eye→At)とEyeUpの為す角度を取得
//			 double thete2 = Mathmatic.calc_angle_begween_vector_vector(eye_direction, this.m_eyeup_direction);
//			 double thete_degree2 = thete*180.0/PI;
// 			System.out.println(thete_degree2);
//			
//			
// 			System.out.println("●●●カメラ上方向を修正2変更後●●●");
//			Mathmatic.output_Value("thete_degree2", thete_degree2);
//			Mathmatic.output_Value("eye_direction", eye_direction);
//			Mathmatic.output_Value("m_eyeup_direction", m_eyeup_direction);
       }
        
// 		System.out.println("●●●カメラ上方向を修正●●●");

    }
//	//************************************************************************//
//	/**
//	*	カメラを上書き
//	*
//	*	@param
//	*	@return
//	*	@version
//	*/
//	//************************************************************************//
////	public void set_camera(Object_Camera camera)
//	public void set_camera(Camera_Base camera)
//	{
//		try
//		{
//			System.out.println(util.debug_ask_class_method_name());
//
//			this.set_EyeX(camera.get_EyeX());
//			this.set_EyeY(camera.get_EyeY());
//			this.set_EyeZ(camera.get_EyeZ());
//			this.set_AtX(camera.get_AtX());
//			this.set_AtY(camera.get_AtY());
//			this.set_AtZ(camera.get_AtZ());
//			this.set_EyeUpX(camera.get_EyeUpX());
//			this.set_EyeUpY(camera.get_EyeUpY());
//			this.set_EyeUpZ(camera.get_EyeUpZ());;
//
//			this.set_AngleF(camera.get_Angle());
//			this.set_ZNear(camera.get_ZNear());
//			this.set_ZFar(camera.get_ZFar());
//
//			this.update();
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//	}

//	//************************************************************************//
//	/**
//	 *	Pick用
//	 *
//	 *	@return
//	 */
//	//************************************************************************//
//	@Override
//	public PickedItem ask_isTouch(
//		double[]	spoint,			//I		ピック円柱の始点
//		double[]	epoint,			//I		ピック円柱の終点
//		Filter		filter,			//I		選択フィルタ
//		double		tolerance		//O		ピック円柱の半径
//	)
//	{
//		try
//		{
//			System.out.println(util.debug_ask_class_method_name());
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//		return null;
//	}

//	//************************************************************************//
//	/**
//	 *	描画
//	 *
//	 *	@return
//	 */
//	//************************************************************************//
//	@Override
//	public void create_object_displaylist_all(
//		OpenGL_Play				OGL,
//		Material				material,
//		DrawPen					drawPen,
//		ArrayList<Object_Base>	arryObject,
//		ArrayList<Integer>		arryDispList_Disp,
//		ArrayList<Integer>		arryDispList_Non_Disp
//	)
//	{
//		try
//		{
//			System.out.println(util.debug_ask_class_method_name());
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//	}

	//************************************************************************//
	/**
	 *
	 *
	 *	@return
	 */
	//************************************************************************//
	//@Override
	//public String ask_ClassObjName(){return this.getClass().getSimpleName();}

//	//************************************************************************//
//	/**
//	 *	読み込みデータを実体化
//	 */
//	//************************************************************************//
//	@Override
//	public void read_data(
//		XmlData			xmlData,		//各オブジェクト形式のXML(例:<Group_Base>1,XXX \n 2,XXX \n ･･･ </Group_Base>)
//		FileInputOutput	dataFileIO,		//生成したオブジェクトを全て格納
//		Group_Base		topGroup		//Xmlオプションに"top"とあるものはTopのGroupにぶら下がる
//	)
//	{
//	}

//	//************************************************************************//
//	/**
//	 *	idと関連付けたオブジェクトをプールに登録
//	 */
//	//************************************************************************//
//	@Override
//	public void factory_and_add(
//		String			id,		//保存、読み込み時のオブジェクトID
//		FileInputOutput	fileIO	//データプール
//	)
//	{
//		fileIO.add(id, new Object_Camera());
//	}
//	//************************************************************************//
//	/**
//	 *	ファイルに書き出し
//	 *
//	 *	@param
//	 *	@return
//	 *	@version
//	 */
//	//************************************************************************//
//	@Override
//	public String write_data(
//		XmlData				xmlDataMySelf,	//OUT	親のXmlData。このXmlDataの子として登録する。
//		FileInputOutput		dataFileIO		//IN	書き込み時の全てのXmlDataを持つ。
//	)
//	{
//		return null;
//
//	}
//	//************************************************************************//
//	/**
//	 *	ポップアップを取得
//	 *
//	 *	@param
//	 *	@return
//	 *	@version
//	 */
//	//************************************************************************//
//	@Override
//	public PopupMenu_TreeNode ask_PopupMenu()
//	{
//		////// ポップアップメニュー 作成 /////
//		PopupMenu_TreeNode popupMenu = new PopupMenu_TreeNode("カメラのポップアップメニュー");
//		//メニューアイテムを作成・追加
//		MenuItem_Base menuitem = new MenuItem_Base(
//			"セット",
//			new Command_set_camera(
//				m_mainFrame,
//				this
//			)
//		);
//		popupMenu.add_MenuItem(menuitem);
//		return popupMenu;
//	}
    
    
    private void test_systemout(String title, double ... vector)
    {
        return;
//        if( title.contains("rot_axis_a") == false &&
//            title.contains("rot_axis_thete_eyeup") == false &&
//            title.contains("rot_axis_result") == false &&
//            title.contains("●")
//            )
//        {
//            return;
//        }
           
            
//        if( title.contains("thete_camera") == false)
//        {
//            return;
//        }    
//    
//        String str = "●TEST●" + title + ":";
//        
//        double sum = 0.0;
//        for(int ic=0; ic<vector.length; ic++)
//        {
//            sum += (vector[ic] * vector[ic]);
//        }
//        sum = Math.sqrt(sum);
//        
//        str += String.format("    |v|=%f", sum);
//        
//        
//        for(int ic=0; ic<vector.length; ic++)
//        {
//            str += String.format(",    %f", vector[ic]);
//        }        
//        
//        System.out.println(str);
    }
    
	//************************************************************************//
	/**
	 *	ポップアップを取得
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
    private void calc_rotate_A(
        double[] camera_direction,
        double[] rot_axis_a
    )
    {
        ///// 角度を求める /////
        double thete_camera = 180.0/PI*Mathmatic.calc_angle_begween_vector_vector(m_camera_direction_def, camera_direction);
        if(thete_camera > 90.0)
        {
            //EyeUp軸回りを180度回転する軸を用意
            double rot_axis_eyeup_180[] = {m_eyeup_direction_def[0], m_eyeup_direction_def[1], m_eyeup_direction_def[2], 180.0};

            //逆転したcamera方向とデフォルトcamera方向を足し合わせて新しい回転軸を作成
            double rot_axis_two_camera[] = new double[3];
            Mathmatic.calc_vector_minus_vector(m_camera_direction_def, camera_direction, rot_axis_two_camera);
            Mathmatic.calc_vector_unit(rot_axis_two_camera, 3, rot_axis_two_camera);
            double rot_axis_buf[] = {rot_axis_two_camera[0], rot_axis_two_camera[1], rot_axis_two_camera[2], 180.0};

            //回転軸を合成する
            Mathmatic.calc_rotation_unite(rot_axis_eyeup_180, rot_axis_buf, rot_axis_a);

//            System.out.println(String.format("■■■■■■■90以上■■■■■■■: %f度,  %f軸の長さ", thete_camera, dvalue));

        }
        else
        {
            //逆転したcamera方向とデフォルトcamera方向を足し合わせて新しい回転軸を作成
            double rot_axis_two_camera[] = new double[3];
            Mathmatic.calc_vector_plus_vector(m_camera_direction_def, camera_direction, rot_axis_two_camera);
//            double dvalue = Mathmatic.calc_length_vector(3, rot_axis_two_camera);

            Mathmatic.calc_vector_unit(rot_axis_two_camera, 3, rot_axis_two_camera);
            rot_axis_a[0] = rot_axis_two_camera[0];
            rot_axis_a[1] = rot_axis_two_camera[1];
            rot_axis_a[2] = rot_axis_two_camera[2];
            rot_axis_a[3] = 180.0;

//            System.out.println(String.format("■■■■■■■通常■■■■■■■: %f度,  %f軸の長さ", thete_camera, dvalue));
        }
        
//        //回転角度を逆転させる。
//        rot_axis_a[3] *= -1.0;
        
        return;
    }
    
    
    private void calc_rotate_B(
        double[] up_direction,  //I
        double[] rot_axis_a,    //I
        double[] rot_axis_b     //O
    )
    {
        //回転軸Aで回転したときのEyeUp_bufを求める。
        double eyeup_dir_buf[] = new double[3];
        Mathmatic.calc_rotation(
            rot_axis_a,     //I		回転軸と回転量
            up_direction,	//I
            eyeup_dir_buf   //O
        );
        Mathmatic.calc_vector_unit(eyeup_dir_buf, 3, eyeup_dir_buf);

        //EyeUpのデフォルト向きとEyeUp_bufの内角を求める。
        double thete_eyeup_radian = Mathmatic.calc_angle_begween_vector_vector(m_eyeup_direction_def, eyeup_dir_buf);
        double thete_eyeup_degree = 180.0/PI*thete_eyeup_radian;
        double threshold_zero = 1e-3;
        if(Math.abs(thete_eyeup_degree - 180.0) > threshold_zero )
        {
            double outer_product[] = new double[3];
            Mathmatic.calc_OuterProduct_unit(m_eyeup_direction_def, eyeup_dir_buf, outer_product);
            double inner_product = Mathmatic.calc_inner_product(3, outer_product, m_camera_direction_def);
            if(inner_product > 0.0)
            {
                thete_eyeup_degree *= -1;
            }
        }            

        //回転軸Bを算出。EyeUpを指定向きにする回転軸。
        rot_axis_b[0] = m_camera_direction_def[0];
        rot_axis_b[1] = m_camera_direction_def[1];
        rot_axis_b[2] = m_camera_direction_def[2];
        rot_axis_b[3] = thete_eyeup_degree;
    }
    
}

