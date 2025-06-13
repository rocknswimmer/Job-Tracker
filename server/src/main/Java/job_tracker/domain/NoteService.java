package job_tracker.domain;

import job_tracker.data.JobRepository;
import job_tracker.data.NoteRepository;
import job_tracker.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository repository;
    private final JobRepository jobRepository;

    public NoteService(NoteRepository repository, JobRepository jobRepository) {
        this.repository = repository;
        this.jobRepository = jobRepository;
    }

    public List<Note> findByJobId(int jobId){
        return repository.findByJobId(jobId);
    }

    public Note findById(int noteId){
        return repository.findById(noteId);
    }

    public Result<Note> add(Note note){
        Result<Note> result = validate(note);

        if(!result.isSuccess()){
            return result;
        }

        Note added = repository.add(note);

        result.setPayload(added);
        return result;
    }

    public Result<Note> update(Note note){
        Result<Note> result = validate(note);

        if(!result.isSuccess()){
            return result;
        }

        if(!repository.update(note)){
            result.setPayload(null);
            result.addMessage(String.format("note with id:%s not found", note.getNoteId()), ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result<Note> deleteById(int noteId){
        Result<Note> result = new Result<>();

        if(!repository.deleteById(noteId)){
            result.addMessage(String.format("Note with id:%s not found", noteId), ResultType.NOT_FOUND);
        }
        return result;
    }


    //validations
    private Result<Note> validate(Note note){
        Result<Note> result = new Result<>();
        result.setPayload(note);

        if(note == null){
            result.addMessage("Note cannot be null", ResultType.INVALID);
            return result;
        }

        if(note.getContent() == null || note.getContent().isBlank()){
            result.addMessage("Note content is required", ResultType.INVALID);
            return result;
        }

        jobExists(result);

        return result;
    }

    private void jobExists(Result<Note> result){
        if(jobRepository.findById(result.getPayload().getJobId()) == null){
            result.addMessage(String.format("Job with id:%s does not exist", result.getPayload().getJobId()),
                    ResultType.NOT_FOUND);
        }
    }

}
