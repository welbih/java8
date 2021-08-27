import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Capitulo7 {

    public static void main(String... args) {

        BiFunction<String, Integer, Usuario> createdUser = Usuario::new;
        Usuario user1 = createdUser.apply("Joana", 200);
        Usuario user2 = createdUser.apply("Cassandra", 190);
        Usuario user3 = createdUser.apply("Daniel", 180);
        Usuario user4 = createdUser.apply("Trent", 170);
        Usuario user5 = createdUser.apply("President", 160);
        Usuario user6 = createdUser.apply("President clone", 150);
        Usuario user7 = createdUser.apply("Blond", 140);
        Usuario user8 = createdUser.apply("Skedar", 130);
        Usuario user9 = createdUser.apply("Meat", 120);
        Usuario user10 = createdUser.apply("Easy", 110);
        Usuario user11 = createdUser.apply("Normal", 100);
        Usuario user12 = createdUser.apply("Hard", 90);
        Usuario user13 = createdUser.apply("Perfect", 80);
        Usuario user14 = createdUser.apply("Dark", 70);
        Usuario user15 = createdUser.apply("Coelho", 60);
        Usuario user16 = createdUser.apply("007", 50);
        Usuario user17 = createdUser.apply("Sniper", 40);

        Supplier<List<Usuario>> createdListUser = ArrayList::new;
        List<Usuario> usuarios = createdListUser.get();
        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);
        usuarios.add(user4);
        usuarios.add(user5);
        usuarios.add(user6);
        usuarios.add(user7);
        usuarios.add(user8);
        usuarios.add(user9);
        usuarios.add(user10);
        usuarios.add(user11);
        usuarios.add(user12);
        usuarios.add(user13);
        usuarios.add(user14);
        usuarios.add(user15);
        usuarios.add(user16);
        usuarios.add(user17);

        //FILTRANDO OS 10 USUARIOS COM MAIS PONTOS E TORNÁ-LOS MODERADORES
        usuarios.sort(comparing(Usuario::getPontos).reversed());
        usuarios
                .subList(0,10)
                .forEach(Usuario::tornaModerador);

        //Filtrar todos os usuários que têm mais de 100 pontos.

        //maneira antiga
        for(Usuario usuario : usuarios) {
            if(usuario.getPontos() > 100) {
                usuario.tornaModerador();
            }
        }

        //STREAM
        Stream<Usuario> stream = usuarios.stream();
//        stream.filter(u -> {return u.getPontos() > 100;}); // podemos deixar mais limpo.
        stream.filter(u -> u.getPontos() > 100);
        //simplificando mais ainda
        usuarios.stream().filter(u -> u.getPontos() > 100);

        usuarios.stream().forEach(System.out::println); // Irá imprimi a lista normal, sem filtro, pois os metódos dos stream não alteram a stream original

        Stream<Usuario> stream1 = usuarios
                                    .stream()
                                    .filter(u -> u.getPontos() > 100);

        stream1.forEach(System.out::println);

        usuarios.stream()
                .filter(u -> u.getPontos() > 150)
                .forEach(Usuario::tornaModerador);

        System.out.println("---------------------Outros Exemplos----------------------------");
        //Outros exemplos
        usuarios.stream()
                .filter(u -> !u.isModerador())
                .forEach(System.out::println);

        usuarios.stream().filter(Usuario::isModerador);

        //OBTENDO DE VOLTA UMA LISTA
        List<Usuario> maisQue100 = createdListUser.get();
        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .forEach(maisQue100::add);

        //COLLECTORS
        Supplier<ArrayList<Usuario>> supplier = ArrayList::new;
        BiConsumer<ArrayList<Usuario>, Usuario> accumulator = ArrayList::add;
        BiConsumer<ArrayList<Usuario>, ArrayList<Usuario>> combiner = ArrayList::addAll;

        List<Usuario> maisQue150 = usuarios.stream()
                                    .filter(u -> u.getPontos() > 150)
                                    .collect(supplier, accumulator, combiner);

        usuarios.stream()
                .filter(u -> u.getPontos() > 150)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        usuarios.stream()
                .filter(u -> u.getPontos() > 140)
                .collect(Collectors.toList());

        //Simplificando ao fazer um import static
        usuarios.stream()
                .filter(u -> u.getPontos() > 140)
                .collect(toList());

        //Deveolvendo um Set
        Set<Usuario> maisDe140 = usuarios
                .stream()
                .filter(u -> u.getPontos() > 140)
                .collect(toSet());

        //toCollection permite que você escolha a implementação que será devolvida no final da coleta.
//        Set<Usuario> set = stream1.collect(toCollection(HashSet::new)); erro ao compilar, pois em alguma linha acima o stream já está fazendo alguma alteração.

        //Listar apenas os pontos de todos os usuários com o Map
        List<Integer> pontos = usuarios.stream()
                .map(Usuario::getPontos)
                .collect(toList());

        IntStream intStream = usuarios.stream().mapToInt(Usuario::getPontos);

        //OPTIONAL
        double pontuacaoMedia = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .getAsDouble();

        OptionalDouble media = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average();
        double mediaPontuacao = media.orElse(0.0);

        double pontuacaoMedia1 = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElse(0.0);

        double pontuacaoMedia2 = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .orElseThrow(IllegalStateException::new);

        //Verifica se existe um valor dentro do optional. E, no caso de existir, passamos um Consumer como argumento
//        usuarios.stream()
//                .mapToInt(Usuario::getPontos)
//                .average()
//                .ifPresent(valor -> janela.atualiza(valor));

        //Usuario com a maior quantidade de pontos e manipulando com optional, pois a lista pode estar vazia e retornar um null
        Optional<Usuario> max = usuarios.stream()
                .max(comparingInt(Usuario::getPontos));

        //Pegando o nome
        usuarios
                .stream()
                .max(Comparator.comparingInt(Usuario::getPontos))
                .map(Usuario::getNome);


    }

}
