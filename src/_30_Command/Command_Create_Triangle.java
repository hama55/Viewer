/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _30_Command;

import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp;
import _20_Object_Template.Entity_Imp;
import _40_Value.Value_Double;
import _22_Object_Primitive.Object_03_Point;
import _22_Object_Primitive.Object_05_Triangle;
import _20_Object_Template.TreeItem_Imp_Data;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import utility.util;
import xml.XmlData;

/**
 *
 * @author 真也
 */
public class Command_Create_Triangle
//	extends Command_Imp
	implements
		Entity_Imp,
		Command_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
	private Object_05_Triangle m_object;
	private String m_command_string = static_default_command_string;
	private String m_name = "三角形の作成";

	private static String static_default_command_string = ""
		+ "<Triangle>"						+ util.string_get_newline()
		+ "	<Name>三角形</Name>"	+ util.string_get_newline()
//		+ ""								+ util.string_get_newline()
//		+ "	//点1"
		+ "	<point_1>0.000, 0.000, 0.000</point_1>"			+ util.string_get_newline()
//		+ ""								+ util.string_get_newline()
//		+ "	//点2"
		+ "	<point_2>20.000, 0.000, 0.000</point_2>"			+ util.string_get_newline()
//		+ ""								+ util.string_get_newline()
//		+ "	//点3"
		+ "	<point_3>0.000, 20.000, 0.000</point_3>"			+ util.string_get_newline()
		+ "</Triangle>";
	
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	public Command_Create_Triangle()
	{
//		super(static_default_command_string);
//		this.m_name = static_default_command_string;
	}


	/***************************************************************************
	 * 
	 * @param e
	 * @return 
	 */
	@Override
	public String execute(MouseEvent e)
	{
		XmlData xd = Command_Imp.ask_xml(this.m_command_string);
		
		if(xd == null)
		{
			//何もしない
			return "何も作成されません。";
		}
		
		//節点を作成
		Command_Imp.read_command_tag(xd, "Triangle").stream()
			.forEach(a -> this.create_triangle(a));

		return "三角形が作成されました。";
	}
	
	//************************************************************************//
	/**
	*	xmldataの<Triangle>から節点を作成する。
	*
	*	@param
	*	@return
	*	@version
	*/
	private void create_triangle(
		XmlData		xd		//節点1つ作成のxmldata
	)
	{
		ArrayList<String[]> arry_string = null;
		ArrayList<double[]> arry_double = null;
		ArrayList<Value_Double>	arry_values = new ArrayList<>();
		
		//名前を取得
		arry_string = Command_Imp.read_command_string(xd, "Name");
		String name = arry_string.get(0)[0];
		
		//点1を取得
		arry_double = Command_Imp.read_command_double(xd, "point_1");
		Object_03_Point point1 = new Object_03_Point(arry_double.get(0));
		
		//点2を取得
		arry_double = Command_Imp.read_command_double(xd, "point_2");
		Object_03_Point point2 = new Object_03_Point(arry_double.get(0));
		
		//点3を取得
		arry_double = Command_Imp.read_command_double(xd, "point_3");
		Object_03_Point point3 = new Object_03_Point(arry_double.get(0));
		
		//三角形を作成
		m_object = new Object_05_Triangle(
			name,
			point1,
			point2,
			point3
		);

		//格納
//		Main.get_model_base().AddObject(obj);
		Main.get_model_base().add_command(this);
	}
	/***************************************************************************
	 * 
	 * setter
	 * 
	 * @param  
	 */
	public void set_command_string(String str)
	{
		this.m_command_string = str;
	}
	
	/***************************************************************************
	 * 
	 * getter
	 * 
	 * @return 
	 */
	public String get_command_string() {return this.m_command_string;}
//	//************************************************************************//
//	/**
//	*	3Dオブジェクトを返す。
//	*
//	*	@param
//	*	@return
//	*	@version
//	***************************************************************************/
//	public Entity_Imp get_display_3D_object()
//	{
//		return this.m_object;
//	}
	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override public Command_Imp	factory() {return new Command_Create_Triangle();}
	/***************************************************************************
	 * 
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public Node ask_JavafxNode(){return this.m_object.ask_JavafxNode();}
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

