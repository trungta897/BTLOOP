// Gói thực hiện truy vấn dữ liệu
package dao;

// Thêm các thư viện cần thiết
import java.sql.*;
import java.util.*;

// Thêm các file cần thiết từ các gói khác
import model.DBConnection;
import model.XuatXu;

// Lớp XuatXuDao chứa các phương thức thao tác với bảng xuat_xu trong database
public class XuatXuDao {
    // Biến lưu trữ kết nối với database
    private Connection connection;

    // Khởi tạo đối tượng XuatXuDao với biến lưu trữ kết nối với database
    public XuatXuDao(Connection connection) {
        this.connection = connection;
    }

    // Hàm lấy danh sách xuất xứ
    public List<XuatXu> danhSachXuatXu() {
        // Danh sách để lưu trữ các Xuất Xứ
        List<XuatXu> xxs = new ArrayList<>();
        
        // Biến để thực hiện truy vấn database
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Chuỗi chứa câu lệnh truy vấn SQL
        String query = "SELECT * FROM xuat_xu";
        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh truy vấn
            stmt = connection.prepareStatement(query);

            // Thực thi truy vấn và lưu kết quả vào ResultSet
            rs = stmt.executeQuery();

            // Duyệt qua từng dòng kết quả
            while (rs.next()) {
                // Tạo một đối tượng XuatXu cho mỗi dòng kết quả
                XuatXu xx = new XuatXu(rs.getInt("MaThietBi"), rs.getString("loaiXuatXu"));

                // Thêm đối tượng XuatXu vào danh sách
                xxs.add(xx);
            }
        } catch (SQLException e) {
            // In lỗi nếu có lỗi xảy ra khi truy vấn
            e.printStackTrace();
        } finally {
            // Luôn đảm bảo đóng các đối tượng ResultSet, Statement và Connection sau khi thực hiện
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Trả về danh sách các Xuất Xứ
        return xxs;
    }

    // Hàm Thêm Xuất Xứ vào database
    public void themXuatXu(XuatXu xuatXu) throws SQLException {
        // Biến để thực hiện INSERT
        PreparedStatement stmt = null;
        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh INSERT với tham số (?) do người dùng nhập
            stmt = connection.prepareStatement("INSERT INTO xuat_xu (maThietBi, loaiXuatXu) VALUES (?,?)");

             // Gán giá trị của đối tượng XuatXu vào các tham số của câu lệnh
            stmt.setInt(1, xuatXu.getMaThietBi());
            stmt.setString(2, xuatXu.getLoaiXuatXu());

            // Thực thi INSERT
            stmt.executeUpdate();
        } catch (Exception e) {
            // In lỗi nếu có lỗi xảy ra
            e.printStackTrace();
        } finally {
            // Luôn đảm bảo đóng đối tượng Statement và Connection sau khi thực hiện
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm Sửa Xuất xứ vào database
    public void suaXuatXu(XuatXu xuatXu) throws SQLException {
        // Biến để thực hiện UPDATE
        PreparedStatement stmt = null;

        // Chuỗi chứa câu lệnh UPDATE
        String query = "UPDATE xuat_xu SET loaiXuatXu = ? WHERE MaThietBi = ?";

        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh UPDATE với tham số (?) do người dùng nhập
            stmt = connection.prepareStatement(query);

            // Gán giá trị của đối tượng XuatXu vào các tham số của câu lệnh
            stmt.setString(1, xuatXu.getLoaiXuatXu());
            stmt.setInt(2, xuatXu.getMaThietBi());

            // Thực thi UPDATE
            stmt.executeUpdate();
        } catch (Exception e) {
            // In lỗi nếu có lỗi xảy ra
            e.printStackTrace();
        } finally {
            // Luôn đóng Statement và Connection sau khi thực hiện
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm Xoá Xuất xứ theo Mã thiết bị
    public void xoaXuatXu(XuatXu xuatXu) throws SQLException {
        // Biến để thực hiện DELETE
        PreparedStatement stmt = null;

        // Chuỗi chứa câu lệnh DELETE
        String query = "DELETE FROM xuat_xu WHERE MaThietBi =?";

        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh DELETE với tham số (?) do người dùng nhập
            stmt = connection.prepareStatement(query);
            
            // Gán mã thiết bị vào tham số của câu lệnh
            stmt.setInt(1, xuatXu.getMaThietBi());

            // Thực thi DELETE
            stmt.executeUpdate();
        } catch (Exception e) {
            // In lỗi nếu có lỗi xảy ra
            e.printStackTrace();
        } finally {
            // Luôn đóng Statement và Connection sau khi thực hiện
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm Tìm kiếm Xuất xứ theo Loại xuất xứ
    public List<XuatXu> timXuatXuTheoLoai(String loaiXuatXu) {
        // Danh sách lưu trữ các Xuất xứ tìm được
        List<XuatXu> xxs = new ArrayList<>();

        // Biến để thực hiện truy vấn
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Chuỗi chứa câu lệnh truy vấn
        String query = "SELECT * FROM xuat_xu WHERE loaiXuatXu LIKE ?";

        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh truy vấn với tham số (?) do người dùng nhập
            stmt = connection.prepareStatement(query);

            // Gán Loại xuất xứ vào tham số của câu lệnh
            stmt.setString(1, "%" + loaiXuatXu + "%");

            try {
                // Thực thi truy vấn và lưu kết quả vào ResultSet
                rs = stmt.executeQuery();

                // Duyệt qua từng dòng kết quả
                while (rs.next()) {
                    // Tạo đối tượng XuatXu từ dữ liệu lấy được
                    XuatXu xx = new XuatXu(rs.getInt("MaThietBi"), rs.getString("loaiXuatXu"));
                    
                    // Thêm đối tượng XuatXu vào danh sách
                    xxs.add(xx);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Luôn đóng ResultSet, Statement và Connection sau khi thực hiện
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return xxs;
    }

    // Các hàm tìm kiếm còn lại có chức năng tương tự như hàm Tìm kiếm Xuất xứ theo Loại xuất xứ
    // Hàm tìm kiếm Xuất xứ theo Mã thiết bị
    public List<XuatXu> timXuatXuTheoMa(Integer MaThietBi) {
        List<XuatXu> xxs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM xuat_xu WHERE MaThietBi =?";

        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, MaThietBi);

            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    XuatXu xx = new XuatXu(rs.getInt("MaThietBi"), rs.getString("loaiXuatXu"));
                    xxs.add(xx);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return xxs;
    }
}
