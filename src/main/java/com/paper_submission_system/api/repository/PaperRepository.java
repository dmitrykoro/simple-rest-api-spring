package com.paper_submission_system.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.paper_submission_system.api.model.PaperModel;


public interface PaperRepository extends JpaRepository<PaperModel, String> {

}
