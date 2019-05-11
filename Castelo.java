import java.util.ArrayList;


public class Castelo{
        Integer numeroDoCastelo;
        Integer numeroDeTropas;

	public Integer getNumeroDoCastelo() {
		return this.numeroDoCastelo;
	}


	public Integer getNumeroDeTropas() {
		return this.numeroDeTropas;
	}

	public void setNumeroDeTropas(Integer numeroDeTropas) {
		this.numeroDeTropas = numeroDeTropas;
	}


        ArrayList<Castelo> estradas = new ArrayList<>();

        
        public Castelo(Integer numeroDoCastelo, Integer numeroDeTropas){
                this.numeroDeTropas = numeroDeTropas;
                this.numeroDoCastelo = numeroDoCastelo;
        }

        public void addEstrada(Castelo c){
                if (this.equals(c)){
                        System.out.println("Tentando Adicionar caminho a si próprio: "+this.numeroDoCastelo);
                        return;
                }

                estradas.add(c);
        }

        public ArrayList<Castelo> getEstradas(){
                return estradas;
        }

        public int simulacaoDeAtaque(Castelo c){
                if (this.equals(c)){
                        System.out.println("Simulando ataque a si próprio: "+this.numeroDoCastelo);
                        return -1;
                }

                int necessarioParaAtacar = c.getNumeroDeTropas()*2;
                int tropasAtacantes = this.getNumeroDeTropas()-50;

                if(tropasAtacantes>necessarioParaAtacar){
                        return tropasAtacantes-necessarioParaAtacar;
                }
                return -1;
        }

        public Boolean Atacar(Castelo c){
                if (this.equals(c)){
                        System.out.println("Tentando atacar a si próprio: "+this.numeroDoCastelo);
                        return false;
                }

                int necessarioParaAtacar = c.getNumeroDeTropas()*2;
                int tropasAtacantes = this.getNumeroDeTropas()-50;

                if(tropasAtacantes>necessarioParaAtacar){
                        int resto = tropasAtacantes-necessarioParaAtacar;                        
                        c.setNumeroDeTropas(resto);
                        this.setNumeroDeTropas(50);
                        return true;
                }
                return false;
        }
}