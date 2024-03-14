// Gói thực hiện truy vấn dữ liệu
package dao;

// Thêm các thư viện cần thiết
import java.sql.*;
import java.util.*;

// Thêm các file cần thiết từ các gói khác trong project
import model.DBConnection;
import model.ThongTinPhongHoc;

// Lớp ThongTinPhongHocDao chứa các phương thức thao tác với bảng thong_tin_phong_hoc trong database
public class ThongTinPhongHocDao {
    // Biến lưu trữ kết nối với database
    private Connection connection;

    // Khởi tạo đối tượng ThongTinPhongHocDao với biến lưu trữ kết nối với database
    public ThongTinPhongHocDao(Connection connection) {
        this.connection = connection;
    }

    // Hàm lấy danh sách thông tin phòng học 
    public List<ThongTinPhongHoc> danhSachPhongHoc() {
        // Danh sách lưu trữ các Thông Tin Phòng Học
        List<ThongTinPhongHoc> phs = new ArrayList<>();

        // Biến để thực hiện truy vấn
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh truy vấn để lấy tất cả dữ liệu từ bảng thong_tin_phong_hoc
            stmt = connection.prepareStatement("SELECT * FROM thong_tin_phong_hoc");
            
            // Thực thi truy vấn và lưu kết quả vào ResultSet
            rs = stmt.executeQuery();

            // Duyệt qua từng dòng kết quả
            while (rs.next()) {
                // Tạo đối tượng ThongTinPhongHoc từ dữ liệu lấy được
                ThongTinPhongHoc ph = new ThongTinPhongHoc(rs.getInt("maPhongHoc"), rs.getString("tenPhongHoc"),
                        rs.getInt("dungLuong"), rs.getInt("maThietBi"), rs.getString("tenThietBi"),
                        rs.getInt("soLuongThietBi"));
                
                // Thêm đối tượng ThongTinPhongHoc vào danh sách
                phs.add(ph);
            }
        } catch (SQLException e) {
            // In lỗi nếu có lỗi xảy ra trong quá trình truy vấn
            e.printStackTrace();
        } finally {
            // Luôn đóng các đối tượng ResultSet, Statement và Connection sau khi thực hiện
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Trả về danh sách các Thông Tin Phòng Học
        return phs;
    }

    // Hàm Thêm thông tin phòng học vào database
    public void themPhongHoc(ThongTinPhongHoc phongHoc) throws SQLException {
        // Biến để thực hiện INSERT
        PreparedStatement stmt = null;

        // Chuỗi chứa câu lệnh INSERT
        String query = "INSERT INTO thong_tin_phong_hoc (maPhongHoc, tenPhongHoc, dungLuong, maThietBi, tenThietBi, soLuongThietBi) VALUES (?,?,?,?,?,?)";
        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh INSERT với tham số (?) do người dùng nhập
            stmt = connection.prepareStatement(query);

            // Gán giá trị của đối tượng ThongTinPhongHoc vào các tham số của câu lệnh
            stmt.setInt(1, phongHoc.getMaPhongHoc());
            stmt.setInt(1, phongHoc.getMaPhongHoc());
            stmt.setString(2, phongHoc.getTenPhongHoc());
            stmt.setInt(3, phongHoc.getDungLuong());
            stmt.setInt(4, phongHoc.getMaThietBi());
            stmt.setString(5, phongHoc.getTenThietBi());
            stmt.setInt(6, phongHoc.getSoLuongThietBi());
            
            // Thực thi INSERT
            stmt.executeUpdate();
        } catch (SQLException e) {
            // In lỗi nếu có lỗi SQL xảy ra
            e.printStackTrace();

            // Ném ngoại lệ SQLException để báo lỗi
            throw e;
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

    // Hàm Sửa Thông tin phòng học vào database
    public void suaPhongHoc(ThongTinPhongHoc phongHoc) throws SQLException {
        // Biến để thực hiện UPDATE
        PreparedStatement stmt = null;

        // Chuỗi chứa câu lệnh UPDATE
        String query = "UPDATE thong_tin_phong_hoc SET tenPhongHoc =?, dungLuong =?, maThietBi =?, tenThietBi =?, soLuongThietBi =? WHERE maPhongHoc =?";

        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh UPDATE với tham số (?) do người dùng nhập
            stmt = connection.prepareStatement(query);

            // Gán giá trị của đối tượng ThongTinPhongHoc vào các tham số của câu lệnh
            stmt.setString(1, phongHoc.getTenPhongHoc());
            stmt.setInt(2, phongHoc.getDungLuong());
            stmt.setInt(3, phongHoc.getMaThietBi());
            stmt.setString(4, phongHoc.getTenThietBi());
            stmt.setInt(5, phongHoc.getSoLuongThietBi());
            stmt.setInt(6, phongHoc.getMaPhongHoc());

            // Thực thi UPDATE
            stmt.executeUpdate();
        } catch (SQLException e) {
            // In lỗi nếu có lỗi SQL xảy ra
            e.printStackTrace();

            // Ném ngoại lệ SQLException để báo lỗi
            throw e;    
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

    // Hàm Xoá thông tin phòng học theo mã phòng học
    public void xoaPhongHoc(ThongTinPhongHoc phongHoc) throws SQLException {
        // Biến để thực hiện DELETE
        PreparedStatement stmt = null;

        // Chuỗi chứa câu lệnh DELETE
        String query = "DELETE FROM thong_tin_phong_hoc WHERE maPhongHoc =?";

        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh DELETE với tham số (?) do người dùng nhập
            stmt = connection.prepareStatement(query);

            // Gán mã phòng học vào tham số của câu lệnh
            stmt.setInt(1, phongHoc.getMaPhongHoc());

            // Thực thi DELETE
            stmt.executeUpdate();
        } catch (SQLException e) {
            // In lỗi nếu có lỗi SQL xảy ra
            e.printStackTrace();

            // Ném ngoại lệ SQLException để báo lỗi
            throw e;
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

    // Hàm Tìm kiếm thông tin phòng học theo Mã phòng học
    public List<ThongTinPhongHoc> timPhongHocTheoMaPhongHoc(Integer maPhongHoc) {
        // Danh sách lưu trữ các Thông Tin Phòng Học tìm được
        List<ThongTinPhongHoc> phs = new ArrayList<>();

        // Biến để thực hiện truy vấn
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        // Chuỗi chứa câu lệnh truy vấn
        String query = "SELECT * FROM thong_tin_phong_hoc WHERE maPhongHoc =?";

        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh truy vấn với tham số (?) do người dùng nhập
            stmt = connection.prepareStatement(query);

            // Gán mã phòng học vào tham số của câu lệnh
            stmt.setInt(1, maPhongHoc);

            try {
                // Thực thi truy vấn và lưu kết quả vào ResultSet
                rs = stmt.executeQuery();

                // Duyệt qua từng dòng kết quả
                while (rs.next()) {
                    // Tạo đối tượng ThongTinPhongHoc từ dữ liệu lấy được
                    ThongTinPhongHoc ph = new ThongTinPhongHoc(rs.getInt("maPhongHoc"), rs.getString("tenPhongHoc"),
                            rs.getInt("dungLuong"), rs.getInt("maThietBi"), rs.getString("tenThietBi"),
                            rs.getInt("soLuongThietBi"));

                    // Thêm đối tượng ThongTinPhongHoc vào danh sách
                    phs.add(ph);
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
        return phs;
    }

    // Các hàm tìm kiếm còn lại có chức năng tương tự như hàm Tìm kiếm Thông tin phòng học theo Mã phòng học

    // Hàm tìm kiếm thông tin phòng học theo Tên phòng học
    public List<ThongTinPhongHoc> timPhongHocTheoTenPhongHoc(String tenPhongHoc) {
        List<ThongTinPhongHoc> phs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM thong_tin_phong_hoc WHERE tenPhongHoc LIKE ?";

        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + tenPhongHoc + "%");

            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ThongTinPhongHoc ph = new ThongTinPhongHoc(rs.getInt("maPhongHoc"), rs.getString("tenPhongHoc"),
                            rs.getInt("dungLuong"), rs.getInt("maThietBi"), rs.getString("tenThietBi"),
                            rs.getInt("soLuongThietBi"));
                    phs.add(ph);
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
        return phs;
    }

    // Hàm tìm kiếm thông tin phòng học theo Dung lượng
    public List<ThongTinPhongHoc> timPhongHocTheoDungLuong(Integer dungLuong) {
        List<ThongTinPhongHoc> phs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM thong_tin_phong_hoc WHERE dungLuong =?";

        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, dungLuong);

            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ThongTinPhongHoc ph = new ThongTinPhongHoc(rs.getInt("maPhongHoc"), rs.getString("tenPhongHoc"),
                            rs.getInt("dungLuong"), rs.getInt("maThietBi"), rs.getString("tenThietBi"),
                            rs.getInt("soLuongThietBi"));
                    phs.add(ph);
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
        return phs;
    }

    // Hàm tìm kiếm thông tin phòng học theo Mã thiết bị
    public List<ThongTinPhongHoc> timPhongHocTheoMaThietBi(Integer maThietBi) {
        List<ThongTinPhongHoc> phs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM thong_tin_phong_hoc WHERE maThietBi =?";

        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, maThietBi);

            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ThongTinPhongHoc ph = new ThongTinPhongHoc(rs.getInt("maPhongHoc"), rs.getString("tenPhongHoc"),
                            rs.getInt("dungLuong"), rs.getInt("maThietBi"), rs.getString("tenThietBi"),
                            rs.getInt("soLuongThietBi"));
                    phs.add(ph);
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
        return phs;
    }

    // Hàm tìm kiếm thông tin phòng học theo Tên thiết bị
    public List<ThongTinPhongHoc> timPhongHocTheoTenThietBi(String tenThietBi) {
        List<ThongTinPhongHoc> phs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM thong_tin_phong_hoc WHERE tenThietBi LIKE?";

        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, "%" + tenThietBi + "%");

            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ThongTinPhongHoc ph = new ThongTinPhongHoc(rs.getInt("maPhongHoc"), rs.getString("tenPhongHoc"),
                            rs.getInt("dungLuong"), rs.getInt("maThietBi"), rs.getString("tenThietBi"),
                            rs.getInt("soLuongThietBi"));
                    phs.add(ph);
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
        return phs;
    }

    // Hàm tìm kiếm thông tin phòng học theo Số lượng thiết bị
    public List<ThongTinPhongHoc> timPhongHocTheoSoLuongThietBi(Integer soLuongThietBi) {
        List<ThongTinPhongHoc> phs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM thong_tin_phong_hoc WHERE soLuongThietBi =?";

        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, soLuongThietBi);

            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ThongTinPhongHoc ph = new ThongTinPhongHoc(rs.getInt("maPhongHoc"), rs.getString("tenPhongHoc"),
                            rs.getInt("dungLuong"), rs.getInt("maThietBi"), rs.getString("tenThietBi"),
                            rs.getInt("soLuongThietBi"));
                    phs.add(ph);
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
        return phs;
    }

}
