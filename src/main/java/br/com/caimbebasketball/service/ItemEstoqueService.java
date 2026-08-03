package br.com.caimbebasketball.service;

import br.com.caimbebasketball.model.ItemEstoque;
import br.com.caimbebasketball.repository.ItemEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemEstoqueService {

    @Autowired
    private ItemEstoqueRepository itemEstoqueRepository;

    public List<ItemEstoque> listarTodos() {
        return itemEstoqueRepository.findAll();
    }

    public ItemEstoque salvar(ItemEstoque item) {
        return itemEstoqueRepository.save(item);
    }

    // Regra 1: Baixa no Estoque
    public ItemEstoque consumirItem(Long id, int quantidadeConsumida) {
        ItemEstoque item = itemEstoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado no estoque!"));

        if (item.getQuantidadeDisponivel() < quantidadeConsumida) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque! Disponível: " + item.getQuantidadeDisponivel());
        }

        item.setQuantidadeDisponivel(item.getQuantidadeDisponivel() - quantidadeConsumida);
        return itemEstoqueRepository.save(item);
    }

    // Regra 2: Reposição de Estoque
    public ItemEstoque reporItem(Long id, int quantidadeAdicionada) {
        ItemEstoque item = itemEstoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado no estoque!"));

        if (quantidadeAdicionada <= 0) {
            throw new IllegalArgumentException("A quantidade a ser adicionada deve ser maior que zero.");
        }

        item.setQuantidadeDisponivel(item.getQuantidadeDisponivel() + quantidadeAdicionada);
        return itemEstoqueRepository.save(item);
    }
}