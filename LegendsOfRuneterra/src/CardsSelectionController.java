import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import card.Follower;
import card.Spell;
import game.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import menu.Menu;

public class CardsSelectionController implements Initializable {

  private Deck deck;
  private Menu menu = Menu.getMenu();

  @FXML
  private JFXListView<Follower> followersDemacia;

  @FXML
  private JFXListView<?> spellsDemacia;

  @FXML
  private JFXListView<?> followersNoxus;

  @FXML
  private JFXListView<?> spellsNoxus;

  @FXML
  private JFXListView<?> followersFreljord;

  @FXML
  private JFXListView<?> spellsFreljord;

  ObservableList<Follower> ObservableUnitsDemacia = FXCollections.observableArrayList();
  ObservableList<Spell> ObservableSpellsDemacia = FXCollections.observableArrayList();

  void getDeck(Deck deck) {
    this.deck = deck;
    System.out.println(deck.getName());
  }

  static class Cell extends ListCell<Follower> {
    HBox hbox = new HBox();
    JFXButton btn = new JFXButton("Escolher Deck");
    Label name = new Label("");
    Label description = new Label("");
    Label power = new Label("");
    Label health = new Label("");
    Label cost = new label("");
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

    public void updateItem(Follower follwer, boolean empty) {
      super.updateItem(name, empty);
      setText(null);
      setGraphic(null);

      if (name != null && !empty) {
        label.setText(name);
        setGraphic(hbox);
      }
    }
  }

  void insertIntoObservable(ObservableList observableList, ArrayList arrayList) {

    for (int i = 0; i < arrayList.size(); i++) {
      observableList.add(arrayList.get(i));
    }

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

}
