package utils;

import components.OrdemServico;

public class TabelaComDuploHash{
    int M;
	No tabela[];
    int primoMenor;
	
	public TabelaComDuploHash(int tam) {
		
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

	private int hashA(int ch){
		double A = (Math.sqrt(5) - 1) / 2; 
		return (int) (M * ((ch * A) % 1));
	}

	private int hashB(int ch){
		return 1 + (ch % (this.M - 3)); // -3 pra garantir um primo menor que M (20 - 3)
	}

	public int hash(int ch, int k) {
		return (hashA(ch) + k*(hashB(ch))) % this.M; 
	}
	
	public void inserir(OrdemServico nova) {
		
		int tentativa = 0;
		int h = this.hash(nova.getCodigo(), tentativa);
		
		while(this.tabela[h] != null) {
			
			if (this.tabela[h].os.getCodigo() == nova.getCodigo()) {
				break;
			}	
			
			h = this.hash(nova.getCodigo(), ++tentativa);
			
		}
			
		if (this.tabela[h] == null) {
			
			this.tabela[h] = new No();
			this.tabela[h].os = nova;
			
		}
		
	}
	
	public No buscar(int codigo) {
		
		int tentativa = 0;
		int h = this.hash(codigo, tentativa);
		
		while(this.tabela[h] != null) {
			
			if (this.tabela[h].os.getCodigo() == codigo) {
				return this.tabela[h];
			}	
			
			h = this.hash(codigo, ++tentativa);
		}
		return null;
		
	}
	
	public void alterar(int codigo, OrdemServico alterada){
		
		int tentativa = 0;
		int h = this.hash(codigo, tentativa);
		
		while(this.tabela[h] != null) {
			
			if (this.tabela[h].os.getCodigo() == codigo) {
				this.tabela[h].os = alterada;
				break;
			}	
			
			h = this.hash(codigo, ++tentativa);
		}
	}

	public void remover(int codigo) {
		
		int tentativa = 0;
		int h = this.hash(codigo, tentativa);
		
		while(this.tabela[h] != null) {
			
			if (this.tabela[h].os.getCodigo() == codigo) {
				this.tabela[h] = null;
				break;
			}	
			
			h = this.hash(codigo, ++tentativa);
		}
	}

	public void imprimirTabelaHash() {
		
		for(int i = 0; i < this.M; i++) {
			
			if (this.tabela[i] != null)
				System.out.println(i + " --> " + this.tabela[i].os.getCodigo() + " - " + this.tabela[i].os.getNome() 
				+ " - " + this.tabela[i].os.getDescricao() + " - " + this.tabela[i].os.getData());
			else
				System.out.println(i);
		}
		
	}
}


