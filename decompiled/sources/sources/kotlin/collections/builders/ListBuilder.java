package kotlin.collections.builders;

import androidx.collection.ArraySet$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InvalidObjectException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0000\n\u0002\b\u000e\n\u0002\u0010)\n\u0000\n\u0002\u0010+\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0000\u0018\u0000 Q*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\u00060\u0006j\u0002`\u0007:\u0003QRSB\u0013\bF\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0086\u0080\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0082\u0080\u0004J\n\u0010\u0019\u001a\u00020\u0011H\u0096\u0080\u0004J\u0017\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\tH\u0096\u0082\u0004¢\u0006\u0002\u0010\u001cJ\u001f\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00028\u0000H\u0096\u0082\b¢\u0006\u0002\u0010\u001fJ\u0017\u0010 \u001a\u00020\t2\u0006\u0010\u001e\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010!J\u0017\u0010\"\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010!J\u0010\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$H\u0096\u0082\u0004J\u0010\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000&H\u0096\u0080\u0004J\u0018\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000&2\u0006\u0010\u001b\u001a\u00020\tH\u0096\u0080\u0004J\u0017\u0010'\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0002\u0010(J\u001f\u0010'\u001a\u00020)2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010*J\u0018\u0010+\u001a\u00020\u00112\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0096\u0080\bJ \u0010+\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\t2\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0096\u0080\bJ\n\u0010.\u001a\u00020)H\u0096\u0080\u0004J\u0017\u0010/\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\tH\u0097\u0080\b¢\u0006\u0002\u0010\u001cJ\u0017\u00100\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0002\u0010(J\u0018\u00101\u001a\u00020\u00112\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0096\u0080\bJ\u0018\u00102\u001a\u00020\u00112\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000-H\u0096\u0080\bJ \u00103\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u00104\u001a\u00020\t2\u0006\u00105\u001a\u00020\tH\u0096\u0080\u0004J'\u00106\u001a\b\u0012\u0004\u0012\u0002H70\r\"\u0004\b\u0001\u001072\f\u00108\u001a\b\u0012\u0004\u0012\u0002H70\rH\u0016¢\u0006\u0002\u00109J\u0015\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\rH\u0016¢\u0006\u0002\u0010:J\u0014\u0010;\u001a\u00020\u00112\b\u0010<\u001a\u0004\u0018\u00010\u0015H\u0096\u0082\u0004J\n\u0010=\u001a\u00020\tH\u0096\u0080\u0004J\n\u0010>\u001a\u00020?H\u0096\u0080\u0004J\n\u0010@\u001a\u00020)H\u0082\u0080\u0004J\n\u0010A\u001a\u00020)H\u0082\u0080\u0004J\u0012\u0010B\u001a\u00020)2\u0006\u0010C\u001a\u00020\tH\u0082\u0080\u0004J\u0012\u0010D\u001a\u00020)2\u0006\u0010E\u001a\u00020\tH\u0082\u0080\u0004J\u0016\u0010F\u001a\u00020\u00112\n\u0010<\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0082\u0080\u0004J\u001a\u0010G\u001a\u00020)2\u0006\u0010H\u001a\u00020\t2\u0006\u0010C\u001a\u00020\tH\u0082\u0080\u0004J\u001f\u0010I\u001a\u00020)2\u0006\u0010H\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00028\u0000H\u0082\u0080\u0004¢\u0006\u0002\u0010*J(\u0010J\u001a\u00020)2\u0006\u0010H\u001a\u00020\t2\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000-2\u0006\u0010C\u001a\u00020\tH\u0082\u0080\u0004J\u0017\u0010K\u001a\u00028\u00002\u0006\u0010H\u001a\u00020\tH\u0082\u0080\u0004¢\u0006\u0002\u0010\u001cJ\u001a\u0010L\u001a\u00020)2\u0006\u0010M\u001a\u00020\t2\u0006\u0010N\u001a\u00020\tH\u0082\u0080\u0004J0\u0010O\u001a\u00020\t2\u0006\u0010M\u001a\u00020\t2\u0006\u0010N\u001a\u00020\t2\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000-2\u0006\u0010P\u001a\u00020\u0011H\u0082\u0080\u0004R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\u000eR\u000f\u0010\u000f\u001a\u00020\tX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0010\u001a\u00020\u0011X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0016\u001a\u00020\t8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006T"}, d2 = {"Lkotlin/collections/builders/ListBuilder;", "E", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "Lkotlin/collections/AbstractMutableList;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "initialCapacity", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "backing", _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/Object;", "length", "isReadOnly", _UrlKt.FRAGMENT_ENCODE_SET, "build", _UrlKt.FRAGMENT_ENCODE_SET, "writeReplace", _UrlKt.FRAGMENT_ENCODE_SET, "size", "getSize", "()I", "isEmpty", "get", "index", "(I)Ljava/lang/Object;", "set", "element", "(ILjava/lang/Object;)Ljava/lang/Object;", "indexOf", "(Ljava/lang/Object;)I", "lastIndexOf", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "listIterator", _UrlKt.FRAGMENT_ENCODE_SET, "add", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "(ILjava/lang/Object;)V", "addAll", "elements", _UrlKt.FRAGMENT_ENCODE_SET, "clear", "removeAt", "remove", "removeAll", "retainAll", "subList", "fromIndex", "toIndex", "toArray", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "()[Ljava/lang/Object;", "equals", "other", "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "registerModification", "checkIsMutable", "ensureExtraCapacity", "n", "ensureCapacityInternal", "minCapacity", "contentEquals", "insertAtInternal", "i", "addAtInternal", "addAllInternal", "removeAtInternal", "removeRangeInternal", "rangeOffset", "rangeLength", "retainOrRemoveAllInternal", "retain", "Companion", "Itr", "BuilderSubList", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,724:1\n1#2:725\n*E\n"})
/* loaded from: classes.dex */
public final class ListBuilder<E> extends AbstractMutableList<E> implements List<E>, RandomAccess, Serializable, KMutableList {
    private static final Companion Companion = null;
    private static final ListBuilder Empty = null;
    private E[] backing;
    private boolean isReadOnly;
    private int length;

    @Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010)\n\u0000\n\u0002\u0010+\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\f\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\u00060\u0006j\u0002`\u0007:\u0001TBE\bF\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0000\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\n\u0010\u0013\u001a\u00020\u0014H\u0082\u0080\u0004J\u0012\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0082\u0080\u0004J\n\u0010\u001c\u001a\u00020\u001dH\u0096\u0080\u0004J\u0017\u0010\u001e\u001a\u00028\u00012\u0006\u0010\u001f\u001a\u00020\u000bH\u0096\u0082\u0004¢\u0006\u0002\u0010 J\u001f\u0010!\u001a\u00028\u00012\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00028\u0001H\u0096\u0082\b¢\u0006\u0002\u0010#J\u0017\u0010$\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00028\u0001H\u0096\u0080\u0004¢\u0006\u0002\u0010%J\u0017\u0010&\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00028\u0001H\u0096\u0080\u0004¢\u0006\u0002\u0010%J\u0010\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00010(H\u0096\u0082\u0004J\u0010\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00010*H\u0096\u0080\u0004J\u0018\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00010*2\u0006\u0010\u001f\u001a\u00020\u000bH\u0096\u0080\u0004J\u0017\u0010+\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00028\u0001H\u0096\u0080\b¢\u0006\u0002\u0010,J\u001f\u0010+\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00028\u0001H\u0096\u0080\u0004¢\u0006\u0002\u0010-J\u0018\u0010.\u001a\u00020\u001d2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u000100H\u0096\u0080\bJ \u0010.\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u000100H\u0096\u0080\bJ\n\u00101\u001a\u00020\u0016H\u0096\u0080\u0004J\u0017\u00102\u001a\u00028\u00012\u0006\u0010\u001f\u001a\u00020\u000bH\u0097\u0080\b¢\u0006\u0002\u0010 J\u0017\u00103\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00028\u0001H\u0096\u0080\b¢\u0006\u0002\u0010,J\u0018\u00104\u001a\u00020\u001d2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u000100H\u0096\u0080\bJ\u0018\u00105\u001a\u00020\u001d2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u000100H\u0096\u0080\bJ \u00106\u001a\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u00107\u001a\u00020\u000b2\u0006\u00108\u001a\u00020\u000bH\u0096\u0080\u0004J'\u00109\u001a\b\u0012\u0004\u0012\u0002H:0\t\"\u0004\b\u0002\u0010:2\f\u0010;\u001a\b\u0012\u0004\u0012\u0002H:0\tH\u0016¢\u0006\u0002\u0010<J\u0015\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\tH\u0016¢\u0006\u0002\u0010=J\u0014\u0010>\u001a\u00020\u001d2\b\u0010?\u001a\u0004\u0018\u00010\u0014H\u0096\u0082\u0004J\n\u0010@\u001a\u00020\u000bH\u0096\u0080\u0004J\n\u0010A\u001a\u00020BH\u0096\u0080\u0004J\n\u0010C\u001a\u00020\u0016H\u0082\u0080\u0004J\n\u0010D\u001a\u00020\u0016H\u0082\u0080\u0004J\n\u0010E\u001a\u00020\u0016H\u0082\u0080\u0004J\u0016\u0010H\u001a\u00020\u001d2\n\u0010?\u001a\u0006\u0012\u0002\b\u00030IH\u0082\u0080\u0004J\u001f\u0010J\u001a\u00020\u00162\u0006\u0010K\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00028\u0001H\u0082\u0080\u0004¢\u0006\u0002\u0010-J(\u0010L\u001a\u00020\u00162\u0006\u0010K\u001a\u00020\u000b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u0001002\u0006\u0010M\u001a\u00020\u000bH\u0082\u0080\u0004J\u0017\u0010N\u001a\u00028\u00012\u0006\u0010K\u001a\u00020\u000bH\u0082\u0080\u0004¢\u0006\u0002\u0010 J\u001a\u0010O\u001a\u00020\u00162\u0006\u0010P\u001a\u00020\u000b2\u0006\u0010Q\u001a\u00020\u000bH\u0082\u0080\u0004J0\u0010R\u001a\u00020\u000b2\u0006\u0010P\u001a\u00020\u000b2\u0006\u0010Q\u001a\u00020\u000b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u0001002\u0006\u0010S\u001a\u00020\u001dH\u0082\u0080\u0004R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\tX\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\u0012R\u000f\u0010\n\u001a\u00020\u000bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\u000bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0000X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u000fX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0019\u001a\u00020\u000b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0015\u0010F\u001a\u00020\u001d8BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\bF\u0010G¨\u0006U"}, d2 = {"Lkotlin/collections/builders/ListBuilder$BuilderSubList;", "E", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "Lkotlin/collections/AbstractMutableList;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "backing", _UrlKt.FRAGMENT_ENCODE_SET, "offset", _UrlKt.FRAGMENT_ENCODE_SET, "length", "parent", "root", "Lkotlin/collections/builders/ListBuilder;", "<init>", "([Ljava/lang/Object;IILkotlin/collections/builders/ListBuilder$BuilderSubList;Lkotlin/collections/builders/ListBuilder;)V", "[Ljava/lang/Object;", "writeReplace", _UrlKt.FRAGMENT_ENCODE_SET, "readObject", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/ObjectInputStream;", "size", "getSize", "()I", "isEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "get", "index", "(I)Ljava/lang/Object;", "set", "element", "(ILjava/lang/Object;)Ljava/lang/Object;", "indexOf", "(Ljava/lang/Object;)I", "lastIndexOf", "iterator", _UrlKt.FRAGMENT_ENCODE_SET, "listIterator", _UrlKt.FRAGMENT_ENCODE_SET, "add", "(Ljava/lang/Object;)Z", "(ILjava/lang/Object;)V", "addAll", "elements", _UrlKt.FRAGMENT_ENCODE_SET, "clear", "removeAt", "remove", "removeAll", "retainAll", "subList", "fromIndex", "toIndex", "toArray", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "()[Ljava/lang/Object;", "equals", "other", "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "registerModification", "checkForComodification", "checkIsMutable", "isReadOnly", "()Z", "contentEquals", _UrlKt.FRAGMENT_ENCODE_SET, "addAtInternal", "i", "addAllInternal", "n", "removeAtInternal", "removeRangeInternal", "rangeOffset", "rangeLength", "retainOrRemoveAllInternal", "retain", "Itr", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class BuilderSubList<E> extends AbstractMutableList<E> implements List<E>, RandomAccess, Serializable, KMutableList {
        private E[] backing;
        private int length;
        private final int offset;
        private final BuilderSubList<E> parent;
        private final ListBuilder<E> root;

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004J\n\u0010\r\u001a\u00020\fH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u0006H\u0096\u0080\u0004J\n\u0010\u000f\u001a\u00020\u0006H\u0096\u0080\u0004J\u000f\u0010\u0010\u001a\u00028\u0002H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0011J\u000f\u0010\u0012\u001a\u00028\u0002H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0002H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0002H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0016J\n\u0010\u0018\u001a\u00020\u0014H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u0014H\u0082\u0080\u0004R\u0015\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0005\u001a\u00020\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lkotlin/collections/builders/ListBuilder$BuilderSubList$Itr;", "E", _UrlKt.FRAGMENT_ENCODE_SET, "list", "Lkotlin/collections/builders/ListBuilder$BuilderSubList;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/collections/builders/ListBuilder$BuilderSubList;I)V", "lastIndex", "expectedModCount", "hasPrevious", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", "previousIndex", "nextIndex", "previous", "()Ljava/lang/Object;", "next", "set", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Object;)V", "add", "remove", "checkForComodification", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder$BuilderSubList$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,724:1\n1#2:725\n*E\n"})
        public static final class Itr<E> implements ListIterator<E>, KMutableListIterator {
            private int expectedModCount;
            private int index;
            private int lastIndex;
            private final BuilderSubList<E> list;

            public Itr(BuilderSubList<E> r1, int r2) {
                this.list = r1;
                this.index = r2;
                this.lastIndex = -1;
                this.expectedModCount = BuilderSubList.access$getModCount$p$s1462993667(r1);
            }

            private final void checkForComodification() {
                if (ListBuilder.m2489access$getModCount$p$s2084097795(BuilderSubList.access$getRoot$p(this.list)) != this.expectedModCount) goto L5;
                return;
            L5:
                ArraySet$$ExternalSyntheticBUOutline0.m();
            }

            @Override // java.util.ListIterator
            public void add(E r4) {
                checkForComodification();
                BuilderSubList<E> r0 = this.list;
                int r1 = this.index;
                this.index = r1 + 1;
                r0.add(r1, r4);
                this.lastIndex = -1;
                this.expectedModCount = BuilderSubList.access$getModCount$p$s1462993667(this.list);
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public boolean hasNext() {
                if (this.index >= BuilderSubList.access$getLength$p(this.list)) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                if (this.index <= 0) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public E next() {
                checkForComodification();
                if (this.index >= BuilderSubList.access$getLength$p(this.list)) goto L6;
                int r0 = this.index;
                this.index = r0 + 1;
                this.lastIndex = r0;
                return (E) BuilderSubList.access$getBacking$p(this.list)[BuilderSubList.access$getOffset$p(this.list) + this.lastIndex];
            L6:
                Utils$$ExternalSyntheticBUOutline0.m();
                return null;
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return this.index;
            }

            @Override // java.util.ListIterator
            public E previous() {
                checkForComodification();
                int r0 = this.index;
                if (r0 <= 0) goto L6;
                int r02 = r0 - 1;
                this.index = r02;
                this.lastIndex = r02;
                return (E) BuilderSubList.access$getBacking$p(this.list)[BuilderSubList.access$getOffset$p(this.list) + this.lastIndex];
            L6:
                Utils$$ExternalSyntheticBUOutline0.m();
                return null;
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return this.index - 1;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public void remove() {
                checkForComodification();
                int r0 = this.lastIndex;
                if (r0 == (-1)) goto L6;
                this.list.removeAt(r0);
                this.index = this.lastIndex;
                this.lastIndex = -1;
                this.expectedModCount = BuilderSubList.access$getModCount$p$s1462993667(this.list);
                return;
            L6:
                Segment$$ExternalSyntheticBUOutline1.m("Call next() or previous() before removing element from the iterator.");
            }

            @Override // java.util.ListIterator
            public void set(E r3) {
                checkForComodification();
                int r0 = this.lastIndex;
                if (r0 == (-1)) goto L6;
                this.list.set(r0, r3);
                return;
            L6:
                Segment$$ExternalSyntheticBUOutline1.m("Call next() or previous() before replacing element from the iterator.");
            }
        }

        public BuilderSubList(E[] r1, int r2, int r3, BuilderSubList<E> r4, ListBuilder<E> r5) {
            this.backing = r1;
            this.offset = r2;
            this.length = r3;
            this.parent = r4;
            this.root = r5;
            ((AbstractList) this).modCount = ListBuilder.m2489access$getModCount$p$s2084097795(r5);
        }

        public static final /* synthetic */ Object[] access$getBacking$p(BuilderSubList r0) {
            return r0.backing;
        }

        public static final /* synthetic */ int access$getLength$p(BuilderSubList r0) {
            return r0.length;
        }

        public static final /* synthetic */ int access$getModCount$p$s1462993667(BuilderSubList r0) {
            return ((AbstractList) r0).modCount;
        }

        public static final /* synthetic */ int access$getOffset$p(BuilderSubList r0) {
            return r0.offset;
        }

        public static final /* synthetic */ ListBuilder access$getRoot$p(BuilderSubList r0) {
            return r0.root;
        }

        private final void addAllInternal(int r2, Collection<? extends E> r3, int r4) {
            registerModification();
            BuilderSubList<E> r0 = this.parent;
            if (r0 == null) goto L5;
            r0.addAllInternal(r2, r3, r4);
        L6:
            this.backing = (E[]) ListBuilder.access$getBacking$p(this.root);
            this.length += r4;
            return;
        L5:
            ListBuilder.access$addAllInternal(this.root, r2, r3, r4);
            goto L6
        }

        private final void addAtInternal(int r2, E r3) {
            registerModification();
            BuilderSubList<E> r0 = this.parent;
            if (r0 == null) goto L5;
            r0.addAtInternal(r2, r3);
        L6:
            this.backing = (E[]) ListBuilder.access$getBacking$p(this.root);
            this.length++;
            return;
        L5:
            ListBuilder.access$addAtInternal(this.root, r2, r3);
            goto L6
        }

        private final void checkForComodification() {
            if (ListBuilder.m2489access$getModCount$p$s2084097795(this.root) != ((AbstractList) this).modCount) goto L5;
            return;
        L5:
            ArraySet$$ExternalSyntheticBUOutline0.m();
        }

        private final void checkIsMutable() {
            if (isReadOnly() == true) goto L5;
            return;
        L5:
            Type$$ExternalSyntheticBUOutline0.m();
        }

        private final boolean contentEquals(List<?> r3) {
            return ListBuilderKt.access$subarrayContentEquals(this.backing, this.offset, this.length, r3);
        }

        private final boolean isReadOnly() {
            return ListBuilder.access$isReadOnly$p(this.root);
        }

        private final void readObject(ObjectInputStream r1) {
            throw new InvalidObjectException("Deserialization is supported via proxy only");
        }

        private final void registerModification() {
            ((AbstractList) this).modCount++;
        }

        private final E removeAtInternal(int r2) {
            registerModification();
            BuilderSubList<E> r0 = this.parent;
            if (r0 == null) goto L5;
            E r22 = r0.removeAtInternal(r2);
        L6:
            this.length--;
            return r22;
        L5:
            r22 = (E) ListBuilder.access$removeAtInternal(this.root, r2);
            goto L6
        }

        private final void removeRangeInternal(int r2, int r3) {
            if (r3 <= 0) goto L4;
            registerModification();
        L4:
            BuilderSubList<E> r0 = this.parent;
            if (r0 == null) goto L7;
            r0.removeRangeInternal(r2, r3);
        L8:
            this.length -= r3;
            return;
        L7:
            ListBuilder.access$removeRangeInternal(this.root, r2, r3);
            goto L8
        }

        private final int retainOrRemoveAllInternal(int r2, int r3, Collection<? extends E> r4, boolean r5) {
            BuilderSubList<E> r0 = this.parent;
            if (r0 == null) goto L5;
            int r22 = r0.retainOrRemoveAllInternal(r2, r3, r4, r5);
        L6:
            if (r22 <= 0) goto L8;
            registerModification();
        L8:
            this.length -= r22;
            return r22;
        L5:
            r22 = ListBuilder.access$retainOrRemoveAllInternal(this.root, r2, r3, r4, r5);
            goto L6
        }

        private final Object writeReplace() {
            if (isReadOnly() == false) goto L7;
            return new SerializedCollection(this, 0);
        L7:
            throw new NotSerializableException("The list cannot be serialized while it is being built.");
        }

        @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
        public void add(int r3, E r4) {
            checkIsMutable();
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r3, this.length);
            addAtInternal(this.offset + r3, r4);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(E r3) {
            checkIsMutable();
            checkForComodification();
            addAtInternal(this.offset + this.length, r3);
            return true;
        }

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int r3, Collection<? extends E> r4) {
            checkIsMutable();
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r3, this.length);
            int r0 = r4.size();
            addAllInternal(this.offset + r3, r4, r0);
            if (r0 <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection<? extends E> r4) {
            checkIsMutable();
            checkForComodification();
            int r0 = r4.size();
            addAllInternal(this.offset + this.length, r4, r0);
            if (r0 <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            checkIsMutable();
            checkForComodification();
            removeRangeInternal(this.offset, this.length);
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(Object r2) {
            checkForComodification();
            if (r2 != this) goto L5;
            return true;
        L5:
            if ((r2 instanceof List) == true) goto L7;
            return false;
        L7:
            if (contentEquals((List) r2) == true) goto L14;
            return false;
        L14:
            return true;
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int r3) {
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, this.length);
            return this.backing[this.offset + r3];
        }

        @Override // kotlin.collections.AbstractMutableList
        public int getSize() {
            checkForComodification();
            return this.length;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            checkForComodification();
            return ListBuilderKt.access$subarrayContentHashCode(this.backing, this.offset, this.length);
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(Object r4) {
            checkForComodification();
            int r0 = 0;
        L4:
            if (r0 >= this.length) goto L9;
            if (Intrinsics.areEqual(this.backing[this.offset + r0], r4) == true) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return r0;
        L9:
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            checkForComodification();
            if (this.length != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<E> iterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(Object r4) {
            checkForComodification();
            int r0 = this.length - 1;
        L3:
            if (r0 < 0) goto L8;
            if (Intrinsics.areEqual(this.backing[this.offset + r0], r4) == true) goto L6;
            r0 = r0 - 1;
            goto L3
        L6:
            return r0;
        L8:
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<E> listIterator(int r3) {
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r3, this.length);
            return new Itr(this, r3);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean remove(Object r1) {
            checkIsMutable();
            checkForComodification();
            int r12 = indexOf(r1);
            if (r12 < 0) goto L5;
            removeAt(r12);
        L5:
            if (r12 < 0) goto L8;
            return true;
        L8:
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean removeAll(Collection<?> r4) {
            checkIsMutable();
            checkForComodification();
            if (retainOrRemoveAllInternal(this.offset, this.length, r4, false) <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // kotlin.collections.AbstractMutableList
        @IgnorableReturnValue
        public E removeAt(int r3) {
            checkIsMutable();
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, this.length);
            return removeAtInternal(this.offset + r3);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean retainAll(Collection<?> r4) {
            checkIsMutable();
            checkForComodification();
            if (retainOrRemoveAllInternal(this.offset, this.length, r4, true) <= 0) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
        public E set(int r3, E r4) {
            checkIsMutable();
            checkForComodification();
            kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, this.length);
            E[] r0 = this.backing;
            int r2 = this.offset;
            E r1 = r0[r2 + r3];
            r0[r2 + r3] = r4;
            return r1;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<E> subList(int r9, int r10) {
            kotlin.collections.AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r9, r10, this.length);
            return new BuilderSubList(this.backing, this.offset + r9, r10 - r9, this, this.root);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public Object[] toArray() {
            checkForComodification();
            E[] r0 = this.backing;
            int r1 = this.offset;
            return ArraysKt.copyOfRange(r0, r1, this.length + r1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public <T> T[] toArray(T[] r5) {
            checkForComodification();
            int r0 = r5.length;
            int r1 = this.length;
            E[] r2 = this.backing;
            if (r0 >= r1) goto L6;
            int r4 = this.offset;
            return (T[]) Arrays.copyOfRange(r2, r4, r1 + r4, r5.getClass());
        L6:
            int r02 = this.offset;
            ArraysKt.copyInto(r2, r5, 0, r02, r1 + r02);
            return (T[]) CollectionsKt.terminateCollectionToArray(this.length, r5);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            checkForComodification();
            return ListBuilderKt.access$subarrayContentToString(this.backing, this.offset, this.length, this);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlin/collections/builders/ListBuilder$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Empty", "Lkotlin/collections/builders/ListBuilder;", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001f\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004J\n\u0010\r\u001a\u00020\fH\u0096\u0082\u0004J\n\u0010\u000e\u001a\u00020\u0006H\u0096\u0080\u0004J\n\u0010\u000f\u001a\u00020\u0006H\u0096\u0080\u0004J\u000f\u0010\u0010\u001a\u00028\u0001H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0011J\u000f\u0010\u0012\u001a\u00028\u0001H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0001H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0016J\u0017\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0001H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0016J\n\u0010\u0018\u001a\u00020\u0014H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u0014H\u0082\u0080\u0004R\u0015\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0005\u001a\u00020\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lkotlin/collections/builders/ListBuilder$Itr;", "E", _UrlKt.FRAGMENT_ENCODE_SET, "list", "Lkotlin/collections/builders/ListBuilder;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/collections/builders/ListBuilder;I)V", "lastIndex", "expectedModCount", "hasPrevious", _UrlKt.FRAGMENT_ENCODE_SET, "hasNext", "previousIndex", "nextIndex", "previous", "()Ljava/lang/Object;", "next", "set", _UrlKt.FRAGMENT_ENCODE_SET, "element", "(Ljava/lang/Object;)V", "add", "remove", "checkForComodification", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/ListBuilder$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,724:1\n1#2:725\n*E\n"})
    public static final class Itr<E> implements ListIterator<E>, KMutableListIterator {
        private int expectedModCount;
        private int index;
        private int lastIndex;
        private final ListBuilder<E> list;

        public Itr(ListBuilder<E> r1, int r2) {
            this.list = r1;
            this.index = r2;
            this.lastIndex = -1;
            this.expectedModCount = ListBuilder.m2489access$getModCount$p$s2084097795(r1);
        }

        private final void checkForComodification() {
            if (ListBuilder.m2489access$getModCount$p$s2084097795(this.list) != this.expectedModCount) goto L5;
            return;
        L5:
            ArraySet$$ExternalSyntheticBUOutline0.m();
        }

        @Override // java.util.ListIterator
        public void add(E r4) {
            checkForComodification();
            ListBuilder<E> r0 = this.list;
            int r1 = this.index;
            this.index = r1 + 1;
            r0.add(r1, r4);
            this.lastIndex = -1;
            this.expectedModCount = ListBuilder.m2489access$getModCount$p$s2084097795(this.list);
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            if (this.index >= ListBuilder.access$getLength$p(this.list)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            if (this.index <= 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            checkForComodification();
            if (this.index >= ListBuilder.access$getLength$p(this.list)) goto L6;
            int r0 = this.index;
            this.index = r0 + 1;
            this.lastIndex = r0;
            return (E) ListBuilder.access$getBacking$p(this.list)[this.lastIndex];
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public E previous() {
            checkForComodification();
            int r0 = this.index;
            if (r0 <= 0) goto L6;
            int r02 = r0 - 1;
            this.index = r02;
            this.lastIndex = r02;
            return (E) ListBuilder.access$getBacking$p(this.list)[this.lastIndex];
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            checkForComodification();
            int r0 = this.lastIndex;
            if (r0 == (-1)) goto L6;
            this.list.removeAt(r0);
            this.index = this.lastIndex;
            this.lastIndex = -1;
            this.expectedModCount = ListBuilder.m2489access$getModCount$p$s2084097795(this.list);
            return;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("Call next() or previous() before removing element from the iterator.");
        }

        @Override // java.util.ListIterator
        public void set(E r3) {
            checkForComodification();
            int r0 = this.lastIndex;
            if (r0 == (-1)) goto L6;
            this.list.set(r0, r3);
            return;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("Call next() or previous() before replacing element from the iterator.");
        }
    }

    static {
        Companion = new Companion(null);
        ListBuilder r0 = new ListBuilder(0);
        r0.isReadOnly = true;
        Empty = r0;
    }

    public ListBuilder() {
        this(0, 1, null);
    }

    public ListBuilder(int r1) {
        this.backing = (E[]) ListBuilderKt.arrayOfUninitializedElements(r1);
    }

    public /* synthetic */ ListBuilder(int r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = 10;
    L5:
        this(r1);
    }

    public static final /* synthetic */ void access$addAllInternal(ListBuilder r0, int r1, Collection r2, int r3) {
        r0.addAllInternal(r1, r2, r3);
    }

    public static final /* synthetic */ void access$addAtInternal(ListBuilder r0, int r1, Object r2) {
        r0.addAtInternal(r1, r2);
    }

    public static final /* synthetic */ Object[] access$getBacking$p(ListBuilder r0) {
        return r0.backing;
    }

    public static final /* synthetic */ int access$getLength$p(ListBuilder r0) {
        return r0.length;
    }

    /* renamed from: access$getModCount$p$s-2084097795, reason: not valid java name */
    public static final /* synthetic */ int m2489access$getModCount$p$s2084097795(ListBuilder r0) {
        return ((AbstractList) r0).modCount;
    }

    public static final /* synthetic */ boolean access$isReadOnly$p(ListBuilder r0) {
        return r0.isReadOnly;
    }

    public static final /* synthetic */ Object access$removeAtInternal(ListBuilder r0, int r1) {
        return r0.removeAtInternal(r1);
    }

    public static final /* synthetic */ void access$removeRangeInternal(ListBuilder r0, int r1, int r2) {
        r0.removeRangeInternal(r1, r2);
    }

    public static final /* synthetic */ int access$retainOrRemoveAllInternal(ListBuilder r0, int r1, int r2, Collection r3, boolean r4) {
        return r0.retainOrRemoveAllInternal(r1, r2, r3, r4);
    }

    private final void addAllInternal(int r5, Collection<? extends E> r6, int r7) {
        registerModification();
        insertAtInternal(r5, r7);
        Iterator<? extends E> r62 = r6.iterator();
        int r0 = 0;
    L3:
        if (r0 >= r7) goto L5;
        this.backing[r5 + r0] = r62.next();
        r0 = r0 + 1;
        goto L3
    }

    private final void addAtInternal(int r2, E r3) {
        registerModification();
        insertAtInternal(r2, 1);
        this.backing[r2] = r3;
    }

    private final void checkIsMutable() {
        if (this.isReadOnly == true) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    private final boolean contentEquals(List<?> r3) {
        return ListBuilderKt.access$subarrayContentEquals(this.backing, 0, this.length, r3);
    }

    private final void ensureCapacityInternal(int r3) {
        if (r3 < 0) goto L8;
        E[] r0 = this.backing;
        if (r3 <= r0.length) goto L9;
        int r32 = kotlin.collections.AbstractList.Companion.newCapacity$kotlin_stdlib(r0.length, r3);
        this.backing = (E[]) ListBuilderKt.copyOfUninitializedElements(this.backing, r32);
        return;
    L9:
        return;
    L8:
        throw new OutOfMemoryError();
    }

    private final void ensureExtraCapacity(int r2) {
        ensureCapacityInternal(this.length + r2);
    }

    private final void insertAtInternal(int r4, int r5) {
        ensureExtraCapacity(r5);
        E[] r0 = this.backing;
        ArraysKt.copyInto(r0, r0, r4 + r5, r4, this.length);
        this.length += r5;
    }

    private final void registerModification() {
        ((AbstractList) this).modCount++;
    }

    private final E removeAtInternal(int r5) {
        registerModification();
        E[] r0 = this.backing;
        E r1 = r0[r5];
        ArraysKt.copyInto(r0, r0, r5, r5 + 1, this.length);
        ListBuilderKt.resetAt(this.backing, this.length - 1);
        this.length--;
        return r1;
    }

    private final void removeRangeInternal(int r4, int r5) {
        if (r5 <= 0) goto L4;
        registerModification();
    L4:
        E[] r0 = this.backing;
        ArraysKt.copyInto(r0, r0, r4, r4 + r5, this.length);
        E[] r42 = this.backing;
        int r02 = this.length;
        ListBuilderKt.resetRange(r42, r02 - r5, r02);
        this.length -= r5;
    }

    private final int retainOrRemoveAllInternal(int r6, int r7, Collection<? extends E> r8, boolean r9) {
        int r0 = 0;
        int r1 = 0;
    L3:
        E[] r2 = this.backing;
        if (r0 >= r7) goto L9;
        int r3 = r6 + r0;
        if (r8.contains(r2[r3]) == r9) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        E[] r22 = this.backing;
        r0 = r0 + 1;
        r22[r1 + r6] = r22[r3];
        r1 = r1 + 1;
        goto L3
    L9:
        int r82 = r7 - r1;
        ArraysKt.copyInto(r2, r2, r6 + r1, r7 + r6, this.length);
        E[] r62 = this.backing;
        int r72 = this.length;
        ListBuilderKt.resetRange(r62, r72 - r82, r72);
        if (r82 <= 0) goto L12;
        registerModification();
    L12:
        this.length -= r82;
        return r82;
    }

    private final Object writeReplace() {
        if (this.isReadOnly == false) goto L7;
        return new SerializedCollection(this, 0);
    L7:
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int r3, E r4) {
        checkIsMutable();
        kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r3, this.length);
        addAtInternal(r3, r4);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E r2) {
        checkIsMutable();
        addAtInternal(this.length, r2);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int r3, Collection<? extends E> r4) {
        checkIsMutable();
        kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r3, this.length);
        int r0 = r4.size();
        addAllInternal(r3, r4, r0);
        if (r0 <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> r3) {
        checkIsMutable();
        int r0 = r3.size();
        addAllInternal(this.length, r3, r0);
        if (r0 <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final List<E> build() {
        checkIsMutable();
        this.isReadOnly = true;
        if (this.length <= 0) goto L6;
        return this;
    L6:
        return Empty;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        checkIsMutable();
        removeRangeInternal(0, this.length);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object r2) {
        if (r2 != this) goto L4;
        return true;
    L4:
        if ((r2 instanceof List) == true) goto L6;
        return false;
    L6:
        if (contentEquals((List) r2) == true) goto L13;
        return false;
    L13:
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int r3) {
        kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, this.length);
        return this.backing[r3];
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.length;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        return ListBuilderKt.access$subarrayContentHashCode(this.backing, 0, this.length);
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.length) goto L9;
        if (Intrinsics.areEqual(this.backing[r0], r3) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        if (this.length != 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object r3) {
        int r0 = this.length - 1;
    L3:
        if (r0 < 0) goto L8;
        if (Intrinsics.areEqual(this.backing[r0], r3) == true) goto L6;
        r0 = r0 - 1;
        goto L3
    L6:
        return r0;
    L8:
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator(int r3) {
        kotlin.collections.AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r3, this.length);
        return new Itr(this, r3);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object r1) {
        checkIsMutable();
        int r12 = indexOf(r1);
        if (r12 < 0) goto L5;
        removeAt(r12);
    L5:
        if (r12 < 0) goto L8;
        return true;
    L8:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<?> r3) {
        checkIsMutable();
        if (retainOrRemoveAllInternal(0, this.length, r3, false) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // kotlin.collections.AbstractMutableList
    @IgnorableReturnValue
    public E removeAt(int r3) {
        checkIsMutable();
        kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, this.length);
        return removeAtInternal(r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<?> r4) {
        checkIsMutable();
        if (retainOrRemoveAllInternal(0, this.length, r4, true) <= 0) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public E set(int r3, E r4) {
        checkIsMutable();
        kotlin.collections.AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, this.length);
        E[] r2 = this.backing;
        E r0 = r2[r3];
        r2[r3] = r4;
        return r0;
    }

    @Override // java.util.AbstractList, java.util.List
    public List<E> subList(int r9, int r10) {
        kotlin.collections.AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r9, r10, this.length);
        return new BuilderSubList(this.backing, r9, r10 - r9, null, this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return ArraysKt.copyOfRange(this.backing, 0, this.length);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] r5) {
        int r0 = r5.length;
        int r1 = this.length;
        E[] r2 = this.backing;
        if (r0 < r1) goto L5;
        ArraysKt.copyInto(r2, r5, 0, 0, r1);
        return (T[]) CollectionsKt.terminateCollectionToArray(this.length, r5);
    L5:
        return (T[]) Arrays.copyOfRange(r2, 0, r1, r5.getClass());
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        return ListBuilderKt.access$subarrayContentToString(this.backing, 0, this.length, this);
    }
}
