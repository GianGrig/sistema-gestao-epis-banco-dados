import java.sql.*;
import java.util.ArrayList;

public class DevolucaoDao {

    public void inserirDevolucao(Devolucao devolucao) {
        String sql = "INSERT INTO devolucao (id_emprestimo, data_devolucao) VALUES (?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, devolucao.getId_Emprestimo());
            stmt.setString(2, devolucao.getData_devolucao());
            stmt.executeUpdate();
            System.out.println("Devolução inserida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir devolução: " + e.getMessage());
        }
    }

    public ArrayList<Devolucao> listarDevolucoes() {
        ArrayList<Devolucao> lista = new ArrayList<>();
        String sql = "SELECT * FROM devolucao";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EmprestimoDao emprestimoDao = new EmprestimoDao();
                Emprestimo emprestimo = emprestimoDao.buscarPorId(rs.getInt("id_emprestimo"));

                Devolucao devolucao = new Devolucao(
                        rs.getInt("id_devolucao"),
                        emprestimo,
                        rs.getString("data_devolucao")
                );
                lista.add(devolucao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar devoluções: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarDevolucao(Devolucao devolucao) {
        String sql = "UPDATE devolucao SET id_emprestimo = ?, data_devolucao = ? WHERE id_devolucao = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, devolucao.getId_Emprestimo());
            stmt.setString(2, devolucao.getData_devolucao());
            stmt.setInt(3, devolucao.getId_devolucao());
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Devolução atualizada com sucesso!");
            } else {
                System.out.println("Devolução não encontrada para atualização.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar devolução: " + e.getMessage());
        }
    }

    public void excluirDevolucao(int id) {
        String sql = "DELETE FROM devolucao WHERE id_devolucao = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Devolução excluída com sucesso!");
            } else {
                System.out.println("Devolução não encontrada para exclusão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir devolução: " + e.getMessage());
        }
    }
}

