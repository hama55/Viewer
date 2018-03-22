/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _40_Value;

import _20_Object_Template.TreeItem_Imp;
import _20_Object_Template.Entity_Imp;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import utility.util;

/**
 *
 * @author 真也
 */
public class Value_Long
	implements
		Entity_Imp,
		Value_Imp,
		TreeItem_Imp
{
	private Long m_long;

	public Value_Long()
	{
//		super(null);
	}
	
	public Value_Long(long long_value)
	{
//		super(null);
		this.m_long = long_value;
	}
	
	public Long get_long() {return this.m_long;}

//	@Override
//	public Node create_JavafxNode() {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}

//	@Override
//	public void factory_and_add(String id, FileInputOutput fileIO) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
//
//	@Override
//	public void read_data(XmlData xmlData, FileInputOutput dataFileIO, Entity_Directory topGroup) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
//
//	@Override
//	public String write_data(XmlData xmlData, FileInputOutput dataFileIO) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}

//	@Override
//	public void observer_receive_update(Entity_Base obj, UpdateType update_type) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
	@Override
	public TreeItem ask_JavafxTreeItem(boolean is_open)
	{
		try
		{
			//名前からツリーノードを作成
			TreeItem tree_item = new TreeItem<>(this);
			
			//なし
			
			return tree_item;
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
		
		return null;
	}
	@Override
	public ContextMenu getPopupMenu(){
		return null;
	}
	@Override
	public String getTreeItemName(){return this.get_name();}
	/***************************************************************************
	 * 
	 * [Entityクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public void		set_name(String value)	{;}
	@Override	public String	get_name()				{return "Long_名前なし";}
}
