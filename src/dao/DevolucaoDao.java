package dao;

import conexao.Conexao;
import modelo.Devolucao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevolucaoDao {

    private Connection conexao;

    public DevolucaoDao() {
        conexao = Conexao.conectar();
    }

    public void inserirDevolucao(Devolucao devolucao) {
        String sql = "INSERT INTO devolucao (id_emprestimo, data_devolucao) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, devolucao.getId_emprestimo());
            stmt.setString(2, devolucao.getData_devolucao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Devolucao> listarDevolucoes() {
        List<Devolucao> devolucoes = new ArrayList<>();
        String sql = "SELECT * FROM devolucao";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Devolucao devolucao = new Devolucao(
                        rs.getInt("id_devolucao"),
                        rs.getInt("id_emprestimo"),
                        rs.getString("data_devolucao")
                );
                devolucoes.add(devolucao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devolucoes;
    }

    public void atualizarDevolucao(Devolucao devolucao) {
        String sql = "UPDATE devolucao SET id_emprestimo = ?, data_devolucao = ? WHERE id_devolucao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, devolucao.getId_emprestimo());
            stmt.setString(2, devolucao.getData_devolucao());
            stmt.setInt(3, devolucao.getId_devolucao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirDevolucao(int id) {
        String sql = "DELETE FROM devolucao WHERE id_devolucao = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
