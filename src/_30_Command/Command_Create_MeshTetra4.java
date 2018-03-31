/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _30_Command;

import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp;
import _20_Object_Template.Entity_Imp;
import _24_Object_Mesh.ObjectManager_MeshGrid;
import _20_Object_Template.TreeItem_Imp_Data;
import _24_Object_Mesh.Object_31_MeshNode;
import _24_Object_Mesh.Object_32_MeshTetra4;
import _42_Utility.XmlData;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import _42_Utility.util;

/**
 *
 * @author 真也
 */
public class Command_Create_MeshTetra4
//	extends Command_Imp
	implements
		Entity_Imp,
		Command_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
//	private String m_name;
	private Object_32_MeshTetra4 m_object;
	private String m_command_string = static_default_command_string;
	private String m_name = "Tetra4を作成";
	private static String static_default_command_string = ""
		+ "<MeshTetra4>"						+ util.string_get_newline()
		+ "	<Name>Tetra4</Name>"	+ util.string_get_newline()
//		+ "	//節点"
		+ "	<node>1, 2, 3, 4</node>"			+ util.string_get_newline()
		+ "</Point>";
	
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	public Command_Create_MeshTetra4()
	{
//		super(static_default_command_string);
//		this.m_name = static_default_command_string;

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

	/***************************************************************************
	 * 
	 * @param e
	 * @return 
	 */
	@Override
	public String execute(MouseEvent e)
	{
		XmlData xd = Command_Imp.ask_xml(this.m_command_string);
		
		if(xd == null)
		{
			//何もしない
			return "何も作成されません。";
		}
		
		//節点を作成
		Command_Imp.read_command_tag(xd, "MeshTetra4").stream()
			.forEach(a -> this.create_MeshTetra4(a));

		return "MeshTetra4が作成されました。";
	}
	
	//************************************************************************//
	/**
	*	xmldataの<Point>から節点を作成する。
	*
	*	@param
	*	@return
	*	@version
	*/
	private void create_MeshTetra4(
		XmlData		xd		//節点1つ作成のxmldata
	)
	{
		ArrayList<String[]> arry_string = null;
		ArrayList<long[]> arry_long = null;
		
		//名前を取得
		arry_string = Command_Imp.read_command_string(xd, "Name");
		String name = arry_string.get(0)[0];
		
		//節点を取得
//		ArrayList<Object_MeshNode> arry_buf = new ArrayList<>();
		arry_long = Command_Imp.read_command_long(xd, "node");

		Object_31_MeshNode node0 = ObjectManager_MeshGrid.get_instance().search(arry_long.get(0)[0]);
		Object_31_MeshNode node1 = ObjectManager_MeshGrid.get_instance().search(arry_long.get(0)[1]);
		Object_31_MeshNode node2 = ObjectManager_MeshGrid.get_instance().search(arry_long.get(0)[2]);
		Object_31_MeshNode node3 = ObjectManager_MeshGrid.get_instance().search(arry_long.get(0)[3]);


		//MeshTetra4を作成
		this.m_object = new Object_32_MeshTetra4(
			name,
			node0,
			node1,
			node2,
			node3
		);
		
		//格納
//		Main.get_model_base().AddObject(obj);
		Main.get_model_base().add_command(this);
	}
//	//************************************************************************//
//	/**
//	*	3Dオブジェクトを返す。
//	*
//	*	@param
//	*	@return
//	*	@version
//	***************************************************************************/
//	public Entity_Imp get_display_3D_object()
//	{
//		return this.m_object;
//	}
	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override public Command_Imp	factory() {return new Command_Create_MeshTetra4();}

	/***************************************************************************
	 * 
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public Node ask_JavafxNode(){
//		Group group = new Group();
//		this.m_arry_object.stream().forEach(obj -> group.getChildren().add(obj.ask_JavafxNode()));
		return this.m_object.ask_JavafxNode();
	}	
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

