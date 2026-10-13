
package pe.automundo.implementacion;

import java.util.ArrayList;
import java.util.List;
import pe.automundo.dao.ProductoDAO;
import pe.automundo.dto.ProductoDTO; 
 
public class ProductoDAOImpl implements ProductoDAO {

    private static final List<ProductoDTO> productos = new ArrayList<>();
    private static boolean inicializado = false;

    public ProductoDAOImpl() {
    synchronized (productos) {
        if (!inicializado) {

            productos.add(new ProductoDTO(
                    1, "Alfombras de goma universales",
                    "Pisos para autos",
                    "Juego de alfombras para automóvil",
                    89.90, 10, "img/alfombras.jpg"
            ));

            productos.add(new ProductoDTO(
                    2, "Soporte para celular",
                    "Accesorios interiores",
                    "Soporte ajustable para vehículo",
                    39.90, 15, "img/soporte-celular.jpg"
            ));

            productos.add(new ProductoDTO(
                    3, "Parlantes para automóvil",
                    "Audio", 
                    "Par de parlantes coaxiales",
                    129.90, 8, "img/parlantes.jpg"
            ));

            productos.add(new ProductoDTO(
                    4, "Fundas universales para asientos",
                    "Fundas de asientos",
                    "Fundas negras para interiores",
                    159.90, 5, "img/fundas-asientos.jpg"
            ));

            productos.add(new ProductoDTO(
                    5, "Llanta para automóvil",
                    "Llantas",
                    "Consulta las medidas disponibles",
                    299.90, 12, "img/llanta.jpg"
            ));

            productos.add(new ProductoDTO(
                    6, "Producto de mantenimiento",
                    "Mantenimiento",
                    "Producto para mantenimiento automotriz",
                    49.90, 20, "img/aceite.jpg"
            ));

            inicializado = true;
        }
    }
}

    @Override
    public List<ProductoDTO> listarTodos() {
        return new ArrayList<>(productos);
    }

    @Override
    public ProductoDTO buscarPorId(int id) {
        for (ProductoDTO producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public List<ProductoDTO> buscarPorCategoria(String categoria) {
        List<ProductoDTO> resultado = new ArrayList<>();

        for (ProductoDTO producto : productos) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(producto);
            }
        }

        return resultado;
    }

    @Override
    public boolean registrar(ProductoDTO producto) {
        if (producto == null || buscarPorId(producto.getId()) != null) {
            return false;
        }

        productos.add(producto);
        return true;
    }

    @Override 
    public boolean actualizar(ProductoDTO producto) {
        if (producto == null) {
            return false;
        }

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == producto.getId()) {
                productos.set(i, producto);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {
        return productos.removeIf(producto -> producto.getId() == id);
    }
}
