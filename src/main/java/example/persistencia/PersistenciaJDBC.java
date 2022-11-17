package example.persistencia;

import java.sql.*;

public class PersistenciaJDBC {

    private String url;
    private String usr;
    private String pass;

    public PersistenciaJDBC() {
//			Properties p = new Properties();
//			p.load(new FileInputStream("src/main/resources/propiedades.properties"));
        url = "jdbc:mysql://127.0.0.1:3307/ad_tienda?serverTimezone=UTC";
        usr = "root";
        pass = "root";

    }

    public boolean aniadirProducto(Producto producto) {
        boolean result = false;

        Connection conn = null;

        try {
            // creo conexión
            conn = DriverManager.getConnection(url, usr, pass);

            //(1) definir el setAutocommit = false
            conn.setAutoCommit(false);

            if (!this.existeCategoria(producto.getCategoria().getId())) {
                return false;
            }

            PreparedStatement ps = conn.prepareStatement("insert into productos values (?,?,?,?,?)");

            ps.setInt(1, producto.getId());
            ps.setString(2, producto.getTitulo());
            ps.setString(3, producto.getDetalle());
            ps.setInt(4, producto.getCategoria().getId());
            ps.setDouble(5, producto.getPrecio());


            // ejecutar consulta
            ps.executeUpdate();

            //(2) confirmamos las instrucciones ejecutadas
            conn.commit();

            ps.close();
            conn.close();
            result = true;
        }  catch (SQLException e) {
            try {
                //(3) si existe algún error hacer rollback
                conn.rollback();
                System.err.println("Se ha realizado el rollback en aniadirProducto");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private boolean existeCategoria(int id) {
        Boolean existe = false;

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, usr, pass);

            PreparedStatement ps = conn.prepareStatement("select * from categorias where id=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            existe = rs.next();

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return existe;
    }
}
