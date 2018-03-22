package _30_Command;

import _00_GUI_Main_JavaFX.Main;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.TreeItem_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import javafx.scene.input.MouseEvent;
import _40_Value.Value_DoubleComplex;
import _23_Object_Iroiro.Object_20_放物線;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import utility.util;
import xml.XmlData;

//import module.Module_MathmaticsWorld;

public class Command_Create_放物線
//	extends Command_Imp
	implements
		Entity_Imp,
		Command_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{
	private String m_name = "放物線の作成";
	private Object_20_放物線 objParablic;
	private String m_command_string = static_default_command_string;
	private static String static_default_command_string = ""
		+ "<Parabolic>"						+ util.string_get_newline()
		+ "	<Name>2次関数（複素数）</Name>"	+ util.string_get_newline()
		//係数
		+ "	<CoeffA>0.01+0.01i</CoeffA>"	+ util.string_get_newline()//2次関数、X^2の係数A
		+ "	<CoeffB>0.0</CoeffB>"			+ util.string_get_newline()//2次関数、X^1の係数B
		+ "	<CoeffC>1.0</CoeffC>"			+ util.string_get_newline()//2次関数、X^0の係数C
		//xの変域
		+ "	<range_x_min>-50.0-50.0i</range_x_min>"			+ util.string_get_newline()
		+ "	<range_x_max>50.0+50i</range_x_max>"			+ util.string_get_newline()
		//ピッチ
		+ "	<pitch>5.0</pitch>"			+ util.string_get_newline()
		+ "</Parabolic>";

	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Command_Create_放物線(	)
	{
	}

	//************************************************************************//
	/**
	*	setter
	*/
	//************************************************************************//
	public void set_command_default_string(String str)	{this.static_default_command_string = str;}
	
	/***************************************************************************
	 * 
	 * 複素の2次関数を作成
	 * 
	 */
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
			this.objParablic = new Object_20_放物線(
				name,		//"2次関数", 
				coeff_a,	//new DoubleT(0.01), 
				coeff_b,	//new DoubleT(0.0), 
				coeff_c		//new DoubleT(1.0)
			);

			//xの変域を設定
			objParablic.set_range_x_re_min(range_x_min.get_re());	//XのRe最小範囲
			objParablic.set_range_x_re_max(range_x_max.get_re());	//XのRe最大範囲
			objParablic.set_range_x_im_min(range_x_min.get_im());	//XのIm最小範囲
			objParablic.set_range_x_im_max(range_x_max.get_im());	//XのIm最大範囲

			//ピッチを設定
			objParablic.set_pitch(pitch.get_re());	//面を生成するときのピッチ
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
	@Override public Command_Imp	factory() {return new Command_Create_放物線();}
	@Override
	public String execute(MouseEvent mouse_event)
	{
		try{
			System.out.println(util.debug_ask_class_method_name());

			//XmlDataに変換
			XmlData xd = Command_Imp.ask_xml(this.m_command_string);

			//連続作成
			Command_Imp.read_command_tag(xd, "Parabolic").stream()
				.forEach(buf -> this.create_parabolic(buf));
			
			//格納
			Main.get_model_base().add_command(this);

			return "2次関数の平面（複素数）が作成されました。";
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
	@Override	public Node ask_JavafxNode(){return this.objParablic.ask_JavafxNode();}

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