package components;

import java.util.Random;

import utils.No;
import utils.TabelaEndAberto;


public class Cache {
    public TabelaEndAberto cache;
    public Cache(){
        cache = new TabelaEndAberto(20);
    }

    // verificar se o código existe na cache
    public boolean isRegistradoCache(int codigo){
        No busca = cache.buscar(codigo);
        if (busca != null){
            return true;
        }
        return false;
    }

    // busca
    public OrdemServico buscarOS(int codigo){
        No busca = cache.buscar(codigo);
        if (busca != null){
            return busca.os;
        }
        return null;
    }

    // inserção
    public void adicionarOS(OrdemServico os){
        if (isRegistradoCache(os.codigo)){
            System.out.println("Ordem de serviço já registrada na cache");
            return;
        }
        if (cache.isFull()){
            randomRemove();
        }
        
        cache.inserir(os);
    }

    // alteração
    public void alterarOS(int codigo, OrdemServico os){
        cache.alterar(codigo, os);

    }

    // remoção direta para o caso de uma remoção na base de dados
    public void remocaoDireta(int codigo){
       cache.remover(codigo);
    }

    // remoção aleatória para garantir a cache eviction
    public void randomRemove(){
        Random randomizer = new Random();
        int id_removido = randomizer.nextInt(20);
        cache.remover(id_removido);
        System.out.println("OS removida aleatoriamente da cache: " + id_removido);
    }

    public void imprimirCache(){
        cache.imprimirTabelaHash();
        System.out.println();
    }
}
