package com.paper_submission_system.api.dao;

import com.paper_submission_system.api.model.AccountModel;

import java.util.List;


public interface PaperDAO {
    /**
     * Get ID of this paper.
     * @return ID
     */
    String getId();

    /**
     * Get title of this paper.
     * @return title
     */
    String getTitle();

    /**
     * Get abstract summary of this paper.
     * @return abstract summary
     */
    String getAbstract_summary();

    /**
     * Get file path of this paper.
     * @return file path
     */
    String getFilepath();

    /**
     * Get previous version ID of this paper.
     * @return previous version ID
     */
    String getPrevious_version_id();

    /**
     * Add authors to this paper.
     * @param authors list of AccountModel objects
     */
    void addAuthors(List<AccountModel> authors);

    /**
     * Get all authors of this paper.
     * @return list of AccountModel objects
     */
    List<AccountModel> getAuthors();

    /**
     * Assign a previous version to this paper.
     * @param previous_version_id ID of the previous version
     */
    void setPrevious_version_id(String previous_version_id);
}
