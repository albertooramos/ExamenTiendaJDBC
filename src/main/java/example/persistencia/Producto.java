package example.persistencia;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Producto {
    public int id;
    public String titulo;
    public String detalle;
    public Categoria categoria;
    public double precio;
}
