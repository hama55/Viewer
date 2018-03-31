package _41_Mathatic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
//import mathmatic.Imp_Value1;
//import mathmatic.Imp_Mathmatic_Newton_Raphson;
import _42_Utility.util;

/**
 *
 * @author 真也
 */
public class Mathmatic_By_Value1 {
	
//	private final static double DEF_EQUAL_ZERO = 1e-8;
//	private final static double DEF_CONVERGENCE_TOL_FOR_CG = 1e-5;
//    private final static double PI = 3.14159265358979;

	/***************************************************************************
	*	
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	//************************************************************************//
	/**
	 * 
	 * @param arry_candidate_values
	 * @return 
	 */
	//************************************************************************//
	static public int calc_楕円体上の収束値をがむしゃらに探す(
            Imp_Mathmatic_Newton_Raphson obj,	//I		方程式を定義したオブジェクト
            Imp_Value1[] candidate_values)	//IO	初期値と収束値
	{
            ////////////////////////////////////////
            //	案２
            //	1変数のニュートンラプソン法をX,Y,Zに対して順次行う。
            //	初期値を決めて、最初はXの反復計算1回→次はYという感じ。
            ////////////////////////////////////////
            Imp_Value1 buf_shiki_value = null;
            int n_count = 0;
            int n_array_num = candidate_values.length;
            boolean b_syusoku = false;

            for(int ic=0; ic<10000; ic++){
                if(b_syusoku == true){break;}
                n_count = ic;

                //収束判定
                buf_shiki_value = obj.Newton_Raphson_1_式の値(candidate_values);
                if(buf_shiki_value.is_zero()){	b_syusoku = true; break;	}

                ArrayList<Imp_Value1> arry_keisuu = new ArrayList<>(n_array_num);

                //各成分の偏微分を算出
                Imp_Value1 sum_square = buf_shiki_value;
                //初期化(Interface越しだと難しいので足して引く)
                sum_square.minus(buf_shiki_value);

                for(int mc=0; mc<n_array_num; mc++){
                    //各成分の偏微分を算出
                    Imp_Value1 buf_keisuu = obj.Newton_Raphson_2_偏微分(mc, candidate_values);
                    arry_keisuu.add(buf_keisuu);
                    //各成分の偏微分二乗の合計を計算
                    sum_square = sum_square.plus(buf_keisuu.times(buf_keisuu));
                }

                for(int jc=0; jc<n_array_num; jc++){
                    //ニュートンラプソン法　次の候補を探す
                    //s = (∂f/∂x)^2 + (∂f/∂y)^2 + (∂f/∂z)^2)
                    //x(k+1) = x(k) - f / s * (∂f/∂x)
                    //y(k+1) = ・・・
                    //z(k+1) = ・・・
                    candidate_values[jc] = candidate_values[jc].minus(buf_shiki_value.quot(sum_square).times(arry_keisuu.get(jc)));
                }

//                System.out.println(String.valueOf(n_count));
//                for(int kc=0; kc<3; kc++){
//                    System.out.printf("%s, %s",candidate_values[kc].print_value(), util.newline());
//                }
            }

            return n_count;
	}
	static public int calc_楕円体上の収束値をがむしゃらに探す(
		Imp_Mathmatic_Newton_Raphson obj,	//I		方程式を定義したオブジェクト
                Imp_Value1 alpha,                       //I             収束緩和係数
		ArrayList<Imp_Value1> candidate_values		//IO	初期値と収束値
	)
	{
		//ArrayList→配列
		Imp_Value1[] values = candidate_values.toArray(new Imp_Value1[candidate_values.size()]);
		int n_count = calc_楕円体上の収束値をがむしゃらに探す(
				obj,	//I		方程式を定義したオブジェクト
				values	//IO	初期値と収束値
		);
		
		for(int ic=0; ic<candidate_values.size(); ic++){
			candidate_values.set(ic, values[ic]);
		}
		
		return n_count;
	}

}