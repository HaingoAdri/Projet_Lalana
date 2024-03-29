<%@page import="lalana.Liaison_Lalana"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String personne = (String)request.getAttribute("pers");
    String date = (String)request.getAttribute("date");
    String prix = (String)request.getAttribute("prix");
    String ch = (String)request.getAttribute("ch");
    Double total = (Double)request.getAttribute("total");
    Double reste = (Double)request.getAttribute("reste");
    Integer ne = (Integer)request.getAttribute("ne");
    List<Liaison_Lalana> liste = (List<Liaison_Lalana>)request.getAttribute("liste");
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
      <span class="fs-4">🛣 Projet Lalana / <a href="FormulaireServlet" class="text-dark text-decoration-none">Insertion </a>  /  <a href="" class="text-dark text-decoration-none">Proposition </a></span>
    </a>
  </header>
 
  <main>
    
    <div class="row g-5">
      <div class="col-md-3">
        <div class="mb-3">
          <label for="exampleFormControlInput1" class="form-label">Personne :<%=personne%></label>
        </div>
      </div>
      <div class="col-md-3">
        <div class="mb-3">
          <label for="exampleFormControlInput1" class="form-label">Date :<%=date%></label>
          
        </div>
      </div>
      <div class="col-md-3">
        <div class="mb-3">
          <label for="exampleFormControlInput1" class="form-label">Budget :<%=prix%></label>
          
        </div>
      </div>
      <div class="col-md-3">
        <div class="mb-3">
          <label for="exampleFormControlInput1" class="form-label">Traitement:<%=ch%></label>
          
        </div>
      </div>
    </div>
    <div class="table">
      <table class="table table-striped table-bordered">
        <thead>
          <tr>
             
            <th scope="col">Traitement</th>
            <th scope="col">Pk</th>
            <th scope="col">Etats</th>
            <th scope="col">EtatsNouveau</th>
            <th scope="col">Priorite</th>
            <th scope="col">Coefficient</th>
            <th scope="col">Reparation</th>
            <th scope="col">Cout</th>   
          </tr>
        </thead>
        <tbody>
          <%for(Liaison_Lalana la : liste){
            
          %>
          <tr>
              <th><%=la.getType()%></th>
            <th scope="row"><%=la.getPk()%></th>
            <td><%=la.getEtats()%></td>
            <td>10</td>
            <td><%=la.getPriorite()%></td>
            <td><%=la.getCoeff()%></td>
            <td>></td>
            <td><%=la.getCout_reparation()%></td>
          </tr>
          <%}%>
        </tbody>
      </table>
    </div>
    <div class="mb-3 text-end">
        <h6>Total traitement : <%=total%></h6>
    </div>
    <div class="mb-3 text-end">
        <h6>Reste somme : <%=reste%></h6>
    </div>
  </main>
  <footer class="pt-5 my-5 text-muted border-top">
    2069
  </footer>
</div>


    <script src="dist/js/bootstrap.bundle.min.js" ></script>


  </body>


</html>
