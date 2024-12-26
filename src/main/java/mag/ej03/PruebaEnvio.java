package mag.ej03;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class PruebaEnvio implements CommandLineRunner {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void run(String... args) throws Exception {
        try {
            System.out.println("Iniciando prueba de envío de correo...");

            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo("destinatario@gmail.com");
            mensaje.setSubject("Prueba de correo");
            mensaje.setText("Este es un correo de prueba enviado desde la aplicación Spring Boot.");
            mensaje.setFrom("gerenteprueba2024@gmail.com");

            javaMailSender.send(mensaje);

            System.out.println("Correo enviado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
}