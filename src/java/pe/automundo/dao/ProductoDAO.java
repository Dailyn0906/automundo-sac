
package pe.automundo.dao;

import java.util.List;
import pe.automundo.dto.ProductoDTO;

public interface ProductoDAO {

    List<ProductoDTO> listarTodos();

    ProductoDTO buscarPorId(int id);

    List<ProductoDTO> buscarPorCategoria(String categoria);

    boolean registrar(ProductoDTO producto);

    boolean actualizar(ProductoDTO producto);

    boolean eliminar(int id);
}
