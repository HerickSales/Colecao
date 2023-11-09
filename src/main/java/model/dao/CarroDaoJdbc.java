package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Carro;

public class CarroDaoJdbc implements InterfaceDao<Carro> {
    private Connection conn;
    
    public CarroDaoJdbc() throws Exception {
        conn = ConnFactory.getConnection();
    }

    @Override
    public void incluir(Carro entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO CARROS (NOME, PLACA, STATUS, KILOMETRAGEM, ANO, OBSERVACAO, FOTO) VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getPlaca());
            ps.setString(3, entidade.getStatus());
            ps.setInt(4, entidade.getKilometragem());
            ps.setString(5, entidade.getAno());
            ps.setString(6, entidade.getObservacao());
            ps.setString(7, entidade.getFoto());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void editar(Carro entidade) throws Exception {
       try {
            PreparedStatement ps = conn.prepareStatement("UPDATE CARROS SET NOME=?, PLACA=?, STATUS=?, KILOMETRAGEM=?, ANO=?, OBSERVACAO=?, FOTO=?  WHERE id=?");
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getPlaca());
            ps.setString(3, entidade.getStatus());
            ps.setInt(4, entidade.getKilometragem());
            ps.setString(5, entidade.getAno());
            ps.setString(6, entidade.getObservacao());
            ps.setString(7, entidade.getFoto());
            ps.setInt(8,entidade.getId());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void excluir(Carro entidade) throws Exception {
       try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM CARROS WHERE id=?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public Carro pesquisarPorId(int id) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM CARROS WHERE ID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Carro c = new Carro();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("NOME"));
                c.setPlaca(rs.getString("PLACA"));
                c.setStatus(rs.getString("STATUS"));
                c.setKilometragem(rs.getInt("KILOMETRAGEM"));
                c.setAno(rs.getString("ANO"));
                c.setObservacao(rs.getString("OBSERVACAO"));
                c.setFoto(rs.getString("FOTO"));
                return c;
            } else {
                return null;
            }   
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    @Override
    public Carro pesquisarPorPlaca(String placa) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM CARROS WHERE PLACA=?");
            ps.setString(1, placa);
            rs = ps.executeQuery();
            if (rs.next()) {
                Carro c = new Carro();
                c.setId(rs.getInt("ID"));
                c.setNome(rs.getString("NOME"));
                c.setPlaca(rs.getString("PLACA"));
                c.setStatus(rs.getString("STATUS"));
                c.setKilometragem(rs.getInt("KILOMETRAGEM"));
                c.setAno(rs.getString("ANO"));
                c.setObservacao(rs.getString("OBSERVACAO"));
                c.setFoto(rs.getString("FOTO"));
                return c;
            } else {
                return null;
            }   
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<Carro> listar(String param) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {            
            if (param.equals("")) {
                ps = conn.prepareStatement("SELECT * FROM CARROS");
            } else {
                ps = conn.prepareStatement(
                        "SELECT * FROM CARROS WHERE NOME like '%" + param + "%' or PLACA like'%"+param+"%' ");
            }
            rs = ps.executeQuery();
            List<Carro> lista = new ArrayList();
            while (rs.next()) {
                Carro c = new Carro();
                 c.setId(rs.getInt("ID"));
                c.setNome(rs.getString("NOME"));
                c.setPlaca(rs.getString("PLACA"));
                c.setStatus(rs.getString("STATUS"));
                c.setKilometragem(rs.getInt("KILOMETRAGEM"));
                c.setAno(rs.getString("ANO"));
                c.setObservacao(rs.getString("OBSERVACAO"));
                c.setFoto(rs.getString("FOTO"));
                lista.add(c);
            }
            return lista;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
}
