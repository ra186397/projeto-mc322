import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import card.Follower;
import card.Region;
import card.Spell;
import javafx.scene.paint.Color;
import game.Deck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

  ObservableList<Follower> ObservableUnitsDemacia = FXCollections.observableArrayList();
  ObservableList<Spell> ObservableSpellsDemacia = FXCollections.observableArrayList();

  void getDeck(Deck deck) {
    this.deck = deck;
    System.out.println(deck.getName());
  }

  static class Cell extends ListCell<Follower> {
    HBox hbox = new HBox();
    JFXButton btn = new JFXButton("Escolher Carta");
    ImageView imageView = new ImageView();
    Label name = new Label("");
    Label power = new Label("");
    Label health = new Label("");
    Pane pane = new Pane();

    public Cell() {
      super();

      Color color = Color.valueOf("#cfc6b7");
      name.setTextFill(color);
      power.setTextFill(color);
      health.setTextFill(color);

      this.setStyle("-fx-background-color: #201B21");
      hbox.getChildren().addAll(imageView, name, power, health, pane, btn);
      hbox.setHgrow(pane, Priority.ALWAYS);
      hbox.setStyle("-fx-background-color: #201B21");
      btn.setTextFill(color);
    }

    public void updateItem(Follower follower, boolean empty) {
      super.updateItem(follower, empty);
      setText(null);
      setGraphic(null);

      if (follower != null && !empty) {
        name.setText(follower.getName());
        power.setText(Integer.toString(follower.getBasePower()));
        health.setText(Integer.toString(follower.getBaseHealth()));

        imageView.setImage(new Image(follower.getImage()));
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

    insertIntoObservable(ObservableUnitsDemacia, menu.getUnitList(Region.DEMACIA));

    followersDemacia.setItems(ObservableUnitsDemacia);
    followersDemacia.setCellFactory(param -> new Cell());

  }

}
