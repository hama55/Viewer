package _11_Base_Button_etc;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

//import observer.Observer;
import _42_Utility.util;

import _30_Command.Command_Imp;
import javafx.scene.input.MouseEvent;



public class Button_Switch
	extends Button_Base
//	implements Observer
{
	Command_Imp m_CurrentCommand;	//現在のCommand
	Command_Imp m_DownCommand;		//押したときのCommand
	Command_Imp m_UpCommand;		//押されていないときのCommand
	ArrayList<Button_Switch>	m_NoSameTimeButton;	//同時に選択状態とはならないボタン（例：回転とパンは同時にはできない）

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Button_Switch(
		String 	Name, 
		String 	IconPath, 
		Command_Imp down_cmd, 
		Command_Imp up_cmd
		)
	{
		super(Name, IconPath, true);

		try
		{
			System.out.println(util.debug_ask_class_method_name());
			this.m_DownCommand = down_cmd;
			this.m_UpCommand   = up_cmd;
			
			//superのボタンOn/Off状態を取得、OnならCurrentにDownを入れる
			if(super.isOn() == true)
			{
				m_CurrentCommand = m_DownCommand;
			}
			else
			{
				m_CurrentCommand = m_UpCommand;
			}
			
			//同時起動できないButtonの保持
			m_NoSameTimeButton = new ArrayList<Button_Switch>();
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
	public void push(MouseEvent e)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
			//ButtonのOn/Off状態を設定
			if(super.isOn() == true)
			{
				//逆の非選択状態に変更
//				super.setSelected(false);
				m_CurrentCommand = m_UpCommand;
			}
			else
			{
				//逆の選択状態に変更
//				super.setSelected(true);
				m_CurrentCommand = m_DownCommand;
			}
			
			//他のボタンとの排他処理
//			for(int ic=0; ic<m_NoSameTimeButton.size(); ic++)
//			{
//				m_NoSameTimeButton.get(ic).update();
//			}
			m_NoSameTimeButton.stream()
				.forEach(a -> a.update());
				
			
			//実行
//			m_CurrentCommand.execute_command(e);
			m_CurrentCommand.execute(e);
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}	
	}
	
	//************************************************************************//
	/**
	*	Buttonの排他制御用
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void registor_observer(Button_Switch btn)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
			m_NoSameTimeButton.add(btn);
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}
	
	//************************************************************************//
	/**
	*	Buttonの排他制御用
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void remove_observer(Button_Switch btn)
	{
		try
		{
			System.out.println(util.debug_ask_class_method_name());
			for(int ic=0; ic<m_NoSameTimeButton.size(); ic++)
			{
				if(m_NoSameTimeButton.get(ic).equals(btn) == true)
				{
					m_NoSameTimeButton.remove(ic);
				}
			}
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}
	
	//************************************************************************//
	/**
	*	Buttonの排他制御用
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
//	@Override
	public void update()
	{
		System.out.println(util.debug_ask_class_method_name());
		super.setSelected(false);
	}
}
