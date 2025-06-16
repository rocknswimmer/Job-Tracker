package job_tracker.controllers;

import job_tracker.domain.NoteService;
import job_tracker.domain.Result;
import job_tracker.models.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {
    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/{jobId}")
    public List<Note> findByJobId(@PathVariable int jobId){
        return service.findByJobId(jobId);
    }

    @GetMapping("/note/{noteId}")
    public Note findById(@PathVariable int noteId){
        return service.findById(noteId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Note note){
        Result<Note> result = service.add(note);
        System.out.println(note);
        if(result.isSuccess()){
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/note/{noteId}")
    public ResponseEntity<Object> update(@PathVariable int noteId, @RequestBody Note note){
        if(noteId != note.getNoteId()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Note> result = service.update(note);
        if(result.isSuccess()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/note/{noteId}")
    public ResponseEntity<Void> deleteById(@PathVariable int noteId){
        if(service.deleteById(noteId).isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
