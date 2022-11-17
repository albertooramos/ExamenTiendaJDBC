package example;

import example.persistencia.Categoria;
import example.persistencia.PersistenciaJDBC;
import example.persistencia.Producto;

public class App {

    public static void main( String[] args )
    {

        int randomInt = (int) Math.floor(Math.random() * 1000.0);
        Producto producto = new Producto(
                randomInt,
                "Zapatos",
                "zapatos blancos",
                new Categoria(3, "aaa", "aaa"),
                25.34
        );
        boolean aniadido = new PersistenciaJDBC().aniadirProducto(producto);
        System.out.println(aniadido ? "añadido ok" : "error");
    }

}
