package app.dao;

import app.model.MyApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyAppDao extends JpaRepository<MyApp,Integer> {
}

