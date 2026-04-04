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
            }
            case 5 -> {
                editar();
            }
            case 6 -> {
                excluirPorNome();
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
    int indiceVeiculo = Input.scanInt("Digite o índice do veículo que você deseja editar: ");
    String veiculoEditado = IO.readln("Insira o novo nome do veículo: ");
    veiculoEditado.trim();

    if (veiculos.isEmpty()){
        IO.println("Nenhum veículo foi cadastrado ainda!");
        return;
    }

    if (veiculoEditado == ""){
        IO.println("Você não escreveu um novo nome válido!");
        return;
    }

    // O novo nome também deve passar pelas mesmas validações do cadastro (não vazio e sem duplicatas).

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

/* 6. Ordenação da lista antes de exibir
Antes de exibir a lista de veículos (na listagem e na busca), o sistema deve ordenar os veículos em ordem alfabética. A ordenação deve ser implementada manualmente, utilizando um algoritmo simples com estruturas de repetição e condicionais — não é permitido o uso de métodos prontos como Collections.sort(), List.sort(), ou qualquer expressão lambda/função de ordem superior. */
void ordenarLista() {
    int tamanho = veiculos.size();
    //bubble sort
    for (int i = 0; i < tamanho - 1; i++) {
        for (int j = 0; j < tamanho - 1 - i; j++) {
            String atual = veiculos.get(j).toLowerCase();
            String proximo = veiculos.get(j + 1).toLowerCase();

            if (atual.compareTo(proximo) > 0) {
                // Troca os elementos de posição
                String valorPraTrocar = veiculos.get(j); //guarda o elemento atual (o que vai ser trocado p/ frente)
                veiculos.set(j, veiculos.get(j + 1)); // o valor atual, vira o valor 'menor' na ordem alfabética
                veiculos.set(j + 1, valorPraTrocar); // o proximo valor recebe o valor do 'maior', que estava guardado 
            }
        }
    }
}


/*7. Remover por nome
Além da remoção por índice já implementada em aula, adicione uma nova opção de remoção onde o usuário informa o nome do veículo a ser removido. Caso o veículo não seja encontrado, exiba uma mensagem adequada. */

void excluirPorNome(){
    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado ainda!");
        return; //early return 
    }

    String veiculo = IO.readln("Digite o nome do veículo que deseja excluir: ");
    veiculo = veiculo.trim();

    boolean veiculoEncontrado = false;
    for (String i : veiculos) {
        if (i.equalsIgnoreCase(veiculo)) {
            veiculos.remove(veiculo);
            IO.println("O veículo " + veiculo + " foi removido com sucesso!");
            veiculoEncontrado = true;
            break;
        }
    }
    if (!veiculoEncontrado){
        IO.println("Não foi possível encontrar esse veículo para removê-lo!");
    }
}