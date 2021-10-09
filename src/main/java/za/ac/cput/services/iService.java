package za.ac.cput.services;

//Group13
//Capstone - Back-End
//iService

public interface iService <T, ID>{
    T create(T t);
    T read(ID id);
    T update(T t);
    boolean delete(ID id);
}
