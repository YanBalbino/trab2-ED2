package utils;

import components.OrdemServico;

public class TabelaHashExt {
	int M;
	No tabela[];
	
	public TabelaHashExt(int tam) {
		setTam(tam);
		this.tabela = new No[this.M];
	}
	
	public No getNoAt(int i){
		return this.tabela[i];
	}

	public int getTam(){
		return this.M;
	}

	public void setTam(int tam){
		if (tam < 0){
			return;
		}
		if (tam < this.getTam()){
			return;
		}
		this.M = tam;
	}

	public double fatorCarga(){
		int qt = 0;
		No no;
		for (int i = 0; i < this.getTam(); i++){
			no = this.tabela[i];
			if (no != null){
				qt++;
			}
		}
		return (double) qt / this.M;
	}

	public TabelaHashExt resize(int novoTam){
		TabelaHashExt novaTabela = new TabelaHashExt(novoTam);
		No no;
		for (int i = 0; i < this.M; i++){
			no = this.tabela[i];
			while (no != null){
				novaTabela.inserir(no.os);
				no = no.proximo;
			}
		}
		return novaTabela;
	}

	public int hash(int ch) {
        // mudar função de hash
		return ch % this.M;
	}

	public void inserir(OrdemServico nova) {
		
		int h = this.hash(nova.getCodigo());
		No no = this.tabela[h];
		
		while(no != null) {
			
			if (no.os.getCodigo() == nova.getCodigo()) {
				break;
			}	
			
			no = no.proximo;
		}
		
		if (no == null) {
			
			no = new No();
			no.os = nova;
			no.proximo = this.tabela[h];
			this.tabela[h] = no;
		}
		
	}
	
	public No buscar(int codigo) {
		
		int h = this.hash(codigo);
		No no = this.tabela[h];
		
		while(no != null) {
			
			if (no.os.getCodigo() == codigo) {
				return no;
			}	
			
			no = no.proximo;
		}
		return null;
		
	}

	public void alterar(int codigo, OrdemServico alterada) {
		
		int h = this.hash(codigo);
		No no = this.tabela[h];
		
		while(no != null) {
			
			if (no.os.getCodigo() == codigo) {
				no.os = alterada;
				break;
			}	
			
			no = no.proximo;
		}
		
	}

	public void remover(int codigo) {
		
		int h = this.hash(codigo);
		No no = this.tabela[h];
		No anterior = null;
		
		while(no != null) {
			
			if (no.os.getCodigo() == codigo) {
				break;
			}	
			
			anterior = no;
			no = no.proximo;
		}
		
		if (no == null) {
			return;
		}
		
		if (anterior == null) {
			this.tabela[h] = no.proximo;
		} else {
			anterior.proximo = no.proximo;
		}
		
	}
	
	public void imprimirTabelaHash() {
		No no;
		
		for(int i = 0; i < this.M; i++) {
			
			no = this.tabela[i];
			
			System.out.println(i + ":");
			
			while (no != null) {
				System.out.print(" --> ");
				no.os.imprimirOS();
				System.out.println();
				no = no.proximo;
				
			}
			System.out.println();
			
		}
	}
}
