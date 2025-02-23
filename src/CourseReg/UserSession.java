
package CourseReg;


public class UserSession {
    private static String regNo;
    private static String name;
    private static String level;
    
    public static void setRegNo(String regno) {
        if (regno != null && !regno.trim().isEmpty()) {
            regNo = regno;
        }
    }
    
    public static String getRegNo() {
        return regNo != null ? regNo : "";
    }
    
    public static void setName(String userName) {
        name = userName;
    }

    public static String getName() {
        return name != null ? name : "";
    }
    public static void setLevel(String userLevel) {  // Added setLevel method
        level = userLevel;
    }
    public static String getLevel() {  // Added getLevel method
        return level != null ? level : "";
    }
}
