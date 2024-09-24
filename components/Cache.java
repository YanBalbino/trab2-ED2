package components;

import java.util.Random;

import utils.No;
import utils.TabelaComDuploHash;


public class Cache {
    public TabelaComDuploHash cache;
    public Cache(){
        cache = new TabelaComDuploHash(20);
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
        return null;
    }

    public void adicionarOS(OrdemServico os){
        if (isRegistradoCache(os.codigo)){
            System.out.println("Ordem de serviço já registrada na cache");
            return;
        }
        else if (cache.isFull()){
            randomRemove();
        }
        cache.inserir(os);

    }

    public void alterarOS(int codigo, OrdemServico os){
        cache.alterar(codigo, os);

    }

    public void remocaoDireta(int codigo){
       cache.remover(codigo);
    }

    public void randomRemove(){
        Random randomizer = new Random();
        int id_removido = randomizer.nextInt(20);
        cache.remover(id_removido);
        System.out.println("OS removida aleatoriamente: " + id_removido);
    }

    public void imprimirCache(){
        cache.imprimirTabelaHash();
    }
}
