package _40_Value;

import _20_Object_Template.TreeItem_Imp;


import utility.util;
import _20_Object_Template.Entity_Imp;
import javafx.scene.control.ContextMenu;
//import _13_Base_Type_etc.Filter;
import javafx.scene.control.TreeItem;
//import moredyn.OpenGL_Play;

public class Value_String
	implements
		Entity_Imp,
		Value_Imp,
		TreeItem_Imp
{
	
	private String		m_string = "";
//	private double[]	m_3d_position = {0.0, 0.0, 0.0};
	
	//************************************************************************//
	/**
	 *	Constructor
	 */
	//************************************************************************//
	public Value_String()
	{
//		super(null);
	}
	public Value_String(String name)
	{
//		super(name);
//		try
//		{
//			Text t = new Text(name);
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
	}
	
	//************************************************************************//
	/**
	 *	setter
	 */
	//************************************************************************//
	
	//************************************************************************//
	/**
	 *	getter
	 */
	//************************************************************************//
	

//	//************************************************************************//
//	/**
//	 *	読み込みデータを実体化
//	 */
//	//************************************************************************//
//	@Override
//	public void read_data
//	(
//		XmlData			xmlData,		//各オブジェクト形式のXML(例:<Group_Base>1,XXX \n 2,XXX \n ･･･ </Group_Base>)
//		FileInputOutput	dataFileIO,		//生成したオブジェクトを全て格納
//		Entity_Directory		topGroup		//Xmlオプションに"top"とあるものはTopのGroupにぶら下がる
//	)
//	{
//	}
//	//************************************************************************//
//	/**
//	 *	idと関連付けたオブジェクトをプールに登録
//	 */
//	//************************************************************************//
//	@Override
//	public void factory_and_add
//	(
//		String			id,		//保存、読み込み時のオブジェクトID
//		FileInputOutput	fileIO	//データプール
//	)
//	{
//		fileIO.add(id, new Value_String());
//	}
//	//************************************************************************//
//	/**
//	 *	ファイルに書き出し
//	 *
//	 *	@param	
//	 *	@return	
//	 *	@version
//	 */
//	//************************************************************************//
//	@Override
//	public String write_data
//	(
//		XmlData				xmlDataMySelf,	//OUT	親のXmlData。このXmlDataの子として登録する。
//		FileInputOutput		dataFileIO		//IN	書き込み時の全てのXmlDataを持つ。
//	)
//	{
//		return null;
//
//	}

//	@Override
//	public Node create_JavafxNode() {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
//
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
			
			//無し

			
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
	@Override	public void		set_name(String value)	{}
	@Override	public String	get_name()				{return "String_名前なし";}

}
