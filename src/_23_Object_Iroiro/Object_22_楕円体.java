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
import _30_Command.Command_Create_ReImAxisDefine;
import _40_Value.Value_DoubleComplex;
import _41_Mathatic.Imp_Mathmatic_Newton_Raphson;
import _41_Mathatic.Imp_Value1;
import _41_Mathatic.Mathmatic;
import _41_Mathatic.Mathmatic_By_Value1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
//import mathmatic.Imp_Mathmatic_Newton_Raphson;
//import mathmatic.Imp_Value1;
import _42_Utility.util;
//import mathmatic.Mathmatic;
//import mathmatic.Mathmatic_By_Value1;

/**
 *
 * @author 真也
 */
public class Object_22_楕円体
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp,
		Imp_Mathmatic_Newton_Raphson
		//implements FileIO
{
	private String m_name;
	private Node m_javafxnode = null;
	private boolean m_update_on = true;
	
	private Value_DoubleComplex m_CoeffA = null;
	private Value_DoubleComplex m_CoeffB = null;
	private Value_DoubleComplex m_CoeffC = null;
	private Value_DoubleComplex m_CoeffD = null;
	private Value_DoubleComplex m_CoeffE = null;
	private Value_DoubleComplex m_CoeffF = null;
	private Value_DoubleComplex m_CoeffG = null;
	private Value_DoubleComplex m_CoeffH = null;
	private Value_DoubleComplex m_CoeffI = null;
	private Value_DoubleComplex m_CoeffRadius2 = null;

	//xの変域
	private Value_DoubleComplex m_range_x_min = new Value_DoubleComplex(-100.0, -100.0);
	private Value_DoubleComplex m_range_x_max = new Value_DoubleComplex(100.0, 100.0);
	private double m_pitch = 5;

	private static final Value_DoubleComplex value_dc2 = new Value_DoubleComplex(2.0, 0.0);
	private static final double m_shusoku_value = 1e-7;

	//************************************************************************//
	/**
	 * 
	 * @param 
	 * @return 
	 */
	//************************************************************************//
	@Override
	public Imp_Value1 Newton_Raphson_1_式の値(Imp_Value1[] values) {
		//楕円体の式に値を代入
		//f = Ax^2 + Bxy + Cxz + Dyx + Ey^2 + Fyz + Gzx + Hzy + Iz^2 - Radius^2
		Value_DoubleComplex value_x = (Value_DoubleComplex) values[0];
		Value_DoubleComplex value_y = (Value_DoubleComplex) values[1];
		Value_DoubleComplex value_z = (Value_DoubleComplex) values[2];
		Value_DoubleComplex buf_kou1 = (Value_DoubleComplex) m_CoeffA.times(value_x).times(value_x);
		Value_DoubleComplex buf_kou2 = (Value_DoubleComplex) m_CoeffB.times(value_x).times(value_y);
		Value_DoubleComplex buf_kou3 = (Value_DoubleComplex) m_CoeffC.times(value_x).times(value_z);
		Value_DoubleComplex buf_kou4 = (Value_DoubleComplex) m_CoeffD.times(value_y).times(value_x);
		Value_DoubleComplex buf_kou5 = (Value_DoubleComplex) m_CoeffE.times(value_y).times(value_y);
		Value_DoubleComplex buf_kou6 = (Value_DoubleComplex) m_CoeffF.times(value_y).times(value_z);
		Value_DoubleComplex buf_kou7 = (Value_DoubleComplex) m_CoeffG.times(value_z).times(value_x);
		Value_DoubleComplex buf_kou8 = (Value_DoubleComplex) m_CoeffH.times(value_z).times(value_y);
		Value_DoubleComplex buf_kou9 = (Value_DoubleComplex) m_CoeffI.times(value_z).times(value_z);
		Value_DoubleComplex buf_kou10 = (Value_DoubleComplex) m_CoeffRadius2;

		return	buf_kou1.plus(buf_kou2).plus(buf_kou3)
                            .plus(buf_kou4).plus(buf_kou5).plus(buf_kou6)
                            .plus(buf_kou7).plus(buf_kou8).plus(buf_kou9)
                            .minus(buf_kou10);
	}

	@Override
	public Value_DoubleComplex Newton_Raphson_2_偏微分(int index, Imp_Value1[] values) {
	
		Value_DoubleComplex value_x = (Value_DoubleComplex) values[0];
		Value_DoubleComplex value_y = (Value_DoubleComplex) values[1];
		Value_DoubleComplex value_z = (Value_DoubleComplex) values[2];
		
		if(index == 0){
			return (Value_DoubleComplex) calc_X_偏微分(value_x, value_y, value_z);
		}
		else if(index == 1){
			return (Value_DoubleComplex) calc_Y_偏微分(value_x, value_y, value_z);
		}
		else{
			return (Value_DoubleComplex) calc_Z_偏微分(value_x, value_y, value_z);
		}
	}
	
//    /***************************************************************************
//	 * 
//     *	クラス内クラス
//     *
//     *	@param
//     *	@return
//     *	@version
//    *///************************************************************************
//	private class Test6角形
//	{
//		private boolean m_is_active = true;
//		private Object_03_Point m_obj_point = null;
//		Test6角形 m_test_parent = null;
//		Test6角形[] m_arry_testpoint = new Test6角形[6];
//		
//		/***************************************************************************
//		 * 
//		 *	コンストラクタ
//		 *
//		*///************************************************************************
//		public Test6角形(){}
//		
//		/***************************************************************************
//		 * 
//		 *	コンストラクタ
//		 *
//		*///************************************************************************
//		public Test6角形(
////			Test6角形 parent,
//			double[][] point)
//		{
////			this.m_test_parent = parent;
//			m_obj_point = new Object_03_Point(
//				"",
//				new Value_DoubleComplex(point[0]),
//				new Value_DoubleComplex(point[1]),
//				new Value_DoubleComplex(point[2])
//			);
//		}
//		/***************************************************************************
//		 * 
//		 *	コンストラクタ
//		 *
//		*///************************************************************************
//		public Test6角形(
//			Test6角形 parent,
//			Object_03_Point point)
//		{
//			this.m_test_parent = parent;
//			m_obj_point = point;
//		}
//		
//		/***************************************************************************
//		 * 
//		 *	setter
//		 *
//		*///************************************************************************
//		public void set_test_point(int n_index, Test6角形 testpoint){	m_arry_testpoint[n_index-1] = testpoint;}
//		public void set_is_active(boolean b){this.m_is_active = b;}
//		public void set_test_parent(Test6角形 t){this.m_test_parent = t;}
//		public void set_point(Object_03_Point buf){this.m_obj_point = buf;}
//		
//		/***************************************************************************
//		 * 
//		 *	getter
//		 *
//		*///************************************************************************
//		public Test6角形 get_test_point(int index){return m_arry_testpoint[index-1];}
//		public boolean get_is_active(){return this.m_is_active;}
//		public Object_03_Point get_point(){return this.m_obj_point;}
//		public Object_03_Point get_point_index(int index){return this.m_arry_testpoint[index-1].get_point();}
//		public Test6角形 get_parent()	{return this.m_test_parent;}
//		
//		/***************************************************************************
//		 * 
//		 *	ask
//		 *
//		*///************************************************************************
//		public int ask_number_me(Test6角形 buf){
//			for(int ic=0; ic<6; ic++){
//				if(m_arry_testpoint[ic] == buf) return ic+1;
//			}
//			return 0;
//		}
//
//		/***************************************************************************
//		 * 
//		 *	ask
//		 *
//		*///************************************************************************
//		public Integer ask_AにとってBは何番目の点か(
//			Test6角形 test6_A,	//I		
//			Test6角形 test6_B	//I		
//		)
//		{
//			//AにとってBは何番目の点か検索
//			int index=-1;
//			for(int ic=1; ic<7; ic++){
//				if(test6_A.get_test_point(ic) != test6_B)	continue;
//				index = ic;
//				break;
//			}
//			
//			//自分の子ではない。
//			if(index == -1) return null;
//			
//			return index;
//		}
//		/***************************************************************************
//		 * 
//		 *	ask
//		 *
//		*///************************************************************************
//		public Test6角形 ask_隣り合う六角形で大きいインデックスの方(Test6角形 child)
//		{
//			try{
//				System.out.println(util.debug_ask_class_method_name());
//				
//				if(child.get_parent() == null) return null;
//				
//				//まず問いかけてきた子供が何番目なのか検索
//				Integer index = ask_AにとってBは何番目の点か(child.get_parent(), child);
//
//				//自分の子ではない。
//				if(index == null) return null;
//
//				//隣の点で大きい方を回答する
//				int answer_index = index + 1;
//				if(answer_index >= 7) answer_index = 1;
//				return this.m_arry_testpoint[answer_index];
//			}
//			catch (Exception ex){
//				util.debug_write_exception(ex);
//			}
//			
//			return null;
//		}
//		/***************************************************************************
//		 * 
//		 *	隣り合う六角形と重複する点を結合を行う。
//		 * 　→まず親に聞く。
//		 * 　　「私の隣の6角形は何？」→「1と3だよ」
//		 * 　　１と３に聞きにいく。
//		 * 　　でも１はからは既に聞かれているはずだな。
//		 * 　　なら３だけ聞きにいく。
//		 * 　　でも自分が２だと知らないはずだな。値が大きい方だけ聞くでいいだろう。
//		 * 　　つまり、３を紹介してもらう。
//		 * 　　３が自分にとっての何番目なのかを調べる。
//		 * 　　２にとっては5番目だよ。
//		 * 　　では5-1＝４番目が共有点となる。
//		 * 　　また、３にとっての２は何番目かきく。
//		 * 　　３にとっては2番目だよ。
//		 * 　　では２＋１＝３番目が共有点となる。
//		 *
//		*///************************************************************************
//		public void 隣り合う六角形と重複する点を結合()
//		{
//			try{
//				System.out.println(util.debug_ask_class_method_name());
//				
//				double d_pitch = m_pitch;
//
//				//親に隣の点で且つ大きいインデックスの方の6角形であるBを教えてもらう
//				Test6角形 buf_test6_B = ask_隣り合う六角形で大きいインデックスの方(this);
//				if(buf_test6_B == null) return;
//
//				///// AとBの共有点を取得（まずはAから） /////
//				// 自分であるAから見てBは何番目か
//				Integer index_A_to_B = ask_AにとってBは何番目の点か(this, buf_test6_B);
//				// AとBの共有点を取得（まずはAから）
//				Object_03_Point buf_test6_A_to_B_same = null;
//				int index_A_to_B_minus = 0;
//				if(index_A_to_B != null){
//					index_A_to_B_minus = index_A_to_B - 1;
//					if(index_A_to_B_minus <= 0) index_A_to_B_minus = 6;
//					//Aの共有点を取得
//					buf_test6_A_to_B_same = this.get_point_index(index_A_to_B_minus);
//				}
//				else{
//					return;
//				}
//
//				///// AとBの共有点を取得（つぎはB） /////
//				// Bから見てAは何番目か
//				Integer index_B_to_A = ask_AにとってBは何番目の点か(buf_test6_B, this);
//				Object_03_Point buf_test6_B_to_A_same = null;
//				int index_B_to_A_plus = 0;
//					if(index_B_to_A != null){
//					index_B_to_A_plus = index_B_to_A + 1;
//					if(index_B_to_A_plus > 6) index_B_to_A_plus = 1;
//					//Aの共有点を取得
//					buf_test6_B_to_A_same = buf_test6_B.get_point_index(index_B_to_A_plus);
//				}
//				else{
//					return;
//				}	
//
//				///// Aの共有点とBの共有点の距離を取得（ピッチより大きい場合は消滅か生成） /////
//				double d_length = mathmatic.Mathmatic.calc_length_between_point_point(
//					buf_test6_A_to_B_same.ask_position(),
//					buf_test6_B_to_A_same.ask_position()
//				);
//
//				///// ●消滅か生成 /////
//				if(d_length >= d_pitch){
//					//消滅か生成の判定
//				}
//				///// 消滅も生成もしない /////
//				else{
//					//2点の中間点を取得
//					double[] mid_point = mathmatic.Mathmatic.calc_mid_point_from_points(
//						buf_test6_A_to_B_same.ask_position(),
//						buf_test6_B_to_A_same.ask_position()
//					);
//
//					Object_03_Point obj_point_mid = new Object_03_Point(mid_point);
//
//					//A側の共有点のTest6角形を取得
//					Test6角形 buf_Test6角形 = this.get_test_point(index_A_to_B_minus);
//					//位置を動かす
//					buf_Test6角形.set_point(obj_point_mid);
//
//					//B側に同じTest6角形を参照させる
//					buf_test6_B.set_test_point(index_B_to_A_plus, buf_Test6角形);
//				}
//			
//			}
//			catch (Exception ex){
//				util.debug_write_exception(ex);
//			}
//
//		}
//
//		/***************************************************************************
//		 * 
//		 *	発展
//		 *
//		*///************************************************************************
//		public void 六角形の足りない点を作成()
//		{
//			//1がない場合、4->中心で新しい点を作る。
//			if(m_arry_testpoint[0] == null)	 {m_arry_testpoint[0] = create_周囲未収束点(4);}
//			//2がない場合、5->中心で新しい点を作る。
//			if(m_arry_testpoint[1] == null)	 {m_arry_testpoint[1] = create_周囲未収束点(5);}
//			//3がない場合、6->中心で新しい点を作る。
//			if(m_arry_testpoint[2] == null)	 {m_arry_testpoint[2] = create_周囲未収束点(6);}
//			//4がない場合、1->中心で新しい点を作る。
//			if(m_arry_testpoint[3] == null)	 {m_arry_testpoint[3] = create_周囲未収束点(1);}
//			//5がない場合、2->中心で新しい点を作る。
//			if(m_arry_testpoint[4] == null)	 {m_arry_testpoint[4] = create_周囲未収束点(2);}
//			//6がない場合、3->中心で新しい点を作る。
//			if(m_arry_testpoint[5] == null)	 {m_arry_testpoint[5] = create_周囲未収束点(3);}
//		}
//		
//		/***************************************************************************
//		 * 
//		 *	周囲未収束点を作る
//		 *
//		*///************************************************************************
//		private Test6角形 create_周囲未収束点(int index)
//		{
//			//中心点取得
//			Object_03_Point buf_center_p = this.m_obj_point;
//			
//			//indexの周囲点を取得
//			Object_03_Point buf_index_p =this.m_arry_testpoint[index-1].get_point();
//			
//			//2つの点から、等間隔に並ぶ次の点を生成
//			Object_03_Point buf_point = Object_03_Point.calc_next_point_by_two_point(buf_index_p, buf_center_p);
//			Test6角形 buf_test = new Test6角形(this, buf_point);
//			
//			//周囲点を入れていく。ただ中心点しかわからないか。
//			buf_test.set_test_point(index, this);
//			
//			return buf_test;
//		}
//	}
	/***************************************************************************
	 * 
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public Object_22_楕円体()
	{
	}
	public Object_22_楕円体(
		String	name,
		Value_DoubleComplex	dCoeffA,
		Value_DoubleComplex	dCoeffB,
		Value_DoubleComplex	dCoeffC,
		Value_DoubleComplex	dCoeffD,
		Value_DoubleComplex	dCoeffE,
		Value_DoubleComplex	dCoeffF,
		Value_DoubleComplex	dCoeffG,
		Value_DoubleComplex	dCoeffH,
		Value_DoubleComplex	dCoeffI,
		Value_DoubleComplex dCoeffRadius2
	)
	{
		System.out.println(util.debug_ask_class_method_name());
		this.m_name = name;
		
		m_CoeffA = dCoeffA;
		m_CoeffB = dCoeffB;
		m_CoeffC = dCoeffC;
		m_CoeffD = dCoeffD;
		m_CoeffE = dCoeffE;
		m_CoeffF = dCoeffF;
		m_CoeffG = dCoeffG;
		m_CoeffH = dCoeffH;
		m_CoeffI = dCoeffI;
		m_CoeffRadius2 = dCoeffRadius2;
	}
	
	///// setter /////
	public void set_range_x_re_min(double d){this.m_range_x_min.set_re(d);}	//XのRe最小範囲
	public void set_range_x_re_max(double d){this.m_range_x_max.set_re(d);}	//XのRe最大範囲
	public void set_range_x_im_min(double d){this.m_range_x_min.set_im(d);}	//XのIm最小範囲
	public void set_range_x_im_max(double d){this.m_range_x_max.set_im(d);}	//XのIm最大範囲
	public void set_pitch(double d){this.m_pitch = d;}	//ピッチ

	//************************************************************************//
	/**
	*	楕円体の3Dデータ作成
	*/
	//************************************************************************//
	private Node create_JavafxNode()
	{		
		try{
			System.out.println(util.debug_ask_class_method_name());

			////////////////////////////////////////////////////////////
			//	ニュートン法で楕円球の式を解く。
			//　※つまり楕円球上の点群をたくさん取得して面を張る
			////////////////////////////////////////////////////////////

			/////////////////////////////////////////////////////////
			//	最初の3角形を作る。
			//	これを核として発展させていく。
			/////////////////////////////////////////////////////////
			Value_DoubleComplex[][] points_3 = create_最初の3角形を作る_1();
//			ArrayList<Test6角形> arry_test_point = create_最初の6角形を作る_2(points_around_shusoku_point);

			///////////////////////////////////
			//	JavaFxデータを作成
			///////////////////////////////////
			Group group = new Group();
			//デバッグ：画面上に点を出力
			ArrayList<Object_03_Point> arry_set_point = new ArrayList<>();
			for(int ic=0; ic<3; ic++){
				arry_set_point.add(new Object_03_Point("", points_3[ic]));
			}

			//点を作成
			for(Object_03_Point buf_point_test : arry_set_point){
				Node node = buf_point_test.ask_JavafxNode();
				if(node == null){
						continue;
				}
				group.getChildren().add(node);
			}
			
			//面を作成
			Object_05_Triangle triangle = new Object_05_Triangle("",arry_set_point);
			group.getChildren().add(triangle.ask_JavafxNode());

			
			//////////////////////////////////////////
			//	
			//////////////////////////////////////////
			Map<Integer, Object_05_Triangle> map_triangle = new HashMap<>();
			map_triangle.put(0, triangle);
			create_核から成長させた3角形パッチ(
				map_triangle);	//IO	初期値は核の3角形パッチ。
			
			this.m_javafxnode = group;
			return group;
	   }
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
		
		return null;
	}

//	private ArrayList<Test6角形> create_最初の6角形を作る_2(double[][][] points_around_shusoku_point)
//	{
//		/////////////////////////////////////////
//		//	周囲の点を発展させる。
//		/////////////////////////////////////////
//		ArrayList<Test6角形> arry_test_point = new ArrayList<>(6);
//		for(int ic=0; ic<7; ic++){
//			arry_test_point.add(new Test6角形(points_around_shusoku_point[ic]));
//		}
//		
//		//中心点
//		Test6角形 center = arry_test_point.get(0);
//		center.set_test_point(1, arry_test_point.get(1));
//		center.set_test_point(2, arry_test_point.get(2));
//		center.set_test_point(3, arry_test_point.get(3));
//		center.set_test_point(4, arry_test_point.get(4));
//		center.set_test_point(5, arry_test_point.get(5));
//		center.set_test_point(6, arry_test_point.get(6));
//		center.set_is_active(false);
//		
//		//1点目
//		Test6角形 point_1st = arry_test_point.get(1);
//		point_1st.set_test_point(1, arry_test_point.get(2));
//		point_1st.set_test_point(2, arry_test_point.get(0));
//		point_1st.set_test_point(3, arry_test_point.get(6));
//		point_1st.set_test_parent(center);
//		
//		//2点目
//		Test6角形 point_2nd = arry_test_point.get(2);
//		point_2nd.set_test_point(1, arry_test_point.get(3));
//		point_2nd.set_test_point(2, arry_test_point.get(0));
//		point_2nd.set_test_point(3, arry_test_point.get(1));
//		point_2nd.set_test_parent(center);
//		
//		//3点目
//		Test6角形 point_3rd = arry_test_point.get(3);
//		point_3rd.set_test_point(1, arry_test_point.get(4));
//		point_3rd.set_test_point(2, arry_test_point.get(0));
//		point_3rd.set_test_point(3, arry_test_point.get(2));
//		point_3rd.set_test_parent(center);
//		
//		//4点目
//		Test6角形 point_4th = arry_test_point.get(4);
//		point_4th.set_test_point(1, arry_test_point.get(5));
//		point_4th.set_test_point(2, arry_test_point.get(0));
//		point_4th.set_test_point(3, arry_test_point.get(3));
//		point_4th.set_test_parent(center);
//		
//		//5点目
//		Test6角形 point_5th = arry_test_point.get(5);
//		point_5th.set_test_point(1, arry_test_point.get(6));
//		point_5th.set_test_point(2, arry_test_point.get(0));
//		point_5th.set_test_point(3, arry_test_point.get(4));
//		point_5th.set_test_parent(center);
//		
//		//6点目
//		Test6角形 point_6th = arry_test_point.get(6);
//		point_6th.set_test_point(1, arry_test_point.get(1));
//		point_6th.set_test_point(2, arry_test_point.get(0));
//		point_6th.set_test_point(3, arry_test_point.get(5));
//		point_6th.set_test_parent(center);
//		
//		return arry_test_point;
//	}
	
	private int create_最初の3角形を作る_0点目(
            Value_DoubleComplex[] point_zero,	//O		
            double[][] vec_法線)			//O
	{
            ////////////////////////////////////////
            //	案２
            //	1変数のニュートンラプソン法をX,Y,Zに対して順次行う。
            //	初期値を決めて、最初はXの反復計算1回→次はYという感じ。
            ////////////////////////////////////////
            //点１の初期値を決める。
            Value_DoubleComplex[] candidate_values = {
                new Value_DoubleComplex(45, 0),
                new Value_DoubleComplex(1, 0),
                new Value_DoubleComplex(1, 0)
            };

            //収束計算
            int n_count = Mathmatic_By_Value1.calc_楕円体上の収束値をがむしゃらに探す(
                this,
                candidate_values);	//IO	初期値と収束値

            for(int ic=0; ic<3; ic++){
                point_zero[ic] = candidate_values[ic];
            }

            ///////////////////////////////////////
            //	中心点（0点）における法線ベクトルを計算
            ///////////////////////////////////////
            for(int ic=0; ic<3; ic++){
                Value_DoubleComplex buf = Newton_Raphson_2_偏微分(ic, point_zero);
                vec_法線[ic][0] = buf.get_re();
                vec_法線[ic][1] = buf.get_im();
            }
            //ユニット化
            Mathmatic.calc_vector_unit_complex(vec_法線, 3, vec_法線);
//            System.out.printf("法線ベクトル %s", util.newline());
//            for(int ic=0; ic<3; ic++){
//                System.out.printf("%f, %f %s",vec_法線[ic][0], vec_法線[ic][1], util.newline());
//            }

            return n_count;
	}
	private int create_最初の3角形を作る_1点目(
            Value_DoubleComplex[] point_zero,	//I		
            double[][] vec_normal_tool,		//I		
            double[][] vec_直交,			//O		
            Value_DoubleComplex[] point_1st)	//O
	{
            /////////////////////////////////
            //	法線ベクトル面上の点を６点出す
            /////////////////////////////////
            /////削りだしたベクトルを伸ばす。そして収束点位置に移動。それが6角形の1点目になる。
            double[] pitch = new double[]{this.m_pitch, 0}; 

            double[][] result = new double[3][2];
            Mathmatic.calc_直交化ベクトルの簡単出力_complex(vec_normal_tool, vec_直交);
//            System.out.printf("直交化ベクトルの簡単出力 %s", util.newline());
//            for(int ic=0; ic<3; ic++){
//                System.out.printf("%f, %f %s",vec_直交[ic][0], vec_直交[ic][1], util.newline());
//            }

            //伸ばす
            Mathmatic.calc_vector_times_scalar_complex(vec_直交, pitch, result);
            //1点目をコピー
            double[][] shusoku_point = new double[3][2];
            for(int ic=0; ic<3; ic++){
                shusoku_point[ic][0] = point_zero[ic].get_re();
                shusoku_point[ic][1] = point_zero[ic].get_im();
            }

            //収束点位置まで移動
            double[][] point1 = new double[3][2];
            Mathmatic.calc_vector_plus_vector_complex(result, shusoku_point, point1);

            point_1st[0] = new Value_DoubleComplex(point1[0][0], point1[0][1]);
            point_1st[1] = new Value_DoubleComplex(point1[1][0], point1[1][1]);
            point_1st[2] = new Value_DoubleComplex(point1[2][0], point1[2][1]);

            //収束計算
            int buf_n_count = Mathmatic_By_Value1.calc_楕円体上の収束値をがむしゃらに探す(
                this,
                point_1st);

            return buf_n_count;
	}
	private int create_最初の3角形を作る_2点目(
            Value_DoubleComplex[]	point_zero,	//I		
            double[][]			vec_法線,	//I		
            Value_DoubleComplex[] 	point_1st,	//I
            Value_DoubleComplex[] 	point_2nd	//O
	)
	{
            double[][] point0 = new double[3][2];
            double[][] point1 = new double[3][2];
            double[][] point2 = new double[3][2];

            //Mathmatic関数使うため値をコンバート
            for(int ic=0; ic<3; ic++){
                //0点
                point0[ic][0] = point_zero[ic].get_re();
                point0[ic][1] = point_zero[ic].get_im();
                //1点
                point1[ic][0] = point_1st[ic].get_re();
                point1[ic][1] = point_1st[ic].get_im();
            }

            //1点目を60°回転させる
            Mathmatic.calc_rotate_around_axis2_2_complex(
                point0,		//I		
                vec_法線,	//I		
                point1,		//I		
                60,			//I		
                point2);	//O		
            
            point_2nd[0] = new Value_DoubleComplex(point2[0][0], point2[0][1]);
            point_2nd[1] = new Value_DoubleComplex(point2[1][0], point2[1][1]);
            point_2nd[2] = new Value_DoubleComplex(point2[2][0], point2[2][1]);

            //収束計算
            int buf_n_count = Mathmatic_By_Value1.calc_楕円体上の収束値をがむしゃらに探す(
                this,
                point_2nd);
            
            return buf_n_count;
	}
	//************************************************************************//
	/**
	 * 最初の3角形を作る
	 * 
	 * @param 
	 * @return double[7][3][2]
	 *	[0][0][0] 6角形の中心点 、Z座標値、実数
	 *	[0][0][1]              、Z座標値、虚数
	 * 
	 *	[n][0][0] 6角形周辺n点目、X座標値、実数
	 *	[n][0][1]              、X座標値、虚数
	 *	[n][1][0]              、Y座標値、実数
	 *	[n][1][1]              、Y座標値、虚数
	 *	[n][2][0]              、Z座標値、実数
	 *	[n][2][1]              、Z座標値、虚数
	 * 
	 */
	//************************************************************************//
	private Value_DoubleComplex[][] create_最初の3角形を作る_1()
	{
		///////////////////////////////////////
		//	中心点（0点）作成
		///////////////////////////////////////
		Value_DoubleComplex[] point_zero = new Value_DoubleComplex[3];
		double[][] vec_法線 = new double[3][2];
		int n_count = create_最初の3角形を作る_0点目(
			point_zero,	//O		
			vec_法線);	//O		

		//コンソール表示
		System.out.printf("反復回数(0点目) :%d%s", n_count, util.newline());
		for(int kc=0; kc<3; kc++){
			System.out.printf("%s, %s",point_zero[kc].print_value(), util.newline());
		}

		///////////////////////////////////////
		//	1点目を作成
		///////////////////////////////////////
		double[][] vec_直交 = new double[3][2];
		Value_DoubleComplex[] point_1st = new Value_DoubleComplex[3];
		n_count = create_最初の3角形を作る_1点目(
			point_zero,	//I		
			vec_法線,	//I		
			vec_直交,	//O		
			point_1st);	//O

		//コンソール表示
		System.out.printf("反復回数(1点目) :%d%s", n_count, util.newline());
		for(int kc=0; kc<3; kc++){
			System.out.printf("%s, %s",point_1st[kc].print_value(), util.newline());
		}

		///////////////////////////////////////
		//	2点目を作成
		///////////////////////////////////////
		Value_DoubleComplex[] point_2nd = new Value_DoubleComplex[3];
		n_count = create_最初の3角形を作る_2点目(
			point_zero,	//I		
			vec_法線,	//I		
			point_1st,	//I
			point_2nd);	//O

		//コンソール表示
		System.out.printf("反復回数(2点目) :%d%s", n_count, util.newline());
		for(int kc=0; kc<3; kc++){
			System.out.printf("%s, %s",point_2nd[kc].print_value(), util.newline());
		}

		Value_DoubleComplex[][] value_return  = {point_zero, point_1st, point_2nd};

		return value_return;
	}
//	//************************************************************************//
//	/**
//	 * 
//	 * @param arry_candidate_values
//	 * @return 
//	 */
//	//************************************************************************//
//	private int calc_楕円体上の収束値をがむしゃらに探す(
//		ArrayList<Value_DoubleComplex> arry_candidate_values 
////		Value_DoubleComplex candidate_values_x,		//IO	X値
////		Value_DoubleComplex candidate_values_y,		//IO	Y値
////		Value_DoubleComplex candidate_values_z		//IO	Z値
//	)
//	{
////		arry_candidate_values
//		Value_DoubleComplex candidate_values_x = arry_candidate_values.get(0);		//IO	X値
//		Value_DoubleComplex candidate_values_y = arry_candidate_values.get(1);		//IO	Y値
//		Value_DoubleComplex candidate_values_z = arry_candidate_values.get(2);		//IO	Z値
//		////////////////////////////////////////
//		//	案２
//		//	1変数のニュートンラプソン法をX,Y,Zに対して順次行う。
//		//	初期値を決めて、最初はXの反復計算1回→次はYという感じ。
//		////////////////////////////////////////
//		Value_DoubleComplex buf_shiki_value = null;
//		int n_count = 0;
//		for(int ic=0; ic<10000; ic++){
//			n_count = ic;
//			//収束判定
//			buf_shiki_value = calc_楕円体(candidate_values_x, candidate_values_y, candidate_values_z);
//			if(buf_shiki_value.ask_length() <= m_shusoku_value){break;}
//
//			//X反復
//			//x(k+1) = x(k) - f(x(k))/f'(x(k))
//			candidate_values_x = (Value_DoubleComplex) candidate_values_x.minus((buf_shiki_value.quot(calc_X_偏微分(candidate_values_x, candidate_values_y, candidate_values_z))));
//
//			//収束判定
//			buf_shiki_value = calc_楕円体(candidate_values_x, candidate_values_y, candidate_values_z);
//			if(buf_shiki_value.ask_length() <= m_shusoku_value){break;}
//
//			//Y反復
//			candidate_values_y = (Value_DoubleComplex) candidate_values_y.minus((buf_shiki_value.quot(calc_Y_偏微分(candidate_values_x, candidate_values_y, candidate_values_z))));
//
//			//収束判定
//			buf_shiki_value = calc_楕円体(candidate_values_x, candidate_values_y, candidate_values_z);
//			if(buf_shiki_value.ask_length() <= m_shusoku_value){break;}
//
//			//Z反復
//			candidate_values_z = (Value_DoubleComplex) candidate_values_z.minus((buf_shiki_value.quot(calc_Z_偏微分(candidate_values_x, candidate_values_y, candidate_values_z))));
//
////			System.out.println(String.valueOf(n_count));
////			System.out.printf("%f, %f %s",candidate_values_x.get_re(), candidate_values_x.get_im(), util.newline());
////			System.out.printf("%f, %f %s",candidate_values_y.get_re(), candidate_values_y.get_im(), util.newline());
////			System.out.printf("%f, %f %s",candidate_values_z.get_re(), candidate_values_z.get_im(), util.newline());
//		}
//		
//		arry_candidate_values.clear();
//		arry_candidate_values.add(candidate_values_x);
//		arry_candidate_values.add(candidate_values_y);
//		arry_candidate_values.add(candidate_values_z);
//		
//		return n_count;
//	}
	//************************************************************************//
	/**
	 * 
	 * @param arry_candidate_values
	 * @return 
	 */
	//************************************************************************//
	//楕円体のx偏微分の値を取得
	private Value_DoubleComplex calc_X_偏微分(
		Value_DoubleComplex value_x,
		Value_DoubleComplex value_y,
		Value_DoubleComplex value_z
	)
	{
		//f = Ax^2 + Bxy + Cxz + Dyx + Ey^2 + Fyz + Gzx + Hzy + Iz^2 - Radius^2
		//xで偏微分
		//∂f/∂x = 2Ax + By + Cz + Dy + Gz
		//        = 2Ax + (B + D)y + (C + G)z
		Value_DoubleComplex buf_kou1 = (Value_DoubleComplex) value_dc2.times(m_CoeffA).times(value_x);
		Value_DoubleComplex buf_kou2 = (Value_DoubleComplex) (m_CoeffB.plus(m_CoeffD)).times(value_y);
		Value_DoubleComplex buf_kou3 = (Value_DoubleComplex) (m_CoeffC.plus(m_CoeffG)).times(value_z);
		Value_DoubleComplex answer = (Value_DoubleComplex) buf_kou1.plus(buf_kou2).plus(buf_kou3);
		
		//0割チェック。f'(x(k))が0なら傾き１にしておく。根拠はないがとりあえず動くように。
		if(answer.ask_length() <= m_shusoku_value){
			answer = new Value_DoubleComplex(1, 0);
		}
			
		return answer;
	}

	//************************************************************************//
	/**
	 * 
	 * @param arry_candidate_values
	 * @return 
	 */
	//************************************************************************//
	//楕円体のy偏微分の値を取得
	private Value_DoubleComplex calc_Y_偏微分(
		Value_DoubleComplex value_x,
		Value_DoubleComplex value_y,
		Value_DoubleComplex value_z
	)
	{
		//f = Ax^2 + Bxy + Cxz + Dyx + Ey^2 + Fyz + Gzx + Hzy + Iz^2 - Radius^2
		//yで偏微分
		//∂f/∂x = Bx + Dx + 2Ey + Fz + Hz
		//        = 2Ey + (B + D)x + (F + H)z

		Value_DoubleComplex buf_kou1 = (Value_DoubleComplex) value_dc2.times(m_CoeffE).times(value_y);
		Value_DoubleComplex buf_kou2 = (Value_DoubleComplex) (m_CoeffB.plus(m_CoeffD)).times(value_x);
		Value_DoubleComplex buf_kou3 = (Value_DoubleComplex) (m_CoeffF.plus(m_CoeffH)).times(value_z);
		Value_DoubleComplex answer = (Value_DoubleComplex) buf_kou1.plus(buf_kou2).plus(buf_kou3);
		
		//0割チェック。f'(x(k))が0なら傾き１にしておく。根拠はないがとりあえず動くように。
		if(answer.ask_length() <= m_shusoku_value){
			answer = new Value_DoubleComplex(1, 0);
		}
			
		return answer;
	}
	//************************************************************************//
	/**
	 * 
	 * @param arry_candidate_values
	 * @return 
	 */
	//************************************************************************//
	//楕円体のz偏微分の値を取得
	private Value_DoubleComplex calc_Z_偏微分(
		Value_DoubleComplex value_x,
		Value_DoubleComplex value_y,
		Value_DoubleComplex value_z
	)
	{
		//f = Ax^2 + Bxy + Cxz + Dyx + Ey^2 + Fyz + Gzx + Hzy + Iz^2 - Radius^2
		//zで偏微分
		//∂f/∂x = Cx + Fy + Gx + Hy + 2Iz
		//        = 2Iz + (C + G)x + (F + H)y
		Value_DoubleComplex buf_kou1 = (Value_DoubleComplex) value_dc2.times(m_CoeffI).times(value_z);
		Value_DoubleComplex buf_kou2 = (Value_DoubleComplex) (m_CoeffC.plus(m_CoeffG)).times(value_x);
		Value_DoubleComplex buf_kou3 = (Value_DoubleComplex) (m_CoeffF.plus(m_CoeffH)).times(value_y);
		Value_DoubleComplex answer = (Value_DoubleComplex) buf_kou1.plus(buf_kou2).plus(buf_kou3);
		
		//0割チェック。f'(x(k))が0なら傾き１にしておく。根拠はないがとりあえず動くように。
		if(answer.ask_length() <= m_shusoku_value){
			answer = new Value_DoubleComplex(1, 0);
		}
			
		return answer;
	}
	//************************************************************************//
	/**
	 * 
	 * @param arry_candidate_values
	 * @return 
	 */
	//************************************************************************//
	private void create_核から成長させた3角形パッチ(
		Map<Integer, Object_05_Triangle> map_triangle)	//IO	初期値は核の3角形パッチ。
	{
//	　→Integerは、次の3角形を作るときに、どの辺の先に作ったのかを示す。1, 2, 4の足し算
//		A.隣を作っていないなら、0
//		B.0-1点間の辺に作れば、1
//		C.1-2点間の辺に作れば、2
//		D.2-0点間の辺に作れば、4
//		3なら、BとC
//		5なら、BとD
//		6なら、CとD
//		7なら、BとCとD
	
	}
//	//************************************************************************//
//	/**
//	 * 
//	 * @param arry_candidate_values
//	 * @return 
//	 */
//	//************************************************************************//
//	//楕円体の式に値を代入
//	private Value_DoubleComplex calc_楕円体(
//		Value_DoubleComplex value_x,
//		Value_DoubleComplex value_y,
//		Value_DoubleComplex value_z
//	)
//	{
//		//f = Ax^2 + Bxy + Cxz + Dyx + Ey^2 + Fyz + Gzx + Hzy + Iz^2 - Radius^2
//
//		Value_DoubleComplex buf_kou1 = (Value_DoubleComplex) m_CoeffA.times(value_x).times(value_x);
//		Value_DoubleComplex buf_kou2 = (Value_DoubleComplex) m_CoeffB.times(value_x).times(value_y);
//		Value_DoubleComplex buf_kou3 = (Value_DoubleComplex) m_CoeffC.times(value_x).times(value_z);
//		Value_DoubleComplex buf_kou4 = (Value_DoubleComplex) m_CoeffD.times(value_y).times(value_x);
//		Value_DoubleComplex buf_kou5 = (Value_DoubleComplex) m_CoeffE.times(value_y).times(value_y);
//		Value_DoubleComplex buf_kou6 = (Value_DoubleComplex) m_CoeffF.times(value_y).times(value_z);
//		Value_DoubleComplex buf_kou7 = (Value_DoubleComplex) m_CoeffG.times(value_z).times(value_x);
//		Value_DoubleComplex buf_kou8 = (Value_DoubleComplex) m_CoeffH.times(value_z).times(value_y);
//		Value_DoubleComplex buf_kou9 = (Value_DoubleComplex) m_CoeffI.times(value_z).times(value_z);
//		Value_DoubleComplex buf_kou10 = (Value_DoubleComplex) m_CoeffRadius2;
//
//		return
//			(Value_DoubleComplex) buf_kou1.plus(buf_kou2).plus(buf_kou3)
//					.plus(buf_kou4).plus(buf_kou5).plus(buf_kou6)
//					.plus(buf_kou7).plus(buf_kou8).plus(buf_kou9)
//					.minus(buf_kou10);
//	}
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
	@Override	public void set_name(String value) {this.m_name = value;}
	@Override	public String get_name() {return this.m_name;}
	
	/***************************************************************************
	 * 
	 * [ObserberReceiveクラスのインプリメントデータ・関数群]
	 * @param 
	 * @return 
	 */
	//**************************************************************************
	public void observer_receive_update(Entity_Imp in_obj)
	{
		if(in_obj instanceof Command_Create_ReImAxisDefine){
			//作り直し
			this.m_update_on = true;
		}
	}
}
