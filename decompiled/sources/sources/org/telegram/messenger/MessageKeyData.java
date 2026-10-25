package org.telegram.messenger;

import org.telegram.tgnet.SerializedData;

/* loaded from: classes.dex */
public class MessageKeyData {
    public byte[] aesIv;
    public byte[] aesKey;

    public MessageKeyData() {
    }

    public static MessageKeyData generateMessageKeyData(byte[] r8, byte[] r9, boolean r10, int r11) {
        MessageKeyData r0 = new MessageKeyData();
        if (r8 != null) goto L5;
    L20:
        r0.aesIv = null;
        r0.aesKey = null;
        return r0;
    L5:
        if (r8.length == 0) goto L20;
        if (r10 == false) goto L10;
        int r102 = 8;
    L12:
        if (r11 != 1) goto L14;
        SerializedData r112 = new SerializedData();
        r112.writeBytes(r9);
        r112.writeBytes(r8, r102, 32);
        byte[] r5 = Utilities.computeSHA1(r112.toByteArray());
        r112.cleanup();
        SerializedData r113 = new SerializedData();
        r113.writeBytes(r8, r102 + 32, 16);
        r113.writeBytes(r9);
        r113.writeBytes(r8, r102 + 48, 16);
        byte[] r6 = Utilities.computeSHA1(r113.toByteArray());
        r113.cleanup();
        SerializedData r114 = new SerializedData();
        r114.writeBytes(r8, r102 + 64, 32);
        r114.writeBytes(r9);
        byte[] r7 = Utilities.computeSHA1(r114.toByteArray());
        r114.cleanup();
        SerializedData r115 = new SerializedData();
        r115.writeBytes(r9);
        r115.writeBytes(r8, r102 + 96, 32);
        byte[] r82 = Utilities.computeSHA1(r115.toByteArray());
        r115.cleanup();
        SerializedData r92 = new SerializedData();
        r92.writeBytes(r5, 0, 8);
        r92.writeBytes(r6, 8, 12);
        r92.writeBytes(r7, 4, 12);
        r0.aesKey = r92.toByteArray();
        r92.cleanup();
        SerializedData r93 = new SerializedData();
        r93.writeBytes(r5, 8, 12);
        r93.writeBytes(r6, 0, 8);
        r93.writeBytes(r7, 16, 4);
        r93.writeBytes(r82, 0, 8);
        r0.aesIv = r93.toByteArray();
        r93.cleanup();
        return r0;
    L14:
        if (r11 == 2) goto L16;
        return r0;
    L16:
        SerializedData r116 = new SerializedData();
        r116.writeBytes(r9, 0, 16);
        r116.writeBytes(r8, r102, 36);
        byte[] r52 = Utilities.computeSHA256(r116.toByteArray());
        r116.cleanup();
        SerializedData r117 = new SerializedData();
        r117.writeBytes(r8, r102 + 40, 36);
        r117.writeBytes(r9, 0, 16);
        byte[] r83 = Utilities.computeSHA256(r117.toByteArray());
        r117.cleanup();
        SerializedData r94 = new SerializedData();
        r94.writeBytes(r52, 0, 8);
        r94.writeBytes(r83, 8, 16);
        r94.writeBytes(r52, 24, 8);
        r0.aesKey = r94.toByteArray();
        r94.cleanup();
        SerializedData r95 = new SerializedData();
        r95.writeBytes(r83, 0, 8);
        r95.writeBytes(r52, 8, 16);
        r95.writeBytes(r83, 24, 8);
        r0.aesIv = r95.toByteArray();
        r95.cleanup();
        return r0;
    L10:
        r102 = 0;
        goto L12
    }
}
