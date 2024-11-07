package br.com.fiap.dao;

import br.com.fiap.to.TarefaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TarefaDAO extends Repository{

    public TarefaTO inserir(int ordem, TarefaTO tarefa) {

        String sql = "INSERT INTO t_tarefas (nome_tarefa, custo_tarefa, data_limite, ordem_apresentacao) VALUES (?, ?, ?,?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)){

            ps.setString(1, tarefa.getNome());
            ps.setDouble(2, tarefa.getCusto());
            ps.setDate(3, java.sql.Date.valueOf(tarefa.getDataLimite()));
            ps.setInt(4, ordem);

            if (ps.executeUpdate() > 0) {
                return tarefa;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println( "Erro de SQL: " + e.getMessage());
        }catch (Exception e){
            System.out.println("erro: " +e.getMessage());
        }finally {
            closeConnection();
        }
        return null;

    }


    public String alterar(TarefaTO tarefa, int idTarefa) {
        String sql = "UPDATE t_tarefas SET nome_tarefa= ?, custo_tarefa=?, data_limite=? WHERE id_tarefa=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(4, idTarefa);
            ps.setString(1, tarefa.getNome());
            ps.setDouble(2, tarefa.getCusto());
            ps.setDate(3, java.sql.Date.valueOf(tarefa.getDataLimite()));

            if (ps.executeUpdate() > 0) {
                return "Alterado com sucesso.";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        } finally {
            closeConnection();
        }
    }

    public String setOrdem(int ordem, int idTarefa) {

        System.out.println("Enviado ordem: " + ordem + ", idTarefa: " + idTarefa);
        String sql = "UPDATE t_tarefas SET ordem_apresentacao= ? WHERE id_tarefa=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(2, idTarefa);
            ps.setInt(1, ordem);

            if (ps.executeUpdate() > 0) {
                System.out.println("Alterado com sucesso");
                return "Alterado com sucesso.";
            } else {
                return "Erro ao alterar";
            }
        } catch (SQLException e) {
            return "Erro de SQL: " + e.getMessage();
        } finally {
            closeConnection();
        }
    }

    public String excluir(int idTarefa) {
        String sql = "delete from t_tarefas where id_tarefa=?";
        try(PreparedStatement ps = getConnection().prepareStatement(sql)){

            ps.setInt(1, idTarefa);
            if (ps.executeUpdate() > 0) {
                return "Excluído com sucesso";
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return "Erro ao excluir";
    }


    public TarefaTO getTarefaById(int idTarefa){
        TarefaTO tarefa = new TarefaTO();
        String sql = "select * from t_tarefas where id_tarefa =?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);) {
            ps.setInt(1, idTarefa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                tarefa.setIdTarefa(rs.getInt("id_tarefa"));
                tarefa.setNome(rs.getString("nome_tarefa"));
                tarefa.setCusto(rs.getDouble("custo_tarefa"));
                tarefa.setDataLimite(rs.getDate("data_limite").toLocalDate());
                tarefa.setOrdem(rs.getInt("ordem_apresentacao"));

            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }
        return tarefa;
    }

    public ArrayList<TarefaTO> listarTarefas(){

        String sql = "select * from t_tarefas";
        ArrayList<TarefaTO> listaTarefas = new ArrayList<>();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TarefaTO tarefa = new TarefaTO();
                tarefa.setIdTarefa(rs.getInt("id_tarefa"));
                tarefa.setNome(rs.getString("nome_tarefa"));
                tarefa.setCusto(rs.getDouble("custo_tarefa"));
                tarefa.setDataLimite(rs.getDate("data_limite").toLocalDate());
                tarefa.setOrdem(rs.getInt("ordem_apresentacao"));

                listaTarefas.add(tarefa);
            }


            return listaTarefas;

        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return null;
        }

    }









}
