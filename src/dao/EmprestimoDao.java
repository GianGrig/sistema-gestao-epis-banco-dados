package dao;

import conexao.Conexao;
import modelo.Emprestimo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDao {

    private Connection conexao;

    public EmprestimoDao() {
        conexao = Conexao.conectar();
    }

    public void inserirEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (id_usuario, id_epi, data_retirada, data_prevista_devolucao, confirmacao_retirada) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getId_usuario());
            stmt.setInt(2, emprestimo.getId_epi());
            stmt.setString(3, emprestimo.getData_retirada());
            stmt.setString(4, emprestimo.getData_prevista_devolucao());
            stmt.setInt(5, emprestimo.getConfirmacao_retirada());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir Empréstimo: " + e.getMessage());
        }
    }

    public List<Emprestimo> listarEmprestimos() {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id_emprestimo"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_epi"),
                        rs.getString("data_retirada"),
                        rs.getString("data_prevista_devolucao"),
                        rs.getInt("confirmacao_retirada")
                );
                lista.add(emprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar Empréstimos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET id_usuario = ?, id_epi = ?, data_retirada = ?, data_prevista_devolucao = ?, confirmacao_retirada = ? WHERE id_emprestimo = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getId_usuario());
            stmt.setInt(2, emprestimo.getId_epi());
            stmt.setString(3, emprestimo.getData_retirada());
            stmt.setString(4, emprestimo.getData_prevista_devolucao());
            stmt.setInt(5, emprestimo.getConfirmacao_retirada());
            stmt.setInt(6, emprestimo.getId_emprestimo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir Empréstimo: " + e.getMessage());
        }
    }

    public void excluirEmprestimo(int id) {
        String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("EPI excluído com sucesso!");
            } else {
                System.out.println("EPI não encontrado para exclusão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir Empréstimo: " + e.getMessage());
        }
    }

    public boolean existeEmprestimoParaUsuario(int idUsuario) {
        String sql = "SELECT COUNT(*) FROM emprestimo WHERE id_usuario = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir Empréstimo: " + e.getMessage());
        }
        return false;
    }
}
