package _00_GUI_Main_JavaFX;

import _10_Base_Model_etc.Model_Base;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.DepthTest;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Translate;


public class Main extends Application
{
	private Group		root3D = new Group();
	private Translate	cameraTranslate = new Translate(100.0, 0.0, -1000.0);
    private static	Model_Base  static_base_current = null;
	private static	Stage	static_primaryStage = null;	//一番下の画面。親子関係を持たせるためにすべてから見えるように。

	///////////////////////////////////
	//
	//	getter
	//
	///////////////////////////////////
	static public Stage get_primary_stage()	{return static_primaryStage;};
	static public Model_Base get_model_base()	{return static_base_current;}
	
	///////////////////////////////////
	//
	//	画面スタート
	//
	///////////////////////////////////
	@Override
	public void start(Stage primaryStage) {
		try
		{
			static_primaryStage = primaryStage;
			///////////////////////////////////
			//
			//	FXMLのUIとコントローラを取得
			//
			///////////////////////////////////
			FXMLLoader			loader	= new FXMLLoader(getClass().getResource("/_00_GUI_Main_JavaFX/MainFrame.fxml"));	//fxml中の名前も変える。
			Parent				root	= loader.load();
			JavaFXController	jfxCtrl	= loader.getController();
            
            //メインパネルを取得
			AnchorPane panelMainView = jfxCtrl.getAnchorPaneMainView();
            panelMainView.setDepthTest(DepthTest.ENABLE);
            
            //ツリーテーブルビューを取得
            TreeView treeviewItems = jfxCtrl.getTreeviewItems();

			//プロシージャを取得
			HBox HBoxProcedure = jfxCtrl.getHBoxProcedure();
			
			///////////////////////////////////
			//
			//	Model
			//
			///////////////////////////////////
			static_base_current = new Model_Base();
			static_base_current.setup(
			    panelMainView,	//I     メインパネル
                treeviewItems,	//I     ツリーテーブルビュー
				HBoxProcedure	//I		プロシージャ
			);

			///////////////////////////////////
			//
			//
			//
			///////////////////////////////////
			Scene scene = new Scene(root, 900, 500, true, SceneAntialiasing.BALANCED);
            root.setDepthTest(DepthTest.ENABLE);

			primaryStage.setScene(scene);
			primaryStage.show();

			///////////////////////////////////
			//
			//	テスト：ダイアログ
			//
			///////////////////////////////////
//			EditDialogControler testEdit = EditDialogControler.factory_EditDialogControler(Main.get_primary_stage());
//			EditDialogControler testEdit = new EditDialogControler();
//			EditDialogControler testEdit = EditDialogControler.factory();
//			testEdit.start(Main.get_primary_stage());

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
