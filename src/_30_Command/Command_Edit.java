/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _30_Command;

import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.Entity_Imp;
import javafx.scene.input.MouseEvent;
import _42_Utility.util;

/*******************************************************************************
 * 
 * 編集する。
 * 実際の動きはCommand_Delete_Commandと同じ考え。置換を使う。
 * 
 * @author 真也
 */
//******************************************************************************
public class Command_Edit	implements
//		TreeItem_Imp,	//デバッグ
		Entity_Imp,
		Command_Imp
{
	private Command_Imp	m_command_before = null;
	private Command_Imp m_command_after = null;
	private String m_name = "編集";

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Command_Edit(Command_Imp cmd)
	{
		this.m_command_before = cmd;
		
		//編集前の命令を編集先のコマンドにコピー
		this.m_command_after = this.m_command_before.factory();
		this.m_command_after.set_command_string(this.m_command_before.get_command_string());
	}
	
	public Command_Imp get_command_after() {return this.m_command_after;}
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
	@Override	public String		get_command_string() {return this.m_command_after.get_command_string();}	
	@Override	public void			set_command_string(String str){ this.m_command_after.set_command_string(str);}
	@Override	public Command_Imp	factory() {return new Command_Edit(null);}
	@Override
	public String execute(MouseEvent mouse_event)
	{
		System.out.println(util.debug_ask_class_method_name());
		
		//編集後のオブジェクトを作成
		String message = this.m_command_after.execute(null);

		//編集後のオブジェクトを置換して、編集コマンドを登録する。
		Main.get_model_base().replace_command(
			this.m_command_after,	//置換元
			this					//置換先
		);
		
		//置換する
		Main.get_model_base().replace_command(
			this.m_command_before,	//置換元
			this.m_command_after	//置換先
		);
		
		//更新
		Main.get_model_base().Update();		
		
		return message;
	}
	@Override
	public void undo()
	{
		//置換する
		Main.get_model_base().replace_command(
			this.m_command_after,	//置換元
			this.m_command_before	//置換先
		);
	}
	@Override
	public void redo()
	{
		//置換する。
		Main.get_model_base().replace_command(
			this.m_command_before,	//置換元
			this.m_command_after	//置換先
		);
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
