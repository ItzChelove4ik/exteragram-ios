package com.exteragram.messenger.feed;

import android.content.SharedPreferences;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ApplicationLoader;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 /2\u00020\u0001:\u00020/B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0007¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\f¢\u0006\u0004\b\u0016\u0010\u0017J\u0013\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001b\u001a\u00020\t2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\u001b\u0010\u000bJ\r\u0010\u001c\u001a\u00020\t¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u001f\u001a\u00020\t2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u001e¢\u0006\u0004\b\u001f\u0010 J\r\u0010!\u001a\u00020\u0002¢\u0006\u0004\b!\u0010\"J\r\u0010$\u001a\u00020#¢\u0006\u0004\b$\u0010%R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.¨\u00061"}, d2 = {"Lcom/exteragram/messenger/feed/FeedConfig;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "account", "<init>", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "updated", _UrlKt.FRAGMENT_ENCODE_SET, "applyExcluded", "(Ljava/util/Set;)V", _UrlKt.FRAGMENT_ENCODE_SET, "isIncludeArchived", "()Z", "value", "setIncludeArchived", "(Z)V", "dialogId", "isExcluded", "(J)Z", "excluded", "setExcluded", "(JZ)V", "getExcludedSnapshot", "()Ljava/util/Set;", "ids", "removeExcluded", "clearExcluded", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "excludeAll", "(Ljava/util/Collection;)V", "getGeneration", "()I", "Lcom/exteragram/messenger/feed/FeedConfig$Snapshot;", "snapshot", "()Lcom/exteragram/messenger/feed/FeedConfig$Snapshot;", "Landroid/content/SharedPreferences;", "preferences", "Landroid/content/SharedPreferences;", "excludedChannels", "Ljava/util/Set;", "includeArchived", "Z", "generation", "I", "Companion", "Snapshot", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFeedConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FeedConfig.kt\ncom/exteragram/messenger/feed/FeedConfig\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 SharedPreferences.kt\nandroidx/core/content/SharedPreferencesKt\n*L\n1#1,143:1\n1651#2:144\n1915#2:145\n1916#2:147\n1652#2:148\n1661#2,3:167\n1#3:146\n41#4,12:149\n41#4,6:161\n47#4,6:170\n*S KotlinDebug\n*F\n+ 1 FeedConfig.kt\ncom/exteragram/messenger/feed/FeedConfig\n*L\n36#1:144\n36#1:145\n36#1:147\n36#1:148\n111#1:167,3\n36#1:146\n50#1:149,12\n111#1:161,6\n111#1:170,6\n*E\n"})
/* loaded from: classes.dex */
public final class FeedConfig {
    public static final Companion Companion = null;
    private static final FeedConfig[] instances = null;
    private static final Object[] lockObjects = null;
    private volatile Set<Long> excludedChannels;
    private volatile int generation;
    private volatile boolean includeArchived;
    private final SharedPreferences preferences;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\r"}, d2 = {"Lcom/exteragram/messenger/feed/FeedConfig$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "instances", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/feed/FeedConfig;", "[Lcom/exteragram/messenger/feed/FeedConfig;", "lockObjects", "[Ljava/lang/Object;", "getInstance", "num", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final FeedConfig getInstance(int r3) {
            FeedConfig r2 = FeedConfig.access$getInstances$cp()[r3];
            if (r2 != null) goto L16;
            Object r22 = FeedConfig.access$getLockObjects$cp()[r3];
            monitor-enter(r22);
            FeedConfig r0 = FeedConfig.access$getInstances$cp()[r3];     // Catch: Throwable -> L9
            if (r0 != null) goto L11;
            r0 = new FeedConfig(r3, null);     // Catch: Throwable -> L9
            FeedConfig.access$getInstances$cp()[r3] = r0;     // Catch: Throwable -> L9
        L11:
            Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L9
            monitor-exit(r22);
            return r0;
        L9:
            th = move-exception;
            throw th;
        L16:
            return r2;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0011\u001a\u00020\u00022\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u0019\u001a\u0004\b\u001a\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/exteragram/messenger/feed/FeedConfig$Snapshot;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "includeArchived", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "excludedChannels", _UrlKt.FRAGMENT_ENCODE_SET, "generation", "<init>", "(ZLjava/util/Set;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Z", "getIncludeArchived", "()Z", "Ljava/util/Set;", "getExcludedChannels", "()Ljava/util/Set;", "I", "getGeneration", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Snapshot {
        private final Set<Long> excludedChannels;
        private final int generation;
        private final boolean includeArchived;

        public Snapshot(boolean r1, Set<Long> r2, int r3) {
            this.includeArchived = r1;
            this.excludedChannels = r2;
            this.generation = r3;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Snapshot) == true) goto L8;
            return false;
        L8:
            Snapshot r52 = (Snapshot) r5;
            if (this.includeArchived == r52.includeArchived) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.excludedChannels, r52.excludedChannels) == true) goto L15;
            return false;
        L15:
            if (this.generation == r52.generation) goto L17;
            return false;
        L17:
            return true;
        }

        public final Set<Long> getExcludedChannels() {
            return this.excludedChannels;
        }

        public final int getGeneration() {
            return this.generation;
        }

        public final boolean getIncludeArchived() {
            return this.includeArchived;
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.includeArchived) * 31) + this.excludedChannels.hashCode()) * 31) + Integer.hashCode(this.generation);
        }

        public String toString() {
            return "Snapshot(includeArchived=" + this.includeArchived + ", excludedChannels=" + this.excludedChannels + ", generation=" + this.generation + ")";
        }
    }

    static {
        Companion = new Companion(null);
        instances = new FeedConfig[16];
        Object[] r1 = new Object[16];
        int r2 = 0;
    L3:
        if (r2 >= 16) goto L5;
        r1[r2] = new Object();
        r2 = r2 + 1;
        goto L3
    L5:
        lockObjects = r1;
    }

    private FeedConfig(int r4) {
        SharedPreferences r42 = ApplicationLoader.applicationContext.getSharedPreferences("feedconfig" + r4, 0);
        this.preferences = r42;
        this.excludedChannels = SetsKt.emptySet();
        this.includeArchived = r42.getBoolean("includeArchived", false);
        Set<String> r43 = r42.getStringSet("excludedChannels", null);
        if (r43 == null) goto L10;
        Set<Long> r0 = new HashSet();
        Iterator<T> r44 = r43.iterator();
    L6:
        if (r44.hasNext() == false) goto L11;
        Long r1 = StringsKt.toLongOrNull((String) r44.next());
        if (r1 == null) goto L6;
        r0.add(r1);
    L11:
        this.excludedChannels = r0;
        return;
    L10:
        r0 = SetsKt.emptySet();
        goto L11
    }

    public /* synthetic */ FeedConfig(int r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    public static final /* synthetic */ FeedConfig[] access$getInstances$cp() {
        return instances;
    }

    public static final /* synthetic */ Object[] access$getLockObjects$cp() {
        return lockObjects;
    }

    private final void applyExcluded(Set<Long> r4) {
        this.excludedChannels = r4;
        this.generation++;
        SharedPreferences.Editor r3 = this.preferences.edit();
        HashSet r0 = new HashSet();
        Iterator<T> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        r0.add(String.valueOf(((Number) r42.next()).longValue()));
        goto L4
    L6:
        r3.putStringSet("excludedChannels", r0);
        r3.apply();
    }

    @JvmStatic
    public static final FeedConfig getInstance(int r1) {
        return Companion.getInstance(r1);
    }

    public final synchronized void clearExcluded() {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.excludedChannels.isEmpty() == false) goto L7;
        monitor-exit(this);
        return;
    L7:
        applyExcluded(SetsKt.emptySet());     // Catch: Throwable -> L10
        monitor-exit(this);
    }

    public final synchronized void excludeAll(Collection<Long> r3) {
        monitor-enter(this);
        HashSet r0 = new HashSet(this.excludedChannels);     // Catch: Throwable -> L10
        if (r0.addAll(r3) == true) goto L7;
        monitor-exit(this);
        return;
    L7:
        applyExcluded(r0);     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public final Set<Long> getExcludedSnapshot() {
        return this.excludedChannels;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public final boolean isExcluded(long r1) {
        return this.excludedChannels.contains(Long.valueOf(r1));
    }

    public final boolean isIncludeArchived() {
        return this.includeArchived;
    }

    public final synchronized void removeExcluded(Set<Long> r3) {
        monitor-enter(this);
    L14:
        th = move-exception;
        throw th;
    L4:
        if (r3.isEmpty() == false) goto L7;
        monitor-exit(this);
        return;
    L7:
        HashSet r0 = new HashSet(this.excludedChannels);     // Catch: Throwable -> L14
        if (r0.removeAll(r3) == true) goto L11;
        monitor-exit(this);
        return;
    L11:
        applyExcluded(r0);     // Catch: Throwable -> L14
        monitor-exit(this);
    }

    public final synchronized void setExcluded(long r3, boolean r5) {
        monitor-enter(this);
        HashSet r0 = new HashSet(this.excludedChannels);     // Catch: Throwable -> L6
        if (r5 == false) goto L8;
        boolean r32 = r0.add(Long.valueOf(r3));     // Catch: Throwable -> L6
    L9:
        if (r32 == true) goto L12;
        monitor-exit(this);
        return;
    L12:
        applyExcluded(r0);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L8:
        r32 = r0.remove(Long.valueOf(r3));     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized void setIncludeArchived(boolean r3) {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.includeArchived != r3) goto L7;
        monitor-exit(this);
        return;
    L7:
        this.includeArchived = r3;     // Catch: Throwable -> L10
        this.generation++;
        SharedPreferences.Editor r0 = this.preferences.edit();     // Catch: Throwable -> L10
        r0.putBoolean("includeArchived", r3);     // Catch: Throwable -> L10
        r0.apply();     // Catch: Throwable -> L10
        monitor-exit(this);
    }

    public final synchronized Snapshot snapshot() {
        monitor-enter(this);
        Snapshot r0 = new Snapshot(this.includeArchived, this.excludedChannels, this.generation);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }
}
