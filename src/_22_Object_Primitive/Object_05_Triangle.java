package _22_Object_Primitive;

import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _20_Object_Template.TreeItem_Imp;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import mathmatic.Mathmatic;
import _20_Object_Template.Entity_Imp;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;

public class Object_05_Triangle
//	extends Entity_Base
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
//	implements FileIO
{
	private String m_name;
	private Node m_javafxnode = null;
	private boolean m_update_on = true;
	//点
//	Object_03_Point[]	m_RelatedTriPnts = {null, null, null};
	ArrayList<Object_03_Point>	m_RelatedTriPnts = new ArrayList<>(3);

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Object_05_Triangle()
	{
//		super("デフォルト三角形");
	}
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_05_Triangle(
		String name,
		Object_03_Point[] triPnt
	)
	{
//		super("デフォルト三角形");
		//点を格納
		m_RelatedTriPnts.addAll(Arrays.asList(triPnt));

		//点に自身のTriFaceを登録
		for(int ic=0; ic<3; ic++)
		{
//			m_RelatedTriPnts[ic].add(this);
			m_RelatedTriPnts.get(ic).add(this);
		}
	}
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Object_05_Triangle(
		String			name,
		Object_03_Point	triPnt1,
		Object_03_Point	triPnt2,
		Object_03_Point	triPnt3
	)
	{
		this.m_name = name;
		
		Object_03_Point[] objs = {triPnt1, triPnt2, triPnt3};

		//点を格納
		for(int ic=0; ic<3; ic++){
			m_RelatedTriPnts.add(ic, objs[ic]);
		}

		//点に自身のTriFaceを登録
		for(int ic=0; ic<3; ic++){
			m_RelatedTriPnts.get(ic).add(this);
		}
	}

	//************************************************************************//
	/**
	*	setter
	*/
	//************************************************************************//
	public void set_TriPoints(Object_03_Point[] triPnt)
	{
		//以前の点から自身のTriFaceを登録抹消
		for(int ic=0; ic<3; ic++)
		{
			if(m_RelatedTriPnts.get(ic) != null)
			{
				m_RelatedTriPnts.get(ic).delete(this);
			}
		}

		//点を格納
		for(int ic=0; ic<3; ic++){
			m_RelatedTriPnts.add(ic, triPnt[ic]);
		}

		//点に自身のTriFaceを登録
		for(int ic=0; ic<3; ic++){
			m_RelatedTriPnts.get(ic).add(this);
		}
	}
	
	//************************************************************************//
	/**
	*	getter
	*/
	//************************************************************************//
//	public Object_03_Point[] get_tri_points(){return this.m_RelatedTriPnts;}
	//************************************************************************//
	/**
	*	三角形の法線方向を算出
	*/
	//************************************************************************//
	public void ask_normal(double[] normal)
	{
		///// 3点から点の位置を取得 /////
		double[] dPos1 = {0.0, 0.0, 0.0};
		double[] dPos2 = {0.0, 0.0, 0.0};
		double[] dPos3 = {0.0, 0.0, 0.0};
		dPos1 = m_RelatedTriPnts.get(0).get_position();
		dPos2 = m_RelatedTriPnts.get(1).get_position();
		dPos3 = m_RelatedTriPnts.get(2).get_position();
		
		///// 外積を法線ベクトルとする /////
		Mathmatic.calc_OuterProduct_unit(dPos1, dPos2, dPos3, normal);
	}
//	//************************************************************************//
//	/**
//	 *	読み込みデータを実体化
//	 */
//	//************************************************************************//
//	@Override
//	public void read_data(
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
//	public void factory_and_add(
//		String			id,		//保存、読み込み時のオブジェクトID
//		FileInputOutput	fileIO	//データプール
//	)
//	{
//		fileIO.add(id, new Object_05_Triangle());
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
//	public String write_data(
//		XmlData				xmlDataMySelf,	//OUT	親のXmlData。このXmlDataの子として登録する。
//		FileInputOutput		dataFileIO		//IN	書き込み時の全てのXmlDataを持つ。
//	)
//	{
//		return null;
//
//	}

	/***************************************************************************
	 * 
	 * Override
	 * オブジェクトを生成する。
	 * 
	 * @return 
	 */
	public Node create_JavafxNode()
	{
//		super.setM_update_on(false);
		
		double[] point0 = this.m_RelatedTriPnts.get(0).get_position();
		double[] point1 = this.m_RelatedTriPnts.get(1).get_position();
		double[] point2 = this.m_RelatedTriPnts.get(2).get_position();
		
		//頂点を設定
		float[] mesh_points = new float[3*3];
		for(int ic=0; ic<3; ic++){
			mesh_points[0*3+ic] = (float)point0[ic];
			mesh_points[1*3+ic] = (float)point1[ic];
			mesh_points[2*3+ic] = (float)point2[ic];
		}
		//面の構成を設定
		int[] mesh_faces = {0,0, 1,0, 2,0};		
		
		TriangleMesh mesh_triangle = new TriangleMesh();
		mesh_triangle.getPoints().addAll(mesh_points);
		mesh_triangle.getFaces().addAll(mesh_faces);
		mesh_triangle.getTexCoords().addAll(0,0);
		MeshView triangle = new MeshView(mesh_triangle);
		triangle.setDrawMode(DrawMode.FILL);
//		triangle.setDrawMode(DrawMode.LINE);
		triangle.setCullFace(CullFace.NONE);
		
        PhongMaterial material_red = new PhongMaterial();
        material_red.setDiffuseColor(Color.RED);
        triangle.setMaterial(material_red);
		
		Group group = new Group();		
		group.getChildren().add(triangle);
		
		//テスト：点も出力
		group.getChildren().addAll(
			this.m_RelatedTriPnts.get(0).ask_JavafxNode(),
			this.m_RelatedTriPnts.get(1).ask_JavafxNode(),
			this.m_RelatedTriPnts.get(2).ask_JavafxNode()
		);
		
		this.m_javafxnode = group;
		return group;
	}

//	@Override
//	public void observer_receive_update(Entity_Base obj, UpdateType update_type) {
////		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//
//		//座標系が変わった
//		if(update_type == Entity_Base.UpdateType.CoordinateSystem_Change){
//			for(Object_03_Point point : this.m_RelatedTriPnts){
//				point.observer_receive_update(obj, update_type);
//			}
//		}
//	}
//
//	@Override
//	public void ask_JavafxTreeItem_children(boolean is_open, TreeItem tree_item)
//	{
//		try
//		{
////			//名前からツリーノードを作成
////			TreeItem tree_item = new TreeItem<>(this);
//			
//			Entity_Directory.ask_JavafxTreeNode_utility_obj(is_open, tree_item, this.m_RelatedTriPnts);
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
	@Override
	public void set_name(String value) {this.m_name = value;}
	@Override
	public String get_name() {return this.m_name;}
}