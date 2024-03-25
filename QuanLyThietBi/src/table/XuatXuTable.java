// Gói bảng để hiển thị dữ liệu trên bảng
package table;

// Thêm các thư viện cần thiết
import javax.swing.table.AbstractTableModel;
import java.util.List;

// Thêm các file cần thiết từ các gói khác trong project
import model.XuatXu;

// Lớp XuatXuTable kế thừa từ lớp AbstractTableModel trong thư viện javax.swing.table.AbstractTableModel
// Tạo ra bảng để hiển thị dữ liệu
public class XuatXuTable extends AbstractTableModel {
    // Danh sách các đối tượng Xuất Xứ được hiển thị trên bảng
    private List<XuatXu> XuatXuList;

    // Tên các cột của bảng
    private final String[] columnNames = {"Mã Thiết Bị","Loại Xuất Xứ"};

    // Khởi tạo đối tượng XuatXuTable với danh sách Xuất Xứ
    public XuatXuTable(List<XuatXu> XuatXuList) {
        this.XuatXuList = XuatXuList;
    }

    // Lấy số lượng dòng dữ liệu (bằng số lượng đối tượng Xuất Xứ)
    @Override
    public int getRowCount() {
        return XuatXuList.size();
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
        // Lấy đối tượng Xuất Xứ tại dòng hiện tại
        XuatXu XuatXu = XuatXuList.get(rowIndex);
        
        // Trả về giá trị thuộc tính của Xuất Xứ tương ứng với chỉ mục cột
        switch (columnIndex) {
            case 0:
                return XuatXu.getMaThietBi();
            case 1:
                return XuatXu.getLoaiXuatXu();
            default:
                return null;
        }
    }
}
