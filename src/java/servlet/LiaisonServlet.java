/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lalana.Liaison_Lalana;
import lalana.Point_kilo;

/**
 *
 * @author Adrienne
 */
@WebServlet(name = "LiaisonServlet", urlPatterns = {"/Liaison"})
public class LiaisonServlet extends HttpServlet {
    DbConnection c= new DbConnection();
    Point_kilo pk = new Point_kilo();
    Liaison_Lalana lalana = new Liaison_Lalana();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LiaisonServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LiaisonServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String personne = request.getParameter("nom_personne");
        String choix = request.getParameter("choix");
        String choix_nom = request.getParameter("choix_nom");
        String date = request.getParameter("date");
        String budget = request.getParameter("prix");
        Integer ch = Integer.parseInt(choix);
        String pks = request.getParameter("pk");
        String ett = request.getParameter("etat");
        String[] pok = pks.split("[;:]");
        String[] etatt = ett.split("[;:]");
        
        try {
            for(int i = 0; i<pok.length; i++){
                int ll = Integer.parseInt(pok[i]);
                int etats = Integer.parseInt(etatt[i]);
                Point_kilo etats_nom = pk.getEtats(c.connectToPostgres(), etats);
                List<Point_kilo> pkListe = pk.getAllPoint_kiloWhere(c.connectToPostgres(), ll);
                for(Point_kilo pp : pkListe){
                    if(ch == 1){
                        Liaison_Lalana liaison = new Liaison_Lalana(personne,pp.getId(),etats_nom.getId(),etats_nom.getNom(),pp.getCoefficientSanitaire(),choix_nom,pp.getReparationSanitaire());
                        liaison.insertLiaison_Lalana(c.connectToPostgres());
                    }else if( ch== 2){
                        Liaison_Lalana liaison = new Liaison_Lalana(personne,pp.getId(),etats_nom.getId(),etats_nom.getNom(),pp.getCoefficientEsthetic(),choix_nom,pp.getReparationEsthetic());
                        liaison.insertLiaison_Lalana(c.connectToPostgres());
                    }
                }
            }
            
            List<Liaison_Lalana> listePriorite = lalana.getListeProposition(c.connectToPostgres(), personne);
            double total = lalana.getSomme(c.connectToPostgres(), personne);
            double pp = Double.parseDouble(budget);
            double reste = lalana.getReste(pp, total);
            request.setAttribute("pers", personne);
            request.setAttribute("date", date);
            request.setAttribute("prix",budget);
            request.setAttribute("ch",choix);
            request.setAttribute("total", total);
            request.setAttribute("reste", reste);
            request.setAttribute("liste", listePriorite);
            int ne = 10;
            
            request.setAttribute("ne",ne);
            RequestDispatcher dispatch = request.getRequestDispatcher("Liste.jsp");
            dispatch.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LiaisonServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
