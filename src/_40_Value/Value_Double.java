package _40_Value;

import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp;
import java.util.ArrayList;
import javafx.scene.control.ContextMenu;

import _42_Utility.util;
import javafx.scene.control.TreeItem;

public class Value_Double
//	extends Value_Imp
//	implements FileIO
//	extends Entity_Base
	implements
		Entity_Imp,
		Value_Imp,
		TreeItem_Imp
{
	private double	m_doubleValue;

	//************************************************************************//
	/**
	 *	Constructor
	 */
	//************************************************************************//
	public Value_Double()
	{
//		super(null);
	}
	public Value_Double(double dValue)
	{
//		super(null);
		this.m_doubleValue = dValue;
	}

	//************************************************************************//
	/**
	 *	setter
	 */
	//************************************************************************//
	public void set_double(double d){this.m_doubleValue = d;}
	//************************************************************************//
	/**
	 *	getter
	 */
	//************************************************************************//
	public double get_double(){return this.m_doubleValue;}
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
//		/////////////////////
//		//	idを取得
//		/////////////////////
//		String id = xmlData.search_option_value("id");
//
//		//////////////////////////////////////////////////////
//		//	idに対応したオブジェクト実体を返す
//		//////////////////////////////////////////////////////
//		Value_Double clsDoubleT = dataFileIO.search_DoubleT(id);
//		if(clsDoubleT == null)
//		{
//			return;
//		}
//
//		//////////////////////////////
//		//	フォーマットの型を準備
//		//////////////////////////////
//		double dValue = 0;
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
////			//コメントは除く
////
////			//カンマで分解
////			util.factorize_string3(elements.get(ic), ",", sData);
////
////			//文字列前後の空白、タブは除く
////			util.string_trim(sData);
////
////			//フォーマット識別番号を取得
////			int nFormNumber = util.change_String_To_Integer(sData.get(0));
//
//			////////////////////////////////////
//			//	フォーマット
//			//	<DoubleT id=XXXXX>
//			//		0,	double	※実数。プリミティブ。
//			//	</DoubleT>
//			////////////////////////////////////
//			switch(nFormNumber)
//			{
//				////////////////////////
//				//	0,	double
//				////////////////////////
//				case 0:
//					dValue	= util.change_String_To_Double(sData.get(1));
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
//		clsDoubleT.set_double(dValue);
//
//		return;
//
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
//		fileIO.add(id, new Value_Double());
//	}
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
//	public String write_data
//	(
//		XmlData				xmlDataParent,	//OUT	親のXmlData。このXmlDataの子として登録する。
//		FileInputOutput		poolFileIO		//IN	書き込み時の全てのXmlDataを持つ。
//	)
//	{
//		try
//		{
//			/////////////////////////////////////////
//			//	XmlDataが既にあるかチェック
//			/////////////////////////////////////////
//			XmlData[]	xmlBuf	= {null};
//			String[]	idBuf	= {null};
//			boolean bSearchExist = poolFileIO.search_or_create_xml(
//				xmlDataParent,	//IO	Xmlデータ。親に追加する。
//				this,			//IN	Xmlデータの元となるオブジェクト
//				poolFileIO,		//IO	Xmlデータを全て格納
//				xmlBuf,			//OUT	検索、または作成したXmlData
//				idBuf			//OUT	検索、または作成されたXmlData
//			);
//			if(bSearchExist == true)
//			{
//				//既にあるのでそのまま返す
//				return idBuf[0];
//			}
//
//			///// フォーマット識別番号を全て書き出し /////
//			XmlData xmlDataMySelf = xmlBuf[0];
//			String	idMySelf = idBuf[0];
//			for(int nIndex=0; true; nIndex++)
//			{
//				////////////////////////////////////
//				//	フォーマット
//				//	<DoubleT id=XXXXX>
//				//		0,	double	※実数。プリミティブ。
//				//	</DoubleT>
//				////////////////////////////////////
//				String sFormNumber		= String.format("%d",nIndex);
//				String sWriteLineBuf	= null;
//				switch(nIndex)
//				{
//					////////////////////////
//					//	0,	double
//					////////////////////////
//					case 0:
//						//要素を追加
//						xmlDataMySelf.add_element(sFormNumber + "," + String.format("%f", this.m_doubleValue));
//						break;
//					////////////////////////
//					//	フォーマット外の値
//					////////////////////////
//					default:
//						return idMySelf;
//				}
//			}
//		}
//		catch (Exception ex)
//		{
//			util.debug_write_exception(ex);
//			return null;
//		}
//	}

	
	///////////////////////////////////////
	//	Value_Double[]をdouble[]に変換
	///////////////////////////////////////
	static public double[] change_list(Value_Double[] doubleValues)
	{
		int count = doubleValues.length;
		double[] values = new double[count];
		for(int ic=0; ic<count; ic++)
		{
			values[ic] = doubleValues[ic].get_double();
		}
		return values;
	}
	///////////////////////////////////////
	//	ArrayList<DoubleT>をdouble[]に変換
	///////////////////////////////////////
	static public double[] change_list(ArrayList<Value_Double> doubleValues)
	{
		int count = doubleValues.size();
		double[] values = new double[count];
		for(int ic=0; ic<count; ic++)
		{
			values[ic] = doubleValues.get(ic).get_double();
		}
		return values;
	}

	@Override
	public TreeItem ask_JavafxTreeItem(boolean is_open)
	{
		try
		{
			//名前からツリーノードを作成
			TreeItem tree_item = new TreeItem<>(this);

			tree_item.getChildren().add(m_doubleValue);
			
			return tree_item;
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
		
		return null;
	}
	@Override
	public ContextMenu getPopupMenu(){
		return null;
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
	@Override	public void		set_name(String value)	{}
	@Override	public String	get_name()				{return "double_名前なし";}

}
