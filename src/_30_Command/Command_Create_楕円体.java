/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _30_Command;

import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _23_Object_Iroiro.Object_22_楕円体;
import _40_Value.Value_DoubleComplex;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import utility.util;
import xml.XmlData;

/**
 *
 * @author 真也
 */
public class Command_Create_楕円体
	implements
		Entity_Imp,
		Command_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
	private String m_name = "楕円体の作成";
	private Object_22_楕円体 obj楕円球;
	private String m_command_string = static_default_command_string;

	private static String static_default_command_string = ""
		+ "<楕円球>"																	+ util.string_get_newline()
		+ "<式>"																		+ util.string_get_newline()
		+ "Ax^2 + Bxy + Cxz + Dyx + Ey^2 + Fyz + Gzx + Hzy + Iz^2 = Radius^2"		+ util.string_get_newline()
		+ "</式>"																	+ util.string_get_newline()
		+ "	<Name>楕円体</Name>"			+ util.string_get_newline()
		+ "	<CoeffA>1</CoeffA>"			+ util.string_get_newline()
		+ "	<CoeffB>0</CoeffB>"			+ util.string_get_newline()
		+ "	<CoeffC>0</CoeffC>"			+ util.string_get_newline()
		+ "	<CoeffD>0</CoeffD>"			+ util.string_get_newline()
		+ "	<CoeffE>1</CoeffE>"			+ util.string_get_newline()
		+ "	<CoeffF>0</CoeffF>"			+ util.string_get_newline()
		+ "	<CoeffG>0</CoeffG>"			+ util.string_get_newline()
		+ "	<CoeffH>0</CoeffH>"			+ util.string_get_newline()
		+ "	<CoeffI>1</CoeffI>"			+ util.string_get_newline()
		+ "	<CoeffRadius2>2500</CoeffRadius2>"			+ util.string_get_newline()
		//xの変域
		+ "	<range_x_min>-50.0-50.0i</range_x_min>"			+ util.string_get_newline()
		+ "	<range_x_max>50.0+50i</range_x_max>"			+ util.string_get_newline()
		//ピッチ
		+ "	<pitch>5.0</pitch>"			+ util.string_get_newline()
		+ "</楕円球>";

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Command_Create_楕円体(	)
	{
	}

	//************************************************************************//
	/**
	*	setter
	*/
	//************************************************************************//
	public void set_command_default_string(String str)	{this.static_default_command_string = str;}
		
	//************************************************************************//
	/**
	 * 複素の2次関数を作成
	 * 
	 */
	//************************************************************************//
	private void create_parabolic(XmlData xd)
	{
		try{
			System.out.println(util.debug_ask_class_method_name());
			
			///////////////////////////////////
			//	値を取得
			///////////////////////////////////
			ArrayList<String[]> arry_string;
			double double_buf[][];

			//名前を取得
			arry_string = Command_Imp.read_command_string(xd, "Name");
			String name = arry_string.get(0)[0];

			//係数Aを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "CoeffA").get(0);
			Value_DoubleComplex coeff_a = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//係数Bを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "CoeffB").get(0);
			Value_DoubleComplex coeff_b = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//係数Cを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "CoeffC").get(0);
			Value_DoubleComplex coeff_c = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//係数Dを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "CoeffD").get(0);
			Value_DoubleComplex coeff_d = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//係数Eを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "CoeffE").get(0);
			Value_DoubleComplex coeff_e = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//係数Fを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "CoeffF").get(0);
			Value_DoubleComplex coeff_f = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//係数Gを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "CoeffG").get(0);
			Value_DoubleComplex coeff_g = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//係数Hを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "CoeffH").get(0);
			Value_DoubleComplex coeff_h = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//係数Iを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "CoeffI").get(0);
			Value_DoubleComplex coeff_i = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//係数Iを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "CoeffRadius2").get(0);
			Value_DoubleComplex coeff_radius2 = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//Xの変域、最小値を取得
			double_buf = Command_Imp.read_command_double_complex(xd, "range_x_min").get(0);
			Value_DoubleComplex range_x_min = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//Xの変域、最大値を取得
			double_buf = Command_Imp.read_command_double_complex(xd, "range_x_max").get(0);
			Value_DoubleComplex range_x_max = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//面を生成するときのピッチを取得
			double_buf = Command_Imp.read_command_double_complex(xd, "pitch").get(0);
			Value_DoubleComplex pitch = new Value_DoubleComplex(double_buf[0][0], double_buf[0][1]);

			//二次関数の値を算出し、グループにObjectとして格納
			this.obj楕円球 = new Object_22_楕円体(
				name,		//"2次関数", 
				coeff_a,	//new DoubleT(0.01), 
				coeff_b,	//new DoubleT(0.0), 
				coeff_c,	//new DoubleT(1.0)
				coeff_d,	//new DoubleT(0.01), 
				coeff_e,	//new DoubleT(0.01), 
				coeff_f,	//new DoubleT(0.01), 
				coeff_g,	//new DoubleT(0.01), 
				coeff_h,	//new DoubleT(0.01), 
				coeff_i,		//new DoubleT(0.01), 
				coeff_radius2
			);

			//xの変域を設定
			obj楕円球.set_range_x_re_min(range_x_min.get_re());	//XのRe最小範囲
			obj楕円球.set_range_x_re_max(range_x_max.get_re());	//XのRe最大範囲
			obj楕円球.set_range_x_im_min(range_x_min.get_im());	//XのIm最小範囲
			obj楕円球.set_range_x_im_max(range_x_max.get_im());	//XのIm最大範囲

			//ピッチを設定
			obj楕円球.set_pitch(pitch.get_re());	//面を生成するときのピッチ
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
	}
	
	//************************************************************************//
	/**
	*	[Commandクラスのインプリメントデータ・関数群]
	*
	*	@param
	*	@return
	*	@version
	***************************************************************************/
	@Override public void			set_command_string(String str)	{this.m_command_string = str;}
	@Override public String			get_command_string() {return this.m_command_string;}
	@Override public Command_Imp	factory() {return new Command_Create_楕円体();}
	@Override
	public String execute(MouseEvent mouse_event)
	{
		try{
			System.out.println(util.debug_ask_class_method_name());

			//XmlDataに変換
			XmlData xd = Command_Imp.ask_xml(this.m_command_string);

			//連続作成
			Command_Imp.read_command_tag(xd, "楕円球").stream()
				.forEach(buf -> this.create_parabolic(buf));
			
			//格納
			Main.get_model_base().add_command(this);

			return "楕円球は未実装だ。";
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
		
		return null;
	}
	/***************************************************************************
	 * 
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public Node ask_JavafxNode(){return this.obj楕円球.ask_JavafxNode();}

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
	 * [Entityクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override	public void set_name(String value) {this.m_name = value;}
	@Override	public String get_name() {return this.m_name;}
}