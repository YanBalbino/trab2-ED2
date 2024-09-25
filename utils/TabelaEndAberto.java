package utils;


import components.OrdemServico;


public class TabelaEndAberto{
    int M;
    No tabela[];
    int primoMenor;
   
    public TabelaEndAberto(int tam) {
       
        this.M = tam;
        this.tabela = new No[this.M];
   
    }


    public boolean isFull(){
        for (int i = 0; i < this.M; i++){
            if (this.tabela[i] == null){
                return false;
            }
        }
        return true;
    }


    private int hash(int ch){
         double A = (Math.sqrt(5) - 1) / 2;
        return (int) (M * ((ch * A) % 1));
    }


    public int tentativaLinear(int ch, int k) {
        return (hash(ch) + k) % this.M;
    }

    public void inserir(OrdemServico nova) {


        int tentativa = 0;
        int h = this.tentativaLinear(nova.getCodigo(), tentativa);
       
        while(this.tabela[h] != null && tentativa < this.M) {
           
            if (this.tabela[h].os.getCodigo() == nova.getCodigo()) {
                break;
            }  
           
            h = this.tentativaLinear(nova.getCodigo(), ++tentativa);
        }
           
        if (this.tabela[h] == null) {
           
            this.tabela[h] = new No();
            this.tabela[h].os = nova;
            return;
        }


        System.out.println("Quantidade de tentativas excedeu o limite");
    }
   
    public No buscar(int codigo) {
        int tentativa = 0;
        int h = this.tentativaLinear(codigo, tentativa);
       
        while(this.tabela[h] != null && tentativa < this.M) {
           
            if (this.tabela[h].os.getCodigo() == codigo) {
                return this.tabela[h];
            }  
           
            h = this.tentativaLinear(codigo, ++tentativa);
        }
        return null;
       
    }
   
    public void alterar(int codigo, OrdemServico alterada){
       
        int tentativa = 0;
        int h = this.tentativaLinear(codigo, tentativa);
       
        while(this.tabela[h] != null) {
           
            if (this.tabela[h].os.getCodigo() == codigo) {
                this.tabela[h].os = alterada;
                break;
            }  
           
            h = this.tentativaLinear(codigo, ++tentativa);
        }
    }


    public void remover(int codigo) {
        int tentativa = 0;
        int h = this.tentativaLinear(codigo, tentativa);
       
        while(this.tabela[h] != null && tentativa < this.M) {
           
            if (this.tabela[h].os.getCodigo() == codigo) {
                this.tabela[h] = null;
                break;
            }  
           
            h = this.tentativaLinear(codigo, ++tentativa);
        }
    }


    public void imprimirTabelaHash() {
       
        for(int i = 0; i < this.M; i++) {
           
            if (this.tabela[i] != null)
                System.out.println(i + " --> " + this.tabela[i].os.getCodigo() + " | " + this.tabela[i].os.getNome()
                + " | " + this.tabela[i].os.getDescricao() + " | " + this.tabela[i].os.getData() + "\n");
            else
                System.out.println(i);
        }
       
    }
}







