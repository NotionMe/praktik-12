package ua.praktik.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ua.praktik.model.Book;

public class AddBookDialogController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField publisherField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField pageCountField;

    @FXML
    private TextField imagePathField;

    public Book getBookFromFields() {
        String title = titleField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();
        int year = Integer.parseInt(yearField.getText());
        String publisher = publisherField.getText();
        double price = Double.parseDouble(priceField.getText());
        int pageCount = Integer.parseInt(pageCountField.getText());
        String imagePath = imagePathField.getText();

        return new Book(title, author, isbn, year, publisher, price, pageCount, imagePath);
    }

    public boolean validateInput() {
        try {
            Integer.parseInt(yearField.getText());
            Double.parseDouble(priceField.getText());
            Integer.parseInt(pageCountField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public TextField getTitleField() {
        return titleField;
    }

    public TextField getAuthorField() {
        return authorField;
    }

    public TextField getIsbnField() {
        return isbnField;
    }

    public TextField getYearField() {
        return yearField;
    }

    public TextField getPublisherField() {
        return publisherField;
    }

    public TextField getPriceField() {
        return priceField;
    }

    public TextField getPageCountField() {
        return pageCountField;
    }

    public TextField getImagePathField() {
        return imagePathField;
    }
}