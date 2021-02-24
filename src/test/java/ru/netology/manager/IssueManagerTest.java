package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Author;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.repository.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    Repository repository = new Repository();
    IssueManager manager = new IssueManager(repository);
    private Author author1 = new Author(1, "Ivan", "url:\\1");
    private Author author2 = new Author(2, "Sveta", "url:\\2");
    private Author author3 = new Author(3, "Kolia", "url:\\3");
    private Set<Author> assignees = new HashSet<>();
    private Set<Integer> number = new HashSet<>();
    private Label label1 = new Label(1, "Kotlin", "code", "Blue");
    private Label label2 = new Label(2, "Java", "component", "Green");
    private Label label3 = new Label(3, "Pyton", "bug", "Red");
    private Set<Label> labels = new HashSet<>();
    private Issue issue1 = new Issue(1, "Issue", true, "15012021","16.01.2021", author1, labels, "Netoligy Java11", "5.8M1", assignees, 2);
    private Issue issue2 = new Issue(2, "Issue", false, "30122020", "11.01.2021",author2, labels, "Netoligy Java11", "5.8M1", assignees, 4);
    private Issue issue3 = new Issue(3, "Issue", true, "01022021", "15.02.2021", author1, labels, "Netoligy Java11", "5.8M1", assignees, 8);

    @Nested
    class MultipleIssue {
        @BeforeEach
        public void setUp() {
            labels.add(label1);
            labels.add(label2);
            assignees.add(author2);
            manager.add(issue1);
            manager.add(issue2);
            manager.add(issue3);
        }

        @Test
        void shouldFilterAuthorNo() {
            List<Issue> actual = manager.filterAuthor(author3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAuthorYes() {
            List<Issue> actual = manager.filterAuthor(author1);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelNo() {
            List<Issue> actual = manager.filterLabel(label3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelYes() {
            List<Issue> actual = manager.filterLabel(label2);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeNo() {
            List<Issue> actual = manager.filterAssignee(author1);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeYes() {
            List<Issue> actual = manager.filterAssignee(author2);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);
            assertEquals(expected, actual);
        }
    }


    @Nested
    class Empty {
        @Test
        void shouldFilterAuthorNo() {
            List<Issue> actual = manager.filterAuthor(author3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelNo() {
            List<Issue> actual = manager.filterLabel(label3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeNo() {
            List<Issue> actual = manager.filterAssignee(author1);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

    }

    @Nested
    class SingleIssue {
        @BeforeEach
        public void setUp() {
            labels.add(label1);
            labels.add(label2);
            assignees.add(author1);
            manager.add(issue1);

        }

        @Test
        void shouldFilterAuthorNo() {
            List<Issue> actual = manager.filterAuthor(author3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAuthorYes() {
            List<Issue> actual = manager.filterAuthor(author1);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelNo() {
            List<Issue> actual = manager.filterLabel(label3);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterLabelYes() {
            List<Issue> actual = manager.filterLabel(label2);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeNo() {
            List<Issue> actual = manager.filterAssignee(author2);
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFilterAssigneeYes() {
            List<Issue> actual = manager.filterAssignee(author1);
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            assertEquals(expected, actual);
        }

    }
}