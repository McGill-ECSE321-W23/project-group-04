package ca.mcgill.ecse321.parkinglotbackend.controller.utilities;

import jakarta.servlet.http.HttpServletRequest;

public class AuthenticationUtility {

    // HTTP Status Codes
    public static int OK = 200;
    // public static int CREATED = 201;     // dont use this: front end will always confirm success with 200
    public static int BAD_REQUEST = 400;
    public static int UNAUTHORIZED = 401;
    public static int FORBIDDEN = 403;
    public static int NOT_FOUND = 404;
    public static int INTERNAL_SERVER_ERROR = 500;

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
        return request.getSession().getAttribute("accountID") != null;
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
        return (long) Integer.parseInt(request.getSession().getAttribute("accountID").toString());
    }

    /**
     * Check if the logged in user is staff
     * @param request
     * @return boolean
     * @throws Exception
     * @author Lin Wei Li
     */
    public static boolean isStaff(HttpServletRequest request) throws Exception {
        if (!isLoggedIn(request)) {
            throw new Exception("Not logged in");
        }
        return request.getSession().getAttribute("role") == Role.STAFF || request.getSession().getAttribute("role") == Role.MANAGER;
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
        return request.getSession().getAttribute("role") == Role.MANAGER;
    }

}
