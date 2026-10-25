package org.chromium.net.impl;

import c.f$$ExternalSyntheticBUOutline1;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public abstract class Preconditions {
    public static void checkDirect(ByteBuffer r0) {
        if (r0.isDirect() == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("byteBuffer must be a direct ByteBuffer.");
    }

    public static void checkHasRemaining(ByteBuffer r0) {
        if (r0.hasRemaining() == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("ByteBuffer is already full.");
    }
}
