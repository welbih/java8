import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

import static java.util.Comparator.*;

public class Capitulo6 {

    public static void main(String... args) {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        usuarios.forEach(u -> u.tornaModerador());

        //METHOD REFERENCE
        usuarios.forEach(Usuario::tornaModerador);

        Consumer<Usuario> tonarModerador = Usuario::tornaModerador;
        usuarios.forEach(tonarModerador);

        //Pensando na fluência do códgio
        Function<Usuario, String> byName = Usuario::getNome;
        usuarios.sort(comparing(byName));

        usuarios.sort(comparingInt(Usuario::getPontos));

        //Odernar pelos pontos e, no caso de empate, ordenar pelo nome.
        Comparator<Usuario> c = comparingInt(Usuario::getPontos)
                                .thenComparing(Usuario::getNome);

        //Todos os usuários nulos para o fim
        usuarios.sort(nullsLast(comparing(Usuario::getNome)));

        //Odernar por pontos na ordem decrecesnte
        usuarios.sort(comparing(Usuario::getPontos).reversed());


        //REFERENCIANDO MÉTODOS DE INSTÂNCIA
        Usuario secretario = new Usuario("Josielson Respeitado", 50);
        Runnable bloco = secretario::tornaModerador;
        bloco.run();

        //SAO EQUIVALENTES
        Runnable bloco1 = secretario::tornaModerador;
        Runnable bloco2 = () -> secretario.tornaModerador();

        //REFERENCIANDO MÉTODOS QUE RECEBEM ARGUMENTOS
        usuarios.forEach(System.out::println);
        //implicitamente isso que está sendo feito
//        usuarios.forEach(u-> System.out.println(u));

        //REFERENCIANDO CONSTRUTORES
        Supplier<Usuario> criadorDeUsuarios = Usuario::new;
        Usuario novo = criadorDeUsuarios.get();

        //Construtor com argumento
        Function<String, Usuario> createdUser = Usuario::new;
        Usuario joana = createdUser.apply("Joana Dark");
        Usuario cassandra = createdUser.apply("Cassandra Datadyne");
        //Neste context, criar um usuário da forma antiga é verdadeiramente mais simples, mas teremos mais para frente
        //situações em que o constructor reference pode ajudar bastante.

        BiFunction<String, Integer, Usuario> createdOfUsers = Usuario::new;
        Usuario daniel = createdOfUsers.apply("Daniel Carrington", 170);
        Usuario trent = createdOfUsers.apply("Trent", 130);

        //REFERENCIANDO O CONTRUTOR DE UM ARRAY com constructor reference

        //REFERENCIANDO O CONTRUTOR DE list com constructor reference
        Supplier<List<Usuario>> listUser = ArrayList::new;
        List<Usuario> usuarios1 = listUser.get();

        //Referenciando métodos estáticos
        BiFunction<Integer, Integer, Integer> methodStatic = Math::max;

        //Cuidado com o boxing
        ToIntBiFunction<Integer, Integer> metodoEstatico = Math::max;

        //Sim, é possível evitar todo boxing, usando
        IntBinaryOperator max3 = Math::max;

        //Qual usar?? IntBinaryOperator certamente é mais interessante, mas vai depender do método que receberá nosso lambda como argumento

    }

}
