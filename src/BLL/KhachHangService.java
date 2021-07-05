package BLL;


import DAL.DBConnect;
import DTO.KhachHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * * Khach Hang Service
 *
 * @author Admin
 * @since 6/15/2021 12:23 SA
 */

public class KhachHangService {
    private static final String SQL_FIND_ALL =
            "SELECT makh, tenkh, ngaysinh, diachi, sodienthoai, email, gioitinh, statusflag " +
                    "FROM khachhang WHERE statusflag = 1";
    private static final String SQL_FETCH =
            "SELECT makh, tenkh, ngaysinh, diachi, sodienthoai, email, gioitinh, statusflag " +
                    "FROM khachhang WHERE makh = ?";

    private static final String SQl_CREATE =
            "INSERT INTO khachhang " +
                    "(tenkh, ngaysinh, diachi, sodienthoai, email, gioitinh, statusflag) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?) ";

    private static final String SQL_UPDATE =
            "UPDATE khachhang " +
                    "SET tenkh=?, ngaysinh=?, diachi=?, sodienthoai=?, email=?, gioitinh=? " +
                    "WHERE makh= ? ";

    private static final String SQL_GET_ID_MAX =
            "SELECT max(makh) FROM khachhang k";

    private static final String SQL_DELETE =
            "UPDATE khachhang set statusflag = 0 " +
                    "WHERE makh=? ";


    /**
     * Find all list.
     *
     * @return the list
     */
    public List<KhachHang> findAll() {
        try {
            List<KhachHang> khachHangList = new ArrayList<>();
            Connection conn = DBConnect.getConnection();
            // crate statement
            PreparedStatement stmt = conn.prepareStatement(SQL_FIND_ALL);

            ResultSet rs = stmt.executeQuery();
            // show data
            while (rs.next()) {
                KhachHang khachHang = new KhachHang(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8)
                );
                khachHangList.add(khachHang);
            }
            stmt.close();
            conn.close();
            return khachHangList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public KhachHang fetchById(Long id) {
        try {
            Connection conn = DBConnect.getConnection();
            // crate statement
            PreparedStatement stmt = conn.prepareStatement(SQL_FETCH);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            // show data
            while (rs.next()) {
                return new KhachHang(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8)
                );
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Create khach hang.
     *
     * @param khachHang the khach hang
     *
     * @return the khach hang
     */
    public KhachHang create(KhachHang khachHang) {
        try {
            Connection conn = DBConnect.getConnection();
            // crate statement
            PreparedStatement stmt = conn.prepareStatement(SQl_CREATE);

            stmt.setString(1, khachHang.getTenKH());
            stmt.setDate(2, khachHang.getNgaySinh());
            stmt.setString(3, khachHang.getDiaChi());
            stmt.setInt(4, khachHang.getSoDienThoai());
            stmt.setString(5, khachHang.getEmail());
            stmt.setBoolean(6, khachHang.getGioiTinh());
            stmt.setBoolean(7, khachHang.getStatusFlag());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return fetchById(getMaxId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Gets max id.
     *
     * @return the max id
     */
    public Long getMaxId() {
        try {
            Connection conn = DBConnect.getConnection();
            // crate statement
            PreparedStatement stmt = conn.prepareStatement(SQL_GET_ID_MAX);
            ResultSet rs = stmt.executeQuery();
            // show data
            while (rs.next()) {
                return rs.getLong(1);
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Update khach hang.
     *
     * @param khachHang the khach hang
     *
     * @return the khach hang
     */
    public KhachHang update(KhachHang khachHang){
        try {
            Connection conn = DBConnect.getConnection();
            // crate statement
            PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, khachHang.getTenKH());
            stmt.setDate(2, khachHang.getNgaySinh());
            stmt.setString(3, khachHang.getDiaChi());
            stmt.setInt(4, khachHang.getSoDienThoai());
            stmt.setString(5, khachHang.getEmail());
            stmt.setBoolean(6, khachHang.getGioiTinh());
            //stmt.setBoolean(7, khachHang.getStatusFlag());
            stmt.setLong(7, khachHang.getMaKH());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return khachHang;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    public int delete(Long id) {
        try {
            Connection conn = DBConnect.getConnection();
            // crate statement
            PreparedStatement stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setLong(1, id);

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
        return 1;
    }


}

