package kotlin.enums;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.Enum;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.8")
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00060\u0005j\u0002`\u00062\u00060\u0007j\u0002`\bB\u0017\bF\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u000fH\u0096\u0082\u0004¢\u0006\u0002\u0010\u0014J\u0017\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u001aJ\n\u0010\u001c\u001a\u00020\u001dH\u0082\u0080\u0004J\u001b\u0010\u001e\u001a\u00020\u001f2\n\u0010 \u001a\u00060!j\u0002`\"H\u0082\u0080\u0004¢\u0006\u0002\u0010#R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\rR\u0015\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006$"}, d2 = {"Lkotlin/enums/EnumEntriesList;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/enums/EnumEntries;", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "entries", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "([Ljava/lang/Enum;)V", "[Ljava/lang/Enum;", "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "get", "index", "(I)Ljava/lang/Enum;", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Enum;)Z", "indexOf", "(Ljava/lang/Enum;)I", "lastIndexOf", "writeReplace", _UrlKt.FRAGMENT_ENCODE_SET, "readObject", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/ObjectInputStream;", "Lkotlin/internal/ReadObjectParameterType;", "(Ljava/io/ObjectInputStream;)V", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
final class EnumEntriesList<T extends Enum<T>> extends AbstractList<T> implements EnumEntries<T>, RandomAccess, Serializable {
    private final T[] entries;

    public EnumEntriesList(T[] r1) {
        this.entries = r1;
    }

    private final void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization is supported via proxy only");
    }

    private final Object writeReplace() {
        return new EnumEntriesSerializationProxy(this.entries);
    }

    public boolean contains(T r2) {
        if (((Enum) ArraysKt.getOrNull(this.entries, r2.ordinal())) != r2) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object r2) {
        if ((r2 instanceof Enum) == true) goto L7;
        return false;
    L7:
        return contains((Enum) r2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public T get(int r3) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, this.entries.length);
        return this.entries[r3];
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int r1) {
        return get(r1);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.entries.length;
    }

    public int indexOf(T r2) {
        int r0 = r2.ordinal();
        if (((Enum) ArraysKt.getOrNull(this.entries, r0)) != r2) goto L5;
        return r0;
    L5:
        return -1;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object r2) {
        if ((r2 instanceof Enum) == true) goto L7;
        return -1;
    L7:
        return indexOf((Enum) r2);
    }

    public int lastIndexOf(T r1) {
        return indexOf(r1);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object r2) {
        if ((r2 instanceof Enum) == true) goto L7;
        return -1;
    L7:
        return lastIndexOf((Enum) r2);
    }
}
