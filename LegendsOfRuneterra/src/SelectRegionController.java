import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SelectRegionController implements Initializable {

  @FXML
  private ToggleGroup firstRegion;

  @FXML
  private Pane selectSecondRegion;

  @FXML
  private TextField deckName;

  @FXML
  private ToggleGroup secondRegion;

  @FXML
  private Pane insertName;

  @FXML
  void handleSelectFirstRegion(ActionEvent event) {
    JFXRadioButton selectedRadio = (JFXRadioButton) firstRegion.getSelectedToggle();
    selectSecondRegion.setVisible(true);
    firstRegion.getToggles().forEach(toggle -> {
      ((JFXRadioButton) toggle).setDisable(true);
    });
  }

  @FXML
  void handleSelectSecondRegion(ActionEvent event) {
    JFXRadioButton selectedRadio = (JFXRadioButton) secondRegion.getSelectedToggle();
    selectSecondRegion.setVisible(true);
    secondRegion.getToggles().forEach(toggle -> {
      ((JFXRadioButton) toggle).setDisable(true);
    });
    insertName.setVisible(true);
  }

  @FXML
  void handleMoveToCardSelection(ActionEvent event) {

    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/cardsSelection.fxml"));
      Parent root = (Parent) fxmlLoader.load();
      Scene scene = new Scene(root, 1280, 720);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    selectSecondRegion.setVisible(false);
    insertName.setVisible(false);

  }

}
