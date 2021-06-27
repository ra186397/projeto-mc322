import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class DeckSelectionController implements Initializable {

  @FXML
  private JFXListView<String> listOfDecks;

  @FXML
  private Label escolha;

  @FXML
  private JFXButton readyToPlay;

  ObservableList<String> listView = FXCollections.observableArrayList("Demacia", "Noxus");

  static class Cell extends ListCell<String> {
    HBox hbox = new HBox();
    JFXButton btn = new JFXButton("Escolher Deck");
    Label label = new Label("");
    Pane pane = new Pane();

    public Cell(Label escolha) {
      super();

      Color color = Color.valueOf("#cfc6b7");

      this.setStyle("-fx-background-color: #201B21");
      hbox.getChildren().addAll(label, pane, btn);
      hbox.setHgrow(pane, Priority.ALWAYS);
      hbox.setStyle("-fx-background-color: #201B21");
      label.setTextFill(color);
      btn.setTextFill(color);
      btn.setOnAction(e -> escolha.setText(getListView().getItems().get(getIndex())));
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

    listOfDecks.setItems(listView);

    listOfDecks.setCellFactory(param -> new Cell(escolha));

  }

}
