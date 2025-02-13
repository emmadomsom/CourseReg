
package CourseReg;


public class UserSession {
    private static String regNo;
    private static String name;
    
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
}
