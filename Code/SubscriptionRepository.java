package com.subclear.repository;

import com.subclear.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserIdOrderByDueDateAsc(String userId);
    List<Subscription> findByUserIdAndActiveOrderByDueDateAsc(String userId, boolean active);
}
