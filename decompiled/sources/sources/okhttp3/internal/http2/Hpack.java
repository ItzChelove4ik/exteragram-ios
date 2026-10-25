package okhttp3.internal.http2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import okio.internal.ZipFilesKt$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0019\u001aB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u0012H\u0002J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001b"}, d2 = {"Lokhttp3/internal/http2/Hpack;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "PREFIX_4_BITS", _UrlKt.FRAGMENT_ENCODE_SET, "PREFIX_5_BITS", "PREFIX_6_BITS", "PREFIX_7_BITS", "SETTINGS_HEADER_TABLE_SIZE", "SETTINGS_HEADER_TABLE_SIZE_LIMIT", "STATIC_HEADER_TABLE", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Header;", "getSTATIC_HEADER_TABLE", "()[Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "NAME_TO_FIRST_INDEX", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/ByteString;", "getNAME_TO_FIRST_INDEX", "()Ljava/util/Map;", "nameToFirstIndex", "checkLowercase", "name", "Reader", "Writer", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nHpack.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Hpack.kt\nokhttp3/internal/http2/Hpack\n+ 2 -UtilJvm.kt\nokhttp3/internal/_UtilJvmKt\n*L\n1#1,647:1\n250#2:648\n*S KotlinDebug\n*F\n+ 1 Hpack.kt\nokhttp3/internal/http2/Hpack\n*L\n398#1:648\n*E\n"})
/* loaded from: classes.dex */
public final class Hpack {
    public static final Hpack INSTANCE = null;
    private static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX = null;
    private static final int PREFIX_4_BITS = 15;
    private static final int PREFIX_5_BITS = 31;
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
    private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
    private static final Header[] STATIC_HEADER_TABLE = null;

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014J\u0006\u0010\u0006\u001a\u00020\u0005J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0006\u0010\u001a\u001a\u00020\u0016J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0010\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\u0010\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\u0005H\u0002J\b\u0010\"\u001a\u00020\u0016H\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0018\u0010'\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u000bH\u0002J\b\u0010)\u001a\u00020\u0005H\u0002J\u0016\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020\u0005J\u0006\u0010-\u001a\u00020$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lokhttp3/internal/http2/Hpack$Reader;", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/Source;", "headerTableSizeSetting", _UrlKt.FRAGMENT_ENCODE_SET, "maxDynamicTableByteCount", "<init>", "(Lokio/Source;II)V", "headerList", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Header;", "Lokio/BufferedSource;", "dynamicTable", _UrlKt.FRAGMENT_ENCODE_SET, "[Lokhttp3/internal/http2/Header;", "nextHeaderIndex", "headerCount", "dynamicTableByteCount", "getAndResetHeaderList", _UrlKt.FRAGMENT_ENCODE_SET, "adjustDynamicTableByteCount", _UrlKt.FRAGMENT_ENCODE_SET, "clearDynamicTable", "evictToRecoverBytes", "bytesToRecover", "readHeaders", "readIndexedHeader", "index", "dynamicTableIndex", "readLiteralHeaderWithoutIndexingIndexedName", "readLiteralHeaderWithoutIndexingNewName", "readLiteralHeaderWithIncrementalIndexingIndexedName", "nameIndex", "readLiteralHeaderWithIncrementalIndexingNewName", "getName", "Lokio/ByteString;", "isStaticHeader", _UrlKt.FRAGMENT_ENCODE_SET, "insertIntoDynamicTable", "entry", "readByte", "readInt", "firstByte", "prefixMask", "readByteString", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Reader {

        @JvmField
        public Header[] dynamicTable;

        @JvmField
        public int dynamicTableByteCount;

        @JvmField
        public int headerCount;
        private final List<Header> headerList;
        private final int headerTableSizeSetting;
        private int maxDynamicTableByteCount;
        private int nextHeaderIndex;
        private final BufferedSource source;

        @JvmOverloads
        public Reader(Source r7, int r8) {
            this(r7, r8, 0, 4, null);
        }

        @JvmOverloads
        public Reader(Source r1, int r2, int r3) {
            this.headerTableSizeSetting = r2;
            this.maxDynamicTableByteCount = r3;
            this.headerList = new ArrayList();
            this.source = Okio.buffer(r1);
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = r1.length - 1;
        }

        public /* synthetic */ Reader(Source r1, int r2, int r3, int r4, DefaultConstructorMarker r5) {
            if ((r4 & 4) == 0) goto L5;
            r3 = r2;
        L5:
            this(r1, r2, r3);
        }

        private final void adjustDynamicTableByteCount() {
            int r0 = this.maxDynamicTableByteCount;
            int r1 = this.dynamicTableByteCount;
            if (r0 >= r1) goto L9;
            if (r0 != 0) goto L7;
            clearDynamicTable();
            return;
        L7:
            evictToRecoverBytes(r1 - r0);
            return;
        }

        private final void clearDynamicTable() {
            ArraysKt.fill$default(this.dynamicTable, null, 0, 0, 6, null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private final int dynamicTableIndex(int r1) {
            return (this.nextHeaderIndex + 1) + r1;
        }

        private final int evictToRecoverBytes(int r5) {
            int r0 = 0;
            if (r5 <= 0) goto L10;
            int r1 = this.dynamicTable.length;
        L5:
            r1 = r1 - 1;
            int r2 = this.nextHeaderIndex;
            if (r1 < r2) goto L9;
            if (r5 <= 0) goto L9;
            int r22 = this.dynamicTable[r1].hpackSize;
            r5 = r5 - r22;
            this.dynamicTableByteCount -= r22;
            this.headerCount--;
            r0 = r0 + 1;
        L9:
            Header[] r52 = this.dynamicTable;
            System.arraycopy(r52, r2 + 1, r52, (r2 + 1) + r0, this.headerCount);
            this.nextHeaderIndex += r0;
        L10:
            return r0;
        }

        private final ByteString getName(int r3) {
            if (isStaticHeader(r3) == true) goto L5;
            int r0 = dynamicTableIndex(r3 - Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length);
            if (r0 < 0) goto L12;
            Header[] r2 = this.dynamicTable;
            if (r0 >= r2.length) goto L12;
            return r2[r0].name;
        L12:
            Hpack$Reader$$ExternalSyntheticBUOutline0.m("Header index too large ", r3 + 1);
            return null;
        L5:
            return Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[r3].name;
        }

        private final void insertIntoDynamicTable(int r6, Header r7) {
            this.headerList.add(r7);
            int r0 = r7.hpackSize;
            if (r6 == (-1)) goto L5;
            r0 = r0 - this.dynamicTable[dynamicTableIndex(r6)].hpackSize;
        L5:
            int r2 = this.maxDynamicTableByteCount;
            if (r0 <= r2) goto L9;
            clearDynamicTable();
            return;
        L9:
            int r22 = evictToRecoverBytes((this.dynamicTableByteCount + r0) - r2);
            if (r6 != (-1)) goto L15;
            int r62 = this.headerCount + 1;
            Header[] r1 = this.dynamicTable;
            if (r62 <= r1.length) goto L14;
            Header[] r63 = new Header[r1.length * 2];
            System.arraycopy(r1, 0, r63, r1.length, r1.length);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.dynamicTable = r63;
        L14:
            int r64 = this.nextHeaderIndex;
            this.nextHeaderIndex = r64 - 1;
            this.dynamicTable[r64] = r7;
            this.headerCount++;
        L16:
            this.dynamicTableByteCount += r0;
            return;
        L15:
            this.dynamicTable[r6 + (dynamicTableIndex(r6) + r22)] = r7;
            goto L16
        }

        private final boolean isStaticHeader(int r2) {
            if (r2 >= 0) goto L4;
            return false;
        L4:
            if (r2 > (Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length - 1)) goto L8;
            return true;
        L8:
            return false;
        }

        private final int readByte() {
            return _UtilCommonKt.and(this.source.readByte(), 255);
        }

        private final void readIndexedHeader(int r4) {
            if (isStaticHeader(r4) == false) goto L6;
            Header r42 = Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[r4];
            this.headerList.add(r42);
            return;
        L6:
            int r0 = dynamicTableIndex(r4 - Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length);
            if (r0 < 0) goto L12;
            Header[] r1 = this.dynamicTable;
            if (r0 >= r1.length) goto L12;
            this.headerList.add(r1[r0]);
            return;
        L12:
            Hpack$Reader$$ExternalSyntheticBUOutline0.m("Header index too large ", r4 + 1);
        }

        private final void readLiteralHeaderWithIncrementalIndexingIndexedName(int r3) {
            insertIntoDynamicTable(-1, new Header(getName(r3), readByteString()));
        }

        private final void readLiteralHeaderWithIncrementalIndexingNewName() {
            insertIntoDynamicTable(-1, new Header(Hpack.INSTANCE.checkLowercase(readByteString()), readByteString()));
        }

        private final void readLiteralHeaderWithoutIndexingIndexedName(int r3) {
            ByteString r32 = getName(r3);
            ByteString r0 = readByteString();
            this.headerList.add(new Header(r32, r0));
        }

        private final void readLiteralHeaderWithoutIndexingNewName() {
            ByteString r0 = Hpack.INSTANCE.checkLowercase(readByteString());
            ByteString r1 = readByteString();
            this.headerList.add(new Header(r0, r1));
        }

        public final List<Header> getAndResetHeaderList() {
            List<Header> r0 = CollectionsKt.toList(this.headerList);
            this.headerList.clear();
            return r0;
        }

        public final int maxDynamicTableByteCount() {
            return this.maxDynamicTableByteCount;
        }

        public final ByteString readByteString() {
            int r0 = readByte();
            if ((r0 & 128) != 128) goto L5;
            boolean r1 = true;
        L6:
            long r2 = readInt(r0, 127);
            if (r1 == false) goto L11;
            Buffer r02 = new Buffer();
            Huffman.INSTANCE.decode(this.source, r2, r02);
            return r02.readByteString();
        L11:
            return this.source.readByteString(r2);
        L5:
            r1 = false;
            goto L6
        }

        public final void readHeaders() {
        L3:
            if (this.source.exhausted() == true) goto L54;
            int r0 = _UtilCommonKt.and(this.source.readByte(), 255);
            if (r0 == 128) goto L30;
            if ((r0 & 128) == 128) goto L8;
            if (r0 == 64) goto L11;
            if ((r0 & 64) == 64) goto L14;
            if ((r0 & 32) == 32) goto L17;
            if (r0 == 16) goto L29;
            if (r0 == 0) goto L29;
            readLiteralHeaderWithoutIndexingIndexedName(readInt(r0, 15) - 1);
        L29:
            readLiteralHeaderWithoutIndexingNewName();
            goto L3
        L17:
            int r02 = readInt(r0, 31);
            this.maxDynamicTableByteCount = r02;
            if (r02 < 0) goto L22;
            if (r02 > this.headerTableSizeSetting) goto L22;
            adjustDynamicTableByteCount();
        L22:
            Hpack$Reader$$ExternalSyntheticBUOutline0.m("Invalid dynamic table size update ", this.maxDynamicTableByteCount);
            return;
        L14:
            readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(r0, 63) - 1);
            goto L3
        L11:
            readLiteralHeaderWithIncrementalIndexingNewName();
            goto L3
        L8:
            readIndexedHeader(readInt(r0, 127) - 1);
            goto L3
        L30:
            Model$$ExternalSyntheticBUOutline0.m("index == 0");
            return;
        }

        public final int readInt(int r3, int r4) {
            int r32 = r3 & r4;
            if (r32 >= r4) goto L5;
            return r32;
        L5:
            int r33 = 0;
        L6:
            int r0 = readByte();
            if ((r0 & 128) == 0) goto L10;
            r4 = r4 + ((r0 & 127) << r33);
            r33 = r33 + 7;
            goto L6
        L10:
            return r4 + (r0 << r33);
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003H\u0002J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u000fH\u0002J\u0014\u0010\u001a\u001a\u00020\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001cJ\u001e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0003J\u000e\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00152\u0006\u0010\u0002\u001a\u00020\u0003J\b\u0010%\u001a\u00020\u0015H\u0002R\u0012\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e8\u0006@\u0006X\u0087\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lokhttp3/internal/http2/Hpack$Writer;", _UrlKt.FRAGMENT_ENCODE_SET, "headerTableSizeSetting", _UrlKt.FRAGMENT_ENCODE_SET, "useCompression", _UrlKt.FRAGMENT_ENCODE_SET, "out", "Lokio/Buffer;", "<init>", "(IZLokio/Buffer;)V", "smallestHeaderTableSizeSetting", "emitDynamicTableSizeUpdate", "maxDynamicTableByteCount", "dynamicTable", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/http2/Header;", "[Lokhttp3/internal/http2/Header;", "nextHeaderIndex", "headerCount", "dynamicTableByteCount", "clearDynamicTable", _UrlKt.FRAGMENT_ENCODE_SET, "evictToRecoverBytes", "bytesToRecover", "insertIntoDynamicTable", "entry", "writeHeaders", "headerBlock", _UrlKt.FRAGMENT_ENCODE_SET, "writeInt", "value", "prefixMask", "bits", "writeByteString", "data", "Lokio/ByteString;", "resizeHeaderTable", "adjustDynamicTableByteCount", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Writer {

        @JvmField
        public Header[] dynamicTable;

        @JvmField
        public int dynamicTableByteCount;
        private boolean emitDynamicTableSizeUpdate;

        @JvmField
        public int headerCount;

        @JvmField
        public int headerTableSizeSetting;

        @JvmField
        public int maxDynamicTableByteCount;
        private int nextHeaderIndex;
        private final Buffer out;
        private int smallestHeaderTableSizeSetting;
        private final boolean useCompression;

        @JvmOverloads
        public Writer(int r7, Buffer r8) {
            this(r7, false, r8, 2, null);
        }

        @JvmOverloads
        public Writer(int r1, boolean r2, Buffer r3) {
            this.headerTableSizeSetting = r1;
            this.useCompression = r2;
            this.out = r3;
            this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
            this.maxDynamicTableByteCount = r1;
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = r1.length - 1;
        }

        public /* synthetic */ Writer(int r1, boolean r2, Buffer r3, int r4, DefaultConstructorMarker r5) {
            if ((r4 & 1) == 0) goto L6;
            r1 = 4096;
        L6:
            if ((r4 & 2) == 0) goto L8;
            r2 = true;
        L8:
            this(r1, r2, r3);
        }

        @JvmOverloads
        public Writer(Buffer r7) {
            this(0, false, r7, 3, null);
        }

        private final void adjustDynamicTableByteCount() {
            int r0 = this.maxDynamicTableByteCount;
            int r1 = this.dynamicTableByteCount;
            if (r0 >= r1) goto L9;
            if (r0 != 0) goto L7;
            clearDynamicTable();
            return;
        L7:
            evictToRecoverBytes(r1 - r0);
            return;
        }

        private final void clearDynamicTable() {
            ArraysKt.fill$default(this.dynamicTable, null, 0, 0, 6, null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private final int evictToRecoverBytes(int r5) {
            int r0 = 0;
            if (r5 <= 0) goto L10;
            int r1 = this.dynamicTable.length;
        L5:
            r1 = r1 - 1;
            int r2 = this.nextHeaderIndex;
            if (r1 < r2) goto L9;
            if (r5 <= 0) goto L9;
            int r22 = this.dynamicTable[r1].hpackSize;
            r5 = r5 - r22;
            this.dynamicTableByteCount -= r22;
            this.headerCount--;
            r0 = r0 + 1;
        L9:
            Header[] r52 = this.dynamicTable;
            System.arraycopy(r52, r2 + 1, r52, (r2 + 1) + r0, this.headerCount);
            Header[] r53 = this.dynamicTable;
            int r12 = this.nextHeaderIndex;
            Arrays.fill(r53, r12 + 1, (r12 + 1) + r0, null);
            this.nextHeaderIndex += r0;
        L10:
            return r0;
        }

        private final void insertIntoDynamicTable(Header r7) {
            int r0 = r7.hpackSize;
            int r1 = this.maxDynamicTableByteCount;
            if (r0 <= r1) goto L6;
            clearDynamicTable();
            return;
        L6:
            evictToRecoverBytes((this.dynamicTableByteCount + r0) - r1);
            int r12 = this.headerCount + 1;
            Header[] r2 = this.dynamicTable;
            if (r12 <= r2.length) goto L9;
            Header[] r13 = new Header[r2.length * 2];
            System.arraycopy(r2, 0, r13, r2.length, r2.length);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.dynamicTable = r13;
        L9:
            int r14 = this.nextHeaderIndex;
            this.nextHeaderIndex = r14 - 1;
            this.dynamicTable[r14] = r7;
            this.headerCount++;
            this.dynamicTableByteCount += r0;
        }

        public final void resizeHeaderTable(int r2) {
            this.headerTableSizeSetting = r2;
            int r22 = Math.min(r2, 16384);
            int r0 = this.maxDynamicTableByteCount;
            if (r0 != r22) goto L5;
            return;
        L5:
            if (r22 >= r0) goto L7;
            this.smallestHeaderTableSizeSetting = Math.min(this.smallestHeaderTableSizeSetting, r22);
        L7:
            this.emitDynamicTableSizeUpdate = true;
            this.maxDynamicTableByteCount = r22;
            adjustDynamicTableByteCount();
        }

        public final void writeByteString(ByteString r5) {
            if (this.useCompression == false) goto L8;
            Huffman r0 = Huffman.INSTANCE;
            if (r0.encodedLength(r5) >= r5.size()) goto L8;
            Buffer r2 = new Buffer();
            r0.encode(r5, r2);
            ByteString r52 = r2.readByteString();
            writeInt(r52.size(), 127, 128);
            this.out.write(r52);
            return;
        L8:
            writeInt(r5.size(), 127, 0);
            this.out.write(r5);
        }

        public final void writeHeaders(List<Header> r13) {
            if (this.emitDynamicTableSizeUpdate == false) goto L8;
            int r0 = this.smallestHeaderTableSizeSetting;
            if (r0 >= this.maxDynamicTableByteCount) goto L7;
            writeInt(r0, 31, 32);
        L7:
            this.emitDynamicTableSizeUpdate = false;
            this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
            writeInt(this.maxDynamicTableByteCount, 31, 32);
        L8:
            int r02 = r13.size();
            int r2 = 0;
        L9:
            if (r2 >= r02) goto L47;
            Header r3 = r13.get(r2);
            ByteString r4 = r3.name.toAsciiLowercase();
            ByteString r5 = r3.value;
            Hpack r6 = Hpack.INSTANCE;
            Integer r7 = r6.getNAME_TO_FIRST_INDEX().get(r4);
            if (r7 == null) goto L23;
            int r72 = r7.intValue();
            int r9 = r72 + 1;
            if (2 <= r9) goto L15;
        L22:
            int r62 = r9;
            r9 = -1;
        L24:
            if (r9 != (-1)) goto L35;
            int r73 = this.nextHeaderIndex + 1;
            int r10 = this.dynamicTable.length;
        L26:
            if (r73 >= r10) goto L35;
            if (Intrinsics.areEqual(this.dynamicTable[r73].name, r4) == false) goto L34;
            if (Intrinsics.areEqual(this.dynamicTable[r73].value, r5) == true) goto L31;
            if (r62 != (-1)) goto L34;
            r62 = (r73 - this.nextHeaderIndex) + Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length;
            goto L34
        L31:
            r9 = Hpack.INSTANCE.getSTATIC_HEADER_TABLE().length + (r73 - this.nextHeaderIndex);
        L34:
            r73 = r73 + 1;
        L35:
            if (r9 == (-1)) goto L38;
            writeInt(r9, 127, 128);
        L46:
            r2 = r2 + 1;
            goto L9
        L38:
            if (r62 != (-1)) goto L41;
            this.out.writeByte(64);
            writeByteString(r4);
            writeByteString(r5);
            insertIntoDynamicTable(r3);
            goto L46
        L41:
            if (r4.startsWith(Header.PSEUDO_PREFIX) == true) goto L43;
        L45:
            writeInt(r62, 63, 64);
            writeByteString(r5);
            insertIntoDynamicTable(r3);
            goto L46
        L43:
            if (Intrinsics.areEqual(Header.TARGET_AUTHORITY, r4) == true) goto L45;
            writeInt(r62, 15, 0);
            writeByteString(r5);
            goto L46
        L15:
            if (r9 >= 8) goto L22;
            if (Intrinsics.areEqual(r6.getSTATIC_HEADER_TABLE()[r72].value, r5) == false) goto L20;
            r62 = r9;
            goto L24
        L20:
            if (Intrinsics.areEqual(r6.getSTATIC_HEADER_TABLE()[r9].value, r5) == false) goto L22;
            r62 = r9;
            r9 = r72 + 2;
            goto L24
        L23:
            r62 = -1;
            r9 = -1;
            goto L24
        }

        public final void writeInt(int r2, int r3, int r4) {
            Buffer r0 = this.out;
            if (r2 >= r3) goto L6;
            r0.writeByte(r4 | r2);
            return;
        L6:
            r0.writeByte(r4 | r3);
            int r22 = r2 - r3;
        L7:
            Buffer r32 = this.out;
            if (r22 < 128) goto L10;
            r32.writeByte(128 | (r22 & 127));
            r22 = r22 >>> 7;
            goto L7
        L10:
            r32.writeByte(r22);
        }
    }

    static {
        Hpack r0 = new Hpack();
        INSTANCE = r0;
        Header r1 = new Header(Header.TARGET_AUTHORITY, _UrlKt.FRAGMENT_ENCODE_SET);
        ByteString r4 = Header.TARGET_METHOD;
        Header r2 = new Header(r4, "GET");
        Header r5 = new Header(r4, "POST");
        ByteString r6 = Header.TARGET_PATH;
        Header r42 = new Header(r6, "/");
        Header r52 = new Header(r6, "/index.html");
        ByteString r8 = Header.TARGET_SCHEME;
        Header r62 = new Header(r8, "http");
        Header r7 = new Header(r8, "https");
        ByteString r10 = Header.RESPONSE_STATUS;
        STATIC_HEADER_TABLE = new Header[]{r1, r2, r5, r42, r52, r62, r7, new Header(r10, "200"), new Header(r10, "204"), new Header(r10, "206"), new Header(r10, "304"), new Header(r10, "400"), new Header(r10, "404"), new Header(r10, "500"), new Header("accept-charset", _UrlKt.FRAGMENT_ENCODE_SET), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", _UrlKt.FRAGMENT_ENCODE_SET), new Header("accept-ranges", _UrlKt.FRAGMENT_ENCODE_SET), new Header("accept", _UrlKt.FRAGMENT_ENCODE_SET), new Header("access-control-allow-origin", _UrlKt.FRAGMENT_ENCODE_SET), new Header("age", _UrlKt.FRAGMENT_ENCODE_SET), new Header("allow", _UrlKt.FRAGMENT_ENCODE_SET), new Header("authorization", _UrlKt.FRAGMENT_ENCODE_SET), new Header("cache-control", _UrlKt.FRAGMENT_ENCODE_SET), new Header("content-disposition", _UrlKt.FRAGMENT_ENCODE_SET), new Header("content-encoding", _UrlKt.FRAGMENT_ENCODE_SET), new Header("content-language", _UrlKt.FRAGMENT_ENCODE_SET), new Header("content-length", _UrlKt.FRAGMENT_ENCODE_SET), new Header("content-location", _UrlKt.FRAGMENT_ENCODE_SET), new Header("content-range", _UrlKt.FRAGMENT_ENCODE_SET), new Header("content-type", _UrlKt.FRAGMENT_ENCODE_SET), new Header("cookie", _UrlKt.FRAGMENT_ENCODE_SET), new Header("date", _UrlKt.FRAGMENT_ENCODE_SET), new Header("etag", _UrlKt.FRAGMENT_ENCODE_SET), new Header("expect", _UrlKt.FRAGMENT_ENCODE_SET), new Header("expires", _UrlKt.FRAGMENT_ENCODE_SET), new Header("from", _UrlKt.FRAGMENT_ENCODE_SET), new Header("host", _UrlKt.FRAGMENT_ENCODE_SET), new Header("if-match", _UrlKt.FRAGMENT_ENCODE_SET), new Header("if-modified-since", _UrlKt.FRAGMENT_ENCODE_SET), new Header("if-none-match", _UrlKt.FRAGMENT_ENCODE_SET), new Header("if-range", _UrlKt.FRAGMENT_ENCODE_SET), new Header("if-unmodified-since", _UrlKt.FRAGMENT_ENCODE_SET), new Header("last-modified", _UrlKt.FRAGMENT_ENCODE_SET), new Header("link", _UrlKt.FRAGMENT_ENCODE_SET), new Header("location", _UrlKt.FRAGMENT_ENCODE_SET), new Header("max-forwards", _UrlKt.FRAGMENT_ENCODE_SET), new Header("proxy-authenticate", _UrlKt.FRAGMENT_ENCODE_SET), new Header("proxy-authorization", _UrlKt.FRAGMENT_ENCODE_SET), new Header("range", _UrlKt.FRAGMENT_ENCODE_SET), new Header("referer", _UrlKt.FRAGMENT_ENCODE_SET), new Header("refresh", _UrlKt.FRAGMENT_ENCODE_SET), new Header("retry-after", _UrlKt.FRAGMENT_ENCODE_SET), new Header("server", _UrlKt.FRAGMENT_ENCODE_SET), new Header("set-cookie", _UrlKt.FRAGMENT_ENCODE_SET), new Header("strict-transport-security", _UrlKt.FRAGMENT_ENCODE_SET), new Header("transfer-encoding", _UrlKt.FRAGMENT_ENCODE_SET), new Header("user-agent", _UrlKt.FRAGMENT_ENCODE_SET), new Header("vary", _UrlKt.FRAGMENT_ENCODE_SET), new Header("via", _UrlKt.FRAGMENT_ENCODE_SET), new Header("www-authenticate", _UrlKt.FRAGMENT_ENCODE_SET)};
        NAME_TO_FIRST_INDEX = r0.nameToFirstIndex();
    }

    private Hpack() {
    }

    private final Map<ByteString, Integer> nameToFirstIndex() {
        Header[] r0 = STATIC_HEADER_TABLE;
        LinkedHashMap r4 = new LinkedHashMap(r0.length, 1.0f);
        int r02 = r0.length;
        int r1 = 0;
    L3:
        if (r1 >= r02) goto L9;
        Header[] r2 = STATIC_HEADER_TABLE;
        if (r4.containsKey(r2[r1].name) == true) goto L7;
        r4.put(r2[r1].name, Integer.valueOf(r1));
    L7:
        r1 = r1 + 1;
        goto L3
    L9:
        return Collections.unmodifiableMap(r4);
    }

    public final ByteString checkLowercase(ByteString r4) {
        int r3 = r4.size();
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L12;
        byte r1 = r4.getByte(r0);
        if (65 > r1) goto L11;
        if (r1 >= 91) goto L11;
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("PROTOCOL_ERROR response malformed: mixed case name: ", r4.utf8());
        return null;
    L11:
        r0 = r0 + 1;
        goto L3
    L12:
        return r4;
    }

    public final Map<ByteString, Integer> getNAME_TO_FIRST_INDEX() {
        return NAME_TO_FIRST_INDEX;
    }

    public final Header[] getSTATIC_HEADER_TABLE() {
        return STATIC_HEADER_TABLE;
    }
}
