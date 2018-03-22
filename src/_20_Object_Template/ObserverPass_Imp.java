/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _20_Object_Template;

/**
 *
 * @author 真也
 */
public interface ObserverPass_Imp {
	
	//通知先を登録
	public void observer_register(ObserverReceive_Imp... objs);
	
	//通知先を削除
	public void observer_delete(ObserverReceive_Imp... objs);

	////////////////////////////////////////////////////////////////////////
	/**
	 * オブザーバー　更新タイプ
	 * 
	 *//////////////////////////////////////////////////////////////////////
	public enum UpdateType {
		CoordinateSystemDefine_Change(1),	//座標系の変更
		etc(2);		//Zの虚数

		private final int id;
		private UpdateType(final int id) {	this.id = id;}
	}
}
