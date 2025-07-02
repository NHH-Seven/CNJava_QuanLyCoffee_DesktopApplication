package com.example;

import java.util.*;

public class VietnameseTelexCounter {
    
    // Định nghĩa các quy tắc Telex
    private static final Map<String, String> TELEX_RULES = new LinkedHashMap<>();
    
    static {
        // Sắp xếp theo độ dài giảm dần để ưu tiên pattern dài hơn
        TELEX_RULES.put("aw", "ă");
        TELEX_RULES.put("aa", "â");
        TELEX_RULES.put("dd", "đ");
        TELEX_RULES.put("ee", "ê");
        TELEX_RULES.put("oo", "ô");
        TELEX_RULES.put("ow", "ơ");
        TELEX_RULES.put("w", "ư");
    }
    
    /**
     * Đếm số lượng chữ cái có dấu tiếng Việt có thể tạo thành từ chuỗi Latin
     * @param input Chuỗi chữ cái Latin đầu vào
     * @return Số lượng chữ cái có dấu tìm thấy
     */
    public static int countVietnameseChars(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        
        input = input.toLowerCase();
        List<String> foundChars = new ArrayList<>();
        int index = 0;
        
        while (index < input.length()) {
            boolean matched = false;
            
            // Kiểm tra các pattern từ dài nhất đến ngắn nhất
            for (String pattern : TELEX_RULES.keySet()) {
                if (index + pattern.length() <= input.length() && 
                    input.substring(index, index + pattern.length()).equals(pattern)) {
                    foundChars.add(pattern);
                    index += pattern.length();
                    matched = true;
                    break;
                }
            }
            
            if (!matched) {
                index++;
            }
        }
        
        return foundChars.size();
    }
    
    /**
     * Đếm và trả về chi tiết các chữ cái có dấu tìm thấy
     * @param input Chuỗi chữ cái Latin đầu vào
     * @return Object chứa số lượng và danh sách các chữ cái tìm thấy
     */
    public static CountResult countVietnameseCharsWithDetail(String input) {
        if (input == null || input.isEmpty()) {
            return new CountResult(0, new ArrayList<>());
        }
        
        input = input.toLowerCase();
        List<String> foundChars = new ArrayList<>();
        int index = 0;
        
        while (index < input.length()) {
            boolean matched = false;
            
            // Kiểm tra các pattern từ dài nhất đến ngắn nhất
            for (String pattern : TELEX_RULES.keySet()) {
                if (index + pattern.length() <= input.length() && 
                    input.substring(index, index + pattern.length()).equals(pattern)) {
                    foundChars.add(pattern);
                    index += pattern.length();
                    matched = true;
                    break;
                }
            }
            
            if (!matched) {
                index++;
            }
        }
        
        return new CountResult(foundChars.size(), foundChars);
    }
    
    // Class để chứa kết quả chi tiết
    public static class CountResult {
        private int count;
        private List<String> foundChars;
        
        public CountResult(int count, List<String> foundChars) {
            this.count = count;
            this.foundChars = foundChars;
        }
        
        public int getCount() {
            return count;
        }
        
        public List<String> getFoundChars() {
            return foundChars;
        }
        
        @Override
        public String toString() {
            return "Số lượng: " + count + ", Danh sách: " + foundChars;
        }
    }
    
    // Hàm main để test
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== CHƯƠNG TRÌNH ĐẾM CHỮ CÁI CÓ DẤU TIẾNG VIỆT - TELEX ===");
        System.out.println("Quy tắc Telex: ă=aw, â=aa, đ=dd, ê=ee, ô=oo, ơ=ow, ư=w");
        System.out.println();
        
        while (true) {
            System.out.print("Nhập chuỗi chữ cái Latin (hoặc 'exit' để thoát): ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Tạm biệt!");
                break;
            }
            
            if (input.isEmpty()) {
                System.out.println("Vui lòng nhập chuỗi không rỗng!");
                continue;
            }
            
            // Đếm số lượng
            int count = countVietnameseChars(input);
            
            // Lấy chi tiết
            CountResult result = countVietnameseCharsWithDetail(input);
            
            System.out.println("Input: " + input);
            System.out.println("Output: " + count + " (" + String.join(", ", result.getFoundChars()) + ")");
            System.out.println();
        }
        
        scanner.close();
        
        // Test với ví dụ trong đề bài
        System.out.println("=== TEST VỚI VÍ DỤ ===");
        String testInput = "hwfdawhwhcoomddfgwdc";
        CountResult testResult = countVietnameseCharsWithDetail(testInput);
        
        System.out.println("Input: " + testInput);
        System.out.println("Output: " + testResult.getCount() + " (" + String.join(", ", testResult.getFoundChars()) + ")");
        
        // Các test case khác
        System.out.println("\n=== CÁC TEST CASE KHÁC ===");
        String[] testCases = {
            "awaadd",
            "wwwww", 
            "aaoweeoo",
            "abcdef",
            "ddddaaawwoo"
        };
        
        for (String testCase : testCases) {
            CountResult result = countVietnameseCharsWithDetail(testCase);
            System.out.println("Input: " + testCase + " -> Output: " + result.getCount() + " (" + String.join(", ", result.getFoundChars()) + ")");
        }
    }
}