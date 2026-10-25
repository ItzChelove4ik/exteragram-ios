package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001a\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\n\u001a\u0004\u0018\u00018\u00002\u0006\u0010\t\u001a\u00020\u0003H\u0096\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0015\u0010\u000bJ\u0017\u0010\u0016\u001a\u00020\u00032\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001a\u0010\u000fJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u001e8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020!8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u001e\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010%0$8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010\u0010\u001a\u00020\u00038\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010(¨\u0006)"}, d2 = {"Landroidx/collection/SparseArrayCompat;", "E", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "initialCapacity", "<init>", "(I)V", "clone", "()Landroidx/collection/SparseArrayCompat;", PluginsConstants.Settings.KEY, "get", "(I)Ljava/lang/Object;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "put", "(ILjava/lang/Object;)V", "size", "()I", "index", "keyAt", "(I)I", "valueAt", "indexOfValue", "(Ljava/lang/Object;)I", "clear", "()V", "append", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "garbage", "Z", _UrlKt.FRAGMENT_ENCODE_SET, "keys", "[I", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "values", "[Ljava/lang/Object;", "I", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSparseArrayCompat.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SparseArrayCompat.jvm.kt\nandroidx/collection/SparseArrayCompat\n+ 2 SparseArrayCompat.kt\nandroidx/collection/SparseArrayCompatKt\n+ 3 CollectionPlatformUtils.jvm.kt\nandroidx/collection/CollectionPlatformUtils\n*L\n1#1,263:1\n250#2,9:264\n263#2,5:273\n271#2,5:278\n279#2,7:283\n294#2,9:290\n327#2,30:299\n360#2,2:329\n327#2,37:331\n367#2,3:368\n327#2,30:371\n371#2:401\n376#2,4:402\n383#2:406\n387#2,4:407\n395#2,5:411\n401#2:417\n406#2,5:418\n414#2,4:423\n422#2,9:427\n435#2:436\n440#2:437\n422#2,9:438\n445#2,8:447\n456#2,17:455\n476#2,21:472\n24#3:416\n*S KotlinDebug\n*F\n+ 1 SparseArrayCompat.jvm.kt\nandroidx/collection/SparseArrayCompat\n*L\n123#1:264,9\n126#1:273,5\n135#1:278,5\n144#1:283,7\n155#1:290,9\n161#1:299,30\n168#1:329,2\n168#1:331,37\n179#1:368,3\n179#1:371,30\n179#1:401\n182#1:402,4\n198#1:406\n204#1:407,4\n210#1:411,5\n210#1:417\n216#1:418,5\n226#1:423,4\n238#1:427,9\n241#1:436\n244#1:437\n244#1:438,9\n247#1:447,8\n253#1:455,17\n261#1:472,21\n210#1:416\n*E\n"})
/* loaded from: classes3.dex */
public class SparseArrayCompat<E> implements Cloneable {

    @JvmField
    public /* synthetic */ boolean garbage;

    @JvmField
    public /* synthetic */ int[] keys;

    @JvmField
    public /* synthetic */ int size;

    @JvmField
    public /* synthetic */ Object[] values;

    @JvmOverloads
    public SparseArrayCompat() {
        this(0, 1, null);
    }

    @JvmOverloads
    public SparseArrayCompat(int r2) {
        if (r2 != 0) goto L6;
        this.keys = ContainerHelpersKt.EMPTY_INTS;
        this.values = ContainerHelpersKt.EMPTY_OBJECTS;
        return;
    L6:
        int r22 = ContainerHelpersKt.idealIntArraySize(r2);
        this.keys = new int[r22];
        this.values = new Object[r22];
    }

    public /* synthetic */ SparseArrayCompat(int r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = 10;
    L5:
        this(r1);
    }

    public void append(int r4, E r5) {
        int r0 = this.size;
        if (r0 == 0) goto L9;
        if (r4 > this.keys[r0 - 1]) goto L9;
        put(r4, r5);
        return;
    L9:
        if (this.garbage == true) goto L11;
    L13:
        int r02 = this.size;
        if (r02 < this.keys.length) goto L16;
        int r1 = ContainerHelpersKt.idealIntArraySize(r02 + 1);
        this.keys = Arrays.copyOf(this.keys, r1);
        this.values = Arrays.copyOf(this.values, r1);
    L16:
        this.keys[r02] = r4;
        this.values[r02] = r5;
        this.size = r02 + 1;
        return;
    L11:
        if (r0 < this.keys.length) goto L13;
        SparseArrayCompatKt.access$gc(this);
        goto L13
    }

    public void clear() {
        int r0 = this.size;
        Object[] r1 = this.values;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        r1[r3] = null;
        r3 = r3 + 1;
        goto L3
    L5:
        this.size = 0;
        this.garbage = false;
    }

    public SparseArrayCompat<E> clone() {
        SparseArrayCompat<E> r0 = (SparseArrayCompat) super.clone();
        r0.keys = (int[]) this.keys.clone();
        r0.values = (Object[]) this.values.clone();
        return r0;
    }

    /* renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m666clone() {
        return clone();
    }

    public E get(int r1) {
        return (E) SparseArrayCompatKt.commonGet(this, r1);
    }

    public int indexOfValue(E r4) {
        if (this.garbage == false) goto L5;
        SparseArrayCompatKt.access$gc(this);
    L5:
        int r0 = this.size;
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L11;
        if (this.values[r1] == r4) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return r1;
    L11:
        return -1;
    }

    public int keyAt(int r2) {
        if (this.garbage == false) goto L6;
        SparseArrayCompatKt.access$gc(this);
    L6:
        return this.keys[r2];
    }

    public void put(int r5, E r6) {
        int r0 = ContainerHelpersKt.binarySearch(this.keys, this.size, r5);
        if (r0 < 0) goto L6;
        this.values[r0] = r6;
        return;
    L6:
        int r02 = ~r0;
        if (r02 >= this.size) goto L13;
        if (this.values[r02] != SparseArrayCompatKt.access$getDELETED$p()) goto L13;
        this.keys[r02] = r5;
        this.values[r02] = r6;
        return;
    L13:
        if (this.garbage == true) goto L15;
    L17:
        int r1 = this.size;
        if (r1 < this.keys.length) goto L20;
        int r12 = ContainerHelpersKt.idealIntArraySize(r1 + 1);
        this.keys = Arrays.copyOf(this.keys, r12);
        this.values = Arrays.copyOf(this.values, r12);
    L20:
        int r13 = this.size;
        if ((r13 - r02) == 0) goto L23;
        int[] r2 = this.keys;
        int r3 = r02 + 1;
        ArraysKt.copyInto(r2, r2, r3, r02, r13);
        Object[] r14 = this.values;
        ArraysKt.copyInto(r14, r14, r3, r02, this.size);
    L23:
        this.keys[r02] = r5;
        this.values[r02] = r6;
        this.size++;
        return;
    L15:
        if (this.size < this.keys.length) goto L17;
        SparseArrayCompatKt.access$gc(this);
        r02 = ~ContainerHelpersKt.binarySearch(this.keys, this.size, r5);
        goto L17
    }

    public int size() {
        if (this.garbage == false) goto L6;
        SparseArrayCompatKt.access$gc(this);
    L6:
        return this.size;
    }

    public String toString() {
        if (size() > 0) goto L6;
        return "{}";
    L6:
        StringBuilder r0 = new StringBuilder(this.size * 28);
        r0.append('{');
        int r1 = this.size;
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L15;
        if (r2 <= 0) goto L10;
        r0.append(", ");
    L10:
        r0.append(keyAt(r2));
        r0.append(SignatureVisitor.INSTANCEOF);
        E r3 = valueAt(r2);
        if (r3 == this) goto L13;
        r0.append(r3);
    L14:
        r2 = r2 + 1;
        goto L7
    L13:
        r0.append("(this Map)");
        goto L14
    L15:
        r0.append('}');
        return r0.toString();
    }

    public E valueAt(int r2) {
        if (this.garbage == false) goto L5;
        SparseArrayCompatKt.access$gc(this);
    L5:
        Object[] r1 = this.values;
        if (r2 < r1.length) goto L8;
        CollectionPlatformUtils r12 = CollectionPlatformUtils.INSTANCE;
        throw new ArrayIndexOutOfBoundsException();
    L8:
        return (E) r1[r2];
    }
}
