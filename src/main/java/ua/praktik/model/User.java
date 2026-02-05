package ua.praktik.model;

public class User {

    private int id;
    private String userName;
    private String password;

    /**
     * Створює користувача з вказаним ідентифікатором.
     *
     * <p>
     * Використовується для відновлення об'єктів з бази даних.
     *
     * @param id       унікальний ідентифікатор користувача
     * @param userName ім'я користувача
     * @param password пароль у відкритому вигляді
     */
    public User(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Повертає ідентифікатор користувача.
     *
     * @return ідентифікатор користувача
     */
    public int getId() {
        return id;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * Повертає ім'я користувача.
     *
     * @return ім'я користувача
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Встановлює ім'я користувача.
     *
     * @param userName нове ім'я користувача
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Повертає пароль користувача.
     *
     * @return хешований пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * Встановлює пароль користувача.
     *
     * @param password новий пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Встановлює ідентифікатор користувача.
     *
     * @param id новий ідентифікатор
     */
    public void setId(int id) {
        this.id = id;
    }
}
