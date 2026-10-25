package kotlin.collections.builders;

import androidx.collection.ArraySet$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InvalidObjectException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.Type$$ExternalSyntheticBUOutline0;
import org.mvel2.asm.signature.SignatureVisitor;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010'\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0010&\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 \u0084\u0001*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u00060\u0004j\u0002`\u0005:\f\u0084\u0001\u0085\u0001\u0086\u0001\u0087\u0001\u0088\u0001\u0089\u0001BG\bB\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010B\t\bV¢\u0006\u0004\b\u000f\u0010\u0011B\u0011\bV\u0012\u0006\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0013J\u0016\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010&H\u0086\u0080\u0004J\n\u0010'\u001a\u00020(H\u0082\u0080\u0004J\u0012\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0082\u0080\u0004J\n\u0010-\u001a\u00020!H\u0096\u0080\u0004J\u0017\u0010.\u001a\u00020!2\u0006\u0010/\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u00100J\u0017\u00101\u001a\u00020!2\u0006\u0010\u0017\u001a\u00028\u0001H\u0096\u0080\u0004¢\u0006\u0002\u00100J\u0019\u00102\u001a\u0004\u0018\u00018\u00012\u0006\u0010/\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u00103J!\u00104\u001a\u0004\u0018\u00018\u00012\u0006\u0010/\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00028\u0001H\u0096\u0080\b¢\u0006\u0002\u00105J \u00106\u001a\u00020*2\u0014\u00107\u001a\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010&H\u0096\u0080\u0004J\u0019\u00108\u001a\u0004\u0018\u00018\u00012\u0006\u0010/\u001a\u00028\u0000H\u0096\u0080\b¢\u0006\u0002\u00103J\n\u00109\u001a\u00020*H\u0096\u0080\u0004J\u0014\u0010E\u001a\u00020!2\b\u0010F\u001a\u0004\u0018\u00010(H\u0096\u0082\u0004J\n\u0010G\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010H\u001a\u00020IH\u0096\u0080\u0004J\n\u0010N\u001a\u00020*H\u0082\u0080\u0004J\u000f\u0010O\u001a\u00020*H\u0080\u0080\u0004¢\u0006\u0002\bPJ\u0012\u0010Q\u001a\u00020*2\u0006\u0010R\u001a\u00020\rH\u0082\u0080\u0004J\u0012\u0010S\u001a\u00020!2\u0006\u0010T\u001a\u00020\rH\u0082\u0080\u0004J\u0012\u0010U\u001a\u00020*2\u0006\u0010V\u001a\u00020\rH\u0082\u0080\u0004J\u0015\u0010W\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007H\u0082\u0080\u0004¢\u0006\u0002\u0010XJ\u0017\u0010Y\u001a\u00020\r2\u0006\u0010/\u001a\u00028\u0000H\u0082\u0080\u0004¢\u0006\u0002\u0010ZJ\u0012\u0010[\u001a\u00020*2\u0006\u0010\\\u001a\u00020!H\u0082\u0080\u0004J\u0012\u0010]\u001a\u00020*2\u0006\u0010^\u001a\u00020\rH\u0082\u0080\u0004J\u0012\u0010_\u001a\u00020!2\u0006\u0010`\u001a\u00020\rH\u0082\u0080\u0004J\u0017\u0010a\u001a\u00020\r2\u0006\u0010/\u001a\u00028\u0000H\u0082\u0080\u0004¢\u0006\u0002\u0010ZJ\u0017\u0010b\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00028\u0001H\u0082\u0080\u0004¢\u0006\u0002\u0010ZJ\u0019\u0010c\u001a\u00020\r2\u0006\u0010/\u001a\u00028\u0000H\u0080\u0080\u0004¢\u0006\u0004\bd\u0010ZJ\u0019\u0010e\u001a\u00020!2\u0006\u0010/\u001a\u00028\u0000H\u0080\u0080\u0004¢\u0006\u0004\bf\u00100J\u0012\u0010g\u001a\u00020*2\u0006\u0010h\u001a\u00020\rH\u0082\u0080\u0004J\u0012\u0010i\u001a\u00020*2\u0006\u0010j\u001a\u00020\rH\u0082\u0080\u0004J#\u0010k\u001a\u00020!2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010mH\u0080\u0080\u0004¢\u0006\u0002\bnJ\u001a\u0010o\u001a\u00020!2\u000e\u0010F\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030&H\u0082\u0080\u0004J\u001b\u0010p\u001a\u00020!2\n\u0010q\u001a\u0006\u0012\u0002\b\u00030rH\u0080\u0080\u0004¢\u0006\u0002\bsJ\u001e\u0010t\u001a\u00020!2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010mH\u0082\u0080\u0004J$\u0010u\u001a\u00020!2\u0018\u00107\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010m0rH\u0083\u0080\bJ#\u0010v\u001a\u00020!2\u0012\u0010l\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010mH\u0080\u0080\u0004¢\u0006\u0002\bwJ\u0019\u0010x\u001a\u00020!2\u0006\u0010y\u001a\u00028\u0001H\u0080\u0080\u0004¢\u0006\u0004\bz\u00100J\u001b\u0010{\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010|H\u0080\u0080\u0004¢\u0006\u0002\b}J\u001c\u0010~\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u007fH\u0080\u0080\u0004¢\u0006\u0003\b\u0080\u0001J\u001e\u0010\u0081\u0001\u001a\u000f\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0082\u0001H\u0080\u0080\u0004¢\u0006\u0003\b\u0083\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\u0014R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0007X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\u0014R\u000f\u0010\t\u001a\u00020\nX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\nX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\rX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000e\u001a\u00020\rX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0015\u001a\u00020\rX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0016\u001a\u00020\rX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u001f\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r@RX\u0096\u008e\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001cX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u001eX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u001d\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010 X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u001f\u0010\"\u001a\u00020!2\u0006\u0010\u0017\u001a\u00020!@BX\u0080\u008e\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001b\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000;8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b<\u0010=R\u001b\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00010?8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b@\u0010AR'\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010C0;8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\bD\u0010=R\u0015\u0010J\u001a\u00020\r8@X\u0080\u0084\b¢\u0006\u0006\u001a\u0004\bK\u0010\u001aR\u0015\u0010L\u001a\u00020\r8BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\bM\u0010\u001a¨\u0006\u008a\u0001"}, d2 = {"Lkotlin/collections/builders/MapBuilder;", "K", "V", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "keysArray", _UrlKt.FRAGMENT_ENCODE_SET, "valuesArray", "presenceArray", _UrlKt.FRAGMENT_ENCODE_SET, "hashArray", "maxProbeDistance", _UrlKt.FRAGMENT_ENCODE_SET, "length", "<init>", "([Ljava/lang/Object;[Ljava/lang/Object;[I[III)V", "()V", "initialCapacity", "(I)V", "[Ljava/lang/Object;", "hashShift", "modCount", "value", "size", "getSize", "()I", "keysView", "Lkotlin/collections/builders/MapBuilderKeys;", "valuesView", "Lkotlin/collections/builders/MapBuilderValues;", "entriesView", "Lkotlin/collections/builders/MapBuilderEntries;", _UrlKt.FRAGMENT_ENCODE_SET, "isReadOnly", "isReadOnly$kotlin_stdlib", "()Z", "build", _UrlKt.FRAGMENT_ENCODE_SET, "writeReplace", _UrlKt.FRAGMENT_ENCODE_SET, "readObject", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/ObjectInputStream;", "isEmpty", "containsKey", PluginsConstants.Settings.KEY, "(Ljava/lang/Object;)Z", "containsValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "from", "remove", "clear", "keys", _UrlKt.FRAGMENT_ENCODE_SET, "getKeys", "()Ljava/util/Set;", "values", _UrlKt.FRAGMENT_ENCODE_SET, "getValues", "()Ljava/util/Collection;", "entries", _UrlKt.FRAGMENT_ENCODE_SET, "getEntries", "equals", "other", "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "getCapacity$kotlin_stdlib", "hashSize", "getHashSize", "registerModification", "checkIsMutable", "checkIsMutable$kotlin_stdlib", "ensureExtraCapacity", "n", "shouldCompact", "extraCapacity", "ensureCapacity", "minCapacity", "allocateValuesArray", "()[Ljava/lang/Object;", "hash", "(Ljava/lang/Object;)I", "compact", "updateHashArray", "rehash", "newHashSize", "putRehash", "i", "findKey", "findValue", "addKey", "addKey$kotlin_stdlib", "removeKey", "removeKey$kotlin_stdlib", "removeEntryAt", "index", "removeHashAt", "removedHash", "containsEntry", "entry", _UrlKt.FRAGMENT_ENCODE_SET, "containsEntry$kotlin_stdlib", "contentEquals", "containsAllEntries", "m", _UrlKt.FRAGMENT_ENCODE_SET, "containsAllEntries$kotlin_stdlib", "putEntry", "putAllEntries", "removeEntry", "removeEntry$kotlin_stdlib", "removeValue", "element", "removeValue$kotlin_stdlib", "keysIterator", "Lkotlin/collections/builders/MapBuilder$KeysItr;", "keysIterator$kotlin_stdlib", "valuesIterator", "Lkotlin/collections/builders/MapBuilder$ValuesItr;", "valuesIterator$kotlin_stdlib", "entriesIterator", "Lkotlin/collections/builders/MapBuilder$EntriesItr;", "entriesIterator$kotlin_stdlib", "Companion", "Itr", "KeysItr", "ValuesItr", "EntriesItr", "EntryRef", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMapBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapBuilder.kt\nkotlin/collections/builders/MapBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,734:1\n1#2:735\n*E\n"})
/* loaded from: classes.dex */
public final class MapBuilder<K, V> implements Map<K, V>, Serializable, KMutableMap {
    public static final Companion Companion = null;
    private static final MapBuilder Empty = null;
    private static final int INITIAL_CAPACITY = 8;
    private static final int INITIAL_MAX_PROBE_DISTANCE = 2;
    private static final int MAGIC = -1640531527;
    private static final int TOMBSTONE = -1;
    private MapBuilderEntries<K, V> entriesView;
    private int[] hashArray;
    private int hashShift;
    private boolean isReadOnly;
    private K[] keysArray;
    private MapBuilderKeys<K> keysView;
    private int length;
    private int maxProbeDistance;
    private int modCount;
    private int[] presenceArray;
    private int size;
    private V[] valuesArray;
    private MapBuilderValues<V> valuesView;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0082\u0080\u0004J\u0012\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0082\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R!\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\nX\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lkotlin/collections/builders/MapBuilder$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MAGIC", _UrlKt.FRAGMENT_ENCODE_SET, "INITIAL_CAPACITY", "INITIAL_MAX_PROBE_DISTANCE", "TOMBSTONE", "Empty", "Lkotlin/collections/builders/MapBuilder;", _UrlKt.FRAGMENT_ENCODE_SET, "getEmpty$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder;", "computeHashSize", "capacity", "computeShift", "hashSize", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ int access$computeHashSize(Companion r0, int r1) {
            return r0.computeHashSize(r1);
        }

        public static final /* synthetic */ int access$computeShift(Companion r0, int r1) {
            return r0.computeShift(r1);
        }

        private final int computeHashSize(int r1) {
            return Integer.highestOneBit(RangesKt.coerceAtLeast(r1, 1) * 3);
        }

        private final int computeShift(int r1) {
            return Integer.numberOfLeadingZeros(r1) + 1;
        }

        public final MapBuilder getEmpty$kotlin_stdlib() {
            return MapBuilder.access$getEmpty$cp();
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\u0010'\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00050\u0004B\u001d\bF\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u000bH\u0096\u0082\u0004J\u000f\u0010\f\u001a\u00020\rH\u0080\u0080\u0004¢\u0006\u0002\b\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013H\u0086\u0080\u0004¨\u0006\u0014"}, d2 = {"Lkotlin/collections/builders/MapBuilder$EntriesItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "map", "Lkotlin/collections/builders/MapBuilder;", "<init>", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "Lkotlin/collections/builders/MapBuilder$EntryRef;", "nextHashCode", _UrlKt.FRAGMENT_ENCODE_SET, "nextHashCode$kotlin_stdlib", "nextAppendString", _UrlKt.FRAGMENT_ENCODE_SET, "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class EntriesItr<K, V> extends Itr<K, V> implements Iterator<Map.Entry<K, V>>, KMutableIterator {
        public EntriesItr(MapBuilder<K, V> r1) {
            super(r1);
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ Object next() {
            return next();
        }

        @Override // java.util.Iterator
        public EntryRef<K, V> next() {
            checkForComodification$kotlin_stdlib();
            if (getIndex$kotlin_stdlib() >= MapBuilder.access$getLength$p(getMap$kotlin_stdlib())) goto L6;
            int r0 = getIndex$kotlin_stdlib();
            setIndex$kotlin_stdlib(r0 + 1);
            setLastIndex$kotlin_stdlib(r0);
            EntryRef<K, V> r02 = new EntryRef(getMap$kotlin_stdlib(), getLastIndex$kotlin_stdlib());
            initNext$kotlin_stdlib();
            return r02;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        public final void nextAppendString(StringBuilder r4) {
            if (getIndex$kotlin_stdlib() >= MapBuilder.access$getLength$p(getMap$kotlin_stdlib())) goto L14;
            int r0 = getIndex$kotlin_stdlib();
            setIndex$kotlin_stdlib(r0 + 1);
            setLastIndex$kotlin_stdlib(r0);
            Object r02 = MapBuilder.access$getKeysArray$p(getMap$kotlin_stdlib())[getLastIndex$kotlin_stdlib()];
            if (r02 != getMap$kotlin_stdlib()) goto L7;
            r4.append("(this Map)");
        L8:
            r4.append(SignatureVisitor.INSTANCEOF);
            Object r03 = MapBuilder.access$getValuesArray$p(getMap$kotlin_stdlib())[getLastIndex$kotlin_stdlib()];
            if (r03 != getMap$kotlin_stdlib()) goto L11;
            r4.append("(this Map)");
        L12:
            initNext$kotlin_stdlib();
            return;
        L11:
            r4.append(r03);
            goto L12
        L7:
            r4.append(r02);
            goto L8
        L14:
            Utils$$ExternalSyntheticBUOutline0.m();
        }

        public final int nextHashCode$kotlin_stdlib() {
            int r2 = 0;
            if (getIndex$kotlin_stdlib() >= MapBuilder.access$getLength$p(getMap$kotlin_stdlib())) goto L13;
            int r0 = getIndex$kotlin_stdlib();
            setIndex$kotlin_stdlib(r0 + 1);
            setLastIndex$kotlin_stdlib(r0);
            Object r02 = MapBuilder.access$getKeysArray$p(getMap$kotlin_stdlib())[getLastIndex$kotlin_stdlib()];
            if (r02 == null) goto L7;
            int r03 = r02.hashCode();
        L8:
            Object r1 = MapBuilder.access$getValuesArray$p(getMap$kotlin_stdlib())[getLastIndex$kotlin_stdlib()];
            if (r1 == null) goto L11;
            r2 = r1.hashCode();
        L11:
            int r04 = r03 ^ r2;
            initNext$kotlin_stdlib();
            return r04;
        L7:
            r03 = 0;
            goto L8
        L13:
            Utils$$ExternalSyntheticBUOutline0.m();
            return 0;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B%\bF\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u0010\u001a\u00028\u00032\u0006\u0010\u0011\u001a\u00028\u0003H\u0096\u0080\b¢\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0007H\u0096\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0019H\u0096\u0080\u0004J\n\u0010\u001a\u001a\u00020\u001bH\u0082\u0080\u0004R\u001b\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000b\u001a\u00028\u00028VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0015\u0010\u000e\u001a\u00028\u00038VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r¨\u0006\u001c"}, d2 = {"Lkotlin/collections/builders/MapBuilder$EntryRef;", "K", "V", _UrlKt.FRAGMENT_ENCODE_SET, "map", "Lkotlin/collections/builders/MapBuilder;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/collections/builders/MapBuilder;I)V", "expectedModCount", PluginsConstants.Settings.KEY, "getKey", "()Ljava/lang/Object;", "value", "getValue", "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "checkForComodification", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class EntryRef<K, V> implements Map.Entry<K, V>, KMutableMap.Entry {
        private final int expectedModCount;
        private final int index;
        private final MapBuilder<K, V> map;

        public EntryRef(MapBuilder<K, V> r1, int r2) {
            this.map = r1;
            this.index = r2;
            this.expectedModCount = MapBuilder.access$getModCount$p(r1);
        }

        private final void checkForComodification() {
            if (MapBuilder.access$getModCount$p(this.map) != this.expectedModCount) goto L6;
            return;
        L6:
            throw new ConcurrentModificationException("The backing map has been modified after this entry was obtained.");
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object r3) {
            if ((r3 instanceof Map.Entry) == false) goto L10;
            Map.Entry r32 = (Map.Entry) r3;
            if (Intrinsics.areEqual(r32.getKey(), getKey()) == true) goto L7;
            return false;
        L7:
            if (Intrinsics.areEqual(r32.getValue(), getValue()) == false) goto L13;
            return true;
        L13:
            return false;
        L10:
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            checkForComodification();
            return (K) MapBuilder.access$getKeysArray$p(this.map)[this.index];
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            checkForComodification();
            return (V) MapBuilder.access$getValuesArray$p(this.map)[this.index];
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K r0 = getKey();
            int r1 = 0;
            if (r0 == null) goto L5;
            int r02 = r0.hashCode();
        L6:
            V r2 = getValue();
            if (r2 == null) goto L10;
            r1 = r2.hashCode();
        L10:
            return r02 ^ r1;
        L5:
            r02 = 0;
            goto L6
        }

        @Override // java.util.Map.Entry
        public V setValue(V r3) {
            checkForComodification();
            this.map.checkIsMutable$kotlin_stdlib();
            Object[] r0 = MapBuilder.access$allocateValuesArray(this.map);
            int r2 = this.index;
            V r1 = (V) r0[r2];
            r0[r2] = r3;
            return r1;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            r0.append(getKey());
            r0.append(SignatureVisitor.INSTANCEOF);
            r0.append(getValue());
            return r0.toString();
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u001d\bF\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\u0014\u001a\u00020\u0015H\u0080\u0080\u0004¢\u0006\u0002\b\u0016J\n\u0010\u0017\u001a\u00020\u0018H\u0086\u0080\u0004J\n\u0010\u0019\u001a\u00020\u0015H\u0086\u0080\u0004J\u000f\u0010\u001a\u001a\u00020\u0015H\u0080\u0080\u0004¢\u0006\u0002\b\u001bR!\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0080\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000bX\u0080\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u000bX\u0080\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u000f\u0010\u0013\u001a\u00020\u000bX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lkotlin/collections/builders/MapBuilder$Itr;", "K", "V", _UrlKt.FRAGMENT_ENCODE_SET, "map", "Lkotlin/collections/builders/MapBuilder;", "<init>", "(Lkotlin/collections/builders/MapBuilder;)V", "getMap$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "getIndex$kotlin_stdlib", "()I", "setIndex$kotlin_stdlib", "(I)V", "lastIndex", "getLastIndex$kotlin_stdlib", "setLastIndex$kotlin_stdlib", "expectedModCount", "initNext", _UrlKt.FRAGMENT_ENCODE_SET, "initNext$kotlin_stdlib", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "remove", "checkForComodification", "checkForComodification$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nMapBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MapBuilder.kt\nkotlin/collections/builders/MapBuilder$Itr\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,734:1\n1#2:735\n*E\n"})
    public static class Itr<K, V> {
        private int expectedModCount;
        private int index;
        private int lastIndex;
        private final MapBuilder<K, V> map;

        public Itr(MapBuilder<K, V> r2) {
            this.map = r2;
            this.lastIndex = -1;
            this.expectedModCount = MapBuilder.access$getModCount$p(r2);
            initNext$kotlin_stdlib();
        }

        public final void checkForComodification$kotlin_stdlib() {
            if (MapBuilder.access$getModCount$p(this.map) != this.expectedModCount) goto L5;
            return;
        L5:
            ArraySet$$ExternalSyntheticBUOutline0.m();
        }

        public final int getIndex$kotlin_stdlib() {
            return this.index;
        }

        public final int getLastIndex$kotlin_stdlib() {
            return this.lastIndex;
        }

        public final MapBuilder<K, V> getMap$kotlin_stdlib() {
            return this.map;
        }

        public final boolean hasNext() {
            if (this.index >= MapBuilder.access$getLength$p(this.map)) goto L6;
            return true;
        L6:
            return false;
        }

        public final void initNext$kotlin_stdlib() {
        L3:
            if (this.index >= MapBuilder.access$getLength$p(this.map)) goto L7;
            int[] r0 = MapBuilder.access$getPresenceArray$p(this.map);
            int r1 = this.index;
            if (r0[r1] >= 0) goto L10;
            this.index = r1 + 1;
            goto L3
        L10:
            return;
        }

        public final void remove() {
            checkForComodification$kotlin_stdlib();
            if (this.lastIndex == (-1)) goto L6;
            this.map.checkIsMutable$kotlin_stdlib();
            MapBuilder.access$removeEntryAt(this.map, this.lastIndex);
            this.lastIndex = -1;
            this.expectedModCount = MapBuilder.access$getModCount$p(this.map);
            return;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("Call next() before removing element from the iterator.");
        }

        public final void setIndex$kotlin_stdlib(int r1) {
            this.index = r1;
        }

        public final void setLastIndex$kotlin_stdlib(int r1) {
            this.lastIndex = r1;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u001d\bF\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00028\u0002H\u0096\u0082\u0004¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lkotlin/collections/builders/MapBuilder$KeysItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", _UrlKt.FRAGMENT_ENCODE_SET, "map", "Lkotlin/collections/builders/MapBuilder;", "<init>", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class KeysItr<K, V> extends Itr<K, V> implements Iterator<K>, KMutableIterator {
        public KeysItr(MapBuilder<K, V> r1) {
            super(r1);
        }

        @Override // java.util.Iterator
        public K next() {
            checkForComodification$kotlin_stdlib();
            if (getIndex$kotlin_stdlib() >= MapBuilder.access$getLength$p(getMap$kotlin_stdlib())) goto L6;
            int r0 = getIndex$kotlin_stdlib();
            setIndex$kotlin_stdlib(r0 + 1);
            setLastIndex$kotlin_stdlib(r0);
            K r02 = (K) MapBuilder.access$getKeysArray$p(getMap$kotlin_stdlib())[getLastIndex$kotlin_stdlib()];
            initNext$kotlin_stdlib();
            return r02;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004B\u001d\bF\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00028\u0003H\u0096\u0082\u0004¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lkotlin/collections/builders/MapBuilder$ValuesItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", _UrlKt.FRAGMENT_ENCODE_SET, "map", "Lkotlin/collections/builders/MapBuilder;", "<init>", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class ValuesItr<K, V> extends Itr<K, V> implements Iterator<V>, KMutableIterator {
        public ValuesItr(MapBuilder<K, V> r1) {
            super(r1);
        }

        @Override // java.util.Iterator
        public V next() {
            checkForComodification$kotlin_stdlib();
            if (getIndex$kotlin_stdlib() >= MapBuilder.access$getLength$p(getMap$kotlin_stdlib())) goto L6;
            int r0 = getIndex$kotlin_stdlib();
            setIndex$kotlin_stdlib(r0 + 1);
            setLastIndex$kotlin_stdlib(r0);
            V r02 = (V) MapBuilder.access$getValuesArray$p(getMap$kotlin_stdlib())[getLastIndex$kotlin_stdlib()];
            initNext$kotlin_stdlib();
            return r02;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    static {
        Companion = new Companion(null);
        MapBuilder r0 = new MapBuilder(0);
        r0.isReadOnly = true;
        Empty = r0;
    }

    public MapBuilder() {
        this(8);
    }

    public MapBuilder(int r8) {
        this(ListBuilderKt.arrayOfUninitializedElements(r8), null, new int[r8], new int[Companion.access$computeHashSize(Companion, r8)], 2, 0);
    }

    private MapBuilder(K[] r1, V[] r2, int[] r3, int[] r4, int r5, int r6) {
        this.keysArray = r1;
        this.valuesArray = r2;
        this.presenceArray = r3;
        this.hashArray = r4;
        this.maxProbeDistance = r5;
        this.length = r6;
        this.hashShift = Companion.access$computeShift(Companion, getHashSize());
    }

    public static final /* synthetic */ Object[] access$allocateValuesArray(MapBuilder r0) {
        return r0.allocateValuesArray();
    }

    public static final /* synthetic */ MapBuilder access$getEmpty$cp() {
        return Empty;
    }

    public static final /* synthetic */ Object[] access$getKeysArray$p(MapBuilder r0) {
        return r0.keysArray;
    }

    public static final /* synthetic */ int access$getLength$p(MapBuilder r0) {
        return r0.length;
    }

    public static final /* synthetic */ int access$getModCount$p(MapBuilder r0) {
        return r0.modCount;
    }

    public static final /* synthetic */ int[] access$getPresenceArray$p(MapBuilder r0) {
        return r0.presenceArray;
    }

    public static final /* synthetic */ Object[] access$getValuesArray$p(MapBuilder r0) {
        return r0.valuesArray;
    }

    public static final /* synthetic */ void access$removeEntryAt(MapBuilder r0, int r1) {
        r0.removeEntryAt(r1);
    }

    private final V[] allocateValuesArray() {
        V[] r0 = this.valuesArray;
        if (r0 == null) goto L5;
        return r0;
    L5:
        V[] r02 = (V[]) ListBuilderKt.arrayOfUninitializedElements(getCapacity$kotlin_stdlib());
        this.valuesArray = r02;
        return r02;
    }

    private final void compact(boolean r8) {
        V[] r0 = this.valuesArray;
        int r1 = 0;
        int r2 = 0;
    L3:
        int r3 = this.length;
        if (r1 >= r3) goto L14;
        int[] r32 = this.presenceArray;
        int r4 = r32[r1];
        if (r4 < 0) goto L13;
        K[] r5 = this.keysArray;
        r5[r2] = r5[r1];
        if (r0 == null) goto L10;
        r0[r2] = r0[r1];
    L10:
        if (r8 == false) goto L12;
        r32[r2] = r4;
        this.hashArray[r4] = r2 + 1;
    L12:
        r2 = r2 + 1;
    L13:
        r1 = r1 + 1;
        goto L3
    L14:
        ListBuilderKt.resetRange(this.keysArray, r2, r3);
        if (r0 == null) goto L17;
        ListBuilderKt.resetRange(r0, r2, this.length);
    L17:
        this.length = r2;
    }

    private final boolean contentEquals(Map<?, ?> r3) {
        if (size() == r3.size()) goto L5;
        return false;
    L5:
        if (containsAllEntries$kotlin_stdlib(r3.entrySet()) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private final void ensureCapacity(int r3) {
        if (r3 < 0) goto L14;
        if (r3 <= getCapacity$kotlin_stdlib()) goto L15;
        int r32 = AbstractList.Companion.newCapacity$kotlin_stdlib(getCapacity$kotlin_stdlib(), r3);
        this.keysArray = (K[]) ListBuilderKt.copyOfUninitializedElements(this.keysArray, r32);
        V[] r0 = this.valuesArray;
        if (r0 == null) goto L8;
        V[] r02 = (V[]) ListBuilderKt.copyOfUninitializedElements(r0, r32);
    L9:
        this.valuesArray = r02;
        this.presenceArray = Arrays.copyOf(this.presenceArray, r32);
        int r33 = Companion.access$computeHashSize(Companion, r32);
        if (r33 <= getHashSize()) goto L16;
        rehash(r33);
        return;
    L16:
        return;
    L8:
        r02 = null;
        goto L9
    L15:
        return;
    L14:
        throw new OutOfMemoryError();
    }

    private final void ensureExtraCapacity(int r2) {
        if (shouldCompact(r2) == false) goto L6;
        compact(true);
        return;
    L6:
        ensureCapacity(this.length + r2);
    }

    private final int findKey(K r6) {
        int r0 = hash(r6);
        int r1 = this.maxProbeDistance;
    L3:
        int r2 = this.hashArray[r0];
        if (r2 == 0) goto L5;
        int r22 = r2 - 1;
        if (Intrinsics.areEqual(this.keysArray[r22], r6) == true) goto L8;
        r1 = r1 - 1;
        if (r1 < 0) goto L11;
        int r23 = r0 - 1;
        if (r0 == 0) goto L14;
        r0 = r23;
        goto L3
    L14:
        r0 = getHashSize() - 1;
        goto L3
    L11:
        return -1;
    L8:
        return r22;
    L5:
        return -1;
    }

    private final int findValue(V r3) {
        int r0 = this.length;
    L3:
        r0 = r0 - 1;
        if (r0 < 0) goto L10;
        if (this.presenceArray[r0] < 0) goto L3;
        if (Intrinsics.areEqual(this.valuesArray[r0], r3) == false) goto L3;
        return r0;
    L10:
        return -1;
    }

    private final int getHashSize() {
        return this.hashArray.length;
    }

    private final int hash(K r2) {
        if (r2 == null) goto L4;
        int r22 = r2.hashCode();
    L6:
        return (r22 * MAGIC) >>> this.hashShift;
    L4:
        r22 = 0;
        goto L6
    }

    @IgnorableReturnValue
    private final boolean putAllEntries(Collection<? extends Map.Entry<? extends K, ? extends V>> r3) {
        boolean r1 = false;
        if (r3.isEmpty() == false) goto L5;
        return false;
    L5:
        ensureExtraCapacity(r3.size());
        Iterator<? extends Map.Entry<? extends K, ? extends V>> r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L11;
        if (putEntry(r32.next()) == false) goto L7;
        r1 = true;
        goto L7
    L11:
        return r1;
    }

    private final boolean putEntry(Map.Entry<? extends K, ? extends V> r5) {
        int r0 = addKey$kotlin_stdlib(r5.getKey());
        V[] r4 = allocateValuesArray();
        if (r0 < 0) goto L6;
        r4[r0] = r5.getValue();
        return true;
    L6:
        int r02 = (-r0) - 1;
        V r2 = r4[r02];
        if (Intrinsics.areEqual(r5.getValue(), r2) == true) goto L10;
        r4[r02] = r5.getValue();
        return true;
    L10:
        return false;
    }

    private final boolean putRehash(int r6) {
        int r0 = hash(this.keysArray[r6]);
        int r1 = this.maxProbeDistance;
    L3:
        int[] r2 = this.hashArray;
        if (r2[r0] == 0) goto L5;
        r1 = r1 - 1;
        if (r1 < 0) goto L9;
        int r22 = r0 - 1;
        if (r0 == 0) goto L13;
        r0 = r22;
        goto L3
    L13:
        r0 = getHashSize() - 1;
        goto L3
    L9:
        return false;
    L5:
        r2[r0] = r6 + 1;
        this.presenceArray[r6] = r0;
        return true;
    }

    private final void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization is supported via proxy only");
    }

    private final void registerModification() {
        this.modCount++;
    }

    private final void rehash(int r4) {
        registerModification();
        int r2 = 0;
        if (this.length <= size()) goto L5;
        compact(false);
    L5:
        this.hashArray = new int[r4];
        this.hashShift = Companion.access$computeShift(Companion, r4);
    L7:
        if (r2 >= this.length) goto L15;
        int r42 = r2 + 1;
        if (putRehash(r2) == false) goto L11;
        r2 = r42;
        goto L7
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
        return;
    }

    private final void removeEntryAt(int r3) {
        ListBuilderKt.resetAt(this.keysArray, r3);
        V[] r0 = this.valuesArray;
        if (r0 == null) goto L5;
        ListBuilderKt.resetAt(r0, r3);
    L5:
        removeHashAt(this.presenceArray[r3]);
        this.presenceArray[r3] = -1;
        this.size = size() - 1;
        registerModification();
    }

    private final void removeHashAt(int r8) {
    L3:
        int r1 = r8;
        int r2 = 0;
    L4:
        int r3 = r8 - 1;
        if (r8 != 0) goto L7;
        r8 = getHashSize() - 1;
    L8:
        int[] r32 = this.hashArray;
        int r4 = r32[r8];
        r2 = r2 + 1;
        if (r2 > this.maxProbeDistance) goto L10;
        if (r4 == 0) goto L13;
        int r5 = r4 - 1;
        if (((hash(this.keysArray[r5]) - r8) & (getHashSize() - 1)) < r2) goto L4;
        this.hashArray[r1] = r4;
        this.presenceArray[r5] = r1;
        goto L3
    L13:
        r32[r1] = 0;
        return;
    L10:
        r32[r1] = 0;
        return;
    L7:
        r8 = r3;
        goto L8
    }

    private final boolean shouldCompact(int r4) {
        int r0 = getCapacity$kotlin_stdlib();
        int r1 = this.length;
        int r02 = r0 - r1;
        int r12 = r1 - size();
        if (r02 < r4) goto L5;
        return false;
    L5:
        if ((r02 + r12) >= r4) goto L7;
        return false;
    L7:
        if (r12 < (getCapacity$kotlin_stdlib() / 4)) goto L13;
        return true;
    L13:
        return false;
    }

    private final Object writeReplace() {
        if (this.isReadOnly == false) goto L7;
        return new SerializedMap(this);
    L7:
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    public final int addKey$kotlin_stdlib(K r8) {
        checkIsMutable$kotlin_stdlib();
    L3:
        int r0 = hash(r8);
        int r1 = RangesKt.coerceAtMost(this.maxProbeDistance * 2, getHashSize() / 2);
        int r2 = 0;
    L4:
        int r3 = this.hashArray[r0];
        if (r3 == 0) goto L7;
        if (Intrinsics.areEqual(this.keysArray[r3 - 1], r8) == true) goto L16;
        r2 = r2 + 1;
        if (r2 > r1) goto L19;
        int r32 = r0 - 1;
        if (r0 == 0) goto L22;
        r0 = r32;
        goto L4
    L22:
        r0 = getHashSize() - 1;
        goto L4
    L19:
        rehash(getHashSize() * 2);
        goto L3
    L16:
        return -r3;
    L7:
        if (this.length < getCapacity$kotlin_stdlib()) goto L9;
        ensureExtraCapacity(1);
        goto L3
    L9:
        int r12 = this.length;
        int r33 = r12 + 1;
        this.length = r33;
        this.keysArray[r12] = r8;
        this.presenceArray[r12] = r0;
        this.hashArray[r0] = r33;
        this.size = size() + 1;
        registerModification();
        if (r2 <= this.maxProbeDistance) goto L12;
        this.maxProbeDistance = r2;
    L12:
        return r12;
    }

    public final Map<K, V> build() {
        checkIsMutable$kotlin_stdlib();
        this.isReadOnly = true;
        if (size() <= 0) goto L6;
        return this;
    L6:
        return Empty;
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if (this.isReadOnly == true) goto L5;
        return;
    L5:
        Type$$ExternalSyntheticBUOutline0.m();
    }

    @Override // java.util.Map
    public void clear() {
        checkIsMutable$kotlin_stdlib();
        int r0 = this.length - 1;
        if (r0 < 0) goto L10;
        int r2 = 0;
    L5:
        int[] r3 = this.presenceArray;
        int r4 = r3[r2];
        if (r4 < 0) goto L8;
        this.hashArray[r4] = 0;
        r3[r2] = -1;
    L8:
        if (r2 == r0) goto L10;
        r2 = r2 + 1;
    L10:
        ListBuilderKt.resetRange(this.keysArray, 0, this.length);
        V[] r02 = this.valuesArray;
        if (r02 == null) goto L13;
        ListBuilderKt.resetRange(r02, 0, this.length);
    L13:
        this.size = 0;
        this.length = 0;
        registerModification();
    }

    public final boolean containsAllEntries$kotlin_stdlib(Collection<?> r3) {
        Iterator<?> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L10;
        Object r0 = r32.next();
        if (r0 == null) goto L9;
        if (containsEntry$kotlin_stdlib((Map.Entry) r0) == true) goto L4;
    L9:
        return false;
    L10:
        return true;
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry<? extends K, ? extends V> r2) {
        int r0 = findKey(r2.getKey());
        if (r0 >= 0) goto L7;
        return false;
    L7:
        return Intrinsics.areEqual(this.valuesArray[r0], r2.getValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsKey(Object r1) {
        if (findKey(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public boolean containsValue(Object r1) {
        if (findValue(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final EntriesItr<K, V> entriesIterator$kotlin_stdlib() {
        return new EntriesItr(this);
    }

    @Override // java.util.Map
    public final /* bridge */ Set<Map.Entry<K, V>> entrySet() {
        return getEntries();
    }

    @Override // java.util.Map
    public boolean equals(Object r2) {
        if (r2 != this) goto L4;
        return true;
    L4:
        if ((r2 instanceof Map) == true) goto L6;
        return false;
    L6:
        if (contentEquals((Map) r2) == true) goto L13;
        return false;
    L13:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V get(Object r1) {
        int r12 = findKey(r1);
        if (r12 >= 0) goto L7;
        return null;
    L7:
        return this.valuesArray[r12];
    }

    public final int getCapacity$kotlin_stdlib() {
        return this.keysArray.length;
    }

    public Set<Map.Entry<K, V>> getEntries() {
        MapBuilderEntries<K, V> r0 = this.entriesView;
        if (r0 != null) goto L6;
        MapBuilderEntries<K, V> r02 = new MapBuilderEntries(this);
        this.entriesView = r02;
        return r02;
    L6:
        return r0;
    }

    public Set<K> getKeys() {
        MapBuilderKeys<K> r0 = this.keysView;
        if (r0 != null) goto L6;
        MapBuilderKeys<K> r02 = new MapBuilderKeys(this);
        this.keysView = r02;
        return r02;
    L6:
        return r0;
    }

    public int getSize() {
        return this.size;
    }

    public Collection<V> getValues() {
        MapBuilderValues<V> r0 = this.valuesView;
        if (r0 != null) goto L6;
        MapBuilderValues<V> r02 = new MapBuilderValues(this);
        this.valuesView = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // java.util.Map
    public int hashCode() {
        EntriesItr<K, V> r2 = entriesIterator$kotlin_stdlib();
        int r0 = 0;
    L4:
        if (r2.hasNext() == false) goto L6;
        r0 = r0 + r2.nextHashCode$kotlin_stdlib();
        goto L4
    L6:
        return r0;
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        if (size() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean isReadOnly$kotlin_stdlib() {
        return this.isReadOnly;
    }

    @Override // java.util.Map
    public final /* bridge */ Set<K> keySet() {
        return getKeys();
    }

    public final KeysItr<K, V> keysIterator$kotlin_stdlib() {
        return new KeysItr(this);
    }

    @Override // java.util.Map
    public V put(K r2, V r3) {
        checkIsMutable$kotlin_stdlib();
        int r22 = addKey$kotlin_stdlib(r2);
        V[] r1 = allocateValuesArray();
        if (r22 >= 0) goto L6;
        int r23 = (-r22) - 1;
        V r0 = r1[r23];
        r1[r23] = r3;
        return r0;
    L6:
        r1[r22] = r3;
        return null;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> r1) {
        checkIsMutable$kotlin_stdlib();
        putAllEntries(r1.entrySet());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public V remove(Object r2) {
        checkIsMutable$kotlin_stdlib();
        int r22 = findKey(r2);
        if (r22 >= 0) goto L6;
        return null;
    L6:
        V r0 = this.valuesArray[r22];
        removeEntryAt(r22);
        return r0;
    }

    public final boolean removeEntry$kotlin_stdlib(Map.Entry<? extends K, ? extends V> r4) {
        checkIsMutable$kotlin_stdlib();
        int r0 = findKey(r4.getKey());
        if (r0 >= 0) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(this.valuesArray[r0], r4.getValue()) == true) goto L8;
        return false;
    L8:
        removeEntryAt(r0);
        return true;
    }

    public final boolean removeKey$kotlin_stdlib(K r1) {
        checkIsMutable$kotlin_stdlib();
        int r12 = findKey(r1);
        if (r12 >= 0) goto L6;
        return false;
    L6:
        removeEntryAt(r12);
        return true;
    }

    public final boolean removeValue$kotlin_stdlib(V r1) {
        checkIsMutable$kotlin_stdlib();
        int r12 = findValue(r1);
        if (r12 >= 0) goto L6;
        return false;
    L6:
        removeEntryAt(r12);
        return true;
    }

    @Override // java.util.Map
    public final /* bridge */ int size() {
        return getSize();
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder((size() * 3) + 2);
        r0.append("{");
        EntriesItr<K, V> r3 = entriesIterator$kotlin_stdlib();
        int r1 = 0;
    L4:
        if (r3.hasNext() == false) goto L8;
        if (r1 <= 0) goto L7;
        r0.append(", ");
    L7:
        r3.nextAppendString(r0);
        r1 = r1 + 1;
        goto L4
    L8:
        r0.append("}");
        return r0.toString();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection<V> values() {
        return getValues();
    }

    public final ValuesItr<K, V> valuesIterator$kotlin_stdlib() {
        return new ValuesItr(this);
    }
}
