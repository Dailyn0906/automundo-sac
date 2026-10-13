
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="pe.automundo.dto.ProductoDTO"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos | Automundo SAC</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background: #f6f8fb;
            color: #202b3c;
            margin: 0;
        }

        header {
            background: #ffffff;
            border-bottom: 1px solid #e1e6ed;
            padding: 22px 5%;
        }

        header strong {
            color: #102f55;
            font-size: 23px;
        }

        header p {
            margin-bottom: 0;
            color: #687385;
        }

        main {
            width: 90%;
            max-width: 1200px;
            margin: 35px auto;
        }

        h1 {
            color: #102f55;
            margin-bottom: 8px;
        }

        .introduccion {
            color: #687385;
            margin-bottom: 25px;
        }

        .productos {
            display: grid;
            grid-template-columns: repeat(3, minmax(0, 1fr));
            gap: 20px;
        }

        .producto {
            background: #ffffff;
            border: 1px solid #e1e6ed;
            border-radius: 10px;
            overflow: hidden;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .producto:hover {
            transform: translateY(-3px);
            box-shadow: 0 5px 18px rgba(16, 47, 85, 0.08);
        }

        .producto-imagen {
            width: 100%;
            height: 220px;
            background: #f3f5f8;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }

        .producto-imagen img {
            width: 100%;
            height: 100%;
            object-fit: contain;
            padding: 12px;
        }

        .producto-info {
            padding: 18px;
        }

        .producto h2 {
            font-size: 17px;
            color: #102f55;
            min-height: 40px;
            margin: 10px 0;
        }

        .producto-descripcion {
            font-size: 14px;
            color: #687385;
            min-height: 35px;
        }

        .precio {
            font-size: 22px;
            font-weight: bold;
            color: #d83232;
            margin: 16px 0 8px;
        }

        .categoria {
            color: #687385;
            font-size: 12px;
            margin: 0;
        }

        .stock {
            color: #16803d;
            font-size: 12px;
            margin-bottom: 0;
        }

        .volver {
            display: inline-block;
            margin-bottom: 20px;
            color: #102f55;
            text-decoration: none;
        }

        .volver:hover {
            text-decoration: underline;
        }

        .sin-productos {
            background: white;
            padding: 20px;
            border-radius: 8px;
        }

        footer {
            margin-top: 45px;
            padding: 25px;
            background: #102f55;
            color: white;
            text-align: center;
            font-size: 13px;
        }

        @media (max-width: 850px) {
            .productos {
                grid-template-columns: repeat(2, minmax(0, 1fr));
            }
        }

        @media (max-width: 550px) {
            .productos {
                grid-template-columns: 1fr;
            }

            main {
                width: 92%;
            }
        }
    </style>
</head>

<body>

    <header>
        <strong>AUTOMUNDO SAC</strong>
        <p>Accesorios automotrices para tu vehículo</p>
    </header>

    <main>

        <a class="volver" href="<%= request.getContextPath() %>/index.html">
            ← Volver a la tienda
        </a>

        <h1>Nuestros productos</h1>
        <p class="introduccion">
            Encuentra accesorios que combinan calidad, comodidad y estilo.
        </p>

        <%
            List<ProductoDTO> productos =
                (List<ProductoDTO>) request.getAttribute("productos");
        %>

        <% if (productos != null && !productos.isEmpty()) { %>

            <div class="productos">

                <% for (ProductoDTO producto : productos) { %>

                    <article class="producto">

                        <div class="producto-imagen">
                            <img
                                src="<%= request.getContextPath() + "/" + producto.getImagen() %>"
                                alt="<%= producto.getNombre() %>">
                        </div>

                        <div class="producto-info">

                            <p class="categoria">
                                <%= producto.getCategoria() %>
                            </p>

                            <h2>
                                <%= producto.getNombre() %>
                            </h2>

                            <p class="producto-descripcion">
                                <%= producto.getDescripcion() %>
                            </p>

                            <p class="precio">
                                S/ <%= String.format(
                                    java.util.Locale.US,
                                    "%.2f",
                                    producto.getPrecio()
                                ) %>
                            </p>

                            <p class="stock">
                                ● Disponible
                            </p>

                        </div>

                    </article>

                <% } %>

            </div>

        <% } else { %>

            <p class="sin-productos">
                No hay productos para mostrar.
            </p>

        <% } %>

    </main>

    <footer>
        © 2026 Automundo SAC - Todos los derechos reservados
    </footer>

</body>
</html>
