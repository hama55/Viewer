package _23_Object_Iroiro;

import _20_Object_Template.TreeItem_Imp;
import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;

import _40_Value.Value_DoubleComplex;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import java.util.ArrayList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.text.Text;

public class Object_10_ReImAxisDefine
//    extends
//		Entity_Base
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
	private String m_name;
	private Node m_javafxnode = null;
	private boolean m_update_on = true;

//	@Override
//	public void observer_receive_update(Entity_Base obj, UpdateType update_type) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
	////////////////////////////////////////////////////////////////////////
	/**
	 * 各軸のタイプ
	 * 
	 *//////////////////////////////////////////////////////////////////////
	public enum AxisType {
		X_REAL		(1),	//Xの実数
		X_IMAGINARY	(2),	//Xの虚数
		Y_REAL		(3),	//Yの実数
		Y_IMAGINARY	(4),	//Yの虚数
		Z_REAL		(5),	//Zの実数
		Z_IMAGINARY	(6);	//Zの虚数

		private final int id;
		private AxisType(final int id) {this.id = id;}
	}
	////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 * 文字列からタイプを検索
	 * 
	 * @param str
	 * @return 
	 *//////////////////////////////////////////////////////////////////////
	public static AxisType ask_id(String str)
	{
		for(AxisType cst : AxisType.values()){
			if(str.contains(cst.name()) == true){
				return cst;
			}
		}
		
		return null;
	}
	public static AxisType[] ask_id(String ... strs)
	{
		AxisType[] buf = new AxisType[strs.length];
		
		for(int ic=0; ic<strs.length; ic++){
			buf[ic] = ask_id(strs[ic]);
		}
		
		return buf;
	}
	//************************************************************************//
	/**
	*	ローカル変数
	*/
	//************************************************************************//
	private AxisType[] m_coordinate_system_type = {		//メイン画面の各軸のタイプ
		AxisType.X_REAL,
		AxisType.Y_REAL,
		AxisType.Z_REAL
//		AxisType.X_REAL,
//		AxisType.X_IMAGINARY,
//		AxisType.Y_REAL
	};
	
//	private ArrayList<Object_DoubleComplex> m_axis_x_vector = new ArrayList<>{
////		add(new Object_DoubleComples(1,1))
//		set
//	}};
//	((1,0); (1,0); (1,0););
//		{{1.0, 0.0}; {1.0, 0.0}; {1.0, 0.0};};
	private ArrayList<Value_DoubleComplex> m_origin = new ArrayList<>(3);
	private ArrayList<Value_DoubleComplex> m_axis_x_vector = new ArrayList<>(3);
	private ArrayList<Value_DoubleComplex> m_axis_y_vector = new ArrayList<>(3);
	private ArrayList<Value_DoubleComplex> m_axis_z_vector = new ArrayList<>(3);

	//************************************************************************//
	/**
	*	Constructor
	*/
	//************************************************************************//
    public Object_10_ReImAxisDefine(String name) {
		this.m_name = name;

    }

	//************************************************************************//
	/**
	*	表示用オブジェクト
	*/
	//************************************************************************//
    public Node create_JavafxNode()
	{
//		super.setM_update_on(false);
		
		double d_box_length = 100.0;
		double d_box_width  = 10.0;
		double d_translate  = (d_box_length + d_box_width)*0.5;
		
		//X軸
		Group group_x_axis = new Group();
        Box box_x_axis = new Box(d_box_length, d_box_width, d_box_width);
        PhongMaterial material_red = new PhongMaterial();
        material_red.setDiffuseColor(Color.RED);
        box_x_axis.setMaterial(material_red);
        box_x_axis.getTransforms().add(new Translate(d_translate, 0, 0));
        box_x_axis.setDepthTest(DepthTest.ENABLE);
		Text text_x = new Text(m_coordinate_system_type[0].toString());
		text_x.getTransforms().add(new Translate(d_box_length, 0, 0));
		group_x_axis.getChildren().add(box_x_axis);
		group_x_axis.getChildren().add(text_x);
        
        //Y軸
 		Group group_y_axis = new Group();
        Box box_y_axis = new Box(d_box_width, d_box_length, d_box_width);
        PhongMaterial material_green = new PhongMaterial();
        material_green.setDiffuseColor(Color.GREEN);
        box_y_axis.setMaterial(material_green);
        box_y_axis.getTransforms().add(new Translate(0.0, d_translate, 0.0));
        box_y_axis.setDepthTest(DepthTest.ENABLE);
		Text text_y = new Text(m_coordinate_system_type[1].toString());
		text_y.getTransforms().add(new Translate(0, d_box_length, 0));
		group_y_axis.getChildren().add(box_y_axis);
		group_x_axis.getChildren().add(text_y);
        
        //Z軸
		Group group_z_axis = new Group();
         Box box_z_axis = new Box(d_box_width, d_box_width, d_box_length);
        PhongMaterial material_blue = new PhongMaterial();
        material_blue.setDiffuseColor(Color.BLUE);
        box_z_axis.setMaterial(material_blue);
        box_z_axis.getTransforms().add(new Translate(0.0, 0.0, d_translate));
        box_z_axis.setDepthTest(DepthTest.ENABLE);
		Text text_z = new Text(m_coordinate_system_type[2].toString());
		text_z.getTransforms().add(new Translate(0, 0, d_box_length));
		group_z_axis.getChildren().add(box_z_axis);
		group_x_axis.getChildren().add(text_z);
        

        Group test_group = new Group();
        test_group.getChildren().addAll(group_x_axis, group_y_axis, group_z_axis);
        test_group.setDepthTest(DepthTest.ENABLE);
        
//        box_buf.setDrawMode(DrawMode.LINE);
		

		
		//テスト：ピックの設定		
		test_group.setOnMouseEntered(me -> {
			System.out.println(get_name());
//			super.getM_tree_item().
		});
		
		this.m_javafxnode = test_group;
        return test_group;
    }
	////////////////////////////////////////////////////////////////////////////
	/** 
	 * setter
	 * 
	 *//////////////////////////////////////////////////////////////////////////
	public void set_axis_type(AxisType[] cst)
	{
		for(AxisType buf_cst : cst)	{
			if(buf_cst == null)	{
				return;
			}
		}
		
		this.m_coordinate_system_type = cst;
	}
	////////////////////////////////////////////////////////////////////////////
	/** 
	 * getter
	 * 
	 *//////////////////////////////////////////////////////////////////////////
	public AxisType[] get_axis_type()	{return this.m_coordinate_system_type;}

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
