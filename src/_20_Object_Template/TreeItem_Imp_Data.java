/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _20_Object_Template;

import _00_GUI_Main_JavaFX.Main;
import _01_GUI_Dialog_JavaFX.EditDialogControler;
import _30_Command.Command_Delete_Command;
import _30_Command.Command_Edit;
import _30_Command.Command_Imp;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import utility.util;

/**
 *
 * @author 真也
 */
public class TreeItem_Imp_Data {
	
	//************************************************************************//
	/**
	 * Treeのアイテムを作る。
	 *
	 * @param
	 * @return
	 * @version
	 */
	//************************************************************************//
//	@Override
	@Deprecated
	public TreeItem create_JavafxTreeItem(boolean is_open, String name)
	{
		return new TreeItem(name);

	}
	public TreeItem<TreeItem_Imp> create_JavafxTreeItem(boolean is_open, TreeItem_Imp item)
	{
		return new TreeItem(item);
		
//		//TreeObjectをimplementしているなら表示する。
//		if(this instanceof TreeItem_Imp){
//			return ((TreeItem_Imp)(this)).create_JavafxTreeItem(is_open);
//		}
//		
//		return null;

		
//		if(this.m_tree_item == null){
//			//新規作成
//			this.m_tree_item = new TreeItem<>(this);
//			//各Objectのツリーを作る
//			ask_JavafxTreeItem_children(is_open, this.m_tree_item);
//			//ツリーを開いたときのイベントハンドラーを設定
//			this.m_tree_item.addEventHandler(
//				TreeItem.<File>branchExpandedEvent(),
//				event -> {ask_JavafxTreeItem_event();}
//			);
//		}
//		else if(this.m_update_on == true){
//			//更新
//			ask_JavafxTreeItem_children(is_open, this.m_tree_item);
//		}
		
//		return this.m_tree_item;
	}
	public ContextMenu create_PopupMenu(int n_type, TreeItem_Imp t_item)
	{
		try{
			System.out.println(util.debug_ask_class_method_name());
			
			if(t_item == null) return null;
			
			ContextMenu popup = new ContextMenu();

			//////////////////////////
			//	編集
			//////////////////////////
			if(t_item instanceof Command_Imp){
				Command_Imp buf_cmd = (Command_Imp)t_item;
				MenuItem menuItem_edit = new MenuItem("編集");
					//実行内容
					menuItem_edit.setOnAction((ActionEvent event) -> {
						//編集コマンドの作成
						Command_Edit cmd_edit = new Command_Edit(buf_cmd);
//						//登録
//						Main.get_model_base().add_command(cmd_edit);
//						//実行
//						cmd_edit.execute(null);
//						//更新
//						Main.get_model_base().Update();
	//					//編集ダイアログを生成
	//					EditDialogControler buf_Edit = EditDialogControler.factory();
	//					//編集するコマンドを設定
	//					buf_Edit.set_command(buf_cmd);
	//					//ダイアログ起動
	//					buf_Edit.start(Main.get_primary_stage());
		
						//編集ダイアログを生成
						EditDialogControler buf_Edit = EditDialogControler.factory();
						//編集するコマンドを設定
						buf_Edit.set_command(cmd_edit);
						//ダイアログ起動
						buf_Edit.start(Main.get_primary_stage());
					});
				popup.getItems().add(menuItem_edit);
			}

			//////////////////////////
			//	削除
			//////////////////////////
			if(t_item instanceof Command_Imp){
				MenuItem menuItem_delete = new MenuItem("削除");
				//実行内容
				menuItem_delete.setOnAction((ActionEvent event) -> {
					//削除コマンドの作成
					Command_Delete_Command cmd_delete = new Command_Delete_Command((Command_Imp)t_item);
					//登録
					Main.get_model_base().add_command(cmd_delete);
					//実行
					cmd_delete.execute(null);
					//更新
					Main.get_model_base().Update();
				});
				popup.getItems().add(menuItem_delete);
			}


			return popup;	}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}

		return null;
	}
	/***************************************************************************
	 * 
	 * [TreeItemクラスのインプリメント関数群]
	 * @param is_open
	 * @return 
	 */
	//**************************************************************************
//	public TreeItem create_JavafxTreeItem(
//		boolean is_open,
//		String name,
//		Command_TreePopup ...cmds)
//	{
//		return new TreeItem(name);
//	}	

//	/***************************************************************************
//	 * 
//	 * [TreeItemクラスのインプリメント関数群]
//	 * @param is_open
//	 * @return 
//	 */
//	//**************************************************************************
//	@Override
//	public TreeItem ask_JavafxTreeItem(boolean is_open) {
//		return this.m_treeitem_imp_data.create_JavafxTreeItem(is_open, this);
//	}
}
