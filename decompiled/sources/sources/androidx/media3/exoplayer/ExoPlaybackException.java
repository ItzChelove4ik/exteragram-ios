package androidx.media3.exoplayer;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.source.MediaSource;
import java.io.IOException;

/* loaded from: classes4.dex */
public final class ExoPlaybackException extends PlaybackException {
    final boolean isRecoverable;
    public final MediaSource.MediaPeriodId mediaPeriodId;
    public final Format rendererFormat;
    public final int rendererFormatSupport;
    public final int rendererIndex;
    public final String rendererName;
    public final int type;

    private ExoPlaybackException(int r12, Throwable r13, int r14) {
        this(r12, r13, null, r14, null, -1, null, 4, null, false);
    }

    private ExoPlaybackException(int r14, Throwable r15, String r16, int r17, String r18, int r19, Format r20, int r21, MediaSource.MediaPeriodId r22, boolean r23) {
        this(deriveMessage(r14, r16, r18, r19, r20, r21), r15, r17, r14, r18, r19, r20, r21, r22, SystemClock.elapsedRealtime(), r23);
    }

    private ExoPlaybackException(String r9, Throwable r10, int r11, int r12, String r13, int r14, Format r15, int r16, MediaSource.MediaPeriodId r17, long r18, boolean r20) {
        super(r9, r10, r11, Bundle.EMPTY, r18);
        boolean r92 = false;
        if (r20 == false) goto L7;
        if (r12 == 1) goto L7;
        boolean r2 = false;
    L8:
        Assertions.checkArgument(r2);
        if (r10 == null) goto L11;
    L12:
        r92 = true;
    L13:
        Assertions.checkArgument(r92);
        this.type = r12;
        this.rendererName = r13;
        this.rendererIndex = r14;
        this.rendererFormat = r15;
        this.rendererFormatSupport = r16;
        this.mediaPeriodId = r17;
        this.isRecoverable = r20;
        return;
    L11:
        if (r12 != 3) goto L13;
    L7:
        r2 = true;
        goto L8
    }

    public static ExoPlaybackException createForRenderer(Throwable r11, String r12, int r13, Format r14, int r15, MediaSource.MediaPeriodId r16, boolean r17, int r18) {
        if (r14 != null) goto L6;
        r15 = 4;
    L6:
        return new ExoPlaybackException(1, r11, null, r18, r12, r13, r14, r15, r16, r17);
    }

    public static ExoPlaybackException createForSource(IOException r2, int r3) {
        return new ExoPlaybackException(0, r2, r3);
    }

    public static ExoPlaybackException createForUnexpected(RuntimeException r2, int r3) {
        return new ExoPlaybackException(2, r2, r3);
    }

    private static String deriveMessage(int r1, String r2, String r3, int r4, Format r5, int r6) {
        if (r1 != 0) goto L4;
        String r12 = "Source error";
    L12:
        if (TextUtils.isEmpty(r2) == false) goto L14;
        return r12;
    L14:
        return r12 + ": " + r2;
    L4:
        if (r1 != 1) goto L6;
        r12 = r3 + " error, index=" + r4 + ", format=" + r5 + ", format_supported=" + Util.getFormatSupportString(r6);
        goto L12
    L6:
        if (r1 == 3) goto L8;
        r12 = "Unexpected runtime error";
        goto L12
    L8:
        r12 = "Remote error";
        goto L12
    }

    public ExoPlaybackException copyWithMediaPeriodId(MediaSource.MediaPeriodId r14) {
        return new ExoPlaybackException((String) Util.castNonNull(getMessage()), getCause(), this.errorCode, this.type, this.rendererName, this.rendererIndex, this.rendererFormat, this.rendererFormatSupport, r14, this.timestampMs, this.isRecoverable);
    }
}
