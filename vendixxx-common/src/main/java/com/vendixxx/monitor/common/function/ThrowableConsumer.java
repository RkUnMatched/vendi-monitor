package com.vendixxx.monitor.common.function;

@FunctionalInterface
public interface ThrowableConsumer<T> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @throws Throwable if met with any error
     */
    void accept(T t) throws Throwable;

    /**
     * Executes {@link ThrowableConsumer}
     *
     * @param t the function argument
     * @throws RuntimeException wrappers {@link Throwable}
     */
    default void execute(T t) throws RuntimeException {
        try {
            accept(t);
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Executes {@link ThrowableConsumer}
     *
     * @param t        the function argument
     * @param consumer {@link ThrowableConsumer}
     * @param <T>      the source type
     * @return the result after execution
     */
    static <T> void execute(T t, ThrowableConsumer<T> consumer) {
        consumer.execute(t);
    }
}
