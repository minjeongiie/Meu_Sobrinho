package model.service.implementacoes;

import model.dao.implementacoes.CategoriaDAOImp;
import model.dao.interfaces.CategoriaDAO;
import model.entity.Categoria;
import model.service.interfaces.CategoriaService;

import java.util.List;

public class CategoriaServiceImp implements CategoriaService {

    private final CategoriaDAO categoriaDAO;

    public CategoriaServiceImp() {
        this.categoriaDAO = new CategoriaDAOImp();
    }

    @Override
    public List<Categoria> listarTodas() {
        return categoriaDAO.listarTodas();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return categoriaDAO.buscarPorId(id);
    }
}