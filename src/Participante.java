import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Participante {
    
    private String nombre;
    
    private int montoAcumulado;

    public Participante(String nombre, int montoAcumulado) {
        this.nombre = nombre;
        this.montoAcumulado = montoAcumulado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMontoAcumulado() {
        return montoAcumulado;
    }

    public void setMontoAcumulado(int montoAcumulado) {
        this.montoAcumulado = montoAcumulado;
    }

    public void sumarAlAcumulado(int monto){
        this.montoAcumulado += monto;
    }

    public void guardarRegistro(String nombre, int premio){
        Connect objConexion = new Connect();

        try {
            Connection conn = objConexion.connect();
            String insert = "INSERT INTO participantes (nombre, premio) VALUES (?, ?);"; 
            PreparedStatement preparedstmt = conn.prepareStatement(insert);
            preparedstmt.setString(1, nombre);
            preparedstmt.setInt(2, premio);
            preparedstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Participante> getParticipantesDB() {

        ArrayList<Participante> participantes = new ArrayList<Participante>();

        Connect objConexion = new Connect();
        String query = "SELECT * FROM participantes ORDER BY id DESC";

        try {
            Connection conn = objConexion.connect();
            PreparedStatement preparedstmt = conn.prepareStatement(query);
            ResultSet resultSet = preparedstmt.executeQuery();

            while (resultSet.next()) {
                Participante part = new Participante(resultSet.getString("nombre"), resultSet.getInt("premio"));

                participantes.add(part);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return participantes;
    }
    
}
