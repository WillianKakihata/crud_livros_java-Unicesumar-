package com.example.demo.service;

import com.example.demo.dto.LivroDTO;
import com.example.demo.model.Livraria;
import com.example.demo.model.StatusLivros;
import com.example.demo.repository.LivrariaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class LivrariaService {

    @Autowired
    private LivrariaRepository livrariaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LivroDTO create(Livraria livraria) {
        Livraria livros = this.livrariaRepository.save(livraria);
        return modelMapper.map(livros, LivroDTO.class);
    }

    public List<LivroDTO> findall() {
        List<Livraria> livros = this.livrariaRepository.findAll();
        return modelMapper.map(livros, new TypeToken<List<LivroDTO>>(){}.getType());
    }

    public void delete(Long id) {
        livrariaRepository.deleteById(id);
    }

    public List<Livraria> findStatus(StatusLivros status) {
        return livrariaRepository.findByStatus(status);
    }

    public Livraria getLivros(Long id) {
        Livraria livros = livrariaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("Livro nao encontrado")));
        return livros;
    }
    public Livraria update(Livraria novosDados, Long id) {
        Livraria livroExistente = getLivros(id);
        if (livroExistente == null) {
            return null;
        }

        modelMapper.map(novosDados, livroExistente);
        return livrariaRepository.save(livroExistente);
        /*if (livros != null) {
            livros.setNome(livraria.getNome());
            livros.setAutor(livraria.getAutor());
            livros.setStatus(livraria.getStatus());
            return livrariaRepository.save(livros);
        }
        */

    }

    public Livraria emprestimoLivro(Livraria livraria, Long id) {
        Livraria livros = livrariaRepository.findById(id).orElse(null);
        if (livros != null) {
            if (livros.getStatus() == StatusLivros.DISPONIVEL) {
                livros.setStatus(StatusLivros.EM_EMPRESTIMO);
                return livrariaRepository.save(livros);
            }else
                return null;
        }
        return livros;

    }

    public Livraria reservarLivro(Livraria livraria, Long id) {
        Livraria livros = livrariaRepository.findById(id).orElse(null);
        if (livros != null) {
            if (livros.getStatus() == StatusLivros.DISPONIVEL) {
                livros.setStatus(StatusLivros.RESERVADO);
                return livrariaRepository.save(livros);
            }else
                return null;
        }
        return livros;


    }



}
