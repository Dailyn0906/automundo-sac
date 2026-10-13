
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="pe.automundo.dto.ItemCarritoDTO"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi carrito | Automundo SAC</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f6f8fb;
            color: #202b3c;
        }

        header {
            background: white;
            padding: 22px 5%;
            border-bottom: 1px solid #e1e6ed;
        }

        header strong {
            color: #102f55;
            font-size: 23px;
        }

        main {
            width: 90%;
            max-width: 1000px;
            margin: 35px auto;
        }

        h1 {
            color: #102f55;
        }

        .volver {
            color: #102f55;
            text-decoration: none;
        }

        .tabla-carrito {
            width: 100%;
            border-collapse: collapse;
            background: white;
            border-radius: 10px;
            overflow: hidden;
        }

        .tabla-carrito th,
        .tabla-carrito td {
            padding: 16px;
            text-align: left;
            border-bottom: 1px solid #e1e6ed;
        }

        .tabla-carrito th {
            background: #102f55;
            color: white;
        }

        .precio {
            color: #d83232;
            font-weight: bold;
        }

        .total {
            margin-top: 22px;
            text-align: right;
            font-size: 24px;
            font-weight: bold;
            color: #102f55;
        }

        .boton {
            display: inline-block;
            border: 0;
            border-radius: 6px;
            padding: 11px 18px;
            background: #102f55;
            color: white;
            text-decoration: none;
            cursor: pointer;
            font-size: 14px;
        }

        .boton-eliminar {
            background: #d83232;
        }

        .acciones {
            margin-top: 25px;
            display: flex;
            gap: 12px;
            flex-wrap: wrap;
        }

        .vacio {
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        @media (max-width: 650px) {
            .tabla-carrito {
                font-size: 13px;
            }

            .tabla-carrito th,
            .tabla-carrito td {
                padding: 9px 5px;
            }
        }
    </style>
</head>

<body>

    <header>
        <strong>AUTOMUNDO SAC</strong>
        <p>Tu compra, fácil y segura</p>
    </header>

    <main>

        <a class="volver"
           href="<%= request.getContextPath() %>/index.html">
            ← Volver a la tienda
        </a>

        <h1>Mi carrito de compras</h1>

        <%
            List<ItemCarritoDTO> carrito =
                (List<ItemCarritoDTO>) request.getAttribute("carrito");

            double total =
                request.getAttribute("total") != null
                ? (Double) request.getAttribute("total")
                : 0.0;
        %>

        <% if (carrito != null && !carrito.isEmpty()) { %>

            <table class="tabla-carrito">
                <thead>
                    <tr>
                        <th>Producto</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Subtotal</th>
                        <th>Acción</th>
                    </tr>
                </thead>

                <tbody>
                    <% for (ItemCarritoDTO item : carrito) { %>
                        <tr>
                            <td>
                                <%= item.getProducto().getNombre() %>
                            </td>

                            <td>
                                S/ <%= String.format(
                                    java.util.Locale.US, "%.2f",
                                    item.getProducto().getPrecio()) %>
                            </td>

                            <td><%= item.getCantidad() %></td>

                            <td class="precio">
                                S/ <%= String.format(
                                    java.util.Locale.US, "%.2f",
                                    item.getSubtotal()) %>
                            </td>

                            <td>
                                <form method="post"
                                      action="<%= request.getContextPath() %>/carrito">
                                    <input type="hidden"
                                           name="accion"
                                           value="eliminar">

                                    <input type="hidden"
                                           name="id"
                                           value="<%= item.getProducto().getId() %>">

                                    <button class="boton boton-eliminar"
                                            type="submit">
                                        Eliminar
                                    </button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <div class="total">
                Total: S/ <%= String.format(
                    java.util.Locale.US, "%.2f", total) %>
            </div>

        <% } else { %>

            <div class="vacio">
                <p>Tu carrito está vacío.</p>
                <p>Explora nuestros accesorios automotrices.</p>
            </div>

        <% } %>

        <div class="acciones">
            <a class="boton"
               href="<%= request.getContextPath() %>/index.html">
                Seguir comprando
            </a>
        </div>

    </main>

</body>
</html>
