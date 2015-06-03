<%-- 
    Document   : NewsPage
    Created on : Jan 10, 2015, 7:57:32 PM
    Author     : kiwi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:indexpage>
    <style>
    .news_title a:after{
        content:"";
    }
    .news_title {
              background-color: #56698F;
  padding: 15px;
  text-align: center;
  text-transform: uppercase;
    -webkit-transform-style: preserve-3d;
  -moz-transform-style: preserve-3d;
  transform-style: preserve-3d;
    }
    .news_title a {
          color: #fff;
        height: auto;
          position: relative;
  top: 50%;
  transform: translateY(-50%);
    }
    .news_text_box {
          height: auto;
    }
    </style>
    <div class="s-new widthClass">
        <div class="">
            <div class="countriesFilter">
                
            </div>
            <c:forEach items="${contentList}" var="item">	
                    <div class="s-cell">
                        <div class="s-block newsHeight">
                            <div class="newsImage" style="display:none">
                                <a href="${Constants.URL}article/category/${item.id}">
                                    <div class="imageHover">
                                        <div class="imageHoverDate">
                                        </div>
                                        <div class="imageHoverCountry">
                                        </div>
                                    </div>
                                    <img src="${Constants.URL}img/dog.png" />
                                </a>
                            </div>

                            <img class="newsImageUnderline" src="${Constants.URL}img/newsLine.png">
                            <div class="news_text_box">
                                <div class="news_title"><a href="${Constants.URL}article/category/${item.id}">${item.titleEN}</a></div>
                                
                            </div>
                        </div>
                    </div>        
            </c:forEach>
        <div class="s-clear"></div>
        </div>
    </div>
</t:indexpage>
 