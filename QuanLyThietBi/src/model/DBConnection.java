// Gói quản lý, xử lý các dữ liệu
package model;

// Thêm thư viện sql
import java.sql.*;

// Lớp DBConnection chứa thông tin cùng các phương thức kết nối database
public class DBConnection {
    // Biến lưu trữ kết nối với database
    private static Connection connection;

    // Biến lưu trữ đối tượng Statement để thực thi truy vấn
    private static Statement stmt;

    // Biến lưu trữ kết quả trả về của truy vấn (ResultSet)
    private static ResultSet rs;

    // Hàm lấy kết nối với database
    public static Connection getConnection() {
        // Cài đặt thông tin kết nối
        String url = "jdbc:mysql://localhost:3306/QuanLyThietBi";           // Địa chỉ, tên database
        String user = "root";                                               // User name (Tên người dùng)
        String password = "trungta897";                                     // Password (Mật khẩu)

        try {
            // Kết nối với cơ sở dữ liệu
            connection = DriverManager.getConnection(url, user, password);

            // Thực hiện truy vấn
            String query = "SELECT * from thiet_bi";                        // Chạy truy vấn lấy toàn bộ dữ liệu từ bảng thiet_bi
            stmt = connection.prepareStatement(query);                      
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);                                          // Thoát chương trình nếu lỗi kết nối
        }
        return connection;
    }

    // Hàm đóng kết nối với database
    public static void closeConnection() {
        try {
            if (rs != null) {
                rs.close();                                                 // Đóng ResultSet
            }
            if (stmt != null) {
                stmt.close();                                               // Đóng Statement
            }
            if (connection != null) {
                connection.close();                                         // Đóng kết nối
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
