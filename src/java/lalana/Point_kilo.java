package lalana;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Point_kilo{
    
    
    int id;
    int coefficientEsthetic;
    int coefficientSanitaire;
    String nom;
    int priorite;
    double reparationSanitaire;
    double reparationEsthetic;
    String route;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCoefficientEsthetic() {
        return coefficientEsthetic;
    }
    public void setCoefficientEsthetic(int coefficientEsthetic) {
        this.coefficientEsthetic = coefficientEsthetic;
    }
    public int getCoefficientSanitaire() {
        return coefficientSanitaire;
    }
    public void setCoefficientSanitaire(int coefficientSanitaire) {
        this.coefficientSanitaire = coefficientSanitaire;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getPriorite() {
        return priorite;
    }
    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }
    public double getReparationSanitaire() {
        return reparationSanitaire;
    }
    public void setReparationSanitaire(double reparationSanitaire) {
        this.reparationSanitaire = reparationSanitaire;
    }
    public double getReparationEsthetic() {
        return reparationEsthetic;
    }
    public void setReparationEsthetic(double reparationEsthetic) {
        this.reparationEsthetic = reparationEsthetic;
    }
    
    public Point_kilo(int id, int coefficientEsthetic, int coefficientSanitaire, String nom, double reparationSanitaire,
            double reparationEsthetic, int distance) {
        this.setId(id);
        this.setCoefficientEsthetic(coefficientEsthetic);
        this.setCoefficientSanitaire(coefficientSanitaire);
        this.setNom(nom);
        this.setReparationSanitaire(reparationSanitaire);
        this.setReparationEsthetic(reparationEsthetic);
        this.setPriorite(distance);
    }
    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    

    public Point_kilo(){

    }

    public Point_kilo(int id, int etats){
        this.setId(id);
        this.setCoefficientEsthetic(etats);
    }

    public Point_kilo(int id, String etats){
        this.setId(id);
        this.setNom(etats);
    }
    public Point_kilo(String route) {
        this.setRoute(route);
    }


    public List<Point_kilo> getAllPoint_kilo(Connection c) throws SQLException{
        List<Point_kilo>  listePoint_kilo = new ArrayList<>();
        String sql = "select * from point_kilometrique";
        Statement st =  c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom_point");
            int coefficientEconimique = rs.getInt("coefEconomique");
            int coefficientJejoo = rs.getInt("coefJejoo");
            double coutRepEconomique = rs.getDouble("coutEconomique");
            double coutNetJejoo = rs.getDouble("coutJejoo");
            int prio = rs.getInt("distance");
            Point_kilo d = new Point_kilo(id, coefficientEsthetic, coefficientSanitaire,nom,
            coutRepEconomique, coutNetJejoo,prio);
            listePoint_kilo.add(d);
        }
        return listePoint_kilo;
    }

    public List<Point_kilo> getAllPoint_kiloWhere(Connection c, int i) throws SQLException{
        List<Point_kilo>  listePoint_kilo = new ArrayList<>();
        String sql = "select * from point_kilometrique where id ="+i;
        Statement st =  c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom_point");
            int coefficientEconimique = rs.getInt("coefeconomique");
            int coefficientJejoo = rs.getInt("coefjejoo");
            double coutRepEconomique = rs.getDouble("coutEconomique");
            double coutNetJejoo = rs.getDouble("coutJejoo");
            int prio = rs.getInt("distance");
            Point_kilo d = new Point_kilo(id, coefficientEconimique, coefficientJejoo,nom,
            coutRepEconomique, coutNetJejoo,prio);
            listePoint_kilo.add(d);
        }
        System.out.println(sql);
        return listePoint_kilo;
    }

    public Point_kilo getEtats(Connection c, int ids)throws SQLException{
        Point_kilo d = new Point_kilo();
        String sql = "select * from etats where id = "+ids;
        Statement st =  c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
             d = new Point_kilo(id, nom);
            
        }
        System.out.println(sql);
        return d;
    }

    public Point_kilo getChoix(Connection c, int i)throws SQLException{
        Point_kilo d = new Point_kilo();
        String sql = "select * from type_choix where id = "+i;
        Statement st =  c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            d = new Point_kilo(id, nom);
            
        }
        return d;
    }
    
    public List<Point_kilo> getQuoi(Connection c)throws SQLException{
        List<Point_kilo> listeQuoi = new ArrayList<>();
        String sql = "select * from type_choix";
        Statement st =  c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            Point_kilo d = new Point_kilo(id, nom);
            listeQuoi.add(d);
        }
        return listeQuoi;
    }

    public List<Point_kilo> getRoute(Connection c)throws SQLException{
        List<Point_kilo> listeRoute = new ArrayList<>();
        String sql = "select * from route_national";
        Statement st =  c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            String noms = rs.getString("id");
            Point_kilo d = new Point_kilo(noms);
            listeRoute.add(d);
        }
        return listeRoute;
    }
    
    public List<Point_kilo> getAllPoint_kiloWhereRoute(Connection c, String i) throws SQLException{
        List<Point_kilo>  listePoint_kilo = new ArrayList<>();
        String sql = "select * from point_kilometrique where route = '"+i+"'";
        Statement st =  c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom_point");
            int coefficientEconimique = rs.getInt("coefEconomique");
            int coefficientJejoo = rs.getInt("coefJejoo");
            double coutRepEconomique = rs.getDouble("coutEconomique");
            double coutNetJejoo = rs.getDouble("coutJejoo");
            int prio = rs.getInt("distance");
            Point_kilo d = new Point_kilo(id, coefficientEsthetic, coefficientSanitaire,nom,
            coutRepEconomique, coutNetJejoo,prio);
            listePoint_kilo.add(d);
        }
        System.out.println(sql);
        return listePoint_kilo;
    }
}