package _30_Command;

import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import _20_Object_Template.Entity_Directory;
import _24_Object_Mesh.ObjectManager_MeshGrid;
//import _20_Object.ObjectManager_GroupBase;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _24_Object_Mesh.Object_31_MeshNode;
import _40_Value.Value_Double;
import _42_Utility.XmlData;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import _42_Utility.util;

public class Command_Create_Node
//	extends Command_Imp
	implements
		Entity_Imp,
		Command_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
//	private String m_name;
//	private Entity_Imp m_object;
	private ArrayList<Object_31_MeshNode> m_arry_object = new ArrayList<>();
	private String m_command_string = static_default_command_string;
	private String m_name = "節点の作成";
	
	static private final String D_GROUP		= "group";		//xmlタグ
	static private final String D_GRID		= "grid";		//xmlタグ
	static private final String D_ID		= "id";			//xmlタグ
	static private final String D_POSITION	= "position";	//xmlタグ

	private static String static_default_command_string = ""
		+ "<grid>"						+ util.string_get_newline()
		+ "	<id>1</id>"						+ util.string_get_newline()
		+ "	<position>50.000, 50.000, 50.000</position>"						+ util.string_get_newline()
		+ "</grid>"						+ util.string_get_newline()
		+ "<grid>"						+ util.string_get_newline()
		+ "	<id>2</id>"						+ util.string_get_newline()
		+ "	<position>100.000, 50.000, 50.000</position>"						+ util.string_get_newline()
		+ "</grid>"						+ util.string_get_newline()
		+ "<grid>"						+ util.string_get_newline()
		+ "	<id>3</id>"						+ util.string_get_newline()
		+ "	<position>50.000, 100.000, 50.000</position>"						+ util.string_get_newline()
		+ "</grid>"						+ util.string_get_newline()
		+ "<grid>"						+ util.string_get_newline()
		+ "	<id>4</id>"						+ util.string_get_newline()
		+ "	<position>50.000, 50.000, 100.000</position>"						+ util.string_get_newline()
		+ "</grid>"						+ util.string_get_newline();	
		
	static private ArrayList<String> default_command = null;
	static{
		default_command = new ArrayList<String>();
//		default_command.add("<group>作成された節点</group>	//※格納するデフォルトグループ");
		default_command.add("<grid>");
		default_command.add("	<id>1</id>");
		default_command.add("	<position>50.000, 50.000, 50.000</position>");
		default_command.add("</grid>");
		default_command.add("<grid>");
		default_command.add("	<id>2</id>");
		default_command.add("	<position>100.000, 50.000, 50.000</position>");
		default_command.add("</grid>");
		default_command.add("<grid>");
		default_command.add("	<id>3</id>");
		default_command.add("	<position>50.000, 100.000, 50.000</position>");
		default_command.add("</grid>");
		default_command.add("<grid>");
		default_command.add("	<id>4</id>");
		default_command.add("	<position>50.000, 50.000, 100.000</position>");
		default_command.add("</grid>");
	}	
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	public Command_Create_Node()
	{
		//格納
//		super(default_command.toArray(new String[0]));
//		super("");
	}

	//************************************************************************//
	/**
	*	execute
	*	渡されたxmlから節点を作成する。
	*	
	*	xmlの構造
	*	
	*		<group>作成された節点</group>	//※格納するデフォルトグループ
	*		<grid>
	*			<id>100</id>
	*			<position>0.000, 5.000, 3.000</position>
	*		</grid>
	*		<grid>
	*			<id>101</id>
	*			<position>6.000, 3.000, 0.000</position>
	*		</grid>
	*		<grid>
	*			<group>特別な節点</group>	//格納する個別グループ
	*			<id>102</id>
	*			<position>6.000, 2.000, 3.000</position>
	*		</grid>
	* 
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	@Override
	public String execute(MouseEvent e)
	{
		XmlData xd = Command_Imp.ask_xml(this.m_command_string);
		
		if(xd == null){
			//何もしない
			return "何も作成されません。";
		}
		
		//節点を作成
		Command_Imp.read_command_tag(xd, D_GRID).stream()
			.forEach(a -> this.create_grid(a, null));

		Main.get_model_base().add_command(this);

		return "節点が作成されました。";
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
	*	xmldataの<grid>から節点を作成する。
	*
	*	@param
	*	@return
	*	@version
	*/
	private void create_grid(
		XmlData		grid_data,		//節点1つ作成のxmldata
		Entity_Directory	group			//デフォルトの節点格納グループ
	)
	{
		ArrayList<String[]>	id			= Command_Imp.read_command_string	(grid_data, D_ID);
		ArrayList<double[]>	position	= Command_Imp.read_command_double (grid_data, D_POSITION);
		ArrayList<String[]>	group_sp	= Command_Imp.read_command_string (grid_data, D_GROUP);

		//Gridが個別にGroup_Baseを指定している場合、それを使用する。
		String s_group_name = "";
		if(group_sp != null){
			s_group_name = group_sp.get(0)[0];
		}
		else{
			s_group_name = "節点Gr";
		}
		group = Entity_Directory.factory_or_search(s_group_name);

		//節点IDを取得
		Long id_buf = Long.valueOf(id.get(0)[0]);
		if(id_buf == null){
			id_buf = ObjectManager_MeshGrid.get_instance().ask_new_id();
		}
		
		//節点を作成する
		Object_31_MeshNode object = this.factory(
			id_buf,			//節点のID
			position.get(0)	//節点の座標値
		);
					
		//グループに格納
		group.add_object(object, true);
		
		m_arry_object.add(object);
		


	}
	//************************************************************************//
	/**
	*	節点を作成
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Object_31_MeshNode factory(
		Long		id,			//節点のID
		double[]	position	//節点の座標値
	)
	{
//		System.out.printf("まだ実装していない。");
//		///// 節点IDの重複チェック /////
//		if(ObjectManager_MeshGrid.get_instance().check_id(id) == false){
//			return null;
//		}
		
		///// 節点を作成 /////
		Object_31_MeshNode obj = new Object_31_MeshNode(
//			id,	
			new Value_Double(position[0]),		//X
			new Value_Double(position[1]),		//Y
			new Value_Double(position[2])		//Z
		);
		
		///// 節点IDを変更 /////
		boolean bSuccess = obj.change_grid_id(id);
		
		if(bSuccess == true)
		{
			return obj;
		}
				
		return obj;
//		return null;
	}
	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override public Command_Imp	factory() {return new Command_Create_Node();}

	/***************************************************************************
	 * 
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public Node ask_JavafxNode(){
		Group group = new Group();
		this.m_arry_object.stream().forEach(obj -> group.getChildren().add(obj.ask_JavafxNode()));
		return group;
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

