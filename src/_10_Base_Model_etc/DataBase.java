/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _10_Base_Model_etc;

import _20_Object_Template.ObserverPass_Imp;
import _20_Object_Template.ObserverReceive_Imp;
import _23_Object_Iroiro.Object_11_CurrentObject;
import _30_Command.Command_Create_ReImAxisDefine;
import _30_Command.Command_Imp;
import java.util.ArrayList;
import java.util.Arrays;
import utility.util;

/**
 *
 * @author 真也
 */
public class DataBase
{
	private String m_name;
	private ArrayList<Command_Imp>	m_arry_command = new ArrayList<>();		//コマンド配列
	private Object_11_CurrentObject<Command_Create_ReImAxisDefine> m_current_re_im_axis = new Object_11_CurrentObject<>("虚実軸の定義");		//カレントの虚実軸
	
	//getter
	public Command_Create_ReImAxisDefine get_current_re_im_axis()	{return this.m_current_re_im_axis.get_current_object();}
	
	//setter
	public void set_current_re_im_axis(Command_Create_ReImAxisDefine csd) {this.m_current_re_im_axis.set_current_object(csd, ObserverPass_Imp.UpdateType.etc);}
	public void add_current_re_im_axis_observer(ObserverReceive_Imp obj) {this.m_current_re_im_axis.observer_register(obj);}
	//************************************************************************//
    /**
     *	コンストラクタ
     *
	 */
    //************************************************************************//
	public DataBase(String name){
		this.m_name = name;
	}
	//************************************************************************//
    /**
     *	Commandを追加
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
	public void add_command(Command_Imp ... commands)
	{
		System.out.println(util.debug_ask_class_method_name());
		
		try{
			Arrays.asList(commands).stream()
				.filter(a -> a != null)
				.filter(a -> this.m_arry_command.contains(a) == false)	//重複防止
				.forEach(a -> this.m_arry_command.add(a));
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
	}
   //************************************************************************//
    /**
     *	Commandを削除
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
	public void delete_command(Command_Imp ... commands)
	{		
		try{
			System.out.println(util.debug_ask_class_method_name());
			Arrays.asList(commands).stream()
				.filter(a -> a != null)
				.forEach(a -> 
					{	//削除
						this.m_arry_command.remove(a);
					});		
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}

	}
   //************************************************************************//
    /**
     *	Commandを置換
	 *  ほぼCommand_Delete_Command用
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
	public void replace_command(
		Command_Imp command_before,		//置換元
		Command_Imp command_after		//置換先
	)
	{		
		try{
			System.out.println(util.debug_ask_class_method_name());
			
			//beforeを探す
			int n_index = this.m_arry_command.indexOf(command_before);

			//見つかったら置換
			if(n_index >= 0){
				this.m_arry_command.set(n_index, command_after);
			}
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
	}
   //************************************************************************//
    /**
     *	Command全てを取得
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
	public ArrayList<Command_Imp> get_commands() {return this.m_arry_command;}
	
}
