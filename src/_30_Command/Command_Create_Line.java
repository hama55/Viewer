/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _30_Command;

import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp;
//import _20_Object_Template.Entity_Base; 
import _20_Object_Template.Entity_Imp;
import _40_Value.Value_Double;
import _20_Object_Template.TreeItem_Imp_Data;
import _23_Object_Iroiro.Object_02_Lines;
import _23_Object_Iroiro.Object_03_Point;
import _42_Utility.XmlData;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import _42_Utility.util;

/**
 *
 * @author 真也
 */
public class Command_Create_Line
//	extends Command_Imp
//	extends Entity_Base
	implements
		Entity_Imp,
		Command_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
//	private String m_name;
	Object_02_Lines	m_obj;
//	private Entity_Imp m_object;
	private String m_command_string = static_default_command_string;
	private String m_name = "Command_test";

	private static String static_default_command_string = ""
		+ "<Line>"						+ util.string_get_newline()
		+ "	<Name>Line</Name>"	+ util.string_get_newline()
//		+ ""								+ util.string_get_newline()
//		+ "	//点1"
		+ "	<point>0.000, 0.000, 0.000</point>"			+ util.string_get_newline()
		+ "	<point>100.000, 0.000, 0.000</point>"			+ util.string_get_newline()
		+ "	<point>0.000, 100.000, 0.000</point>"			+ util.string_get_newline()
//		+ "	<point>50.000, 0.000, 0.000</point>"			+ util.string_get_newline()
		+ "</Line>";
	
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	public Command_Create_Line()
	{
//		super(static_default_command_string);
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
		
		if(xd == null){
			//何もしない
			return "何も作成されません。";
		}
		
		//節点を作成
		Command_Imp.read_command_tag(xd, "Line").stream()
			.forEach(a -> this.create_Line(a));

		//Command登録
		Main.get_model_base().add_command(this);
		
		//更新
		Main.get_model_base().Update();
		
		return "Lineを実装した！キリっ！";
	}
	
	//************************************************************************//
	/**
	*	xmldataの<Point>から節点を作成する。
	*
	*	@param
	*	@return
	*	@version
	*/
	private void create_Line(
		XmlData		xd		//節点1つ作成のxmldata
	)
	{
		ArrayList<String[]> arry_string = null;
		ArrayList<double[]> arry_double = null;
		ArrayList<Value_Double[]> arry_value = new ArrayList<>();
		
		//名前を取得
		arry_string = Command_Imp.read_command_string(xd, "Name");
		String name = arry_string.get(0)[0];
		
		//点を取得
		arry_double = Command_Imp.read_command_double(xd, "point");
		for(double[] buf : arry_double)
		{
			Value_Double[] buf_dt = new Value_Double[3];
			buf_dt[0] = new Value_Double(buf[0]);
			buf_dt[1] = new Value_Double(buf[1]);
			buf_dt[2] = new Value_Double(buf[2]);
			arry_value.add(buf_dt);
		}
		
		//Object_03_Pointを作成
		ArrayList<Object_03_Point> arry_obj_point = new ArrayList<>();
		arry_value.stream()
			.forEach(obj -> arry_obj_point.add(new Object_03_Point(obj[0].get_double(), obj[1].get_double(), obj[2].get_double())));

		
		//Lineを作成
		this.m_obj = new Object_02_Lines(
			"Lineを作成",
			arry_obj_point
		);
				//格納
//		Main.get_model_base().AddObject(obj);		
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
	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override public Command_Imp	factory() {return new Command_Create_Line();}

	/***************************************************************************
	 * 
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public Node ask_JavafxNode(){return m_obj.ask_JavafxNode();}
	
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

