/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement st;

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();

        try {
            st = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)");
            st.setString(1, produto.getNome());
            st.setInt(2, produto.getValor());
            st.setString(3, produto.getStatus());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.\nAperte o botão 'Consultar Produtos' para ver a listagem de todos os produtos.");
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
        }

    }

    public List<ProdutosDTO> listarProdutos() {
        Connection con;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProdutosDTO> produtosLista = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?autoReconnect=true&useSSL=false", "root", "505101");
            try {
                stmt = con.prepareStatement("SELECT * FROM produtos");
                rs = stmt.executeQuery();

                while (rs.next()) {

                    ProdutosDTO produto = new ProdutosDTO();

                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setValor(rs.getInt("valor"));
                    produto.setStatus(rs.getString("status"));
                    produtosLista.add(produto);

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error, class 'ProdutosDAO' " + ex.getMessage() + "\nCheck for typos or a wrong user/password on 'DriverManager.getConnection'", "Connection error", JOptionPane.ERROR_MESSAGE);
            } finally {
                con.close();
                stmt.close();
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error, class 'ProdutosDAO' " + ex.getMessage() + "\nCheck for typos or a wrong user/password on 'DriverManager.getConnection'", "Connection error", JOptionPane.ERROR_MESSAGE);
        }

        return produtosLista;
    }

    public void venderProduto(int id) {
        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?autoReconnect=true&useSSL=false", "root", "505101")) {

            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {

                stmt.setInt(1, id);
                stmt.executeUpdate();

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error, class 'ProdutosDAO' " + ex.getMessage() + "\nCheck for typos or a wrong user/password on 'DriverManager.getConnection'", "Connection error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<ProdutosDTO> listarProdutosVendidos() {
        Connection con;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<ProdutosDTO> produtosLista = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?autoReconnect=true&useSSL=false", "root", "505101");
            try {
                stmt = con.prepareStatement("SELECT * FROM produtos WHERE status = 'Vendido'");
                rs = stmt.executeQuery();

                while (rs.next()) {

                    ProdutosDTO produto = new ProdutosDTO();

                    produto.setId(rs.getInt("id"));
                    produto.setNome(rs.getString("nome"));
                    produto.setValor(rs.getInt("valor"));
                    produto.setStatus(rs.getString("status"));
                    produtosLista.add(produto);

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error, class 'ProdutosDAO' " + ex.getMessage() + "\nCheck for typos or a wrong user/password on 'DriverManager.getConnection'", "Connection error", JOptionPane.ERROR_MESSAGE);
            } finally {
                con.close();
                stmt.close();
                rs.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error, class 'ProdutosDAO' " + ex.getMessage() + "\nCheck for typos or a wrong user/password on 'DriverManager.getConnection'", "Connection error", JOptionPane.ERROR_MESSAGE);
        }

        return produtosLista;
    }
}
