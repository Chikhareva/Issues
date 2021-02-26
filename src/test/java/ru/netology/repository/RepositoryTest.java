package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Author;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.sort.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryTest {
    private Repository repository = new Repository();
    private Author author1 = new Author(1, "Vera", "url:\\1");
    private Author author2 = new Author(2, "Semen", "url:\\2");
    private Author author3 = new Author(3, "Slava", "url:\\3");
    private Set<Author> assignees = new HashSet<>();
    private Set<Integer> number = new HashSet<>();
    private Label label1 = new Label(1, "Kotlin", "code", "Blue");
    private Label label2 = new Label(2, "Java", "component", "Green");
    private Label label3 = new Label(3, "Pyton", "bug", "Red");
    private Set<Label> labels = new HashSet<>();
    private Issue issue1 = new Issue(1, "Issue", false,"15.01.2021", "16.01.2021", author1, labels, "Netoligy Java11", "5.8M1", assignees, 2);
    private Issue issue2 = new Issue(2, "Issue", true, "30.12.2020", "11.01.2021", author2, labels, "Netoligy Java11", "5.8M1", assignees, 4);
    private Issue issue3 = new Issue(3, "Issue", true, "01.02.2021", "15.02.2021", author1, labels, "Netoligy Java11", "5.8M1", assignees, 8);


    @Nested
    class MultipleIssue {
        @BeforeEach
        public void setUp() {
            labels.add(label1);
            labels.add(label2);
            assignees.add(author2);
            repository.add(issue1);
            repository.add(issue2);
            repository.add(issue3);
        }

        @Test
        void shouldOutputListAllOpenIssue() {
            List<Issue> actual = repository.findOpen(repository.getAll());
            List<Issue> expected = new ArrayList<>();
            expected.add(issue2);
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOutputListAllCloseIssue() {
            List<Issue> actual = repository.findClose(repository.getAll());
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpenIssue() {
            repository.openById(repository.getAll(), 2);
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(new Issue(2, "Issue", true, "30.12.2020", "11.01.2021", author2, labels, "Netoligy Java11", "5.8M1", assignees, 4));
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseIssue() {
            repository.closeById(repository.getAll(), 1);
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(new Issue(1, "Issue", false, "15.01.2021", "16.01.2021", author1, labels, "Netoligy Java11", "5.8M1", assignees, 2));
            expected.add(issue2);
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldMostComment() {
            Collections.sort(repository.getAll(), new MostPopularComment());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            expected.add(issue2);
            expected.add(issue1);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortLeastComment() {
            Collections.sort(repository.getAll(), new LeastComment());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationNew() {
            Collections.sort(repository.getAll(), new NewSort());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            expected.add(issue1);
            expected.add(issue2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationOld() {
            Collections.sort(repository.getAll(), new OldSort());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue2);
            expected.add(issue1);
            expected.add(issue3);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateNew() {
            Collections.sort(repository.getAll(), new RecentlyUpdated());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue3);
            expected.add(issue1);
            expected.add(issue2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateOld() {
            Collections.sort(repository.getAll(), new LastUpdate());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            expected.add(issue2);
            expected.add(issue1);
            expected.add(issue3);
            assertEquals(expected, actual);
        }

    }

    @Nested
    class Empty {
        @Test
        void shouldOutputListAllOpenIssue() {
            List<Issue> actual = repository.findOpen(repository.getAll());
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOutputListAllCloseIssue() {
            List<Issue> actual = repository.findClose(repository.getAll());
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpenIssue() {
            repository.openById(repository.getAll(), 2);
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseIssue() {
            repository.closeById(repository.getAll(), 1);
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortMostComment() {
            Collections.sort(repository.getAll(), new MostPopularComment());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortLeastComment() {
            Collections.sort(repository.getAll(), new LeastComment());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationNew() {
            Collections.sort(repository.getAll(), new NewSort());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationOld() {
            Collections.sort(repository.getAll(), new OldSort());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateNew() {
            Collections.sort(repository.getAll(), new RecentlyUpdated());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateOld() {
            Collections.sort(repository.getAll(), new LastUpdate());
            List<Issue> actual = repository.getAll();
            List<Issue> expected = new ArrayList<>();
            assertEquals(expected, actual);
        }
    }

    @Nested
    class SingleIssue {
        @BeforeEach
        public void setUp() {
            repository.add(issue2);
        }

        @Test
        void shouldOutputListAllOpenIssue() {
            List<Issue> actual = repository.findOpen(repository.getAll());
            List<Issue> expected=new ArrayList<>();
            expected.add(issue2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldOutputListAllCloseIssue() {
            List<Issue> actual=repository.findClose(repository.getAll());
            List<Issue> expected=new ArrayList<>();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpenIssue() {
            repository.openById(repository.getAll(), 2);
            List<Issue> actual=repository.getAll();
            List<Issue> expected=new ArrayList<>();
            expected.add(new Issue(2, "Issue", true, "30.12.2020", "11.01.2021", author2, labels, "Netoligy Java11", "5.8M1", assignees, 4));
            assertEquals(expected, actual);
        }

        @Test
        void shouldCloseIssue() {
            repository.closeById(repository.getAll(), 2);
            List<Issue> actual=repository.getAll();
            List<Issue> expected=new ArrayList<>();
            expected.add(new Issue(2, "Issue", false, "30.12.2020", "11.01.2021", author2, labels, "Netoligy Java11", "5.8M1", assignees, 4));
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortMostComment() {
            Collections.sort(repository.getAll(), new MostPopularComment());
            List<Issue> actual= repository.getAll();
            List<Issue> expected=new ArrayList<>();
            expected.add(issue2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortLeastComment() {
            Collections.sort(repository.getAll(), new LeastComment());
            List<Issue> actual= repository.getAll();
            List<Issue> expected=new ArrayList<>();
            expected.add(issue2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationNew() {
            Collections.sort(repository.getAll(),new NewSort());
            List<Issue> actual= repository.getAll();
            List<Issue> expected=new ArrayList<>();
            expected.add(issue2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateCreationOld() {
            Collections.sort(repository.getAll(),new OldSort());
            List<Issue> actual= repository.getAll();
            List<Issue> expected=new ArrayList<>();
            expected.add(issue2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateNew() {
            Collections.sort(repository.getAll(),new RecentlyUpdated());
            List<Issue> actual= repository.getAll();
            List<Issue> expected=new ArrayList<>();
            expected.add(issue2);
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortDateUpdateOld() {
            Collections.sort(repository.getAll(),new LastUpdate());
            List<Issue> actual= repository.getAll();
            List<Issue> expected=new ArrayList<>();
            expected.add(issue2);
            assertEquals(expected, actual);
        }
    }
}
