package org.sefglobal.scholarx.controller;

import org.sefglobal.scholarx.exception.BadRequestException;
import org.sefglobal.scholarx.exception.ResourceNotFoundException;
import org.sefglobal.scholarx.model.Mentor;
import org.sefglobal.scholarx.model.Program;
import org.sefglobal.scholarx.service.ProgramService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramController {
    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Program getProgramById(@PathVariable long id) throws ResourceNotFoundException {
        return programService.getProgramById(id);
    }

    @GetMapping("/{id}/mentors")
    @ResponseStatus(HttpStatus.OK)
    public List<Mentor> getAllMentorsByProgramId(@PathVariable long id)
            throws ResourceNotFoundException {
        return programService.getAllMentorsByProgramId(id);
    }

    @PostMapping("/{id}/mentor")
    @ResponseStatus(HttpStatus.CREATED)
    public Mentor applyAsMentor(@PathVariable long id,
                                @Valid @RequestBody Mentor mentor)
            throws ResourceNotFoundException, BadRequestException {
        long profileId = 1; // TODO: Get the profileId from headers
        return programService.applyAsMentor(id, profileId, mentor);
    }
}
