package _30_Command;

import _40_Value.Value_Double;
import _42_Utility.XmlData;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import _42_Utility.util;

public interface Command_Imp
{
	public void			set_command_string(String str);
	public String		get_command_string();
	public Command_Imp	factory();
	
	//アンドゥ
	default public void undo(){}	
	//リドゥ
	default public void redo(){}		
	//各Commandが実行される
	public String execute(MouseEvent e);
	
	
    //************************************************************************//
	/**
	 * 
	 * 子が使う便利関数
	 * Command文からXmlDataを生成して渡す。
	 */
    //************************************************************************//
	static public XmlData ask_xml(String m_command_string)
	{
		return read(m_command_string);
	}
	
	/***************************************************************************
	 * 
	 * StringからXmlDataを生成するえ
	 * 
	 * @param statements
	 * @return 
	 */
    //************************************************************************//
	static public XmlData read(String ... statements)
	{
		XmlData buf = new XmlData("デフォルトコマンド", null, statements);
		
		if(buf.get_children() != null)
		{
//			return buf.get_children().get(0);
			return buf;
		}
		
		return null;
	}

	//************************************************************************//
	/**
	*	指定したxmldataの子のタグ名を指定すると、子の要素をStringのまま取得する。
	*	1段目の子のみが対象。2段目以降は見ない。
	*
	*	入力するXmlDataの例：
	*	<grid>	//これが現在のXmlDataとする。
	*		<id>100</id>
	*		<position>0.000, 5.000, 3.000</position>
	*		<position>6.000, 3.000, 0.000</position>
	*		<position>
	*			6.000
	*			2.000
	*			3.000
	*		</position>
	*		<test_data>
	*			<position>1.111, 2.222, 3.333</position>
	*		</test_data>
	*	</grid>
	*
	*
	*	例　：tag_nameを"position"としたとき。
	*	結果：ArrayList<String[]>の中身は以下となる。
	*			ArrayList.get(0) = {"0.000", "5.000", "3.000"};
	*			ArrayList.get(1) = {"6.000", "3.000", "0.000"};
	*			ArrayList.get(2) = {"6.000", "2.000", "3.000"};
	*		※<test_data>の中の<position>は取得されない
	*
	*	@param	
	*	@return	
	*	@version
	*/
    //************************************************************************//
	static public ArrayList<String[]> read_command_string(
		XmlData		xmldata,	//I		読み込み対象のXmlData
		String		tag_name	//I		xmlのタグの名前
	)
	{
		XmlData xml_data = xmldata;

		//子にtagがあるか検索
		ArrayList<XmlData> arry_xmldata = xml_data.search_xmldata_by_tag_name(tag_name);

		//tagがない場合は抜ける
		if(arry_xmldata == null)
		{
			return null;
		}

		//要素をカンマ分割してStringで取得
		ArrayList<String[]> arryElementString = new ArrayList<String[]>();
		for(XmlData xmldata_buf : arry_xmldata)
		{
			ArrayList<String> arry_string = xmldata_buf.get_elements();

			//文字列を分解
			ArrayList<String> arryData = new ArrayList<String>();
			for(String sLine : arry_string)
			{
				//カンマで分割
				util.string_factorize3(
					sLine,		//分割対象の文字列
					",",		//分割位置を指定する文字列(","など)
					arryData	//分割後の文字列の配列
				);
			}

			//各Stringをトリムする
			ArrayList<String> s_string_elem = new ArrayList<String>();
			for(String s_elem : arryData)
			{
				//文字列前後の空白、タブは除く
				s_elem = util.string_trim(s_elem);
				
				//格納
				s_string_elem.add(s_elem);
			}

			//格納
			arryElementString.add(s_string_elem.toArray(new String[0]));
		}
		
		return arryElementString;
	}

	/***************************************************************************
	*	指定したxmldataの子のタグ名を指定すると、子の要素をdoubleに変換して取得する。
	*	1段目の子のみが対象。2段目以降は見ない。
	*
	*	@param
	*	@return
	*	@version
	*/
	//**************************************************************************
	static public ArrayList<double[]> read_command_double(
		XmlData		xmldata,	//I		読み込み対象のXmlData
		String		tag_name	//I		xmlのタグの名前
	)
	{
		//最初にStringとして取得
		ArrayList<String[]> arry_elem_strings = read_command_string(
			xmldata,	//I		読み込み対象のXmlData
			tag_name	//I		xmlのタグの名前
		);
		
		//全てのStringをdoubleに変換
		ArrayList<double[]> arry_elem_double = new ArrayList<double[]>(arry_elem_strings.size());
		arry_elem_strings.stream()
			.map(a -> util.change_String_To_Double(a))
			.forEachOrdered(a -> arry_elem_double.add(a));

		return arry_elem_double;
	}
	/***************************************************************************
	*	指定したxmldataの子のタグ名を指定すると、子の要素をdoubleに変換して取得する。
	*	1段目の子のみが対象。2段目以降は見ない。
	*
	*	@param
	*	@return
	*	@version
	*/
	//**************************************************************************
	static public ArrayList<Value_Double[]> read_command_valuedouble(
		XmlData		xmldata,	//I		読み込み対象のXmlData
		String		tag_name	//I		xmlのタグの名前
	)
	{
		ArrayList<Value_Double[]> out_arry_vd = new ArrayList<>();

		for(double[] arry_d : read_command_double(xmldata,tag_name)){
			out_arry_vd.add(change_double_to_valuedouble(arry_d));
		}

		return out_arry_vd;
	}
	/***************************************************************************
	*	指定したxmldataの子のタグ名を指定すると、子の要素を複素数に変換して取得する。
	*	1段目の子のみが対象。2段目以降は見ない。
	*
	*	@param
	*	@return
	*	@version
	*/
    //************************************************************************//
	static public ArrayList<double[][]> read_command_double_complex(
		XmlData		xmldata,	//I		読み込み対象のXmlData
		String		tag_name	//I		xmlのタグの名前
	)
	{
		//最初にStringとして取得
		ArrayList<String[]> arry_elem_strings = read_command_string(
			xmldata,	//I		読み込み対象のXmlData
			tag_name	//I		xmlのタグの名前
		);
		
		//全てのStringをdoubleに変換
		ArrayList<double[][]> arry_elem_double = new ArrayList<>(arry_elem_strings.size());
		arry_elem_strings.stream()
			.map(a -> util.change_String_To_Double_Complex(a))
			.forEachOrdered(a -> arry_elem_double.add(a));

		return arry_elem_double;
	}
	//************************************************************************//
	/**
	*	指定したxmldataの子のタグ名を指定すると、子の要素をintegerに変換して取得する。
	*	1段目の子のみが対象。2段目以降は見ない。
	*
	*	@param
	*	@return
	*	@version
	*/
    //************************************************************************//
	static public ArrayList<int[]> read_command_integer(
		XmlData		xmldata,	//I		読み込み対象のXmlData
		String		tag_name	//I		xmlのタグの名前
	)
	{
		//最初にStringとして取得
		ArrayList<String[]> arry_elem_strings = read_command_string(
			xmldata,	//I		読み込み対象のXmlData
			tag_name	//I		xmlのタグの名前
		);
		
		//全てのStringをintegerに変換
		ArrayList<int[]> arry_elem_int = new ArrayList<int[]>(arry_elem_strings.size());
		arry_elem_strings.stream()
			.map(a -> util.change_String_To_Integer(a))
			.forEachOrdered(a -> arry_elem_int.add(a));

		return arry_elem_int;
	}
	//************************************************************************//
	/**
	*	指定したxmldataの子のタグ名を指定すると、子の要素をlongに変換して取得する。
	*	1段目の子のみが対象。2段目以降は見ない。
	*
	*	@param
	*	@return
	*	@version
	*/
    //************************************************************************//
	static public ArrayList<long[]> read_command_long(
		XmlData		xmldata,	//I		読み込み対象のXmlData
		String		tag_name	//I		xmlのタグの名前
	)
	{
		//最初にStringとして取得
		ArrayList<String[]> arry_elem_strings = read_command_string(
			xmldata,	//I		読み込み対象のXmlData
			tag_name	//I		xmlのタグの名前
		);
		
		
		//全てのStringをintegerに変換
		ArrayList<long[]> arry_elem_long = new ArrayList<long[]>(arry_elem_strings.size());
		arry_elem_strings.stream()
			.map(a -> util.change_String_To_Long(a))
			.forEachOrdered(a -> arry_elem_long.add(a));

		return arry_elem_long;
	}	
	//************************************************************************//
	/**
	*	指定したxmldataの子のタグ名を指定すると、該当する子のXmlDataを取得する。
	*	1段目の子のみが対象。2段目以降は見ない。
	*
	*	@param
	*	@return
	*	@version
	*/
    //************************************************************************//
	static public ArrayList<XmlData> read_command_tag(
		XmlData		xmldata,	//I		読み込み対象のXmlData
		String		tag_name	//I		xmlのタグの名前
	)
	{
		//子にtagがあるか検索
		return xmldata.search_xmldata_by_tag_name(tag_name);
	}
	//************************************************************************//
	/**
	*	double[]→Value_Double[]
	*
	*	@param
	*	@return
	*	@version
	*/
	//**************************************************************************
	static public Value_Double[] change_double_to_valuedouble(double[] in_double_arry)
	{
		int num = in_double_arry.length;
		Value_Double[] arry_vd = new Value_Double[num];
		
		for(int ic=0; ic<num; ic++){
			arry_vd[ic] = new Value_Double(in_double_arry[ic]);
		}
		
		return arry_vd;
	}

}
