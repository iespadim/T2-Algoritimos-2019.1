import java.io.*;
import java.util.ArrayList;

public class PlanejadorDeEstrategias {
	public static void main(String[] args) {	
		String nomeDoArquivo = "caso30.txt";	

		try {
			// configura e/s	
			String path= (new File(".").getAbsolutePath());		
			FileReader arq = new FileReader(path+"\\T2-Algoritimos-2019.1\\"+nomeDoArquivo);
			
			BufferedReader reader = new BufferedReader(arq);
			String linha = reader.readLine();
			String[] palavras = linha.split(" ");

			Integer tamanhoExercitoSiberio = Integer.parseInt(palavras[0]);
			Integer castelosDaVizinhanca = Integer.parseInt(palavras[1]);
			Integer estradasDaRegiao = Integer.parseInt(palavras[2]);
			System.out.println(castelosDaVizinhanca+" castelos na vizinhança. "+estradasDaRegiao+" estradas pelas redondezas.");
			
			ArrayList<Castelo> castelos = new ArrayList<Castelo>();
			Castelo casteloInicial = new Castelo(0, tamanhoExercitoSiberio);
			castelos.add(casteloInicial);
			System.out.println("Novo castelo: "+0+", "+tamanhoExercitoSiberio+" tropas.");

			//Le os castelos e cria todos.
			for (int i = 0; i < castelosDaVizinhanca; i++) {
				linha = reader.readLine();
				palavras = linha.split(" ");
				
				Integer numeroDoCastelo = Integer.parseInt(palavras[0]);
				Integer numeroDeTropas = Integer.parseInt(palavras[1]);

				
				castelos.add(new Castelo(numeroDoCastelo, numeroDeTropas));
				System.out.println("Novo castelo: "+numeroDoCastelo+", "+numeroDeTropas+" tropas.");
			}

			//le todas as estradas e linka elas
			for (int i = 0; i < estradasDaRegiao; i++) {
				linha = reader.readLine();
				palavras = linha.split(" ");

				Integer casteloOrigem = Integer.parseInt(palavras[0]);
				Integer casteloDestino = Integer.parseInt(palavras[1]);
				Castelo origem = null;
				Castelo destino = null;

				Boolean achouOrigem = false;
				Boolean achouDestino = false;

				for (Castelo c : castelos) {
					if(c.getNumeroDoCastelo().equals(casteloDestino)){
						destino = c;
						achouDestino = true;
					}else if(c.getNumeroDoCastelo().equals(casteloOrigem)){
						origem = c;
						achouOrigem = true;
					}					
				}

				if(achouDestino && achouOrigem){
					origem.addEstrada(destino);
					System.out.println("Castelo "+casteloOrigem+" tem uma rota para o castelo "+casteloDestino);
				}
			}

			Castelo pivo = casteloInicial;
			boolean modoDeAtaque = true;

			while(modoDeAtaque){
				System.out.println(pivo.getNumeroDoCastelo()+" vai atacar");
				if(modoDeAtaque && pivo.getEstradas().size()>0){					
					Castelo melhorCanditato = pivo.getEstradas().get(0);
					int maisAltas = 0;

					for (Castelo c : pivo.getEstradas()) {
						int resto = pivo.simulacaoDeAtaque(c);					
						if (resto>maisAltas ) {
							melhorCanditato = c;
						}
					}

					modoDeAtaque = pivo.Atacar(melhorCanditato);
					pivo = melhorCanditato;
				}
			}
			// fim de arquivo;
			arq.close();


		} catch (IOException e) {
			String format = "Erro na abertura do arquivo: %s.\n";
			System.err.printf(format, e.getMessage());
		}

		System.exit(0);
	}
}