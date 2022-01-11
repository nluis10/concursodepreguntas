import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Respuesta {
    
    private int id;

    private int idPregunta;

    private String respuesta;

    private int correcta;

    public Respuesta(int id, int idPregunta, String respuesta, int correcta) {
        this.id = id;
        this.idPregunta = idPregunta;
        this.respuesta = respuesta;
        this.correcta = correcta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }

    public static ArrayList<Respuesta> getRespuestasDB(int idPregunta) {

        ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();

        Connect objConexion = new Connect();
        String query = "SELECT * FROM respuestas WHERE idPregunta = ? ";

        try {
            Connection conn = objConexion.connect();
            PreparedStatement preparedstmt = conn.prepareStatement(query);
            preparedstmt.setInt(1, idPregunta);
            ResultSet resultSet = preparedstmt.executeQuery();

            while (resultSet.next()) {
                Respuesta resp = new Respuesta(resultSet.getInt("id"), resultSet.getInt("idPregunta"), resultSet.getString("respuesta"), resultSet.getInt("correcta"));

                respuestas.add(resp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return respuestas;
    }

    public void mostrarRespuestas(){
        
    }
    
}
