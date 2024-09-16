// importar cache

import java.time.LocalDateTime;

public class Cliente{
    private Cache cache;
    public Cliente(){
        cache = new Cache();
    }

    public OrdemServico buscarOS(int codigo, Servidor servidor){
        // Código para buscar a Ordem de Serviço
        return null;
    }

    public void CadastrarOS(OrdemServico os, Servidor servidor){
        // Código para cadastrar a Ordem de Serviço
    }

    public void listarOS(Servidor servidor){
        // Código para listar as Ordens de Serviço
    }

    public void alterarOS(int codigoOS, OrdemServico os, Servidor servidor){
        // Código para alterar a Ordem de Serviço
    }

    public void removerOS(int codigoOS, Servidor servidor){
        // Código para remover a Ordem de Serviço
    }   

    public int qtRegistros(Servidor servidor){
        // Código para retornar a quantidade de registros
        return 0;
    }

    public void gerarOS(Servidor servidor){
        // Código para gerar as primeiras 70 OS
        System.out.println("Primeiras 70 OS geradas.");
        for (int i = 0; i < 70; i++){
            OrdemServico os = new OrdemServico(i + 1, "OS numero" + i + 1, "Descrição da OS " + i + 1, LocalDateTime.now());
            CadastrarOS(os, servidor);
        }
    }
}