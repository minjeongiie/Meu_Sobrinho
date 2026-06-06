package model.service.interfaces;

import model.entity.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> listarTodas();

    Categoria buscarPorId(Long id);
}