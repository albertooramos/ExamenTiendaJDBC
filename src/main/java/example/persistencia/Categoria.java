package example.persistencia;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Categoria {
    public int id;
    public String titulo;
    public String descripcion;
}
