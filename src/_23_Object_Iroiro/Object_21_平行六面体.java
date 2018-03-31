package _23_Object_Iroiro;

import _20_Object_Template.TreeItem_Imp;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;

//import javax.media.opengl.GL;

import _42_Utility.util;

import _12_Base_Color_etc.DrawPen;
import _12_Base_Color_etc.Material;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _40_Value.Value_Double;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;

public class Object_21_平行六面体
//	extends Entity_Base
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
	float nSize = 30;
	private String m_name;
	private Node m_javafxnode = null;
	private boolean m_update_on = true;
//	
//	private Value_Double[]	m_origin;
//	private Value_Double[]	m_axisX;
//	private Value_Double[]	m_axisY;
//	private Value_Double[]	m_axisZ;
//	private Value_Double[]	m_length;
//	private Object_03_Point[]	m_vertex_points = new Object_03_Point[8];
//	private Object_01_Face[]	m_face;// = new Object_01_Face[6];
	
	private ArrayList<Value_Double>	m_origin = new ArrayList<>(3);
	private ArrayList<Value_Double>	m_axisX = new ArrayList<>(3);
	private ArrayList<Value_Double>	m_axisY = new ArrayList<>(3);
	private ArrayList<Value_Double>	m_axisZ = new ArrayList<>(3);
	private ArrayList<Value_Double>	m_length = new ArrayList<>(3);
	private ArrayList<Object_03_Point>	m_vertex_points = new ArrayList<>(8);
	private ArrayList<Object_01_Face>	m_face = new ArrayList<>(6);
	
	

	//************************************************************************//
	/**
	 *	コンストラクタ
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public Object_21_平行六面体()
	{
//		super(null);
	}

	public Object_21_平行六面体(
		String				name,		//名前
		ArrayList<Value_Double>	arryDoubleT	//平行6面体を作成する基本データ
	)
	{
		this.m_name = name;
		try
		{
			System.out.println(util.debug_ask_class_method_name());
			
			/////////////////////////////////////////
			//	テーブルから値を取得
			//
			//	  項目,		     X,    Y,    Z 
			//	-----------------------------------
			//	{"原点",     	"0",  "0",  "0"  }
			//	{"軸X",			"1",  "0",  "0"  }
			//	{"軸Y",			"0",  "1",  "0"  }
			//	{"軸Z",			"0",  "0",  "1"  }
			//	{"各軸の長さ",		"100","100","100"}
			//
			/////////////////////////////////////////
			
			this.m_origin.addAll(arryDoubleT.subList(0, 3));
			this.m_axisX.addAll(arryDoubleT.subList(3, 6));
			this.m_axisY.addAll(arryDoubleT.subList(6, 9));
			this.m_axisZ.addAll(arryDoubleT.subList(9, 12));
			this.m_length.addAll(arryDoubleT.subList(12, 15));
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//
	/**
	 *	描画
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public Node create_JavafxNode() 
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
			
//			PhongMaterial material_red = new PhongMaterial();
//			material_red.setDiffuseColor(Color.RED);

			///////////////////////////////////////
			//	平行6面体を作成
			///////////////////////////////////////
			Group javafx_group = this.make_cuboid(null, null);
			
			return javafx_group;	
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
		
		return null;
	}

	//************************************************************************//
	/**
	*	
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	private	Group make_cuboid(
		Material	material, 
		DrawPen		drawPen
	)
	{
		////////////////////////////////
		//	平行6面体の頂点8つを算出
		////////////////////////////////
		double[] dPoint0 = new double[3];
		double[] dPoint1 = new double[3];
		double[] dPoint2 = new double[3];
		double[] dPoint3 = new double[3];
		double[] dPoint4 = new double[3];
		double[] dPoint5 = new double[3];
		double[] dPoint6 = new double[3];
		double[] dPoint7 = new double[3];
		this.calc_vertex_points(
			dPoint0,
			dPoint1,
			dPoint2,
			dPoint3,
			dPoint4,
			dPoint5,
			dPoint6,
			dPoint7
		);
		
		//Object_Pointを作成
		this.m_vertex_points.add(0, new Object_03_Point("0", dPoint0));
		this.m_vertex_points.add(1, new Object_03_Point("1", dPoint1));
		this.m_vertex_points.add(2, new Object_03_Point("2", dPoint2));
		this.m_vertex_points.add(3, new Object_03_Point("3", dPoint3));
		this.m_vertex_points.add(4, new Object_03_Point("4", dPoint4));
		this.m_vertex_points.add(5, new Object_03_Point("5", dPoint5));
		this.m_vertex_points.add(6, new Object_03_Point("6", dPoint6));
		this.m_vertex_points.add(7, new Object_03_Point("7", dPoint7));
		//////////////////////////
		//	三角形の連続作成
		//////////////////////////
		//確保
//		m_face = new Object_01_Face[6];
		
		//面0(0321)
		this.m_face.add(0, this.create_face(
			"面1",
			this.m_vertex_points.get(0),
			this.m_vertex_points.get(3),
			this.m_vertex_points.get(2),
			this.m_vertex_points.get(1)
		));

		//面1(0154)
		this.m_face.add(1, this.create_face(
			"面2",
			this.m_vertex_points.get(0),
			this.m_vertex_points.get(1),
			this.m_vertex_points.get(5),
			this.m_vertex_points.get(4)
		));

		//面2(4567)
		this.m_face.add(2, this.create_face(
			"面3",
			this.m_vertex_points.get(4),
			this.m_vertex_points.get(5),
			this.m_vertex_points.get(6),
			this.m_vertex_points.get(7)
		));

		//面3(2376)
		this.m_face.add(3, this.create_face(
			"面4",
			this.m_vertex_points.get(2),
			this.m_vertex_points.get(3),
			this.m_vertex_points.get(7),
			this.m_vertex_points.get(6)
		));

		//面4(0473)
		this.m_face.add(4, this.create_face(
			"面5",
			this.m_vertex_points.get(0),
			this.m_vertex_points.get(4),
			this.m_vertex_points.get(7),
			this.m_vertex_points.get(3)
		));

		//面5(1265)
		this.m_face.add(5, this.create_face(
			"面6",
			this.m_vertex_points.get(1),
			this.m_vertex_points.get(2),
			this.m_vertex_points.get(6),
			this.m_vertex_points.get(5)			
		));	
		
		Group group = new Group();
		List<Object_01_Face>	list_face  = m_face;
		List<Object_03_Point>	list_point = m_vertex_points;
		
		list_face.stream().forEach(a -> group.getChildren().add(a.ask_JavafxNode()));
		list_point.stream().forEach(a -> group.getChildren().add(a.ask_JavafxNode()));
		
		this.m_javafxnode = group;
		return group;
	}
	
	//************************************************************************//
	/**
	*	
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	private Object_01_Face create_face(
		String			name,	//I
		Object_03_Point	Pos0,	//I
		Object_03_Point	Pos1,	//I
		Object_03_Point	Pos2,	//I
		Object_03_Point	Pos3	//I
	)
	{
//		Object_05_Triangle tri1 = new Object_05_Triangle("三角形1", Pos1, Pos2, Pos3);
		Object_05_Triangle tri1 = new Object_05_Triangle("三角形1", Pos0, Pos1, Pos3);
		Object_05_Triangle tri2 = new Object_05_Triangle("三角形2", Pos1, Pos2, Pos3);
		
		return new Object_01_Face(name, tri1, tri2);
		
//		double[] normal = new double[3];
//		
//		///// 外積を法線ベクトルとする /////
//		Mathmatic.calc_OuterProduct_unit(dPos1, dPos2, dPos3, normal);
//	
//		//法線方向を指定
//		gl.glBegin(GL.GL_TRIANGLE_STRIP);
//		//法線方向を指定
//		gl.glNormal3dv(normal, 0);
//		//頂点を指定
//		gl.glVertex3dv(dPos1, 0);
//		gl.glVertex3dv(dPos2, 0);
//		gl.glVertex3dv(dPos3, 0);
//		gl.glVertex3dv(dPos4, 0);
//		gl.glEnd();
	}

//	//************************************************************************//
//	/**
//	 *	読み込みデータを実体化
//	 */
//	//************************************************************************//
//	@Override
//	public void read_data(
//		XmlData				xmlData,		//各オブジェクト形式のXML(例:<Group_Base>1,XXX \n 2,XXX \n ･･･ </Group_Base>)
//		FileInputOutput		dataFileIO,		//生成したオブジェクトを全て格納
//		Entity_Directory	topGroup		//Xmlオプションに"top"とあるものはTopのGroupにぶら下がる
//	)
//	{
//	}
//	//************************************************************************//
//	/**
//	 *	idと関連付けたオブジェクトをプールに登録
//	 */
//	//************************************************************************//
//	@Override
//	public void factory_and_add
//	(
//		String			id,		//保存、読み込み時のオブジェクトID
//		FileInputOutput	fileIO	//データプール
//	)
//	{
//		fileIO.add(id, new Object_21_平行六面体());
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
	*	平行6面体の頂点8つを算出
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//	
	private void calc_vertex_points(
		double[]		dPoint0,	//O		
		double[]		dPoint1,	//O		
		double[]		dPoint2,	//O		
		double[]		dPoint3,	//O		
		double[]		dPoint4,	//O		
		double[]		dPoint5,	//O		
		double[]		dPoint6,	//O		
		double[]		dPoint7		//O		
	)
	{
		double[] dAxis1L = {0.0, 0.0, 0.0};
		double[] dAxis2L = {0.0, 0.0, 0.0};
		double[] dAxis3L = {0.0, 0.0, 0.0};
	
		//あらかじめ計算によく使う値を算出
		double dLength0 = this.m_length.get(0).get_double();
		double dLength1 = this.m_length.get(1).get_double();
		double dLength2 = this.m_length.get(2).get_double();
		for(int nc=0; nc<3; nc++)
		{
			dAxis1L[nc] = this.m_axisX.get(nc).get_double() * dLength0;
			dAxis2L[nc] = this.m_axisY.get(nc).get_double() * dLength1;
			dAxis3L[nc] = this.m_axisZ.get(nc).get_double() * dLength2;
		}
		
		for(int kc=0; kc<3; kc++)
		{
			dPoint0[kc]	= this.m_origin.get(kc).get_double();
			dPoint1[kc]	= dPoint0[kc] + dAxis1L[kc];
			dPoint2[kc]	= dPoint1[kc] + dAxis2L[kc];
			dPoint3[kc]	= dPoint0[kc] + dAxis2L[kc];
			dPoint4[kc]	= dPoint0[kc] + dAxis3L[kc];
			dPoint5[kc]	= dPoint1[kc] + dAxis3L[kc];
			dPoint6[kc]	= dPoint2[kc] + dAxis3L[kc];
			dPoint7[kc]	= dPoint3[kc] + dAxis3L[kc];
		}
	}

//	@Override
//	public void observer_receive_update(Entity_Base obj, UpdateType update_type) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
//
//	@Override
//	public void ask_JavafxTreeItem_children(boolean is_open, TreeItem tree_item)
//	{
//		try
//		{
////			//名前からツリーノードを作成
////			TreeItem tree_item = new TreeItem<>(this);
//			
//			Entity_Directory.ask_JavafxTreeNode_utility_value(
//				is_open,
//				tree_item,
//				m_origin,
//				m_axisX,
//				m_axisY,
//				m_axisZ,
//				m_length);
//
//			Entity_Directory.ask_JavafxTreeNode_utility_obj(
//				is_open,
//				tree_item,
//				m_vertex_points,
//				m_face);
//			
////			return tree_item;					
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//		
////		return null;
//	}
	/***************************************************************************
	 * 
	 * [TreeItemクラスのインプリメントデータ・関数群]
	 * @param is_open
	 * @return 
	 */
	//**************************************************************************
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
	@Override
	public void set_name(String value) {this.m_name = value;}
	@Override
	public String get_name() {return this.m_name;}
}
