package androidx.room;

import androidx.room.InvalidationTracker;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0011H\u0000¢\u0006\u0002\b\u0016J\u001b\u0010\u0017\u001a\u00020\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0011H\u0000¢\u0006\u0002\b\u0019R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/room/ObserverWrapper;", _UrlKt.FRAGMENT_ENCODE_SET, "observer", "Landroidx/room/InvalidationTracker$Observer;", "tableIds", _UrlKt.FRAGMENT_ENCODE_SET, "tableNames", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroidx/room/InvalidationTracker$Observer;[I[Ljava/lang/String;)V", "getObserver$room_runtime", "()Landroidx/room/InvalidationTracker$Observer;", "getTableIds$room_runtime", "()[I", "[Ljava/lang/String;", "singleTableSet", _UrlKt.FRAGMENT_ENCODE_SET, "notifyByTableIds", _UrlKt.FRAGMENT_ENCODE_SET, "invalidatedTablesIds", _UrlKt.FRAGMENT_ENCODE_SET, "notifyByTableIds$room_runtime", "notifyByTableNames", "invalidatedTablesNames", "notifyByTableNames$room_runtime", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInvalidationTracker.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InvalidationTracker.android.kt\nandroidx/room/ObserverWrapper\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,592:1\n13567#2,3:593\n1761#3,3:596\n1869#3,2:599\n*S KotlinDebug\n*F\n+ 1 InvalidationTracker.android.kt\nandroidx/room/ObserverWrapper\n*L\n532#1:593,3\n550#1:596,3\n558#1:599,2\n*E\n"})
/* loaded from: classes4.dex */
public final class ObserverWrapper {
    private final InvalidationTracker.Observer observer;
    private final Set<String> singleTableSet;
    private final int[] tableIds;
    private final String[] tableNames;

    public ObserverWrapper(InvalidationTracker.Observer r1, int[] r2, String[] r3) {
        this.observer = r1;
        this.tableIds = r2;
        this.tableNames = r3;
        if (r2.length == r3.length) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        throw null;
    L5:
        if (r3.length != 0) goto L7;
        boolean r12 = true;
    L8:
        if (r12 == true) goto L10;
        Set<String> r13 = SetsKt.setOf(r3[0]);
    L11:
        this.singleTableSet = r13;
        return;
    L10:
        r13 = SetsKt.emptySet();
        goto L11
    L7:
        r12 = false;
        goto L8
    }

    public final InvalidationTracker.Observer getObserver$room_runtime() {
        return this.observer;
    }

    public final int[] getTableIds$room_runtime() {
        return this.tableIds;
    }

    public final void notifyByTableIds$room_runtime(Set<Integer> r8) {
        int[] r0 = this.tableIds;
        int r1 = r0.length;
        if (r1 == 0) goto L17;
        int r2 = 0;
        if (r1 == 1) goto L14;
        Set r02 = SetsKt.createSetBuilder();
        int[] r12 = this.tableIds;
        int r3 = r12.length;
        int r4 = 0;
    L7:
        if (r2 >= r3) goto L12;
        int r6 = r4 + 1;
        if (r8.contains(Integer.valueOf(r12[r2])) == false) goto L11;
        r02.add(this.tableNames[r4]);
    L11:
        r2 = r2 + 1;
        r4 = r6;
        goto L7
    L12:
        Set<String> r82 = SetsKt.build(r02);
    L19:
        if (r82.isEmpty() == true) goto L25;
        this.observer.onInvalidated(r82);
        return;
    L25:
        return;
    L14:
        if (r8.contains(Integer.valueOf(r0[0])) == false) goto L16;
        r82 = this.singleTableSet;
        goto L19
    L16:
        r82 = SetsKt.emptySet();
        goto L19
    L17:
        r82 = SetsKt.emptySet();
        goto L19
    }

    public final void notifyByTableNames$room_runtime(Set<String> r10) {
        int r0 = this.tableNames.length;
        if (r0 != 0) goto L5;
        Set<String> r102 = SetsKt.emptySet();
    L29:
        if (r102.isEmpty() == true) goto L40;
        this.observer.onInvalidated(r102);
        return;
    L40:
        return;
    L5:
        if (r0 == 1) goto L16;
        Set r02 = SetsKt.createSetBuilder();
        Iterator<T> r103 = r10.iterator();
    L8:
        if (r103.hasNext() == false) goto L15;
        String r3 = (String) r103.next();
        String[] r4 = this.tableNames;
        int r5 = r4.length;
        int r6 = 0;
    L10:
        if (r6 >= r5) goto L8;
        String r7 = r4[r6];
        if (StringsKt.equals(r7, r3, true) == true) goto L13;
        r6 = r6 + 1;
        goto L10
    L13:
        r02.add(r7);
        goto L8
    L15:
        r102 = SetsKt.build(r02);
        goto L29
    L16:
        if (r10 != null) goto L18;
    L20:
        Iterator<T> r104 = r10.iterator();
    L22:
        if (r104.hasNext() == false) goto L26;
        if (StringsKt.equals((String) r104.next(), this.tableNames[0], true) == false) goto L22;
        r102 = this.singleTableSet;
    L26:
        r102 = SetsKt.emptySet();
        goto L29
    L18:
        if (r10.isEmpty() == false) goto L20;
        goto L20
    }
}
