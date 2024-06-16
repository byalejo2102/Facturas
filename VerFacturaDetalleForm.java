import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class VerFacturaDetalleForm extends JFrame {

    private JTable facturaTable;
    private DefaultTableModel tableModel;

    public VerFacturaDetalleForm() {
        setTitle("Detalle de Factura");
        setSize(1200, 600);  // Ajusta el tamaño de la ventana para acomodar todas las columnas
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear la tabla y el modelo de la tabla
        tableModel = new DefaultTableModel();
        facturaTable = new JTable(tableModel);

        // Añadir columnas al modelo de la tabla
        tableModel.addColumn("Código Factura");
        tableModel.addColumn("Código Cliente");
        tableModel.addColumn("Cliente");
        tableModel.addColumn("Identificación");
        tableModel.addColumn("Fecha");
        tableModel.addColumn("Código Producto");
        tableModel.addColumn("Descripción");
        tableModel.addColumn("Unidad Medida");
        tableModel.addColumn("Valor Unitario");
        tableModel.addColumn("Cantidad");
        tableModel.addColumn("Descuento");
        tableModel.addColumn("IVA");
        tableModel.addColumn("ICE");
        tableModel.addColumn("Subtotal");
        tableModel.addColumn("Total");
        tableModel.addColumn("Forma de Pago");

        // Llenar la tabla con datos desde la base de datos
        cargarFacturas();

        // Añadir la tabla a un JScrollPane y luego al JFrame
        add(new JScrollPane(facturaTable), BorderLayout.CENTER);
    }

    public void cargarFacturas() {
        tableModel.setRowCount(0); // Limpiar la tabla antes de cargar los datos

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";  // Reemplaza con tu nombre de usuario real
        String password = "corina2102";  // Reemplaza con tu contraseña real

        String sql = "SELECT F.FACNUMERO, F.CLICODIGO, C.CLINOMBRE, C.CLIIDENTIFICACION, F.FACFECHA, " +
                     "D.PROCODIGO, P.PRODESCRIPCION, P.PROUNIDADMEDIDA, P.PROPRECIOUM, " +
                     "D.DETCANTIDAD, F.FACDESCUENTO, F.FACIVA, F.FACICE, F.FACSUBTOTAL, " +
                     "(F.FACSUBTOTAL - F.FACDESCUENTO + F.FACIVA + F.FACICE) AS FACTOTAL, F.FACFORMAPAGO " +
                     "FROM FACTURAS F " +
                     "JOIN CLIENTES C ON F.CLICODIGO = C.CLICODIGO " +
                     "JOIN DETALLEFACTURA D ON F.FACNUMERO = D.FACNUMERO " +
                     "JOIN PRODUCTOS P ON D.PROCODIGO = P.PROCODIGO";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String facNumero = rs.getString("FACNUMERO");
                String cliCodigo = rs.getString("CLICODIGO");
                String cliNombre = rs.getString("CLINOMBRE");
                String cliIdentificacion = rs.getString("CLIIDENTIFICACION");
                String facFecha = rs.getString("FACFECHA");
                String proCodigo = rs.getString("PROCODIGO");
                String proDescripcion = rs.getString("PRODESCRIPCION");
                String proUnidadMedida = rs.getString("PROUNIDADMEDIDA");
                String proPrecioUM = rs.getString("PROPRECIOUM");
                String detCantidad = rs.getString("DETCANTIDAD");
                String facDescuento = rs.getString("FACDESCUENTO");
                String facIva = rs.getString("FACIVA");
                String facIce = rs.getString("FACICE");
                String facSubtotal = rs.getString("FACSUBTOTAL");
                String facTotal = rs.getString("FACTOTAL");
                String facFormaPago = rs.getString("FACFORMAPAGO");

                tableModel.addRow(new Object[]{facNumero, cliCodigo, cliNombre, cliIdentificacion, facFecha,
                                               proCodigo, proDescripcion, proUnidadMedida, proPrecioUM,
                                               detCantidad, facDescuento, facIva, facIce, facSubtotal, 
                                               facTotal, facFormaPago});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las facturas.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VerFacturaDetalleForm().setVisible(true);
            }
        });
    }
}
