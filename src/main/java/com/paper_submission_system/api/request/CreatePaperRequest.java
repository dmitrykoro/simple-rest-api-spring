/**
 * Request model for creating Paper
 */
package com.paper_submission_system.api.request;


public class CreatePaperRequest {
    public String title;

    public String abstract_summary;

    public String filepath;

    public String[] author_ids;
}
