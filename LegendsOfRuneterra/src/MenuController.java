import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import game.Player;
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
import menu.TypePlayer;

public class MenuController implements Initializable {

  private Menu menu = Menu.getMenu();

  @FXML
  void handleIA(ActionEvent event) {
    this.handleMoveToDeckSelection(event);

    this.menu.selectPlayers(TypePlayer.IAVIA);

  }

  @FXML
  void handlePlayerIA(ActionEvent event) {
    this.handleMoveToDeckSelection(event);

    this.menu.selectPlayers(TypePlayer.PVIA);
  }

  @FXML
  void handlePlayerPlayer(ActionEvent event) {
    this.handleMoveToDeckSelection(event);

    this.menu.selectPlayers(TypePlayer.PVP);
  }

  public void handleMoveToDeckSelection(ActionEvent event) {

    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DeckSelection.fxml"));
      Parent root = (Parent) fxmlLoader.load();
      Scene scene = new Scene(root);
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
