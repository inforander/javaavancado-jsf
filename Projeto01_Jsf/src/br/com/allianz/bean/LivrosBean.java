package br.com.allianz.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.allianz.dao.LivrosDao;
import br.com.allianz.models.Livro;

@ManagedBean(name = "beanLivro")
@RequestScoped
public class LivrosBean {

	private Livro livro;

	public LivrosBean() {
		if (livro == null) {
			livro = new Livro();
		}
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	// action method when executed return the redirection conformed by result execution
	public String incluirLivro() {
		try {
			new LivrosDao().incluirLivro(livro);
			return "sucesso";
		} catch (Exception e) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage();
			
			msg.setSummary("OK");
			msg.setDetail("");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			e.printStackTrace();
			return "erro";
		}
	}
	
	//property for use show book list
	public List<Livro> getListaLivros() throws Exception{
		return new LivrosDao().listarLivros();
	}
}
