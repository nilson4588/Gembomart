package com.rest.utility;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplate {

	 
     public static String prepareCRMEmailTemplate(long username, String password, String url) {
		 
		 StringBuilder emailFormat = new StringBuilder();
		 emailFormat.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
		 		"<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head>\n" + 
		 		"<!--[if gte mso 9]><xml>\n" + 
		 		"<o:OfficeDocumentSettings>\n" + 
		 		"<o:AllowPNG/>\n" + 
		 		"<o:PixelsPerInch>96</o:PixelsPerInch>\n" + 
		 		"</o:OfficeDocumentSettings>\n" + 
		 		"</xml><![endif]-->\n" + 
		 		"<title>Real Estate CRM</title>\n" + 
		 		"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" + 
		 		"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" + 
		 		"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0 \">\n" + 
		 		"<meta name=\"format-detection\" content=\"telephone=no\">\n" + 
		 		"<!--[if !mso]><!-->\n" + 
		 		"<link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800\" rel=\"stylesheet\">\n" + 
		 		"<!--<![endif]-->\n" + 
		 		"<style type=\"text/css\">\n" + 
		 		"body {\n" + 
		 		"	margin: 0 !important;\n" + 
		 		"	padding: 0 !important;\n" + 
		 		"	-webkit-text-size-adjust: 100% !important;\n" + 
		 		"	-ms-text-size-adjust: 100% !important;\n" + 
		 		"	-webkit-font-smoothing: antialiased !important;\n" + 
		 		"}\n" + 
		 		"img {\n" + 
		 		"	border: 0 !important;\n" + 
		 		"	outline: none !important;\n" + 
		 		"}\n" + 
		 		"p {\n" + 
		 		"	Margin: 0px !important;\n" + 
		 		"	Padding: 0px !important;\n" + 
		 		"}\n" + 
		 		"table {\n" + 
		 		"	border-collapse: collapse;\n" + 
		 		"	mso-table-lspace: 0px;\n" + 
		 		"	mso-table-rspace: 0px;\n" + 
		 		"}\n" + 
		 		"td, a, span {\n" + 
		 		"	border-collapse: collapse;\n" + 
		 		"	mso-line-height-rule: exactly;\n" + 
		 		"}\n" + 
		 		".ExternalClass * {\n" + 
		 		"	line-height: 100%;\n" + 
		 		"}\n" + 
		 		".em_defaultlink a {\n" + 
		 		"	color: inherit !important;\n" + 
		 		"	text-decoration: none !important;\n" + 
		 		"}\n" + 
		 		"span.MsoHyperlink {\n" + 
		 		"	mso-style-priority: 99;\n" + 
		 		"	color: inherit;\n" + 
		 		"}\n" + 
		 		"span.MsoHyperlinkFollowed {\n" + 
		 		"	mso-style-priority: 99;\n" + 
		 		"	color: inherit;\n" + 
		 		"}\n" + 
		 		" @media only screen and (min-width:481px) and (max-width:699px) {\n" + 
		 		".em_main_table {\n" + 
		 		"	width: 100% !important;\n" + 
		 		"}\n" + 
		 		".em_wrapper {\n" + 
		 		"	width: 100% !important;\n" + 
		 		"}\n" + 
		 		".em_hide {\n" + 
		 		"	display: none !important;\n" + 
		 		"}\n" + 
		 		".em_img {\n" + 
		 		"	width: 100% !important;\n" + 
		 		"	height: auto !important;\n" + 
		 		"}\n" + 
		 		".em_h20 {\n" + 
		 		"	height: 20px !important;\n" + 
		 		"}\n" + 
		 		".em_padd {\n" + 
		 		"	padding: 20px 10px !important;\n" + 
		 		"}\n" + 
		 		"}\n" + 
		 		"@media screen and (max-width: 480px) {\n" + 
		 		".em_main_table {\n" + 
		 		"	width: 100% !important;\n" + 
		 		"}\n" + 
		 		".em_wrapper {\n" + 
		 		"	width: 100% !important;\n" + 
		 		"}\n" + 
		 		".em_hide {\n" + 
		 		"	display: none !important;\n" + 
		 		"}\n" + 
		 		".em_img {\n" + 
		 		"	width: 100% !important;\n" + 
		 		"	height: auto !important;\n" + 
		 		"}\n" + 
		 		".em_h20 {\n" + 
		 		"	height: 20px !important;\n" + 
		 		"}\n" + 
		 		".em_padd {\n" + 
		 		"	padding: 20px 10px !important;\n" + 
		 		"}\n" + 
		 		".em_text1 {\n" + 
		 		"	font-size: 16px !important;\n" + 
		 		"	line-height: 24px !important;\n" + 
		 		"}\n" + 
		 		"u + .em_body .em_full_wrap {\n" + 
		 		"	width: 100% !important;\n" + 
		 		"	width: 100vw !important;\n" + 
		 		"}\n" + 
		 		"}\n" + 
		 		"</style>\n" + 
		 		"</head>\n" + 
		 		"\n" + 
		 		"<body class=\"em_body\" style=\"margin:0px; padding:0px;\" bgcolor=\"#efefef\">\n" + 
		 		"<table class=\"em_full_wrap\" valign=\"top\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" bgcolor=\"#efefef\" align=\"center\">\n" + 
		 		"  <tbody><tr>\n" + 
		 		"    <td valign=\"top\" align=\"center\"><table class=\"em_main_table\" style=\"width:700px;\" width=\"700\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" + 
		 		"        <!--Header section-->\n" + 
		 		"        <tbody><tr>\n" + 
		 		"          <td style=\"padding:15px;\" class=\"em_padd\" valign=\"top\" bgcolor=\"#f6f7f8\" align=\"center\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" + 
		 		"              <tbody><tr>\n" + 
		 		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:12px; line-height:15px; color:#0d1121;\" valign=\"top\" align=\"center\"> \n" + 
		 		"                <img class=\"em_img\" alt=\"Gembomart\" style=\"display:block; font-family:Arial, sans-serif; font-size:30px; line-height:34px; color:#000000; max-width:700px;\" src=\"https://gembomart.com/img/assets/images/product/mfstore/logo.png\" >\n" + 
		 		
		 		"                </td>\n" + 
		 		"              </tr>\n" + 
		 		"            </tbody></table></td>\n" + 
		 		"        </tr>\n" + 
		 		"        <!--//Header section--> \n" + 
		 		"        <!--Banner section-->\n" + 
		 		"        <tr>\n" + 
		 		"          <td valign=\"top\" align=\"center\" bgcolor=\"#fbeb59\">\n" + 
		 		"           <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" + 
		 		"              <tbody><tr> \n" + 
		 		"                <td valign=\"top\" align=\"center\">\n" + 
		 		
		 		"                 <h1 style=\"color:#0d1121;\" width=\"700\" border=\"0\" height=\"345\" >GemboMart Tradelink Pvt. Ltd.</h1>\n" + 
		 		"                 </td>\n" + 
		 		"              </tr>\n" + 
		 		"            </tbody></table></td>\n" + 
		 		"        </tr>\n" + 
		 		"        <!--//Banner section--> \n" + 
		 		"        <!--Content Text Section-->\n" + 
		 		"                 <tr>\n" + 
		 		"          <td style=\"padding:35px 70px 30px;\" class=\"em_padd\" valign=\"top\" bgcolor=\"#0d1121\" align=\"center\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" + 
		 		"              <tbody> <!--<tr>\n" + 
		 		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:18px; line-height:30px; color:#ffffff;\" valign=\"top\" align=\"center\">You’ve received the following order from :"+ ".</td>\n" + 
		 		"              </tr>-->\n" + 
		 		"             <!--  <tr>\n" + 
		 		"                <td style=\"font-size:0px; line-height:0px; height:15px;\" height=\"15\">&nbsp;</td>\n" + 
		 		"\n" + 
		 		"              </tr> -->\n" + 
		 		"             <!--  <tr>\n" + 
		 		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:16px; line-height:22px; color:#fbeb59; letter-spacing:2px; padding-bottom:12px;\" valign=\"top\" align=\"center\">With clients being the backbone of your business, it’s imperative that you, as real estate developer, stay on your toes in anticipating\n" + 
		 		"their needs better, establishing consistency in maintaining your relationship with them and delivering speedily.</td>\n" + 
		 		"              </tr> -->\n" + 
		 		"              <tr>\n" + 
		 		"                <td class=\"em_h20\" style=\"font-size:0px; line-height:0px; height:25px;\" height=\"25\">&nbsp;</td>\n" + 
		 		"<!--—this is space of 25px to separate two paragraphs ---->\n" + 
		 		"              </tr>\n" + 
		 		"               <tr>\n" + 
		 		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:16px; line-height:22px; color:#fbeb59; text-transform:uppercase; letter-spacing:2px; padding-bottom:12px;\" valign=\"top\" align=\"center\"> <b> Gembomart </b> helps you serve your customers better by providing you with a seamless experience across the client lifecycle.</td>\n" + 
		 		"              </tr>\n" + 
		 		"               <tr>\n" + 
		 		"                <td class=\"em_h20\" style=\"font-size:0px; line-height:0px; height:25px;\" height=\"25\">&nbsp;</td>\n" + 
		 		"<!--—this is space of 25px to separate two paragraphs ---->\n" + 
		 		"              </tr>\n" + 
		 		"              <tr>\n" + 
		 		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:16px; line-height:22px; color:#fbeb59; letter-spacing:2px; padding-bottom:12px;\" valign=\"top\" align=\"center\">\n" + 
		 		"                   <b style=\"color:white;\">Let's have a tour of system</b> <br>\n" + 
		 		"                   Click here <a href="+url+" target=\"_blank\"  ><b style=\"color:white;\">GEMBOMART.COM</b></a> <br>\n" + 
		 		"                   username:  <a style=\"color:white; text-decoration:none;\"><b>"+username+"</b></a> <br>\n" + 
		 		"                   password: <b style=\"color:white;\">"+password+"</b>\n" + 
		 		"                </td>\n" + 
		 		"              </tr>\n" + 
		 		"            </tbody></table></td>\n" + 
		 		"        </tr>\n" + 
		 		"\n" + 
		 		"        <!--//Content Text Section--> \n" + 
		 		"        <!--Footer Section-->\n" + 
		 		"        <tr>\n" + 
		 		"          <td style=\"padding:38px 30px;\" class=\"em_padd\" valign=\"top\" bgcolor=\"#f6f7f8\" align=\"center\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" + 
		 		"              <tbody><!-- <tr>\n" + 
		 		"                <td style=\"padding-bottom:16px;\" valign=\"top\" align=\"center\"><table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" + 
		 		"                    <tbody><tr>\n" + 
		 		"                      <td valign=\"top\" align=\"center\"><a href=\"#\" target=\"_blank\" style=\"text-decoration:none;\"><img src=\"images/fb.png\" alt=\"fb\" style=\"display:block; font-family:Arial, sans-serif; font-size:14px; line-height:14px; color:#ffffff; max-width:26px;\" width=\"26\" border=\"0\" height=\"26\"></a></td>\n" + 
		 		"                      <td style=\"width:6px;\" width=\"6\">&nbsp;</td>\n" + 
		 		"                      <td valign=\"top\" align=\"center\"><a href=\"#\" target=\"_blank\" style=\"text-decoration:none;\"><img src=\"images/tw.png\" alt=\"tw\" style=\"display:block; font-family:Arial, sans-serif; font-size:14px; line-height:14px; color:#ffffff; max-width:27px;\" width=\"27\" border=\"0\" height=\"26\"></a></td>\n" + 
		 		"                      <td style=\"width:6px;\" width=\"6\">&nbsp;</td>\n" + 
		 		"                      <td valign=\"top\" align=\"center\"><a href=\"#\" target=\"_blank\" style=\"text-decoration:none;\"><img src=\"images/yt.png\" alt=\"yt\" style=\"display:block; font-family:Arial, sans-serif; font-size:14px; line-height:14px; color:#ffffff; max-width:26px;\" width=\"26\" border=\"0\" height=\"26\"></a></td>\n" + 
		 		"                    </tr>\n" + 
		 		"                  </tbody></table></td>\n" + 
		 		"              </tr> -->\n" + 
		 		"              <tr>\n" + 
		 		"                <td style=\"font-family:'Open Sans', Arial, sans-serif; font-size:11px; line-height:18px; color:#999999;\" valign=\"top\" align=\"center\">\n" + 
		 		"             <!--    <a href=\"#\" target=\"_blank\" style=\"color:#999999; text-decoration:underline;\">PRIVACY STATEMENT</a> | <a href=\"#\" target=\"_blank\" style=\"color:#999999; text-decoration:underline;\">TERMS OF SERVICE</a> | <a href=\"#\" target=\"_blank\" style=\"color:#999999; text-decoration:underline;\">RETURNS</a><br>\n" + 
		 		"              -->     � 2021 GemboMart Tradelink Pvt. Ltd. All Rights Reserved.<br>\n" + 
		 		"              Soham Tower, Ring Road, Near Tukaram Hospital, Keshav Nagar,  <br> Akola, Maharashtra 444 004.<br>\n" + 
		 		"Phone:  +91-848-484-8781 <br>\n" + 
		 		"E-mail: gembomart@gmail.com<br>\n" + 
		 		"Website: gembomart.com\n" + 
		 		"              <!--     If you do not wish to receive any further emails from us, please <a href=\"#\" target=\"_blank\" style=\"text-decoration:none; color:#999999;\">unsubscribe</a></td>\n" + 
		 		"               --></tr>\n" + 
		 		"            </tbody></table></td>\n" + 
		 		"        </tr>\n" + 
		 		"        <tr>\n" + 
		 		"          <td class=\"em_hide\" style=\"line-height:1px;min-width:700px;background-color:#ffffff;\"><img alt=\"\" src=\"images/spacer.gif\" style=\"max-height:1px; min-height:1px; display:block; width:700px; min-width:700px;\" width=\"700\" border=\"0\" height=\"1\"></td>\n" + 
		 		"        </tr>\n" + 
		 		"      </tbody></table></td>\n" + 
		 		"  </tr>\n" + 
		 		"</tbody></table>\n" + 
		 		"<div class=\"em_hide\" style=\"white-space: nowrap; display: none; font-size:0px; line-height:0px;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</div>\n" + 
		 		"</body></html>");
		 
		 return emailFormat.toString();
	 }
     
     
     public static String prepareTemEmailTemplate(String email, String password) {
		 
		 StringBuilder emailFormat = new StringBuilder();
		 emailFormat.append("<html>\n" + 
			 		"   <head>\n" + 
			 		"      <style>\n" + 
			 		"         .banner-color {\n" + 
			 		"         background-color: #a361e5;\n" + 
			 		"         }\n" + 
			 		"         .title-color {\n" + 
			 		"         color: #a361e5;\n" + 
			 		"         }\n" + 
			 		"         .button-color {\n" + 
			 		"         background-color: #a361e5;\n" + 
			 		"         }\n" + 
			 		"         \n" + 
			 		"         @media screen and (min-width: 500px) {\n" + 
			 		"         .banner-color {\n" + 
			 		"         background-color: #a361e5;\n" + 
			 		"         }\n" + 
			 		"         .title-color {\n" + 
			 		"         color: #a361e5;\n" + 
			 		"         }\n" + 
			 		"         .button-color {\n" + 
			 		"         background-color: #a361e5;\n" + 
			 		"         }\n" + 
			 		"         }\n" + 
			 		"      </style>\n" + 
			 		"   </head>\n" + 
			 		"   <body>\n" + 
			 		"      <div style=\"background-color:#ececec;padding:0;margin:0 auto;font-weight:200;width:100%!important\">\n" + 
			 		"         <table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"table-layout:fixed;font-weight:200;font-family:Helvetica,Arial,sans-serif\" width=\"100%\">\n" + 
			 		"            <tbody>\n" + 
			 		"               <tr>\n" + 
			 		"                  <td align=\"center\">\n" + 
			 		"                     <center style=\"width:100%\">\n" + 
					"                        <table bgcolor=\"#FFFFFF\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin:0 auto;max-width:512px;font-weight:200;width:inherit;font-family:Helvetica,Arial,sans-serif\" >\n"
					+ /*
						 * width=\"512\"
						 */			 		"                           <tbody>\n" +                          
			 		"                              <tr>\n" + 
			 		"                                 <td align=\"left\">\n" + 
			 		"                                    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-weight:200;font-family:Helvetica,Arial,sans-serif\" width=\"100%\">\n" + 
			 		"                                       <tbody>\n" + 
			 		"                                          <tr>\n" + 
			 		"                                             <td width=\"100%\">\n" + 
			 		"                                                <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-weight:200;font-family:Helvetica,Arial,sans-serif\" width=\"100%\">\n" + 
			 		"                                                   <tbody>\n" + 
			 		"                                                      <tr>\n" + 
			 		"                                                         <td align=\"center\" bgcolor=\"#8448c1\" style=\"padding:20px 48px;color:#ffffff\" class=\"banner-color\">\n" + 
			 		"                                                            <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-weight:200;font-family:Helvetica,Arial,sans-serif\" width=\"100%\">\n" + 
			 		"                                                               <tbody>\n" + 
			 		"                                                                  <tr>\n" + 
			 		"                                                                     <td align=\"center\" width=\"100%\">\n" + 
			 		"                                                                        <h1 style=\"padding:0;margin:0;color:#ffffff;font-weight:500;font-size:20px;line-height:24px\">The Education Match </h1>\n" + 
			 		"                                                                     </td>\n" + 
			 		"                                                                  </tr>\n" + 
			 		"                                                               </tbody>\n" + 
			 		"                                                            </table>\n" + 
			 		"                                                         </td>\n" + 
			 		"                                                      </tr>\n" + 
			 		"                                                      <tr>\n" + 
			 		"                                                         <td align=\"center\" style=\"padding:20px 0 10px 0\">\n" + 
			 		"                                                            <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-weight:200;font-family:Helvetica,Arial,sans-serif\" width=\"100%\">\n" + 
			 		"                                                               <tbody>\n" + 
			 		"                                                                  <tr>\n" + 
			 		"                                                                     <td align=\"center\" width=\"100%\" style=\"padding: 0 15px;text-align: justify;color: rgb(76, 76, 76);font-size: 12px;line-height: 18px;\">\n" + 
			 		"                                                                        <h3 style=\"padding: 0px; margin: 0px; font-size: 16px; line-height: 24px; text-align: left;\">Hello,</h3>\n" + 
			 		"                                                                        <p style=\"margin:  0px 0 30px 0;font-size: 15px;text-align: left;\">Thank you for registering with The Education Match. Sharing with you login details</p>\n" + 
			 		"                                                                                             \n" + 
			 		"                                                                        <p style=\"font-size: 13px;text-align: left;\"><b style=\"font-weight: bold;\">URL: </b>https://theeducationmatch.com/tem/student/login <br><b style=\"font-weight: bold;\">Email : </b>"+email+"<br><b style=\"font-weight: bold;\">Password : </b>"+ password+"</p>\n" + 
			 		"                                                                       \n" + 	                                                                   
			 		"                                                                         <p style=\"font-size: 10px;text-align: center;\">Caution : Do not share your password with anyone. This can be misused.</p> \n" + 
			 		"                                                                       \n" + 
			 		"                                                                     </td>\n" + 
			 		"                                                                  </tr>\n" + 
			 		"                                                               </tbody>\n" + 
			 		"                                                            </table>\n" + 
			 		"                                                         </td>\n" + 
			 		"                                                      </tr>\n" + 
			 		"                                                      <tr>\n" + 
			 		"                                                      </tr>\n" + 
			 		"                                                      <tr>\n" + 
			 		"                                                      </tr>\n" + 
			 		"                                                   </tbody>\n" + 
			 		"                                                </table>\n" + 
			 		"                                             </td>\n" + 
			 		"                                          </tr>\n" + 
			 		"                                       </tbody>\n" + 
			 		"                                    </table>\n" + 
			 		"                                 </td>\n" + 
			 		"                              </tr>\n" + 
			 		"                              <tr>\n" + 
			 		"                                 <td align=\"left\">\n" + 
			 		"                                    <table bgcolor=\"#FFFFFF\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"padding:0 24px;color:#999999;font-weight:200;font-family:Helvetica,Arial,sans-serif\" width=\"100%\">\n" + 
			 		"                                       <tbody>\n" + 
			 		"                                          <tr>\n" + 
			 		"                                             <td align=\"center\" width=\"100%\">\n" + 
			 		"                                                <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-weight:200;font-family:Helvetica,Arial,sans-serif\" width=\"100%\">\n" + 
			 		"                                                   <tbody>\n" + 
			 		"                                                      <tr>\n" + 
			 		"                                                         <td align=\"center\" valign=\"middle\" width=\"100%\" style=\"border-top:1px solid #d9d9d9;padding:12px 0px 20px 0px;text-align:center;color:#4c4c4c;font-weight:200;font-size:12px;line-height:18px\">Regards,\n" + 
			 		"                                                            <br><b>The Education Match</b>\n" + 
			 		"                                                         </td>\n" + 
			 		"                                                      </tr>\n" + 
			 		"                                                   </tbody>\n" + 
			 		"                                                </table>\n" + 
			 		"                                             </td>\n" + 
			 		"                                          </tr>\n" + 
			 		"                                          <tr>\n" + 
			 		"                                             <td align=\"center\" width=\"100%\">\n" + 
			 		"                                                <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-weight:200;font-family:Helvetica,Arial,sans-serif\" width=\"100%\">\n" + 
			 		"                                                   <tbody>\n" + 
			 		"                                                      <tr>\n" + 
			 		"                                                         <td align=\"center\" style=\"padding:0 0 8px 0\" width=\"100%\"></td>\n" + 
			 		"                                                      </tr>\n" + 
			 		"                                                   </tbody>\n" + 
			 		"                                                </table>\n" + 
			 		"                                             </td>\n" + 
			 		"                                          </tr>\n" + 
			 		"                                       </tbody>\n" + 
			 		"                                    </table>\n" + 
			 		"                                 </td>\n" + 
			 		"                              </tr>\n" + 
			 		"                           </tbody>\n" + 
			 		"                        </table>\n" + 
			 		"                     </center>\n" + 
			 		"                  </td>\n" + 
			 		"               </tr>\n" + 
			 		"            </tbody>\n" + 
			 		"         </table>\n" + 
			 		"      </div>\n" + 
			 		"   </body>\n" + 
			 		"</html>");
			 
			 return emailFormat.toString();		
	 }     
     
    public static String prepareUpdatedTemEmailTemplate(String email, String password, String name) {
		 
		 StringBuilder emailFormat = new StringBuilder();
		 emailFormat.append("<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\"><b>Hello Admin,</b></p>\r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">Thank you for registering on The Education Match. We are glad to confirm your free unbiased education counselling slot and career discovery assessment from us.</p> \r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">The event is live from 5th April to 11th April 2021. The timings are 10 am to 7 pm.</p> \r\n"
		 		+ "\r\n"
		 		+ "</br>\r\n"
		 		+ " <p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;text-align: left;\"><b style=\"font-weight: bold;\">URL: </b>https://theeducationmatch.com/tem/student/login <br><b style=\"font-weight: bold;\">Username : </b>"+email+"<br><b style=\"font-weight: bold;\">Password : </b>"+password+"</p>\r\n"
		 		+ "</br>\r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">We request you to please call on +91 9819706178  to confirm your appointment. Please do note limited slots are available at one time.</p> \r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">How will The Education Match help you?</p>\r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">We understand choosing education has never been so chaotic. The complexities, the permutations & combinations are increasing by the passing term. We are expected by large to make our career decisions around the age of 15 & 17.</p>\r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-weight: bold;font-size: 13px;\">Can we make a career out of the things that seem likeable and interesting?<br>\r\n"
		 		+ "What if we loose interest as we grow older?<br>\r\n"
		 		+ "Will I get paid right for it? How will my social life be?<br>\r\n"
		 		+ "There are so many endless questions.</p>\r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">Let's look at the brighter side. The options available in education are far so many today than the past. If you know what you want, you can choose a much relevant education and learn specifics at much younger age. The newer education providers has given a rise to a debate between the age old formal structured education and the new age industry specific learning based education.</p> \r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">There was a time that specialisations mostly began after graduation. Now we have the curse and the boon to choose much earlier.</p>\r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-weight: bold;font-size: 13px;\">The Education Match would like to step in.</p>\r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">The Education Match considers your likes, dislikes, your education till date and your aspirations</p> \r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">One of the most finest career discovery test the country has to offer, analyses your aptitude, personality, your subconscious lean & helps you match career options.</p> \r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">Thirdly, and most importantly we connect you one-to-one with a professional unbiased education career expert who will answer all your queries.</p> \r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-size: 13px;\">We aim to help every school going to a post graduation seeking student to make the right career choice and guide them in the options available and the path to reach their career.</p> \r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-weight: bold;;font-size: 13px;\">Please book your slot at the earliest</p> \r\n"
		 		+ "\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-weight: bold;;font-size: 13px;\">Regards,</p>\r\n"
		 		+ "<p style=\"font-family:Helvetica,Arial,sans-serif;font-weight: bold;;font-size: 13px;\">The Education Match</p>");
			 
			 return emailFormat.toString();		
	 } 
}
