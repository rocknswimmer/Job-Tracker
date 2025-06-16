package job_tracker.controllers;

import job_tracker.domain.NoteService;
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
}
