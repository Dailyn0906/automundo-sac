
package pe.automundo.controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import pe.automundo.dao.ProductoDAO;
import pe.automundo.dto.ProductoDTO;
import pe.automundo.implementacion.ProductoDAOImpl;
import pe.automundo.servicio.ProductoServicio;

@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {

    private ProductoServicio productoServicio;

    @Override 
    public void init() throws ServletException {
        ProductoDAO productoDAO = new ProductoDAOImpl();
        productoServicio = new ProductoServicio(productoDAO);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String categoria = request.getParameter("categoria");

        List<ProductoDTO> productos;

        if (categoria == null || categoria.trim().isEmpty()) {
            productos = productoServicio.listarProductos();
        } else {
            productos = productoServicio.buscarPorCategoria(categoria);
        }

        request.setAttribute("productos", productos);

        request.getRequestDispatcher("/WEB-INF/views/productos.jsp")
               .forward(request, response);
    }
}

