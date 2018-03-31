package _42_Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;


public class XmlData
{
	String				m_Name			= null;
	ArrayList<String[]>	m_Options		= null;
	XmlData				m_Parent		= null;		//親
	ArrayList<XmlData>	m_Children		= null;		//子（XmlData）
	ArrayList<String>	m_Elements		= null;		//子（要素）
	int					m_Index			= -1;		//自分が総合何番目か設定する値
	int					m_TotalIndex	= 0;		//子と要素が混ざっているが、総合何番か設定する為の値

	static final char[] cPass = {' ', '\t'};		//読み飛ばす文字列

	///////////////////////////////////////////
	//	constructor1
	///////////////////////////////////////////
	public XmlData()
	{
	}
	///////////////////////////////////////////
	//	constructor2
	///////////////////////////////////////////
	public XmlData(
		String name	//IN	名前
	)
	{
		m_Name = name;
	}
	///////////////////////////////////////////
	//	constructor3
	///////////////////////////////////////////
	public XmlData(
			String	name, 	//IN	名前
			XmlData	parent	//IN	親のXmlData
	)
	{
		m_Name = name;
		m_Parent = parent;
		parent.add_child(this);
	}
	///////////////////////////////////////////
	//	constructor4
	///////////////////////////////////////////
	public XmlData(
		String				name, 		//IN	名前
		XmlData				parent,		//IN	親のXmlData
		ArrayList<XmlData>	children	//IN	子のXmlData
	)
	{
		m_Name = name;
		m_Parent = parent;
		m_Children = children;
	}
	///////////////////////////////////////////
	//	constructor5
	///////////////////////////////////////////
	public XmlData(XmlData parent)
	{
		m_Parent = parent;
	}
	///////////////////////////////////////////
	//	constructor6
	///////////////////////////////////////////
	public XmlData(
		String		name, 		//IN	名前
		XmlData		parent,		//IN	親のXmlData
		String[]	statements	//IN	Xmlのテキストデータ。この内容をXmlData化する。
	)
	{
		m_Name = name;
		m_Parent = parent;
		XmlData.read_divide_xml_file(statements, this);
	}
	///////////////////////////////////////////
	//	setter
	///////////////////////////////////////////
	public void set_name(String  name)							{m_Name = name;}
	public void set_parent(XmlData xmlData)						{m_Parent = xmlData;}
	public void set_children(ArrayList<XmlData> arryDataXml)	{m_Children = arryDataXml;}
	public void set_elements(ArrayList<String> arryString)		{m_Elements = arryString;}
	public void set_index(int nIndex)							{m_Index = nIndex;}
	///////////////////////////////////////////
	//	getter
	///////////////////////////////////////////
	public String  				get_name()		{return m_Name;}
	public ArrayList<String[]>  get_options()	{return m_Options;}
	public XmlData				get_parent()	{return m_Parent;}
	public ArrayList<XmlData>	get_children()	{return m_Children;}
	public ArrayList<String>	get_elements()	{return m_Elements;}
	public int					get_index()	{return m_Index;}
	///////////////////////////////////////////
	//	ask
	///////////////////////////////////////////
	public String	ask_element(int nIndex)
	{
		return m_Elements.get(nIndex);
	}
	///////////////////////////////////////////
	//	オプションを追加
	///////////////////////////////////////////
	public void add_option(
		String	sOption	//IN	追加するオプション名
	)
	{
		//まだ作成していない場合は作成
		if(m_Options == null)
		{
			m_Options = new ArrayList<String[]>();
		}
		
		//オプションを追加(配列にしているのは、オプションの値が指定されていた場合を想定)
		//例  type=abc
		String[] sBuf = {sOption};
		
		//追加
		m_Options.add(sBuf);		
	}
	///////////////////////////////////////////
	//	オプションの値を追加
	///////////////////////////////////////////
	public void add_option_value
	(
		String	sOptionValue	//IN	追加するオプションの値
	)
	{
		//まだ作成していない場合
		if(m_Options == null)
		{
			//この状態はありえない
			return;
		}
		
		//最後に追加されたオプションを編集
		int nLast = m_Options.size() - 1;
		
		if(m_Options.get(nLast).length != 1)
		{
			//既にオプションの値が入っている。
//			return "Warning:オプションの値が既に入っています";
			return;
		}
		
		String[] sBuf = {m_Options.get(nLast)[0], sOptionValue};
		
		//最後を削除
		m_Options.remove(nLast);
		//追加
		m_Options.add(sBuf);		
	}
	///////////////////////////////////////////
	//	オプションの値を追加
	///////////////////////////////////////////
	public void add_option_and_value
	(
		String sOption,
		String sValue
	)
	{
		String[] sOptionAndValue = {sOption, sValue};

		add_option_and_value
		(
			sOptionAndValue
		);
	}
	///////////////////////////////////////////
	//	オプションの値を追加
	///////////////////////////////////////////
	public void add_option_and_value
	(
		String[] sOptionAndValue
	)
	{
		//まだ作成していない場合は作成
		if(m_Options == null)
		{
			m_Options = new ArrayList<String[]>();
		}
		
		//オプションを追加(配列にしているのは、オプションの値が指定されていた場合を想定)
		//例  type=abc
		//配列に２つあるかチェック
		if(sOptionAndValue.length != 2)
		{
			//オプション名と値の２つだけではない。
//			return "Warning:オプションの指定が正しくありません。";
			return;
		}
		
		//追加
		m_Options.add(sOptionAndValue);		
	}

	///////////////////////////////////////////
	//	子供を追加
	///////////////////////////////////////////
	public void add_child(XmlData xmlData)
	{
		//まだ生成していない場合は生成する
		if(m_Children == null)
		{
			m_Children = new ArrayList<XmlData>();
		}
		//総合何番目か設定
		int nInd = xmlData.get_index();
		if(nInd != -1)
		{
			//そんなことはありえない。循環が起きている可能性あり。
			return;
		}
		xmlData.set_index(this.m_TotalIndex);
		
		//追加
		m_Children.add(xmlData);
		
		//親を登録
		xmlData.set_parent(this);
		
		//総合インデックスを増やす
		this.m_TotalIndex++;
	}
	///////////////////////////////////////////
	//	要素を追加
	///////////////////////////////////////////
	public void add_element(String element)
	{
		//まだ生成していない場合は生成する
		if(m_Elements == null)
		{
			m_Elements = new ArrayList<String>();
		}
		//追加
		m_Elements.add(element);

		//総合インデックスを増やす
		this.m_TotalIndex++;
	}

	//************************************************************************//
	/**
	 *	xmlデータをファイルに書き出す
	 *
	 *	@param	Boolean　bAppendOn:	追記:true, 上書き:false
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public int write_xml_file(
			String	sFilePath,	//IN	ファイルパス名
			Boolean	bAppendOn,	//IN	追記:true, 上書き:false
			String	sIncWord	//IN	字下げの文字列（空白やタブを想定）
	)
	{
		//上書きモードで開く
		PrintWriter pw = util.file_open_write(sFilePath, bAppendOn);
		if(pw == null)
		{
			return -1;
		}

		//xmlファイルを記述
		write_myself(pw, sIncWord, 0);

		//ファイルを閉じる
		pw.close();
		
		return 0;
	}
	//************************************************************************//
	/**
	 *	xmlデータをファイルに書き出す
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	private int write_myself(
		PrintWriter	pw,			//IN	
		String		sIncWord,	//IN	字下げの文字列（空白やタブを想定）
		int			nLayer		//IN	子の階層
	)
	{
		String sIncrement = "";

		/////////////////////////////////////
		//インクリメントを作成
		//例：タブで2階層なら、\t\t
		/////////////////////////////////////
		for(int ic=0; ic<nLayer; ic++)
		{
			sIncrement += sIncWord;
		}

		/////////////////////////////////////
		//自分のタグとオプションを記述する
		/////////////////////////////////////
		this.print_tag_option(
			pw,			//プリントクラス
			sIncrement	//字下げ
		);

		/////////////////////////////////////
		//子や要素は、更に1階層増やす
		/////////////////////////////////////
		String sIncrementBuf = sIncrement + sIncWord;

		/////////////////////////////////////
		//子と要素を順番に記述する
		//子のXMLは、自分が何番目に記述されるかわかるように、番号を保持している。
		//要素は保持していない。
		/////////////////////////////////////
		int nIndexMax = this.m_TotalIndex;
		int nLayerNext = nLayer + 1;
		int nChildIndex = 0;
		int nElemIndex = 0;

		for(int ic=0; ic<nIndexMax; ic++)
		{
			//子がある場合
			if(this.m_Children != null)
			{
				//インデックスを取得
				int nIndBuf = this.m_Children.get(nChildIndex).get_index();

				//カレントインデックスと同じ場合
				if(nIndBuf == ic)
				{
					//子の内容を記述する
					this.m_Children.get(nChildIndex).write_myself(pw, sIncWord, nLayerNext);
					
					//子のインデックスを1つ増やす
					nChildIndex++;
				}
				//カレントインデックスの方が小さい場合
				else if(nIndBuf > ic)
				{
					//要素を書き出す
					int nMax = nElemIndex + nIndBuf - ic;
					for(int jc=nElemIndex; jc<nMax; jc++)
					{
//						//要素を書き出す
//						pw.println(String.format("%s%s", sIncrementBuf, m_Elements.get(jc)));
						write_line(pw, sIncrementBuf, m_Elements.get(jc));
						
						//要素のインデックスを1つ増やす
						nElemIndex++;
						
						//全体インデックスを1つ増やす
						ic++;
					}
				}
				else
				{
					//カレントインデックスの方が大きい場合はありえない。エラーを返す。
					return -1;
				}
			}
			else
			{
				if(m_Elements != null)
				{
					int nMax = m_Elements.size();
					for(int jc=0; jc<nMax; jc++)
					{
						//要素を書き出す
//						pw.println(String.format("%s%s", sIncrementBuf, m_Elements.get(jc)));
						write_line(pw, sIncrementBuf, m_Elements.get(jc));
					}
				}
					
				//ループを抜ける
				break;

			}
		}

		/////////////////////////////////////
		//自分の終わりのタグを記述する
		/////////////////////////////////////
		pw.println(String.format("%s</%s>", sIncrement, this.get_name()));
		
		return 0;
	}
	
	//************************************************************************//
	/**
	 *	ファイルに書き出す。
	 *	Stringに複数の文が入っているときも、それぞれの列の先頭にインデックスを追加する。
	 */
	//************************************************************************//
	private void write_line(
		PrintWriter pw,
		String	s_increment,
		String	in_s_line
	)
	{
		//要素を書き出す。
		int n_start = 0;
		for(;;)
		{
			String s_substr = "";

			//改行まで
			int n_indexof = in_s_line.indexOf(util.newline(), n_start);

			if(n_indexof == -1)
			{
				s_substr = in_s_line.substring(n_start, in_s_line.length());
				//要素を書き出す
				pw.println(String.format("%s%s", s_increment, s_substr));
				break;
			}
			else
			{
				s_substr = in_s_line.substring(n_start, n_indexof);
				n_start = n_indexof + 1;
				//要素を書き出す
				pw.println(String.format("%s%s", s_increment, s_substr));
			}
		}		
	}
	//************************************************************************//
	/**
	 *	xmlデータをファイルに書き出す
	 *	MoreDyn用
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public void write_myself_for_moredyn(
		PrintWriter	pw			//IN
	)
	{
		/////////////////////////////////////
		//自分のタグとオプションを記述する
		/////////////////////////////////////
		this.print_tag_option(
			pw,		//プリントクラス
			""		//字下げ
		);
		/////////////////////////////////////
		//要素を全て書き出す
		/////////////////////////////////////
		if(m_Elements != null)
		{
			for(int jc=0; jc<m_Elements.size(); jc++)
			{
				//要素を書き出す
				pw.println(String.format("%s%s", "\t", m_Elements.get(jc)));
			}
		}
		/////////////////////////////////////
		//自分の終わりのタグを記述する
		/////////////////////////////////////
		pw.println(String.format("</%s>", this.get_name()));

		/////////////////////////////////////
		//子のXmlDataをそれぞれ書き出し
		/////////////////////////////////////
		if(this.m_Children != null)
		{
			for(int jc=0; jc<this.m_Children.size(); jc++)
			{
				//子も書き出し
				this.m_Children.get(jc).write_myself_for_moredyn(pw);
			}
		}
	}
	//************************************************************************//
	/**
	 *	自分のタグとオプションを記述する
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	private void print_tag_option(
		PrintWriter	pw,			//IN	
		String		sIncrement	//IN	字下げの文字列（空白やタブを想定）
	)
	{
		pw.print(String.format("%s<%s", sIncrement, this.get_name()));
		if(m_Options != null)
		{
			for(int ic=0; ic<m_Options.size(); ic++)
			{
				String[] sBufs = m_Options.get(ic);
				//オプション名と値を記述
				if(sBufs.length > 1)
				{
					//オプション名と値を記述
					pw.print(String.format(" %s=%s", sBufs[0], sBufs[1]));
				}
				else
				{
					//オプション名を記述
					pw.print(String.format(" %s", sBufs[0]));
				}
			}
		}
		pw.println(">");
	}
	//************************************************************************//
	/**
	 *	指定したタグ名のXmlDataを取得
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public ArrayList<XmlData> search_xmldata_by_tag_name(
			String	sTagName	//IN	検索するタグ名
	)
	{
		ArrayList<XmlData> arryXmlData = null;

		if(this.m_Children == null)
		{
			return null;
		}
		
		int nMax = this.m_Children.size();
		for(int ic=0; ic<nMax; ic++)
		{
			//子を取得
			String sBuf = this.m_Children.get(ic).get_name();
			
			//名前を比較
			if(sTagName.compareTo(sBuf) == 0)
			{
				if(arryXmlData == null)
				{
					arryXmlData = new ArrayList<XmlData>();
				}
				
				//追加
				arryXmlData.add(this.m_Children.get(ic));
			}
		}

		return arryXmlData;
	}
	//************************************************************************//
	/**
	 *	指定したタグ名で、指定番目のXmlDataを取得
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public XmlData search_xmldata_by_tag_name(String sTagName, int nNumber)
	{
//		ArrayList<XmlData> arryXmlData = null;

		if(this.m_Children == null)
		{
			return null;
		}
		
		int nNowNum = 0;
		int nMax = this.m_Children.size();
		for(int ic=0; ic<nMax; ic++)
		{
			//子を取得
			String sBuf = this.m_Children.get(ic).get_name();
			
			//名前を比較
			if(sTagName.compareTo(sBuf) == 0)
			{
				if(nNowNum == nNumber)
				{
					//追加
					return this.m_Children.get(ic);
				}
				nNowNum++;
			}
		}

		return null;
	}
//	//************************************************************************//
//	/**
//	 *	指定したタグ名、値、そして番号のXmlDataを取得
//	 *
//	 *	@param	
//	 *	@return	
//	 *	@version
//	 */
//	//************************************************************************//
//	public XmlData search_xmldata_by_tag_elem_number(String sTagName, String[] sElements, int nNumber)
//	{
//		ArrayList<XmlData> arryXmlData = null;
//
//		int nNumCount = 0;
//
//		Boolean bSame = false;
//
//		if(this.m_Children == null)
//		{
//			return null;
//		}
//		
//		int nMax = this.m_Children.size();
//		for(int ic=0; ic<nMax; ic++)
//		{
//			//子を取得
//			XmlData xmlBuf = this.m_Children.get(ic);
//			String sBuf = xmlBuf.get_name();
//			
//			///////////////////////////////////
//			//	タグを比較
//			///////////////////////////////////
//			if(sTagName.compareTo(sBuf) == 0)
//			{
//				///////////////////////////////////
//				//	要素を比較
//				///////////////////////////////////
//				int nElemMax = sElements.length;
//				//要素の数が合っているかチェック
//				if(nElemMax == xmlBuf.get_elements().size())
//				{
//					bSame = true;
//					//要素が全て一致しているかチェック
//					for(int jc=0; jc<nElemMax; jc++)
//					{
//						if(sElements[jc].compareTo(xmlBuf.get_elements().get(jc)) != 0)
//						{
//							//違う
//							bSame = false;
//							break;
//						}
//					}
//				}
//
//				//要素が全て一致している場合
//				if(bSame == true)
//				{
//					//番号が合っているかチェック
//					if(nNumber == nNumCount)
//					{
//						//返す
//						return xmlBuf;
//					}
//					
//					//1つ上げておく
//					nNumCount++;
//					
//					//falseにしておく
//					bSame = false;					
//				}
//			}
//		}
//
//		return null;
//	}
	//************************************************************************//
	/**
	 *	指定したタグ名、オプション、値のXmlDataを取得
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public ArrayList<XmlData> search_xmldata_by_tag_option_elem(
		String				sTagName,				//IN	
		ArrayList<String[]>	arryOptions,			//IN	
		Boolean				bOptionMatchAll,		//IN	
		Boolean				bCompareOrContainOpt,	//IN	
		ArrayList<String>	arryElements,			//IN	
		Boolean				bElemMatchAll,			//IN	
		Boolean				bCompareOrContainElem	//IN	
	)
	{
//		//nullの場合は生成する
//		if(arryXmlData == null)
//		{
//			arryXmlData = new ArrayList<XmlData>();
//		}
		
		ArrayList<XmlData> arryXmlData = new ArrayList<XmlData>();


		if(this.m_Children == null)
		{
			return arryXmlData;
		}
		
		int nMax = this.m_Children.size();
		for(int ic=0; ic<nMax; ic++)
		{
			//子を取得
			XmlData xmlBuf = this.m_Children.get(ic);
			String sBuf = xmlBuf.get_name();
			
			///////////////////////////////////
			//	タグ名を比較
			///////////////////////////////////
			if(sTagName.compareTo(sBuf) == 0)
			{
				///////////////////////////////////
				//	オプションを比較
				///////////////////////////////////
				Boolean bSameOption = this.check_same_options(bOptionMatchAll, arryOptions, xmlBuf.get_options());
				
				if(bSameOption == false)
				{
					continue;
				}

				///////////////////////////////////
				//	要素が全て同じか比較
				///////////////////////////////////
				Boolean bSameElem = check_same_elements(bElemMatchAll, arryElements, null, xmlBuf.get_elements());

				//要素が全て一致している場合
				if(bSameElem == true)
				{
					//追加
					arryXmlData.add(xmlBuf);
					
					//falseにしておく
					bSameElem = false;
				}
			}
		}

		return arryXmlData;
	}
	//************************************************************************//
	/**
	 *	指定したタグ名、オプション、値のXmlDataを取得
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public XmlData search_xmldata_by_tag_option_number(
		String sTagName, 
		ArrayList<String[]> arryOptions, 
		Boolean bOptionMatchAll, 
		int nNumber
	)
	{
		if(nNumber < 0)
		{
			return null;
		}		
		ArrayList<XmlData> arryXmlData = new ArrayList<XmlData>();


		if(this.m_Children == null)
		{
			return null;
		}
		
		int nMax = this.m_Children.size();
		int nNumCount = 0;
		for(int ic=0; ic<nMax; ic++)
		{
			//子を取得
			XmlData xmlBuf = this.m_Children.get(ic);
			String sBuf = xmlBuf.get_name();
			
			///////////////////////////////////
			//	タグ名を比較
			///////////////////////////////////
			if(sTagName.compareTo(sBuf) == 0)
			{
				///////////////////////////////////
				//	オプションを比較
				///////////////////////////////////
				Boolean bSameOption = this.check_same_options(bOptionMatchAll, arryOptions, xmlBuf.get_options());
				
				if(bSameOption == true)
				{
					if(nNumCount == nNumber)
					{
						return xmlBuf;
					}
					else
					{
						//1つ上げておく
						nNumCount++;
						
						//falseにしておく
						bSameOption = false;
					}
				}
			}
		}

		return null;
	}
	//************************************************************************//
	/**
	 *	指定したタグ名、オプション、値のXmlDataを取得
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public XmlData search_xmldata_by_tag_option_elem_number(
		String sTagName, 
		ArrayList<String[]> arryOptions, 
		Boolean bOptionMatchAll, 
		ArrayList<String> arryElements, 
		Boolean bElemMatchAll, 
		int nNumber
	)
	{
//		//nullの場合は生成する
//		if(arryXmlData == null)
//		{
//			arryXmlData = new ArrayList<XmlData>();
//		}
		
		if(nNumber < 0)
		{
			return null;
		}

		int nNumCount = 0;

		if(this.m_Children == null)
		{
			return null;
		}
		
		int nMax = this.m_Children.size();
		for(int ic=0; ic<nMax; ic++)
		{
			//子を取得
			XmlData xmlBuf = this.m_Children.get(ic);
			String sBuf = xmlBuf.get_name();
			
			///////////////////////////////////
			//	タグ名を比較
			///////////////////////////////////
			if(sTagName.compareTo(sBuf) == 0)
			{
				///////////////////////////////////
				//	オプションを比較
				///////////////////////////////////
				Boolean bSameOption = this.check_same_options(bOptionMatchAll, arryOptions, xmlBuf.get_options());
				
				if(bSameOption == false)
				{
					continue;
				}

				///////////////////////////////////
				//	要素が全て同じか比較
				///////////////////////////////////
				Boolean bSameElem = check_same_elements(bElemMatchAll, arryElements, false, xmlBuf.get_elements());

				//要素が全て一致している場合
				if(bSameElem == true)
				{
					if(nNumCount == nNumber)
					{
						return xmlBuf;
					}
					else
					{
						//1つ上げておく
						nNumCount++;
						
						//falseにしておく
						bSameElem = false;
					}
				}
			}
		}

		return null;
	}	
	//************************************************************************//
	/**
	 *	指定した文字列を含む要素があるインデックスを取得
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public int search_element(ArrayList<String> arryMatchWords)
	{
		ArrayList<String> arryElements = this.get_elements();
		Boolean bExistOn;
		
		if(arryMatchWords == null ||
		   arryElements == null)
		{
			//無いため、-1を返す
			return -1;
		}
		
		//要素のループ
		for(int ic=0; ic<arryElements.size(); ic++)
		{
			String sElement = arryElements.get(ic);
			bExistOn = true;
			//指定した文字列のループ
			for(int nc=0; nc<arryMatchWords.size(); nc++)
			{
				//無い場合
				if(sElement.indexOf(arryMatchWords.get(nc)) < 0)
				{
					//次にいく
					bExistOn = false;
					break;					
				}
			}
			
			//一致した場合はインデックスを返す
			if(bExistOn == true)
			{
				return ic;
			}
		}
		
		return -1;
	}
	//************************************************************************//
	/**
	 *	オプションを比較
	 *
	 *	@param	bAllSame	(完全一致の場合はtrue、部分一致で良い場合はfalse)
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	///////////////////////////////////
	private Boolean check_same_options(Boolean bAllSame, ArrayList<String[]> arryOptCheck, ArrayList<String[]> arryOptAll)
	{
		///////////////////////////////////
		//	オプションを比較
		///////////////////////////////////
		if(arryOptCheck == null)
		{
			//比較したいオプションが無いのでtrueを返す。
			return true;
		}
		else if(arryOptAll == null)
		{
			//比較したいオプションがあるのに、比較先はオプションがないのでfalseを返す
			return false;
		}
		
		//数を比較
		int nNumAll = arryOptAll.size();
		int nNumCheck = arryOptCheck.size();
		
//		//完全一致の場合
//		if(bAllSame == true)
//		{
//			//完全一致ならば、比較したいオプションと比較先のオプションの数が同じ必要有り。
//			if(nNumAll != nNumCheck)
//			{
//				return false;
//			}
//		}
		//部分一致の場合
//		else if(nNumAll < nNumCheck)
		if(nNumAll < nNumCheck)
		{
			//比較先のほうが少ない場合、一致するはずが無い。falseを返す。
			return false;
		}
		
		//比較したいオプションのループ
		Boolean bSame;
		for(int icCheck=0; icCheck<nNumCheck; icCheck++)
		{
			//初期化
			bSame = false;
			
			String[] sBufCheck = arryOptCheck.get(icCheck);
			
			//比較先のオプションのループ
			for(int icAll=0; icAll<nNumAll; icAll++)
			{
				String[] sBufAll = arryOptAll.get(icAll);
				
				//同じオプション名、値か比較
				bSame = check_same_option_value(sBufCheck, sBufAll);

				//もし同じだった場合
				if(bSame == true)
				{
					//このループを抜ける
					break;
				}
			}
			
			//もしこのオプションが無かった場合
			if(bSame == false)
			{
				return false;
			}
		}
		
		return true;
	}
	//************************************************************************//
	/**
	 *	オプションの名前と値が同じかチェック
	 *
	 *	@param	sOption1	比較するオプション1
	 *	@param	sOption2	比較するオプション2
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	private Boolean check_same_option_value(String[] sOption1, String[] sOption2)
	{
		//数が違う場合
		int nNumBuf = sOption1.length;
		if(nNumBuf != sOption2.length)
		{
			//違うオプション。
			return false;
		}
		
		//オプション名と値をチェック
		for(int nOptMatch=0; nOptMatch<nNumBuf; nOptMatch++)
		{
			//比較
			if(sOption1[nOptMatch].compareTo(sOption2[nOptMatch]) != 0)
			{
				//違う。
				return false;
			}
		}
		
		return true;
	}
	//************************************************************************//
	/**
	 *	要素を比較<br>
	 *
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	private Boolean check_same_elements(
		Boolean				bAllSame, 			//IN	完全一致の場合はtrue、部分一致で良い場合はfalse
		ArrayList<String>	arryElemCheck, 		//IN	比較したい要素のリスト
		Boolean				bCompareOrContain,	//IN	文字列比較をCompareToの完全一致(true)か、Containsの部分一致(false)を指定
		ArrayList<String>	arryElemAll			//OUT	比較先の要素のリスト
	)
	{
		///////////////////////////////////
		//	要素を比較
		///////////////////////////////////
		if(arryElemCheck == null)
		{
			//比較したい要素が無いのでtrueを返す。
			return true;
		}
		else if(arryElemAll == null)
		{
			//比較したい要素があるのに、比較先は要素がないのでfalseを返す
			return false;
		}
		
		//数を比較
		int nNumAll = arryElemAll.size();
		int nNumCheck = arryElemCheck.size();
		
		//完全一致の場合
		if(bAllSame == true)
		{
			//完全一致ならば、比較したい要素と比較先の要素の数が同じ必要有り。
			if(nNumAll != nNumCheck)
			{
				return false;
			}
		}
		//部分一致の場合
		else if(nNumAll < nNumCheck)
		{
			//比較先のほうが少ない場合、一致するはずが無い。falseを返す。
			return false;
		}
		
		//比較したい要素のループ
		Boolean bSame;
		for(int icCheck=0; icCheck<nNumCheck; icCheck++)
		{
			//初期化
			bSame = false;
			
			String sBufCheck = arryElemCheck.get(icCheck);
					
			//比較先の要素のループ
			for(int icAll=0; icAll<nNumAll; icAll++)
			{
				String sBufAll = arryElemAll.get(icAll);
				

				///////////////////
				//	同じ要素か比較
				///////////////////
				//Compare(完全一致か判定)の場合
				if(bCompareOrContain == true)
				{
					if(sBufCheck.compareTo(sBufAll) == 0)
					{
						//このループを抜ける
						bSame = true;
						break;
					}
				}
				//Conatain（部分一致か判定）の場合
				else
				{
					if(sBufCheck.contains(sBufAll) == true)
					{
						//このループを抜ける
						bSame = true;
						break;
					}
				}
			}
			
			//もしこの要素が無かった場合
			if(bSame == false)
			{
				return false;
			}
		}
		
		return true;
	}

	//************************************************************************//
	/**
	 *	オプションの値を取得
	 *
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public String search_option_value
	(
		String		sOptValue	//検索するオプションの値（例：<Object_Base id=XXX>　のタグの場合、"id"を指定してXXXを取得）
	)
	{
		ArrayList<String[]> arryOpt_Value = this.get_options();
		
		if(arryOpt_Value == null)
		{
			return null;
		}
		
		for(int ic=0; ic<arryOpt_Value.size(); ic++)
		{
			String[] sBuf =arryOpt_Value.get(ic); 
			//2つではない場合は飛ばす
			if(sBuf.length != 2)
			{
				continue;
			}
			
			if(sBuf[0].equals(sOptValue) == true)
			{
				return sBuf[1];
			}
		}
		
		return null;
	}
	//************************************************************************//
	/**
	 *	オプションの名前を指定して、オプションを取得。値があればそれも取得
	 *
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public String[] search_option
	(
		String		sOptName	//検索するオプションの値（例：<Object_Base id=XXX>　のタグの場合、"id"を指定してStringの[0]=id、[1]=XXXを取得）
	)
	{
		ArrayList<String[]> arryOpt_Value = this.get_options();
		
		
		//オプションがない場合はnullを返す
		if(arryOpt_Value == null)
		{
			return null;
		}
		
		String[] sBuf;
		for(int ic=0; ic<arryOpt_Value.size(); ic++)
		{
			sBuf = arryOpt_Value.get(ic);
			if(sBuf[0].equals(sOptName) == true)
			{
				//オプション名が一致した場合はそのオプション（値があれば値も）を返す。
				return sBuf;
			}
		}
		
		return null;
	}

	
	//************************************************************************//
	/**
	 *	xmlファイルを分解しながら読み込む
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public static String read_divide_xml_file(
		String[]	statements,		//I		xmlのテキストデータ
		XmlData		dataXmlBase		//IO	RootのXmlData。読み込んだXmlDataを追加する。
	)
	{		
		String sError = null;

		try
		{

			//初期XmlDataを設定
			XmlData[] xdCurrent = {dataXmlBase};

			/////////////////////////////
			//	ファイル内を最初から最後まで読む
			/////////////////////////////
			int nLineCount = 0;

			for(String line : statements)
			{
				nLineCount++;
	
				//進捗状況を出力
				util.print_progress(5000, nLineCount);

				////////////////////////////
				//	分解処理
				////////////////////////////
				sError = sub_read_xml(
					line,			//I		読み込んだ文字列
					xdCurrent		//IO	現在読み込んでいるXmlData
				);
				
				if(sError != null)
				{
//					return sError + "\r\n" + nLineCount + "行目";
					return sError + util.string_get_newline() + nLineCount + "行目";
				}
			}

		}
		catch(Exception e)
		{
			return "Exception:\n" + sError + "\n" + e.getMessage();
		}	
		
		return null;
	}	
	//************************************************************************//
	/**
	 *	xmlファイルを分解しながら読み込む
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public static String read_divide_xml_file(
		String	sXmlFilePath,	//I		ファイルパス
		XmlData	dataXmlBase		//IO	RootのXmlData。読み込んだXmlDataを追加する。
	)
	{		
		try
		{
			String sError = null;
			
			////////////////////////
			//	ファイルを開く
			////////////////////////
			BufferedReader br = util.file_open_read_stream(sXmlFilePath);			
			if(br == null)
			{
				return "Error:ファイルが開けません\r\n" + sXmlFilePath ;
			}

			//初期DataXmlを設定
			XmlData[] xdCurrent = {dataXmlBase};

			/////////////////////////////
			//	ファイル内を最初から最後まで読む
			/////////////////////////////
			int nLineCount = 0;
			String line[] = {""};
			
			while(util.file_read_next_line(br, line))
			{
				nLineCount++;
	
				//進捗状況を出力
				util.print_progress(5000, nLineCount);

				////////////////////////////
				//	分解処理
				////////////////////////////
				sError = sub_read_xml(
					line[0],		//I		読み込んだ文字列
					xdCurrent		//IO	現在読み込んでいるXmlData
				);
				
				if(sError != null)
				{
					return sError + "\r\n" + sXmlFilePath + "\r\n" +  nLineCount + "行目";
				}
			}

		}
		catch(Exception e)
		{
			return "Exception:\n" + e.getMessage();
		}	
		
		return null;
	}
	
	//************************************************************************//
	/**
	 *	タグを読み取る
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	static private String sub_read_xml(
		String		line,		//I		読み込んだ文字列
		XmlData[]	xdCurrent	//IO	現在読み込んでいるXmlData
	)
	{
		String sLineBuf = "";
		ArrayList<String> sData = new ArrayList<String>();

		////////////////////////////
		//	コメント行の場合は次の行に飛ぶ
		////////////////////////////
		int nCommentNum = line.indexOf("//");
		if(nCommentNum != -1)
		{
			sLineBuf = line.substring(0, nCommentNum);
		}
		else
		{
			sLineBuf = line;
		}

		//行を、タグと要素に分割して取得
		sData.clear();
		factorize_by_xml_tag_from_line(sLineBuf, sData);

		//////////////////////////
		//	分割した数だけループ
		//////////////////////////
		int nDataSize = sData.size();
		for(int ic=0; ic<nDataSize; ic++)
		{
			String sBuf = sData.get(ic);

			int sBufSize = sBuf.length();
			//////////////////////////
			//	最初の空白とタブは除く
			//////////////////////////
			int StartIndex = skip_charactor_in_string(cPass, sBuf, 0);
			if(sBufSize <= StartIndex)
			{
				continue;
			}

			//sBuf = util.string_trimLeft(sBuf);

			//////////////////////////
			//	タグの場合
			//////////////////////////
			if(sBuf.charAt(StartIndex) == '<')
			{
				//タグ名とオプションを取得
				String sError = read_tag_name_and_option(sBuf, xdCurrent);

				//エラー
				if(sError != null)
				{
					return sError;
				}
			}
			//////////////////////////
			//	タグではない場合
			//////////////////////////
			else
			{
				//カレントDataXmlに要素を追加
				xdCurrent[0].add_element(sBuf.substring(StartIndex, sBufSize));
			}
		}
		
		return null;
			
	}
	
	//************************************************************************//
	/**
	 *	タグを読み取る
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	private static String read_tag_name_and_option(String sBuf, XmlData[] xdCurrent)
	{
		int nStartIndex = 0;

		/////////////////////////
		//	タグ名とオプションのみにする
		/////////////////////////
		//<ABC option1 = "aa bb cc" id=3>
		//ABC option1 = "aa bb cc" id=3
		String sTagNameOption = sBuf.substring(1, sBuf.length()-1);
		
		//最初の空白とタブは除く
		char[] cPass = {' ', '\t'};
		nStartIndex = skip_charactor_in_string(cPass, sTagNameOption, nStartIndex);

		////////////////////////////////////////////
		//	タグの終わりの場合
		////////////////////////////////////////////
		//終わりタグの場合（判定条件：カレントと同じキーで、</ABC>などのバックスラッシュ）
		if(sTagNameOption.charAt(nStartIndex) == '/')
		{
			//'/'分をとばす
			nStartIndex++;
			
			//'/'の後の空白は除く
			nStartIndex = skip_charactor_in_string(' ', sTagNameOption, nStartIndex);

			//タグ名を取得
			String[] sBufTag = new String[1];
			nStartIndex = search_string_till_chara(nStartIndex, sTagNameOption, ' ', sBufTag);
			String sTagName = sBufTag[0];
			//タグ名が同じ場合
			if(sTagName.compareTo(xdCurrent[0].get_name()) == 0)
			{
				//カレントDataXmlを、親に変更（カレントのDataXmlを終了）
				xdCurrent[0] = xdCurrent[0].get_parent();
			}
			//タグ名が異なる場合
			else
			{
				//そんなことはありえない。
				return "Error:" + "XMLの構文が解決できません。" + "\r\n" + "開始タグ<" + xdCurrent[0].get_name() + ">" + "\r\n" + "終了タグ</" + sTagName + ">";
			}
		}
		////////////////////////////////////////////
		//	新しいタグの場合
		////////////////////////////////////////////
		// ABC option1 = "aa bb cc" id=3
		else
		{
			/////////////////////////
			//	タグ名探索
			/////////////////////////
			nStartIndex = search_xml_tag_name(nStartIndex, sTagNameOption, xdCurrent);

			/////////////////////////
			//	オプション探索
			/////////////////////////
			nStartIndex = skip_charactor_in_string(cPass, sTagNameOption, nStartIndex);
			while(nStartIndex < sTagNameOption.length())
			{
				//次の1文字を取得
				char cNext = sTagNameOption.charAt(nStartIndex);
				
				/////////////////////////
				//	オプション名探索
				/////////////////////////
				if(cNext != '=')
				{
					nStartIndex = search_xml_option(nStartIndex, sTagNameOption, xdCurrent);
				}
				/////////////////////////
				//	オプションの値探索
				/////////////////////////
				else if(cNext == '=')
				{
					nStartIndex = search_xml_option_value(nStartIndex, sTagNameOption, xdCurrent);
				}
			}
		}
		
		return null;
	}
	//************************************************************************//
	/**
	 *	文字列の指定インデックス位置から、指定文字までの文字列を取得
	 *
	 *	例：	文字列str="ABCDEF GHI",cEndが' ', 最初のインデックスは2のとき。
	 *
	 *	ABCDEF GHI
	 *	0123456789
	 *	  CDEF	←これが取得される。また最初のインデックスは6となる。
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	public static int search_string_till_charas(int nStartIndex, String str, char[] cEnds, String[] sOutput)
	{
		int nLast = str.length();
		Boolean bBreak = false;
		for(int ic=nStartIndex+1; ic<str.length(); ic++)
		{
			//1文字ずつチェックしていく
			//A
			//AB
			//ABC
			//ABC 
			//指定の文字が出てきたら、前の文字列を取得。
			for(int jc=0; jc<cEnds.length; jc++)
			{
				if(str.charAt(ic) == cEnds[jc])
				{
					//
					nLast = ic;
					//フラグを立てる
					bBreak = true;
					//ループを抜ける
					break;
				}
			}
			
			if(bBreak == true)
			{
				break;
			}
		}
		
		//文字列を取得
		sOutput[0] = str.substring(nStartIndex, nLast);
		//スタート位置を修正
		return nLast;
	}
	//************************************************************************//
	/**
	 *	文字列の指定インデックス位置から、指定文字までの文字列を取得
	 *
	 *	例：	文字列str="ABCDEF GHI",cEndが' ', 最初のインデックスは2のとき。
	 *
	 *	ABCDEF GHI
	 *	0123456789
	 *	  CDEF	←これが取得される。また最初のインデックスは6となる。
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	public static int search_string_till_chara(int nStartIndex, String str, char cEnd, String[] sOutput)
	{
		int nLast = str.length();
		for(int ic=nStartIndex+1; ic<str.length(); ic++)
		{
			//1文字ずつチェックしていく
			//A
			//AB
			//ABC
			//ABC 
			//指定の文字が出てきたら、前の文字列を取得。
			if(str.charAt(ic) == cEnd)
			{
				//
				nLast = ic;
				//ループを抜ける
				break;
			}
		}
		
		//文字列を取得
		sOutput[0] = str.substring(nStartIndex, nLast);
		//スタート位置を修正
		return nLast;
	}
	//************************************************************************//
	/**
	 *	タグ名探索モード
	 *
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	public static int search_xml_tag_name(int nStartIndex, String str, XmlData[] xdCurrent)
	{
		//最初の空白は除く
		nStartIndex = skip_charactor_in_string(' ', str, nStartIndex);

		//タグ名を取得
		String[] sBuf = new String[1];
		nStartIndex = search_string_till_chara(nStartIndex, str, ' ', sBuf);
		String sTag = sBuf[0];

		//新しいXmlDataを作成し、タグ名設定
		XmlData xdBuf = new XmlData(sTag, xdCurrent[0]);
		//子供として追加
		xdCurrent[0].add_child(xdBuf);
		//カレントXmlDataを変更
		xdCurrent[0] = xdBuf;
		//その後の空白は除く
		nStartIndex = skip_charactor_in_string(' ', str, nStartIndex);

		
		return nStartIndex;
	}
	//************************************************************************//
	/**
	 *	オプションの値探索モード
	 *
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	public static int search_xml_option_value(int nStartIndex, String str, XmlData[] xdCurrent)
	{
		//=の後の空白は除く
		nStartIndex = skip_charactor_in_string(' ', str, nStartIndex+1);
		
		//ダブルクォーテーションの読み込みモード
		Boolean bDoubleQuotOn = false;
		
		//ダブルクォーテーションかチェック
		if(str.charAt(nStartIndex) == '\"')
		{
			bDoubleQuotOn = true;
		}
		
		/////////////////////////
		//	[オプションの値探索モード]
		/////////////////////////
		/////////////////////////////////////
		//	ダブルクォーテーション読み込みモードの場合
		/////////////////////////////////////
		if(bDoubleQuotOn == true)
		{			
			//オプションの値を取得
			String[] sBuf = new String[1];
			nStartIndex = search_string_till_chara(nStartIndex, str, '\"', sBuf);
			String sOptionValue = sBuf[0] + "\"";
			nStartIndex++;
	
			//オプションの値を保存
			xdCurrent[0].add_option_value(sOptionValue);
		}
		/////////////////////////////////////
		//	ダブルクォーテーションを使っていない場合
		/////////////////////////////////////
		else
		{
			//空白は除く
			nStartIndex = skip_charactor_in_string(' ', str, nStartIndex);
			
			//オプションの値を取得
			String[] sBuf = new String[1];
			nStartIndex = search_string_till_chara(nStartIndex, str, ' ', sBuf);
			String sOptionValue = sBuf[0];
	
			//オプションの値を保存
			xdCurrent[0].add_option_value(sOptionValue);	
		}
	
		//空白は除く
		nStartIndex = skip_charactor_in_string(' ', str, nStartIndex);

		return nStartIndex;
			
	}
	//************************************************************************//
	/**
	 *	オプション探索モード
	 *
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	public static int search_xml_option(int nStartIndex, String str, XmlData[] xdCurrent)
	{
		//空白は除く
		nStartIndex = skip_charactor_in_string(' ', str, nStartIndex);
		
		//オプション名を取得
		String[] sBuf = new String[1];
		char[] cEnds = {' ', '='};
		nStartIndex = search_string_till_charas(nStartIndex, str, cEnds, sBuf);
		String sOption = sBuf[0];

		//オプションを保存
		xdCurrent[0].add_option(sOption);
		
		//空白は除く
		nStartIndex = skip_charactor_in_string(' ', str, nStartIndex);
		
//		for(int ic=nStartIndex+1; ic<str.length(); ic++)
//		{
//			//次を1文字ずつ取得していく
//			//o
//			//op
//			//opt
//			//opti
//			//optio
//			//option
//			//option1
//			//option1 
//			//空白が出てきたら、オプション名として取得。
//			if(str.charAt(ic) == ' ')
//			{
//				//オプション名を取得
//				String sOption = str.substring(nStartIndex, ic);
//				//スタート位置を修正
//				nStartIndex = ic+1;
//				//オプションを保存
//				xdCurrent.add_option(sOption);
//				//その後の空白は除く
//				nStartIndex = skip_charactor_in_string(' ', str, nStartIndex);
//				//ループを抜ける
//				break;
//			}
//		}
		
		return	nStartIndex;
	}
	//************************************************************************//
	/**
	 *	空白など、特定文字を連続して飛ばし、それ以外の文字が出たとき、処理を終わる。
	 *	[     あいうえお]
	 *	 012345
	 *
	 *	特定文字を空白とした場合、nStart[0]には5が入る。
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	public static int skip_charactor_in_string(char chara, String str, int nStartIndex)
	{
		//最初の空白は除く
		for(int ic=nStartIndex; ic<str.length(); ic++)
		{
			//空白の場合
			if(str.charAt(ic) == chara)
			{
				//読み込みのスタート位置をずらしていく
				nStartIndex = ic+1;
			}
			//空白ではなかった場合
			else
			{
				//ループを抜ける
				break;
			}
		}
		
		return nStartIndex;
	}
	//************************************************************************//
	/**
	 *	skip_charactor_in_stringの複数文字版
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	public static int skip_charactor_in_string(char[] charasSkip, String str, int nStartIndex)
	{
		int nCharas = charasSkip.length;
		
		//最初の空白は除く
		Boolean bLoopEnd = false;
		for(int ic=nStartIndex; ic<str.length(); ic++)
		{
			bLoopEnd = true;
			for(int jc=0; jc<nCharas; jc++)
			{
				//スキップ文字の場合
				if(str.charAt(ic) == charasSkip[jc])
				{
					//読み込みのスタート位置をずらしていく
					nStartIndex = ic+1;
					bLoopEnd = false;
					break;
				}
			}
//			//スキップ文字ではなかった場合
//			else
//			{
//				//ループを抜ける
//				bLoopEnd = true;
//				break;
//			}
			
			if(bLoopEnd == true)
			{
				break;
			}
		}
		
		return nStartIndex;
	}
	//************************************************************************//
	/**
	 *	タグを探して分割する
	 *	<あいうえお>　←このようなタグで分割
	 *
	 *	例:	ABC<Group>テスト</Group>012
	 *	これを以下のように分解する
	 *	ABC
	 *	<Group>
	 *	テスト
	 *	</Group>
	 *	012
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	public static void factorize_by_xml_tag_from_line(String line, ArrayList<String> sData)
	{
		String sFirstKakko = "<";
		String sEndKakko   = ">";
		
		ArrayList<String> sDataBuf = new ArrayList<String>();
		
		util.string_factorize_by_two_string_from_line(line, sFirstKakko, sEndKakko, sDataBuf);
		
		char[] cPass = {' ', '\t'};
//		String sBuf = "";
//		int nEndIndex = 0;
//		for(int ic=0; ic<sDataBuf.size(); ic++)
//		{
//			sBuf = sDataBuf.get(ic);
//			
//			//最初の空白とタブは除く
//			nEndIndex = skip_charactor_in_string(cPass, sBuf, nEndIndex);
//			
//			//もし文字列が空白やタブだけではないときは、格納する。
//			if(nEndIndex != sBuf.length())
//			{
//				sData.add(sBuf);
//			}
//		}
		
		for(int ic=0; ic<sDataBuf.size(); ic++)
		{
			//空白とタブは除く
			String sBuf = util.string_trim(sDataBuf.get(ic), cPass);
			
			//もし文字列が空白やタブだけではないときは、格納する。
			if(sBuf.length() != 0)
			{
				sData.add(sBuf);
			}
		}
		
		return;
	}
	//************************************************************************//
	/**
	 *	ファイルを分割する
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public static String divide_files(String sXmlFilePath, String sBasePath)
	{
		System.out.println("Read xml file ...");

		////////////////////////////////////////////////
		//	xmlファイル読み込み
		////////////////////////////////////////////////
		XmlData dataXmlBase = new XmlData("Base");
		XmlData.read_divide_xml_file(sXmlFilePath, dataXmlBase);

		System.out.println("Divide xml file ...");

		////////////////////////////////////////////////
		//	xmlを分割して書き出し
		////////////////////////////////////////////////
		ArrayList<XmlData> arryXml = dataXmlBase.get_children();
		for(int ic=0; ic<arryXml.size(); ic++)
		{
			XmlData xmlBuf = arryXml.get(ic);
			
			//名前が"Name"のxmlを取得
			ArrayList<XmlData> arryNameXml = xmlBuf.search_xmldata_by_tag_name("Name");
			XmlData xdName = arryNameXml.get(0);

			if(xdName.get_elements() == null)
			{
				continue;
			}
			else
			{
				//Nameに記述された名前を取得
				String sName = xdName.get_elements().get(0);
				
				//ファイルパスを作成
				String sFilePath = sBasePath + "\\" + sName + ".log";
				
				//個別に書き出し
				xmlBuf.write_xml_file(sFilePath, false, "\t");
			}
		}

		System.out.println("Done");
		
		return null;
	}
	
	
}

