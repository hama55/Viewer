/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _23_Object_Iroiro;


import _20_Object_Template.TreeItem_Imp;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.ObserverPass_Imp;
import _20_Object_Template.ObserverPass_Imp_Data;
import _20_Object_Template.ObserverReceive_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;

//////////////////////////////////////////////////////////////////////////////
/**
 * 2016/10/10
 * 方針：カレント状態（アクティブ状態）のObjectを入れる。
 * 例えば座標系をたくさん作ったとき、どれがカレント状態かわかるように。
 * もしカレントの座標系が変われば、オブザーバーで更新がわかるようにするため用意。
 *
 * @author 真也
 *////////////////////////////////////////////////////////////////////////////
public class Object_11_CurrentObject<_T extends Entity_Imp>
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp,
		ObserverPass_Imp
{
	private _T	m_current_object = null;
	private String m_name;

	public Object_11_CurrentObject(String name) {
		this.m_name = name;
	}

	public Object_11_CurrentObject(
		String name,
		_T	obj)
	{
		this.m_name = name;
		m_current_object = obj;
	}
	
	////////////////////////////////////////////////////////////////////////////
	/**
	 * setter
	 * 
	 * @return 
	 *//////////////////////////////////////////////////////////////////////////
	public void set_current_object(
		_T obj,
		ObserverPass_Imp.UpdateType update_type
	)
	{	
		//カレントオブジェクトを変更
		this.m_current_object = obj;
		
		//カレントが変わったため、オブザーバーで更新をお知らせ
//		super.observer_inform_update(update_type);
		m_observer_pass.update_inform(this.m_current_object);
	}
	
	////////////////////////////////////////////////////////////////////////////
	/**
	 * getter
	 * 
	 * @return 
	 *//////////////////////////////////////////////////////////////////////////
	public _T get_current_object()	{return this.m_current_object;}



//	@Override
//	public void observer_receive_update(Entity_Base obj, UpdateType update_type) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
//
//	@Override
//	public void factory_and_add(String id, FileInputOutput fileIO) {
////		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//		this.m_current_object.factory_and_add(id, fileIO);
//	}
//
//	@Override
//	public void read_data(XmlData xmlData, FileInputOutput dataFileIO, Entity_Directory topGroup) {
////		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//		this.m_current_object.read_data(xmlData, dataFileIO, topGroup);
//	}
//
//	@Override
//	public String write_data(XmlData xmlData, FileInputOutput dataFileIO) {
////		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//		return this.m_current_object.write_data(xmlData, dataFileIO);
//	}
//	@Override
//	public void ask_JavafxTreeItem_children(boolean is_open, TreeItem tree_item)
//	{
//		try
//		{
////			//名前からツリーノードを作成
////			TreeItem tree_item = new TreeItem<>(this);
//			
//			Entity_Directory.ask_JavafxTreeNode_utility_obj(is_open, tree_item, this.m_current_object);
//			
////			return tree_item;
//		}
//		catch (Exception ex)
//		{
//			utility.write_exception(ex);
//		}
//		
////		return null;
//	}
	/***************************************************************************
	 * 
	 * [TreeItemクラスのインプリメントデータ・関数群]
	 * @param is_open
	 * @return 
	 */
	//**************************************************************************
	//データ
	TreeItem_Imp_Data m_treeitem_imp_data = new TreeItem_Imp_Data();
	//オーバーライド関数
	@Override
	public TreeItem ask_JavafxTreeItem(boolean is_open) {
		return this.m_treeitem_imp_data.create_JavafxTreeItem(is_open, this);
	}
	@Override
	public ContextMenu getPopupMenu(){
		return m_treeitem_imp_data.create_PopupMenu(0, this);
	}
	@Override
	public String getTreeItemName(){return this.get_name();}
	/***************************************************************************
	 * 
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override
	public Node ask_JavafxNode(){
		Node obj;
		if(this.m_current_object instanceof Display_3D_Object_Imp){
			obj = ((Display_3D_Object_Imp)this.m_current_object).ask_JavafxNode();
		}
		else{
			obj = new Group();
		}
		
		return obj;
	}
	/***************************************************************************
	 * 
	 * [Entityクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public void		set_name(String value)	{this.m_name = value;}
	@Override	public String	get_name()				{return this.m_name;}

	/***************************************************************************
	 * 
	 * [Observerクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	private ObserverPass_Imp_Data m_observer_pass = new ObserverPass_Imp_Data();
	@Override	public void observer_register	(ObserverReceive_Imp... objs){this.m_observer_pass.observer_register(objs);}
	@Override	public void observer_delete	(ObserverReceive_Imp... objs){this.m_observer_pass.observer_delete(objs);}

}
