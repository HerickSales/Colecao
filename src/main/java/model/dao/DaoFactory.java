package model.dao;

public class DaoFactory {
    public static CarroDaoJdbc novoCarroDao() throws Exception {
        return new CarroDaoJdbc();
    }
}
