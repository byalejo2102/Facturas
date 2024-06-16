import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class ProductosForm extends JFrame {
    private JTextField codigoField, descripcionField, saldoInicialField, ingresosField, egresosField;
    private JTextField ajustesField, saldoFinalField, costoUMField, precioUMField;
    private JComboBox<String> unidadMedidaCombo, estadoCombo;

    public ProductosForm() {
        setTitle("Datos del Producto - Editar datos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Crear etiquetas y campos de texto
        JLabel codigoLabel = new JLabel("Código del producto:");
        JLabel descripcionLabel = new JLabel("Descripción:");
        JLabel unidadMedidaLabel = new JLabel("Unidad de Medida:");
        JLabel saldoInicialLabel = new JLabel("Saldo Inicial:");
        JLabel ingresosLabel = new JLabel("Ingresos:");
        JLabel egresosLabel = new JLabel("Egresos:");
        JLabel ajustesLabel = new JLabel("Ajustes:");
        JLabel saldoFinalLabel = new JLabel("Saldo Final:");
        JLabel costoUMLabel = new JLabel("Costo Unidad Medida:");
        JLabel precioUMLabel = new JLabel("Precio Unidad Medida:");
        JLabel estadoLabel = new JLabel("Estado:");

        codigoField = new JTextField(20);
        descripcionField = new JTextField(20);
        saldoInicialField = new JTextField(20);
        ingresosField = new JTextField(20);
        egresosField = new JTextField(20);
        ajustesField = new JTextField(20);
        saldoFinalField = new JTextField(20);
        costoUMField = new JTextField(20);
        precioUMField = new JTextField(20);

        unidadMedidaCombo = new JComboBox<>(new String[]{"kg", "gr", "lt", "ml", "un", "m", "cm", "mm"});
        estadoCombo = new JComboBox<>(new String[]{"des", "exp", "ret", "sus", "esc", "act"});

        JButton guardarButton = new JButton("Guardar");
        JButton actualizarButton = new JButton("Actualizar");
        JButton eliminarButton = new JButton("Eliminar");
        JButton buscarButton = new JButton("Buscar");
        JButton verProductosButton = new JButton("Ver Productos");
        JButton irAClientesButton = new JButton("Ir a Clientes");
        JButton verFacturaButton = new JButton("Ver Factura");

        // Colocar componentes en el contenedor
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(codigoLabel, gbc);
        gbc.gridx = 1;
        add(codigoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(descripcionLabel, gbc);
        gbc.gridx = 1;
        add(descripcionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(unidadMedidaLabel, gbc);
        gbc.gridx = 1;
        add(unidadMedidaCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(saldoInicialLabel, gbc);
        gbc.gridx = 1;
        add(saldoInicialField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(ingresosLabel, gbc);
        gbc.gridx = 1;
        add(ingresosField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(egresosLabel, gbc);
        gbc.gridx = 1;
        add(egresosField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(ajustesLabel, gbc);
        gbc.gridx = 1;
        add(ajustesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(saldoFinalLabel, gbc);
        gbc.gridx = 1;
        add(saldoFinalField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(costoUMLabel, gbc);
        gbc.gridx = 1;
        add(costoUMField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(precioUMLabel, gbc);
        gbc.gridx = 1;
        add(precioUMField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        add(estadoLabel, gbc);
        gbc.gridx = 1;
        add(estadoCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        add(guardarButton, gbc);
        gbc.gridy = 12;
        add(actualizarButton, gbc);
        gbc.gridy = 13;
        add(eliminarButton, gbc);
        gbc.gridy = 14;
        add(buscarButton, gbc);
        gbc.gridy = 15;
        add(verProductosButton, gbc);
        gbc.gridy = 16;
        add(irAClientesButton, gbc);
        gbc.gridy = 17;
        add(verFacturaButton, gbc);

        // Acción del botón Guardar
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        // Acción del botón Buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        // Acción del botón Actualizar
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });

        // Acción del botón Eliminar
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        // Acción del botón Ver Productos
        verProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerProductosForm().setVisible(true);
            }
        });

        // Acción del botón Ir a Clientes
        irAClientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteForm().setVisible(true);
                ProductosForm.this.dispose();
            }
        });

        // Acción del botón Ver Factura
        verFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FacturaForm().setVisible(true);
                ProductosForm.this.dispose();
            }
        });

        setLocationRelativeTo(null);
    }

    private void guardarProducto() {
        String codigo = codigoField.getText();
        String descripcion = descripcionField.getText();
        String unidadMedida = (String) unidadMedidaCombo.getSelectedItem();
        String saldoInicial = saldoInicialField.getText();
        String ingresos = ingresosField.getText();
        String egresos = egresosField.getText();
        String ajustes = ajustesField.getText();
        String saldoFinal = saldoFinalField.getText();
        String costoUM = costoUMField.getText();
        String precioUM = precioUMField.getText();
        String estado = (String) estadoCombo.getSelectedItem();

        if (codigo.isEmpty() || descripcion.isEmpty() || saldoInicial.isEmpty() || ingresos.isEmpty() ||
            egresos.isEmpty() || ajustes.isEmpty() || saldoFinal.isEmpty() || costoUM.isEmpty() ||
            precioUM.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";  // Reemplaza con tu nombre de usuario real
        String password = "corina2102";  // Reemplaza con tu contraseña real

        String sql = "INSERT INTO PRODUCTOS (PROCODIGO, PRODESCRIPCION, PROUNIDADMEDIDA, PROSALDOINICIAL, PROINGRESOS, PROEGRESOS, PROAJUSTES, PROSALDOFINAL, PROCOSTOUM, PROPRECIOUM, PROSTATUS) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            pstmt.setString(2, descripcion);
            pstmt.setString(3, unidadMedida);
            pstmt.setBigDecimal(4, new BigDecimal(saldoInicial));
            pstmt.setBigDecimal(5, new BigDecimal(ingresos));
            pstmt.setBigDecimal(6, new BigDecimal(egresos));
            pstmt.setBigDecimal(7, new BigDecimal(ajustes));
            pstmt.setBigDecimal(8, new BigDecimal(saldoFinal));
            pstmt.setBigDecimal(9, new BigDecimal(costoUM));
            pstmt.setBigDecimal(10, new BigDecimal(precioUM));
            pstmt.setString(11, estado);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Producto guardado exitosamente.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el producto.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos.");
        }
    }

    private void buscarProducto() {
        String codigo = codigoField.getText();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa el código del producto para buscar.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";  // Reemplaza con tu nombre de usuario real
        String password = "corina2102";  // Reemplaza con tu contraseña real

        String sql = "SELECT * FROM PRODUCTOS WHERE PROCODIGO = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                descripcionField.setText(rs.getString("PRODESCRIPCION"));
                unidadMedidaCombo.setSelectedItem(rs.getString("PROUNIDADMEDIDA"));
                saldoInicialField.setText(rs.getString("PROSALDOINICIAL"));
                ingresosField.setText(rs.getString("PROINGRESOS"));
                egresosField.setText(rs.getString("PROEGRESOS"));
                ajustesField.setText(rs.getString("PROAJUSTES"));
                saldoFinalField.setText(rs.getString("PROSALDOFINAL"));
                costoUMField.setText(rs.getString("PROCOSTOUM"));
                precioUMField.setText(rs.getString("PROPRECIOUM"));
                estadoCombo.setSelectedItem(rs.getString("PROSTATUS"));
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar el producto.");
        }
    }

    private void actualizarProducto() {
        String codigo = codigoField.getText();
        String descripcion = descripcionField.getText();
        String unidadMedida = (String) unidadMedidaCombo.getSelectedItem();
        String saldoInicial = saldoInicialField.getText();
        String ingresos = ingresosField.getText();
        String egresos = egresosField.getText();
        String ajustes = ajustesField.getText();
        String saldoFinal = saldoFinalField.getText();
        String costoUM = costoUMField.getText();
        String precioUM = precioUMField.getText();
        String estado = (String) estadoCombo.getSelectedItem();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, busca un producto para actualizar.");
            return;
        }

        if (descripcion.isEmpty() || saldoInicial.isEmpty() || ingresos.isEmpty() ||
            egresos.isEmpty() || ajustes.isEmpty() || saldoFinal.isEmpty() ||
            costoUM.isEmpty() || precioUM.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llena todos los campos.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";  // Reemplaza con tu nombre de usuario real
        String password = "corina2102";  // Reemplaza con tu contraseña real

        String sql = "UPDATE PRODUCTOS SET PRODESCRIPCION = ?, PROUNIDADMEDIDA = ?, PROSALDOINICIAL = ?, PROINGRESOS = ?, PROEGRESOS = ?, PROAJUSTES = ?, PROSALDOFINAL = ?, PROCOSTOUM = ?, PROPRECIOUM = ?, PROSTATUS = ? WHERE PROCODIGO = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, descripcion);
            pstmt.setString(2, unidadMedida);
            pstmt.setBigDecimal(3, new BigDecimal(saldoInicial));
            pstmt.setBigDecimal(4, new BigDecimal(ingresos));
            pstmt.setBigDecimal(5, new BigDecimal(egresos));
            pstmt.setBigDecimal(6, new BigDecimal(ajustes));
            pstmt.setBigDecimal(7, new BigDecimal(saldoFinal));
            pstmt.setBigDecimal(8, new BigDecimal(costoUM));
            pstmt.setBigDecimal(9, new BigDecimal(precioUM));
            pstmt.setString(10, estado);
            pstmt.setString(11, codigo);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Producto actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el producto.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos.");
        }
    }

    private void eliminarProducto() {
        String codigo = codigoField.getText();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, busca un producto para eliminar.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";  // Reemplaza con tu nombre de usuario real
        String password = "corina2102";  // Reemplaza con tu contraseña real

        String sql = "DELETE FROM PRODUCTOS WHERE PROCODIGO = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Producto eliminado exitosamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el producto.");
        }
    }

    private void limpiarCampos() {
        codigoField.setText("");
        descripcionField.setText("");
        saldoInicialField.setText("");
        ingresosField.setText("");
        egresosField.setText("");
        ajustesField.setText("");
        saldoFinalField.setText("");
        costoUMField.setText("");
        precioUMField.setText("");
        unidadMedidaCombo.setSelectedIndex(0);
        estadoCombo.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClienteForm().setVisible(true);
            }
        });
    }
}
