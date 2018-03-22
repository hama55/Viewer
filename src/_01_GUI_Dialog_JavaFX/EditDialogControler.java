/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _01_GUI_Dialog_JavaFX;

import _00_GUI_Main_JavaFX.Main;
import _30_Command.Command_Imp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.Window;
import utility.util;

/**
 *
 * @author 真也
 */
//public class EditDialogControler extends Application{
public class EditDialogControler{
	
	static String path_fxml = "/_01_GUI_Dialog_JavaFX/EditDialog.fxml";
	
	@FXML	private	TextArea	textareaEdit;
	@FXML	private	TextArea	textareaMessage;

	@FXML	private	Button		buttonOk;
	@FXML	private	Button		buttonCancel;
	
	private	Stage				dialogStage = null;
	private Parent				m_root;
	
	private	Command_Imp		m_ok_command = null;	//OK時のコマンド
	
	/***************************************************************************
	 * 
	 * @return 
	 */
	static public EditDialogControler factory()
	{
		try{
			System.out.println(util.debug_ask_class_method_name());

			//fxmlをロードする。
			FXMLLoader			loader	= new FXMLLoader(EditDialogControler.class.getResource(path_fxml));	//fxml中の名前も変える。
			//fxmlのGUIを取得する。
			Parent				root	= loader.load();
			//fxmlのコントローラを取得する。
			EditDialogControler	jfxCtrl	= loader.getController();
			//rootをセットする
			jfxCtrl.set_root(root);

			return jfxCtrl;
		}
		catch(Exception ex){
			util.debug_write_exception(ex);
		}
		
		return null;
	}
	/***************************************************************************
	 * setter
	 */
	public void set_root(Parent r){m_root = r;}
	public void set_command(Command_Imp command){this.m_ok_command = command;}
	
	/***************************************************************************
	 *	getter
	 */
	public TextArea get_textarea_edit()	{return this.textareaEdit;};
	
	/***************************************************************************
	 *	コンストラクタ
	 */
	public EditDialogControler()
	{
	}

	/***************************************************************************
	 * 
	 * OKボタンが押された時の処理
	 */
	private void ok_button()
	{
		try{
			System.out.println(util.debug_ask_class_method_name());
			
			//現在のメッセージを取得
			String before_message = textareaMessage.getText();

			if(this.m_ok_command == null)
			{
				//エラー文を出力
				String message = "システムエラー：コマンドが設定されていません";
				textareaMessage.setText(before_message + message + util.string_get_newline());

				return;
			}
			if(textareaEdit != null)
			{			
				//テキストエディタの文を読み込み
				String command_string = textareaEdit.getText();
				this.m_ok_command.set_command_string( command_string );

				//コマンドを実行
				String message = this.m_ok_command.execute(null);
				textareaMessage.setText(before_message + message + util.string_get_newline());

				//更新
				Main.get_model_base().Update();
			}
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}

	}
	/***************************************************************************
	 * 
	 * キャンセルボタンが押された時の処理
	 */
	private void cancel_button()
	{
		try{
			System.out.println(util.debug_ask_class_method_name());
			
			//ダイアログを閉じる
			if(dialogStage != null){
				dialogStage.close();
			}
		}
		catch (Exception ex){
			util.debug_write_exception(ex);
		}
		

	}
	/***************************************************************************
	 * 
	 * ダイアログを表示
	 */
	public void start(Window parent_window)
	{
		try{
			System.out.println(util.debug_ask_class_method_name());
			
			//キャンセルボタンの動作を定義
			buttonCancel.setOnMouseClicked(mouseevent -> {this.cancel_button();});
			//OKボタンの動作を定義
			buttonOk.setOnMouseClicked(mouseevent -> {this.ok_button();});

			//シーンを作る
			Scene scene = new Scene(m_root, 300, 0, true, SceneAntialiasing.BALANCED);
			
			//ダイアログを作る
			dialogStage = new Stage();
			
			//ダイアログにシーンを設定する。
			dialogStage.setScene(scene);
			
			//親のウィンドウを設定する
			dialogStage.initOwner(parent_window);

			//初期コマンドをセット
			textareaEdit.setText(this.m_ok_command.get_command_string());
			
			//ダイアログを表示する	
			dialogStage.show();
		}
		catch(Exception ex){
		    util.debug_write_exception(ex);
		}
	}

	
}
