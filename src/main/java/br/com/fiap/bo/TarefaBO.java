package br.com.fiap.bo;

import br.com.fiap.dao.TarefaDAO;
import br.com.fiap.to.TarefaTO;

import java.util.ArrayList;

public class TarefaBO {

    private TarefaDAO tarefaDAO;

    public TarefaTO inserir(int ordem, TarefaTO tarefa) {
        tarefaDAO = new TarefaDAO();
        // Aqui se implementa a regra de negócios
        return tarefaDAO.inserir(ordem, tarefa);

    }

    public String alterar(TarefaTO tarefa, int idTarefa) {
        tarefaDAO = new TarefaDAO();
        // Aqui se implementa a regra de negócios
        return tarefaDAO.alterar(tarefa, idTarefa);
    }

    public String excluir(int idTarefa) {
        tarefaDAO = new TarefaDAO();
        // Aqui se implementa a regra de negócios
        return tarefaDAO.excluir(idTarefa);
    }

    public TarefaTO getTarefaById(int idTarefa){
        tarefaDAO = new TarefaDAO();
        // Aqui se implementa a regra de negócios
        return tarefaDAO.getTarefaById(idTarefa);
    }

    public String setOrdem(int ordem, int idTarefa){
        tarefaDAO = new TarefaDAO();
        // Aqui se implementa a regra de negócios
        return tarefaDAO.setOrdem(ordem, idTarefa);
    }

    public ArrayList<TarefaTO> listarTarefas(){
        tarefaDAO = new TarefaDAO();
        // Aqui se implementa a regra de negócios
        return tarefaDAO.listarTarefas();
    }
}
