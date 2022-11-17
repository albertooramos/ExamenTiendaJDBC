package example.persistencia;

import lombok.Data;

import java.util.Date;

@Data
public class Pedido {
    public int Id;
    public Usuario usuario;
    public Estado estado ;
    public Date fechapedido;
}
