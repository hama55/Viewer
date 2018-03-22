/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _30_Command;

import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.TreeItem_Imp;

import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
//import _21_Object_Value_etc.AxisType;
import java.util.ArrayList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import utility.util;
import xml.XmlData;

/*******************************************************************************
 * 
 * メイン画面の実虚軸のタイプを変える
 * 
 * 例）
 * X軸はXの実数、Y軸はXの虚数、Z軸はYの実数。
 *
 * @author 真也
 */


public class Command_Change_ReImAxisDefine
//	extends Command_Imp
	implements
		Entity_Imp,
		Command_Imp,
		TreeItem_Imp
{
	private String m_command_string = static_default_command_string;
	private String m_name = "実虚軸を変更";
	private Command_Create_ReImAxisDefine m_reim_axis_before = null;
	private Command_Create_ReImAxisDefine m_reim_axis_after = null;
	
	private static String static_default_command_string = ""
		+ "<Change_AxisType>"					+ util.string_get_newline()
		+ "	<Name>実虚軸のタイプを変更</Name>"	+ util.string_get_newline()
		+ "	<cs_def_id>1</cs_def_id>"			+ util.string_get_newline()
		+ "</Change_AxisType>";

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	public Command_Change_ReImAxisDefine(	)
	{
	}

	//************************************************************************//
	/**
	*	setter
	*/
	public void set_command_default_string(String str)	{this.static_default_command_string = str;}

	/***************************************************************************
	 * 
	 * 
	 * 
	 */
	private void change_axis_type(XmlData xd)
	{
		///////////////////////////////////
		//
		//	値を取得
		//
		///////////////////////////////////
		ArrayList<String[]> arry_string;
		
		//idを取得
		arry_string = Command_Imp.read_command_string(xd, "Name");
		String name = arry_string.get(0)[0];
			
		//座標系定義のid
		arry_string = Command_Imp.read_command_string(xd, "cs_def_id");
		String cs_def_id = arry_string.get(0)[0];
//		Object_10_CoordinateSystemDefine.AxisType cst_x = Object_10_CoordinateSystemDefine.ask_id(x_axis);
		int buf_id = Integer.parseInt(cs_def_id);

		
		//idの検索
		this.m_reim_axis_after = Main.get_model_base().search_command_coordinate_def_by_id(buf_id);
		
		String error_message = "";
		if(this.m_reim_axis_after == null){
			error_message += "指定の実虚軸はありません" ; 
			return;
		}
		
		//カレントを取得　※アンドゥ用
		m_reim_axis_before = Main.get_model_base().get_current_re_im_axis();
		
		//カレントを変更
		Main.get_model_base().set_current_re_im_axis(this.m_reim_axis_after);
//		Main.get_model_base().get_coordinate_system_current().set_current_object(cs_def, ObserverPass_Imp.UpdateType.etc);

//		//新たにObject_CoordinateSystemを作る。
//		Object_10_CoordinateSystemDefine coord = new Object_10_CoordinateSystemDefine(name);
//		Object_10_CoordinateSystemDefine.AxisType[] cst_xyz = {cst_x, cst_y, cst_z};
//		coord.set_axis_type(cst_xyz);
		
//		Main.get_model_base().set_axis_type(cst_xyz);
//		Main.get_model_base().get_coordinate_system_current().set_current_object(coord, ObserverPass_Imp.UpdateType.CoordinateSystemDefine_Change);
	}
	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override public void			set_command_string(String str){this.m_command_string = str;}
	@Override public String			get_command_string() {return this.m_command_string;}
	@Override public Command_Imp	factory() {return new Command_Change_ReImAxisDefine();}
	@Override
	public String execute(MouseEvent mouse_event)
	{
		System.out.println(util.debug_ask_class_method_name());

		//XmlDataに変換
		XmlData xd = Command_Imp.ask_xml(this.m_command_string);

		//連続作成
		Command_Imp.read_command_tag(xd, "Change_AxisType").stream()
			.forEach(buf -> this.change_axis_type(buf));
		
		//登録
		Main.get_model_base().add_command(this);

		return "実虚軸のタイプが変更されました。";
	}
	@Override
	public void undo(){
		//カレントを変更
		Main.get_model_base().set_current_re_im_axis(this.m_reim_axis_before);
	}
	@Override
	public void redo(){
		//カレントを変更
		Main.get_model_base().set_current_re_im_axis(this.m_reim_axis_after);
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
	@Override	public void		set_name(String value) {this.m_name = value;}
	@Override	public String	get_name() {return this.m_name;}
}