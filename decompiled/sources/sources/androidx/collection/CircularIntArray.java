package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u0005J\r\u0010\u000b\u001a\u00020\u0002¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\bJ\r\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0015¨\u0006\u0018"}, d2 = {"Landroidx/collection/CircularIntArray;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "minCapacity", "<init>", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "doubleCapacity", "()V", "element", "addLast", "popFirst", "()I", "clear", _UrlKt.FRAGMENT_ENCODE_SET, "isEmpty", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "elements", "[I", "head", "I", "tail", "capacityBitmask", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCircularIntArray.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CircularIntArray.kt\nandroidx/collection/CircularIntArray\n+ 2 RuntimeHelpers.kt\nandroidx/collection/internal/RuntimeHelpersKt\n+ 3 CollectionPlatformUtils.jvm.kt\nandroidx/collection/CollectionPlatformUtils\n*L\n1#1,212:1\n59#2,5:213\n59#2,5:218\n24#3:223\n24#3:224\n24#3:225\n24#3:226\n24#3:227\n24#3:228\n24#3:229\n*S KotlinDebug\n*F\n+ 1 CircularIntArray.kt\nandroidx/collection/CircularIntArray\n*L\n37#1:213,5\n38#1:218,5\n101#1:223\n114#1:224\n138#1:225\n155#1:226\n168#1:227\n180#1:228\n192#1:229\n*E\n"})
/* loaded from: classes3.dex */
public final class CircularIntArray {
    private int capacityBitmask;
    private int[] elements;
    private int head;
    private int tail;

    @JvmOverloads
    public CircularIntArray() {
        this(0, 1, null);
    }

    @JvmOverloads
    public CircularIntArray(int r4) {
        boolean r0 = false;
        if (r4 < 1) goto L5;
        boolean r2 = true;
    L6:
        if (r2 == true) goto L9;
        RuntimeHelpersKt.throwIllegalArgumentException("capacity must be >= 1");
    L9:
        if (r4 > 1073741824) goto L11;
        r0 = true;
    L11:
        if (r0 == true) goto L14;
        RuntimeHelpersKt.throwIllegalArgumentException("capacity must be <= 2^30");
    L14:
        if (Integer.bitCount(r4) == 1) goto L16;
        r4 = Integer.highestOneBit(r4 - 1) << 1;
    L16:
        this.capacityBitmask = r4 - 1;
        this.elements = new int[r4];
        return;
    L5:
        r2 = false;
        goto L6
    }

    public /* synthetic */ CircularIntArray(int r1, int r2, DefaultConstructorMarker r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = 8;
    L5:
        this(r1);
    }

    private final void doubleCapacity() {
        int[] r0 = this.elements;
        int r1 = r0.length;
        int r2 = this.head;
        int r3 = r1 - r2;
        int r4 = r1 << 1;
        if (r4 < 0) goto L6;
        int[] r5 = new int[r4];
        ArraysKt.copyInto(r0, r5, 0, r2, r1);
        ArraysKt.copyInto(this.elements, r5, r3, 0, this.head);
        this.elements = r5;
        this.head = 0;
        this.tail = r1;
        this.capacityBitmask = r4 - 1;
        return;
    L6:
        GlShader$$ExternalSyntheticBUOutline1.m("Max array capacity exceeded");
    }

    public final void addLast(int r3) {
        int[] r0 = this.elements;
        int r1 = this.tail;
        r0[r1] = r3;
        int r32 = this.capacityBitmask;
        int r33 = r32 & (r1 + 1);
        this.tail = r33;
        if (r33 != this.head) goto L6;
        doubleCapacity();
        return;
    }

    public final void clear() {
        this.tail = this.head;
    }

    public final boolean isEmpty() {
        if (this.head != this.tail) goto L6;
        return true;
    L6:
        return false;
    }

    public final int popFirst() {
        int r0 = this.head;
        if (r0 == this.tail) goto L6;
        int r1 = this.elements[r0];
        this.head = (r0 + 1) & this.capacityBitmask;
        return r1;
    L6:
        CollectionPlatformUtils r3 = CollectionPlatformUtils.INSTANCE;
        throw new ArrayIndexOutOfBoundsException();
    }
}
