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

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0007\u001a\u00020\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0004\b\u0007\u0010\bJA\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\tH\u0007¢\u0006\u0004\b\u0010\u0010\u0011J\u000f\u0010\u0012\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001b\u001a\u00020\u001a8\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u0012\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u00020\u00028\u0000@\u0000X\u0081\u000e¢\u0006\f\n\u0004\b\u001f\u0010 \u0012\u0004\b!\u0010\u001eR\u0012\u0010#\u001a\u00020\u00028Ç\u0002¢\u0006\u0006\u001a\u0004\b\"\u0010\u0013\u0082\u0001\u0001$¨\u0006%"}, d2 = {"Landroidx/collection/IntList;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "initialCapacity", "<init>", "(I)V", "index", "get", "(I)I", _UrlKt.FRAGMENT_ENCODE_SET, "separator", "prefix", "postfix", "limit", "truncated", _UrlKt.FRAGMENT_ENCODE_SET, "joinToString", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;ILjava/lang/CharSequence;)Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "content", "[I", "getContent$annotations", "()V", "_size", "I", "get_size$annotations", "getSize", "size", "Landroidx/collection/MutableIntList;", "collection"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIntList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntList.kt\nandroidx/collection/IntList\n*L\n1#1,968:1\n363#1:969\n366#1:970\n237#1,6:971\n265#1,6:977\n237#1,6:983\n74#1:989\n237#1,6:990\n363#1:996\n237#1,6:997\n237#1,6:1003\n251#1,6:1009\n265#1,6:1015\n279#1,6:1021\n251#1,6:1027\n251#1,6:1033\n279#1,6:1039\n363#1:1045\n70#1:1046\n265#1,6:1047\n279#1,6:1053\n65#1:1059\n251#1,6:1060\n251#1,6:1066\n237#1,6:1072\n74#1:1078\n484#1,10:1079\n251#1,4:1089\n494#1,9:1093\n256#1:1102\n503#1,2:1103\n484#1,10:1105\n251#1,4:1115\n494#1,9:1119\n256#1:1128\n503#1,2:1129\n484#1,10:1131\n251#1,4:1141\n494#1,9:1145\n256#1:1154\n503#1,2:1155\n484#1,10:1157\n251#1,4:1167\n494#1,9:1171\n256#1:1180\n503#1,2:1181\n484#1,10:1183\n251#1,4:1193\n494#1,9:1197\n256#1:1206\n503#1,2:1207\n*S KotlinDebug\n*F\n+ 1 IntList.kt\nandroidx/collection/IntList\n*L\n78#1:969\n83#1:970\n89#1:971,6\n103#1:977,6\n113#1:983,6\n126#1:989\n143#1:990,6\n152#1:996\n166#1:997,6\n182#1:1003,6\n196#1:1009,6\n212#1:1015,6\n226#1:1021,6\n328#1:1027,6\n341#1:1033,6\n354#1:1039,6\n373#1:1045\n376#1:1046\n387#1:1047,6\n400#1:1053,6\n422#1:1059\n462#1:1060,6\n493#1:1066,6\n509#1:1072,6\n522#1:1078\n-1#1:1079,10\n-1#1:1089,4\n-1#1:1093,9\n-1#1:1102\n-1#1:1103,2\n-1#1:1105,10\n-1#1:1115,4\n-1#1:1119,9\n-1#1:1128\n-1#1:1129,2\n-1#1:1131,10\n-1#1:1141,4\n-1#1:1145,9\n-1#1:1154\n-1#1:1155,2\n-1#1:1157,10\n-1#1:1167,4\n-1#1:1171,9\n-1#1:1180\n-1#1:1181,2\n-1#1:1183,10\n-1#1:1193,4\n-1#1:1197,9\n-1#1:1206\n-1#1:1207,2\n*E\n"})
/* loaded from: classes3.dex */
public abstract class IntList {

    @JvmField
    public int _size;

    @JvmField
    public int[] content;

    private IntList(int r1) {
        if (r1 != 0) goto L5;
        int[] r12 = IntSetKt.getEmptyIntArray();
    L6:
        this.content = r12;
        return;
    L5:
        r12 = new int[r1];
        goto L6
    }

    public /* synthetic */ IntList(int r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    public static /* synthetic */ String joinToString$default(IntList r1, CharSequence r2, CharSequence r3, CharSequence r4, int r5, CharSequence r6, int r7, Object r8) {
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
        if ((r6 instanceof IntList) == false) goto L16;
        IntList r62 = (IntList) r6;
        int r0 = r62._size;
        int r2 = this._size;
        if (r0 != r2) goto L16;
        int[] r5 = this.content;
        int[] r63 = r62.content;
        IntRange r02 = RangesKt.until(0, r2);
        int r22 = r02.getFirst();
        int r03 = r02.getLast();
        if (r22 <= r03) goto L10;
        return true;
    L10:
        if (r5[r22] != r63[r22]) goto L11;
        if (r22 == r03) goto L19;
        r22 = r22 + 1;
        goto L10
    L19:
        return true;
    L11:
        return false;
    L16:
        return false;
    }

    public final int get(int r2) {
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
        int[] r0 = this.content;
        int r4 = this._size;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r4) goto L5;
        r2 = r2 + (Integer.hashCode(r0[r1]) * 31);
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    @JvmOverloads
    public final String joinToString(CharSequence r4, CharSequence r5, CharSequence r6, int r7, CharSequence r8) {
        StringBuilder r0 = new StringBuilder();
        r0.append(r5);
        int[] r52 = this.content;
        int r3 = this._size;
        int r1 = 0;
    L3:
        if (r1 >= r3) goto L10;
        int r2 = r52[r1];
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

    public String toString() {
        return joinToString$default(this, null, "[", "]", 0, null, 25, null);
    }
}
