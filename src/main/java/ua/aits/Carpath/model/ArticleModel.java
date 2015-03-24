/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.aits.Carpath.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import ua.aits.Carpath.functions.DB;
import ua.aits.Carpath.functions.Helpers;

/**
 *
 * @author kiwi
 */
public class ArticleModel {
    public Integer id;
    public Integer type;
    public String textType;
    public Integer publish;
    public Double x;
    public Double y;
    public String title;
    public String titleEN;
    public String titleUA;
    public String titleHU;
    public String titleSK;
    public String titlePL;
    public String titleRO;
    public String titleGE;
    public String titleCZ;
    public String titleSRB;
    public String textEN;
    public String textUA;
    public String textHU;
    public String textSK;
    public String textPL;
    public String textRO;
    public String textGE;
    public String textCZ;
    public String textSRB;
    public String date;
    public String public_country;
    public String author; 
    public String image; 
    public String actDate;
    public String markerIcon;
    public String filters;
    public String country;
    public String region;
    public String district;
    public String town;
    public String menuCat;
    public String menuText;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public String getTextType() {
        return textType;
    }
    public void setTextType(String textType) {
        this.textType = textType;
    }
    
    public Integer getPublish() {
        return publish;
    }
    public void setPublish(Integer publish) {
        this.publish = publish;
    }
    
    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        this.x = x;
    }
    
    public Double getY() {
        return y;
    }
    public void setY(Double y) {
        this.y = y;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitleEN() {
        return titleEN;
    }
    public void setTitleEN(String titleEN) {
        this.titleEN = titleEN;
    }
    
    public String getTitleUA() {
        return titleUA;
    }
    public void setTitleUA(String titleUA) {
        this.titleUA = titleUA;
    }
    
    public String getTitleHU() {
        return titleHU;
    }
    public void setTitleHU(String titleHU) {
        this.titleHU = titleHU;
    }
    
    public String getTitleSK() {
        return titleSK;
    }
    public void setTitleSK(String titleSK) {
        this.titleSK = titleSK;
    }
    
    public String getTitlePL() {
        return titlePL;
    }
    public void setTitlePL(String titlePL) {
        this.titlePL = titlePL;
    }
    
    public String getTitleRO() {
        return titleRO;
    }
    public void setTitleRO(String titleRO) {
        this.titleRO = titleRO;
    }
    
    public String getTitleGE() {
        return titleGE;
    }
    public void setTitleGE(String titleGE) {
        this.titleGE = titleGE;
    }
    
    public String getTitleCZ() {
        return titleCZ;
    }
    public void setTitleCZ(String titleCZ) {
        this.titleCZ = titleCZ;
    }
    
    public String getTitleSRB() {
        return titleSRB;
    }
    public void setTitleSRB(String titleSRB) {
        this.titleSRB = titleSRB;
    }
    
    public String getPublic_country() {
        return public_country;
    }
    public void setPublic_country(String public_country) {
        this.public_country = public_country;
    }
    
    public String getTextEN() {
        return textEN;
    }
    public void setTextEN(String textEN) {
        this.textEN = textEN;
    }
    
    public String getTextUA() {
        return textUA;
    }
    public void setTextUA(String textUA) {
        this.textUA = textUA;
    }
    
    public String getTextHU() {
        return textHU;
    }
    public void setTextHU(String textHU) {
        this.textHU = textHU;
    }
    
    public String getTextSK() {
        return textSK;
    }
    public void setTextSK(String textSK) {
        this.textSK = textSK;
    }
    
    public String getTextPL() {
        return textPL;
    }
    public void setTextPL(String textPL) {
        this.textPL = textPL;
    }
    
    public String getTextRO() {
        return textRO;
    }
    public void setTextRO(String textRO) {
        this.textRO = textRO;
    }
    
    public String getTextGE() {
        return textGE;
    }
    public void setTextGE(String textGE) {
        this.textGE = textGE;
    }
    
    public String getTextCZ() {
        return textCZ;
    }
    public void setTextCZ(String textCZ) {
        this.textCZ = textCZ;
    }
    
    public String getTextSRB() {
        return textSRB;
    }
    public void setTextSRB(String textSRB) {
        this.textSRB = textSRB;
    }
    
    public String getMarkerIcon() {
        return markerIcon;
    }
    public void setMarkerIcon(String markerIcon) {
        this.markerIcon = markerIcon;
    }
    
    public String getFilters() {
        return filters;
    }
    public void setFilters(String filters) {
        this.filters = filters;
    }
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getActDate() {
        return actDate;
    }
    public void setActDate(String actDate) {
        this.actDate = actDate;
    }
    
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    
    
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    
    
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    
    public String getMenuCat() {
        return menuCat;
    }
    public void setMenuCat(String menuCat) {
        this.menuCat = menuCat;
    }
    
    public String getMenuText() {
        return menuText;
    }
    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }
    
    
    public List<ArticleModel> getAllNews() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet result = DB.getResultSet("select * from content where type in (0,1) and publish = 1 order by id desc;");
        List<ArticleModel> newsList = new LinkedList<>();
        while (result.next()) { 
            ArticleModel temp = new ArticleModel();
            String str  = result.getString("textEN").replaceAll("\\<.*?>","");
            if(str.length() > 400){
                str = str.substring(0,400);
            }
            temp.setId(result.getInt("id"));
            temp.setTitle(result.getString("titleEN"));
            temp.setTextEN(str);
            temp.setDate(result.getString("date"));
            temp.setImage(result.getString("image"));
            if("".equals(temp.getImage())){
                temp.setImage("img/no-photo.png");
            }
            temp.setActDate(result.getString("actual"));
            temp.setCountry(result.getString("country")); 
            temp.setAuthor(result.getString("author"));
            newsList.add(temp);
        } 
        DB.closeCon();
    return newsList;
    }
    public List<ArticleModel> getArticleByCount(String lan,String count) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet result = DB.getResultSet("select * from content where type NOT IN (2,3) and publish = 1 order by id desc limit "+count+";");
        List<ArticleModel> newsList = new LinkedList<>();
        while (result.next()) { 
            ArticleModel temp = new ArticleModel();
            String f_title = result.getString("title"+lan.toUpperCase());
            if("".equals(f_title) || f_title == null){
                f_title = result.getString("titleEN");
            }
            if(f_title.length() > 30){
                f_title = f_title.substring(0,30) + "...";
            }
            String text = Helpers.html2text(result.getString("text"+lan.toUpperCase()));
            if("".equals(text) || text == null){
                text = result.getString("textEN");
            }
            if(text.length() > 175){
                text = text.substring(0,175) + "...";
            }
            temp.setTextEN(text);
            temp.setId(result.getInt("id"));
            temp.setTitle(f_title);
            temp.setCountry(result.getString("country")); 
            temp.setDate(result.getString("date"));
            String [] arr = result.getString("image").split(",");
            if("".equals(arr[0])){
                arr[0] = "img/zak.png";
            }
            temp.setImage(arr[0]); 
            newsList.add(temp);
        } 
        DB.closeCon();
    return newsList;
    }
    
    public List<ArticleModel> getAllAdv() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet result = DB.getResultSet("select * from content where type = 1 and publish = 1 order by date desc;");
        List<ArticleModel> advList = new LinkedList<>();
        while (result.next()) { 
            ArticleModel temp = new ArticleModel();
            String str  = result.getString("textEN").replaceAll("\\<.*?>","");
            if(str.length() > 400){
                str = str.substring(0,400);
            }
            temp.setId(result.getInt("id"));
            temp.setTitleEN(result.getString("titleEN"));
            temp.setTextEN(str);
            temp.setDate(result.getString("date"));
            temp.setActDate(result.getString("actual"));
            temp.setAuthor(result.getString("author"));
            advList.add(temp);
        } 
        DB.closeCon();
    return advList;
    }
    public List<ArticleModel> getFiveAdv() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet result = DB.getResultSet("select * from content where type = 1 and publish = 1 order by date desc limit 5;");
        List<ArticleModel> advList = new LinkedList<>();
        while (result.next()) { 
            ArticleModel temp = new ArticleModel();
            String str  = result.getString("textEN").replaceAll("\\<.*?>","");
            if(str.length() > 100){
                str = str.substring(0,100);
            }
            temp.setId(result.getInt("id"));
            temp.setTitle(result.getString("titleEN"));
            temp.setTextEN(str);
            temp.setDate(result.getString("date"));
            temp.setActDate(result.getString("actual"));
            advList.add(temp);
        } 
        DB.closeCon();
    return advList;
    }
    public ArticleModel getOneArticle(String lan, String id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ResultSet result = DB.getResultSet("select * from content where id = "+ id +";");
        ArticleModel temp = new ArticleModel();
        while (result.next()) { 
            temp.setId(result.getInt("id"));
            temp.setX(result.getDouble("x"));
            temp.setY(result.getDouble("y"));
            temp.setType(result.getInt("type"));
            temp.setPublic_country(result.getString("public_country"));
            temp.setTitle(result.getString("title"+lan));
            temp.setTextEN(result.getString("text"+lan));
            if("".equals(temp.getTitle()) || temp.getTitle() == null){
                temp.setTitle(result.getString("titleEN"));
            }
            if("".equals(temp.getTextEN()) || temp.getTextEN() == null){
                temp.setTextEN(result.getString("textEN"));
            }
            temp.setDate(result.getString("date"));
            temp.setActDate(result.getString("actual"));
            temp.setImage(result.getString("image"));
            temp.setAuthor(result.getString("author"));
            temp.setMarkerIcon(result.getString("markerIcon")); 
            temp.setFilters(result.getString("filters"));
            temp.setCountry(result.getString("country")); 
            temp.setRegion(result.getString("region")); 
            temp.setDistrict(result.getString("district")); 
            temp.setTown(result.getString("town")); 
            temp.setMenuCat(result.getString("menuCat")); 
        }
        DB.closeCon();
        return temp;
    }
    
    public List<ArticleModel> getByCategory(String catID) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet result = DB.getResultSet("select * from content where menuCat = "+catID+" and publish = 1 order by date desc;");
        List<ArticleModel> contentList = new LinkedList<>();
        while (result.next()) { 
            ArticleModel temp = new ArticleModel();
            temp.setId(result.getInt("id"));
            temp.setType(result.getInt("type"));
            temp.setTitle(result.getString("titleEN"));
            temp.setTextEN(result.getString("textEN"));
            temp.setDate(result.getString("date"));
            temp.setActDate(result.getString("actual"));
            temp.setAuthor(result.getString("author"));
            temp.setPublish(result.getInt("publish"));
            contentList.add(temp);
        } 
        DB.closeCon();
    return contentList;
    }
    
    
    public List<ArticleModel> getAllContentByType(String username, String type, String value) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String where;
        String user;
        if("admin".equals(username)){
            user = "";
        }
        else {
            user = " AND author='"+username+"'";
        }
        if("default".equals(type) || "all".equals(value)) {
            where = "c.isDelete = 0";
        } 
        else {
            where = "c.isDelete = 0 AND c."+type+"='"+value+"'";
        }
        
        ResultSet result = DB.getResultSet("SELECT t.titleEN as 'menuText', c. * FROM content c LEFT JOIN category t ON c.menuCat = t.id WHERE "+ where + user +" order by id desc;");
        List<ArticleModel> contentList = new LinkedList<>();
        while (result.next()) { 
            ArticleModel temp = new ArticleModel();
            temp.setId(result.getInt("id"));
            temp.setType(result.getInt("type"));
            temp.setTitle(result.getString("titleEN"));
            temp.setPublic_country(result.getString("public_country"));
            temp.setDate(result.getString("date"));
            temp.setActDate(result.getString("actual"));
            temp.setAuthor(result.getString("author"));
            temp.setCountry(result.getString("country"));
            temp.setPublish(result.getInt("publish"));
            temp.setMenuCat(result.getString("menuCat")); 
            switch(temp.getType()) {
                case 0: temp.setTextType("News"); break;
                case 1: temp.setTextType("Anons"); break;
                case 2: temp.setTextType("Marker"); break;
                case 3: temp.setTextType("Article"); break;
            }
            temp.setMenuText(result.getString("menuText"));
            contentList.add(temp);
        } 
        DB.closeCon();
    return contentList;
    }
    
    public String insertArticle(String titleEN, String titleUA, String titleHU, String titleSK, String titlePL,String titleRO,String titleGE,String titleCZ,String titleSRB, String date, String actDate, String type, String author, String img, String x, String y, 
            String public_country, String country, String region, String district, String town, String markerType, String filters, String menuCat,
            String textEN, String textUA, String textHU, String textSK, String textRO, String textPL, String textGE, String textCZ, String textSRB) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if("".equals(x)){ x = "0";}if("".equals(y)){ y = "0";}    
        DB.runQuery("INSERT INTO content (titleEN,titleUA, titleHU, titleSK, titlePL, titleRO, titleGE, titleCZ, titleSRB, date, type, author, image, x, y, public_country, country, region, district, town, markerIcon, filters, publish, textEN, textUA, textHU, textSK, textRO, textPL, textGE, textCZ, textSRB, actual, menuCat, isDelete)"
                    + "values ('"+ StringEscapeUtils.escapeSql(titleEN) +"','"
                + StringEscapeUtils.escapeSql(titleUA) +"','"
                + StringEscapeUtils.escapeSql(titleHU) +"','"
                + StringEscapeUtils.escapeSql(titleSK) +"','"
                + StringEscapeUtils.escapeSql(titlePL) +"','"
                + StringEscapeUtils.escapeSql(titleRO) +"','"
                + StringEscapeUtils.escapeSql(titleGE) +"','"
                + StringEscapeUtils.escapeSql(titleCZ) +"','"
                + StringEscapeUtils.escapeSql(titleSRB) +"','"
                +  date +"',"+  type +",'"+  author +"','"+  img +"',"+  x +","+  y +","+ 
"                '"+ public_country +"','"+ StringEscapeUtils.escapeSql(country) +"','"+  StringEscapeUtils.escapeSql(region) +"','"+ StringEscapeUtils.escapeSql(district) +"','"+  StringEscapeUtils.escapeSql(town) +"','"+  markerType +"','"+  filters +"', 0, '"+  
                StringEscapeUtils.escapeSql(textEN) +"', '"
                +  StringEscapeUtils.escapeSql(textUA) +"', '"
                +  StringEscapeUtils.escapeSql(textHU) +"', '"
                +  StringEscapeUtils.escapeSql(textSK) +"', '"
                +  StringEscapeUtils.escapeSql(textRO) +"', '"
                +  StringEscapeUtils.escapeSql(textPL) +"', '"
                +  StringEscapeUtils.escapeSql(textGE) +"', '"
                +  StringEscapeUtils.escapeSql(textCZ) +"', '"
                +  StringEscapeUtils.escapeSql(textSRB) +"','"
                +  actDate +"','"+  menuCat +"', 0);");
            ResultSet result = DB.getResultSet("SELECT * FROM content WHERE type="+  type +" AND date='"+  date +"' AND author='"+  author +"' ORDER BY id DESC LIMIT 1;");
            Integer temp = 1;
            while (result.next()) {
                temp = result.getInt("id");
            }
            return temp.toString();
    } 
    public String updateArticle(String id,String titleEN, String titleUA, String titleHU, String titleSK, String titlePL,String titleRO,String titleGE,String titleCZ,String titleSRB, String date, String actDate, String type, String author, String img, String x, String y, 
            String public_country, String country, String region, String district, String town, String markerType, String filters, String menuCat,
            String textEN, String textUA, String textHU, String textSK, String textRO, String textPL, String textGE, String textCZ, String textSRB) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if("".equals(x)){ x = "0";}if("".equals(y)){ y = "0";}    
        DB.runQuery("UPDATE content SET titleEN = '"+ StringEscapeUtils.escapeSql(titleEN) 
                +"', titleUA = '" + StringEscapeUtils.escapeSql(titleUA)
                +"', titleHU = '" + StringEscapeUtils.escapeSql(titleHU)
                +"', titleSK = '" + StringEscapeUtils.escapeSql(titleSK)
                +"', titlePL = '" + StringEscapeUtils.escapeSql(titlePL)
                +"', titleRO = '" + StringEscapeUtils.escapeSql(titleRO)
                +"', titleGE = '" + StringEscapeUtils.escapeSql(titleGE)
                +"', titleCZ = '" + StringEscapeUtils.escapeSql(titleCZ)
                +"', titleSRB = '" + StringEscapeUtils.escapeSql(titleSRB)
                +"', date = '"+  date +"', author = '"+  author +"', image = '"+  img +"', x = "+  x +", y = "+  y +", public_country = '"+ public_country +"',country = '"+ StringEscapeUtils.escapeSql(country) +"', region = '"+  StringEscapeUtils.escapeSql(region) +"', district = '"+ StringEscapeUtils.escapeSql(district) +"', town = '"+  StringEscapeUtils.escapeSql(town) +"', markerIcon = '"+  markerType +"', filters = '"+  filters +"', publish  = 0, textEN = '"+ StringEscapeUtils.escapeSql(textEN) +"', textUA = '"+ StringEscapeUtils.escapeSql(textUA) +
                "', textHU = '"+ StringEscapeUtils.escapeSql(textHU) +
                "', textSK = '"+ StringEscapeUtils.escapeSql(textSK) +
                "', textRO = '"+ StringEscapeUtils.escapeSql(textRO) +
                "', textPL = '"+ StringEscapeUtils.escapeSql(textPL) +
                "', textGE = '"+ StringEscapeUtils.escapeSql(textGE) +
                "', textCZ = '"+ StringEscapeUtils.escapeSql(textCZ) +
                "', textSRB = '"+ StringEscapeUtils.escapeSql(textSRB) +
                "', actual = '"+  actDate +"', menuCat = '"+  menuCat +"' where id = '"+id+"'");
            return id;
    }
    public Boolean deleteArticle(String id) throws SQLException{
        DB.runQuery("UPDATE content SET isDelete = 1, publish = 0 where id = "+ id +";");
            return true;
    }
    public Boolean publishArticle(String id, String status) throws SQLException{
        int stat = 1;
        if("false".equals(status)) {
            stat = 0;
        }
        DB.runQuery("UPDATE content SET publish = "+stat+" where id = "+ id +";");
            return true;
    }
    
}
