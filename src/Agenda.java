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
        List<Telefone> telefones = this.generateTelefoneList(telefonesString);

        if (telefones == null) {
            return;
        }

        Long currentUserId = ++this.lastContatoId;
        Contato contato = new Contato(currentUserId, nome, sobrenome);
        contato.setTelefones(telefones);

        this.listaDeContatos.add(contato);
    }

    private List<Telefone> generateTelefoneList(String telefonesString) {
        List<Telefone> telefones = new ArrayList<Telefone>();

        String[] telefonesStringArray = telefonesString.split(",");

        for (String telefoneString: telefonesStringArray) {
            Long currentTelefoneId = ++this.lastTelefoneId;
            Telefone telefone = new Telefone(currentTelefoneId, telefoneString);
            if (isTelefoneUnique(telefone)) {
                telefones.add(telefone);
            } else {
                System.out.printf("O número %s já pertence a outro contato, informe os dados novamente\n", telefoneString);
                return null;
            }
        }

        return telefones;
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

    public void removeContato(Long id) {
        if (this.listaDeContatos.isEmpty()) {
            return;
        }

        for (int i = 0; i < this.listaDeContatos.size(); i++) {
            Contato currentContato = this.listaDeContatos.get(i);

            if (currentContato.getId().equals(id)) {
                this.listaDeContatos.remove(i);
                return;
            }
        }
    }

    public Contato contatoExists(Long id) {
        for (Contato contato : this.listaDeContatos) {
            if(contato.getId().equals(id)) {
                return contato;
            }
        }

        return null;
    }

    public void editContato(Contato contato, String nome, String sobrenome, String telefonesString) {
        contato.setNome(nome);
        contato.setSobreNome(sobrenome);
        contato.setTelefones(this.generateTelefoneList(telefonesString));
    }

    public void printListaDeContatos() {
        System.out.println(">>>> Contatos <<<<");

        if (this.listaDeContatos.isEmpty()) {
            System.out.println("Agenda de Contatos vazia");
        } else {
            System.out.println("Id | Nome");

            for (Contato contato : this.listaDeContatos) {
                String fullname = contato.getNome() + " " + contato.getSobreNome();
                System.out.printf("%d | %s\n", contato.getId(), fullname);
            }
        }

        System.out.println();
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "listaDeContatos=" + listaDeContatos +
                '}';
    }
}
