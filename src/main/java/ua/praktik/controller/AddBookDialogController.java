package ua.praktik.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ua.praktik.model.Book;

public class AddBookDialogController {

  @FXML private TextField titleField;

  @FXML private TextField authorIdField;

  @FXML private TextField isbnField;

  @FXML private TextField yearField;

  @FXML private TextField publisherIdField;

  @FXML private TextField categoryIdField;

  @FXML private TextField priceField;

  @FXML private TextField pageCountField;

  @FXML private TextField imagePathField;

  public Book getBookFromFields() {
    String title = titleField.getText();
    Integer authorId = Integer.parseInt(authorIdField.getText());
    String isbn = isbnField.getText();
    Integer year = Integer.parseInt(yearField.getText());
    Integer publisherId = Integer.parseInt(publisherIdField.getText());
    Integer categoryId =
        categoryIdField.getText().isEmpty() ? null : Integer.parseInt(categoryIdField.getText());
    Double price = Double.parseDouble(priceField.getText());
    Integer pageCount = Integer.parseInt(pageCountField.getText());
    String imagePath = imagePathField.getText();

    return Book.builder()
        .title(title)
        .authorId(authorId)
        .isbn(isbn)
        .publicationYear(year)
        .publisherId(publisherId)
        .categoryId(categoryId)
        .price(price)
        .pageCount(pageCount)
        .imagePath(imagePath)
        .build();
  }

  public boolean validateInput() {
    try {
      Integer.parseInt(yearField.getText());
      Double.parseDouble(priceField.getText());
      Integer.parseInt(pageCountField.getText());
      Integer.parseInt(authorIdField.getText());
      Integer.parseInt(publisherIdField.getText());
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public TextField getTitleField() {
    return titleField;
  }

  public TextField getAuthorIdField() {
    return authorIdField;
  }

  public TextField getIsbnField() {
    return isbnField;
  }

  public TextField getYearField() {
    return yearField;
  }

  public TextField getPublisherIdField() {
    return publisherIdField;
  }

  public TextField getCategoryIdField() {
    return categoryIdField;
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
