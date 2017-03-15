<%@ page language="java" import="java.text.SimpleDateFormat,java.util.Date" pageEncoding="UTF-8"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    <head>
      <jsp:include page="inc/key.jsp" flush="true">
        <jsp:param name="pageTitle" value="首页"/>
      </jsp:include>
      <script>
        $(function(){
          $("div.container").addClass("fill");
          $("div.container > div.row").addClass("vertical-center");

        });
      </script>
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

        <div class="container fill">
          <div class="row vertical-center">
            <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3">

              <form action="results" method="get" name="searchForm" id="searchForm">
                  <jsp:include page="inc/searchBoxTitle.jsp" flush="true" />
                  <jsp:include page="inc/searchBox.jsp" flush="true" />
              </form>

            </div>
          </div>
        </div>


        <%@ include file="inc/footer.jsp" %>
    </body>
</html>
