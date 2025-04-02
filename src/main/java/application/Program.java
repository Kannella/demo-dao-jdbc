package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        //O metodo createSellerDao() da DaoFactory retorna um objeto do tipo SellerDao.
        SellerDao sellerDao = DaoFactory.createSellerDao(); //Macete para nao expor a implementacao, somente a interface. Uma forma de fazer a injecao de dependencia sem mostrar a implementacao

        System.out.println("=== TEST 1: seller findById =====");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

    }
}
