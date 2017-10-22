package pagehopping;

import java.util.Scanner;

class Main {
	
	
	class Grafo{
		public static final int INFINITO = 101;
		private int[][] matrizDistancia;
		

		public Grafo(int linhas, int colunas) {
			this.matrizDistancia = new int[linhas][colunas];
			//inicioaliza a matriz com 0 nos encontros do proprios vertices e 
			// infinito nos demais
			for (int i = 1; i < matrizDistancia.length; i++) {
				for (int j = 1; j < matrizDistancia.length; j++) {
					if(i==j){
						matrizDistancia[i][j] = 0;
					}
					else{
						matrizDistancia[i][j]=INFINITO;
					}
				}
			}
		}
						
		public void adicionarAresta(int origemId, int destinoId) {
			this.matrizDistancia[origemId][destinoId] = 1;
			
			
		}
		
		public void removerAresta(int origemId, int destinoId) {
			matrizDistancia[origemId][destinoId] = INFINITO;
			
			
		}

		
		public void imprimirGrafo() {
			for (int i = 1; i < matrizDistancia.length; i++) {
				for (int j = 1; j < matrizDistancia.length; j++) {
					if (matrizDistancia[i][j]==INFINITO){
	                    System.out.print("INF ");
					}
	                else{
	                  System.out.print(matrizDistancia[i][j]+" ");
	                }
						
				}
				System.out.println();
			}
				
		}		
		//Media Distancia Caminhos Minimos
		public double pegarMediaDistancias(int[][] distGrafo, int tam){
			int distanciasCaminhos = 0;
			int paresPossiveis = 0;
			
			for (int i = 1; i < tam; i++){
	            for (int j = 1; j < tam; j++){
	            	if(i!=j && distGrafo[i][j] < INFINITO){
	            		distanciasCaminhos += distGrafo[i][j];
	            		paresPossiveis++;
	            	}
	            }
			}
			
			return (double)distanciasCaminhos/(double)paresPossiveis;
			
		}
		
		//Floyd-Warshall
		public int[][] gerarMatrizCaminhosMinimosTodosPares(){
			//n = W.linhas
			int n = matrizDistancia.length;
		
			//D(0)=W
			int[][] dist = new int[n][n];
			for (int i = 1; i < n; i++){
	            for (int j = 1; j < n; j++){
	                dist[i][j] = matrizDistancia[i][j];
	            }
			}
			
			for (int k = 1; k < n; k++) {				
				for(int i = 1; i < n; i++){
					for (int j = 1; j < n; j++) {
						dist[i][j] = Integer.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}					
			}
			
			
			return dist;
			
			
		}

	}

	private static final int MAX = 101;
	private static Scanner scanner;
	
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in); 
		Main obj = new Main();
		
		Grafo grafoPaginas = null;
		int[][] matriz;
		boolean primeiroParDoCasoTeste = false;
		boolean fimLinhaTeste = false;
		int origem, destino;
		int numeroCasoTeste=1;		
		
		
		while(!primeiroParDoCasoTeste){
			primeiroParDoCasoTeste = true;
			grafoPaginas = obj.new Grafo(MAX,MAX);
			while(!fimLinhaTeste){
				
				origem = scanner.nextInt();
				destino = scanner.nextInt();
				
				if(origem == 0 && destino == 0){
					if(primeiroParDoCasoTeste){
						return;
					}else{
						fimLinhaTeste=true;
					}
										
				}else{
					
					grafoPaginas.adicionarAresta(origem, destino);
					primeiroParDoCasoTeste=false;
				}	
				
			}
			
			fimLinhaTeste = false;
			double media = grafoPaginas.pegarMediaDistancias(grafoPaginas.gerarMatrizCaminhosMinimosTodosPares(), MAX);
		
			
			System.out.println("Case "+numeroCasoTeste+": average length between pages = "+
					String.format("%.3f", media)+" clicks");
			numeroCasoTeste++;		
			
		}
		
		
		

	}

}

