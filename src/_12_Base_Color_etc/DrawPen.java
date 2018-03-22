package _12_Base_Color_etc;

import java.util.ArrayList;

import _20_Object_Template.Entity_Directory;
import _14_Base_File_etc.FileIO;
import _14_Base_File_etc.FileInputOutput;
import utility.util;
import xml.XmlData;

public class DrawPen
//	implements FileIO
{
//	private String m_name;
//	
//	
//	//************************************************************************//
//	/**
//	*	コンストラクタ
//	*
//	*	@param
//	*	@return
//	*	@version
//	*/
//	//************************************************************************//
//	public DrawPen()
//	{
//		try
//		{
//			System.out.println(util.ask_class_method_name());
//		}
//		catch (Exception ex)
//		{
//			util.write_exception(ex);
//		}
//	}
//
//	//************************************************************************//
//	/**
//	 *	読み込みファイルデータを実体化する
//	 *
//	 *	@param	
//	 *	@return	
//	 *	@version
//	 */
//	//************************************************************************//
//	@Override
//	public void factory_and_add
//	(
//		String			sID,
//		FileInputOutput	fileIO
//	)
//	{
//		fileIO.add(sID, new DrawPen());
//	}
//	//************************************************************************//
//	/**
//	 *	setter
//	 */
//	//************************************************************************//
//	void set_name(String s){this.m_name = s;}
//
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
//		DrawPen dpCurrent = dataFileIO.search_DrawPen(id);
//		if(dpCurrent == null)
//		{
//			return;
//		}
//		
//		//////////////////////////////
//		//	フォーマットの型を準備
//		//////////////////////////////
//		String					stringBuf	= null;
//		
//		/////////////////////////////////////
//		//	Group_Baseデータ読み込みモード
//		/////////////////////////////////////
//		ArrayList<String>	sData		= new ArrayList<String>();
//		ArrayList<String>	elements	= xmlData.get_elements();
//		int nSize = elements.size();
//		for(int ic=0; ic<nSize; ic++)
//		{
//			//コメントは除く
//
//			//カンマで分解
//			util.string_factorize3(elements.get(ic), ",", sData);
//			
//			//文字列前後の空白、タブは除く
//			util.string_trim(sData);
//
//			//フォーマット識別番号を取得
//			int nFormNumber = util.change_String_To_Integer(sData.get(0));
//
//			////////////////////////////////////
//			//	フォーマット
//			//	<DrawPen id=XXXXX>
//			//		0,	String	※name
//			//		1,	
//			//	</DrawPen>
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
//				//	1,	
//				////////////////////////
//				case 1:
//					//colorBuf	= dataFileIO.search_Color_Base(sData.get(1));
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
//		if(stringBuf != null)	dpCurrent.set_name(stringBuf);
//		
//		return;
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
//	public String write_data
//	(
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
//			//	<DrawPen id=XXXXX>
//			//		0,	String	※name
//			//		1,	
//			//	</DrawPen>
//			////////////////////////////////////
//			String sFormNumber		= String.format("%d",nIndex);
//			String sWriteLineBuf	= null;
//			switch(nIndex)
//			{
//				////////////////////////
//				//	0,	String
//				////////////////////////
//				case 0:
//					if(this.m_name != null)	xmlDataMySelf.add_element(sFormNumber + "," + this.m_name);
//					break;
//				////////////////////////
//				//	1,	
//				////////////////////////
//				case 1:
//					break;
//				////////////////////////
//				//	フォーマット外の値
//				////////////////////////
//				default:
//					return idMySelf;
//			}
//		}
//	}
}
