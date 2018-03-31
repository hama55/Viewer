package _30_Command;
import _20_Object_Template.TreeItem_Imp;
import javafx.scene.input.MouseEvent;

import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;

import _42_Utility.util;

//import object.Object_Group;

//import module.Module_MorecularDynamics;
//import moredyn.PopupMenu_TreeNode;

public class Command_show_group 
//	extends Command_Imp
	implements
		Entity_Imp,
		Command_Imp,
		TreeItem_Imp
{
	private String m_command_string = null;
	private String m_name = "Command_test";

	/*
	Groupを取得
	このGroupを、Module_Post_Processorに知らせる
	Module_Post_Processorは、これを次に表示するものに登録する。
	*/

//	Module_MorecularDynamics m_module = null;
	//Group m_group = null;
//	PopupMenu_TreeNode m_popup;
	

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	//public Command_show_group(Module_Post_Processor module, Group group){
	public Command_show_group(
//		Module_MorecularDynamics	module, 
//		PopupMenu_TreeNode			popup
	)
	{
//		super("");
		try
		{
			System.out.println(util.debug_ask_class_method_name());
			
//			this.m_module = module;
//			this.m_popup = popup;
			//this.m_group = group;
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//
	/**
	*	execute
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	@Override
	public String execute(MouseEvent e)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
	
			//Groupを取得
			//Group_Base object_Group = m_popup.get_Group();
//			Group_Base object_Group = (Group_Base) m_popup.get_SelectedTreeNodeItem();
//			m_module.set_GroupShow(object_Group);
//			m_module.set_bDisplayFlag(true);
//			m_module.Redisplay();
			
			return "正常終了";
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
		return "異常終了";
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
	@Override public Command_Imp	factory() {return new Command_show_group();}

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
