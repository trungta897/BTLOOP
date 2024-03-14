// Gói điều khiển thao tác của người dùng
package controller;

// Thêm các thư viện cần thiết
import dao.*;
import model.*;
import java.util.*;
import java.sql.*;

// Lớp Controller chứa các phương thức điều khiển thao tác 
public class Controller {
    // Các đối tượng DAO để tương tác với database
    private ThietBiDao tbDao;
    private XuatXuDao xxDao;
    private ThongTinPhongHocDao phDao;

    // Hàm khởi tạo - truyền vào các đối tượng DAO (constructor)
    public Controller(ThietBiDao tbDao, XuatXuDao xxDao, ThongTinPhongHocDao phDao) {
        this.tbDao = tbDao;
        this.xxDao = xxDao;
        this.phDao = phDao;
    }

    //* Quản lý Thiết Bị:

    //  Lấy danh sách tất cả thiết bị
    public List<ThietBi> danhSachThietBi() {
        return tbDao.danhSachThietBi();
    }

    // Thêm thiết bị mới
    public void themThietBi(ThietBi thietBi) throws Exception {
        tbDao.themThietBi(thietBi);
    }

    // Cập nhật thông tin thiết bị
    public void capNhatThietBi(ThietBi thietBi) throws Exception {
        tbDao.suaThietBi(thietBi);
    }

    // Xóa thiết bị
    public void xoaThietBi(ThietBi thietBi) throws SQLException {
        tbDao.xoaThietBi(thietBi);
    }

    // Tìm kiếm thiết bị theo tên
    public List<ThietBi> timThietBiTheoTenThietBi(String tenThietBi) {
        return tbDao.timThietBiTheoTen(tenThietBi);
    }

    // Tìm kiếm thiết bị theo mã
    public List<ThietBi> timThietBiTheoMaThietBi(Integer maThietBi) {
        return tbDao.timThietBiTheoMaThietBi(maThietBi);
    }

    // Tìm kiếm thiết bị theo năm sản xuất
    public List<ThietBi> timThietBiTheoNamSanXuat(Integer namSanXuat) {
        return tbDao.timThietBiTheoNamSanXuat(namSanXuat);
    }

    // Tìm kiếm thiết bị theo xuất xứ
    public List<ThietBi> timThietBiTheoXuatXu(String xuatXu) {
        return tbDao.timThietBiTheoXuatXu(xuatXu);
    }

    // Tìm kiếm thiết bị theo tình trạng
    public List<ThietBi> timThietBiTheoTinhTrang(String tinhTrang) {
        return tbDao.timThietBiTheoTinhTrang(tinhTrang);
    }

    //* Quản lý Xuất Xứ:

    // Lấy danh sách tất cả xuất xứ
    public List<XuatXu> danhSachXuatXu() {
        return xxDao.danhSachXuatXu();
    }

    // Thêm xuất xứ mới
    public void themXuatXu(XuatXu xuatXu) throws Exception {
        xxDao.themXuatXu(xuatXu);
    }

    // Cập nhật thông tin xuất xứ
    public void capNhatXuatXu(XuatXu xuatXu) throws Exception {
        xxDao.suaXuatXu(xuatXu);
    }

    // Xóa xuất xứ
    public void xoaXuatXu(XuatXu xuatXu) throws Exception {
        xxDao.xoaXuatXu(xuatXu);
    }

    // Tìm kiếm xuất xứ theo loại xuất xứ
    public List<XuatXu> timXuatXuTheoLoaiXuatXu(String loaiXuatXu) {
        return xxDao.timXuatXuTheoLoai(loaiXuatXu);
    }

    // Tìm kiếm xuất xứ theo mã
    public List<XuatXu> timXuatXuTheoMaXuatXu(Integer maXuatXu) {
        return xxDao.timXuatXuTheoMa(maXuatXu);
    }

    //* Quản lý Phòng Học:

    // Lấy danh sách tất cả thông tin phòng học
    public List<ThongTinPhongHoc> danhSachThongTinPhongHoc() {
        return phDao.danhSachPhongHoc();
    }

    // Thêm thông tin phòng học mới
    public void themThongTinPhongHoc(ThongTinPhongHoc thongTinPhongHoc) throws Exception {
        phDao.themPhongHoc(thongTinPhongHoc);
    }

    // Sửa thông tin phòng học
    public void capNhatThongTinPhongHoc(ThongTinPhongHoc thongTinPhongHoc) throws Exception {
        phDao.suaPhongHoc(thongTinPhongHoc);
    }

    // Xoá thông tin phòng học
    public void xoaThongTinPhongHoc(ThongTinPhongHoc thongTinPhongHoc) throws Exception {
        phDao.xoaPhongHoc(thongTinPhongHoc);
    }

    // Tìm kiếm thông tin phòng học theo tên
    public List<ThongTinPhongHoc> timPhongHocTheoTenPhongHoc(String tenPhongHoc) {
        return phDao.timPhongHocTheoTenPhongHoc(tenPhongHoc);
    }

    // Tìm kiếm thông tin phòng học theo mã phòng học
    public List<ThongTinPhongHoc> timPhongHocTheoMaPhongHoc(Integer maPhongHoc) {
        return phDao.timPhongHocTheoMaPhongHoc(maPhongHoc);
    }

    // Tìm kiếm thông tin phòng học theo sức chứa
    public List<ThongTinPhongHoc> timPhongHocTheoDungLuong(Integer dungLuong) {
        return phDao.timPhongHocTheoDungLuong(dungLuong);
    }

    // Tìm kiếm thông tin phòng học theo mã thiết bị
    public List<ThongTinPhongHoc> timPhongHocTheoMaThietBi(Integer maThietBi) {
        return phDao.timPhongHocTheoMaThietBi(maThietBi);
    }

    // Tìm kiếm thông tin phòng học theo tên thiết bị
    public List<ThongTinPhongHoc> timPhongHocTheoTenThietBi(String tenThietBi) {
        return phDao.timPhongHocTheoTenThietBi(tenThietBi);
    }

    // Tìm kiếm thông tin phòng học theo số lượng thiết bị
    public List<ThongTinPhongHoc> timPhongHocTheoSoLuongThietBi(Integer soLuongThietBi) {
        return phDao.timPhongHocTheoSoLuongThietBi(soLuongThietBi);
    }

}
