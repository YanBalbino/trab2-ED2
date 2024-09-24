import java.time.LocalDateTime;

import components.Cliente;
import components.OrdemServico;
import components.Servidor;

public class Test {
    public static void main(String args[]){
        Cliente cliente = new Cliente();
        Servidor servidor = new Servidor();

        OrdemServico os1 = new OrdemServico(0, "nome0", "descricao0", LocalDateTime.now());
        cliente.CadastrarOS(os1, servidor);
        OrdemServico os2 = new OrdemServico(1, "nome1", "descricao1", LocalDateTime.now());
        cliente.CadastrarOS(os2, servidor);
        OrdemServico os3 = new OrdemServico(2, "nome2", "descricao2", LocalDateTime.now());
        cliente.CadastrarOS(os3, servidor);
        OrdemServico os4 = new OrdemServico(3, "nome3", "descricao3", LocalDateTime.now());
        cliente.CadastrarOS(os4, servidor);
        OrdemServico os8 = new OrdemServico(51, "nome51", "descricao51", LocalDateTime.now());
        cliente.CadastrarOS(os8, servidor);

        cliente.buscarOS(0, servidor);
        cliente.buscarOS(1, servidor);
        cliente.buscarOS(1, servidor);
        cliente.buscarOS(2, servidor);
        cliente.buscarOS(3, servidor);
        cliente.buscarOS(51, servidor);
        cliente.buscarOS(315, servidor);

        System.out.println("Cache:");
        cliente.imprimirCache();
        System.out.println();
        System.out.println("Registros no servidor:");
        cliente.listarOS(servidor);

        OrdemServico alterar = new OrdemServico(51, "nome18", "descricao18", LocalDateTime.now());
        cliente.alterarOS(51, alterar, servidor);

        System.out.println("Cache com alteração:");
        cliente.imprimirCache();
        System.out.println();
        System.out.println("Registros no servidor com alteração:");
        cliente.listarOS(servidor);

        cliente.removerOS(51, servidor);
        System.out.println("Cache pós remoção:");
        cliente.imprimirCache();
        System.out.println();
        System.out.println("Registros no servidor pós remoção:");
        cliente.listarOS(servidor);
    }
}
