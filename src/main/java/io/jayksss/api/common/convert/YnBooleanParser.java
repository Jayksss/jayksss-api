package io.jayksss.api.common.convert;

/**
 * DB·API에서 쓰는 Y/N 형태를 {@link Boolean} 필터로 바꿀 때 사용. 쿼리 파라미터 {@code y}, {@code Y}, {@code true}, {@code 1} 등 허용.
 */
public final class YnBooleanParser {
    private YnBooleanParser() {
    }

    /**
     * {@code null}·공백이면 조건 미적용({@code null}), 그 외는 Y/N·true/false·1/0만 허용.
     *
     * @throws IllegalArgumentException 인식할 수 없는 값
     */
    public static Boolean parseFilter(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        String v = raw.trim();
        if (v.equalsIgnoreCase("Y")) {
            return true;
        }
        if (v.equalsIgnoreCase("N")) {
            return false;
        }
        if (v.equals("1") || v.equalsIgnoreCase("true")) {
            return true;
        }
        if (v.equals("0") || v.equalsIgnoreCase("false")) {
            return false;
        }
        throw new IllegalArgumentException("Unsupported Y/N value: " + raw);
    }
}
