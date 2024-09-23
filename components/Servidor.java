package components;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import utils.No;
import utils.TabelaHashExt;

public class Servidor {
    static int totalRegistros;
    private TabelaHashExt baseDados;
    File logs;

    public Servidor(){
        totalRegistros = 0;
        // fazer tamanho expansível
        baseDados = new TabelaHashExt(20);

        try{
            this.logs = new File("logs.txt");
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

    public void setBaseDados(TabelaHashExt bd){
        this.baseDados = bd;
    }

    private TabelaHashExt getBD(){
        return this.baseDados;
    }

    public boolean isRegistrado(int codigo){
        No busca = baseDados.buscar(codigo);
        if (busca != null){
            return true;
        }
        return false;
    }

    public static int getTotalRegistros(){
        return totalRegistros;
    }

    public OrdemServico buscarOS(int codigo){
        No busca = baseDados.buscar(codigo);
        if (busca != null){
            atualizarLog("Busca");
            return busca.os;
        }
        else{
            System.out.println("Ordem de serviço não encontrada na base de dados.");
        }
        atualizarLog("Busca");
        return null;
    }   

    // Tenho a impressão de estar fazendo isso muito errado
    public void CadastrarOS(OrdemServico os){
        if (baseDados.fatorCarga() > 0.75){
            expandirBD(baseDados.getTam());
        }
        baseDados.inserir(os);
        totalRegistros++;

        atualizarLog("Cadastro");
    }

    public void listarOS(){
        baseDados.imprimirTabelaHash();
        atualizarLog("Listagem");
    }

    public void alterarOS(int codigoOS, OrdemServico os){
        atualizarLog("Alteração");
        baseDados.alterar(codigoOS, os);
        System.out.println("Ordem de serviço alterada no servidor com sucesso");
    }

    public void removerOS(int codigoOS){
        baseDados.remover(codigoOS);
        atualizarLog("Remoção");
    }  

    public int qtRegistrosAtual(){
        int qt = 0;
        TabelaHashExt bd = getBD();
        for (int i = 0; i < bd.getTam(); i++){
            No no = bd.getNoAt(i);

            while (no != null){
                qt++;
                no = no.proximo;
            }
        }

        return qt;
    }

    public void expandirBD(int tamAtual){
        TabelaHashExt expandida = baseDados.resize((int)(tamAtual * 1.5));
        setBaseDados(expandida);
    }

    private void atualizarLog(String operacao){
        TabelaHashExt bd = getBD();
        No temp;

        for (int i = 0; i < bd.getTam(); i++){
            temp = bd.getNoAt(i);
            try{
                FileWriter fw = new FileWriter(this.logs, true);

                fw.write(operacao + "\n");
                fw.close();

                while (temp != null){
                    fw = new FileWriter(this.logs, true);
    
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
