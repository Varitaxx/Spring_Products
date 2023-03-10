package eu.asgardschmiede.sproducts;

import eu.asgardschmiede.sproducts.model.Category;
import eu.asgardschmiede.sproducts.model.Product;
import eu.asgardschmiede.sproducts.model.User;
import eu.asgardschmiede.sproducts.repository.CategoryRepository;
import eu.asgardschmiede.sproducts.repository.ProductRepository;
import eu.asgardschmiede.sproducts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@SpringBootApplication // enthält auch die @Configuration Annotation
public class SpringProductsApplication implements WebMvcConfigurer, CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Value("${db.reset}") // greift auf den Wert aus application.yaml zu
    private boolean dbReset;

    @Value("${ui.language}")
    private String uiLanguage;

    public static void main(String[] args) {
        SpringApplication.run(SpringProductsApplication.class, args);
    }

    // Über Beans kann ich bereits vorhandene, vorkonfigurierte Bestandteile von Spring
    // ersetzen und damit das Verhalten anpassen
    // Erlaubt das Ändern der Sprache über wechsel der Locale
    @Bean
    public LocaleResolver localeResolver() {
        Locale.setDefault(new Locale(uiLanguage));
        /*CookieLocaleResolver resolver = new CookieLocaleResolver(); // Sprache wird im Cookie des Browser gespeichert
        resolver.setCookieName("eiertanz"); //Name des Cookies
        resolver.setCookieMaxAge(60*60*24*30); //Lebensdauer des Cookies
         */
        SessionLocaleResolver resolver = new SessionLocaleResolver(); // Sprache wird in der Session des Servers gespeichert
        resolver.setDefaultLocale(new Locale(uiLanguage));
        return resolver;
    }

    @Override
    public void run(String... args) throws Exception {

        if(dbReset) {
            userRepository.deleteAll();
            User admin = new User("p.parker@shield.com", "geheim123");
            userRepository.save(admin);

            categoryRepository.deleteAll();
            Category c1 = new Category("Spielzeug");
            Category c2 = new Category("Haushalt");
            categoryRepository.saveAll(List.of(c1, c2));

            productRepository.deleteAll();
            Product p1 = new Product("Krokodoc", "Spiel für Kinder", 19.99, c1, LocalDateTime.now());
            Product p2 = new Product("Tasse", "Keramik, gelb", 7.99, c2, LocalDateTime.now());
            Product p3 = new Product("Tasse", "Keramik, blau", 7.99, c2, LocalDateTime.now());
            productRepository.saveAll(List.of(p1, p2, p3));
        }
    }

    //Erlaubt das Ändern der Sprache über die URL, http://www.domain.de?lang=de
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

}
