package repository.postgresql;

import org.junit.Test;

import static org.junit.Assert.*;

public class PostgresQLSkillRepositoryImplTest {
    private PostgresQLSkillRepositoryImpl postgresRepo = new PostgresQLSkillRepositoryImpl();

    @Test
    public void getAllNotNullTest() {
        assertNotNull(postgresRepo.getAll());
    }
}