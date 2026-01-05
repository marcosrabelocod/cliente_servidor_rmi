public class DivisaoPorZeroException extends RuntimeException {
    public DivisaoPorZeroException() {
        super("Erro: Divisão por zero não é permitida.");
    }

    public DivisaoPorZeroException(String mensagem) {
        super(mensagem);
    }

    public DivisaoPorZeroException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
