package org.chromium.net.telemetry;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.MediaError;

/* loaded from: classes5.dex */
public abstract class SizeBuckets {
    public static int calcRequestBodySizeBucket(long r2) {
        checkSizeIsValid(r2, "Request body size is negative");
        double r22 = r2 / 1024.0d;
        if (r22 != 0.0d) goto L6;
        return 1;
    L6:
        if (r22 <= 0.0d) goto L12;
        if (r22 >= 10.0d) goto L12;
        return 2;
    L12:
        if (isInClosedOpenRange(r22, 10, 50) == false) goto L16;
        return 3;
    L16:
        if (isInClosedOpenRange(r22, 50, 200) == false) goto L20;
        return 4;
    L20:
        if (isInClosedOpenRange(r22, 200, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN) == false) goto L24;
        return 5;
    L24:
        if (isInClosedOpenRange(r22, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 1000) == false) goto L28;
        return 6;
    L28:
        if (isInClosedOpenRange(r22, 1000, 5000) == false) goto L31;
        return 7;
    L31:
        return 8;
    }

    public static int calcRequestHeadersSizeBucket(long r2) {
        checkSizeIsValid(r2, "Request header size is negative");
        double r22 = r2 / 1024.0d;
        if (isInClosedOpenRange(r22, 0, 1) == false) goto L6;
        return 1;
    L6:
        if (isInClosedOpenRange(r22, 1, 10) == false) goto L10;
        return 2;
    L10:
        if (isInClosedOpenRange(r22, 10, 25) == false) goto L14;
        return 3;
    L14:
        if (isInClosedOpenRange(r22, 25, 50) == false) goto L18;
        return 4;
    L18:
        if (isInClosedOpenRange(r22, 50, 100) == false) goto L21;
        return 5;
    L21:
        return 6;
    }

    public static int calcResponseBodySizeBucket(long r2) {
        checkSizeIsValid(r2, "Response body size is negative");
        double r22 = r2 / 1024.0d;
        if (r22 != 0.0d) goto L6;
        return 1;
    L6:
        if (r22 <= 0.0d) goto L12;
        if (r22 >= 10.0d) goto L12;
        return 2;
    L12:
        if (isInClosedOpenRange(r22, 10, 50) == false) goto L16;
        return 3;
    L16:
        if (isInClosedOpenRange(r22, 50, 200) == false) goto L20;
        return 4;
    L20:
        if (isInClosedOpenRange(r22, 200, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN) == false) goto L24;
        return 5;
    L24:
        if (isInClosedOpenRange(r22, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, 1000) == false) goto L28;
        return 6;
    L28:
        if (isInClosedOpenRange(r22, 1000, 5000) == false) goto L31;
        return 7;
    L31:
        return 8;
    }

    public static int calcResponseHeadersSizeBucket(long r2) {
        checkSizeIsValid(r2, "Response header size is negative");
        double r22 = r2 / 1024.0d;
        if (isInClosedOpenRange(r22, 0, 1) == false) goto L6;
        return 1;
    L6:
        if (isInClosedOpenRange(r22, 1, 10) == false) goto L10;
        return 2;
    L10:
        if (isInClosedOpenRange(r22, 10, 25) == false) goto L14;
        return 3;
    L14:
        if (isInClosedOpenRange(r22, 25, 50) == false) goto L18;
        return 4;
    L18:
        if (isInClosedOpenRange(r22, 50, 100) == false) goto L21;
        return 5;
    L21:
        return 6;
    }

    private static void checkSizeIsValid(long r2, String r4) {
        if (r2 < 0) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m(r4);
    }

    private static boolean isInClosedOpenRange(double r2, int r4, int r5) {
        if (r2 >= r4) goto L5;
        return false;
    L5:
        if (r2 >= r5) goto L10;
        return true;
    L10:
        return false;
    }
}
