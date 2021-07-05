/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.ArrayList;
import Models.NhanVien;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class NhanVienController extends DBConnect {
    public Connection connection;
    public NhanVienController(){
        this.connection = getConnection();
    }
    public ArrayList<NhanVien> getAllNhanVien(){
        ArrayList<NhanVien> listNhanVien = new ArrayList<NhanVien>();
        String query = "select * from nhanvien";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                //retrieve data from result set
                int maNhanVien = resultSet.getInt(1);
                String tenNhanVien = resultSet.getString(2);
                String ngaySinh = resultSet.getString(3);
                String diaChi = resultSet.getString(4);
                int soDienThoai = resultSet.getInt(5);
                String email = resultSet.getString(6);
                int gioiTinh = resultSet.getInt(7);
                String chucVu = resultSet.getString(8);
                String queQuan = resultSet.getString(9);
                String ngayVaoLam = resultSet.getString(10);
                String hinhAnh = resultSet.getString(11);
                String username = resultSet.getString(12);
                String password = resultSet.getString(13);
                int luongCoBan = resultSet.getInt(14);
                int luongThuong = resultSet.getInt(15);
                float heSoLuong = resultSet.getFloat(16);
                int status = resultSet.getInt(17);
                
                //init new model
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNhanVien(maNhanVien);
                nhanVien.setTenNhanVien(tenNhanVien);
                nhanVien.setNgaySinh(ngaySinh);
                nhanVien.setDiaChi(diaChi);
                nhanVien.setSoDienThoai(soDienThoai);
                nhanVien.setEmail(email);
                nhanVien.setGioiTinh(gioiTinh);
                nhanVien.setChucVu(chucVu);
                nhanVien.setQueQuan(queQuan);
                nhanVien.setNgayVaoLam(ngayVaoLam);
                nhanVien.setHinhAnh(hinhAnh);
                nhanVien.setUsername(username);
                nhanVien.setPassword(password);
                nhanVien.setHeSoLuong(heSoLuong);
                nhanVien.setLuongCoBan(luongCoBan);
                nhanVien.setLuongThuong(luongThuong);
                nhanVien.setStatusFlag(status);
                
                //add model to List
                listNhanVien.add(nhanVien);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }          
        return listNhanVien;
    }
    public boolean insert(NhanVien nhanVien){
        boolean result = false;
        try {
            String insert = "insert into NhanVien(tennhanvien, ngaysinh,diachi,sodienthoai,email,gioitinh,chucvu,quequan,ngayvl,hinhanh,luong_co_ban,luong_thuong,he_so_luong,username,password) "
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1,nhanVien.getTenNhanVien());
            preparedStatement.setString(2,nhanVien.getNgaySinh().toString());
            preparedStatement.setString(3,nhanVien.getDiaChi());
            preparedStatement.setInt(4,nhanVien.getSoDienThoai());
            preparedStatement.setString(5,nhanVien.getEmail());
            preparedStatement.setInt(6,nhanVien.getGioiTinh());
            preparedStatement.setString(7,nhanVien.getChucVu());
            preparedStatement.setString(8,nhanVien.getQueQuan());
            preparedStatement.setString(9,nhanVien.getNgayVaoLam().toString());
            preparedStatement.setString(10,nhanVien.getHinhAnh());
            preparedStatement.setInt(11,nhanVien.getLuongCoBan());
            preparedStatement.setInt(12,nhanVien.getLuongThuong());
            preparedStatement.setFloat(13,nhanVien.getHeSoLuong());
            preparedStatement.setString(14,nhanVien.getUsername());
            preparedStatement.setString(15,nhanVien.getPassword());
            
            if (preparedStatement.executeUpdate()>0) {
                result = true;
            }
            else result = false;

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return result;
    }

    public boolean update(NhanVien nhanVien) {
        boolean result = false;
        try {
            String insert = "update NhanVien set tennhanvien = ?, ngaysinh = ?,diachi = ?, sodienthoai = ?,email = ? ,gioitinh = ? ,chucvu = ?, quequan = ?, ngayvl = ?, hinhanh = ?, luong_co_ban = ?,luong_thuong = ?, he_so_luong = ?, statusflag = ?, username = ?, password = ? "
                    +"where manhanvien = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1,nhanVien.getTenNhanVien());
            preparedStatement.setString(2,nhanVien.getNgaySinh().toString());
            preparedStatement.setString(3,nhanVien.getDiaChi());
            preparedStatement.setInt(4,nhanVien.getSoDienThoai());
            preparedStatement.setString(5,nhanVien.getEmail());
            preparedStatement.setInt(6,nhanVien.getGioiTinh());
            preparedStatement.setString(7,nhanVien.getChucVu());
            preparedStatement.setString(8,nhanVien.getQueQuan());
            preparedStatement.setString(9,nhanVien.getNgayVaoLam().toString());
            preparedStatement.setString(10,nhanVien.getHinhAnh());
            preparedStatement.setInt(11,nhanVien.getLuongCoBan());
            preparedStatement.setInt(12,nhanVien.getLuongThuong());
            preparedStatement.setFloat(13,nhanVien.getHeSoLuong());
            preparedStatement.setInt(14, nhanVien.getStatusFlag());
            preparedStatement.setString(15, nhanVien.getUsername());
            preparedStatement.setString(16, nhanVien.getPassword());
            preparedStatement.setInt(17, nhanVien.getMaNhanVien());

            
            if (preparedStatement.executeUpdate()>0) {
                result = true;
            }
            else result = false;

        } catch (SQLException ex) {
            Logger.getLogger(NhanVienController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return result;
    }
    
    public boolean delete(NhanVien nhanVien){
        boolean result = true;
        try{
            String query = "delete from NhanVien where manhanvien = " + nhanVien.getMaNhanVien();
            Statement smt = connection.createStatement();
            if (smt.executeUpdate(query) > 0) {
                result = true;
            }
            else result = false;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
