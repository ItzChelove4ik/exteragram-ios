package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import androidx.collection.internal.RuntimeHelpersKt;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001a\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\u000b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\n\u001a\u00020\tH\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u000b\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000b\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\tH\u0017¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0012\u0010\u0011J\u0017\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0014\u0010\u0006J\u001f\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010#\u001a\u00020\u001a2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b#\u0010$J\u000f\u0010%\u001a\u00020\u000fH\u0016¢\u0006\u0004\b%\u0010&J\u001f\u0010'\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0004\b'\u0010\u0017J\u000f\u0010)\u001a\u00020(H\u0016¢\u0006\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010.\u001a\u00020-8\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u001e\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u000101008\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u0010\u0018\u001a\u00020\u00038\u0000@\u0000X\u0081\u000e¢\u0006\u0006\n\u0004\b\u0018\u00104¨\u00065"}, d2 = {"Landroidx/collection/LongSparseArray;", "E", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "initialCapacity", "<init>", "(I)V", "clone", "()Landroidx/collection/LongSparseArray;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, "get", "(J)Ljava/lang/Object;", "defaultValue", "(JLjava/lang/Object;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "delete", "(J)V", "remove", "index", "removeAt", "value", "put", "(JLjava/lang/Object;)V", "size", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "isEmpty", "()Z", "keyAt", "(I)J", "valueAt", "(I)Ljava/lang/Object;", "indexOfKey", "(J)I", "containsKey", "(J)Z", "clear", "()V", "append", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "garbage", "Z", _UrlKt.FRAGMENT_ENCODE_SET, "keys", "[J", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "values", "[Ljava/lang/Object;", "I", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLongSparseArray.jvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LongSparseArray.jvm.kt\nandroidx/collection/LongSparseArray\n+ 2 LongSparseArray.kt\nandroidx/collection/LongSparseArrayKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 RuntimeHelpers.kt\nandroidx/collection/internal/RuntimeHelpersKt\n*L\n1#1,243:1\n218#2:244\n229#2,5:245\n223#2,11:250\n239#2,8:261\n239#2,8:269\n250#2,9:277\n263#2,5:286\n271#2,7:291\n286#2,9:298\n320#2,12:307\n299#2,18:319\n334#2,21:337\n358#2,2:358\n360#2:361\n364#2,5:362\n373#2,2:367\n299#2,18:369\n376#2:387\n380#2:388\n384#2:389\n385#2:393\n388#2,2:395\n299#2,18:397\n391#2:415\n396#2:416\n397#2:420\n400#2,2:422\n299#2,18:424\n404#2:442\n409#2:443\n410#2:447\n413#2,2:449\n299#2,18:451\n416#2,2:469\n421#2,2:471\n299#2,18:473\n424#2:491\n429#2,2:492\n299#2,18:494\n432#2,6:512\n442#2:518\n447#2:519\n452#2,8:520\n463#2,6:528\n299#2,18:534\n470#2,10:552\n483#2,21:562\n1#3:360\n59#4,3:390\n63#4:394\n59#4,3:417\n63#4:421\n59#4,3:444\n63#4:448\n*S KotlinDebug\n*F\n+ 1 LongSparseArray.jvm.kt\nandroidx/collection/LongSparseArray\n*L\n92#1:244\n92#1:245,5\n99#1:250,11\n103#1:261,8\n106#1:269,8\n115#1:277,9\n118#1:286,5\n127#1:291,7\n138#1:298,9\n144#1:307,12\n144#1:319,18\n144#1:337,21\n150#1:358,2\n150#1:361\n161#1:362,5\n164#1:367,2\n164#1:369,18\n164#1:387\n171#1:388\n183#1:389\n183#1:393\n183#1:395,2\n183#1:397,18\n183#1:415\n195#1:416\n195#1:420\n195#1:422,2\n195#1:424,18\n195#1:442\n203#1:443\n203#1:447\n203#1:449,2\n203#1:451,18\n203#1:469,2\n209#1:471,2\n209#1:473,18\n209#1:491\n218#1:492,2\n218#1:494,18\n218#1:512,6\n221#1:518\n224#1:519\n227#1:520,8\n233#1:528,6\n233#1:534,18\n233#1:552,10\n241#1:562,21\n150#1:360\n183#1:390,3\n183#1:394\n195#1:417,3\n195#1:421\n203#1:444,3\n203#1:448\n*E\n"})
/* loaded from: classes.dex */
public class LongSparseArray<E> implements Cloneable {

    @JvmField
    public /* synthetic */ boolean garbage;

    @JvmField
    public /* synthetic */ long[] keys;

    @JvmField
    public /* synthetic */ int size;

    @JvmField
    public /* synthetic */ Object[] values;

    @JvmOverloads
    public LongSparseArray() {
        this(0, 1, null);
    }

    @JvmOverloads
    public LongSparseArray(int r2) {
        if (r2 != 0) goto L6;
        this.keys = ContainerHelpersKt.EMPTY_LONGS;
        this.values = ContainerHelpersKt.EMPTY_OBJECTS;
        return;
    L6:
        int r22 = ContainerHelpersKt.idealLongArraySize(r2);
        this.keys = new long[r22];
        this.values = new Object[r22];
    }

    public /* synthetic */ LongSparseArray(int r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = 10;
    L5:
        this(r1);
    }

    public void append(long r10, E r12) {
        int r0 = this.size;
        if (r0 == 0) goto L9;
        if (r10 > this.keys[r0 - 1]) goto L9;
        put(r10, r12);
        return;
    L9:
        if (this.garbage == false) goto L21;
        long[] r1 = this.keys;
        if (r0 < r1.length) goto L21;
        Object[] r2 = this.values;
        int r4 = 0;
        int r5 = 0;
    L13:
        if (r4 >= r0) goto L20;
        Object r6 = r2[r4];
        if (r6 == LongSparseArrayKt.access$getDELETED$p()) goto L19;
        if (r4 == r5) goto L18;
        r1[r5] = r1[r4];
        r2[r5] = r6;
        r2[r4] = null;
    L18:
        r5 = r5 + 1;
    L19:
        r4 = r4 + 1;
        goto L13
    L20:
        this.garbage = false;
        this.size = r5;
    L21:
        int r02 = this.size;
        if (r02 < this.keys.length) goto L24;
        int r13 = ContainerHelpersKt.idealLongArraySize(r02 + 1);
        this.keys = Arrays.copyOf(this.keys, r13);
        this.values = Arrays.copyOf(this.values, r13);
    L24:
        this.keys[r02] = r10;
        this.values[r02] = r12;
        this.size = r02 + 1;
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

    public LongSparseArray<E> clone() {
        LongSparseArray<E> r0 = (LongSparseArray) super.clone();
        r0.keys = (long[]) this.keys.clone();
        r0.values = (Object[]) this.values.clone();
        return r0;
    }

    /* renamed from: clone, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object m665clone() {
        return clone();
    }

    public boolean containsKey(long r1) {
        if (indexOfKey(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Deprecated(message = "Alias for `remove(key)`.", replaceWith = @ReplaceWith(expression = "remove(key)", imports = {}))
    public void delete(long r3) {
        int r32 = ContainerHelpersKt.binarySearch(this.keys, this.size, r3);
        if (r32 >= 0) goto L5;
        return;
    L5:
        if (this.values[r32] == LongSparseArrayKt.access$getDELETED$p()) goto L9;
        this.values[r32] = LongSparseArrayKt.access$getDELETED$p();
        this.garbage = true;
        return;
    }

    public E get(long r3) {
        int r32 = ContainerHelpersKt.binarySearch(this.keys, this.size, r3);
        if (r32 >= 0) goto L5;
        return null;
    L5:
        if (this.values[r32] != LongSparseArrayKt.access$getDELETED$p()) goto L8;
        return null;
    L8:
        return (E) this.values[r32];
    }

    public E get(long r3, E r5) {
        int r32 = ContainerHelpersKt.binarySearch(this.keys, this.size, r3);
        if (r32 >= 0) goto L5;
    L9:
        return r5;
    L5:
        if (this.values[r32] == LongSparseArrayKt.access$getDELETED$p()) goto L9;
        return (E) this.values[r32];
    }

    public int indexOfKey(long r10) {
        if (this.garbage == false) goto L14;
        int r0 = this.size;
        long[] r1 = this.keys;
        Object[] r2 = this.values;
        int r4 = 0;
        int r5 = 0;
    L5:
        if (r4 >= r0) goto L12;
        Object r6 = r2[r4];
        if (r6 == LongSparseArrayKt.access$getDELETED$p()) goto L11;
        if (r4 == r5) goto L10;
        r1[r5] = r1[r4];
        r2[r5] = r6;
        r2[r4] = null;
    L10:
        r5 = r5 + 1;
    L11:
        r4 = r4 + 1;
        goto L5
    L12:
        this.garbage = false;
        this.size = r5;
    L14:
        return ContainerHelpersKt.binarySearch(this.keys, this.size, r10);
    }

    public boolean isEmpty() {
        if (size() != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public long keyAt(int r10) {
        if (r10 >= 0) goto L5;
    L7:
        boolean r1 = false;
    L8:
        if (r1 == true) goto L11;
        RuntimeHelpersKt.throwIllegalArgumentException("Expected index to be within 0..size()-1, but was " + r10);
    L11:
        if (this.garbage == false) goto L22;
        int r12 = this.size;
        long[] r2 = this.keys;
        Object[] r3 = this.values;
        int r4 = 0;
        int r5 = 0;
    L13:
        if (r4 >= r12) goto L20;
        Object r6 = r3[r4];
        if (r6 == LongSparseArrayKt.access$getDELETED$p()) goto L19;
        if (r4 == r5) goto L18;
        r2[r5] = r2[r4];
        r3[r5] = r6;
        r3[r4] = null;
    L18:
        r5 = r5 + 1;
    L19:
        r4 = r4 + 1;
        goto L13
    L20:
        this.garbage = false;
        this.size = r5;
    L22:
        return this.keys[r10];
    L5:
        if (r10 >= this.size) goto L7;
        r1 = true;
        goto L8
    }

    public void put(long r10, E r12) {
        int r0 = ContainerHelpersKt.binarySearch(this.keys, this.size, r10);
        if (r0 < 0) goto L6;
        this.values[r0] = r12;
        return;
    L6:
        int r02 = ~r0;
        if (r02 >= this.size) goto L13;
        if (this.values[r02] != LongSparseArrayKt.access$getDELETED$p()) goto L13;
        this.keys[r02] = r10;
        this.values[r02] = r12;
        return;
    L13:
        if (this.garbage == false) goto L25;
        int r1 = this.size;
        long[] r2 = this.keys;
        if (r1 < r2.length) goto L25;
        Object[] r03 = this.values;
        int r4 = 0;
        int r5 = 0;
    L17:
        if (r4 >= r1) goto L24;
        Object r6 = r03[r4];
        if (r6 == LongSparseArrayKt.access$getDELETED$p()) goto L23;
        if (r4 == r5) goto L22;
        r2[r5] = r2[r4];
        r03[r5] = r6;
        r03[r4] = null;
    L22:
        r5 = r5 + 1;
    L23:
        r4 = r4 + 1;
        goto L17
    L24:
        this.garbage = false;
        this.size = r5;
        r02 = ~ContainerHelpersKt.binarySearch(this.keys, r5, r10);
    L25:
        int r13 = this.size;
        if (r13 < this.keys.length) goto L28;
        int r14 = ContainerHelpersKt.idealLongArraySize(r13 + 1);
        this.keys = Arrays.copyOf(this.keys, r14);
        this.values = Arrays.copyOf(this.values, r14);
    L28:
        int r15 = this.size;
        if ((r15 - r02) == 0) goto L31;
        long[] r22 = this.keys;
        int r3 = r02 + 1;
        ArraysKt.copyInto(r22, r22, r3, r02, r15);
        Object[] r16 = this.values;
        ArraysKt.copyInto(r16, r16, r3, r02, this.size);
    L31:
        this.keys[r02] = r10;
        this.values[r02] = r12;
        this.size++;
    }

    public void remove(long r3) {
        int r32 = ContainerHelpersKt.binarySearch(this.keys, this.size, r3);
        if (r32 >= 0) goto L5;
        return;
    L5:
        if (this.values[r32] == LongSparseArrayKt.access$getDELETED$p()) goto L9;
        this.values[r32] = LongSparseArrayKt.access$getDELETED$p();
        this.garbage = true;
        return;
    }

    public void removeAt(int r3) {
        if (this.values[r3] == LongSparseArrayKt.access$getDELETED$p()) goto L6;
        this.values[r3] = LongSparseArrayKt.access$getDELETED$p();
        this.garbage = true;
        return;
    }

    public int size() {
        if (this.garbage == false) goto L14;
        int r0 = this.size;
        long[] r1 = this.keys;
        Object[] r2 = this.values;
        int r4 = 0;
        int r5 = 0;
    L5:
        if (r4 >= r0) goto L12;
        Object r6 = r2[r4];
        if (r6 == LongSparseArrayKt.access$getDELETED$p()) goto L11;
        if (r4 == r5) goto L10;
        r1[r5] = r1[r4];
        r2[r5] = r6;
        r2[r4] = null;
    L10:
        r5 = r5 + 1;
    L11:
        r4 = r4 + 1;
        goto L5
    L12:
        this.garbage = false;
        this.size = r5;
    L14:
        return this.size;
    }

    public String toString() {
        if (size() > 0) goto L6;
        return "{}";
    L6:
        StringBuilder r1 = new StringBuilder(this.size * 28);
        r1.append('{');
        int r0 = this.size;
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L15;
        if (r2 <= 0) goto L10;
        r1.append(", ");
    L10:
        r1.append(keyAt(r2));
        r1.append(SignatureVisitor.INSTANCEOF);
        E r3 = valueAt(r2);
        if (r3 == r1) goto L13;
        r1.append(r3);
    L14:
        r2 = r2 + 1;
        goto L7
    L13:
        r1.append("(this Map)");
        goto L14
    L15:
        r1.append('}');
        return r1.toString();
    }

    public E valueAt(int r10) {
        if (r10 >= 0) goto L5;
    L7:
        boolean r1 = false;
    L8:
        if (r1 == true) goto L11;
        RuntimeHelpersKt.throwIllegalArgumentException("Expected index to be within 0..size()-1, but was " + r10);
    L11:
        if (this.garbage == false) goto L22;
        int r12 = this.size;
        long[] r2 = this.keys;
        Object[] r3 = this.values;
        int r4 = 0;
        int r5 = 0;
    L13:
        if (r4 >= r12) goto L20;
        Object r6 = r3[r4];
        if (r6 == LongSparseArrayKt.access$getDELETED$p()) goto L19;
        if (r4 == r5) goto L18;
        r2[r5] = r2[r4];
        r3[r5] = r6;
        r3[r4] = null;
    L18:
        r5 = r5 + 1;
    L19:
        r4 = r4 + 1;
        goto L13
    L20:
        this.garbage = false;
        this.size = r5;
    L22:
        return (E) this.values[r10];
    L5:
        if (r10 >= this.size) goto L7;
        r1 = true;
        goto L8
    }
}
