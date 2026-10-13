
package pe.automundo.servicio;

import java.util.List;
import pe.automundo.dao.ProductoDAO;
import pe.automundo.dto.ProductoDTO;

public class ProductoServicio {

    private final ProductoDAO productoDAO;

    public ProductoServicio(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO; 
    }

    public List<ProductoDTO> listarProductos() {
        return productoDAO.listarTodos();
    }

    public ProductoDTO buscarProducto(int id) {
        if (id <= 0) {
            return null;
        }

        return productoDAO.buscarPorId(id);
    }

    public List<ProductoDTO> buscarPorCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            return productoDAO.listarTodos();
        }

        return productoDAO.buscarPorCategoria(categoria.trim());
    }

    public boolean registrarProducto(ProductoDTO producto) {
        if (producto == null
                || producto.getNombre() == null
                || producto.getNombre().trim().isEmpty()
                || producto.getPrecio() < 0
                || producto.getStock() < 0) {
            return false;
        }

        return productoDAO.registrar(producto);
    }

    public boolean actualizarProducto(ProductoDTO producto) {
        if (producto == null
                || producto.getId() <= 0
                || producto.getNombre() == null
                || producto.getNombre().trim().isEmpty()
                || producto.getPrecio() < 0
                || producto.getStock() < 0) {
            return false;
        }

        return productoDAO.actualizar(producto);
    }

    public boolean eliminarProducto(int id) {
        if (id <= 0) {
            return false;
        }

        return productoDAO.eliminar(id);
    }
}
