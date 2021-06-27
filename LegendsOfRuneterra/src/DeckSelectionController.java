import java.net.URL;
import java.util.ResourceBundle;

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

public class DeckSelectionController implements Initializable {

  @FXML
  private JFXListView<String> listOfDecks;

  ObservableList<String> listView = FXCollections.observableArrayList("Demacia", "Noxus");

  static class Cell extends ListCell<String> {
    HBox hbox = new HBox();
    Button btn = new Button("Escolher Deck");
    Label label = new Label("");
    Pane pane = new Pane();

    public Cell() {
      super();

      hbox.getChildren().addAll(label, pane, btn);
      hbox.setHgrow(pane, Priority.ALWAYS);
      btn.addEventHandler();
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

    listOfDecks.setCellFactory(param -> new Cell());

  }

}
