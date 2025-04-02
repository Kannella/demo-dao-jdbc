package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Seller findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
            "SELECT seller.*,department.Name as DepName\n" +
                    "FROM seller INNER JOIN department\n" +
                    "ON seller.DepartmentId = department.Id\n" +
                    "WHERE seller.Id = ?");

            st.setInt(1, id );
            rs = st.executeQuery(); //Aqui vai retornar o numero de linhas afetadas pela query

            //Esse resultSet esta apontando para a posicao 0 e essa posicao 0 nao contem objeto, eh so na
            //posicao 1 que contem, por isso eu coloco rs.next()

            //Se a minha consulta nao retornou nenhum registro, esse rs.next vai dar falso e eu vou simplesmente
            //retornar null, ou seja, nao existia nenhum vendendedor com esse id passado como parametro.

            //Se rs.next() der verdadeiro eh pq retornou os dados do meu vendedor que eu passei o ID como parametro

            // Por isso eu vou ter que navegar nesses dados que foram retornados para isntanciar os meus objetos (o vendedor
            // com o departamento pendurado nele.

            if (rs.next()) {
                //Definindo os atributos do objeto Department
                Department dep = instanciateDepartment(rs);

                //Definindo os atributos do objeto Seller
                Seller obj = instanciateSeller(rs, dep);

                return obj;
            }

            return null;


        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }


    }

    //Como eu ja tratei a excessao no getById acima eu apenas vou propagar (em vez de tratar) a excessao que der (se der) no metodo getById com o throws SQLExceprion
    private Seller instanciateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setDepartment(dep);

        return obj;
    }

    //Como eu ja tratei a excessao no getById acima eu apenas vou propagar (em vez de tratar) a excessao que der (se der) no metodo getById com o throws SQLExceprion
    private Department instanciateDepartment(ResultSet rs) throws SQLException{
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        return List.of();
    }
}
