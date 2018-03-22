package _40_Value;

import _20_Object_Template.TreeItem_Imp;
import mathmatic.Mathmatic;
import _20_Object_Template.Entity_Imp;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import utility.util;
import mathmatic.Imp_Value1;

public class Value_DoubleComplex
	implements
		Entity_Imp,
		Value_Imp,
		TreeItem_Imp,
		Imp_Value1
{
	double m_re = 0.0;	//実数
	double m_im = 0.0;	//虚数
	private static final double m_shusoku_value = 1e-7;

	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Value_DoubleComplex()
	{
	}
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Value_DoubleComplex(double re, double im)
	{
		this.m_re = re;
		this.m_im = im;
	}
	//************************************************************************//
	/**
	*	コンストラクタ
	*/
	//************************************************************************//
	public Value_DoubleComplex(double[] value)
	{
		this.m_re = value[0];
		this.m_im = value[1];
	}
	//************************************************************************//
	/**
	*	setter
	*/
	//************************************************************************//
	public void set_re(double re){this.m_re = re;}
	public void set_im(double im){this.m_im = im;}
	public void set(double re, double im)
	{
		m_re = re;
		m_im = im;
	}
	public void set(Value_DoubleComplex dcmp)
	{
		m_re = dcmp.get_re();
		m_im = dcmp.get_im();
	}
	//************************************************************************//
	/**
	*	getter
	*/
	//************************************************************************//
	public double get_re(){return this.m_re;}
	public double get_im(){return this.m_im;}

	//************************************************************************//
	/**
	*	plus　足し算　和
	*/
	//************************************************************************//
	@Override
//	public Value_DoubleComplex plus(Value_DoubleComplex dcmp)
//	{
//		return new Value_DoubleComplex(
//				this.get_re() + dcmp.get_re(),
//				this.get_im() + dcmp.get_im()
//				);
//	}
	public Imp_Value1 plus(Imp_Value1 value)
	{
		Value_DoubleComplex dcmp = (Value_DoubleComplex) value;
		return new Value_DoubleComplex(
				this.get_re() + dcmp.get_re(),
				this.get_im() + dcmp.get_im()
				);
	}
	//************************************************************************//
	/**
	*	minus　引き算　差
	*/
	//************************************************************************//
	@Override
//	public Value_DoubleComplex minus(Value_DoubleComplex dcmp)
//	{
//		return new Value_DoubleComplex(
//				this.get_re() - dcmp.get_re(), 
//				this.get_im() - dcmp.get_im()
//				);
//	}
	public Imp_Value1 minus(Imp_Value1 value)
	{
		Value_DoubleComplex dcmp = (Value_DoubleComplex) value;
		return new Value_DoubleComplex(
				this.get_re() - dcmp.get_re(), 
				this.get_im() - dcmp.get_im()
				);
	}
	//************************************************************************//
	/**
	*	times　掛け算　積
	*/
	//************************************************************************//
	@Override
//	public Value_DoubleComplex times(Value_DoubleComplex dcmp)
//	{
//		double dReal = this.get_re() * dcmp.get_re() - this.get_im() * dcmp.get_im();
//		double dImag = this.get_re() * dcmp.get_im() + this.get_im() * dcmp.get_re();
//		return new Value_DoubleComplex(dReal, dImag);
//	}
	public Imp_Value1 times(Imp_Value1 value)
	{
		Value_DoubleComplex dcmp = (Value_DoubleComplex) value;
		double dReal = this.get_re() * dcmp.get_re() - this.get_im() * dcmp.get_im();
		double dImag = this.get_re() * dcmp.get_im() + this.get_im() * dcmp.get_re();
		return new Value_DoubleComplex(dReal, dImag);
	}

	//************************************************************************//
	/**
	*	quot(quotient)　割り算　商　除算
	*/
	//************************************************************************//
	@Override
//	public Value_DoubleComplex quot(Value_DoubleComplex dcmp)
//	{
//		double dValue = Mathmatic.calc_power_integer(dcmp.get_re(), 2) + Mathmatic.calc_power_integer(dcmp.get_im(), 2);
//		dValue = 1.0 / dValue;
//
//		double dReal =        (this.get_re() * dcmp.get_re() + this.get_im() * dcmp.get_im()) * dValue;
//		double dImag = -1.0 * (this.get_im() * dcmp.get_re() - this.get_re() * dcmp.get_im()) * dValue;
//		return new Value_DoubleComplex(dReal, dImag);
//	}
	public Imp_Value1 quot(Imp_Value1 value)
	{
		Value_DoubleComplex dcmp = (Value_DoubleComplex) value;
		double dValue = Mathmatic.calc_power_integer(dcmp.get_re(), 2) + Mathmatic.calc_power_integer(dcmp.get_im(), 2);
		dValue = 1.0 / dValue;

		double dReal =        (this.get_re() * dcmp.get_re() + this.get_im() * dcmp.get_im()) * dValue;
		double dImag = -1.0 * (this.get_im() * dcmp.get_re() - this.get_re() * dcmp.get_im()) * dValue;
		return new Value_DoubleComplex(dReal, dImag);
	}
	//************************************************************************//
	/**
	*	収束判定
	*/
	//************************************************************************//
	@Override
	public boolean is_zero()
	{
		if(ask_length() <= m_shusoku_value){
			return true;
		}else{
			return false;
		}
	}
	//************************************************************************//
	/**
	*	大きさ
	*/
	//************************************************************************//
	@Override
	public double norm() {return ask_length();}

	//************************************************************************//
	/**
	*	収束判定
	*/
	//************************************************************************//
	@Override
	public String print_value()
	{
		return String.format("%f,  %f", this.get_re(), this.get_im());
	}

	//************************************************************************//
	/**
	*	長さ（絶対値）を取得
	*/
	//************************************************************************//
	public double ask_length()
	{
		double dValue = Mathmatic.calc_power_integer(this.get_re(), 2) + Mathmatic.calc_power_integer(this.get_im(), 2);
		return Math.sqrt(dValue);
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
//		fileIO.add(id, new Value_DoubleComplex());
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
//	}

//	@Override
//	public Node create_JavafxNode() {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
//
//	@Override
//	public void observer_receive_update(Entity_Base obj, UpdateType update_type) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
	@Override
	public TreeItem ask_JavafxTreeItem(boolean is_open)
	{
		try
		{
			//名前からツリーノードを作成
			TreeItem tree_item = new TreeItem<>(this);
			
			//なし
			
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
	@Override	public String	get_name()				{return "DoubleComplex_名前なし";}
}
