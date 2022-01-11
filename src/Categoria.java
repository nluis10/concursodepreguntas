import java.util.ArrayList;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Categoria {
    
    private int id;

    private int idPremio;

    private String nombre;

    public Categoria(int id, int idPremio, String nombre) {
        this.id = id;
        this.idPremio = idPremio;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPremio() {
        return idPremio;
    }

    public void setIdPremio(int idPremio) {
        this.idPremio = idPremio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public static ArrayList<Categoria> getCategoriasDB(){


        ArrayList<Categoria> categorias = new ArrayList<Categoria>();

        Connect objConexion = new Connect();
        String query = "SELECT * FROM categorias";
        
        try (Connection conn = objConexion.connect(); Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            
            while (resultSet.next()) {
                Categoria cat = new Categoria(resultSet.getInt("id"),resultSet.getInt("idPremio"),resultSet.getString("nombre"));
                
                categorias.add(cat);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categorias;
    }

}
