/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _20_Object_Template;

import _22_Object_Primitive.FaceTriangle;
import _22_Object_Primitive.PointTriangle;

/**************************************************************
 *
 * 3角形パッチで面をつくるオブジェクトのためのテンプレート
 * 3角形パッチを効率的に作っていくために必要な関数を定義
 * 
 * @author shinya
 *///**********************************************************
public interface FaceCreate_Imp {
    //核となる3角形を作る
	FaceTriangle create_最初の3角形を作る_1();
	
	//-------Faceオブジェクト側に作る-------
	//→まだ隣を作っていない辺を取得
	
	//3角形の辺の隣に3角形の点を作成
	PointTriangle create_3角形の隣の点(
		FaceTriangle ft,		//I		3角形
		int point_1st_index,	//I		辺の1点目インデックス
		int point_2nd_index);	//I		辺の2点目インデックス

	//-------Faceオブジェクト側に作る-------
	//→3角形の法線ベクトルを取得
    //  →辺を60度回転させて推定点を作成
    //  →推定点を収束点に変換
		
    //点が他と干渉とか、なんか近づきすぎてないかチェック
	boolean check_他の点と近いかどうか(
		PointTriangle	point_1st,	//I		
		PointTriangle	point_2nd);	//I		
	
	//点が他の3角形の中に入っていないかチェック(自分の点が相手に入る場合と、相手の点が自分に入る場合がある）
	int check_他の3角形に入っていないか(
		PointTriangle	point,				//I		
		FaceTriangle	triangle);			//I
		//returnは、結ぶべき3角形の点インデックス（0, 1, 2)。結ばなくていい場合は-1		
	
	//点が他の3角形に入っているどころか、飛び越えていないか。つまり他3角形の2辺を超えてるかチェック
	int check_他の3角形を飛び越えていないか(
		PointTriangle	point,				//I		
		FaceTriangle	triangle);			//I
		//returnは、結ぶべき3角形の点インデックス（0, 1, 2)。結ばなくていい場合は-1		
		
	
	//-------Faceオブジェクト側に作る-------
    //      →チェック引っかかったら既存点と統合する
    //      →既存点側の3角形に辺フラグを登録
    //  →3角形を作る
    //  →隣を作った辺フラグを登録

}
