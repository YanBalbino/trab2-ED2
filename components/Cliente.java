package components;

import java.time.LocalDateTime;


public class Cliente{
    private Cache cache;

    public Cliente(){
        cache = new Cache();
    }

    // buscar
    public OrdemServico buscarOS(int codigo, Servidor servidor){
        OrdemServico busca = cache.buscarOS(codigo);
        if (busca == null){
            //System.out.println("Ordem de serviço não registrada na cache, buscando no servidor");
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

    // cadastrar
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

    // listar todas as informações no servidor
    public void listarOS(Servidor servidor){
        servidor.listarOS();
    }

    public void imprimirCache(){
        cache.imprimirCache();
    }

    // alterar 
    public void alterarOS(int codigoOS, OrdemServico os, Servidor servidor){
        if (os == null){
            System.out.println("Ordem de serviço inválida");
            return;
        }
        if (servidor.isRegistrado(codigoOS) == false && cache.isRegistradoCache(codigoOS) == false){
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

    // remover
    public void removerOS(int codigoOS, Servidor servidor){
        if (servidor.isRegistrado(codigoOS) == false  && cache.isRegistradoCache(codigoOS) == false){
            System.out.println("Código de OS não registrado no sistema");
            return;
        }
        servidor.removerOS(codigoOS);
        cache.remocaoDireta(codigoOS);
    }  

    // acessar a quantidade atual de registros na base de dados
    public int qtRegistros(Servidor servidor){
        return servidor.qtRegistrosAtual();
    }

    // gerar as primeiras 70 OS para testes
    public void gerarOSInicio(Servidor servidor){
        // Código para gerar as primeiras 70 OS
        System.out.println("Primeiras 70 OS geradas.");
        for (int i = 0; i < 70; i++){
            OrdemServico os = new OrdemServico(i, "OS número " + i, "Descrição da OS " + i, LocalDateTime.now());
            CadastrarOS(os, servidor);
        }
    }

    // preencher a cache com 20 buscas
    public void preencherCache(Servidor servidor){
        for (int i = 0; i < 20; i++){
            buscarOS(i, servidor);
        }
    }
}
