package io.jayksss.api.config;

import java.util.HashMap;
import java.util.Map;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.springframework.boot.EnvironmentPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

/**
 * Spring Boot 4와 호환되도록 Jasypt {@code ENC(...)} 형태의 프로퍼티만 선별 복호화합니다.
 * 마스터 비밀번호는 환경 변수 {@code JASYPT_ENCRYPTOR_PASSWORD}, JVM 옵션 {@code -Djasypt.encryptor.password},
 * 또는 설정 {@code jasypt.encryptor.password}({@code ${...}} 해석) 순으로 사용합니다.
 */
public class JasyptDecryptEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final String DEFAULT_ALGORITHM = "PBEWithMD5AndDES";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String[] keys = resolveKeys(environment);
        if (keys.length == 0) {
            return;
        }

        boolean anyEncrypted = false;
        for (String key : keys) {
            if (isEncrypted(environment.getProperty(key))) {
                anyEncrypted = true;
                break;
            }
        }
        if (!anyEncrypted) {
            return;
        }

        String masterPassword = resolveMasterPassword(environment);
        if (masterPassword == null || masterPassword.isBlank()) {
            throw new IllegalStateException(
                    "Jasypt: ENC(...) 형식의 값이 있으나 마스터 비밀번호가 없습니다. "
                            + "JASYPT_ENCRYPTOR_PASSWORD 환경 변수, -Djasypt.encryptor.password, "
                            + "또는 application.properties 의 jasypt.encryptor.password 를 설정하세요.");
        }

        String algorithm = environment.getProperty("jasypt.encryptor.algorithm", DEFAULT_ALGORITHM);
        StandardPBEStringEncryptor encryptor = createEncryptor(masterPassword, algorithm);

        Map<String, Object> decrypted = new HashMap<>();
        for (String key : keys) {
            String value = environment.getProperty(key);
            if (isEncrypted(value)) {
                String cipher = value.substring(4, value.length() - 1);
                decrypted.put(key, encryptor.decrypt(cipher));
            }
        }
        if (!decrypted.isEmpty()) {
            environment.getPropertySources().addFirst(new MapPropertySource("jasypt-decrypted-properties", decrypted));
        }
    }

    private static String[] resolveKeys(ConfigurableEnvironment environment) {
        String keysConfig = environment.getProperty("jasypt.encryptable.property-keys");
        if (keysConfig == null || keysConfig.isBlank()) {
            return new String[] {
                "spring.datasource.url",
                "spring.datasource.username",
                "spring.datasource.password"
            };
        }
        String[] split = keysConfig.split(",");
        int n = 0;
        for (String k : split) {
            if (!k.trim().isEmpty()) {
                n++;
            }
        }
        String[] keys = new String[n];
        int i = 0;
        for (String k : split) {
            String trimmed = k.trim();
            if (!trimmed.isEmpty()) {
                keys[i++] = trimmed;
            }
        }
        return keys;
    }

    private static String resolveMasterPassword(ConfigurableEnvironment environment) {
        String env = System.getenv("JASYPT_ENCRYPTOR_PASSWORD");
        if (env != null && !env.isBlank()) {
            return env;
        }
        String sys = System.getProperty("jasypt.encryptor.password");
        if (sys != null && !sys.isBlank()) {
            return sys;
        }
        String fromConfig = environment.getProperty("jasypt.encryptor.password");
        if (fromConfig == null || fromConfig.isBlank()) {
            return null;
        }
        try {
            return environment.resolvePlaceholders(fromConfig);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    private static boolean isEncrypted(String value) {
        return value != null && value.startsWith("ENC(") && value.endsWith(")") && value.length() > 5;
    }

    private static StandardPBEStringEncryptor createEncryptor(String masterPassword, String algorithm) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(masterPassword);
        encryptor.setAlgorithm(algorithm);
        if (algorithm != null && algorithm.contains("AES")) {
            encryptor.setIvGenerator(new RandomIvGenerator());
        }
        return encryptor;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
