package _23_Object_Iroiro;

import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.TreeItem_Imp;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;
//import mathmatic.Mathmatic;
import _20_Object_Template.Entity_Imp;
//import _21_Object_Value_etc.AxisType;
import _40_Value.Value_Double;
import _23_Object_Iroiro.Object_10_ReImAxisDefine;
import _40_Value.Value_DoubleComplex;
import _41_Mathatic.Mathmatic;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;


/////////////////////////////////////////////////////////////////////////////
/**
 * 点オブジェクト
 * 
 * @author 真也
 */
/////////////////////////////////////////////////////////////////////////////
public class Object_03_Point
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
	private String m_name;
	private Node m_javafxnode = null;
	private boolean m_update_on = true;
	double[]			m_dNormal   = {0.0, 0.0, 0.0};
	Boolean				m_bUpdateNormal = true;
	ArrayList<Object_05_Triangle>	m_arryRelatedTri = new ArrayList<>();
	
	//点はx,y,z座標だけとはかぎらない。複素数、4次元などにも対応するように変更。
	Map<Object_10_ReImAxisDefine.AxisType, Value_Double> m_map_position = new HashMap<>();

	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_03_Point()
	{
	}	
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_03_Point(double... position)
	{
		//各座標系軸を取得して格納。
		Object_10_ReImAxisDefine.AxisType[] main_type = ask_current_axis_type();
		for(int ic=0; ic<3; ic++){
			m_map_position.put(main_type[ic], new Value_Double(position[ic]));
		}
	}
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_03_Point(
		String name,
		double ... position)
	{
		this.m_name = name;
		
		//各座標系軸を取得して格納。
		Object_10_ReImAxisDefine.AxisType[] main_type = ask_current_axis_type();
		for(int ic=0; ic<3; ic++)
		{
			m_map_position.put(main_type[ic], new Value_Double(position[ic]));
		}
	}
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_03_Point(
		Object_10_ReImAxisDefine.AxisType cst[],
		double ... position)
	{		
		//各座標系軸を取得して格納。
		for(int ic=0; ic<cst.length; ic++)
		{
			m_map_position.put(cst[ic], new Value_Double(position[ic]));
		}
	}
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_03_Point(
		String name,
		Object_10_ReImAxisDefine.AxisType cst[],
		double ... position)
	{
		this.m_name = name;
		
		//各座標系軸を取得して格納。
		for(int ic=0; ic<cst.length; ic++)
		{
			m_map_position.put(cst[ic], new Value_Double(position[ic]));
		}
	}
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_03_Point(
		String name,
		Value_DoubleComplex value_x,
		Value_DoubleComplex value_y,
		Value_DoubleComplex value_z
	)
	{
		this.m_name = name;
		
		//各座標系軸を取得して格納。
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.X_REAL, new Value_Double(value_x.get_re()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.X_IMAGINARY, new Value_Double(value_x.get_im()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Y_REAL, new Value_Double(value_y.get_re()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Y_IMAGINARY, new Value_Double(value_y.get_im()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Z_REAL, new Value_Double(value_z.get_re()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Z_IMAGINARY, new Value_Double(value_z.get_im()));
	}
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_03_Point(
		String name,
		ArrayList<Value_DoubleComplex> value
	)
	{
		Value_DoubleComplex value_x = value.get(0);
		Value_DoubleComplex value_y = value.get(1);
		Value_DoubleComplex value_z = value.get(2);

		this.m_name = name;
		
		//各座標系軸を取得して格納。
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.X_REAL, new Value_Double(value_x.get_re()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.X_IMAGINARY, new Value_Double(value_x.get_im()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Y_REAL, new Value_Double(value_y.get_re()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Y_IMAGINARY, new Value_Double(value_y.get_im()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Z_REAL, new Value_Double(value_z.get_re()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Z_IMAGINARY, new Value_Double(value_z.get_im()));
	}
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_03_Point(
		String name,
		Value_DoubleComplex[] value
	)
	{
		Value_DoubleComplex value_x = value[0];
		Value_DoubleComplex value_y = value[1];
		Value_DoubleComplex value_z = value[2];

		this.m_name = name;
		
		//各座標系軸を取得して格納。
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.X_REAL, new Value_Double(value_x.get_re()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.X_IMAGINARY, new Value_Double(value_x.get_im()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Y_REAL, new Value_Double(value_y.get_re()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Y_IMAGINARY, new Value_Double(value_y.get_im()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Z_REAL, new Value_Double(value_z.get_re()));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Z_IMAGINARY, new Value_Double(value_z.get_im()));
	}

	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_03_Point(
		String name,
		double[] value_x,
		double[] value_y,
		double[] value_z
	)
	{
		this.m_name = name;
		
		//各座標系軸を取得して格納。
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.X_REAL, new Value_Double(value_x[0]));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.X_IMAGINARY, new Value_Double(value_x[1]));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Y_REAL, new Value_Double(value_y[0]));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Y_IMAGINARY, new Value_Double(value_y[1]));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Z_REAL, new Value_Double(value_z[0]));
		m_map_position.put(Object_10_ReImAxisDefine.AxisType.Z_IMAGINARY, new Value_Double(value_z[1]));
	}
	//************************************************************************//
	/**
	*	現在の実虚軸のタイプを取得
	*/
	//************************************************************************//
	private Object_10_ReImAxisDefine.AxisType[] ask_current_axis_type(){
		return Main.get_model_base().get_current_re_im_axis().get_object().get_axis_type();
	}
	//************************************************************************//
	/**
	*	setter
	*/
	//************************************************************************//
//	public void set_position(double[] position)
//	{
//		//点の位置を格納
////		m_dPosition[0] = position[0];
////		m_dPosition[1] = position[1];
////		m_dPosition[2] = position[2];
//		
//		//各座標系軸を取得して格納。
//		Object_10_ReImAxisDefine.AxisType[] main_type = Main.get_model_base().get_coordinate_system().get_axis_type();
//		for(int ic=0; ic<3; ic++)
//		{
//			m_map_position.put(main_type[ic], new Value_Double(position[ic]));
//		}
//	}
	public void set_position(Object_10_ReImAxisDefine.AxisType[] cst, double[] position)
	{
		//点の位置を格納
//		m_dPosition[0] = position[0];
//		m_dPosition[1] = position[1];
//		m_dPosition[2] = position[2];
		
		//各座標系軸を取得して格納。
//		AxisType[] main_type = Main.get_model_base().get_axis_type();
		for(int ic=0; ic<3; ic++)
		{
			m_map_position.put(cst[ic], new Value_Double(position[ic]));
		}
	}
	//************************************************************************//
	/**
	*	getter
	*/
	//************************************************************************//
//	public double[] get_position(){return this.m_dPosition;}
	public double[] get_position(){return this.ask_position();}
	
	/***************************************************************************
	 * 
	 * ask
	 */
	public double[] ask_position()
	{
		double[] buf = {0.0, 0.0, 0.0};
		
		//カレント座標系の各軸を取得
		Object_10_ReImAxisDefine.AxisType[] main_type = ask_current_axis_type();

		//対応した値を取得
		for(int ic=0; ic<3; ic++){
			//対応する軸があれば入力する
			Value_Double buf_vd = this.m_map_position.get(main_type[ic]);
			if(buf_vd != null){
				buf[ic] = buf_vd.get_double();
			}
		}
		
		return buf;
	}
	
	//************************************************************************//
	/**
	*	getter copy
	*/
	//************************************************************************//
//	public void getc_position(double[] position)
//	{
//		//点の位置を取得
//		position[0] = m_dPosition[0];
//		position[1] = m_dPosition[1];
//		position[2] = m_dPosition[2];
//	}
	//************************************************************************//
	/**
	*	add
	*/
	//************************************************************************//
	public void add(Object_05_Triangle triFace){	this.m_arryRelatedTri.add(triFace);	}
	//************************************************************************//
	/**
	*	delete
	*/
	//************************************************************************//
	public void delete(Object_05_Triangle triFace)
	{
		//取り除く
		this.m_arryRelatedTri.remove(triFace);	
	}
	//************************************************************************//
	/**
	*	頂点の法線方向を求める。
	*	値は頂点の属する三角形の法線方向を平均して求める。
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void ask_normal_average(double[] normalAvrg)
	{
		//更新が必要な場合
		if(m_bUpdateNormal == true)
		{	
			double[] dNormalSum = {0.0, 0.0, 0.0};
			for(int ic=0; ic<this.m_arryRelatedTri.size(); ic++)
			{
				double[] dBuf = {0.0, 0.0, 0.0};
				this.m_arryRelatedTri.get(ic).ask_normal(dBuf);
				
				//足し合わせる
				dNormalSum[0] += dBuf[0];
				dNormalSum[1] += dBuf[1];
				dNormalSum[2] += dBuf[2];
			}
			
			//長さを1にする。
			Mathmatic.calc_vector_unit(m_dNormal, m_dNormal.length, m_dNormal);
			
			//メンバ変数に格納
			this.m_dNormal[0] = dNormalSum[0];
			this.m_dNormal[1] = dNormalSum[1];
			this.m_dNormal[2] = dNormalSum[2];
			
			//更新済み
			this.m_bUpdateNormal = false;
		}

		//格納
		normalAvrg[0] = this.m_dNormal[0];
		normalAvrg[1] = this.m_dNormal[1];
		normalAvrg[2] = this.m_dNormal[2];
	}

//	//************************************************************************//
//	/**
//	 *	読み込みデータを実体化
//	 */
//	//************************************************************************//
//	@Override
//	public void read_data
//	(
//		XmlData			xmlData,		//各オブジェクト形式のXML(例:<Group_Base>1,XXX \n 2,XXX \n ･･･ </Group_Base>)
//		FileInputOutput	dataFileIO,		//生成したオブジェクトを全て格納
//		Entity_Directory		topGroup		//Xmlオプションに"top"とあるものはTopのGroupにぶら下がる
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
//		fileIO.add(id, new Object_03_Point());
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

	//************************************************************************//
	/**
	 *	オブジェクト作成
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public Node create_JavafxNode()
	{
		//座標値を取得（座標軸も考慮）
		double[] buf_position = this.ask_position();
		
		//球を作成
		Sphere sphere = new Sphere();
		sphere.setRadius(0.5);
		sphere.getTransforms().add(new Translate(buf_position[0], buf_position[1], buf_position[2]));
		
		this.m_javafxnode = sphere;
		return sphere;
	}
	
	/***************************************************************************
	 * 
	 * [便利関数]	<br>
	 * 2点間の距離測定
	 * 
	 * @param p1
	 * @param p2
	 * @return 
	 *///***********************************************************************
	static public double calc_length_between_two_point(
		Object_03_Point p1,
		Object_03_Point p2
	)
	{
		return Mathmatic.calc_length_between_point_point(
				p1.ask_position(),
				p2.ask_position());
	}
	/***************************************************************************
	 * 
	 * [便利関数]	<br>
	 * 2つの点を指定して、等間隔の位置に新たな点を生成
	 * 
	 * 例：AとBを指定してCを求める
	 * A----B----C
	 * 
	 * @param p1
	 * @param p2
	 * @return 
	 *///***********************************************************************
	static public Object_03_Point calc_next_point_by_two_point(
		Object_03_Point p1,		//I		始点
		Object_03_Point p2		//I		終点
	)
	{
		//ベクトルを算出（2点間）
		double[] buf_vector = {0, 0, 0};
		Mathmatic.calc_vector_between_point_point(
			p1.ask_position(),
			p2.ask_position(),
			buf_vector);
		
		//始点＋ベクトル
		double[] buf_new_point = {0, 0, 0};
		Mathmatic.calc_vector_plus_vector(buf_vector, p2.ask_position(), buf_new_point);
		
		return new Object_03_Point(buf_new_point);
	}
	/***************************************************************************
	 * 
	 * [TreeItemクラスのインプリメントデータ・関数群]
	 * @param is_open
	 * @return 
	 *///***********************************************************************
	//データ
	TreeItem_Imp_Data m_treeitem_imp_data = new TreeItem_Imp_Data();
	//オーバーライド関数
	@Override
	public TreeItem ask_JavafxTreeItem(boolean is_open) {
		return this.m_treeitem_imp_data.create_JavafxTreeItem(is_open, this);
	}
	@Override
	public ContextMenu getPopupMenu(){
		return m_treeitem_imp_data.create_PopupMenu(0, this);
	}
	@Override
	public String getTreeItemName(){return this.get_name();}
	/***************************************************************************
	 * 
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override
	public Node ask_JavafxNode(){
		if(this.m_update_on == true || this.m_javafxnode == null){
			this.m_update_on = false;
			this.m_javafxnode = create_JavafxNode();
		}
		return this.m_javafxnode;
	}
	/***************************************************************************
	 * 
	 * [Entityクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public void set_name(String value) {this.m_name = value;}
	@Override	public String get_name() {return this.m_name;}
}
