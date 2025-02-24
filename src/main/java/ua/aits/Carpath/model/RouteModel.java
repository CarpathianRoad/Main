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
import ua.aits.Carpath.functions.PageFiltersTranslate;

/**
 *
 * @author kiwi
 */
public class RouteModel {
    public Integer id;
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
    public String file;
    public String category;
    public String country;
    public Integer type;
    public String textType;
    public String images; 
    public String public_country;
    public String date;  
    public Integer publish;
    public Integer difficulty;
    public String textDifficulty;
    public String duration;
    public String length;
    
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
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
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
    
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    
    public String getPublic_country() {
        return public_country;
    }
    public void setPublic_country(String public_country) {
        this.public_country = public_country;
    }
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getTextDifficulty() {
        return textDifficulty;
    }

    public void setTextDifficulty(String textDifficulty) {
        this.textDifficulty = textDifficulty;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
    
    PageFiltersTranslate translate = new PageFiltersTranslate();
    
     private static List<RouteModel> routeList;
     public List<RouteModel> getAllRoutes() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet result = DB.getResultSet("select * from routes where publish = 1 and isDelete = 0 order by id desc;");
        routeList = new LinkedList<>();
        while (result.next()) { 
            String str  = result.getString("textUA").replaceAll("'\\<.*?>","");
            String str2 = str.replaceAll("[\\x00-\\x1F]", "");
            String str3 = str2.replaceAll("'", "\\\\'");
            if(str3.length() > 250){
                str3 = str3.substring(0,250);
            }
            RouteModel temp = new RouteModel();
            temp.setId(result.getInt("id"));
            temp.setTitle(result.getString("titleEN"));
            temp.setTextUA(Helpers.html2text(str3));
            temp.setFile(result.getString("file"));
            temp.setImages(result.getString("images")); 
            String countryN = translate.longToShortCountry(result.getString("public_country"));
            temp.setPublic_country(countryN); 
            temp.setDate(result.getString("date")); 
            temp.setCategory(result.getString("category"));
            temp.setType(result.getInt("type"));
            temp.setDifficulty(result.getInt("difficulty"));
            temp.setDuration(result.getString("duration"));
            temp.setLength(result.getString("length"));
            routeList.add(temp);
        } 
        DB.closeCon();
    return routeList;
    }
     public List<RouteModel> getAllRoutesSystem(String type, String value) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet result = DB.getResultSet("select * from routes where isDelete = 0 order by id desc;");
        routeList = new LinkedList<>();
        while (result.next()) { 
            RouteModel temp = new RouteModel();
            temp.setId(result.getInt("id"));
            temp.setTitle(result.getString("titleEN"));
            temp.setType(result.getInt("type"));
            temp.setPublic_country(result.getString("public_country")); 
            temp.setCountry(result.getString("country"));
            temp.setCategory(result.getString("category"));
            temp.setFile(result.getString("file"));
            temp.setImages(result.getString("images")); 
            switch(temp.getType()) {
                case 0: temp.setTextType("Test"); break;
                case 1: temp.setTextType("Foot"); break;
                case 2: temp.setTextType("Bike"); break;
                case 3: temp.setTextType("Ski"); break;
                case 4: temp.setTextType("Horse"); break;
                case 5: temp.setTextType("Water"); break;
            }
            temp.setPublish(result.getInt("publish"));
            temp.setDifficulty(result.getInt("difficulty"));
            temp.setDuration(result.getString("duration"));
            temp.setLength(result.getString("length"));
            routeList.add(temp);
        } 
        DB.closeCon();
    return routeList;
    }
     public RouteModel getOneRoute(String id, String lan) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ResultSet result = DB.getResultSet("select * from routes where id = "+ id +";");
        RouteModel temp = new RouteModel();
        while (result.next()) { 
            temp.setId(result.getInt("id"));
            temp.setTitle(result.getString("title" + lan.toUpperCase()));
            temp.setTextUA(result.getString("text" + lan.toUpperCase()));
            if("".equals(temp.title) || temp.title == null){
                temp.title = result.getString("titleEN");
            }
            if("".equals(temp.textUA) || temp.textUA == null){
                temp.textUA = result.getString("textEN");
            }
            temp.setFile(result.getString("file"));
            temp.setPublic_country(result.getString("public_country")); 
            temp.setDate(result.getString("date")); 
            temp.setImages(result.getString("images"));  
            temp.setDifficulty(result.getInt("difficulty"));
            temp.setDuration(result.getString("duration"));
            temp.setLength(result.getString("length"));
        }
        DB.closeCon();
        return temp;
    }
    public RouteModel getOneRouteForEdit(String id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ResultSet result = DB.getResultSet("select * from routes where id = "+ id +";");
        RouteModel temp = new RouteModel();
        while (result.next()) { 
            temp.setId(result.getInt("id"));
            temp.setPublic_country(result.getString("public_country"));
            
            temp.setTitleEN(result.getString("titleEN").replace("\"","&quot;"));
            temp.setTitleUA(result.getString("titleUA").replace("\"","&quot;"));
            temp.setTitleHU(result.getString("titleHU").replace("\"","&quot;"));
            temp.setTitleSK(result.getString("titleSK").replace("\"","&quot;"));
            temp.setTitleRO(result.getString("titleRO").replace("\"","&quot;"));
            temp.setTitlePL(result.getString("titlePL").replace("\"","&quot;"));
            temp.setTitleGE(result.getString("titleGE").replace("\"","&quot;"));
            temp.setTitleCZ(result.getString("titleCZ").replace("\"","&quot;"));
            temp.setTitleSRB(result.getString("titleSRB").replace("\"","&quot;"));
            
            temp.setTextEN(result.getString("textEN"));
            temp.setTextUA(result.getString("textUA"));
            temp.setTextHU(result.getString("textHU"));
            temp.setTextSK(result.getString("textSK"));
            temp.setTextRO(result.getString("textRO"));
            temp.setTextPL(result.getString("textPL"));
            temp.setTextGE(result.getString("textGE"));
            temp.setTextCZ(result.getString("textCZ"));
            temp.setTextSRB(result.getString("textSRB"));
            
            temp.setDate(result.getString("date").replace("/", "."));  
            temp.setType(result.getInt("type"));
            temp.setImages(result.getString("images")); 
            temp.setCategory(result.getString("category"));
            temp.setFile(result.getString("file"));
            temp.setDifficulty(result.getInt("difficulty"));
            temp.setDuration(result.getString("duration"));
            temp.setLength(result.getString("length"));
        }
        DB.closeCon();
        return temp;
    }
     
    public Boolean deleteRoute(String id) throws SQLException{
        DB.runQuery("UPDATE routes SET isDelete = 1, publish = 0 where id = "+ id +";");
            return true;
    }
    public Boolean publishRoute(String id, String status) throws SQLException{
        int stat = 1;
        if("false".equals(status)) {
            stat = 0;
        }
        DB.runQuery("UPDATE routes SET publish = "+stat+" where id = "+ id +";");
            return true;
    }
    public String insertRoute(String titleEN, String titleUA, String titleHU, String titleSK, String titlePL,String titleRO,String titleGE,String titleCZ,String titleSRB, 
            String filename, String img, String date, String type, String public_country, String filter, String difficulty, String duration, String length,
            String textEN, String textUA, String textHU, String textSK, String textRO, String textPL, String textGE, String textCZ, String textSRB) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        DB.runQuery("INSERT INTO routes (titleEN,titleUA, titleHU, titleSK, titlePL, titleRO, titleGE, titleCZ, titleSRB, "
                + "file, images, date, type, public_country, category, difficulty, duration, length, publish, "
                + "textEN, textUA, textHU, textSK, textRO, textPL, textGE, textCZ, textSRB, isDelete)"
                    + "values ('"+ StringEscapeUtils.escapeSql(titleEN) +"','"
                + StringEscapeUtils.escapeSql(titleUA) +"','"
                + StringEscapeUtils.escapeSql(titleHU) +"','"
                + StringEscapeUtils.escapeSql(titleSK) +"','"
                + StringEscapeUtils.escapeSql(titlePL) +"','"
                + StringEscapeUtils.escapeSql(titleRO) +"','"
                + StringEscapeUtils.escapeSql(titleGE) +"','"
                + StringEscapeUtils.escapeSql(titleCZ) +"','"
                + StringEscapeUtils.escapeSql(titleSRB) +"','"
                + filename +"','"+ img +"','"+ date +"',"+  type +",'"+  public_country +"','"+  filter +"',"+ difficulty +",'"+ duration+"','" + length + "',"+ 
                "0, '"+  
                StringEscapeUtils.escapeSql(textEN) +"', '"
                +  StringEscapeUtils.escapeSql(textUA) +"', '"
                +  StringEscapeUtils.escapeSql(textHU) +"', '"
                +  StringEscapeUtils.escapeSql(textSK) +"', '"
                +  StringEscapeUtils.escapeSql(textRO) +"', '"
                +  StringEscapeUtils.escapeSql(textPL) +"', '"
                +  StringEscapeUtils.escapeSql(textGE) +"', '"
                +  StringEscapeUtils.escapeSql(textCZ) +"', '"
                +  StringEscapeUtils.escapeSql(textSRB) +"', 0);");
            ResultSet result = DB.getResultSet("SELECT * FROM routes WHERE type="+  type +" AND date='"+  date +"' AND file='"+  filename +"' ORDER BY id DESC LIMIT 1;");
            Integer temp = 1;
            while (result.next()) {
                temp = result.getInt("id");
            }
            return temp.toString();
    } 
    public String updateRoute(String id, String titleEN, String titleUA, String titleHU, String titleSK, String titlePL,String titleRO,String titleGE,String titleCZ,String titleSRB, 
            String filename, String img, String date, String type, String public_country, String filter, String difficulty, String duration, String length,
            String textEN, String textUA, String textHU, String textSK, String textRO, String textPL, String textGE, String textCZ, String textSRB) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        
        DB.runQuery("UPDATE routes SET titleEN = '"+ StringEscapeUtils.escapeSql(titleEN) 
                +"', titleUA = '" + StringEscapeUtils.escapeSql(titleUA)
                +"', titleHU = '" + StringEscapeUtils.escapeSql(titleHU)
                +"', titleSK = '" + StringEscapeUtils.escapeSql(titleSK)
                +"', titlePL = '" + StringEscapeUtils.escapeSql(titlePL)
                +"', titleRO = '" + StringEscapeUtils.escapeSql(titleRO)
                +"', titleGE = '" + StringEscapeUtils.escapeSql(titleGE)
                +"', titleCZ = '" + StringEscapeUtils.escapeSql(titleCZ)
                +"', titleSRB = '" + StringEscapeUtils.escapeSql(titleSRB)
                +"', date = '"+  date
                +"', type = "+  type
                 +", file = '"+  filename
                 +"', images = '"+  img
                +"', category = '"+  filter +"', difficulty = "+  difficulty +", duration = '"+ duration +"', length = '"+ length +"', public_country = '"+ public_country +"', publish  = 0"+ 
                ", textEN = '"+ StringEscapeUtils.escapeSql(textEN) +"', textUA = '"+ StringEscapeUtils.escapeSql(textUA) +
                "', textHU = '"+ StringEscapeUtils.escapeSql(textHU) +
                "', textSK = '"+ StringEscapeUtils.escapeSql(textSK) +
                "', textRO = '"+ StringEscapeUtils.escapeSql(textRO) +
                "', textPL = '"+ StringEscapeUtils.escapeSql(textPL) +
                "', textGE = '"+ StringEscapeUtils.escapeSql(textGE) +
                "', textCZ = '"+ StringEscapeUtils.escapeSql(textCZ) +
                "', textSRB = '"+ StringEscapeUtils.escapeSql(textSRB) +
                "' where id = '"+id+"'");
            return id;
    } 
     public List<RouteImage> getRouteImages(String id)throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException { 
        ResultSet result = DB.getResultSet("select * from routeimages where route = "+id+";");
        LinkedList<RouteImage> imageRoute = new LinkedList<>();
        while (result.next()) { 
            RouteImage temp = new RouteImage();
            temp.setId(result.getInt("id"));
            temp.setRouteID(result.getInt("route"));
            temp.setName(result.getString("name"));
            temp.setX(result.getString("x"));
            temp.setY(result.getString("y"));
            temp.setImg(result.getString("img"));
            imageRoute.add(temp);
        }
        DB.closeCon();
        return imageRoute;
     }
     
    
    
    
     public List<RouteModel> get_routes_by_country_filter(String lan, String country, String type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = " select * from routes where publish = 1 ";
        if(!country.equals("all"))
            {
                query = query + " AND `public_country` = \""+country+"\" ";
            }
       
        if(!type.equals("all_type"))
            {
                query = query + " AND `type` = \""+type+"\" ";
            }
        
        query = query + " order by id desc; ";
        ResultSet result = DB.getResultSet(query);
        routeList = new LinkedList<>();
        while (result.next()) { 
            String str  = result.getString("textUA").replaceAll("'\\<.*?>","");
            String str2 = str.replaceAll("[\\x00-\\x1F]", "");
            String str3 = str2.replaceAll("'", "\\\\'");
            if(str3.length() > 250){
                str3 = str3.substring(0,250);
            }
            RouteModel temp = new RouteModel();
            temp.setId(result.getInt("id"));
            temp.setTitle(result.getString("titleEN"));
            temp.setTextUA(Helpers.html2text(str3));
            temp.setFile(result.getString("file"));
            temp.setImages(result.getString("images")); 
            temp.setPublic_country(result.getString("public_country")); 
            temp.setDate(result.getString("date")); 
            temp.setCategory(result.getString("category"));
            temp.setType(result.getInt("type"));
            temp.setDifficulty(result.getInt("difficulty"));
            temp.setDuration(result.getString("duration"));
            temp.setLength(result.getString("length"));
            routeList.add(temp);
        } 
        DB.closeCon();
    return routeList;
    }
     
     
    public Integer get_count_routes_by_country_filter(String lan, String country, String type) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = " select COUNT(id) as count from routes where publish = 1 ";
        if(!country.equals("all"))
            {
                query = query + " AND `public_country` = \""+country+"\" ";
            }
       
        if(!type.equals("all_type"))
            {
                query = query + " AND `type` = \""+type+"\" ";
            }
        
        query = query + " order by id desc; ";
        ResultSet result = DB.getResultSet(query);
        int count = 0;
    while(result.next()){
        count = result.getInt("count");
        }
        DB.closeCon();
    return count;
    } 
    public class RouteImage {
         public Integer id;
         public Integer routeID; 
         public String name;
         public String x;
         public String y;
         public String img;
         
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRouteID() {
        return routeID;
    }
    public void setRouteID(Integer routeID) {
        this.routeID = routeID;
    }
    public String getX() {
        return x;
    }
    public void setX(String x) {
        this.x = x;
    }
    
    public String getY() {
        return y;
    }
    public void setY(String y) {
        this.y = y;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    }
}
