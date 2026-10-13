
package pe.automundo.servicio;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import pe.automundo.dao.ProductoDAO;
import pe.automundo.dto.ProductoDTO;

public class ProductoServicioTest {

    @Test
    public void buscarProductoConIdCeroDebeRetornarNull() {

        ProductoDAO productoDAO = new ProductoDAO() {

            @Override
            public List<ProductoDTO> listarTodos() {
                return Collections.emptyList();
            }

            @Override
            public ProductoDTO buscarPorId(int id) {
                fail("No debería consultar el DAO con un ID inválido");
                return null;
            }

            @Override
            public List<ProductoDTO> buscarPorCategoria(String categoria) {
                return Collections.emptyList();
            }

            @Override
            public boolean registrar(ProductoDTO producto) {
                return false;
            }

            @Override
            public boolean actualizar(ProductoDTO producto) {
                return false;
            }

            @Override
            public boolean eliminar(int id) {
                return false;
            }
        };

        ProductoServicio servicio = new ProductoServicio(productoDAO);

        assertNull(servicio.buscarProducto(0));
    }
}

