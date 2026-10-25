package com.google.firebase.installations;

import android.util.Base64;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.UUID;

/* loaded from: classes.dex */
public class RandomFidGenerator {
    private static final byte FID_4BIT_PREFIX = 0;
    private static final byte REMOVE_PREFIX_MASK = 0;

    static {
        FID_4BIT_PREFIX = Byte.parseByte("01110000", 2);
        REMOVE_PREFIX_MASK = Byte.parseByte("00001111", 2);
    }

    public RandomFidGenerator() {
    }

    private static String encodeFidBase64UrlSafe(byte[] r2) {
        return new String(Base64.encode(r2, 11), Charset.defaultCharset()).substring(0, 22);
    }

    private static byte[] getBytesFromUUID(UUID r2, byte[] r3) {
        ByteBuffer r32 = ByteBuffer.wrap(r3);
        r32.putLong(r2.getMostSignificantBits());
        r32.putLong(r2.getLeastSignificantBits());
        return r32.array();
    }

    public String createRandomFid() {
        byte[] r3 = getBytesFromUUID(UUID.randomUUID(), new byte[17]);
        byte r1 = r3[0];
        r3[16] = r1;
        r3[0] = (byte) ((r1 & REMOVE_PREFIX_MASK) | FID_4BIT_PREFIX);
        return encodeFidBase64UrlSafe(r3);
    }
}
