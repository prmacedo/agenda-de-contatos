import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private Long lastContatoId;
    private Long lastTelefoneId;
    private List<Contato> listaDeContatos;

    public Agenda() {
        this.lastContatoId = 0L;
        this.lastTelefoneId = 0L;
        this.listaDeContatos = new ArrayList<Contato>();
    }

    public void addContato(String nome, String sobrenome, String telefonesString) {
        List<Telefone> telefones = new ArrayList<Telefone>();

        String[] telefonesStringArray = telefonesString.split(",");

        for (String telefoneString: telefonesStringArray) {
            Long currentTelefoneId = ++this.lastTelefoneId;
            Telefone telefone = new Telefone(currentTelefoneId, telefoneString);
            if (isTelefoneUnique(telefone)) {
                telefones.add(telefone);
            } else {
                System.out.printf("O número %s já pertence a outro contato, informe os dados novamente\n", telefoneString);
                return;
            }
        }

        Long currentUserId = ++this.lastContatoId;
        Contato contato = new Contato(currentUserId, nome, sobrenome);
        contato.setTelefones(telefones);

        this.listaDeContatos.add(contato);
    }

    private boolean isTelefoneUnique(Telefone telefoneToCheck) {
        for (Contato contato : this.listaDeContatos) {
            List<Telefone> telefones = contato.getTelefones();

            for (Telefone telefone : telefones) {
                if (telefone.equals(telefoneToCheck)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printListaDeContatos() {
        if (this.listaDeContatos.isEmpty()) {
            System.out.println("Agenda de Contatos vazia");
        } else {
            System.out.println(">>>> Contatos <<<<");
            System.out.println("Id | Nome");

            for (Contato contato : this.listaDeContatos) {
                String fullname = contato.getNome() + " " + contato.getSobreNome();
                System.out.printf("%d | %s\n", contato.getId(), fullname);
            }
        }

        System.out.println();
    }

}
