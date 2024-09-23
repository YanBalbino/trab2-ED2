
import java.time.LocalDateTime;

import utils.TabelaHashExt;
import components.OrdemServico;


public class Test {
    public static void main(String args[]){
        TabelaHashExt tabela = new TabelaHashExt(10);
        OrdemServico os1 = new OrdemServico(15, "OS1", "OS1", LocalDateTime.now());
        OrdemServico os2 = new OrdemServico(12, "OS2", "OS2", LocalDateTime.now());
        OrdemServico os3 = new OrdemServico(10, "OS3", "OS3", LocalDateTime.now());
        OrdemServico os4 = new OrdemServico(5, "OS4", "OS4", LocalDateTime.now());
        OrdemServico os5 = new OrdemServico(20, "OS5", "OS5", LocalDateTime.now());
        OrdemServico os6 = new OrdemServico(25, "OS6", "OS6", LocalDateTime.now());


        tabela.inserir(os1);
        tabela.inserir(os2);
        tabela.inserir(os3);
        tabela.inserir(os4);
        tabela.inserir(os5);
        tabela.inserir(os6);

        tabela.imprimirTabelaHash();

        System.out.println("Busca do 15 na tabela: ");
        tabela.buscar(15).os.imprimirOS();

        tabela.remover(15);
        System.out.println("Tabela pós remoção do 15:\n");
        tabela.imprimirTabelaHash();
        tabela.remover(20);
        System.out.println();
        System.out.println("Tabela pós remoção do 20:\n");
        tabela.imprimirTabelaHash();
    }
}
