package model.dao.implementacoes;

import model.dao.interfaces.CategoriaDAO;
import model.entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImp implements CategoriaDAO {

    private static final List<Categoria> categorias = new ArrayList<>();

    static {

        categorias.add(new Categoria(1L, "Frontend"));
        categorias.add(new Categoria(2L, "Backend"));
        categorias.add(new Categoria(3L, "Manutenção"));
        categorias.add(new Categoria(4L, "Infraestrutura"));

    }

    @Override
    public List<Categoria> listarTodas() {
        return categorias;
    }

    @Override
    public Categoria buscarPorId(Long id) {

        for (Categoria categoria : categorias) {

            if (categoria.getId().equals(id)) {
                return categoria;
            }

        }

        return null;
    }
}