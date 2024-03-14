-- drop database quanLyThietBi;
create database quanLyThietBi;

use quanLyThietBi;

-- Bảng Thiết bị
CREATE TABLE thiet_bi (
  maThietBi INT PRIMARY KEY,
  tenThietBi VARCHAR(255) NOT NULL,
  xuatXu VARCHAR(255) NOT NULL,
  namSanXuat INT NOT NULL,
  tinhTrang VARCHAR(255) NOT NULL
);

-- Bảng Xuất xứ
CREATE TABLE xuat_xu (
  maThietBi INT PRIMARY KEY,
  loaiXuatXu VARCHAR(255) NOT NULL
);

CREATE TABLE thong_tin_phong_hoc (
maPhongHoc INT NOT NULL,
tenPhongHoc VARCHAR (255) NOT NULL,
dungLuong INT NOT NULL,
maLoaiThietBi INT NOT NULL,
tenLoaiThietBi VARCHAR(255) NOT NULL,
soLuongLoaiThietBi INT NOT NULL,
PRIMARY KEY (maPhongHoc, maLoaiThietBi));


-- Thêm dữ liệu vào bảng Thiết bị
INSERT INTO thiet_bi (maThietBi, tenThietBi, xuatXu, namSanXuat, tinhTrang) VALUES
  (1, 'Máy chiếu', 'Việt Nam', 2023, 'Hoạt động tốt'),
  (2, 'Máy tính', 'Việt Nam', 2022, 'Hoạt động tốt'),
  (3, 'Bàn học', 'Nhật Bản', 2021, 'Hoạt động tốt');

-- Thêm dữ liệu vào bảng Xuất xứ
INSERT INTO xuat_xu (maThietBi, loaiXuatXu) VALUES
  (1, 'Nhập khẩu'),
  (2, 'Nội địa'),
  (3, 'Liên doanh');

INSERT INTO thong_tin_phong_hoc (maPhongHoc, tenPhongHoc, dungLuong, maLoaiThietBi, tenLoaiThietBi, soLuongLoaiThietBi) VALUES 
    (1, 'Phòng 101', 50, 1, 'Máy tính', 20),
    (1, 'Phòng 101', 50, 2, 'Bảng trắng', 5),
    (2, 'Phòng 102', 30, 1, 'Máy tính', 15),
    (2, 'Phòng 102', 30, 3, 'Máy chiếu', 2);

