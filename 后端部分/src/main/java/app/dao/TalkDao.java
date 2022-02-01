package app.dao;

import app.model.Talk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalkDao extends JpaRepository<Talk,Integer> {
}
