package ca.mcgill.ecse321.parkinglotbackend.controller.utilities;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class AuthenticationUtility {

    // HTTP Status Codes
    public static int UNAUTHORIZED = 401;
    public static int FORBIDDEN = 403;

    // Role enum
    public enum Role {
        CUSTOMER, STAFF, MANAGER
    }

    /**
     * Check if the user is logged in
     * @param request
     * @return boolean
     * @author Lin Wei Li
     */
    public static boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("accountID") != null;
    }

    /**
     * Get the account id of the logged in user
     * @param request
     * @return long
     * @throws Exception
     * @author Lin Wei Li
     */
    public static long getAccountId(HttpServletRequest request) throws Exception {
        if (!isLoggedIn(request)) {
            throw new Exception("Not logged in");
        }
        return (long) Integer.parseInt(request.getSession(false).getAttribute("accountID").toString());
    }

    /**
     * Check if the logged in user is staff (or manager)
     * @param request
     * @return boolean
     * @throws Exception
     * @author Lin Wei Li
     */
    public static boolean isStaff(HttpServletRequest request) throws Exception {
        if (!isLoggedIn(request)) {
            throw new Exception("Not logged in");
        }
        return request.getSession(false).getAttribute("role") == Role.STAFF || request.getSession(false).getAttribute("role") == Role.MANAGER;
    }

    /**
     * Check if the logged in user is the manager
     * @param request
     * @return boolean
     * @throws Exception
     * @author Lin Wei Li
     */
    public static boolean isManager(HttpServletRequest request) throws Exception {
        if (!isLoggedIn(request)) {
            throw new Exception("Not logged in");
        }
        return request.getSession(false).getAttribute("role") == Role.MANAGER;
    }

}