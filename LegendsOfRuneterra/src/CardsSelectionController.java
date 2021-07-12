import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import card.Follower;
import card.Region;
import card.Spell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import game.Deck;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tab;
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
  private JFXListView<Follower> followersNoxus;

  @FXML
  private JFXListView<Spell> spellsDemacia;

  @FXML
  private JFXListView<Spell> spellsFreljord;

  @FXML
  private JFXListView<Spell> spellsNoxus;

  @FXML
  private JFXButton btnCreateDeck;

  @FXML
  private Tab demaciaCards;

  @FXML
  private Tab noxusCards;

  @FXML
  private Tab freljordCards;

  ObservableList<Follower> ObservableUnitsDemacia = FXCollections.observableArrayList();
  ObservableList<Follower> ObservableUnitsFreljord = FXCollections.observableArrayList();
  ObservableList<Follower> ObservableUnitsNoxus = FXCollections.observableArrayList();
  ObservableList<Spell> ObservableSpellsDemacia = FXCollections.observableArrayList();
  ObservableList<Spell> ObservableSpellsFreljord = FXCollections.observableArrayList();
  ObservableList<Spell> ObservableSpellsNoxus = FXCollections.observableArrayList();

  static class CellUnits extends ListCell<Follower> {
    HBox hbox = new HBox();
    ImageView imageView = new ImageView();
    Label power = new Label("");
    Label health = new Label("");

    public CellUnits(Deck deck, Label numOfCards) {

      Color primaryColor = Color.valueOf("#cfc6b7");
      Color whiteColor = Color.valueOf("#fff");
      JFXButton button = new JFXButton("Selecionar/Remover");
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
      hbox.getChildren().addAll(moldura, button);
      hbox.setSpacing(5);
      hbox.setAlignment(Pos.CENTER);
      hbox.setStyle("-fx-background-color: #201B21");
      button.setTextFill(primaryColor);
      button.setOnMouseClicked(e -> {
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
        setGraphic(hbox);
      }
    }
  }

  static class CellSpells extends ListCell<Spell> {
    HBox hbox = new HBox();
    ImageView imageView = new ImageView();

    public CellSpells(Deck deck, Label numOfCards) {

      Color primaryColor = Color.valueOf("#cfc6b7");
      JFXButton button = new JFXButton("Selecionar/Remover");
      Pane moldura = new Pane();

      moldura.getChildren().addAll(imageView);
      moldura.setMaxWidth(200);
      moldura.setMaxHeight(301);
      this.setStyle("-fx-background-color: #201B21");
      hbox.getChildren().addAll(moldura, button);
      hbox.setSpacing(5);
      hbox.setAlignment(Pos.CENTER);
      hbox.setStyle("-fx-background-color: #201B21");
      button.setTextFill(primaryColor);
      button.setOnMouseClicked(e -> {
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
        setGraphic(hbox);
      }
    }
  }

  @FXML
  void createDeck(ActionEvent event) {
    if (Integer.parseInt(numOfCards.getText()) > 30) {
      btnCreateDeck.setText("Você só pode ter até 30 cartas!");
    } else if (Integer.parseInt(numOfCards.getText()) <= 0) {
      btnCreateDeck.setText("Você deve ter pelo menos um carta!");
    } else {
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
  }

  void insertIntoObservable(ObservableList observableList, ArrayList arrayList) {

    for (int i = 0; i < arrayList.size(); i++) {
      observableList.add(arrayList.get(i));
    }

  }

  void blockTab(Region region) {
    switch (region) {
      case DEMACIA:
        demaciaCards.setDisable(false);
        break;
      case NOXUS:
        noxusCards.setDisable(false);
        break;
      case FRELJORD:
        freljordCards.setDisable(false);
        break;
      default:
        break;
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    demaciaCards.setDisable(true);
    noxusCards.setDisable(true);
    freljordCards.setDisable(true);

    blockTab(menu.getDecks().get(menu.getDecks().size() - 1).getFirstRegion());
    blockTab(menu.getDecks().get(menu.getDecks().size() - 1).getSecondRegion());

    insertIntoObservable(ObservableUnitsDemacia, menu.getUnitList(Region.DEMACIA));
    insertIntoObservable(ObservableUnitsFreljord, menu.getUnitList(Region.FRELJORD));
    insertIntoObservable(ObservableUnitsNoxus, menu.getUnitList(Region.NOXUS));
    followersDemacia.setItems(ObservableUnitsDemacia);
    followersDemacia
        .setCellFactory(param -> new CellUnits(menu.getDecks().get(menu.getDecks().size() - 1), numOfCards));

    followersFreljord.setItems(ObservableUnitsFreljord);
    followersFreljord
        .setCellFactory(param -> new CellUnits(menu.getDecks().get(menu.getDecks().size() - 1), numOfCards));
    
    followersNoxus.setItems(ObservableUnitsNoxus);
    followersNoxus
        .setCellFactory(param -> new CellUnits(menu.getDecks().get(menu.getDecks().size() - 1), numOfCards));

    insertIntoObservable(ObservableSpellsDemacia, menu.getSpellList(Region.DEMACIA));
    insertIntoObservable(ObservableSpellsFreljord, menu.getSpellList(Region.FRELJORD));
    insertIntoObservable(ObservableSpellsNoxus, menu.getSpellList(Region.NOXUS));
    spellsDemacia.setItems(ObservableSpellsDemacia);
    spellsDemacia.setCellFactory(param -> new CellSpells(menu.getDecks().get(menu.getDecks().size() - 1), numOfCards));

    spellsFreljord.setItems(ObservableSpellsFreljord);
    spellsFreljord.setCellFactory(param -> new CellSpells(menu.getDecks().get(menu.getDecks().size() - 1), numOfCards));

    spellsNoxus.setItems(ObservableSpellsNoxus);
    spellsNoxus.setCellFactory(param -> new CellSpells(menu.getDecks().get(menu.getDecks().size() - 1), numOfCards));

  }

}
