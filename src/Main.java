import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        boolean isRunning = true;

        while (isRunning) {
            showMenu(agenda);
            isRunning = selectAction(agenda);
        }

    }

    public static void showMenu(Agenda agenda) {
        System.out.println("""
                ##################
                ##### AGENDA #####
                ##################
                """);

        agenda.printListaDeContatos();

        System.out.println("""                                       
                >>>> Menu <<<<
                1 - Adicionar Contato
                2 - Remover Contato
                3 - Editar Contato
                4 - Sair
                """);
    }

    public static boolean selectAction(Agenda agenda) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecione uma opção: ");
        int action = Integer.parseInt(scanner.nextLine());

        switch (action) {
            case 1:
                System.out.println("\nAdicionar Contato");
                System.out.print("Informe o nome: ");
                String nome = scanner.nextLine();
                System.out.print("Informe o sobrenome: ");
                String sobrenome = scanner.nextLine();
                System.out.println("Informe a lista de telefones separados por vírgula seguindo o modelo: 99 99999999, 88 88888888");
                String telefonesString = scanner.nextLine();

                agenda.addContato(nome, sobrenome, telefonesString);

                break;

            case 2:
                System.out.println("\nRemover Contato");
                System.out.print("Informe o Id do Contato: ");
                Long id = scanner.nextLong();
                agenda.removeContato(id);
                break;

            case 3:
                System.out.println("\nEditar Contato");
                break;

            case 4:
                System.out.println("\nSair");
                break;

            default:
                System.out.println("\nOpção inválida\n");
                break;
        }

        scanner.close();
        return action != 4;
    }
}