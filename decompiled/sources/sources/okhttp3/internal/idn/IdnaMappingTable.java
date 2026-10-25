package okhttp3.internal.idn;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSink;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J \u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000fH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lokhttp3/internal/idn/IdnaMappingTable;", _UrlKt.FRAGMENT_ENCODE_SET, "sections", _UrlKt.FRAGMENT_ENCODE_SET, "ranges", "mappings", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSections", "()Ljava/lang/String;", "getRanges", "getMappings", "map", _UrlKt.FRAGMENT_ENCODE_SET, "codePoint", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/BufferedSink;", "findSectionsIndex", "findRangesOffset", "position", "limit", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIdnaMappingTable.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IdnaMappingTable.kt\nokhttp3/internal/idn/IdnaMappingTable\n+ 2 IdnaMappingTable.kt\nokhttp3/internal/idn/IdnaMappingTableKt\n*L\n1#1,286:1\n272#2,13:287\n272#2,13:300\n*S KotlinDebug\n*F\n+ 1 IdnaMappingTable.kt\nokhttp3/internal/idn/IdnaMappingTable\n*L\n209#1:287,13\n237#1:300,13\n*E\n"})
/* loaded from: classes.dex */
public final class IdnaMappingTable {
    private final String mappings;
    private final String ranges;
    private final String sections;

    public IdnaMappingTable(String r1, String r2, String r3) {
        this.sections = r1;
        this.ranges = r2;
        this.mappings = r3;
    }

    private final int findRangesOffset(int r4, int r5, int r6) {
        int r42 = r4 & 127;
        int r62 = r6 - 1;
    L3:
        if (r5 > r62) goto L9;
        int r0 = (r5 + r62) / 2;
        int r1 = Intrinsics.compare(r42, this.ranges.charAt(r0 * 4));
        if (r1 < 0) goto L6;
        if (r1 <= 0) goto L10;
        r5 = r0 + 1;
    L10:
        if (r0 < 0) goto L14;
        return r0 * 4;
    L14:
        return ((-r0) - 2) * 4;
    L6:
        r62 = r0 - 1;
        goto L3
    L9:
        r0 = (-r5) - 1;
        goto L10
    }

    private final int findSectionsIndex(int r6) {
        int r62 = (r6 & 2097024) >> 7;
        int r0 = (this.sections.length() / 4) - 1;
        int r1 = 0;
    L3:
        if (r1 > r0) goto L9;
        int r2 = (r1 + r0) / 2;
        int r3 = Intrinsics.compare(r62, IdnaMappingTableKt.read14BitInt(this.sections, r2 * 4));
        if (r3 < 0) goto L6;
        if (r3 <= 0) goto L10;
        r1 = r2 + 1;
    L10:
        if (r2 < 0) goto L14;
        return r2 * 4;
    L14:
        return ((-r2) - 2) * 4;
    L6:
        r0 = r2 - 1;
        goto L3
    L9:
        r2 = (-r1) - 1;
        goto L10
    }

    public final String getMappings() {
        return this.mappings;
    }

    public final String getRanges() {
        return this.ranges;
    }

    public final String getSections() {
        return this.sections;
    }

    public final boolean map(int r5, BufferedSink r6) {
        int r0 = findSectionsIndex(r5);
        int r1 = IdnaMappingTableKt.read14BitInt(this.sections, r0 + 2);
        if ((r0 + 4) >= this.sections.length()) goto L5;
        int r02 = IdnaMappingTableKt.read14BitInt(this.sections, r0 + 6);
    L6:
        int r03 = findRangesOffset(r5, r1, r02);
        char r12 = this.ranges.charAt(r03 + 1);
        if (r12 < 0) goto L11;
        if (r12 >= '@') goto L11;
        int r52 = IdnaMappingTableKt.read14BitInt(this.ranges, r03 + 2);
        r6.writeUtf8(this.mappings, r52, r12 + r52);
        return true;
    L11:
        if ('@' > r12) goto L14;
        if (r12 >= 'P') goto L14;
        r6.writeUtf8CodePoint(r5 - (this.ranges.charAt(r03 + 3) | (((r12 & 15) << 14) | (this.ranges.charAt(r03 + 2) << 7))));
        return true;
    L14:
        if ('P' > r12) goto L19;
        if (r12 >= '`') goto L19;
        r6.writeUtf8CodePoint(r5 + (this.ranges.charAt(r03 + 3) | (((r12 & 15) << 14) | (this.ranges.charAt(r03 + 2) << 7))));
        return true;
    L19:
        if (r12 != 'w') goto L22;
        Unit r4 = Unit.INSTANCE;
        return true;
    L22:
        if (r12 != 'x') goto L25;
        r6.writeUtf8CodePoint(r5);
        return true;
    L25:
        if (r12 != 'y') goto L29;
        r6.writeUtf8CodePoint(r5);
        return false;
    L29:
        if (r12 != 'z') goto L32;
        r6.writeByte(this.ranges.charAt(r03 + 2));
        return true;
    L32:
        if (r12 != '{') goto L35;
        r6.writeByte(this.ranges.charAt(r03 + 2) | 128);
        return true;
    L35:
        if (r12 != '|') goto L38;
        r6.writeByte(this.ranges.charAt(r03 + 2));
        r6.writeByte(this.ranges.charAt(r03 + 3));
        return true;
    L38:
        if (r12 != '}') goto L41;
        r6.writeByte(this.ranges.charAt(r03 + 2) | 128);
        r6.writeByte(this.ranges.charAt(r03 + 3));
        return true;
    L41:
        if (r12 != '~') goto L44;
        r6.writeByte(this.ranges.charAt(r03 + 2));
        r6.writeByte(this.ranges.charAt(r03 + 3) | 128);
        return true;
    L44:
        if (r12 != 127) goto L49;
        r6.writeByte(this.ranges.charAt(r03 + 2) | 128);
        r6.writeByte(this.ranges.charAt(r03 + 3) | 128);
        return true;
    L49:
        throw new IllegalStateException(("unexpected rangesIndex for " + r5).toString());
    L5:
        r02 = this.ranges.length() / 4;
        goto L6
    }
}
