/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _22_Object_Primitive;

import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _20_Object_Template.TreeItem_Imp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Node;
import _20_Object_Template.Entity_Imp;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import utility.util;

//************************************************************************//
/**
 *	三角形を組み合わせて面を作る
 * 
 * @author 真也
 */
//************************************************************************//
public class Object_01_Face
	implements
		Display_3D_Object_Imp,
		TreeItem_Imp,
		Entity_Imp

{
	private String m_name;
	private Node m_javafxnode = null;
	private boolean m_update_on = true;
	private ArrayList<Object_05_Triangle> m_triangles = new ArrayList<>();
	
	
	//************************************************************************//
	/**
	*	Constructor
	*/
	//************************************************************************//
	public Object_01_Face(
		String name,
		Object_05_Triangle	... triangles
	)
	{
		m_name = name;
		List<Object_05_Triangle> list = Arrays.asList(triangles);
		
		list.stream()
			.forEach(a -> m_triangles.add(a));
	}
	

	//************************************************************************//
	/**
	*	
	 * @return 
	*/
	//************************************************************************//
	public Node create_JavafxNode()
	{
		System.out.println(util.debug_ask_class_method_name());

		Group group = new Group();
		
		this.m_triangles.stream()
//			.parallel()
			.filter(a -> a != null)
			.forEach(a -> group.getChildren().add(a.ask_JavafxNode()));
		
		this.m_javafxnode = group;
		return group;
	}

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
//		
//		//座標系が変わった
//		if(update_type == Entity_Base.UpdateType.CoordinateSystem_Change){
//			this.setM_update_on(true);
//			//重複は避けながら全てのObject_Pointを集める。
//			Set<Object_Point> list_point = new HashSet<>();
//			this.m_triangles.stream()
//				.filter(a -> a != null)
//				.forEach(a -> Arrays.asList(a.get_tri_points()).stream()
//					.forEach(b -> list_point.add(b)));
//			
//			//Object_Pointを更新する
//			list_point.stream()
//				.filter(a -> a != null)
////				.forEach(a -> a.update_JavaFxNode());
//				.forEach(a -> a.setM_update_on(true));
//		}
//	}
//	@Override
//	public void ask_JavafxTreeItem_children(boolean is_open, TreeItem tree_item)
//	{
//		try
//		{
//			//名前からツリーノードを作成
////			TreeItem tree_item = new TreeItem<>(this);
//			
//			Entity_Directory.ask_JavafxTreeNode_utility_obj(
//				is_open,
//				tree_item,
//				m_triangles);
//			
////			return tree_item;
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
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
		if(this.m_update_on == true || this.m_javafxnode == null){
			this.m_update_on = false;
			this.m_javafxnode = create_JavafxNode();
		}
		return this.m_javafxnode;
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
}
