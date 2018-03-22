package _22_Object_Primitive;

import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _20_Object_Template.TreeItem_Imp;
import java.util.ArrayList;
import javafx.scene.Node;
import utility.util;
import _20_Object_Template.Entity_Imp;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;

public class Object_02_Lines
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
//	implements FileIO
{
//	ArrayList<Object_Double[]> m_arryPoint = new ArrayList<Object_Double[]>();
//	ArrayList<ArrayList<Object_Double>> m_arryPoint = new ArrayList<>();
	private ArrayList<Object_03_Point> m_arryPoint = new ArrayList<>();
	private ArrayList<Object_06_Cylinder> m_arry_cylinder = new ArrayList<>();
	private String m_name;
	private Node m_javafxnode = null;
	private boolean m_update_on = true;

//	javafx.scene.shape.Shape3D

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Object_02_Lines()
	{
	}
	public Object_02_Lines(String name)
	{
		this.m_name = name;
		
		try{
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
	}
	public Object_02_Lines(
		String						name, 
		ArrayList<Object_03_Point>	points
	)
	{
//		super(name);
		this.m_name = name;

		try{
			this.m_arryPoint.addAll(points);
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
	}
	//************************************************************************//
	/**
	*	setter
	*/
	//************************************************************************//
	public void set_points(ArrayList<Object_03_Point> points){this.m_arryPoint.clear(); this.m_arryPoint.addAll(points);}
	//************************************************************************//
	/**
	*	getter
	*/
	//************************************************************************//
	public void get_points(ArrayList<Object_03_Point> points){points.addAll(this.m_arryPoint);}
	//************************************************************************//
	/**
	*	add
	*/
	//************************************************************************//
	public void add_point(Object_03_Point point){this.m_arryPoint.add(point);}
	
//	//************************************************************************//
//	/**
//	 *	Lineを表示するDisplayListを作成
//	 *
//	 *	@return	true 成功
//	 *	@return	false 失敗
//	 */
//	//************************************************************************//
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
//			//GLUT	glut = OGL.get_glut();
//	
//			///////////////////////////////////////
//			//	材質のDisplayListを作成
//			///////////////////////////////////////
//			//ディスプレイリストの番号を取得
//			int nDispMaterial = gl.glGenLists(1);
//			gl.glNewList(nDispMaterial, GL.GL_COMPILE); //コンパイルのみ
//			//材質を設定
//			gl.glMaterialfv(
//					GL.GL_FRONT_AND_BACK, 					//表裏描画
//					GL.GL_AMBIENT, 							//
//					material.get_color().ask_color_float(), //色
//					0										//？
//					);
//			//ディスプレイリスト終了
//			gl.glEndList();
//			//材質を非描画に保存
//			arryDispList_Non_Disp.add(nDispMaterial);
//			
//			///////////////////////////////////////
//			//	全LineのObjectをまとめて作成
//			///////////////////////////////////////
//			//ディスプレイリストの番号を取得
//			int nDispLine = gl.glGenLists(1);
//			gl.glNewList(nDispLine, GL.GL_COMPILE); //コンパイルのみ
//			//材質のDisplayList呼び出し
//			gl.glCallList(nDispMaterial);			
//			///////////////////////////////////////
//			//	全Objectループ
//			///////////////////////////////////////
//			ArrayList<DoubleT[]> arryPointBuf = new ArrayList<DoubleT[]>();
//			for(int ic=0; ic<arryObject.size(); ic++)
//			{
//				Object_02_Lines objLineBuf = (Object_02_Lines)arryObject.get(ic);
//
//				//点を全て取得
//				arryPointBuf.clear();
//				objLineBuf.get_points(arryPointBuf);
//				///////////////////////////////////////
//				//	Lineを折れ線で作成
//				///////////////////////////////////////
//				//初め
//				gl.glBegin(GL.GL_LINE_STRIP);
//				int nMaxNum = arryPointBuf.size();
//				for(int nc=0; nc<nMaxNum; nc++)
//				{
//					Value_Double[] dDoubleTBuf = arryPointBuf.get(nc);
//							
//					double[] dBufP = {
//							dDoubleTBuf[0].get_double(),
//							dDoubleTBuf[1].get_double(),
//							dDoubleTBuf[2].get_double()
//							};
//					//Line上の点
//					gl.glVertex3dv(dBufP, 0);
//				}
//				//終了
//				gl.glEnd();
//			}
//			gl.glEndList();
//			arryDispList_Disp.add(nDispLine);
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//		}
//	}

	private Node create_JavafxNode()
	{
		
		//点が2つ以上あるかチェック
		if(this.m_arryPoint.size() < 2){
			return null;
		}
		
		//初期化
		m_arry_cylinder.clear();
		double d_radius = 0.5;
		int n_bunkatsu = 7;
		Group group = new Group();
		
		for(int ic=0; ic<this.m_arryPoint.size()-1; ic++){
			//シリンダー作成
			Object_06_Cylinder cylinder = new Object_06_Cylinder(
				this.m_arryPoint.get(ic),		//始点座標値
				this.m_arryPoint.get(ic+1),		//終点座標値
				d_radius,						//半径
				n_bunkatsu						//分割数(3以上)
			);
			
			m_arry_cylinder.add(cylinder);
			group.getChildren().add(cylinder.ask_JavafxNode());
		}
		
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
