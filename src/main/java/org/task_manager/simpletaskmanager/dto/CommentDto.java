package org.task_manager.simpletaskmanager.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private Date createdAt;
    private Long taskId;
    private Long userId;
    private String postedBy;
}
