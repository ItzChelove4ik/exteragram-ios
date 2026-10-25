package androidx.work;

import android.annotation.SuppressLint;
import android.net.NetworkRequest;
import android.net.Uri;
import androidx.work.impl.utils.NetworkRequestCompat;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 -2\u00020\u0001:\u0003+,-B1\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tB;\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u000bB_\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0004\b\b\u0010\u0012Bg\b\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0004\b\b\u0010\u0015B\u0011\b\u0017\u0012\u0006\u0010\u0016\u001a\u00020\u0000¢\u0006\u0004\b\b\u0010\u0017J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\n\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u0007\u001a\u00020\u0005J\b\u0010%\u001a\u00020\u0005H\u0007J\u0013\u0010&\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0097\u0002J\b\u0010'\u001a\u00020(H\u0017J\b\u0010)\u001a\u00020*H\u0017R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0013\u001a\u00020\u00148\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0010\u0010\u0004\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u00020\r8GX\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u000e\u001a\u00020\r8GX\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108GX\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u0006."}, d2 = {"Landroidx/work/Constraints;", _UrlKt.FRAGMENT_ENCODE_SET, "requiredNetworkType", "Landroidx/work/NetworkType;", "requiresCharging", _UrlKt.FRAGMENT_ENCODE_SET, "requiresBatteryNotLow", "requiresStorageNotLow", "<init>", "(Landroidx/work/NetworkType;ZZZ)V", "requiresDeviceIdle", "(Landroidx/work/NetworkType;ZZZZ)V", "contentTriggerUpdateDelayMillis", _UrlKt.FRAGMENT_ENCODE_SET, "contentTriggerMaxDelayMillis", "contentUriTriggers", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/Constraints$ContentUriTrigger;", "(Landroidx/work/NetworkType;ZZZZJJLjava/util/Set;)V", "requiredNetworkRequestCompat", "Landroidx/work/impl/utils/NetworkRequestCompat;", "(Landroidx/work/impl/utils/NetworkRequestCompat;Landroidx/work/NetworkType;ZZZZJJLjava/util/Set;)V", "other", "(Landroidx/work/Constraints;)V", "getRequiredNetworkType", "()Landroidx/work/NetworkType;", "requiredNetworkRequest", "Landroid/net/NetworkRequest;", "getRequiredNetworkRequest", "()Landroid/net/NetworkRequest;", "getRequiredNetworkRequestCompat$work_runtime_release", "()Landroidx/work/impl/utils/NetworkRequestCompat;", "getContentTriggerUpdateDelayMillis", "()J", "getContentTriggerMaxDelayMillis", "getContentUriTriggers", "()Ljava/util/Set;", "hasContentUriTriggers", "equals", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Builder", "ContentUriTrigger", "Companion", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Constraints {
    public static final Companion Companion = null;

    @JvmField
    public static final Constraints NONE = null;
    private final long contentTriggerMaxDelayMillis;
    private final long contentTriggerUpdateDelayMillis;
    private final Set<ContentUriTrigger> contentUriTriggers;
    private final NetworkRequestCompat requiredNetworkRequestCompat;
    private final NetworkType requiredNetworkType;
    private final boolean requiresBatteryNotLow;
    private final boolean requiresCharging;
    private final boolean requiresDeviceIdle;
    private final boolean requiresStorageNotLow;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\u0010R\u0016\u0010\u0017\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0010R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Landroidx/work/Constraints$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/work/NetworkType;", "networkType", "setRequiredNetworkType", "(Landroidx/work/NetworkType;)Landroidx/work/Constraints$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "requiresBatteryNotLow", "setRequiresBatteryNotLow", "(Z)Landroidx/work/Constraints$Builder;", "Landroidx/work/Constraints;", "build", "()Landroidx/work/Constraints;", "requiresCharging", "Z", "requiresDeviceIdle", "Landroidx/work/impl/utils/NetworkRequestCompat;", "requiredNetworkRequest", "Landroidx/work/impl/utils/NetworkRequestCompat;", "requiredNetworkType", "Landroidx/work/NetworkType;", "requiresStorageNotLow", _UrlKt.FRAGMENT_ENCODE_SET, "triggerContentUpdateDelay", "J", "triggerContentMaxDelay", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/Constraints$ContentUriTrigger;", "contentUriTriggers", "Ljava/util/Set;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Builder {
        private Set<ContentUriTrigger> contentUriTriggers;
        private NetworkRequestCompat requiredNetworkRequest;
        private NetworkType requiredNetworkType;
        private boolean requiresBatteryNotLow;
        private boolean requiresCharging;
        private boolean requiresDeviceIdle;
        private boolean requiresStorageNotLow;
        private long triggerContentMaxDelay;
        private long triggerContentUpdateDelay;

        public Builder() {
            this.requiredNetworkRequest = new NetworkRequestCompat(null, 1, null);
            this.requiredNetworkType = NetworkType.NOT_REQUIRED;
            this.triggerContentUpdateDelay = -1;
            this.triggerContentMaxDelay = -1;
            this.contentUriTriggers = new LinkedHashSet();
        }

        public final Constraints build() {
            Set r12 = CollectionsKt.toSet(this.contentUriTriggers);
            long r8 = this.triggerContentUpdateDelay;
            long r10 = this.triggerContentMaxDelay;
            return new Constraints(this.requiredNetworkRequest, this.requiredNetworkType, this.requiresCharging, this.requiresDeviceIdle, this.requiresBatteryNotLow, this.requiresStorageNotLow, r8, r10, r12);
        }

        public final Builder setRequiredNetworkType(NetworkType r3) {
            this.requiredNetworkType = r3;
            this.requiredNetworkRequest = new NetworkRequestCompat(null, 1, null);
            return this;
        }

        public final Builder setRequiresBatteryNotLow(boolean r1) {
            this.requiresBatteryNotLow = r1;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/work/Constraints$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "NONE", "Landroidx/work/Constraints;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u000f"}, d2 = {"Landroidx/work/Constraints$ContentUriTrigger;", _UrlKt.FRAGMENT_ENCODE_SET, "uri", "Landroid/net/Uri;", "isTriggeredForDescendants", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroid/net/Uri;Z)V", "getUri", "()Landroid/net/Uri;", "()Z", "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class ContentUriTrigger {
        private final boolean isTriggeredForDescendants;
        private final Uri uri;

        public ContentUriTrigger(Uri r1, boolean r2) {
            this.uri = r1;
            this.isTriggeredForDescendants = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L5;
            return true;
        L5:
            if (r5 == null) goto L7;
            Class<?> r1 = r5.getClass();
        L9:
            if (Intrinsics.areEqual(ContentUriTrigger.class, r1) == true) goto L11;
            return false;
        L11:
            ContentUriTrigger r52 = (ContentUriTrigger) r5;
            if (Intrinsics.areEqual(this.uri, r52.uri) == true) goto L15;
            return false;
        L15:
            if (this.isTriggeredForDescendants == r52.isTriggeredForDescendants) goto L17;
            return false;
        L17:
            return true;
        L7:
            r1 = null;
            goto L9
        }

        public final Uri getUri() {
            return this.uri;
        }

        public int hashCode() {
            return (this.uri.hashCode() * 31) + Boolean.hashCode(this.isTriggeredForDescendants);
        }

        public final boolean isTriggeredForDescendants() {
            return this.isTriggeredForDescendants;
        }
    }

    static {
        Companion = new Companion(null);
        NONE = new Constraints(null, false, false, false, 15, null);
    }

    @SuppressLint({"NewApi"})
    public Constraints(Constraints r3) {
        this.requiresCharging = r3.requiresCharging;
        this.requiresDeviceIdle = r3.requiresDeviceIdle;
        this.requiredNetworkRequestCompat = r3.requiredNetworkRequestCompat;
        this.requiredNetworkType = r3.requiredNetworkType;
        this.requiresBatteryNotLow = r3.requiresBatteryNotLow;
        this.requiresStorageNotLow = r3.requiresStorageNotLow;
        this.contentUriTriggers = r3.contentUriTriggers;
        this.contentTriggerUpdateDelayMillis = r3.contentTriggerUpdateDelayMillis;
        this.contentTriggerMaxDelayMillis = r3.contentTriggerMaxDelayMillis;
    }

    @SuppressLint({"NewApi"})
    public Constraints(NetworkType r7, boolean r8, boolean r9, boolean r10) {
        this(r7, r8, false, r9, r10);
    }

    public /* synthetic */ Constraints(NetworkType r2, boolean r3, boolean r4, boolean r5, int r6, DefaultConstructorMarker r7) {
        if ((r6 & 1) == 0) goto L6;
        r2 = NetworkType.NOT_REQUIRED;
    L6:
        if ((r6 & 2) == 0) goto L9;
        r3 = false;
    L9:
        if ((r6 & 4) == 0) goto L12;
        r4 = false;
    L12:
        if ((r6 & 8) == 0) goto L14;
        r5 = false;
    L14:
        this(r2, r3, r4, r5);
    }

    public Constraints(NetworkType r14, boolean r15, boolean r16, boolean r17, boolean r18) {
        this(r14, r15, r16, r17, r18, -1, 0, null, 192, null);
    }

    public Constraints(NetworkType r4, boolean r5, boolean r6, boolean r7, boolean r8, long r9, long r11, Set<ContentUriTrigger> r13) {
        this.requiredNetworkRequestCompat = new NetworkRequestCompat(null, 1, null);
        this.requiredNetworkType = r4;
        this.requiresCharging = r5;
        this.requiresDeviceIdle = r6;
        this.requiresBatteryNotLow = r7;
        this.requiresStorageNotLow = r8;
        this.contentTriggerUpdateDelayMillis = r9;
        this.contentTriggerMaxDelayMillis = r11;
        this.contentUriTriggers = r13;
    }

    public /* synthetic */ Constraints(NetworkType r3, boolean r4, boolean r5, boolean r6, boolean r7, long r8, long r10, Set r12, int r13, DefaultConstructorMarker r14) {
        if ((r13 & 1) == 0) goto L6;
        r3 = NetworkType.NOT_REQUIRED;
    L6:
        if ((r13 & 2) == 0) goto L9;
        r4 = false;
    L9:
        if ((r13 & 4) == 0) goto L12;
        r5 = false;
    L12:
        if ((r13 & 8) == 0) goto L15;
        r6 = false;
    L15:
        if ((r13 & 16) == 0) goto L18;
        r7 = false;
    L18:
        if ((r13 & 32) == 0) goto L21;
        r8 = -1;
    L21:
        if ((r13 & 64) == 0) goto L24;
        r10 = -1;
    L24:
        if ((r13 & 128) == 0) goto L26;
        r12 = SetsKt.emptySet();
    L26:
        Set r142 = r12;
        long r122 = r10;
        long r102 = r8;
        boolean r9 = r7;
        boolean r72 = r5;
        NetworkType r52 = r3;
        this(r52, r4, r72, r6, r9, r102, r122, r142);
    }

    public Constraints(NetworkRequestCompat r1, NetworkType r2, boolean r3, boolean r4, boolean r5, boolean r6, long r7, long r9, Set<ContentUriTrigger> r11) {
        this.requiredNetworkRequestCompat = r1;
        this.requiredNetworkType = r2;
        this.requiresCharging = r3;
        this.requiresDeviceIdle = r4;
        this.requiresBatteryNotLow = r5;
        this.requiresStorageNotLow = r6;
        this.contentTriggerUpdateDelayMillis = r7;
        this.contentTriggerMaxDelayMillis = r9;
        this.contentUriTriggers = r11;
    }

    @SuppressLint({"NewApi"})
    public boolean equals(Object r6) {
        if (this != r6) goto L6;
        return true;
    L6:
        if (r6 != null) goto L8;
    L36:
        return false;
    L8:
        if (Intrinsics.areEqual(Constraints.class, r6.getClass()) == false) goto L36;
        Constraints r62 = (Constraints) r6;
        if (this.requiresCharging == r62.requiresCharging) goto L14;
        return false;
    L14:
        if (this.requiresDeviceIdle == r62.requiresDeviceIdle) goto L17;
        return false;
    L17:
        if (this.requiresBatteryNotLow == r62.requiresBatteryNotLow) goto L20;
        return false;
    L20:
        if (this.requiresStorageNotLow == r62.requiresStorageNotLow) goto L23;
        return false;
    L23:
        if (this.contentTriggerUpdateDelayMillis == r62.contentTriggerUpdateDelayMillis) goto L26;
        return false;
    L26:
        if (this.contentTriggerMaxDelayMillis == r62.contentTriggerMaxDelayMillis) goto L29;
        return false;
    L29:
        if (Intrinsics.areEqual(getRequiredNetworkRequest(), r62.getRequiredNetworkRequest()) == true) goto L32;
        return false;
    L32:
        if (this.requiredNetworkType == r62.requiredNetworkType) goto L35;
        return false;
    L35:
        return Intrinsics.areEqual(this.contentUriTriggers, r62.contentUriTriggers);
    }

    public final long getContentTriggerMaxDelayMillis() {
        return this.contentTriggerMaxDelayMillis;
    }

    public final long getContentTriggerUpdateDelayMillis() {
        return this.contentTriggerUpdateDelayMillis;
    }

    public final Set<ContentUriTrigger> getContentUriTriggers() {
        return this.contentUriTriggers;
    }

    public final NetworkRequest getRequiredNetworkRequest() {
        return this.requiredNetworkRequestCompat.getNetworkRequest();
    }

    public final NetworkRequestCompat getRequiredNetworkRequestCompat$work_runtime_release() {
        return this.requiredNetworkRequestCompat;
    }

    public final NetworkType getRequiredNetworkType() {
        return this.requiredNetworkType;
    }

    public final boolean hasContentUriTriggers() {
        return !this.contentUriTriggers.isEmpty();
    }

    @SuppressLint({"NewApi"})
    public int hashCode() {
        int r0 = ((((((((this.requiredNetworkType.hashCode() * 31) + (this.requiresCharging ? 1 : 0)) * 31) + (this.requiresDeviceIdle ? 1 : 0)) * 31) + (this.requiresBatteryNotLow ? 1 : 0)) * 31) + (this.requiresStorageNotLow ? 1 : 0)) * 31;
        long r1 = this.contentTriggerUpdateDelayMillis;
        int r02 = (r0 + ((int) (r1 ^ (r1 >>> 32)))) * 31;
        long r12 = this.contentTriggerMaxDelayMillis;
        int r03 = (((r02 + ((int) (r12 ^ (r12 >>> 32)))) * 31) + this.contentUriTriggers.hashCode()) * 31;
        NetworkRequest r6 = getRequiredNetworkRequest();
        if (r6 == null) goto L5;
        int r62 = r6.hashCode();
    L7:
        return r03 + r62;
    L5:
        r62 = 0;
        goto L7
    }

    public final boolean requiresBatteryNotLow() {
        return this.requiresBatteryNotLow;
    }

    public final boolean requiresCharging() {
        return this.requiresCharging;
    }

    public final boolean requiresDeviceIdle() {
        return this.requiresDeviceIdle;
    }

    public final boolean requiresStorageNotLow() {
        return this.requiresStorageNotLow;
    }

    @SuppressLint({"NewApi"})
    public String toString() {
        return "Constraints{requiredNetworkType=" + this.requiredNetworkType + ", requiresCharging=" + this.requiresCharging + ", requiresDeviceIdle=" + this.requiresDeviceIdle + ", requiresBatteryNotLow=" + this.requiresBatteryNotLow + ", requiresStorageNotLow=" + this.requiresStorageNotLow + ", contentTriggerUpdateDelayMillis=" + this.contentTriggerUpdateDelayMillis + ", contentTriggerMaxDelayMillis=" + this.contentTriggerMaxDelayMillis + ", contentUriTriggers=" + this.contentUriTriggers + ", }";
    }
}
