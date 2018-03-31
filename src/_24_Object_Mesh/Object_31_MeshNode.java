package _24_Object_Mesh;

import _20_Object_Template.TreeItem_Imp;
import _23_Object_Iroiro.Object_03_Point;
import javafx.scene.Node;


import _40_Value.Value_Long;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _40_Value.Value_Double;
import javafx.scene.control.ContextMenu;
//import _40_Value.SaveValue_FixAry;
//import _40_Value.SaveValue_One;
import javafx.scene.control.TreeItem;
//import moredyn.OpenGL_Play;

public class Object_31_MeshNode
//	extends Entity_Base
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
//	implements FileIO
{	
	private String m_name;

	///////////////////////////////////////////////
	//	キー
	//	目的：
	//	データ保存、読み込み時にこのキーを使用する。
	///////////////////////////////////////////////
	final static String D_NID		= "node_id";
	final static String D_POSITION	= "position";
	
//	SaveValue_One<Object_Long>		m_node_id  = new SaveValue_One(new Value_Long(0));
//	SaveValue_FixAry<Object_Double>	m_position = new SaveValue_FixAry(new Value_Double(0.0), new Value_Double(0.0), new Value_Double(0.0));
	private Value_Long		m_node_id  = new Value_Long();
	private Object_03_Point	m_position = new Object_03_Point();
	
	//************************************************************************//
	/**
	 *	getter
	 */
	//************************************************************************//
//	public Value_Long		get_node_id()	{return m_node_id.get_value();}
//	public Value_Double[]	get_position()	{return m_position.get_value();}
	public Value_Long		get_node_id()	{return m_node_id;}
	public Object_03_Point 	get_position()	{return m_position;}
	
	//************************************************************************//
	/**
	 *	setter
	 */
	//************************************************************************//
//	public void set_node_id(Value_Long value)						{m_node_id.set_value(value);}
//	public void set_position(Value_Double[] values)					{m_position.set_value(values);}
//	public void set_position(Value_Double x, Value_Double y, Value_Double z)	{m_position.set_value(x,y,z);}
	public void set_node_id(Value_Long value)						{m_node_id = value;}
	public void set_position(Object_03_Point values)					{m_position = values;}
//	public void set_position(Value_Double x, Value_Double y, Value_Double z)	{m_position = value;}
	
	//************************************************************************//
	/**
	 *	Constructor
	 */
	//************************************************************************//
	public Object_31_MeshNode()
	{
//		super(null);
//		this.m_data = new ArrayList<Object>(num_value);
	}
	public Object_31_MeshNode(
//		String	id, 
		Value_Double	x,
		Value_Double	y,
		Value_Double	z
	)
	{
//		super("");

		//位置
		Object_03_Point pos_point = new Object_03_Point(x.get_double(), y.get_double(), z.get_double());
		this.set_position(pos_point);
		
		//節点IDを生成
		long id_buf = ObjectManager_MeshGrid.get_instance().add_new_grid(this);
		if(id_buf > 0)
		{
			this.set_node_id(new Value_Long(id_buf));
		}
		else
		{
			//節点idの上限に達した。もう節点は作れない。
		}
	}

	//************************************************************************//
	/**
	 *	節点idを変更する
	 */
	//************************************************************************//
	synchronized public boolean change_grid_id(Long new_node_id)
	{
		//登録する
		if(ObjectManager_MeshGrid.get_instance().registor(this, new_node_id) == true)
		{
			//node_idを変更
			this.set_node_id(new Value_Long(new_node_id));
			return true;
		}
		//重複のため登録できない
		else
		{
			return false;
		}
	}

//	//************************************************************************//
//	/**
//	 *	線分と交差するかチェック
//	 *
//	 *	@return	0：交差する
//	 *	@return	負の値：交差しない
//	 *			-1：線分が届かない
//	 *			-2：線分は届くが半径より遠い
//	 *
//	 *	Status:	0	交差している
//	 *			1	非交差
//	 *
//	 */
//	//************************************************************************//
//	@Override
//	public PickedItem ask_isTouch(
//		double[]	spoint,			//I		ピック円柱の始点 
//		double[]	epoint,			//I		ピック円柱の終点
//		Filter		filter,			//I		選択フィルタ
//		double		tolerance		//I		ピックのトレランス（ピック円柱の半径）
//	)
//	{
//		try
//		{
//			//System.out.println(utility.ask_class_method_name());
//	
//			double[] dCenter = new double[3];
//			dCenter[0] = this.m_arryPosition.get(0).get_double();
//			dCenter[1] = this.m_arryPosition.get(1).get_double();
//			dCenter[2] = this.m_arryPosition.get(2).get_double();
//					
//			double dRadius   = 1.0;
//
//			//交差するかチェック
//			PickedItem pickItem = check_sphere_touch(
//				dCenter,
//				dRadius,
//				spoint,
//				epoint
//			);
//
//			//結果を格納
//			if(pickItem == null)
//			{
//				return null;
//			}
//			else
//			{
//				return pickItem;
//			}
//		}
//		catch (Exception ex)
//		{
//			utility.write_exception(ex);
//		}		
//		
//		return null;
//	}
//	//************************************************************************//
//	/**
//	 *	球と線分が交差するかチェック
//	 *
//	 *	@return	0：交差する
//	 *	@return	負の値：交差しない
//	 *			-1：線分が届かない
//	 *			-2：線分は届くが半径より遠い
//	 */
//	//************************************************************************//
////	private int check_sphere_touch(
////	private ePickStatus check_sphere_touch(
////		double[] 	dCenter, 
////		double 		dRadius, 
////		double[] 	spoint, 
////		double[] 	epoint,
////		double[] 	pMostNearTouch, 
////		double[] 	dMostNearLength
////	)
//	private PickedItem check_sphere_touch(
//		double[] 	dCenter, 
//		double 		dRadius, 
//		double[] 	spoint, 
//		double[] 	epoint
//	)
//	{
//		try
//		{
////			System.out.println(utility.ask_class_method_name());
//
//			//[式5参照]
//			//ベクトルS-Eと、球の中心点を通る垂直線の交点Qを算出
//			double[] dQ = {0.0, 0.0, 0.0};
//			double[] dRatio = {0.0};
//			Mathmatic.calc_CrossPoint_vec_point(
//				spoint,		//始点
//				epoint,		//終点
//				dCenter,	//球の中心
//				dQ,			//始点→終点ベクトルと、球の中心を通る垂直線の交点Q
//				dRatio		//始点→終点ベクトルに対する、始点→交点Qベクトルの倍率。
//			);
//	
//			//Ratioが0～1に無いとき、交差しない。
//			if(dRatio[0] < 0 ||1.0 < dRatio[0])
//			{
////				return -1;
////				return ePickStatus.NO_TOUCH_NO_REACH;
//				return null;			
//			}
//			
//			//球の中心点Cと交点Q間の長さが、半径より大きければ交差しない。
//			double dDistance = Mathmatic.calc_length_between_point_point(dCenter, dQ); 
//			if(dDistance > dRadius)
//			{
////				return -2;
////				return ePickStatus.NO_TOUCH;
//				return null;
//			}
//
//			//交点を算出
//			//三平方の定理から、線分QTを求める。
//			//|Q-T|^2 = R^2 - |Q-C|^2 
//			double dLengQT = 0.0;
//			double dSum2 = 0.0;
//			for(int ic=0; ic<3; ic++)
//			{
//				double dBuf = dQ[ic] - dCenter[ic];
//				dSum2 += dBuf*dBuf;
//			}
//			dLengQT = Math.sqrt(dRadius*dRadius - dSum2);
//	
//			//交差点Tは、点Qから線分QTだけベクトルS->Eの反対方向に移動した点となる。
//			//T = Q - |Q-T|*(E-S)/|E-S|
//			double[] dT = {0.0, 0.0, 0.0};
//			double dLengES = Mathmatic.calc_length_between_point_point(spoint, epoint);
//			double dRev    = 1.0/dLengES;
//			for(int ic=0; ic<3; ic++)
//			{
//				dT[ic] = dQ[ic] - dLengQT*(epoint[ic]-spoint[ic])*dRev;
//			}
//	
//			//始点から交差点までの距離を求める。
//			double dLengST = Mathmatic.calc_length_between_point_point(spoint, dT);
//	
//			
//			PickedItem pickItem = new PickedItem(
//				this,
//				dT,
//				dLengST
//			);
//
//			return pickItem;	
//			
////			//値を格納
////			for(int ic=0; ic<3; ic++)
////			{
////				pMostNearTouch[ic] = dT[ic];
////			}
////			dMostNearLength[0] = dLengST;
//		}
//		catch (Exception ex)
//		{
//			utility.write_exception(ex);
//		}
//		
////		return 	ePickStatus.TOUCH;
//		return null;
//	}

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
////		/////////////////////
////		//	idを取得
////		/////////////////////
////		String id = xmlData.search_option_value("id");
////
////		//////////////////////////////////////////////////////
////		//	idに対応したオブジェクト実体を返す
////		//////////////////////////////////////////////////////
////		Object_MeshGrid meshCurrent = dataFileIO.search_Object_MeshGrid(id);
////		if(meshCurrent == null)
////		{
////			return;
////		}
////		
////		/////////////////////////
////		//	topオプションを検索
////		/////////////////////////
////		if(xmlData.search_option("top") != null)
////		{
////			//TopのGroupに追加
////			topGroup.add_object(
////				meshCurrent,	//
////				false			//既に追加されている場合でも追加する
////			);
////		}
////
////		//////////////////////////////
////		//	フォーマットの型を準備
////		//////////////////////////////
////		String					stringBuf		= null;
////		ArrayList<DoubleT> 		arryPosition	= new ArrayList<DoubleT>(3);
////		
////		/////////////////////////////////////
////		//	Group_Baseデータ読み込みモード
////		/////////////////////////////////////
////		ArrayList<String>	sData		= new ArrayList<String>();
////		ArrayList<String>	elements	= xmlData.get_elements();
////		int nSize = elements.size();
////		for(int ic=0; ic<nSize; ic++)
////		{
////			//コメントは除く
////
////			//カンマで分解
////			utility.factorize_string3(elements.get(ic), ",", sData);
////			
////			//文字列前後の空白、タブは除く
////			utility.string_trim(sData);
////
////			//フォーマット識別番号を取得
////			int nFormNumber = utility.change_String_To_Integer(sData.get(0));
////
////			////////////////////////////////////
////			//	フォーマット
////			//	<Object_MeshGrid id=XXXXX>
////			//		0,	String	※name
////			//		1,	ArrayList<DoubleT>
////			//	</Object_MeshGrid>
////			////////////////////////////////////
////			switch(nFormNumber)
////			{
////				////////////////////////
////				//	0,	String
////				////////////////////////
////				case 0:
////					stringBuf	= sData.get(1);
////					break;
////				/////////////////////////////////
////				//	1,	ArrayList<DoubleT>
////				/////////////////////////////////
////				case 1:
////					for(int nc=1; nc<sData.size(); nc++)
////					{
////						arryPosition.add( dataFileIO.search_DoubleT(sData.get(nc)) );
////					}
////					break;
////				////////////////////////
////				//	フォーマット外の値
////				////////////////////////
////				default:
////					break;
////			}
////		}
////		////////////////////////////////////////////////////
////		//	取得データを、メンバ変数に格納
////		////////////////////////////////////////////////////
////		meshCurrent.set_Name(stringBuf);
////		meshCurrent.set_position(arryPosition.Lists(new Value_Double(0.0)));
////		
////		return;
//	}
//	@Override
//	public void factory_and_add(String id, FileInputOutput fileIO)
//	{
//		fileIO.add(id, new Object_31_MeshNode());
//	}
//	
//	//************************************************************************//
//	/**
//	 *	ファイルに書き出し
//	 *
//	 *	@param	
//	 *	@return	id
//	 *	@version
//	 */
//	//************************************************************************//
//	@Override
//	public String write_data(
//		XmlData				xmlDataParent,	//OUT	親のXmlData。このXmlDataの子として登録する。
//		FileInputOutput		poolFileIO		//IN	書き込み時の全てのXmlDataを持つ。
//	)
//	{
////		try
////		{
////			/////////////////////////////////////////
////			//	XmlDataが既にあるかチェック
////			/////////////////////////////////////////
////			XmlData[]	xmlBuf	= {null};
////			String[]	idBuf	= {null};
////			boolean bSearchExist = poolFileIO.search_or_create_xml(
////				xmlDataParent,	//IO	Xmlデータ。親に追加する。
////				this,			//IN	Xmlデータの元となるオブジェクト
////				poolFileIO,		//IO	Xmlデータを全て格納
////				xmlBuf,			//OUT	検索、または作成したXmlData
////				idBuf			//OUT	検索、または作成されたXmlData
////			);
////			if(bSearchExist == true)
////			{
////				//既にあるのでそのまま返す
////				return idBuf[0];
////			}
////			
////			///// フォーマット識別番号を全て書き出し /////
////			XmlData xmlDataMySelf = xmlBuf[0];
////			String	idMySelf = idBuf[0];
////			for(int nIndex=0; true; nIndex++)
////			{
////				////////////////////////////////////
////				//	フォーマット
////				//	<Object_MeshGrid id=XXXXX>
////				//		0,	String	※name
////				//		1,	ArrayList<DoubleT>
////				//	</Object_MeshGrid>
////				////////////////////////////////////
////				String sFormNumber		= String.format("%d",nIndex);
////				String sWriteLineBuf	= null;
////				switch(nIndex)
////				{
////					////////////////////////
////					//	0,	String
////					////////////////////////
////					case 0:
////						//要素を追加
////						if(super.getM_Name() != null)	xmlDataMySelf.add_element(sFormNumber + "," + super.getM_Name());
////						break;
////					////////////////////////
////					//	1,	ArrayList<DoubleT>
////					////////////////////////
////					case 1:
////						if(this.get_position() == null) break;
////						
////						ArrayList<DoubleT> buf_arry_pos = this.get_position();
////						for(int nc=0; nc<buf_arry_pos.size(); nc++)
////						{
////							//書き出し文字列作成、また子のXmlData作成
////							sWriteLineBuf = poolFileIO.ask_write_line_and_create_xml(
////								xmlDataMySelf,					//OUT	親となるXmlデータ。これに追加する。
////								sWriteLineBuf,					//OUT	親となるXmlデータのデータ書き出し文字列。
////								buf_arry_pos.get(nc),	//IN	子となるXmlデータの元となるオブジェクト。
////								poolFileIO						//IN	Xmlデータを全て格納
////							);
////						}
////						//要素を追加
////						if(sWriteLineBuf != null)	xmlDataMySelf.add_element(sFormNumber + sWriteLineBuf);					
////						break;
////					////////////////////////
////					//	フォーマット外の値
////					////////////////////////
////					default:
////						return idMySelf;
////				}
////			}
////		}
////		catch (Exception ex)
////		{
////			utility.write_exception(ex);
////			return null;
////		}
//
//		return null;
//	}


//	@Override
//	public void observer_receive_update(Entity_Base obj, UpdateType update_type) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
//			//現段階では無しとする
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
	public Node ask_JavafxNode(){	return this.m_position.create_JavafxNode();	}
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
