package _20_Object_Template;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.control.TreeItem;
import _12_Base_Color_etc.DrawPen;
import _12_Base_Color_etc.Material;

import java.util.Arrays;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import utility.util;


//************************************************************************//
/**
 * Group
 * 
* 特徴：<br>
 * 1.Groupは、親と子のGroup_Baseと、子のObject_Baseを保持する。<br>
 * 2.Group内のObjectは、共通の材質、描画ペンを使用する。<br>
 * 3.親Groupと子Group配列の関係をそのままTree構造として、項目名はGroup名とする。<br><br>
 *
 * 2015/01/11<br>
 * Groupの定義を変えた。以前のGroupはDataPoolとObjectとGroupを兼ねていたが複雑すぎた。<br>
 * 今回は役目をGroupとObject_Baseのまとまりに集中している。<br>
 *
 * 2016/02/23<br>
 * Object_Baseに限定する必要もないな。TreeItem_Impでいつでもツリーから参照可能としよう。 そしてCommand_Baseも入れる。
 * 
* 2016/10/08<br>
 * Group_Baseは、そのままDBとする。だから自由には作らせない。 ただシングルトンで別のオブジェクトを用意するのは面倒だ。
 * ObjectManager_GroupBaseは作らず、Group_Baseに実装する。
 * 
* 2016/10/09<br>
 * やっぱり、Group_BaseはDBとはせずに、ただのまとまりとする。
 * DBは、Object_Baseにして、Group_BaseのスーパークラスもObject_Baseにする。
 * 名前もJavaFxのGroupと混同して紛らわしいので、Object_Directoryとする。
 */
//************************************************************************//
public class Entity_Directory
	implements
		Entity_Imp,
		TreeItem_Imp,
		Display_3D_Object_Imp
{
	//************************************************************************//

	/**
	 * シングルトンのためのstatic変数、関数
	 *
	 * @return
	 */
	//************************************************************************//
	static private Integer static_m_group_default_name_number = 0;
	static private ArrayList<Entity_Directory> static_m_arry_group = new ArrayList<Entity_Directory>();
	
	private String m_name;
	//************************************************************************//
	/**
	 * search
	 *
	 * @param name_group
	 * @return
	 * @version
	 */
	//************************************************************************//
	static synchronized public Entity_Directory search(
		String name_group //I		検索するGroup_Baseの名前
	) {
		Entity_Directory group = null;
		for (Entity_Directory group_base : static_m_arry_group) {
			//見つけた場合
			if (name_group.equals(group_base.get_name()) == true) {
				group = group_base;
				break;
			}
		}
		return group;
	}

	//************************************************************************//
	/**
	 * 既存のGroup_Baseを取得。ない場合は作成する。
	 *
	 * @param name_group
	 * @return
	 * @version
	 */
	//************************************************************************//
	static synchronized public Entity_Directory factory_or_search(
		String name_group //I		検索するGroup_Baseの名前
	) {
		Entity_Directory group = null;

		if (name_group != null) {
			group = search(name_group);
			//無い場合は作る
			if (group == null) {
				group = new Entity_Directory(name_group);
			}
		} else {
			//名前がnullの場合は名前を付ける。重複した名前は付けない(group_0, group1 …)
			String name_buf = "";
			for (;;) {
				name_buf = "group_" + static_m_group_default_name_number.toString();
				static_m_group_default_name_number++;
				if (search(name_group) == null) {
					break;
				}
			}
			group = new Entity_Directory(name_buf);
		}

		//追加
		static_m_arry_group.add(group);

		return group;
	}

	//************************************************************************//
	/**
	 * 全てのGroup_Baseを返す
	 *
	 * @param name_group
	 * @return
	 * @version
	 */
	//************************************************************************//
	static public ArrayList<Entity_Directory> get_all_group() {
		return static_m_arry_group;
	}
	//************************************************************************//
	/**
	 * 通常の変数、関数
	 *
	 * @return
	 */
	//************************************************************************//
	private Entity_Directory m_GroupParent;				//親のGroup
	private Material m_material;					//Object描画関係 ： 材質（色、反射率など）
	private DrawPen m_drawPen;					//Object描画関係 ： 描画ペン（エッジの線種、フォントなど）
	private Boolean m_bDisplayUpdateOn;			//Object描画関係 ： DisplayListに更新があるか判定フラグ
//	private PopupMenu_TreeNode		m_popup;					//ポップアップメニュー
	private Group m_javafx_group_myself;      //本グループベースを表すjavafxのGroup

//	private ArrayList<TreeItem_Imp> m_arry_entities;
	private ArrayList<Entity_Imp> m_arry_entities;

	//************************************************************************//
	/**
	 * Constructor
	 */
	//************************************************************************//
	private Entity_Directory() {
//		super("Default");
//		m_Name = "Default";
		constructor();
	}

	//************************************************************************//

	/**
	 * Constructor
	 */
	//************************************************************************//
	private Entity_Directory(String name) {
		this.m_name = name;
		try {
//			m_Name = name;
			constructor();
		} catch (Exception ex) {
			util.debug_write_exception(ex);
		}
	}

	//************************************************************************//

	/**
	 * Constructor
	 */
	//************************************************************************//
	private void constructor() {
		//System.out.println(util.debug_ask_class_method_name());

//		m_arryDispList_Disp		= new ArrayList<Integer>();
//		m_arryDispList_Non_Disp	= new ArrayList<Integer>();
		m_bDisplayUpdateOn = true;
//		m_javafx_group_myself = null;

//		//グループのマネージャに登録
//		ObjectManager_GroupBase.get_instance().add_arry_group(this);
		//Materialの初期値
//		this.m_material = new Material();
	}

	//************************************************************************//
	/**
	 * getter
	 */
	//************************************************************************//
//	public String					get_Name()					{return this.m_Name;}
//	public Group_Base				get_GroupParent()			{return this.m_GroupParent;}
//	public ArrayList<Object_Base>	get_ArrayObject()			{return this.m_arryObject;}
//	public ArrayList<Group_Base>	get_ArrayGroup()			{return this.m_arryGroup;}
//	public Material get_Material() {
//		return this.m_material;
//	}
//
//	public DrawPen get_DrawPen() {
//		return this.m_drawPen;
//	}
//	public PopupMenu_TreeNode		get_PopupMenu_TreeNode()	{return this.m_popup;}

//	public Boolean get_bDisplayUpdateOn() {
//		return this.m_bDisplayUpdateOn;
//	}

	//************************************************************************//
	/**
	 * setter
	 */
	//************************************************************************//
//	public void set_Name				(String str)						{this.m_Name = str;}
	public void set_GroupParent(Entity_Directory group_Base) {
		this.m_GroupParent = group_Base;
	}
//	public void set_ArrayObject			(ArrayList<Object_Base> arryObj)	{this.m_arryObject = arryObj;}
//	public void set_ArrayGroup			(ArrayList<Group_Base> arryGrp)		{this.m_arryGroup = arryGrp;}

//	public void set_Material(Material mat) {
//		this.m_material = mat;
//	}
//
//	public void set_DrawPen(DrawPen dp) {
//		this.m_drawPen = dp;
//	}
////	public void set_PopupMenu_TreeNode	(PopupMenu_TreeNode pm)				{this.m_popup = pm;}
//
//	public void set_bDisplayUpdateOn(Boolean b) {
//		this.m_bDisplayUpdateOn = b;
//	}

	//************************************************************************//
	/**
	 * 全てのアイテムを削除する
	 */
	//************************************************************************//
	public void clear() {
		if (m_arry_entities != null) {
			m_arry_entities.clear();
		}
	}

	//************************************************************************//

	/**
	 * add（子Group）
	 */
	//************************************************************************//
//	public int add_group_child(
//		Group_Base		group_Base,		//I		追加するGroup
//		Boolean			bExistNoAdd		//I		同じGroupが既にある場合に追加するか判定(true:追加する、false:追加しない)
//	)
//	{
//		try
//		{
//			if(m_arryGroup == null)
//			{
//				m_arryGroup = new ArrayList<Group_Base>();
//			}
//
//			if(bExistNoAdd == false)
//			{
//				if(m_arryGroup.contains(group_Base) == true)
//				{
//					//値が既にある
//					return 1;
//				}
//			}
//
//			//追加
//			m_arryGroup.add(group_Base);
//
//			//子に親を登録
//			group_Base.set_GroupParent(this);
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//
//
//		return 0;
//	}
	//************************************************************************//
	/**
	 * add
	 */
	//************************************************************************//
	public int add_object(
//		TreeItem_Imp object,
		Entity_Imp object,
		Boolean bExistNoAdd) {
		try {
//			if(this.m_arryObject == null){
//				this.m_arryObject = new ArrayList<Object_Base>();
//			}
//
//			if(bExistNoAdd == false){
//				if(m_arryObject.contains(object) == true){
//					//値が既にある
//					return 1;
//				}
//			}
//
//			//追加
//			m_arryObject.add(object);

			if (this.m_arry_entities == null) {
				this.m_arry_entities = new ArrayList<>();
			}

			if (bExistNoAdd == false) {
				if (m_arry_entities.contains(object) == true) {
					//値が既にある
					return 1;
				}
			}

			//追加
			m_arry_entities.add(object);
		} catch (Exception ex) {
			util.debug_write_exception(ex);
		}

		return 0;
	}

	//************************************************************************//

	/**
	 * remove（子Group）
	 */
	//************************************************************************//
//	public int remove_group_child(Group_Base group_Base)
//	{
//		try
//		{
//			//指定されたGroupはnull
//			if(group_Base == null){
//				return 1;
//			}
//			//子Group配列がない
//			if(m_arryGroup == null){
//				return 2;
//			}
//			//子Groupはない
//			if(m_arryGroup.remove(group_Base) == false){
//				return 3;
//			}
//
//			//子の親をnull
//			group_Base.m_GroupParent = null;
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//
//
//		return 0;
//	}
//	public int remove_group_child(int nIndex)
//	{
//		try
//		{
//			return remove_group_child(this.m_arryGroup.get(nIndex));
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//
//		return 0;
//	}
	//************************************************************************//
	/**
	 * remove
	 */
	//************************************************************************//
	public int remove_object(Entity_Imp object)
//	public int remove_object(TreeItem_Imp object)
	{
		try {
			//指定されたObject_Baseはnull
			if (object == null) {
				return 1;
			}
			//子Object_Base配列がない
			if (m_arry_entities == null) {
				return 2;
			}
			//子Object_Baseはない
			if (m_arry_entities.remove(object) == false) {
				return 3;
			}
		} catch (Exception ex) {
			util.debug_write_exception(ex);
		}

		return 0;
	}

	public int remove_object(int nIndex) {
		return remove_object(this.m_arry_entities.get(nIndex));
	}

	//************************************************************************//
	/**
	 * Treeのアイテムを作る。
	 *
	 * @param
	 * @return
	 * @version
	 */
	//************************************************************************//
//	@Override
//	public TreeItem ask_JavafxTreeItem() {
//		try {
//			//名前からツリーノードを作成
//			TreeItem tree_item = new TreeItem<TreeItem_Imp>(this);
//
//			//子Groupがある場合、ツリーノードに追加
//			if (m_arry_entities != null) {
//				this.m_arry_entities.stream()
//					.filter(a -> a != null)
//					.forEach(a -> tree_item.getChildren().add(a.ask_JavafxTreeItem()));
//			}
//
////			//子Object_Baseがある場合、ツリーノードに追加
////			if(this.m_arryObject != null)
////			{
////				this.m_arryObject.stream()
////					.filter(a -> a != null)
////					.forEach(a -> tree_item.getChildren().add(a.ask_JavafxTreeItem()));
////				
////			}
//			return tree_item;
//		} catch (Exception ex) {
//			util.debug_write_exception(ex);
//		}
//
//		return null;
//	}
//	@Override
//	public void ask_JavafxTreeItem_children(boolean is_open, TreeItem tree_item)
//	{
//		try {
////			//名前からツリーノードを作成
////			TreeItem tree_item = new TreeItem<TreeItem_Imp>(this);
//			
//			Entity_Directory.create_JavafxTreeNode_utility_treeitem(
//				is_open,
//				tree_item,
//				m_arry_entities
//			);
//			
////			return tree_item;
//			
//		} catch (Exception ex) {
//			util.debug_write_exception(ex);
//		}
//
////		return null;
//	}	

//	//************************************************************************//
//	/**
//	*	GroupからTreeを作る。
//	*
//	*	@param
//	*	@return
//	*	@version
//	*/
//	//************************************************************************//
////	@Override
//	public void create_tree_nodes(
////		DefaultMutableTreeNode	parentTreeNode	//親のノード
//	)
//	{
////		try
////		{
////			//System.out.println(util.debug_ask_class_method_name());
////
////			//名前からツリーノードを作成
////			DefaultMutableTreeNode node = new DefaultMutableTreeNode(this.get_Name());
////			//								  DefaultMutableTreeNode(sTopNodeName);
////
////			//ポップアップメニューに対応するため、Groupを持たせる
////			node.setUserObject(this);
////
////			//親ツリーノードに追加
////			parentTreeNode.add(node);
////
////			//子Groupがある場合、ツリーノードに追加
////			if(m_arryGroup != null)
////			{
////				//子Groupのループ
////				for(int ic=0; ic<m_arryGroup.size(); ic++)
////				{
////					m_arryGroup.get(ic).create_tree_nodes(node);
////				}
////			}
////
////			//子Object_Baseがある場合、ツリーノードに追加
////			if(this.m_arryObject != null)
////			{
////				//子Object_Baseのループ
////				for(int ic=0; ic<m_arryObject.size(); ic++)
////				{
////					m_arryObject.get(ic).create_tree_nodes(node);
////				}
////			}
////		}
////		catch (Exception ex)
////		{
////			util.debug_write_exception(ex);
////		}
//	}
	//************************************************************************//
	/**
	 * Mapから指定されたObjectのクラス名があるか検索。あれば返す。 
	 * なければ内部的に作成してその配列を返す。
	 *
	 * @return
	 * @return
	 */
	//************************************************************************//
	private ArrayList<Entity_Imp> find_or_create( //		Map<String, ArrayList<Object_Base>> 	mapObject,	//IO
		//		Entity_ 							object		//I
		) {
//		try
//		{
//			//System.out.println(util.debug_ask_class_method_name());
//
//			//クラス名を取得
//			String sClassObjName = object.ask_ClassObjName();
//
//			//検索
//			ArrayList<Object_Base>	arryObj	= mapObject.get(sClassObjName);	//キーから配列を取得
//
//			//ある場合はそのまま返す
//			if(arryObj != null)
//			{
//				return arryObj;
//			}
//
//			//ない場合は新たに作る
//			ArrayList<Object_Base> arryObjBuf = new ArrayList<Object_Base>();
//
//			//追加
//			mapObject.put(sClassObjName, arryObjBuf);
//
//			return arryObjBuf;
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}

		return null;
	}

	//************************************************************************//
	/**
	 * 読み込みデータを実体化
	 */
	//************************************************************************//
//	@Override	//
//	public void read_data(
//		XmlData			xmlData,		//各オブジェクト形式のXML(例:<Group_Base>1,XXX \n 2,XXX \n ･･･ </Group_Base>)
//		FileInputOutput	dataFileIO,		//生成したオブジェクトを全て格納
//		Group_Base		topGroup		//Xmlオプションに"top"とあるものはTopのGroupにぶら下がる
//	)
//	{
//		/////////////////////
//		//	idを取得
//		/////////////////////
//		String id = xmlData.search_option_value("id");
//
//		//////////////////////////////////////////////////////
//		//	idに対応したオブジェクト実体を返す
//		//////////////////////////////////////////////////////
//		Group_Base groupCurrent = dataFileIO.search_Group_Base(id);
//		if(groupCurrent == null)
//		{
//			return;
//		}
//
//		//////////////////////////////
//		//	フォーマットの型を準備
//		//////////////////////////////
//		String					stringBuf		= null;
//		Group_Base				groupParent		= null;
//		ArrayList<Object_Base>	arryObjBase		= new ArrayList<Object_Base>();
//		ArrayList<Group_Base>	arryGroupBase	= new ArrayList<Group_Base>();
//		Material				materialBuf		= null;
//		DrawPen					drawPenBuf		= null;
//
//		/////////////////////////////////////
//		//	Group_Baseデータ読み込みモード
//		/////////////////////////////////////
//		ArrayList<String>	sData		= new ArrayList<String>();
//		ArrayList<String>	elements	= xmlData.get_elements();
//		int nSize = elements.size();
//		for(int ic=0; ic<nSize; ic++)
//		{
//			//1行読み込み
//			Integer nFormNumber = dataFileIO.read_one_line(
//				elements.get(ic),
//				sData
//			);
//
//			//フォーマット識別番号がない場合は飛ばす
//			if(nFormNumber == null)
//			{
//				continue;
//			}
//
//			////////////////////////////////////
//			//	フォーマット
//			//	<Group_Base id=XXXXX>
//			//		0,	String	※name
//			//		1,	GroupParent
//			//		2,	ArrayList<Object_Base>
//			//		3,	ArrayList<Group_Base>
//			//		4,	Material
//			//		5,	DrawPen
//			//	</Object_MeshTetra4>
//			////////////////////////////////////
//			switch(nFormNumber)
//			{
//				////////////////////////
//				//	0,	String
//				////////////////////////
//				case 0:
//					stringBuf	= sData.get(1);
//					break;
//				////////////////////////
//				//	1,	Group_Base
//				////////////////////////
//				case 1:
//					groupParent	= dataFileIO.search_Group_Base(sData.get(1));
//					break;
//				/////////////////////////////////
//				//	2,	ArrayList<Object_Base>
//				/////////////////////////////////
//				case 2:
//					for(int nc=1; nc<sData.size(); nc++)
//					{
//						arryObjBase.add( dataFileIO.search_Object_Base(sData.get(nc)) );
//					}
//					break;
//				/////////////////////////////////
//				//	3,	ArrayList<Group_Base>
//				/////////////////////////////////
//				case 3:
//					for(int nc=1; nc<sData.size(); nc++)
//					{
//						arryGroupBase.add( dataFileIO.search_Group_Base(sData.get(nc)) );
//					}
//					break;
//				////////////////////////
//				//	4,	Material
//				////////////////////////
//				case 4:
//					materialBuf = dataFileIO.search_Material(sData.get(1));
//					break;
//				////////////////////////
//				//	5,	DrawPen
//				////////////////////////
//				case 5:
//					drawPenBuf = dataFileIO.search_DrawPen(sData.get(1));
//					break;
//				////////////////////////
//				//	フォーマット外の値
//				////////////////////////
//				default:
//					break;
//			}
//		}
//		////////////////////////////////////////////////////
//		//	取得データを、メンバ変数に格納
//		////////////////////////////////////////////////////
//		if(stringBuf != null)		groupCurrent.set_Name(stringBuf);
////		if(groupParent != null)		groupCurrent.set_GroupParent(groupParent);
////		if(arryObjBase != null)		groupCurrent.set_ArrayObject(arryObjBase);
////		if(arryGroupBase != null)	groupCurrent.set_ArrayGroup(arryGroupBase);
//		if(materialBuf != null)		groupCurrent.set_Material(materialBuf);
//		if(drawPenBuf != null)		groupCurrent.set_DrawPen(drawPenBuf);
//
//
//		/////////////////////////
//		//	topオプションを検索
//		/////////////////////////
//		if(xmlData.search_option("top") != null)
//		{
//			//TopのGroupに追加
//			topGroup.add_object	(
//				groupCurrent,	//
//				false			//既に追加されている場合でも追加する
//			);
//		}
//
//		return;
//	}
	//************************************************************************//
	/**
	 * 読み込みファイルデータを実体化する
	 *
	 * @param
	 * @return
	 * @version
	 */
	//************************************************************************//
////	@Override
//	public void factory_and_add(
//		String			sID,
//		FileInputOutput	fileIO
//	)
//	{
//		//Group_Base objBase = new Group_Base();
//		fileIO.add(sID, new Group_Base());
//	}
	//************************************************************************//
	/**
	 * ファイルに書き出し
	 *
	 * @param
	 * @return	id
	 * @version
	 */
	//************************************************************************//
//	@Override
//	public String write_data(
//		XmlData				xmlDataParent,	//OUT	親のXmlData。このXmlDataの子として登録する。
//		FileInputOutput		poolFileIO		//IN	書き込み時の全てのXmlDataを持つ。
//	)
//	{
//		/////////////////////////////////////////
//		//	XmlDataが既にあるかチェック
//		/////////////////////////////////////////
//		XmlData[]	xmlBuf	= {null};
//		String[]	idBuf	= {null};
//		boolean bSearchExist = poolFileIO.search_or_create_xml(
//			xmlDataParent,	//IO	Xmlデータ。親に追加する。
//			this,			//IN	Xmlデータの元となるオブジェクト
//			poolFileIO,		//IO	Xmlデータを全て格納
//			xmlBuf,			//OUT	検索、または作成したXmlData
//			idBuf			//OUT	検索、または作成されたXmlData
//		);
//		if(bSearchExist == true)
//		{
//			//既にあるのでそのまま返す
//			return idBuf[0];
//		}
//
//		///// フォーマット識別番号を全て書き出し /////
//		XmlData xmlDataMySelf = xmlBuf[0];
//		String	idMySelf = idBuf[0];
//		for(int nIndex=0; true; nIndex++)
//		{
//			////////////////////////////////////
//			//	フォーマット
//			//	<Group_Base id=XXXXX>
//			//		0,	String	※name
//			//		1,	GroupParent
//			//		2,	ArrayList<Object_Base>
//			//		3,	ArrayList<Group_Base>
//			//		4,	Material
//			//		5,	DrawPen
//			//	</Object_MeshTetra4>
//			////////////////////////////////////
//			String sFormNumber		= String.format("%d",nIndex);
//			String sWriteLineBuf	= null;
//			switch(nIndex)
//			{
//				////////////////////////
//				//	0,	String
//				////////////////////////
//				case 0:
//					//要素を追加
//					if(this.m_Name != null)	xmlDataMySelf.add_element(sFormNumber + "," + this.m_Name);
//					//xmlDataMySelf.add_element(sWriteLineBuf + "," + this.m_Name);
//					break;
//				////////////////////////
//				//	1,	Group_Base
//				////////////////////////
//				case 1:
//					//書き出し文字列作成、また子のXmlData作成
//					sWriteLineBuf = poolFileIO.ask_write_line_and_create_xml(
//						xmlDataMySelf,			//OUT	親となるXmlデータ。これに追加する。
//						sWriteLineBuf,			//OUT	親となるXmlデータのデータ書き出し文字列。
//						this.m_GroupParent,		//IN	子となるXmlデータの元となるオブジェクト。
//						poolFileIO				//IN	Xmlデータを全て格納
//					);
//					//要素を追加
//					if(sWriteLineBuf != null)	xmlDataMySelf.add_element(sFormNumber + sWriteLineBuf);
//
//					break;
//				/////////////////////////////////
//				//	2,	ArrayList<Object_Base>
//				/////////////////////////////////
//				case 2:
//					if(this.m_arryObject == null) break;
//					for(int nc=0; nc<this.m_arryObject.size(); nc++)
//					{
//						//書き出し文字列作成、また子のXmlData作成
//						sWriteLineBuf = poolFileIO.ask_write_line_and_create_xml(
//							xmlDataMySelf,			//OUT	親となるXmlデータ。これに追加する。
//							sWriteLineBuf,			//OUT	親となるXmlデータのデータ書き出し文字列。
//							m_arryObject.get(nc),	//IN	子となるXmlデータの元となるオブジェクト。
//							poolFileIO				//IN	Xmlデータを全て格納
//						);
//					}
//					//要素を追加
//					if(sWriteLineBuf != null)	xmlDataMySelf.add_element(sFormNumber + sWriteLineBuf);
//
//					break;
//				/////////////////////////////////
//				//	3,	ArrayList<Group_Base>
//				/////////////////////////////////
//				case 3:
//					if(this.m_arryGroup == null) break;
//					for(int nc=0; nc<this.m_arryGroup.size(); nc++)
//					{
//						//書き出し文字列作成、また子のXmlData作成
//						sWriteLineBuf = poolFileIO.ask_write_line_and_create_xml(
//							xmlDataMySelf,			//OUT	親となるXmlデータ。これに追加する。
//							sWriteLineBuf,			//OUT	親となるXmlデータのデータ書き出し文字列。
//							m_arryGroup.get(nc),	//IN	子となるXmlデータの元となるオブジェクト。
//							poolFileIO				//IN	Xmlデータを全て格納
//						);
//					}
//					//要素を追加
//					if(sWriteLineBuf != null)	xmlDataMySelf.add_element(sFormNumber + sWriteLineBuf);
//
//					break;
//				////////////////////////
//				//	4,	Material
//				////////////////////////
//				case 4:
//					//書き出し文字列作成、また子のXmlData作成
//					sWriteLineBuf = poolFileIO.ask_write_line_and_create_xml
//					(
//						xmlDataMySelf,			//OUT	親となるXmlデータ。これに追加する。
//						sWriteLineBuf,			//OUT	親となるXmlデータのデータ書き出し文字列。
//						this.m_material,		//IN	子となるXmlデータの元となるオブジェクト。
//						poolFileIO				//IN	Xmlデータを全て格納
//					);
//					//要素を追加
//					if(sWriteLineBuf != null)	xmlDataMySelf.add_element(sFormNumber + sWriteLineBuf);
//
//					break;
//				////////////////////////
//				//	5,	DrawPen
//				////////////////////////
//				case 5:
//					//書き出し文字列作成、また子のXmlData作成
//					sWriteLineBuf = poolFileIO.ask_write_line_and_create_xml
//					(
//						xmlDataMySelf,			//OUT	親となるXmlデータ。これに追加する。
//						sWriteLineBuf,			//OUT	親となるXmlデータのデータ書き出し文字列。
//						this.m_drawPen,			//IN	子となるXmlデータの元となるオブジェクト。
//						poolFileIO				//IN	Xmlデータを全て格納
//					);
//					//要素を追加
//					if(sWriteLineBuf != null)	xmlDataMySelf.add_element(sFormNumber + sWriteLineBuf);
//
//					break;
//				////////////////////////
//				//	フォーマット外の値
//				////////////////////////
//				default:
//					return idMySelf;
//					//break;
//			}
//		}
//
////		return null;
//	}
	//************************************************************************//
	/**
	 * ピック円柱内にあるGroup内のオブジェクトを取得
	 *
	 * @param
	 * @return
	 * @version
	 */
	//************************************************************************//
	public void ask_isTouch_In_Group( //		double[]				spoint,				//I		ピック円柱の始点
		//		double[]				epoint,				//I		ピック円柱の終点
		//		Filter					filter,				//I		選択フィルタ
		//		double					tolerance,			//I		ピック円柱の半径
		//		ArrayList<PickedItem>	arryCandiPickItem	//O		ピック円柱内にあるオブジェクト
		) {
//		//Group内の子Groupループ
//		if(this.m_arryGroup != null)
//		{
//			for(int ic=0; ic<m_arryGroup.size(); ic++)
//			{
//				//ピックされているオブジェクトがあるかGroupに聞いて回る
//				m_arryGroup.get(ic).ask_isTouch_In_Group(
//					spoint,				//I		ピック円柱の始点
//					epoint,				//I		ピック円柱の終点
//					filter,				//I		選択フィルタ
//					tolerance,			//I		ピック円柱の半径
//					arryCandiPickItem	//O		ピック円柱内にあるオブジェクト
//				);
//			}
//		}
//
//		//Group内の子Object_Baseのループ
//		if(this.m_arryObject != null)
//		{
//			PickedItem pickedItem = null;
//			for(int ic=0; ic<m_arryObject.size(); ic++)
//			{
//				//ピックされているオブジェクトがあるかObjectに聞いて回る
//				pickedItem = m_arryObject.get(ic).ask_isTouch(
//					spoint,				//I		ピック円柱の始点
//					epoint,				//I		ピック円柱の終点
//					filter,				//I		選択フィルタ
//					tolerance			//I		ピック円柱の半径
//				);
//				if(pickedItem  != null)
//				{
//					//ピック円柱内にあるのであれば追加
//					arryCandiPickItem.add(pickedItem);
//				}
//			}
//		}
	}

//	//************************************************************************//
//	/**
//	*	Treeノードの名前を取得
//	*
//	*	@param
//	*	@return
//	*	@version
//	*/
//	//************************************************************************//
////	@Override
//	public String ask_tree_name(){return this.m_Name;};
	//************************************************************************//
	/**
	 * Treeノードのアイコンを取得
	 *
	 * @param
	 * @return
	 * @version
	 */
	//************************************************************************//
////	@Override
//	public Icon ask_tree_icon()
//	{
//		//com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
//		//Icon icon = MetalIconFactory.getFileChooserDetailViewIcon();	//OK
//
//		/*
//		javax.swing.plaf.metal.MetalLookAndFeel
//		com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
//		com.sun.java.swing.plaf.motif.MotifLookAndFeel
//		com.sun.java.swing.plaf.windows.WindowsLookAndFeel
//		com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
//			Icon icon = MetalIconFactory.getFileChooserDetailViewIcon();	//OK
//		*/
//
//		//Icon icon = com.sun.java.swing.plaf.windows.WindowsIconFactory.getCheckBoxIcon();
//
//
////		Icon icon = WindowsIconFactory.getCheckBoxIcon();
////		Icon icon = WindowsIconFactory.createFrameIconifyIcon();
//		//Icon icon3 = Windows
//		//String sIconPth = ;
//
//		//Icon icon3 = javax.swing.plaf.basic.BasicIconFactory.getCheckBoxIcon();
//
//
//		//return static_m_Icon;
//		//ImageIcon icon = new ImageIcon("D:\\workspace\\Eclipse_workspace\\pic\\stop.png");
//		ImageIcon icon = null;
//		return icon;
//
//	};
//	//************************************************************************//
//	/**
//	*	3D描画用のオブジェクトグループを追加
//	*
//	*	@param
//	*	@return
//	*	@version
//	*/
//	//************************************************************************//
//	public void addJavafxGroup(Group group)
//	{
//        //子のJavafx Groupを作成
//        Group group_node = new Group();
//        this.m_javafx_group_myself = group_node;
//        
//        //親のJavafx Groupに追加
//        group.getChildren().add(group_node);
//        
//        //子のJavafx Groupにオブジェクトを追加
//		if(this.get_ArrayObject() != null)
//		{
//			for(Entity_Base obj_base : this.get_ArrayObject())
//			{
//				if(obj_base == null) continue;
////				group.getChildren().add(obj_base.getJavafxGroup() );
//				group_node.getChildren().add(obj_base.ask_JavafxGroup() );
//           }
//		}
//
//        //子のJavafx Groupにグループベースを追加
//		if(this.get_ArrayGroup() != null)
//		{
//			for(Group_Base group_base : this.get_ArrayGroup())
//			{
//				if(group_base == null) continue;
////				group_base.addJavafxNode(group);
//				group_base.addJavafxGroup(group_node);
//			}
//		}
//	}
//	//************************************************************************//
//	/**
//	 * 3D描画用のオブジェクトグループを追加
//	 *
//	 * @param
//	 * @return
//	 * @version
//	 */
//	//************************************************************************//
//	public Group create_JavafxNode() {
//		//Groupは常に更新をtrueにしておく（暫定的）
//		//本当は、毎回下位のObjectに更新があるか聞かないといけない。
//		//…と思ったが、結局上の処理は更新があるか聞いていることになるから問題ない。
//		//恒久的な策となる。
////		super.setM_update_on(true);
//
//		//子のJavafx Groupを作成
//		Group group_node = new Group();
//		this.m_javafx_group_myself = group_node;
//
////		//子のJavafx Groupにオブジェクトを追加
////		if (this.m_arry_entities != null) {
////			this.m_arry_entities.stream()
////				.filter(a -> a != null)
////				.filter(a -> a instanceof Entity_Base)
////				.map(a -> (Entity_Base)a )
////				.forEach(a -> group_node.getChildren().add(a.ask_JavafxNode()));
////		}
//
//		return group_node;
//	}

	//************************************************************************//

	/**
	 * オブジェクトグループを削除
	 *
	 * @param
	 * @return
	 * @version
	 */
	//************************************************************************//
	public void deleteJavafxGroup(Group group) {
		if (this.m_javafx_group_myself != null) {
			group.getChildren().remove(this.m_javafx_group_myself);
			m_javafx_group_myself = null;
		}
	}
	
	//************************************************************************//
	/**
	 * Treeのアイテムを作るときの便利関数。
	 * いちいち各Objectで作るのが面倒なので。
	 *
	 * @param
	 * @return
	 * @version
	 */
	//************************************************************************//	
	static public void create_JavafxTreeNode_utility_treeitem(
		boolean						is_open,	//I
		TreeItem					tree_item,	//IO
		ArrayList<TreeItem_Imp> ...	arry_objs)	//I
	{
		try {
			//名前からツリーノードを作成
//			TreeItem tree_item = new TreeItem<TreeItem_Imp>(tree_item_parent);

			if(is_open == true){
				//ArrayのObject_Baseをツリーノードに追加
				if (arry_objs != null) {
					for(ArrayList<TreeItem_Imp> objs : arry_objs)
					objs.stream()
						.filter(a -> a != null)
//						.forEach(a -> tree_item.getChildren().add(a.create_JavafxTreeItem(false)));
						.forEach(a -> tree_item.getChildren().add(a.ask_JavafxTreeItem(false)));
				}
			}
			else{
				if (arry_objs != null) {
					//Object_Triangleがある。ダミーを入れてアイテム名の前に＋を出す。
					tree_item.getChildren().add(TreeItem_Imp.static_tree_item_dummy);
				}
			}

			return;
		} catch (Exception ex) {
			util.debug_write_exception(ex);
		}

		return;
	}
	static public void create_JavafxTreeNode_utility_treeitem(
		boolean				is_open,
		TreeItem			tree_item_parent,
		TreeItem_Imp ...	arry_objs)
	{
		create_JavafxTreeNode_utility_treeitem(
			is_open,
			tree_item_parent,
			(ArrayList<TreeItem_Imp>)Arrays.asList(arry_objs)
		);
	}

	//************************************************************************//
	/**
	 * Treeのアイテムを作るときの便利関数。
	 * いちいち各Objectで作るのが面倒なので。
	 *
	 * @param
	 * @return
	 * @version
	 */
	//************************************************************************//	
	static public void create_JavafxTreeNode_utility_entity(
		boolean			is_open,
		TreeItem		tree_item_parent,
		Entity_Imp ...	arry_objs)
	{
		ArrayList<Entity_Imp> arry = new ArrayList<>(arry_objs.length);
		for(Entity_Imp obj : arry_objs){
			arry.add(obj);
		}
		Entity_Directory.create_JavafxTreeNode_utility_entity(
			is_open,
			tree_item_parent,
			arry
		);
	}
	static public void create_JavafxTreeNode_utility_entity(
		boolean									is_open,
		TreeItem								tree_item_parent,
		ArrayList<? extends Entity_Imp> ...	arry_objs)
	{
		ArrayList<TreeItem_Imp> arry = new ArrayList<>(arry_objs.length);
		
		for(ArrayList<? extends Entity_Imp> arry_obj_buf : arry_objs){
			for(Entity_Imp obj : arry_obj_buf){
				if(obj instanceof TreeItem_Imp){
					arry.add((TreeItem_Imp)obj);
				};
			}
		}
		create_JavafxTreeNode_utility_treeitem(
			is_open,
			tree_item_parent,
			arry
		);
	}
	/////////////////////////////////////////////////////////////////////////
//	//************************************************************************//
//	/**
//	 * Treeのアイテムを作るときの便利関数。
//	 * いちいち各Objectで作るのが面倒なので。
//	 *
//	 * @param
//	 * @return
//	 * @version
//	 */
//	//************************************************************************//	
//	static public void ask_JavafxTreeNode_utility_obj(
//		boolean			is_open,
//		TreeItem		tree_item_parent,
//		Entity_Base ...	arry_objs)
//	{
//		ArrayList<TreeItem_Imp> arry = new ArrayList<>(arry_objs.length);
//		for(Entity_Base obj : arry_objs){
//			arry.add(obj);
//		}
//		create_JavafxTreeNode_utility_treeitem(
//			is_open,
//			tree_item_parent,
//			arry
//		);
//	}
//	static public void ask_JavafxTreeNode_utility_obj(
//		boolean									is_open,
//		TreeItem								tree_item_parent,
//		ArrayList<? extends Entity_Base> ...	arry_objs)
//	{
//		ArrayList<TreeItem_Imp> arry = new ArrayList<>(arry_objs.length);
//		for(ArrayList<? extends Entity_Base> arry_obj_buf : arry_objs){
//			for(Entity_Base obj : arry_obj_buf){
//				arry.add(obj);
//			}
//		}
//		create_JavafxTreeNode_utility_treeitem(
//			is_open,
//			tree_item_parent,
//			arry
//		);
//		
//		return;
//	}


//	@Override
	public TreeItem create_JavafxTreeItem(boolean is_open) {
		
		TreeItem treeitem = new TreeItem(get_name());
		
		if(m_arry_entities != null){
			Entity_Directory.create_JavafxTreeNode_utility_entity(
				is_open,
				treeitem,
				this.m_arry_entities
			);
		}
		
		return treeitem;
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
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override
	public Node ask_JavafxNode(){
		//毎回下位のObjectに更新があるか確認。
		Group group_node = new Group();

		//子のJavafx Groupにオブジェクトを追加
		if (this.m_arry_entities != null) {
			this.m_arry_entities.stream()
				.filter(a -> a != null)
				.filter(a -> a instanceof Display_3D_Object_Imp)
				.forEach(a -> group_node.getChildren().add(((Display_3D_Object_Imp)a).ask_JavafxNode()));
		}

		return group_node;
	}
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
