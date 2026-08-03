package br.com.caimbebasketball.controller;

import br.com.caimbebasketball.model.ItemEstoque;
import br.com.caimbebasketball.service.ItemEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estoque")
@CrossOrigin(origins = "*")
public class ItemEstoqueController {

    @Autowired
    private ItemEstoqueService itemEstoqueService;

    // Adicionar item ao estoque (POST http://localhost:8080/api/estoque)
    @PostMapping
    public ResponseEntity<ItemEstoque> adicionar(@RequestBody ItemEstoque item) {
        return ResponseEntity.ok(itemEstoqueService.salvar(item));
    }

    // Ver estoque completo (GET http://localhost:8080/api/estoque)55
    @GetMapping
    public ResponseEntity<List<ItemEstoque>> listarTudo() {
        return ResponseEntity.ok(itemEstoqueService.listarTodos());
    }

    // Dar baixa ao entregar uniforme (PUT http://localhost:8080/api/estoque/1/baixa?quantidade=2)
    @PutMapping("/{id}/baixa")
    public ResponseEntity<ItemEstoque> darBaixa(@PathVariable Long id, @RequestParam Integer quantidade) {
        ItemEstoque itemAtualizado = itemEstoqueService.consumirItem(id, quantidade);
        return ResponseEntity.ok(itemAtualizado);
    }
}