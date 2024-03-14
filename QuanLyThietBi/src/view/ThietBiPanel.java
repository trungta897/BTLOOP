// Gói chứa giao diện người dùng
package view;

// Thêm các thư viện cần thiết
import javax.swing.*;
import java.awt.*;
import java.util.List;

// Thêm các file cần thiết từ các gói khác trong project
import dao.ThietBiDao;          // Lớp tương tác với dữ liệu Thiết bị (ThietBi)
import model.ThietBi;           // Lớp định nghĩa model của Thiết Bị ()
import table.ThietBiTable;      // Lớp hiển thị dữ liệu Thiết Bị

// Lớp ThietBiPanel tạo ra giao diện người dùng để quản lý Thiết Bị
public class ThietBiPanel extends JPanel {
    // Khai báo các thành phần giao diện
    private JTable tbTable;
    private JTextField txtMaThietBi, txtTenThietBi, txtXuatXu, txtNamSanXuat, txtTinhTrang;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnHienThi;
    
    // Đối tượng ThietBiDao để thao tác với dữ liệu Thiết Bị
    private ThietBiDao tbDao;
    
    // Hàm khởi tạo, nhận đối tượng ThietBiDao để truyền dữ liệu
    public ThietBiPanel(ThietBiDao tbDao) {
        this.tbDao = tbDao;
        initUI();                                           // Gọi hàm khởi tạo các thành phần của giao diện
        setLayout(new BorderLayout());                      // Thiết lập layout BorderLayout
        add(inputPanel(), BorderLayout.NORTH);              // Thêm vùng nhập dữ liệu vào phía Bắc (NORTH)
        add(tablePanel(), BorderLayout.CENTER);             // Thêm bảng dữ liệu vào Giữa (CENTER)
        add(buttonPanel(), BorderLayout.SOUTH);             // Thêm vùng nút chức năng vào phía Nam (SOUTH)
    }

    // Hàm khởi tạo các thành phần giao diện
    public void initUI() {
        txtMaThietBi = new JTextField();
        txtTenThietBi = new JTextField();
        txtXuatXu = new JTextField();
        txtNamSanXuat = new JTextField();
        txtTinhTrang = new JTextField();
        btnThem = new JButton("Thêm thiết bị");
        btnSua = new JButton("Sửa thiết bị");
        btnXoa = new JButton("Xóa thiết bị");
        btnTimKiem = new JButton("Tìm kiếm");
        btnHienThi = new JButton("Hiển thị");
        tbTable = new JTable();


        // Thêm sự kiện click các nút chức năng
        btnThem.addActionListener(e -> themThietBi());                  // Thực hiện chức năng thêm thiết bị
        btnSua.addActionListener(e -> suaThietBi());                    // Thực hiện chức năng sửa thiết bị theo mã thiết bị
        btnXoa.addActionListener(e -> xoaThietBi());                    // Thực hiện chức năng xoá thiết bị theo mã thiết bị
        btnTimKiem.addActionListener(e -> timKiemThietBi());            // Thực hiện chức năng tìm kiếm thiết bị
        btnHienThi.addActionListener(e -> loadData());           // Thực hiện chức năng hiển thị danh sách thiết bị
    }

    // Hàm tạo vùng nhập dữ liệu
    public JPanel inputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout());               // Khởi tạo panel với layout GridLayout (sắp xếp dạng lưới)                
        inputPanel.add(new JLabel("Mã thiết bị: "));               // Thêm nhãn "Mã Thiết Bị"
        inputPanel.add(txtMaThietBi);                                   // Thêm ô nhập liệu maThietBi

        inputPanel.add(new JLabel("Tên thiết bị: "));              // Thêm nhãn "Tên Thiết Bị"
        inputPanel.add(txtTenThietBi);                                  // Thêm ô nhập liệu tenThietBi

        inputPanel.add(new JLabel("Xuất xứ: "));                   // Thêm nhãn "Xuất Xứ"
        inputPanel.add(txtXuatXu);                                      // Thêm ô nhập liệu xuatXu

        inputPanel.add(new JLabel("Năm sản xuất: "));              // Thêm nhãn "Năm Sản Xuất"
        inputPanel.add(txtNamSanXuat);                                  // Thêm ô nhập liệu namSanXuat

        inputPanel.add(new JLabel("Tình trạng: "));                // Thêm nhãn "Tình Trạng"
        inputPanel.add(txtTinhTrang);                                   // Thêm ô nhập liệu tinhTrang

        return inputPanel;
    }

    // Hàm tạo vùng hiển thị bảng dữ liệu
    private JScrollPane tablePanel() {
        JScrollPane scrollPanel = new JScrollPane(tbTable);             // Khởi tạo scroll pane chứa bảng ThietBiTable
        loadData();                                                     // Gọi hàm loadData()
        return scrollPanel;
    }

    // Hàm tạo vùng chứa các nút chức năng
    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();                              // Khởi tạo panel chứa các nút chức năng
        buttonPanel.add(btnThem);                                       // Thêm nút "Thêm"
        buttonPanel.add(btnSua);                                        // Thêm nút "Sửa"
        buttonPanel.add(btnXoa);                                        // Thêm nút "Xoá"
        buttonPanel.add(btnTimKiem);                                    // Thêm nút "Tìm kiếm"
        buttonPanel.add(btnHienThi);                                    // Thêm nút "Hiển thị"
        return buttonPanel;
    }

    // Hàm tải dữ liệu bảng Thiết Bị (loadData)
    public void loadData() {
        // Lấy danh sách Thiết Bị từ database (Sử dụng lớp tbDao)
        List<ThietBi> tbs = tbDao.danhSachThietBi();

        // Khởi tạo đối tượng cho bảng ThietBiTable để hiển thị dữ liệu
        ThietBiTable tBi = new ThietBiTable(tbs);

        // Thiết lập model cho bảng thietBiTable (model chứa dữ liệu hiển thị trên bảng)
        tbTable.setModel(tBi);
    }

    // Hàm lấy thông tin nhập liệu trên form và tạo thành một đối tượng mới
    private ThietBi layThongTinThietBiTuForm() {
        // Lấy giá trị từ ô Mã Thiết Bị
        Integer maThietBi = !txtMaThietBi.getText().isEmpty() ? Integer.parseInt(txtMaThietBi.getText()) : null;

        // Lấy giá trị từ ô Tên Thiết Bị
        String tenThietBi = txtTenThietBi.getText();

        // Lấy giá trị từ ô Xuất Xứ
        String xuatXu = txtXuatXu.getText();

        // Lấy giá trị từ ô Năm Sản Xuất
        Integer namSanXuat = !txtNamSanXuat.getText().isEmpty() ? Integer.parseInt(txtNamSanXuat.getText()) : null;

        // Lấy giá trị từ ô Tình Trạng
        String tinhTrang = txtTinhTrang.getText();

        // Tạo và trả về một đối tượng Thiết Bị mới với giá trị lấy được
        return new ThietBi(maThietBi, tenThietBi, xuatXu, namSanXuat, tinhTrang);
    }
    

    // Hàm thực hiện Thêm Thiết Bị
    public void themThietBi() {
        try {
            // Tạo đối tượng Thiết Bị với thông tin lấy được từ form
            ThietBi tb = layThongTinThietBiTuForm();

            // Gọi hàm tbDao.themThietBi để thêm dữ liệu vào database
            tbDao.themThietBi(tb);
            
            // Xoá chuỗi nhập ở ô nhập liệu
            clearInputField();

            // Cập nhật lại dữ liệu trên bảng sau khi thêm
            loadData();
        } catch (Exception e) {
            // Hiển thị thong báo lỗi chung nếu có vấn đề trong việc thêm thiết bị
            hienThongBaoLoi("Lỗi khi thêm thiết bị: " + e.getMessage());
        }
    }

    // Hàm thực hiện Sửa Thiết Bị
    private void suaThietBi() {
        try {
            // Tạo đối tượng Thiết Bị với thông tin lấy được từ form
            ThietBi tb = layThongTinThietBiTuForm();

            // Gọi hàm tbDao.suaThietBi để sửa dữ liệu vào database
            tbDao.suaThietBi(tb);
            
            // Xoá chuỗi nhập ở ô nhập liệu
            clearInputField();

            // Cập nhật lại dữ liệu trên bảng sau khi sửa
            loadData();
        } catch (Exception e) {
            // Hiển thị thông báo lỗi chung nếu có vấn đề trong việc sửa thiết bị
            hienThongBaoLoi("Lỗi khi sửa thiết bị: " + e.getMessage());
        }
    }

    // Hàm thực hiện Xoá Thiết Bị
    private void xoaThietBi() {
        try {
            // Chuyển đổi chuỗi nhập ở ô nhập liệu sang kiểu số nguyên
            Integer maThietBi = Integer.parseInt(txtMaThietBi.getText());

            // Khởi tạo đối tượng thiết bị:
            // Sử dụng mã từ ô nhập liệu
            // Không cần thiết điền giá trị cho các dữ liệu còn lại vì nó không được sử dụng trong hàm tbDao.xoaThietBi
            ThietBi tb = new ThietBi(maThietBi," "," ",0," ");
            
            // Gọi hàm tbDao.xoaThietBi để xoá phòng học thêo mã thiết bị
            tbDao.xoaThietBi(tb);

            // Xoá chuỗi nhập ở ô nhập liệu
            clearInputField();

            // Cập nhật lại dữ liệu trên bảng sau khi xoá
            loadData();
        } catch (Exception e) {
            // Hiển thị thông báo Lỗi chung nếu có vấn đề trong việc xoá thiết bị
            hienThongBaoLoi("Lỗi khi xóa thiết bị: " + e.getMessage());
        }
    }


    // Hàm thực hiện Tìm Kiếm Thiết Bị
    private void timKiemThietBi() {
        try {
            // Tạo đối tượng Thiết Bị với thông tin lấy được từ form
            ThietBi tb = layThongTinThietBiTuForm();

            // Tạo danh sách tìm kiếm Thiết Bị
            List<ThietBi> tbs;

            // TÌm kiếm theo Mã Thiết Bị (nếu có)
            if (tb.getMaThietBi() != null) {
                tbs = tbDao.timThietBiTheoMaThietBi(tb.getMaThietBi());

            // Tìm kiếm theo Tên Thiết Bị (nếu có)
            } else if (!tb.getTenThietBi().isEmpty()) {
                tbs = tbDao.timThietBiTheoTen(tb.getTenThietBi());

            // Tìm kiếm theo Xuất Xứ (nếu có)
            } else if (!tb.getXuatXu().isEmpty()) {
                tbs = tbDao.timThietBiTheoXuatXu(tb.getXuatXu());

            // Tìm kiếm theo Năm Sản Xuất (nếu có)
            } else if (tb.getNamSanXuat() != null) {
                tbs = tbDao.timThietBiTheoNamSanXuat(tb.getNamSanXuat());

            // Tìm kiếm theo Tình Trạng (nếu có)
            } else if (!tb.getTinhTrang().isEmpty()) {
                tbs = tbDao.timThietBiTheoTinhTrang(tb.getTinhTrang());

            // Hiển thị thông báo Lỗi nếu không có điều kiện tìm kiếm
            } else {
                hienThongBaoLoi("Vui lòng nhập thông tin tìm kiếm");
                return;
            }

            // Cập nhật dữ liệu bảng với kết quả tìm kiếm:
            // Khởi tạo đối tượng ThietBiTable để hiển thị dữ liệu
            ThietBiTable model = new ThietBiTable(tbs);

            // Thiết lập model cho bảng ThietBiTable (model chứa dữ liệu để hiển thị)
            tbTable.setModel(model);
        } catch (Exception e) {
            // Hiển thị thông báo Lỗi chung nếu có vấn đề trong quá trình tìm kiếm
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm thiết bị: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Hàm hiển thị thông báo Lỗi
    private void hienThongBaoLoi(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    // Hàm xoá dữ liệu trên các ô nhập liệu
    private void clearInputField() {
        txtMaThietBi.setText("");
        txtTenThietBi.setText("");
        txtXuatXu.setText("");
        txtNamSanXuat.setText("");
        txtTinhTrang.setText("");
    }
}
