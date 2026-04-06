import java.util.ArrayList;
import java.util.List;

//Lista de Strings:
List<String> veiculos = new ArrayList<>(); // lista só pode armazenar um tipo de variável por vez

void main() {

    IO.println("Bem-vindo(a) ao Sistema CadVeiculos!");
    String menu = """
            MENU DE OPÇÕES
            1- Cadastrar veículo
            2- Listar veículos
            3- Remover veículo (por índice)
            4- Buscar veículo por nome
            5- Editar
            6- Remover veículo (por nome)
            0- Sair
            """;
    int opcao;
    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite a opção desejada: ");

        switch (opcao) {
            case 1 -> {
                cadastrar();
                IO.readln("Pressione Enter para continuar");
            }
            case 2 -> {
                listar();
                IO.readln("Pressione Enter para continuar");
            }
            case 3 -> {
                excluir();
                IO.readln("Pressione Enter para continuar");
            }
            case 4 -> {
                buscarPorNome();
                IO.readln("Pressione Enter para continuar");
            }
            case 5 -> {
                editar();
                IO.readln("Pressione Enter para continuar");
            }
            case 6 -> {
                excluirPorNome();
                IO.readln("Pressione Enter para continuar");
            }
            case 0 -> {
                // Sair
                IO.readln("Volte sempre!");
            }
            default -> {
                IO.println("Opção inválida");
                IO.readln("Pressione Enter para continuar");
            }
        }

    } while (opcao != 0);
}

void cadastrar() {
    String veiculo = IO.readln("Digite o nome do novo veículo: ");
    veiculo = veiculo.trim();

    if (veiculo.isEmpty()) {
        IO.println("Nome do veículo inválido!");
        return;
    }

    // equalsIgnoreCase junto com for
    boolean veiculoExiste = false;
    for (String i : veiculos) {
        if (i.equalsIgnoreCase(veiculo)) {
            IO.println("Esse veículo já foi cadastrado");
            veiculoExiste = true;
            break;
        }
    }

    if (!veiculoExiste) {
        veiculos.add(veiculo);
        IO.println("Veículo adicionado com sucesso");
    }
}

void listar() {
    ordenarLista();
    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado ainda!");
        return;
    }
    for (int i = 1; i <= veiculos.size(); i++) {
        IO.println(i + "- " + veiculos.get(i - 1));
    }
    IO.println("Total de veículos cadastrados: " + veiculos.size());
}

void excluir() {
    listar();
    int indice = Input.scanInt("Digite o índice do veículo que você deseja remover: ");
    if (indice > veiculos.size() || indice <= 0) {
        IO.println("Veículo não encontrado");
    } else {
        veiculos.remove(indice - 1);
        IO.println("Veículo removido com sucesso!");
    }
}

void buscarPorNome() {

    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado ainda!");
        return; //early return -> faz com que o resto da função não seja executada caso nao tenha nenhum veiculo cadastrado
    }

    listar();

    String veiculo = IO.readln("Digite o nome do veículo que deseja buscar: ");

    boolean veiculoCadastrado = false;
    for (String i : veiculos) {
        if (i.equalsIgnoreCase(veiculo)) {
            IO.println("O veículo " + veiculo + " está cadastrado!");
            IO.println("Total de veículos cadastrados: " + veiculos.size());
            veiculoCadastrado = true;
            break;
        }
    }
    if (!veiculoCadastrado) {
        IO.println("Esse veículo ainda não foi cadastrado");
    }

}

void editar(){
    listar();

    if (veiculos.isEmpty()){
        IO.println("Nenhum veículo foi cadastrado ainda!");
        return;
    }

    int indiceVeiculo = Input.scanInt("Digite o índice do veículo que você deseja editar: ");

    if (indiceVeiculo > veiculos.size()){
        IO.println("Esse índice de veículo não existe!");
        return;
    }

    String veiculoEditado = IO.readln("Insira o novo nome do veículo: ");
    veiculoEditado = veiculoEditado.trim();


    if (veiculoEditado.isEmpty()){
        IO.println("Você não escreveu um novo nome válido!");
        return;
    }

    //confere se o veiculo já foi cadastrado antes
    boolean veiculoCadastrado = false;
    for (String i : veiculos) {
        if (i.equalsIgnoreCase(veiculoEditado)) {
            IO.println("Esse nome de veículo já foi cadastrado");
            veiculoCadastrado = true;
            break;
        }
    }
    if (!veiculoCadastrado) {
        veiculos.set(indiceVeiculo - 1, veiculoEditado );
        IO.println("Veículo editado com sucesso!");
    }

}

void ordenarLista() {
    int tamanho = veiculos.size();
    //bubble sort
    for (int i = 0; i < tamanho - 1; i++) {
        for (int j = 0; j < tamanho - 1 - i; j++) {
            String atual = veiculos.get(j).toLowerCase();
            String proximo = veiculos.get(j + 1).toLowerCase();

            if (atual.compareTo(proximo) > 0) {
                // troca os elementos de posição
                String valorPraTrocar = veiculos.get(j); //guarda o elemento atual (o que vai ser trocado p/ frente)
                veiculos.set(j, veiculos.get(j + 1)); // o valor atual, vira o valor 'menor' na ordem alfabética
                veiculos.set(j + 1, valorPraTrocar); // o proximo valor recebe o valor do 'maior', que estava guardado 
            }
        }
    }
}


void excluirPorNome(){
    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado ainda!");
        return; //early return 
    }

    String veiculo = IO.readln("Digite o nome do veículo que deseja excluir: ");
    veiculo = veiculo.trim();

    boolean veiculoEncontrado = false;
    for (int i = 0; i < veiculos.size(); i++) {
        if (veiculos.get(i).equalsIgnoreCase(veiculo)) {
            String nomeRemovido = veiculos.get(i); // guarda o nome real cadastrado
            veiculos.remove(i); // remove pelo indice
            IO.println("O veículo \"" + nomeRemovido + "\" foi removido com sucesso!");
            veiculoEncontrado = true;
            break;
        }
    }
    if (!veiculoEncontrado){
        IO.println("Não foi possível encontrar esse veículo para removê-lo!");
    }
}