package com.google.mlkit.genai.common;

import j$.time.Duration;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class GenAiException extends Exception {
    private final int zza;
    private final Duration zzb;

    public GenAiException(String r2, Throwable r3, int r4) {
        this(r2, r3, r4, Duration.ZERO);
    }

    public GenAiException(String r1, Throwable r2, int r3, Duration r4) {
        super(r1, r2);
        this.zza = r3;
        this.zzb = r4;
    }

    public GenAiException(Throwable r2, int r3) {
        this(r2, r3, Duration.ZERO);
    }

    public GenAiException(Throwable r5, int r6, Duration r7) {
        if (r6 != (-107)) goto L5;
        String r0 = "Audio buffer overflow. The audio input rate is too fast.";
    L53:
        StringBuilder r2 = new StringBuilder((String.valueOf(r6).length() + 13) + r0.length());
        r2.append("[ErrorCode ");
        r2.append(r6);
        r2.append("] ");
        r2.append(r0);
        super(r2.toString(), r5);
        this.zza = r6;
        this.zzb = r7;
        return;
    L5:
        if (r6 != 4) goto L7;
        r0 = "Request doesn't pass certain policy check. Please try a different input.";
        goto L53
    L7:
        if (r6 != 30) goto L9;
        r0 = "Background usage is blocked. Please use the API when your app is in the foreground instead.";
        goto L53
    L9:
        if (r6 != 501) goto L11;
        r0 = "Not enough storage. Please clear some space.";
        goto L53
    L11:
        if (r6 != 604) goto L13;
        r0 = "Android version is too low. Please update.";
        goto L53
    L13:
        if (r6 != 7) goto L15;
        r0 = "The request is canceled.";
        goto L53
    L15:
        if (r6 != 8) goto L17;
        r0 = "This feature is not available on this device. It is due to either the device not supporting it or the latest configuration file has not been downloaded.";
        goto L53
    L17:
        if (r6 != 9) goto L19;
        r0 = "Request cannot be processed. Either your app is out of usage quota (try again later) or the request is from disallowed background usage (use the API while the app is in the foreground).";
        goto L53
    L19:
        if (r6 != 11) goto L21;
        r0 = "Generated response doesn't pass certain policy check. Please try a different input.";
        goto L53
    L21:
        if (r6 != 12) goto L23;
        r0 = "Request too large. Please shorten your input.";
        goto L53
    L23:
        if (r6 != 15) goto L25;
        r0 = "Couldn't generate a response due to policy check failure. Please try a different input.";
        goto L53
    L25:
        if (r6 != 16) goto L27;
        r0 = "This functionality is not implemented nor supported by the inference engine.";
        goto L53
    L27:
        if (r6 != 27) goto L29;
        r0 = "A long-duration (for example per-day) quota for the calling app's uid has been exceeded. Use of AICore from this app has been restricted to preserve battery.";
        goto L53
    L29:
        if (r6 == 28) goto L39;
        switch(r6) {
            case -102: goto L38;
            case -101: goto L37;
            case -100: goto L36;
            default: goto L31;
        };
    L31:
        if (r5 == null) goto L35;
        r0 = r5.getMessage();
        if (r0 != null) goto L53;
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L53
    L35:
        r0 = "Unknown error.";
        goto L53
    L36:
        r0 = "Request too small. Please provide a longer input.";
        goto L53
    L37:
        r0 = "AICore is either not installed or the installed version is too low.";
        goto L53
    L38:
        r0 = "Invalid input image. It can be due to invalid image URL, failure to decode to bitmap, invalid mime type or other image processing failures.";
        goto L53
    L39:
        r0 = "A long-duration (for example per-day) quota for the device has been exceeded. Use of AICore (from any app) has been restricted to preserve battery.";
        goto L53
    }

    public int getErrorCode() {
        int r1 = this.zza;
        if (r1 != 28) goto L6;
        return 9;
    L6:
        return r1;
    }
}
