import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Premio {
    
    private int id;

    private int monto;
    
    public Premio(int id, int monto) {
        this.id = id;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public static Premio getPremioDB(int idPremio) {

        Premio premio = new Premio(0,0);

        Connect objConexion = new Connect();
        String query = "SELECT * FROM premios WHERE id = ? ";

        try {
            Connection conn = objConexion.connect();
            PreparedStatement preparedstmt = conn.prepareStatement(query);
            preparedstmt.setInt(1, idPremio);
            ResultSet resultSet = preparedstmt.executeQuery();

            while (resultSet.next()) {
                premio = new Premio(resultSet.getInt("id"), resultSet.getInt("monto"));

 
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return premio;
    }

}
