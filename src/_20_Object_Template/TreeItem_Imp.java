/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _20_Object_Template;



import javafx.scene.control.ContextMenu;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;

//************************************************************************//
/**
*	ツリービューに表示するアイテム
*
*	@param
*	@return
*	@version
*/
//************************************************************************//
public interface TreeItem_Imp
{
	static String static_tree_item_dummy = "dummy";
//	TreeItem_Imp_Data data=null;
	
	@Override
	public String toString();
	
	public String getTreeItemName();

	//ツリーアイテムを作成
	public TreeItem ask_JavafxTreeItem(boolean is_open);
	
	//ポップアップメニュー
	default public ContextMenu getPopupMenu(){return null;}

	//アイコン
	default public Image getTreeItemIcon(){return null;};

}
