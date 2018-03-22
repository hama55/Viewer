package _14_Base_File_etc;

//
public class FileInputOutput
{
//	static private final String sDefaultName = "_NO_NAME_";
//
//	//////////////////////////////////////////////
//	//	生成オブジェクトタイプの振り分け用
//	//////////////////////////////////////////////
//	Map<String, FileIO>	m_map_ObjName_ObjFileIO;
//
//	////////////////////////////////////////////////////////
//	//	ファイル読み込み時に生成した全データをプールする
//	////////////////////////////////////////////////////////
//	Map<String, FileIO>				m_mapId_FilIO				= new HashMap<String, FileIO>();
//	//Map<String, StringT>			m_mapId_StringT				= new HashMap<String, StringT>();
//	Map<String, Value_Double>			m_mapId_DoubleT				= new HashMap<String, Value_Double>();
//	Map<String, Value_DoubleComplex>		m_mapId_DoubleComplex		= new HashMap<String, Value_DoubleComplex>();
//	Map<String, Object_Directory>			m_mapId_Group_Base			= new HashMap<String, Object_Directory>();
////	Map<String, Object_Camera>		m_mapId_Object_Camera		= new HashMap<String, Object_Camera>();
////	Map<String, Object_Clipping>	m_mapId_Object_Clipping		= new HashMap<String, Object_Clipping>();
//	Map<String, Object_ParallelHex>	m_mapId_Object_Cubic		= new HashMap<String, Object_ParallelHex>();
//	//Map<String, Object_Group>		m_mapId_Object_Group		= new HashMap<String, Object_Group>();
//	Map<String, Object_Lines>		m_mapId_Object_Lines		= new HashMap<String, Object_Lines>();
//	Map<String, Object_MeshNode>	m_mapId_Object_MeshGrid		= new HashMap<String, Object_MeshNode>();
//	Map<String, Object_MeshTetra4>	m_mapId_Object_MeshTetra4	= new HashMap<String, Object_MeshTetra4>();
//	Map<String, Object_Parabolic>	m_mapId_Object_Parabolic	= new HashMap<String, Object_Parabolic>();
//	Map<String, Object_Sphere>		m_mapId_Object_Sphere		= new HashMap<String, Object_Sphere>();
//	Map<String, Value_String>		m_mapId_Object_String		= new HashMap<String, Value_String>();
//	Map<String, Object_Triangle>	m_mapId_TriFace				= new HashMap<String, Object_Triangle>();
//	Map<String, Object_Point>		m_mapId_TriPoint			= new HashMap<String, Object_Point>();
//	Map<String, Material>			m_mapId_Material			= new HashMap<String, Material>();
//	Map<String, DrawPen>			m_mapId_DrawPen				= new HashMap<String, DrawPen>();
//	////////////////////////////////////////////////////////
//	//	ファイル書き込み時に生成した全データをプールする
//	////////////////////////////////////////////////////////
//	Map<FileIO, XmlData>			m_mapFileIO_Xml				= new HashMap<FileIO, XmlData>();
//	Map<FileIO, String>				m_mapFileIO_String			= new HashMap<FileIO, String>();
//	long 							m_Id = 0;
//
//
//	//************************************************************************//
//	/**
//	 *	add
//	 *	idをキーとしてオブジェクトを格納する。
//	 */
//	//************************************************************************//
//	//				(登録キー,	登録オブジェクト)
//	//private void add(String	id,	FileIO 	obj)			{m_mapId_Object.put(id, obj);}
//	//private void add(String id, StringT obj)			{this.m_mapId_StringT.put(id, obj);}
//	public void add(String id, Value_Double obj)				{this.m_mapId_DoubleT.put			(id, obj);}
//	public void add(String id, Value_DoubleComplex obj)		{this.m_mapId_DoubleComplex.put		(id, obj);}
////	public void add(String id, Object_Camera obj)		{this.m_mapId_Object_Camera.put		(id, obj);}
////	public void add(String id, Object_Clipping obj)		{this.m_mapId_Object_Clipping.put	(id, obj);}
//	public void add(String id, Object_ParallelHex obj)		{this.m_mapId_Object_Cubic.put		(id, obj);}
//	public void add(String id, Object_Directory obj)			{this.m_mapId_Group_Base.put		(id, obj);}
//	//public void add(String id, Object_Group obj)		{this.m_mapId_Object_Group.put(id, obj);}
//	public void add(String id, Object_Lines obj)		{this.m_mapId_Object_Lines.put		(id, obj);}
//	public void add(String id, Object_MeshNode obj)		{this.m_mapId_Object_MeshGrid.put	(id, obj);}
//	public void add(String id, Object_MeshTetra4 obj)	{this.m_mapId_Object_MeshTetra4.put	(id, obj);}
//	public void add(String id, Object_Parabolic obj)	{this.m_mapId_Object_Parabolic.put	(id, obj);}
//	public void add(String id, Object_Sphere obj)		{this.m_mapId_Object_Sphere.put		(id, obj);}
//	public void add(String id, Value_String obj)		{this.m_mapId_Object_String.put		(id, obj);}
//	public void add(String id, Object_Triangle obj)				{this.m_mapId_TriFace.put			(id, obj);}
//	public void add(String id, Object_Point obj)			{this.m_mapId_TriPoint.put			(id, obj);}
//	public void add(String id, Material obj)			{this.m_mapId_Material.put			(id, obj);}
//	public void add(String id, DrawPen obj)				{this.m_mapId_DrawPen.put			(id, obj);}
//	public void add(FileIO fileIO, XmlData xd)			{this.m_mapFileIO_Xml.put			(fileIO, xd);}
//
//	//************************************************************************//
//	/**
//	 *	search
//	 */
//	//************************************************************************//
//	public Value_Double				search_DoubleT(String id)			{return m_mapId_DoubleT.get(id);}
//	public Value_DoubleComplex		search_DoubleComplex(String id)		{return m_mapId_DoubleComplex.get(id);}
//	public Object_Directory			search_Group_Base(String id)		{return m_mapId_Group_Base.get(id);}
////	public Object_Camera		search_Object_Camera(String id)		{return m_mapId_Object_Camera.get(id);}
////	public Object_Clipping		search_Object_Clipping(String id)	{return m_mapId_Object_Clipping.get(id);}
//	public Object_ParallelHex	search_Object_Cubic(String id)		{return m_mapId_Object_Cubic.get(id);}
//	public Object_Lines			search_Object_Lines(String id)		{return m_mapId_Object_Lines.get(id);}
//	public Object_MeshNode		search_Object_MeshGrid(String id)	{return m_mapId_Object_MeshGrid.get(id);}
//	public Object_MeshTetra4	search_Object_MeshTetra4(String id)	{return m_mapId_Object_MeshTetra4.get(id);}
//	public Object_Parabolic 	search_Object_Parabolic(String id)	{return m_mapId_Object_Parabolic.get(id);}
//	public Object_Sphere		search_Object_Sphere(String id)		{return  m_mapId_Object_Sphere.get(id);}
//	public Value_String		search_Object_String(String id)		{return  m_mapId_Object_String.get(id);}
//	public Object_Triangle		search_TriFace(String id)			{return  m_mapId_TriFace.get(id);}
//	public Object_Point			search_TriPoint(String id)			{return  m_mapId_TriPoint.get(id);}
//	public Material				search_Material(String id)			{return  m_mapId_Material.get(id);}
//	public DrawPen				search_DrawPen(String id)			{return  m_mapId_DrawPen.get(id);}
//	public XmlData				search_XmlData(FileIO fileIO)		{return  m_mapFileIO_Xml.get(fileIO);}
//
//	public Entity_Base search_Object_Base(String id)
//	{
//		////////////////////////////////////////
//		//	Object_Baseは抽象クラス。
//		//	そのためObject_Baseを継承したクラスを探す
//		////////////////////////////////////////
//		Entity_Base obj;
////		obj = m_mapId_Object_String.get(id);		if(obj != null){return obj;}
//		obj = m_mapId_Object_MeshGrid.get(id);		if(obj != null){return obj;}
////		obj = m_mapId_Object_Camera.get(id);		if(obj != null){return obj;}
////		obj = m_mapId_Object_Clipping.get(id);		if(obj != null){return obj;}
//		obj = m_mapId_Object_Cubic.get(id);			if(obj != null){return obj;}
//		obj = m_mapId_Object_Lines.get(id);			if(obj != null){return obj;}
//		obj = m_mapId_Object_MeshTetra4.get(id);	if(obj != null){return obj;}
//		obj = m_mapId_Object_Parabolic.get(id);		if(obj != null){return obj;}
//		obj = m_mapId_Object_Sphere.get(id);		if(obj != null){return obj;}
//			
//		return null;
//	}
//
//	
//	//************************************************************************//
//	/**
//	 *	コンストラクタ
//	 *
//	 *	@param	
//	 *	@return	
//	 *	@version
//	 */
//	//************************************************************************//
//	public FileInputOutput()
//	{
//		//クラス名とオブジェクトを取得（全て「FileIO」をimplementしているとする）
//		ArrayList<FileIO> arryFileIO = new ArrayList<FileIO>();
//		
////		Group_Base gTest = new Group_Base();
//		Object_Directory gTest = Object_Directory.factory_or_search("test_FileInputOutput");
//
//		//全種類のオブジェクト実体を取得
//		//arryFileIO.add(new StringT				());
//		arryFileIO.add(new Value_Double				());
//		arryFileIO.add(new Value_DoubleComplex		());
////		arryFileIO.add(new Object_Camera		());
////		arryFileIO.add(new Object_Clipping		());
//		arryFileIO.add(new Object_ParallelHex	());
////		arryFileIO.add(new Group_Base			());
//		//arryFileIO.add(new Object_Group			());
//		arryFileIO.add(new Object_Lines			());
//		arryFileIO.add(new Object_MeshNode		());
//		arryFileIO.add(new Object_MeshTetra4	());
//		arryFileIO.add(new Object_Parabolic		());
//		arryFileIO.add(new Object_Sphere		());
//		arryFileIO.add(new Value_String		());
//		arryFileIO.add(new Object_Triangle		());
//		arryFileIO.add(new Object_Point			());
//		arryFileIO.add(new Material				());
//		arryFileIO.add(new DrawPen				());
//
//		//全種類のオブジェクトをMapに追加（オブジェクト名とオブジェクト実体）
//		int nSize = arryFileIO.size();
//		m_map_ObjName_ObjFileIO = new HashMap<String, FileIO>(nSize);
//		for(int ic=0; ic<nSize; ic++)
//		{
//			FileIO fileIO = arryFileIO.get(ic);
//			m_map_ObjName_ObjFileIO.put
//			(
//				fileIO.getClass().getSimpleName(),
//				fileIO
//			);
//		}
//	}
//
//	//************************************************************************//
//	/**
//	 *	ファイルからオブジェクトタイプを読み込み、各オブジェクトを実体化していく
//	 *
//	 *	@param	
//	 *	@return	
//	 *	@version
//	 */
//	//************************************************************************//
//	public void read_file(
//		String		sFilePath,	//データファイルフルパス
//		Object_Directory	topGroup	//データを格納するトップのGroup
//	)
//	{
//		//ファイルがこのプログラムの形式なのかチェック。
//		//※ここでは常にxml形式で書かれているとする。
//		
//		///////////////////////////////////
//		//	XmlDataクラスに読み込み
//		///////////////////////////////////
//		XmlData topXml = new XmlData();
//		XmlData.read_divide_xml_file(
//			sFilePath,	//ファイルのフルパス
//			topXml		//一番上のXmlData
//		);
//		
//		///////////////////////////////////
//		//	子をループして実体化していく
//		///////////////////////////////////
//		ArrayList<XmlData> arryXml = topXml.get_children();
//		int nSize = arryXml.size();
//		for(int ic=0; ic<nSize; ic++)
//		{
//			XmlData xml = arryXml.get(ic);
//			
//			//Xmlのタグ名を取得
//			String sXmlName = xml.get_name();
//			
//			//タグ名に応じて、呼び出す生成クラスを変える
//			FileIO fileIO = m_map_ObjName_ObjFileIO.get(sXmlName);
//			if(fileIO == null)
//			{
//				//サポートしていないオブジェクト名
//				continue;
//			}
//			
//			//idを取得
//			String sID = xml.search_option_value("id");
//			
//			///////////////////////////
//			//	オブジェクトを実体化
//			///////////////////////////
//			fileIO.factory_and_add(
//				sID,
//				this
//			);
//		}
//		
//		
//		///////////////////////////////////
//		//	子の中身を設定していく
//		///////////////////////////////////
//		for(int ic=0; ic<nSize; ic++)
//		{
//			XmlData xml = arryXml.get(ic);
//			
//			//Xmlのタグ名を取得
//			String sXmlName = xml.get_name();
//			
//			//タグ名に応じて、呼び出す生成クラスを変える
//			FileIO fileIO = m_map_ObjName_ObjFileIO.get(sXmlName);
//			
//			//サポートしていないオブジェクト名
//			if(fileIO == null){
//				continue;
//			}
//			
//			///////////////////////////
//			//	中身を設定
//			///////////////////////////
//			fileIO.read_data(
//				xml,		//各オブジェクト形式のXML(例:<Group_Base>1,XXX 2,XXX ･･･ </Group_Base>)
//				this,		//生成したオブジェクトを全て格納
//				topGroup	//Xmlオプションに"top"とあるものはトップGroupにぶら下がる
//			);
//		}
//	}
//	
//	//************************************************************************//
//	/**
//	 *	ファイル書き込み時のIDを作成する
//	 *	整数をカウントアップしていく。
//	 *
//	 *	@param	
//	 *	@return	
//	 *	@version
//	 */
//	//************************************************************************//
//	public String ask_id(
//		FileIO	fileIO
//	)
//	{
//		String sIdBuf = this.m_mapFileIO_String.get(fileIO);
//		
//		if(sIdBuf == null)
//		{
//			//IDをカウントアップ
//			this.m_Id++;
//			//IDの文字列を渡す
//			sIdBuf = Long.toString(this.m_Id);
//			//格納する
//			this.m_mapFileIO_String.put(fileIO, sIdBuf);
//		}
//
//		return sIdBuf;
//	}
//
//	//************************************************************************//
//	/**
//	 *	xmlを検索。なければ作る。
//	 *
//	 *	@param	
//	 *	@return	
//	 *	@version
//	 */
//	//************************************************************************//
//	public boolean search_or_create_xml(
//		XmlData			xmlDataParent,	//IO	Xmlデータ。親に追加する。
//		FileIO			objFileIO,		//I		Xmlデータの元となるオブジェクト
//		FileInputOutput	poolFileIO,		//I		Xmlデータを全て格納
//		XmlData[]		xmlDataMySelf,	//O		検索された、または作られたXmlData
//		String[]		idMyself		//O		検索された、または作られたid
//	)
//	{
//		/////////////////////////////////////////
//		//	XmlDataが既にあるかチェック
//		/////////////////////////////////////////
//		XmlData xmlBuf = poolFileIO.search_XmlData(objFileIO); 
//		if(xmlBuf != null)
//		{
//			//自分自身は既に書かれている。
//			//またその下も既に書かれているということなので、取得したものを親に入れてそのまま返す。
//			
//			//検索したXmlDataを返す
//			xmlDataMySelf[0] = xmlBuf;
//			
//			//idを取得
//			idMyself[0] = xmlBuf.search_option_value("id");
//
//			//親に追加
//			xmlDataParent.add_child(xmlBuf);
//			
//			//検索にあった。つまり既に完成している。
//			return true;
//		}
//
//		/////////////////////////////////////////
//		//	XmlDataを新規作成
//		/////////////////////////////////////////
//		//Objectのタグ名を作成
//		String sObjName = objFileIO.getClass().getSimpleName();
//		//書き出しObjectに対応したXmlDataを作成し、オプションとしてidを設定
//		xmlBuf = new XmlData(sObjName);
//
//		//XmlDataを返す
//		xmlDataMySelf[0] = xmlBuf;
//		
//		//idを作成
//		String sId = poolFileIO.ask_id(objFileIO);
//		
//		//idを追加
//		xmlDataMySelf[0].add_option_and_value("id", sId);
//		idMyself[0] = sId;
//		
//		//親に追加
//		xmlDataParent.add_child(xmlBuf);
//
//		//////////////////////////////
//		//	FileIOとXmlDataを登録しておく
//		//////////////////////////////
//		poolFileIO.add(objFileIO, xmlBuf);
//		
//		//検索になかったので作った。
//		return false;
//	}
//	//************************************************************************//
//	/**
//	 *	書き出しオブジェクトの中のデータもxmlを検索または作成。
//	 *	同時に、書き出しオブジェクトのデータ書き出し文字列を作成
//	 *
//	 *	@param	
//	 *	@return	
//	 *	@version
//	 */
//	//************************************************************************//
//	public String ask_write_line_and_create_xml
//	(
//		XmlData			xmlDataParent,	//OUT	親となるXmlデータ。これに追加する。
//		String			sWriteLine,		//IO	親となるXmlデータのデータ書き出し文字列。
//		FileIO			objFileIO,		//IN	子となるXmlデータの元となるオブジェクト。
//		FileInputOutput	poolFileIO		//IN	Xmlデータを全て格納
//	)
//	{
//		if(objFileIO == null){
//			return sWriteLine;
//		}
//		
//		//子のXmlData内部を作成
//		String id = objFileIO.write_data(
//			//xmlChild,
//			xmlDataParent,
//			poolFileIO
//		);
//
//		//チェックして文字列追加
//		if(sWriteLine != null)
//		{
//			sWriteLine += String.format(",%s", id);
//		}
//		else
//		{
//			sWriteLine = String.format(",%s", id);
//		}
//
//		return sWriteLine;
//	}
//	
//	//************************************************************************//
//	/**
//	 *	Xmlデータ読み込み補助
//	 *
//	 *	@param	
//	 *	@return	
//	 *	@version
//	 */
//	//************************************************************************//
//	public Integer read_one_line(
//		String				sOneLine,
//		ArrayList<String>	sData
//	)
//	{
//		//コメントは除く
//		sOneLine = utility.string_remove_comment(sOneLine, "//");
//		
//		//カンマで分解
//		utility.string_factorize3(sOneLine, ",", sData);
//		
//		//文字列前後の空白、タブは除く
//		utility.string_trim(sData);
//		
//		//フォーマット識別番号を取得。
//		//整数では無い場合、nullとなる。
//		return utility.change_String_To_Integer(sData.get(0));
//	}
//
//	
//	
//	
//	
//	//************************************************************************//
//	/**
//	 *	データをXmlDataに変換し、ファイルに保存
//	 *
//	 *	@param	
//	 *	@return	
//	 *	@version
//	 */
//	//************************************************************************//
//	public void write_file(
//		String		sSaveFilePath,	//保存ファイルパス
//		Object_Directory	gSaveData		//保存するGroupトップ
//	)
//	{
//		try
//		{
//			//Groupを探索していき、XmlDataを作成する。
//			XmlData xmlDataRoot = new XmlData("Root");
//	
//			//Groupループ
////			ArrayList<Group_Base> arryGroup = gSaveData.get_ArrayGroup();
////			if(arryGroup != null)
////			{
////				for(int ic=0; ic<arryGroup.size(); ic++)
////				{
////					arryGroup.get(ic).write_data(
////						xmlDataRoot,	//IO	親のXmlData。このXmlDataの子として登録する。
////						this			//I		書き込み時の全てのXmlDataを持つ。
////					);
////				}
////			}
////	
////			//Object_Baseループ
////			ArrayList<Object_Base> arryObject = gSaveData.get_ArrayObject();
////			if(arryObject != null)
////			{
////				//XmlData xmlDataChild = null;
////				for(int ic=0; ic<arryObject.size(); ic++)
////				{
////					arryObject.get(ic).write_data(
////						xmlDataRoot,	//IO	親のXmlData。このXmlDataの子として登録する。
////						this			//I		書き込み時の全てのXmlDataを持つ。
////					);
////				}
////			}
//			
//			//top属性を追加
//			ArrayList<XmlData>	arryXmlData = xmlDataRoot.get_children();
//			if(arryXmlData != null)
//			{
//				for(int ic=0; ic<arryXmlData.size(); ic++)
//				{
//					arryXmlData.get(ic).add_option("top");
//				}
//			}
//	
//			//ファイルに書き出し
//			PrintWriter pw = utility.open_write_file(sSaveFilePath, false);
//			
//			for(int ic=0; ic<xmlDataRoot.get_children().size(); ic++)
//			{
//				xmlDataRoot.get_children().get(ic).write_myself_for_moredyn(pw);
//			}
//			
//			pw.close();
//		}
//		catch (Exception ex)
//		{
//			utility.write_exception(ex);
//		}
//
//
//	}
////	//************************************************************************//
////	/**
////	 *	idをキーとして、格納されているFileIOを返す。
////	 */
////	//************************************************************************//
////	///public Entity_Base search
////	public FileIO search
////	(
////		String	sObjClassName,	//クラスの名前
////		String	id				//Objectの一意のID
////	)
////	{
////		///////////////
////		//	検索する
////		///////////////
////		//Object_Base objBase =  m_mapId_Object.get(id);
////		FileIO objFileIO =  m_mapId_FileIO.get(id);
////
////		//ある場合はそのまま返す。
////		if(objBase != null)
////		{
////			//return objBase;
////			return objFileIO;
////		}
////
////		/////////////////////////////////
////		//	ない場合は生成して返す
////		/////////////////////////////////
////		//型名を判定
////		switch(sObjClassName)
////		{
////			case "StringT":				return new StringT				(sDefaultName);
////			case "Value_Double":				return new Value_Double				(sDefaultName);
////			case "Value_DoubleComplex":		return new Value_DoubleComplex		(sDefaultName);
////			case "Object_Camera":		return new Object_Camera		(sDefaultName);
////			case "Object_Clipping":		return new Object_Clipping		(sDefaultName);
////			case "Object_Cubic":		return new Object_Cubic			(sDefaultName);
////			case "Object_Group":		return new Object_Group			(sDefaultName);
////			case "Object_Lines":		return new Object_Lines			(sDefaultName);
////			case "Object_MeshGrid":		return new Object_MeshGrid		(sDefaultName);
////			case "Object_MeshTetra4":	return new Object_MeshTetra4	(sDefaultName);
////			case "Object_Parabolic":	return new Object_Parabolic		(sDefaultName);
////			case "Object_Sphere":		return new Object_Sphere		(sDefaultName);
////			case "Value_String":		return new Value_String		(sDefaultName);
////			case "TriFace":				return new TriFace				(sDefaultName);
////			case "TriPoint":			return new TriPoint				(sDefaultName);
////		}
////		
////		return null;
////	}
}