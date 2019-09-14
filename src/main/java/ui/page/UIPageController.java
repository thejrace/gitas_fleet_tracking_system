package ui.page;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UIPageController  {

    @FXML protected Label uiTitle;

    public void setTitle( String title ){
        uiTitle.setText( title );
    }

}
