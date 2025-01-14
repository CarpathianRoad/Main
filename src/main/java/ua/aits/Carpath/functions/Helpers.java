/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.aits.Carpath.functions;

import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.jsoup.Jsoup;
import ua.aits.Carpath.model.MenuModel;

/**
 *
 * @author kiwi
 */
public class Helpers extends FileMethods {
    
	MenuModel menu = new MenuModel();
    
	public static String html2text(String html) {
    	return Jsoup.parse(html).text();
	}
    
	public static List<String> listFilesForFolder(final File folder) {
    	List<String> imageList = new LinkedList<>();
    	for (final File fileEntry : folder.listFiles()) {
        	if (fileEntry.isDirectory()) {
            	listFilesForFolder(fileEntry);
        	} else {
            	imageList.add(fileEntry.getName());
            	System.out.println(fileEntry.getName());
        	}
    	}
    	return imageList;
	}
	public String getRowHtml(String lang, String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	List<MenuModel> tempMenu = menu.getMenuRow(lang, id);
    	String clas = "";
    	String clas2 ="";
    	if("0".equals(id)){
        	clas2 = "DropDownMenu";
    	}
    	if(tempMenu.size() > 0){
   	 
    	MenuModel first = tempMenu.get(0);
    	if(first.level == 2){
        	clas = "menuLine";
    	}
    	}
    	String html = "<ul class=\""+clas2+"\">\n<div class=\""+clas+"\"></div>" ;
            	for(MenuModel temp : tempMenu) {
                	String idhtml = "";
                	String disableClass = "";
                	String tempUrl = "article/category/"+temp.id;
                	if(!"".equals(temp.url) && temp.url != null){
                    	tempUrl = temp.url;
                	}
                	if("disable".equals(temp.url)) {
                    	disableClass = "disable-menu-link";
                	}
                	if("0".equals(id)){
                    	idhtml = "<div id=\""+temp.htmlID+"\"></div>";
                	}
                	html = html + "<li><a href=\""+Constants.URL+tempUrl+"\" class=\"" + disableClass + "\"><div class=\""+temp.caret+ " " + disableClass + "\"></div>"+temp.titleEN+idhtml+"</a>";
                	html = html + this.getRowHtml(lang, temp.id.toString());
                	html = html + "</li>";
            	}
            	html = html + "</ul>";
    	return html;
	}
	public String getRowHtmlSelect(String lang, String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	List<MenuModel> tempMenu = menu.getMenuRowSelect(lang, id);
    	String html = null;
    	if("0".equals(id)) {
        	html = "<label id=\"sel3-label\" for=\"sel3\">Category (menu)<span class=\"red-star\">*</span></label><select class=\"form-control\" name=\"menuCat\" id=\"sel3\"><option value=\"option\" disabled selected>Select option</option>";
        	for(MenuModel temp : tempMenu) {
                	html = html + "<optgroup label=\""+temp.titleEN.toUpperCase()+"\">";
                	html = html + this.getRowHtmlSelect(lang, temp.id.toString());
                	html = html + "</optgroup>";
            	}
        	html += "</select>";
    	}
    	else if("2".equals(id) || "3".equals(id)) {
        	for(MenuModel temp : tempMenu) {
                	if(temp.id == 92 || temp.id == 17 || temp.id == 95 || temp.id == 15) {
                         	html = html + "<option value=\""+temp.id+"\">&nbsp;&nbsp"+temp.titleEN.toUpperCase();
                            	html = html + "</option>";
                     	}
                	else {
                    	html = html + "<optgroup label=\"&nbsp;&nbsp;"+temp.titleEN.toUpperCase()+"\">";
                    	html = html + this.getRowHtmlSelect(lang, temp.id.toString());
                    	html = html + "</optgroup>";
                	}
            	}
    	}
    	else {
         	for(MenuModel temp : tempMenu) {
             	if(temp.parentID == 2 || temp.parentID == 3) {
                 	if(temp.id == 92 || temp.id == 17 || temp.id == 95 || temp.id == 15) {
                     	html = html + "<option value=\""+temp.id+"\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+temp.titleEN.toUpperCase();
                        	html = html + "</option>";
                 	}
                 	else {
                	html = html + "<optgroup label=\"&nbsp;&nbsp;&nbsp;&nbsp;"+temp.titleEN.toUpperCase()+"\">";
                	html = html + this.getRowHtmlSelect(lang, temp.id.toString());
                	html = html + "</optgroup>";
                 	}
             	}
             	else {
                	html = html + "<option value=\""+temp.id+"\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+temp.titleEN.toUpperCase();
                	html = html + "</option>";
             	}
            	}
       	 
    	}
    	return html;
	}
    
	public String getRowHtmlSelectSmall(String lang, String id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	List<MenuModel> tempMenu = menu.getMenuRowSelect(lang, id);
    	String html = null;
    	if("0".equals(id)) {
        	html = "<label for=\"sel1\">Menu</label><select class=\"filter-select\" name=\"type\" id=\"menuCat\"><option value=\"all\">All</option>";
        	for(MenuModel temp : tempMenu) {
                	html = html + "<optgroup label=\""+temp.titleEN.toUpperCase()+"\">";
                	html = html + this.getRowHtmlSelectSmall(lang, temp.id.toString());
                	html = html + "</optgroup>";
            	}
        	html += "</select>";
    	}
    	else if("2".equals(id) || "3".equals(id)) {
        	for(MenuModel temp : tempMenu) {
            	if(temp.id == 92 || temp.id == 17 || temp.id == 95 || temp.id == 15) {
                     	html = html + "<option value=\""+temp.id+"\">&nbsp;&nbsp"+temp.titleEN.toUpperCase();
                        	html = html + "</option>";
                 	}
            	else {
                	html = html + "<optgroup label=\"&nbsp;&nbsp;"+temp.titleEN.toUpperCase()+"\">";
                	html = html + this.getRowHtmlSelectSmall(lang, temp.id.toString());
                	html = html + "</optgroup>";
            	}
            	}
    	}
    	else {
         	for(MenuModel temp : tempMenu) {
             	if(temp.parentID == 2 || temp.parentID == 3) {
                 	if(temp.id == 92 || temp.id == 17 || temp.id == 95 || temp.id == 15) {
                     	html = html + "<option value=\""+temp.id+"\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+temp.titleEN.toUpperCase();
                        	html = html + "</option>";
                 	}
                 	else {
                	html = html + "<optgroup label=\"&nbsp;&nbsp;&nbsp;&nbsp;"+temp.titleEN.toUpperCase()+"\">";
                	html = html + this.getRowHtmlSelectSmall(lang, temp.id.toString());
                	html = html + "</optgroup>";
                 	}
             	}
             	else {
                	html = html + "<option value=\""+temp.id+"\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+temp.titleEN.toUpperCase();
                	html = html + "</option>";
             	}
            	}
       	 
    	}
    	return html;
	}
	public String getRowHtmlList(String lang, String id, String login_time) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, ParseException {
    	List<MenuModel> tempMenu = menu.getMenuRowSelect(lang, id);
    	String html = "";
    	if("0".equals(id)) {
        	html = "<ul class=\"content-list\"><li>";
        	for(MenuModel temp : tempMenu) {
                	html = html + "<a href=\"javascript:;\"  class=\"list-group-item parent-item-main main-item-text\" aria-expanded=\"false\">"+temp.titleEN.toUpperCase()+" </a>\n" +
"                            	<ul id=\""+temp.titleEN.toUpperCase()+"\" class=\"main-item\">";
                	html = html + this.getRowHtmlList(lang, temp.id.toString(), login_time);
                	html = html + "</ul>";
            	}
        	html += "</li><li id=\"news-li\" class=\"list-group-item parent-item-main main-item-text\"><a class=\"parent-children\" href=\"/Carpath/system/archive/articles/4\">NEWS<i class=\"count-subs\">("+menu.countArticles("4").toString()+" articles, "+menu.countPublish("4").toString()+" published)</i><span class=\"new-articles\">"+menu.checkNewArticlesForChildren("4", login_time)+"</span>";
                        	html = html + "</a></li></ul>";
    	}
    	else if("2".equals(id) || "3".equals(id)) {
        	for(MenuModel temp : tempMenu) {
            	if(temp.id == 92 || temp.id == 17 || temp.id == 95 || temp.id == 15) {
                     	html = html + "<li class=\"collapsed list-group-item has-no-childrens\"><a class=\"parent-children\" href=\"/Carpath/system/archive/articles/"+temp.id+"\">"+temp.titleEN.toUpperCase()
                             	+"<i class=\"count-subs\">("+menu.countArticles(temp.id.toString()).toString()+" articles, "+menu.countPublish(temp.id.toString()).toString()+" published)</i><span class=\"new-articles\">"+menu.checkNewArticlesForChildren(temp.id.toString(), login_time)+"</span>";
                        	html = html + "</a></li>";
                 	}
            	else {
                	html = html + "<a href=\"javascript:;\" data-toggle=\"collapse\" data-target=\"#"+temp.id+"\" class=\"collapsed list-group-item  parent-item-text\" "
                        	+ "aria-expanded=\"false\">"+temp.titleEN.toUpperCase()+
                        	"<i class=\"count-subs\">("+menu.countSubs(temp.id.toString()).toString()+" subcategories, "+menu.countArticlesInSubs(temp.id.toString()).toString()+" articles, "+menu.countPublishInSubs(temp.id.toString()).toString()+" published)</i><span class=\"new-articles\">"+menu.checkNewArticles(temp.id.toString(), login_time)+"</span><i class=\"fa fa-fw fa-caret-down\"></i></a>\n" +
"                            	<ul id=\""+temp.id+"\" class=\"collapse parent-item\" aria-expanded=\"false\" style=\"height: 0px;\">";
                	html = html + this.getRowHtmlList(lang, temp.id.toString(), login_time);
                	html = html + "</ul>";
            	}
            	}
    	}
    	else {
         	for(MenuModel temp : tempMenu) {
             	if(temp.parentID == 2 || temp.parentID == 3) {
                 	if(temp.id == 92 || temp.id == 17 || temp.id == 95 || temp.id == 15) {
                     	html = html + "<li class=\"collapsed list-group-item has-no-childrens\"><a class=\"parent-children\" href=\"/Carpath/system/archive/articles/"+temp.id+"\">"+temp.titleEN.toUpperCase()
                             	+"<i class=\"count-subs\">("+menu.countArticles(temp.id.toString()).toString()+" articles, "+menu.countPublish(temp.id.toString()).toString()+" published)</i><span class=\"new-articles\">"+menu.checkNewArticlesForChildren(temp.id.toString(), login_time)+"</span>";
                        	html = html + "</a></li>";
                 	}
                 	else {
                	html = html + "<a href=\"javascript:;\" data-toggle=\"collapse\" data-target=\"#"+temp.id+"\" class=\"collapsed list-group-item  parent-item-text\" "
                        	+ "aria-expanded=\"false\">"+temp.titleEN.toUpperCase()+
                        	"<i class=\"count-subs\">("+menu.countSubs(temp.id.toString()).toString()+" subcategories)</i><span class=\"new-articles\">"+menu.checkNewArticlesForChildren(temp.id.toString(), login_time)+"</span><i class=\"fa fa-fw fa-caret-down\"></i></a>\n" +
"                            	<ul id=\""+temp.id+"\" class=\"collapse parent-item\" aria-expanded=\"false\" style=\"height: 0px;\">";
                	html = html + this.getRowHtmlList(lang, temp.id.toString(), login_time);
                	html = html + "</ul>";
                 	}
             	}
             	else {
                	html = html + "<li class=\"collapsed list-group-item has-no-childrens\"><a class=\"parent-children\" href=\"/Carpath/system/archive/articles/"+temp.id+"\">"+temp.titleEN.toUpperCase()
                        	+"<i class=\"count-subs\">("+menu.countArticles(temp.id.toString()).toString()+" articles, "+menu.countPublish(temp.id.toString()).toString()+" published)</i><span class=\"new-articles\">"+menu.checkNewArticlesForChildren(temp.id.toString(), login_time)+"</span>";
                	html = html + "</a></li>";
             	}
            	}
       	 
    	}
    	return html;
	}
	public static String replaceChars(String text) {
    	text = text.replaceAll("[\\x00-\\x1F]", "");
    	text = text.replaceAll("'\\<.*?>","");
    	text = text.replaceAll("'", "'");
    	text = text.replaceAll("\\\"", "");
    	text = text.replaceAll("’", "");
   	 text = text.replaceAll("–", "-");
   	 text = text.replaceAll(",", ",");
   	 text = text.replaceAll(".", "");
   	 text = text.replaceAll("/", "");
   	 text = text.replaceAll("“", "");
   	 text = text.replaceAll("„", "");
   	 text = text.replaceAll("‘", "");
    	text = text.replaceAll("«", "");
    	text = text.replaceAll("»", "");
    	text = text.replaceAll("”","");
    	text = text.replaceAll("'","");
    	return text;
	}
	public static Boolean checkOldArticle(String actual_date) throws ParseException{
            	String date_str = actual_date.replace("/", ".");
            	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            	Date actual = dateFormat.parse(date_str);
            	Date today = new Date();
            	return actual.before(today);
	}
	public static boolean isNumeric(String str)  
	{  
            try  
            {  
                double d = Double.parseDouble(str);  
            }  
            catch(NumberFormatException nfe)  
            {  
                return false;  
            }  
            return true;  
	}
        
        public void sendMail(String url, String stack) {
        final String username = "robot@aits.ua";
        final String password = "37Rh!_09=S/U";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication()  {
                return new javax.mail.PasswordAuthentication( username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("robot@aits.ua"));
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress("sirakandrew@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("director@aits.ua"));
            message.setSubject("Carpathian Road error mail:");
            message.setText("URL: "+url+"\nError stack: \n" + stack);
            Transport.send(message);
	} catch (MessagingException e) {
            throw new RuntimeException(e);
	}
        }
}

