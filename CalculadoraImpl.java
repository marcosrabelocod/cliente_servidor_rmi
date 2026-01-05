public class CalculadoraImpl implements Calculadora {

    @Override
    public Numero soma(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() + b.getValor());
    }

    @Override
    public Numero subtrai(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() - b.getValor());
    }

    @Override
    public Numero multiplica(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() * b.getValor());
    }

    @Override
    public Numero divide(Numero a, Numero b) throws DivisaoPorZeroException {
        if (b.getValor() == 0) {
            throw new DivisaoPorZeroException("Erro: Tentativa de divisão por zero!");
        }
        return new NumeroImpl(a.getValor() / b.getValor());
    }

    @Override
    public Numero seno(Numero a) {
        return new NumeroImpl(Math.sin(a.getValor()));
    }

    @Override
    public Numero cosseno(Numero a) {
        return new NumeroImpl(Math.cos(a.getValor()));
    }

    @Override
    public Numero tangente(Numero a) throws DivisaoPorZeroException {
        double valor = a.getValor();
        if (Math.cos(valor) == 0) { // tan(x) é indefinido quando cos(x) == 0
            throw new DivisaoPorZeroException("Erro: Tangente indefinida para este valor!");
        }
        return new NumeroImpl(Math.tan(valor));
    }

    @Override
    public Numero potenciacao(Numero a, Numero b) throws DivisaoPorZeroException {
        return new NumeroImpl(Math.pow(a.getValor(),b.getValor()));
    }
    
    @Override
    public Numero raizQuadrada(Numero a) {
        return new NumeroImpl(Math.sqrt(a.getValor()));
    }

    @Override
    public Numero raizCubica(Numero a) {
        return new NumeroImpl(Math.cbrt(a.getValor()));
    }

    @Override
    public Numero logaritmo(Numero a) {
        return new NumeroImpl(Math.cbrt(a.getValor()));
    }

}
