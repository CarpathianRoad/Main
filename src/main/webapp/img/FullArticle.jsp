<%-- 
    Document   : FullArticle
    Created on : Jan 11, 2015, 3:06:54 PM
    Author     : kiwi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:indexpage>
    <section>
        <div class="s-new markerPageTable">
            <div class="breadcrumbsMarker">
                <ul class="breadcrumbsUlMarker">
                    <li><a href="${Constants.URL}index">Main</a><div class="right_arrow"> </div></li>
                    <li><a href="#">News</a><div class="right_arrow"> </div></li>
                    <li><a>${article.title}</a></li>
                </ul>
                <div class="countriesFilterMarker">
                    <a class="selected_country" href="#">All countries</a>
                    <a href="#">Poland</a>
                    <a href="#">Hungary</a>
                    <a href="#">Romania</a>
                    <a href="#">Slovakia</a>
                    <a href="#">Ukraine</a>
                </div>
            </div>
            <div class="markerLeftDescr">
                <div class="markerPageTitle">${article.title}</div>
                <div class="markerPageUnderHeading">
                    <div class="markerPageDate">
                        ${article.date}
                    </div>
                    <div class="markerPageCountry">
                        <img src="/Carpath/img/mapControlsImageSelected.png"/>
                        ${article.country}
                    </div>
                </div>
                <div class="markerPageText">
                    ${article.textEN}
                </div>
                <c:if test="${fn:length(images[0])!=''}">
    <script type="text/javascript" src="${Constants.URL}js/article_gallery.js"></script>
                                    <div id="article_slider1_container" style="position: relative; top: 0px; left: 0px; width: 617px; height: 150px; overflow: hidden;">
                                        <div u="slides" style="cursor: move; position: absolute; left: 0px; top: 0px; width: 617px; height: 150px; overflow: hidden;">
                                            <c:forEach items="${images}" var="image" varStatus="loop">
                                                <div onclick="set_main_picture('${Constants.URL}${image}','${loop.index}')">
                                                    <div class="sliderHover">
                                                        <div class="imageHoverMarkerPage"></div><img u="image" src="${Constants.URL}${image}"/>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <span class="markerPageArrowLeft" style="width: 50px; height: 95px; top: 28px; left: 20px;">
                                        </span>
                                        <span class="markerPageArrowRight" style="width: 50px; height: 95px; top: 28px; right: 20px">
                                        </span>
                                    </div>
                                    <div class="article_main_image">
                                        <img id="main_image" src="${Constants.URL}<c:out value="${images[0]}" />" />
                                        <div class="mainImageSliderLine">
                                            <div id="imageCount">1</div>/${fn:length(images)}
                                            &nbsp;&nbsp;
                                                ${article.title} Gallery
                                        </div>
                                    </div>
                </c:if>
                                    <div class="markerPageBottomLine"></div>
                                    <div class="markerPageSocial">
                                        <div class="fbMarker markerPageSocialHover">
                                            <div class="markerPageSocialIcon">
                                                <img src="${Constants.URL}img/fb_icon_marker.png">
                                            </div>
                                            <div class="markerPageSocialNumber">25</div>
                                        </div>
                                        <div class="twMarker markerPageSocialHover">
                                            <div class="markerPageSocialIcon">
                                                <img src="${Constants.URL}img/tw_icon_marker.png">
                                            </div>
                                            <div class="markerPageSocialNumber">117</div>
                                        </div>
                                    </div>
            </div>
            <div class="markerRightDescr">
                <div class="otherNewsHeading">OTHER NEWS</div>
            </div>
        </div>
    </section>
</t:indexpage>