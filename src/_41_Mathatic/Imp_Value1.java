package _41_Mathatic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 真也
 */
public interface Imp_Value1 {
	
	//足し算
	Imp_Value1 plus(Imp_Value1 val);
	
	//引き算
	Imp_Value1 minus(Imp_Value1 val);
	
	//掛け算
	Imp_Value1 times(Imp_Value1 val);
	
	//割り算
	Imp_Value1 quot(Imp_Value1 val);
        
	//0判定(収束判定に使う)
	boolean is_zero();
        
        //大きさ
        double norm();

	
	//画面表示用
	String print_value();
}
