package androidx.collection;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000e\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u0005¨\u0006\u000f"}, d2 = {"Landroidx/collection/MutableIntList;", "Landroidx/collection/IntList;", _UrlKt.FRAGMENT_ENCODE_SET, "initialCapacity", "<init>", "(I)V", "element", _UrlKt.FRAGMENT_ENCODE_SET, "add", "(I)Z", _UrlKt.FRAGMENT_ENCODE_SET, "clear", "()V", "capacity", "ensureCapacity", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIntList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntList.kt\nandroidx/collection/MutableIntList\n+ 2 IntList.kt\nandroidx/collection/IntList\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,968:1\n556#1:970\n363#2:969\n70#2:971\n237#2,6:974\n70#2:980\n70#2:981\n70#2:988\n13330#3,2:972\n1663#3,6:982\n*S KotlinDebug\n*F\n+ 1 IntList.kt\nandroidx/collection/MutableIntList\n*L\n692#1:970\n628#1:969\n749#1:971\n762#1:974,6\n776#1:980\n822#1:981\n839#1:988\n757#1:972,2\n824#1:982,6\n*E\n"})
/* loaded from: classes3.dex */
public final class MutableIntList extends IntList {
    public MutableIntList() {
        this(0, 1, null);
    }

    public MutableIntList(int r2) {
        super(r2, null);
    }

    public /* synthetic */ MutableIntList(int r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = 16;
    L5:
        this(r1);
    }

    public final boolean add(int r4) {
        ensureCapacity(this._size + 1);
        int[] r0 = this.content;
        int r2 = this._size;
        r0[r2] = r4;
        this._size = r2 + 1;
        return true;
    }

    public final void clear() {
        this._size = 0;
    }

    public final void ensureCapacity(int r3) {
        int[] r0 = this.content;
        if (r0.length >= r3) goto L6;
        this.content = Arrays.copyOf(r0, Math.max(r3, (r0.length * 3) / 2));
        return;
    }
}
