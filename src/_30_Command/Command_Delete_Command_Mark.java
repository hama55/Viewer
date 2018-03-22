/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _30_Command;

import _20_Object_Template.Entity_Imp;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author 真也
 */
public class Command_Delete_Command_Mark
		implements
//		TreeItem_Imp,	//デバッグ
		Entity_Imp,
		Command_Imp
{
	private Command_Delete_Command m_delete_parent = null;
	
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Command_Delete_Command_Mark(Command_Delete_Command cmd)
	{
		this.m_delete_parent = cmd;
	}

	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override public Command_Imp	factory() {return new Command_Delete_Command_Mark(null);}
	@Override
	public String get_command_string() {return null;}	
	@Override
	public void set_command_string(String str){}
	@Override
	public String execute(MouseEvent mouse_event){	return "";}
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

	/***************************************************************************
	 * 
	 * [Entityクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override
	public void set_name(String value) {}
	@Override
	public String get_name() {return  "削除マーク";}
}
