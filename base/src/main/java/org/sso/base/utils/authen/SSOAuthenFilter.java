package org.sso.base.utils.authen;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.sso.base.dto.InternalApiResp;
import org.sso.base.dto.SSOAuthen;
import org.sso.base.dto.UserInfo;

import javax.net.ssl.SSLContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class SSOAuthenFilter extends OncePerRequestFilter {

    private final String prefixMain = "fnac:key:";
    private final String prefixExpire = "fnac:expire:";
    LettuceConnectionFactory lettuceConnectionFactory;
    private String tokenUrl;
    private String authenUrl;

    private String mockUser = "{\"code\":200,\"message\":\"OK\",\"data\":{\"username\":\"aphiwat@marcotechnology.com\",\"fullName\":\"วรัญญา เขียงโส\",\"ssoPosition\":{\"ssoPositionId\":1,\"code\":\"20100\",\"name\":\"เจ้าหน้าที่พิเศษ 1\"},\"ssoOffice\":{\"ssoOfficeId\":147,\"provinceNo\":\"10\",\"ssoOfficeCode\":\"1001\",\"name\":\"เขตพื้นที่ 1\",\"address1\":\"สำนักงานประกันสังคม                                                   \",\"address2\":\"ถนนติวานนท์ นนทบุรี 11000                                             \",\"address3\":\"สำนักสิทธิประโยชน์                      \",\"telephone\":null,\"status\":\"A\",\"taxId\":\"9000000000101\",\"runningTempCode\":\"0000004\",\"runningM39No\":\"000000\",\"unpRegion\":\"1\",\"unpOnline\":\" \",\"unpSize\":\" \",\"label\":\"1000 - สำนักงานใหญ่        \"},\"ssoProfile\":null,\"appPermission\":[{\"userPermissionId\":1,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":1,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"REGISTER\",\"code\":\"002\",\"url\":\"http://103.113.178.149:30101\",\"description\":\"เข้าระบบทะเบียน ม33\"}},{\"userPermissionId\":42,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":62,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"FINANCE\",\"code\":\"012\",\"url\":\"http://103.113.178.149:30302\",\"description\":\"เข้าระบบการเงินจ่าย\"}},{\"userPermissionId\":22,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":2,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"MASTER\",\"code\":\"001\",\"url\":null,\"description\":\"เข้าระบบจัดการข้อมูลหลัก\"}},{\"userPermissionId\":20,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":3,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"BENEFIT\",\"code\":\"004\",\"url\":\"http://103.113.178.149:30502\",\"description\":\"เข้าระบบประโยชน์\"}},{\"userPermissionId\":21,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":4,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"CONTRIBUTION\",\"code\":\"003\",\"url\":\"http://103.113.178.149:30601\",\"description\":\"เข้าระบบเงินสมทบ\"}},{\"userPermissionId\":23,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":5,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"MEDICAL\",\"code\":\"005\",\"url\":\"http://103.113.178.149:30201\",\"description\":\"เข้าระบบการแพทย์\"}},{\"userPermissionId\":24,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":6,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"FINANCE\",\"code\":\"006\",\"url\":\"http://103.113.178.149:30301\",\"description\":\"เข้าระบบการเงินรับ\"}},{\"userPermissionId\":25,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":7,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"ACCOUNT\",\"code\":\"007\",\"url\":\"http://103.113.178.149:30303\",\"description\":\"เข้าระบบบัญชี\"}},{\"userPermissionId\":26,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":8,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"AUDIT\",\"code\":\"008\",\"url\":null,\"description\":\"เข้าระบบตรวจสอบ\"}},{\"userPermissionId\":27,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":9,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"REGULATE\",\"code\":\"009\",\"url\":null,\"description\":\"เข้าระบบกฏหมาย\"}},{\"userPermissionId\":41,\"action\":\"ACCESS\",\"description\":null,\"function\":{\"userFunctionId\":61,\"parentFunctionId\":0,\"functionType\":\"APPLICATION\",\"name\":\"REGISTER\",\"code\":\"011\",\"url\":\"http://103.113.178.149:30013\",\"description\":\"เข้าระบบทะเบียน ม39\"}}],\"hierarchyPermission\":[{\"permissionId\":1,\"code\":\"002\",\"functionId\":1,\"parentId\":null,\"name\":\"REGISTER\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[{\"permissionId\":2,\"code\":\"00203\",\"functionId\":11,\"parentId\":1,\"name\":\"REG_M33\",\"type\":\"MENU\",\"action\":\"ACCESS\",\"children\":[{\"permissionId\":3,\"code\":\"00203001\",\"functionId\":14,\"parentId\":11,\"name\":\"REG_M33_REGIS\",\"type\":\"MENU\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":29,\"code\":\"00203002\",\"functionId\":41,\"parentId\":11,\"name\":\"REG_M33_EDIT\",\"type\":\"MENU\",\"action\":\"ACCESS\",\"children\":[{\"permissionId\":30,\"code\":\"00203002.1\",\"functionId\":42,\"parentId\":41,\"name\":\"REG_M33_EDIT_TRANSFER\",\"type\":\"MENU\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":31,\"code\":\"00203002.2\",\"functionId\":43,\"parentId\":41,\"name\":\"REG_M33_EDIT_ENDEMPLOY (override)\",\"type\":\"MENU\",\"action\":\"ACCESS\",\"children\":[]}]}]}]},{\"permissionId\":20,\"code\":\"004\",\"functionId\":3,\"parentId\":null,\"name\":\"BENEFIT\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":21,\"code\":\"003\",\"functionId\":4,\"parentId\":null,\"name\":\"CONTRIBUTION\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":22,\"code\":\"001\",\"functionId\":2,\"parentId\":null,\"name\":\"MASTER\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":23,\"code\":\"005\",\"functionId\":5,\"parentId\":null,\"name\":\"MEDICAL\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":24,\"code\":\"006\",\"functionId\":6,\"parentId\":null,\"name\":\"FINANCE\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":25,\"code\":\"007\",\"functionId\":7,\"parentId\":null,\"name\":\"ACCOUNT\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":26,\"code\":\"008\",\"functionId\":8,\"parentId\":null,\"name\":\"AUDIT\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":27,\"code\":\"009\",\"functionId\":9,\"parentId\":null,\"name\":\"REGULATE\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":28,\"code\":\"010\",\"functionId\":10,\"parentId\":null,\"name\":\"DATAMAP (override)\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":41,\"code\":\"011\",\"functionId\":61,\"parentId\":null,\"name\":\"REGISTER\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]},{\"permissionId\":42,\"code\":\"012\",\"functionId\":62,\"parentId\":null,\"name\":\"FINANCE\",\"type\":\"APPLICATION\",\"action\":\"ACCESS\",\"children\":[]}],\"operationPermission\":{\"R02001_DELETE\":{\"permissionId\":17,\"code\":\"0020300101\",\"functionId\":15,\"parentId\":14,\"name\":\"R02001\",\"type\":\"PAGE\",\"action\":\"DELETE\",\"children\":[]},\"R02003_EDIT\":{\"permissionId\":12,\"code\":\"0020300103\",\"functionId\":22,\"parentId\":14,\"name\":\"R02003\",\"type\":\"PAGE\",\"action\":\"EDIT\",\"children\":[]},\"R02001_EDIT\":{\"permissionId\":10,\"code\":\"0020300101\",\"functionId\":15,\"parentId\":14,\"name\":\"R02001\",\"type\":\"PAGE\",\"action\":\"EDIT\",\"children\":[]},\"R02001_VIEW\":{\"permissionId\":7,\"code\":\"0020300101\",\"functionId\":15,\"parentId\":14,\"name\":\"R02001\",\"type\":\"PAGE\",\"action\":\"VIEW\",\"children\":[]},\"R02001_APPROVE\":{\"permissionId\":13,\"code\":\"0020300101\",\"functionId\":15,\"parentId\":14,\"name\":\"R02001\",\"type\":\"PAGE\",\"action\":\"APPROVE\",\"children\":[]},\"R02002_ACCESS\":{\"permissionId\":5,\"code\":\"0020300102\",\"functionId\":16,\"parentId\":14,\"name\":\"R02002\",\"type\":\"PAGE\",\"action\":\"ACCESS\",\"children\":[]},\"R02003_VIEW\":{\"permissionId\":9,\"code\":\"0020300103\",\"functionId\":22,\"parentId\":14,\"name\":\"R02003\",\"type\":\"PAGE\",\"action\":\"VIEW\",\"children\":[]},\"R02001_ACCESS\":{\"permissionId\":4,\"code\":\"0020300101\",\"functionId\":15,\"parentId\":14,\"name\":\"R02001\",\"type\":\"PAGE\",\"action\":\"ACCESS\",\"children\":[]},\"R02003_ACCESS\":{\"permissionId\":6,\"code\":\"0020300103\",\"functionId\":22,\"parentId\":14,\"name\":\"R02003\",\"type\":\"PAGE\",\"action\":\"ACCESS\",\"children\":[]},\"R02002_VIEW\":{\"permissionId\":8,\"code\":\"0020300102\",\"functionId\":16,\"parentId\":14,\"name\":\"R02002\",\"type\":\"PAGE\",\"action\":\"VIEW\",\"children\":[]},\"R02003_DELETE\":{\"permissionId\":19,\"code\":\"0020300103\",\"functionId\":22,\"parentId\":14,\"name\":\"R02003\",\"type\":\"PAGE\",\"action\":\"DELETE\",\"children\":[]},\"R02002_DELETE\":{\"permissionId\":18,\"code\":\"0020300102\",\"functionId\":16,\"parentId\":14,\"name\":\"R02002\",\"type\":\"PAGE\",\"action\":\"DELETE\",\"children\":[]},\"R02002_EDIT\":{\"permissionId\":11,\"code\":\"0020300102\",\"functionId\":16,\"parentId\":14,\"name\":\"R02002\",\"type\":\"PAGE\",\"action\":\"EDIT\",\"children\":[]},\"R02003_PRINT_BATCH\":{\"permissionId\":16,\"code\":\"0020300103\",\"functionId\":22,\"parentId\":14,\"name\":\"R02003\",\"type\":\"PAGE\",\"action\":\"PRINT_BATCH\",\"children\":[]}}}}";

    public SSOAuthenFilter(LettuceConnectionFactory lettuceConnectionFactory, String tokenUrl, String authenUrl) {
        this.lettuceConnectionFactory = lettuceConnectionFactory;
        this.tokenUrl = tokenUrl;
        this.authenUrl = authenUrl;
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (authenUrl.compareTo("noauthen") == 0) {
            ObjectMapper mapper = new ObjectMapper();
            InternalApiResp<SSOAuthen> authObj = mapper.readValue(mockUser, new TypeReference<InternalApiResp<SSOAuthen>>() {});
            authObj.getData().setUsername("Admin");
            UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken("Admin", authObj, null);
            SecurityContextHolder.getContext().setAuthentication(authenToken);
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");
        if (request.getRequestURI().compareTo("/api/v1/me") == 0 && authorizationHeader != null &&
                authorizationHeader.startsWith("Bearer")) {
            try {
                HttpHeaders headers = new HttpHeaders() {{
                    set( "Authorization", authorizationHeader );
                }};

                TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
                SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                        .loadTrustMaterial(null, acceptingTrustStrategy)
                        .build();
                SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
                CloseableHttpClient httpClient = HttpClients.custom()
                        .setSSLSocketFactory(csf)
                        .build();
                HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

                requestFactory.setHttpClient(httpClient);
                RestTemplate api = new RestTemplate(requestFactory);
                ResponseEntity<UserInfo> userInfo = api.exchange(tokenUrl,
                                HttpMethod.GET,
                                new HttpEntity(headers), UserInfo.class);

                if (userInfo.getStatusCode() == HttpStatus.OK) {

                    String fixMeUserName = "aphiwat@marcotechnology.com";

                    RestTemplate restTemplate = new RestTemplate();
                    String checkPermissionUrl = authenUrl + "/" + fixMeUserName;
                    ResponseEntity<InternalApiResp<SSOAuthen>> apiResp = restTemplate.exchange(checkPermissionUrl, HttpMethod.GET,
                            null, new ParameterizedTypeReference<InternalApiResp<SSOAuthen>>() {});
                    if (apiResp.getStatusCode() != HttpStatus.OK || apiResp.getBody().getCode() != 200) {
                        filterChain.doFilter(request, response);
                        return;
                    }

                    String key = UUID.randomUUID().toString();
                    String val = UUID.randomUUID().toString();
                    String auth = Base64.getEncoder().encodeToString((key + ":" + val)
                                    .getBytes(StandardCharsets.UTF_8));

                    Cookie fnacCookie = new Cookie("fnac", auth);
                    // fnacCookie.setSecure(true);
                    fnacCookie.setHttpOnly(true);
                    fnacCookie.setPath("/");
                    response.addCookie(fnacCookie);

                    ObjectMapper mapper = new ObjectMapper();
                    RedisConnection redis = lettuceConnectionFactory.getConnection();
                    redis.hSet((prefixMain + key).getBytes(StandardCharsets.UTF_8), "val".getBytes(StandardCharsets.UTF_8),
                            val.getBytes(StandardCharsets.UTF_8));
                    redis.hSet((prefixMain + key).getBytes(StandardCharsets.UTF_8), "info".getBytes(StandardCharsets.UTF_8),
                            userInfo.getBody().getPreferredUsername().getBytes(StandardCharsets.UTF_8));
                    redis.hSet((prefixMain + key).getBytes(StandardCharsets.UTF_8), "auth".getBytes(StandardCharsets.UTF_8),
                            mapper.writeValueAsBytes(apiResp.getBody()));

                    redis.expire((prefixMain + key).getBytes(StandardCharsets.UTF_8), (30 * 60));

                    UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(
                            userInfo.getBody().getPreferredUsername(), apiResp.getBody(), null);
                    SecurityContextHolder.getContext().setAuthentication(authenToken);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            filterChain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i=0; i<cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().compareTo("fnac") == 0) {
                    String[] pair = new String(Base64.getDecoder().decode(cookie.getValue()), StandardCharsets.UTF_8).split(":");
                    if (pair.length == 2) {
                        RedisConnection redis = lettuceConnectionFactory.getConnection();
                        String key = pair[0];
                        byte[] rawVal = redis.hGet((prefixMain + key).getBytes(StandardCharsets.UTF_8), "val".getBytes(StandardCharsets.UTF_8));
                        byte[] rawInfo = redis.hGet((prefixMain + key).getBytes(StandardCharsets.UTF_8), "info".getBytes(StandardCharsets.UTF_8));
                        byte[] rawAuth = redis.hGet((prefixMain + key).getBytes(StandardCharsets.UTF_8), "auth".getBytes(StandardCharsets.UTF_8));
                        if (rawVal != null) {
                            String val = new String(rawVal, StandardCharsets.UTF_8);
                            String info = new String(rawInfo, StandardCharsets.UTF_8);
                            if (val.compareTo(pair[1]) == 0) {
                                Date now = new Date();
                                redis.expire((prefixMain + key).getBytes(StandardCharsets.UTF_8), now.getTime() + (30 * 60));
                                ObjectMapper mapper = new ObjectMapper();
                                String auth = new String(rawAuth, StandardCharsets.UTF_8);
                                InternalApiResp<SSOAuthen> authObj = mapper.readValue(auth, new TypeReference<InternalApiResp<SSOAuthen>>() {});
                                UsernamePasswordAuthenticationToken authenToken = new UsernamePasswordAuthenticationToken(info, authObj, null);
                                SecurityContextHolder.getContext().setAuthentication(authenToken);
                                filterChain.doFilter(request, response);
                                return;
                            }
                        }
                    }
                }
            }
        }

        SecurityContextHolder.getContext().setAuthentication(null);
        filterChain.doFilter(request, response);
    }
}
