
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Lớp tiện ích giúp mã hóa và xác thực mật khẩu
 * 
 * @author zhieu
 */
public class HashUtil {
    
    private static final int SALT_LENGTH = 16; // Độ dài muối (salt) trong bytes
    private static final String HASH_ALGORITHM = "SHA-256"; // Thuật toán băm
    
    /**
     * Tạo một chuỗi muối (salt) ngẫu nhiên
     * @return Chuỗi muối dạng byte array
     */
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }
    
    /**
     * Tạo mã băm từ mật khẩu và muối
     * @param password Mật khẩu cần băm
     * @param salt Muối để băm
     * @return Mã băm dạng byte array
     */
    private static byte[] hash(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
            md.update(salt);
            return md.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Lỗi khi băm mật khẩu", e);
        }
    }
    
    /**
     * Mã hóa mật khẩu với muối ngẫu nhiên
     * @param password Mật khẩu cần mã hóa
     * @return Mật khẩu đã mã hóa (định dạng: base64(salt):base64(hash))
     */
    public static String hashPassword(String password) {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hash(password, salt);
        
        // Chuyển salt và hash thành chuỗi Base64
        String saltStr = Base64.getEncoder().encodeToString(salt);
        String hashStr = Base64.getEncoder().encodeToString(hashedPassword);
        
        // Kết hợp salt và hash với dấu hai chấm làm phân cách
        return saltStr + ":" + hashStr;
    }
    
    /**
     * Xác thực mật khẩu với mật khẩu đã mã hóa
     * @param password Mật khẩu cần xác thực
     * @param storedPassword Mật khẩu đã mã hóa từ cơ sở dữ liệu
     * @return true nếu mật khẩu đúng, false nếu sai
     */
    public static boolean verifyPassword(String password, String storedPassword) {
        try {
            // Tách salt và hash từ chuỗi đã lưu
            String[] parts = storedPassword.split(":");
            if (parts.length != 2) {
                return false; // Định dạng không đúng
            }
            
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] storedHash = Base64.getDecoder().decode(parts[1]);
            
            // Tạo hash mới từ mật khẩu nhập vào và salt đã lưu
            byte[] newHash = hash(password, salt);
            
            // So sánh hash mới với hash đã lưu
            if (newHash.length != storedHash.length) {
                return false;
            }
            
            // So sánh từng byte
            for (int i = 0; i < newHash.length; i++) {
                if (newHash[i] != storedHash[i]) {
                    return false;
                }
            }
            
            return true;
        } catch (Exception e) {
            // Có lỗi xảy ra trong quá trình xác thực
            return false;
        }
    }
}