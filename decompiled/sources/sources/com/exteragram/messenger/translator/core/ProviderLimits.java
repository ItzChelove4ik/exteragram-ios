package com.exteragram.messenger.translator.core;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0012R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0019\u0010\u0012R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u001a\u0010\u0012R\u0017\u0010\u0006\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0017\u001a\u0004\b\u001b\u0010\u0012R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\u0017\u001a\u0004\b\u001f\u0010\u0012R\u0017\u0010\n\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\n\u0010\u001c\u001a\u0004\b \u0010\u001eR\u0017\u0010\u000b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001c\u001a\u0004\b!\u0010\u001e¨\u0006#"}, d2 = {"Lcom/exteragram/messenger/translator/core/ProviderLimits;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "maxTextsPerRequest", "maxCharsPerText", "maxCharsPerRequest", "maxConcurrent", _UrlKt.FRAGMENT_ENCODE_SET, "minIntervalMs", "maxAttempts", "baseBackoffMs", "maxBackoffMs", "<init>", "(IIIIJIJJ)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "I", "getMaxTextsPerRequest", "getMaxCharsPerText", "getMaxCharsPerRequest", "getMaxConcurrent", "J", "getMinIntervalMs", "()J", "getMaxAttempts", "getBaseBackoffMs", "getMaxBackoffMs", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ProviderLimits {
    public static final Companion Companion = null;

    @JvmField
    public static final ProviderLimits GOOGLE = null;

    @JvmField
    public static final ProviderLimits MICROSOFT = null;

    @JvmField
    public static final ProviderLimits YANDEX = null;
    private final long baseBackoffMs;
    private final int maxAttempts;
    private final long maxBackoffMs;
    private final int maxCharsPerRequest;
    private final int maxCharsPerText;
    private final int maxConcurrent;
    private final int maxTextsPerRequest;
    private final long minIntervalMs;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/translator/core/ProviderLimits$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "GOOGLE", "Lcom/exteragram/messenger/translator/core/ProviderLimits;", "YANDEX", "MICROSOFT", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        GOOGLE = new ProviderLimits(20, 5000, 8000, 2, 0, 4, 1000, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        YANDEX = new ProviderLimits(20, 5000, 5000, 2, 0, 4, 1000, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        MICROSOFT = new ProviderLimits(20, 5000, 10000, 4, 0, 4, 1000, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
    }

    public ProviderLimits(int r1, int r2, int r3, int r4, long r5, int r7, long r8, long r10) {
        this.maxTextsPerRequest = r1;
        this.maxCharsPerText = r2;
        this.maxCharsPerRequest = r3;
        this.maxConcurrent = r4;
        this.minIntervalMs = r5;
        this.maxAttempts = r7;
        this.baseBackoffMs = r8;
        this.maxBackoffMs = r10;
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof ProviderLimits) == true) goto L8;
        return false;
    L8:
        ProviderLimits r82 = (ProviderLimits) r8;
        if (this.maxTextsPerRequest == r82.maxTextsPerRequest) goto L12;
        return false;
    L12:
        if (this.maxCharsPerText == r82.maxCharsPerText) goto L15;
        return false;
    L15:
        if (this.maxCharsPerRequest == r82.maxCharsPerRequest) goto L18;
        return false;
    L18:
        if (this.maxConcurrent == r82.maxConcurrent) goto L21;
        return false;
    L21:
        if (this.minIntervalMs == r82.minIntervalMs) goto L24;
        return false;
    L24:
        if (this.maxAttempts == r82.maxAttempts) goto L27;
        return false;
    L27:
        if (this.baseBackoffMs == r82.baseBackoffMs) goto L30;
        return false;
    L30:
        if (this.maxBackoffMs == r82.maxBackoffMs) goto L32;
        return false;
    L32:
        return true;
    }

    public final long getBaseBackoffMs() {
        return this.baseBackoffMs;
    }

    public final int getMaxAttempts() {
        return this.maxAttempts;
    }

    public final long getMaxBackoffMs() {
        return this.maxBackoffMs;
    }

    public final int getMaxCharsPerRequest() {
        return this.maxCharsPerRequest;
    }

    public final int getMaxCharsPerText() {
        return this.maxCharsPerText;
    }

    public final int getMaxConcurrent() {
        return this.maxConcurrent;
    }

    public final int getMaxTextsPerRequest() {
        return this.maxTextsPerRequest;
    }

    public final long getMinIntervalMs() {
        return this.minIntervalMs;
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.maxTextsPerRequest) * 31) + Integer.hashCode(this.maxCharsPerText)) * 31) + Integer.hashCode(this.maxCharsPerRequest)) * 31) + Integer.hashCode(this.maxConcurrent)) * 31) + Long.hashCode(this.minIntervalMs)) * 31) + Integer.hashCode(this.maxAttempts)) * 31) + Long.hashCode(this.baseBackoffMs)) * 31) + Long.hashCode(this.maxBackoffMs);
    }

    public String toString() {
        return "ProviderLimits(maxTextsPerRequest=" + this.maxTextsPerRequest + ", maxCharsPerText=" + this.maxCharsPerText + ", maxCharsPerRequest=" + this.maxCharsPerRequest + ", maxConcurrent=" + this.maxConcurrent + ", minIntervalMs=" + this.minIntervalMs + ", maxAttempts=" + this.maxAttempts + ", baseBackoffMs=" + this.baseBackoffMs + ", maxBackoffMs=" + this.maxBackoffMs + ")";
    }
}
