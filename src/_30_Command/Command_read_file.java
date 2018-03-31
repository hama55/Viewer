package _30_Command;
import _20_Object_Template.TreeItem_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import java.util.ArrayList;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

//import moredyn.Main_Frame;

import _42_Utility.util;

//import dialog.Dialog_Base;
//import dialog.Dialog_file_choose;


public class Command_read_file
//	extends Command_Imp
	implements
		Entity_Imp,
		Command_Imp,
		TreeItem_Imp
{
	private String m_command_string = null;
	private String m_name = "Command_test";
//	private Main_Frame			m_mainFrame;
	private ArrayList<String>	m_arryFilePaths;
	//private Module_Base			m_module;			//ファイル保存先のモジュール
	
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Command_read_file(
//		Main_Frame			mainFrame,
		ArrayList<String>	arryFilePaths
	)
	{
//		super("");
		try
		{
			System.out.println(util.debug_ask_class_method_name());
	
//			this.m_mainFrame		= mainFrame;
			this.m_arryFilePaths	= arryFilePaths;
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
	
			//ファイルパス指定が無い場合、ダイアログから選択
			if(	this.m_arryFilePaths == null	||
				this.m_arryFilePaths.size() == 0	)
			{
				//ファイルを選択するダイアログを作成
//				Dialog_file_choose dlg = new Dialog_file_choose(
//					this.m_mainFrame,
//					true,
//					"開く"
//				);
				//ダイアログを開く
//				dlg.Show();
				
//				ArrayList<String> arryFiles = dlg.get_arryFilePaths();
				
//				if(arryFiles == null)
//				{
//					//ファイルは選ばれていない。
//					return;
//				}
				
				//ファイルを渡す
//				this.m_mainFrame.file_read(arryFiles.get(0));
				return "正常終了";
			}
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
	@Override public Command_Imp	factory() {return new Command_read_file(null);}

	
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
