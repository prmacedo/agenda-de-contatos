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
            case 1 -> add(agenda);
            case 2 -> remove(agenda);
            case 3 -> edit(agenda);
            case 4 -> exit();
            default -> System.out.println("\nOpção inválida\n");
        }

        return action != 4;
    }

    public static void add(Agenda agenda) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAdicionar Contato");

        System.out.print("Informe o nome: ");
        String nome = scanner.nextLine().trim();

        System.out.print("Informe o sobrenome: ");
        String sobrenome = scanner.nextLine().trim();

        System.out.println("Informe a lista de telefones separados por vírgula seguindo o modelo: 99 99999999, 88 88888888");
        String telefonesString = scanner.nextLine().trim();

        agenda.addContato(nome, sobrenome, telefonesString);
    }

    public static void remove(Agenda agenda) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nRemover Contato");

        System.out.print("Informe o Id do Contato: ");
        Long id = Long.parseLong(scanner.nextLine());

        agenda.removeContato(id);
    }

    public static void edit(Agenda agenda) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEditar Contato");
        System.out.print("Informe o Id do Contato: ");
        Long id = Long.parseLong(scanner.nextLine());

        Contato contatoToEdit = agenda.contatoExists(id);
        if (contatoToEdit != null) {
            System.out.print("Informe o nome: ");
            String nome = scanner.nextLine().trim();

            System.out.print("Informe o sobrenome: ");
            String sobrenome = scanner.nextLine().trim();

            System.out.println("Informe a lista de telefones separados por vírgula seguindo o modelo: 99 99999999, 88 88888888");
            String telefonesString = scanner.nextLine().trim();

            agenda.editContato(contatoToEdit, nome, sobrenome, telefonesString);
        }
    }

    public static void exit() {
        System.out.println("\nSair");
    }

}