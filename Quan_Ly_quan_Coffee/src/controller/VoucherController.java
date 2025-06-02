package controller;
import model.Database;
import model.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import utils.DatabaseUtil;

/**
 * Controller xử lý các thao tác với voucher
 */
public class VoucherController {
    
    private DatabaseUtil dbUtil;
    
    public VoucherController() {
        this.dbUtil = new DatabaseUtil();
    }
    
    /**
     * Lấy tất cả voucher từ database
     * @return Danh sách các voucher
     */
    public List<Voucher> getAllVouchers() {
        List<Voucher> listVouchers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = Database.getInstance().getConnection();
            
            String sql = "SELECT * FROM Voucher ORDER BY id ASC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt("id"));
                voucher.setMaVoucher(rs.getString("maVoucher"));
                voucher.setPhanTramGiamGia(rs.getBigDecimal("phanTramGiam"));
                voucher.setNgayBatDau(rs.getDate("ngayBatDau"));
                voucher.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                String trangThaiStr = rs.getString("trangThai");
                voucher.setTrangThai("Kích hoạt".equalsIgnoreCase(trangThaiStr));
                listVouchers.add(voucher);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách voucher: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
        }
        
        return listVouchers;
    }
    
    /**
     * Thêm voucher mới vào database
     * @param voucher Đối tượng voucher cần thêm
     * @return true nếu thêm thành công, false nếu thất bại
     */
    public boolean themVoucher(Voucher voucher) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = Database.getInstance().getConnection();
            
            String sql = "INSERT INTO Voucher (maVoucher, phanTramGiam, ngayBatDau, ngayKetThuc, trangThai) VALUES (?, ?, ?, ?, ?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, voucher.getMaVoucher());
            pstmt.setBigDecimal(2, voucher.getPhanTramGiamGia());
            pstmt.setDate(3, convertToSqlDate(voucher.getNgayBatDau()));
            pstmt.setDate(4, convertToSqlDate(voucher.getNgayKetThuc()));
            pstmt.setString(5, voucher.isTrangThai() ? "Kích hoạt" : "Hết hạn");
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm voucher: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(pstmt);
        }
    }
    
    /**
     * Cập nhật thông tin voucher trong database
     * @param voucher Đối tượng voucher cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean suaVoucher(Voucher voucher) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = Database.getInstance().getConnection();
            
            String sql = "UPDATE Voucher SET maVoucher = ?, phanTramGiam = ?, ngayBatDau = ?, ngayKetThuc = ?, trangThai = ? WHERE id = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, voucher.getMaVoucher());
            pstmt.setBigDecimal(2, voucher.getPhanTramGiamGia());
            pstmt.setDate(3, convertToSqlDate(voucher.getNgayBatDau()));
            pstmt.setDate(4, convertToSqlDate(voucher.getNgayKetThuc()));
            pstmt.setString(5, voucher.isTrangThai() ? "Kích hoạt" : "Hết hạn");
            pstmt.setInt(6, voucher.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật voucher: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(pstmt);
        }
    }
    
    /**
     * Xóa voucher khỏi database
     * @param id ID của voucher cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     */
    public boolean xoaVoucher(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = Database.getInstance().getConnection();
            
            // Kiểm tra xem voucher đã được sử dụng trong hóa đơn chưa
            if (isVoucherUsedInHoaDon(conn, id)) {
                return false; // Không xóa nếu voucher đã sử dụng
            }
            
            String sql = "DELETE FROM Voucher WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa voucher: " + e.getMessage());
            return false;
        } finally {
            DatabaseUtil.closePreparedStatement(pstmt);
        }
    }
    
    /**
     * Tìm kiếm voucher theo từ khóa (mã voucher, mô tả)
     * @param keyword Từ khóa tìm kiếm
     * @return Danh sách các voucher phù hợp
     */
    public List<Voucher> timKiemVoucher(String keyword) {
        List<Voucher> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = Database.getInstance().getConnection();
            
            String sql = "SELECT * FROM Voucher WHERE maVoucher LIKE ? ORDER BY id ASC";
            pstmt = conn.prepareStatement(sql);
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Voucher voucher = new Voucher();
                voucher.setId(rs.getInt("id"));
                voucher.setMaVoucher(rs.getString("maVoucher"));
                voucher.setPhanTramGiamGia(rs.getBigDecimal("phanTramGiam"));
                voucher.setNgayBatDau(rs.getDate("ngayBatDau"));
                voucher.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                String trangThaiStr = rs.getString("trangThai");
                voucher.setTrangThai("Kích hoạt".equalsIgnoreCase(trangThaiStr));
                results.add(voucher);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm voucher: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
        }
        
        return results;
    }
    
    /**
     * Kiểm tra xem mã voucher đã tồn tại trong hệ thống chưa
     * @param maVoucher Mã voucher cần kiểm tra
     * @param excludeId ID voucher cần loại trừ (dùng khi cập nhật)
     * @return true nếu mã đã tồn tại, false nếu chưa
     */
    public boolean kiemTraMaVoucherTonTai(String maVoucher, int excludeId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean exists = false;
        
        try {
            conn = Database.getInstance().getConnection();
            
            String sql = "SELECT COUNT(*) FROM Voucher WHERE maVoucher = ? AND id != ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maVoucher);
            pstmt.setInt(2, excludeId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra mã voucher: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
        }
        
        return exists;
    }
    
    /**
     * Tìm voucher theo mã voucher
     * @param maVoucher Mã voucher cần tìm
     * @return Đối tượng Voucher nếu tìm thấy, null nếu không
     */
    public Voucher timVoucherTheoMa(String maVoucher) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Voucher voucher = null;
        
        try {
            conn = Database.getInstance().getConnection();
            
            String sql = "SELECT * FROM Voucher WHERE maVoucher = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maVoucher);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                voucher = new Voucher();
                voucher.setId(rs.getInt("id"));
                voucher.setMaVoucher(rs.getString("maVoucher"));
                voucher.setPhanTramGiamGia(rs.getBigDecimal("phanTramGiam"));
                voucher.setNgayBatDau(rs.getDate("ngayBatDau"));
                voucher.setNgayKetThuc(rs.getDate("ngayKetThuc"));
                String trangThaiStr = rs.getString("trangThai");
                voucher.setTrangThai("Kích hoạt".equalsIgnoreCase(trangThaiStr));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm voucher theo mã: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
        }
        
        return voucher;
    }
    
    /**
     * Cập nhật trạng thái voucher dựa trên ngày hiện tại
     * Tự động cập nhật voucher sang trạng thái "Hết hạn" nếu ngày kết thúc đã qua
     * @return Số lượng voucher đã được cập nhật
     */
    public int capNhatTrangThaiVoucher() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int updatedCount = 0;
        
        try {
            conn = Database.getInstance().getConnection();
            
            String sql = "UPDATE Voucher SET trangThai = N'Hết hạn' " +
                         "WHERE ngayKetThuc < GETDATE() AND trangThai = N'Kích hoạt'";
            
            pstmt = conn.prepareStatement(sql);
            updatedCount = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật trạng thái voucher: " + e.getMessage());
        } finally {
            DatabaseUtil.closePreparedStatement(pstmt);
        }
        
        return updatedCount;
    }
    
    /**
     * Kiểm tra xem voucher có thể áp dụng cho một đơn hàng không
     * @param voucherId ID của voucher
     * @param tongTien Tổng tiền của đơn hàng
     * @return true nếu voucher có thể áp dụng, false nếu không
     */
    public boolean kiemTraVoucherHopLe(int voucherId, BigDecimal tongTien) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isValid = false;
        
        try {
            conn = Database.getInstance().getConnection();
            
            String sql = "SELECT * FROM Voucher WHERE id = ? AND trangThai = N'Kích hoạt' " +
                         "AND phanTramGiam <= ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, voucherId);
            pstmt.setBigDecimal(2, tongTien);
            
            rs = pstmt.executeQuery();
            isValid = rs.next();
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi kiểm tra voucher hợp lệ: " + e.getMessage());
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
        }
        
        return isValid;
    }
    
    /**
     * Giảm số lượng voucher khi được sử dụng
     * @param voucherId ID của voucher
     * @return true nếu giảm thành công, false nếu thất bại
     */
    public boolean giamSoLuongVoucher(int voucherId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = Database.getInstance().getConnection();
            conn.setAutoCommit(false);
            
            // Kiểm tra số lượng hiện tại
            String checkSql = "SELECT phanTramGiam FROM Voucher WHERE id = ? AND phanTramGiam > 0";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, voucherId);
            ResultSet rs = checkStmt.executeQuery();
            
            if (rs.next()) {
                BigDecimal currentQuantity = rs.getBigDecimal("phanTramGiam");
                if (currentQuantity.compareTo(BigDecimal.ZERO) > 0) {
                    // Giảm số lượng
                    String updateSql = "UPDATE Voucher SET phanTramGiam = phanTramGiam - ? WHERE id = ?";
                    pstmt = conn.prepareStatement(updateSql);
                    pstmt.setBigDecimal(1, currentQuantity.subtract(BigDecimal.ONE));
                    pstmt.setInt(2, voucherId);
                    
                    int rowsAffected = pstmt.executeUpdate();
                    success = rowsAffected > 0;
                    
                    // Cập nhật trạng thái khi số lượng = 0
                    String updateStatusSql = "UPDATE Voucher SET trangThai = N'Hết hạn' WHERE id = ? AND phanTramGiam = 0";
                    PreparedStatement updateStatusStmt = conn.prepareStatement(updateStatusSql);
                    updateStatusStmt.setInt(1, voucherId);
                    updateStatusStmt.executeUpdate();
                    
                    conn.commit();
                }
            }
            
            rs.close();
            checkStmt.close();
            
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Lỗi khi rollback: " + ex.getMessage());
            }
            System.err.println("Lỗi khi giảm số lượng voucher: " + e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                System.err.println("Lỗi khi reset auto commit: " + e.getMessage());
            }
            DatabaseUtil.closePreparedStatement(pstmt);
        }
        
        return success;
    }

    // ===== CÁC PHƯƠNG THỨC HỖ TRỢ =====
    
    /**
     * Kiểm tra xem voucher đã được sử dụng trong hóa đơn nào chưa
     * @param conn Kết nối database
     * @param voucherId ID của voucher
     * @return true nếu đã sử dụng, false nếu chưa
     */
    private boolean isVoucherUsedInHoaDon(Connection conn, int voucherId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isUsed = false;
        
        try {
            String sql = "SELECT COUNT(*) FROM HoaDon WHERE voucher_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, voucherId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                isUsed = rs.getInt(1) > 0;
            }
        } finally {
            DatabaseUtil.closeResultSet(rs);
            DatabaseUtil.closePreparedStatement(pstmt);
        }
        
        return isUsed;
    }
    
    /**
     * Chuyển đổi từ java.util.Date sang java.sql.Date
     * @param utilDate java.util.Date
     * @return java.sql.Date
     */
    private java.sql.Date convertToSqlDate(java.util.Date utilDate) {
        if (utilDate == null) {
            return null;
        }
        return new java.sql.Date(utilDate.getTime());
    }
    
    /**
     * Chuyển đổi từ trạng thái số sang trạng thái chuỗi
     * @param trangThaiInt Trạng thái dạng số (1: Kích hoạt, 2: Hết hạn, 3: Ngưng sử dụng)
     * @return Trạng thái dạng chuỗi
     */
    private String convertTrangThaiToString(String trangThai) {
        return trangThai;
    }
    
    /**
     * Chuyển đổi từ trạng thái chuỗi sang trạng thái số
     * @param trangThaiString Trạng thái dạng chuỗi
     * @return Trạng thái dạng số
     */
    private String convertTrangThaiToInt(String trangThaiString) {
        if (trangThaiString == null) {
            return "Kích hoạt";
        }
        return trangThaiString;
    }
    
    /**
     * Ánh xạ từ ResultSet sang đối tượng Voucher
     * @param rs ResultSet chứa dữ liệu từ database
     * @return Đối tượng Voucher
     */
    private Voucher mapResultSetToVoucher(ResultSet rs) throws SQLException {
        Voucher voucher = new Voucher();
        
        voucher.setId(rs.getInt("id"));
        voucher.setMaVoucher(rs.getString("maVoucher"));
        voucher.setPhanTramGiamGia(rs.getBigDecimal("phanTramGiam"));
        voucher.setNgayBatDau(rs.getDate("ngayBatDau"));
        voucher.setNgayKetThuc(rs.getDate("ngayKetThuc"));
        String trangThaiStr = rs.getString("trangThai");
        voucher.setTrangThai("Kích hoạt".equalsIgnoreCase(trangThaiStr));
        
        return voucher;
    }
}