package com.paper_submission_system.api.controller;

import com.paper_submission_system.api.model.AccountModel;
import com.paper_submission_system.api.model.PaperModel;
import com.paper_submission_system.api.repository.AccountRepository;
import com.paper_submission_system.api.repository.PaperRepository;
import com.paper_submission_system.api.exception.ResourceNotFoundException;
import com.paper_submission_system.api.request.CreatePaperRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api/v1/")
public class PaperController {
    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Get all papers in the system.
     */
    @GetMapping("/papers/all")
    public List<PaperModel> gelAllPapers() {
        return paperRepository.findAll();
    }

    /**
     * Get all paper for a specified author.
     * @param author_id ID of the author whose papers to get
     */
    @GetMapping("/papers/author/{author_id}")
    public List<PaperModel> getAllAuthorPapers(@PathVariable String author_id) {
        List<PaperModel> allPapers = paperRepository.findAll();
        AccountModel authorToSearch = accountRepository.findById(author_id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Error: account does not exist. Account ID: " + author_id
                ));
        allPapers.removeIf(paper -> !paper.getAuthors().contains(authorToSearch));

        return allPapers;
    }

    /**
     * Create a new paper instance.
     */
    @PostMapping("/papers/create")
    public ResponseEntity<PaperModel> createPaper(@RequestBody CreatePaperRequest paperRequestParams) {
        PaperModel paper = createPaperObject(
                paperRequestParams.title,
                paperRequestParams.abstract_summary,
                paperRequestParams.filepath,
                paperRequestParams.author_ids
        );
        paperRepository.save(paper);

        return ResponseEntity.ok(paper);
    }

    /**
     * Update a paper. New version will be linked to the previous version
     * in the field :previous_version_id:.
     * @param paper_id ID of the updating paper
     */
    @PutMapping("/papers/update/{paper_id}")
    public ResponseEntity<PaperModel> updatePaper(
            @PathVariable String paper_id,
            @RequestBody CreatePaperRequest paperRequestParams
    ) {
        PaperModel oldPaperModel = paperRepository.findById(paper_id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Error: paper does not exist. Paper ID: " + paper_id
                ));

        PaperModel newPaperModel = createPaperObject(
                paperRequestParams.title,
                paperRequestParams.abstract_summary,
                paperRequestParams.filepath,
                paperRequestParams.author_ids
        );
        newPaperModel.setPrevious_version_id(oldPaperModel.getId());
        paperRepository.save(newPaperModel);

        return ResponseEntity.ok(newPaperModel);
    }

    /**
     * Delete a paper.
     * @param paper_id ID of the deleting paper
     */
    @DeleteMapping("/papers/delete/{paper_id}")
    public ResponseEntity<Map<String, Boolean>> deletePaper(@PathVariable String paper_id) {
        PaperModel paperToDelete = paperRepository.findById(paper_id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Error: paper does not exist. Paper ID: " + paper_id
                ));
        paperRepository.delete(paperToDelete);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(response);
    }

    /**
     * Create a new PaperModel.
     * @param title paper title
     * @param abstract_summary paper abstract
     * @param filepath the path to the paper file
     * @param author_ids IDs of paper authors
     */
    private PaperModel createPaperObject(
            String title, String abstract_summary, String filepath, String[] author_ids
    ) {
        PaperModel paper = new PaperModel(title, abstract_summary, filepath);
        List<AccountModel> authors = accountRepository.findAllById(List.of(author_ids));
        paper.addAuthors(authors);

        return paper;
    }
}
