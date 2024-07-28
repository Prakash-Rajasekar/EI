public class UserSessionManager {
    private static UserSessionManager instance;
    private String currentUserSession;

    private UserSessionManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized UserSessionManager getInstance() {
        if (instance == null) {
            instance = new UserSessionManager();
        }
        return instance;
    }

    public String getCurrentUserSession() {
        return currentUserSession;
    }

    public void setCurrentUserSession(String session) {
        this.currentUserSession = session;
    }
}
