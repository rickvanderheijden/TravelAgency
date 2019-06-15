package com.travelagency.repository;

import com.travelagency.model.Address;
import com.travelagency.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByTravelGroupTo(long travelGroupId);
    List<Message> findAllByReceiverId(long id);
    List<Message> findAllBySender_Id(long id);

}
