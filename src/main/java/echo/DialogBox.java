package echo;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle userImage;

    @FXML
    private Rectangle textBoxBorder;

    @FXML
    private StackPane profPicPane;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        profPicPane.setPadding(new Insets(10,10,10,10));
        dialog.setText(text);
        Text texts = new Text(text);
        enTextBox(texts);
        userImage.setFill(new ImagePattern(img));

    }

    /**
     * Styles the text box by setting its size, background colour, and shadow effect.
     *
     * @param text The text object containing the dialog message.
     */
    private void enTextBox(Text text) {
        textBoxBorder.setFill(Color.WHITE);
        double padding = 15;
        textBoxBorder.setHeight(text.getLayoutBounds().getHeight() + padding);
        textBoxBorder.setWidth(text.getLayoutBounds().getWidth() + padding);
        textBoxBorder.setEffect(new DropShadow(1, -1, 2, Color.LIGHTGRAY));
    }


    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the messgae.
     *
     * @param text The message..
     * @param img The profile image.
     * @return A DialogBox configured for the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }


    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
