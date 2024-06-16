import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class VerClientesForm extends JFrame {

    private JTable clientesTable;
    private DefaultTableModel tableModel;

    public VerClientesForm() {
        setTitle("Ver Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear la tabla y el modelo de la tabla
        tableModel = new DefaultTableModel();
        clientesTable = new JTable(tableModel);

        // Añadir columnas al modelo de la tabla
        tableModel.addColumn("Código");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Identificación");
        tableModel.addColumn("Dirección");
        tableModel.addColumn("Teléfono");
        tableModel.addColumn("Celular");
        tableModel.addColumn("Email");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Estado");

        // Llenar la tabla con datos desde la base de datos
        cargarClientes();

        // Añadir la tabla a un JScrollPane y luego al JFrame
        add(new JScrollPane(clientesTable), BorderLayout.CENTER);
    }

    public void cargarClientes() {
        tableModel.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";  // Reemplaza con tu nombre de usuario real
        String password = "corina2102";  // Reemplaza con tu contraseña real

        String sql = "SELECT * FROM CLIENTES";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String codigo = rs.getString("CLICODIGO");
                String nombre = rs.getString("CLINOMBRE");
                String identificacion = rs.getString("CLIIDENTIFICACION");
                String direccion = rs.getString("CLIDIRECCION");
                String telefono = rs.getString("CLITELEFONO");
                String celular = rs.getString("CLICELULAR");
                String email = rs.getString("CLIEMAIL");
                String tipo = rs.getString("CLITIPO");
                String estado = rs.getString("CLISTATUS");

                tableModel.addRow(new Object[]{codigo, nombre, identificacion, direccion, telefono, celular, email, tipo, estado});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los clientes.");
        }
    }
}
