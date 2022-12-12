/*
Alunos:
Alexandre Generoso Barroso Junior (Matricula: 792360)
Rafael Vinícius da Silva (Matricula: 794688)

Curso: Sistemas de informação, turno noturno
Data: 07/12/2022

*/ 


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Main {
	
	public static int[] extende_vetor_int(int[] velhaLista, int novoValor, int tamanho) {
        tamanho += velhaLista.length;
        int novaLista[] = new int[tamanho];
        for (int i = 0; i < velhaLista.length; i++) {
            novaLista[i] = velhaLista[i];}
        for (int i = velhaLista.length; i < tamanho; i++) {
            novaLista[i] = novoValor;}
        return novaLista;
    }
    
    
    public static int[][] extende_matriz_int(int[][] velhaLista, int novoValor[], int tamanho) {
        tamanho += velhaLista.length;
        int novaLista[][] = new int[tamanho][];
        
        int novoValorCopia[] = new int [novoValor.length];
        for (int i = 0; i < novoValorCopia.length; i++) {
            novoValorCopia[i] = novoValor[i];}
        
        for (int i = 0; i < velhaLista.length; i++) {
            novaLista[i] = velhaLista[i];}
            
        for (int i = velhaLista.length; i < tamanho; i++) {
            novaLista[i] = novoValorCopia;}
        return novaLista;
    }
        
        
    public static String[][] extende_matriz_string(String[][] velhaLista, String[] novoValor, int tamanho) {
        tamanho += velhaLista.length;
        String novaLista[][] = new String[tamanho][];
        
        for (int i = 0; i < velhaLista.length; i++) {
            novaLista[i] = velhaLista[i];
        }
            
        for (int i = velhaLista.length; i < tamanho; i++) {
            novaLista[i] = novoValor;
        }
        return novaLista;
    }
        
        
    public static String[][] arquivo_para_matriz(String caminho)  throws IOException {

        BufferedReader leitor = new BufferedReader(new FileReader(caminho));
        String texto[][] = {};
        String linha[] = {};
        String textoLinha = "";
        while (true) {
            textoLinha = leitor.readLine();
            if (textoLinha != null){
                linha = textoLiwnha.split("");
                texto = extende_matriz_string(texto, linha, 1);
            } else {
                break;
            }
        }
        leitor.close();
        return texto;
    }
    
        
	public static String caminho_labirinto(String[][] labirinto, String comeco, String saida, String piso, boolean renderizacaoGrafica) {
		
		String mapa[][] = new String [labirinto.length][labirinto[0].length];
		int dicionarioPosicaoValor[][] = new int [mapa.length][mapa[0].length];
        
        boolean achou = false;
        boolean parar = false;
        String caminho = "";
        
        
 	   int sequenciaPais[][] = {};
 	   int filho = 0;
        int inicio[] = {-1,-1};
        int fim[] = {-1,-1};
        int quantidadeReprodutores = 0;
        int quantidadeMarcadores= 1;
        int informacoes[] = {0,0,0,0,0};
        int acoes = 0;
        
		for (int i = 0; i < labirinto.length; i++) {
		    for (int j = 0; j < labirinto[i].length; j++) {
		        mapa[i][j] = labirinto[i][j];
		        dicionarioPosicaoValor[i][j] = -1;
		        if (labirinto[i][j].equals(comeco)) {
		            mapa[i][j] = ".";
		            dicionarioPosicaoValor[i][j] = 0;
		            inicio[0] = i;
		            inicio[1] = j; 
		        } else if (labirinto[i][j].equals(saida)) {
		            mapa[i][j] = piso;
		            fim[0] = i;
		            fim[1] = j;
		        }
		    }
		}
		            
		if ((inicio[0] == -1) || (inicio[1] == -1)) {
		    return "O começo não existe";
		}
		    
		if ((fim[0] == -1) || (fim[1] == -1)) {
		    return "A saída não existe";
		}
		   
		int dados[][] = {{0,0,0,inicio[0], inicio[1]}};
		dicionarioPosicaoValor[inicio[0]][inicio[1]] = 0;
		
		
		/* " " > "." > "•" > "°"
		   marcador
		   {".", " ", " "}
		   marcador vira reprodutor 
		   {"•", " ", " "}
		   reprodutor cria marcador²
		   {"•", ".", " "}
		   reprodutor vira inativo
		   {"°", ".", " "}
		   marcador² vira reprodutor²
		   {"°", "•", " "}
		   reprodutor² criar marcador³
		   {"°", "•", "."}
		   reprodutor² vira inativo²
		   {"°", "°", "."}
		   marcador³ vira reprodutor³
		   {"°", "°", "•"}
		   reprodutor³ vira inativo
		   {"°", "°", "°"} */
		 
		while (!achou) {
		    acoes = 0;
		    parar = false;
    		for (int i = 0; i < mapa.length; i++) {
    		    for (int j = 0; j < mapa[i].length; j++) {
    		        if (mapa[i][j] == ".") {
    		            quantidadeMarcadores--;
    		            mapa[i][j] = "•";
    		            quantidadeReprodutores++;
    		            acoes++;}
    		        if (quantidadeMarcadores == 0) {
    		            parar = true;
    		            break;
    		        }
    		    }
    		    if (parar) {
    		        break;
    		    }
    		}
		  		 
		    parar = false;
    		for (int i = 0; i < mapa.length; i++) {
    		    for (int j = 0; j < mapa[i].length; j++) {
    		        if (mapa[i][j] == "•") {
    		            
                       
                        informacoes[0] = dicionarioPosicaoValor[i][j]; //atual
                        
                        if ((i >= 1) && (mapa[i-1][j].equals(piso))) {
                            mapa[i-1][j] = ".";
                            informacoes[1] = 1;
                            informacoes[2] = dados.length;
                            informacoes[3] = i-1;
                            informacoes[4] = j;
                            dicionarioPosicaoValor[i-1][j] =  dados.length;
                            dados = extende_matriz_int(dados,informacoes, 1);
                            quantidadeMarcadores++;
                            acoes++;
                        }
                     
    		            if ((j < mapa[i].length-1) && (mapa[i][j+1].equals(piso))) {
                            mapa[i][j+1] = ".";
                            informacoes[1] = 2;
                            informacoes[2] = dados.length;
                            informacoes[3] = i;
                            informacoes[4] = j+1;
                            dicionarioPosicaoValor[i][j+1] =  dados.length;
                            dados = extende_matriz_int(dados,informacoes, 1);
                            quantidadeMarcadores++;
                            acoes++;
    		            }
                             
                        if ((i < mapa.length-1) && (mapa[i+1][j].equals(piso))) {
                            mapa[i+1][j] = ".";
                            informacoes[1] = 3;
                            informacoes[2] = dados.length;
                            informacoes[3] = i+1;
                            informacoes[4] = j;
                            dicionarioPosicaoValor[i+1][j] =  dados.length;
                            dados = extende_matriz_int(dados,informacoes, 1);
                            quantidadeMarcadores++;
                            acoes++;
                        }
                             
                        if ((j >= 1) && (mapa[i][j-1].equals(piso))) {
                            mapa[i][j-1] = ".";
                            informacoes[1] = 4;
                            informacoes[2] = dados.length;
                            informacoes[3] = i;
                            informacoes[4] = j-1;
                            dicionarioPosicaoValor[i][j-1] =  dados.length;
                            dados = extende_matriz_int(dados,informacoes, 1);
                            quantidadeMarcadores++;
                            acoes++;
                        }
             
            
    		            mapa[i][j] = "°";
    		            quantidadeReprodutores--;
    		            
    		            if (quantidadeReprodutores == 0) {
    		                parar = true;
    		                break;
    		            }
    		        }
    		    }
    		    if (parar) {
    		        break;
    		    }
    		}
    		
    	    if (mapa[fim[0]][fim[1]] == ".") {
    		    break;
    		}
    		if (acoes == 0) {
    		    return "Não existe um caminho até a saída!";
    		}
        }
	    
	    parar = false;
        for (int i = 0; i < dados.length; i++) {
 	       for (int j = 0; j < dados[i].length; j++) {
 	           if ((dados[i][3] == fim[0]) && (dados[i][4] == fim[1])) {
     	           filho = dados[i][2];
     	           parar = true;
     	           break;
     	       }
     	   }
 	       if (parar) {
 	           break;
 	       }
 	   }

 	   for (int i = filho; i >= 0; i--) {
            for (int j = 0; j < dados.length; j++) {
                if (dados[j][2] == filho){
 	               sequenciaPais = extende_matriz_int(sequenciaPais, dados[j], 1);
 	               filho = dados[j][0];
 	           }
 	       }
 	       if (sequenciaPais[sequenciaPais.length-1][2] == 0) {
 	           break;
 	       }
 	   }
 	           
 	   
 	   for (int i = sequenciaPais.length-1; i >= 0; i--){
 	       caminho += sequenciaPais[i][1];
 	   }
 	   caminho = caminho.replace("0","").replace("1","C");
 	   caminho = caminho.replace("2","D").replace("3","B");
 	   caminho = caminho.replace("4","E");
 	           

        //rederizaco gráfica 
        
 	   if (renderizacaoGrafica) {
 	   	       
            String textoDoLabirinto = "";
            String textoDoMapa = "";
            String textoDicionario = "";
            String textoTabelaCaminho = "";
            String textoDeImformacoes = "";
     	       
     	   for (int i = sequenciaPais.length-1; i >= 0; i--){
     	       for (int j = 0; j < sequenciaPais[i].length; j++) {
     	           textoTabelaCaminho += sequenciaPais[i][j] + ", ";
     	       }
     	       textoTabelaCaminho += "\n";
     	   }
            
    		for (int i = 0; i < labirinto.length; i++) {
    		    textoDicionario += "{"; // dicionário 
    		    for (int j = 0; j < labirinto[i].length; j++) {
    		        textoDoMapa += mapa[i][j]; //mapa
    		        textoDicionario += dicionarioPosicaoValor[i][j] + ", "; //dicionario
    		        textoDoLabirinto += labirinto[i][j]; // labirinto
    		    }
    		    textoDoLabirinto += "\n";   //labirinto 
    		    textoDoMapa += "\n"; // mapa 
    		    textoDicionario += "},\n"; // dicionário 
    		}
                
            for (int i = 0; i < dados.length; i++) {
                textoDeImformacoes += "{";
     	       for (int j = 0; j < dados[i].length; j++) {
     	           textoDeImformacoes += dados[i][j] + ", ";
     	       }
     	       textoDeImformacoes += "},\n";
     	   } 
     
    		System.out.print(textoDoLabirinto);
     	   System.out.println("\n---------------------\n");
    		System.out.print(textoDoMapa);
     	   System.out.println("\n---------------------\n");
     	   System.out.print(textoDicionario);
     	   System.out.println("\n=====================\n");
     	   System.out.print(textoDeImformacoes);
    		System.out.println("\n----------------\n");
     	   System.out.print(textoTabelaCaminho);
     	   System.out.println("\n=====================\n");
     	   System.out.println(caminho);
     	   System.out.println("\n=====================\n");
 	   }
        return caminho;
	}
	
	
	
	
	public static void main(String[] args) throws IOException{
	    
	    String local = ""; 
        if (true) {
            Scanner teclado = new Scanner(System.in); 
            while (true) {
                System.out.print("escolha o caminho ate o arquivo: ");
        		local = teclado.next();
                File file = new File(local);
                if ((!file.exists()) || (file.isDirectory())) {
                    System.out.print("arquivo inexistente\nX para proseguir: ");
                    teclado.next();
                } else {
                    System.out.println("\n--------------------\n");
                    break;
                }   
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            } 
        	teclado.close();
        } else {
            local = "mapas/mapa14.txt";
        }
        
        String mapLab[][] = arquivo_para_matriz(local);
        String textoMapLab = "";
        String caminhoLab = "";
        boolean temChave = false;
        boolean temPorta = false;

        for (int i=0; i < mapLab.length; i++) {
            for (int j=0; j < mapLab[i].length;j++) {
                textoMapLab += mapLab[i][j];
                if (mapLab[i][j].equals("k")) {
                    temChave = true;
                } else if (mapLab[i][j].equals("d")) {
                    temPorta = true;
                }
            }
            textoMapLab += "\n";
        }
        
        if (temChave && temPorta) { 
        	caminhoLab += caminho_labirinto(mapLab, "s","k"," ",false);
        	caminhoLab += caminho_labirinto(mapLab, "k","d"," ",false);
        	caminhoLab += caminho_labirinto(mapLab, "d","e"," ",false);
        } else {
        	caminhoLab += caminho_labirinto(mapLab, "s","e"," ",false);
        }
        
        System.out.println(textoMapLab);
	    System.out.println(caminhoLab);
	}
	
}