import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Capitulo4 {

    public static void main(String... args) {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        Consumer<Usuario> mostraMensagem = u -> System.out.println("Antes de imprimir os nomes");

        Consumer<Usuario> imprimeNome = u -> System.out.println(u.getNome());

        //O método andThen pode ser usado para compor instâncias da interface Consumer para que possam ser executadas sequencialmente.
        usuarios.forEach(mostraMensagem.andThen(imprimeNome));

        Predicate<Usuario> predicado = new Predicate<Usuario>() {
            public boolean test(Usuario u) {
                return u.getPontos() > 160;
            }
        };

        //Novos métodos da interface Collection
//        usuarios.removeIf(predicado); // O removeIf invoca o remove de uma coleção, então ela não pode ser imutável, ou um UnsupportedOperationException
//        usuarios.forEach(u -> System.out.println(u.getNome()));

        List<Usuario> usuarios2 = new ArrayList<>();
        usuarios2.add(user1);
        usuarios2.add(user2);
        usuarios2.add(user3);

        //Usemos o lambda
        usuarios2.removeIf(u -> u.getPontos() > 160);
        usuarios2.forEach(u -> System.out.println(u.getNome()));
        // Quase sempre é vantajoso utilizar um lambda em vez de criar uma classe anônima.



    }

}
