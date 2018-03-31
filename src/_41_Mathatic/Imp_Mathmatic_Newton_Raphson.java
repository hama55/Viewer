package _41_Mathatic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/***************************************************************************
 * 
 * ニュートンラプソン法を計算
 * doubleだけじゃなく、複素数も計算できるように一般化した。
 * 
 * @author 真也
 *///***********************************************************************

public interface Imp_Mathmatic_Newton_Raphson {
    /***************************************************************************
     * 
     * 式の値を返す
     * 例：  f(x) = 3x^2-5
     *	    式の値(3)なら戻り値22
     * 
     *///***********************************************************************
    Imp_Value1 Newton_Raphson_1_式の値(
		Imp_Value1[] values	//I		各変数の値
	);

    /***************************************************************************
     * 
     * 偏微分値を返す(indexでx,y,zを指定)
     * 例：  f(x) = 3x^2-5
     *	    偏微分f = 6x
     *	    式の値(0,3)なら戻り値18
     * 
     *///***********************************************************************
//    Imp_Value1 Newton_Raphson_2_偏微分(
//		int			index,	//I	偏微分の対象変数(f=ax+by+cz, xなら0とか。)  
//		Imp_Value1[] values	//I	各変数の値    
//    );
    <_T extends Imp_Value1> _T Newton_Raphson_2_偏微分(
		int	index,	//I		偏微分の対象変数(f=ax+by+cz, xなら0番目とか。)  
		_T[] values	//I		各変数の値
    );
}
