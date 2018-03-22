package _30_Command;
import _20_Object_Template.TreeItem_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _31_CommandMouse.CommandMouse_Imp;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

import utility.util;

//import moredyn.Main_Frame;

public class Command_setForceMouseCommand
//	extends Command_Imp
	implements
		Entity_Imp,
		Command_Imp,
		TreeItem_Imp
{
	private String m_command_string = null;
	private String m_name = "Command_test";
	CommandMouse_Imp m_MouseCom;

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Command_setForceMouseCommand(
//		Main_Frame		mainframe, 
		CommandMouse_Imp	mousecom
	)
	{
//		super("");
		try
		{
			System.out.println(util.debug_ask_class_method_name());
//			m_mainframe = mainframe;
			m_MouseCom = mousecom;
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}
	
	//************************************************************************//
	/**
	*	コンストラクタ
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
//			this.m_mainframe.setMouseCommandForceActionFlag(true);
//			this.m_mainframe.setMouseCommandForceAction(m_MouseCom);
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
	@Override public Command_Imp	factory() {return new Command_setForceMouseCommand(null);}

	
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
