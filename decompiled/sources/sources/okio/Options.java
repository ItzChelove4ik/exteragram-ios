package okio;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004:\u0001\u0016B!\b\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0011H\u0096\u0002R\u001e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lokio/Options;", "Lkotlin/collections/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "byteStrings", _UrlKt.FRAGMENT_ENCODE_SET, "trie", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "([Lokio/ByteString;[I)V", "getByteStrings$okio", "()[Lokio/ByteString;", "[Lokio/ByteString;", "getTrie$okio", "()[I", "size", _UrlKt.FRAGMENT_ENCODE_SET, "getSize", "()I", "get", "index", "Companion", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {
    public static final Companion Companion = null;
    private final ByteString[] byteStrings;
    private final int[] trie;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010 \n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\bH\u0007¢\u0006\u0002\u0010\tJT\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0012H\u0002R\u0018\u0010\u0016\u001a\u00020\r*\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lokio/Options$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "of", "Lokio/Options;", "byteStrings", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/ByteString;", "([Lokio/ByteString;)Lokio/Options;", "buildTrieRecursive", _UrlKt.FRAGMENT_ENCODE_SET, "nodeOffset", _UrlKt.FRAGMENT_ENCODE_SET, "node", "Lokio/Buffer;", "byteStringOffset", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "fromIndex", "toIndex", "indexes", "intCount", "getIntCount", "(Lokio/Buffer;)J", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Options.kt\nokio/Options$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,238:1\n1#2:239\n13870#3,3:240\n73#4:243\n73#4:244\n*S KotlinDebug\n*F\n+ 1 Options.kt\nokio/Options$Companion\n*L\n48#1:240,3\n153#1:243\n210#1:244\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final void buildTrieRecursive(long r22, Buffer r24, int r25, List<? extends ByteString> r26, int r27, int r28, List<Integer> r29) {
            int r10 = r25;
            if (r27 >= r28) goto L68;
            int r3 = r27;
        L5:
            if (r3 >= r28) goto L11;
            if (r26.get(r3).size() < r10) goto L9;
            r3 = r3 + 1;
            goto L5
        L9:
            f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
            return;
        L11:
            ByteString r2 = r26.get(r27);
            ByteString r32 = r26.get(r28 - 1);
            char r12 = 65535;
            if (r10 != r2.size()) goto L14;
            int r23 = r29.get(r27).intValue();
            int r1 = r27 + 1;
            ByteString r4 = r26.get(r1);
            int r6 = r1;
            int r13 = r23;
            r2 = r4;
        L15:
            long r132 = 2;
            if (r2.getByte(r10) == r32.getByte(r10)) goto L48;
            int r210 = r6 + 1;
            int r33 = 1;
        L18:
            if (r210 >= r28) goto L23;
            if (r26.get(r210 - 1).getByte(r10) == r26.get(r210).getByte(r10)) goto L22;
            r33 = r33 + 1;
        L22:
            r210 = r210 + 1;
            goto L18
        L23:
            long r15 = ((r22 + getIntCount(r24)) + 2) + (r33 * 2);
            r24.writeInt(r33);
            r24.writeInt(r13);
            int r14 = r6;
        L24:
            if (r14 >= r28) goto L31;
            byte r211 = r26.get(r14).getByte(r10);
            if (r14 != r6) goto L28;
        L29:
            r24.writeInt(r211 & UByte.MAX_VALUE);
        L30:
            r14 = r14 + 1;
            goto L24
        L28:
            if (r211 == r26.get(r14 - 1).getByte(r10)) goto L30;
        L31:
            Buffer r34 = new Buffer();
        L32:
            if (r6 >= r28) goto L46;
            byte r16 = r26.get(r6).getByte(r10);
            int r212 = r6 + 1;
            int r42 = r212;
        L34:
            if (r42 >= r28) goto L39;
            if (r16 != r26.get(r42).getByte(r10)) goto L37;
            r42 = r42 + 1;
            goto L34
        L37:
            int r7 = r42;
        L40:
            if (r212 == r7) goto L42;
        L44:
            r24.writeInt(((int) (getIntCount(r34) + r15)) * (-1));
            long r17 = r15;
            buildTrieRecursive(r17, r34, r10 + 1, r26, r6, r7, r29);
        L45:
            r15 = r17;
            r6 = r7;
            goto L32
        L42:
            if ((r10 + 1) != r26.get(r6).size()) goto L44;
            r24.writeInt(r29.get(r6).intValue());
            r17 = r15;
            goto L45
        L39:
            r7 = r28;
            goto L40
        L46:
            r24.writeAll(r34);
            return;
        L48:
            int r43 = Math.min(r2.size(), r32.size());
            int r72 = 0;
            int r152 = r10;
        L49:
            char r162 = r12;
            if (r152 >= r43) goto L54;
            long r172 = r132;
            if (r2.getByte(r152) != r32.getByte(r152)) goto L55;
            r72 = r72 + 1;
            r152 = r152 + 1;
            r12 = r162;
            r132 = r172;
        L55:
            long r35 = (((r22 + getIntCount(r24)) + r172) + r72) + 1;
            r24.writeInt(-r72);
            r24.writeInt(r13);
            int r18 = r10 + r72;
        L56:
            if (r10 >= r18) goto L59;
            r24.writeInt(r2.getByte(r10) & UByte.MAX_VALUE);
            r10 = r10 + 1;
            goto L56
        L59:
            if ((r6 + 1) == r28) goto L61;
            Buffer r36 = new Buffer();
            r24.writeInt(((int) (getIntCount(r36) + r35)) * (-1));
            buildTrieRecursive(r35, r36, r18, r26, r6, r28, r29);
            r24.writeAll(r36);
            return;
        L61:
            if (r18 != r26.get(r6).size()) goto L64;
            r24.writeInt(r29.get(r6).intValue());
            return;
        L64:
            Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
            return;
        L54:
            r172 = r132;
            goto L55
        L14:
            r6 = r27;
            r13 = -1;
            goto L15
        L68:
            f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        }

        public static /* synthetic */ void buildTrieRecursive$default(Companion r9, long r10, Buffer r12, int r13, List r14, int r15, int r16, List r17, int r18, Object r19) {
            if ((r18 & 1) == 0) goto L5;
            r10 = 0;
        L5:
            long r1 = r10;
            if ((r18 & 4) == 0) goto L8;
            int r4 = 0;
        L10:
            if ((r18 & 16) == 0) goto L12;
            int r6 = 0;
        L14:
            if ((r18 & 32) == 0) goto L17;
            int r7 = r14.size();
        L18:
            r9.buildTrieRecursive(r1, r12, r4, r14, r6, r7, r17);
            return;
        L17:
            r7 = r16;
            goto L18
        L12:
            r6 = r15;
            goto L14
        L8:
            r4 = r13;
            goto L10
        }

        private final long getIntCount(Buffer r3) {
            return r3.size() / 4;
        }

        @JvmStatic
        public final Options of(ByteString... r17) {
            int r4 = 0;
            if (r17.length == 0) goto L5;
            List r5 = ArraysKt.toMutableList(r17);
            CollectionsKt.sort(r5);
            int r1 = r5.size();
            ArrayList r13 = new ArrayList(r1);
            int r6 = 0;
        L7:
            if (r6 >= r1) goto L9;
            r13.add(-1);
            r6 = r6 + 1;
            goto L7
        L9:
            int r12 = r17.length;
            int r3 = 0;
            int r11 = 0;
        L10:
            if (r3 >= r12) goto L13;
            r13.set(CollectionsKt.binarySearch$default(r5, r17[r3], 0, 0, 6, null), Integer.valueOf(r11));
            r3 = r3 + 1;
            r11 = r11 + 1;
            goto L10
        L13:
            if (((ByteString) r5.get(0)).size() <= 0) goto L36;
            int r14 = 0;
        L16:
            if (r14 >= r5.size()) goto L31;
            ByteString r32 = (ByteString) r5.get(r14);
            int r62 = r14 + 1;
            int r7 = r62;
        L19:
            if (r7 >= r5.size()) goto L30;
            ByteString r8 = (ByteString) r5.get(r7);
            if (r8.startsWith(r32) == false) goto L30;
            if (r8.size() == r32.size()) goto L28;
            if (((Number) r13.get(r7)).intValue() > ((Number) r13.get(r14)).intValue()) goto L26;
            r7 = r7 + 1;
            goto L19
        L26:
            r5.remove(r7);
            ((Number) r13.remove(r7)).intValue();
            goto L19
        L28:
            Options$Companion$$ExternalSyntheticBUOutline0.m("duplicate option: ", r8);
            return null;
        L30:
            r14 = r62;
            goto L16
        L31:
            Buffer r82 = new Buffer();
            buildTrieRecursive$default(this, 0, r82, 0, r5, 0, 0, r13, 53, null);
            int r15 = (int) getIntCount(r82);
            int[] r33 = new int[r15];
        L32:
            if (r4 >= r15) goto L35;
            r33[r4] = r82.readInt();
            r4 = r4 + 1;
            goto L32
        L35:
            return new Options((ByteString[]) Arrays.copyOf(r17, r17.length), r33, null);
        L36:
            f$$ExternalSyntheticBUOutline1.m("the empty byte string is not a supported option");
            return null;
        L5:
            return new Options(new ByteString[0], new int[]{0, -1}, null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    private Options(ByteString[] r1, int[] r2) {
        this.byteStrings = r1;
        this.trie = r2;
    }

    public /* synthetic */ Options(ByteString[] r1, int[] r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object r2) {
        if ((r2 instanceof ByteString) == true) goto L7;
        return false;
    L7:
        return contains((ByteString) r2);
    }

    public /* bridge */ boolean contains(ByteString r1) {
        return super.contains(r1);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public /* bridge */ /* synthetic */ Object get(int r1) {
        return get(r1);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public ByteString get(int r1) {
        return this.byteStrings[r1];
    }

    public final ByteString[] getByteStrings$okio() {
        return this.byteStrings;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.byteStrings.length;
    }

    public final int[] getTrie$okio() {
        return this.trie;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object r2) {
        if ((r2 instanceof ByteString) == true) goto L7;
        return -1;
    L7:
        return indexOf((ByteString) r2);
    }

    public /* bridge */ int indexOf(ByteString r1) {
        return super.indexOf(r1);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object r2) {
        if ((r2 instanceof ByteString) == true) goto L7;
        return -1;
    L7:
        return lastIndexOf((ByteString) r2);
    }

    public /* bridge */ int lastIndexOf(ByteString r1) {
        return super.lastIndexOf(r1);
    }
}
