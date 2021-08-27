import java.util.Arrays;
import java.util.Collections;
import static java.util.Comparator.*;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;



public class Capitulo5 {

    public static void main(String... args) {

        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        Comparator<Usuario> comparator = new Comparator<Usuario>(){
            public int compare(Usuario u1, Usuario u2) {
                return u1.getNome().compareTo(u2.getNome());
            }
        };
        Collections.sort(usuarios, comparator);

        //Comparator é uma interface funcional
        Comparator<Usuario> comparator1 = (u1, u2) -> u1.getNome().compareTo(u2.getNome());
        Collections.sort(usuarios, comparator);

        Collections.sort(usuarios, (u1, u2) -> u1.getNome().compareTo(u2.getNome()));

        Collections.sort(usuarios, (u1, u2) -> String.CASE_INSENSITIVE_ORDER.compare(u1.getNome(), u2.getNome()));

        usuarios.forEach(u -> System.out.println(u.getNome()));


        //PODEMOS ORDERNAR UMA LISTA DE USUÁRIOS DE FORMA AINDA MAIS SUCINTA - List.sort
        usuarios.sort((u1, u2) -> u1.getNome().compareTo(u2.getNome()));

        //MÉTODOS ESTÁTICOS NA INTERFACE COMPARATOR
        Comparator<Usuario> comparator2 = Comparator.comparing(u -> u.getNome());
        usuarios.sort(comparing(u -> u.getNome())); //Foi feito o import static da clase Comparator

        List<String> palavras = Arrays.asList("Casa do Código", "Alura", "Caelum");

        palavras.sort(naturalOrder());
        palavras.forEach(p -> System.out.println(p));

        palavras.sort(reverseOrder());
        palavras.forEach(p -> System.out.println(p));

        Function<Usuario, String> extraiNome = u -> u.getNome();
        Comparator<Usuario> comparator3 = Comparator.comparing(extraiNome);
        usuarios.sort(comparator3);

        usuarios.sort(Comparator.comparing(u -> u.getPontos()));

//        Function<Usuario, Integer> extraiPontos = u -> u.getPontos();
//        Comparator<Usuario> comparator4 = comparing(extraiPontos);
//        usuarios.sort(comparator4);

        //EVITANDO O AUTOBOXING NOS LAMBDAS

        ToIntFunction<Usuario> extraiPontos = u -> u.getPontos();
        Comparator<Usuario> comparator5 = comparingInt(extraiPontos);
        usuarios.sort(comparator5);

        //Deve se usar a versão mais enxuta, passando diretamente o lambda para a fábrica de comparators, e até mesmo invocar o sort na mesma linha
        usuarios.sort(comparingInt(u-> u.getPontos()));
        usuarios.forEach(u-> System.out.println(u.getPontos()));

    }

}
