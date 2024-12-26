package mag.ej03.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeBodyPart;
import jakarta.validation.Valid;
import mag.ej03.FormInfo;
import mag.ej03.services.EmailService;
import mag.ej03.services.FormService;

@Controller
public class Controlador {

    @Autowired
    private FormService formService;

    @Autowired
    private EmailService emailService;


    @GetMapping("/")

    public String showHome(
            @RequestParam Optional<String> nombreUsuario,
            @RequestParam Optional<String> modulo,
            Model model) {

        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("nombreUsuario", nombreUsuario.orElse(""));
        model.addAttribute("modul", modulo.orElse(""));

        return "indexView"; // Nuestra página Home con bienvenida
    }

    @GetMapping("/biografia")

    public String showBiografia(Model model) {

        ArrayList<String> discografia = new ArrayList<>(Arrays.asList("Space Oddity", "Let's Dance",
                "The Man Who Sold the Word", "Heroes", "The Rise and Fall of Ziggy", "Aladdin Sane"));
        model.addAttribute("discos", discografia);
        return "biografiaView"; // pagina biografía
    }

    @GetMapping("/enlaces")

    public String showEnlaces() {

        return "enlaces-externosView"; // pagina enlaces
    }

    @GetMapping("/galeria")

    public String showGaleria() {

        return "galeria-fotosView"; // pagina galería
    }

    // RUTA GET FORMULARIO
    @GetMapping("/formulario")

    public String showForm(Model model) {

        model.addAttribute("formInfo", new FormInfo());
        return "formView"; // pagina formulario
    }

    // RUTA POST FORMULARIO
    @PostMapping("/formulario")

    public String showFormInfo(
            // Ojo! el orden importa--> BindingResult debe ir justo después del @Valid
            // ya que debe asociarse al objeto que se va a validar (formInfo) y del cuál
            // recogerá los errores de validación si los hubiera.
            @Valid @ModelAttribute("formInfo") FormInfo formInfo,
            BindingResult bindingResult,
            @RequestParam MultipartFile fichero,
            Model model) {

        // Si hay errores en el formulario mostramos nuevamente la vista
        // con los mensajes de error
        if (bindingResult.hasErrors()) {
            return "formView";
        }

        try {
            String newFileName = formService.store(fichero, formInfo.getDni());
            // devuelve el nombre definitivo con el que se ha guardado.
            // lo podríamos guardar en algún objeto
            System.out.println("Fichero almacenado:" + newFileName);

            String ruta = "uploadDir/" + newFileName;

            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(ruta);

             String cuerpoMensaje = String.format(
                    "Se ha recibido un nuevo formulario:\n\nNombre: %s\nDNI: %s\nEmail: %s\nDirección de envío: %s\nPedido: %s",
                    formInfo.getNombre(), formInfo.getDni(), formInfo.getEmail(), formInfo.getDireccion(), formInfo.getTipoProducto());

            // Enviar el correo              
            emailService.sendEmail(formInfo, "gerenteprueba2024@gmail.com", cuerpoMensaje);

        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al procesar el formulario: " + e.getMessage());
        }

        // Mensaje de formulario enviado correctamente
        model.addAttribute("formularioEnviado", "Formulario enviado correctamente");

        return "formView";
    }
}
