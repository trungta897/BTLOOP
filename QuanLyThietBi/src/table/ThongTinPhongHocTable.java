// Gói bảng để hiển thị dữ liệu trên bảng
package table;

// Thêm các thư viện cần thiết
import javax.swing.table.AbstractTableModel;
import java.util.List;

// Thêm các file cần thiết từ các gói khác trong project 
import model.ThongTinPhongHoc;

// Lớp ThongTinPhongHocTable kế thừa từ lớp AbstractTableModel trong thư viện javax.swing.table.AbstractTableModel
// Tạo ra bảng để hiển thị dữ liệu
public class ThongTinPhongHocTable extends AbstractTableModel {
    // Danh sách các đối tượng Thông Tin Phòng Học được hiển thị trên bảng
    private List<ThongTinPhongHoc> thongTinPhongHocList;

    // Tên các cột của bảng
    private final String[] columnNames = {"Mã Phòng Học", "Tên Phòng Học", "Dung Lượng", "Mã Thiết Bị", "Tên Thiết Bị", "Số Lượng Thiết Bị"};

    // Khởi tạo đối tượng ThongTinPhongHocTable với danh sách Thông Tin Phòng Học
    public ThongTinPhongHocTable(List<ThongTinPhongHoc> thongTinPhongHocList) {
        this.thongTinPhongHocList = thongTinPhongHocList;
    }

     // Lấy số lượng dòng dữ liệu (bằng số lượng đối tượng Thông Tin Phòng Học)
    @Override
    public int getRowCount() {
        return thongTinPhongHocList.size();
    }

    // Lấy số lượng cột dữ liệu (bằng số lượng tên cột)
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Lấy tên của một cột cụ thể
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    // Lấy giá trị tại một ô cụ thể (dòng, cột)
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Lấy đối tượng Thông Tin Phòng Học tại dòng hiện tại
        ThongTinPhongHoc tt = thongTinPhongHocList.get(rowIndex);
        // Trả về giá trị thuộc tính của Thiết Bị tương ứng với chỉ mục cột
        switch (columnIndex) {
            case 0:
                return tt.getMaPhongHoc();
            case 1: 
                return tt.getTenPhongHoc();
            case 2:
                return tt.getDungLuong();
            case 3:
                return tt.getMaThietBi();
            case 4:
                return tt.getTenThietBi();
            case 5:
                return tt.getSoLuongThietBi();
            default:
                return null;
        }
    }
}


