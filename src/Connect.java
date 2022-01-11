import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
   
    public Connection connect() {
        // Ruta donde está al db creada
        
        String url = "jdbc:sqlite:src/concurso.db";
        Connection conn = null;
        try {
            // Creamos la conexión
           
            conn = DriverManager.getConnection(url);
            //System.out.println("Conectado a la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error en la conexion");
            System.out.println(e.getMessage());
        }
        return conn;
    }

} 
