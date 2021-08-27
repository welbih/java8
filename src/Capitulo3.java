import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Capitulo3 {

    public static void main(String... args) {

        // Mais uma interface funcional
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0; i <= 1000; i++) {
//                    System.out.println(i);
//                }
//            }
//        };
//        new Thread(r).start();

        //Como é uma interface funcional, ela sempre pode ser instanciada através de uma expressão lambda.
//        Runnable r = () -> {
//            for(int i = 0; i <= 1000; i++) {
//                System.out.println(i);
//            }
//        };
//        new Thread(r).start();

        //Poderíamos ir além e fazer tudo em um único statement, com talvez um pouco menos de legibilidade:
//        new Thread(() -> {
//            for(int i = 0; i <= 1000; i++){
//                System.out.println(i);
//            }
//        }).start();


        //OUTROS EXEMPLOS
        Button button = new Button();
        button.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 System.out.println("evento do click acionado");
             }
         });

        //Como é uma função funcional,iremos representá-la como uma expressão lambda
        button.addActionListener((event) -> {
            System.out.println("evendo do click acionado");
        });

        //Como sabemos, essa expressão pode ficar ainda mais simples, sem parênteses no único argumento e podemos também remover o { } e ;:
        button.addActionListener(event -> System.out.println("evento do click acionado"));


        //MINHA PRÓPRIA INTERFACE FUNCIONAL
        Validador<String> vlidadorCEP = new Validador<String>() {
            public boolean valida(String valor) {
                return valor.matches("[0-9]{5}-[0-9]{3}");
            }
        };

        //Utilizando co lambda
        Validador<String> validadorCEP2 =
                valor -> {
                    return valor.matches("[0-9]{5}-[0-9]{3}");
                };

        //Podemos remumir mais ainda nossa expressão lambda, mesmo ela sendo uma instração de return
        //Podemos remover o próprio return, assim como o seu ponto e vírgula e as chaves delimitadoras.
        Validador<String> validadorCEP3 = valor -> valor.matches("[0-9]{5}-[0-9]{3}");

        System.out.println("CEP válido? " + validadorCEP3.valida("72860-800"));

        //É sempre necessário haver a atribuição (ou alguma forma de inferir) daquele lambda para uma interface funcional.
        Runnable o = () -> {
            System.out.println("O que sou eu? Que lambda?");
        };

        System.out.println(o);
        System.out.println(o.getClass());


        //CAPTURA DE VARIÁVEIS LOCAIS
        final int numero = 5;
        int segundoNumero = 10;
        new Thread(()-> {
           System.out.println(numero);
           System.out.println(segundoNumero); // não compilaria por causa da linha 89.
        }).start();

//        segundoNumero = 11;

    }

}
