package com.paper_submission_system.api.model;

import com.paper_submission_system.api.dao.PaperDAO;
import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "paper_model")
public class PaperModel implements PaperDAO {
    @Id
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "abstract_summary")
    private String abstract_summary;

    @Column(name = "filepath")
    private String filepath;

    @Column(name = "previous_version_id")
    private String previous_version_id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<AccountModel> authors;

    public PaperModel() {}

    /**
     * Create a new paper. Creates paper without authors and a previous version.
     * @param title paper title
     * @param abstract_summary paper abstract
     * @param filepath a path to the file with paper contents
     */
    public PaperModel(
            String title,
            String abstract_summary,
            String filepath
    ) {
        super();
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.abstract_summary = abstract_summary;
        this.filepath = filepath;
        this.previous_version_id = null;
        this.authors = new LinkedList<>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getAbstract_summary() {
        return this.abstract_summary;
    }

    @Override
    public String getFilepath() {
        return this.filepath;
    }

    @Override
    public String getPrevious_version_id() {
        return this.previous_version_id;
    }

    @Override
    public void addAuthors(List<AccountModel> authors) {
        this.authors.addAll(authors);
    }

    @Override
    public List<AccountModel> getAuthors() {
        return authors;
    }

    @Override
    public void setPrevious_version_id(String previous_version_id) {
        this.previous_version_id = previous_version_id;
    }
}
