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
			termoPesquisa = nome.toLowerCase();
			termoPesquisa = Normalizer.normalize(termoPesquisa, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		}
		for(Programa p: this.programas) {
			String nomeCadastrado = p.nome.toLowerCase();
			nomeCadastrado = Normalizer.normalize(nomeCadastrado, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
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
	}
	
	//m�todo de adicionar programas em massa
	public void addProgramas(List<Programa> programas) {
		this.programas.addAll(programas);
	}
	
	//m�todo de deletar programa por id
	public void deletarProgramaPorId(int id) {
		for(int i = 0; i < this.programas.size(); i++) {
			if(this.programas.get(i).getId() == id) {
				this.programas.remove(i);
				break;
			}
		}
	}
		
	
}
class ordenarProgramasPorNome implements Comparator<Programa>
{
    public int compare(Programa a, Programa b)
    {
        return a.nome.compareTo(b.nome);
    }
}
