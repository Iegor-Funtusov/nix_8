package ua.com.alevel.supplier.service;

import org.springframework.stereotype.Service;
import ua.com.alevel.supplier.exception.AuthException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {

    private final Map<String, ShopInfo> authMap = new HashMap<>();

    public TokenService() {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setToken(UUID.randomUUID().toString());
        shopInfo.setExpire(new Date());
        authMap.put("book1", shopInfo);
    }

    public String login(String shopId) {
        ShopInfo shopInfo = authMap.get(shopId);
        if (shopInfo == null) {
            throw new AuthException("invalid");
        }
        shopInfo.setExpire(new Date());
        return shopInfo.getToken();
    }

    public Map<String, ShopInfo> getAuthMap() {
        return authMap;
    }

    public static class ShopInfo {

        private String token;
        private Date expire;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Date getExpire() {
            return expire;
        }

        public void setExpire(Date expire) {
            this.expire = expire;
        }
    }
}
