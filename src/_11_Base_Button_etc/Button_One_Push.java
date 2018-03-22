package _11_Base_Button_etc;
//import java.awt.event.ActionEvent;

import _00_GUI_Main_JavaFX.Main;
import utility.util;
import _30_Command.Command_Imp;

import javafx.scene.input.MouseEvent;


//1回押したら実行を行うButton
public class Button_One_Push
	extends Button_Base
{
	Command_Imp m_Command;	//ボタン選択時のCommand	

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Button_One_Push(
		String Name, 
		String IconPath, 
		Command_Imp cmd
	)
	{
		super(Name, IconPath, false);
	
		try
		{
			System.out.println(util.debug_ask_class_method_name());
			this.m_Command = cmd;
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}	
	}

	//************************************************************************//
	/**
	*	push
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	@Override
	public void push(MouseEvent mouse_event)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
			
			//実行
//			m_Command.execute(mouse_event);
			m_Command.factory().execute(mouse_event);

			//更新
			Main.get_model_base().Update();
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}	
	}
}
