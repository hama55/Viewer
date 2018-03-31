/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _11_Base_Button_etc;

import _00_GUI_Main_JavaFX.Main;
import _01_GUI_Dialog_JavaFX.EditDialogControler;
import _30_Command.Command_Imp;
import javafx.scene.input.MouseEvent;
import _42_Utility.util;

/*******************************************************************************
 * 
 * ボタンを押すとEditDialogが開く
 *
 * @author 真也
 */
public class Button_EditDialog
	extends Button_Base
{
	private Command_Imp	m_command = null;
	/***************************************************************************
	 * 
	 * コンストラクタ
	 * 
	 * @param Name
	 * @param IconPath
	 * @param cmd 
	 */
	public Button_EditDialog(
		String Name, 
		String IconPath, 
		Command_Imp cmd
	)
	{
		super(Name, IconPath, false);
		
		m_command = cmd;
	}
	
	/***************************************************************************
	 * 
	 * @param e 
	 */
	@Override
	public void push(MouseEvent e)
	{
		try{
			System.out.println(util.debug_ask_class_method_name());
			
			//ダイアログ生成
			EditDialogControler testEdit = EditDialogControler.factory();
			//コマンド生成　(生成すればオブジェクトが1つ以上作れる)
			testEdit.set_command(this.m_command.factory());
			//ダイアログ起動
			testEdit.start(Main.get_primary_stage());
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
	}
	
}
