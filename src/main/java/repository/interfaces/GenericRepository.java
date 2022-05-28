package repository.interfaces;

public interface GenericRepository<T, ID> {
    void create(T t, ID id);
    T read(ID id);
    void update(T t, ID id);
    void delete(T t, ID id);

    void checkConnectionToRepositoryFile();
}
