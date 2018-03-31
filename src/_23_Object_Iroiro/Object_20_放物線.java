package _23_Object_Iroiro;

import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.TreeItem_Imp;
//import _22_Object_Primitive.*;
import java.util.ArrayList;
import _42_Utility.util;
import javafx.scene.Group;
import javafx.scene.Node;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.ObserverReceive_Imp;
import _30_Command.Command_Create_ReImAxisDefine;
//import _21_Object_Value_etc.AxisType;
import _40_Value.Value_DoubleComplex;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;

///////////////////////////////////////////////////////////////////////////////
/**
 * 放物面を作成する。
 * xは複素数も可。むしろそれが見たい。
 * 
 * @author 真也
 */////////////////////////////////////////////////////////////////////////////
public class Object_20_放物線
//	extends Entity_Base
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp,
		ObserverReceive_Imp
//	implements FileIO
{
	//　Y = A*X^2 + B*X + C
	private String m_name;
	private Node m_javafxnode = null;
	private boolean m_update_on = true;
	
	private Value_DoubleComplex m_CoeffA = null;
	private Value_DoubleComplex m_CoeffB = null;
	private Value_DoubleComplex m_CoeffC = null;
	
	//xの変域
	private Value_DoubleComplex m_range_x_min = new Value_DoubleComplex(-100.0, -100.0);
	private Value_DoubleComplex m_range_x_max = new Value_DoubleComplex(100.0, 100.0);
	
	//yの変域
	private Value_DoubleComplex m_range_y_min = new Value_DoubleComplex(-100.0, -100.0);
	private Value_DoubleComplex m_range_y_max = new Value_DoubleComplex(100.0, 100.0);
	
	private double m_pitch = 5;
	
	//2次関数のX値（複素数マップ）
	private ArrayList<ArrayList<Value_DoubleComplex>> m_arryXComplex = new ArrayList<ArrayList<Value_DoubleComplex>>();

	//2次関数のY値（結果の複素数マップ）
	private ArrayList<ArrayList<Value_DoubleComplex>> m_arryYComplex = new ArrayList<ArrayList<Value_DoubleComplex>>();
	
	//更新フラグ
	private Boolean m_bUpdateOn = true;
	
	//放物線の面
	private Object_01_Face m_face;
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Object_20_放物線()
	{
	}
	public Object_20_放物線(
		String	name,
		Value_DoubleComplex	dCoeffA,
		Value_DoubleComplex	dCoeffB,
		Value_DoubleComplex	dCoeffC
	)
	{
		this.m_name = name;
	
		System.out.println(util.debug_ask_class_method_name());
		
		m_CoeffA = dCoeffA;
		m_CoeffB = dCoeffB;
		m_CoeffC = dCoeffC;
	}
	
	//************************************************************************//
	/**
	*	setter
	*/
	//************************************************************************//
	public void set_range_x_re_min(double d){this.m_range_x_min.set_re(d);}	//XのRe最小範囲
	public void set_range_x_re_max(double d){this.m_range_x_max.set_re(d);}	//XのRe最大範囲
	public void set_range_x_im_min(double d){this.m_range_x_min.set_im(d);}	//XのIm最小範囲
	public void set_range_x_im_max(double d){this.m_range_x_max.set_im(d);}	//XのIm最大範囲
	public void set_pitch(double d){this.m_pitch = d;}	//ピッチ

	//************************************************************************//
	/**
	*	getter
	*/
	//************************************************************************//

	//************************************************************************//
	/**
	*	放物面オブジェクトの作成
	*/
	//************************************************************************//
	public Node create_JavafxNode()
	{		
		try{
			System.out.println(util.debug_ask_class_method_name());
			
			///////////////////////////////////
			//	カレント座標系のオブザーバーに登録
			//	※座標系の各軸タイプに合わせて形状が変わるように。
			///////////////////////////////////	
			Main.get_model_base().add_current_re_im_axis_observer(this);

	//		放物線では、点の方で実部と虚部を持っている。
	//		だからこっちのオブジェクトで制御できるようにしないと、再作成うまくいかないかも。

			///////////////////////////////////
			//	面を作成
			///////////////////////////////////
			this.m_face = this.create_parabolic();

			///////////////////////////////////
			//	JavaFxデータを作成
			///////////////////////////////////
			Group group = new Group();
			group.getChildren().add(this.m_face.ask_JavafxNode());

			System.out.println(String.format("Faceの作成終了"));

			this.m_javafxnode = group;
			return group;
	   }
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
		
		return null;
	}

	//************************************************************************//
	/**
	*	画面に表示する放物線の面を用意
	*/
	//************************************************************************//
	private Object_01_Face create_parabolic()
	{
		try{
			System.out.println(util.debug_ask_class_method_name());
			
			////////////////////////////////////
			//	2次関数の値の複素数マップを作成
			////////////////////////////////////
			this.create_parabolic_values(
				this.m_CoeffA,				//I		2次関数の係数A
				this.m_CoeffB,				//I		2次関数の係数B
				this.m_CoeffC,				//I		2次関数の係数C
				this.m_range_x_min.get_re(),		//I		x実軸の最小値
				this.m_range_x_max.get_re(),		//I		x実軸の最大値
				this.m_range_x_min.get_im(),		//I		x虚軸の最小値
				this.m_range_x_max.get_im(),		//I		x虚軸の最大値
				this.m_pitch,				//I		空間の刻み幅
				this.m_arryXComplex,		//O		X結果格納配列
				this.m_arryYComplex			//O		Y結果格納配列
			);

			///////////////////////////////////////////////
			//	点座標値を作成
			///////////////////////////////////////////////
			ArrayList<ArrayList<Object_03_Point>> arryObjPointAll = new ArrayList<ArrayList<Object_03_Point>>();
			this.prepare_opengl_parabolic1_point_change(
				this.m_arryXComplex,	//I		X配列
				this.m_arryYComplex,	//I		Y配列
				arryObjPointAll			//O		面を作成する点座標値
			);

			////////////////////////////////////
			//	三角形を作れるようにObject_Pointを並べる
			////////////////////////////////////
			ArrayList<Object_03_Point[]> arryObjPointSort = new ArrayList<Object_03_Point[]>();
			this.prepare_opengl_parabolic2_triangle(
				arryObjPointAll,	//I		すべてのObject_Point
				arryObjPointSort	//O		三角形を作れるように並べられたObject_Point
			);

			////////////////////////////////////
			//	三角形を作成
			////////////////////////////////////
			ArrayList<Object_05_Triangle> arryTriangle = new ArrayList<>();
			arryObjPointSort.stream()
				.forEach(a -> arryTriangle.add(new Object_05_Triangle("", a)));

			////////////////////////////////////
			//	面に変換
			////////////////////////////////////
			Object_01_Face face = new Object_01_Face(
				"放物面",
				arryTriangle.toArray(new Object_05_Triangle[0])
			);

			return face;
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
		
		return null;
	}

	//************************************************************************//
	/**
	*	二次式の計算をして、Yの値を複素数で算出
	*/
	//************************************************************************//
	private void create_parabolic_values(
		Value_DoubleComplex CoeffA,									//I		2次関数のX^2の係数A
		Value_DoubleComplex CoeffB,									//I		2次関数のX^1の係数B
		Value_DoubleComplex CoeffC,									//I		2次関数のX^0の係数C
		double range_x_re_min,									//I		x実軸の最小値
		double range_x_re_max,									//I		x実軸の最大値
		double range_x_im_min,									//I		x虚軸の最小値
		double range_x_im_max,									//I		x虚軸の最大値
		double pitch,											//I		空間の刻み幅
		ArrayList<ArrayList<Value_DoubleComplex>> arryXColumn_Out,	//O		X格納配列
		ArrayList<ArrayList<Value_DoubleComplex>> arryYColumn_Out		//O		Y結果格納配列
	)
	{

		try{
			System.out.println(util.debug_ask_class_method_name());
			if(this.m_bUpdateOn == false){
				return;
			}
			else{
				//更新フラグをOff
				this.m_bUpdateOn = false;
			}

			//係数も複素数にする
			Value_DoubleComplex dcmpCoeffA = new Value_DoubleComplex(CoeffA.get_re(), CoeffA.get_im());
			Value_DoubleComplex dcmpCoeffB = new Value_DoubleComplex(CoeffB.get_re(), CoeffB.get_im());
			Value_DoubleComplex dcmpCoeffC = new Value_DoubleComplex(CoeffC.get_re(), CoeffC.get_im());

			//X格納配列の行
			ArrayList<ArrayList<Value_DoubleComplex>> arryXColumn = new ArrayList<ArrayList<Value_DoubleComplex>>();
			//Y結果格納配列の行
			ArrayList<ArrayList<Value_DoubleComplex>> arryYColumn = new ArrayList<ArrayList<Value_DoubleComplex>>();

			//XのRe軸
			for(double dXRe=range_x_re_min; dXRe<range_x_re_max; dXRe += pitch)
			{
				//X結果の列
				ArrayList<Value_DoubleComplex> arryXRow = new ArrayList<Value_DoubleComplex>();
				arryXColumn.add(arryXRow);
				//Y結果の列
				ArrayList<Value_DoubleComplex> arryYRow = new ArrayList<Value_DoubleComplex>();
				arryYColumn.add(arryYRow);

				//XのIm軸
				for(double dXIm=range_x_im_min; dXIm<range_x_im_max; dXIm += pitch)
				{
					//Xの複素数を作成
					Value_DoubleComplex dcmp = new Value_DoubleComplex(dXRe, dXIm);

					////////////////////////
					//	二次関数の式を計算
					////////////////////////
					//2次の項
					Value_DoubleComplex dcmpY_Result2 = (Value_DoubleComplex) dcmpCoeffA.times(dcmp).times(dcmp);

					//1次の項
					Value_DoubleComplex dcmpY_Result1 = (Value_DoubleComplex) dcmpCoeffB.times(dcmp);

					//合計
					Value_DoubleComplex dcmpY_Result  = (Value_DoubleComplex) dcmpY_Result2.plus(dcmpY_Result1).plus(dcmpCoeffC);

					//Xを格納
					arryXRow.add(dcmp);
					//Y結果を格納
					arryYRow.add(dcmpY_Result);
				}
			}

			//格納
			arryXColumn_Out.addAll(0, arryXColumn);
			arryYColumn_Out.addAll(0, arryYColumn);
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}

	}

	//************************************************************************//
	/**
	*	複素数の点を、3次元空間に描画できるように変換
	*/
	//************************************************************************//
	private void prepare_opengl_parabolic1_point_change(
		ArrayList<ArrayList<Value_DoubleComplex>> arryXComplex,	//I		X配列
		ArrayList<ArrayList<Value_DoubleComplex>> arryYComplex,	//I		Y配列
		ArrayList<ArrayList<Object_03_Point>>	arryPointAll_Out		//O		面を作成する点座標値
	)
	{
		try{
			System.out.println(util.debug_ask_class_method_name());
			Object_10_ReImAxisDefine.AxisType[] cst = {
				Object_10_ReImAxisDefine.AxisType.X_REAL,
				Object_10_ReImAxisDefine.AxisType.X_IMAGINARY,
				Object_10_ReImAxisDefine.AxisType.Y_REAL,
				Object_10_ReImAxisDefine.AxisType.Y_IMAGINARY
			};
	//		ArrayList<ArrayList<double[]>>	arryPointAll = new ArrayList<ArrayList<double[]>>();
			ArrayList<ArrayList<Object_03_Point>>	arryPointAll = new ArrayList<>();

			//行のループ（全点）
	//		int list_size = arryXComplex.size();
			for(int nColumn=0; nColumn<arryXComplex.size(); nColumn++)
			{
				//XとYについての行を取得
				ArrayList<Value_DoubleComplex> arryX = arryXComplex.get(nColumn);
				ArrayList<Value_DoubleComplex> arryY = arryYComplex.get(nColumn);

				//列の配列を取得
				ArrayList<Object_03_Point> arryBuf = new ArrayList<>(arryX.size());

				//列のループ
				for(int nRow=0; nRow<arryX.size(); nRow++){
					//点を作成
					Object_03_Point buf_point = new Object_03_Point(
						cst,
						arryX.get(nRow).get_re(),
						arryX.get(nRow).get_im(),
						arryY.get(nRow).get_re(),
						arryY.get(nRow).get_im()
					);

					//点を格納
					arryBuf.add(buf_point);
				}

				//格納
				arryPointAll.add(arryBuf);
			}

			arryPointAll_Out.addAll(0, arryPointAll);
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
		

	}
	//************************************************************************//
	/**
	*	三角形の点の組み合わせを決定
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	private void prepare_opengl_parabolic2_triangle(
		ArrayList<ArrayList<Object_03_Point>>	arryObjPointAll,		//I		点
		ArrayList<Object_03_Point[]>			arryObjPointSort_Out	//O		点
	)
	{
		try{
			System.out.println(util.debug_ask_class_method_name());
			
			ArrayList<Object_03_Point[]> arryObjPointSort = new ArrayList<Object_03_Point[]>();

			//最初の1行と2行目を取得し、各四角形に三角形パッチを当てはめていく
			int list_size = arryObjPointAll.size();
			for(int nColumn=0; nColumn<list_size-1; nColumn++)
			{
				//////////////////////////
				//	三角形の連続作成
				//////////////////////////
				//行を2つずつ取得
				ArrayList<Object_03_Point> arry1 = arryObjPointAll.get(nColumn);
				ArrayList<Object_03_Point> arry2 = arryObjPointAll.get(nColumn+1);

				int list_size_row = arry1.size();
				for(int nRow=0; nRow<list_size_row-1; nRow++)
				{
					//点を取得
					Object_03_Point pBuf1 = arry1.get(nRow);
					Object_03_Point pBuf2 = arry2.get(nRow);
					Object_03_Point pBuf3 = arry1.get(nRow+1);
					Object_03_Point pBuf4 = arry2.get(nRow+1);

					Object_03_Point[] sort1 = {pBuf1, pBuf2, pBuf3};
					arryObjPointSort.add(sort1);

					Object_03_Point[] sort2 = {pBuf2, pBuf4, pBuf3};
					arryObjPointSort.add(sort2);
				}
			}

			arryObjPointSort_Out.addAll(0, arryObjPointSort);
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
	}

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
//		fileIO.add(id, new Object_20_放物線());
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
//
//	@Override
//	public void observer_receive_update(Entity_Base obj, UpdateType update_type) {
////		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//		//座標系が変わった
//		if(update_type == Entity_Base.UpdateType.CoordinateSystem_Change){
//			this.setM_update_on(true);
////			if(this.m_face != null){
////				this.m_face.observer_receive_update(obj, update_type);
////			}
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
//			Entity_Directory.ask_JavafxTreeNode_utility_value(
//				is_open,
//				tree_item,
//				m_CoeffA,
//				m_CoeffB,
//				m_CoeffC,
//				m_range_x_min,
//				m_range_x_max,
//				m_range_y_min,
//				m_range_y_max);
//
//			Entity_Directory.ask_JavafxTreeNode_utility_obj(
//				is_open,
//				tree_item,
//				m_face);
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
	@Override	public void set_name(String value) {this.m_name = value;}
	@Override	public String get_name() {return this.m_name;}
	
	/***************************************************************************
	 * 
	 * [ObserberReceiveクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	public void observer_receive_update(Entity_Imp in_obj)
	{
		if(in_obj instanceof Command_Create_ReImAxisDefine){
			//作り直し
			this.m_update_on = true;
		}
	}

}
