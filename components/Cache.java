package components;

import utils.No;
import utils.TabelaHashExt;

//TODO hash de endereçamento interior/aberto

public class Cache {
    public TabelaHashInt cache;
    public Cache(){
        cache = new TabelaHashExt(20);
    }

    public boolean isRegistradoCache(int codigo){
        No busca = cache.buscar(codigo);
        if (busca != null){
            return true;
        }
        return false;
    }

    public OrdemServico buscarOS(int codigo){
        No busca = cache.buscar(codigo);
        if (busca != null){
            return busca.os;
        }
        else{
            System.out.println("Ordem de serviço não encontrada na cache");
        }
        return null;
    }

    public void adicionarOS(OrdemServico os){
        if (isRegistradoCache(os.codigo)){
            System.out.println("Ordem de serviço já registrada na cache");
            return;
        }
        else{
            
            cache.inserir(os);
        }

    }

    public void alterarOS(int codigo, OrdemServico os){
        cache.alterar(codigo, os);

    }


    public void remocaoDireta(int codigo){
       cache.remover(codigo);
    }

    public void remocaoRandom(){
        //remover um item aleatório da cache
        
    }
}
