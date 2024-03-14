// Gói quản lý, xử lý dữ liệu
package model;

// Lớp ThongTinPhongHoc chứa các dữ liệu của bảng thong_tin_phong_hoc cùng với các phương thức getter, setter
public class ThongTinPhongHoc {
    // Mã Phòng Học
    private Integer maPhongHoc;

    // Tên Phòng Học
    private String tenPhongHoc;

    // Dung Lượng
    private Integer dungLuong;

    // Mã Thiết Bị
    private Integer maThietBi;

    // Tên Thiết Bị
    private String tenThietBi;

    // Số Lượng Thiết Bị
    private Integer soLuongThietBi;

    // Khởi tạo đối tượng ThongTinPhongHoc với đầy đủ thông tin (constructor)
    public ThongTinPhongHoc(Integer maPhongHoc, String tenPhongHoc, Integer dungLuong, Integer maThietBi,String tenThietBi, Integer soLuongThietBi) {
        this.maPhongHoc = maPhongHoc;
        this.tenPhongHoc = tenPhongHoc;
        this.dungLuong = dungLuong;
        this.maThietBi = maThietBi;
        this.tenThietBi = tenThietBi;
        this.soLuongThietBi = soLuongThietBi;
    }

    // Khởi tạo đối tượng ThongTinPhongHoc không tham số (mặc định)
    public ThongTinPhongHoc() {
    }

    // Lấy mã phòng học
    public Integer getMaPhongHoc() {
        return maPhongHoc;
    }

    // Lấy tên phòng học
    public String getTenPhongHoc() {
        return tenPhongHoc;
    }

    // Lấy dung lượng
    public Integer getDungLuong() {
        return dungLuong;
    }

    // Lấy mã thiết bị
    public Integer getMaThietBi() {
        return maThietBi;
    }

    // Lấy tên thiết bị
    public String getTenThietBi() {
        return tenThietBi;
    }

    // Lấy số lượng thiết bị
    public Integer getSoLuongThietBi() {
        return soLuongThietBi;
    }

    // Thiết lập mã phòng học
    public void setMaPhongHoc(Integer maPhongHoc) {
        this.maPhongHoc = maPhongHoc;
    }

    // Thiết lập tên phòng học
    public void setTenPhongHoc(String tenPhongHoc) {
        this.tenPhongHoc = tenPhongHoc;
    }

    // Thiết lập dung lượng
    public void setDungLuong(Integer dungLuong) {
        this.dungLuong = dungLuong;
    }

    // Thiết lập mã thiết bị
    public void setMaThietBi(Integer maThietBi) {
        this.maThietBi = maThietBi;
    }

    // Thiết lập tên thiết bị
    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    // Thiết lập số lượng thiết bị
    public void setSoLuongThietBi(Integer soLuongThietBi) {
        this.soLuongThietBi = soLuongThietBi;
    }
}