// Gói thực hiện truy vấn dữ liệu
package dao;

// Thêm các thư viện cần thiết
import java.sql.*;
import java.util.*;

// Thêm các file cần thiết từ các gói khác trong project
import model.DBConnection;
import model.ThietBi;

// Lớp ThietBiDao chứa các phương thức thao tác với bảng thiết bị trong database
public class ThietBiDao {
    // Biến lưu trữ kết nối với database
    private Connection connection;

    // Khởi tạo đối tượng ThietBiDao với biến lưu trữ kết nối với database
    public ThietBiDao(Connection connection) {
        this.connection = connection;
    }

    // Khởi tạo đối tượng ThietBiDao với không tham số (mặc định)
    public ThietBiDao() {
    }

    // Hàm lấy danh sách thiết bị
    public List<ThietBi> danhSachThietBi() {
        // Khởi tạo danh sách lưu trữ các Thiết Bị
        List<ThietBi> tbs = new ArrayList<>();

        // Biến để thực hiện truy vấn
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh truy vấn để lấy tất cả dữ liệu từ bảng thiet_bi
            stmt = connection.prepareStatement("SELECT * FROM thiet_bi");

            // Thực thi truy vấn và lưu kết quả vào ResultSet
            rs = stmt.executeQuery();

            // Duyệt qua từng dòng kết quả
            while (rs.next()) {
                // Tạo đối tượng ThietBi từ dữ liệu lấy được
                ThietBi tb = new ThietBi(rs.getInt("maThietBi"),
                        rs.getString("tenThietBi"),
                        rs.getString("xuatXu"),
                        rs.getInt("namSanXuat"),
                        rs.getString("tinhTrang"));

                // Thêm đối tượng ThietBi vào danh sách
                tbs.add(tb);
            }
        } catch (SQLException e) {
            // Hiển thị lỗi nếu có lỗi xảy ra trong quá trình truy vấn
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
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // Trả về danh sách các Thiết Bị
        return tbs;
    }

    // Hàm thực hiện Thêm Thiết Bị vào bảng thiet_bi trong database
    public void themThietBi(ThietBi thietBi) throws Exception {
        // Biến để thực hiện INSERT
        PreparedStatement stmt = null;
        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();
            // Chuẩn bị câu lệnh INSERT với tham số (?) do người dùng nhập
            stmt = connection.prepareStatement("INSERT INTO thiet_bi (maThietBi, tenThietBi, xuatXu, namSanXuat, tinhTrang) VALUES (?,?,?,?,?)");
            
            // Gán giá trị của đối tượng ThietBi vào các tham số của câu lệnh
            stmt.setInt(1, thietBi.getMaThietBi());
            stmt.setString(2, thietBi.getTenThietBi());
            stmt.setString(3, thietBi.getXuatXu());
            stmt.setInt(4, thietBi.getNamSanXuat());
            stmt.setString(5, thietBi.getTinhTrang());

            // Thực thi INSERT
            stmt.executeUpdate();

        } catch (SQLException e) {
            // In lỗi nếu có lỗi SQL xảy ra
            e.printStackTrace();

            // Ném ngoại lệ Exception để báo lỗi cho lớp gọi hàm
            throw new Exception("Lỗi khi thêm thiết bị!");
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

    // Hàm thực hiện Sửa Thiết Bị vào bảng thiet_bi trong database
    public void suaThietBi(ThietBi thietBi) throws SQLException {
        // Biến để thực thi UPDATE
        PreparedStatement stmt = null;

        // Chuỗi chứa câu lệnh UPDATE
        String query = "UPDATE thiet_bi SET tenThietBi = ?, xuatXu = ?, namSanXuat = ? ,tinhTrang = ? WHERE maThietBi = ?";
        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh UPDATE với tham số (?) do người dùng nhập
            stmt = connection.prepareStatement(query);

            // Gán giá trị của đối tượng ThietBi vào các tham số của câu lệnh
            stmt.setString(1, thietBi.getTenThietBi());
            stmt.setString(2, thietBi.getXuatXu());
            stmt.setInt(3, thietBi.getNamSanXuat());
            stmt.setString(4, thietBi.getTinhTrang());
            stmt.setInt(5, thietBi.getMaThietBi());

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm thực hiện Xóa Thiết Bị vào bảng thiet_bi trong database
    public void xoaThietBi(ThietBi thietBi) throws SQLException {
        // Biến để thực thi DELETE
        PreparedStatement stmt = null;

        // Chuỗi chứa câu lệnh DELETE
        String query = "DELETE FROM thiet_bi WHERE maThietBi =?";
        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh DELETE với tham số (?) do người dùng nhập 
            stmt = connection.prepareStatement(query);

            // Gán mã thiết bị vào tham số của câu lệnh
            stmt.setInt(1, thietBi.getMaThietBi());

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm thực hiện tìm kiếm Thiết Bị theo Tên
    public List<ThietBi> timThietBiTheoTen(String tenThietBi) {
        // Khởi tạo danh sách lưu trữ các Thiết Bị
        List<ThietBi> tbs = new ArrayList<>();

        // Biến để thực hiện Tìm kiếm
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Chuỗi chứa câu lệnh Tìm kiếm
        String query = "SELECT * FROM thiet_bi WHERE tenThietBi LIKE ?";

        try {
            // Kết nối đến database
            connection = DBConnection.getConnection();

            // Chuẩn bị câu lệnh tìm kiếm
            stmt = connection.prepareStatement(query);

            // Gán tên thiết bị vào tham số của câu lệnh
            stmt.setString(1, "%" + tenThietBi + "%");

            try {
                // Thực thi truy vấn và lưu kết quả vào ResultSet
                rs = stmt.executeQuery();
                while (rs.next()) {
                    // Tạo đối tượng ThietBi từ dữ liệu lấy được
                    ThietBi tb = new ThietBi(rs.getInt("maThietBi"),
                            rs.getString("tenThietBi"),
                            rs.getString("xuatXu"),
                            rs.getInt("namSanXuat"),
                            rs.getString("tinhTrang"));

                    // Thêm đối tượng ThietBi vào danh sách
                    tbs.add(tb);
                }
            } catch (Exception e) {
                // In lỗi nếu có lỗi xảy ra
                e.printStackTrace();
            }
        } catch (SQLException e) {
            // In lỗi nếu có lỗi SQL xảy ra
            e.printStackTrace();
        } finally {
            // Luông đóng ResultSet, Statement và Connection sau khi thực hiện
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

        // Trả về danh sach thiết bị
        return tbs;
    }

    // Các hàm tìm kiếm bên dưới có các chức năng tương tự như hàm tìm kiếm thiết bị theo tên

    // Hàm tìm kiếm thiết bị theo Năm sản xuất
    public List<ThietBi> timThietBiTheoNamSanXuat(Integer namSanXuat) {
        List<ThietBi> tbs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM thiet_bi WHERE namSanXuat =?";

        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, namSanXuat);

            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ThietBi tb = new ThietBi(rs.getInt("maThietBi"),
                            rs.getString("tenThietBi"),
                            rs.getString("xuatXu"),
                            rs.getInt("namSanXuat"),
                            rs.getString("tinhTrang"));
                    tbs.add(tb);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
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
        return tbs;
    }

    // Hàm tìm kiếm thiết bị theo Xuất xứ
    public List<ThietBi> timThietBiTheoXuatXu(String xuatXu) {
        List<ThietBi> tbs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM thiet_bi WHERE xuatXu =?";
        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, xuatXu);

            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ThietBi tb = new ThietBi(rs.getInt("maThietBi"),
                            rs.getString("tenThietBi"),
                            rs.getString("xuatXu"),
                            rs.getInt("namSanXuat"),
                            rs.getString("tinhTrang"));
                    tbs.add(tb);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
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
        return tbs;
    }

    // Hàm tìm kiếm thiết bị theo Mã thiết bị
    public List<ThietBi> timThietBiTheoMaThietBi(Integer maThietBi) {
        List<ThietBi> tbs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM thiet_bi WHERE maThietBi =?";
        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, maThietBi);

            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ThietBi tb = new ThietBi(rs.getInt("maThietBi"),
                            rs.getString("tenThietBi"),
                            rs.getString("xuatXu"),
                            rs.getInt("namSanXuat"),
                            rs.getString("tinhTrang"));
                    tbs.add(tb);
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
        return tbs;
    }

    // Hàm tìm kiếm thiết bị theo Tình trạng
    public List<ThietBi> timThietBiTheoTinhTrang(String tinhTrang) {
        List<ThietBi> tbs = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String query = "SELECT * FROM thiet_bi WHERE tinhTrang =?";
        try {
            connection = DBConnection.getConnection();
            stmt = connection.prepareStatement(query);
            stmt.setString(1, tinhTrang);

            try {
                rs = stmt.executeQuery();
                while (rs.next()) {
                    ThietBi tb = new ThietBi(rs.getInt("maThietBi"),
                            rs.getString("tenThietBi"),
                            rs.getString("xuatXu"),
                            rs.getInt("namSanXuat"),
                            rs.getString("tinhTrang"));
                    tbs.add(tb);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
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
        return tbs;
    }

}
