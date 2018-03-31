package _24_Object_Mesh;

import _20_Object_Template.TreeItem_Imp;
import _23_Object_Iroiro.Object_03_Point;
import _23_Object_Iroiro.Object_05_Triangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.scene.Group;
import javafx.scene.Node;

//import javax.media.opengl.GL;
//import com.sun.opengl.util.GLUT;

//import mathmatic.Mathmatic;
import _41_Mathatic.Mathmatic;
import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.Entity_Imp;
import _20_Object_Template.TreeItem_Imp_Data;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
//import moredyn.OpenGL_Play;
public class Object_32_MeshTetra4
//	extends Entity_Base
	implements
		Entity_Imp,
		Display_3D_Object_Imp,
		TreeItem_Imp
//	implements
//		Object_Mesh
//		FileIO
{
	private String m_name;
	ArrayList<Object_31_MeshNode>		m_arryGrid		= new ArrayList<>(4);
	

	private Node m_javafxnode = null;
	private boolean m_update_on = true;
	
	boolean[] m_bDuplicateFace = {false, false, false, false};	//他のMeshTetra4の面と接しているか判定結果を入力
	
	///// 面の節点組み合わせを定義 /////
	/*
	 *               3 
	 *              /|\ 
	 *             / | \
	 *            /  |  \
	 *           /   |   \
	 *          /    0＿＿ \2＿＿＿＿＿
	 *         /    ／       .・
	 *        /   ／      .・ 
	 *       /  ／     .・
	 *      / ／   .・
	 *     /／.・
	 *     1
	*/
	private static final int[][] m_FaceCombination = {
		{0, 2, 1},	//FaceId 0
		{0, 3, 2},	//FaceId 1
		{0, 1, 3},	//FaceId 2
		{1, 2, 3}	//FaceId 3
	};
	
	//************************************************************************//
	/**
	 *	Constructor
	 */
	//************************************************************************//
	public Object_32_MeshTetra4()
	{
//		super(null);
	}
	public Object_32_MeshTetra4(
		String			id, 
		Object_31_MeshNode	grid0,
		Object_31_MeshNode	grid1,
		Object_31_MeshNode	grid2,
		Object_31_MeshNode	grid3
	)
	{
		this.m_name = id;
	
		m_arryGrid.add(grid0);
		m_arryGrid.add(grid1);
		m_arryGrid.add(grid2);
		m_arryGrid.add(grid3);
	}
	//************************************************************************//
	/**
	 *	setter
	 */
	//************************************************************************//
	public void set_DuplicateFace(int nId, boolean b)				{this.m_bDuplicateFace[nId] = b;}
	public void set_arryGrid(ArrayList<Object_31_MeshNode> arryGrid)	{this.m_arryGrid = arryGrid;}
	
	//************************************************************************//
	/**
	 *	getter
	 */
	//************************************************************************//
	public ArrayList<Object_31_MeshNode> get_grids(){return this.m_arryGrid;} 
//	//************************************************************************//
//	/**
//	 *	Objectを表示するDisplayListを作成
//	 *
//	 *	@return	true 成功
//	 *	@return	false 失敗
//	 */
//	//************************************************************************//
//	@Override
//	public void create_object_displaylist_all
//	(
//		OpenGL_Play				OGL, 					//I		OpenGLクラス
//		Material				material,				//I 	材質（色、反射率）
//		DrawPen					drawPen,				//I 	描画ペン（線種、フォント）
//		ArrayList<Object_Base>	arryObject,				//I 	同じ表示方式のオブジェクト
//		ArrayList<Integer>		arryDispList_Disp,		//OUT
//		ArrayList<Integer>		arryDispList_Non_Disp	//OUT
//	)
//	{
//		try
//		{
//			System.out.println(utility.ask_class_method_name());
//
//			///////////////////////////////////////
//			// OpenGLを取得
//			///////////////////////////////////////
//			GL		gl		= OGL.get_gl();
//			//GLUT	glut	= OGL.get_glut();
//	
//			///////////////////////////////////////
//			//	材質のDisplayListを作成
//			///////////////////////////////////////
//			int nDispMaterial = material.create_material(gl);
//			//材質を非描画に保存
//			arryDispList_Non_Disp.add(nDispMaterial);
//	
//			////////////////////////////////////////////////////
//			//	三角形が同一の場合は表示しない
//			////////////////////////////////////////////////////
//			int nSize = arryObject.size();
//			Object_32_MeshTetra4	objMTet4Buf;
//			//boolean				bDuplicateAll;
//			//全組み合わせを検索（1つ目）
//			for(int ic=0; ic<nSize-1; ic++)
//			{
//				objMTet4Buf = (Object_32_MeshTetra4) arryObject.get(ic);
//				//全組み合わせを検索（2つ目）
//				for(int nc=ic+1; nc<nSize; nc++)
//				{
//					//もし全ての面が重複していたら次のObjectに移動する。
//					if(objMTet4Buf.is_duplicated_faces() == true)
//					{
//						//次のオブジェクトに移動
//						break;
//					}
//	
//					//2つのTetra4間で、同一の三角形があるかチェック
//					objMTet4Buf.check_same_triangle((Object_32_MeshTetra4) arryObject.get(nc));
//				}
//			}
//	
//			//////////////////////////////////////
//			//	三角形のディスプレイリスト作成
//			//////////////////////////////////////
//			//ディスプレイリストの番号を取得
//			int nDispMeshTetra4 = gl.glGenLists(1);
//			gl.glNewList(nDispMeshTetra4, GL.GL_COMPILE); //コンパイルのみ
//			//材質のDisplayList呼び出し
//			gl.glCallList(nDispMaterial);
//	
//			//////////////////////////////
//			//	三角形の連続作成
//			//////////////////////////////
//			gl.glBegin(GL.GL_TRIANGLES);
//			double[] dNormal = {0.0, 0.0, 0.0};
//			ArrayList<Object_MeshGrid> arryObjGrid = new ArrayList<Object_MeshGrid>(3);
//			boolean bIsDispFace;
//			int nMax = arryObject.size();
//			for(int ic=0; ic<nMax; ic++)
//			{
//				Object_32_MeshTetra4 objBuf = (Object_32_MeshTetra4) arryObject.get(ic);
//	
//				for(int nc=0; nc<4; nc++)
//				{
//					//各Tetra4に、表示する面を構成する3節点と法線方向を聞いて回る。
//					bIsDispFace = objBuf.ask_display_face(
//						nc, 			//面の番号
//						dNormal, 		//面の法線方向
//						arryObjGrid		//面を構成する3節点
//					);
//					if(bIsDispFace == false)
//					{
//						//表示面ではない。次の面へ移動
//						continue;
//					}
//	
//					//法線を指定
//					gl.glNormal3dv(dNormal, 0);
//					//3節点を指定
//					double[] position = new double[3];
//					for(int kc=0; kc<3; kc++)
//					{
//						//頂点を指定
//						arryObjGrid.get(kc).getc_position(position);
//						gl.glVertex3dv(position, 0);
//					}
//				}
//			}
//			gl.glEnd();
//	
//			//ディスプレイリストを閉じる
//			gl.glEndList();
//			
//			//Tetra4のメッシュをディスプレイリストに追加
//			arryDispList_Disp.add(nDispMeshTetra4);
//	
//		}
//		catch (Exception ex)
//		{
//			utility.write_exception(ex);
//		}		
//	}

	//************************************************************************//
	/**
	*	2つのTetra4間で、同一の三角形があるかチェック。
	*	ある場合は重複フラグをtrueにする。
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public void check_same_triangle(
		Object_32_MeshTetra4 objSecond
	)
	{
		//Tetra1のGrid
		ArrayList<Object_31_MeshNode> arryGridFirst  = this.m_arryGrid;

		//Tetra2のGrid
		ArrayList<Object_31_MeshNode> arryGridSecond = objSecond.get_grids();

		//一致節点格納
		ArrayList<Object_31_MeshNode> arrySame = new ArrayList<Object_31_MeshNode>(4);
		
		//まずTetra1の節点が、Tetra2に3つ以上含まれているかチェック
		int nNumSame = 0;
		for(int ic=0; ic<2; ic++)
		{
			Object_31_MeshNode objGrid = arryGridFirst.get(ic);
			for(int nc=0; nc<4; nc++)
			{
				if(objGrid != arryGridSecond.get(nc))
				{
					continue;
				}

				//一致カウントを増加
				arrySame.add(objGrid);
				nNumSame++;
				break;
			}
		}

		//2個の節点を調べた時点で一致数が0個なら、決して3個以上が一致することはない。
		if(nNumSame == 0){
			//何もしない。
			return;
		}

		for(int ic=2; ic<4; ic++){
			Object_31_MeshNode objGrid = arryGridFirst.get(ic);
			for(int nc=0; nc<4; nc++){
				if(objGrid != arryGridSecond.get(nc) == false){
					continue;
				}

				//一致カウントを増加
				arrySame.add(objGrid);
				nNumSame++;
				break;
			}
		}
			
		//3個以下の場合は重複三角形はない。
		if(nNumSame < 3){
			//何もしない。
			return;
		}

		//節点が3つ以上含まれている場合は同一の三角形がある。
		if(nNumSame == 4){
			//完全一致の要素となっている。それはそれで駄目●	//TODO
			//ここではとりあえず何もせずに抜ける
			return;
		}

		//一致した面を探し、重複しているフラグを反映する。
		int nId = this.ask_same_face_id(arrySame);
		this.m_bDuplicateFace[nId] = true;

		//二つ目のTetraにも反映
		nId = objSecond.ask_same_face_id(arrySame);
		objSecond.set_DuplicateFace(nId, true);
	}
	
	public int ask_same_face_id(
		ArrayList<Object_31_MeshNode> arrySame	//I		
	)
	{
		
		return 0;
	}
	//************************************************************************//
	/**
	*	表示する面か判定。表示の場合は構成する3節点と法線方向を出力
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	public boolean is_duplicated_faces()
	{
		///// 重複面がないかチェック /////
		for(int ic=0; ic<4; ic++)
		{
			if(this.m_bDuplicateFace[ic] == true)
			{
				return true;
			}
		}

		return false;
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
	public void ask_face_combination(
		int		nFaceId,
		int[]	nCombination
	)
	{
		nCombination = m_FaceCombination[nFaceId];
	}

	//************************************************************************//
	/**
	*	表示する面か判定。表示の場合は構成する3節点と法線方向を出力
	*
	 * @param nFaceId
	 * @param dNormal
	 * @param arryObjGrid
	*	@return
	*	@version
	*/
	//************************************************************************//
	public boolean ask_display_face(
		int								nFaceId,		//面の番号
		double[]						dNormal,		//面の法線方向
		ArrayList<Object_31_MeshNode>	arryObjGrid)	//面を構成する3節点
	{
		//面番号かチェック
		if(nFaceId < 0 || 4 < nFaceId)
		{
			return false;
		}
	
		//表示する面かチェック
		if(m_bDuplicateFace[nFaceId] == true)
		{
			return false;
		}
	
		int[] nCombi = Object_32_MeshTetra4.m_FaceCombination[nFaceId];

		//三節点を取得
		arryObjGrid.clear();
		arryObjGrid.add(this.m_arryGrid.get(nCombi[0]));
		arryObjGrid.add(this.m_arryGrid.get(nCombi[1]));
		arryObjGrid.add(this.m_arryGrid.get(nCombi[2]));
	
		///// 法線方向を取得（外積を法線ベクトルとする） /////
//		double[] dAxis1 = new double[3];
//		double[] dAxis2 = new double[3];
//		double[] dAxis3 = new double[3];
//		arryObjGrid.get(0).getc_position(dAxis1); 
//		arryObjGrid.get(1).getc_position(dAxis2); 
//		arryObjGrid.get(2).getc_position(dAxis3); 
//		double[] dAxis1 = arryObjGrid.get(0).get_position();
//		double[] dAxis2 = arryObjGrid.get(1).get_position();
//		double[] dAxis3 = arryObjGrid.get(2).get_position();
//		Value_Double[] dAxis1 = arryObjGrid.get(0).get_position().toArray(new Value_Double[0]);
//		Value_Double[] dAxis2 = arryObjGrid.get(1).get_position().toArray(new Value_Double[0]);
//		Value_Double[] dAxis3 = arryObjGrid.get(2).get_position().toArray(new Value_Double[0]);
//		Value_Double[] dAxis1 = arryObjGrid.get(0).get_position();
//		Value_Double[] dAxis2 = arryObjGrid.get(1).get_position();
//		Value_Double[] dAxis3 = arryObjGrid.get(2).get_position();
		
//		Mathmatic.calc_OuterProduct_unit(
//			Value_Double.change_list(dAxis1),
//			Value_Double.change_list(dAxis2),
//			Value_Double.change_list(dAxis3),
//			dNormal
//		);
		
		Mathmatic.calc_OuterProduct_unit(
			arryObjGrid.get(0).get_position().get_position(),
			arryObjGrid.get(1).get_position().get_position(),
			arryObjGrid.get(2).get_position().get_position(),
			dNormal
		);
		
		return true;
	}
//	//************************************************************************//
//	/**
//	 *	線分と交差するかチェック
//	 *
//	 *	@return	0：交差する
//	 *	@return	負の値：交差しない
//	 *			-1：線分が届かない
//	 *			-2：線分は届くが半径より遠い
//	 *
//	 *	Status:	0	交差している
//	 *			1	非交差
//	 *
//	 */
//	//************************************************************************//
//	@Override
//	public PickedItem ask_isTouch(
//		double[]	spoint,			//I		ピック円柱の始点 
//		double[]	epoint,			//I		ピック円柱の終点
//		Filter		filter,			//I		選択フィルタ
//		double		tolerance		//O		ピック円柱の半径
//	)
//	{
//		//実装
//		
//		return null;
//	}

//	//************************************************************************//
//	/**
//	 *	読み込みデータを実体化
//	 */
//	//************************************************************************//
//	@Override
//	public void read_data
//	(
//		XmlData			xmlData,		//各オブジェクト形式のXML(例:<Group_Base>1,XXX \n 2,XXX \n ･･･ </Group_Base>)
//		FileInputOutput	dataFileIO,		//生成したオブジェクトを全て格納
//		Entity_Directory		topGroup		//Xmlオプションに"top"とあるものはTopのGroupにぶら下がる
//	)
//	{
//		/////////////////////
//		//	idを取得
//		/////////////////////
//		String id = xmlData.search_option_value("id");
//
//		//////////////////////////////////////////////////////
//		//	idに対応したオブジェクト実体を返す
//		//////////////////////////////////////////////////////
//		Object_32_MeshTetra4 meshCurrent = dataFileIO.search_Object_MeshTetra4(id);
//		if(meshCurrent == null)
//		{
//			return;
//		}
//		
//		/////////////////////////
//		//	topオプションを検索
//		/////////////////////////
//		if(xmlData.search_option("top") != null)
//		{
//			//TopのGroupに追加
//			topGroup.add_object
//			(
//				meshCurrent,	//
//				false			//既に追加されている場合でも追加する
//			);
//		}
//
//		//////////////////////////////
//		//	フォーマットの型を準備
//		//////////////////////////////
//		String						stringBuf	= null;
//		ArrayList<Object_MeshNode> 	arryGrid	= new ArrayList<Object_MeshNode>(4);
//		
//		/////////////////////////////////////
//		//	Group_Baseデータ読み込みモード
//		/////////////////////////////////////
//		ArrayList<String>	sData		= new ArrayList<String>();
//		ArrayList<String>	elements	= xmlData.get_elements();
//		int nSize = elements.size();
//		for(int ic=0; ic<nSize; ic++)
//		{
//			//カンマで分解
//			utility.string_factorize3(elements.get(ic), ",", sData);
//			
//			//文字列前後の空白、タブは除く
//			utility.string_trim(sData);
//
//			//フォーマット識別番号を取得
//			int nFormNumber = utility.change_String_To_Integer(sData.get(0));
//
//			////////////////////////
//			//	フォーマット
//			////////////////////////
//			switch(nFormNumber)
//			{
//				////////////////////////
//				//	0,	String
//				////////////////////////
//				case 0:
//					stringBuf	= sData.get(1);
//					break;
//				/////////////////////////////////////
//				//	1,	ArrayList<Object_MeshGrid>
//				/////////////////////////////////////
//				case 1:
//					for(int nc=1; nc<sData.size(); nc++)
//					{
//						arryGrid.add( dataFileIO.search_Object_MeshGrid( sData.get(nc)) );
//					}
//					break;
//				////////////////////////
//				//	フォーマット外の値
//				////////////////////////
//				default:
//					break;
//			}
//		}
//		////////////////////////////////////////////////////
//		//	取得データを、メンバ変数に格納
//		////////////////////////////////////////////////////
//		meshCurrent.set_Name(stringBuf);
//		meshCurrent.set_arryGrid(arryGrid);
//		
//		return;
//	}
//	//************************************************************************//
//	/**
//	 *	idと関連付けたオブジェクトをプールに登録
//	 */
//	//************************************************************************//
//	@Override
//	public void factory_and_add
//	(
//		String			id,		//保存、読み込み時のオブジェクトID
//		FileInputOutput	fileIO	//データプール
//	)
//	{
//		fileIO.add(id, new Object_32_MeshTetra4());
//	}
	
//	//************************************************************************//
//	/**
//	*	ファイル読み込み、書き出し時のフォーマットを定義する。
//	*
//	*	@param
//	*	@return
//	*	@version
//	*/
//	//************************************************************************//
//	Boolean define_io_format
//	(
//		Boolean					bModeRead,	//モード（true:読み込み、false：書き出し）
//		int						nIndex,		//フォーマット識別番号
//		XmlData					xmlData,	//XmlData
//		FileInputOutput			dataFileIO	//生成したオブジェクトを全て格納
//	)
//	{
//		int nFormNumber;
//		ArrayList<String> arryStr = new ArrayList<String>();
//
//		///// 読み込み /////
//		if(bModeRead == true)
//		{
//			//カンマで分解
//			utility.factorize_string3(xmlData.ask_element(nIndex), ",", arryStr);
//			
//			//文字列前後の空白、タブは除く
//			utility.string_trim(arryStr);
//
//			//フォーマット識別番号を取得
//			nFormNumber = utility.change_String_To_Integer(arryStr.get(0));
//		}
//		///// 書き出し /////
//		else
//		{
//			nFormNumber = nIndex;
//		}
//
//		////////////////////////////
//		//	フォーマット識別子判定
//		////////////////////////////
//		switch(nFormNumber)
//		{
//			////////////////////////
//			//	0,	String
//			//	XmlDatの要素にて、0のものはストリング
//			////////////////////////
//			case 0:
//				///// 読み込み /////
//				if(bModeRead == true)
//				{
//					this.m_Name = arryStr.get(1);
//				}
//				///// 書き出し /////
//				else
//				{
//					xmlData.add_element(String.format("%d,\t%s", nFormNumber, this.m_Name));
//				}
//				break;
//			/////////////////////////////////////
//			//	1,	ArrayList<Object_MeshGrid>
//			/////////////////////////////////////
//			case 1:
//				///// 読み込み /////
//				if(bModeRead == true)
//				{
//					for(int nc=1; nc<arryStr.size(); nc++)
//					{
//						this.m_arryGrid.add( dataFileIO.search_Object_MeshGrid( arryStr.get(nc)) );
//					}
//				}
//				///// 書き出し /////
//				else
//				{
//					String sBuf = String.format("%d",nFormNumber);
//					for(int nc=0; nc<this.m_arryGrid.size(); nc++)
//					{
//						//節点をフォーマットに沿って書き出し
//						String id = dataFileIO.ask_id( this.m_arryGrid.get(nc) );
//						sBuf += String.format(",\t%s", id);
//
//						//子を用意
//						XmlData xmlChild = null;
//						
//						//子のXmlData内部を作成
//						this.m_arryGrid.get(nc).write_data
//						(
//							xmlChild,
//							dataFileIO
//						);
//						
//						//親に追加
//						xmlData.add_child(xmlChild);
//					}
//					//要素を追加
//					xmlData.add_element(sBuf);
//				}
//				break;
//			/////////////////////////////////////
//			//	その他
//			/////////////////////////////////////
//			default:
//				return false;
//		}
//		
//		return true;
//	}
//	//************************************************************************//
//	/**
//	 *	ファイルに書き出し
//	 *
//	 *	@param	
//	 *	@return	id
//	 *	@version
//	 */
//	//************************************************************************//
//	@Override
//	public String write_data
//	(
//		XmlData				xmlDataParent,	//OUT	親のXmlData。このXmlDataの子として登録する。
//		FileInputOutput		poolFileIO		//IN	書き込み時の全てのXmlDataを持つ。
//	)
//	{
//		try
//		{
//			/////////////////////////////////////////
//			//	XmlDataが既にあるかチェック
//			/////////////////////////////////////////
//			XmlData[]	xmlBuf	= {null};
//			String[]	idBuf	= {null};
//			boolean bSearchExist = poolFileIO.search_or_create_xml(
//				xmlDataParent,	//IO	Xmlデータ。親に追加する。
//				this,			//IN	Xmlデータの元となるオブジェクト
//				poolFileIO,		//IO	Xmlデータを全て格納
//				xmlBuf,			//OUT	検索、または作成したXmlData
//				idBuf			//OUT	検索、または作成されたXmlData
//			);
//			if(bSearchExist == true)
//			{
//				//既にあるのでそのまま返す
//				return idBuf[0];
//			}
//			
//			///// フォーマット識別番号を全て書き出し /////
//			XmlData xmlDataMySelf = xmlBuf[0];
//			String	idMySelf = idBuf[0];
//			for(int nIndex=0; true; nIndex++)
//			{
//				////////////////////////////////////
//				//	フォーマット
//				//	<Object_MeshTetra4 id=XXXXX>
//				//		0,	name
//				//		1,	AAAAA,	BBBBB,	CCCCC
//				//	</Object_MeshTetra4>
//				////////////////////////////////////
//				String sFormNumber		= String.format("%d",nIndex);
//				String sWriteLineBuf	= null;
//				switch(nIndex)
//				{
//					////////////////////////
//					//	0,	String
//					//	XmlDatの要素にて、0のものはストリング
//					////////////////////////
//					case 0:
//						//xmlDataMySelf.add_element(String.format("%d,%s", nIndex, this.m_Name));
//						if(super.getM_Name() != null)	xmlDataMySelf.add_element(sFormNumber + "," + super.getM_Name());
//						break;
//					/////////////////////////////////////
//					//	1,	ArrayList<Object_MeshGrid>
//					/////////////////////////////////////
//					case 1:
//						if(this.m_arryGrid == null) break;
//						for(int nc=0; nc<this.m_arryGrid.size(); nc++)
//						{
//							//書き出し文字列作成、また子のXmlData作成
//							sWriteLineBuf = poolFileIO.ask_write_line_and_create_xml
//							(
//								xmlDataMySelf,			//OUT	親となるXmlデータ。これに追加する。
//								sWriteLineBuf,			//OUT	親となるXmlデータのデータ書き出し文字列。
//								m_arryGrid.get(nc),		//IN	子となるXmlデータの元となるオブジェクト。
//								poolFileIO				//IN	Xmlデータを全て格納
//							);
//						}
//						//要素を追加
//						if(sWriteLineBuf != null)	xmlDataMySelf.add_element(sFormNumber + sWriteLineBuf);					
//	
//						break;
//					/////////////////////////////////////
//					//	その他
//					/////////////////////////////////////
//					default:
//						return idMySelf;
//				}
//			}
//		}
//		catch (Exception ex)
//		{
//			utility.write_exception(ex);
//			return null;
//		}
//
//	}

	public Node create_JavafxNode()
	{
		//節点をObject_Pointに変換
		List<Object_03_Point> obj_grids = this.m_arryGrid.stream()
			.map(a -> new Object_03_Point("", a.get_position().get_position()))
			.collect(Collectors.toList());

		//各面をObject_Triangleで構築
		Group group = new Group();
		Arrays.asList(m_FaceCombination).stream()
			.map(a -> new Object_05_Triangle("", obj_grids.get(a[0]), obj_grids.get(a[1]), obj_grids.get(a[2])))
			.forEach(a -> group.getChildren().add(a.create_JavafxNode()));
		
		this.m_javafxnode = group;
		return group;
	}

//	@Override
//	public void observer_receive_update(Entity_Base obj, UpdateType update_type) {
//		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//	}
//	
//	@Override
//	public void ask_JavafxTreeItem_children(boolean is_open, TreeItem tree_item)
//	{
//		try
//		{
////			//名前からツリーノードを作成
////			TreeItem tree_item = new TreeItem<>(this);
//
//			//現段階では無しとする。
//			
////			return tree_item;
//		}
//		catch (Exception ex)
//		{
//			utility.write_exception(ex);
//		}
//		
////		return null;
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
	//データ
//	Display_3D_Object_Imp_Data m_disp_3d_obj_imp_data = new Display_3D_Object_Imp_Data();
	//オーバーライド関数
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
