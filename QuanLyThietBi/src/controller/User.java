// Gói điều khiển thao tác của người dùng
package controller;

// Lớp User chứa các thông tin của người dùng cùng với các phương thức getter, setter
public class User {
    // Tên người dùng
    protected String username;

    // Mật khẩu
    protected String password;

    // Khởi tạo đối tượng User với đầy đủ thông tin (constructor)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Lấy tên người dùng
    public String getUsername() {
        return username;
    }

    // Thiết lập tên người dùng
    public void setUsername(String username) {
        this.username = username;
    }

    // Lấy mật khẩu
    public String getPassword() {
        return password;
    }

    // Thiết lập mật khẩu
    public void setPassword(String password) {
        this.password = password;
    }
}
