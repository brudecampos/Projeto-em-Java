package dao;
import projeto1.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDAO {
    private Connection c;
    
    public ClienteDAO(){
        c=new ConnectionFactory().conexao();
    }
public void adicionar (Cliente cli){
    String sql="insert into cliente values (default,?,?)";
    try {
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setString(1, cli.getNome ());
        stmt.setInt(2, cli.getIdade());
	stmt.execute();
        stmt.close();
    }catch (SQLException e) {
        throw new RuntimeException (e);
    }
}

public void alterar (Cliente cli){
    String sql="update cliente set nome=?, idade=? where id=?";
    try {
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.setString(1, cli.getNome());
        stmt.setInt(2,cli.getIdade());
        stmt.setInt(3, cli.getId());
        stmt.execute();
        stmt.close();
    } catch (SQLException e) {
        throw new RuntimeException (e);
    }
}
public void deletar (String query) {
    String sql="delete from cliente "+query;
    try {
        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.execute();
        stmt.close();
    }catch (SQLException e) {
        throw new RuntimeException (e);
    }
}
public List <Cliente> listar (String query) {
    String sql="select * from cliente "+query;
    try {
        List<Cliente> clientes=new ArrayList<Cliente>();
        PreparedStatement stmt = c.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Cliente cli = new Cliente();
            cli.setId(rs.getInt("id"));
            cli.setNome(rs.getString ("nome"));
            cli.setIdade(rs.getInt("idade"));
            clientes.add (cli);
        }
        rs.close();
        stmt.close();
        return clientes;
    }catch (SQLException e) {
        throw new RuntimeException (e);
    }
}

}

