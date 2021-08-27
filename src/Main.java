import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        List<Usuario> usuarios2 = new ArrayList<>();

        usuarios2.addAll(usuarios);
        usuarios2.addAll(usuarios);

        usuarios2.forEach(System.out::println);
    }
}
