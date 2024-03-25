// Gói điều khiển thao tác của người dùng
package controller;

// Thêm các file cần thiết từ các gói khác trong project
import view.MainScreen;

// Thêm các thư viện cần thiết
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Lớp LoginFrame chứa các phương thức nhằm giúp người dùng đăng nhập
public class LoginFrame extends JFrame {
    // Biến thành phần UI
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    // Giả định danh sách người dùng đã đăng ký với hệ thống
    private List<User> UsersList;

    public LoginFrame() {
        UsersList = new ArrayList<>();
        // Thêm một số người dùng ví dụ
        UsersList.add(new User("trungta897", "trungta897"));
        UsersList.add(new User("Admin", "admin"));

        setTitle("Đăng nhập");
        setSize(400, 200);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        // Khởi tạo layout GridLayout (sắp xếp dạng lưới)
        setLayout(new GridLayout(3, 3));
        
        // Thêm nhãn và trường văn bản cho tên người dùng và mật khẩu
        add(new JLabel("Tên đăng nhập:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Mật khẩu:"));
        passwordField = new JPasswordField();
        add(passwordField);

        // Tạo và thêm nút đăng nhập và đăng ký
        loginButton = new JButton("Đăng nhập");
        add(loginButton);

        registerButton = new JButton("Đăng ký");
        add(registerButton);

        // Thêm ActionListener cho nút đăng nhập
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienDangNhap();
            }
        });

        // Thêm ActionListener cho nút đăng ký
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienDangKy();
            }
        });

        setLocationRelativeTo(null);

        // Thêm KeyListener cho phím Enter trong trường tên người dùng, mật khẩu và nút đăng nhập
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    thucHienDangNhap();
                }
            }
        });

        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    thucHienDangNhap();
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thucHienDangNhap();
            }
        });
    }

    // Hàm thực hiện đăng nhập
    private void thucHienDangNhap() {
        // Lấy thông tin từ ô nhập liệu
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        // Kiểm tra xem tên đăng nhập và mật khẩu có trùng khớp với danh sách người dùng đã đăng ký hay không
        User user = timUser(username, password);

        if (user != null) {
            // Nếu đăng nhập thành công
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công cho người dùng: " + user.getUsername());
            // Xóa các trường nhập liệu
            clearInputField();
            openMainScreen();
        } else {
            // Nếu đăng nhập thất bại
            JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng thử lại.");
            // Xóa mật khẩu trên thất bại
            passwordField.setText("");
        }

    }

    // Hàm kiểm tra việc đăng ký
    public boolean kiemTraDangKy() {
        // Kiểm tra mỗi trường thông tin đăng ký
        if (usernameField.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin đăng ký", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // Trả về true nếu thông tin đăng ký hợp lệ
    }

    // Hàm thực hiện đăng ký
    private void thucHienDangKy() {
        // Thực hiện kiểm tra đăng ký
        if (kiemTraDangKy()) {
            // Lấy thông tin từ ô nhập liệu
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            // Kiểm tra xem tên đăng nhập có sẵn hay chưa
            if (kiemTraTonTai(username)) {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
                clearInputField();
                return;
            }

            // Thêm người dùng mới vào danh sách đăng ký
            User newUser = new User(username, password);
            UsersList.add(newUser);

            JOptionPane.showMessageDialog(this, "Đăng ký thành công cho người dùng: " + username);
            clearInputField();
            thucHienDangNhap();
            openMainScreen();
        }
    }

    // Hàm kiểm tra xem tên đăng nhập đã tồn tại hay chưa
    private boolean kiemTraTonTai(String username) {
        for (User user : UsersList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Hàm gọi và mở cửa sổ chính của ứng dụng
    private void openMainScreen() {
        // Tạo và hiển thị màn hình chính
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);

        // Đóng cửa sổ đăng nhập
        dispose();
    }

    // Hàm kiểm tra tính xác thực của tên người dùng và mật khẩu
    private User timUser(String username, String password) {
        for (User user : UsersList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Hàm thực hiện xoá các trường nhập liệu
    private void clearInputField() {
        usernameField.setText("");
        passwordField.setText("");
    }
}
