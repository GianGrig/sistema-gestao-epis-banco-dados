import java.sql.*;
import java.util.ArrayList;

public class EmprestimoDao {

    public Emprestimo buscarPorId(int id) {
        String sql = "SELECT * FROM emprestimo WHERE id_emprestimo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UsuarioDao usuarioDao = new UsuarioDao();
                    EPIDao epiDao = new EPIDao();

                    Usuario usuario = usuarioDao.buscarPorId(rs.getInt("id_usuario"));
                    EPI epi = epiDao.buscarPorId(rs.getInt("id_epi"));

                    return new Emprestimo(
                            rs.getInt("id_emprestimo"),
                            usuario,
                            epi,
                            rs.getString("data_retirada"),
                            rs.getString("data_prevista_devolucao"),
                            rs.getBoolean("confirmacao_retirada")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar empréstimo por ID: " + e.getMessage());
        }
        return null;
    }

    public void inserirEmprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (id_usuario, id_epi, data_retirada, data_prevista_devolucao, confirmacao_retirada) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getId_Usuario());
            stmt.setInt(2, emprestimo.getId_Epi());
            stmt.setString(3, emprestimo.getData_retirda());
            stmt.setString(4, emprestimo.getData_prevista_devolucao());
            stmt.setBoolean(5, emprestimo.isConfirmacao_retirada());
            stmt.executeUpdate();
            System.out.println("Empréstimo inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir empréstimo: " + e.getMessage());
        }
    }

    public ArrayList<Emprestimo> listarEmprestimos() {
        ArrayList<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            UsuarioDao usuarioDao = new UsuarioDao();  // Supondo que você tenha esse DAO
            EPIDao epiDao = new EPIDao();              // Supondo que você tenha esse DAO

            while (rs.next()) {
                int id_usuario = rs.getInt("id_usuario");
                int id_epi = rs.getInt("id_epi");

                Usuario usuario = usuarioDao.buscarPorId(id_usuario);  // Você deve implementar isso no UsuarioDao
                EPI epi = epiDao.buscarPorId(id_epi);                  // Você deve implementar isso no EPIDao

                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id_emprestimo"),
                        usuario,
                        epi,
                        rs.getString("data_retirada"),
                        rs.getString("data_prevista_devolucao"),
                        rs.getBoolean("confirmacao_retirada")
                );
                lista.add(emprestimo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizarEmprestimo(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET id_usuario = ?, id_epi = ?, data_retirada = ?, data_prevista_devolucao = ?, confirmacao_retirada = ? WHERE id_emprestimo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, emprestimo.getId_Usuario());
            stmt.setInt(2, emprestimo.getId_Epi());
            stmt.setString(3, emprestimo.getData_retirda());
            stmt.setString(4, emprestimo.getData_prevista_devolucao());
            stmt.setBoolean(5, emprestimo.isConfirmacao_retirada());
            stmt.setInt(6, emprestimo.getId_emprstimo());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Empréstimo atualizado com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado para atualização.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }

    public void excluirEmprestimo(int id) {
        String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Empréstimo excluído com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado para exclusão.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir empréstimo: " + e.getMessage());
        }
    }
}

