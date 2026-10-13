
package pe.automundo.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import pe.automundo.dao.ProductoDAO;
import pe.automundo.dto.ItemCarritoDTO;
import pe.automundo.dto.ProductoDTO;
import pe.automundo.implementacion.ProductoDAOImpl;

@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {

    private ProductoDAO productoDAO;

    @Override
    public void init() throws ServletException {
        productoDAO = new ProductoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        List<ItemCarritoDTO> carrito = obtenerCarrito(sesion);
        
        request.setAttribute("carrito", carrito);
        
        if ("contador".equals(request.getParameter("accion"))) {
            int cantidad = carrito.stream()
                    .mapToInt(ItemCarritoDTO::getCantidad)
                    .sum();

            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().print(cantidad);
            return;
        }

        request.setAttribute("cantidadCarrito", carrito.stream()
        .mapToInt(ItemCarritoDTO::getCantidad)
        .sum());

        double total = 0;

        for (ItemCarritoDTO item : carrito) {
            total += item.getSubtotal();
        }

        request.setAttribute("total", total);
        request.getRequestDispatcher(
                "/WEB-INF/views/carrito.jsp"
        ).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        HttpSession sesion = request.getSession();
 
        List<ItemCarritoDTO> carrito = obtenerCarrito(sesion);
        request.setAttribute("carrito", carrito);
        System.out.println("Accion recibida: " + accion);
        System.out.println("ID recibido: " + request.getParameter("id"));
        System.out.println("Cantidad recibida: " + request.getParameter("cantidad"));

        if ("agregar".equals(accion)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                ProductoDTO producto = productoDAO.buscarPorId(id);

                System.out.println("Producto encontrado: " + (producto != null));

                if (producto == null) {
                    System.out.println("ERROR: producto no encontrado.");
                } else if (cantidad <= 0) {
                    System.out.println("ERROR: cantidad invalida.");
                } else if (cantidad > producto.getStock()) {
                    System.out.println("ERROR: stock insuficiente.");
                } else {
                    ItemCarritoDTO existente = null;

                    for (ItemCarritoDTO item : carrito) {
                        if (item.getProducto().getId() == id) {
                            existente = item;
                            break;
                        }
                    }

                    if (existente != null) {
                        int nuevaCantidad = existente.getCantidad() + cantidad;

                        if (nuevaCantidad <= producto.getStock()) {
                            existente.setCantidad(nuevaCantidad);
                        } else {
                            System.out.println("ERROR: stock insuficiente para agregar mas unidades.");
                        }
                    } else {
                        carrito.add(new ItemCarritoDTO(producto, cantidad));
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("ERROR: ID o cantidad no numericos.");
            }

        } else if ("eliminar".equals(accion)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));

                carrito.removeIf(
                    item -> item.getProducto().getId() == id
                );

            } catch (NumberFormatException e) {
                System.out.println("ERROR: ID invalido al eliminar.");
            }
        }

        sesion.setAttribute("carrito", carrito);
        System.out.println("SESION: " + sesion.getId());
        System.out.println("CARRITO GUARDADO: " + carrito);

        System.out.println("Productos en carrito: " + carrito.size());

        response.sendRedirect(request.getContextPath() + "/carrito");
    }


    @SuppressWarnings("unchecked")
    private List<ItemCarritoDTO> obtenerCarrito(HttpSession sesion) {

        List<ItemCarritoDTO> carrito =
                (List<ItemCarritoDTO>) sesion.getAttribute("carrito");

        if (carrito == null) {
            carrito = new ArrayList<>();
            sesion.setAttribute("carrito", carrito);
        }

        return carrito;
    }
}
