/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _00_GUI_Main_JavaFX;

import _20_Object_Template.TreeItem_Imp;
import javafx.scene.control.TreeCell;

/**
 *
 * @author 真也
 */
public final class TreeCellImpl
	extends TreeCell<TreeItem_Imp>
{
	
	public TreeCellImpl(){
		
	}
	
	@Override
	public void updateItem(TreeItem_Imp item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setText(null);
			setGraphic(null);
		} else {
			setText(getItem() == null ? "" : getItem().getTreeItemName());
			setGraphic(getTreeItem().getGraphic());
			setContextMenu(getTreeItem().getValue().getPopupMenu());
		}
	}
}
