package components;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import utils.No;
import utils.TabelaHashEncadeada;

public class Servidor {
    private static int totalRegistros;
    private TabelaHashEncadeada baseDados;
    private File logs;

    public Servidor(){
        totalRegistros = 0;
        
        // considerando os 70 elementos
        // 70 / 5 = 14
        // 70 / 10 = 7
        // primos entre 14 e 7: 11, 13
        // tamanho inicial escolhido: 11
        baseDados = new TabelaHashEncadeada(11);

        // abertura do arquivo de logs
        try{
            this.logs = new File("logs.log");
            if (this.logs.exists()){
                this.logs.delete();
                this.logs.createNewFile();
            } else {
                this.logs.createNewFile();
            }

        } catch (IOException e){
            System.out.println("Erro ao criar arquivo de logs de operações.");
        }
    }

    public void setBaseDados(TabelaHashEncadeada bd){
        this.baseDados = bd;
    }

    private TabelaHashEncadeada getBD(){
        return this.baseDados;
    }

    // verifica se uma OS existe na base de dados
    public boolean isRegistrado(int codigo){
        No busca = baseDados.buscar(codigo);
        if (busca != null){
            return true;
        }
        return false;
    }

    // retorna o total de registros para geração automática de código de OS
    public static int getTotalRegistros(){
        return totalRegistros;
    }

    // buscar
    public OrdemServico buscarOS(int codigo){
        No busca = baseDados.buscar(codigo);
        if (busca != null){
            atualizarLog("Busca");
            return busca.os;
        }
        else{
            System.out.println("Ordem de serviço não encontrada.");
        }
        atualizarLog("Busca");
        return null;
    }   

    // cadastrar
    public void CadastrarOS(OrdemServico os){
        if (baseDados.fatorCarga() > 0.9){
            expandirBD(baseDados.getTam());
        }
        baseDados.inserir(os);
        totalRegistros++;

        atualizarLog("Cadastro");
    }

    // listar
    public void listarOS(){
        baseDados.imprimirTabelaHash();
        atualizarLog("Listagem");
    }

    // alterar
    public void alterarOS(int codigoOS, OrdemServico os){
        baseDados.alterar(codigoOS, os);
        System.out.println("Ordem de serviço alterada no servidor com sucesso");
        atualizarLog("Alteração");
    }

    //remover
    public void removerOS(int codigoOS){
        baseDados.remover(codigoOS);
        atualizarLog("Remoção");
    }  

    // quantidade de registros atual
    public int qtRegistrosAtual(){
        return baseDados.getQtRegistros();
    }

    // função de expansão da base de dados
    public void expandirBD(int tamAtual){
        TabelaHashEncadeada expandida = baseDados.resize(tamAtual * 2);
        setBaseDados(expandida);
    }

    // função de atualização do arquivo de logs
    private void atualizarLog(String operacao){
        TabelaHashEncadeada bd = getBD();
        No temp;

        try{
            FileWriter fw = new FileWriter(this.logs, true);
            fw.write("\nOperação: " + operacao);
            fw.close();
        } catch (IOException e){
            System.out.println("Erro ao atualizar arquivo de logs.");
        }

        for (int i = 0; i < bd.getTam(); i++){
            temp = bd.getNoAt(i);
            try{
                FileWriter fw;
                while (temp != null){
                    fw = new FileWriter(this.logs, true);
                    
                    fw.write("\n");
                    fw.write(temp.os.getCodigo() + " " + temp.os.getNome() +  " " + temp.os.getDescricao() 
                    +  " " + temp.os.getData() + "\n");
    
                    fw.close();
                    
                    temp = temp.proximo;
                }
                

            } catch (IOException e){
                System.out.println("Erro ao atualizar arquivo de logs.");
            }
        }
    }
}
