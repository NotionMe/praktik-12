package ua.praktik.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {

  private int id;
  private String title;
  private String author;
  private String isbn;
  private int publicationYear;
  private String publisher;
  private double price;
  private int pageCount;
  private String imagePath;

  public Book(
      int id,
      String title,
      String author,
      String isbn,
      int publicationYear,
      String publisher,
      double price,
      int pageCount,
      String imagePath) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publicationYear = publicationYear;
    this.publisher = publisher;
    this.price = price;
    this.pageCount = pageCount;
    this.imagePath = imagePath;
  }

  public Book(
      String title,
      String author,
      String isbn,
      int publicationYear,
      String publisher,
      double price,
      int pageCount) {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publicationYear = publicationYear;
    this.publisher = publisher;
    this.price = price;
    this.pageCount = pageCount;
  }

  public Book(
      String title,
      String author,
      String isbn,
      int publicationYear,
      String publisher,
      double price,
      int pageCount,
      String imagePath) {
    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publicationYear = publicationYear;
    this.publisher = publisher;
    this.price = price;
    this.pageCount = pageCount;
    this.imagePath = imagePath;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public int getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(int publicationYear) {
    this.publicationYear = publicationYear;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public StringProperty imagePathProperty() {
    return new SimpleStringProperty(this.imagePath);
  }

  @Override
  public String toString() {
    return "Book{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", author='"
        + author
        + '\''
        + ", isbn='"
        + isbn
        + '\''
        + ", publicationYear="
        + publicationYear
        + ", publisher='"
        + publisher
        + '\''
        + ", price="
        + price
        + ", pageCount="
        + pageCount
        + ", imagePath='"
        + imagePath
        + '\''
        + '}';
  }
}
