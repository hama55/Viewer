package _41_Mathatic;

import _42_Utility.Opti;
import java.math.BigDecimal;
import java.util.ArrayList;

import _42_Utility.util;

////////////////////////////////////////////////////////////////////////////
/////
/////	数値計算
/////
////////////////////////////////////////////////////////////////////////////
public class Mathmatic
{
	private final static double DEF_EQUAL_ZERO = 1e-8;
	private final static double DEF_CONVERGENCE_TOL_FOR_CG = 1e-5;
    private final static double PI = 3.14159265358979;

	//************************************************************************//
	/**
	*	行列*行列の掛け算を行う。
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static int calculate_Matrix_Times_Matrix(
		int			nMxSize,		//I
		Double[]	vctMatrixA,		//I
		Boolean		bTransA,		//I
		Double[]	vctMatrixB,		//I
		Boolean		bTransB,		//I
		Double[]	vctAnswer		//O
	)
	{
		//変数宣言
		int n_matrix = nMxSize;
		double sum;

		////////// A*B //////////
		if(bTransA == false && bTransB == false){
			///// 掛け算 /////
			for(int ic=0; ic<n_matrix; ic++){
				for(int jc=0; jc<n_matrix; jc++){
					sum = 0.0;
					for(int lc=0; lc<n_matrix; lc++){
						sum += vctMatrixA[mxIndex(n_matrix, ic, lc)] * vctMatrixB[mxIndex(n_matrix, lc, jc)];
					}
					vctAnswer[mxIndex(n_matrix, ic, jc)] = sum;
				}
			}
		}



		////////// A^T * B //////////
		else if(bTransA == true && bTransB == false){
			///// 掛け算 /////
			for(int ic=0; ic<n_matrix; ic++){
				for(int jc=0; jc<n_matrix; jc++){
					sum = 0.0;
					for(int lc=0; lc<n_matrix; lc++){
						sum += vctMatrixA[mxIndex(n_matrix, lc, ic)] * vctMatrixB[mxIndex(n_matrix, lc, jc)];
					}
					vctAnswer[mxIndex(n_matrix, ic, jc)] = sum;
				}
			}
		}



		////////// A * B^T //////////
		else if(bTransA == false && bTransB == true){
			///// 掛け算 /////
			for(int ic=0; ic<n_matrix; ic++){
				for(int jc=0; jc<n_matrix; jc++){
					sum = 0.0;
					for(int lc=0; lc<n_matrix; lc++){
						sum += vctMatrixA[mxIndex(n_matrix, ic, lc)] * vctMatrixB[mxIndex(n_matrix, jc, lc)];
					}
					vctAnswer[mxIndex(n_matrix, ic, jc)] = sum;
				}
			}
		}



		////////// A^T + B^T //////////
		else if(bTransA == true && bTransB == true){
			///// 掛け算 /////
			for(int ic=0; ic<n_matrix; ic++){
				for(int jc=0; jc<n_matrix; jc++){
					sum = 0.0;
					for(int lc=0; lc<n_matrix; lc++){
						sum += vctMatrixA[mxIndex(n_matrix, lc, ic)] * vctMatrixB[mxIndex(n_matrix, jc, lc)];
					}
					vctAnswer[mxIndex(n_matrix, ic, jc)] = sum;
				}
			}
		}

		////////// それ以外の場合 //////////
		else{
			//エラー出力
			return -1;
		}

		return 0;
	}
	/////**************************************************************************
	/////
	/////	関数名：
	/////		int common_calculateArrayTimesMatrix()
	/////
	/////	概要：
	/////		配列*行列の掛け算を行う。
	/////	
	/////	入力/出力：
	/////		[IN]	
	/////		[OUT]	
	/////
	/////
	/////
	/////	戻り値：
	/////
	/////
	/////	備考：
	/////
	/////**************************************************************************
	public static int calculate_Array_Times_Matrix(int nMxSize, Double[] vctArray, Double[] vctvctMatrix, Boolean bTrans, Double[] vctAnswer)
	{
		///// 掛け算(ベクトル×マトリックス) /////
		if(bTrans == false){
			//掛け算(ベクトル×マトリックス)
			for(int ic=0; ic<nMxSize; ic++){
				double sum = 0.0;
				for(int lc=0; lc<nMxSize; lc++){
					sum = sum + vctArray[lc] * vctvctMatrix[mxIndex(nMxSize, lc, ic)];
				}
				vctAnswer[ic] = sum;
			}
		}

		///// 掛け算(ベクトル×転置マトリックス) /////
		else if(bTrans == true){
			//掛け算(ベクトル×マトリックス)
			for(int ic=0; ic<nMxSize; ic++){
				double sum = 0.0;
				for(int lc=0; lc<nMxSize; lc++){
					sum = sum + vctArray[lc] * vctvctMatrix[mxIndex(nMxSize, ic, lc)];
				}
				vctAnswer[ic] = sum;
			}
		}

		///// その他 /////
		else{
			//エラー
			return -1;
		}


		return 0;
	}
	/////**************************************************************************
	/////
	/////	関数名：
	/////		int calculateMatrixTimesArray()
	/////
	/////	概要：
	/////		マトリックス * 配列の掛け算を行う。
	/////	
	/////	入力/出力：
	/////		[IN]	
	/////		[OUT]	
	/////
	/////
	/////
	/////	戻り値：
	/////
	/////
	/////	備考：
	/////
	/////**************************************************************************
	public static int calculate_Matrix_Times_Array(int nMxSize, Double[] vctvctMatrix, Boolean bTrans, Double[] vctArray, Double[] vctAnswer)
	{
		///// 掛け算(マトリックス×ベクトル) /////
		if(bTrans == false){
			//掛け算(マトリックス×ベクトル)
			for(int ic=0; ic<nMxSize; ic++){
				double sum = 0.0;
				for(int lc=0; lc<nMxSize; lc++){
					sum = sum + vctvctMatrix[mxIndex(nMxSize, ic, lc)] * vctArray[lc];
				}
				vctAnswer[ic] = sum;
			}
		}

		///// 掛け算(転置マトリックス×ベクトル) /////
		else if(bTrans == true){
			//掛け算(マトリックス×ベクトル)
			for(int ic=0; ic<nMxSize; ic++){
				double sum = 0.0;
				for(int lc=0; lc<nMxSize; lc++){
					sum = sum + vctvctMatrix[mxIndex(nMxSize, lc, ic)] * vctArray[lc];
				}
				vctAnswer[ic] = sum;
			}
		}

		///// その他 /////
		else{
			//エラー
			return -1;
		}

		return 0;
	}
	//************************************************************************//
	/**
	*	Matrixとvectorの積
	*	Matrixは1次元配列だが、並びは以下とする。
	*
	*	|a0, a1, a2|
	*	|a3, a4, a5|
	*	|a6, a7, a8|
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static void calc_multi_matrix_vector(int nSize, double[] matrix, double[] vector, double[] result){
		
		double[] dBuff = new double[nSize];
		double dSum;
		for(int ic=0; ic<nSize; ic++){
			dSum = 0.0;
			for(int jc=0; jc<nSize; jc++){
				dSum += matrix[ic*nSize+jc] * vector[jc]; 
			}
//			result[ic] = dSum;
			dBuff[ic] = dSum;
		}

		System.arraycopy(dBuff, 0, result, 0, nSize);
	}
	//************************************************************************//
	/**
	*	Matrixの転置行列とvectorの積
	*	転置前のMatrixを入力する。Matrixは1次元配列だが、並びは以下とする。
	*
	*	|a0, a1, a2|
	*	|a3, a4, a5|
	*	|a6, a7, a8|
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static void calc_multi_matrixT_vector(
			int nSize, 
			double[] matrix, 
			double[] vector, 
			double[] result
			)
	{
		
		double[] dBuff = new double[nSize];
		double dSum;
		for(int ic=0; ic<nSize; ic++){
			dSum = 0.0;
			for(int jc=0; jc<nSize; jc++){
				dSum += matrix[jc*nSize+ic] * vector[jc]; 
			}
//			result[ic] = dSum;
			dBuff[ic] = dSum;
		}

		System.arraycopy(dBuff, 0, result, 0, nSize);
	}
	///////////////////////////////////////////////////////////////////////
	//
	//	スワップを考慮して、行列をコピー
	//
	///////////////////////////////////////////////////////////////////////
	public static void swap_matrix(int nSize, Double[] swapMatrix, int[] SwapList, Double[] Matrix)
	{
		for(int ic=0; ic<nSize; ic++)
		{
			for(int jc=0; jc<nSize; jc++)
			{
				Matrix[mxIndex(nSize, ic, jc)] = swapMatrix[mxInxSwap(nSize, SwapList, ic, jc)];
			}
		}
	}
	///////////////////////////////////////////////////////////////////////
	//
	//	行列の行を交換する
	//
	///////////////////////////////////////////////////////////////////////
	public static void swap_matrix_row(int nSize, Double[] matrix, int nFrom, int nTo)
	{
		double dBuf;
		for(int ic=0; ic<nSize; ic++)
		{
			dBuf = matrix[mxIndex(nSize, nFrom, ic)];
			matrix[mxIndex(nSize, nFrom, ic)] = matrix[mxIndex(nSize, nTo, ic)];
			matrix[mxIndex(nSize, nTo, ic)] = dBuf;
		}
	}
	//************************************************************************//
	/**
	*	ベクトルをスワップリストから復元する
	*
	*	@param
	*	@return	
	*	@version
	*/
	//************************************************************************//
	public static void swap_vector(int nSize, Double[] vector, int[] SwapList)
	{
		Double[] vecBuf = new Double[nSize];

		//スワップリストから復元
		int nIndex = 0;
		for(int ic=0; ic<nSize; ic++)
		{
			vecBuf[ic] = vector[SwapList[ic]];
		}

		//コピー
		System.arraycopy(vecBuf, 0, vector, 0, nSize);
	}
	//************************************************************************//
	//
	//	ベクトル同士の足し算
	//
	//************************************************************************//
	public static Opti<Double[]> calc_vector_plus_vector_o(Double[] vec_a, Double[] vec_b)
	{
		try{
			int nSize = vec_a.length;
			Double[] vec_res = new Double[nSize];
			for(int ic=0; ic<nSize; ic++){
				vec_res[ic] = vec_a[ic] + vec_b[ic];
			}
			return Opti.of(vec_res);
		}
		catch(Exception ex){
			return Opti.empty();
		}
	}
	@Deprecated
	public static int calc_vector_plus_vector(Double[] vec_a, Double[] vec_b, Double[] vec_res)
	{
		int nSize = vec_a.length;
		for(int ic=0; ic<nSize; ic++){
			vec_res[ic] = vec_a[ic] + vec_b[ic];
		}

		return 0;
	}
	public static Opti<double[]> calc_vector_plus_vector_o(double[] vec_a, double[] vec_b)
	{
		try{
			int nSize = vec_a.length;
			double[] vec_res = new double[nSize];
			for(int ic=0; ic<nSize; ic++){
				vec_res[ic] = vec_a[ic] + vec_b[ic];
			}
			return Opti.of(vec_res);
		}
		catch(Exception ex){
			return Opti.empty();
		}
	}
	@Deprecated
	public static int calc_vector_plus_vector(double[] vec_a, double[] vec_b, double[] vec_res)
	{
		int nSize = vec_a.length;
		for(int ic=0; ic<nSize; ic++)
		{
			vec_res[ic] = vec_a[ic] + vec_b[ic];
		}

		return 0;
	}
	//************************************************************************//
	//
	//	ベクトル同士の足し算　複素数
	//
	//************************************************************************//
	public static int calc_vector_plus_vector_complex(
		double[][] vec_a,
		double[][] vec_b,
		double[][] vec_res
	)
	{
		int nSize = vec_a.length;
		for(int ic=0; ic<nSize; ic++)
		{
			vec_res[ic][0] = vec_a[ic][0] + vec_b[ic][0];
			vec_res[ic][1] = vec_a[ic][1] + vec_b[ic][1];
		}

		return 0;
	}
	//************************************************************************//
	//
	//	ベクトル同士の引き算
	//
	//************************************************************************//
	public static int calc_vector_minus_vector(Double[] vec_a, Double[] vec_b, Double[] vec_res)
	{
		int nSize = vec_a.length;
		for(int ic=0; ic<nSize; ic++)
		{
			vec_res[ic] = vec_a[ic] - vec_b[ic];
		}

		return 0;
	}	
   	public static int calc_vector_minus_vector(double[] vec_a, double[] vec_b, double[] vec_res)
	{
		int nSize = vec_a.length;
		for(int ic=0; ic<nSize; ic++)
		{
			vec_res[ic] = vec_a[ic] - vec_b[ic];
		}

		return 0;
	}
	//************************************************************************//
	//
	//	ベクトル同士の引き算
	//
	//************************************************************************//
   	public static int calc_vector_minus_vector_complex(
		double[][] vec_a,
		double[][] vec_b,
		double[][] vec_res
	)
	{
		int nSize = vec_a.length;
		for(int ic=0; ic<nSize; ic++)
		{
			vec_res[ic][0] = vec_a[ic][0] - vec_b[ic][0];
			vec_res[ic][1] = vec_a[ic][1] - vec_b[ic][1];
		}

		return 0;
	}
	//************************************************************************//
	/**
	*	ベクトル×スカラー
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
    public static void calc_vector_times_scalar(
        double vector[],    //I     入力ベクトル
        double scalar,      //I     スカラー
        double result[]     //O     スカラー倍した出力ベクトル
    )
    {
        int nSize = vector.length;
        for(int ic=0; ic<nSize; ic++)
        {
            result[ic] = vector[ic]*scalar;
        }
    }
	//************************************************************************//
	/**
	*	ベクトル×スカラー　複素数
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
    public static void calc_vector_times_scalar_complex(
        double[][] vector,	//I     入力ベクトル
        double[] scalar,	//I     スカラー
        double[][] result	//O     スカラー倍した出力ベクトル
    )
    {
        int nSize = vector.length;
        for(int ic=0; ic<nSize; ic++)
        {
            result[ic] = calc_complex_times_complex(vector[ic], scalar);
        }
    }
	//************************************************************************//
	/**
	*	複素数×複素数
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
    public static double[] calc_complex_times_complex(
        double[] complex_a,	//I     複素数a
        double[] complex_b	//I     複素数b
    )
    {
        return new double[]{
			complex_a[0]*complex_b[0] - complex_a[1]*complex_b[1],
			complex_a[1]*complex_b[0] + complex_a[0]*complex_b[1]};
    }

	//************************************************************************//
	//
	//	実数をべき乗
	//
	//************************************************************************//
	public static double calc_power_integer(
			double	dValue, 
			int		nIndex
			)
	{
		double dResult = 1.0;
		for(int ic=0; ic<nIndex; ic++)
		{
			dResult = dResult * dValue;
		}
		return dResult;
	}

	public static void a(){	}
	//================================================================================
	//
	//	ガウスの消去法。LU分解を使用する。メモリを極力節約する
	//
	//================================================================================
	public static int Gauss_Elimination_Method_LU2(int nSize, Double[] matrix, Double[] vectorb)
	{
		int nReturn;
		int[] SwapList = new int[nSize];
		
		//LU分解
		nReturn = LU_decomposition(nSize, matrix, SwapList);
		if(nReturn < 0)
		{
			return -1;
		}
		
		//スワップリストをXに反映
		swap_vector(nSize, vectorb, SwapList);
		
//		//最初の行列を出力
//		utilityMath.output_Matrix("LU", matrix, nSize, nSize);

		//L行列の前進消去
		double dBuf;
		for(int ic=0; ic<nSize-1; ic++)
		{
			dBuf = vectorb[ic];
			//2段目→3段目→・・・→J-1段目→J段目を処理
			for(int jc=ic+1; jc<nSize; jc++)
			{
//				vectorb[jc] -= matrix[mxIndex(nSize, jc, ic)] * vectorb[ic];
				vectorb[jc] -= matrix[mxIndex(nSize, jc, ic)] * dBuf;
			}
		}
		
		//U行列の後退消去
		double dPivotFrac;
		for(int ic=nSize-1; ic>0; ic--)
		{
			dPivotFrac = vectorb[ic]/matrix[mxIndex(nSize, ic, ic)];

			// J段目→J-1段目→　・・・　2段目→1段目
			for(int jc=ic-1; jc>=0; jc--)
			{
//				vectorb[jc] -= matrix[mxIndex(nSize, jc, ic)] * vectorb[ic] * dPivotFrac;
				vectorb[jc] -= matrix[mxIndex(nSize, jc, ic)] * dPivotFrac;
			}
		}		
		for(int ic=0; ic<nSize; ic++)
		{
			vectorb[ic] /= matrix[mxIndex(nSize, ic, ic)];
		}
		
		return 0;
	}
	//================================================================================
	//
	//	ガウスの消去法。LU分解を使用する
	//
	//================================================================================
	public static int Gauss_Elimination_Method_LU(int nSize, Double[] matrix, Double[] vectorb, Double[] vectorX)
	{

		Double[] Lmatrix = new Double[nSize*nSize];
		Double[] Umatrix = new Double[nSize*nSize];
		int[] SwapList = new int[nSize];
		
		//LU分解
		if(LU_decomposition(nSize, matrix, Lmatrix, Umatrix, SwapList) < 0)
		{
			return -1;
		}
//		//最初の行列を出力
//		utilityMath.output_Matrix("L", Lmatrix, nSize, nSize);
//		//最初の行列を出力
//		utilityMath.output_Matrix("U", Umatrix, nSize, nSize);
		
		//スワップリストをXに反映
		for(int ic=0; ic<nSize; ic++)
		{
			vectorX[ic] = vectorb[SwapList[ic]];
		}
		
		//L行列の前進消去
		for(int ic=0; ic<nSize-1; ic++)
		{
			//2段目→3段目→・・・→J-1段目→J段目を処理
			for(int jc=ic+1; jc<nSize; jc++)
			{
				vectorX[jc] -= Lmatrix[mxInxSwap(nSize, SwapList, jc, ic)] * vectorX[ic];
			}
		}
		
		//U行列の後退消去
		double dPivotFrac;
		for(int ic=nSize-1; ic>0; ic--)
		{
			dPivotFrac = 1.0/Umatrix[mxIndex(nSize, ic, ic)];

			// J段目→J-1段目→　・・・　2段目→1段目
			for(int jc=ic-1; jc>=0; jc--)
			{
				vectorX[jc] -= Umatrix[mxIndex(nSize, jc, ic)] * vectorX[ic] * dPivotFrac;
			}
		}		
		for(int ic=0; ic<nSize; ic++)
		{
			vectorX[ic] /= Umatrix[mxIndex(nSize, ic, ic)];
		}
		
		return 0;
	}

	//================================================================================
	//
	//拡大係数行列を入力し、ガウスの消去法によって計算する。
	//
	//戻り値：
	//正常：0
	//異常：-1
	//
	//備考：
	//1.拡大係数行列が参照渡しによって渡され、答えも参照渡しで戻される。
	//2.拡大係数行列の大きさは問わない
	//
	//入力と出力：
	//|A1, A2, A3, A4|		|1, 0, 0, Answer1|
	//|B1, B2, B3, B4|	⇒	|0, 1, 0, Answer2|
	//|C1, C2, C3, C4|		|0, 0, 1, Answer3|
	//
	//================================================================================
	public static int Gauss_Elimination_Method(int nRowSize, Double[] vctExpandMatrix)
	{
		// 一次独立性のチェック
		//※結果の算出可否でチェックとする
		
		int[] SwapList = new int[nRowSize];
		for(int ic=0; ic<nRowSize; ic++)
		{
			SwapList[ic] = ic;
		}

		///// 前進消去 /////
		int nColumnSize = nRowSize+1;
		for(int ic=0; ic<nRowSize-1; ic++)
		{
			////// ピボット判定による配列の交換 /////
			double dPivot;
			double dPivotAbs;
			double dPivotmax = 0.0;
			double dPivotmaxAbs = 0.0;
			int nPivotmax = ic;
			//ピボット最大の行を探す
			for(int nc=ic; nc<nRowSize; nc++)
			{
				// ピボットが0の場合
				dPivot = vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, nc, ic)];
				dPivotAbs = Math.abs(dPivot);
				if(dPivotAbs > dPivotmaxAbs)
				{
					dPivotmax = dPivot;
					dPivotmaxAbs = dPivotAbs;
					nPivotmax = nc;
				}
			}
			// ピボット最大値が0になる場合
			if(dPivotmaxAbs < DEF_EQUAL_ZERO)
			{
				// 一次独立では無いため、結果を算出できない
				return -1;
			}
			else
			{
				// 2つの行を交換
				int nBuf = SwapList[ic];
				SwapList[ic] = SwapList[nPivotmax];
				SwapList[nPivotmax] = nBuf;
			}


			// 2段目→3段目→・・・→J-1段目→J段目を処理
			for(int jc=ic+1; jc<nRowSize; jc++)
			{
				dPivot = vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, ic, ic)];
				// K-1列目→K列目
				double dBuf = vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, jc, ic)]/dPivot;
				for(int kc=ic+1; kc<nRowSize+1; kc++)
				{
					vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, jc, kc)] -= vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, ic, kc)]*dBuf;
				}
				vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, jc, ic)] = 0.0;
			}
		}

		///// 後進消去 /////
		for(int ic=nRowSize-1; ic>0; ic--)
		{
			double dPivot = vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, ic, ic)];

			// J段目→J-1段目→　・・・　2段目→1段目
			double dBuf = vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, ic, nRowSize)]/dPivot;
			for(int jc=ic-1; jc>=0; jc--)
			{
				vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, jc, nRowSize)] -= vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, jc, ic)]*dBuf;
				vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, jc, ic)] = 0.0;
			}
		}

		///// 解 /////
		for(int ic=0; ic<nRowSize; ic++)
		{
			vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, ic, nRowSize)] /= vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, ic, ic)];
			vctExpandMatrix[mxInxSwap(nColumnSize, SwapList, ic, ic)] = 1.0;
		}

		return 0;
	}
	//*===================================================
	//
	//	行列を1次元配列→2次元配列へ変換
	//
	//*===================================================
	static int change_matrix_arry(
		int			n_size,			//I		正方行列のサイズ　n×n
		Double[]	mat_1_before,	//I		1次元配列の行列
		Double[][]	mat_2_after		//O		2次元配列の行列
	)
	{
		for(int ic=0; ic<n_size; ic++)
		{
			for(int jc=0; jc<n_size; jc++)
			{
				mat_2_after[ic][jc] = mat_1_before[mxIndex(n_size, ic, jc)];
			}
		}
		
		return 0;
	}
	//*===================================================
	//
	//	行列を2次元配列→1次元配列へ変換
	//
	//*===================================================
	static int change_matrix_arry(
		Double[][]	mat_2_before,	//O		2次元配列の行列
		Double[]	mat_1_after		//I		1次元配列の行列
	)
	{
		int n_size = mat_2_before.length;
		
		for(int ic=0; ic<n_size; ic++)
		{
			for(int jc=0; jc<n_size; jc++)
			{
				mat_1_after[mxIndex(n_size, ic, jc)] = mat_2_before[ic][jc];
			}
		}
		
		return 0;
	}
	//*===================================================
	//*
	//*	2次元配列を1次元に格納するとき、添え字2つから1次元の位置を算出
	//
	//	LU分解用
	//
	//	|<--nColumnSize-->|
	//	| 0, 1, 2, 3, 4, 5|
	//	| 6, 7, 8, 9,10,11|
	//	|12,13,14,15,16,17|
	//	|18,19,20,21,22,23|
	//	|24,25,26,27,28,29|
	//*
	//*===================================================
	private static int mxInxSwap(int nColumnSize, int[] SwapList, int nI, int nJ)
	{
		return SwapList[nI]*nColumnSize + nJ;
	}
	///////////////////////////////////////////////////////////////////////
	//
	//	LU分解
	//
	//入力と出力：
	//    行列A           L（下三角）                U(上三角)
	//|A1, A2, A3|		|1,  0,  0|   |C1, C2, C3|
	//|B1, B2, B3|	⇒	|B1, 1,  0|   | 0, C4, C5|
	//|C1, C2, C3|		|B2, B3, 1|   | 0,  0, C6|
	///////////////////////////////////////////////////////////////////////
	public static int LDL_decomposition(
		int nSize,
		Double[] Amatrix,
		Double[] Lmatrix,
		Double[] dTrace,
		int[] SwapList
	)
	{
		//LU分解を実施
		Double[] Umatrix = new Double[nSize*nSize];
		LU_decomposition(nSize, Amatrix, Lmatrix, Umatrix, SwapList);
		
		//Umatrixの対角成分で、各行を割る
		double dBuf;
		for(int ic=0; ic<nSize; ic++)
		{
			dBuf = Umatrix[mxIndex(nSize, ic, ic)];
			dTrace[ic] = dBuf;
			for(int jc=ic+1; jc<nSize; jc++)
			{
				Umatrix[mxIndex(nSize, ic, jc)] /= dBuf;
			}
			//対角成分は1.0になる
			Umatrix[mxIndex(nSize, ic, ic)] = 1.0;
		}
		///// L行列の出力 ///// 
		Double[] LmatrixFix = new Double[nSize*nSize];
		Mathmatic.swap_matrix(nSize, Lmatrix, SwapList, LmatrixFix);
		Mathmatic.output_Matrix("L_Fixの行列", LmatrixFix, nSize, nSize);
		Mathmatic.output_Matrix("Uの行列", Umatrix, nSize, nSize);
		
//		///// テスト、L行列の出力 ///// 
//		utilityMath.output_Matrix(String.format("Lの行列 %d"), Lmatrix, nSize, nSize);
//
//		///// テスト、U行列の出力 ///// 
//		utilityMath.output_Matrix(String.format("Uの行列 %d"), Umatrix, nSize, nSize);
		
		
		return 0;
	}
	///////////////////////////////////////////////////////////////////////
	//
	//	LU分解
	//
	//	入力のA行列をそのままLU行列に変換
	//
	//入力と出力：
	//    行列A           L（下三角）    U(上三角)       LU(Lの対角は1なので省略して足し合わせた)
	//|A1, A2, A3|		|1,  0,  0|   |C1, C2, C3|		|C1, C2, C3|
	//|B1, B2, B3|	⇒	|B1, 1,  0|   | 0, C4, C5| ⇒	|B1, C4, C5|
	//|C1, C2, C3|		|B2, B3, 1|   | 0,  0, C6|		|B2, B3, C6|
	///////////////////////////////////////////////////////////////////////
	public static int LU_decomposition(
		int			nSize,		//I		正方行列の大きさ n×n
		Double[]	Amatrix,	//IO	行列
		int[]		SwapList,	//O		ピボット0を回避したときの行の入れ替えリスト
		int[]		swap_num	//O		ピボット0を回避のための行入れ替え回数。行列式計算時に必要。
	)
	{
		//変数宣言
		int n_matrix = nSize;
		
//		swap_num = new int[1];

		//ピボットが0を回避するためのスワップリストを作成
		for(int ic=0; ic<n_matrix; ic++)
		{
			SwapList[ic] = ic;
		}

		////////// LU分解の実行 //////////
		double dBuf;
		int nBuf;
		for(int ic = 0; ic < n_matrix; ic++)
		{
			///// ピボットを選ぶ /////
			///// U行列の対角線の値を生成し、最大のものが最適なピボットとなる/////
			double dPivotAbs;
			double dPivot;
			double dPivotMaxAbs = 0.0;
			int nPivotmax = ic;
			for(int jc = ic; jc < n_matrix; jc++)
			{	
				//ピボットを算出
				dBuf = 0.0;
				for(int kc = 0; kc < ic; kc++)
				{
					dBuf += Amatrix[mxIndex(n_matrix, kc, ic)] * Amatrix[mxIndex(n_matrix, jc, kc)];
				}
				dPivot = Amatrix[mxIndex(n_matrix, jc, ic)] - dBuf;
				
				//ピボットが最大か判定
				dPivotAbs = Math.abs(dPivot);
				if(dPivotAbs > dPivotMaxAbs)
				{
					dPivotMaxAbs = dPivotAbs;
					nPivotmax = jc;
				}				
			}
			// ピボット最大値が0になる場合
			if(dPivotMaxAbs < DEF_EQUAL_ZERO)
			{
				// 一次独立では無いため、結果を算出できない
				return -1;
			}
			else if(ic != nPivotmax)
			{
				// 2つの行を交換
				nBuf = SwapList[ic];
				SwapList[ic] = SwapList[nPivotmax];
				SwapList[nPivotmax] = nBuf;
				
				swap_matrix_row(nSize, Amatrix, ic, nPivotmax);
				
				//行入れ替えをカウントアップ
				swap_num[0]++;
			}
				
			///// U行列の生成 /////
			for(int jc = ic; jc < n_matrix; jc++)
			{	
				dBuf = 0.0;
				for(int kc = 0; kc < ic; kc++)
				{
					dBuf += Amatrix[mxIndex(n_matrix, kc, jc)] * Amatrix[mxIndex(n_matrix, ic, kc)];
				}
				Amatrix[mxIndex(n_matrix, ic, jc)] = Amatrix[mxIndex(n_matrix, ic, jc)] - dBuf;
			}
			///// L行列の生成 /////
			for(int jc = ic+1; jc < n_matrix; jc++)
			{
				dBuf = 0.0;
				for(int kc = 0; kc < ic; kc++)
				{
					dBuf += Amatrix[mxIndex(n_matrix, kc, ic)] * Amatrix[mxIndex(n_matrix, jc, kc)];
				}
				Amatrix[mxIndex(n_matrix, jc, ic)] = (Amatrix[mxIndex(n_matrix, jc, ic)] - dBuf)/Amatrix[mxIndex(n_matrix, ic, ic)];
			}
		}

//		utilityMath.output_Matrix("A行列", Amatrix, nSize, nSize);
//
//		///// 分解が合っているか、LとUを掛けた行列の出力 ///// 
//		double dSum = 0.0;
//		Double[] Ansmatrix = new Double[nSize*nSize];
//		double dL = 0.0;
//		double dU = 0.0;
//		for(int ic=0; ic<nSize; ic++)
//		{
//			for(int jc=0; jc<nSize; jc++)
//			{
//				dSum = 0.0;
//				for(int kc=0; kc<nSize; kc++)
//				{
//					//Lの値
//					if(ic < kc)
//					{
//						dL = 0.0;
//					}
//					else if(ic == kc)
//					{
//						dL = 1.0;
//					}
//					else
//					{
//						dL = Amatrix[mxIndex(nSize, ic, kc)];
//					}
//					
//					//Uの値
//					if(kc <= jc)
//					{
//						dU = Amatrix[mxIndex(nSize,kc, jc)];
//					}
//					else
//					{
//						dU = 0.0;
//					}
//
//					dSum += dL * dU;
//				}
//				Ansmatrix[mxIndex(nSize, ic, jc)] = dSum;
//			}
//		}
//		utilityMath.output_Matrix("掛けた時の行列", Ansmatrix, nSize, nSize);

		return 0;
	}
	///////////////////////////////////////////////////////////////////////
	//
	//	LU分解
	//
	//	入力のA行列をそのままLU行列に変換
	//
	//入力と出力：
	//    行列A           L（下三角）    U(上三角)       LU(Lの対角は1なので省略して足し合わせた)
	//|A1, A2, A3|		|1,  0,  0|   |C1, C2, C3|		|C1, C2, C3|
	//|B1, B2, B3|	⇒	|B1, 1,  0|   | 0, C4, C5| ⇒	|B1, C4, C5|
	//|C1, C2, C3|		|B2, B3, 1|   | 0,  0, C6|		|B2, B3, C6|
	///////////////////////////////////////////////////////////////////////
	public static int LU_decomposition(
		int			nSize,		//I		正方行列の大きさ n×n
		Double[]	Amatrix,	//IO	行列
		int[]		SwapList	//O		ピボット0を回避したときの行の入れ替えリスト
	)
	{
		int swap_count[] = new int[1];
		
		return LU_decomposition(
			nSize,		//I		正方行列の大きさ n×n
			Amatrix,	//IO	行列
			SwapList,	//O		ピボット0を回避したときの行の入れ替えリスト
			swap_count
		);
	}

	///////////////////////////////////////////////////////////////////////
	//
	//	LU分解
	//
	//入力と出力：
	//    行列A           L（下三角）                U(上三角)
	//|A1, A2, A3|		|1,  0,  0|   |C1, C2, C3|
	//|B1, B2, B3|	⇒	|B1, 1,  0|   | 0, C4, C5|
	//|C1, C2, C3|		|B2, B3, 1|   | 0,  0, C6|
	///////////////////////////////////////////////////////////////////////
	@Deprecated
	public static int LU_decomposition(
		int			nSize,
		Double[]	Amatrix,
		Double[]	Lmatrix,
		Double[]	Umatrix,
		int[]		SwapList
	)
	{
		//変数宣言
		int n_matrix = nSize;

		//ピボットが0を回避するためのスワップリストを作成
		for(int ic=0; ic<n_matrix; ic++)
		{
			SwapList[ic] = ic;
		}
		
		///// L行列、U行列を1と0で初期化 /////
		for(int ic = 0; ic < n_matrix; ic++)
		{
			for(int jc = 0; jc < n_matrix; jc++)
			{
				Umatrix[mxIndex(n_matrix, ic, jc)] = 0.0;
				Lmatrix[mxIndex(n_matrix, ic, jc)] = 0.0;
			}
		}

		////////// LU分解の実行 //////////
		double dBuf;
		for(int ic = 0; ic < n_matrix; ic++)
		{
			///// ピボットを選ぶ /////
			///// U行列の対角線の値を生成し、最大のものが最適なピボットとなる/////
			double dPivotAbs;
			double dPivot;
			double dPivotMaxAbs = 0.0;
			int nPivotmax = ic;
			for(int jc = ic; jc < n_matrix; jc++)
			{	
				//ピボットを算出
//				Umatrix[mxIndex(n_matrix, jc, jc)] = Amatrix[mxInxLU(n_matrix, SwapList, jc, jc)];
				dBuf = 0.0;
				for(int kc = 0; kc < jc; kc++)
				{
//					dBuf += Umatrix[mxIndex(n_matrix, kc, jc)] * Lmatrix[mxInxSwap(n_matrix, SwapList, jc, kc)];
					dBuf += Umatrix[mxIndex(n_matrix, kc, ic)] * Lmatrix[mxInxSwap(n_matrix, SwapList, jc, kc)];
				}
//				dPivot = Umatrix[mxIndex(n_matrix, jc, jc)] - dBuf;
//				dPivot = Amatrix[mxInxSwap(n_matrix, SwapList, jc, jc)] - dBuf;
				dPivot = Amatrix[mxInxSwap(n_matrix, SwapList, jc, ic)] - dBuf;
				
				//ピボットが最大か判定
				dPivotAbs = Math.abs(dPivot);
				if(dPivotAbs > dPivotMaxAbs)
				{
					dPivotMaxAbs = dPivotAbs;
					nPivotmax = jc;
				}				
			}
			// ピボット最大値が0になる場合
			if(dPivotMaxAbs < DEF_EQUAL_ZERO)
			{
				// 一次独立では無いため、結果を算出できない
				return -1;
			}
			else
			{
				// 2つの行を交換
				int nBuf = SwapList[ic];
				SwapList[ic] = SwapList[nPivotmax];
				SwapList[nPivotmax] = nBuf;
			}


				
			///// U行列の生成 /////
			for(int jc = ic; jc < n_matrix; jc++)
			{	
//				Umatrix[mxIndex(n_matrix, ic, jc)] = Amatrix[mxInxLU(n_matrix, SwapList, ic, jc)];
				dBuf = 0.0;
				for(int kc = 0; kc < ic; kc++)
				{
					dBuf += Umatrix[mxIndex(n_matrix, kc, jc)] * Lmatrix[mxInxSwap(n_matrix, SwapList, ic, kc)];
				}
//				Umatrix[mxIndex(n_matrix, ic, jc)] -= dBuf;
				Umatrix[mxIndex(n_matrix, ic, jc)] = Amatrix[mxInxSwap(n_matrix, SwapList, ic, jc)] - dBuf;
			}
			///// L行列の生成 /////
			for(int jc = ic+1; jc < n_matrix; jc++)
			{
//				Lmatrix[mxInxLU(n_matrix, SwapList, jc, ic)] = Amatrix[mxInxLU(n_matrix, SwapList, jc, ic)];
				dBuf = 0.0;
				for(int kc = 0; kc < ic; kc++)
				{
					dBuf += Umatrix[mxIndex(n_matrix, kc, ic)] * Lmatrix[mxInxSwap(n_matrix, SwapList, jc, kc)];
				}
//				Lmatrix[mxInxLU(n_matrix, SwapList, jc, ic)] -= dBuf;
//				Lmatrix[mxInxLU(n_matrix, SwapList, jc, ic)] /= Umatrix[mxIndex(n_matrix, ic, ic)];
				Lmatrix[mxInxSwap(n_matrix, SwapList, jc, ic)] = (Amatrix[mxInxSwap(n_matrix, SwapList, jc, ic)] - dBuf)/Umatrix[mxIndex(n_matrix, ic, ic)];
			}
			
//			///// テスト、L行列の出力 ///// 
//			utilityMath.output_Matrix(String.format("Lの行列 %d", ic), Lmatrix, nSize, nSize);
//			///// テスト、U行列の出力 ///// 
//			utilityMath.output_Matrix(String.format("Uの行列 %d", ic), Umatrix, nSize, nSize);
//			///// テスト、スワップリスト出力 ///// 
//			utilityMath.output_Array(String.format("スワップリスト %d", ic), SwapList, true);
		}
		
//		///// L行列の対角を1に設定 /////
//		for(int ic = 0; ic < n_matrix; ic++)
//		{
//			Lmatrix[mxInxLU(n_matrix, SwapList, ic, ic)] = 1.0;
//		}

		return 0;
	}

	//*===================================================
	//*
	//*	2次元配列を1次元に格納するとき、添え字2つから1次元の位置を算出
	//
	//	|<--nColumnSize-->|
	//	| 0, 1, 2, 3, 4, 5|
	//	| 6, 7, 8, 9,10,11|
	//	|12,13,14,15,16,17|
	//	|18,19,20,21,22,23|
	//	|24,25,26,27,28,29|
	//*
	//*===================================================
	private static int mxIndex(
		int nColumnSize,	//I		正方行列のサイズ	n×n
		int nI,				//I		行のインデックス
		int nJ				//I		列のインデックス
	)
	{
		return nI*nColumnSize + nJ;
	}

	/*/////////////////////////////////////////////////
	//
	//	最小二乗法（線形）
	 * 引数
	double[]	dX 		[IN]	Yの値
	double[]	dY 		[IN]	Xの値
	int			nStart	[IN]	配列の何番目から算出するか
	int			nNumber	[IN]	計算に使用する個数
	double[]	dResult	[OUT]	0:線形の傾きA、1:切片B
	戻り値
	0	success
	-1	error	Xの数が足りない
	-2	error	Yの数が足りない
	-3	error	Xの総和が0のため、傾きA,切片Bが求まらない
	//
	 */
	/////////////////////////////////////////////////
	//************************************************************************//
	/**
	 *	最小二乗法（線形）
	 *
	 *	@param	dX 			[IN]	Yの値
	 *	@param	dY 			[IN]	Xの値
	 *	@param	nStart		[IN]	配列の何番目から算出するか
	 *	@param	dnNumber	[IN]	計算に使用する個数
	 *	@param	dX 			[IN]	Yの値
	 *	@param	Result		[OUT]	0:線形の傾きA、1:切片B
	 *
	 *	@return	0	success
	 *	-1	error	Xの数が足りない
	 *	-2	error	Yの数が足りない
	 *	-3	error	Xの総和が0のため、傾きA,切片Bが求まらない
	 *
	 *	@version
	 */
	//************************************************************************//
	public static int calc_least_square_method_linear(Double[] dX, Double[] dY, int nStart, int nNumber, Double[] dResult) {
		int nNumX = dX.length;
		int nNumY = dY.length;
		int nEnd = nStart + nNumber;
		if (nNumX < nEnd) {
			//Xの数が足りない
			return -1;
		}
		if (nNumY < nEnd) {
			//Yの数が足りない
			return -2;
		}
		if (nStart < 0) {
			//スタートがマイナスになっている
			return -3;
		}
		double dSigmaY = 0.0;
		double dSigmaX = 0.0;
		double dSigmaXY = 0.0;
		double dSigmaX2 = 0.0;
		for (int ic = nStart; ic < nEnd; ic++) {
			dSigmaY += dY[ic];
			dSigmaX += dX[ic];
			dSigmaXY += dX[ic] * dY[ic];
			dSigmaX2 += dX[ic] * dX[ic];
		}
		if (dSigmaX == 0.0) {
			return -4;
		}
		double dBuf = nNumber * dSigmaX2 - dSigmaX * dSigmaX;
		dResult[0] = (nNumber * dSigmaXY - dSigmaX * dSigmaY) / dBuf;
		dResult[1] = (dSigmaX2 * dSigmaY - dSigmaXY * dSigmaX) / dBuf;
		return 0;
	}

	public void b(){}
	//================================================================================
	//
	//	共役勾配法	Ax = bの連立一次方程式を解く
	//	森正武　数値解析　共立出版　p59　
	//
	//戻り値：
	//正常：0
	//異常：-1
	//
	//備考：
	//1.係数行列と値ベクトルが渡される
	//2.行列の大きさは問わない
	//
	//入力と出力：
	//|A11, A12, A13|		|B1|	  |X1(Answer)|
	//|A21, A22, A23|  &	|B2|  ⇒     |X2(Answer)|
	//|A31, A32, A33|		|B3|	  |X3(Answer)|
	//
	//================================================================================
	public static int calculate_CG(
		Double[]	Amatrix,
		Double[]	Bvector,
		Double[]	Xvector,
		int			nCount
	)
	{
		//#define OUTPUT
//		#define CG_SYMMETRY
		Boolean bCG_SYMMETRY = true;
		
		Boolean bPrint = false;

		int nReturn = 0;

		//変数宣言
		int nSize = Bvector.length;
		Double[] vec_r = new Double[nSize]; 
		Double[] vec_x = new Double[nSize]; 
		Double[] vec_p = new Double[nSize]; 

		///// 初期xベクトルを設定 /////
		for(int ic=0; ic<nSize; ic++)
		{
			vec_x[ic] = 1.0;
		}
		if(bPrint == true)	output_Array("初期xベクトル", vec_x, false);

		///// 初期rベクトルの算出 /////
		//変数宣言
		Double[] vecBuff = new Double[nSize]; 
		//Axを算出
		nReturn = calculate_Matrix_Times_Array(nSize, Amatrix, false, vec_x, vecBuff);
		//r = b-Axを算出
		calc_vector_minus_vector(Bvector, vecBuff, vec_r);
		if(bPrint == true)	output_Array("初期rベクトル", vec_r, false);
		
		///// 初期pベクトルの算出(rをコピー) /////
		copy_vector(3, vec_r, vec_p);
		if(bPrint == true)	output_Array("初期pベクトル", vec_p, false);

		///// 共役勾配法のループ /////
		for(int ic=0; true ; ic++)
		{
			if(bPrint == true)
			{
				System.out.println("/////////////////////////////////");
				System.out.println("//");
				System.out.println(String.format("//\t\t 反復数:%d回目",ic+1));
				System.out.println("//");
				System.out.println("/////////////////////////////////");
				System.out.println();
			}

			//********************
			//
			//		rをコピー
			//
			//********************
			//変数宣言
			Double[] vec_r_old = new Double[nSize]; 
			//コピー
			copy_vector(3, vec_r, vec_r_old);

			double dAlpha;
			double dBuff_rr;
			double dBuff_pAp;
			Double[] vecBuff_Ap = new Double[nSize]; 
			if(bCG_SYMMETRY == true)
			{
				//********************
				//
				//		αを算出
				//
				//********************
				//r*rを算出
				dBuff_rr = calc_inner_product(vec_r_old, vec_r_old);
				//Apを算出
				nReturn = calculate_Matrix_Times_Array(nSize, Amatrix, false, vec_p, vecBuff_Ap);
				//p*Apを算出
				dBuff_pAp = calc_inner_product(vec_p, vecBuff_Ap);
				//ゼロ割チェック
				if(Math.abs(dBuff_pAp) < DEF_EQUAL_ZERO)
				{
					//エラー出力
					nReturn = output_Value("反復回数", ic+1);
					nReturn = output_Value("エラー：ゼロ割発生：dBuff_pAp", dBuff_pAp);
					return 1;
				}
				//αを算出
				dAlpha = dBuff_rr/dBuff_pAp;
				if(bPrint == true)	output_Value("αを算出", dAlpha);
			}

			else
			{
				//*********************************
				//
				//		αを算出(式変形前の形)
				//
				//*********************************
				//変数宣言
				double dBuff_pr;
				//初期化
				for(int jc=0; jc<nSize; jc++){vecBuff_Ap[jc] = 0.0;}
				//r*rを算出
				dBuff_rr = calc_inner_product(vec_r_old, vec_r_old);
				//p*rを算出
				dBuff_pr = calc_inner_product(vec_p, vec_r_old);
				//Apを算出
				nReturn = calculate_Matrix_Times_Array(nSize, Amatrix, false, vec_p, vecBuff_Ap);
				//p*Apを算出
				dBuff_pAp = calc_inner_product(vec_p, vecBuff_Ap);
				//ゼロ割チェック
				if(Math.abs(dBuff_pAp) < DEF_EQUAL_ZERO)
				{
					//エラー出力
					nReturn = output_Value("反復回数", ic+1);
					nReturn = output_Value("エラー：ゼロ割発生：dBuff_pAp", dBuff_pAp);
					return 1;
				}
				//αを算出
				dAlpha = dBuff_pr/dBuff_pAp;
				if(bPrint == true)
				{
					//nReturn = output_Value("dBuff_rrを算出", dBuff_rr);
					//nReturn = output_Value("dBuff_pApを算出", dBuff_pAp);
					nReturn = output_Value("αを算出", dAlpha);
				}
			}

			//********************
			//
			//		xを更新
			//
			//********************
			for(int jc=0; jc<nSize; jc++)
			{
				vec_x[jc] += dAlpha * vec_p[jc];
			}
			if(bPrint == true)	output_Array("xベクトル", vec_x, false);

			//********************
			//
			//		rを更新
			//
			//********************
			for(int jc=0; jc<nSize; jc++)
			{
				vec_r[jc] = vec_r_old[jc] - dAlpha * vecBuff_Ap[jc];
			}
			if(bPrint == true)	output_Array("rベクトル", vec_r, false);
			
			//********************
			//
			//		収束判定
			//
			//********************
			//変数宣言
			double dEpsilon = DEF_CONVERGENCE_TOL_FOR_CG;
//			Boolean bConvergence = true;
			//判定
			double dNormR = calc_inner_product(vec_r, vec_r);
			double dNormB = calc_inner_product(Bvector, Bvector);
			if(dNormR <= dEpsilon * dNormB)
			{
				//収束したのでループを抜ける
				break;
			}

			double dBeta;
			double dBuff_pAr_new;
			if(bCG_SYMMETRY == true)
			{
				//****************************************
				//
				//		βを算出(対称行列のみ使用可能)
				//
				//****************************************
				double dBuff_rr_new;
				//r_old*r_oldを算出（上で計算しているのでそれを使用）dBuff_rr
				//r*rを算出
				dBuff_rr_new = calc_inner_product(vec_r, vec_r);
				//ゼロ割チェック
				if(Math.abs(dBuff_rr) < DEF_EQUAL_ZERO)
				{
					//エラー出力
					nReturn = output_Value("反復回数", ic+1);
					nReturn = output_Value("エラー：ゼロ割発生：dBuff_rr", dBuff_rr);
					return 1;
				}
				//βを算出
				dBeta = dBuff_rr_new/dBuff_rr;
				if(bPrint == true) output_Value("β(対称行列のみ使用可能)を算出", dBeta );
			}
			else
			{			
				//******************************************
				//
				//		βを算出(式変形前の形)
				//
				//******************************************
				//変数宣言

				Double[] vecBuff_Ar_new = new Double[nSize]; 
				//初期化
				for(int jc=0; jc<nSize; jc++){vecBuff_Ar_new[jc] = 0.0;}
				//A*r_newを計算
				nReturn = calculate_Matrix_Times_Array(nSize, Amatrix, false, vec_r, vecBuff_Ar_new);
				//p*A*r_newを計算
				dBuff_pAr_new = calc_inner_product(vec_p, vecBuff_Ar_new);
				//ゼロ割チェック
				if(Math.abs(dBuff_rr) < DEF_EQUAL_ZERO)
				{
					//エラー出力
					nReturn = output_Value("反復回数", ic+1);
					nReturn = output_Value("エラー：ゼロ割発生：dBuff_rr", dBuff_rr);
					return 1;
				}
				//βを算出
				dBeta = -dBuff_pAr_new/dBuff_pAp;
				if(bPrint == true)	output_Value("β(非対称行列でも使用可能)を算出", dBeta );
			}


			//********************
			//
			//		pを更新
			//
			//********************
			//変数宣言
			for(int jc=0; jc<nSize; jc++)
			{
				vec_p[jc] = vec_r[jc] + dBeta*vec_p[jc];
			}
			if(bPrint == true)	output_Array("pベクトルを更新", vec_p, false);
		}


		///// 収束したxを格納 /////
		copy_vector(3, vec_x, Xvector);


		return 0;
	}
	public void c(){}
	//*===================================================
	//*
	//*       Jacob method
	//*
	//	          |a00, ･･･ a0p, ･･･ a0q, ･･･ a0n|
	//	          |a10, ･･･ a1p, ･･･ a1q, ･･･ a1n|
	//	          | ･                ･                ･                ･   |
	//	          | ･                ･                ･                ･   |
	//	          | ･                ･                ･                ･   |
	//	          |ap0, ･･･ app, ･･･ apq, ･･･ apn|
	//	対称行列       | ･                ･   ・            ･                ･  |
	//	Amatrix = | ･                ･       ･        ･                ･  |
	//	          | ･                ･           ・    ･                ･  |
	//	          |aq0, ･･･ aqp, ･･･ aqa, ･･･ aqn|
	//	          | ･                ･                ･                ･   |
	//	          | ･                ･                ･                ･   |
	//	          | ･                ･                ･                ･   |
	//	          |an0, ･･･ anp, ･･･ anq, ･･･ ann|
	//	
	//	              p列      q列
	//	          | 1, ･････ 0, ･････ 0, ･････0 |
	//	          | 0,1 ････  0, ･････ 0, ･････0 |
	//	          | ･       1      ･               ･             0 |
	//	          | ･           1  ･               ･             0 |
	//	          | ･               1               ･             0 |
	//	       p行| 0, ････ cosθ ･･ -sinθ ･･ 0 |
	//	          | ･               ･    1         ･             0 |
	//	R(θ,p,q) =| ･               ･        1     ･             0 |
	//	          | ･               ･            1 ･             0 |
	//	       q行| 0, ･････sinθ ･･ cosθ ･･･ 0 |
	//	          | ･               ･               ･   1    0 |
	//	          | ･               ･               ･      1  0 |
	//	          | ･               ･               ･          1 0 |
	//	          | 0, ･････ 0, ･････ 0, ･････ 0 |
	//	
	//	相似変換を行う。
	//	A' = R^T(θ,p,q) * A *  R(θ,p,q)
	//	
	//	一度の相似変換により、各成分は以下のようになる。
	//	a'pj =  apj*cosθ + aqj*sinθ (j≠p,q)
	//	a'qj = -apj*sinθ + aqj*cosθ (同上)
	//	a'ip =  aip*cosθ + aiq*sinθ (i≠p,q)
	//	a'iq = -aip*sinθ + aiq*cosθ (同上)
	//	a'pp = app*(cosθ)^2 + aqq(sinθ)^2 + 2apq*sinθ*cosθ
	//	a'qq = app*(sinθ)^2 + aqq(cosθ)^2 - 2apq*sinθ*cosθ
	//	a'pq = apq((cosθ)^2 - (sinθ)^2) + (aqq - app)sinθcosθ
	//	
	//	ここで、Jacobi法ではa'pq成分が0になるようなconθ、sinθを選ぶ。その時の条件は以下。
	//	tan2θ = 2apq/(app-aqq)
	//	
	//	ここからcosθ、sinθを算出すると、
	//	cosθ = sqrt(0.5*(1 + 1/sqrt(1 + (tan2θ)^2)))
	//	sinθ = sqrt(1-(cosθ)^2)
	//	
	//	この相似変換を、非対角項全てが0になるまで続ける。
	//	ここで、|apq|が最大のものを選ばないと必ずしも0に近づかないので注意！
	//*===================================================
	public static int calc_EigenValue_Jacob_method(int nSize, Double[] Amatrix)
	{
		//最大apqを探索
		double dPivot;
		double dPivMx;
		int nP;
		int nQ;
		int nCount = 0;
		
		//apqが全て0になるまでループ
		for( ;;nCount++)
		{
			dPivMx = 0.0;
			nP = 0;
			nQ = 1;
			for(int ic=0; ic<nSize; ic++)
			{
				for(int jc=ic+1; jc<nSize; jc++)
				{
					//apqの絶対値を取得
					dPivot = Math.abs(Amatrix[mxIndex(nSize, ic, jc)]);
					if(dPivot > dPivMx)
					{
						dPivMx = dPivot;
						nP = ic;
						nQ = jc;
					}
				}
			}
			
			//ピボット最大値が0のときは計算終了
			if(dPivMx < DEF_EQUAL_ZERO)
			{
				//計算終了
				break;
			}
			
			//回転行列による相似変換
			double dTan2Thete = 2.0 * Amatrix[mxIndex(nSize, nP, nQ)]/(Amatrix[mxIndex(nSize, nP, nP)] - Amatrix[mxIndex(nSize, nQ, nQ)]);
			double dCos = Math.sqrt(0.5*(1 + 1.0/Math.sqrt(1 + dTan2Thete*dTan2Thete)));
			double dSin = Math.sqrt(1-dCos*dCos);
			
			//一度の相似変換により、各成分は以下のようになる。
			//a'pj =  apj*cosθ + aqj*sinθ (j≠p,q)
			for(int jc=0; jc<nSize; jc++)
			{
				if(jc == nP || jc == nQ)	continue;
				Amatrix[mxIndex(nSize, nP, jc)] =  Amatrix[mxIndex(nSize, nP, jc)]*dCos + Amatrix[mxIndex(nSize, nQ, jc)]*dSin;
			}
			
			//a'qj = -apj*sinθ + aqj*cosθ (同上)
			for(int jc=0; jc<nSize; jc++)
			{
				if(jc == nP || jc == nQ)	continue;
				Amatrix[mxIndex(nSize, nQ, jc)] = -Amatrix[mxIndex(nSize, nP, jc)]*dSin + Amatrix[mxIndex(nSize, nQ, jc)]*dCos;
			}
			
			//a'ip =  aip*cosθ + aiq*sinθ (i≠p,q)
			for(int ic=0; ic<nSize; ic++)
			{
				if(ic == nP || ic == nQ)	continue;
				Amatrix[mxIndex(nSize, ic, nP)] =  Amatrix[mxIndex(nSize, ic, nP)]*dCos + Amatrix[mxIndex(nSize, ic, nQ)]*dSin;
			}
	
			//a'iq = -aip*sinθ + aiq*cosθ (同上)
			for(int ic=0; ic<nSize; ic++)
			{
				if(ic == nP || ic == nQ)	continue;
				Amatrix[mxIndex(nSize, ic, nP)] =  Amatrix[mxIndex(nSize, ic, nP)]*dCos + Amatrix[mxIndex(nSize, ic, nQ)]*dSin;
			}
			
			//a'pp = app*(cosθ)^2 + aqq(sinθ)^2 + 2apq*sinθ*cosθ
			Amatrix[mxIndex(nSize, nP, nP)] =  Amatrix[mxIndex(nSize, nP, nP)]*dCos*dCos + Amatrix[mxIndex(nSize, nQ, nQ)]*dSin*dSin + 2.0*Amatrix[mxIndex(nSize, nP, nQ)]*dSin*dCos;
			
			//a'qq = app*(sinθ)^2 + aqq(cosθ)^2 - 2apq*sinθ*cosθ
			Amatrix[mxIndex(nSize, nQ, nQ)] =  Amatrix[mxIndex(nSize, nP, nP)]*dSin*dSin + Amatrix[mxIndex(nSize, nQ, nQ)]*dCos*dCos + 2.0*Amatrix[mxIndex(nSize, nP, nQ)]*dSin*dCos;
	
			//a'pq = apq((cosθ)^2 - (sinθ)^2) + (aqq - app)sinθcosθ
			Amatrix[mxIndex(nSize, nP, nQ)] = 0.0;
			Amatrix[mxIndex(nSize, nQ, nP)] = 0.0;
		}
		
		System.out.println(String.format("apqの選択回数 %d", nCount));
		
		return 0;
	}
	//*===================================================
	//*
	//*       Householder QR method
	//*
	//*===================================================
	public static int QR_method(
		int nSize,
		Double[] Amatrix,
		Double[] eigenvalueOutput,
		Double[] eigenmatrixOutput
	)
	{
		int nReturn = 0;
		
		//変数宣言
		double dTolerance = 5.0e-10;
		int n_matrix = nSize;
		int nMxSize = n_matrix*n_matrix;

		Double matrix[];// = new Double[nMxSize];
		Double Q_matrix[] = new Double[nMxSize];
		Double R_matrix[]  = new Double[nMxSize];
		Double Q_times_matrix[] = new Double[nMxSize];


		matrix = Amatrix;
		

		//変数宣言
		Double eigenmatrix[] = new Double[nMxSize];
		Double eigenvalue[]  = new Double[n_matrix];

		//単位行列に初期化
		for(int ic=0; ic<n_matrix; ic++)
		{
			for(int jc=0; jc<n_matrix; jc++)
			{
				eigenmatrix[mxIndex(n_matrix, ic, jc)] = 0.0;
			}
			eigenmatrix[mxIndex(n_matrix, ic, ic)] = 1.0;
		}

		//////////////////// QR法の実行 ////////////////////
		//変数宣言
		int count=0;
		Boolean bConvergenceFlag = false;
		//収束するまでループ
		for(; bConvergenceFlag == false; count++)
		{
			////////// 対角要素を取得 //////////
			for(int ic=0; ic<n_matrix; ic++)
			{
				eigenvalue[ic] = matrix[mxIndex(n_matrix, ic, ic)];
			}
			
			////////// 行列の取得 //////////
			copy_vector(eigenmatrix.length, eigenmatrix, Q_times_matrix);

			////////// QR分解（直交行列と上三角行列に分解） //////////
			nReturn = QR_separation(n_matrix, matrix, Q_matrix, R_matrix);

			////////// QR ⇒ RQ の計算//////////
			Mathmatic.calculate_Matrix_Times_Matrix(nSize, R_matrix, false, Q_matrix, false, matrix);

			////////// Q0 * Q1 * ・・・Qi の計算//////////
			double summax = 0.0;
			for(int ic=0; ic<n_matrix; ic++)
			{
				for(int jc=0; jc<n_matrix; jc++)
				{
					//変数宣言
					double sum = 0.0;
					// Q0 * Q1 * ・・・Qiの算出
					for(int lc=0; lc<n_matrix; lc++)
					{
						sum += Q_times_matrix[mxIndex(n_matrix, ic, lc)] * Q_matrix[mxIndex(n_matrix, lc, jc)];
					}
					// 行列要素内の最大値を取得
					if(sum > summax)
					{
						summax = sum;
					}
					eigenmatrix[mxIndex(n_matrix, ic, jc)] = sum;
				}
			}

			//ゼロ割判定
			if(summax < DEF_EQUAL_ZERO)
			{
				//ゼロ割になるため、エラー
				return -1;
			}

			////////// 行列要素内の最大値で除算し、値を1.0以下に変更//////////
			int length = eigenmatrix.length;
			for(int ic=0; ic<length; ic++)
			{
				eigenmatrix[ic] /= summax;
			}

			////////// 収束判定 //////////
			for(int ic=0; ic<n_matrix; ic++)
			{
				bConvergenceFlag = true;
				if(Math.abs((matrix[mxIndex(n_matrix, ic, ic)]-eigenvalue[ic])/matrix[mxIndex(n_matrix, ic, ic)]) > dTolerance)
				{
					bConvergenceFlag = false;
					break;
				}
			}
		}

		//////////////////// 固有値、固有ベクトルを取得 ////////////////////
		//固有値の取得
		for(int ic=0; ic<n_matrix; ic++)
		{
			eigenvalue[ic] = matrix[mxIndex(n_matrix, ic, ic)];
		}
		//固有ベクトルの取得
		for(int jc=0; jc<n_matrix; jc++)
		{
			//変数宣言
			double sum = 0.0;
			//規格化のため、2乗和を取得
			for(int ic=0; ic<n_matrix; ic++)
			{
				sum = sum + eigenmatrix[mxIndex(n_matrix, ic, jc)] * eigenmatrix[mxIndex(n_matrix, ic, jc)];
			}

			//規格化された固有ベクトルの取得
			sum = Math.sqrt(sum);
			for(int ic=0; ic<n_matrix; ic++)
			{
				eigenmatrix[mxIndex(n_matrix, ic, jc)] = eigenmatrix[mxIndex(n_matrix, ic, jc)] / sum;
			}

		}

		////////// 出力 //////////
		copy_vector(eigenmatrix.length, eigenmatrix, eigenmatrixOutput);
		copy_vector(eigenvalue.length, eigenvalue, eigenvalueOutput);

		return 0;
	}
	///////////////////////////////////////////////////////
	//
	//	固有値マトリックスの原点移動・減次
	//
	//	精度の悪い固有値λ'があったとき、行列を以下のように書き直す
	//	A' = A-λ'I
	//	そしてこのA'について、QR法で計算すると、Hessenberg行列は以下のようになる
	//	|*, *,  *, * | 
	//	|*, *,  *, * |
	//	|0, *,  *, * |
	//	|0, 0, dA, B |
	//	ここで、dAは非常に0に近づいていき、そしてBは正しい固有値λに近づく。
	//	完全に近づいたら、行列を小さくする。
	//
	//	|*,  *, * | 
	//	|*,  *, * |
	//	|0, dA, B |
	//	また推定値の固有値を出して、繰り返していく。
	///////////////////////////////////////////////////////
	public static int eigen_down_matrix(int Size, Double[] matrix, Double[] unitVec, Double[] reflect_matrix)
	{
		
		return 0;
	}
	///////////////////////////////////////////////////////
	//
	//	HouseHolder method
	//
	//	鏡面の法線ベクトルu
	//	その時、鏡映変換を表す行列Hは以下
	//	H = I-2uu^T
	//
	//	QR法に利用するとき。
	//	以下の行列をHessenberg行列にすることを考える
	//	|*, *, *, * |
	//	|x, *, *, * |
	//	|y, *, *, * |
	//	|z, *, *, * |
	//
	//	uを以下のようにする。
	//	a = (x-sqrt(x^2+y^2+z^2), y, z)^T としたとき、
	//	u = a/|a|
	//	このuから作ったH行列を作用させると、上の行列が以下のようになる
	//	|*,   *, *, * |
	//	||x|, *, *, * |
	//	|0,   *, *, * |
	//	|0,   *, *, * |
	//
	//	次は、以下の行列で考える
	//	|*, *, *, * |
	//	|*, *, *, * |
	//	|0, x, *, * |
	//	|0, y, *, * |
	//
	//	uを以下のようにする。
	//	a = (x-sqrt(x^2+y^2), y)^T としたとき、
	//	u = a/|a|
	//
	//	このuから作ったH行列を作用させると、上の行列が以下のようになる
	//	|*,  *,  *, * |
	//	|*,  *,  *, * |
	//	|0, |x|, *, * |
	//	|0,  0,  *, * |
	//	これを繰り返す
	//
	//	ただしここまでだとHessemberg行列どまり。
	//	ここから対角項以下を消すには、Jacobi法による平面回転が必要。
	///////////////////////////////////////////////////////
	public static int HouseHolder_method(int Size, Double[] matrix, Double[] unitVec, Double[] reflect_matrix)
	{
		Double[] Hmatrix = new Double[Size*Size];
		
		//法線ベクトルをユニット化
		Mathmatic.calc_vector_unit(unitVec);
		
		//鏡映変換を表す行列Hを算出
		for(int ic=0; ic<Size; ic++)
		{
			for(int jc=0; jc<Size; jc++)
			{
				Hmatrix[mxIndex(Size, ic, jc)] = -2.0 * unitVec[ic] * unitVec[jc];
				if(ic == jc)
				{
					Hmatrix[mxIndex(Size, ic, jc)] += 1.0;
				}
			}
		}
		
		//H*Mを算出
		Mathmatic.calculate_Matrix_Times_Matrix(Size, Hmatrix, false, matrix, false, reflect_matrix);
		
		return 0;
	}
	///////////////////////////////////////////////////////
	//
	//	QR separation
	//
	///////////////////////////////////////////////////////
	public static int QR_separation(int Size, Double[] matrix, Double[] Q_matrix, Double[] R_matrix)
	{
		//初期化
		int n_matrix = Size;
		int nMxSize = n_matrix*n_matrix;

		//変数宣言
		Double[] qmatrix = new Double[nMxSize];
		Double[] Q_calmatrix = new Double[nMxSize];
		Double[] w1 = new Double[n_matrix];
		Double[] w2 = new Double[n_matrix];
		double s1,s2,ppp,c;

		////////// QR分解の実行 //////////
		//Q行列の初期化(単位行列)
		for(int ic=0; ic<n_matrix; ic++)
		{
			for(int jc=0; jc<n_matrix; jc++)
			{
				Q_matrix[mxIndex(n_matrix, ic, jc)] = 0.0;
			}
			Q_matrix[mxIndex(n_matrix, ic, ic)] = 1.0;
		}

		////////// HouseHolder変換によるQR分解の実行 //////////
		//行列の次元マイナス１回のループ
		for(int kc=0; kc<n_matrix-1; kc++)
		{
			///// sとs^2を計算 /////
			s2 = 0.0;
			double dBuf;
			for(int ic=kc; ic<n_matrix; ic++)
			{
				dBuf = matrix[mxIndex(n_matrix, ic, kc)];
				s2 += dBuf * dBuf;
			}
			if(s2 == 0.0)
			{
				continue;
			}
			//符号の取得
			dBuf = matrix[mxIndex(n_matrix, kc, kc)];
			ppp = (dBuf/Math.abs(dBuf));
			//値の算出
			s1 = ppp*Math.sqrt(s2);


			///// wの計算 /////
			for(int ic=0; ic<n_matrix; ic++)
			{
				w1[ic] = 0.0;
				w2[ic] = 0.0;
			}

			w1[kc] = matrix[mxIndex(n_matrix, kc, kc)] + s1;
			for(int ic=kc+1; ic<n_matrix; ic++)
			{
				w1[ic] = matrix[mxIndex(n_matrix, ic, kc)];
			}

			c = 1.0/(s2 + s1*matrix[mxIndex(n_matrix, kc, kc)]);
			for(int ic=kc; ic<n_matrix; ic++)
			{
				w2[ic] = c * w1[ic];
			}

			///// Qの計算(前処理1/4) /////
			for(int ic=0; ic<n_matrix; ic++)
			{
				for(int jc=0; jc<n_matrix; jc++)
				{
					qmatrix[mxIndex(n_matrix, ic, jc)] = -1.0 * w1[ic] * w2[jc];
				}
			}

			///// Q行列に単位行列を加算(前処理2/4) /////
			for(int ic=0; ic<n_matrix; ic++)
			{
				qmatrix[mxIndex(n_matrix, ic, ic)] += 1.0;
			}

			///// Q行列の取得(前処理3/4)  /////
			copy_vector(Q_matrix.length, Q_matrix, Q_calmatrix);

			///// Q行列の算出 (Qi * Qi+1の計算) (前処理4/4)  /////
			Mathmatic.calculate_Matrix_Times_Matrix(Size, Q_calmatrix, false, qmatrix, false, Q_matrix);

			///// R行列の算出 (Q * Aの計算) (前処理1/1) /////
			Mathmatic.calculate_Matrix_Times_Matrix(Size, qmatrix, false, matrix, false, R_matrix);

			///// R行列の取得 /////　※1:Rは上三角行列　※2:対称のときは3重対角行列)
			copy_vector(R_matrix.length, R_matrix, matrix);
		}

		return 0;
	}

	public void d(){}
	/////*********************************************************
	/////
	/////	行列の出力
	/////
	/////*********************************************************
	public static int output_Matrix(String sTitle, Double[] vctMatrix, int nRow, int nColumn)
	{
		///// タイトルの出力 /////
		System.out.println(String.format("%s", sTitle));
		
		////////// マトリックスの出力 //////////
		for(int ic=0; ic<nRow; ic++)
		{
			for(int jc=0; jc<nColumn; jc++)
			{
				double dBuf = vctMatrix[mxIndex(nColumn, ic, jc)];
				System.out.print(String.format("%f ", dBuf));
			}
			System.out.println();
		}
		System.out.println();

		return 0;
	}
	/////*********************************************************
	/////
	/////	行列の出力
	/////
	/////*********************************************************
	public static int output_Matrix(String sTitle, String[] vctMatrix, int nRow, int nColumn)
	{
		///// タイトルの出力 /////
		System.out.println(String.format("%s", sTitle));
		
		////////// マトリックスの出力 //////////
		for(int ic=0; ic<nRow; ic++)
		{
			for(int jc=0; jc<nColumn; jc++)
			{
				String sBuf = vctMatrix[mxIndex(nColumn, ic, jc)];
				System.out.print(String.format("%s ", sBuf));
			}
			System.out.println();
		}
		System.out.println();

		return 0;
	}

	/////*********************************************************
	/////
	/////	配列の出力
	/////
	/////*********************************************************
	public static int output_Array(String sTitle, Double[] vctArray, Boolean bTrans)
	{
		///// タイトルの出力 /////
		System.out.println(String.format("%s", sTitle));
		
		int nArrySize = vctArray.length;

		////////// 配列の出力(横に出力) //////////
		if(bTrans == true)
		{
			for(int ic=0; ic<nArrySize; ic++)
			{
				System.out.print(String.format("%f ", vctArray[ic]));
			}
			System.out.println();
		}

		////////// 配列の出力(縦に出力) //////////
		else if(bTrans == false)
		{
			for(int ic=0; ic<nArrySize; ic++)
			{
				System.out.println(String.format("%f", vctArray[ic]));
			}
		}

		////////// その他 //////////
		else
		{
			//エラー
			return -1;
		}

		System.out.println();

		return 0;
	}
	/////*********************************************************
	/////
	/////	配列の出力
	/////
	/////*********************************************************
	public static int output_Array(String sTitle, String[] vctArray, Boolean bTrans)
	{
		///// タイトルの出力 /////
		System.out.println(String.format("%s", sTitle));
		
		int nArrySize = vctArray.length;

		////////// 配列の出力(横に出力) //////////
		if(bTrans == true)
		{
			for(int ic=0; ic<nArrySize; ic++)
			{
				System.out.print(String.format("%s ", vctArray[ic]));
			}
			System.out.println();
		}

		////////// 配列の出力(縦に出力) //////////
		else if(bTrans == false)
		{
			for(int ic=0; ic<nArrySize; ic++)
			{
				System.out.println(String.format("%s", vctArray[ic]));
			}
		}

		////////// その他 //////////
		else
		{
			//エラー
			return -1;
		}

		System.out.println();

		return 0;
	}
	/////*********************************************************
	/////
	/////	配列の出力
	/////
	/////*********************************************************
	public static int output_Array(String sTitle, int[] vctArray, Boolean bTrans)
	{
		///// タイトルの出力 /////
		System.out.println(String.format("%s", sTitle));
		
		int nArrySize = vctArray.length;

		////////// 配列の出力(横に出力) //////////
		if(bTrans == true)
		{
			for(int ic=0; ic<nArrySize; ic++)
			{
				System.out.print(String.format("%d ", vctArray[ic]));
			}
			System.out.println();
		}

		////////// 配列の出力(縦に出力) //////////
		else if(bTrans == false)
		{
			for(int ic=0; ic<nArrySize; ic++)
			{
				System.out.println(String.format("%d", vctArray[ic]));
			}
		}

		////////// その他 //////////
		else
		{
			//エラー
			return -1;
		}

		System.out.println();

		return 0;
	}

	/////*********************************************************
	/////
	/////	値の出力（倍精度）
	/////
	/////*********************************************************
	//////////画面に数値を出力する //////////
	public static int output_Value(String sTitle, double ... dValue)
	{
		///// タイトルの出力 /////
		System.out.println(String.format("%s", sTitle));

		///// 値の出力 /////
		for(double buf : dValue)
		{
			System.out.println(String.format("%f", buf));
		}

		return 0;
	}

	/////*********************************************************
	/////
	/////	値の出力（整数）
	/////
	/////*********************************************************
	public static int output_Value(String sTitle, int nValue)
	{
		///// タイトルの出力 /////
		System.out.println(String.format("%s", sTitle));

		///// 値の出力 /////
		System.out.println(String.format("%d", nValue));

		return 0;
	}








	public void e(){}
	//************************************************************************//
	/**
	*	点から点のVectorを算出し、ユニット化する。
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static void calc_vector_between_point_point_unit(double[] start_point, double[] end_point, double[] vector){
		
		Mathmatic.calc_vector_between_point_point(start_point, end_point, vector);
		Mathmatic.calc_vector_unit(vector, 3, vector);
	}
	//************************************************************************//
	/**
	*	点から点のVectorを算出する。
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static void calc_vector_between_point_point(
		double[] start_point,	//I		始点
		double[] end_point,		//I		終点
		double[] vector			//O		S->Eのベクトル
	)
	{
		for(int ic=0; ic<3; ic++){
			vector[ic] = end_point[ic] - start_point[ic];
		}
	}
	//************************************************************************//
	/**
	*	内積を計算
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static double calc_InnerProduct(
		int			nSize,
		double[]	vector1,
		double[]	vector2
	)
	{
		double dSum = 0.0;
		for(int ic=0; ic<nSize; ic++){
			dSum += vector1[ic] * vector2[ic];
		}
//		result = dSum;
		return dSum;
	}
	//************************************************************************//
	/**
	*	行列式を計算
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static double calc_matrix_determinant(
		int			n_size,
		Double[]	matrix
	)
	{
		Double[] matrix_copy = new Double[n_size*n_size];
		
		//行列をコピー
		copy_vector(
			n_size*n_size,
			matrix,
			matrix_copy
		);
		
		//LU分解
		int[] SwapList = new int[n_size];
		int[] swap_num = new int[1];
		LU_decomposition(
			n_size,
			matrix_copy,
			SwapList,
			swap_num
		);
		
		//対角項をすべて掛け算
		double gyouretsushiki = 1.0;
		for(int ic=0; ic<n_size; ic++)
		{
			gyouretsushiki *= matrix_copy[mxIndex(n_size, ic, ic)];
		}
		
		if(swap_num[0]%2 == 1)
		{
			return -1*gyouretsushiki;
		}
		
		return gyouretsushiki;
	}

	//************************************************************************//
	/**
	*	ベクトルRo->Rvと、点Pを通る垂直線の交点Qを計算
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static void calc_CrossPoint_vec_point(double[] dRo, double[] dRv, double[] dP, double[] dResultQ, double[] dRatio){
		
		double[] dPmRo  = {0.0, 0.0, 0.0};
		double[] dRvmRo = {0.0, 0.0, 0.0};
		
		for(int ic=0; ic<3; ic++){
			dPmRo[ic]  = dP[ic]  - dRo[ic];
			dRvmRo[ic] = dRv[ic] - dRo[ic];
		}

		///// 交点Qを算出 /////
		//係数tを算出
		double dBuf1 = Mathmatic.calc_InnerProduct(3, dPmRo, dRvmRo);
//		util.calc_InnerProduct(3, dPmRo, dRvmRo, dBuf1);
//		dBuf1
		double dBuf2 = Mathmatic.calc_InnerProduct(3, dRvmRo, dRvmRo);
//		util.calc_InnerProduct(3, dRvmRo, dRvmRo, dBuf2);
//		dBuf2
		double dt = dBuf1/dBuf2;

		//交点Qを算出
		for(int ic=0; ic<3; ic++){
			dResultQ[ic] = dt*dRvmRo[ic] + dRo[ic];
		}
		
		//係数を格納
		dRatio[0] = dt;
	}
	//************************************************************************//
	/**
	*	四元数、クォータニオンを使用して点を回転
	*
	*	@param	
	*	@return	dResultS 回転後の点
	*	@version
	*/
	//************************************************************************//
	public static void calc_rotation(
        double[] rot_axis_angle,    //I     4成分 ※回転軸の3成分と回転量[度]
        double[] point,             //I     回転前の点
        double[] result             //I     回転後の点
    )
    {
        double angle_radian_h = rot_axis_angle[3]*PI/180.0 * 0.5;
        
        //クォータニオン
        double cos_thete_h = Math.cos(angle_radian_h);
        double sin_thete_h = Math.sin(angle_radian_h);
        
        //クォータニオン
        double quotanion[] = {
            cos_thete_h,
            sin_thete_h*rot_axis_angle[0],
            sin_thete_h*rot_axis_angle[1],
            sin_thete_h*rot_axis_angle[2]
        };
        
        //クォータニオン共役
        double quotanionInv[] = {
            quotanion[0],
            -quotanion[1],
            -quotanion[2],
            -quotanion[3]
        };
        
        //点をクォータニオン表現
        double point_quotanion[] = {
            0.0,
            point[0],
            point[1],
            point[2]
        };
        
        //計算
        double buffer[] = new double[4];
        Mathmatic.calc_quotanion_times_quotanion(
            quotanion,
            point_quotanion,
            buffer
        );
        
        Mathmatic.calc_quotanion_times_quotanion(
            buffer,
            quotanionInv,
            buffer
        );
        
        result[0] = buffer[1];
        result[1] = buffer[2];
        result[2] = buffer[3];

    }
	//************************************************************************//
	/**
	*	四元数、クォータニオン、2つの回転軸、回転量を合成
	*
	*	@param	
	*	@return	dResultS 回転後の点
	*	@version
	*/
	//************************************************************************//
	public static void calc_rotation_unite(
        double[] rot_axis_thete1,	//I		回転軸と回転量（degree）     
        double[] rot_axis_thete2,	//I     
        double[] result				//O     
    )
    {

        //クォータニオン1
        double thete1_radian = PI*rot_axis_thete1[3]/180.0;
        double cos_thete_h1 = Math.cos(thete1_radian*0.5);
        double sin_thete_h1 = Math.sin(thete1_radian*0.5);
        double quotanion1[] = {
            cos_thete_h1,
            sin_thete_h1*rot_axis_thete1[0],
            sin_thete_h1*rot_axis_thete1[1],
            sin_thete_h1*rot_axis_thete1[2]
        };

        //クォータニオン2
        double thete2_radian = PI*rot_axis_thete2[3]/180.0;
        double cos_thete_h2 = Math.cos(thete2_radian*0.5);
        double sin_thete_h2 = Math.sin(thete2_radian*0.5);
        double quotanion2[] = {
            cos_thete_h2,
            sin_thete_h2*rot_axis_thete2[0],
            sin_thete_h2*rot_axis_thete2[1],
            sin_thete_h2*rot_axis_thete2[2]
        };        
        
        //合成
        double quot_result[] = new double[4]; 
        calc_quotanion_times_quotanion(
            quotanion1,    //I     
            quotanion2,    //I     
            quot_result    //O     
        );
        
        //-1 <= θ <= 1の範囲にあるかチェック
        if(quot_result[0] < -1.0)
        {
            quot_result[0] = -1.0;
        }
        else if(quot_result[0] > 1.0)
        {
            quot_result[0] = 1.0;
        }
        
        double thete = Math.acos(quot_result[0]) * 2.0*180.0/PI;
//        double sin_half_value = Math.sin(thete*0.5*PI/180.0);
        double sin_thete_h = Math.sin(PI/180.0*thete*0.5);
        
        double axis[] = {quot_result[1]/sin_thete_h, quot_result[2]/sin_thete_h, quot_result[3]/sin_thete_h};
//        double length = Mathmatic.calc_length_vector(3, axis);
//        //-1 <= θ <= 1の範囲にあるかチェック
//        if(length < -1.0)
//        {
//            length = -1.0;
//        }
//        else if(length > 1.0)
//        {
//            length = 1.0;
//        }        
        //テスト：sin側のθも取得
//        double thete2 = Math.asin(length) * 2.0*180/PI;
//        double thete = Math.asin(length) * 2.0*180/PI;
//        Mathmatic.calc_vector_unit(axis, 3, axis);
        
//        if(thete > 180)
//        {
//            axis[0] = -axis[0];
//            axis[1] = -axis[1];
//            axis[2] = -axis[2];
//        }
        
//        if(sin_half_value*length < 0.0)
//        {
//            System.out.println(String.format("●符号が逆転, %f   %f", sin_half_value, length));
//        }
//        else
//        {
//            System.out.println(String.format("●符号は同じ, %f   %f", sin_half_value, length));
//        }
        
//        double inner_product = Mathmatic.calc_InnerProduct(3, quot_result, axis);
        
        
        
        
//        System.out.println(String.format("角度, %f  %f", thete, thete2));
        
        
        result[0] = axis[0];
        result[1] = axis[1];
        result[2] = axis[2];
        result[3] = thete;
        
//        
//        //クォータニオン_check
//        double thete_radian_check = PI*result[3]/180.0;
//        double cos_thete_h_check = Math.cos(thete_radian_check*0.5);
//        double sin_thete_h_check = Math.sin(thete_radian_check*0.5);
//        double quotanion_check[] = {
//            cos_thete_h_check,
//            sin_thete_h_check*result[0],
//            sin_thete_h_check*result[1],
//            sin_thete_h_check*result[2]
//        };
//        
//        System.out.println(String.format("合成の回転軸1, %f, %f, %f  %f", quot_result[0], quot_result[1], quot_result[2], quot_result[3]));
//        System.out.println(String.format("合成の回転軸2, %f, %f, %f  %f", quotanion_check[0], quotanion_check[1], quotanion_check[2], quotanion_check[3]));

//                //クォータニオンtest
//        double thete9_radian = PI*result[3]/180.0;
//        double cos_thete_h9 = Math.cos(thete9_radian*0.5);
//        double sin_thete_h9 = Math.sin(thete9_radian*0.5);
//        double quotanion9[] = {
//            cos_thete_h9,
//            sin_thete_h9*result[0],
//            sin_thete_h9*result[1],
//            sin_thete_h9*result[2]
//        };
//        //テスト出力
//        System.out.println(String.format("合成の回転軸1, %f, %f, %f  %f", quot_result[0], quot_result[1], quot_result[2], quot_result[3]));
//        System.out.println(String.format("合成の回転軸2, %f, %f, %f  %f", quotanion9[0], quotanion9[1], quotanion9[2], quotanion9[3]));

    } 
	//************************************************************************//
	/**
	*	座標系を一致させるための1軸周り回転を算出<br>
	*
	*	@param		csys_begin,		//I		回転前の座標系。X軸の3成分、Y軸の3成分の計6成分。<br>
	*	@param		csys_end,		//I		回転後の座標系。X軸の3成分、Y軸の3成分の計6成分。<br>
	*	@return	rot_axis_thete	//O		回転軸と回転角度（degree）、4成分必要。<br>
	*	@version
	*/
	//************************************************************************//
	public static void calc_rotate_axis_for_matching_vector(
		double[]	vector_begin,	//I		回転前のベクトル。
		double[]	vector_end,		//I		回転後のベクトル。
		double[]	rot_axis_thete	//O		回転軸と回転角度（degree）、4成分必要。
	)
	{
		//////////////////////////////////////////
		//	ベクトルを合わせるための回転軸と回転量を算出
		//////////////////////////////////////////
		//回転軸を取得するため、外積を算出
		double[] buf_rot_axis_x = new double[3];
		Mathmatic.calc_OuterProduct_unit(vector_begin, vector_end, buf_rot_axis_x);
		//回転量を取得するため、角度を算出
		double buf_rot_angle_x;
		buf_rot_angle_x = Mathmatic.calc_angle_begween_vector_vector_degree(vector_begin, vector_end);
		//回転軸と回転量をまとめる
		System.arraycopy(buf_rot_axis_x, 0, rot_axis_thete, 0, 3);
		rot_axis_thete[3] = buf_rot_angle_x;
	}
	//************************************************************************//
	/**
	*	座標系を一致させるための1軸周り回転を算出<br>
	*
	*	@param		csys_begin,		//I		回転前の座標系。X軸の3成分、Y軸の3成分の計6成分。<br>
	*	@param		csys_end,		//I		回転後の座標系。X軸の3成分、Y軸の3成分の計6成分。<br>
	*	@return	rot_axis_thete	//O		回転軸と回転角度（degree）、4成分必要。<br>
	*	@version
	*/
	//************************************************************************//
	public static void calc_rotate_axis_for_matching_coordinate(
		double[]	csys_begin,		//I		回転前の座標系。X軸の3成分、Y軸の3成分の計6成分。
		double[]	csys_end,		//I		回転後の座標系。X軸の3成分、Y軸の3成分の計6成分。
		double[]	rot_axis_thete	//O		回転軸と回転角度（degree）、4成分必要。
	)
	{
		double[] buf_begin_x = new double[3];
		double[] buf_begin_y = new double[3];
		double[] buf_end_x = new double[3];
		double[] buf_end_y = new double[3];
		
		for(int nc=0; nc<3; nc++)
		{
			buf_begin_x[nc]	= csys_begin[nc];
			buf_begin_y[nc]	= csys_begin[nc+3];
			buf_end_x[nc]	= csys_end[nc];
			buf_end_y[nc]	= csys_end[nc+3];			
		}
		//////////////////////////////////////////
		//	X軸を合わせるための回転軸と回転量を算出
		//////////////////////////////////////////
		//回転軸を取得するため、外積を算出
		double[] buf_rot_axis_x = new double[3];
		Mathmatic.calc_OuterProduct_unit(buf_begin_x, buf_end_x, buf_rot_axis_x);
		//回転量を取得するため、角度を算出
		double buf_rot_angle_x;
		buf_rot_angle_x = Mathmatic.calc_angle_begween_vector_vector_degree(buf_begin_x, buf_end_x);
		//回転軸と回転量をまとめる
		double[] buf_rot_axis_angle_x = {
			buf_rot_axis_x[0],
			buf_rot_axis_x[1],
			buf_rot_axis_x[2],
			buf_rot_angle_x
		};
		
		//////////////////////////////////////////
		//	上記の回転をbegin座標系のY軸に適用。
		//////////////////////////////////////////
		double[] buf_begin_y_new = new double[3];
		Mathmatic.calc_rotation(buf_rot_axis_angle_x, buf_begin_y, buf_begin_y_new);
		
		//////////////////////////////////////////
		//	Y軸を合わせるための回転量を算出
		//	回転軸は、end座標系X軸に固定。
		//////////////////////////////////////////
		//回転軸の右回り、左回りを判定するため、外積を算出
		double[] buf_rot_axis_y = new double[3];
		Mathmatic.calc_OuterProduct_unit(buf_begin_y_new, buf_end_y, buf_rot_axis_y);
		//内積をとり、＋なら右ねじ、－なら左ねじ回転。
		double buf_sign;
		double buf_inner = Mathmatic.calc_inner_product(3, buf_end_x, buf_rot_axis_y);
		if(buf_inner > 0.0)
		{
			buf_sign = 1.0;
		}
		else
		{
			buf_sign = -1.0;
		}
		
		//回転量を取得するため、角度を算出
		double buf_rot_angle_y;
		buf_rot_angle_y = Mathmatic.calc_angle_begween_vector_vector_degree(buf_begin_y_new, buf_end_y);
		//回転軸と回転量をまとめる
		double[] buf_rot_axis_angle_y = {
			buf_end_x[0],
			buf_end_x[1],
			buf_end_x[2],
			buf_sign * buf_rot_angle_y
		};
		
		//////////////////////////////////////////
		//	回転を合成（クォータニオンを使用）
		//////////////////////////////////////////
		Mathmatic.calc_rotation_unite(buf_rot_axis_angle_x, buf_rot_axis_angle_y, rot_axis_thete);
		
//		return;
	}
	//************************************************************************//
	/**
	*	四元数、クォータニオンの掛け算
	*
	*	@param	
	*	@return	dResultS 回転後の点
	*	@version
	*/
	//************************************************************************//
	public static void calc_quotanion_times_quotanion(
        double[] q1,        //I     
        double[] q2,        //I     
        double[] result     //O     
    )
    {
        double res_buf[] = new double[4];
        res_buf[0] = q1[0]*q2[0] - q1[1]*q2[1] - q1[2]*q2[2] - q1[3]*q2[3];
        res_buf[1] = q1[0]*q2[1] + q1[1]*q2[0] + q1[2]*q2[3] - q1[3]*q2[2];
        res_buf[2] = q1[0]*q2[2] - q1[1]*q2[3] + q1[2]*q2[0] + q1[3]*q2[1];
        res_buf[3] = q1[0]*q2[3] + q1[1]*q2[2] - q1[2]*q2[1] + q1[3]*q2[0];
        
        Mathmatic.copy_vector(4, res_buf, result);
    } 
	//************************************************************************//
	/**
	*	2つのベクトルの内角（radian）を算出
	*
	*	@return		内角（radian）
	*	@version
	*/
	//************************************************************************//
	public static double calc_angle_begween_vector_vector(
        double[] vector_a,  //I     ベクトルA
        double[] vector_b  //I     ベクトルB
    )
	{
            double distance_a = Mathmatic.calc_length_between_point_point(vector_a, vector_b);
            double distance_b = Mathmatic.calc_length_vector(3, vector_a);
            double distance_c = Mathmatic.calc_length_vector(3, vector_b);
            double cos_thete  = (-distance_a*distance_a + distance_b*distance_b + distance_c*distance_c)/2.0/distance_b/distance_c;
            if(cos_thete >1.0)
            {
                cos_thete = 1.0;
            }
            else if(cos_thete < -1.0)
            {
                cos_thete = -1.0;
            }
            return Math.acos(cos_thete);
    }
	//************************************************************************//
	/**
	*	2つのベクトルの内角（degree）を算出
	*
	*	@return		内角（degree）
	*	@version
	*/
	//************************************************************************//
	public static double calc_angle_begween_vector_vector_degree(
        double[] vector_a,  //I     ベクトルA
        double[] vector_b  //I     ベクトルB
    )
	{
            double buf = calc_angle_begween_vector_vector(vector_a, vector_b);
			return buf*180/Math.PI;
    }
	//************************************************************************//
	/**
	*	ベクトルRo->Rvを回転軸として、点Pを右ねじの方向にThete度(degree)回転させる。
	*	※追記
	*	精度が悪い。細かい回転で無視できないレベルの誤差が生まれる。
	*
	*	@param	dRo　回転軸の始点（始点->終点ベクトルの右ネジ周りに回転）
	*	@param	dRv　回転軸の終点
	*	@param	dP　 回転前の点
	*	@param	dThete　回転角度（degree）
	*	@return	dResultS 回転後の点
	*	@version
	*/
	//************************************************************************//
	@Deprecated
	public static void calc_rotate_around_axis(
        double[]	dRo,		//I     回転軸の始点（始点->終点ベクトルの右ネジ周りに回転）
        double[]	dRv,		//I     回転軸の終点
        double[]	dP,			//I     回転前の点
        double		dThete,		//I     回転角度（degree）
        double[]	dResultS	//O     回転後の点
        )
	{

		//ベクトルRo->Rvと、点Pを通る垂直線の交点Qを算出
		double[] dQ = {0.0, 0.0, 0.0};
		double[] dt = {0.0};
		Mathmatic.calc_CrossPoint_vec_point(dRo, dRv, dP, dQ, dt);

		//ベクトルQ->Rvと、ベクトルQ->Pの外積ベクトルVを求める。
		double[] dV = {0.0, 0.0, 0.0};
		double[] dRvmQ = {0.0, 0.0, 0.0};
		double[] dPmQ  = {0.0, 0.0, 0.0};
		for(int ic=0; ic<3; ic++){
			dRvmQ[ic] = dRv[ic] - dQ[ic];
			dPmQ[ic]  = dP[ic]  - dQ[ic];
		}
		Mathmatic.calc_OuterProduct_unit(dRvmQ, dPmQ, dV);

		//この段階で、dRvmQがx軸、dPmQがy軸、dVがz軸と考える。
		//y軸上に注目する。dPmQはy軸なので、点dPmQは{0, |dPmQ|, 0}となる。
		//この点dPmQを、x軸周りにthete度回転させた点Sを算出する。
		double dLengPmQ = calc_length_vector(3, dPmQ);
		double dRad = dThete*Math.PI/180.0;
		double[] dS = {0.0, dLengPmQ*Math.cos(dRad), dLengPmQ*Math.sin(dRad)};

		//dRvmQとdPmQをユニット化する。
		Mathmatic.calc_vector_unit(dRvmQ, 3, dRvmQ);
		Mathmatic.calc_vector_unit(dPmQ, 3, dPmQ);
		
		//元の座標系に変換する。
		double[] matrix =  {0.0, 0.0, 0.0, 
							0.0, 0.0, 0.0, 
							0.0, 0.0, 0.0};
		for(int ic=0; ic<3; ic++){
			matrix[ic*3+0] = dRvmQ[ic];
			matrix[ic*3+1] = dPmQ[ic];
			matrix[ic*3+2] = dV[ic];
		}
		Mathmatic.calc_multi_matrix_vector(3, matrix, dS, dResultS);
		
		//最後にQを足す
		for(int ic=0; ic<3; ic++){
			dResultS[ic] += dQ[ic];
		}
	}
	//************************************************************************//
	/**
	*	クォータニオンを使用した回転。
	*	ベクトルRo->Rvを回転軸として、点Pを右ねじの方向にThete度(degree)回転させる。
	*
	*	@param	dRo　回転軸の始点（始点->終点ベクトルの右ネジ周りに回転）
	*	@param	dRv　回転軸の終点
	*	@param	dP　 回転前の点
	*	@param	dThete　回転角度（degree）
	*	@return	dResultS 回転後の点
	*	@version
	*/
	//************************************************************************//
	public static void calc_rotate_around_axis2(
        double[]	dRo,		//I     回転軸の始点（始点->終点ベクトルの右ネジ周りに回転）
        double[]	dRv,		//I     回転軸の終点
        double[]	dP,			//I     回転前の点
        double		dThete,		//I     回転角度（degree）
        double[]	dResultS	//O     回転後の点
        )
	{
		//回転軸と回転量のベクトルを作成
		double[] rot_axis_angle = {dRv[0]-dRo[0], dRv[1]-dRo[1], dRv[2]-dRo[2]};

		calc_rotate_around_axis2_2(
			dRo,			//I     回転中心
			rot_axis_angle,	//I     回転軸のベクトル
			dP,				//I     回転前の点
			dThete,			//I     回転角度（degree）
			dResultS		//O     回転後の点
        );
	}
	//************************************************************************//
	/**
	*	クォータニオンを使用した回転。
	*	ベクトルRvを回転軸、dRoを回転中心として、点Pを右ねじの方向にThete度(degree)回転させる。
	*
	*	@param	dRo　回転中心
	*	@param	dRv　回転軸のベクトル
	*	@param	dP　 回転前の点
	*	@param	dThete　回転角度（degree）
	*	@return	dResultS 回転後の点
	*	@version
	*/
	//************************************************************************//
	public static void calc_rotate_around_axis2_2(
        double[]	dRo,		//I     回転中心
        double[]	dRv,		//I     回転軸のベクトル
        double[]	dP,			//I     回転前の点
        double		dThete,		//I     回転角度（degree）
        double[]	dResultS	//O     回転後の点
        )
	{
		//回転軸と回転量のベクトルを作成
		double[] rot_axis_angle = {dRv[0], dRv[1], dRv[2], dThete};
		
		//回転前の点を原点移動する
		double[] point_before = {dP[0]-dRo[0], dP[1]-dRo[1], dP[2]-dRo[2]};
		
		//回転をクォータニオンで計算
		double[] buf_point_after = new double[3];
		calc_rotation(
			rot_axis_angle,    //I     4成分 ※回転軸の3成分と回転量[度]
			point_before,      //I     回転前の点
			buf_point_after    //I     回転後の点
		);
		
		//原点をもとに戻す
		dResultS[0] = buf_point_after[0] + dRo[0];
		dResultS[1] = buf_point_after[1] + dRo[1];
		dResultS[2] = buf_point_after[2] + dRo[2];
	}
	//************************************************************************//
	/**
	 *  複素数。
	 *  2017/04/01 しかしどうやっていいのかわからない。今はただの飾り。
	 * 
	*	クォータニオンを使用した回転。
	*	ベクトルRvを回転軸、dRoを回転中心として、点Pを右ねじの方向にThete度(degree)回転させる。
	*
	*	@param	dRo　回転中心
	*	@param	dRv　回転軸のベクトル
	*	@param	dP　 回転前の点
	*	@param	dThete　回転角度（degree）
	*	@return	dResultS 回転後の点
	*	@version
	*/
	//************************************************************************//
	public static void calc_rotate_around_axis2_2_complex(
        double[][]	dRo,		//I     回転中心
        double[][]	dRv,		//I     回転軸のベクトル
        double[][]	dP,			//I     回転前の点
        double		dThete,		//I     回転角度（degree）
        double[][]	dResultS	//O     回転後の点
        )
	{
		//回転軸と回転量のベクトルを作成
		double[] rot_axis_angle = {dRv[0][0], dRv[1][0], dRv[2][0], dThete};
		
		//回転前の点を原点移動する
		double[] point_before = {dP[0][0]-dRo[0][0], dP[1][0]-dRo[1][0], dP[2][0]-dRo[2][0]};
		
		//回転をクォータニオンで計算
		double[] buf_point_after = new double[3];
		calc_rotation(
			rot_axis_angle,    //I     4成分 ※回転軸の3成分と回転量[度]
			point_before,      //I     回転前の点
			buf_point_after    //I     回転後の点
		);
		
		//原点をもとに戻す
		dResultS[0][0] = buf_point_after[0] + dRo[0][0];
		dResultS[1][0] = buf_point_after[1] + dRo[1][0];
		dResultS[2][0] = buf_point_after[2] + dRo[2][0];
	}
	//************************************************************************//
	/**
	*	外積を算出し、ユニット化　
	*
	*	@param
	*	@return	
	*	@version
	*/
	//************************************************************************//	
	public static void calc_OuterProduct_unit(
        ArrayList<Double>Axis_1st,
        ArrayList<Double>Axis_2nd,
        ArrayList<Double> Outer_Product
    )
    {
		///// 変数 /////
		ArrayList<Double> Buff_OP = new ArrayList<Double>();
	
		///// 外積を計算 /////
		Buff_OP.add(Axis_1st.get(1)*Axis_2nd.get(2) - Axis_1st.get(2)*Axis_2nd.get(1));
		Buff_OP.add(Axis_1st.get(2)*Axis_2nd.get(0) - Axis_1st.get(0)*Axis_2nd.get(2));
		Buff_OP.add(Axis_1st.get(0)*Axis_2nd.get(1) - Axis_1st.get(1)*Axis_2nd.get(0));
		
		/////ユニット化 /////
		calc_vector_unit(Buff_OP);
		
		///// 格納 /////
		for(int ic=0; ic<3; ic++){
			Outer_Product.add(Buff_OP.get(ic));
		}
		
//		///// 画面に出力 /////
//		System.out.printf("外積: %f,  %f,  %f\n",Outer_Product.get(0), Outer_Product.get(1), Outer_Product.get(2));
	}

	//************************************************************************//
	/**
	*	外積を算出し、ユニット化　
	*	点が時計回りに並んでいるときは上方向。（イメージ的には右ネジの法則）
	*
	*	@param
	*	@return	
	*	@version
	*/
	//************************************************************************//	
	public static void calc_OuterProduct_unit(
		double[] point_1st, 
		double[] point_2nd, 
		double[] point_3rd, 
		double[] Outer_Product
	)
	{
		//1軸目
		double[] Axis_1st = {
			point_2nd[0] - point_1st[0],
			point_2nd[1] - point_1st[1],
			point_2nd[2] - point_1st[2],
		};
		//2軸目
		double[] Axis_2nd = {
			point_3rd[0] - point_2nd[0],
			point_3rd[1] - point_2nd[1],
			point_3rd[2] - point_2nd[2],
		};
		
		//外積を計算
		Mathmatic.calc_OuterProduct_unit(
			Axis_1st, 
			Axis_2nd, 
			Outer_Product
		);		
	}
	//************************************************************************//
	/**
	*	外積を算出し、ユニット化　
	*
	*	@param
	*	@return	
	*	@version
	*/
	//************************************************************************//	
	public static void calc_OuterProduct_unit(
		double[] Axis_1st, 
		double[] Axis_2nd, 
		double[] Outer_Product
	)
	{
		double[] dOuterBuf = {0.0, 0.0, 0.0};
		
		///// 外積を計算 /////
		dOuterBuf[0] = Axis_1st[1]*Axis_2nd[2] - Axis_1st[2]*Axis_2nd[1];
		dOuterBuf[1] = Axis_1st[2]*Axis_2nd[0] - Axis_1st[0]*Axis_2nd[2];
		dOuterBuf[2] = Axis_1st[0]*Axis_2nd[1] - Axis_1st[1]*Axis_2nd[0];
		
		/////ユニット化 /////
		calc_vector_unit(dOuterBuf, 3, dOuterBuf);
		
		///// 格納 /////
		Outer_Product[0] = dOuterBuf[0];
		Outer_Product[1] = dOuterBuf[1];
		Outer_Product[2] = dOuterBuf[2];
	}

	//************************************************************************//
	/**
	*	ベクトルをユニット化　
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//	
	public static void calc_vector_unit(ArrayList<Double>Axis)
    {
		///// 2乗和を算出 /////
		double dSum2 = 0.0;
		for (Double Axi : Axis) {
			dSum2 += Axi * Axi;
		}
		
		///// ゼロ割を検出 /////
		if(dSum2 == 0.0){
			return;
		}
		
		///// ルート /////
		double length = Math.sqrt(dSum2);
		
		///// ユニット化 /////
		for(int ic=0; ic<Axis.size(); ic++)
		{
			Axis.set(ic, Axis.get(ic)/length);
		}
	}
	//************************************************************************//
	/**
	*	ベクトルをユニット化　
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//	
	public static void calc_vector_unit(Double[] Axis){
		
		///// 2乗和を算出 /////
		double dSum2 = 0.0;
		for (Double Axi : Axis) {
			dSum2 += Axi * Axi;
		}
		
		///// ゼロ割を検出 /////
		if(dSum2 == 0.0){
			return;
		}
		
		///// ルート /////
		double length = Math.sqrt(dSum2);
		
		///// ユニット化 /////
		for(int ic=0; ic<Axis.length; ic++)
		{
			Axis[ic] /= length;
		}
	}

	//************************************************************************//
	/**
	*	ベクトルをユニット化　
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//	
	public static void calc_vector_unit(double[] vector_in, int nSize, double[] vector_out)
    {
		
		///// 2乗和を算出 /////
		double dSum2 = 0.0;
		for(int ic=0; ic<nSize; ic++)
		{
			dSum2 += vector_in[ic]*vector_in[ic];
		}
		
		///// ゼロ割を検出 /////
		if(dSum2 == 0.0){
			return;
		}
		
		///// ルート /////
		double dRevLength = 1.0/Math.sqrt(dSum2);
		
		///// ユニット化 /////		
		for(int ic=0; ic<nSize; ic++)
		{
			vector_out[ic] = vector_in[ic]*dRevLength;
		}
	}
	//************************************************************************//
	/**
	*	ベクトルをユニット化　複素数
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//	
	public static void calc_vector_unit_complex(
		double[][] vector_in,
		int nSize,
		double[][] vector_out
	)
    {		
		///// 長さを算出 /////
		///// (a+bi)(a-bi) = a^2+b^2
		double dLength = calc_length_vector_complex(nSize, vector_in);
	
		///// ゼロ割を検出 /////
		if(dLength == 0.0){
			return;
		}
		
		///// ルート /////
		double dRevLength = 1.0/dLength;
		
		///// ユニット化 /////		
		for(int ic=0; ic<nSize; ic++)
		{
			vector_out[ic][0] = vector_in[ic][0]*dRevLength;
			vector_out[ic][1] = vector_in[ic][1]*dRevLength;
		}
	}
	//************************************************************************//
	/**
	*	ベクトルをユニット化　複素数
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//	
	public static double[] calc_mid_point_from_points(
		double[] ... points)
    {		
		//数を取得
		int num1 = points.length;
		int num2 = points[0].length;
		
		//総和を出す
		double[] sum = new double[num2];
		for(int ic=0; ic<num1; ic++){
			for(int jc=0; jc<num2; jc++){
				sum[jc] += points[ic][jc];
			}
		}
		
		//割る
		for(int jc=0; jc<num2; jc++){
			sum[jc] /= num1;
		}
		
		return sum;
	}
	//************************************************************************//
	/**
	*	グラム・シュミットの直交化法
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
    public static void calc_Gram_Schmidt_vector(
        double[]    vector_target,      //I     削る前のベクトル（ターゲットベクトル）
        double[]    vector_tool,        //I     削るベクトル（ツールベクトル）
        double[]    result              //O     削った後のベクトル
    )
    {
        //ツールを単位化
        double unit_tool[] = new double[vector_tool.length];
        Mathmatic.calc_vector_unit(vector_tool, vector_tool.length, unit_tool);
        
        //内積を計算
        double dInner = Mathmatic.calc_inner_product(unit_tool.length, vector_target, unit_tool);
        
        //内積・ツールベクトル
        double buf_vec[] = new double[vector_tool.length];
        Mathmatic.calc_vector_times_scalar(unit_tool, dInner, buf_vec);
        
        //グラム・シュミットによる直交化
        Mathmatic.calc_vector_minus_vector(vector_target, buf_vec, result);
    }
	//************************************************************************//
	/**
	*	グラム・シュミットの直交化法 複素数
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
    public static void calc_Gram_Schmidt_vector_complex(
        double[][]    vector_target,      //I     削る前のベクトル（ターゲットベクトル）
        double[][]    vector_tool,        //I     削るベクトル（ツールベクトル）
        double[][]    result              //O     削った後のベクトル
    )
    {
        //ツールを単位化
        double unit_tool[][] = new double[vector_tool.length][2];
        Mathmatic.calc_vector_unit_complex(vector_tool, vector_tool.length, unit_tool);
        
        //内積を計算
        double dInner[] = Mathmatic.calc_inner_product_complex(unit_tool.length, vector_target, unit_tool);
        
        //内積・ツールベクトル
        double buf_vec[][] = new double[vector_tool.length][2];
        Mathmatic.calc_vector_times_scalar_complex(unit_tool, dInner, buf_vec);
        
        //グラム・シュミットによる直交化
        Mathmatic.calc_vector_minus_vector_complex(vector_target, buf_vec, result);
    }
	//************************************************************************//
	/**
	*	直交化ベクトルの簡単出力	<br>
	*	とりあえず何でもいいから直交化したベクトルが欲しいときに使う。
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
    public static void calc_直交化ベクトルの簡単出力(
        double[]	vec,	//I     削る前のベクトル（ターゲットベクトル）
        double[]	result	//O     とりあえず直交化したベクトル
    )
    {
		//単純な足し引きで直交化を目指した式
		double[] buf_vec1 = {vec[1]-vec[2], vec[2]-vec[0], vec[0]-vec[1]};	//この式はxyzが同じ値の時特異点あり。
		double buf_length = calc_length_vector(3, buf_vec1);
		double buf_特異点除去項 = (1-buf_length)/(1+buf_length);
		double[] vec_candidate = {buf_vec1[0]+buf_特異点除去項, buf_vec1[1]-buf_特異点除去項, buf_vec1[2]};
		
        //グラムシュミットの直交化
		calc_Gram_Schmidt_vector(vec_candidate, vec, result);
		
		//ユニット化
		calc_vector_unit(result, 3, result);
    }
	//************************************************************************//
	/**
	*	直交化ベクトルの簡単出力	<br>
	*	とりあえず何でもいいから直交化したベクトルが欲しいときに使う。	<br>
	*	課題はたくさんありそう。
	* 
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
    public static void calc_直交化ベクトルの簡単出力_complex(
        double[][]	vec,	//I     削る前のベクトル（ターゲットベクトル）
        double[][]	result	//O     とりあえず直交化したベクトル
    )
    {
		//単純な足し引きで直交化を目指した式
//		double[][] buf_vec1 = {
//			{vec[1][0]-vec[2][0], vec[1][1]-vec[2][1]},
//			{vec[2][0]-vec[0][0], vec[2][1]-vec[0][1]},
//			{vec[0][0]-vec[1][0], vec[0][1]-vec[1][1]}
//		};
		double[][] buf_vec1 = {	//グラムシュミットで収束しない。暫定的に虚数は０に。
			{vec[1][0]-vec[2][0], 0},
			{vec[2][0]-vec[0][0], 0},
			{vec[0][0]-vec[1][0], 0}
		};
		double buf_length = calc_length_vector_complex(3, buf_vec1);
		double buf_特異点除去項 = (1-buf_length)/(1+buf_length);
//		double[][] vec_candidate = {
//			{buf_vec1[0][0]+buf_特異点除去項, buf_vec1[0][1]+buf_特異点除去項},
//			{buf_vec1[1][0]-buf_特異点除去項, buf_vec1[1][1]-buf_特異点除去項},
//			{buf_vec1[2][0],				 buf_vec1[2][1]}
//		};
		double[][] vec_candidate = {	//グラムシュミットで収束しない。暫定的に虚数は０に。
			{buf_vec1[0][0]+buf_特異点除去項, buf_vec1[0][1]},
			{buf_vec1[1][0]-buf_特異点除去項, buf_vec1[1][1]},
			{buf_vec1[2][0],				 buf_vec1[2][1]}
		};
		
        //グラムシュミットの直交化
		calc_Gram_Schmidt_vector_complex(vec_candidate, vec, result);
		
		//ユニット化
		calc_vector_unit_complex(result, 3, result);
    }
	//************************************************************************//
	/**
	*	点から点の長さを算出する
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static double calc_length_between_point_point(
		double[]	point1,	//I
		double[]	point2	//I
	)
	{
		double[] dPtoP = {0.0, 0.0, 0.0};
		for(int ic=0; ic<3; ic++){
			dPtoP[ic] = point1[ic] - point2[ic];
		}
		return Mathmatic.calc_length_vector(3, dPtoP);
	}

	//************************************************************************//
	/**
	*	ベクトルの長さを算出する
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static double calc_length_vector(int nSize, double[] dvector){
		
		double dSum = 0.0;
		for(int ic=0; ic<nSize; ic++){
			dSum += dvector[ic] * dvector[ic];
		}
		return Math.sqrt(dSum);
	}
	//************************************************************************//
	/**
	*	ベクトルの長さを算出する　複素数
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static double calc_length_vector_complex(
		int nSize,
		double[][] vector_in
	)
	{	
		///// 長さを算出 /////
		///// (a+bi)(a-bi) = a^2+b^2
		double dSum2 = 0.0;
		for(int ic=0; ic<nSize; ic++)
		{
			dSum2 += vector_in[ic][0]*vector_in[ic][0] + vector_in[ic][1]*vector_in[ic][1];
		}

		return Math.sqrt(dSum2);
	}
	//************************************************************************//
	/**
	*	直線と点間の距離
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static double calc_length_between_point_line(
		double[]	check_point,	//I		点
		double[]	point_tri1,		//I		直線上の点1
		double[]	point_tri2		//I		直線上の点2
	)
	{
		//ベクトル作成
		double[] dVec12 = new double[3];
		double[] dVec1Q = new double[3];
		for(int ic=0; ic<3; ic++)
		{
			dVec12[ic] = point_tri2[ic]  - point_tri1[ic];
			dVec1Q[ic] = check_point[ic] - point_tri1[ic];
		}

		//内積
		double dInner = Mathmatic.calc_InnerProduct(
			3,			//I		ベクトルの成分数
			dVec12,		//I		ベクトル1
			dVec1Q		//I		ベクトル2
		);
		
		//V12の内積
		double dInnerV12 = Mathmatic.calc_InnerProduct(
			3,
			dVec12,
			dVec12
		);
		
		//ベクトル12に対するベクトル1Cの割合
		double dRatio = dInner/dInnerV12;

//		//ベクトル12の長さ
//		double dLengV12 = Mathmatic.calc_length_vector(
//			3,
//			dVec12
//		);

		//点Cを、直線12と点Qの垂直交点とする。
		//そしてベクトル1Cを算出
		double[]dVec1C = new double[3];
		for(int ic=0; ic<3; ic++)
		{
//			dVec1C[ic] = dInner * dVec12[ic] / dLengV12;
			dVec1C[ic] = dRatio * dVec12[ic];
		}

		//ベクトルを点とみなして、2点間距離を算出
		return Mathmatic.calc_length_between_point_point(dVec1Q, dVec1C);
	}

	//************************************************************************//
	/**
	*	2点間の距離を算出
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static int calc_length_between_two_point(
		ArrayList<Double>	point1,	//I
		ArrayList<Double>	point2,	//I
		Double[]			dLength	//O
	)
	{
		//変数宣言
		int nSize    = point1.size();
		double dBuff = 0.0;
		double dBetween_two;

		//和
		for(int ic=0; ic<nSize; ic++)
		{
			dBetween_two = point1.get(ic) - point2.get(ic);
			dBuff += dBetween_two * dBetween_two;
		}
		//平方根
		dLength[0] = Math.sqrt(dBuff);

		return 0;
	}

	//************************************************************************//
	/**
	*	平面から点pまでの最短距離を算出
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static double calc_length_between_Plane_Point(double dPlan_orign[], double dPlane_normal[], double pos[]/*, double dLength*/)
	{
		double dVop[] = {0.0, 0.0, 0.0};
		double dInner_Product = 0.0;
		///// 平面（原点o,法線ベクトルVの平面)から点pまでの距離を測る /////
		//ベクトルVopを算出（平面原点oから対象の粒子位置pのベクトル）
		for(int ic=0; ic<3; ic++)
		{
			dVop[ic] = dPlan_orign[ic]-pos[ic];
		}
		//VopとV(面の法線ベクトル)の内積値Aを算出
		dInner_Product = 0.0;
		for(int kc=0; kc<3; kc++){
			dInner_Product += dPlane_normal[kc]*dVop[kc];
		}
		//Aの絶対値を距離とする
		return Math.abs(dInner_Product);
		
		//System.out.println(dLength);
	}
	//************************************************************************//
	//
	//機能名：
	//creat_axis_from_gram_schumit
	//
	//概要：
	//グラム・シュミットの直交化法を使用し、軸を削り出す
	//
	//	vec_bから、vec_a成分を取り除く
	//
	//************************************************************************//
	public static int creat_axis_from_gram_schumit(Double[] vec_a, Double[] vec_b, Double[] vec_res)
	{
		//変数宣言
		int nReturn = 0;
		
		int nSize = vec_a.length;

		/// 基準ベクトルの規格化 ///
		Double[] vec_a_unit = new Double[nSize];
		nReturn = calc_vector_unit(vec_a, vec_a_unit);
		if(nReturn != 0)
		{
			//エラー処理
		}

		/// 従属ベクトルの規格化 ///
		Double[] vec_b_unit = new Double[nSize];
		nReturn = calc_vector_unit(vec_b, vec_b_unit);

		/// ベクトルの内積 ///
		double dInner = calc_inner_product(vec_a_unit, vec_b_unit);

		/// 従属ベクトルから基準ベクトルの成分を除く ///
		for(int ic=0; ic<nSize; ic++)
		{
			vec_res[ic] = vec_b_unit[ic] - dInner*vec_a_unit[ic];
		}

		return 0;
	}

	//************************************************************************//
	//
	//機能名：
	//calc_vector_unit
	//
	//概要：
	//ベクトルを規格化する
	//
	//************************************************************************//
	public static int calc_vector_unit(Double[] vec_a, Double[] vec_result)
	{
		/// ベクトルの規格化 ///
		//変数宣言
		double dNorm  = 0.0;
		int    nSize  = vec_a.length;

		//ノルムを算出
		double dBuff = 0.0;
		for(int ic=0; ic<nSize; ic++)
		{
			double dBufvec = vec_a[ic];
			dBuff += dBufvec*dBufvec;
		}
		dNorm = Math.sqrt(dBuff);

		//規格化
		for(int ic=0; ic<nSize; ic++)
		{
			vec_result[ic] = vec_a[ic]/dNorm;
		}

		return 0;
	}

	//************************************************************************//
	//
	//機能名：
	//calc_inner_product
	//
	//概要：
	//ベクトル同士の内積を算出
	//
	//************************************************************************//
	public static double calc_inner_product(Double[] vec_a, Double[] vec_b)
	{
		int nArrySize = vec_a.length;

		/// ベクトルの内積 ///
		//変数宣言
		double dBuff = 0;
		//処理
		for(int ic=0; ic<nArrySize; ic++)
		{
			dBuff += vec_a[ic]*vec_b[ic];
		}

		return dBuff;
	}
	//************************************************************************//
	/**
	*	ベクトルの内積
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static double calc_inner_product(
		int			nSize,	//I		ベクトルの成分の数
		double[]	vec_a,	//I		ベクトル1
		double[]	vec_b	//I		ベクトル2
	)
	{
		/// ベクトルの内積 ///
		//変数宣言
		double dBuff = 0;
		//処理
		for(int ic=0; ic<nSize; ic++)
		{
			dBuff += vec_a[ic]*vec_b[ic];
		}

		return dBuff;
	}
	//************************************************************************//
	/**
	*	ベクトルの内積　複素数
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static double[] calc_inner_product_complex(
		int			nSize,	//I		ベクトルの成分の数
		double[][]	vec_a,	//I		ベクトル1
		double[][]	vec_b	//I		ベクトル2
	)
	{
		/// 複素数ベクトルの内積 ///
		/// a*共役ｂが内積の式。
		//変数宣言
		double dBuff_Re = 0;
		double dBuff_Im = 0;
		//処理
		for(int ic=0; ic<nSize; ic++)
		{
			double va_r = vec_a[ic][0];
			double va_i = vec_a[ic][1];
			double vb_r = vec_b[ic][0];
			double vb_i = vec_b[ic][1];
			dBuff_Re += va_r*vb_r +  va_i*vb_i;
			dBuff_Im += -va_r*vb_i +  va_i*vb_r;
		}
		double[] result = {dBuff_Re, dBuff_Im};

		return result;
	}
	//************************************************************************//
	/**
	*	三角形の属する平面式の係数を算出
	*	(時計回りで入力したとき、文字盤が読めるほうが＋とする)
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static void calc_plane_coeff_from_triangle(
		double[]	point_tri1,		//I		三角形の点1(時計回りで入力したとき、文字盤が読めるほうが＋とする)
		double[]	point_tri2,		//I		三角形の点2
		double[]	point_tri3,		//I		三角形の点3
		double[]	plane_coeff		//O		平面式の係数（平面式Ax+By+Cz+D=0のA,B,C,D）
	)
	{
		//外積を計算
		double[] Outer_Product = new double[3];
		Mathmatic.calc_OuterProduct_unit(
			point_tri1,		//
			point_tri2,		//
			point_tri3,		//
			Outer_Product	//
		);

		//平面式の係数A,B,Cは外積の各X,Y,Zの値と対応している。
		//平面式のDを算出するため、三角形の1点を代入する。
		double dCoeffD = -1.0*(Outer_Product[0]*point_tri1[0] + Outer_Product[1]*point_tri1[1] + Outer_Product[2]*point_tri1[2]);

		//平面式の係数を格納
		plane_coeff[0] = Outer_Product[0];
		plane_coeff[1] = Outer_Product[1];
		plane_coeff[2] = Outer_Product[2];
		plane_coeff[3] = dCoeffD;
	}
	//************************************************************************//
	/**
	*	平面と直線の交点
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static int calc_cross_point_line_plane(
		double[]	point_line_s,		//I		直線上の点1
		double[]	point_line_e,		//I		直線上の点2
		double[]	plane_coeff,		//I		平面式の係数（平面式Ax+By+Cz+D=0のA,B,C,D）
		double[]	dCrossPoint,		//O		平面と直線の交点
		double[]	dDistanceFromStart	//O		始点から交点までの距離
	)
	{
		//ベクトルS->Eを求める
		double[] dVecSE = new double[3];
		for(int ic=0; ic<3; ic++)
		{
			dVecSE[ic] = point_line_e[ic] - point_line_s[ic];
		}

		//内積（平面式とベクトルSE）
		double dInner_Coeff_VecSE = Mathmatic.calc_inner_product(
			3,				//ベクトルの成分の数
			plane_coeff,	//ベクトル1
			dVecSE			//ベクトル2
		);
		
		//ゼロ割判定
		if(dInner_Coeff_VecSE == 0.0)
		{
			//平面と直線が平行になっている？
			return -1;
		}

		//内積（平面式と点S）
		double dInner_Coeff_PointS = Mathmatic.calc_inner_product(
			3,				//ベクトルの成分の数
			plane_coeff,	//ベクトル1
			point_line_s	//ベクトル2
		);

		//係数λを求める
		double lambda = -1.0 * (dInner_Coeff_PointS + plane_coeff[3]) / dInner_Coeff_VecSE;

		//交点を求める。同時に始点から交点までの距離を求める。
		double dBuf = 0.0;
		double dBuf2Sum = 0.0;
		for(int ic=0; ic<3; ic++)
		{
			dBuf = lambda*dVecSE[ic];

			//交点の計算
			dCrossPoint[ic] = point_line_s[ic] + dBuf;
			
			//始点から交点までの距離の計算
			dBuf2Sum += dBuf*dBuf;
		}
		
		//始点から交点までの距離を求める
		dDistanceFromStart[0] = Math.sqrt(dBuf2Sum);
		
		return 0;
	}
	//************************************************************************//
	/**
	*	交点と三角形の内外判定
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static void check_point_in_triangle(
		double[]	point_tri1,		//I		三角形の点1（点の並びは時計回りとする）
		double[]	point_tri2,		//I		三角形の点2
		double[]	point_tri3,		//I		三角形の点3
		double[]	check_point,	//I		内外判定する点（三角形の平面上にある）
		double[]	dDistLinePoint	//O		各辺から判定点までの最短距離(辺A(1->2), 辺B(2->3), 辺C(3->1)、三角形内側は＋、全て+なら内側の点)
	)
	{
		//3角形の法線ベクトル
		double[] dOuterTriNormal = new double[3];
		Mathmatic.calc_OuterProduct_unit(
			point_tri1,		//I		三角形の点1(点は時計回りに入力)
			point_tri2,		//I		三角形の点2
			point_tri3,		//I		三角形の点3
			dOuterTriNormal	//O		三角形の法線ベクトルI(ユニット化済み)
		);

		//三角形エッジ1とチェック点間の距離を算出
		dDistLinePoint[0] = Mathmatic.calc_prepare_length_between_triangle_edge_point(
			dOuterTriNormal,	//I		三角形の法線ベクトル(ユニット化されている)
			point_tri1,			//I		三角形のエッジの点1(点は時計回りに取る)
			point_tri2,			//I		三角形のエッジの点2
			check_point			//I		チェック点
		);

		//三角形エッジ2とチェック点間の距離を算出
		dDistLinePoint[1] = Mathmatic.calc_prepare_length_between_triangle_edge_point(
			dOuterTriNormal,	//I		三角形の法線ベクトル(ユニット化されている)
			point_tri2,			//I		三角形のエッジの点2(点は時計回りに取る)
			point_tri3,			//I		三角形のエッジの点3
			check_point			//I		チェック点
		);

		//三角形エッジ3とチェック点間の距離を算出
		dDistLinePoint[2] = Mathmatic.calc_prepare_length_between_triangle_edge_point(
			dOuterTriNormal,	//I		三角形の法線ベクトル(ユニット化されている)
			point_tri3,			//I		三角形のエッジの点3(点は時計回りに取る)
			point_tri1,			//I		三角形のエッジの点1
			check_point			//I		チェック点
		);
	}

	//************************************************************************//
	/**
	*	3角形のエッジとチェック点間距離を算出。
	*	+の場合はチェック点が三角形の内側
	*	-の場合は外側
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	private static double calc_prepare_length_between_triangle_edge_point(
		double[]	triangle_normal,	//I		三角形の法線ベクトル(ユニット化されている)
		double[]	tri_edge_point1,	//I		三角形のエッジの点1(点は時計回りに取る)
		double[]	tri_edge_point2,	//I		三角形のエッジの点2
		double[]	check_point			//I		チェック点
	)
	{
		//三角形エッジとチェック点間の距離
		double dDistance = Mathmatic.calc_length_between_point_line(
			check_point,		//I		点
			tri_edge_point1,	//I		直線上の点1
			tri_edge_point2		//I		直線上の点2
		);

		///////////////////////////////////////////////////////////////
		//	三角形の内外判定。
		//
		//	3角形法線ベクトルの点と外積ベクトルの点間距離をとる
		//	この値の結果から、2つのベクトルが同じ方向(2.0ぐらい)なのか、
		//	逆方向（0.0ぐらい）なのか判定する。
		//	逆方向の場合は3角形の外と判定できる。
		///////////////////////////////////////////////////////////////
		//ベクトルを作成
		double[] tri_edge_vector	= new double[3];
		double[] check_vector		= new double[3];
		for(int ic=0; ic<3; ic++)
		{
			tri_edge_vector[ic]	= tri_edge_point2[ic] - tri_edge_point1[ic];
			check_vector[ic]	= check_point[ic]     - tri_edge_point1[ic];
		}

		//外積
		double[] dOuterVedgeVcheck = new double[3];
		Mathmatic.calc_OuterProduct_unit(
			tri_edge_vector,	//
			check_vector,		//
			dOuterVedgeVcheck	//
		);

		//ベクトルを点とみなして2点間の距離計算
		double dDirecSame = Mathmatic.calc_length_between_point_point(
			triangle_normal,
			dOuterVedgeVcheck
		);

		//三角形の内側か外側判定
		if(dDirecSame < 1.0)
		{
			dDistance *= -1.0;
		}

		return dDistance;
	}
	//************************************************************************//
	/**
	*	直線が三角形を通過しているか判定
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static boolean check_line_in_triangle(
		double[]	point_line_s,		//I		直線上の点1
		double[]	point_line_e,		//I		直線上の点2
		double[]	point_tri1,			//I		三角形の点1
		double[]	point_tri2,			//I		三角形の点2
		double[]	point_tri3,			//I		三角形の点3
		double		dTolerance,			//I		ピックのトレランス（ピック円柱の半径）
		double[] 	dCrossPoint,		//O		平面と直線の交点
		double[] 	dDistCrossFromStart	//O		始点から交点までの距離
	)
	{
		int nReturnCord = 0;

		//三角形の属する平面式の係数を算出
		double[] plane_coeff = new double[4];
		Mathmatic.calc_plane_coeff_from_triangle(
			point_tri1,		//I		三角形の点1
			point_tri2,		//I		三角形の点2
			point_tri3,		//I		三角形の点3
			plane_coeff		//O		平面式の係数（平面式Ax+By+Cz+D=0のA,B,C,D）
		);

		//平面と直線の交点
//		double[] dCrossPoint			= new double[3];
//		double[] dDistCrossFromStart	= new double[1];
		nReturnCord = Mathmatic.calc_cross_point_line_plane(
			point_line_s,		//I		直線上の点1
			point_line_e,		//I		直線上の点2
			plane_coeff,		//I		平面式の係数（平面式Ax+By+Cz+D=0のA,B,C,D）
			dCrossPoint,		//O		平面と直線の交点
			dDistCrossFromStart	//O		始点から交点間での距離
		);
		if(nReturnCord < 0)
		{
			//交点が一意に決まらない。
			return false;
		}
		
		//まず三角形の頂点からトレランスの円を描き、その中に入っているか判定する。
		if(dTolerance > 0.0)
		{
			double dLength_vertex_checkpoint = 0.0;
			dLength_vertex_checkpoint = Mathmatic.calc_length_between_point_point(point_tri1, dCrossPoint);
			if(dLength_vertex_checkpoint < dTolerance)
			{
				//トレランス範囲に入っている。
				return true;
			}
			dLength_vertex_checkpoint = Mathmatic.calc_length_between_point_point(point_tri2, dCrossPoint);
			if(dLength_vertex_checkpoint < dTolerance)
			{
				//トレランス範囲に入っている。
				return true;
			}
			dLength_vertex_checkpoint = Mathmatic.calc_length_between_point_point(point_tri3, dCrossPoint);
			if(dLength_vertex_checkpoint < dTolerance)
			{
				//トレランス範囲に入っている。
				return true;
			}
		}
		

		//交点と三角形の内外判定
		double[] dDistLinePoint = new double[3];
		Mathmatic.check_point_in_triangle(
			point_tri1,		//I		三角形の点1
			point_tri2,		//I		三角形の点2
			point_tri3,		//I		三角形の点3
			dCrossPoint,	//I		内外判定する点（三角形平面内の点）
			dDistLinePoint	//O		各辺から判定点までの最短距離(辺A(1->2), 辺B(2->3), 辺C(3->1)、三角形内側は＋、全て+なら内側の点)
		);

		//三角形の内外判定（全て+なら三角形内側）
		double	dTolBuf = -1.0 * dTolerance;
		boolean	bAlreadyExistMinus = false;
		for(double dDist : dDistLinePoint)
		{
			if(dDist < dTolBuf)
			{
				//2つのエッジから外側判定されている場合、取り除く
				if(bAlreadyExistMinus == false)
				{
					bAlreadyExistMinus = true;
				}
				else
				{
					//三角形の外側
					return false;
				}
			}
		}

		return true;
	}
	//************************************************************************//
	/**
	*	
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static void test_big_decimal()
	{
		BigDecimal bd1 = new BigDecimal(1.00);
		BigDecimal bd2 = new BigDecimal(2.00);
		BigDecimal bd3 = new BigDecimal(3.00);
		
		BigDecimal testPlus		= bd1.add(bd2);
		BigDecimal testMinus	= bd1.subtract(bd2);
		BigDecimal testTimes	= bd1.multiply(bd2);
		
		int nSeido = 5;	//有効数字の個数。12.3456で、Nを3としたときは、12.3が有効数字。
		BigDecimal testDivide	= bd1.divide(bd2, nSeido, BigDecimal.ROUND_HALF_UP);

		//ROUND_CEILING	正の無限大に近づくように丸めるモード
		//ROUND_DOWN	ゼロに近づくように丸めるモード
		//ROUND_FLOOR	負の無限大に近づくように丸めるモード
		//ROUND_HALF_DOWN	五捨六入する
		//ROUND_HALF_EVEN	末尾が偶数のほうに丸める
		//ROUND_HALF_UP	四捨五入する
		//ROUND_UNNECESSARY	丸め不要
		//ROUND_UP	0 から離れるように丸めるモード
		
	}


	public void f(){}
	//************************************************************************//
	//
	//行列・または配列をコピー
	//
	//概要：
	//2点間の距離を算出
	//
	//************************************************************************//
	public static int copy_vector(
		int nSize,
		Double[] matOrigin,
		Double[] matCopy
	)
	{
//		int nSize = matOrigin.length;
		System.arraycopy(matOrigin, 0, matCopy, 0, nSize);
		
		return 0;
	}
    public static int copy_vector(int nSize, double[] matOrigin, double[] matCopy)
	{
//		int nSize = matOrigin.length;
		System.arraycopy(matOrigin, 0, matCopy, 0, nSize);
		
		return 0;
	}
	//************************************************************************//
	/**
	*	行列に値を入力する際に、Stringでまとめて入力
	*
	*	@param
	*	@return	
	*	@version
	*/
	//************************************************************************//
	public static void spread_string_to_double(int nRow, int nColumn, String sFactor, String[] str, Double[] dSpread)
	{
		ArrayList<String> sData = new ArrayList<String>();

		for(int ic=0; ic<str.length; ic++)
		{
			//sFactorで分解
			util.string_factorize3(str[ic], sFactor, sData);
		}
		
		int nIndex = -1;
		for(int ic=0; ic<nRow; ic++)
		{
			for(int jc=0; jc<nColumn; jc++)
			{
				nIndex++;
				dSpread[mxIndex(nRow, ic, jc)] = util.change_String_To_Double(sData.get(nIndex));
			}
		}
	}
	public static void spread_string_to_double(int nColumn, String sFactor, String[] str, Double[] dSpread)
	{
		spread_string_to_double(1, nColumn, sFactor, str, dSpread);
	}
	public void g(){}
	//************************************************************************//
	/**
	*	フーリエ変換
	*
	*	http://laputa.cs.shinshu-u.ac.jp/~yizawa/InfSys1/basic/chap7/index.htm
	*
	*	@param
	*	@return	
	*	@version
	*
	*
	*	回転子
	*	W = exp(-i*2π/n)
	*	
	*	周波数                                     大きさ
	*	|X0  | |W^0, W^0,   W^0,        ･･･, W^0         | |x0  |
	*	|X1  | |W^0, W^1,   W^2,        ･･･, W^(n-1)     | |x1  |
	*	|X2  | |W^0, W^2,   W^4,        ･･･, W^2(n-1)    | |x2  |
	*	|X3  |=|W^0, W^3,   W^6,        ･･･, W^3(n-1)    |*|x3  |
	*	|・  | |・  ・    ・            ・               | |・  |
	*	|・  | |・  ・    ・            ・               | |・  |
	*	|・  | |・  ・    ・            ・               | |・  |
	*	|Xn-1| |W^0, W^(n-1), W2^(n-1), ･･･, W^((n-1)^2) | |xn-1|
	*	
	*	
	*	回転子のべき乗は、周期性や対称性がある。それを利用する。
	*	
	*	
	*	点の数 n=1024の場合
	*	※時計回りに回っていく。
	*	                                  W^768
	*	                             ...････|････...                        
	*	                       . ･･''       |       ''･ .                   
	*	                     ･              |            '･                 
	*	                 .･'                |               ' .             
	*	               ･                    |                  '･.          
	*	             ･'                     |                     ･         
	*	           .･                       |                      '･       
	*	          ･                         |                        ･      
	*	         ･         第3象限          |       第4象限           ･     
	*	        ･                           |                          ･    
	*	       ･                            |                           ･   
	*	       ･                            |                           ･   
	*	      ･                             |                            ･  
	*	      ･                             |                            ･  
	*	      ･        　                   | 　                         ･  
	*	W^512――――――――――――――――――――――――――――――― W^0
	*	      ･                             |                            ･  
	*	      ･                             |                            ･  
	*	      ･                             |                            ･  
	*	       ･                            |                           ･   
	*	       ･                            |                           ･   
	*	        ･           第2象限         |       第1象限            ･    
	*	         ･                          |                         ･     
	*	          ･                         |                        ･      
	*	            ･                       |                      .'       
	*	              ･　                   | 　                ＼･         
	*	               '･                   |                  .･   W^128   
	*	                 '･                 |                 ･             
	*	                    '･              |             .･'               
	*	                       ' ･ .        |        . ･ '                  
	*	                            '''･････|･････'''                       
	*	                                  W^256
	*	
	*	
	*	回転子は1象限だけ計算する。2,3,4象限の回転子は、以下のルールで作成する。
	*	※時計回りとする
	*	
	*	2象限
	*	　→実数 = 1象限の虚数
	*	　→虚数 = 1象限の実数*-1
	*	
	*	3象限
	*	　→1象限*-1
	*	
	*	4象限
	*	　→実数 = 1象限の虚数*-1
	*	　→虚数 = 1象限の実数
	*
	*/
	//************************************************************************//
	public static String calc_Fourier_translation(
					int nPointNum,			//データ点の数
					Double[] dValue,		//データ
					Double[] dFourier_Re,	//出力：フーリエ変換の実数
					Double[] dFourier_Im	//出力：フーリエ変換の虚数
					)
	{
		//*====================================================
		//	第1象限の回転子を準備
		//*====================================================
		//点の数を設定(4の倍数でなければいけない)
		//int nPointNum = 1024;
		
		if(nPointNum%4 != 0)
		{
			return "ERROR:フーリエ変換を行う点の数が4の倍数ではありません。";
		}

		int nPointNum4 = nPointNum/4;
		int nPointNum2 = nPointNum/2;
		double dFarcPointNum2 = 1.0/(double)nPointNum2;

		///回転子を第一象限分だけ作成
		Double[] dRotWeight_Re = new Double[nPointNum4];
		Double[] dRotWeight_Im = new Double[nPointNum4];

		double dCoeff = -1*2*Math.PI/(double)nPointNum;
		double dBuf = 0.0;
		for(int ic=0; ic<nPointNum4; ic++)
		{
			//回転子：W = exp(-i*2π/N*n)
			dBuf = dCoeff * (double)ic;
			
			dRotWeight_Re[ic] = Math.cos(dBuf);
			dRotWeight_Im[ic] = Math.sin(dBuf);
		}

		//*====================================================
		//	フーリエ変換
		//*====================================================
		//点の値
//		dFourier_Re = new Double[nPointNum];
//		dFourier_Im = new Double[nPointNum];
		Double[] dValueSum2 = new Double[nPointNum2];

		double dSum_Re = 0.0;
		double dSum_Im = 0.0;

		//nRow行目を計算する。
		for(int nRow=0; nRow<nPointNum; nRow++)
		{
			//初期化
			for(int ic=0; ic<nPointNum2; ic++)
			{
				dValueSum2[ic] = 0.0;
			}
			
			//同じ回転子や対称性のある回転子を掛ける場合、あらかじめ掛けられる方の値を足しておく。
			//4象限に分けて,そして3象限は1象限に移し、4象限は2象限に移す。
			int nPowIndex = 0;
			int nBuf = 0;
			for(int nColumn=0; nColumn<nPointNum; nColumn++)
			{
				//回転子のべき乗のみ算出(nRow行目、nColumn列の回転子の指数)
				nPowIndex = nRow * nColumn;
				
				//指数を点の数を基底として余りを算出
				nBuf = nPowIndex % nPointNum;
				
				//どの象限に入っているか判定
				if(nBuf < nPointNum2)
				{
					//第1,2象限
					dValueSum2[nBuf] += dValue[nColumn];
				}
				else
				{
					//第3,4象限
					//　→3象限は1象限*-1
					//	→4象限は2象限*-1
					dValueSum2[nBuf-nPointNum2] -= dValue[nColumn];
				}
			}


			//1象限,3象限のフーリエ変換の値を計算
			dSum_Re = 0.0;
			dSum_Im = 0.0;
			for(int ic=0; ic<nPointNum4; ic++)
			{
				dSum_Re += dRotWeight_Re[ic] * dValueSum2[ic];
				dSum_Im += dRotWeight_Im[ic] * dValueSum2[ic];
			}

			//2象限,4象限のフーリエ変換の値を計算
			//2象限の回転子の実数 = 1象限の回転子の虚数
			//2象限の回転子の虚数 = 1象限の回転子の実数*-1
			for(int ic=nPointNum4; ic<nPointNum2; ic++)
			{
				dSum_Re +=      dRotWeight_Im[ic-nPointNum4] * dValueSum2[ic];
				dSum_Im += -1 * dRotWeight_Re[ic-nPointNum4] * dValueSum2[ic];
			}

			//フーリエ変換
			//dFourier_Re[nRow] = dSum_Re;
			//dFourier_Im[nRow] = dSum_Im;
			dFourier_Re[nRow] = dFarcPointNum2*dSum_Re;
			dFourier_Im[nRow] = dFarcPointNum2*dSum_Im;
		}
		
		return null;
	}


}
