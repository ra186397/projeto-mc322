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
import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import menu.Menu;

public class CardsSelectionController implements Initializable {

  private Menu menu = Menu.getMenu();

  @FXML
  private Label numOfCards;

  @FXML
  private JFXListView<Follower> followersDemacia;

  @FXML
  private JFXListView<Follower> followersFreljord;

  @FXML
  private JFXListView<Spell> spellsDemacia; 

  @FXML
  private JFXListView<Spell> spellsFreljord;

  ObservableList<Follower> ObservableUnitsDemacia = FXCollections.observableArrayList();
  ObservableList<Follower> ObservableUnitsFreljord = FXCollections.observableArrayList();
  ObservableList<Spell> ObservableSpellsDemacia = FXCollections.observableArrayList();
  ObservableList<Spell> ObservableSpellsFreljord = FXCollections.observableArrayList();

  static class CellUnits extends ListCell<Follower> {
    HBox vbox = new HBox();
    ImageView imageView = new ImageView();
    Label power = new Label("");
    Label health = new Label("");

    public CellUnits(Deck deck, Label numOfCards) {

      Color primaryColor = Color.valueOf("#cfc6b7");
      Color whiteColor = Color.valueOf("#fff");
      JFXButton checkBox = new JFXButton("Selecionar/Remover");
      Pane moldura = new Pane();

      power.setTextFill(whiteColor);
      health.setTextFill(whiteColor);
      moldura.getChildren().addAll(imageView, power, health);
      moldura.setMaxWidth(200);
      moldura.setMaxHeight(301);
      power.setLayoutX(20);
      power.setLayoutY(252);
      power.setStyle("-fx-font-size: 24px");
      health.setLayoutX(167);
      health.setLayoutY(252);
      health.setStyle("-fx-font-size: 24px");
      this.setStyle("-fx-background-color: #201B21");
      vbox.getChildren().addAll(moldura, checkBox);
      vbox.setSpacing(5);
      vbox.setAlignment(Pos.CENTER);
      vbox.setStyle("-fx-background-color: #201B21");
      checkBox.setTextFill(primaryColor);
      checkBox.setOnMouseClicked(e -> {
        if (deck.getCards().contains(getItem())) {
          deck.getCards().remove(getItem());
        } else {
          deck.addCard(getItem());
        }
        numOfCards.setText(Integer.toString(deck.getCards().size()));
      });
    }

    public void updateItem(Follower follower, boolean empty) {
      super.updateItem(follower, empty);

      if (follower != null && !empty) {
        power.setText(Integer.toString(follower.getBasePower()));
        health.setText(Integer.toString(follower.getBaseHealth()));
        imageView.setImage(new Image(follower.getImage(), 200.00, 301.00, true, true));
        setGraphic(vbox);
      }
    }
  }

  static class CellSpells extends ListCell<Spell> {
    HBox vbox = new HBox();
    ImageView imageView = new ImageView();

    public CellSpells(Deck deck, Label numOfCards) {

      Color primaryColor = Color.valueOf("#cfc6b7");
      JFXButton checkBox = new JFXButton("Selecionar/Remover");
      Pane moldura = new Pane();

      moldura.getChildren().addAll(imageView);
      moldura.setMaxWidth(200);
      moldura.setMaxHeight(301);
      this.setStyle("-fx-background-color: #201B21");
      vbox.getChildren().addAll(moldura, checkBox);
      vbox.setSpacing(5);
      vbox.setAlignment(Pos.CENTER);
      vbox.setStyle("-fx-background-color: #201B21");
      checkBox.setTextFill(primaryColor);
      checkBox.setOnMouseClicked(e -> {
        if (deck.getCards().contains(getItem())) {
          deck.getCards().remove(getItem());
        } else {
          deck.addCard(getItem());
        }
        numOfCards.setText(Integer.toString(deck.getCards().size()));
      });
    }

    public void updateItem(Spell spell, boolean empty) {
      super.updateItem(spell, empty);

      if (spell != null && !empty) {
        imageView.setImage(new Image(spell.getImage(), 200.00, 301.00, true, true));
        setGraphic(vbox);
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
    insertIntoObservable(ObservableUnitsFreljord, menu.getUnitList(Region.FRELJORD));
    followersDemacia.setItems(ObservableUnitsDemacia);
    followersDemacia.setCellFactory(param -> new CellUnits(menu.getDecks().get(menu.getDecks().size() - 1), numOfCards));

    followersFreljord.setItems(ObservableUnitsFreljord);
    followersFreljord.setCellFactory(param -> new CellUnits(menu.getDecks().get(menu.getDecks().size() - 1), numOfCards));

    insertIntoObservable(ObservableSpellsDemacia, menu.getSpellList(Region.DEMACIA));
    insertIntoObservable(ObservableSpellsFreljord, menu.getSpellList(Region.FRELJORD));
    spellsDemacia.setItems(ObservableSpellsDemacia);
    spellsDemacia.setCellFactory(param -> new CellSpells(menu.getDecks().get(menu.getDecks().size() - 1), numOfCards));

    spellsFreljord.setItems(ObservableSpellsFreljord);
    spellsFreljord.setCellFactory(param -> new CellSpells(menu.getDecks().get(menu.getDecks().size() - 1), numOfCards));

  }

}
