package components;

import java.time.LocalDateTime;


public class Cliente{
    private Cache cache;

    public Cliente(){
        cache = new Cache();
    }

    public OrdemServico buscarOS(int codigo, Servidor servidor){
        OrdemServico busca = cache.buscarOS(codigo);
        if (busca == null){
            busca = servidor.buscarOS(codigo);
            if (busca == null){
                return null;
            }
            else{
                cache.adicionarOS(busca);
            }
        }
        return busca;
    }

    public void CadastrarOS(OrdemServico os, Servidor servidor){
        if (os == null){
            System.out.println("Ordem de serviço inválida");
            return;
        }
        if (os.codigo < 0){
            System.out.println("Código inválido");
            return;
        }
        if (os.nome == null || os.nome.trim().equals("")){
            System.out.println("Nome inválido");
            return;
        }
        if (os.descricao == null || os.descricao.trim().equals("")){
            System.out.println("Descrição inválida");
            return;
        }
        servidor.CadastrarOS(os);
        
    }

    public void listarOS(Servidor servidor){
        servidor.listarOS();
    }

    public void imprimirCache(){
        cache.imprimirCache();
    }

    public void alterarOS(int codigoOS, OrdemServico os, Servidor servidor){
        if (os == null){
            System.out.println("Ordem de serviço inválida");
            return;
        }
        if (servidor.isRegistrado(codigoOS) == false){
            System.out.println("Código de OS não registrado no sistema");
            return;
        }
        if (os.nome == null || os.nome.trim().equals("")){
            System.out.println("Nome inválido");
            return;
        }
        if (os.descricao == null || os.descricao.trim().equals("")){
            System.out.println("Descrição inválida");
            return;
        }

        servidor.alterarOS(codigoOS, os);  
        if (cache.isRegistradoCache(codigoOS)){
            cache.alterarOS(codigoOS, os);
        }
        else{
            System.out.println("Ordem de serviço não registrada na cache, nenhuma alteração necessária");
        }
    }

    public void removerOS(int codigoOS, Servidor servidor){
        if (servidor.isRegistrado(codigoOS) == false){
            System.out.println("Código de OS não registrado no sistema");
            return;
        }
        servidor.removerOS(codigoOS);
        cache.remocaoDireta(codigoOS);
    }  

    public int qtRegistros(Servidor servidor){
        return servidor.qtRegistrosAtual();
    }

    public void gerarOSInicio(Servidor servidor){
        // Código para gerar as primeiras 70 OS
        System.out.println("Primeiras 70 OS geradas.");
        for (int i = 0; i < 70; i++){
            OrdemServico os = new OrdemServico(i, "OS número " + i, "Descrição da OS " + i, LocalDateTime.now());
            CadastrarOS(os, servidor);
        }
    }

    public void preencherCache(Servidor servidor){
        for (int i = 0; i < 20; i++){
            buscarOS(i, servidor);
        }
    }
}
