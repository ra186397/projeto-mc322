import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import game.Deck;
import game.Game;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import menu.Menu;

public class DeckSelectionController implements Initializable {

  private Menu menu = Menu.getMenu();

  @FXML
  private JFXListView<String> listOfDecks;

  @FXML
  private Label chosen;

  @FXML
  private JFXButton readyToPlay;

  @FXML
  private Label player;

  ObservableList<String> listView = FXCollections.observableArrayList();

  @FXML
  void handleStart(ActionEvent event) {

    if (player.getText() == "Player 1") {

      if (chosen.getText() != "Deck Escolhido") {
        ArrayList<Deck> decks = this.menu.getDecks();
        boolean found = false;

        for (int i = 0; i < decks.size() && !found; i++) {
          if (decks.get(i).getName() == chosen.getText()) {
            this.menu.getPlayer1().selectDeck(decks.get(i));
            found = true;
          }
        }

        if (menu.getPlayer2().isHuman()) {
          player.setText("Player 2");
        } else {

          // AQUI ÒOOOOOOOOOOOOOO
          try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/arena.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();

          } catch (Exception e) {
            e.printStackTrace();
          }
        }

      }

    } else if (player.getText() == "Player 2") {

      ArrayList<Deck> decks = this.menu.getDecks();
      boolean found = false;

      for (int i = 0; i < decks.size() && !found; i++) {
        if (decks.get(i).getName() == chosen.getText()) {
          this.menu.getPlayer2().selectDeck(decks.get(i));
          found = true;
        }
      }

      Game game = Game.getGame(menu.getPlayer1(), menu.getPlayer2());
      game.startGame();

      // try {
      //   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/arena.fxml"));
      //   Parent root = (Parent) fxmlLoader.load();
      //   Scene scene = new Scene(root);
      //   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      //   stage.setScene(scene);
      //   stage.show();

      // } catch (Exception e) {
      //   e.printStackTrace();
      // }

    }
  }

  @FXML
  void handleMoveToDeckCreation(ActionEvent event) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/SelectRegion.fxml"));
      Parent root = (Parent) fxmlLoader.load();
      Scene scene = new Scene(root);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setScene(scene);
      stage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static class Cell extends ListCell<String> {
    HBox hbox = new HBox();
    JFXButton btn = new JFXButton("Escolher Deck");
    Label label = new Label("");
    Pane pane = new Pane();

    public Cell(Label chosen) {
      super();

      Color color = Color.valueOf("#cfc6b7");

      this.setStyle("-fx-background-color: #201B21");
      hbox.getChildren().addAll(label, pane, btn);
      hbox.setHgrow(pane, Priority.ALWAYS);
      hbox.setStyle("-fx-background-color: #201B21");
      label.setTextFill(color);
      btn.setTextFill(color);
      btn.setOnAction(e -> chosen.setText(getListView().getItems().get(getIndex())));
    }

    public void updateItem(String name, boolean empty) {
      super.updateItem(name, empty);
      setText(null);
      setGraphic(null);

      if (name != null && !empty) {
        label.setText(name);
        setGraphic(hbox);
      }
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    if (menu.getPlayer1().getDeck() == null) {
      player.setText("Player 1");
    } else {
      player.setText("Player 2");
    }
    player.setLayoutX(500);

    ArrayList<Deck> decks = this.menu.getDecks();

    for (Deck object : decks) {
      listView.add(object.getName());
    }

    listOfDecks.setItems(listView);

    listOfDecks.setCellFactory(param -> new Cell(chosen));

  }

}
