// Gói chứa giao diện người dùng
package view;

// Thêm các thư viện cần thiết
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

// Thêm các file cần thiết từ các gói khác trong project
import dao.*;
import model.DBConnection;


public class MainScreen extends JFrame {

    public MainScreen() {
        // Thiết lập tiêu đề cho cửa sổ
        setTitle("Quản lý thiết bị phòng học");

        // Thiết lập thao tác đóng mặc định
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Thiết lập kích thước cửa sổ
        setSize(1280, 720);

        // Căn giữa cửa sổ trên màn hình
        setLocationRelativeTo(null);

        // ========= Tạo các panel cho các chức năng khác nhau ==========
        // Kết nối đến cơ sở dữ liệu
        Connection connection = DBConnection.getConnection();

        // Tạo các đối tượng DAO để tương tác với các bảng cơ sở dữ liệu
        ThietBiDao tbDao = new ThietBiDao(connection);
        XuatXuDao xxDao = new XuatXuDao(connection);
        ThongTinPhongHocDao phDao = new ThongTinPhongHocDao(connection);

        // Tạo các panel cho từng phần quản lý
        ThietBiPanel thietBiPanel = new ThietBiPanel(tbDao);
        XuatXuPanel xuatXuPanel = new XuatXuPanel(xxDao);
        ThongTinPhongHocPanel phongHocPanel = new ThongTinPhongHocPanel(phDao);

        // ========= Tạo và cấu hình tabbed pane để chuyển đổi giữa các view ==========
        // Tạo JTabbedPane để hiển thị các phần quản lý khác nhau
        JTabbedPane tabbedPane = new JTabbedPane();

        // Thêm tab với tiêu đề mô tả và panel tương ứng
        tabbedPane.addTab("Quản lý Thiết bị", thietBiPanel);
        tabbedPane.addTab("Quản lý Xuất xứ", xuatXuPanel);
        tabbedPane.addTab("Quản lý Phòng học", phongHocPanel);

        // Thêm tabbed pane vào cửa sổ chính (JFrame)
        add(tabbedPane, BorderLayout.CENTER);

        // Hiển thị cửa sổ
        setVisible(true);
    }
}