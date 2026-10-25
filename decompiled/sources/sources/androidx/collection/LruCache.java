package androidx.collection;

import androidx.collection.internal.Lock;
import androidx.collection.internal.LruHashMap;
import androidx.collection.internal.RuntimeHelpersKt;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\f\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000H\u0086\u0002¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000e\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0011\u0010\u0007J\u0017\u0010\u0012\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0004\b\u0012\u0010\rJ1\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u00012\b\u0010\u0016\u001a\u0004\u0018\u00018\u0001H\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u0019\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u0019\u0010\rJ\u001f\u0010\u001a\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001H\u0014¢\u0006\u0004\b\u001a\u0010\u000bJ\r\u0010\u001b\u001a\u00020\u0010¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0004¢\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010 \u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001f¢\u0006\u0004\b \u0010!J\u000f\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\b#\u0010$R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010%R \u0010'\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010*\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010\u001d\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010%R\u0016\u0010,\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010%R\u0016\u0010-\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010%R\u0016\u0010.\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010%R\u0016\u0010/\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u0010%R\u0016\u00100\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u0010%¨\u00061"}, d2 = {"Landroidx/collection/LruCache;", _UrlKt.FRAGMENT_ENCODE_SET, "K", "V", _UrlKt.FRAGMENT_ENCODE_SET, "maxSize", "<init>", "(I)V", PluginsConstants.Settings.KEY, "value", "safeSizeOf", "(Ljava/lang/Object;Ljava/lang/Object;)I", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "trimToSize", "remove", _UrlKt.FRAGMENT_ENCODE_SET, "evicted", "oldValue", "newValue", "entryRemoved", "(ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "create", "sizeOf", "evictAll", "()V", "size", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "snapshot", "()Ljava/util/Map;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "I", "Landroidx/collection/internal/LruHashMap;", "map", "Landroidx/collection/internal/LruHashMap;", "Landroidx/collection/internal/Lock;", "lock", "Landroidx/collection/internal/Lock;", "putCount", "createCount", "evictionCount", "hitCount", "missCount", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LruCache.kt\nandroidx/collection/LruCache\n+ 2 RuntimeHelpers.kt\nandroidx/collection/internal/RuntimeHelpersKt\n+ 3 LockExt.kt\nandroidx/collection/internal/LockExtKt\n+ 4 Lock.jvm.kt\nandroidx/collection/internal/Lock\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,356:1\n59#2,5:357\n59#2,5:362\n45#2,5:382\n45#2,5:390\n26#3,2:367\n26#3,2:370\n26#3,2:373\n26#3,2:376\n26#3,2:379\n26#3,2:387\n26#3,2:395\n26#3,2:398\n26#3,2:401\n26#3,2:404\n26#3,2:407\n26#3,2:410\n26#3,2:413\n26#3,2:416\n26#3,2:421\n26#4:369\n26#4:372\n26#4:375\n26#4:378\n26#4:381\n26#4:389\n26#4:397\n26#4:400\n26#4:403\n26#4:406\n26#4:409\n26#4:412\n26#4:415\n26#4:418\n26#4:423\n1855#5,2:419\n*S KotlinDebug\n*F\n+ 1 LruCache.kt\nandroidx/collection/LruCache\n*L\n62#1:357,5\n83#1:362,5\n170#1:382,5\n245#1:390,5\n85#1:367,2\n96#1:370,2\n113#1:373,2\n140#1:376,2\n169#1:379,2\n198#1:387,2\n267#1:395,2\n274#1:398,2\n277#1:401,2\n280#1:404,2\n283#1:407,2\n286#1:410,2\n289#1:413,2\n298#1:416,2\n306#1:421,2\n85#1:369\n96#1:372\n113#1:375\n140#1:378\n169#1:381\n198#1:389\n267#1:397\n274#1:400\n277#1:403\n280#1:406\n283#1:409\n286#1:412\n289#1:415\n298#1:418\n306#1:423\n300#1:419,2\n*E\n"})
/* loaded from: classes.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final Lock lock;
    private final LruHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int r3) {
        this.maxSize = r3;
        if (r3 <= 0) goto L5;
        boolean r32 = true;
    L6:
        if (r32 == true) goto L8;
        RuntimeHelpersKt.throwIllegalArgumentException("maxSize <= 0");
    L8:
        this.map = new LruHashMap(0, 0.75f);
        this.lock = new Lock();
        return;
    L5:
        r32 = false;
        goto L6
    }

    private final int safeSizeOf(K r3, V r4) {
        int r2 = sizeOf(r3, r4);
        if (r2 < 0) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == true) goto L8;
        RuntimeHelpersKt.throwIllegalStateException("Negative size: " + r3 + SignatureVisitor.INSTANCEOF + r4);
    L8:
        return r2;
    L5:
        r0 = false;
        goto L6
    }

    public V create(K r1) {
        return null;
    }

    public void entryRemoved(boolean r1, K r2, V r3, V r4) {
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final V get(K r6) {
        Lock r0 = this.lock;
        monitor-enter(r0);
        V r1 = this.map.get(r6);     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        this.hitCount++;
        monitor-exit(r0);
        return r1;
    L11:
        this.missCount++;
        monitor-exit(r0);
        V r02 = create(r6);
        if (r02 != null) goto L17;
        return null;
    L17:
        Lock r12 = this.lock;
        monitor-enter(r12);
        this.createCount++;
        V r2 = (V) this.map.put(r6, r02);
        if (r2 == null) goto L24;
        this.map.put(r6, r2);     // Catch: Throwable -> L22
    L25:
        monitor-exit(r12);
        if (r2 == null) goto L29;
        entryRemoved(false, r6, r02, r2);
        return r2;
    L29:
        trimToSize(this.maxSize);
        return r02;
    L24:
        this.size += safeSizeOf(r6, r02);
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L22
    L22:
        th = move-exception;
        throw th;
    L9:
        th = move-exception;
        throw th;
    }

    public final V put(K r5, V r6) {
        Lock r0 = this.lock;
        monitor-enter(r0);
        this.putCount++;
        this.size += safeSizeOf(r5, r6);
        V r1 = this.map.put(r5, r6);     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        this.size -= safeSizeOf(r5, r1);
    L9:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        if (r1 == null) goto L13;
        entryRemoved(false, r5, r1, r6);
    L13:
        trimToSize(this.maxSize);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public final V remove(K r5) {
        Lock r0 = this.lock;
        monitor-enter(r0);
        V r1 = this.map.remove(r5);     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        this.size -= safeSizeOf(r5, r1);
    L9:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        if (r1 == null) goto L13;
        entryRemoved(false, r5, r1, null);
    L13:
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public final int size() {
        Lock r0 = this.lock;
        monitor-enter(r0);
        int r1 = this.size;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public int sizeOf(K r1, V r2) {
        return 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Map<K, V> snapshot() {
        Lock r0 = this.lock;
        monitor-enter(r0);
        LinkedHashMap r1 = new LinkedHashMap(this.map.getEntries().size());     // Catch: Throwable -> L9
        Iterator<T> r4 = this.map.getEntries().iterator();     // Catch: Throwable -> L9
    L5:
        if (r4.hasNext() == false) goto L11;
        Map.Entry r2 = (Map.Entry) r4.next();     // Catch: Throwable -> L9
        r1.put(r2.getKey(), r2.getValue());     // Catch: Throwable -> L9
        goto L5
    L11:
        monitor-exit(r0);
        return r1;
    L9:
        th = move-exception;
        throw th;
    }

    public String toString() {
        Lock r1 = this.lock;
        monitor-enter(r1);
        int r2 = this.hitCount;     // Catch: Throwable -> L7
        int r3 = this.missCount + r2;     // Catch: Throwable -> L7
        if (r3 == 0) goto L9;
        int r22 = (r2 * 100) / r3;     // Catch: Throwable -> L7
    L10:
        String r4 = "LruCache[maxSize=" + this.maxSize + ",hits=" + this.hitCount + ",misses=" + this.missCount + ",hitRate=" + r22 + "%]";     // Catch: Throwable -> L7
        monitor-exit(r1);
        return r4;
    L9:
        r22 = 0;
    L7:
        th = move-exception;
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void trimToSize(int r7) {
    L2:
        Lock r0 = this.lock;
        monitor-enter(r0);
        if (this.size >= 0) goto L7;
    L14:
        boolean r1 = false;
    L15:
        if (r1 == true) goto L18;
        RuntimeHelpersKt.throwIllegalStateException("LruCache.sizeOf() is reporting inconsistent results!");     // Catch: Throwable -> L11
    L18:
        if (this.size <= r7) goto L29;
        if (this.map.isEmpty() == true) goto L29;
        Map.Entry r12 = (Map.Entry) CollectionsKt.firstOrNull(this.map.getEntries());     // Catch: Throwable -> L11
        if (r12 == null) goto L24;
        Object r3 = r12.getKey();     // Catch: Throwable -> L11
        Object r13 = r12.getValue();     // Catch: Throwable -> L11
        this.map.remove(r3);     // Catch: Throwable -> L11
        this.size -= safeSizeOf(r3, r13);
        this.evictionCount++;
        monitor-exit(r0);
        entryRemoved(true, r3, r13, null);
        goto L2
    L24:
        monitor-exit(r0);
        return;
    L29:
        monitor-exit(r0);
        return;
    L7:
        if (this.map.isEmpty() == true) goto L9;
    L13:
        r1 = true;
        goto L15
    L9:
        if (this.size != 0) goto L14;
    L11:
        th = move-exception;
        throw th;
    }
}
