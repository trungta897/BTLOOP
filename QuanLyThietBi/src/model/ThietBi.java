// Gói quản lý, xử lý dữ liệu
package model;

// Lớp ThietBi chứa các thông tin về bảng thiet_bi trong database cùng với các phương thức getter, setter tương ứng
public class ThietBi {
    // Mã thiết bị
    private Integer maThietBi;

    // Tên thiết bị
    private String tenThietBi;

    // Xuất xứ
    private String xuatXu;

    // Năm sản xuất
    private Integer namSanXuat;

    // Tình trạng
    private String tinhTrang;

    // Khởi tạo đối tượng ThietBi với đầy đủ thông tin (constructor)
    public ThietBi(Integer maThietBi, String tenThietBi, String xuatXu, Integer namSanXuat, String tinhTrang) {
        this.maThietBi = maThietBi;
        this.tenThietBi = tenThietBi;
        this.xuatXu = xuatXu;
        this.namSanXuat = namSanXuat;
        this.tinhTrang = tinhTrang;
    }

    // Khởi tạo đối tượng ThietBi mặc định (không tham số)
    public ThietBi() {
    }

    // Lấy mã thiết bị
    public Integer getMaThietBi() {
        return this.maThietBi;
    }

    // Lấy tên thiết bị
    public String getTenThietBi() {
        return tenThietBi;
    }

    // Lấy xuất xứ
    public String getXuatXu() {
        return xuatXu;
    }

    // Lấy năm sản xuất
    public Integer getNamSanXuat() {
        return namSanXuat;
    }

    // Lấy tình trạng
    public String getTinhTrang() {
        return tinhTrang;
    }

    // Thiết lập mã thiết bị
    public void setMaThietBi(Integer maThietBi) {
        this.maThietBi = maThietBi;
    }

    // Thiết lập tên thiết bị
    public void setTenThietBi(String tenThietBi) {
        this.tenThietBi = tenThietBi;
    }

    // Thiết lập xuất xứ
    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    // Thiết lập năm sản xuất
    public void setNamSanXuat(Integer namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    // Thiết lập tình trạng
    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}