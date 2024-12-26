package mag.ej03;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FormInfo {
    @NotNull(message = "Debe introducir un nombre")
    @NotEmpty(message = "Debe introducir un nombre")
    private String nombre;

    @NotNull(message = "Debe introducir un dni")
    @NotEmpty(message = "Debe introducir un dni")
    private String dni;

    @NotNull(message = "Debe introducir un email")
    @NotEmpty(message = "Debe introducir un email")
    @Email(message = "El correo electrónico debe tener un formato válido")
    private String email;

    @NotNull(message = "Debe introducir una dirección")
    @NotEmpty(message = "Debe introducir una dirección")
    private String direccion;

    @NotNull(message = "Debe seleccionar una opcion")
    private TipoProducto tipoProducto;

    //Condición de validación para el booleano "Aceptar condiciones" debe estar seleccionado(true)
    @AssertTrue(message = "Debe aceptar las condiciones para continuar.")
    private Boolean condiciones;

    @NotNull(message = "Debe introducir un fichero")
    private MultipartFile fichero;

    //Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public Boolean getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(Boolean condiciones) {
        this.condiciones = condiciones;
    }

    
    public MultipartFile getFichero() {
        return fichero;
    }

    public void setFichero(MultipartFile fichero) {
        this.fichero = fichero;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    
}
