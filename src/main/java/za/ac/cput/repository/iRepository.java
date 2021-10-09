package za.ac.cput.repository;

//Group13
//Capstone - Back-End
//iRepo

@Deprecated
public interface iRepository <T, ID>{
    T create(T t);
    T read(ID id);
    T update(T t);
    void delete(ID id);
}
