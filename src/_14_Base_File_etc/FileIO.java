package _14_Base_File_etc;

import _20_Object_Template.Entity_Directory;
import xml.XmlData;

public interface FileIO 
{
	//************************************************************************//
	/**
	 *	読み込みファイルデータを実体化する
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public void factory_and_add(
		String			id,
		FileInputOutput	fileIO
	);
	//************************************************************************//
	/**
	 *	実体化した読み込みデータの中身を設定する
	 *
	 *	@param	
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public void read_data(
		XmlData			xmlData,
		FileInputOutput	dataFileIO,
		Entity_Directory		topGroup
	);
	
	//************************************************************************//
	/**
	 *	ファイルに書き出し
	 *
	 *	@param	
	 *	@return	id
	 *	@version
	 */
	//************************************************************************//
	public String write_data(
		XmlData				xmlData,	//OUT	親のXmlData。このXmlDataの子として登録する。
		FileInputOutput		dataFileIO	//IN	書き込み時の全てのXmlDataを持つ。
	);
}
