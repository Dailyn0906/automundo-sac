
package pe.automundo.dto;

public class ItemCarritoDTO {

    private ProductoDTO producto;
    private int cantidad;

    public ItemCarritoDTO() {
    }

    public ItemCarritoDTO(ProductoDTO producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ProductoDTO getProducto() {
        return producto;
    } 

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
