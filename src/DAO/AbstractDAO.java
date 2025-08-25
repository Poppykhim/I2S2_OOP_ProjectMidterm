package DAO;

public abstract class AbstractDAO<T> {

    public abstract boolean addProduct(T entity);

    public abstract boolean deleteProduct(int id);

    public abstract boolean updateProductField(int id, String fieldName, Object newValue);

}
