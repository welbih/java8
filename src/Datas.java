import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Datas {

    public final static ChronoUnit ANOS = ChronoUnit.YEARS, DIAS = ChronoUnit.DAYS, MESES = ChronoUnit.MONTHS;
    public final static DayOfWeek DOMINGO = DayOfWeek.SUNDAY,
                                SABADO = DayOfWeek.SATURDAY,
                                SEGUNDA = DayOfWeek.MONDAY,
                                TERCA = DayOfWeek.TUESDAY,
                                QUARTA = DayOfWeek.WEDNESDAY,
                                QUINTA = DayOfWeek.THURSDAY,
                                SEXTA = DayOfWeek.FRIDAY;

    public final static int DIA_EM_MILISSEGUNDOS = Datas.HORA_EM_MILISSEGUNDOS * 24,
                        HORA_EM_MILISSEGUNDOS = Datas.MINUTO_EM_MILISSEGUNDOS * 60,
                    MINUTO_EM_MILISSEGUNDOS = Datas.SEGUNDO_EM_MILISSEGUNDOS * 60,
                SEGUNDO_EM_MILISSEGUNDOS = 1000;

    private Datas(){}

    /**
     * Encontra um dia da semana baseado em uma data relativa.
     *
     * @param data data relativa
     * @param dow dia da semana desejado
     * @return dia da semana correspondente
     */
    public static LocalDate findDiaDaSemana(DayOfWeek dow, LocalDate data)
    {
        return findDiaDaSemana(data, dow.getValue());
    }
    /**
     * Encontra um dia da semana baseado em uma data relativa.
     *
     * @param data data relativa
     * @param dow dia da semana desejado
     * @return dia da semana correspondente
     */
    public static LocalDate findDiaDaSemana(LocalDate data, int dow)
    {
        return data.plusDays(dow % 7 - data.getDayOfWeek().getValue() % 7);
    }
}
