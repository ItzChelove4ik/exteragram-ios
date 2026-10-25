package androidx.room;

import androidx.room.util.SQLiteConnectionUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H$J\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H$¢\u0006\u0002\u0010\fJ\u001d\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0010J'\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013J \u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u0014J\u001d\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u001aJ'\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u0012¢\u0006\u0002\u0010\u001bJ-\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00160\u00122\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u001a¢\u0006\u0002\u0010\u001dJ/\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00160\u00122\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u0012¢\u0006\u0002\u0010\u001eJ-\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160 2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\b\b\u0001\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u0012¢\u0006\u0002\u0010!J&\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160 2\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u001a¨\u0006\""}, d2 = {"Landroidx/room/EntityInsertAdapter;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "(Landroidx/sqlite/SQLiteStatement;Ljava/lang/Object;)V", "insert", "connection", "Landroidx/sqlite/SQLiteConnection;", "(Landroidx/sqlite/SQLiteConnection;Ljava/lang/Object;)V", "entities", _UrlKt.FRAGMENT_ENCODE_SET, "(Landroidx/sqlite/SQLiteConnection;[Ljava/lang/Object;)V", _UrlKt.FRAGMENT_ENCODE_SET, "insertAndReturnId", _UrlKt.FRAGMENT_ENCODE_SET, "(Landroidx/sqlite/SQLiteConnection;Ljava/lang/Object;)J", "insertAndReturnIdsArray", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "(Landroidx/sqlite/SQLiteConnection;[Ljava/lang/Object;)[J", "insertAndReturnIdsArrayBox", "(Landroidx/sqlite/SQLiteConnection;Ljava/util/Collection;)[Ljava/lang/Long;", "(Landroidx/sqlite/SQLiteConnection;[Ljava/lang/Object;)[Ljava/lang/Long;", "insertAndReturnIdsList", _UrlKt.FRAGMENT_ENCODE_SET, "(Landroidx/sqlite/SQLiteConnection;[Ljava/lang/Object;)Ljava/util/List;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nEntityInsertAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EntityInsertAdapter.kt\nandroidx/room/EntityInsertAdapter\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,270:1\n13472#2,2:271\n1869#3,2:273\n*S KotlinDebug\n*F\n+ 1 EntityInsertAdapter.kt\nandroidx/room/EntityInsertAdapter\n*L\n229#1:271,2\n256#1:273,2\n*E\n"})
/* loaded from: classes.dex */
public abstract class EntityInsertAdapter<T> {
    public EntityInsertAdapter() {
    }

    public abstract void bind(SQLiteStatement r1, T r2);

    public abstract String createQuery();

    public final void insert(SQLiteConnection r2, Iterable<? extends T> r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        SQLiteStatement r22 = r2.prepare(createQuery());
        Iterator<? extends T> r32 = r3.iterator();     // Catch: Throwable -> L11
    L7:
        if (r32.hasNext() == false) goto L13;
        T r0 = r32.next();     // Catch: Throwable -> L11
        if (r0 == null) goto L7;
        bind(r22, r0);     // Catch: Throwable -> L11
        r22.step();     // Catch: Throwable -> L11
        r22.reset();     // Catch: Throwable -> L11
        goto L7
    L13:
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L11
        AutoCloseableKt.closeFinally(r22, null);
        return;
    L11:
        th = move-exception;
        throw th;     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        AutoCloseableKt.closeFinally(r22, th);
        throw th;
    }

    public final void insert(SQLiteConnection r2, T r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        SQLiteStatement r22 = r2.prepare(createQuery());
        bind(r22, r3);     // Catch: Throwable -> L8
        r22.step();     // Catch: Throwable -> L8
        AutoCloseableKt.closeFinally(r22, null);
        return;
    L8:
        th = move-exception;
        throw th;     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        AutoCloseableKt.closeFinally(r22, th);
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void insert(SQLiteConnection r2, T[] r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        SQLiteStatement r22 = r2.prepare(createQuery());
        Iterator r32 = ArrayIteratorKt.iterator(r3);     // Catch: Throwable -> L11
    L7:
        if (r32.hasNext() == false) goto L13;
        Object r0 = r32.next();     // Catch: Throwable -> L11
        if (r0 == null) goto L7;
        bind(r22, r0);     // Catch: Throwable -> L11
        r22.step();     // Catch: Throwable -> L11
        r22.reset();     // Catch: Throwable -> L11
        goto L7
    L13:
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L11
        AutoCloseableKt.closeFinally(r22, null);
        return;
    L11:
        th = move-exception;
        throw th;     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        AutoCloseableKt.closeFinally(r22, th);
        throw th;
    }

    public final long insertAndReturnId(SQLiteConnection r2, T r3) {
        if (r3 != null) goto L5;
        return -1;
    L5:
        SQLiteStatement r0 = r2.prepare(createQuery());
        bind(r0, r3);     // Catch: Throwable -> L9
        r0.step();     // Catch: Throwable -> L9
        AutoCloseableKt.closeFinally(r0, null);
        return SQLiteConnectionUtil.getLastInsertedRowId(r2);
    L9:
        th = move-exception;
        throw th;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        AutoCloseableKt.closeFinally(r0, th);
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long[] insertAndReturnIdsArray(SQLiteConnection r7, Collection<? extends T> r8) {
        int r0 = 0;
        if (r8 == null) goto L5;
        SQLiteStatement r1 = r7.prepare(createQuery());
        int r2 = r8.size();     // Catch: Throwable -> L12
        long[] r3 = new long[r2];     // Catch: Throwable -> L12
    L8:
        if (r0 >= r2) goto L17;
        Object r4 = CollectionsKt.elementAt(r8, r0);     // Catch: Throwable -> L12
        if (r4 == null) goto L14;
        bind(r1, r4);     // Catch: Throwable -> L12
        r1.step();     // Catch: Throwable -> L12
        r1.reset();     // Catch: Throwable -> L12
        long r42 = SQLiteConnectionUtil.getLastInsertedRowId(r7);     // Catch: Throwable -> L12
    L15:
        r3[r0] = r42;     // Catch: Throwable -> L12
        r0 = r0 + 1;
        goto L8
    L14:
        r42 = -1;
        goto L15
    L17:
        AutoCloseableKt.closeFinally(r1, null);
        return r3;
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    L5:
        return new long[0];
    }

    public final long[] insertAndReturnIdsArray(SQLiteConnection r7, T[] r8) {
        int r0 = 0;
        if (r8 == null) goto L5;
        SQLiteStatement r1 = r7.prepare(createQuery());
        int r2 = r8.length;     // Catch: Throwable -> L12
        long[] r3 = new long[r2];     // Catch: Throwable -> L12
    L8:
        if (r0 >= r2) goto L17;
        T r4 = r8[r0];     // Catch: Throwable -> L12
        if (r4 == null) goto L14;
        bind(r1, r4);     // Catch: Throwable -> L12
        r1.step();     // Catch: Throwable -> L12
        r1.reset();     // Catch: Throwable -> L12
        long r42 = SQLiteConnectionUtil.getLastInsertedRowId(r7);     // Catch: Throwable -> L12
    L15:
        r3[r0] = r42;     // Catch: Throwable -> L12
        r0 = r0 + 1;
        goto L8
    L14:
        r42 = -1;
        goto L15
    L17:
        AutoCloseableKt.closeFinally(r1, null);
        return r3;
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    L5:
        return new long[0];
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Long[] insertAndReturnIdsArrayBox(SQLiteConnection r7, Collection<? extends T> r8) {
        int r0 = 0;
        if (r8 == null) goto L5;
        SQLiteStatement r1 = r7.prepare(createQuery());
        int r2 = r8.size();     // Catch: Throwable -> L12
        Long[] r3 = new Long[r2];     // Catch: Throwable -> L12
    L8:
        if (r0 >= r2) goto L17;
        Object r4 = CollectionsKt.elementAt(r8, r0);     // Catch: Throwable -> L12
        if (r4 == null) goto L14;
        bind(r1, r4);     // Catch: Throwable -> L12
        r1.step();     // Catch: Throwable -> L12
        r1.reset();     // Catch: Throwable -> L12
        long r42 = SQLiteConnectionUtil.getLastInsertedRowId(r7);     // Catch: Throwable -> L12
    L15:
        r3[r0] = Long.valueOf(r42);     // Catch: Throwable -> L12
        r0 = r0 + 1;
        goto L8
    L14:
        r42 = -1;
        goto L15
    L17:
        AutoCloseableKt.closeFinally(r1, null);
        return r3;
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    L5:
        return new Long[0];
    }

    public final Long[] insertAndReturnIdsArrayBox(SQLiteConnection r7, T[] r8) {
        int r0 = 0;
        if (r8 == null) goto L5;
        SQLiteStatement r1 = r7.prepare(createQuery());
        int r2 = r8.length;     // Catch: Throwable -> L12
        Long[] r3 = new Long[r2];     // Catch: Throwable -> L12
    L8:
        if (r0 >= r2) goto L17;
        T r4 = r8[r0];     // Catch: Throwable -> L12
        if (r4 == null) goto L14;
        bind(r1, r4);     // Catch: Throwable -> L12
        r1.step();     // Catch: Throwable -> L12
        r1.reset();     // Catch: Throwable -> L12
        long r42 = SQLiteConnectionUtil.getLastInsertedRowId(r7);     // Catch: Throwable -> L12
    L15:
        r3[r0] = Long.valueOf(r42);     // Catch: Throwable -> L12
        r0 = r0 + 1;
        goto L8
    L14:
        r42 = -1;
        goto L15
    L17:
        AutoCloseableKt.closeFinally(r1, null);
        return r3;
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    L5:
        return new Long[0];
    }

    public final List<Long> insertAndReturnIdsList(SQLiteConnection r5, Collection<? extends T> r6) {
        if (r6 == null) goto L4;
        List r0 = CollectionsKt.createListBuilder();
        SQLiteStatement r1 = r5.prepare(createQuery());
        Iterator<T> r62 = r6.iterator();     // Catch: Throwable -> L12
    L8:
        if (r62.hasNext() == false) goto L15;
        T r2 = r62.next();     // Catch: Throwable -> L12
        if (r2 != null) goto L11;
        r0.add(-1L);     // Catch: Throwable -> L12
        goto L8
    L11:
        bind(r1, r2);     // Catch: Throwable -> L12
        r1.step();     // Catch: Throwable -> L12
        r1.reset();     // Catch: Throwable -> L12
        r0.add(Long.valueOf(SQLiteConnectionUtil.getLastInsertedRowId(r5)));     // Catch: Throwable -> L12
        goto L8
    L15:
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L12
        AutoCloseableKt.closeFinally(r1, null);
        return CollectionsKt.build(r0);
    L12:
        th = move-exception;
        throw th;     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    L4:
        return CollectionsKt.emptyList();
    }

    public final List<Long> insertAndReturnIdsList(SQLiteConnection r7, T[] r8) {
        if (r8 == null) goto L4;
        List r0 = CollectionsKt.createListBuilder();
        SQLiteStatement r1 = r7.prepare(createQuery());
        int r2 = r8.length;     // Catch: Throwable -> L11
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L15;
        T r4 = r8[r3];     // Catch: Throwable -> L11
        if (r4 == null) goto L13;
        bind(r1, r4);     // Catch: Throwable -> L11
        r1.step();     // Catch: Throwable -> L11
        r1.reset();     // Catch: Throwable -> L11
        r0.add(Long.valueOf(SQLiteConnectionUtil.getLastInsertedRowId(r7)));     // Catch: Throwable -> L11
    L14:
        r3 = r3 + 1;     // Catch: Throwable -> L11
        goto L7
    L13:
        r0.add(-1L);     // Catch: Throwable -> L11
        goto L14
    L15:
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L11
        AutoCloseableKt.closeFinally(r1, null);
        return CollectionsKt.build(r0);
    L11:
        th = move-exception;
        throw th;     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    L4:
        return CollectionsKt.emptyList();
    }
}
