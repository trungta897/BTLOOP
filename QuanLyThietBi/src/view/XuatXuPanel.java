// Gói chứa giao diện người dùng
package view;

// Thêm các thư viện cần thiết
import javax.swing.*;
import java.awt.*;
import java.util.List;

// Thêm cái file cần thiết từ các gói khác trong project
import dao.XuatXuDao;       // Lớp tương tác với dữ liệu xuất xứ (XuatXu)
import model.XuatXu;        // Lớp định nghĩa model của Xuất Xứ
import table.XuatXuTable;   // Lớp hiển thị dữ liệu Xuất Xứ trong bảng

// Lớp XuatXuPanel tạo ra giao diện người dùng để quản lý Xuất Xứ
public class XuatXuPanel extends JPanel {
    // Khai báo các thành phần giao diện
    private JTextField maThietBiField, loaiXuatXuFiled;                      // Ô nhập cho Mã Thiết Bị và Loại Xuất Xứ
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnHienThi;    // Các nút chức năng
    private JTable xuatXuTable;                                         // Bảng hiển thị danh sách Xuất Xứ

    // Đối tượng XuatXuDao để thao tác với dữ liệu Xuất Xứ
    private XuatXuDao xxDao;

    // Hàm khởi tạo, nhận đối tượng XuatXuDao để truyền dữ liệu
    public XuatXuPanel(XuatXuDao xxDao) {
        this.xxDao = xxDao;
        initUI();                                   // Gọi hàm khởi tạo các thành phần của giao diện
        setLayout(new BorderLayout());              // Thiết lập layout BorderLayout
        add(inputPanel(), BorderLayout.NORTH);      // Thêm vùng nhập dữ liệu vào phía Bắc (NORTH)
        add(tablePanel(), BorderLayout.CENTER);     // Thêm bảng dữ liệu vào Giữa          (CENTER)
        add(buttonPanel(), BorderLayout.SOUTH);     // Thêm vùng nút chức năng vào phía Nam(SOUTH)
    }

    // Hàm khởi tạo các thành phần giao diện
    private void initUI() {
        maThietBiField = new JTextField();
        loaiXuatXuFiled = new JTextField();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xoá");
        btnTimKiem = new JButton("Tìm kiếm");
        btnHienThi = new JButton("Hiển thị danh sách");
        xuatXuTable = new JTable();

        // Thêm sự kiện click cho các nút chức năng
        btnThem.addActionListener(e->themXuatXu());                // Thực hiện chức năng thêm xuất xứ
        btnSua.addActionListener(e->suaXuatXu());                  // Thực hiện chức năng sửa xuất xứ theo mã xuất xứ
        btnXoa.addActionListener(e->xoaXuatXu());                  // Thực hiện chức năng xoá xuất xứ theo mã xuất xứ
        btnTimKiem.addActionListener(e->timKiemXuatXu());          // Thực hiện chức năng tìm kiếm thông tin xuất xứ
        btnHienThi.addActionListener(e->loadData());               // Thực hiện chức năng hiển thị danh sách xuất xứ
    }

    // Hàm tạo vùng nhập dữ liệu
    private JPanel inputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout());     // Khởi tạo panel với layout GridLayout (sắp xếp dạng lưới)
        inputPanel.add(new JLabel("Mã Thiết Bị"));       // Thêm nhãn "Mã Thiết Bị"
        inputPanel.add(maThietBiField);                        // Thêm ô nhập liệu maThietBiField
        inputPanel.add(new JLabel("Loại xuất xứ"));      // Thêm nhãn "Loại Xuất Xứ"
        inputPanel.add(loaiXuatXuFiled);                      // Thêm ô nhập liệu loaiXuatXuFiled
        return inputPanel;
    }

    // Hàm tạo vùng hiển thị bảng dữ liệu
    private JScrollPane tablePanel() {
        JScrollPane scrollPane = new JScrollPane(xuatXuTable);  // Khởi tạo scroll pane chứa bảng xuatXuTable
        loadData();                                             // Gọi hàm loadData để tải dữ liệu vào bảng
        return scrollPane;
    }

    // Hàm tạo vùng chứa các nút chức năng
    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();                      // Khởi tạo panel chứa các nút
        buttonPanel.add(btnThem);                               // Thêm nút "Thêm"
        buttonPanel.add(btnSua);                                // Thêm nút "Sửa"
        buttonPanel.add(btnXoa);                                // Thêm nút "Xoá"
        buttonPanel.add(btnTimKiem);                            // Thêm nút "Tìm kiếm"
        buttonPanel.add(btnHienThi);                            // Thêm nút "Hiển thị"
        return buttonPanel;
    }
    
    // Hàm lấy thông tin từ các ô nhập liệu trên form và tạo thành một đối tượng XuatXu
    private XuatXu layThongTinXuatXuTuForm() {
        // Lấy giá trị từ ô "Mã Thiết Bị" 
        Integer maThietBi = !maThietBiField.getText().isEmpty() ? Integer.parseInt(maThietBiField.getText()) : null;

        // Lấy giá trị từ ô "Loại Xuất Xứ"
        String loaiXuatXu =loaiXuatXuFiled.getText();

        // Tạo và trả về một đối tượng XuatXu mới với thông tin lấy được
        return new XuatXu(maThietBi, loaiXuatXu);
    }
    
    // Hàm thực hiện thêm Xuất Xứ
    private void themXuatXu() {
        try {
            // Tạo đối tượng XuatXu từ thông tin trên form
            XuatXu xuatXu = layThongTinXuatXuTuForm();

            // Gọi hàm xxDao.themXuatXu để thêm dữ liệu vào database
            xxDao.themXuatXu(xuatXu);

            // Xoá chuỗi nhập ở ô nhập liệu
            clearInputFields();

            // Cập nhật lại dữ liệu trên bảng sau khi thêm
            loadData();
        } catch (NumberFormatException e) {
            // Hiển thị thông báo lỗi nếu mã không phải số nguyên
            JOptionPane.showMessageDialog(this, "Mã Thiết Bị phải là số nguyên");
        } catch (Exception e) {
            // Hiển thị thông báo lỗi chung nếu có vấn đề trong quá trình thêm
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm xuất xứ: " + e.getMessage());
        }
    }

    // Hàm thực hiện sửa Xuất Xứ 
    private void suaXuatXu() {
        try {
            // Tạo đối tượng XuatXu từ thông tin trên form
            XuatXu xuatXu = layThongTinXuatXuTuForm();

            // Gọi hàm xxDao.suaXuatXu để cập nhật dữ liệu vào database
            xxDao.suaXuatXu(xuatXu);

            // Xoá chuỗi nhập
            clearInputFields();

            // Cập nhật lại dữ liệu trên bảng sau khi sửa
            loadData();
        } catch (NumberFormatException e) {
            // Hiển thị thông báo lỗi nếu mã không phải số nguyên
            JOptionPane.showMessageDialog(this, "Mã Thiết Bị phải là số nguyên");
        } catch (Exception e) {
            // Hiển thị thông báo lỗi chung nếu có vấn đề trong quá trình sửa
            JOptionPane.showMessageDialog(this, "Lỗi khi sửa xuất xứ: " + e.getMessage());
        }
    }

    // Hàm thực hiện xóa Xuất Xứ
    private void xoaXuatXu() {
        try {
            // Chuyển đổi chuỗi nhập ở ô Mã Thiết Bị sang số nguyên
            Integer maThietBi = Integer.parseInt(maThietBiField.getText());

            //* Tạo đối tượng XuatXu:  
            //  Sử dụng mã lấy từ ô nhập liệu
            //  Không cần thiết điền giá trị cho "Loại Xuất Xứ" vì nó không được dùng trong hàm xxDao.xoaXuatXu
            XuatXu xuatXu = new XuatXu(maThietBi, " ");

             // Gọi hàm xxDao.xoaXuatXu để xóa dữ liệu
            xxDao.xoaXuatXu(xuatXu);

            // Xoá chuỗi nhập
            clearInputFields();

            // Cập nhật lại dữ liệu trên bảng sau khi xóa
            loadData();
        } catch (NumberFormatException e) {
            // Hiển thị thông báo lỗi nếu mã không phải số nguyên
            JOptionPane.showMessageDialog(this, "Mã Thiết Bị phải là số nguyên");
        } catch (Exception e) {
            // Hiển thị thông báo lỗi chung nếu có vấn đề trong quá trình xóa
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa xuất xứ: " + e.getMessage());
        }
    }


    // Hàm thực hiện tìm kiếm Xuất Xứ 
    public void timKiemXuatXu() {
        try {
            // Tạo đối tượng XuatXu từ thông tin trên form
            XuatXu xxs = layThongTinXuatXuTuForm();

            // Tạo danh sách tìm kiếm Xuất Xứ
            List<XuatXu> xxList;

            // Tìm kiếm theo Mã Thiết Bị (nếu có)
            if (xxs.getMaThietBi() != null) {
                xxList = xxDao.timXuatXuTheoMa(xxs.getMaThietBi());
            
            // Tìm kiếm theo Loại Xuất Xứ (nếu có)
            } else if (!xxs.getLoaiXuatXu().isEmpty()) {
                xxList = xxDao.timXuatXuTheoLoai(xxs.getLoaiXuatXu());

            // Hiển thị thông báo lỗi nếu không có điều kiện tìm kiếm
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm thiết bị", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật dữ liệu bảng với kết quả tìm kiếm:
            // Khởi tạo đối tượng XuatXuTable để hiển thị dữ liệu 
            XuatXuTable model = new XuatXuTable(xxList);

            // Thiết lập model cho bảng xuatXuTable (model chứa dữ liệu hiển thị trên bảng)
            xuatXuTable.setModel(model);
        } catch (Exception e) {

            // Hiển thị thông báo lỗi nếu gặp lỗi trong quá trình tìm kiếm
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm xuất xứ: " + e.getMessage());
        }
    }

    // Hàm tải dữ liệu bảng Xuất Xứ (loadData)
    private void loadData() {
        // Lấy danh sách Xuất Xứ từ database (sử dụng lớp xxDao)
        List<XuatXu> xuatXuList = xxDao.danhSachXuatXu();

        // Khởi tạo đối tượng XuatXuTable để hiển thị dữ liệu 
        XuatXuTable xxtb = new XuatXuTable(xuatXuList);

        // Thiết lập model cho bảng xuatXuTable (model chứa dữ liệu hiển thị trên bảng)
        xuatXuTable.setModel(xxtb);
    }

    // Hàm xóa dữ liệu trên các ô nhập liệu
    private void clearInputFields() {
        maThietBiField.setText("");
        loaiXuatXuFiled.setText("");
    }
}
