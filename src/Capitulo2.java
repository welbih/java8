import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Capitulo2 {

    public static void main(String... args) {
        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

//        for(Usuario u : usuarios){
//            System.out.println(u.getNome());
//        }

//        Mostrador mostrador = new Mostrador();
//        usuarios.forEach(mostrador);

        //Essa abordagem acaba gerando um .class com um nome estranho, como por exemplo Capitulo2$1.class
        //Como não podemos nos referenciar a um nome dessa classe, chamamo-la de classe anônima.
//        Consumer<Usuario> mostrador = new Consumer<Usuario>() {
//            @Override
//            public void accept(Usuario u) {
//                System.out.println(u.getNome());
//            }
//        };
//        usuarios.forEach(mostrador);

        //Simplificando bastante, um lambda no Java é uma maneira mais simples de implementar uma interface que só tem um único método.
        //No nosso caso a interface consumer é uma boa candidata.
//        usuarios.forEach(new Consumer<Usuario>() {
//            @Override
//            public void accept(Usuario u) {
//                System.out.println(u.getNome());
//            }
//        });

//        Consumer<Usuario> mostrador =
//                (Usuario u) -> {System.out.println(u.getNome());}; // <- Esse trecho é um lambda do java 8

        //O compilador consegue também inferir o tipo, sem a necessidade de utilizar Usuario, nem parênteses:
//        Consumer<Usuario> mostrador = u -> {System.out.println(u.getNome());};

        //Caso o bloco dentro de { } CONTENHA APENAS UMA INSTRUÇÃO, PODEMOS IMITI-LO e remover também o ponto e vírgula:
        Consumer<Usuario> mostrador = u-> System.out.println(u.getNome());

        //Podemos passa esse ^ esse trecho de código diretamente para usuarios.forEach em vez de declarar a variável temporária mostrador:
        usuarios.forEach(u -> System.out.println(u.getNome()));

        //Mais exemplos
        usuarios.forEach(u -> u.tornaModerador());

        usuarios.forEach(u -> System.out.println(u.getNome() +" "+ u.isModerador()));

    }
}
