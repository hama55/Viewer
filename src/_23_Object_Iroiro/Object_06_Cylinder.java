/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _23_Object_Iroiro;

import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import _41_Mathatic.Mathmatic;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

/**
 *
 * @author 真也
 */
public class Object_06_Cylinder
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
{

	final private double[] def_d_init_axis = {0.0, 1.0, 0.0};		//初期シリンダーの方向
	private String m_name = "";
	private boolean m_update_on = true;
	private Node m_javafxnode = null;
	
	private Object_03_Point m_point_start = null;	//始点座標値
	private Object_03_Point m_point_end = null;		//終点座標値
	private double			m_d_radius = 1;			//半径
	private int				m_n_bunkatsu = 10;		//分割数(3以上)	

	public Object_06_Cylinder(){}
	public Object_06_Cylinder(
		Object_03_Point pnt1,		//始点座標値
		Object_03_Point pnt2,		//終点座標値
		double			d_radius,	//半径
		int				n_bunkatsu	//分割数(3以上)
	)
	{
		m_point_start = pnt1;
		m_point_end = pnt2;	
		m_d_radius = d_radius;
		m_n_bunkatsu = n_bunkatsu;
	}
	
	private Node create_JavafxNode()
	{
		//シリンダーの長さ
        double height = Object_03_Point.calc_length_between_two_point(
							this.m_point_start,
							this.m_point_end);

		//JavaFXのCylinder作成
		Cylinder cylinder = new	Cylinder(
			this.m_d_radius,	//半径
			height,
			this.m_n_bunkatsu		//分割数
		);
		
		//初期設定。並進でシリンダーのstart点を(0.0, 0.0, 0.0)に移動する。
		double[] buf_init_position = new double[3];
		Translate translate_init = new Translate(buf_init_position[0], buf_init_position[1], buf_init_position[2]);
		for(int ic=0; ic<3; ic++){
			buf_init_position[ic] = def_d_init_axis[ic]*height*0.5;
		}
		
 		//並進
		double[] buf_position = this.m_point_start.ask_position();
		for(int ic=0; ic<3; ic++){
			buf_position[ic] += buf_init_position[ic];
		}
		Translate translate = new Translate(buf_position[0], buf_position[1], buf_position[2]);

		/////回転
		//回転軸と回転量の算出

		//シリンダーを向けたい方向
		double[] d_next_axis = new double[3];
		Mathmatic.calc_vector_between_point_point_unit(this.m_point_start.ask_position(), this.m_point_end.ask_position(), d_next_axis);
		//回転軸と回転量。
		double[] rot_axis_angle = new double[4];
		Mathmatic.calc_rotate_axis_for_matching_vector(def_d_init_axis, d_next_axis, rot_axis_angle);
		//Rotateオブジェクト作成
        Rotate rotation = new Rotate();
		rotation.setAngle(rot_axis_angle[3]);
        Point3D point3d = new Point3D(rot_axis_angle[0], rot_axis_angle[1], rot_axis_angle[2]);
		rotation.setAxis(point3d);
//		rotation.setPivotX(-buf_position[0]);
//		rotation.setPivotY(-buf_position[1]);
//		rotation.setPivotZ(-buf_position[2]);
		rotation.setPivotX(-buf_init_position[0]);
		rotation.setPivotY(-buf_init_position[1]);
		rotation.setPivotZ(-buf_init_position[2]);

		//並進と回転を適用
//		cylinder.getTransforms().add(translate_init);
		cylinder.getTransforms().add(translate);
		cylinder.getTransforms().add(rotation);
//		translate.
		
		return cylinder;
	}

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
	 * [Display_3D_Objectクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override
	public Node ask_JavafxNode(){
		if(this.m_update_on == true || this.m_javafxnode == null){
			this.m_update_on = false;
			this.m_javafxnode = create_JavafxNode();
		}
		return this.m_javafxnode;
	}
	/***************************************************************************
	 * 
	 * [Entityクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	@Override
	public void set_name(String value) {this.m_name = value;}
	@Override
	public String get_name() {return this.m_name;}
}
