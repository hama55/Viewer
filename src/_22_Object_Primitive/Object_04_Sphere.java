package _22_Object_Primitive;

import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _20_Object_Template.TreeItem_Imp;

import utility.util;

import java.util.ArrayList;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Translate;

import _20_Object_Template.Entity_Imp;
import _40_Value.Value_Double;
import _23_Object_Iroiro.Object_10_ReImAxisDefine;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Object_04_Sphere
//	extends Entity_Base
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
//	implements FileIO
{
	private String m_name;
	final int nDivV = 30;
	final int nDivH = 30;

	private Object_03_Point	m_dCenterXYZ = new Object_03_Point();	//中心の座標値X,Y,Z
	private Value_Double   m_Radius     = null;		//半径

	private Node m_javafxnode = null;
	private boolean m_update_on = true;
	//************************************************************************//
	/**
	*	Constructor
	*/
	//************************************************************************//
	public Object_04_Sphere()
	{
//		super(null);
	}
	public Object_04_Sphere(
		String name,
		Value_Double[] d_position_xyz,
		Value_Double   Radius
	)
	{
		this.m_name = name;
		try
		{
			//System.out.println(util.debug_ask_class_method_name());
//			m_dCenterXYZ[0] = d_position_xyz[0];
//			m_dCenterXYZ[1] = d_position_xyz[1];
//			m_dCenterXYZ[2] = d_position_xyz[2];
			
//			m_Radius = Radius;
			Object_10_ReImAxisDefine.AxisType[] axis = {
				Object_10_ReImAxisDefine.AxisType.X_REAL,
				Object_10_ReImAxisDefine.AxisType.Y_REAL,
				Object_10_ReImAxisDefine.AxisType.Z_REAL,
			};
			
			double[] position =  {
				d_position_xyz[0].get_double(),
				d_position_xyz[1].get_double(),
				d_position_xyz[2].get_double()
			};
			m_dCenterXYZ.set_position(axis, position);
			m_Radius = Radius;
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
	}
//	public Object_04_Sphere(
//			String sName,
//			double[] CenterXYZ,
//			double   Radius
//			)
//	{
//		super(sName);
//		try
//		{
//			//System.out.println(util.debug_ask_class_method_name());
//			m_dCenterXYZ[0] = new Value_Double(CenterXYZ[0]);
//			m_dCenterXYZ[1] = new Value_Double(CenterXYZ[1]);
//			m_dCenterXYZ[2] = new Value_Double(CenterXYZ[2]);
//			m_Radius = new Value_Double(Radius);
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//	}
//	public Object_04_Sphere(
//			String sName,
//			double[] CenterXYZ,
//			Value_Double   Radius
//			)
//	{
//		super(sName);
//		try
//		{
//			//System.out.println(util.debug_ask_class_method_name());
//			m_dCenterXYZ[0] = new Value_Double(CenterXYZ[0]);
//			m_dCenterXYZ[1] = new Value_Double(CenterXYZ[1]);
//			m_dCenterXYZ[2] = new Value_Double(CenterXYZ[2]);
//			m_Radius = Radius;
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//	}
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
//	public void get_center(Value_Double[] dtValues){for(int ic=0; ic<3; ic++){dtValues[ic] = this.m_dCenterXYZ[ic];}}
//	public void get_center(double[] dValues){for(int ic=0; ic<3; ic++){dValues[ic] = this.m_dCenterXYZ[ic].get_double();}}
	public Value_Double get_radius(){return this.m_Radius;}

	//************************************************************************//
	/**
	 *	球を表示するDisplayListを作成
	 *
	 *	@return	true 成功
	 *	@return	false 失敗
	 */
	//************************************************************************//
//	@Override
//	public void create_object_displaylist_all(
//			OpenGL_Play				OGL, 					//I		OpenGLクラス
//			Material				material,				//I 	材質（色、反射率）
//			DrawPen					drawPen,				//I 	描画ペン（線種、フォント）
//			ArrayList<Object_Base>	arryObject,				//I 	同じ表示方式のオブジェクト
//			ArrayList<Integer>		arryDispList_Disp,		//OUT
//			ArrayList<Integer>		arryDispList_Non_Disp	//OUT
//			)
//	{
//		try
//		{
//			System.out.println(util.debug_ask_class_method_name());
//
//			///////////////////////////////////////
//			// OpenGLを取得
//			///////////////////////////////////////
//			GL		gl = OGL.get_gl();
//			GLUT	glut = OGL.get_glut();
//
//			///////////////////////////////////////
//			//	材質のDisplayListを作成
//			///////////////////////////////////////
//			int nDispMaterial = material.create_material(gl);
//			//材質を非描画に保存
//			arryDispList_Non_Disp.add(nDispMaterial);
//
//			/////////////////////////////////////////////
//			//	同じ半径を揃える
//			/////////////////////////////////////////////
//			Map<Double, ArrayList<Object_Sphere>> mapObject_Radius = new HashMap<Double, ArrayList<Object_Sphere>>();
//			//全オブジェクトをループ
//			for(int ic=0; ic<arryObject.size(); ic++)
//			{
//				Object_04_Sphere objSphereBuf = (Object_04_Sphere)arryObject.get(ic);
//
//				//既にあるか問い合わせ(ある場合はその配列を返す。ない場合は新しく作ってその配列を返す)
//				ArrayList<Object_Sphere> arryBuf = this.find_or_create(mapObject_Radius, objSphereBuf);
//
//				//既存の配列に追加
//				arryBuf.add(objSphereBuf);
//			}
//
//			///////////////////////////////////////
//			//	半径毎に球を描画
//			///////////////////////////////////////
//	        Set keySet = mapObject_Radius.keySet();  //すべてのキー値を取得
//	        Iterator keyIte = keySet.iterator();
//	        //ループ。反復子iteratorによる　キー　取得
//			while(keyIte.hasNext())
//			{
//				//Objectのタイプを取得
//				Double						dDRadius			= (Double)keyIte.next();
//				ArrayList<Object_Sphere>	arryRadius_Objects	= (ArrayList<Object_Sphere>)mapObject_Radius.get(dDRadius);	//キーより配列を取得
//
//				//半径を取得
//				double dRadius = dDRadius.doubleValue();
//
//				///////////////////////////////////////
//				//	球のDisplayListを作成（位置は原点）
//				///////////////////////////////////////
//				//ディスプレイリスト初め
//				int nDispSphere = -1;
//				//ディスプレイリストの番号を取得
//				nDispSphere = gl.glGenLists(1);
//				gl.glNewList(nDispSphere,GL.GL_COMPILE); //コンパイルのみ
//				//球を作成
//				glut.glutSolidSphere(dRadius, nDivV, nDivH);
//				//ディスプレイリスト終了
//				gl.glEndList();
//
//				//////////////////////////////////////////////
//				//	各座標位置に球を描画するDisplayListを作成
//				//////////////////////////////////////////////
//				//ディスプレイリスト初め
//				int nDispSphereRadius = -1;
//				//ディスプレイリストの番号を取得
//				nDispSphereRadius = gl.glGenLists(1);
//				gl.glNewList(nDispSphereRadius,GL.GL_COMPILE); //コンパイルのみ
//					//材質のDisplayList呼び出し
//					gl.glCallList(nDispMaterial);
//					//各座標位置に描画
//					for(int nc=0; nc<arryRadius_Objects.size(); nc++)
//					{
//						Object_04_Sphere objSphereBuf = arryRadius_Objects.get(nc);
//
//						//各オブジェクトに原点移動させる
//						objSphereBuf.move_sphere_origin(
//							gl,
//							nDispSphere		//球オブジェクトのDisplayList
//						);
//					}
//				//ディスプレイリスト終了
//				gl.glEndList();
//
//				//球単体を非描画に保存
//				arryDispList_Non_Disp.add(nDispSphere);
//
//				//全球を描画に保存
//				arryDispList_Disp.add(nDispSphereRadius);
//			}
//
//			return;
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//	}

	//************************************************************************//
	/**
	 *	Mapから指定されたObjectのクラス名があるか検索。あれば返す。
	 *	なければ内部的に作成してその配列を返す。
	 *
	 *	@return
	 *	@return
	 */
	//************************************************************************//
	private ArrayList<Object_04_Sphere> find_or_create(
		Map<Double, ArrayList<Object_04_Sphere>> 	mapObject,	//IO
		Object_04_Sphere 							objSphere	//I
		)
	{
		try
		{
			//System.out.println(util.debug_ask_class_method_name());

			//半径を取得
			Double dRadius = objSphere.get_radius().get_double();

			//検索
			ArrayList<Object_04_Sphere> arryObj	= mapObject.get(dRadius);	//キーより配列を取得

			//ある場合はそのまま返す
			if(arryObj != null)
			{
				return arryObj;
			}

			//ない場合は新たに作る
			ArrayList<Object_04_Sphere> arryObjBuf = new ArrayList<Object_04_Sphere>();

			//追加
			mapObject.put(dRadius, arryObjBuf);

			return arryObjBuf;
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}

		return null;
	}

	//************************************************************************//
	/**
	 *	球を作成
	 *
	 *	@return	
	 *	@return	
	 */
	//************************************************************************//
	public Node create_JavafxNode()
	{
		Sphere sphere = new Sphere();
		//半径
		sphere.setRadius(this.m_Radius.get_double());
		//位置
		double[] position  = this.m_dCenterXYZ.ask_position();
		sphere.getTransforms().add(new Translate(position[0], position[1], position[2]));		
		
		//色
        final PhongMaterial material_red = new PhongMaterial();
        material_red.setDiffuseColor(Color.RED);
        sphere.setMaterial(material_red);
		
		Group group = new Group();
		group.getChildren().add(sphere);
		
		this.m_javafxnode = group;
		return group;
	}

	//************************************************************************//
	/**
	 *	球の原点へ移動
	 *
	 *	@return	true 成功
	 *	@return	false 失敗
	 */
	//************************************************************************//
//	public void move_sphere_origin(
//		GL		gl,
//		int		nDispSphere
//	)
//	{
//		try
//		{
//			//System.out.println(util.debug_ask_class_method_name());
//
//			// 現在の座標系を保存
//			gl.glPushMatrix();
//			// 粒子座標値に設定
//			gl.glTranslated(
//				this.m_dCenterXYZ[0].get_double(),
//				this.m_dCenterXYZ[1].get_double(),
//				this.m_dCenterXYZ[2].get_double()
//			);
//			//球を作成
//			gl.glCallList(nDispSphere);
//			// 座標系を元に戻す
//			gl.glPopMatrix();
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//	}

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
//		fileIO.add(id, new Object_04_Sphere());
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
//
//	@Override
//	public void observer_receive_update(Entity_Base obj, UpdateType update_type) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
//	@Override
//	public void ask_JavafxTreeItem_children(boolean is_open, TreeItem tree_item)
//	{
//		try
//		{
////			//名前からツリーノードを作成
////			TreeItem tree_item = new TreeItem<>(this);
//
//			Entity_Directory.ask_JavafxTreeNode_utility_obj(
//				is_open,
//				tree_item,
//				this.m_dCenterXYZ);
//			
//			Entity_Directory.ask_JavafxTreeNode_utility_value(
//				is_open,
//				tree_item,
//				this.m_Radius);
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
	@Override
	public void set_name(String value) {this.m_name = value;}
	@Override
	public String get_name() {return this.m_name;}
}

