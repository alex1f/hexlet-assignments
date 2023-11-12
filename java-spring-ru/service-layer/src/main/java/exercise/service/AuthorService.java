package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    public List<AuthorDTO> findALl() {
        return authorRepository.findAll().stream().map(authorMapper::map).toList();
    }

    public AuthorDTO findById(Long id){
        Author target = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + "not found"));
        return authorMapper.map(target);
    }

    public AuthorDTO create(AuthorCreateDTO authorData){
        Author precursor = authorMapper.map(authorData);
        precursor = authorRepository.save(precursor);
        return authorMapper.map(precursor);
    }

    public AuthorDTO update(Long id, AuthorUpdateDTO authorData){
        Author target = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + "not found"));
        authorMapper.update(authorData, target);
        target = authorRepository.save(target);
        return authorMapper.map(target);
    }

    public void delete(Long id){
        authorRepository.deleteById(id);
    }


    // END
}
