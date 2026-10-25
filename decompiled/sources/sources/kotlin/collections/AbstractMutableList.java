package kotlin.collections;

import java.util.List;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.markers.KMutableList;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.1")
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\t\bD¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H§\u0082\b¢\u0006\u0002\u0010\nJ\u0017\u0010\u000b\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\bH§\u0080\b¢\u0006\u0002\u0010\fJ\u001f\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H¦\u0080\u0004¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Lkotlin/collections/AbstractMutableList;", "E", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/AbstractList;", "<init>", "()V", "set", "index", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(ILjava/lang/Object;)Ljava/lang/Object;", "removeAt", "(I)Ljava/lang/Object;", "add", _UrlKt.FRAGMENT_ENCODE_SET, "(ILjava/lang/Object;)V", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AbstractMutableList<E> extends java.util.AbstractList<E> implements List<E>, KMutableList {
    public AbstractMutableList() {
    }

    @Override // java.util.AbstractList, java.util.List
    public abstract void add(int r1, E r2);

    public abstract int getSize();

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ E remove(int r1) {
        return removeAt(r1);
    }

    @IgnorableReturnValue
    public abstract E removeAt(int r1);

    @Override // java.util.AbstractList, java.util.List
    @IgnorableReturnValue
    public abstract E set(int r1, E r2);

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
