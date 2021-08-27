import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Capitulo10 {

    public static void main(String... args) {
        List<List<LocalDate>> calendario;
        YearMonth anoMesAtual;

        calendario = new ArrayList<>();

        YearMonth anoEmes = YearMonth.of(2019, 5);

        YearMonth.now().getMonthValue();

        ///////////////////////////////////

        LocalDate primeiroDiaDoMesAtual = LocalDate.of(anoEmes.getYear(), anoEmes.getMonth(), 1);
        calendario.clear();
        anoMesAtual = anoEmes;

        for(int x = 0; x < 6; x++) {
            calendario.add(new ArrayList<>());
            calendario.get(x).add(Datas.findDiaDaSemana(Datas.DOMINGO, primeiroDiaDoMesAtual.plusDays(x * 7)));
            calendario.get(x).add(Datas.findDiaDaSemana(Datas.SEGUNDA, primeiroDiaDoMesAtual.plusDays((x * 7) + 1)));
            calendario.get(x).add(Datas.findDiaDaSemana(Datas.TERCA, primeiroDiaDoMesAtual.plusDays((x * 7) + 1)));
            calendario.get(x).add(Datas.findDiaDaSemana(Datas.QUARTA, primeiroDiaDoMesAtual.plusDays((x * 7) + 1)));
            calendario.get(x).add(Datas.findDiaDaSemana(Datas.QUINTA, primeiroDiaDoMesAtual.plusDays((x * 7) + 1)));
            calendario.get(x).add(Datas.findDiaDaSemana(Datas.SEXTA, primeiroDiaDoMesAtual.plusDays((x * 7) + 1)));
            calendario.get(x).add(Datas.findDiaDaSemana(Datas.SABADO, primeiroDiaDoMesAtual.plusDays((x * 7) + 1)));
        }

        System.out.println("Dia da semana valor: " + primeiroDiaDoMesAtual.getDayOfWeek().getValue());

        System.out.println("Resultado 1: " + Datas.DOMINGO.getValue() % 7);
        System.out.println("Resultado 2: " + primeiroDiaDoMesAtual.getDayOfWeek().getValue() % 7);
        System.out.println("Resultado 3: " + (Datas.DOMINGO.getValue() % 7 - primeiroDiaDoMesAtual.getDayOfWeek().getValue() % 7));

        System.out.println(primeiroDiaDoMesAtual.plusDays(0 * 7));
        System.out.println("Ano e Mês atual: " + anoEmes);
        System.out.println(calendario.size());

        System.out.println(DayOfWeek.MONDAY.getValue());

    }

}
