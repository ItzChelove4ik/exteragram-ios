package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001a\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\u0006¢\u0006\u0004\b\f\u0010\bJA\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\rH\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0016\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001f\u001a\u00020\u001e8\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b\u001f\u0010 \u0012\u0004\b!\u0010\"R\u001c\u0010#\u001a\u00020\u00028\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b#\u0010$\u0012\u0004\b%\u0010\"R\u0012\u0010'\u001a\u00020\u00028Ç\u0002¢\u0006\u0006\u001a\u0004\b&\u0010\u0017\u0082\u0001\u0001(¨\u0006)"}, d2 = {"Landroidx/collection/FloatList;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "initialCapacity", "<init>", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "first", "()F", "index", "get", "(I)F", "last", _UrlKt.FRAGMENT_ENCODE_SET, "separator", "prefix", "postfix", "limit", "truncated", _UrlKt.FRAGMENT_ENCODE_SET, "joinToString", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;)Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "content", "[F", "getContent$annotations", "()V", "_size", "I", "get_size$annotations", "getSize", "size", "Landroidx/collection/MutableFloatList;", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFloatList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FloatList.kt\nandroidx/collection/FloatList\n*L\n1#1,976:1\n365#1:977\n368#1:978\n237#1,6:979\n265#1,6:985\n237#1,6:991\n74#1:997\n237#1,6:998\n365#1:1004\n237#1,6:1005\n237#1,6:1011\n251#1,6:1017\n265#1,6:1023\n279#1,6:1029\n251#1,6:1035\n251#1,6:1041\n279#1,6:1047\n365#1:1053\n70#1:1054\n265#1,6:1055\n279#1,6:1061\n65#1:1067\n251#1,6:1068\n251#1,6:1074\n237#1,6:1080\n74#1:1086\n486#1,10:1087\n251#1,4:1097\n496#1,9:1101\n256#1:1110\n505#1,2:1111\n486#1,10:1113\n251#1,4:1123\n496#1,9:1127\n256#1:1136\n505#1,2:1137\n486#1,10:1139\n251#1,4:1149\n496#1,9:1153\n256#1:1162\n505#1,2:1163\n486#1,10:1165\n251#1,4:1175\n496#1,9:1179\n256#1:1188\n505#1,2:1189\n486#1,10:1191\n251#1,4:1201\n496#1,9:1205\n256#1:1214\n505#1,2:1215\n*S KotlinDebug\n*F\n+ 1 FloatList.kt\nandroidx/collection/FloatList\n*L\n78#1:977\n83#1:978\n89#1:979,6\n103#1:985,6\n113#1:991,6\n126#1:997\n143#1:998,6\n152#1:1004\n166#1:1005,6\n182#1:1011,6\n196#1:1017,6\n212#1:1023,6\n226#1:1029,6\n328#1:1035,6\n342#1:1041,6\n356#1:1047,6\n375#1:1053\n378#1:1054\n389#1:1055,6\n402#1:1061,6\n424#1:1067\n464#1:1068,6\n495#1:1074,6\n511#1:1080,6\n524#1:1086\n-1#1:1087,10\n-1#1:1097,4\n-1#1:1101,9\n-1#1:1110\n-1#1:1111,2\n-1#1:1113,10\n-1#1:1123,4\n-1#1:1127,9\n-1#1:1136\n-1#1:1137,2\n-1#1:1139,10\n-1#1:1149,4\n-1#1:1153,9\n-1#1:1162\n-1#1:1163,2\n-1#1:1165,10\n-1#1:1175,4\n-1#1:1179,9\n-1#1:1188\n-1#1:1189,2\n-1#1:1191,10\n-1#1:1201,4\n-1#1:1205,9\n-1#1:1214\n-1#1:1215,2\n*E\n"})
/* loaded from: classes3.dex */
public abstract class FloatList {

    @JvmField
    public int _size;

    @JvmField
    public float[] content;

    private FloatList(int r1) {
        if (r1 != 0) goto L5;
        float[] r12 = FloatSetKt.getEmptyFloatArray();
    L6:
        this.content = r12;
        return;
    L5:
        r12 = new float[r1];
        goto L6
    }

    public /* synthetic */ FloatList(int r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    public static /* synthetic */ String joinToString$default(FloatList r1, CharSequence r2, CharSequence r3, CharSequence r4, int r5, CharSequence r6, int r7, Object r8) {
        if (r8 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: joinToString");
        return null;
    L4:
        if ((r7 & 1) == 0) goto L7;
        r2 = ", ";
    L7:
        if ((r7 & 2) == 0) goto L10;
        r3 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        if ((r7 & 4) == 0) goto L13;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        if ((r7 & 8) == 0) goto L16;
        r5 = -1;
    L16:
        if ((r7 & 16) == 0) goto L18;
        r6 = "...";
    L18:
        int r72 = r5;
        CharSequence r82 = r6;
        CharSequence r62 = r4;
        CharSequence r42 = r2;
        return r1.joinToString(r42, r3, r62, r72, r82);
    }

    public boolean equals(Object r6) {
        if ((r6 instanceof FloatList) == false) goto L16;
        FloatList r62 = (FloatList) r6;
        int r0 = r62._size;
        int r2 = this._size;
        if (r0 != r2) goto L16;
        float[] r5 = this.content;
        float[] r63 = r62.content;
        IntRange r02 = RangesKt.until(0, r2);
        int r22 = r02.getFirst();
        int r03 = r02.getLast();
        if (r22 <= r03) goto L10;
        return true;
    L10:
        if (r5[r22] != r63[r22]) goto L13;
        if (r22 == r03) goto L19;
        r22 = r22 + 1;
        goto L10
    L19:
        return true;
    L13:
        return false;
    L16:
        return false;
    }

    public final float first() {
        if (this._size != 0) goto L6;
        RuntimeHelpersKt.throwNoSuchElementException("FloatList is empty.");
    L6:
        return this.content[0];
    }

    public final float get(int r2) {
        if (r2 >= 0) goto L4;
    L6:
        RuntimeHelpersKt.throwIndexOutOfBoundsException("Index must be between 0 and size");
    L8:
        return this.content[r2];
    L4:
        if (r2 >= this._size) goto L6;
        goto L6
    }

    public final int getSize() {
        return this._size;
    }

    public int hashCode() {
        float[] r0 = this.content;
        int r4 = this._size;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r4) goto L5;
        r2 = r2 + (Float.hashCode(r0[r1]) * 31);
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    @JvmOverloads
    public final String joinToString(CharSequence r4, CharSequence r5, CharSequence r6, int r7, CharSequence r8) {
        StringBuilder r0 = new StringBuilder();
        r0.append(r5);
        float[] r52 = this.content;
        int r3 = this._size;
        int r1 = 0;
    L3:
        if (r1 >= r3) goto L10;
        float r2 = r52[r1];
        if (r1 == r7) goto L6;
        if (r1 == 0) goto L9;
        r0.append(r4);
    L9:
        r0.append(r2);
        r1 = r1 + 1;
        goto L3
    L6:
        r0.append(r8);
    L12:
        return r0.toString();
    L10:
        r0.append(r6);
        goto L12
    }

    public final float last() {
        if (this._size != 0) goto L6;
        RuntimeHelpersKt.throwNoSuchElementException("FloatList is empty.");
    L6:
        return this.content[this._size - 1];
    }

    public String toString() {
        return joinToString$default(this, null, "[", "]", 0, null, 25, null);
    }
}
