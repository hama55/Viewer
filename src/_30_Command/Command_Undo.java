/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _30_Command;

import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.Entity_Imp;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import _42_Utility.util;

/*******************************************************************************
 * 
 * アンドゥする
 * 実際の動きは以下。
 * 
 * コマンドツリーが以下だとする。※削除マークと削除はユーザーは見えない。
 * ・座標系作成１
 * ・削除マーク　※放物線と入れ替え
 * ・平行6面体
 * ・球
 * ・削除（削除は、放物線と削除マークを持っている。アンドゥ対応のため。）
 * 
 * アンドゥを2回するとこうなる
 * ・座標系作成１
 * ・放物線
 * ・平行6面体
 * ・アンドゥ　（内部に球を持つ）
 * ・アンドゥ　（内部に削除を持つ。また削除のアンドゥ関数を実行。放物線が復活）
 * 
 * 
 * @author 真也
 */
//******************************************************************************
public class Command_Undo
	implements
//		TreeItem_Imp,	//デバッグ
		Entity_Imp,
		Command_Imp
{
	Command_Imp	m_command = null;
	private String m_command_string = static_default_command_string;
	private String m_name = "アンドゥ";
	
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
	public Command_Undo()
	{
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
	@Override
	public String get_command_string() {return this.m_command_string;}	
	@Override
	public void set_command_string(String str){this.m_command_string = str;}
	@Override public Command_Imp	factory() {return new Command_Undo();}
	@Override
	public String execute(MouseEvent mouse_event)
	{
		System.out.println(util.debug_ask_class_method_name());
		
		//最後のコマンドを取得
		ArrayList<Command_Imp> all_commands = Main.get_model_base().get_command_all();
		for(int ic=all_commands.size()-1; ic>=0; ic--){
			//Command_Undoは対象から外す。
			if(all_commands.get(ic) instanceof Command_Undo){
				continue;
			}
			m_command = all_commands.get(ic);
			break;
		}

		if(m_command == null){
			return "アンドゥオブジェクトはありません";
		}
			
		//各コマンドのアンドゥを実行
		m_command.undo();
		
		//置換する
		Main.get_model_base().replace_command(
			this.m_command,				//置換元
			this						//置換先
		);
		
		//登録
		Main.get_model_base().add_command(this);
		
		return "アンドゥされました";
	}
	@Override
	public void redo()
	{
		//置換する
		Main.get_model_base().replace_command(
			this,				//置換元
			this.m_command		//置換先
		);
		
		//各コマンドのリドゥ
		this.m_command.redo();
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

