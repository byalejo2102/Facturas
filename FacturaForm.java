import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class FacturaForm extends JFrame {
    private JTextField clienteField, identificacionField, codigoClienteField, codigoProductoField;
    private JTextField descripcionField, unidadMedidaField, valorUnitarioField, cantidadField;
    private JTextField descuentoField, subtotalField, ivaField, iceField, totalField, fechaField, horaField;
    private JComboBox<String> formaPagoCombo, ivaCombo;
    private JTextField codigoFacturaField;
    private JButton buscarFacturaButton, regresarClientesButton, regresarProductosButton, actualizarButton;
    private JTable productosTable;
    private DefaultTableModel tableModel;

    private int facturaCounter = 1; // Contador para generar códigos de factura

    public FacturaForm() {
        setTitle("Factura");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Crear etiquetas y campos de texto
        JLabel codigoFacturaLabel = new JLabel("Código Factura:");
        JLabel codigoClienteLabel = new JLabel("Código cliente:");
        JLabel identificacionLabel = new JLabel("Identificación:");
        JLabel clienteLabel = new JLabel("Cliente:");
        JLabel fechaLabel = new JLabel("Fecha (aaaa/mm/dd):");
        JLabel horaLabel = new JLabel("Hora:");
        JLabel codigoProductoLabel = new JLabel("Código producto:");
        JLabel descripcionLabel = new JLabel("Descripción:");
        JLabel unidadMedidaLabel = new JLabel("Unidad Medida:");
        JLabel valorUnitarioLabel = new JLabel("Valor Unitario:");
        JLabel cantidadLabel = new JLabel("Cantidad:");
        JLabel descuentoLabel = new JLabel("Descuento:");
        JLabel ivaLabel = new JLabel("IVA:");
        JLabel iceLabel = new JLabel("ICE:");
        JLabel subtotalLabel = new JLabel("Subtotal:");
        JLabel totalLabel = new JLabel("Total:");
        JLabel formaPagoLabel = new JLabel("Forma de Pago:");

        codigoFacturaField = new JTextField(10);
        clienteField = new JTextField(10);
        identificacionField = new JTextField(10);
        codigoClienteField = new JTextField(10);
        codigoProductoField = new JTextField(10);
        descripcionField = new JTextField(10);
        unidadMedidaField = new JTextField(10);
        valorUnitarioField = new JTextField(10);
        cantidadField = new JTextField(10);
        descuentoField = new JTextField(10);
        subtotalField = new JTextField(10);
        subtotalField.setEditable(false);
        ivaField = new JTextField(10);
        iceField = new JTextField(10);
        totalField = new JTextField(10);
        totalField.setEditable(false);
        fechaField = new JTextField(10);
        horaField = new JTextField(10);

        formaPagoCombo = new JComboBox<>(new String[]{"EFECT", "TARJE", "TRANS"});
        ivaCombo = new JComboBox<>(new String[]{"0%", "8%", "12%", "15%"});

        buscarFacturaButton = new JButton("Buscar Factura");
        JButton buscarClienteButton = new JButton("Buscar");
        JButton buscarProductoButton = new JButton("Buscar");
        JButton verDetalleFacturaButton = new JButton("Ver Detalle Factura");
        JButton eliminarFacturaButton = new JButton("Eliminar Factura");
        actualizarButton = new JButton("Actualizar");
        regresarClientesButton = new JButton("Ir a Clientes");
        regresarProductosButton = new JButton("Ir a Productos");

        // Colocar componentes en el contenedor
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(codigoFacturaLabel, gbc);
        gbc.gridx = 1;
        add(codigoFacturaField, gbc);
        gbc.gridx = 2;
        add(buscarFacturaButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(clienteLabel, gbc);
        gbc.gridx = 1;
        add(clienteField, gbc);
        gbc.gridx = 2;
        add(buscarClienteButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(identificacionLabel, gbc);
        gbc.gridx = 1;
        add(identificacionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(codigoClienteLabel, gbc);
        gbc.gridx = 1;
        add(codigoClienteField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(fechaLabel, gbc);
        gbc.gridx = 1;
        add(fechaField, gbc);

        gbc.gridx = 2;
        add(horaLabel, gbc);
        gbc.gridx = 3;
        add(horaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(codigoProductoLabel, gbc);
        gbc.gridx = 1;
        add(codigoProductoField, gbc);
        gbc.gridx = 2;
        add(buscarProductoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(descripcionLabel, gbc);
        gbc.gridx = 1;
        add(descripcionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(unidadMedidaLabel, gbc);
        gbc.gridx = 1;
        add(unidadMedidaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(valorUnitarioLabel, gbc);
        gbc.gridx = 1;
        add(valorUnitarioField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(cantidadLabel, gbc);
        gbc.gridx = 1;
        add(cantidadField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        add(descuentoLabel, gbc);
        gbc.gridx = 1;
        add(descuentoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        add(ivaLabel, gbc);
        gbc.gridx = 1;
        add(ivaCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        add(iceLabel, gbc);
        gbc.gridx = 1;
        add(iceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        add(subtotalLabel, gbc);
        gbc.gridx = 1;
        add(subtotalField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        add(totalLabel, gbc);
        gbc.gridx = 1;
        add(totalField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 15;
        add(formaPagoLabel, gbc);
        gbc.gridx = 1;
        add(formaPagoCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 16;
        gbc.gridwidth = 2;
        add(verDetalleFacturaButton, gbc);
        gbc.gridy = 17;
        add(eliminarFacturaButton, gbc);
        gbc.gridy = 18;
        add(actualizarButton, gbc);
        gbc.gridy = 19;
        add(regresarClientesButton, gbc);
        gbc.gridy = 20;
        add(regresarProductosButton, gbc);

        setLocationRelativeTo(null);

        // Acción del botón Buscar Cliente
        buscarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });

        // Acción del botón Buscar Producto
        buscarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        // Acción del botón Ver Detalle Factura
        verDetalleFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerFacturaDetalleForm().setVisible(true);
            }
        });

        // Acción del botón Eliminar Factura
        eliminarFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarFactura();
            }

            private void eliminarFactura() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });

        // Acción del botón Buscar Factura
        buscarFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarFactura();
            }
        });

        // Acción del botón Actualizar
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarFactura();
            }
        });

        // Acción del botón Regresar a Clientes
        regresarClientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteForm().setVisible(true);
                FacturaForm.this.dispose();
            }
        });

        // Acción del botón Regresar a Productos
        regresarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductosForm().setVisible(true);
                FacturaForm.this.dispose();
            }
        });

        // Listener para actualizar Subtotal y Total
        ActionListener updateTotalsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTotales();
            }
        };

        valorUnitarioField.addActionListener(updateTotalsListener);
        cantidadField.addActionListener(updateTotalsListener);
        descuentoField.addActionListener(updateTotalsListener);
        ivaCombo.addActionListener(updateTotalsListener);

        // Generar código de factura automáticamente
        codigoFacturaField.setText(generateCodigoFactura());
    }

    private String generateCodigoFactura() {
        return String.format("F-N%04d", facturaCounter++);
    }

    private void buscarCliente() {
        String cliente = clienteField.getText();
        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa el nombre del cliente para buscar.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";
        String password = "corina2102";

        String sql = "SELECT * FROM CLIENTES WHERE CLINOMBRE = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                identificacionField.setText(rs.getString("CLIIDENTIFICACION"));
                codigoClienteField.setText(rs.getString("CLICODIGO"));
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar el cliente.");
        }
    }
    private void eliminarCliente() {
    String codigoCliente = codigoClienteField.getText();

    if (codigoCliente.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un código de cliente válido.");
        return;
    }

    String url = "jdbc:postgresql://localhost:5432/Facturas";
    String user = "postgres";
    String password = "corina2102";

    String sqlDeleteFacturas = "DELETE FROM FACTURAS WHERE CLICODIGO = ?";
    String sqlDeleteCliente = "DELETE FROM CLIENTES WHERE CLICODIGO = ?";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmtDeleteFacturas = conn.prepareStatement(sqlDeleteFacturas);
         PreparedStatement pstmtDeleteCliente = conn.prepareStatement(sqlDeleteCliente)) {

        // Eliminar las facturas asociadas
        pstmtDeleteFacturas.setString(1, codigoCliente);
        pstmtDeleteFacturas.executeUpdate();

        // Eliminar el cliente
        pstmtDeleteCliente.setString(1, codigoCliente);
        pstmtDeleteCliente.executeUpdate();

        JOptionPane.showMessageDialog(this, "Cliente y sus facturas asociadas eliminados exitosamente.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al eliminar el cliente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    private void buscarProducto() {
        String codigoProducto = codigoProductoField.getText();
        if (codigoProducto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa el código del producto para buscar.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";
        String password = "corina2102";

        String sql = "SELECT * FROM PRODUCTOS WHERE PROCODIGO = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigoProducto);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                descripcionField.setText(rs.getString("PRODESCRIPCION"));
                unidadMedidaField.setText(rs.getString("PROUNIDADMEDIDA"));
                valorUnitarioField.setText(rs.getString("PROPRECIOUM"));
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar el producto.");
        }
    }

    private void actualizarTotales() {
        try {
            BigDecimal valorUnitario = new BigDecimal(valorUnitarioField.getText());
            BigDecimal cantidad = new BigDecimal(cantidadField.getText());
            BigDecimal descuento = new BigDecimal(descuentoField.getText());
            BigDecimal ivaPorcentaje = new BigDecimal(ivaCombo.getSelectedItem().toString().replace("%", ""));
            BigDecimal ice = iceField.getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(iceField.getText());

            BigDecimal subtotal = valorUnitario.multiply(cantidad);
            subtotalField.setText(subtotal.toString());

            BigDecimal iva = subtotal.multiply(ivaPorcentaje).divide(new BigDecimal(100));
            BigDecimal total = subtotal.subtract(descuento).add(iva).add(ice);
            totalField.setText(total.toString());

            guardarFactura(subtotal, descuento, iva, ice, total);
        } catch (NumberFormatException e) {
            // Valores no válidos
        }
    }

    private void guardarFactura(BigDecimal subtotal, BigDecimal descuento, BigDecimal iva, BigDecimal ice, BigDecimal total) {
    String codigoFactura = codigoFacturaField.getText();
    String codigoCliente = codigoClienteField.getText();
    String fecha = fechaField.getText();
    String formaPago = (String) formaPagoCombo.getSelectedItem();

    String url = "jdbc:postgresql://localhost:5432/Facturas";
    String user = "postgres";
    String password = "corina2102";

    String sql = "INSERT INTO FACTURAS (FACNUMERO, CLICODIGO, FACFECHA, FACSUBTOTAL, FACDESCUENTO, FACIVA, FACICE, FACFORMAPAGO, FACSTATUS) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'ACT')";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, codigoFactura);
        pstmt.setString(2, codigoCliente);
        pstmt.setDate(3, Date.valueOf(fecha)); // Convertir String a Date
        pstmt.setBigDecimal(4, subtotal);
        pstmt.setBigDecimal(5, descuento);
        pstmt.setBigDecimal(6, iva);
        pstmt.setBigDecimal(7, ice);
        pstmt.setString(8, formaPago);

        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(this, "Factura guardada exitosamente.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al guardar la factura.");
    }
}

private void buscarFactura() {
    String codigoFactura = codigoFacturaField.getText();

    if (codigoFactura.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa el código de la factura para buscar.");
        return;
    }

    String url = "jdbc:postgresql://localhost:5432/Facturas";
    String user = "postgres";
    String password = "corina2102";

    String sql = "SELECT * FROM FACTURAS WHERE FACNUMERO = ?";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, codigoFactura);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            codigoClienteField.setText(rs.getString("CLICODIGO"));
            clienteField.setText(rs.getString("CLICODIGO")); // Añade código de cliente al campo de cliente
            fechaField.setText(rs.getString("FACFECHA"));
            subtotalField.setText(rs.getString("FACSUBTOTAL"));
            descuentoField.setText(rs.getString("FACDESCUENTO"));
            ivaCombo.setSelectedItem(rs.getString("FACIVA") + "%");
            iceField.setText(rs.getString("FACICE"));
            totalField.setText(rs.getString("FACTOTAL")); // Corrige aquí
            formaPagoCombo.setSelectedItem(rs.getString("FACFORMAPAGO"));
        } else {
            JOptionPane.showMessageDialog(this, "Factura no encontrada.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al buscar la factura.");
    }
}

private void actualizarFactura() {
    String codigoFactura = codigoFacturaField.getText();
    String codigoCliente = codigoClienteField.getText();
    String fecha = fechaField.getText();
    String formaPago = (String) formaPagoCombo.getSelectedItem();
    BigDecimal subtotal = new BigDecimal(subtotalField.getText());
    BigDecimal descuento = new BigDecimal(descuentoField.getText());
    BigDecimal iva = new BigDecimal(ivaCombo.getSelectedItem().toString().replace("%", ""));
    BigDecimal ice = new BigDecimal(iceField.getText());
    BigDecimal total = new BigDecimal(totalField.getText());

    if (codigoFactura.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, busca una factura para actualizar.");
        return;
    }

    String url = "jdbc:postgresql://localhost:5432/Facturas";
    String user = "postgres";
    String password = "corina2102";

    String sql = "UPDATE FACTURAS SET CLICODIGO = ?, FACFECHA = ?, FACSUBTOTAL = ?, FACDESCUENTO = ?, FACIVA = ?, FACICE = ?, FACFORMAPAGO = ? WHERE FACNUMERO = ?";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, codigoCliente);
        pstmt.setDate(2, Date.valueOf(fecha)); // Convertir String a Date
        pstmt.setBigDecimal(3, subtotal);
        pstmt.setBigDecimal(4, descuento);
        pstmt.setBigDecimal(5, iva);
        pstmt.setBigDecimal(6, ice);
        pstmt.setString(7, formaPago);
        pstmt.setString(8, codigoFactura);

        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Factura actualizada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(this, "Factura no encontrada.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al actualizar la factura.");
    }
}

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new FacturaForm().setVisible(true);
        }
        });
    }
}
