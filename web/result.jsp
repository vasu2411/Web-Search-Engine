<%-- 
    Document   : temp
    Created on : 30 Nov, 2018, 7:30:43 PM
    Author     : vasus
--%>

<%@page import="java.nio.file.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="files.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="author" content="Colorlib">
    <meta name="description" content="#">
    <meta name="keywords" content="#">
    <!-- Favicons -->
    <link rel="shortcut icon" href="#">
    <!-- Page Title -->
    <title>Results</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">
    <!-- Simple line Icon -->
    <link rel="stylesheet" href="css/simple-line-icons.css">
    <!-- Themify Icon -->
    <link rel="stylesheet" href="css/themify-icons.css">
    <!-- Hover Effects -->
    <link rel="stylesheet" href="css/set1.css">
    <!-- Main CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
    <!--============================= HEADER =============================-->
    <div class="dark-bg sticky-top">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-10">
                    <nav class="navbar navbar-expand-lg navbar-light">
                        <a class="navbar-brand" href="index.html">Swift</a>
                        <form action="Search" class="form-wrap" method="post" style="width: 100%;">
                            <input style="padding: 19px;
                                              width: 80%;
                                              border-top-left-radius: 3px;
                                              border-bottom-left-radius: 3px;
                                              border: none;" 
                                              type="text" value="${query}" id="keywords" name="keyword">x
                            </input>
                            <input type="submit" style="font-weight: 400;
                                font-size: 18px;
                                padding: 17px;
                                width: 17%;
                                background: #ff3a6d;
                                color: #ffffff;
                                border: none;
                                border-top-right-radius: 3px;
                                border-bottom-right-radius: 3px;" value="Search">
                            </input>
                        </form>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!--//END HEADER -->
    <!--============================= DETAIL =============================-->
	
    <section>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-7 responsive-wrap">
                    <div class="featured-title-box">
                        <div class="col-12">
                            <%
                                int c=0;
                            %>  
                            <div class="row">
                                Result found in ${totalTime} milliseconds...
                            </div>
                            <c:forEach items="${output}" var="list">
                             <div class="row">
                                 <% c=1; %> 
                                 <a href="./data/www.javatpoint.com/${list}html" target="_blank">${list}</a> 
                             </div>
                            </c:forEach>
                            <div class="row">
                                <% 
                                    if(c==0)
                                    {%>
                                        <%= "Not found" %>   
                                     <%}
                                %>
                            </div>
                            
                        </div>
                       
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--//END DETAIL -->
    <!--============================= FOOTER =============================-->
    
    <!--//END FOOTER -->




    <!-- jQuery, Bootstrap JS. -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    
</body>
<style>
    .ui-autocomplete{
        background: white;
        max-height: 180px;
        overflow: scroll;
    }
</style>
<script>
    $( document ).ready(function() {
       $("#keywords").autocomplete({
           source: function( request, response ) {
        $.ajax( {
          url: "http://localhost:8084/SearchEngine/Search",
          data: {
            suggest:function(){
                var s = $("#keywords").val().trim().replace(/\s{2,}/g, ' ').split(" ");
                
              return s[s.length-1]
            },
          },
          success: function( data ) {
              var temp = data.split(',')
            response( temp );
          },
      
        } );
      },
          select: function( e, ui ) {
        $("#keywords").val("Uttam"+ui.item.value);
      }
       });
//       
//            $("#keywords").keypress(function(){
//                   $.ajax({
//                        url: "http://localhost:8084/SearchEngine/Search",
//                        
//                        crossDomain: true,
//                        data: {
//                          suggest: 'w3'
//                        },
//                        success: function( data ) {
//                          var temp = data.split(',')
//                          console.log(temp);
//                                   $(this).autocomplete(temp)
//                        }
//                      });
//            })
	
   });

</script>
</html>

