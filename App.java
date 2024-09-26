
import java.time.LocalDateTime;
import java.util.Scanner;

import components.Cliente;
import components.OrdemServico;
import components.Servidor;

public class App {
    public static void main(String[] args){

        Cliente cliente = new Cliente();
        Servidor servidor = new Servidor();

        cliente.gerarOSInicio(servidor);
        cliente.preencherCache(servidor);

        int op = -1;
        Scanner s = new Scanner(System.in);
        System.out.println("------------Bem vindo!------------\n");
        while (op != 0){
            clienteMenu();
            op = s.nextInt();
            switch(op){
                // cadastro
                case 1:
                    // chamamento estático pra sempre pegar um código diferente
                    int codigoNova = Servidor.getTotalRegistros() + 1;
                    System.out.println("Digite o nome da Ordem de Serviço:");
                    s.nextLine();
                    String nome = s.nextLine();
                    System.out.println("Digite a descrição da Ordem de Serviço:");
                    String descricao = s.nextLine();

                    OrdemServico novaOS = new OrdemServico(codigoNova, nome, descricao, LocalDateTime.now());
                    cliente.CadastrarOS(novaOS, servidor);
                    break;
                    
                // buscar
                case 2:
                    System.out.println("Digite o código da Ordem de Serviço:");
                    int codigoBusca = s.nextInt();
                    OrdemServico busca = cliente.buscarOS(codigoBusca, servidor);

                    if (busca != null){
                        System.out.println("Ordem de Serviço encontrada:");
                        System.out.println("Código: " + busca.getCodigo());
                        System.out.println("Nome: " + busca.getNome());
                        System.out.println("Descrição: " + busca.getDescricao());
                        System.out.println("Data: " + busca.getData() + "\n");
                    } else {
                        System.out.println("Ordem de Serviço não encontrada");
                    }
                    break;

                // listar
                case 3:
                    cliente.listarOS(servidor);
                    break;

                // alterar
                case 4:
                    System.out.println("Digite o código da Ordem de Serviço:");
                    int codigoOS = s.nextInt();
                    s.nextLine();

                    System.out.println("Digite o nome da Ordem de Serviço:");
                    String nomeOS = s.nextLine();
                    System.out.println("Digite a descrição da Ordem de Serviço:");
                    String descricaoOS = s.nextLine();

                    OrdemServico alterada = new OrdemServico(codigoOS, nomeOS, descricaoOS, LocalDateTime.now());
                    cliente.alterarOS(codigoOS, alterada, servidor);
                    break;

                // remover
                case 5:
                    System.out.println("Digite o código da Ordem de Serviço:");
                    int codigoRemover = s.nextInt();
                    cliente.removerOS(codigoRemover, servidor);
                    break;

                // quantidade de registros
                case 6:
                    System.out.println("Quantidade de registros: " + cliente.qtRegistros(servidor));
                    break;
                
                // imprimir cache
                case 7:
                    System.out.println();
                    cliente.imprimirCache();
                    break;

                // sair
                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }
        s.close();
    }

    public static void clienteMenu(){
        System.out.println("1 - Cadastrar Ordem de Serviço");
        System.out.println("2 - Buscar Ordem de Serviço");
        System.out.println("3 - Listar Ordens de Serviço");
        System.out.println("4 - Alterar Ordem de Serviço");
        System.out.println("5 - Remover Ordem de Serviço");
        System.out.println("6 - Quantidade de Registros");
        System.out.println("7 - Imprimir Cache");
        System.out.println("0 - Sair");
    }
}
