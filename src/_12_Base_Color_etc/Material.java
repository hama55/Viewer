package _12_Base_Color_etc;


public class Material
//	implements FileIO
{
//	private String		m_name;		//名前
//	private Color_Base	m_color;	//色
//	//private 						//反射率とか。
//
//	//************************************************************************//
//	/**
//	 *	Constructor
//	 */
//	//************************************************************************//
//	public Material()
//	{
//		try
//		{
////			System.out.println(utility.ask_class_method_name());
//			this.m_name = "Default";
//			this.m_color = new Color_Blue();
//		}
//		catch (Exception ex)
//		{
//			utility.write_exception(ex);
//		}
//	}
//	public Material(String name, Color_Base color)
//	{
//		try
//		{
//			//System.out.println(utility.ask_class_method_name());
//			this.m_name = name;
//			this.m_color = color;
//		}
//		catch (Exception ex)
//		{
//			utility.write_exception(ex);
//		}
//	}
//	//************************************************************************//
//	/**
//	 *	getter
//	 */
//	//************************************************************************//
//	public String get_name(){return m_name;}
//	public Color_Base get_color(){return m_color;}
//	
//	//************************************************************************//
//	/**
//	 *	setter
//	 */
//	//************************************************************************//
//	public void set_name(String name){this.m_name = name;}
//	public void set_color(Color_Base color){this.m_color = color;}
//	
////	//************************************************************************//
////	/**
////	*	
////	*
////	*	@param
////	*	@return
////	*	@version
////	*/
////	//************************************************************************//
////	public int create_material(GL gl)
////	{
////		///////////////////////////////////////
////		//	材質のDisplayListを作成
////		///////////////////////////////////////
////		//ディスプレイリストの番号を取得
////		int nDispMaterial = gl.glGenLists(1);
////		gl.glNewList(nDispMaterial, GL.GL_COMPILE); //コンパイルのみ
////		//材質を設定
////		//gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, this.get_color().get_color(), 0);
////		//gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT_AND_DIFFUSE, this.get_color().get_color(), 0);
////		
////		//gl.glColor3dv(this.get_color().get_color(), 0);
////		
////		//gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT_AND_DIFFUSE, this.get_color().ask_color_float(), 0);
////
////		//gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, this.get_color().ask_color_float(), 0);
////		gl.glMaterialfv(GL.GL_FRONT, GL.GL_AMBIENT, this.get_color().ask_color_float(), 0);
////
////		//ディスプレイリスト終了
////		gl.glEndList();
////		
////		return nDispMaterial;
////	}
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
//		fileIO.add(sID, new Material());
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
//		/////////////////////
//		//	idを取得
//		/////////////////////
//		String id = xmlData.search_option_value("id");
//
//		//////////////////////////////////////////////////////
//		//	idに対応したオブジェクト実体を返す
//		//////////////////////////////////////////////////////
//		Material matCurrent = dataFileIO.search_Material(id);
//		if(matCurrent == null)
//		{
//			return;
//		}
//		
//		//////////////////////////////
//		//	フォーマットの型を準備
//		//////////////////////////////
//		String					stringBuf	= null;
//		Color_Base				colorBuf	= null;
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
//			utility.string_factorize3(elements.get(ic), ",", sData);
//			
//			//文字列前後の空白、タブは除く
//			utility.string_trim(sData);
//
//			//フォーマット識別番号を取得
//			int nFormNumber = utility.change_String_To_Integer(sData.get(0));
//
//			////////////////////////////////////
//			//	フォーマット
//			//	<Material id=XXXXX>
//			//		0,	String
//			//		1,	Color_Base
//			//	</Material>
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
//				//	1,	Color_Base
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
//		if(stringBuf != null)	matCurrent.set_name(stringBuf);
//		if(colorBuf != null)	matCurrent.set_color(colorBuf);
//		
//		return;
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
//			//	<Material id=XXXXX>
//			//		0,	String
//			//		1,	Color_Base
//			//	</Material>
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
//				//	1,	Color_Base
//				////////////////////////
//				case 1:
//					//TODO
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
