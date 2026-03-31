package io.jayksss.api.common.uuid;

import java.util.UUID;

public final class UuidV4 {
    private UuidV4() {
    }

    public static String newString() {
        return UUID.randomUUID().toString();
    }
}

