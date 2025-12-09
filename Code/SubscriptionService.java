package com.subclear.service;

import com.subclear.model.Subscription;
import com.subclear.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {
    
    @Autowired
    private SubscriptionRepository repository;
    
    public List<Subscription> getAllSubscriptions(String userId) {
        return repository.findByUserIdOrderByDueDateAsc(userId);
    }
    
    public Optional<Subscription> getSubscriptionById(Long id) {
        return repository.findById(id);
    }
    
    public Subscription saveSubscription(Subscription subscription) {
        return repository.save(subscription);
    }
    
    public void deleteSubscription(Long id) {
        repository.deleteById(id);
    }
    
    public Subscription toggleSubscription(Long id) {
        Optional<Subscription> optSub = repository.findById(id);
        if (optSub.isPresent()) {
            Subscription sub = optSub.get();
            sub.setActive(!sub.isActive());
            return repository.save(sub);
        }
        return null;
    }
    
    public BigDecimal calculateTotalMonthly(String userId) {
        List<Subscription> subs = repository.findByUserIdAndActiveOrderByDueDateAsc(userId, true);
        return subs.stream()
                .map(Subscription::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public long countActive(String userId) {
        return repository.findByUserIdAndActiveOrderByDueDateAsc(userId, true).size();
    }
}
