import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;

import card.Region;
import game.Deck;
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
import menu.Menu;

public class SelectRegionController implements Initializable {

  private Deck newDeck;
  private Menu menu = Menu.getMenu();

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
    selectSecondRegion.setVisible(true);
    firstRegion.getToggles().forEach(toggle -> {
      ((JFXRadioButton) toggle).setDisable(true);
    });
  }

  @FXML
  void handleSelectSecondRegion(ActionEvent event) {
    selectSecondRegion.setVisible(true);
    secondRegion.getToggles().forEach(toggle -> {
      ((JFXRadioButton) toggle).setDisable(true);
    });
    insertName.setVisible(true);
  }

  Region transformRadioToRegion(JFXRadioButton radio) {

    Region region = Region.NONE;

    switch (radio.getText()) {
      case "Demacia":
        region = Region.DEMACIA;
        break;
      case "Noxus":
        region = Region.NOXUS;
        break;
      case "Freljord":
        region = Region.FRELJORD;
        break;
      case "Nenhuma":
        region = Region.NONE;
        break;
      default:
        break;
    }

    return region;
  }

  @FXML
  void handleMoveToCardSelection(ActionEvent event) {

    JFXRadioButton selectedFirstRegion = (JFXRadioButton) firstRegion.getSelectedToggle();
    JFXRadioButton selectedSecondRegion = (JFXRadioButton) secondRegion.getSelectedToggle();

    newDeck = new Deck(transformRadioToRegion(selectedFirstRegion), transformRadioToRegion(selectedSecondRegion),
        deckName.getText());

    menu.getDecks().add(newDeck);

    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/cardsSelection.fxml"));
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
  public void initialize(URL location, ResourceBundle resources) {
    selectSecondRegion.setVisible(false);
    insertName.setVisible(false);

  }

}
