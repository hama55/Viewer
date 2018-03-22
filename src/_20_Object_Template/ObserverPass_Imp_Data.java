/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _20_Object_Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author 真也
 */
public class ObserverPass_Imp_Data
	implements
		ObserverPass_Imp
{
	//オブザーバーの更新リスト
	private ArrayList<ObserverReceive_Imp> m_array_observer_list = new ArrayList<>();
	
	//************************************************************************//
    /**
     *	更新の登録
     *
     *	@param	
     *	@return	
     *	@version
     */
    //************************************************************************//
	@Override
	public void observer_register(ObserverReceive_Imp... objs)
	{
		//ArrayListに変更
		ArrayList<ObserverReceive_Imp> arry_buf = new ArrayList<>(Arrays.asList(objs));
		
		//追加
		m_array_observer_list.addAll(arry_buf);
		
		//重複を除去
		Set<ObserverReceive_Imp> set = new LinkedHashSet<>(m_array_observer_list);
		m_array_observer_list = new ArrayList<>(set);		
	}
    //************************************************************************//
    /**
     *	更新の登録解除
     *
     *	@param	
     *	@return	
     *	@version
     */
    //************************************************************************//
	@Override
	public void observer_delete(ObserverReceive_Imp... objs)
	{
		//ArrayListに変更
		ArrayList<ObserverReceive_Imp> arry_buf = new ArrayList<>(Arrays.asList(objs));
		
		//削除
		this.m_array_observer_list.removeAll(arry_buf);
	}
	
	public void update_inform(Entity_Imp obj){
		this.m_array_observer_list.stream()
			.filter(a -> a != null)
			.forEach(a -> a.observer_receive_update(obj));
	}
}
