package lalana;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lalana.Point_kilo;

public class Liaison_Lalana{
    
    int id;
    String personne;
    int point_kilo;
    int etats;
    int etats_nom;
    int types;
    int remplacement;
    double cout_reparation;
    String nom;
    String type;
    int coeff;
    String pk;
    int priorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonne() {
        return personne;
    }

    public void setPersonne(String personne) {
        this.personne = personne;
    }

    public int getPoint_kilo() {
        return point_kilo;
    }

    public void setPoint_kilo(int point_kilo) {
        this.point_kilo = point_kilo;
    }

    public int getEtats() {
        return etats;
    }

    public void setEtats(int etats) {
        this.etats = etats;
    }

    public int getEtats_nom() {
        return etats_nom;
    }

    public void setEtats_nom(int etats_nom) {
        this.etats_nom = etats_nom;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }

    public int getRemplacement() {
        return remplacement;
    }

    public void setRemplacement(int remplacement) {
        this.remplacement = remplacement;
    }

    public double getCout_reparation() {
        return cout_reparation;
    }

    public void setCout_reparation(double cout_reparation) {
        this.cout_reparation = cout_reparation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCoeff() {
        return coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }
    
    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }
    

    public Liaison_Lalana(int id, String personne, int point_kilo, int etats, int types, int remplacement) {
        this.setId(id);
        this.setPersonne(personne);
        this.setPoint_kilo(point_kilo);
        this.setEtats(etats);
        this.setTypes(types);
        this.setRemplacement(remplacement);
    }

    public Liaison_Lalana(String personne, int point_kilo, int etats, int types, int remplacement) {
        this.setPersonne(personne);
        this.setPoint_kilo(point_kilo);
        this.setEtats(etats);
        this.setTypes(types);
        this.setRemplacement(remplacement);
    }
    
    public Liaison_Lalana(String personne, String point_kilo, int etats,String nom, int coef,String type, double reparation, int prio) {
        this.setPersonne(personne);
        this.setPk(point_kilo);
        this.setEtats(etats);
        this.setNom(nom);
        this.setCoeff(coef);
        this.setType(type);
        this.setCout_reparation(reparation);
        this.setPriorite(prio);
    }

    public Liaison_Lalana() {
    }

    public Liaison_Lalana(String personne, int point_kilo, int etats,String nom, int coef,String type, double reparation) {
        this.setPersonne(personne);
        this.setPoint_kilo(point_kilo);
        this.setEtats(etats);
        this.setNom(nom);
        this.setCoeff(coef);
        this.setType(type);
        this.setCout_reparation(reparation);
    }

    public void insertLiaison_Lalana(Connection c) throws SQLException{
        String sql = "insert into liaison(personne,point_kilometrique,etats,etat_nom,coefficient,type_coeff,cout_reparation) values('"+this.getPersonne()+"',"+this.getPoint_kilo()+","+this.getEtats()+",'"+this.getNom()+"',"+this.getCoeff()+",'"+this.getType()+"',"+this.getCout_reparation()+")";
        PreparedStatement st = c.prepareStatement(sql);
        st.executeUpdate();
        System.out.println(sql);
    }

    public List<Liaison_Lalana> getListeProposition(Connection c, String personne) throws SQLException{
        String sql = "select v_liaison.*, (etats*coefficient) as priorite, etats, coefficient from v_liaison where v_liaison.personne='"+personne+"' order by priorite asc";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        List<Liaison_Lalana> listeLalana = new ArrayList<>();
        while(rs.next()){
            String personnes = rs.getString("personne");
            String point_kilos = rs.getString("nom_point");
            int etatss = rs.getInt("etats");
            String noms =  rs.getString("etat_nom");
            int coeffs = rs.getInt("coefficient");
            String nom_coeff= rs.getString("type_coeff");
            Double cout_remplacement = rs.getDouble("cout_total");
            int prio = rs.getInt("priorite");
            Liaison_Lalana Liaison_Lalana = new Liaison_Lalana(personnes, point_kilos, etatss,noms, coeffs,nom_coeff,cout_remplacement,prio);
            listeLalana.add(Liaison_Lalana);
        }
        System.out.println(sql);
        return listeLalana;
    }
    
    public double getSomme(Connection c, String perso) throws SQLException{
        String sql = "select sum(cout_total) as total from v_liaison where personne='"+perso+"'";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        double traite = 0;
        double somme = 0;
        while(rs.next()){
            traite = rs.getDouble("total");
            somme = traite;
        }
        return somme;
    }

    public double getReste(double budget, double total){
        return budget-total;
    }
}