//package com.example.bookstoreapp.actuator;
//
//import org.springframework.boot.actuate.health.Health;
//import org.springframework.boot.actuate.health.HealthIndicator;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyCustomHealthIndicator implements HealthIndicator {
//
//    @Override
//    public Health health() {
//        // Sağlık durumu logic'inizi burada uygulayın
//        // Örneğin, belirli bir koşulu kontrol ederek UP veya DOWN durumu belirleyebilirsiniz.
//        boolean isHealthy = // ... sağlık kontrolü yapılır ...
//
//        if (isHealthy) {
//            return Health.up().build();
//        } else {
//            return Health.down().withDetail("Reason", "Özel durum: ...").build();
//        }
//    }
//}
