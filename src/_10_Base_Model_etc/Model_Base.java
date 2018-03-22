package _10_Base_Model_etc;

import _00_GUI_Main_JavaFX.TreeCellImpl;
import _20_Object_Template.TreeItem_Imp;
import _11_Base_Button_etc.Button_Base;
import _11_Base_Button_etc.Button_EditDialog;
import _11_Base_Button_etc.Button_One_Push;

import _20_Object_Template.Display_3D_Object_Imp;
import _20_Object_Template.ObserverReceive_Imp;
import _30_Command.Command_Change_ReImAxisDefine;
import _30_Command.Command_Create_ReImAxisDefine;
import _31_CommandMouse.CommandMouse_Affine_Rotation;
import _31_CommandMouse.CommandMouse_Affine_Zoom;
import _31_CommandMouse.CommandMouse_Imp;
import _31_CommandMouse.CommandMouse_NoAction;
import _31_CommandMouse.CommandMouse_Affine_Pan;
import _30_Command.Command_Create_放物線;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.ArrayList;

import utility.util;

import javafx.scene.DepthTest;
import javafx.scene.Group;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

//import _30_Command.Command_Imp;
import _30_Command.Command_Create_楕円体;
import _30_Command.Command_Imp;
import _30_Command.Command_Redo;
import _30_Command.Command_Undo;
import javafx.scene.control.TreeCell;
import javafx.util.Callback;

//************************************************************************//
/**
 * 1つのPaneに描画するオブジェクトとカメラ、光源などを一まとめにしたクラス
*/
//************************************************************************//
public class Model_Base
{
    //************************************************************************//
    /**
     * フィールド
    */
    //************************************************************************//
    private SubScene                m_subscene_fx;			//JavaFXの画面
    private Group                   m_group_fx;				//JavaFXの画面に描画するオブジェクト、ライト格納
    private Group                   m_group_fx_light;		//JavaFXの画面に描画するライト格納
    private Group                   m_group_fx_object;		//JavaFXの画面に描画するオブジェクト格納
	private TreeItem				m_treeitem_fx_root;		//JavaFXのツリーテーブルビューのルート
	
    private View_Base               m_view_current;			//カメラ、光源（カレント）
    private ArrayList<View_Base>	m_arry_view_base;		//カメラ、光源
	private DataBase				m_database;				//データベース
	
    private CommandMouse_Imp		m_MouseCommand;					//MouseCommand（Mouseの動作を指定）
    private CommandMouse_Imp		m_MouseComLongPress;			//Mouse長押し時のMouseCommand
    private CommandMouse_Imp		m_MouseCommand_ForceAction;		//Mouseの動作を強制設定（Affine変換のボタンOn時用）
    private Boolean					m_bMouseCommand_ForceAction;	//？
    
    private Point   m_start_point = new Point(0, 0);
    private Point   m_end_point = new Point(0, 0);;

    //************************************************************************//
    /**
     *	コンストラクタ
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
    public Model_Base(){}
		
    //************************************************************************//
    /**
     *	セットアップ
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
    public void setup(
        Pane		pane,
		TreeView	treeviewItems,
		Pane		paneProcedure
    )
    {
        int width_init	= 90;
        int height_init	= 50;
        
        ///// 初期化 /////
        m_group_fx				= new Group();
        m_group_fx_light		= new Group();
        m_group_fx_object		= new Group();
        m_subscene_fx			= new SubScene(m_group_fx, width_init, height_init, true, SceneAntialiasing.BALANCED);
        m_view_current			= new View_Base();
        m_arry_view_base		= new ArrayList<View_Base>();
		m_database				= new DataBase("ベースDB");
		m_treeitem_fx_root		= new TreeItem<TreeItem_Imp>();
		m_group_fx.getChildren().add(m_group_fx_object);

        //デプスバッファを設定
        m_subscene_fx.setDepthTest(DepthTest.ENABLE);
        m_subscene_fx.depthTestProperty().setValue(DepthTest.ENABLE);
       
        //Paneにセット
        pane.getChildren().add(m_subscene_fx);

        //MouseHandlerをセット
		pane.setOnMouseClicked(mouseevent -> {this.mouseClicked(mouseevent);});
		pane.setOnMouseDragged(mouseevent -> {this.mouseDragged(mouseevent);});
		pane.setOnMousePressed(mouseevent -> {this.mousePressed(mouseevent);});
		
		
        //カメラをセット
        m_subscene_fx.setCamera(m_view_current.getCameraCurrent().getCamera());
//		//カメラの回転軸をセット
//		m_directory_objects.add_object(m_view_current.getCameraCurrent().get_Rotate_Axis(), true);

        //光源をセット
		m_view_current.getLightCurrent().stream()
			.filter(a -> a!= null)
			.forEach(a -> m_group_fx_light.getChildren().add(a.getLight()));

		//////////////////////////
		//	ツリー設定
		//////////////////////////
		//ツリーテーブルビューをセット
		treeviewItems.setRoot(m_treeitem_fx_root);
		treeviewItems.setShowRoot(false);
		//ポップアップメニュー対応
		treeviewItems.setCellFactory(new Callback<TreeView<TreeItem_Imp>, TreeCell<TreeItem_Imp>>() {
            @Override
			public TreeCell<TreeItem_Imp> call(TreeView<TreeItem_Imp> listView) {
                return new TreeCellImpl();
            }
        });
		//////////////////////////
		//	画面リサイズの設定
		//////////////////////////
		//横幅
		pane.widthProperty().addListener ((observable, oldValue, newValue) -> {m_subscene_fx.setWidth(newValue.intValue());});
		//縦幅
		pane.heightProperty().addListener((observable, oldValue, newValue) -> {m_subscene_fx.setHeight(newValue.intValue());});	

		
		//////////////////////////////
		//	座標系定義を生成
		//////////////////////////////
		//座標系定義を作成
		Command_Create_ReImAxisDefine coord_def = new Command_Create_ReImAxisDefine();
		coord_def.execute(null);
		//カレントを変更
		this.m_database.set_current_re_im_axis(coord_def);

		/////////////////////////////////////////////
		//	テスト：放物線の面を作成するButtonを生成
		/////////////////////////////////////////////
		Button_Base button1 = new Button_EditDialog(
			"放物線",
			null,
			new Command_Create_放物線()
		);
		paneProcedure.getChildren().add(button1.ask_JavafxButton());
		
//		/////////////////////////////////////////////
//		//	テスト：節点を作成するButtonを生成
//		/////////////////////////////////////////////
//		Button_Base button2 = new Button_EditDialog(
//			"Node",
//			null,
//			new Command_Create_Node()
//		);
//		paneProcedure.getChildren().add(button2.ask_JavafxButton());
//		
//		/////////////////////////////////////////////
//		//	平行6面体
//		/////////////////////////////////////////////
//		Button_Base button3 = new Button_EditDialog(
//			"平行6面体",
//			null,
//			new Command_Create_平行六面体()
//		);
//		paneProcedure.getChildren().add(button3.ask_JavafxButton());
//				
//		/////////////////////////////////////////////
//		//	三角形
//		/////////////////////////////////////////////
//		paneProcedure.getChildren().add(
//			new Button_EditDialog(
//				"Triangle",
//				null,
//				new Command_Create_Triangle()
//			).ask_JavafxButton()
//		);
//				
//		/////////////////////////////////////////////
//		//	球
//		/////////////////////////////////////////////
//		paneProcedure.getChildren().add(
//			new Button_EditDialog(
//				"Sphere",
//				null,
//				new Command_Create_Sphere()
//			).ask_JavafxButton()
//		);
//				
//		/////////////////////////////////////////////
//		//	点
//		/////////////////////////////////////////////
//		paneProcedure.getChildren().add(
//			new Button_EditDialog(
//				"Point",
//				null,
//				new Command_Create_Point()
//			).ask_JavafxButton()
//		);
//				
//		/////////////////////////////////////////////
//		//	線
//		/////////////////////////////////////////////
//		paneProcedure.getChildren().add(
//			new Button_EditDialog(
//				"Line",
//				null,
//				new Command_Create_Line()
//			).ask_JavafxButton()
//		);
//				
//		/////////////////////////////////////////////
//		//	Tetra4
//		/////////////////////////////////////////////
//		paneProcedure.getChildren().add(
//			new Button_EditDialog(
//				"Tetra4",
//				null,
//				new Command_Create_MeshTetra4()
//			).ask_JavafxButton()
//		);
				
		/////////////////////////////////////////////
		//	座標軸を作成
		/////////////////////////////////////////////
		paneProcedure.getChildren().add(
			new Button_EditDialog(
				"座標系定義を作成",
				null,
				new Command_Create_ReImAxisDefine()
			).ask_JavafxButton()
		);

		/////////////////////////////////////////////
		//	座標系定義のカレント変更
		/////////////////////////////////////////////
		paneProcedure.getChildren().add(
			new Button_EditDialog(
				"座標系定義のカレント変更",
				null,
				new Command_Change_ReImAxisDefine()
			).ask_JavafxButton()
		);
		
		/////////////////////////////////////////////
		//	楕円体
		/////////////////////////////////////////////
		paneProcedure.getChildren().add(
			new Button_EditDialog(
				"楕円体",
				null,
				new Command_Create_楕円体()
			).ask_JavafxButton()
		);

		/////////////////////////////////////////////
		//	アンドゥ
		/////////////////////////////////////////////
		paneProcedure.getChildren().add(
			new Button_One_Push(
				"Undo",
				null,
				new Command_Undo()
			).ask_JavafxButton()
		);

		/////////////////////////////////////////////
		//	リドゥ
		/////////////////////////////////////////////
		paneProcedure.getChildren().add(
			new Button_One_Push(
				"Redo",
				null,
				new Command_Redo()
			).ask_JavafxButton()
		);

		//////////////////////////////
		//	更新
		//////////////////////////////		
		this.Update();
    }
    //************************************************************************//
    /**
     *	getter
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
    public View_Base	getViewCurrent()	{return this.m_view_current;}
    public double		getHeight()		{return m_subscene_fx.getBoundsInLocal().getHeight();}    
    public double		getWidth()			{return m_subscene_fx.getBoundsInLocal().getWidth();}
    public SubScene     getSubScene()		{return this.m_subscene_fx;}
//	public Object_11_CurrentObject<Command_Create_CoordinateSystemDefine> get_coordinate_system_current() {return this.m_coordinate_system_current;}
	public Command_Create_ReImAxisDefine get_current_re_im_axis()	{return this.m_database.get_current_re_im_axis();}

	
	//setter
	public void	set_current_re_im_axis(Command_Create_ReImAxisDefine csd) {this.m_database.set_current_re_im_axis(csd);}
	public void add_current_re_im_axis_observer(ObserverReceive_Imp obj) {this.m_database.add_current_re_im_axis_observer(obj);}
	//************************************************************************//
    /**
     *	Commandを追加
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
	public void add_command(Command_Imp ... commands)
	{
		System.out.println(util.debug_ask_class_method_name());
		m_database.add_command(commands);
	}

   //************************************************************************//
    /**
     *	Commandを置換
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
	public void replace_command(
		Command_Imp command_before,		//置換元
		Command_Imp command_after		//置換先
	)
	{
		System.out.println(util.debug_ask_class_method_name());
		m_database.replace_command(command_before, command_after);
	}
   //************************************************************************//
    /**
     *	Commandを全て取得
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
	public ArrayList<Command_Imp> get_command_all()
	{
		System.out.println(util.debug_ask_class_method_name());
		return m_database.get_commands();
	}

    //************************************************************************//
    /**
     *	Commandを検索
     *
     *	@param
     *	@return
     *	@version
    */
    //************************************************************************//
	public Command_Create_ReImAxisDefine search_command_coordinate_def_by_id(int in_id)
	{
		Command_Create_ReImAxisDefine out_command = null;

		Command_Create_ReImAxisDefine buf_cmd_csd = null;
		for(Command_Imp cmd : this.m_database.get_commands()){
			//インターフェース判定
			if(cmd instanceof Command_Create_ReImAxisDefine == false){
				continue;
			}
			//インターフェース変更
			buf_cmd_csd = (Command_Create_ReImAxisDefine)cmd;
			//取得
			if(buf_cmd_csd.get_id() == in_id){
				out_command = buf_cmd_csd;
				break;
			};
		}
		
		return out_command;
	}

//   //************************************************************************//
//    /**
//     *	グループを追加
//     *
//     *	@param
//     *	@return
//     *	@version
//    */
//    //************************************************************************//
//	public void AddGroup(Entity_Directory ... groupbase)
//	{
//		System.out.println(util.debug_ask_class_method_name());
//		
//		List<Entity_Directory> list = Arrays.asList(groupbase);
//		
//		list.stream()
//			.filter(a -> a != null)
////			.forEach(a -> this.m_directory_data_base.add_group_child(a, true));
//			.forEach(a -> this.m_directory_objects.add_object(a, true));
//	}

    //************************************************************************//
    /**
     *	リスナー：マウスが押された
     *
     *	EventTypeは以下を参照した。
     *	http://sunday-programming.hatenablog.com/entry/2014/02/02/223834
	 * 
	 *	@param	e
     *	@return	なし
     *	@version
     */
    //************************************************************************//
    private void mousePressed(MouseEvent e)
    {
		try
		{
			System.out.println(util.debug_ask_class_method_name());

			//*************************
			//	位置を取得
			//*************************
			m_start_point.x = (int) e.getSceneX();
			m_start_point.y = (int) e.getSceneY();

			//*************************
			//	Mouse同時押しの組合せ
			//*************************
			int onmask_12 = InputEvent.BUTTON1_DOWN_MASK | InputEvent.BUTTON2_DOWN_MASK;
			int onmask_23 = InputEvent.BUTTON2_DOWN_MASK | InputEvent.BUTTON3_DOWN_MASK;
			int onmask_13 = InputEvent.BUTTON1_DOWN_MASK | InputEvent.BUTTON3_DOWN_MASK;
			int onmask_123 = InputEvent.BUTTON1_DOWN_MASK | InputEvent.BUTTON2_DOWN_MASK | InputEvent.BUTTON3_DOWN_MASK;

//			//*************************
//			//	Affine変換ボタンを選択時
//			//*************************
//			if(m_bMouseCommand_ForceAction == true)
//			{
//				m_MouseCommand = m_MouseCommand_ForceAction;
//			}

			//*************************
			//	MB1
			//*************************
			if(e.getButton().equals(MouseButton.PRIMARY))
			{
				System.out.println("MB1");
				m_MouseCommand		= new CommandMouse_Affine_Zoom(this);
				m_MouseComLongPress = new CommandMouse_NoAction();
			}

			//*************************
			//	MB2
			//*************************
//			else if(javax.swing.SwingUtilities.isMiddleMouseButton(e))
			else if(e.getButton().equals(MouseButton.MIDDLE))
			{
				System.out.println("MB2");
				m_MouseCommand		= new CommandMouse_Affine_Rotation(this);
				m_MouseComLongPress = new CommandMouse_NoAction();
			}

			//*************************
			//	MB3
			//*************************
			else if(e.getButton().equals(MouseButton.SECONDARY))
			{
				System.out.println("MB3");
				m_MouseCommand		= new CommandMouse_Affine_Pan(this);
				m_MouseComLongPress = new CommandMouse_NoAction();
			}

			//*************************
			//	その他（MB1,MB2,MB3以外のボタンがある場合の処理）
			//*************************
			else
			{
				m_MouseCommand = new CommandMouse_NoAction();
			}

			//Drag処理を実行
			m_MouseCommand.pressed(m_start_point);
		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
    }
    //************************************************************************//
    /**
     *	リスナー：マウスがクリックされた
     *
     *	@param	e
     *	@return	なし
     *	@version
     */
    //************************************************************************//
    public void mouseClicked(MouseEvent e)
    {

    }

    //************************************************************************//
    /**
     *	リスナー：マウスがWindowに入った
     *
     *	@param	e
     *	@return	なし
     *	@version
     */
    //************************************************************************//
    public void mouseEntered(MouseEvent e)
    {
		try
		{
			System.out.println(util.debug_ask_class_method_name());

		}
		catch (Exception ex)
		{
			util.debug_write_exception(ex);
		}
    }
    //************************************************************************//
    /**
     *	リスナー：マウスがWindowから出た場合
     *
     *	@param	e
     *	@return	なし
     *	@version
     */
    //************************************************************************//
    public void mouseExited(MouseEvent e)
    {
    }

    //************************************************************************//
    /**
     *	リスナー：マウスがリリースされた
     *
     *	@param	e
     *	@return	なし
     *	@version
     */
    //************************************************************************//
    public void mouseReleased(MouseEvent e)
    {
        try{
            System.out.println(util.debug_ask_class_method_name());

//            //*************************
//            //	アニメーションを再開する
//            //*************************
//            if(bAnimeForceStop == true)
//            {
//                //アニメーションを再開する
//                this.m_OGL.anime_start();
//                bAnimeForceStop = false;
//            }

            this.Update();
        }
        catch (Exception ex)
        {
            util.debug_write_exception(ex);
        }
    }

    //************************************************************************//
    /**
     *	リスナー(マウスがドラッグされた)
     *
     *	@param	e
     *	@return	なし
     *	@version
     */
    //************************************************************************//
    public void mouseDragged(MouseEvent e)
    {
        try
        {
            System.out.println(util.debug_ask_class_method_name());
			
            m_end_point.x = (int) e.getSceneX();
            m_end_point.y = (int) e.getSceneY();
            
//            //*************************
//            //	アニメーションを止める
//            //*************************
//            if(this.m_OGL.is_animeting() == true)
//            {
//                this.m_OGL.anime_stop();
//                bAnimeForceStop = true;
//            }

            //Drag時の処理
            m_MouseCommand.dragged(m_end_point);
        }
        catch (Exception ex)
        {
            util.debug_write_exception(ex);
        }
    }

    //************************************************************************//
    /**
     *	リスナー(マウスが動いた)
     *
     *	@param	e
     *	@return	なし
     *	@version
     */
    //************************************************************************//
    public void mouseMoved(MouseEvent e)
    {
        try
        {
            //System.out.println(util.debug_ask_class_method_name());
        }
        catch (Exception ex)
        {
            util.debug_write_exception(ex);
        }
    }
    //************************************************************************//
    /**
     *	再描画
     *
     *	@param	e
     *	@return	なし
     *	@version
     */
    //************************************************************************//
    public void Update()
    {
        try{
			///////////////////////////////////////
			//	JavaFXの3Dオブジェクトをセット
			///////////////////////////////////////
			this.m_group_fx_object.getChildren().clear();
			this.m_database.get_commands().stream()
				.filter(com -> (com instanceof Display_3D_Object_Imp))
				.map(com -> (Display_3D_Object_Imp)com)
				.forEach(jfx -> {this.m_group_fx_object.getChildren().add(jfx.ask_JavafxNode());});
			
			/////////////////////////////////
			//	JavaFXのツリービューをセット
			/////////////////////////////////
			this.m_treeitem_fx_root.getChildren().clear();
			this.m_database.get_commands().stream()
				.filter(com -> (com instanceof TreeItem_Imp))
				.map(com -> (TreeItem_Imp)com)
				.forEach(jfx -> {this.m_treeitem_fx_root.getChildren().add(jfx.ask_JavafxTreeItem(true));});
		}
        catch (Exception ex)
        {
            util.debug_write_exception(ex);
        }
    }

}
