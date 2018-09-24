package servlets;

import business.Tempuser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import business.User;
import data.UserDB;
import java.util.UUID;
import javax.mail.MessagingException;
import utility.EmailHelper;
import utility.MailUtilGmail;
import utility.PasswordUtil;

@WebServlet(name = "UserController", urlPatterns = {"/users"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String url = null;
        String action = request.getParameter("action");
        if (action == null) {
            String host = request.getRemoteHost();
            String port = String.valueOf(request.getRemotePort());
            Cookie c1 = new Cookie("host", host);
            c1.setMaxAge(60 * 60 * 12);
            c1.setPath("/");
            response.addCookie(c1);
            Cookie c2 = new Cookie("port", port);
            c2.setMaxAge(60);
            c2.setPath("/");
            response.addCookie(c2);
            url = "/home.jsp";
        } else if (action.equals("login")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String msg = null;
            boolean flag = UserDB.validateUser(email, password);
            if (flag) {
                User user = UserDB.getUserByEmail(email);
                if (user.getType().equals("Participant")) {
                    session.setAttribute("theUser", user);
                    url = "/main.jsp";
                }
                if (user.getType().equals("Admin")) {
                    session.setAttribute("theAdmin", user);
                    url = "/admin.jsp";
                }
            } else {
                msg = "Invalid user info. Please try again.";
                request.setAttribute("loginErrorMsg", msg);
                url = "/login.jsp";
            }
        } else if (action.equals("create")) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String type = request.getParameter("type");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm_password");
            String tokenFromURL = request.getParameter("token");
            String errorMsg = null;
            if (type.equals("Participant") && password.equals(confirmPassword)) {
                if (tokenFromURL.isEmpty()) {
                    UUID uid = UUID.randomUUID();
                    String token = uid.toString();
                    java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                    Tempuser tempUser = new Tempuser();
                    tempUser.setEmail(email);
                    tempUser.setUsername(name);
                    tempUser.setToken(token);
                    tempUser.setPassword(password);
                    tempUser.setIssueDate(date);
                    UserDB.addTempUser(tempUser);
                    
                    String subject = "Research Exchange Participants Activation Email for " + name;
                    String body = EmailHelper.getActivationBody(token);
                    try {
                        MailUtilGmail.sendMail(email, subject, body, false);
                        errorMsg = "Please check your email for the link to activate your account";
                    } catch (MessagingException ex) {
                        System.err.println(ex);
                        errorMsg = "Mail sending failed!!!";
                    }
                    request.setAttribute("loginErrorMsg", errorMsg);
                    url = "/login.jsp";
                } else {
                    Tempuser tempUser = new Tempuser();
                    tempUser = UserDB.getTempUserByToken(tokenFromURL);
                    
                    User recommender = new User();
                    recommender = UserDB.getUserByEmail(tempUser.getRecommenderEmail());
                    recommender.setCoins(recommender.getCoins() + 2);
                    UserDB.updateUser(recommender);
                    
                    String salt = PasswordUtil.getSalt();
                    String hashedPassword = PasswordUtil.getSaltHashPassword(salt, password);
                    
                    User user = new User();
                    user.setUsername(name);
                    user.setUseremail(email);
                    user.setPassword(hashedPassword);
                    user.setType(type);
                    user.setParticipation(0);
                    user.setCoins(0);
                    user.setSalt(salt);
                    UserDB.addUser(user);
                    UserDB.removeTempUser(tempUser);
                    session.setAttribute("theUser", user);
                    url = "/home.jsp";
                }
                
            } else if (action.equals("how")) {
                if (session.getAttribute("theUser") != null) {
                    url = "/main.jsp";
                } else {
                    url = "/how.jsp";
                }
            }
        } else if (action.equals("activate")){
            String token = request.getParameter("token");
            Tempuser tempuser = UserDB.getTempUserByToken(token);
            String tokenFromDB = tempuser.getToken();
            String type = "Participant";
            String errorMsg = null;
            String salt = PasswordUtil.getSalt();
            String hashedPassword = PasswordUtil.getSaltHashPassword(salt, tempuser.getPassword());
            if (token.equals(tokenFromDB)){
                User user = new User();
                user.setUsername(tempuser.getUsername());
                user.setUseremail(tempuser.getEmail());
                user.setPassword(hashedPassword);
                user.setType(type);
                user.setStudies(0);
                user.setParticipation(0);
                user.setCoins(0);
                user.setSalt(salt);
                UserDB.addUser(user);
                UserDB.removeTempUser(tempuser);
                session.setAttribute("theUser", user);
                url = "/home.jsp";
            } else {
                errorMsg = "Activation token mismatch. Please click on the link in your activation email";
                request.setAttribute("loginErrorMsg", errorMsg);
                url = "/login.jsp";
            }
        } else if(action.equals("forgot")) {
            String email = request.getParameter("email");
            UUID uid = UUID.randomUUID();
            String token = uid.toString();
            String errorMsg = null;
            User user = UserDB.getUserByEmail(email);
            user.setToken(token);
            UserDB.updateUser(user);
            
            String subject = "Research Exchange Participants Forgot Password Email for " + user.getUsername();
            String body = EmailHelper.getForgotPasswordBody(token);
            try {
                MailUtilGmail.sendMail(email, subject, body, false);
                errorMsg = "Please check your email for the reset password link";
            } catch (MessagingException ex) {
                System.err.println(ex);
                errorMsg = "Forgot Password Mail sending failed!!!";
            }
            request.setAttribute("loginErrorMsg", errorMsg);
            url = "/login.jsp";
        } else if(action.equals("resetpassword")) {
            String token = request.getParameter("token");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm_password");
            String errorMsg = null;
            User user = UserDB.getUserByToken(token);
            String salt = user.getSalt();
            String hashedPassword = PasswordUtil.getSaltHashPassword(salt, password);
            if(password.equals(confirmPassword) && email.equals(user.getUseremail())){
                user.setPassword(hashedPassword);
                user.setToken(null);
                UserDB.updateUser(user);
                errorMsg = "Password Reset successfull!!! Login using your new Password";
            } else {
                errorMsg = "Passwords do not match or the user is not valid";
            }
            request.setAttribute("loginErrorMsg", errorMsg);
            url = "/login.jsp";
            
        } else if (action.equals("about")) {
            if (session.getAttribute("theUser") != null) {
                url = "/about.jsp";
            } else {
                url = "/aboutl.jsp";
            }
        } else if (action.equals("home")) {
            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else {
                url = "/home.jsp";
            }
        } else if (action.equals("main")) {
            if (session.getAttribute("theUser") != null) {
                url = "/main.jsp";
            } else {
                url = "/login.jsp";
            }
        } else if (action.equals("recommend")) {
            String userEmail = request.getParameter("email");
            String friendEmail = request.getParameter("friend_email");
            User sessionUser = (User) session.getAttribute("theUser");
            String message = request.getParameter("message");
            String errorMsg = null;
            UUID uid = UUID.randomUUID();
            String token = uid.toString();
            Tempuser tempuser = new Tempuser();
            if (sessionUser.getUseremail().equals(userEmail)) {
                String subject = EmailHelper.getRecommendationSubject();
                String body = EmailHelper.getRecommendationBody(sessionUser.getUsername(), message, token);
                
                tempuser.setEmail(friendEmail);
                tempuser.setRecommenderEmail(userEmail);
                tempuser.setToken(token);
                UserDB.addTempUser(tempuser);
                try {
                    MailUtilGmail.sendMail(friendEmail, subject, body, false);
                    url = "/confirmr.jsp";
                } catch (MessagingException ex) {
                    errorMsg = "Unable to send email";
                    request.setAttribute("errorMsg", errorMsg);
                    url = "/recommend.jsp";
                }
            } else {
                errorMsg = "User Email is not the same as that of the logged in user...";
                request.setAttribute("errorMsg", errorMsg);
                url = "/recommend.jsp";
            }

        } else if (action.equals("contact")) {
            String userEmail = request.getParameter("email");
            User sessionUser = (User) session.getAttribute("theUser");
            String errorMsg = null;
            if (sessionUser.getUseremail().equals(userEmail)) {
                String subject = EmailHelper.getContactSubject();
                String body = EmailHelper.getContactBody(sessionUser.getUsername());
                try {
                    MailUtilGmail.sendMail(userEmail, subject, body, false);
                    url = "/confirmc.jsp";
                } catch (MessagingException ex) {
                    errorMsg = "Unable to send email";
                    request.setAttribute("errorMsg", errorMsg);
                    url = "/contact.jsp";
                }
            } else {
                errorMsg = "User Email is not the same as that of the logged in user...";
                request.setAttribute("errorMsg", errorMsg);
                url = "/contact.jsp";
            }

        } else if (action.equals("logout")) {
            if (session.getAttribute("theUser") != null || session.getAttribute("theAdmin") != null) {
                session.invalidate();
                url = "/home.jsp";
            } else {
                url = "/home.jsp";
            }
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
