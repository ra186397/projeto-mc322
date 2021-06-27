import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import menu.Menu;

public class MenuController implements Initializable {

  private Menu menu;

  @FXML
  void handleIA(ActionEvent event) {
    this.handleMoveToDeckSelection(event);

    this.menu.openMenu();

  }

  @FXML
  void handlePlayerIA(ActionEvent event) {
    this.handleMoveToDeckSelection(event);

    this.menu.openMenu();
  }

  @FXML
  void handlePlayerPlayer(ActionEvent event) {
    this.handleMoveToDeckSelection(event);

    this.menu.openMenu();
  }

  public void handleMoveToDeckSelection(ActionEvent event) {

    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DeckSelection.fxml"));
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
  public void initialize(URL arg0, ResourceBundle arg1) {
    this.menu = Menu.getMenu();

  }

}
