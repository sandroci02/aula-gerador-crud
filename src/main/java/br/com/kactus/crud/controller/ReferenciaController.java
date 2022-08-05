package br.com.kactus.crud.controller;

import java.util.List;

import br.com.kactus.crud.Exception.NotFoundException;
import br.com.kactus.crud.message.ReferenciaMessage;
import br.com.kactus.crud.model.Referencia;
import br.com.kactus.crud.service.ReferenciaService;
import br.com.kactus.crud.util.PageData;
import br.com.kactus.crud.util.Paginador;
import br.com.kactus.crud.util.ParametroData;
import br.com.kactus.crud.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/referencia")
public class ReferenciaController {
    @Autowired
    private ReferenciaService service;

    @PostMapping("/save")
    public ResponseEntity<ReturnData> save(@RequestBody Referencia referencia) {
        return ResponseEntity.ok(
                ReturnData.builder()
                        .entidade(service.save(referencia))
                        .mensagem(ReferenciaMessage.REFERENCIA_INCLUIDA_COM_SUCESSO)
                        .build());
    }

    @PutMapping("/update")
    public ResponseEntity<ReturnData> update(@RequestBody Referencia referencia) throws NotFoundException {
        return ResponseEntity.ok(
                ReturnData.builder()
                        .entidade(service.update(referencia))
                        .mensagem(ReferenciaMessage.REFERENCIA_ALTERADA_COM_SUCESSO)
                        .build());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Referencia> find(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ok(service.find(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PageData> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
        service.remove(id);
        return ResponseEntity.ok(
                PageData.builder()
                        .mensagem(ReferenciaMessage.REFERENCIA_EXCLUIDA_COM_SUCESSO)
                        .build());
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Referencia>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }


    //@CrossOrigin
    @PostMapping("/list")
    public ResponseEntity<PageData> list(@RequestBody ParametroData entrada) {
        Page<Referencia> list = service.list(entrada);
        return ResponseEntity.ok(
                PageData.builder()
                        .lista(list.getContent())
                        .paginador(new Paginador(list, entrada))
                        .build());
    }
}
