package _11_Base_Button_etc;
//import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.ImageIcon;
//import javax.swing.JButton;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import _42_Utility.util;



abstract public class Button_Base
//	implements ActionListener
{
	static final String def_icon_path = "D:\\workspace\\Eclipse_workspace\\pic\\stop.png";
	
	String m_Name;			//名前
	Button m_jbtn;			//JavaFXのボタン
	ImageView m_imageView;	//JavaFXのイメージ
	
	boolean m_enable_updown;	//ボタンの押された状態、押されていない状態を使用する。
	boolean is_down = false;	//ボタンの押す、押さない状態を使用するときのボタンの状態
//	Command m_cmd;

	int width = 50;	//サイズ
	int height = 50;
	int nBetween = 0;	//間隔
//	int nTextv = JButton.BOTTOM;	//文字の位置
//	int nTexth = JButton.CENTER;
	

	
	//************************************************************************//
	/**
	*	コンストラクタ
	*
	*	@param
	*	@return
	*	@version
	*/
	//************************************************************************//
	Button_Base(
		String	Name, 
		String	IconPath,
		boolean	enable_updown
		/*, Command cmd*/
	)
	{
		try{
			System.out.println(util.debug_ask_class_method_name());

			m_Name = Name;
			m_enable_updown = enable_updown;
	
			m_jbtn = new Button();
			m_jbtn.setOnMouseClicked(mouseevent -> {this.push_base(mouseevent);});
			
			//名前
			m_jbtn.setText(Name);
			//アイコンのパス
			if(IconPath == null)
			{
				IconPath = def_icon_path;
			}

//			m_jbtn.setGraphic(new ImageView(IconPath));
			
			//サイズ(指定すると固定)
//			m_jbtn.setPrefHeight(height);
//			m_jbtn.setPrefWidth(width);

			//アイコンと文字の間隔（ピクセル：デフォルトは4）
//			m_jbtn.setIconTextGap(nBetween);
			//文字の位置（縦）
//			m_jbtn.setHorizontalTextPosition(nTexth);
			//文字の位置（横）
//			m_jbtn.setVerticalTextPosition(nTextv);
			//アクションリスナーの登録
//			m_jbtn.addActionListener(this);
			//登録
	//		JComp.add(m_jbtn);
			
			
			
		}
		catch (Exception e){
			util.debug_write_exception(e);
		}	
	}
	//************************************************************************//
	/**
	*	setter
	*/
	//************************************************************************//
	public void setName(String name){	m_jbtn.setText(name);	}
	public void setIconPath(String picpath){	m_jbtn.setGraphic(new ImageView(picpath));	}
	//************************************************************************//
	/**
	*	getter
	*/
	//************************************************************************//
	public String 	getName(){	return m_Name;	}
	public Button	getButton(){	return m_jbtn;	}
	
	//************************************************************************//
	/**
	*	押したときの動作
	*/
	//************************************************************************//
	private void push_base(MouseEvent mouse_event)
	{
    try{
		System.out.println(util.debug_ask_class_method_name());
		
		//ボタンを押した、押していない状態を設定
		if(m_enable_updown == false){
			this.m_enable_updown = true;
		}else{
			this.m_enable_updown = false;
		}
		
		//push
		push(mouse_event);
    }
    catch (Exception ex){
	    util.debug_write_exception(ex);
    }
	
	}
	abstract public void push(MouseEvent e);

	//************************************************************************//
	/**
	*	
	*/
	//************************************************************************//
	public void setVisible(Boolean bool){m_jbtn.setVisible(bool);}

	//************************************************************************//
	/**
	*	
	*/
	//************************************************************************//
	public Boolean isOn(){return m_enable_updown;} //m_jbtn.isSelected();}
	
	public void setSelected(Boolean bool){m_enable_updown = bool;}
	

	//************************************************************************//
	/**
	*	JavaFXのボタンを取得
	*/
	//************************************************************************//
	public Button ask_JavafxButton()
	{
		return m_jbtn;
	}
}
