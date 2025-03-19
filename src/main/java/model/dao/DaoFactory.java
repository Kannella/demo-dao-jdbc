package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    //Macete para nao expor a implementacao, somente a interface
    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC();
    }
}
