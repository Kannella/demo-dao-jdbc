package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    //Macete para nao expor a implementacao, somente a interface
    /*

    O metodo createSellerDao() da DaoFactory retorna um objeto do tipo SellerDao.

    Entretanto, o metodo createSellerDao retorna de fato new SellerDaoJDBC() (implementacao da interface
    SellerDao), mas isso está escondido dentro da classe DaoFactory. Ou seja, a classe Program
    nem precisa saber que SellerDaoJDBC existe.

    Entao, dessa forma, a minha classe vai expor um metodo (createSellerDao) que retorna o tipo da
    minha interface (SellerDao) mas internamente vai instanciar a minha implementacao (SellerDaoJDBC), algo que
    a classe Program nao sabe, ela sabe somente da interface e nao da implementacao concreta dela.

    Então, no seu Program (programa principal), você poderia ter 10 implementações diferentes do SellerDao
    (por exemplo, uma versão com arquivos, uma com banco de dados, uma mockada para teste…), e o Program
    continuaria funcionando igualzinho, desde que a DaoFactory retornasse a implementação correta.

    E por que isso é bom?
        Desacoplamento: o Program não depende diretamente da classe concreta (SellerDaoJDBC).
        Isso reduz a dependência entre módulos.

        Facilidade para testar: você pode criar um MockSellerDao e usar ele para testes.

        Flexibilidade: amanhã você pode trocar SellerDaoJDBC por SellerDaoHibernate, e o
        resto do programa continua igual.

    Conclusao:
        Mesmo que a implementacao SellerDaoJDBC esteja no meu projeto, o Program não sabe explicitamente
        sobre ela — ele só usa a interface SellerDao. Isso é um exemplo clássico de programar para uma interface,
        e não para a implementação em si, assim garantindo desacoplamento (em relacao a classe concreta), facilidade
        para testar e flexibilidade de trocar a maneira de acesso a dados (amanhã você pode trocar SellerDaoJDBC
        por SellerDaoHibernate, e o resto do programa continua igual)
    */
    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }
}
