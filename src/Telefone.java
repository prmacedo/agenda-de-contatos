import java.util.Objects;

public class Telefone {
    private Long id;
    private String ddd;
    private Long numero;

    public Telefone(Long id, String telefoneString) {
        this.id = id;

        String[] dddENumero = telefoneString.trim().split(" ");
        this.ddd = dddENumero[0];
        this.numero = Long.parseLong(dddENumero[1]);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return  Objects.equals(ddd, telefone.ddd) &&
                Objects.equals(numero, telefone.numero);
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", ddd='" + ddd + '\'' +
                ", numero=" + numero +
                '}';
    }
}
