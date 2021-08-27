import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Capitulo8 {

    public static void main(String... args) {

        Usuario user1 = new Usuario("Paulo Silveira", 150);
        Usuario user2 = new Usuario("Rodrigo Turini", 120);
        Usuario user3 = new Usuario("Guilherme Silveira", 190);

        List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

        //Filtrar usuários com mais de 100 pontos e ordená-los e retornar para uma lista.
        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(Collectors.toList());

        //Encontrar um usuário com mais de 100 pontos. Basta um e serve qualquer um.
        usuarios.stream()
                    .filter(u -> u.getPontos() > 100)
                    .collect(Collectors.toList())
                    .get(0); //Muito trabalho para algo simples. O stream possui o método findAny que devolve qualquer um dos elementos.

        Optional<Usuario> usuarioOptional = usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .findAny();

        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .findFirst();

        //EXERGANDO A EXECUÇÃO DO PIPELINE COM PEEK
        //podemos pedir para que o stream execute uma tarefa toda vez que processar um elemento. Utilizamos o peek
        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .peek(System.out::println)
                .findAny();

        //O seguinte código não irá imprimir nada.
        usuarios.stream()
                .filter(u -> u.getPontos() > 100)
                .peek(System.out::println);
        //Por quê? O peek devolve um Stream, onde está marcado para imprimir todos os elementos processados.
        //Ele só vai processar elementos quando encontrar uma operação terminal, como findAny, o collect ou o forEach

        usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .peek(System.out::println)
                .findAny();

        double pontuacaoMedia = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .average()
                .getAsDouble();

        //métodos, com exceção do sum e count, trabalham com Optional.
        Optional<Usuario> maximo = usuarios.stream()
                .max(Comparator.comparing(Usuario::getPontos));
        Usuario maximaPontuacao = maximo.get();

        int total = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .sum();


        //Vamos quebrar essa operação de soma para enxergar melhor o que é uma operação de redução. Repare as definições
        int valorInicial = 0;
        IntBinaryOperator operacao = (a, b) -> a + b;

        int total2 = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(valorInicial, operacao);

        //Poderíamos ter escrito tudo mais sucintamente, sem a declaração de variáveis locais.
        int total3 = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, (a, b) -> a + b);

        //Podemos ir além. Na classe Integer, há agora o método estático Integer.sum, que soma dois inteiros. Em vez do lambda, podemos usar um method reference
        usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(0, Integer::sum);

        //Não há nenhuma vantagem em usar a redução com relação ao sum. Mas o importante é conhecer para realizar operações que não se encontram no Stream.
        //Exemplo, multiplicar todos os pontos.
        int multiplicacao = usuarios.stream()
                .mapToInt(Usuario::getPontos)
                .reduce(1, (a, b) -> a * b);

        //Há casos especiais em que invocar o map pode ser custoso, e o melhor seria fazer diretamente. A soma sem o map ficaria assim.
        usuarios.stream()
                .reduce(0, (atual, u) -> atual + u.getPontos(), Integer::sum); //Overload do reduce

        //Percorrendo os elementos de um stream
        usuarios.stream().iterator()
                .forEachRemaining(System.out::println);
        //deve se ser utilizado quando queremos modificar os objetos de um stream. Quando utilizarmos stream paralelos, veremos que não
        //devemos mudar o estado dos objetos que estão nele, correndo o risco de ter resultados não desterminísticos.
        //Outro motivo é a compartibilidade de APIs. Pode ser que você precise invocar um método que recebe Iterator

        //TESTANDO PREDICATES

        //Filtrar predicados sem filtrar a lista. Saber se há algum elemento daquela lsita de usuário que é moderador
        boolean hasModerador = usuarios.stream()
                .anyMatch(Usuario::isModerador); //o processamento dessa operação vai parar assim que o stream encontrar algum usuário que é moderador

        //podemos descobrir se todos os usuários são moderadores utilizando o allMatch
        usuarios.stream()
                .allMatch(Usuario::isModerador);

        //podemos descobrir se não há nenhum moderador
        usuarios.stream()
                .noneMatch(Usuario::isModerador);

        //Quantidade de elementos do stream com o count
        usuarios.stream().count();

        //Skip pula n próximos elementos
        usuarios.stream().skip(2).anyMatch(Usuario::isModerador);

        //limit para cortar o número de elementos
        usuarios.stream().limit(1).filter(u -> u.getPontos() > 150);

        //cria um stream vazio
        Stream<Object> streamVazio = Stream.empty();

        //Cria um stream de acordo com o que você passar como argumento
        Stream<Usuario> streamDeUsuario = Stream.of(user1, user2);


        //concatena dois stream.
        Stream<String> concatDeString = Stream.concat(Stream.of("Secretário", "Vice secretário"), Stream.of("Chefe", "Sub-chefe"));

        IntStream range = IntStream.range(10, 20);
        range.forEach(System.out::println);

        //Criando lista infinita de números aleatórios
        Random random = new Random(0);
        Supplier<Integer> supplier = () -> random.nextInt();
        Stream<Integer> streamGenerate = Stream.generate(supplier); // Estamos gerando o boxing o tempo todo.

        // Para evitar o boxing
        Random random1 = new Random(0);
        IntStream streamGenerate1 = IntStream.generate(() -> random1.nextInt());

        //PRECISAMOS DE CUIDADO.  QUALQUER OPERAÇÃO QUE NECESSITE PASSAR POR TODOS OS ELEMENTOS DO STREAM NUNCA TERMINARÁ DE EXECUTAR.
        // POR EXEMPLO
//        int valor = streamGenerate1.sum();

        //Pode-se utilizar operações de curto-circuito em Streams infinitos

        List<Integer> list = streamGenerate1.limit(100)
                .boxed() //O uso do boxed retorna um Stream<Integer> em vez de IntStream, possibilitando a invocação da collect
                .collect(Collectors.toList());

        //Vamos ver o mesmo código com interface fluente:

        List<Integer> listInteger = IntStream.generate(() -> random.nextInt())
                .limit(100)
                .boxed()
                .collect(Collectors.toList());

        // O supplier passado ao generate pode servir para gerar um Stream infinito de constantes, por exemplo
        //IntStream.generate(() -> 1) e Stream.generate(() -> new Object())

        //Pode ser útil para um supplier manter estado. Nesse caso, precisamos usar uma classe ou classe
        //anônima, pois dentro de um lambda não podemos declarar atributos. VAmos gerar a sequência infinita de
        //números de Fibonacci de maneira lazy e imprimir seus 10 primeiros elementos:

        IntStream.generate(new Fibonacci())
                .limit(10)
                .forEach(System.out::println);

        //Veremos que manter o estado de uma interface funcional pode limitar os recursos de paralelização que um
        //Stream fornece
        //Além do limit, há outras operações que são de curto-circuito. O findFirst

        //Vamos pegar o primeiro elemento maior que 100.

        int maiorQue100 = IntStream.generate(new Fibonacci())
                .filter(f -> f > 100) //O filter não é curto-circuito, ele não produz um Stream finito dado um STream infinito
                .findFirst()
                .getAsInt();
        System.out.println(maiorQue100);

        //Quando for necessário manter o estado de apenas uma variável, podemos usar o iterate em vez do generate
        //que recebe um UnaryOperator. Para gerar os números naturais

        IntStream.iterate(0, x -> x + 1)
                .limit(10)
                .forEach(System.out::println);


    }

}
