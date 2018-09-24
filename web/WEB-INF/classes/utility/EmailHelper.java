/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author Vinayak
 */
public class EmailHelper {
    
    public static final String LINK = "http://assignment4-vkolhapu.rhcloud.com/vin_assignment4/";

    public static String getRecommendationSubject() {
        String subject = "Recommendation to join - Researcher Exchange Participants";
        return subject;
    }
    
    public static String getRecommendationBody(String userName, String message, String token){
        String body = "Hello,\n\n"
                + "Your friend " + userName + " " + "has recommended you to join and has sent this message, \n\n"
                + message + " \n\n"
                + "Researchers Exchange Participants. Please join using the following link.\n\n"
                + LINK+"signup.jsp?token="+token + "\n\n"
                + "Thank you.";
        return body;
    }
    
    public static String getContactSubject(){
        String subject = "Contact confirmation";
        return subject;        
    }
    
    public static String getContactBody(String userName){
        String body = "Hello " + userName + ",\n"
                        + "Thank you for contacting us. Our support will soon get in touch with you.";
        return body;
    }
    
    public static String getActivationBody(String token) {
        String applicationLink = LINK+"users?action=activate&token="+token;
        
        String body = "Hello,\n\n"
                + "Please click on the link below to activate your account,\n\n"
                + applicationLink + "\n\n"
                + "Thank you!!!";
        return body;
    }
    
    public static String getForgotPasswordBody(String token){
        String body = "Click on the link below to reset password,\n\n"
                    +LINK+"resetpassword.jsp?token="+token;
        return body;
    }
}
