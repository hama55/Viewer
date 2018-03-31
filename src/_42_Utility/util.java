package _42_Utility;
import java.io.*;
import java.text.DateFormat;
import java.util.*;
//import java.lang.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class util
{
	final static String DEF_STR_EMPTY = "";
	
	//定義
	private static enum eNumIntDcimal{
		INTEGER_ZERO,
		DCIMAL_ZERO,
		INTEGER_NON_ZERO,
		DCIMAL_NON_ZERO,
		NO_INT_DCI	//整数でも小数でもゼロでもない
	}
	
	public static enum eNumType{
		NO_NUMERIC,		//数値ではない
		INTEGER_PLUS,	//プラスの値	(記述は整数タイプ)
		DCIMAL_PLUS,	//プラスの値	(記述は小数タイプ)
		INDEX_PLUS,		//プラスの値	(記述は指数タイプ)
		INTEGER_ZERO,	//ゼロ	(記述は整数タイプ)
		DCIMAL_ZERO,	//ゼロ	(記述は小数タイプ)
		INDEX_ZERO,		//ゼロ	(記述は指数タイプ)
		INTEGER_MINUS,	//マイナスの値	(記述は整数タイプ)
		DCIMAL_MINUS,	//マイナスの値	(記述は小数タイプ)
		INDEX_MINUS,	//マイナスの値	(記述は指数タイプ)
	}
	public void a(){}
	public void b(){}
	public void c(){}
	public void d(){}
	public void e(){}
	public void f(){}
	public void g(){}
	public void h(){}
	public void i(){}
	public void j(){}
	public void k(){}
	public void l(){}
	public void m(){}
	public void n(){}
	public void o(){}
	public void p(){}
	public void q(){}
	public void r(){}
	public void s(){}
	public void t(){}
	public void u(){}
	public void v(){}
	public void w(){}
	public void x(){}
	public void y(){}
	public void z(){}
	//************************************************************************//
	/**
	*	Doubleの値が一致するか比較
	*
	*	doubleは値が一致しているか判定するため、トレランスを使用する。
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static Boolean is_coincident_double(	
		double dA,
		double dB,
		double dTolerance
	)
	{
		double dAccidentError = Math.abs(dA - dB);
		return dAccidentError < dTolerance;
	}
	
	//************************************************************************//
	/**
	*	Stringをdoubleに変換　
	*
	*	@param
	*	@return	double
	*	@version
	*/
	//************************************************************************//
	public static double change_String_To_Double(String str)
	{
		return Double.valueOf(str);
	}
	public static double[] change_String_To_Double(
		String ... strings
	)
	{
		double[] arry_double = new double[strings.length];
		for(int ic=0; ic<strings.length; ic++)
		{
			arry_double[ic] = Double.parseDouble(strings[ic]);
		}
		
		return arry_double;
	}
	//************************************************************************//
	/**
	*	Stringをdouble_complexに変換　
	*
	*	 1
	*	-1
	*	 1+1i
	*	 1-1i
	*	-1+1i
	*	-1-1i
	*	    1i
	*      -1i
	*	 1i+1
	*	-1i+1
	*	 1i-1
	*	-1i-1
	* 
	* まず+,-で分割する。
	* といっても、+、-は消さずに分ける。
	* 
	*	 1		→	 1
	*	-1		→	-1
	*	 1+1i	→	 1,		+1i
	*	 1-1i	→	 1,		-1i
	*	-1+1i	→	-1,		+1i
	*	-1-1i	→	-1,		-1i
	*	    1i	→	 1i
	*      -1i	→	-1i
	*	 1i+1	→	 1i,	+1
	*	-1i+1	→	-1i,	+1
	*	 1i-1	→	 1i,	-1
	*	-1i-1	→	-1i,	-1
	*
	* iがあれば虚数にいれて、それ以外は実数に入れる。
	* 虚数の時、iを消すだけだとiや-iの場合はまずい。
	* iを消して数値が入っていない場合は1を入れる。
	* 
	*	@param
	*	@return	double
	*	@version
	*/
	//************************************************************************//
	public static double[] change_String_To_Double_Complex(String str)
	{
		double value[] = new double[2];
		//まず+,-で分割する。
		//といっても、+、-は消さずに分ける。
		ArrayList<String> sData = new ArrayList<>();
		ArrayList<String> factors = new ArrayList<>();
		factors.add("+");
		factors.add("-");
		util.string_factorize4(str, factors, sData, false);
		
		//iがあれば虚数にいれて、それ以外は実数に入れる。		
		//虚数の時、iを消すだけだとiや-iの場合はまずい。
		//iを消して数値が入っていない場合は1を入れる。
		final String numbers[] = {"0", "1", "2", "3", "4", "5", "6" ,"7" ,"8" ,"9"};
		for(String sbuf : sData)
		{
			//実数の場合
			if(sbuf.contains("i") == false)
			{
				value[0] = Double.valueOf(sbuf);				
			}
			//虚数の場合
			else
			{
				String s_replace = "1";
				//数字が入っているかチェック
				for(String check_num : numbers)
				{
					//数字が入っている場合
					if(sbuf.contains(check_num) == true)
					{
						//数字が入っているから、iをそのまま消してよい。
						s_replace = "";
						break;
					}
				}
				//iを消した文字列を値とする。
				sbuf = string_replace(sbuf, "i", s_replace);
				value[1] = Double.valueOf(sbuf);
			}
		}
		
		return value;
	}
	public static double[][] change_String_To_Double_Complex(
		String ... strings
	)
	{
		double[][] arry_double = new double[strings.length][2];
		for(int ic=0; ic<strings.length; ic++)
		{
//			arry_double[ic] = Double.parseDouble(strings[ic]);
			arry_double[ic] = change_String_To_Double_Complex(strings[ic]);
		}
		
		return arry_double;
	}
	//************************************************************************//
	/**
	*	StringをIntegerに変換　
	*
	*	@param
	*	@return	int
	*	@version
	*/
	//************************************************************************//
	public static Integer change_String_To_Integer(String str)
	{
		return Integer.valueOf(str);
	}
	public static int[] change_String_To_Integer(
		String ... strings
	)
	{
		int[] arry_int = new int[strings.length];
		for(int ic=0; ic<strings.length; ic++)
		{
			arry_int[ic] = Integer.parseInt(strings[ic]);
		}
		
		return arry_int;
	}
	//************************************************************************//
	/**
	*	Stringをlongに変換　
	*
	*	@param
	*	@return	int
	*	@version
	*/
	//************************************************************************//
	public static long[] change_String_To_Long(
		String ... strings
	)
	{
		long[] arry_long = new long[strings.length];
		for(int ic=0; ic<strings.length; ic++)
		{
			arry_long[ic] = Long.parseLong(strings[ic]);
		}
		
		return arry_long;
	}
	//************************************************************************//
	/**
	*	文字列の数値タイプを取得　
	*
	*	@param
	*	@return	eNumType	（整数、小数、指数、プラス、マイナス、ゼロ、数値ではない）
	*	@version
	*/
	//************************************************************************//
	public static eNumType check_string_numeric_type(String str){
		
		//変数宣言
		Boolean bIndex = false;
		
		///// 文字列の先頭と後ろの空白を除く /////
		String sWork = str.trim();	
		
		///// 指数部があれば除く /////
		String sIndex = DEF_STR_EMPTY;
		//大文字に変える
		sWork = sWork.toUpperCase();
		//指数表記があるかチェック
		int nStart = sWork.indexOf('E');
		//指数表記がある場合
		if(nStart != -1){
			//指数表記Eが先頭、または一番後ろではない場合
			if(0 < nStart && nStart < sWork.length()-1)
			{
				//指数表記があり、先頭または一番後ろではない
				bIndex = true;
				//指数部を取得
				sIndex = sWork.substring(nStart+1, sWork.length());
				//整数部or小数部を取得
				sWork = sWork.substring(0, nStart);
			}
		}
		
		/////有効桁数部を判定 /////
		eNumIntDcimal eType_IntDci;
		eType_IntDci = check_string_is_int_or_dcimal(sWork);
		
		///// 指数部の判定 /////
		eNumIntDcimal eType_Index = eNumIntDcimal.INTEGER_NON_ZERO;
		if(bIndex){
			eType_Index = check_string_is_int_or_dcimal(sIndex);
		}
		
		///// 判定 /////
		//*******************
		//	数値ではない場合
		//*******************
		if(eType_IntDci == eNumIntDcimal.NO_INT_DCI){
			//有効桁数部が数値ではない場合
			return eNumType.NO_NUMERIC;
		}
		else if(eType_Index == eNumIntDcimal.DCIMAL_NON_ZERO ||
				eType_Index == eNumIntDcimal.DCIMAL_ZERO){
			//指数表記Eの後が小数の場合
			return eNumType.NO_NUMERIC;
		}
		else if(eType_Index == eNumIntDcimal.NO_INT_DCI){
			//指数表記Eの後が、整数でも小数でもゼロでもない場合
			return eNumType.NO_NUMERIC;
		}
		//*******************
		//	指数表記の場合
		//*******************
		else if(bIndex){
			//ゼロの場合
			if( eType_IntDci == eNumIntDcimal.INTEGER_ZERO ||
				eType_IntDci == eNumIntDcimal.DCIMAL_ZERO){
				return eNumType.INDEX_ZERO;	
			}
			//プラスの場合
			else if(sWork.charAt(0) != '-'){
				return eNumType.INDEX_PLUS;
			}
			//マイナスの場合
			else{
				return eNumType.INDEX_MINUS;
			}
		}
		//*******************
		//	整数の場合
		//*******************
		else if(eType_IntDci == eNumIntDcimal.INTEGER_ZERO){
			//ゼロの場合
			return eNumType.INTEGER_ZERO;
		}
		else if(eType_IntDci == eNumIntDcimal.INTEGER_NON_ZERO){
			//プラス判定
			if(sWork.charAt(0) != '-'){
				return eNumType.INTEGER_PLUS;
			}else{
				return eNumType.INTEGER_MINUS;
			}
		}
		//*******************
		//	小数の場合
		//*******************
		else if(eType_IntDci == eNumIntDcimal.DCIMAL_ZERO){
			//ゼロの場合
			return eNumType.DCIMAL_ZERO;
		}
		else{
			if(sWork.charAt(0) != '-'){
				//プラス
				return eNumType.DCIMAL_PLUS;
			}else{
				//マイナス
				return eNumType.DCIMAL_MINUS;
			}
		}

		//return eBuffNumtype;
	}
	
	//************************************************************************//
	/**
	*	文字列の数値タイプをチェック
	*
	*	@param
	*	@return	eNumIntDcimal	[整数、小数、それ以外]
	*	@version
	*/
	//************************************************************************//
	private static eNumIntDcimal check_string_is_int_or_dcimal(String str){
		
		//変数宣言
		char cBuff;
		String sWork = str.trim();
		int nCountZero    = 0;
		int nCountNonZero = 0;
		int nCountPoint = 0;
		//可能性フラグ
		Boolean bNumeric = true;
		Boolean bInteger = true;
		
		///// 文字列が空ではないかチェック /////
		if(sWork.length() == 0){
			bNumeric = false;
		}
		
		///// 1文字ずつ取得 /////
		for (int ic = 0 ; ic < sWork.length(); ic++){
			cBuff = sWork.charAt(ic);
			
			///// ゼロの場合 /////
			if ('0' == cBuff){
				//ゼロ
				nCountZero++;
			}
			///// ゼロ以外の数字の場合 /////
			else if('1' <= cBuff && cBuff <= '9'){
				//数字
				nCountNonZero++;
			}
			///// マイナスの場合 /////
			else if(cBuff == '-'){
				//ルール：一番初め
				if(ic != 0){
					//数値ではない
					bNumeric = false;	
				}
			}
			///// プラスの場合 /////
			else if(cBuff == '+'){
				//ルール：一番初め
				if(ic != 0){
					//数値ではない
					bNumeric = false;	
				}
			}
			///// 点の場合 /////
			else if(cBuff == '.'){
				bInteger = false;
				//カウントアップ
				nCountPoint++;
				//ルール：2つは無い
				if(nCountPoint >= 2){
					//数値ではない
					bNumeric = false;				
				}
			}
			else{
				//数値ではない
				bNumeric = false;
			}
			
			///// 判定 /////
			if(bNumeric == false){
				//数値ではない
				break;
			}
		}
		
		
		///// タイプを出力 /////
		//変数宣言
		eNumIntDcimal eType;
		if(bNumeric){
			//数字が無い場合
			if((nCountZero + nCountNonZero) == 0){
				//整数でも小数でもゼロでもない
				eType = eNumIntDcimal.NO_INT_DCI;
			}
			///// 記述が整数タイプ /////
			else if(bInteger){
				//整数
				if(nCountNonZero == 0){
					//ゼロ
					eType = eNumIntDcimal.INTEGER_ZERO;
				}else{
					//ゼロ以外
					eType = eNumIntDcimal.INTEGER_NON_ZERO;
				}				
			}
			///// 記述が小数タイプ /////
			else{
				//小数				
				if(nCountNonZero == 0){
					//ゼロ
					eType = eNumIntDcimal.DCIMAL_ZERO;
				}else{
					//ゼロ以外
					eType = eNumIntDcimal.DCIMAL_NON_ZERO;
				}
			}
		}else{
			//記述が整数でも小数でもゼロでもない
			eType = eNumIntDcimal.NO_INT_DCI;
		}
		return eType;
	}

	//************************************************************************//
	/**
	 *	改行記号を取得
	 *
     * 例)  Windowsなら\r\n		<br>
     *      Linuxなら他の何か。		<br>
     * 
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_get_newline()
	{
		char buf = 0x0A;
		return String.format("%c", buf);
		//System.lineSeparator(); //JDK1.7以降
		// return System.getProperty("line.separator");
    }
	public static String newline()
	{
        return string_get_newline();

    }
	//************************************************************************//
	/**
	 *	タブ記号を取得							<br>
     * 文字コードは16進数だと0x09				<br>
	 *
     * 例)  Windowsは¥t						<br>
     *      Linuxは\t							<br>
     * 
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_get_tab()
	{
        char buf = 0x09;
        return String.format("%c", buf);
    }
	//************************************************************************//
	/**
	 *  復帰（行頭にカーソルを戻す）記号を取得	<br>
     *  文字コードは16進数だと0x09				<br>
	 *
     *  例) Windowsは¥t						<br>
     *      Linuxは\t							<br>
     * 
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_get_reverse()
	{
        char buf = 0x0D;
        return String.format("%c", buf);
    }
    //************************************************************************//
	/**
	 *	ファイルパスのフォルダ区切り記号を取得		<br>
	 *
     * 例） Windowsなら¥¥							<br>
     *     Linuxなら\								<br>
     * 
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_get_directory_separator()
	{
        return System.getProperty("file.separator");
    }    
	//************************************************************************//
	/**
	 *	システムプロパティを取得											<br>
	 *
     * 取得する関数															<br>
     * Properties props = System.getProperties();						<br>
     * props.list(System.out);											<br>
     * 
     * 取得した文字列														<br>
     *  java.runtime.name=Java(TM) SE Runtime Environment			<br>
	 *  sun.boot.library.path=C:\jdk1.6.0_18\jre\bin					<br>
	 *	java.vm.version=16.0-b13											<br>
	 *	java.vm.vendor=Sun Microsystems Inc.							<br>
	 *	java.vendor.url=http://java.sun.com/								<br>
	 *	path.separator=;													<br>
	 *	java.vm.name=Java HotSpot(TM) Client VM								<br>
	 *	file.encoding.pkg=sun.io											<br>
	 *	user.country=JP														<br>
	 *	sun.java.launcher=SUN_STANDARD										<br>
	 *	sun.os.patch.level=													<br>
	 *	java.vm.specification.name=Java Virtual Machine Specification		<br>
	 *	user.dir=C:\XXX														<br>
	 *	java.runtime.version=1.6.0_18-b07									<br>
	 *	java.awt.graphicsenv=sun.awt.Win32GraphicsEnvironment				<br>
	 *	java.endorsed.dirs=C:\jdk1.6.0_18\jre\lib\endorsed					<br>
	 *	os.arch=x86															<br>
	 *	java.io.tmpdir=C:\XXX												<br>
	 *	line.separator=														<br>
	 *	java.vm.specification.vendor=Sun Microsystems Inc.					<br>
	 *	user.variant=														<br>
	 *	os.name=Windows 7													<br>
	 *	sun.jnu.encoding=MS932												<br>
	 *	java.library.path=C:\jdk1.6.0_18\bin;.;C:\Windows\Sun\J...			<br>
	 *	java.specification.name=Java Platform API Specification				<br>
	 *	java.class.version=50.0												<br>
	 *	sun.management.compiler=HotSpot Client Compiler						<br>
	 *	os.version=6.1														<br>
	 *	user.home=C:\XXX													<br>
	 *	user.timezone=														<br>
	 *	java.awt.printerjob=sun.awt.windows.WPrinterJob						<br>
	 *	file.encoding=MS932													<br>
	 *	java.specification.version=1.6										<br>
	 *	user.name=XXX														<br>
	 *	java.class.path=C:\XXX												<br>
	 *	java.vm.specification.version=1.0									<br>
	 *	sun.arch.data.model=32												<br>
	 *	java.home=C:\jdk1.6.0_18\jre										<br>
	 *	java.specification.vendor=Sun Microsystems Inc.						<br>
	 *	user.language=ja													<br>
	 *	awt.toolkit=sun.awt.windows.WToolkit								<br>
	 *	java.vm.info=mixed mode, sharing									<br>
	 *	java.version=1.6.0_18												<br>
	 *	java.ext.dirs=C:\jdk1.6.0_18\jre\lib\ext;C:\Windows...				<br>
	 *	sun.boot.class.path=C:\jdk1.6.0_18\jre\lib\resources.jar;...		<br>
	 *	java.vendor=Sun Microsystems Inc.									<br>
	 *	file.separator=\													<br>
	 *	java.vendor.url.bug=http://java.sun.com/cgi-bin/bugreport...		<br>
	 *	sun.cpu.endian=little												<br>
	 *	sun.io.unicode.encoding=UnicodeLittle								<br>
	 *	sun.desktop=windows													<br>
	 *	sun.cpu.isalist=pentium_pro+mmx pentium_pro pentium+m...			<br>
     * 
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_get_system_property()
	{
        return System.getProperty("？？？");
    }
    //************************************************************************//
	/**
	 *	文字列を指定した行数で区切る
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
    public static ArrayList<String> string_divide(
        String  comment,        //元の文章
        int     n_divide_max    //行数の上限
    )
    {
        ArrayList<String> arry_comment = new ArrayList<>();
        
        //文章の文字数
        int n_num = comment.length();
        
        //行の数
        int n_line_max = 1 + (n_num - 1) / n_divide_max;
        
        for(int nc=1; nc<=n_line_max; nc++)
        {
            int num_s = (nc-1) * n_divide_max;
            int num_e = (nc  ) * n_divide_max;
            
            //文章の最大文字数を越えている場合は修正
            if(num_e > n_num)
            {
                num_e = n_num;
            }
            
            arry_comment.add(comment.substring(num_s, num_e));
        }
        
        return arry_comment;
    }
        //************************************************************************//
	/**
	 *	文字列を指定した行数で区切る
     *  そして先頭だけ日付などのタイトルを付ける。
     * 
     * 例)
     * 2015/12/19 testaabbccddee
     *            ffgghhiijjkkll
     *            mmnnoo
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
    public static String string_divide_modify(
        String  title,          //1行目に使うタイトル
        String  comment,        //元の文章
        int     n_max_line      //行数の上限
    )
    {
        String sSpace = "";
        for(int nc=0; nc<title.length(); nc++)
        {
            sSpace += " ";
        }

//        int n_comment_length = comment.length();

        //文字列を指定した行数で区切る
        ArrayList<String> arry_comment = util.string_divide(comment, n_max_line);

        //max_lineで改行する。
        for(int nc=0 ; nc < arry_comment.size() ; nc++)
        {
            String s_buf;

            if(nc == 0)
            {
                s_buf = title + arry_comment.get(nc);
            }
            else
            {
                s_buf = sSpace + arry_comment.get(nc);
            }

            arry_comment.set(nc, s_buf);
        }
        
        //１つの文字列にまとめる（互換性を考えるとこれがいい）
        String sBuf = "";
        String newline = util.string_get_newline();
        for(int nc=0 ; nc < arry_comment.size() ; nc++)
        {
            sBuf += arry_comment.get(nc) + newline;
        }
            
        return sBuf;
    }
    //************************************************************************//
	/**
	 *	二つの記号に囲まれた文字列を出力する
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static void string_between(
			String str, 
			String sHidari, 
			String sMigi, 
			ArrayList<int[]> arryPair
			)
	{
		//全てのsHidariを探す
		//初期値
		int nHidari = -1;
		while(true)
		{
			//sHidariを探す
			nHidari = str.indexOf(sHidari, nHidari+1);
			if(nHidari == -1)
			{
				break;
			}
			
			//push
			int[] nNums = new int[2];
			nNums[0] = nHidari;
			nNums[1] = 0;
			arryPair.add(nNums);
		}
		
		//sMigiを検索し、一番近いsHidariとペアを作る
		int nMigi = -1;
		for(int ic=0; ic<arryPair.size(); ic++)
		{
			//sHidariを探す
			nMigi = str.indexOf(sMigi, nMigi+1);
			if(nMigi == -1)
			{
				break;
			}
			else if(nMigi == 0)
			{
				continue;
			}

			//一番近い値を探る（追い越した瞬間）
			int nNum = -1;
			for(int jc=0; jc<arryPair.size(); jc++)
			{	
				//既にペアができているものは除外
				if(arryPair.get(jc)[1] != 0)
				{
					continue;
				}
				
				//差の最小を取得
				int nBuf = nMigi - arryPair.get(jc)[0];
				//sHidariが追い越した場合
				if(nBuf < 0)
				{
					break;
				}
				
				nNum = jc;				
			}
			
			//ペアを見つけた場合は格納
			if(nNum >= 0)
			{
				arryPair.get(nNum)[1] = nMigi;
			}

		}
		
		arryPair.stream().forEach((arryPair1) -> {
			//あとでString.substringのときにstr.substring(***[0], ***[1])ですむように1足す。
			arryPair1[0]++;
		});//		//ペアから文字列を抜き出す
//		for(int ic=0; ic<arryPair.size(); ic++)
//		{
//			int beginIndex = arryPair.get(ic)[0];
//			int endIndex   = arryPair.get(ic)[1];
//			
//			if(endIndex == 0)
//			{
//				continue;
//			}
//			
//			arryValue.add(str.substring(beginIndex+1, endIndex));
//		}
			
		
		
		
	}

	//************************************************************************//
	/**
	 *	文字列の特定文字を置換する
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_replace(String str, String sFrom, String sTo)
	{
		
		StringBuilder strEdit = new StringBuilder(str);

		int nNum = 0;
		int length = sFrom.length();
		for(;;)
		{ 
			nNum = strEdit.indexOf(sFrom, nNum);
			if(nNum == -1)
			{
				break;
			}
			strEdit.replace(nNum, nNum+length, sTo);
			nNum += sTo.length();
		}
		return strEdit.toString();
	}
	//************************************************************************//
	/**
	 *	文字列の左側の要らない文字（空白、タブ、全角空白）を取り除く
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_trimLeft(String str)
	{
		if(str.length() == 0 ) return "";
		
		//飛ばす文字列
		char[] cRemoves = {'\t', ' ', '　'}; 
		int nLastN = cRemoves.length;
		Boolean bExist;
		
		int nLast = str.length();
		int nStartIndex = nLast-1;
		for(int ic=0; ic<nLast; ic++)
		{
			//1文字ずつチェックしていく
			//A
			//AB
			//ABC
			//ABC 
			//指定の文字が出てきたら、前の文字列を取得。
			bExist = false;
			for(int nc=0; nc<nLastN; nc++)
			{
				//同じ場合
				if(str.charAt(ic) == cRemoves[nc])
				{
					//フラグを立てる
					bExist = true;
					//ループを抜ける
					break;
				}
			}
			
			//飛ばす文字では無かった場合
			if(bExist == false)
			{
				nStartIndex = ic;
				break;
			}
		}
		
		//文字列を返す
		return str.substring(nStartIndex, nLast);
	}
	public static String string_trimLeft(String str, char[] cRemoves)
	{
		if(str.length() == 0 ) return "";

		//飛ばす文字列
//		char[] cRemoves = {'\t', ' ', '　'}; 
		int nLastN = cRemoves.length;
		Boolean bExist;
		
		int nLast = str.length();
		int nStartIndex = nLast-1;
		for(int ic=0; ic<nLast; ic++)
		{
			//1文字ずつチェックしていく
			//A
			//AB
			//ABC
			//ABC 
			//指定の文字が出てきたら、前の文字列を取得。
			bExist = false;
			for(int nc=0; nc<nLastN; nc++)
			{
				//同じ場合
				if(str.charAt(ic) == cRemoves[nc])
				{
					//フラグを立てる
					bExist = true;
					//ループを抜ける
					break;
				}
			}
			
			//飛ばす文字では無かった場合
			if(bExist == false)
			{
				nStartIndex = ic;
				break;
			}
		}
		
		//文字列を返す
		return str.substring(nStartIndex, nLast);
	}
	//************************************************************************//
	/**
	 *	文字列の右側の要らない文字（空白、タブ、全角空白）を取り除く
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_trimRight(String str)
	{
		if(str.length() == 0 ) return "";

		//飛ばす文字列
		char[] cRemoves = {'\t', ' ', '　'}; 
		int nLastN = cRemoves.length;
		Boolean bExist;
		
		int nLast = str.length();
		int nEndIndex = 0;
		for(int ic=nLast-1; ic>=0; ic--)
		{
			//1文字ずつチェックしていく
			bExist = false;
			for(int nc=0; nc<nLastN; nc++)
			{
				//同じ場合
				if(str.charAt(ic) == cRemoves[nc])
				{
					//フラグを立てる
					bExist = true;
					//ループを抜ける
					break;
				}
			}
			
			//飛ばす文字では無かった場合
			if(bExist == false)
			{
				nEndIndex = ic+1;
				break;
			}
		}
		
		//文字列を返す
		return str.substring(0, nEndIndex);
	}
   	//************************************************************************//
	/**
	 *	文字列の右側の要らない文字（空白、タブ、全角空白）を取り除く
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_trimRight(String str, char[] cRemoves)
	{
		if(str.length() == 0 ) return "";

		//飛ばす文字列
//		char[] cRemoves = {'\t', ' ', '　'}; 
		int nLastN = cRemoves.length;
		Boolean bExist;
		
		int nLast = str.length();
		int nEndIndex = 0;
		for(int ic=nLast-1; ic>=0; ic--)
		{
			//1文字ずつチェックしていく
			bExist = false;
			for(int nc=0; nc<nLastN; nc++)
			{
				//同じ場合
				if(str.charAt(ic) == cRemoves[nc])
				{
					//フラグを立てる
					bExist = true;
					//ループを抜ける
					break;
				}
			}
			
			//飛ばす文字では無かった場合
			if(bExist == false)
			{
				nEndIndex = ic+1;
				break;
			}
		}
		
		//文字列を返す
		return str.substring(0, nEndIndex);
	}
	//************************************************************************//
	/**
	 *	文字列の右側の要らない文字（空白、タブ、全角空白）を取り除く
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_trim(String str)
	{
		if(str.length() == 0 ) return "";
		str = util.string_trimLeft(str);
		return util.string_trimRight(str);
	}
	public static String string_trim(
		String str,			//I		トリムする文字列
		char[] skip_chara	//I		スキップする文字（例：{'\t', ' '}など）
	)
	{
		if(str.length() == 0 ) return "";
		str = util.string_trimLeft(str, skip_chara);
		return util.string_trimRight(str, skip_chara);
	}
	//************************************************************************//
	/**
	 *	まとめ処理
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static void string_trim(
		ArrayList<String> arryStr	//IN/OUT	前後の空白とタブをトリムしたい文字列の配列。結果は同じ配列に置換される
	)
	{
		String str;
		for(int ic=0; ic<arryStr.size(); ic++)
		{
			str = arryStr.get(ic);
			if(str.length() == 0 ){
				arryStr.set(ic, str);
			}
			else{
				str = util.string_trimLeft(str);
				str = util.string_trimRight(str);
				arryStr.set(ic, str);
			}
		}
		
	}
	//************************************************************************//
	/**
	 *	メントは除く
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_remove_comment(
		String	sOneLine,
		String	sComment
	)
	{
		//コメントは除く
		int nCommentNum = sOneLine.indexOf(sComment);
		if(nCommentNum != -1)
		{
			return sOneLine.substring(0, nCommentNum);
		}
		
		return sOneLine;
	}
		

	//************************************************************************//
	/**
	 *	文字列を整数に。数字以外は除く。
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_only_number(String str)
	{
		if(str == null)	return null;
		
		char[] cNumChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};	//, '-', '.'};
		Boolean bWrite;
		Boolean bNum = false;
		Boolean bDot = false;
		Boolean bMinus = false;
		StringBuilder strEdit = new StringBuilder(str);
		  
		for(int ic=0; ic<strEdit.length();  ){
			 //一文字取得
			char charactor = strEdit.charAt(ic);
			bWrite = false;
			for(int jc=0; jc<cNumChars.length; jc++){
				//文字が数字だった場合
				if(charactor == cNumChars[jc]){
					bNum = true;
					bWrite = true;
					break;
				}
				//'.'のとき。ドットは1つしか許されない。場所は何処でもよい。
				if(charactor == '.'){
					if(bDot == false){
						bDot = true;
						bWrite = true;
						break;
					}
				}
				//'-'のとき。マイナスは先頭にしか許されない。'.'の後ろは許されない
				if(charactor == '-'){
					if(ic == 0 && bMinus == false && bDot == false){
						bMinus = true;
						bWrite = true;
						break;
					}
				}
			}
			//数字ではない場合
			if(bWrite == false){
				strEdit.deleteCharAt(ic);
			}
			else{
				ic++;
			}
		}
		
		//マイナスとドットだけの文字列はだめ。数字が入っている必要がある。
		if(bNum == false){
			return null;
		}
		
		return strEdit.toString();	
	}

	//************************************************************************//
	/**
	 *	文字列の特定文字を置換する
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String string_replace(String str, char cFrom, char cTo)
	{
		
		StringBuilder strEdit = new StringBuilder(str);

		for(;;){ 
			int nNum = strEdit.indexOf(String.valueOf(cFrom));
			if(nNum == -1){
				break;
			}
			strEdit.replace(nNum, nNum+1, String.valueOf(cTo));
		}
		return strEdit.toString();
	}

	//************************************************************************//
	/**
	 *	Stringを複数のStringに分解。分解はsFactorの文字で行う。（CSVならsFactorを「,」に指定）
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static int string_factorize1(
			String line, 
			String sFactor, 
			ArrayList<String> sData
			)
	{
		//初期化
		sData.clear();
		
		// 1行をデータの要素に分割
		StringTokenizer st = new StringTokenizer(line, sFactor);

		///// 一行のデータをカンマ分割して取得 /////
		while (st.hasMoreTokens()) {
			sData.add(st.nextToken());
		}
		return 0;
	}
	//************************************************************************//
	/**
	 *	文字列を分解2
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static int string_factorize2(String line, String sFactor, ArrayList<String> sData)
	{
		//初期化
		sData.clear();
		
		int beginIndex = 0;
		int endIndex = 0;
		while(true)
		{
			//sFactorの位置を取得
			endIndex = line.indexOf(sFactor, endIndex);
			if(endIndex == -1)
			{
				int nLineLast = line.length();
				if(beginIndex != nLineLast)
				{
					sData.add(line.substring(beginIndex, nLineLast));
				}
				break;
			}
			//分解した値を取得
			sData.add(line.substring(beginIndex, endIndex));
			endIndex++;
			beginIndex = endIndex;
		}
		
		return 0;
	}

	//************************************************************************//
	/**
	 * 
	*	stringを複数のStringに分解。分解はsFactorの文字で行う。（CSVならsFactorを「,」に指定）
	*	sFactorが続いた場合、その分は飛ばす
	* 
	* string_factorize3_1をお勧めする。
	* 最後の引数はtrueにすれば同じ動作。
	* 
	*	@param
	*	@return
	*	@version
	* 
	*/
	//************************************************************************//
	@Deprecated
	public static int string_factorize3(
		String				line,		//分割対象の文字列 			
		String				sFactor,	//分割位置を指定するの文字列(","など)
		ArrayList<String>	sData		//分割後の文字列の配列
	)
	{
		String lineBuf = line;
		
		int s_index = 0;
		int e_index = 0;
		String sBuf;
		while(true)
		{
			//分割文字の位置を取得
			//
			e_index = lineBuf.indexOf(sFactor, e_index);
			//読み進めていくうちに、もうsFactorが無い場合
			if(e_index == -1)
			{
				int nLineLast = lineBuf.length();
				if(s_index != nLineLast)
				{
					sData.add(lineBuf.substring(s_index, nLineLast));
				}
				break;
			}
			//分解した値を取得
			sBuf = lineBuf.substring(s_index, e_index);
			e_index++;
			s_index = e_index;
			if(sBuf.compareTo("") == 0)
			{
				continue;
			}
			sData.add(sBuf);
		}
		
		return 0;
	}
	//************************************************************************//
	/**
	*	オーバーロード
	* 
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static ArrayList<String> string_factorize3_1(
		String				line,		//I		分割対象の文字列 			
		String				s_factor,	//I		分割位置を指定する文字列(","など)
		boolean				b_delete	//I		分割文字は削除する（true：削除する、false：残す）
	)
	{
		ArrayList<String> arry = new ArrayList<String>();
		string_factorize3_1(
			line,		//I		分割対象の文字列 			
			s_factor,	//I		分割位置を指定する文字列(","など)
			arry,		//O		分割後の文字列の配列
			b_delete	//I		分割文字は削除する（true：削除する、false：残す）
		);
		return arry;
		
	}
	//************************************************************************//
	/**
	*	stringを複数のStringに分解。分解はsFactorの文字で行う。（CSVならsFactorを「,」に指定）
	*	sFactorが続いた場合、その分は飛ばす
	*
	* 例）
	* >>>>abc>>def>>ghi>>
	* 分割文字列は「>>」
	* 
	* 答1）分割文字列を残す場合(b_delete=false)
	* >>    >>abc    >>def    >>ghi    >>
	* 
	* 答2）分割文字列を削除する場合(b_delete=true)
	* 「※空白」    abc    def    ghi    「※空白」
	* 
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static int string_factorize3_1(
		String				line,		//I		分割対象の文字列 			
		String				s_factor,	//I		分割位置を指定する文字列(","など)
		ArrayList<String>	sData,		//O		分割後の文字列の配列
		boolean				b_delete	//I		分割文字は削除する（true：削除する、false：残す）
	)
	{
		String line_buf = line;
		ArrayList<String> buf_div_data = new ArrayList<>();
		int e_index;

		//ループの最大値は文字列の文字数となるため、この値を設定。
		//無限ループ回避のため。
		for(int nc=0; nc<line.length(); nc++)
		{
			//注意！　後ろから検索していく。分割文字の位置を取得
			e_index = line_buf.lastIndexOf(s_factor);
			
			//もし、分割文字が無い場合
			if(e_index == -1)
			{
				//最後の文字列を作って終わる。
				buf_div_data.add(line_buf);
				break;
			}
			else
			{
				String s_buf;
				
				/////////////////
				//	分解値を取得
				/////////////////
				//分解文字列を削除する場合
				if(b_delete == true)
				{
					s_buf = line_buf.substring(e_index + s_factor.length());
				}
				//分解文字列を残す場合
				else
				{
					s_buf = line_buf.substring(e_index);
				}
				
				/////////////////
				//	格納
				/////////////////
				if(s_buf.compareTo("") != 0)
				{
					buf_div_data.add(s_buf);
				}

				///////////////////////
				//	文字列バッファの更新
				///////////////////////
				//もし文字列の先頭だった場合
				if(e_index == 0)
				{
					//そのまま終わる。
					break;
				}
				else
				{
					//次の文字列を決める。
					line_buf = line_buf.substring(0, e_index);
				}
			}
		}
		
		//結果を格納
		for(int ic=buf_div_data.size()-1; ic>=0; ic--)
		{
			//逆順に格納
			sData.add(buf_div_data.get(ic));
		}
		
		return 0;
	}
	//************************************************************************//
	/**
	*	stringを複数のStringに分解。分解はsFactorの文字で行う。sFactorは複数設定可能<br>
	*	ただし、分割文字は消さない。
	*	sFactorが続いた場合、その分は飛ばす<br>
	* 
	* 例）<br>
	* 入力文字列：+10+9-8+4												<br>
	* 分割文字列：[+,-]														<br>
	* 
	* 結果：+10, +9, -8, +4
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public static int string_factorize4(
		String				line,			//分割対象の文字列 			
		ArrayList<String>	sFactorArry,	//分割位置を指定する文字列(","など)
		ArrayList<String>	sData,			//分割後の文字列の配列
		boolean				b_delete		//分割文字は削除する（true：削除する、false：残す）
	)
	{
		ArrayList<String> s_div_data_buf = new ArrayList<>();
		ArrayList<String> s_arry_buf = new ArrayList<>();
		
		//初期文字列を格納
		s_div_data_buf.add(line);
		
		//各分割文字で分けていく		
		for(String s_div : sFactorArry)
		{
			for(String s_div_data : s_div_data_buf )
			{
				util.string_factorize3_1(
					s_div_data,	//I
					s_div,		//I
					s_arry_buf,	//O
					b_delete	//I		分割文字は残す
				);
			}
			//初期化
			s_div_data_buf.clear();
			//一時格納
			s_div_data_buf.addAll(s_arry_buf);
			//初期化
			s_arry_buf.clear();
		}
		
		//結果を格納
		sData.addAll(s_div_data_buf);
		
		return 0;
	}

	//************************************************************************//
	/**
	 *	二つの文字に囲まれる文字列（XMLのタグみたいなの）を探して分割する	<br>
	 *	<あいうえお>　←このようなタグで分割								<br>
	 *
	 *	例:	ABC<Group>テスト</Group>012									<br>
	 *	これを以下のように分解する											<br>
	 *	ABC																	<br>
	 *	<Group>															<br>
	 *	テスト																<br>
	 *	</Group>															<br>
	 *	012																	<br>
	 *
	 *	@param
	 *	@return	void
	 *	@version
	 */
	//************************************************************************//
	public static void string_factorize_by_two_string_from_line(
		String line,			//I		
		String sFirstKakko,		//I		
		String sEndKakko,		//I		
		ArrayList<String> sData	//O		
	)
	{
//		String sFirstKakko = "<";
//		String sEndKakko   = ">";
		
		//囲む文字の長さを取得
		int nFirstKakko = sFirstKakko.length();
		int nEndKakko   = sEndKakko.length();

		//初期化
		sData.clear();

		int beginIndex = 0;

		Boolean bFindBraket = false;
		while(true){
			//"<"を見つけたフラグが立っていない場合
			if(bFindBraket == false){
				//"<"を見付ける
				int nBraketFarstIndex = line.indexOf(sFirstKakko, beginIndex);
				//"<"を見付けた場合
				if(nBraketFarstIndex != -1){
					//見付けたフラグを立てる。次は">"を探す。
					bFindBraket = true;
					//"<"以前の文字列をsDataに格納する
					if(beginIndex != nBraketFarstIndex){
						String sSub = line.substring(beginIndex, nBraketFarstIndex);
						sData.add(sSub);
					}
					//検索開始インデックスを更新
					beginIndex = nBraketFarstIndex + nFirstKakko - 1;
				}	
				//">"が見つからない場合
				else{
					//最後まで取得して、sDataに格納する。
					if(beginIndex != line.length()){
						String sSub = line.substring(beginIndex, line.length());
						sData.add(sSub);
					}
					//ループを抜ける
					break;
				}
			}
			//"<"を見つけたフラグが立っている場合
			else{
				//">"を見付ける
				int nBraketEndIndex = line.indexOf(sEndKakko, beginIndex);
				//">"を見付けた場合
				if(nBraketEndIndex != -1){
					//見付けたフラグを降ろす。
					bFindBraket = false;
					//"<"、">"とその間をタグとして取得し、sDataに格納する。
					if(beginIndex != nBraketEndIndex+1){
						String sSub = line.substring(beginIndex, nBraketEndIndex+1);
						sData.add(sSub);
					}
					//検索開始インデックスを更新
					beginIndex = nBraketEndIndex + 1 + nEndKakko - 1;
				}
				//">"が見つからない場合
				else{
					//最後まで取得して、sDataに格納する。
					if(beginIndex != line.length()){
						String sSub = line.substring(beginIndex, line.length());
						sData.add(sSub);
					}
					//ループを抜ける
					break;
				}
			}
		}
	}

	/***************************************************************************
	 * 
	 * ファイルパスからフォルダとファイルに分ける
	 * 
	 *///***********************************************************************
	public static ArrayList<String> string_filepath_and_folder_divide(
		String s_file_path)				//I		ファイルパス
	{
		s_file_path = util.string_trim(s_file_path);
		
		//ファイル前の区切りを念のため２つ取得
		int n_folder_last_buf1 = s_file_path.lastIndexOf("\\");
		int n_folder_last_buf2 = s_file_path.lastIndexOf("/");
		
		//大きい方を取得
		int n_folder_last = n_folder_last_buf1;
		if(n_folder_last_buf1 < n_folder_last_buf2){
			n_folder_last = n_folder_last_buf2;
		}
		
		//抜き出し
		String s_folder = s_file_path.substring(0, n_folder_last);
		String s_file   = s_file_path.substring(n_folder_last + 1);
		
		//格納
		ArrayList<String> arry = new ArrayList<>(2);		//O		フォルダパスとファイル名
		arry.add(s_folder);
		arry.add(s_file);
		
		return arry;

	}

	//************************************************************************//
	/**
	 *	ファイルの一番最後の文を取得（ただし空白は除く）
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static int string_find_last_line_in_file(String sfile, int[] nLastLine, String[] str)
	{
		//ファイルを開く
		BufferedReader br = util.file_open_read_stream(sfile);
		if(br == null)
		{
			//ファイルがない場合
			return -2;
		}
		
		//1行ずつ読み込み
		// 最終行まで読み込む 
		String line[] = {""};
		String sBuf = "";
		String sLastLineNow = "";
		int nLineCount = 0;
		
		while(util.file_read_next_line(br, line)){
			nLineCount++;
			sBuf = line[0];
			//空白かチェック
			if(util.string_replace(sBuf, " ", "").length() == 0){
				continue;
			}
			else{
				sLastLineNow = line[0];
			}
		}
		str[0] = sLastLineNow;
		nLastLine[0] = nLineCount;
		
		//なし
		return 0;
	}
	/////////////////////////////////////////////////
	//
	//	年月日から曜日を取得
	//	0：	日
	//	1：	月
	//	2：	火
	//	3：	水
	//	4：	木
	//	5：	金
	//	6：	土
	//	うまくいかない。→うまくいった（2014/08/21）
	//	
	/////////////////////////////////////////////////
	//************************************************************************//
	/**
	 *	年月日から曜日を取得
	 *	0：	日
	 *	1：	月
	 *	2：	火
	 *	3：	水
	 *	4：	木
	 *	5：	金
	 *	6：	土
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static int time_get_week(int nYear, int nMonth, int nDay)
	{
		Integer[] nSunday = new Integer[3];
		Integer[] nOneDay = new Integer[3];
		
		//基準となる日曜日。2014/08/11(日)。この日を指定日に近づけていく。
		nSunday[0] = 2014;
		nSunday[1] = 8;
		nSunday[2] = 10;	//日曜日
		
		nOneDay[0] = nYear;
		nOneDay[1] = nMonth;
		nOneDay[2] = nDay;
		
		//二つの日の間が何日か算出
		int nDiff = time_get_YearMonthDay_Between(nSunday, nOneDay);

		//曜日を算出
		if(nDiff >= 0)
		{
			//7で割り、余りを出力
			return (nDiff)%7;
		}
		else
		{
			return 6 + (nDiff+1)%7;
		}
	}
	//************************************************************************//
	/**
	 *	年月日から曜日を取得
	 *	0：	日
	 *	1：	月
	 *	2：	火
	 *	3：	水
	 *	4：	木
	 *	5：	金
	 *	6：	土
	 *
	 *	@param	day	Integerの配列で指定できる
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static int time_get_week(Integer[] day)
	{
		return time_get_week(day[0], day[1], day[2]);
	}
	//************************************************************************//
	/**
	 *	週を日、月、火、水、木、金、土の文字で取得する
	 *
	 *	@param	day	Integerの配列で指定できる
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String time_get_Week_Chara(Integer[] day)
	{
		int nWeek = time_get_week(day[0], day[1], day[2]);
		
		switch(nWeek)
		{
			case 0: return "日";
			case 1: return "月";
			case 2: return "火";
			case 3: return "水";
			case 4: return "木";
			case 5: return "金";
			case 6: return "土";	
		}
		
		return "無";
	}
	//************************************************************************//
	/**
	 *	稼働日(月火水木金)を判定
	 *
	 *	@param	true：稼働日(月火水木金)　false：土日
	 *	@return	
	 *	@version
	 */
	//************************************************************************//
	public static Boolean time_check_稼働日(Integer[] nInputAndNextDay)
	{
		int iy = nInputAndNextDay[0];
		int im = nInputAndNextDay[1];
		int id = nInputAndNextDay[2];
		
		//曜日を調べ、土日なら除く
		int nWeek = util.time_get_week(iy, im, id);
		if(nWeek == 0 || nWeek == 6)
		{
			return false;
		}
		
		return true;
	}

	//************************************************************************//
	/**
	 *	年月から、その月の最終日を判定（閏年も考慮）
	 *
	 *	@param	
	 *	@return	最終日（28か29か30か31）
	 *	@version
	 */
	//************************************************************************//
	public static int time_get_month_last_day(int nYear, int nMonth)
	{
		switch(nMonth)
		{
			case  1: return 31;
			case  2: break;		//閏年を計算
			case  3: return 31;
			case  4: return 30;
			case  5: return 31;
			case  6: return 30;
			case  7: return 31;
			case  8: return 31;
			case  9: return 30;
			case 10: return 31;
			case 11: return 30;
			case 12: return 31;
			default: return 0;
		}
		
		///// 閏年判定(グレゴリウス暦) /////
		int nUruDoshi = 28;
		if(time_check_閏年(nYear) == true)
		{
			nUruDoshi = 29;
		}

		return nUruDoshi;
	}

	//************************************************************************//
	/**
	 *	閏年を判定
	 *
	 *	@param	nYear	判定する西暦
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static Boolean time_check_閏年(int nYear)
	{
		///// 閏年判定(グレゴリウス暦) /////
		if(nYear%4 == 0)
		{
			//年数が100で割り切れ、かつ400では割り切れない年は平年とする
			if(nYear%100 == 0 && nYear%400 != 0)
			{
				//閏年ではない
				return false;
			}
			
			//閏年
			return true;
		}
		
		//閏年ではない
		return false;
	}

	//************************************************************************//
	/**
	 *	年月日を過去から現在に向かって取得
	 *
	 *	入力された日の次の日を出力する。
	 *	次の日が指定した最後の日を超えた場合、falseを出力する
	 *	入力された日の月が12月を超えていてもfalseを出力する
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static Boolean time_get_next_year_month_day(
		Integer[] nLastDay, 
		Integer[] nInputAndNextDay)
	{
		//リミットの年月日
		int nYearE  = nLastDay[0];
		int nMonthE = nLastDay[1];
		int nDayE   = nLastDay[2];

		//入力の年月日。この日の次の日を求める
		int nYearS  = nInputAndNextDay[0];
		int nMonthS = nInputAndNextDay[1];
		int nDayS   = nInputAndNextDay[2];
		
		//次の日を求める
		nDayS++;
		
		//繰上げ判定
		if(nDayS > 28)
		{
			//月の最終日判定
			int nLimitDay = time_get_month_last_day(nYearS, nMonthS);
	
			//日の繰り上がり判定
			if(nDayS > nLimitDay)
			{
				nDayS = 1;
				nMonthS++;
			}
			//月の繰り上がり判定
			if(nMonthS > 12)
			{
				nMonthS = 1;
				nYearS++;
			}
		}
		
 		//戻り値を設定
    	nInputAndNextDay[0] = nYearS;
    	nInputAndNextDay[1] = nMonthS;
    	nInputAndNextDay[2] = nDayS;
		
		///// 最後の日を超えていないかチェック /////
    	//年が過ぎている場合
    	if(nYearS > nYearE)
    	{
    		return false;
    	}
    	//年は同じだが、月が過ぎている場合
    	else if(nYearS >= nYearE && nMonthS > nMonthE)
    	{
    		return false;
    	}
    	//年と月は同じだが、日が過ぎている場合
    	else if(nYearS >= nYearE && nMonthS >= nMonthE && nDayS > nDayE)
    	{
    		return false;
    	}
	
	    return true;
	}

	//************************************************************************//
	/**
	 *	2つの指定日は何日離れているか返す
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static int time_get_YearMonthDay_Between(Integer[] nFromDay, Integer[] nToDay)
	{
		int  nYearFrom  = nFromDay[0];
		int  nMonthFrom = nFromDay[1];
		int  nDayFrom   = nFromDay[2];
		
		int nYearTo  = nToDay[0];
		int nMonthTo = nToDay[1];
		int nDayTo   = nToDay[2];
		/////////////////////////////
		//まず年を基準と合わせる。
		/////////////////////////////
		//2017/12/31→2014/12/31
		//この間の差を計算する。※上では差は+3年
		int nDiff = 0;		
		//年について基準日の方が小さい場合
		if(nYearFrom < nYearTo)
		{		
			//年のインクリメントループ
			for(int ic=nYearFrom; ic<nYearTo; ic++)
			{
				if(util.time_check_閏年(ic)==true)
				{
					//閏年
					nDiff += 366;
				}
				else
				{
					//閏年ではない年
					nDiff += 365;
				}
			}
		}
		else
		{
			//年のデクリメントループ
			for(int ic=nYearFrom; ic>nYearTo; ic--)
			{
				if(util.time_check_閏年(ic)==true)
				{
					//閏年
					nDiff -= 366;
				}
				else
				{
					//閏年ではない年
					nDiff -= 365;
				}
			}
		}
		
		/////////////////////////////
		//月と日を合わせる。
		/////////////////////////////
		//月について基準日の方が小さい場合
		if(nMonthFrom < nMonthTo)
		{
			//その月の最終日までの日数を足す。
			nDiff += util.time_get_month_last_day(nYearTo, nMonthFrom) - nDayFrom;
			
			for(int ic=nMonthFrom+1; ic<nMonthTo; ic++)
			{
				nDiff += util.time_get_month_last_day(nYearTo, ic);
			}
			
			nDiff += nDayTo;
		}
		else if(nMonthFrom > nMonthTo)
		{
			//その月の最終日までの日数を足す。
			nDiff -= nDayFrom;
			
			for(int ic=nMonthFrom-1; ic>nMonthTo; ic--)
			{
				nDiff -= util.time_get_month_last_day(nYearTo, ic);
			}
			
			nDiff -= util.time_get_month_last_day(nYearTo, nMonthTo) - nDayTo;
		}
		else
		{
			nDiff += nDayTo - nDayFrom;
		}
		
		return nDiff;
	}

	//************************************************************************//
	/**
	 *	指定日から数日前または数日後の年月日を返す
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static Boolean time_get_YearMonthDay_Move(
		Integer[] nInputAndMoveDay,
		int nMove)
	{
		//入力の年月日。この日の次の日を求める
		int nYearS  = nInputAndMoveDay[0];
		int nMonthS = nInputAndMoveDay[1];
		int nDayS   = nInputAndMoveDay[2];
		
		//移動した日を求める
		nDayS += nMove;
		
		/////繰上げ判定/////
		//月の最終日判定
		int nLimitDay = time_get_month_last_day(nYearS, nMonthS);
		while(true)
		{
	
			//月の最終日より大きい場合
			if(nDayS > nLimitDay)
			{
				//日の繰り上がり判定
				nDayS -= nLimitDay;
				
				//月は繰り上がり
				nMonthS++;
					
				//年の繰り上がり判定
				if(nMonthS > 12)
				{
					nMonthS = 1;
					nYearS++;
				}
				//月の最終日を更新
				nLimitDay = time_get_month_last_day(nYearS, nMonthS);
			}
			//1よりも小さい場合はさかのぼる
			else if(nDayS < 1)
			{
				//月は遡る
				nMonthS--;
					
				//年の遡り判定
				if(nMonthS < 1)
				{
					nMonthS = 12;
					nYearS--;
				}
				//月の最終日取得
				nLimitDay = time_get_month_last_day(nYearS, nMonthS);
				nDayS += nLimitDay;
			}
			else
			{
				break;
			}
		}
		
 		//戻り値を設定
    	nInputAndMoveDay[0] = nYearS;
    	nInputAndMoveDay[1] = nMonthS;
    	nInputAndMoveDay[2] = nDayS;
	
	    return true;
	}

	//************************************************************************//
	/**
	 *	日付、時間を整数で取得
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static void time_get_date_integer(Integer[] dates)
	{
		Calendar rightNow = Calendar.getInstance(); //インスタンス化

		int y =  rightNow.get(Calendar.YEAR);			//年を取得
		int mo = rightNow.get(Calendar.MONTH); mo++;	//月を取得
		int d =  rightNow.get(Calendar.DATE);			//現在の日を取得
		int h = rightNow.get(Calendar.HOUR_OF_DAY);			//時を取得
		int m = rightNow.get(Calendar.MINUTE);				//分を取得
		int s = rightNow.get(Calendar.SECOND);				//秒を取得
		
		dates[0] = y;
		dates[1] = mo;
		dates[2] = d;
		dates[3] = h;
		dates[4] = m;
		dates[5] = s;
	}

	//************************************************************************//
	/**
	 *	日付、時間を文字列で取得。											<br>
	 *	引数が0,　2,　"/"なら、"2014/03/30"となる。						<br>
	 *	引数が1, 2, "/"なら、"03/30"となる。								<br>
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String time_get_date_string(int nStart, int nEnd, String sDifFactor)
	{
		//日にち、時間を整数で取得
		Integer[] dates = new Integer[6];
		time_get_date_integer(dates);
		int y =  dates[0];	//年を取得
		int mo = dates[1];	//月を取得
		int d =  dates[2];	//現在の日を取得
		int h = dates[3];	//時を取得
		int m = dates[4];	//分を取得
		int s = dates[5];	//秒を取得
		
		ArrayList<String> arryDate = new ArrayList<String>();
		
		//年
		String sYear = String.valueOf(y);
		arryDate.add(sYear);
		
		//月
		String sMonth;
		if(mo < 10)	sMonth = "0" + String.valueOf(mo);
		else		sMonth = String.valueOf(mo);
		arryDate.add(sMonth);
		
		//日
		String sDay;
		if(d < 10)	sDay = "0" + String.valueOf(d);
		else		sDay = String.valueOf(d);
		arryDate.add(sDay);
		
		//時間
		String sHour;
		if(h < 10)	sHour = "0" + String.valueOf(h);
		else		sHour = String.valueOf(h);
		arryDate.add(sHour);
		
		//分
		String sMin;
		if(m < 10)	sMin = "0" + String.valueOf(m);
		else		sMin = String.valueOf(m);
		arryDate.add(sMin);
		
		//秒
		String sSec;
		if(s < 10)	sSec = "0" + String.valueOf(s);
		else		sSec = String.valueOf(s);
		arryDate.add(sSec);

		//文字列作成
		String sTime = "";
		int nData = arryDate.size();
		if(nStart >= nData)
		{
			nStart = nData - 1;
		}
		if(nEnd >= nData)
		{
			nEnd = nData - 1;
		}
		for(int ic=nStart; ic<=nEnd; ic++)
		{
			//最初ではない場合
			if(ic != nStart)
			{
				//区切りを追加
				sTime += sDifFactor;
			}
			//文字列追加
			sTime += arryDate.get(ic);
		}
		
		return sTime;
	}

	//************************************************************************//
	/**
	 *	ダウンロードファイル名を作成(2011-12-27_stockT.csv)
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String create_date_name2(int nYear, int nMonth, int nDay, String str)
	{
		String sY = String.valueOf(nYear);
		
		String sM = "";
		if(nMonth < 10)
		{
			sM = "0" + String.valueOf(nMonth);
		}
		else
		{
			sM = String.valueOf(nMonth);
		}
		String sD = "";
		if(nDay < 10)
		{
			sD = "0" + String.valueOf(nDay);
		}
		else
		{
			sD = String.valueOf(nDay);
		}		
		return String.format("%s%s%s%s%s", sY, str, sM, str, sD);
	}	
	public static String create_date_name2(Integer[] date, String str)
	{
		return create_date_name2(date[0], date[1], date[2], str);
	}	

	/***************************************************************************
	 * 
	 * 配列をコピー
	 * 
	 * https://www.sejuku.net/blog/14067
	*/
	//**************************************************************************
	public static void copy_array(
		int[] arry_a,	//I		コピー元
		int[] arry_b)	//O		コピー先
	{
//		arry_b = arry_a.clone();

		System.arraycopy(
			arry_a,			//コピー元
			0,				//コピー元のスタート位置
			arry_b,			//コピー先
			0,				//コピー先のスタート位置
			arry_a.length);	//コピーの個数

	}
	
	//************************************************************************//
	/**
	 *	2つの配列を比較する。
	 *	最初の配列Aと、次の配列Bを比較。
	 *	Aにあって、Bにもあるものはout_arry_same,
	 *	Aにあって、Bに無いものはout_arry_diff
	 * 
	 *	※Bにあって、Aに無いものは出力しない。
	 *	※文字列は完全一致
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static void compare_array_string(
		ArrayList<String>	in_arry_first,		//I		配列A。{AAA, BBB, CCC, DDD}
		ArrayList<String>	in_arry_second,		//I		配列B。{AAA, EEE}
		ArrayList<String>	out_arry_same,		//O		配列AとBどちらもあるもの。{AAA}
		ArrayList<String>	out_arry_diff		//O		配列AにありBに無いもの。{BBB, CCC, DDD}
	)
	{
		//配列Aループ
		boolean b_exist;
		for (String sAfter : in_arry_first) {
			b_exist = false;
			
			//配列Bループ
			for(int nc=0; nc<in_arry_second.size(); nc++)
			{
				//同じ文字列の場合　※完全一致
				if(sAfter.equals(in_arry_second.get(nc)) == true)
				{
					b_exist = true;
					break;
				}
			}
			
			//配列AにありBに無いものを追加
			if(b_exist == false)
			{
				out_arry_diff.add(sAfter);
			}
			//配列AとBどちらもあるもの
			else
			{
				out_arry_same.add(sAfter);
			}
		}
	}
	//************************************************************************//
	/**
	 *	日付、時間を取得
	 *	例：2014/03/24 10:41:00
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String time_get_date()
	{
		///// 日付を取得 /////
		return DateFormat.getDateTimeInstance().format(new Date());
	}
	
	//************************************************************************//
	/**
	 *	時間を測定(ms)
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static long time_get_now()
	{
//		Date date = new Date();		
//		return date.getTime()/1000;	//秒

		return System.currentTimeMillis();
	}	

	//************************************************************************//
	/**
	 *	時間を測定(s)
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static long time_get_now_second()
	{
		return System.currentTimeMillis()/1000;
	}
	
	//************************************************************************//
	/**
	 *	pidを取得
	 * プロセスIDは部分一致でも取得する
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static void get_pid(
		String				s_process_name,	//I		取得するPIDの名前 ※タスクマネージャ内の名前
		ArrayList<String>	arryPID			//O		取得したPID結果。既存配列に追加。
		)
	{
//		final String const_tasklist = "tasklist";	
		final String const_tasklist = "tasklist /FO CSV";
		
		/////////////////////////////
		//	PID取得の前準備
		/////////////////////////////
		//全てのPIDを取得
		ArrayList<String> arry_all_pid = util.exe_program(const_tasklist);

		//部分一致チェック
		for (String sLine : arry_all_pid) {
			if(sLine == null) continue;
			
			//部分一致
			if(sLine.contains(s_process_name) == true)
			{
				ArrayList<String> sData = new ArrayList<String>();
//				util.string_factorize3_1(sLine, " ", sData, true);
				util.string_factorize3_1(sLine, ",", sData, true);
				arryPID.add(sData.get(1));
			}
		}
	}
	

	//************************************************************************//
	/**
	 *	外部exeを起動
	 *	戻り値は、コマンドプロンプト実行後に画面に出力される内容
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static ArrayList<String> exe_program(String str)
	{
    	ArrayList<String> arryMessage = new ArrayList<String>();
    	
	    try {
	    	//実行
	    	Process p;
	        Runtime rt = Runtime.getRuntime();
	        p = rt.exec(str);
	        
	        //文字列取得
	        //Create Inputstream for Read Processes
	        //http://metoojava.wordpress.com/2010/02/20/java-code-to-view-system-task-list/
	        InputStream inputStream = p.getInputStream();
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
	        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	        
	        //Read the processes from sysrtem and add & as delimeter for tokenize the output
	        String line = bufferedReader.readLine();
	        while (line != null)
	        {
		        line = bufferedReader.readLine();
		        arryMessage.add(line);
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    
	       return arryMessage;
	}

	//************************************************************************//
	/**
	 *	ファイルの存在チェック
	 *
	 *	http://www.ne.jp/asahi/hishidama/home/tech/java/files.html#move
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static boolean file_exist_2(String str)
	{
    	try
    	{
    		//"は変換
    		str = util.string_replace(str, "\"", "");
    		// ファイルパス  
//    		Path path = new File(str).toPath();
//    		return Files.exists(path);
    		return new File(str).exists();
    	}
    	catch(Exception e)
    	{
    		e.getStackTrace();
    		return false;    		
		}
	}

	//************************************************************************//
	/**
	 *	ファイルを移動
	 *
	 *	http://www.ne.jp/asahi/hishidama/home/tech/java/files.html#move
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static boolean file_move(String orgFilePath, String destDir)
    {
    	try
    	{
//    		// 移動元なるファイルパス  
//    		Path from = new File(orgFilePath).toPath();
//	    	// 移動先ディレクトリ  
//	    	Path to   = new File(destDir).toPath();
//	    	// 移動  
//	    	Files.move(from, to);
//	    	return true;
    		

            File file1 = new File(orgFilePath);
            File file2 = new File(destDir);
			return file1.renameTo(file2);
			//  System.out.println("移動成功");
			//System.out.println("移動失敗");
    	}
    	catch(Exception e)
    	{
    		e.getStackTrace();
    		return false;    		
		}
    }  

	//************************************************************************//
	/**
	 *	ファイルをリネーム								<br>
	 *	パスは""で囲まないようにする。					<br>
	 *	本機能はリネームのみ。ファイルの移動は行わない。<br>
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static boolean file_rename(String FromPath, String NewName)
    {
    	try
    	{
    		//存在するかチェック
    		if(util.file_exist_2(FromPath) == false)
    		{
    			return false;
    		}
    		
    		// 移動元なるファイルパス  
    		File from = new File(FromPath);
//    		Path from = new File(FromPath).toPath();
//    		Path from = Paths.get(FromPath);
//    		Path fromPath = from.toPath();

	    	// 移動先ファイル名  
//    		Path to   = new File(ToPath).toPath();
//    		Path to   = Paths.get(ToPath);
//    		File To = new File(ToPath);
//    		File To = new File(from.toPath().getParent() + "\\" + NewName);
    		File To = new File(from.getParent() + "\\" + NewName);

    		// リネーム
    		from.renameTo(To);
//	    	Files.copy(from, from.resolveSibling(ToPath));
//    		Files.move(from, target, options);
	    	return true;
    	}
    	catch(Exception e)
    	{
    		e.getStackTrace();
    		return false;    		
		}
    }  
	/*/////////////////////////////////////////////////
	//
	//	単純移動平均
	 * 引数
		double[]	dX 		[IN]	Yの値	
		double[]	dY 		[IN]	Xの値
		int			nStart	[IN]	配列の何番目から算出するか		
		int			nNumber	[IN]	計算に使用する個数
		double[]	dResult	[OUT]	0:単純移動平均値
		
		戻り値
		0	success
		-1	error	Xの数が足りない
		-2	error	Yの数が足りない
		-3	error	スタートがマイナスになっている
	//
	*//////////////////////////////////////////////////
	//************************************************************************//
	/**
	 *	単純移動平均
	 *
	 *	@param	dX 		[IN]	Yの値
	 *	@param	dY 		[IN]	Xの値
	 *	@param	nStart	[IN]	配列の何番目から算出するか
	 *	@param	nNumber	[IN]	計算に使用する個数
	 *	@param	dResult	[OUT]	0:単純移動平均値
	 *
	 *	@return	0	success
	 *	-1	error	Xの数が足りない
	 *	-2	error	Yの数が足りない
	 *	-3	error	スタートがマイナスになっている	
	 *
	 *	@version
	 */
	//************************************************************************//
	public static int kabu_calc_simple_moving_average(Double[] dX, Double[] dY, int nStart, int nNumber, Double[] dResult)
	{
		//点の数を取得
		int nNumX = dX.length;
		int nNumY = dY.length;
		int nEnd = nStart + nNumber;
		if(nNumX < nEnd)
		{	//Xの数が足りない
//			return -1;
			nEnd = nNumX;
			nNumber = nEnd - nStart;
		}
//		if(nNumY < nEnd)
//		{	//Yの数が足りない
//			return -2;
//		}
		if(nStart < 0)
		{	//スタートがマイナスになっている
			nStart = 0;
			nNumber = nEnd - nStart;
//			return -3;
		}
		
		double dSigmaY = 0.0;
		
		for(int ic=nStart; ic<nEnd; ic++)
		{
			dSigmaY  += dY[ic];
		}

		dResult[0] = dSigmaY/nNumber;

		
		return 0;
	}	

	//************************************************************************//
	/**
	 *	単純移動平均
	 *
	 *	@param	dX 		[IN]	Xの値
	 *	@param	dY 		[IN]	Yの値
	 *	@param	nNumber	[IN]	計算に使用する個数
	 *	@param	dResult	[OUT]	単純移動平均値

	 *	@return	0	success
	 *	-1	error	Xの数が足りない
	 *	-2	error	Yの数が足りない
	 *	-3	error	スタートがマイナスになっている
	 *
	 *	Double dValues[];
	 *	dValues = m_ValueHistry.toArray(new Double[0]);
	 *
	 *	@version
	 */
	//************************************************************************//
	public static int kabu_calc_simple_moving_average(Double[] dX, Double[] dY, int nNumber, Double[] dResult)
	{
		//点の数を取得
		int nNumX = dX.length;
//		int nNumY = dY.length;
		
		for(int ic=0; ic<nNumX; ic++)
		{
			double dSigmaY = 0.0;
			int nCount = 0;
			
			if(ic < nNumber)
			{
				for(int jc=ic; ; --jc)
				{
					if(jc<0 || nCount >= nNumber)
					{
						break;
					}
					dSigmaY  += dY[jc];
					nCount++;
				}
				dResult[ic] = dSigmaY/(double)nCount;
			}
			else
			{
				//1つ前の結果から最初の値を引き、次の値を足して割る。
				dResult[ic] = dResult[ic-1] + (-dY[ic-nNumber] + dY[ic])/(double)nNumber;
			}
		}

		return 0;
	}	

	//************************************************************************//
	/**
	 *	ファイルの存在チェック
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	@Deprecated
	public static Boolean file_exist(String sfile_path)
	{
		///// ファイルの存在チェック /////
		File file = new File(sfile_path);
		return file.exists();
	}
	//************************************************************************//
	/**
	 *	ファイルを書き込みで開く <br>
	 *	bAppend = true  -> 追記 <br>
	 *	bAppend = false -> 新規ファイル <br>
	 *
	 *	//使い方
	 *	String strSignal = "D:\\DO\\Stock\\02_signal_data\\01_Signals_All.log"; <br>
	PrintWriter pw = util.file_open_write(strSignal, false); <br>
	 *	pw.printf("%100s,", ""); <br>
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static PrintWriter file_open_write(String sfile_path, Boolean bAppend)
	{
		try
		{
			///// ファイルを新規作成or上書き /////
			File file = new File(sfile_path);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file, bAppend)));
			return pw;
		}
		catch (IOException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return null;
		}
	}
	//************************************************************************//
	/**
	 *	ファイル（ストリームタイプ）を開く
	 *	ストリームタイプとは、ファイル先頭から読み込み、後戻りはできないタイプ
	 *
	 *	@param
	 *	@return	ファイルがある場合：BufferedReader
	 *			ファイルが無い場合：null
	 *	@version
	 */
	//************************************************************************//
	public static BufferedReader file_open_read_stream(String sfile_path){
		try{
			File csv = new File(sfile_path); // ファイルを開く
			return  new BufferedReader(new FileReader(csv));
		}
		catch (Exception e) {
//			System.out.println(e);
			return null;
		}	
	}

	//************************************************************************//
	/**
	 *	ファイル（ランダムアクセスタイプ）を開く
	 *	ランダムアクセスタイプとは、seekによってファイル読み込み書き込みを自由な位置で行えるタイプ
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static RandomAccessFile file_open_random_access(String sfile_path)
	{
		try {
			return new RandomAccessFile(sfile_path, "r");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}

	}
//	//************************************************************************//
//	/**
//	 *	1ファイルを複数ファイルに分割
//	 *	改良版
//	 *
//	 *	@param	sOneFile		分割前のファイルパス
//	 *	@param	sStartSimbol	分割スタートの文字列
//	 *	@return
//	 *	@version
//	 */
//	//************************************************************************//
//	public static Boolean file_divide(
//			String sOneFile,
//			String sStartSimbol, 
//			String  sEndSimbol, 
//			String sPath, 
//			String sFileNameFirst, 
//			String sFileNameEnd)
//	{		
//		try
//		{
//			//ファイルを開く
//			BufferedReader br = util.open_file_stream(sOneFile);
//			if(br == null)
//			{
//				//ない場合
//				return false;
//			}
//			
//			//エンドシンボルが設定されているか判定
//			Boolean bExistEndSimbol = false;
//			if(sEndSimbol.length() != 0)
//			{
//				bExistEndSimbol = true;
//			}
//			
//			// 最終行まで読み込む 
//			String line[] = {""};
//			Boolean bWriteFlag = false;
//			PrintWriter pw_Divide = null;
//			int nCount = 0;
//			while(util.file_read_next_line(br, line))
//			{
//				//分割の前文字列を検索
//				int nIndexStart = line[0].indexOf(sStartSimbol);
//				//分割の後文字列を検索
//				int nIndexEnd   = line[0].indexOf(sEndSimbol);
//				//分割の前文字列が見つかった場合
//				if(nIndexStart != -1)
//				{
//					///// 前回のpwがあるならば、閉じる /////
//					if(pw_Divide != null)
//					{
//						pw_Divide.close();
//					}
//				
//					///// ファイル名を取得（StartSimbolの直後としておく）　/////
//					String sFilePath = line[0].substring(nIndexStart+sStartSimbol.length(), line[0].length());
//					
//					///// 分割ファイルを作成 /////
//					File file = new File(sFilePath);
//					pw_Divide = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
//					
//					bWriteFlag = true;
//					
//					//画面出力
//					nCount++;
//					if(nCount%100 == 0)
//					{
//						System.out.println(String.format("%d", nCount));
//					}
//					
//					continue;
//				}	
//				
//				//エンドシンボルが指定されている場合
//				if(bExistEndSimbol == true)
//				{
//					//中にエンドシンボルが見つかり、しかも書き込み中の場合
//					if(nIndexEnd != -1 && bWriteFlag == true)
//					{
//						//ファイル最後のため、閉じる
//						pw_Divide.close();
//						
//						//書き込みモードを終わる
//						bWriteFlag = false;
//					}
//				}
//				
//				if(bWriteFlag == true)
//				{
//					//空白は飛ばす
//					if(line[0].length() == 0)
//					{
//						continue;
//					}
//					///// 書き込み /////
//					pw_Divide.println(line[0]);
//				}
//			}
//			//閉じる
//			br.close();
//			//閉じる
//			pw_Divide.close();
//		}
//		catch(Exception e)
//		{
//			return false;
//		}	
//		
//		return true;
//	}

	//************************************************************************//
	/**
	 *	1ファイルを複数ファイルに分割
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static Boolean file_divide(String sOneFile, String sStartSimbol, String  sEndSimbol)
	{		
		try
		{
			//ファイルを開く
			BufferedReader br = util.file_open_read_stream(sOneFile);
			if(br == null)
			{
				//ない場合
				return false;
			}
			
			//エンドシンボルが設定されているか判定
			Boolean bExistEndSimbol = false;
			if(sEndSimbol.length() != 0)
			{
				bExistEndSimbol = true;
			}
			
			// 最終行まで読み込む 
			String line[] = {""};
			Boolean bWriteFlag = false;
			PrintWriter pw_Divide = null;
			int nCount = 0;
			while(util.file_read_next_line(br, line))
			{
				int nIndexStart = line[0].indexOf(sStartSimbol);
				int nIndexEnd   = line[0].indexOf(sEndSimbol);
				if(nIndexStart != -1)
				{
					///// 前回のpwがあるならば、閉じる /////
					if(pw_Divide != null)
					{
						pw_Divide.close();
					}
				
					///// ファイル名を取得（StartSimbolの直後としておく）　/////
					String sFilePath = line[0].substring(nIndexStart+sStartSimbol.length(), line[0].length());
					
					///// 分割ファイルを作成 /////
					File file = new File(sFilePath);
					pw_Divide = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
					
					bWriteFlag = true;
					
					//画面出力
					nCount++;
					if(nCount%100 == 0)
					{
						System.out.println(String.format("%d", nCount));
					}
					
					continue;
				}	
				
				//エンドシンボルが指定されている場合
				if(bExistEndSimbol == true)
				{
					//中にエンドシンボルが見つかり、しかも書き込み中の場合
					if(nIndexEnd != -1 && bWriteFlag == true)
					{
						//ファイル最後のため、閉じる
						pw_Divide.close();
						
						//書き込みモードを終わる
						bWriteFlag = false;
					}
				}
				
				if(bWriteFlag == true)
				{
					//空白は飛ばす
					if(line[0].length() == 0)
					{
						continue;
					}
					///// 書き込み /////
					pw_Divide.println(line[0]);
				}
			}
			//閉じる
			br.close();
			//閉じる
			pw_Divide.close();
		}
		catch(Exception e)
		{
			return false;
		}	
		
		return true;
	}

	//************************************************************************//
	/**
	 *	ファイルを削除
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static Boolean file_delete(String[] sFilePath)
	{
		for(int ic=0;ic<sFilePath.length; ic++)
		{
			File file = new File(sFilePath[ic]);
			
			if(file.exists() == true)
			{
				file.delete();
			}
		}
		
		return true;
	}

	//************************************************************************//
	/**
	 *	textファイルを1ファイルに結合。
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static Boolean file_unite(String sUniteFile, String[] sPieceFiles, String sFileKey)
	{
		///// 最後に1ファイルにする /////
		File file = new File(sUniteFile);
		PrintWriter pw_Unite;
		try
		{
			//新規作成
			pw_Unite = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));
			
			/////////////////////
			// 各ファイルを読み込み 
			/////////////////////
			for (String sfile_path : sPieceFiles) {
				//ファイルを開く
				BufferedReader br = util.file_open_read_stream(sfile_path);
				if(br == null)
				{
					//ない場合
					continue;
				}
				
				//最初にキーとパス名を書く
				pw_Unite.println(String.format("%s%s", sFileKey, sfile_path));
				
				// 最終行まで読み込む 
				String line[] = {""};
				int nLineCount = 0;
				while(util.file_read_next_line(br, line))
				{
					//画面出力
					nLineCount++;
					if(nLineCount%100 == 0)
					{
						System.out.println(String.format("%d", nLineCount));
					}
					
					///// 結合ファイルに書き込み /////
					pw_Unite.println(line[0]);
				}
				//閉じる
				br.close();
				
				//改行
				pw_Unite.println("");
			}
			
			//閉じる
			pw_Unite.close();
		}
		catch(Exception e)
		{
			return false;
		}	
		
		return true;
	}
	//************************************************************************//
	/**
	 *	ファイルから行を取得する。								<br>
	 *	探したい行に含まれる文字列を指定する。					<br>
	 * 
	 * 例：														<br>
	 * value1 = "aaaaa"										<br>
	 * value2 = "bbbbb"										<br>
	 * 
	 * 探したい行に含まれる文字列	 = value2					<br>
	 * 帰ってくる行					 = value2 = "bbbbb"		<br>
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static ArrayList<String> file_read_easy_by_word(
		String in_s_file_path,		//I		ファイルパス
		String in_s_match_word		//I		探したい行に含まれる文字列
	){
		BufferedReader br = file_open_read_stream(in_s_file_path);
		
		if(br == null){
			return null;
		}
		
		ArrayList<String> out_arry = new ArrayList<>();
		String line[] = {""};
		while(util.file_read_next_line(br, line))				
		{															
		   //処理を書く												
		   if(line[0].contains(in_s_match_word) == true)
		   {
			   out_arry.add(line[0]);
		   }
		}
		
		return out_arry;
	}
	//************************************************************************//
	/**
	 *	ファイルから行を取得する。									<br>
	 *	探したい行に含まれる文字列を指定する。						<br>
	 * 
	 * 例：															<br>
	 * value1 = "aaaaa"											<br>
	 * value2 = "bbbbb"											<br>
	 * value1 = "ccccc"											<br>
	 * 
	 * 探したい行に含まれる文字列	 =	value1, value2			<br>
	 * 帰ってくる行					 =	value1は aaaaa, ccccc		<br>
	 *									value2は bbbbb				<br>
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static ArrayList<ArrayList<String>> file_read_easy_by_words(
		String				in_s_file_path,		//I		ファイルパス
		ArrayList<String>	in_s_match_words	//I		探したい行に含まれる検索文字列
	){
		BufferedReader br = file_open_read_stream(in_s_file_path);
		
		if(br == null){
			return null;
		}
		
		int n_words_length = in_s_match_words.size();
		
		//戻り値の器を作成
		ArrayList<ArrayList<String>> out_arry2 = new ArrayList<>();
		for(int ic=0; ic<n_words_length; ic++)
		{
			out_arry2.add(new ArrayList<String>());
		}	
		
		//ファイルを先頭から読み込み
		String line[] = {""};
		while(util.file_read_next_line(br, line))				
		{
			//検索
			for(int ic=0; ic<n_words_length; ic++)
			{
				//見つかった時、行を追加
				String s_search_word_buf = in_s_match_words.get(ic);
				if(line[0].contains(s_search_word_buf) == true)
				{
					out_arry2.get(ic).add(line[0]);
				}
			}
		}
		
		try {
			br.close();
		} catch (IOException ex) {
			Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return out_arry2;
	}
	//************************************************************************//
	/**
	 *	ファイルを一行ずつ読込む <br>
	 *
	 *	下のように使う <br>
	 *  String line[] = {""};										<br>
  while(util.file_read_next_line(br, line))				<br>
	 *	{																<br>
	 *		//処理を書く												<br>
	 *		String s_line = line[0];									<br>
	 *	}																<br>
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public /*final*/ static Boolean file_read_next_line(
		BufferedReader	br,		//I		ファイル
		String[]		line	//O		取得した行
	)
	{
		try{
			return (line[0] = br.readLine()) != null;
		}
		catch (IOException e) {
			System.out.println(e);
			return false;
		}
	}
	//************************************************************************//
	/**
	 *	RandomAccessFile用
	 *
	 *	http://tomorrowscode.blogspot.jp/2010/02/javaiorandomaccessfilereadlineutf-8.html
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public /*final*/ static Boolean file_read_next_line(RandomAccessFile raf, String[] line)
	{
		try{

			////			
			////			for(int ic=0; ic<sBuf.length(); ic++)
			////			{
			//////				sLine += sBuf.indexOf(ic);
			////				sLine += String.format("%s",sBuf.charAt(ic));
			//////				sLine += String.format("%s",sBuf.indexOf(ic));
			////			}
			//
			////			int nCharByte = 2;	//Unicodeは16bit = 2Byteらしい。直打ちは避けたいがしかたない。
			////			long nSeekNow = raf.getFilePointer();
			////			String sBuf = "";
			////			int nCountKaigyou = 0;
			//			//遡っていく
			////			while(true)
			//			
			//			String sOriginLine = raf.readLine();
			//			//文字列取得直後のシークを取得
			//			long nLastSeek = raf.getFilePointer();
			//			long nLineLengthByte = sOriginLine.length();
			//			
			//			String sLine = "";
			//			String sBuf = "";
			//
			//			byte bytetest[] = sOriginLine.getBytes();
			//			
			//			for(int ic=0; ic<bytetest.length; ic++)
			//			{
			//				System.out.print(bytetest[ic]);
			//				System.out.print(",");
			//			}
			//			System.out.println();
			//			
			////			Strings sBufByte = String.
			////			
			////			//シークを移動
			////			raf.seek(nLastSeek - nLineLengthByte-1); 
			////			for(int ic=0; ic<sOriginLine.length(); ic++)
			////			{
			////				Byte byteBuf1;
			////				Byte byteBuf2;
			////				byteBuf1 = raf.readByte();
			////				byteBuf2 = raf.readByte();
			////				
			////				//現在のシーク位置の文字を取得
			//////				sBuf = raf.readUTF();
			////				sBuf = String.format("%s",raf.readChar());
			//////				sBuf = raf.readChar();
			////				
			////				//改行があった場合
			////				if(sBuf.compareTo("\n") == 0)
			////				{
			////					//1行が終わったため、ループを抜ける
			////					break;
			////				}
			////				else
			////				{
			////					sLine += sBuf;
			////				}
			////			}
			//			
			//			return (line[0] = sOriginLine) != null;
			////			return (line[0] = sLine) != null;
			//		}
			//		catch (IOException e) {
			//			System.out.println(e);
			//			return false;
			//		}

			List<Byte> input = new ArrayList<Byte>();
			int c = -1;
			boolean eol = false;

			//ファイルの最後まで行っていない場合
			while (!eol)
			{
				switch (c = raf.read())
				{
					case -1:	return false;	//EOFに達した場合
					case '\n':	eol = true;	break;
					case '\r':	eol = true;
								long cur = raf.getFilePointer();
								if ((raf.read()) != '\n')
								{
									raf.seek(cur);
								}
								break;
					default:	input.add((byte)c);	break;
				}
			}


			if ((c == -1) && (input.isEmpty()))
			{
				return false;
			}
			byte[] bytes = new byte[input.size()];
			for(int i=0;i<input.size();i++)
			{
				bytes[i]=input.get(i);
			}

			//http://www.ne.jp/asahi/hishidama/home/tech/lang/encode.html
			//ASCII 	JIS 	SJIS 	EUC 	UTF8 	UTF16 	Unicode 	
			//		  line[0] = new String(bytes, "utf8");
			line[0] = new String(bytes, "SJIS");
			return true;
		}
		catch (IOException e)
		{
			System.out.println(e);
			return false;
		}
	}

	//************************************************************************//
	/**
	 * 	RandomAccessFile用
	 * 
	 *	ファイルを指定された数だけ前後の行を読込む。		<br>
	 *	3行後なら3		<br>
	 *  10行前なら-10	<br>
	 *  
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public /*final*/ static Boolean file_read_up_down_line(RandomAccessFile raf, String[] line, int nLineUpDown)
	{
		try{
			////////////////////////
			//	UpかDownか判定
			////////////////////////
			///// Downの場合 /////
			if(nLineUpDown >= 0)
			{
				for(int ic=0; ic<nLineUpDown; ic++)
				{
					if(file_read_next_line(raf, line) == false)
					{
						return false;
					}
				}
				return true;
			}
			
			///// Upの場合 /////
			
			//2つ前の行を取得する場合の例
			//abcdefg\n	※3行前
			//hijklmn\n	※2行前
			//opqrstu\n	※1行前
			//vwxyz\n	※ユーザーここが今の行と考える。
			//[現在のシーク]
			
//			int nCharByte = 2;	//Unicodeは16bit = 2Byteらしい。直打ちは避けたいがしかたない。
			int nByte = 1;	//バイト単位で読み込む
			long nSeekNow = raf.getFilePointer();
//			String sBuf;
			int nBuf;
			int nCountKaigyou = 0;
			//遡っていく
			while(true)
			{
//				  List<Byte> input = new ArrayList<Byte>();
//				  int c = -1;
//				  boolean eol = false;
//				  while (!eol) {
//				    switch (c = raf.read()) {
//				    case -1: //EOFに達した場合
//				    case '\n':
//				      eol = true;
//				      break;
//				    case '\r':
//				      eol = true;
//				      long cur = raf.getFilePointer();
//				      if ((raf.read()) != '\n') {
//				        raf.seek(cur);
//				      }
//				      break;
//				    default:
//				      input.add((byte)c);
//				      break;
//				    }
//				  }
				  
				//Char分だけさかのぼる
//				nSeekNow -= nCharByte;
				nSeekNow -= nByte;
				if(nSeekNow < 0)
				{
					return false;
				}
				
				//シークを移動
				raf.seek(nSeekNow);
				
				//現在のシーク位置の文字を取得
				nBuf = raf.read();
				
				//改行があった場合
				if(nBuf == '\n')
				{
					//改行カウントを追加
					nCountKaigyou++;
				}
				
				//改行カウントが、指定行+2となったとき
				if(nCountKaigyou == -nLineUpDown + 2)
				{
					//指定行だけ戻ったので、取得する。
					break;
				}
				
				//シークを移動
				raf.seek(nSeekNow);
			}

			return file_read_next_line(raf, line);
			
//			return (line[0] = sLine) != null;
		}
		catch (IOException e) {
			System.out.println(e);
			return false;
		}
	}

	//************************************************************************//
	/**
	 *	文字列を整数に。数字以外は除く。
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static Integer string_to_integer(String str)
	{
		return Integer.valueOf(string_only_number(str));
	}	

	//************************************************************************//
	/**
	 *	文字列を整数に。数字以外は除く。
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static Double string_to_double(String str)
	{
		return Double.valueOf(string_only_number(str));
	}	

	//************************************************************************//
	/**
	 *	JUnitテスト用。何のテストか見やすくする
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static void debug_print_delux(String str)
	{
		System.out.println("/////////////////////////////////////////////////////////////////////");
		System.out.println("/////");
		System.out.println(String.format("/////\t\t %s", str));
		System.out.println("/////");
		System.out.println("/////////////////////////////////////////////////////////////////////");

	}

	//************************************************************************//
	/**
	 *	現在のクラス名、メソッド名を出力
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String log_thread(Object obj)
	{
//		return obj.getClass().getName() + "/" + Thread.currentThread().getStackTrace()[1].getMethodName();
		return obj.getClass().getName() + "/" + Thread.currentThread().getStackTrace()[2].getMethodName();
	}

	//************************************************************************//
	/**
	 *	現在のクラス名、メソッド名を出力
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String log_thread()
	{
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
	//************************************************************************//
	/**
	 *	sleep
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static void sleep(int millitime)
	{
		try
        {	
        	Thread.sleep(millitime);
        }
        catch (InterruptedException e)
        {
			e.printStackTrace();
        }
	}	
	//************************************************************************//
	/**
	 *	呼び出し側のクラス名とメソッド名を取得
	 *
	 *	元々使っていたものは以下
	 *	this.getClass().getName() + "/" + Thread.currentThread().getStackTrace()[1].getMethodName();
	 *	しかしこれではその関数内に直接書く必要があった。
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static String debug_ask_class_method_name()
	{
		StackTraceElement stackBuf = Thread.currentThread().getStackTrace()[2];
		return stackBuf.getClassName() + "/" + stackBuf.getMethodName();
	}
	//************************************************************************//
	/**
	 *	try,catch構文の中に書く用の関数。					<br>
	 *  Exceptionがどの関数で起こったのかわかるように。	<br>
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static void debug_write_exception(Exception e)
	{
		//クラス名、メソッド名を取得
		StackTraceElement stackBuf = Thread.currentThread().getStackTrace()[2];
		String sClassMethodName = stackBuf.getClassName() + "/" + stackBuf.getMethodName();
		
		//エラーを取得
		String sError = e.getMessage();
		
		System.out.println("+++++ Error in " + sClassMethodName);
		System.out.println(sError);
	}

	//************************************************************************//
	/**
	 *	配列に格納し、格納位置の先頭インデックスを返す
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static int set_data_get_index(ArrayList<Double> Array, double[] Value)
	{
		int nIndex = Array.size();
		
		int Num = Value.length;
		for(int ic=0; ic<Num; ic++)
		{
			Array.add(Value[ic]);
		}

		return nIndex;
	}
		
	//************************************************************************//
	/**
	 *	配列に同じものがあれば、そのインデックスを返す。
	 *	無ければ配列に追加して、そのインデックスを返す。
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static int set_same_data_get_index(
		ArrayList<Double> Array,
		double Value,
		Boolean bNew[])
	{
		int nIndexValue = -1;
		for(int ic=0; ic<Array.size(); ic++)
		{
			if(Array.get(ic) == Value)
			{
				nIndexValue = ic;
			}
		}
		//無かった場合
		if(nIndexValue < 0)
		{
			//新たな半径を格納
			Array.add(Value);
			//インデックスを取得
			nIndexValue = Array.size() - 1; 
			//新規のフラグ
			bNew[0] = true;
		}
		return nIndexValue;
	}
	
	//************************************************************************//
	/**
	 *	配列に同じものがあれば、そのインデックスを返す。
	 *	無ければ配列に追加して、そのインデックスを返す。
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static int set_same_data_get_index(ArrayList<Integer> Array, int Value, Boolean bNew[])
	{
		int nIndexValue = -1;
		for(int ic=0; ic<Array.size(); ic++)
		{
			if(Array.get(ic) == Value)
			{
				nIndexValue = ic;
			}
		}
		//無かった場合
		if(nIndexValue < 0)
		{
			//新たな半径を格納
			Array.add(Value);
			//インデックスを取得
			nIndexValue = Array.size() - 1; 
			//新規のフラグ
			bNew[0] = true;
		}
		return nIndexValue;
	}

	//************************************************************************//
	/**
	 *	配列に同じものがあれば、そのインデックスを返す。
	 *	無ければ配列に追加して、そのインデックスを返す。
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static int set_same_data_get_index(ArrayList<Short> Array, short Value, Boolean bNew[])
	{
		int nIndexValue = -1;
		for(int ic=0; ic<Array.size(); ic++)
		{
			if(Array.get(ic) == Value)
			{
				nIndexValue = ic;
			}
		}
		//無かった場合
		if(nIndexValue < 0)
		{
			//新たな半径を格納
			Array.add(Value);
			//インデックスを取得
			nIndexValue = Array.size() - 1; 
			//新規のフラグ
			bNew[0] = true;
		}
		return nIndexValue;
	}

	//************************************************************************//
	/**
	 *	進捗状況を出力
	 *
	 *	@param
	 *	@return
	 *	@version
	 */
	//************************************************************************//
	public static void print_progress(int nNumber, int nNow)
	{
		if(nNow%nNumber == 0)
		{
			System.out.println(String.format("%d", nNow));
		}
	}
	
}


