@FunctionalInterface
interface Validador<T> {
    boolean valida(T t);
}