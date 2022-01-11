import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pregunta {

    private int id;

    private int idCategoria;

    private String pregunta;

    public Pregunta(int id, int idCategoria, String pregunta) {
        this.id = id;
        this.idCategoria = idCategoria;
        this.pregunta = pregunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public static ArrayList<Pregunta> getPreguntasDB(int idCategoria) {

        ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();

        Connect objConexion = new Connect();
        String query = "SELECT * FROM preguntas WHERE idCategoria = ? ";

        try {
            Connection conn = objConexion.connect();
            PreparedStatement preparedstmt = conn.prepareStatement(query);
            preparedstmt.setInt(1, idCategoria);
            ResultSet resultSet = preparedstmt.executeQuery();

            while (resultSet.next()) {
                Pregunta pre = new Pregunta(resultSet.getInt("id"), resultSet.getInt("idCategoria"),resultSet.getString("pregunta"));

                preguntas.add(pre);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return preguntas;
    }

}
