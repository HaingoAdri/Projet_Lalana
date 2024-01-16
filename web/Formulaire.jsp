<%-- 
    Document   : Formulaire
    Created on : 4 janv. 2024, 02:45:36
    Author     : Adrienne
--%>

<%@page import="lalana.Point_kilo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Point_kilo> listPk = (List<Point_kilo>)request.getAttribute("rn");
    List<Point_kilo> listChoix = (List<Point_kilo>)request.getAttribute("choix");
%>
<!doctype html>
<html lang="en">
  <meta http-equiv="content-type" content="text/html;charset=utf-8" />
  <head>
    <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.88.1">
        <title>-----</title>

        <link rel="canonical" href="index.html">



      <!-- Bootstrap core CSS -->
  <link href="dist/css/bootstrap.min.css" rel="stylesheet" >

      <!-- Favicons -->
  <link rel="apple-touch-icon" href="assets/img/favicons/apple-touch-icon.png" sizes="180x180">
  <link rel="icon" href="assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
  <link rel="icon" href="assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
  <link rel="manifest" href="assets/img/favicons/manifest.json">
  <link rel="mask-icon" href="assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
  <link rel="icon" href="assets/img/favicons/favicon.ico">
  <meta name="theme-color" content="#7952b3">


      <style>
        .bd-placeholder-img {
          font-size: 1.125rem;
          text-anchor: middle;
          -webkit-user-select: none;
          -moz-user-select: none;
          user-select: none;
        }

        @media (min-width: 768px) {
          .bd-placeholder-img-lg {
            font-size: 3.5rem;
          }
        }
      </style>


      <!-- Custom styles for this template -->
      <link href="starter-template.css" rel="stylesheet">
    </head>
  <body>

<div class="col-lg-8 mx-auto p-3 py-md-5">
  <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
    <a href="index.html" class="d-flex align-items-center text-dark text-decoration-none">
      <span class="fs-4">🛣 Projet Lalana / <a href="FormulaireServlet" class="text-dark text-decoration-none">Insertion </a>  /  <a href="Liste.html" class="text-dark text-decoration-none">Proposition </a></span>
    </a>
  </header>
 
  <main>
    <form action="Formulaire" method="post">
    <div class="row g-5">
      <div class="col-md-3">
        <div class="mb-3">
          <label for="exampleFormControlInput1" class="form-label">Personne</label>
          <input type="text" class="form-control" placeholder="personne" name="personne">
        </div>
      </div>
      <div class="col-md-3">
        <div class="mb-3">
          <label for="exampleFormControlInput1" class="form-label">Date</label>
          <input type="date" class="form-control" placeholder="date" name="date">
        </div>
      </div>
      <div class="col-md-3">
        <div class="mb-3">
          <label for="exampleFormControlInput1" class="form-label">Budget</label>
          <input type="number" class="form-control" placeholder="budget" name="budget">
        </div>
      </div>
      <div class="col-md-3">
        <div class="mb-3">
          <label for="exampleFormControlInput1" class="form-label">Priorite</label>
          <select name="priorite" id="" class="form-select">
            <%for(Point_kilo choix : listChoix){%>
                <option value="<%=choix.getId()%>"><%=choix.getNom()%></option>
                
            <%}%>
          </select>
          <%for(Point_kilo choix : listChoix){%>
                <input type="hidden" value="<%=choix.getNom()%>" name="nom_c">
          <%}%>
        </div>
          <div class="col-12">
            <button class="btn btn-warning" type="submit">Suivant</button>
          </div>
      </div>
    </div>
    <div class="table">
      <table class="table table-striped">
        <thead>
          <tr>
            <th scope="col">Route</th>
            
          </tr>
        </thead>
        <tbody>
          <%for(Point_kilo pk : listPk){%> 
          <tr>
            <th scope="row">
                <%=pk.getRoute()%>
                <input type="checkbox" class="form-check-input"  name="pk"  value="<%=pk.getRoute()%>">
            </th>
          </tr>
         <%}%>
        </tbody>
      </table>
    </div>
    
    </form>
  </main>
  <footer class="pt-5 my-5 text-muted border-top">
    2069
  </footer>
</div>


    <script src="dist/js/bootstrap.bundle.min.js" ></script>


  </body>


</html>

