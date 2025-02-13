
package CourseReg;


public class RegistrationSettings {
    private static RegistrationSettings instance;
    private String currentSession;
    private String currentSemester;
    private boolean isRegistrationOpen;
    
    private RegistrationSettings() {
        isRegistrationOpen = false;
    }
    
    public static RegistrationSettings getInstance() {
        if (instance == null) {
            instance = new RegistrationSettings();
        }
        return instance;
    }
    
    public void setRegistrationMode(String session, String semester) {
        this.currentSession = session;
        this.currentSemester = semester;
        this.isRegistrationOpen = true;
    }
    
    public boolean isValidRegistrationPeriod(String session, String semester) {
        return isRegistrationOpen && 
               currentSession.equals(session) && 
               currentSemester.equals(semester);
    }
    
    public void closeRegistration() {
        this.isRegistrationOpen = false;
    }
    
}
