import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class VerProductosForm extends JFrame {

    private JTable productosTable;
    private DefaultTableModel tableModel;

    public VerProductosForm() {
        setTitle("Ver Productos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear la tabla y el modelo de la tabla
        tableModel = new DefaultTableModel();
        productosTable = new JTable(tableModel);

        // Añadir columnas al modelo de la tabla
        tableModel.addColumn("Código");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Unidad Medida");
        tableModel.addColumn("Saldo Inicial");
        tableModel.addColumn("Ingresos");
        tableModel.addColumn("Egresos");
        tableModel.addColumn("Ajustes");
        tableModel.addColumn("Saldo Final");
        tableModel.addColumn("Costo UM");
        tableModel.addColumn("Precio UM");
        tableModel.addColumn("Estado");

        // Llenar la tabla con datos desde la base de datos
        cargarProductos();

        // Añadir la tabla a un JScrollPane y luego al JFrame
        add(new JScrollPane(productosTable), BorderLayout.CENTER);
    }

    public void cargarProductos() {
        tableModel.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";  // Reemplaza con tu nombre de usuario real
        String password = "corina2102";  // Reemplaza con tu contraseña real

        String sql = "SELECT * FROM PRODUCTOS";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String codigo = rs.getString("PROCODIGO");
                String descripcion = rs.getString("PRODESCRIPCION");
                String unidadMedida = rs.getString("PROUNIDADMEDIDA");
                String saldoInicial = rs.getString("PROSALDOINICIAL");
                String ingresos = rs.getString("PROINGRESOS");
                String egresos = rs.getString("PROEGRESOS");
                String ajustes = rs.getString("PROAJUSTES");
                String saldoFinal = rs.getString("PROSALDOFINAL");
                String costoUM = rs.getString("PROCOSTOUM");
                String precioUM = rs.getString("PROPRECIOUM");
                String estado = rs.getString("PROSTATUS");

                tableModel.addRow(new Object[]{codigo, descripcion, unidadMedida, saldoInicial, ingresos, egresos, ajustes, saldoFinal, costoUM, precioUM, estado});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los productos.");
        }
    }
}
