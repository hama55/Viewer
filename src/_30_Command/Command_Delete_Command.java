/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _30_Command;

import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.Entity_Imp;
import javafx.scene.input.MouseEvent;
import utility.util;

/*******************************************************************************
 * 
 * 削除する。
 * 実際の動きは以下。
 * 
 * コマンドツリーが以下だとする。
 * ・座標系作成１
 * ・放物線
 * ・平行6面体
 * ・球
 * 
 * ここで放物線を削除するとコマンドツリーは以下になる。
 * ・座標系作成１
 * ・削除マーク　※放物線と入れ替え
 * ・平行6面体
 * ・球
 * ・削除（削除は、放物線と削除マークを持っている。アンドゥ対応のため。）
 * 
 * @author 真也
 */
//******************************************************************************
public class Command_Delete_Command
	implements
//		TreeItem_Imp,	//デバッグ
		Entity_Imp,
		Command_Imp
{
	Command_Imp	m_command = null;
	Command_Imp m_command_delete_mark = null;
	private String m_command_string = static_default_command_string;
	private String m_name = "削除";
	
	private static String static_default_command_string = "";
//		+ "<Change_AxisType>"					+ util.string_get_newline()
//		+ "	<Name>座標軸のタイプを変更</Name>"	+ util.string_get_newline()
//		+ "	<cs_def_id>1</cs_def_id>"			+ util.string_get_newline()
//		+ "</Change_AxisType>";

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Command_Delete_Command(Command_Imp cmd)
	{
		this.m_command = cmd;
	}

	//************************************************************************//
	/**
	*	setter
	*/
	//************************************************************************//
	public void set_command_default_string(String str)	{this.static_default_command_string = str;}
//	/***************************************************************************
//	 * 
//	 * [TreeItemクラスのインプリメントデータ・関数群]
//	 * @param is_open
//	 * @return 
//	 */
//	//**************************************************************************
//	TreeItem_Imp_Data m_treeitem_imp_data = new TreeItem_Imp_Data();
//	@Override
//	public TreeItem ask_JavafxTreeItem(boolean is_open) {
//		return this.m_treeitem_imp_data.create_JavafxTreeItem(is_open, this);
//	}
//	@Override
//	public ContextMenu getPopupMenu(){
//		return m_treeitem_imp_data.create_PopupMenu(0, this);
//	}
//	@Override
//	public String getTreeItemName(){return this.get_name();}

	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override	public String get_command_string() {return this.m_command_string;}	
	@Override	public void set_command_string(String str){this.m_command_string = str;}
	@Override	public Command_Imp	factory() {return new Command_Delete_Command(null);}
	@Override
	public String execute(MouseEvent mouse_event)
	{
		System.out.println(util.debug_ask_class_method_name());
		
		//削除のマークを作成
		this.m_command_delete_mark = new Command_Delete_Command_Mark(this);
		
		//置換する
		Main.get_model_base().replace_command(
			this.m_command,				//置換元
			this.m_command_delete_mark	//置換先
		);
		
		//登録
		Main.get_model_base().add_command(this);
		
		return "削除されました";
	}
	@Override
	public void undo()
	{
		//置換する。
		Main.get_model_base().replace_command(
			this.m_command_delete_mark,	//置換元
			this.m_command				//置換先
		);
	}
	@Override
	public void redo()
	{
		//置換する。
		Main.get_model_base().replace_command(
			this.m_command,				//置換元
			this.m_command_delete_mark	//置換先
		);
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

