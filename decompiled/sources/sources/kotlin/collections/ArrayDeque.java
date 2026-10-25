package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UIntArray$Iterator$$ExternalSyntheticBUOutline0;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@SinceKotlin(version = "1.4")
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \\*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\\B\u0011\bV\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006B\t\bV¢\u0006\u0004\b\u0005\u0010\u0007B\u0017\bV\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\u0005\u0010\nJ\u0012\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0004H\u0082\u0080\u0004J\u0012\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0004H\u0082\u0080\u0004J\u0017\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0004H\u0083\u0088\u0004¢\u0006\u0002\u0010\u001bJ\u0012\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0082\u0080\u0004J\u0012\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0082\u0080\u0004J\u0012\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0083\u0088\u0004J\u0012\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0082\u0080\u0004J\u0012\u0010 \u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004H\u0082\u0080\u0004J\n\u0010!\u001a\u00020\"H\u0096\u0080\u0004J\u000f\u0010#\u001a\u00028\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010$J\u0011\u0010%\u001a\u0004\u0018\u00018\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010$J\u000f\u0010&\u001a\u00028\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010$J\u0011\u0010'\u001a\u0004\u0018\u00018\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010$J\u0017\u0010(\u001a\u00020\u00152\u0006\u0010)\u001a\u00028\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010*J\u0017\u0010+\u001a\u00020\u00152\u0006\u0010)\u001a\u00028\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010*J\u000f\u0010,\u001a\u00028\u0000H\u0087\u0080\b¢\u0006\u0002\u0010$J\u0011\u0010-\u001a\u0004\u0018\u00018\u0000H\u0087\u0080\b¢\u0006\u0002\u0010$J\u000f\u0010.\u001a\u00028\u0000H\u0087\u0080\b¢\u0006\u0002\u0010$J\u0011\u0010/\u001a\u0004\u0018\u00018\u0000H\u0087\u0080\b¢\u0006\u0002\u0010$J\u0017\u00100\u001a\u00020\"2\u0006\u0010)\u001a\u00028\u0000H\u0097\u0080\b¢\u0006\u0002\u00101J\u001f\u00100\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010)\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u00102J \u00103\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0082\u0080\u0004J\u0018\u00104\u001a\u00020\"2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0097\u0080\bJ \u00104\u001a\u00020\"2\u0006\u0010\u001d\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0097\u0080\bJ\u0017\u00105\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00020\u0004H\u0096\u0082\u0004¢\u0006\u0002\u0010\u001bJ\u001f\u00106\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010)\u001a\u00028\u0000H\u0097\u0082\b¢\u0006\u0002\u00107J\u0017\u00108\u001a\u00020\"2\u0006\u0010)\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u00101J\u0017\u00109\u001a\u00020\u00042\u0006\u0010)\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010:J\u0017\u0010;\u001a\u00020\u00042\u0006\u0010)\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010:J\u0017\u0010<\u001a\u00020\"2\u0006\u0010)\u001a\u00028\u0000H\u0097\u0080\b¢\u0006\u0002\u00101J\u0017\u0010=\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00020\u0004H\u0097\u0080\b¢\u0006\u0002\u0010\u001bJ\u0018\u0010>\u001a\u00020\"2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0097\u0080\bJ\u0018\u0010?\u001a\u00020\"2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0097\u0080\bJ\u001e\u0010@\u001a\u00020\"2\u0012\u0010A\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\"0BH\u0082\u0088\u0004J\n\u0010C\u001a\u00020\u0015H\u0096\u0080\u0004J)\u0010D\u001a\b\u0012\u0004\u0012\u0002HE0\r\"\u0004\b\u0001\u0010E2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002HE0\rH\u0096\u0080\u0004¢\u0006\u0002\u0010GJ\u0017\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0096\u0080\u0004¢\u0006\u0002\u0010HJ\u001a\u0010I\u001a\u00020\u00152\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004H\u0094\u0080\u0004J\u001a\u0010L\u001a\u00020\u00152\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004H\u0082\u0080\u0004J\u001a\u0010M\u001a\u00020\u00152\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004H\u0082\u0080\u0004J\u001a\u0010N\u001a\u00020\u00152\u0006\u0010O\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u0004H\u0082\u0080\u0004J\n\u0010Q\u001a\u00020\u0015H\u0082\u0080\u0004J+\u0010R\u001a\b\u0012\u0004\u0012\u0002HE0\r\"\u0004\b\u0001\u0010E2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002HE0\rH\u0080\u0080\u0004¢\u0006\u0004\bS\u0010GJ\u0019\u0010R\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rH\u0080\u0080\u0004¢\u0006\u0004\bS\u0010HJ\u001f\u0010T\u001a\u00020\u00152\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004H\u0080\u0080\u0004¢\u0006\u0002\bUJO\u0010V\u001a\u00020\u00152>\u0010W\u001a:\u0012\u0013\u0012\u00110\u0004¢\u0006\f\bY\u0012\b\bZ\u0012\u0004\b\b(\u000b\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r¢\u0006\f\bY\u0012\b\bZ\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00150XH\u0080\u0080\u0004¢\u0006\u0002\b[R\u000f\u0010\u000b\u001a\u00020\u0004X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rX\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\u000fR\u001f\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004@RX\u0096\u008e\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006]"}, d2 = {"Lkotlin/collections/ArrayDeque;", "E", "Lkotlin/collections/AbstractMutableList;", "initialCapacity", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "()V", "elements", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Collection;)V", "head", "elementData", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/Object;", "value", "size", "getSize", "()I", "ensureCapacity", _UrlKt.FRAGMENT_ENCODE_SET, "minCapacity", "copyElements", "newCapacity", "internalGet", "internalIndex", "(I)Ljava/lang/Object;", "positiveMod", "index", "negativeMod", "incremented", "decremented", "isEmpty", _UrlKt.FRAGMENT_ENCODE_SET, "first", "()Ljava/lang/Object;", "firstOrNull", "last", "lastOrNull", "addFirst", "element", "(Ljava/lang/Object;)V", "addLast", "removeFirst", "removeFirstOrNull", "removeLast", "removeLastOrNull", "add", "(Ljava/lang/Object;)Z", "(ILjava/lang/Object;)V", "copyCollectionElements", "addAll", "get", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "contains", "indexOf", "(Ljava/lang/Object;)I", "lastIndexOf", "remove", "removeAt", "removeAll", "retainAll", "filterInPlace", "predicate", "Lkotlin/Function1;", "clear", "toArray", "T", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "()[Ljava/lang/Object;", "removeRange", "fromIndex", "toIndex", "removeRangeShiftPreceding", "removeRangeShiftSucceeding", "nullifyNonEmpty", "internalFromIndex", "internalToIndex", "registerModification", "testToArray", "testToArray$kotlin_stdlib", "testRemoveRange", "testRemoveRange$kotlin_stdlib", "internalStructure", "structure", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "internalStructure$kotlin_stdlib", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nArrayDeque.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayDeque.kt\nkotlin/collections/ArrayDeque\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,673:1\n488#1,53:676\n488#1,53:729\n37#2,2:674\n*S KotlinDebug\n*F\n+ 1 ArrayDeque.kt\nkotlin/collections/ArrayDeque\n*L\n482#1:676,53\n485#1:729,53\n46#1:674,2\n*E\n"})
/* loaded from: classes.dex */
public final class ArrayDeque<E> extends AbstractMutableList<E> {
    public static final Companion Companion = null;
    private static final int defaultMinCapacity = 10;
    private static final Object[] emptyElementData = null;
    private Object[] elementData;
    private int head;
    private int size;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u0006R\u000f\u0010\u0007\u001a\u00020\bX\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lkotlin/collections/ArrayDeque$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "emptyElementData", _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/Object;", "defaultMinCapacity", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        emptyElementData = new Object[0];
    }

    public ArrayDeque() {
        this.elementData = emptyElementData;
    }

    public ArrayDeque(int r1) {
        if (r1 != 0) goto L5;
        Object[] r12 = emptyElementData;
    L7:
        this.elementData = r12;
        return;
    L5:
        if (r1 <= 0) goto L9;
        r12 = new Object[r1];
        goto L7
    L9:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Illegal Capacity: ", r1);
        throw null;
    }

    public ArrayDeque(Collection<? extends E> r2) {
        Object[] r22 = r2.toArray(new Object[0]);
        this.elementData = r22;
        this.size = r22.length;
        if (r22.length != 0) goto L6;
        this.elementData = emptyElementData;
        return;
    }

    private final void copyCollectionElements(int r5, Collection<? extends E> r6) {
        Iterator<? extends E> r0 = r6.iterator();
        int r1 = this.elementData.length;
    L3:
        if (r5 >= r1) goto L7;
        if (r0.hasNext() == false) goto L7;
        this.elementData[r5] = r0.next();
        r5 = r5 + 1;
    L7:
        int r52 = this.head;
        int r12 = 0;
    L8:
        if (r12 >= r52) goto L12;
        if (r0.hasNext() == false) goto L12;
        this.elementData[r12] = r0.next();
        r12 = r12 + 1;
    L12:
        this.size = size() + r6.size();
    }

    private final void copyElements(int r5) {
        Object[] r52 = new Object[r5];
        Object[] r0 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(r0, r52, 0, this.head, r0.length);
        Object[] r02 = this.elementData;
        int r1 = r02.length;
        int r2 = this.head;
        ArraysKt___ArraysJvmKt.copyInto(r02, r52, r1 - r2, 0, r2);
        this.head = 0;
        this.elementData = r52;
    }

    private final int decremented(int r1) {
        if (r1 != 0) goto L6;
        return ArraysKt___ArraysKt.getLastIndex(this.elementData);
    L6:
        return r1 - 1;
    }

    private final void ensureCapacity(int r3) {
        if (r3 < 0) goto L12;
        Object[] r0 = this.elementData;
        if (r3 > r0.length) goto L7;
        return;
    L7:
        if (r0 != emptyElementData) goto L10;
        this.elementData = new Object[RangesKt.coerceAtLeast(r3, 10)];
        return;
    L10:
        copyElements(AbstractList.Companion.newCapacity$kotlin_stdlib(r0.length, r3));
        return;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Deque is too big.");
    }

    private final boolean filterInPlace(Function1<? super E, Boolean> r12) {
        boolean r1 = false;
        r1 = false;
        int r13 = 0;
        r1 = false;
        if (isEmpty() == false) goto L5;
    L35:
        return r1;
    L5:
        if (this.elementData.length == 0) goto L35;
        int r0 = positiveMod(this.head + size());
        int r2 = this.head;
        if (r2 >= r0) goto L18;
        int r5 = r2;
    L10:
        Object[] r6 = this.elementData;
        if (r2 >= r0) goto L17;
        Object r62 = r6[r2];
        if (r12.invoke(r62).booleanValue() == false) goto L15;
        this.elementData[r5] = r62;
        r5 = r5 + 1;
    L16:
        r2 = r2 + 1;
        goto L10
    L15:
        r1 = true;
        goto L16
    L17:
        ArraysKt___ArraysJvmKt.fill(r6, null, r5, r0);
    L33:
        if (r1 == false) goto L35;
        registerModification();
        this.size = negativeMod(r5 - this.head);
        goto L35
    L18:
        int r52 = this.elementData.length;
        boolean r7 = false;
        int r63 = r2;
    L19:
        if (r2 >= r52) goto L25;
        Object[] r8 = this.elementData;
        Object r9 = r8[r2];
        r8[r2] = null;
        if (r12.invoke(r9).booleanValue() == false) goto L23;
        this.elementData[r63] = r9;
        r63 = r63 + 1;
    L24:
        r2 = r2 + 1;
        goto L19
    L23:
        r7 = true;
        goto L24
    L25:
        r5 = positiveMod(r63);
    L26:
        if (r13 >= r0) goto L32;
        Object[] r22 = this.elementData;
        Object r64 = r22[r13];
        r22[r13] = null;
        if (r12.invoke(r64).booleanValue() == false) goto L30;
        this.elementData[r5] = r64;
        r5 = incremented(r5);
    L31:
        r13 = r13 + 1;
        goto L26
    L30:
        r7 = true;
        goto L31
    L32:
        r1 = r7;
        goto L33
    }

    private final int incremented(int r1) {
        if (r1 != ArraysKt___ArraysKt.getLastIndex(this.elementData)) goto L7;
        return 0;
    L7:
        return r1 + 1;
    }

    @InlineOnly
    private final E internalGet(int r1) {
        return (E) this.elementData[r1];
    }

    @InlineOnly
    private final int internalIndex(int r2) {
        return positiveMod(this.head + r2);
    }

    private final int negativeMod(int r1) {
        if (r1 < 0) goto L4;
        return r1;
    L4:
        return r1 + this.elementData.length;
    }

    private final void nullifyNonEmpty(int r4, int r5) {
        Object[] r0 = this.elementData;
        if (r4 >= r5) goto L6;
        ArraysKt___ArraysJvmKt.fill(r0, null, r4, r5);
        return;
    L6:
        ArraysKt___ArraysJvmKt.fill(r0, null, r4, r0.length);
        ArraysKt___ArraysJvmKt.fill(this.elementData, null, 0, r5);
    }

    private final int positiveMod(int r2) {
        Object[] r1 = this.elementData;
        if (r2 >= r1.length) goto L5;
        return r2;
    L5:
        return r2 - r1.length;
    }

    private final void registerModification() {
        ((java.util.AbstractList) this).modCount++;
    }

    private final void removeRangeShiftPreceding(int r7, int r8) {
        int r0 = positiveMod(this.head + (r7 - 1));
        int r82 = positiveMod(this.head + (r8 - 1));
    L3:
        if (r7 <= 0) goto L5;
        int r1 = r0 + 1;
        int r2 = Math.min(r7, Math.min(r1, r82 + 1));
        Object[] r3 = this.elementData;
        int r83 = r82 - r2;
        int r02 = r0 - r2;
        ArraysKt___ArraysJvmKt.copyInto(r3, r3, r83 + 1, r02 + 1, r1);
        r0 = negativeMod(r02);
        r82 = negativeMod(r83);
        r7 = r7 - r2;
        goto L3
    }

    private final void removeRangeShiftSucceeding(int r5, int r6) {
        int r0 = positiveMod(this.head + r6);
        int r52 = positiveMod(this.head + r5);
        int r1 = size();
    L3:
        r1 = r1 - r6;
        if (r1 <= 0) goto L6;
        Object[] r62 = this.elementData;
        r6 = Math.min(r1, Math.min(r62.length - r0, r62.length - r52));
        Object[] r2 = this.elementData;
        int r3 = r0 + r6;
        ArraysKt___ArraysJvmKt.copyInto(r2, r2, r52, r0, r3);
        r0 = positiveMod(r3);
        r52 = positiveMod(r52 + r6);
        goto L3
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    public void add(int r8, E r9) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r8, size());
        if (r8 != size()) goto L6;
        addLast(r9);
        return;
    L6:
        if (r8 != 0) goto L9;
        addFirst(r9);
        return;
    L9:
        registerModification();
        ensureCapacity(size() + 1);
        int r0 = positiveMod(this.head + r8);
        if (r8 >= ((size() + 1) >> 1)) goto L16;
        int r82 = decremented(r0);
        int r02 = decremented(this.head);
        int r2 = this.head;
        Object[] r4 = this.elementData;
        if (r82 < r2) goto L14;
        r4[r02] = r4[r2];
        ArraysKt___ArraysJvmKt.copyInto(r4, r4, r2, r2 + 1, r82 + 1);
    L15:
        this.elementData[r82] = r9;
        this.head = r02;
    L21:
        this.size = size() + 1;
        return;
    L14:
        ArraysKt___ArraysJvmKt.copyInto(r4, r4, r2 - 1, r2, r4.length);
        Object[] r22 = this.elementData;
        r22[r22.length - 1] = r22[0];
        ArraysKt___ArraysJvmKt.copyInto(r22, r22, 0, 1, r82 + 1);
        goto L15
    L16:
        int r83 = positiveMod(this.head + size());
        Object[] r23 = this.elementData;
        if (r0 >= r83) goto L19;
        ArraysKt___ArraysJvmKt.copyInto(r23, r23, r0 + 1, r0, r83);
    L20:
        this.elementData[r0] = r9;
        goto L21
    L19:
        ArraysKt___ArraysJvmKt.copyInto(r23, r23, 1, 0, r83);
        Object[] r84 = this.elementData;
        r84[0] = r84[r84.length - 1];
        ArraysKt___ArraysJvmKt.copyInto(r84, r84, r0 + 1, r0, r84.length - 1);
        goto L20
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    @IgnorableReturnValue
    public boolean add(E r1) {
        addLast(r1);
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    @IgnorableReturnValue
    public boolean addAll(int r9, Collection<? extends E> r10) {
        AbstractList.Companion.checkPositionIndex$kotlin_stdlib(r9, size());
        if (r10.isEmpty() == false) goto L6;
        return false;
    L6:
        if (r9 == size()) goto L8;
        registerModification();
        ensureCapacity(size() + r10.size());
        int r0 = positiveMod(this.head + size());
        int r2 = positiveMod(this.head + r9);
        int r3 = r10.size();
        if (r9 >= ((size() + 1) >> 1)) goto L25;
        int r92 = this.head;
        int r02 = r92 - r3;
        if (r2 < r92) goto L20;
        Object[] r4 = this.elementData;
        if (r02 < 0) goto L16;
        ArraysKt___ArraysJvmKt.copyInto(r4, r4, r02, r92, r2);
    L24:
        this.head = r02;
        copyCollectionElements(negativeMod(r2 - r3), r10);
    L39:
        return true;
    L16:
        r02 = r02 + r4.length;
        int r6 = r2 - r92;
        int r7 = r4.length - r02;
        if (r7 < r6) goto L19;
        ArraysKt___ArraysJvmKt.copyInto(r4, r4, r02, r92, r2);
        goto L24
    L19:
        ArraysKt___ArraysJvmKt.copyInto(r4, r4, r02, r92, r92 + r7);
        Object[] r93 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(r93, r93, 0, this.head + r7, r2);
        goto L24
    L20:
        Object[] r42 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(r42, r42, r02, r92, r42.length);
        Object[] r94 = this.elementData;
        if (r3 < r2) goto L23;
        ArraysKt___ArraysJvmKt.copyInto(r94, r94, r94.length - r3, 0, r2);
        goto L24
    L23:
        ArraysKt___ArraysJvmKt.copyInto(r94, r94, r94.length - r3, 0, r3);
        Object[] r95 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(r95, r95, 0, r3, r2);
        goto L24
    L25:
        int r96 = r2 + r3;
        Object[] r43 = this.elementData;
        if (r2 >= r0) goto L34;
        int r32 = r3 + r0;
        if (r32 > r43.length) goto L31;
        ArraysKt___ArraysJvmKt.copyInto(r43, r43, r96, r2, r0);
    L38:
        copyCollectionElements(r2, r10);
        goto L39
    L31:
        if (r96 < r43.length) goto L33;
        ArraysKt___ArraysJvmKt.copyInto(r43, r43, r96 - r43.length, r2, r0);
        goto L38
    L33:
        int r33 = r0 - (r32 - r43.length);
        ArraysKt___ArraysJvmKt.copyInto(r43, r43, 0, r33, r0);
        Object[] r03 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(r03, r03, r96, r2, r33);
        goto L38
    L34:
        ArraysKt___ArraysJvmKt.copyInto(r43, r43, r3, 0, r0);
        Object[] r04 = this.elementData;
        if (r96 < r04.length) goto L37;
        ArraysKt___ArraysJvmKt.copyInto(r04, r04, r96 - r04.length, r2, r04.length);
        goto L38
    L37:
        ArraysKt___ArraysJvmKt.copyInto(r04, r04, 0, r04.length - r3, r04.length);
        Object[] r05 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(r05, r05, r96, r2, r05.length - r3);
        goto L38
    L8:
        return addAll(r10);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @IgnorableReturnValue
    public boolean addAll(Collection<? extends E> r3) {
        if (r3.isEmpty() == false) goto L6;
        return false;
    L6:
        registerModification();
        ensureCapacity(size() + r3.size());
        copyCollectionElements(positiveMod(this.head + size()), r3);
        return true;
    }

    public final void addFirst(E r3) {
        registerModification();
        ensureCapacity(size() + 1);
        int r0 = decremented(this.head);
        this.head = r0;
        this.elementData[r0] = r3;
        this.size = size() + 1;
    }

    public final void addLast(E r4) {
        registerModification();
        ensureCapacity(size() + 1);
        this.elementData[positiveMod(this.head + size())] = r4;
        this.size = size() + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        if (isEmpty() == true) goto L5;
        registerModification();
        int r0 = positiveMod(this.head + size());
        nullifyNonEmpty(this.head, r0);
    L5:
        this.head = 0;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object r1) {
        if (indexOf(r1) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public final E first() {
        if (isEmpty() == false) goto L5;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("ArrayDeque is empty.");
        return null;
    L5:
        return (E) this.elementData[this.head];
    }

    public final E firstOrNull() {
        if (isEmpty() == false) goto L7;
        return null;
    L7:
        return (E) this.elementData[this.head];
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int r3) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, size());
        return (E) this.elementData[positiveMod(this.head + r3)];
    }

    @Override // kotlin.collections.AbstractMutableList
    public int getSize() {
        return this.size;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object r5) {
        int r0 = positiveMod(this.head + size());
        int r1 = this.head;
        if (r1 >= r0) goto L12;
    L4:
        if (r1 >= r0) goto L36;
        if (Intrinsics.areEqual(r5, this.elementData[r1]) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        int r4 = this.head;
    L9:
        return r1 - r4;
    L36:
        return -1;
    L12:
        if (isEmpty() == true) goto L27;
        r1 = this.head;
        if (r1 < r0) goto L35;
        int r2 = this.elementData.length;
    L16:
        if (r1 >= r2) goto L21;
        if (Intrinsics.areEqual(r5, this.elementData[r1]) == true) goto L19;
        r1 = r1 + 1;
        goto L16
    L19:
        r4 = this.head;
        goto L9
    L21:
        int r12 = 0;
    L22:
        if (r12 >= r0) goto L37;
        if (Intrinsics.areEqual(r5, this.elementData[r12]) == true) goto L25;
        r12 = r12 + 1;
        goto L22
    L25:
        r1 = r12 + this.elementData.length;
        r4 = this.head;
        goto L9
    L37:
        return -1;
    L35:
        return -1;
    L27:
        return -1;
    }

    public final void internalStructure$kotlin_stdlib(Function2<? super Integer, ? super Object[], Unit> r3) {
        int r0 = positiveMod(this.head + size());
        if (isEmpty() == true) goto L8;
        int r1 = this.head;
        if (r1 < r0) goto L8;
        int r12 = r1 - this.elementData.length;
    L9:
        r3.invoke(Integer.valueOf(r12), toArray());
        return;
    L8:
        r12 = this.head;
        goto L9
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        if (size() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public final E last() {
        if (isEmpty() == false) goto L5;
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("ArrayDeque is empty.");
        return null;
    L5:
        return (E) this.elementData[positiveMod(this.head + CollectionsKt__CollectionsKt.getLastIndex(this))];
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object r5) {
        int r0 = positiveMod(this.head + size());
        int r1 = this.head;
        if (r1 >= r0) goto L14;
        int r02 = r0 - 1;
        if (r1 <= r02) goto L7;
    L31:
        return -1;
    L7:
        if (Intrinsics.areEqual(r5, this.elementData[r02]) == true) goto L8;
        if (r02 == r1) goto L31;
        r02 = r02 - 1;
        goto L7
    L8:
        int r4 = this.head;
    L10:
        return r02 - r4;
    L14:
        if (isEmpty() == true) goto L31;
        if (this.head < r0) goto L31;
    L18:
        r0 = r0 - 1;
        Object[] r12 = this.elementData;
        if ((-1) >= r0) goto L24;
        if (Intrinsics.areEqual(r5, r12[r0]) == false) goto L18;
        r02 = r0 + this.elementData.length;
        r4 = this.head;
        goto L10
    L24:
        r02 = ArraysKt___ArraysKt.getLastIndex(r12);
        int r13 = this.head;
        if (r13 > r02) goto L31;
    L27:
        if (Intrinsics.areEqual(r5, this.elementData[r02]) == true) goto L28;
        if (r02 == r13) goto L31;
        r02 = r02 - 1;
        goto L27
    L28:
        r4 = this.head;
        goto L10
    }

    public final E lastOrNull() {
        if (isEmpty() == false) goto L7;
        return null;
    L7:
        return (E) this.elementData[positiveMod(this.head + CollectionsKt__CollectionsKt.getLastIndex(this))];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @IgnorableReturnValue
    public boolean remove(Object r2) {
        int r22 = indexOf(r2);
        if (r22 != (-1)) goto L6;
        return false;
    L6:
        removeAt(r22);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @IgnorableReturnValue
    public boolean removeAll(Collection<?> r12) {
        boolean r1 = false;
        r1 = false;
        int r13 = 0;
        r1 = false;
        if (isEmpty() == false) goto L5;
    L35:
        return r1;
    L5:
        if (this.elementData.length == 0) goto L35;
        int r0 = positiveMod(this.head + size());
        int r2 = this.head;
        if (r2 >= r0) goto L18;
        int r5 = r2;
    L10:
        Object[] r6 = this.elementData;
        if (r2 >= r0) goto L17;
        Object r62 = r6[r2];
        if (r12.contains(r62) == true) goto L15;
        this.elementData[r5] = r62;
        r5 = r5 + 1;
    L16:
        r2 = r2 + 1;
        goto L10
    L15:
        r1 = true;
        goto L16
    L17:
        ArraysKt___ArraysJvmKt.fill(r6, null, r5, r0);
    L33:
        if (r1 == false) goto L35;
        registerModification();
        this.size = negativeMod(r5 - this.head);
        goto L35
    L18:
        int r52 = this.elementData.length;
        boolean r7 = false;
        int r63 = r2;
    L19:
        if (r2 >= r52) goto L25;
        Object[] r8 = this.elementData;
        Object r9 = r8[r2];
        r8[r2] = null;
        if (r12.contains(r9) == true) goto L23;
        this.elementData[r63] = r9;
        r63 = r63 + 1;
    L24:
        r2 = r2 + 1;
        goto L19
    L23:
        r7 = true;
        goto L24
    L25:
        r5 = positiveMod(r63);
    L26:
        if (r13 >= r0) goto L32;
        Object[] r22 = this.elementData;
        Object r64 = r22[r13];
        r22[r13] = null;
        if (r12.contains(r64) == true) goto L30;
        this.elementData[r5] = r64;
        r5 = incremented(r5);
    L31:
        r13 = r13 + 1;
        goto L26
    L30:
        r7 = true;
        goto L31
    L32:
        r1 = r7;
        goto L33
    }

    @Override // kotlin.collections.AbstractMutableList
    @IgnorableReturnValue
    public E removeAt(int r9) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(r9, size());
        if (r9 == CollectionsKt__CollectionsKt.getLastIndex(this)) goto L5;
        if (r9 == 0) goto L8;
        registerModification();
        int r0 = positiveMod(this.head + r9);
        E r1 = (E) this.elementData[r0];
        int r2 = size() >> 1;
        int r4 = this.head;
        if (r9 >= r2) goto L16;
        Object[] r92 = this.elementData;
        if (r0 < r4) goto L14;
        ArraysKt___ArraysJvmKt.copyInto(r92, r92, r4 + 1, r4, r0);
    L15:
        Object[] r93 = this.elementData;
        int r02 = this.head;
        r93[r02] = null;
        this.head = incremented(r02);
    L21:
        this.size = size() - 1;
        return r1;
    L14:
        ArraysKt___ArraysJvmKt.copyInto(r92, r92, 1, 0, r0);
        Object[] r94 = this.elementData;
        r94[0] = r94[r94.length - 1];
        int r03 = this.head;
        ArraysKt___ArraysJvmKt.copyInto(r94, r94, r03 + 1, r03, r94.length - 1);
        goto L15
    L16:
        int r95 = positiveMod(r4 + CollectionsKt__CollectionsKt.getLastIndex(this));
        Object[] r22 = this.elementData;
        if (r0 > r95) goto L19;
        ArraysKt___ArraysJvmKt.copyInto(r22, r22, r0, r0 + 1, r95 + 1);
    L20:
        this.elementData[r95] = null;
        goto L21
    L19:
        ArraysKt___ArraysJvmKt.copyInto(r22, r22, r0, r0 + 1, r22.length);
        Object[] r04 = this.elementData;
        r04[r04.length - 1] = r04[0];
        ArraysKt___ArraysJvmKt.copyInto(r04, r04, 0, 1, r95 + 1);
        goto L20
    L8:
        return removeFirst();
    L5:
        return removeLast();
    }

    @IgnorableReturnValue
    public final E removeFirst() {
        if (isEmpty() == true) goto L6;
        registerModification();
        Object[] r0 = this.elementData;
        int r1 = this.head;
        E r2 = (E) r0[r1];
        r0[r1] = null;
        this.head = incremented(r1);
        this.size = size() - 1;
        return r2;
    L6:
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("ArrayDeque is empty.");
        return null;
    }

    @IgnorableReturnValue
    public final E removeFirstOrNull() {
        if (isEmpty() == false) goto L7;
        return null;
    L7:
        return removeFirst();
    }

    @IgnorableReturnValue
    public final E removeLast() {
        if (isEmpty() == true) goto L6;
        registerModification();
        int r0 = positiveMod(this.head + CollectionsKt__CollectionsKt.getLastIndex(this));
        Object[] r1 = this.elementData;
        E r2 = (E) r1[r0];
        r1[r0] = null;
        this.size = size() - 1;
        return r2;
    L6:
        UIntArray$Iterator$$ExternalSyntheticBUOutline0.m("ArrayDeque is empty.");
        return null;
    }

    @IgnorableReturnValue
    public final E removeLastOrNull() {
        if (isEmpty() == false) goto L7;
        return null;
    L7:
        return removeLast();
    }

    @Override // java.util.AbstractList
    public void removeRange(int r3, int r4) {
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(r3, r4, size());
        int r0 = r4 - r3;
        if (r0 != 0) goto L6;
        return;
    L6:
        if (r0 != size()) goto L10;
        clear();
        return;
    L10:
        if (r0 != 1) goto L13;
        removeAt(r3);
        return;
    L13:
        registerModification();
        if (r3 >= (size() - r4)) goto L16;
        removeRangeShiftPreceding(r3, r4);
        int r32 = positiveMod(this.head + r0);
        nullifyNonEmpty(this.head, r32);
        this.head = r32;
    L17:
        this.size = size() - r0;
        return;
    L16:
        removeRangeShiftSucceeding(r3, r4);
        int r33 = positiveMod(this.head + size());
        nullifyNonEmpty(negativeMod(r33 - r0), r33);
        goto L17
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    @IgnorableReturnValue
    public boolean retainAll(Collection<?> r12) {
        boolean r1 = false;
        r1 = false;
        int r13 = 0;
        r1 = false;
        if (isEmpty() == false) goto L5;
    L35:
        return r1;
    L5:
        if (this.elementData.length == 0) goto L35;
        int r0 = positiveMod(this.head + size());
        int r2 = this.head;
        if (r2 >= r0) goto L18;
        int r5 = r2;
    L10:
        Object[] r6 = this.elementData;
        if (r2 >= r0) goto L17;
        Object r62 = r6[r2];
        if (r12.contains(r62) == false) goto L15;
        this.elementData[r5] = r62;
        r5 = r5 + 1;
    L16:
        r2 = r2 + 1;
        goto L10
    L15:
        r1 = true;
        goto L16
    L17:
        ArraysKt___ArraysJvmKt.fill(r6, null, r5, r0);
    L33:
        if (r1 == false) goto L35;
        registerModification();
        this.size = negativeMod(r5 - this.head);
        goto L35
    L18:
        int r52 = this.elementData.length;
        boolean r7 = false;
        int r63 = r2;
    L19:
        if (r2 >= r52) goto L25;
        Object[] r8 = this.elementData;
        Object r9 = r8[r2];
        r8[r2] = null;
        if (r12.contains(r9) == false) goto L23;
        this.elementData[r63] = r9;
        r63 = r63 + 1;
    L24:
        r2 = r2 + 1;
        goto L19
    L23:
        r7 = true;
        goto L24
    L25:
        r5 = positiveMod(r63);
    L26:
        if (r13 >= r0) goto L32;
        Object[] r22 = this.elementData;
        Object r64 = r22[r13];
        r22[r13] = null;
        if (r12.contains(r64) == false) goto L30;
        this.elementData[r5] = r64;
        r5 = incremented(r5);
    L31:
        r13 = r13 + 1;
        goto L26
    L30:
        r7 = true;
        goto L31
    L32:
        r1 = r7;
        goto L33
    }

    @Override // kotlin.collections.AbstractMutableList, java.util.AbstractList, java.util.List
    @IgnorableReturnValue
    public E set(int r3, E r4) {
        AbstractList.Companion.checkElementIndex$kotlin_stdlib(r3, size());
        int r32 = positiveMod(this.head + r3);
        Object[] r2 = this.elementData;
        E r0 = (E) r2[r32];
        r2[r32] = r4;
        return r0;
    }

    public final void testRemoveRange$kotlin_stdlib(int r1, int r2) {
        removeRange(r1, r2);
    }

    public final Object[] testToArray$kotlin_stdlib() {
        return toArray();
    }

    public final <T> T[] testToArray$kotlin_stdlib(T[] r1) {
        return (T[]) toArray(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] r8) {
        if (r8.length < size()) goto L5;
    L4:
        T[] r1 = r8;
        int r4 = positiveMod(this.head + size());
        int r3 = this.head;
        if (r3 >= r4) goto L10;
        ArraysKt___ArraysJvmKt.copyInto$default(this.elementData, r1, 0, r3, r4, 2, null);
    L13:
        return (T[]) CollectionsKt__CollectionsJVMKt.terminateCollectionToArray(size(), r1);
    L10:
        if (isEmpty() == true) goto L13;
        Object[] r82 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(r82, r1, 0, this.head, r82.length);
        Object[] r83 = this.elementData;
        ArraysKt___ArraysJvmKt.copyInto(r83, r1, r83.length - this.head, 0, r4);
        goto L13
    L5:
        r8 = (T[]) ArraysKt__ArraysJVMKt.arrayOfNulls(r8, size());
        goto L4
    }
}
