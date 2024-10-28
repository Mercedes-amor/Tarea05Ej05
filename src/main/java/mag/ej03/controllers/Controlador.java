package mag.ej03.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Controlador {

    // Sin Optional

    // @GetMapping("/")

    // public String showHome(
    // @RequestParam(required=false) String nombreUsuario,
    // Model model){

    // model.addAttribute("year", LocalDate.now().getYear());
    // model.addAttribute("nombreUsuario", nombreUsuario);

    // return "indexView"; //Nuestra página Home con bienvenida
    // }

    // Con Optional

    @GetMapping("/")

    public String showHome(
            @RequestParam Optional<String> nombreUsuario,
            @RequestParam Optional<String> modulo,
            Model model) {

        model.addAttribute("year", LocalDate.now().getYear());
        model.addAttribute("nombreUsuario", nombreUsuario.orElse(""));
        model.addAttribute ("modul", modulo.orElse(""));

        return "indexView"; // Nuestra página Home con bienvenida
    }

    @GetMapping("/biografia")

    public String showBiografia(Model model) {

        ArrayList<String> discografia = new ArrayList<>(Arrays.asList("Space Oddity", "Let's Dance",
                "The Man Who Sold the Word", "Heroes", "The Rise and Fall of Ziggy", "Aladdin Sane"));
        model.addAttribute("discos", discografia);
        return "biografiaView"; // pagina biografía
    }

    // @GetMapping("/enlaces")

    // public String showEnlaces(){

    // return "enlaces-externosView"; //pagina enlaces
    // }

    @GetMapping("/galeria")

    public String showGaleria() {

        return "galeria-fotosView"; // pagina galería
    }
}
