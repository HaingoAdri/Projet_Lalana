/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DbConnection;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lalana.Personne;
import lalana.Point_kilo;

/**
 *
 * @author Adrienne
 */
@WebServlet(name = "FormulaireServlet", urlPatterns = {"/Formulaire"})
public class FormulaireServlet extends HttpServlet {
    DbConnection c = new DbConnection();
    Point_kilo pk = new Point_kilo();
        
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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        List<Point_kilo> listPk = pk.getRoute(c.connectToPostgres());
        List<Point_kilo> listChoix = pk.getQuoi(c.connectToPostgres());
        request.setAttribute("rn", listPk);
        request.setAttribute("choix", listChoix);
        RequestDispatcher dispatch = request.getRequestDispatcher("Formulaire.jsp");
        dispatch.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Cltrick on the + sign on the left to edit the code.">
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FormulaireServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String route = request.getParameter("pk");
        String personne = request.getParameter("personne");
        String date = request.getParameter("date");
        Date dates = Date.valueOf(date);
        String budget = request.getParameter("budget");
        double budg = Double.parseDouble(budget);
        String priorite = request.getParameter("priorite");
        int prio = Integer.parseInt(priorite);
        String nom_Prio = request.getParameter("nom_c");
        Personne p = new Personne(personne,budg,dates,prio);
        try {
            p.insertPersonne(c.connectToPostgres());
            List<Point_kilo> listePk = pk.getAllPoint_kiloWhereRoute(c.connectToPostgres(), route);
            Personne nouvellePersonne = p.getPersonne(c.connectToPostgres(), dates, personne);
            request.setAttribute("pk", route);
            request.setAttribute("personne", nouvellePersonne);
            request.setAttribute("choix", prio);
            request.setAttribute("priorite",nom_Prio);
            request.setAttribute("pkliste", listePk);
            RequestDispatcher dispacth = request.getRequestDispatcher("SuiteDorm.jsp");
            dispacth.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FormulaireServlet.class.getName()).log(Level.SEVERE, null, ex);
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
