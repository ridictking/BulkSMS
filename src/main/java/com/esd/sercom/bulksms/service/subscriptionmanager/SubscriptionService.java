package com.esd.sercom.bulksms.service.subscriptionmanager;

import com.esd.sercom.bulksms.model.DTO.SubscriptionDetails;
import com.esd.sercom.bulksms.model.DTO.UserDetails;

public interface SubscriptionService {
    SubscriptionDetails newSubscription(UserDetails userDetails);
    SubscriptionDetails updateUser(String uniqueUserId, UserDetails userDetails);
    void deleteUser(String uniqueUserId);
    SubscriptionDetails getUser(String uniquesUserId);
}
