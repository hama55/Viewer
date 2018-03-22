package _30_Command;

//import java.awt.event.ActionEvent;
import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.TreeItem_Imp;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;

import _40_Value.Value_Double;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _23_Object_Iroiro.Object_21_平行六面体;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import utility.util;
import xml.XmlData;

public class Command_Create_平行六面体
	implements
		Command_Imp,
		TreeItem_Imp,
		Display_3D_Object_Imp,
		Entity_Imp		
{
	Object_21_平行六面体	m_obj;
	private String m_command_string = static_default_command_string;
	private String m_name = "平行六面体の作成";
	
	private static String static_default_command_string = ""
		+ "<Cuboid>"						+ util.string_get_newline()
		+ "	<Name>平行6面体</Name>"	+ util.string_get_newline()
//		+ ""								+ util.string_get_newline()
//		+ "	//原点"
		+ "	<origin>0.000, 0.000, 0.000</origin>"			+ util.string_get_newline()
//		+ ""								+ util.string_get_newline()
//		+ "	//軸X"
		+ "	<axis_x>1.000, 0.000, 0.000</axis_x>"			+ util.string_get_newline()
//		+ ""								+ util.string_get_newline()
//		+ "	//軸Y"
		+ "	<axis_y>0.000, 1.000, 0.000</axis_y>"			+ util.string_get_newline()
//		+ ""								+ util.string_get_newline()
//		+ "	//軸Z"
		+ "	<axis_z>0.000, 0.000, 1.000</axis_z>"			+ util.string_get_newline()
//		+ ""								+ util.string_get_newline()
//		+ "	//各軸の長さ"
		+ "	<length_xyz>100.000, 200.000, 300.000</length_xyz>"			+ util.string_get_newline()
		+ "</Cuboid>";


	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Command_Create_平行六面体()
	{
		m_command_string = static_default_command_string;
	}

	//************************************************************************//
	/**
	*	execute
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	@Override
	public String execute(MouseEvent e)
	{
		System.out.println(util.debug_ask_class_method_name());

		//XmlDataに変換
		XmlData xd = Command_Imp.ask_xml(this.m_command_string);

		//連続作成
		Command_Imp.read_command_tag(xd, "Cuboid").stream()
			.forEach(buf -> this.create_cuboid(buf));
		
//		return "2次関数の平面（複素数）が作成されました。";

		//Command登録
		Main.get_model_base().add_command(this);
		
		//更新
		Main.get_model_base().Update();
		
		return "平行6面体が作成されました。";
	}
	
	/***************************************************************************
	 * 
	 * 
	 * 
	 */
	public void create_cuboid(XmlData xd)
	{
		/////////////////////////////////////////
		//	テーブルから値を取得
		//
		//	  項目,		     X,    Y,    Z 
		//	-----------------------------------
		//	{"原点",     	"0",  "0",  "0"  }
		//	{"軸X",			"1",  "0",  "0"  }
		//	{"軸Y",			"0",  "1",  "0"  }
		//	{"軸Z",			"0",  "0",  "1"  }
		//	{"各軸の長さ",	"100","100","100"}
		//
		/////////////////////////////////////////
		
		ArrayList<String[]> arry_string = null;
		ArrayList<double[]> arry_double = null;
		ArrayList<Value_Double>	arry_values = new ArrayList<>();
		
		//名前を取得
		arry_string = Command_Imp.read_command_string(xd, "Name");
		String name = arry_string.get(0)[0];
		
		//原点を取得
		arry_double = Command_Imp.read_command_double(xd, "origin");
		for(int ic=0; ic<3; ic++)
		{
			Value_Double buf = new Value_Double(arry_double.get(0)[ic]);
			arry_values.add(buf);
		}
		
		//X軸を取得
		arry_double = Command_Imp.read_command_double(xd, "axis_x");
		for(int ic=0; ic<3; ic++)
		{
			Value_Double buf = new Value_Double(arry_double.get(0)[ic]);
			arry_values.add(buf);
		}
		
		//Y軸を取得
		arry_double = Command_Imp.read_command_double(xd, "axis_y");
		for(int ic=0; ic<3; ic++)
		{
			Value_Double buf = new Value_Double(arry_double.get(0)[ic]);
			arry_values.add(buf);
		}
		
		//Z軸を取得
		arry_double = Command_Imp.read_command_double(xd, "axis_z");
		for(int ic=0; ic<3; ic++)
		{
			Value_Double buf = new Value_Double(arry_double.get(0)[ic]);
			arry_values.add(buf);
		}
		
		//各軸の長さを取得
		arry_double = Command_Imp.read_command_double(xd, "length_xyz");
		for(int ic=0; ic<3; ic++)
		{
			Value_Double buf = new Value_Double(arry_double.get(0)[ic]);
			arry_values.add(buf);
		}
		
		//平行6面体を作成する
		m_obj = new Object_21_平行六面体(
			name,		//名前
			arry_values
		);		
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
	//************************************************************************//
	/**
	*	3Dオブジェクトを返す。
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	public Entity_Imp get_display_3D_object()
	{
		return this.m_obj;
	}
	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override public Command_Imp	factory() {return new Command_Create_平行六面体();}
	/***************************************************************************
	 * 
	 * [TreeItemクラスのインプリメントデータ・関数群]
	 * @param is_open
	 * @return 
	 */
	//**************************************************************************
	TreeItem_Imp_Data m_treeitem_imp_data = new TreeItem_Imp_Data();
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
	@Override	public Node ask_JavafxNode(){return m_obj.ask_JavafxNode();}


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
