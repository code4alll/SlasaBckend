package com.ecommerce.slasa.services.email;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ecommerce.slasa.Utility.Response;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    private OTPservices optService;
    

    public Response sendSimpleMessage(String to, String subject, String text) {
    	try {
    		    MimeMessage  helper= emailSender.createMimeMessage();
    	        MimeMessageHelper message = new MimeMessageHelper(helper, true);
    	        
    	        String[] parts = text.split("_");
    	        String name = parts[0]; // First part is the name
    	        String otp = parts[1].replaceAll("\\D", ""); 
    	        String msg= text.replaceFirst(name, "").replaceFirst(otp, "").replaceAll("_", "");

    	       


    	        String mailTemplate=getTemplate3(name,msg,otp);
    	       
    	        message.setTo(to);
    	        message.setSubject(subject);
    	        message.setText(text);
    	        message.setText(mailTemplate, true);
    	        emailSender.send(helper);
    		   return new Response(true,"email send to: "+to);
        }catch (MessagingException e) {
        	e.printStackTrace();
            return new Response(false,e.getMessage());

        }catch(Exception e) {
            return new Response(false,e.getMessage());

        }
       
    }
    
    
    public Response sendMessageWithCCAndAttachment(String to, String cc, String subject, String text, File attachment)  {
        try {
        	MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setCc(cc);
            helper.setSubject(subject);
            helper.setText(text);

            if (attachment != null) {
            	
                helper.addAttachment(attachment.getName(), attachment);
            }

            emailSender.send(message);
            
            return new Response(true,"email send to: "+to);
        }catch (MessagingException e) {
            return new Response(true,e.getMessage());

        }catch(Exception e) {
        return new Response(false,e.getMessage());

    }
    	
    }
    
    
    public static String getEmailTemplate(String text) {
		return "<!DOCTYPE html>\n" +
	            "<html lang=\"en\">\n" +
	            "<head>\n" +
	            "    <meta charset=\"UTF-8\">\n" +
	            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
	            "    <title>Otp Verification</title>\n" +
	            "</head>\n" +
	            "<body style=\"font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4;\">\n" +
	            "    <div style=\"max-width: 90vw; margin: 20px auto; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n" +
	            "        "
	            + "<div style><h1 style=\"color: #333333;\">Welcome to slasa!</h1>\n" +
	            "        <p style=\"color: #666666;\">Dear User,</p>\n" +
	            "        <p style=\"color: #666666;\">We are thrilled to have you on board. Thank you for choosing slasa.com </p>\n" +
	            "        <p style=\"color: #666666;\"> "+text +" </p>\n" +
	            "        <a href=\"www.slasa.com\" style=\"display: inline-block; padding: 10px 20px; background-color: #007bff; color: #ffffff; text-decoration: none; border-radius: 5px;\">Get Started</a>\n" +
	            "        <p style=\"color: #666666;\">If you have any questions or need assistance, feel free to <a href=\"#\" style=\"color: #007bff;\">contact us</a>.</p>\n" +
	            "        <p style=\"color: #666666;\">Best Regards,<br/>Ulinkit.com</p>\n" +
	            "    <div style=\\\"max-width: 600px; margin: 20px auto; padding: 20px; background-color: #ffffff; border-radius: 20px; border:2px solid gray; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\\>"
	            + "</div>\n" +
	            "</body>\n" +
	            "</html>";
	
		
}
    
    public String getTemplate2(String text) {
        return "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout:fixed;background-color:#f9f9f9\" id=\"bodyTable\">\r\n" + 
                "    <tbody>\r\n" + 
                "        <tr>\r\n" + 
                "            <td style=\"padding-right:10px;padding-left:10px;\" align=\"center\" valign=\"top\" id=\"bodyCell\">\r\n" + 
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"wrapperWebview\" style=\"max-width:600px\">\r\n" + 
                "                    <tbody>\r\n" + 
                "                        <tr>\r\n" + 
                "                            <td align=\"center\" valign=\"top\">\r\n" + 
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
                "                                    <tbody>\r\n" + 
                "                                        <tr>\r\n" + 
                "                                            <td style=\"padding-top: 20px; padding-bottom: 20px; padding-right: 0px;\" align=\"right\" valign=\"middle\" class=\"webview\"> <a href=\"#\" style=\"color:#bbb;font-family:'Open Sans',Helvetica,Arial,sans-serif;font-size:12px;font-weight:400;font-style:normal;letter-spacing:normal;line-height:20px;text-transform:none;text-align:right;text-decoration:underline;padding:0;margin:0\" target=\"_blank\" class=\"text hideOnMobile\">Oh wait, there's more! →</a>\r\n" + 
                "                                            </td>\r\n" + 
                "                                        </tr>\r\n" + 
                "                                    </tbody>\r\n" + 
                "                                </table>\r\n" + 
                "                            </td>\r\n" + 
                "                        </tr>\r\n" + 
                "                    </tbody>\r\n" + 
                "                </table>\r\n" + 
                "                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"wrapperBody\" style=\"max-width:600px\">\r\n" + 
                "                    <tbody>\r\n" + 
                "                        <tr>\r\n" + 
                "                            <td align=\"center\" valign=\"top\">\r\n" + 
                "                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"tableCard\" style=\"background-color:#fff;border-color:#e5e5e5;border-style:solid;border-width:0 1px 1px 1px;\">\r\n" + 
                "                                    <tbody>\r\n" + 
                "                                        <tr>\r\n" + 
                "                                            <td style=\"background-color:#00d2f4;font-size:1px;line-height:3px\" class=\"topBorder\" height=\"3\">&nbsp;</td>\r\n" + 
                "                                        </tr>\r\n" + 
                "                                        <tr>\r\n" + 
                "                                            <td style=\"padding-top: 60px; padding-bottom: 20px;\" align=\"center\" valign=\"middle\" class=\"emailLogo\">\r\n" + 
                "                                                <a href=\"#\" style=\"text-decoration:none\" target=\"_blank\">\r\n" + 
                "                                                    <img alt=\"\" border=\"0\" src=\"http://email.aumfusion.com/vespro/img/hero-img/blue/logo.png\" style=\"width:100%;max-width:150px;height:auto;display:block\" width=\"150\">\r\n" + 
                "                                                </a>\r\n" + 
                "                                            </td>\r\n" + 
                "                                        </tr>\r\n" + 
                "                                        <tr>\r\n" + 
                "                                            <td style=\"padding-bottom: 20px;\" align=\"center\" valign=\"top\" class=\"imgHero\">\r\n" + 
                "                                                <a href=\"#\" style=\"text-decoration:none\" target=\"_blank\">\r\n" + 
                "                                                    <img alt=\"\" border=\"0\" src=\"http://email.aumfusion.com/vespro/img/hero-img/blue/heroGradient/user-account.png\" style=\"width:100%;max-width:600px;height:auto;display:block;color: #f9f9f9;\" width=\"600\">\r\n" + 
                "                                                </a>\r\n" + 
                "                                            </td>\r\n" + 
                "                                        </tr>\r\n" + 
                "                                        <tr>\r\n" + 
                "                                            <td style=\"padding-bottom: 5px; padding-left: 20px; padding-right: 20px;\" align=\"center\" valign=\"top\" class=\"mainTitle\">\r\n" + 
                "                                                <h2 class=\"text\" style=\"color:#000;font-family:Poppins,Helvetica,Arial,sans-serif;font-size:28px;font-weight:500;font-style:normal;letter-spacing:normal;line-height:36px;text-transform:none;text-align:center;padding:0;margin:0\">Hi \"John Doe\"</h2>\r\n" + 
                "                                            </td>\r\n" + 
                "                                        </tr>\r\n" + 
                "                                        <tr>\r\n" + 
                "                                            <td style=\"padding-bottom: 30px; padding-left: 20px; padding-right: 20px;\" align=\"center\" valign=\"top\" class=\"subTitle\">\r\n" + 
                "                                                <h4 class=\"text\" style=\"color:#999;font-family:Poppins,Helvetica,Arial,sans-serif;font-size:16px;font-weight:500;font-style:normal;letter-spacing:normal;line-height:24px;text-transform:none;text-align:center;padding:0;margin:0\">Verify Your Email Account</h4>\r\n" + 
                "                                            </td>\r\n" + 
                "                                        </tr>\r\n" + 
                "                                        <tr>\r\n" + 
                "                                            <td style=\"padding-left:20px;padding-right:20px\" align=\"center\" valign=\"top\" class=\"containtTable ui-sortable\">\r\n" + 
                "                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"tableDescription\" style=\"\">\r\n" + 
                "                                                    <tbody>\r\n" + 
                "                                                        <tr>\r\n" + 
                "                                                            <td style=\"padding-bottom: 20px;\" align=\"center\" valign=\"top\" class=\"description\">\r\n" + 
                "                                                                <p class=\"text\" style=\"color:#666;font-family:'Open Sans',Helvetica,Arial,sans-serif;font-size:14px;font-weight:400;font-style:normal;letter-spacing:normal;line-height:22px;text-transform:none;text-align:center;padding:0;margin:0\">Thanks for subscribing to the Vespro newsletter. Please click the confirm button to start receiving our emails.</p>\r\n" + 
                "                                                            </td>\r\n" + 
                "                                                        </tr>\r\n" + 
                "                                                    </tbody>\r\n" + 
                "                                                </table>\r\n" + 
                "                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"tableButton\" style=\"\">\r\n" + 
                "                                                    <tbody>\r\n" + 
                "                                                        <tr>\r\n" + 
                "                                                            <td style=\"padding-top:20px;padding-bottom:20px\" align=\"center\" valign=\"top\">\r\n" + 
                "                                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\r\n" + 
                "                                                                    <tbody>\r\n" + 
                "                                                                        <tr>\r\n" + 
                "                                                                            <td style=\"background-color: rgb(0, 210, 244); padding: 12px 35px; border-radius: 50px;\" align=\"center\" class=\"ctaButton\"> <a href=\"#\" style=\"color:#fff;font-family:Poppins,Helvetica,Arial,sans-serif;font-size:13px;font-weight:600;font-style:normal;letter-spacing:1px;line-height:20px;text-transform:uppercase;text-decoration:none;display:block\" target=\"_blank\" class=\"text\">Confirm Email</a>\r\n" + 
                "                                                                            </td>\r\n" + 
                "                                                                        </tr>\r\n" + 
                "                                                                    </tbody>\r\n" + 
                "                                                                </table>\r\n" + 
                "                                                            </td>\r\n" + 
                "                                                        </tr>\r\n" + 
                "                                                    </tbody>\r\n" + 
                "                                                </table>\r\n" + 
                "                                            </td>\r\n" + 
                "                                        </tr>\r\n" + 
                "                                        <tr>\r\n" + 
                "                                            <td style=\"font-size:1px;line-height:1px\" height=\"20\">&nbsp;</td>\r\n" + 
                "                                        </tr>\r\n" + 
                "                                    </tbody>\r\n" + 
                "                                </table>\r\n" + 
                "                            </td>\r\n" + 
                "                        </tr>\r\n" + 
                "                    </tbody>\r\n" + 
                "                </table>\r\n" + 
                "            </td>\r\n" + 
                "        </tr>\r\n" + 
                "    </tbody>\r\n" + 
                "</table>";
    }
    public String getTemplate3(String name, String text, String otp) {
    	return "<!DOCTYPE html>\r\n" + 
    			"<html lang=\"en\">\r\n" + 
    			"<head>\r\n" + 
    			"    <meta charset=\"UTF-8\">\r\n" + 
    			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
    			"    <title>Email Template</title>\r\n" + 
    			"    <style>\r\n" + 
    			"        body {\r\n" + 
    			"            font-family: Arial, sans-serif;\r\n" + 
    			"            margin: 0;\r\n" + 
    			"            padding: 0;\r\n" + 
    			"            background-color: #f0f2f5;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .container {\r\n" + 
    			"            max-width: 600px;\r\n" + 
    			"            margin: 0 auto;\r\n" + 
    			"            background-color: #ffffff;\r\n" + 
    			"            border-radius: 10px;\r\n" + 
    			"            overflow: hidden;\r\n" + 
    			"            border: 1px solid #3f51b5;\r\n" + 
    			"            box-shadow: 0 0 20px rgba(0, 0, 0, 5.1);\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .upper-part {\r\n" + 
    			"            background-color: #3f51b5;\r\n" + 
    			"            color: #ffffff;\r\n" + 
    			"            padding: 30px;\r\n" + 
    			"            text-align: center;\r\n" + 
    			"            border-bottom: 2px solid #2a3f9d;\r\n" + 
    			"            position: relative;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .lock-icon {\r\n" + 
    			"            width: 100px;\r\n" + 
    			"            height: 100px;\r\n" + 
    			"            background-image: url(https://ci3.googleusercontent.com/meips/ADKq_NZizVVkrIgojLdfS4pCTiptVCv6P4GFyEugdXxyPYf6Q4CwBL-vhmj-_qSN7y3R5EZN0NmYJhuSWM0ovnWbnJgqc978fNXmmAiU4Yujtw=s0-d-e1-ft#https://www.iconsdb.com/icons/preview/white/lock-xxl.png);\r\n" + 
    			"            background-size: cover;\r\n" + 
    			"            margin: 0 0;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .text {\r\n" + 
    			"            font-size: 20px;\r\n" + 
    			"            margin-top: 10px;\r\n" + 
    			"            font-weight: bold;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .lower-part {\r\n" + 
    			"            padding: 30px;\r\n" + 
    			"            text-align: center;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .otp-container {\r\n" + 
    			"            margin-bottom: 20px;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .otp {\r\n" + 
    			"            font-weight: bold;\r\n" + 
    			"            font-size: 30px;\r\n" + 
    			"            color: #3f51b5;\r\n" + 
    			"            background-color: #f0f2f5;\r\n" + 
    			"            padding: 15px;\r\n" + 
    			"            border-radius: 8px;\r\n" + 
    			"            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n" + 
    			"            display: inline-block;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .footer {\r\n" + 
    			"            background-color: #f0f2f5;\r\n" + 
    			"            padding: 20px;\r\n" + 
    			"            text-align: center;\r\n" + 
    			"            border-top: 2px solid #ddd;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .footer p {\r\n" + 
    			"            margin: 10px 0;\r\n" + 
    			"            font-size: 14px;\r\n" + 
    			"            color: #666;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .footer a {\r\n" + 
    			"            color: #3f51b5;\r\n" + 
    			"            text-decoration: none;\r\n" + 
    			"            font-weight: bold;\r\n" + 
    			"        }\r\n" + 
    			"\r\n" + 
    			"        .footer a:hover {\r\n" + 
    			"            text-decoration: underline;\r\n" + 
    			"        }\r\n" + 
    			"    </style>\r\n" + 
    			"</head>\r\n" + 
    			"<body>\r\n" + 
    			"    <div class=\"container\">\r\n" + 
    			"        <div class=\"upper-part\">\r\n" + 
    			"            <div class=\"lock-icon\"></div>\r\n" + 
    			"            <div class=\"text\"> <p>Hi "+name+", Welcome to slasa!</p> </div>\r\n" + 
    			"        </div>\r\n" + 
    			"        <div class=\"lower-part\">\r\n" + 
    			"            <p><b>"+text+"</b></p>\r\n" + 
    			"            <div class=\"otp-container\">\r\n" + 
    			"                <div class=\"otp\">"+otp+"</div>\r\n" + 
    			"            </div>\r\n" + 
    			"        </div>\r\n" + 
    			"        <div class=\"footer\">\r\n" + 
    			"            <p>&copy; 2024 <a href=\"https://www.slasa.com\" target=\"_blank\">slasa.com</a> All rights reserved.</p>\r\n" + 
    			"        </div>\r\n" + 
    			"    </div>\r\n" + 
    			"</body>\r\n" + 
    			"</html>\r\n" + 
    			"";
    }
}

