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
import _20_Object_Template.TreeItem_Imp_Data;
import _23_Object_Iroiro.Object_10_ReImAxisDefine;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import utility.util;
import xml.XmlData;

/*******************************************************************************
 * 
 * メイン画面の実虚軸のタイプを作成
 * 
 * 例）
 * X軸はXの実数、Y軸はXの虚数、Z軸はYの実数。
 *
 * @author 真也
 */


public class Command_Create_ReImAxisDefine
//	extends Command_Imp
	implements
		Entity_Imp,
		Command_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
	private Object_10_ReImAxisDefine m_object;
	private String m_command_string = static_default_command_string;
	private String m_name = "実虚軸のタイプを作成";
	private int m_id = -1;
	
	private static String static_default_command_string = ""
		+ "<Create_AxisType>"						+ util.string_get_newline()
		+ "	<Name>実虚軸のタイプを変更</Name>"		+ util.string_get_newline()
		//選べるアイテム
		+ "	<select_item>"			+ util.string_get_newline()
		+ "	X_REAL"					+ util.string_get_newline()
		+ "	X_IMAGINARY"			+ util.string_get_newline()
		+ "	Y_REAL"					+ util.string_get_newline()
		+ "	Y_IMAGINARY"			+ util.string_get_newline()
		+ "	Z_REAL"					+ util.string_get_newline()
		+ "	Z_IMAGINARY"			+ util.string_get_newline()
		+ "	</select_item>"			+ util.string_get_newline()
		+ "	<x_axis>X_REAL</x_axis>"			+ util.string_get_newline()	//X軸
		+ "	<y_axis>Y_REAL</y_axis>"		+ util.string_get_newline()		//Y軸
		+ "	<z_axis>Z_REAL</z_axis>"			+ util.string_get_newline()	//Z軸
//		+ "	<x_axis>X_REAL</x_axis>"			+ util.string_get_newline()
//		+ "	<y_axis>X_IMAGINARY</y_axis>"		+ util.string_get_newline()
//		+ "	<z_axis>Y_REAL</z_axis>"			+ util.string_get_newline()
		+ "</Create_AxisType>";

	///// idマネージ /////
	private static int manage_id_max = -1;
	private static int get_new_id() {manage_id_max++;	return manage_id_max;}
	
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	public Command_Create_ReImAxisDefine(	)
	{
	}

	//************************************************************************//
	/**
	*	setter
	*/
	public void set_command_default_string(String str)	{this.static_default_command_string = str;}
	
	//************************************************************************//
	/**
	*	getter
	*/
	//**************************************************************************
	public Object_10_ReImAxisDefine get_object()	{return this.m_object;}
	
	public int get_id()	{return m_id;}
	
	//************************************************************************//
	/**
	*	execute
	*/
	@Override
	public String execute(MouseEvent mouse_event)
	{
		System.out.println(util.debug_ask_class_method_name());

		this.m_id = get_new_id();

		//XmlDataに変換
		XmlData xd = Command_Imp.ask_xml(this.m_command_string);

		//連続作成
		Command_Imp.read_command_tag(xd, "Create_AxisType").stream()
			.forEach(buf -> this.change_axis_type(buf));
		
		return "実虚軸が作成されました。";
	}
	/***************************************************************************
	 * 
	 * setter
	 * 
	 * @param  	/***************************************************************************
	 * 
	 * setter
	 * 
	 * @param  
	 */
	
	/***************************************************************************
	 * 
	 * getter
	 * 
	 * @return 
	 */
	/***************************************************************************
	 * 
	 * 複素の2次関数を作成
	 * 
	 */
	private void change_axis_type(XmlData xd)
	{
		///////////////////////////////////
		//	値を取得
		///////////////////////////////////
		ArrayList<String[]> arry_string;
		
		//名前を取得
		arry_string = Command_Imp.read_command_string(xd, "Name");
		String name = arry_string.get(0)[0];
			
		//X軸
		arry_string = Command_Imp.read_command_string(xd, "x_axis");
		String x_axis = arry_string.get(0)[0];
		Object_10_ReImAxisDefine.AxisType cst_x = Object_10_ReImAxisDefine.ask_id(x_axis);
			
		//Y軸
		arry_string = Command_Imp.read_command_string(xd, "y_axis");
		String y_axis = arry_string.get(0)[0];
		Object_10_ReImAxisDefine.AxisType cst_y = Object_10_ReImAxisDefine.ask_id(y_axis);
			
		//Z軸
		arry_string = Command_Imp.read_command_string(xd, "z_axis");
		String z_axis = arry_string.get(0)[0];
		Object_10_ReImAxisDefine.AxisType cst_z = Object_10_ReImAxisDefine.ask_id(z_axis);
		
//		//タイプを取得
//		AxisType[] cst_xyz = AxisType.ask_id(x_axis, y_axis, z_axis);
		
		String error_message = "";
		if(cst_x == null)
		{
			error_message += "X軸を変更できません。 : " + x_axis + util.string_get_newline(); 
		}
		if(cst_y == null)
		{
			error_message += "Y軸を変更できません。 : " + y_axis + util.string_get_newline(); 
		}
		if(cst_z == null)
		{
			error_message += "Z軸を変更できません。 : " + z_axis + util.string_get_newline(); 
		}
		
//		if(error_message.length() != 0)
//		{
//			return error_message;
//		}

		//新たにObject_CoordinateSystemを作る。
		m_object = new Object_10_ReImAxisDefine(name);
		Object_10_ReImAxisDefine.AxisType[] cst_xyz = {cst_x, cst_y, cst_z};
		m_object.set_axis_type(cst_xyz);
		
		
		//格納
//		Main.get_model_base().AddObject(obj);
		Main.get_model_base().add_command(this);
		
//		Main.get_model_base().set_axis_type(cst_xyz);
//		Main.get_model_base().get_coordinate_system_current().change_current_object(m_object, ObserverPass_Imp.UpdateType.CoordinateSystem_Change);
	}
	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override public void			set_command_string(String str)	{this.m_command_string = str;}
	@Override public String			get_command_string()				{return this.m_command_string;}	
	@Override public Command_Imp	factory() {return new Command_Create_ReImAxisDefine();}

	
	/***************************************************************************
	 * 
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public Node ask_JavafxNode(){
		return this.m_object.ask_JavafxNode();
	}	
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
	@Override	public void set_name(String value) {this.m_name = value;}
	@Override	public String get_name() {return this.m_name + "(" + String.valueOf(m_id) + ")";}
}