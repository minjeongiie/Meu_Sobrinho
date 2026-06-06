package model.dao.interfaces;

import model.entity.Categoria;

import java.util.List;

public interface CategoriaDAO {

    List<Categoria> listarTodas();

    Categoria buscarPorId(Long id);
}