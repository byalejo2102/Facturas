import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    // URL de la base de datos, usuario y contraseña
    private static final String URL = "jdbc:postgresql://localhost:5432/Facturas";
    private static final String USER = "postgres";
    private static final String PASSWORD = "corina2102";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Registrar el driver de PostgreSQL
            Class.forName("org.postgresql.Driver");
            
            // Establecer la conexión
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            if (connection != null) {
                System.out.println("¡Conexión establecida con éxito!");
            } else {
                System.out.println("Conexión fallida.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("El driver de PostgreSQL no se encontró. Asegúrate de haber agregado el JAR del driver.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexión a la base de datos.");
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
