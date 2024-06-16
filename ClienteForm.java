import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ClienteForm extends JFrame {
    private JTextField codigoField, nombreField, identificacionField, direccionField;
    private JTextField telefonoField, celularField, emailField;
    private JComboBox<String> dominioEmailCombo, tipoCombo, estadoCombo;

    public ClienteForm() {
        setTitle("Datos del Cliente - Editar datos");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Crear etiquetas y campos de texto
        JLabel codigoLabel = new JLabel("Código del cliente:");
        JLabel nombreLabel = new JLabel("Nombre:");
        JLabel identificacionLabel = new JLabel("Identificación:");
        JLabel direccionLabel = new JLabel("Dirección:");
        JLabel telefonoLabel = new JLabel("Teléfono:");
        JLabel celularLabel = new JLabel("Celular:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel dominioEmailLabel = new JLabel("Dominio Email:");
        JLabel tipoLabel = new JLabel("Tipo:");
        JLabel estadoLabel = new JLabel("Estado:");

        codigoField = new JTextField(20);
        nombreField = new JTextField(20);
        identificacionField = new JTextField(20);
        direccionField = new JTextField(20);
        telefonoField = new JTextField(20);
        celularField = new JTextField(20);
        emailField = new JTextField(20);

        dominioEmailCombo = new JComboBox<>(new String[]{"@hotmail.com", "@gmail.com", "@yahoo.com"});
        tipoCombo = new JComboBox<>(new String[]{"NAT", "JUR"});
        estadoCombo = new JComboBox<>(new String[]{"ACT", "INA"});

        JButton guardarButton = new JButton("Guardar");
        JButton actualizarButton = new JButton("Actualizar");
        JButton eliminarButton = new JButton("Eliminar");
        JButton buscarButton = new JButton("Buscar");
        JButton verClientesButton = new JButton("Ver Clientes");
        JButton subirImagenButton = new JButton("Subir Imagen");
        JButton eliminarFotoButton = new JButton("Eliminar Foto");
        JButton irAProductosButton = new JButton("Ir a Productos");

        // Colocar componentes en el contenedor
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(codigoLabel, gbc);
        gbc.gridx = 1;
        add(codigoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nombreLabel, gbc);
        gbc.gridx = 1;
        add(nombreField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(identificacionLabel, gbc);
        gbc.gridx = 1;
        add(identificacionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(direccionLabel, gbc);
        gbc.gridx = 1;
        add(direccionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(telefonoLabel, gbc);
        gbc.gridx = 1;
        add(telefonoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(celularLabel, gbc);
        gbc.gridx = 1;
        add(celularField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(dominioEmailLabel, gbc);
        gbc.gridx = 1;
        add(dominioEmailCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(tipoLabel, gbc);
        gbc.gridx = 1;
        add(tipoCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(estadoLabel, gbc);
        gbc.gridx = 1;
        add(estadoCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        add(subirImagenButton, gbc);

        gbc.gridy = 11;
        gbc.gridwidth = 1;
        add(guardarButton, gbc);
        gbc.gridx = 1;
        add(actualizarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        add(eliminarButton, gbc);
        gbc.gridx = 1;
        add(buscarButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        add(verClientesButton, gbc);
        gbc.gridx = 1;
        add(eliminarFotoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        add(irAProductosButton, gbc);

        // Acción del botón Guardar
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCliente();
            }
        });

        // Acción del botón Buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });

        // Acción del botón Actualizar
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });

        // Acción del botón Eliminar
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });

        // Acción del botón Ver Clientes
        verClientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerClientesForm().setVisible(true);
            }
        });

        // Acción del botón Ir a Productos
        irAProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductosForm().setVisible(true);
                ClienteForm.this.dispose();
            }
        });

        setLocationRelativeTo(null);
    }

    private void guardarCliente() {
        String codigo = codigoField.getText();
        String nombre = nombreField.getText();
        String identificacion = identificacionField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String celular = celularField.getText();
        String email = emailField.getText() + dominioEmailCombo.getSelectedItem();
        String tipo = (String) tipoCombo.getSelectedItem();
        String estado = (String) estadoCombo.getSelectedItem();

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";  // Reemplaza con tu nombre de usuario real
        String password = "corina2102";  // Reemplaza con tu contraseña real

        String sql = "INSERT INTO CLIENTES (CLICODIGO, CLINOMBRE, CLIIDENTIFICACION, CLIDIRECCION, CLITELEFONO, CLICELULAR, CLIEMAIL, CLITIPO, CLISTATUS) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            pstmt.setString(2, nombre);
            pstmt.setString(3, identificacion);
            pstmt.setString(4, direccion);
            pstmt.setString(5, telefono);
            pstmt.setString(6, celular);
            pstmt.setString(7, email);
            pstmt.setString(8, tipo);
            pstmt.setString(9, estado);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Cliente guardado exitosamente.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el cliente.");
        }
    }

    private void buscarCliente() {
    String codigo = codigoField.getText();

    if (codigo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingresa el código del cliente para buscar.");
        return;
    }

    String url = "jdbc:postgresql://localhost:5432/Facturas";
    String user = "postgres";  // Reemplaza con tu nombre de usuario real
    String password = "corina2102";  // Reemplaza con tu contraseña real

    String sql = "SELECT * FROM CLIENTES WHERE CLICODIGO = ?";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, codigo);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            nombreField.setText(rs.getString("CLINOMBRE"));
            identificacionField.setText(rs.getString("CLIIDENTIFICACION"));
            direccionField.setText(rs.getString("CLIDIRECCION"));
            telefonoField.setText(rs.getString("CLITELEFONO"));
            celularField.setText(rs.getString("CLICELULAR"));
            String[] emailParts = rs.getString("CLIEMAIL").split("@");
            if (emailParts.length > 1) {
                emailField.setText(emailParts[0]);
                dominioEmailCombo.setSelectedItem("@" + emailParts[1]);
            } else {
                emailField.setText(rs.getString("CLIEMAIL"));
            }
            tipoCombo.setSelectedItem(rs.getString("CLITIPO"));
            estadoCombo.setSelectedItem(rs.getString("CLISTATUS"));
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al buscar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void actualizarCliente() {
        String codigo = codigoField.getText();
        String nombre = nombreField.getText();
        String identificacion = identificacionField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String celular = celularField.getText();
        String email = emailField.getText() + dominioEmailCombo.getSelectedItem();
        String tipo = (String) tipoCombo.getSelectedItem();
        String estado = (String) estadoCombo.getSelectedItem();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, busca un cliente para actualizar.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";  // Reemplaza con tu nombre de usuario real
        String password = "corina2102";  // Reemplaza con tu contraseña real

        String sql = "UPDATE CLIENTES SET CLINOMBRE = ?, CLIIDENTIFICACION = ?, CLIDIRECCION = ?, CLITELEFONO = ?, CLICELULAR = ?, CLIEMAIL = ?, CLITIPO = ?, CLISTATUS = ? WHERE CLICODIGO = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setString(2, identificacion);
            pstmt.setString(3, direccion);
            pstmt.setString(4, telefono);
            pstmt.setString(5, celular);
            pstmt.setString(6, email);
            pstmt.setString(7, tipo);
            pstmt.setString(8, estado);
            pstmt.setString(9, codigo);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al actualizar el cliente.");
        }
    }

    private void eliminarCliente() {
        String codigo = codigoField.getText();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, busca un cliente para eliminar.");
            return;
        }

        String url = "jdbc:postgresql://localhost:5432/Facturas";
        String user = "postgres";  // Reemplaza con tu nombre de usuario real
        String password = "corina2102";  // Reemplaza con tu contraseña real

        String sql = "DELETE FROM CLIENTES WHERE CLICODIGO = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al eliminar el cliente.");
        }
    }

    private void limpiarCampos() {
        codigoField.setText("");
        nombreField.setText("");
        identificacionField.setText("");
        direccionField.setText("");
        telefonoField.setText("");
        celularField.setText("");
        emailField.setText("");
        dominioEmailCombo.setSelectedIndex(0);
        tipoCombo.setSelectedIndex(0);
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
