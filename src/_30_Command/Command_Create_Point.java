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
import _23_Object_Iroiro.Object_03_Point;
import _20_Object_Template.TreeItem_Imp_Data;
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
public class Command_Create_Point
//	extends Command_Imp
	implements
		Entity_Imp,
		Command_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
	private Object_03_Point obj;
	private String m_command_string = static_default_command_string;
	private String m_name = "点の作成";

	private static String static_default_command_string = ""
		+ "<Point>"						+ util.string_get_newline()
		+ "	<Name>Point</Name>"	+ util.string_get_newline()
//		+ ""								+ util.string_get_newline()
//		+ "	//原点"
		+ "	<origin>0.000, 0.000, 0.000</origin>"			+ util.string_get_newline()
		+ "</Point>";
	
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	public Command_Create_Point()
	{
//		super(static_default_command_string);
//		this.m_name = static_default_command_string;
	}


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
		Command_Imp.read_command_tag(xd, "Point").stream()
			.forEach(a -> this.create_Point(a));

		return "Pointが作成されました。";
	}
	
	//************************************************************************//
	/**
	*	xmldataの<Point>から節点を作成する。
	*
	*	@param
	*	@return
	*	@version
	*/
	private void create_Point(
		XmlData		xd		//節点1つ作成のxmldata
	)
	{
		ArrayList<String[]> arry_string = null;
		ArrayList<double[]> arry_double = null;
		
		//名前を取得
		arry_string = Command_Imp.read_command_string(xd, "Name");
		String name = arry_string.get(0)[0];
		
		//原点を取得
		arry_double = Command_Imp.read_command_double(xd, "origin");
		double[] origin = arry_double.get(0);

		//Pointを作成
		this.obj = new Object_03_Point(
			name,
			origin
		);

		//格納
//		Main.get_model_base().AddObject(obj);
		Main.get_model_base().add_command(this);
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
//		return this.obj;
//	}
	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override public Command_Imp	factory() {return new Command_Create_Point();}
	/***************************************************************************
	 * 
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public Node ask_JavafxNode(){return this.obj.ask_JavafxNode();}
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

