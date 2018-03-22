package _00_GUI_Main_JavaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class JavaFXController {

	@FXML	private Button			buttonRotation;
	@FXML	private Button			buttonPan;
	@FXML	private Button			buttonZoom;
	@FXML	private	ComboBox		comboToolChange;
	@FXML	private	TreeView		treeviewItems;


//	@FXML	private Pane			paneMainView;
	@FXML	private Label			labelTest;
	@FXML	private AnchorPane		AnchorPaneMainView;
	@FXML	private HBox			HBoxProcedure;

	public HBox getHBoxProcedure() {
		return HBoxProcedure;
	}

	public void setHBoxProcedure(HBox HBoxProcedure) {
		this.HBoxProcedure = HBoxProcedure;
	}
	



	@FXML
	public void buttonRotation_OnClicked(MouseEvent event)
	{
//		buttonRotation.setText("aaa");
		labelTest.setText("aaaaa");

	}
	@FXML
	public void buttonZoom_OnClicked(MouseEvent event)
	{

	}
	@FXML
	public void buttonPan_OnClicked(MouseEvent event)
	{

	}
//	/**
//	 * @return paneMainView
//	 */
//	public Pane getPaneMainView() {
//		return paneMainView;
//	}
//	/**
//	 * @param paneMainView セットする paneMainView
//	 */
//	public void setPaneMainView(Pane paneMainView) {
//		this.paneMainView = paneMainView;
//	}
	/**
	 * @return anchorPaneMainView
	 */
	public AnchorPane getAnchorPaneMainView() {
		return AnchorPaneMainView;
	}
	/**
	 * @param anchorPaneMainView セットする anchorPaneMainView
	 */
	public void setAnchorPaneMainView(AnchorPane anchorPaneMainView) {
		AnchorPaneMainView = anchorPaneMainView;
	}

    public Button getButtonRotation() {
        return buttonRotation;
    }

    public Button getButtonPan() {
        return buttonPan;
    }

    public Button getButtonZoom() {
        return buttonZoom;
    }

    public ComboBox getComboToolChange() {
        return comboToolChange;
    }

    public Label getLabelTest() {
        return labelTest;
    }

    public void setButtonRotation(Button buttonRotation) {
        this.buttonRotation = buttonRotation;
    }

    public void setButtonPan(Button buttonPan) {
        this.buttonPan = buttonPan;
    }

    public void setButtonZoom(Button buttonZoom) {
        this.buttonZoom = buttonZoom;
    }

    public void setComboToolChange(ComboBox comboToolChange) {
        this.comboToolChange = comboToolChange;
    }

    public void setLabelTest(Label labelTest) {
        this.labelTest = labelTest;
    }
	
	public void setTreeviewItems(TreeView treeviewItems) {
		this.treeviewItems = treeviewItems;
	}

	public TreeView getTreeviewItems() {
		return treeviewItems;
	}
}
