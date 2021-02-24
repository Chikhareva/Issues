package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Issue {
    public int id;
    public String title;
    private  boolean status;
    private String  creationDate;
    private String  updateDate;
    private Author author;
    private Set<Label>label;
    private String project;
    private String milestones;
    private Set<Author>assignee;
    private int countComment;
}
