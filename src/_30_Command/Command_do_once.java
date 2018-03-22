package _30_Command;
import _20_Object_Template.Entity_Imp;
import java.awt.event.ActionListener;
import javafx.scene.input.MouseEvent;

import utility.util;


public class Command_do_once
	implements
		Entity_Imp,
		Command_Imp
{
	private String m_command_string = null;
	private String m_name = "Command_test";
//	ActionListener m_act;
	
	//************************************************************************//
	/**
	*	
	*/
	//************************************************************************//
	public Command_do_once(ActionListener act)
	{
//			super("");
		try
		{
			System.out.println(util.debug_ask_class_method_name());
//			m_act = act;
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//
	/**
	*	
	*/
	//************************************************************************//
	@Override
	public String execute(MouseEvent e)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
//			m_act.actionPerformed(e);
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
	@Override public Command_Imp	factory() {return new Command_do_once(null);}

	
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
