package Serraflix;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Biblioteca{
	private ArrayList<Programa> programas = new ArrayList<>();
	//construtor vazio
	public Biblioteca() {
		
	}
	
	//listar programas por tipo, categoria, e nome
	public ArrayList<Programa> listarProgramas(int opcao, Categoria cat, String nome){
		ArrayList<Programa> programas = new ArrayList<>();
		String termoPesquisa = "";
		if(nome != null) {
			termoPesquisa = Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
		}
		for(Programa p: this.programas) {
			String nomeCadastrado = "";
			if(opcao == 5) {
				nomeCadastrado = Normalizer.normalize(p.nome, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
			}
			if((opcao == 1 && p instanceof Filme) || (opcao == 2 && p instanceof Serie) || (opcao == 3) || (opcao == 4 && p.getCategoria().equals(cat)) || (opcao == 5 && nomeCadastrado.contains(termoPesquisa)) ) {
				programas.add(p);
			}
		}
		Collections.sort(programas, new ordenarProgramasPorNome());
		return programas;
	}
	
	//m�todo de adicionar um programa
	public void addPrograma(Programa programa) {
		this.programas.add(programa);
		programa.getCategoria().aumentarContador();
	}
	
	//m�todo de adicionar programas em massa
	public void addProgramas(List<Programa> programas) {
		this.programas.addAll(programas);
		for(Programa p: programas) {
			p.getCategoria().aumentarContador();
		}
	}
	
	//m�todo de deletar programa por indice
	public void deletarProgramaPorIndice(int indice) {
		this.programas.get(indice).getCategoria().diminuirContador();
		this.programas.remove(indice);
	}
	
	//m�todo de encontrar �ndice do programa por id
	public int encontrarIndicePorId(int id) {
		int indice = -1;
		for(int i = 0; i < this.programas.size(); i++) {
			if(this.programas.get(i).getId() == id) {
				indice = i;
				break;
			}
		}
		return indice;
	}
	
	/*
	public String infoPrograma(Programa prog) {
		String retorno = "";
		if(prog instanceof Filme) {
			
			retorno = "\n" + prog.nome + " | FILME\n\n"
					 + "NOTA: " + (prog.pontuacao == null ? "SEM NOTA" : String.format("%.1f", prog.pontuacao) + "/5") + "\n"
					 + "CATEGORIA: " + prog.getCategoria().getNomeCategoria() + "\n"
					 + "DURA��O: " + ((Filme)prog).getDuracao() + " MINUTOS";
			
		}else {		
			
			retorno = "\n" + prog.nome + " | S�RIE\n\n"
					 + "NOTA: " + (prog.pontuacao == null ? "SEM NOTA" : String.format("%.1f", prog.pontuacao) + "/10") + "\n"
					 + "CATEGORIA: " + prog.getCategoria().getNomeCategoria() + "\n\n"
					 + "TEMPORADAS: " + ((Serie)prog).getNumeroTemporas() + "\n\n";
			for(Temporada t: ((Serie)prog).getTemporadas()) {
				retorno += String.format("%02d", t.getNomeTemporada()) + "� TEMP: " + String.format("%02d", t.getQuantidadeEpisodios()) + " EPS\n";
			}
			retorno += "\nTOTAL DE EPIS�DIOS: " + ((Serie)prog).getTotalEpisodios();
			
		}
		return retorno;	
	}*/
	
}
class ordenarProgramasPorNome implements Comparator<Programa>
{
    public int compare(Programa a, Programa b)
    {
        return a.nome.compareTo(b.nome);
    }
}
