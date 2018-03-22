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
import utility.util;

/*******************************************************************************
 * 
 * リドゥ
 * 
 * 
 * @author 真也
 */
//******************************************************************************
public class Command_Redo
	implements
		Entity_Imp,
		Command_Imp
{
//	Command_Undo	m_command_undo = null;
	private String m_command_string = static_default_command_string;
	private String m_name = "リドゥ";
	
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
	public Command_Redo()
	{
	}

	//************************************************************************//
	/**
	*	setter
	*/
	//************************************************************************//
	public void set_command_default_string(String str)	{this.static_default_command_string = str;}

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
	@Override	public Command_Imp	factory() {return new Command_Redo();}
	@Override
	public String execute(MouseEvent mouse_event)
	{
		System.out.println(util.debug_ask_class_method_name());
		
		//最初のアンドゥコマンドを取得
		ArrayList<Command_Imp> all_commands = Main.get_model_base().get_command_all();
		for(int ic=0; ic<all_commands.size(); ic++){
			//Command_Undoを検索
			if(all_commands.get(ic) instanceof Command_Undo){
				Command_Undo command_undo = (Command_Undo)all_commands.get(ic);
				//Command_Undoのredoを実行
				command_undo.redo();
				break;
			}
		}
		
		//登録はしない
		
		return "リドゥされました";
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

