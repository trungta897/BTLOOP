// Gói quản lý, xử lý dữ liệu
package model;

// Lớp XuatXu chứa các thông tin của bảng xuat_xu cùng với các phương thức getter, setter
public class XuatXu {
    // Mã thiết bị
    private Integer maThietBi;

    // Loại xuất xứ
    private String loaiXuatXu;

    // Khởi tạo đối tượng XuatXu với thông tin đầy đủ (constructor)
    public XuatXu(Integer maThietBi, String loaiXuatXu) {
        this.maThietBi = maThietBi;
        this.loaiXuatXu = loaiXuatXu;
    }

    // Khởi tạo đối tượng XuatXu với không tham số (mặc định)
    public XuatXu() {
    }

    // Lấy mã thiết bị
    public Integer getMaThietBi() {
        return maThietBi;
    }

    // Lấy loại xuất xứ
    public String getLoaiXuatXu() {
        return loaiXuatXu;
    }

    // Thiết lập mã thiết bị
    public void setMaThietBi(Integer maThietBi) {
        this.maThietBi = maThietBi;
    }

    // Thiết lập loại xuất xứ
    public void setLoaiXuatXu(String loaiXuatXu) {
        this.loaiXuatXu = loaiXuatXu;
    }
}