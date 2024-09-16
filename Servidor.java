public class Servidor {
    static int totalRegistros;
    public Servidor(){
        totalRegistros = 0;
    }

    public static int getTotalRegistros(){
        return totalRegistros;
    }

    public OrdemServico buscarOS(int codigo){
        // Código para buscar a Ordem de Serviço
        return null;
    }

    public void CadastrarOS(OrdemServico os){
        // Código para cadastrar a Ordem de Serviço
    }

    public void listarOS(){
        // Código para listar as Ordens de Serviço
    }

    public void alterarOS(int codigoOS, OrdemServico os){
        // Código para alterar a Ordem de Serviço
    }

    public void removerOS(int codigoOS){
        // Código para remover a Ordem de Serviço
    }   

    public int qtRegistros(){
        // Código para retornar a quantidade de registros
        return 0;
    }
}
