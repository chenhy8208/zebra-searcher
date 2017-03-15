<%@ page language="java" import="java.text.SimpleDateFormat,java.util.Date" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    <head>
      <jsp:include page="inc/key.jsp" flush="true">
        <jsp:param name="pageTitle" value="结果页"/>
      </jsp:include>
    </head>
    <body style="padding-top: 40px;">
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <div class="container">
          <div class="row">
            <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">

              <form action="results" method="get" name="searchForm">
                <jsp:include page="inc/searchBoxTitle.jsp" flush="true" />
                <jsp:include page="inc/searchBox.jsp" flush="true" />
              </form>

            </div>
          </div>
        </div>

      <div class="container">
        <div class="row">
          <div>

            <!-- Tab panes -->
            <div class="tab-content">

              <div role="tabpanel" class="tab-pane tab-body active" id="results1">
                <div class="">

                  <div class="resultIntro">找到约${findTotalResults}条结果，（用时${findSeconds}秒），共约${findTotalPagesizes}页</div>

                  <c:if test="${empty results}">
                      <center><h3><i>很遗憾，没有符合的搜索结果！</i></h3></center>
                  </c:if>

<c:if test="${not empty results}">
                  <!--循环体-->
<c:forEach items="${results}" var="result">
                  <div class="panel panel-default list-group-item">
                    <div class="panel-body">
                      <div class="media">

                        <div class="media-body">
                            <h4 class="media-heading"><a href="${result.webHtml.url}" target="_blank">${result.webHtml.metaTitle}</a></h4>
                              <div class="relativeFont"><a href="${result.webHtml.url}" target="_blank">${result.webHtml.url}</a></div>
                            <span class="newTimeFactor_before_abs m"><fmt:formatDate value="${result.webHtml.pageUpdateTime}" pattern="yyyy年MM月dd日" />&nbsp;-&nbsp;</span>
                            ${result.webHtml.metaDescription}
                            <div class="resultTail">收录时间：<fmt:formatDate value="${result.webHtml.crawlTime}" pattern="yyyy-MM-dd HH:mm" /></div>
                        </div>
                      </div>
                    </div>
                  </div>
</c:forEach>
</c:if>

              </div>
            </div>

            <div class="page">
              ${pageVo.pageNumHtml}
              <span style="clear:both;" class="page_z">共<em>${pageVo.getCount()}</em>条数据，第<em>${pageVo.pageNum}</em>页，共<em>${pageVo.pageCount}</em>页 </span>
            </div>

          </div>
        </div>
      </div>


        <div class="container">
          <div class="row">
            <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">

              <form action="results" method="get" name="searchForm">
                <jsp:include page="inc/searchBoxNoneTitle.jsp" flush="true" />
                <jsp:include page="inc/searchBox.jsp" flush="true" />
              </form>

            </div>
          </div>
        </div>

        <%@ include file="inc/footer.jsp" %>
    </body>
</html>
