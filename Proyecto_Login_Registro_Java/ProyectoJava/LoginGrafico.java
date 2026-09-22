import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGrafico extends JFrame implements ActionListener {

    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private JCheckBox chkMostrar;
    private char defaultEcho;

    private final String USUARIO_CORRECTO = "admin";
    private final String CLAVE_CORRECTA = "1234";

    public LoginGrafico() {
        setTitle("Inicio de Sesión Seguro");
        setSize(420, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        GradientPanel content = new GradientPanel();
        content.setLayout(new GridBagLayout());
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(content);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);

        // Avatar grande
        JLabel lblAvatar;
        try {
            ImageIcon imgOriginal = new ImageIcon("candado.png");
            Image imgEscalada = imgOriginal.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            lblAvatar = new JLabel(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            lblAvatar = new JLabel("🔒");
            lblAvatar.setFont(new Font("Segoe UI", Font.PLAIN, 72));
            lblAvatar.setHorizontalAlignment(SwingConstants.CENTER);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        content.add(lblAvatar, gbc);

        // Título
        JLabel titulo = new JLabel("Bienvenido");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        content.add(titulo, gbc);

        // Formulario
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.gridx = 0;
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        content.add(lblUsuario, gbc);

        gbc.gridx = 1;
        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setPreferredSize(new Dimension(200, 28));
        content.add(txtUsuario, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        JLabel lblClave = new JLabel("Contraseña:");
        lblClave.setForeground(Color.WHITE);
        lblClave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        content.add(lblClave, gbc);

        gbc.gridx = 1;
        txtClave = new JPasswordField();
        txtClave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtClave.setPreferredSize(new Dimension(200, 28));
        defaultEcho = txtClave.getEchoChar();
        content.add(txtClave, gbc);

        // Mostrar contraseña
        gbc.gridy = 4;
        gbc.gridx = 1;
        chkMostrar = new JCheckBox("Mostrar contraseña");
        chkMostrar.setBackground(new Color(0,0,0,0));
        chkMostrar.setForeground(Color.WHITE);
        chkMostrar.addActionListener(ev -> {
            if (chkMostrar.isSelected()) txtClave.setEchoChar((char)0);
            else txtClave.setEchoChar(defaultEcho);
        });
        content.add(chkMostrar, gbc);

        // Botón Ingresar estilizado
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setBackground(new Color(26, 188, 156));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnIngresar.addActionListener(this);
        btnIngresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) { btnIngresar.setBackground(new Color(22,160,133)); }
            @Override
            public void mouseExited(MouseEvent e) { btnIngresar.setBackground(new Color(26,188,156)); }
        });
        content.add(btnIngresar, gbc);

        // Enlaces y pie
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JLabel link = new JLabel("¿Olvidaste tu contraseña?");
        link.setForeground(new Color(236,240,241));
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(LoginGrafico.this, "Contacta al administrador para recuperar tu contraseña.", "Recuperar contraseña", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        link.setHorizontalAlignment(SwingConstants.CENTER);
        content.add(link, gbc);

        // Enlace para ir al Registro de Usuario
        gbc.gridy = 7;
        JLabel linkRegistro = new JLabel("¿No tienes cuenta? Regístrate");
        linkRegistro.setForeground(new Color(236,240,241));
        linkRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        linkRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        linkRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new FormularioRegistro().setVisible(true);
                dispose();
            }
        });
        content.add(linkRegistro, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngresar) {
            String usuario = txtUsuario.getText();
            String clave = new String(txtClave.getPassword());

            if (usuario.equals(USUARIO_CORRECTO) && clave.equals(CLAVE_CORRECTA)) {
                JOptionPane.showMessageDialog(this, "¡Acceso Concedido! Bienvenido.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginGrafico ventana = new LoginGrafico();
            ventana.setVisible(true);
        });
    }

    // Panel con fondo degradado
    static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth();
            int h = getHeight();
            Color color1 = new Color(52, 152, 219);
            Color color2 = new Color(44, 62, 80);
            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
            g2.setPaint(gp);
            g2.fillRect(0, 0, w, h);
            g2.dispose();
        }
    }
}
