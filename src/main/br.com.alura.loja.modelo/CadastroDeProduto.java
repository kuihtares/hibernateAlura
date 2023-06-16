package br.com.alura.loja.modelo;

import br.com.alura.loja.modelo.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        Produto p = produtoDAO.buscarPorId(1l);
        System.out.println(p.getPreco());

       List<Produto> todos = produtoDAO.buscarTodos();
       todos.forEach(p2 -> System.out.println(p.getNome()));
        List<Produto> todosNome = produtoDAO.buscarNome("Xiaomi Redmi");
        todosNome.forEach(p2 -> System.out.println(p.getNome()));
        List<Produto> filtroCategoria = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
        filtroCategoria.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal precoDoProduto = produtoDAO.buscarPrecoProduto("Xiaomi Redmi");
        System.out.println(precoDoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares );

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDao = new ProdutoDAO(em);
        CategoriaDAO categoriaDao = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
