// Gói chứa giao diện người dùng
package view;

// Thêm các thư viện cần thiết
import javax.swing.*;
import java.awt.*;
import java.util.List;

// Thêm cái file cần thiết từ các gói khác trong project
import dao.ThongTinPhongHocDao;             // Lớp tương tác với dữ liệu thông tin phòng học (ThongTinPhongHoc)
import model.ThongTinPhongHoc;              // Lớp định nghĩa model của Thông Tin Phòng Học
import table.ThongTinPhongHocTable;         // Lớp hiển thị dữ liệu Thông tin phòng học trong bảng


// Lớp ThongTinPhongHocPanel tạo ra giao diện người dùng để quản lý Thông tin phòng học
public class ThongTinPhongHocPanel extends JPanel{
    // Khai báo các thành phần giao diện
    private JTextField maPhongHocField, tenPhongHocField, dungLuongField, maThietBiField, tenThietBiField, soLuongThietBiField;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnHienThi;
    private JTable thongTinPhongHocTable;

    // Đối tượng ThongTinPhongHocDao để thao tác với dữ liệu Thông tin phòng học
    private ThongTinPhongHocDao ttDao;

    // Hàm khởi tạo, nhận đối tượng ThongTinPhongHocDao để truyền dữ liệu
    public ThongTinPhongHocPanel(ThongTinPhongHocDao ttDao) {
        this.ttDao = ttDao;
        initUI();                                       // Gọi hàm khởi tạo các thành phần của giao diện
        setLayout(new BorderLayout());                  // Thiết lập layout BorderLayout 
        add(inputPanel(), BorderLayout.NORTH);          // Thêm vùng nhập dữ liệu vào phía Bắc (NORTH)
        add(tablePanel(), BorderLayout.CENTER);         // Thêm bảng dữ liệu vào Giữa (CENTER)
        add(buttonPanel(), BorderLayout.SOUTH);         // Thêm vùng nút chức năng vào phía Nam (SOUTH)
    }

    // Hàm khởi tạo các thành phần giao diện
    public void initUI() {
        maPhongHocField = new JTextField();
        tenPhongHocField = new JTextField();
        dungLuongField = new JTextField();
        maThietBiField = new JTextField();
        tenThietBiField = new JTextField();
        soLuongThietBiField = new JTextField();
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xoá");
        btnTimKiem = new JButton("Tìm kiếm");
        btnHienThi = new JButton("Hiển thị danh sách");
        thongTinPhongHocTable = new JTable();

        // Thêm sự kiện click cho các nút chức năng
        btnThem.addActionListener(e->themThongTinPhongHoc());               // Thực hiện chức năng thêm phòng học
        btnSua.addActionListener(e->suaThongTinPhongHoc());                 // Thực hiện chức năng sửa phòng học theo mã phòng học
        btnXoa.addActionListener(e->xoaThongTinPhongHoc());                 // Thực hiện chức năng xoá phòng học theo mã phòng học
        btnTimKiem.addActionListener(e->timKiemThongTinPhongHoc());         // Thực hiện chức năng tìm kiếm thông tin phòng học
        btnHienThi.addActionListener(e->loadData());                        // Thực hiện chức năng hiển thị danh sách phòng học
    }

    // Hàm tạo vùng nhập dữ liệu
    private JPanel inputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout());       // Khởi tạo panel với layout GridLayout (sắp xếp dạng lưới)
        inputPanel.add(new JLabel("Mã Phòng Học"));        // Thêm nhãn "Mã Phòng Học"
        inputPanel.add(maPhongHocField);                        // Thêm ô nhập liệu maPhongHoc

        inputPanel.add(new JLabel("Tên Phòng Học"));       // Thêm nhãn "Tên Phòng Học"
        inputPanel.add(tenPhongHocField);                       // Thêm ô nhập liệu tenPhongHoc

        inputPanel.add(new JLabel("Dung Lượng"));          // Thêm nhãn "Dung Lượng"
        inputPanel.add(dungLuongField);                         // Thêm ô nhập liệu dungLuong

        inputPanel.add(new JLabel("Mã Thiết Bị"));         // Thêm nhãn "Mã Thiết Bị"
        inputPanel.add(maThietBiField);                         // Thêm ô nhập liệu maThietBi

        inputPanel.add(new JLabel("Tên Thiết Bị"));        // Thêm nhãn "Tên Thiết Bị"
        inputPanel.add(tenThietBiField);                        // Thêm ô nhập liệu tenThietBi

        inputPanel.add(new JLabel("Số Lượng Thiết Bị"));   // Thêm nhãn "Số Lượng Thiết Bị"
        inputPanel.add(soLuongThietBiField);                    // Thêm ô nhập liệu soLuongThietBi
        return inputPanel;
    }

    // Hàm tạo vùng hiển thị bảng dữ liệu
    private JScrollPane tablePanel() {
        JScrollPane scrollPane = new JScrollPane(thongTinPhongHocTable);    // Khởi tạo scroll pane chứa bảng thongTinPhongHocTabble
        loadData();                                                         // Gọi hàm loadData()
        return scrollPane;
    }

    // Hàm tạo vùng chứa các nút chức năng
    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();              // Khởi tạo panel chứa các nút chức năng
        buttonPanel.add(btnThem);                       // Thêm nút "Thêm"
        buttonPanel.add(btnSua);                        // Thêm nút "Sửa"
        buttonPanel.add(btnXoa);                        // Thêm nút "Xoá"
        buttonPanel.add(btnTimKiem);                    // Thêm nút "Tìm kiếm"
        buttonPanel.add(btnHienThi);                    // Thêm nút "Hiển thị"
        return buttonPanel;
    }
    
    // Hàm tải dữ liệu bảng Thông Tin Phòng Học (loadData)
    private void loadData() {
        // Lấy danh sách Thông Tin Phòng Học từ database (Sử dụng lớp ttDao)
        List<ThongTinPhongHoc> ttList = ttDao.danhSachPhongHoc();
        
        // Khởi tạo đối tượng cho bảng ThongTinPhongHocTable để hiển thị dữ liệu
        ThongTinPhongHocTable ttTb = new ThongTinPhongHocTable(ttList);
        
        // Thiết lập model cho bảng thongTinPhongHocTable (model chứa dữ liệu hiển thị trên bảng)
        thongTinPhongHocTable.setModel(ttTb);
    }
    
    // Hàm lấy thông tin nhập liệu trên form và tạo thành một đối tượng mới
    private ThongTinPhongHoc layThongTinPhongHocTuForm() {
        // Lấy giá trị từ ô Mã Phòng Học
        Integer maPhongHoc =!maPhongHocField.getText().isEmpty() ? Integer.parseInt(maPhongHocField.getText()) : null ;

        // Lấy giá trị từ ô Tên Phòng Học
        String tenPhongHoc = tenPhongHocField.getText();

        // Lấy giá trị từ ô Dung Lượng
        Integer dungLuong = !dungLuongField.getText().isEmpty() ? Integer.parseInt(dungLuongField.getText()) : null;

        // Lấy giá trị từ ô Mã Thiết Bị
        Integer maThietBi =!maThietBiField.getText().isEmpty() ? Integer.parseInt(maThietBiField.getText()) : null;

        // Lấy giá trị từ ô Tên Thiết Bị
        String tenThietBi = tenThietBiField.getText();

        // Lấy giá trị từ ô Số Lượng Thiết Bị
        Integer soLuongThietBi =!soLuongThietBiField.getText().isEmpty() ? Integer.parseInt(soLuongThietBiField.getText()) : null;

        // Tạo và trả về một đối tượng Thông Tin Phòng Học mới với giá trị lấy được
        return new ThongTinPhongHoc(maPhongHoc,tenPhongHoc,dungLuong,maThietBi,tenThietBi,soLuongThietBi);
    }

    // Hàm thực hiện thêm Thông Tin Phòng Học
    public void themThongTinPhongHoc() {
        try {
            // Tạo đối tượng Thông Tin Phòng Học với thông tin lấy được từ form
            ThongTinPhongHoc ttPH = layThongTinPhongHocTuForm();

            // Gọi hàm ttDao.themPhongHoc để thêm dữ liệu vào database
            ttDao.themPhongHoc(ttPH);

            // Xoá chuỗi nhập ở ô nhập liệu
            clearInputFields();

            // Cập nhật lại dữ liệu trên bảng sau khi thêm
            loadData();
        } catch (NumberFormatException e) {
            // Hiển thị thông báo lỗi nếu một trong mã phòng học, dung lượng, mã thiết bị, số lượng thiết bị không phải số nguyên
            JOptionPane.showMessageDialog(this, "Mã Phòng Học, Dung Lượng, Mã Thiết Bị, Số Lượng Thiết Bị phải là số nguyên!");
        } catch (Exception e) {
            // Hiển thị thông báo lỗi chung nếu có vấn đề trong việc thêm phòng học
            JOptionPane.showMessageDialog(this,"Lỗi khi thêm thông tin phòng học: " + e.getMessage());
        }
    }

    // Hàm thực hiện sửa Thông Tin Phòng Học
    public void suaThongTinPhongHoc() {
        try {
            // Tạo đối tượng Thông Tin Phòng Học với thông tin lấy được từ form
            ThongTinPhongHoc ttPH =layThongTinPhongHocTuForm();

            // Gọi hàm ttDao.suaPhongHoc để sửa dữ liệu vào database
            ttDao.suaPhongHoc(ttPH);

            // Xoá chuỗi nhập ở ô nhập liệu
            clearInputFields();

            // Cập nhật lại dữ liệu trên bảng sau khi sửa
            loadData();
        } catch (NumberFormatException e) {
            // Hiển thị thông báo lỗi nếu một trong mã phòng học, dung lượng, mã thiết bị, số lượng thiết bị không phải số nguyên
            JOptionPane.showMessageDialog(this, "Mã Phòng Học, Dung Lượng, Mã Thiết Bị, Số Lượng Thiết Bị phải là số nguyên!");
        } catch (Exception e) {
            // Hiển thị thông báo lỗi chung nếu có vấn đề trong việc thêm phòng học
            JOptionPane.showMessageDialog(this,"Lỗi khi sửa thông tin phòng học: " + e.getMessage());
        }
    }

    // Hàm thực hiện xoá Thông tin phòng học
    public void xoaThongTinPhongHoc() {
        try {
            // Chuyển đổi chuỗi nhập ở ô nhập liệu sang kiểu số nguyên
            Integer maPhongHoc = Integer.parseInt(maPhongHocField.getText());

            // Khởi tạo đối tượng thông tin phòng học:
            // Sử dụng mã từ ô nhập liệu
            // Không cần thiết điền giá trị cho các dữ liệu còn lại vì nó không được sử dụng trong hàm ttDao.xoaPhongHoc
            ThongTinPhongHoc ttPH = new ThongTinPhongHoc(maPhongHoc, " ",0,0,"",0);

            // Gọi hàm ttDao.xoaPhongHoc để xoá phòng học theo mã phòng học
            ttDao.xoaPhongHoc(ttPH);

            // Xoá chuỗi nhập ở ô nhập liệu
            clearInputFields();

            // Cập nhật lại dữ liệu trên bảng sau khi xoá
            loadData();
        } catch (NumberFormatException e) {
            // Hiển thị thông báo lỗi nếu Mã thiết bị không phải là số nguyên
            JOptionPane.showMessageDialog(this, "Mã Thiết Bị phải là số nguyên!");
        } catch (Exception e) {
            // Hiển thị thông báo lỗi chung nếu có vấn đề trong việc xoá phòng học
            JOptionPane.showMessageDialog(this,"Lỗi khi xóa thông tin phòng học: " + e.getMessage());
        }
    }


    // Hàm thực hiện tìm kiếm Thông Tin Phòng Học
    public void timKiemThongTinPhongHoc() {
        try {
            // Tạo đối tượng Thông Tin Phòng Học với thông tin lấy được từ form
            ThongTinPhongHoc ttPH = layThongTinPhongHocTuForm();

            // Tạo danh sách tìm kiếm Thông Tin Phòng Học
            List<ThongTinPhongHoc> ttList;

            // Tìm kiếm theo Mã Phòng Học (nếu có)
            if (ttPH.getMaPhongHoc() != null) {
                ttList = ttDao.timPhongHocTheoMaPhongHoc(ttPH.getMaPhongHoc());
            
            // Tìm kiểm theo Tên Phòng Học (nếu có)
            } else if (!ttPH.getTenPhongHoc().isEmpty()) {
                ttList = ttDao.timPhongHocTheoTenPhongHoc(ttPH.getTenPhongHoc());

            // Tìm kiếm theo Dung Lượng (nếu có)
            } else if (ttPH.getDungLuong()!=null) {
                ttList = ttDao.timPhongHocTheoDungLuong(ttPH.getDungLuong());

            // Tìm kiếm theo Mã Thiết Bị (nếu có)
            } else if (ttPH.getMaThietBi() != null) {
                ttList = ttDao.timPhongHocTheoMaThietBi(ttPH.getMaThietBi());

            // Tìm kiếm theo Tên Thiết Bị (Nếu có)
            } else if (!ttPH.getTenThietBi().isEmpty()) {
                ttList = ttDao.timPhongHocTheoTenThietBi(ttPH.getTenThietBi());
            
            // Tìm kiếm theo Số Lượng Thiết Bị (nếu có)
            } else if (ttPH.getSoLuongThietBi() != null) {
                ttList = ttDao.timPhongHocTheoSoLuongThietBi(ttPH.getSoLuongThietBi());

            // Hiển thị thông báo Lối nếu không có điều kiện tìm kiếm
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm thông tin phòng học", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cập nhật dữ liệu bảng với kết quả tìm kiếm:
            // Khởi tạo đối tượng ThongTinPhongHocTable để hiển thị dữ liệu
            ThongTinPhongHocTable model = new ThongTinPhongHocTable(ttList);

            // Thiết lập model cho bảng thongTinPhongHocTable (model chứa dữ liệu để hiển thị trên bảng)
            thongTinPhongHocTable.setModel(model);
        } catch (Exception e) {
            // Hiển thị thông báo Lỗi nếu gặp lỗi trong quá trình tìm kiếm
            JOptionPane.showMessageDialog(this,"Lỗi khi tìm kiếm thông tin phòng học: " + e.getMessage());
        }
    }

    // Hàm xoá dữ liệu trên các ô nhập liệu
    private void clearInputFields() {
        maPhongHocField.setText("");
        tenPhongHocField.setText("");
        dungLuongField.setText("");
        maThietBiField.setText("");
        tenThietBiField.setText("");
        soLuongThietBiField.setText("");
    }
}
