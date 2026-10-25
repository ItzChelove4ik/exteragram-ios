package okio.internal;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.BufferedSource;
import okio.FileHandle;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.ZipFileSystem;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a5\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004H\u0000¢\u0006\u0004\b\t\u0010\n\u001a)\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00050\r2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0013\u0010\u0011\u001a\u00020\u0005*\u00020\u0010H\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0013\u0010\u0014\u001a\u00020\u0013*\u00020\u0010H\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u001b\u0010\u0017\u001a\u00020\u0013*\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a5\u0010\u001f\u001a\u00020\u001d*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00192\u0018\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bH\u0002¢\u0006\u0004\b\u001f\u0010 \u001a\u0013\u0010!\u001a\u00020\u001d*\u00020\u0010H\u0000¢\u0006\u0004\b!\u0010\"\u001a\u001b\u0010$\u001a\u00020\u0005*\u00020\u00102\u0006\u0010#\u001a\u00020\u0005H\u0000¢\u0006\u0004\b$\u0010%\u001a\u001f\u0010&\u001a\u0004\u0018\u00010\u0005*\u00020\u00102\b\u0010#\u001a\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\b&\u0010%\u001a\u0017\u0010(\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u001cH\u0000¢\u0006\u0004\b(\u0010)\u001a!\u0010,\u001a\u0004\u0018\u00010\u001c2\u0006\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u0019H\u0000¢\u0006\u0004\b,\u0010-\"\u0018\u00101\u001a\u00020.*\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b/\u00100¨\u00062"}, d2 = {"Lokio/Path;", "zipPath", "Lokio/FileSystem;", "fileSystem", "Lkotlin/Function1;", "Lokio/internal/ZipEntry;", _UrlKt.FRAGMENT_ENCODE_SET, "predicate", "Lokio/ZipFileSystem;", "openZip", "(Lokio/Path;Lokio/FileSystem;Lkotlin/jvm/functions/Function1;)Lokio/ZipFileSystem;", _UrlKt.FRAGMENT_ENCODE_SET, "entries", _UrlKt.FRAGMENT_ENCODE_SET, "buildIndex", "(Ljava/util/List;)Ljava/util/Map;", "Lokio/BufferedSource;", "readCentralDirectoryZipEntry", "(Lokio/BufferedSource;)Lokio/internal/ZipEntry;", "Lokio/internal/EocdRecord;", "readEocdRecord", "(Lokio/BufferedSource;)Lokio/internal/EocdRecord;", "regularRecord", "readZip64EocdRecord", "(Lokio/BufferedSource;Lokio/internal/EocdRecord;)Lokio/internal/EocdRecord;", _UrlKt.FRAGMENT_ENCODE_SET, "extraSize", "Lkotlin/Function2;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "block", "readExtra", "(Lokio/BufferedSource;ILkotlin/jvm/functions/Function2;)V", "skipLocalHeader", "(Lokio/BufferedSource;)V", "centralDirectoryZipEntry", "readLocalHeader", "(Lokio/BufferedSource;Lokio/internal/ZipEntry;)Lokio/internal/ZipEntry;", "readOrSkipLocalHeader", "filetime", "filetimeToEpochMillis", "(J)J", "date", "time", "dosDateTimeToEpochMillis", "(II)Ljava/lang/Long;", _UrlKt.FRAGMENT_ENCODE_SET, "getHex", "(I)Ljava/lang/String;", "hex", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nZipFiles.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZipFiles.kt\nokio/internal/ZipFilesKt\n+ 2 Okio.kt\nokio/Okio__OkioKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,503:1\n58#2,4:504\n58#2,4:508\n58#2,22:512\n66#2,10:534\n62#2,3:544\n77#2,3:547\n58#2,22:550\n66#2,10:572\n62#2,3:582\n77#2,3:585\n1056#3:588\n*S KotlinDebug\n*F\n+ 1 ZipFiles.kt\nokio/internal/ZipFilesKt\n*L\n66#1:504,4\n101#1:508,4\n109#1:512,22\n101#1:534,10\n101#1:544,3\n101#1:547,3\n125#1:550,22\n66#1:572,10\n66#1:582,3\n66#1:585,3\n155#1:588\n*E\n"})
/* loaded from: classes5.dex */
public abstract class ZipFilesKt {
    /* JADX WARN: Type inference failed for: r10v3, types: [T, java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r11v2, types: [T, java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r14v6, types: [T, java.lang.Integer] */
    /* renamed from: $r8$lambda$00L8-DWbjy5sZTSutntgRZsY7ng, reason: not valid java name */
    public static Unit m3763$r8$lambda$00L8DWbjy5sZTSutntgRZsY7ng(BufferedSource r10, Ref.ObjectRef r11, Ref.ObjectRef r12, Ref.ObjectRef r13, int r14, long r15) {
        if (r14 != 21589) goto L37;
        long r0 = 1;
        if (r15 < 1) goto L34;
        byte r142 = r10.readByte();
        boolean r5 = false;
        if ((r142 & 1) != 1) goto L9;
        boolean r4 = true;
    L11:
        if ((r142 & 2) != 2) goto L13;
        boolean r7 = true;
    L15:
        if ((r142 & 4) != 4) goto L17;
        r5 = true;
    L17:
        if (r4 == false) goto L20;
        r0 = 5;
    L20:
        if (r7 == false) goto L22;
        r0 = r0 + 4;
    L22:
        if (r5 == false) goto L25;
        r0 = r0 + 4;
    L25:
        if (r15 < r0) goto L32;
        if (r4 == false) goto L28;
        r11.element = Integer.valueOf(r10.readIntLe());
    L28:
        if (r7 == false) goto L30;
        r12.element = Integer.valueOf(r10.readIntLe());
    L30:
        if (r5 == false) goto L37;
        r13.element = Integer.valueOf(r10.readIntLe());
        goto L37
    L32:
        Model$$ExternalSyntheticBUOutline0.m("bad zip: extended timestamp extra too short");
        return null;
    L13:
        r7 = false;
        goto L15
    L9:
        r4 = false;
        goto L11
    L34:
        Model$$ExternalSyntheticBUOutline0.m("bad zip: extended timestamp extra too short");
        return null;
    L37:
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Unit $r8$lambda$BY2EJxV3DltaiT8Wp4_9EammrI8(Ref.ObjectRef r0, BufferedSource r1, Ref.ObjectRef r2, Ref.ObjectRef r3, int r4, long r5) {
        return readCentralDirectoryZipEntry$lambda$1$0(r0, r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$fzsduIAs-uDmDx09oQZvsh85aRw, reason: not valid java name */
    public static Unit m3764$r8$lambda$fzsduIAsuDmDx09oQZvsh85aRw(Ref.BooleanRef r2, long r3, Ref.LongRef r5, BufferedSource r6, Ref.LongRef r7, Ref.LongRef r8, Ref.ObjectRef r9, Ref.ObjectRef r10, Ref.ObjectRef r11, int r12, long r13) {
        if (r12 == 1) goto L13;
        if (r12 != 10) goto L28;
        if (r13 < 4) goto L10;
        r6.skip(4);
        readExtra(r6, (int) (r13 - 4), new ZipFilesKt$$ExternalSyntheticLambda5(r9, r6, r10, r11));
        goto L28
    L10:
        Model$$ExternalSyntheticBUOutline0.m("bad zip: NTFS extra too short");
        return null;
    L28:
        return Unit.INSTANCE;
    L13:
        if (r2.element == true) goto L31;
        r2.element = true;
        if (r13 < r3) goto L29;
        long r22 = r5.element;
        if (r22 != 4294967295L) goto L19;
        r22 = r6.readLongLe();
    L19:
        r5.element = r22;
        long r32 = 0;
        if (r7.element != 4294967295L) goto L22;
        long r112 = r6.readLongLe();
    L23:
        r7.element = r112;
        if (r8.element != 4294967295L) goto L26;
        r32 = r6.readLongLe();
    L26:
        r8.element = r32;
        goto L28
    L22:
        r112 = 0;
        goto L23
    L29:
        Model$$ExternalSyntheticBUOutline0.m("bad zip: zip64 extra too short");
        return null;
    L31:
        Model$$ExternalSyntheticBUOutline0.m("bad zip: zip64 extra repeated");
        return null;
    }

    private static final Map<Path, ZipEntry> buildIndex(List<ZipEntry> r28) {
        Path r6 = Path.Companion.get$default(Path.Companion, "/", false, 1, null);
        Map<Path, ZipEntry> r0 = MapsKt.mutableMapOf(new Pair[]{TuplesKt.to(r6, new ZipEntry(r6, true, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null, 65532, null))});
        Iterator r1 = CollectionsKt.sortedWith(r28, new ZipFilesKt$buildIndex$$inlined$sortedBy$1()).iterator();
    L4:
        if (r1.hasNext() == false) goto L14;
        ZipEntry r2 = (ZipEntry) r1.next();
        if (r0.put(r2.getCanonicalPath(), r2) != null) goto L4;
    L7:
        Path r5 = r2.getCanonicalPath().parent();
        if (r5 == null) goto L4;
        ZipEntry r3 = r0.get(r5);
        if (r3 != null) goto L12;
        ZipEntry r4 = new ZipEntry(r5, true, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, null, 65532, null);
        r0.put(r5, r4);
        r4.getChildren().add(r2.getCanonicalPath());
        r2 = r4;
        goto L7
    L12:
        r3.getChildren().add(r2.getCanonicalPath());
        goto L4
    L14:
        return r0;
    }

    public static final Long dosDateTimeToEpochMillis(int r7, int r8) {
        if (r8 != (-1)) goto L7;
        return null;
    L7:
        return Long.valueOf(_ZlibJvmKt.datePartsToEpochMillis(((r7 >> 9) & 127) + 1980, (r7 >> 5) & 15, r7 & 31, (r8 >> 11) & 31, (r8 >> 5) & 63, (r8 & 31) << 1));
    }

    public static final long filetimeToEpochMillis(long r2) {
        return (r2 / NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) - 11644473600000L;
    }

    private static final String getHex(int r2) {
        return "0x" + Integer.toString(r2, CharsKt.checkRadix(16));
    }

    public static final ZipFileSystem openZip(Path r17, FileSystem r18, Function1<? super ZipEntry, Boolean> r19) {
        FileHandle r3 = r18.openReadOnly(r17);
        long r4 = r3.size();     // Catch: Throwable -> L60
        long r6 = r4 - 22;     // Catch: Throwable -> L60
        long r8 = 0;
        if (r6 < 0) goto L108;
        long r42 = Math.max(r4 - 65558, 0);     // Catch: Throwable -> L60
    L6:
        BufferedSource r10 = Okio.buffer(r3.source(r6));     // Catch: Throwable -> L60
    L98:
        th = move-exception;
        r10.close();     // Catch: Throwable -> L60
        throw th;     // Catch: Throwable -> L60
    L8:
        if (r10.readIntLe() == 101010256) goto L9;
        r10.close();     // Catch: Throwable -> L60
        r6 = r6 - 1;
        if (r6 >= r42) goto L6;
        throw new IOException("not a zip: end of central directory signature not found");     // Catch: Throwable -> L60
    L9:
        EocdRecord r43 = readEocdRecord(r10);     // Catch: Throwable -> L98
        String r5 = r10.readUtf8(r43.getCommentByteCount());     // Catch: Throwable -> L98
        r10.close();     // Catch: Throwable -> L60
        long r62 = r6 - 20;     // Catch: Throwable -> L60
        Throwable r102 = null;
        if (r62 <= 0) goto L66;
        BufferedSource r63 = Okio.buffer(r3.source(r62));     // Catch: Throwable -> L60
    L44:
        th = th;
        EocdRecord r11 = r43;
    L39:
        Throwable r44 = th;
        if (r63 != null) goto L125;
    L62:
        Throwable th = r44;
        r43 = r11;
    L63:
        if (th == null) goto L66;
        throw th;     // Catch: Throwable -> L60
    L125:
        r63.close();     // Catch: Throwable -> L58
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L58
    L58:
        th = move-exception;
        ExceptionsKt.addSuppressed(r44, th);     // Catch: Throwable -> L60
        goto L62
    L14:
        if (r63.readIntLe() != 117853008) goto L48;
        int r02 = r63.readIntLe();     // Catch: Throwable -> L44
        long r112 = r63.readLongLe();     // Catch: Throwable -> L44
        if (r63.readIntLe() != 1) goto L47;
        if (r02 != 0) goto L47;
        BufferedSource r7 = Okio.buffer(r3.source(r112));     // Catch: Throwable -> L44
        int r03 = r7.readIntLe();     // Catch: Throwable -> L28
        if (r03 != 101075792) goto L31;
        r43 = readZip64EocdRecord(r7, r43);     // Catch: Throwable -> L28
        Unit r04 = Unit.INSTANCE;     // Catch: Throwable -> L28
        if (r7 != null) goto L120;
    L27:
        Throwable th2 = null;
    L41:
        if (th2 == null) goto L48;
        throw th2;     // Catch: Throwable -> L44
    L120:
        r7.close();     // Catch: Throwable -> L25
    L25:
        th2 = th;
        goto L41
    L31:
        throw new IOException("bad zip: expected " + getHex(101075792) + " but was " + getHex(r03));     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        r11 = r43;
        if (r7 != null) goto L138;
    L40:
        th2 = th;
        r43 = r11;
        goto L41
    L138:
        r7.close();     // Catch: Throwable -> L35
        Unit r05 = Unit.INSTANCE;     // Catch: Throwable -> L35
    L35:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);     // Catch: Throwable -> L38
    L38:
        th = th;
    L47:
        throw new IOException("unsupported zip: spanned");     // Catch: Throwable -> L44
    L48:
        Unit r06 = Unit.INSTANCE;     // Catch: Throwable -> L44
        if (r63 != null) goto L116;
    L54:
        th = null;
        goto L63
    L116:
        r63.close();     // Catch: Throwable -> L52
    L52:
        th = th;
    L66:
        ArrayList r64 = new ArrayList();     // Catch: Throwable -> L60
        BufferedSource r72 = Okio.buffer(r3.source(r43.getCentralDirectoryOffset()));     // Catch: Throwable -> L60
        long r113 = r43.getEntryCount();     // Catch: Throwable -> L75
    L69:
        if (r8 >= r113) goto L80;
        ZipEntry r07 = readCentralDirectoryZipEntry(r72);     // Catch: Throwable -> L75
        if (r07.getOffset() >= r43.getCentralDirectoryOffset()) goto L79;
        if (r19.invoke(r07).booleanValue() == false) goto L77;
        r64.add(r07);     // Catch: Throwable -> L75
    L77:
        r8 = r8 + 1;     // Catch: Throwable -> L75
        goto L69
    L79:
        throw new IOException("bad zip: local file header offset >= central directory offset");     // Catch: Throwable -> L75
    L80:
        Unit r08 = Unit.INSTANCE;     // Catch: Throwable -> L75
        if (r72 != null) goto L128;
    L92:
        if (r102 != null) goto L97;
        ZipFileSystem r45 = new ZipFileSystem(r17, r18, buildIndex(r64), r5);     // Catch: Throwable -> L60
        if (r3 != null) goto L130;
    L96:
        return r45;
    L130:
        r3.close();     // Catch: Throwable -> L115
        Unit r09 = Unit.INSTANCE;     // Catch: Throwable -> L115
        goto L96
    L97:
        throw r102;     // Catch: Throwable -> L60
    L128:
        r72.close();     // Catch: Throwable -> L84
    L84:
        th = move-exception;
        r102 = th;
    L75:
        th = move-exception;
        if (r72 != null) goto L136;
    L91:
        r102 = th;
        goto L92
    L136:
        r72.close();     // Catch: Throwable -> L89
        Unit r010 = Unit.INSTANCE;     // Catch: Throwable -> L89
    L89:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);     // Catch: Throwable -> L60
        goto L91
    L108:
        throw new IOException("not a zip: size=" + r3.size());     // Catch: Throwable -> L60
    L60:
        th = move-exception;
        if (r3 == null) goto L146;
        r3.close();     // Catch: Throwable -> L112
        Unit r011 = Unit.INSTANCE;     // Catch: Throwable -> L112
        throw th;
    L112:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        throw th;
    L146:
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final ZipEntry readCentralDirectoryZipEntry(BufferedSource r36) {
        int r0 = r36.readIntLe();
        if (r0 != 33639248) goto L32;
        r36.skip(4);
        short r02 = r36.readShortLe();
        int r2 = r02 & UShort.MAX_VALUE;
        if ((r02 & 1) != 0) goto L30;
        int r23 = r36.readShortLe() & UShort.MAX_VALUE;
        int r27 = r36.readShortLe() & UShort.MAX_VALUE;
        int r26 = r36.readShortLe() & UShort.MAX_VALUE;
        long r17 = r36.readIntLe() & 4294967295L;
        Ref.LongRef r6 = new Ref.LongRef();
        r6.element = r36.readIntLe() & 4294967295L;
        Ref.LongRef r4 = new Ref.LongRef();
        r4.element = r36.readIntLe() & 4294967295L;
        int r03 = r36.readShortLe() & UShort.MAX_VALUE;
        int r13 = r36.readShortLe() & UShort.MAX_VALUE;
        int r14 = r36.readShortLe() & UShort.MAX_VALUE;
        r36.skip(8);
        Ref.LongRef r7 = new Ref.LongRef();
        r7.element = r36.readIntLe() & 4294967295L;
        String r04 = r36.readUtf8(r03);
        if (StringsKt.contains$default(r04, 0, false, 2, null) == false) goto L9;
        Model$$ExternalSyntheticBUOutline0.m("bad zip: filename contains 0x00");
        return null;
    L9:
        if (r4.element != 4294967295L) goto L12;
        long r1 = 8;
    L14:
        if (r6.element != 4294967295L) goto L17;
        r1 = r1 + 8;
    L17:
        if (r7.element != 4294967295L) goto L19;
        r1 = r1 + 8;
    L19:
        long r22 = r1;
        Ref.ObjectRef r8 = new Ref.ObjectRef();
        Ref.ObjectRef r9 = new Ref.ObjectRef();
        Ref.ObjectRef r10 = new Ref.ObjectRef();
        Ref.BooleanRef r12 = new Ref.BooleanRef();
        readExtra(r36, r13, new ZipFilesKt$$ExternalSyntheticLambda4(r12, r22, r4, r36, r6, r7, r8, r9, r10));
        if (r22 <= 0) goto L27;
        if (r12.element == true) goto L27;
        Model$$ExternalSyntheticBUOutline0.m("bad zip: zip64 extra required but absent");
        return null;
    L27:
        return new ZipEntry(Path.Companion.get$default(Path.Companion, "/", false, 1, null).resolve(r04), StringsKt.endsWith$default(r04, "/", false, 2, null), r36.readUtf8(r14), r17, r6.element, r4.element, r23, r7.element, r26, r27, (Long) r8.element, (Long) r9.element, (Long) r10.element, null, null, null, 57344, null);
    L12:
        r1 = 0;
        goto L14
    L30:
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("unsupported zip: general purpose bit flag=", getHex(r2));
        return null;
    L32:
        ZipFilesKt$$ExternalSyntheticBUOutline2.m("bad zip: expected ", getHex(33639248), " but was ", getHex(r0));
        return null;
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Long] */
    /* JADX WARN: Type inference failed for: r3v6, types: [T, java.lang.Long] */
    /* JADX WARN: Type inference failed for: r7v4, types: [T, java.lang.Long] */
    private static final Unit readCentralDirectoryZipEntry$lambda$1$0(Ref.ObjectRef r3, BufferedSource r4, Ref.ObjectRef r5, Ref.ObjectRef r6, int r7, long r8) {
        if (r7 != 1) goto L14;
        if (r3.element == 0) goto L7;
        Model$$ExternalSyntheticBUOutline0.m("bad zip: NTFS extra attribute tag 0x0001 repeated");
        return null;
    L7:
        if (r8 != 24) goto L9;
        r3.element = Long.valueOf(r4.readLongLe());
        r5.element = Long.valueOf(r4.readLongLe());
        r6.element = Long.valueOf(r4.readLongLe());
        goto L14
    L9:
        Model$$ExternalSyntheticBUOutline0.m("bad zip: NTFS extra attribute tag 0x0001 size != 24");
        return null;
    L14:
        return Unit.INSTANCE;
    }

    private static final EocdRecord readEocdRecord(BufferedSource r10) {
        int r0 = r10.readShortLe() & UShort.MAX_VALUE;
        int r2 = r10.readShortLe() & UShort.MAX_VALUE;
        long r5 = r10.readShortLe() & UShort.MAX_VALUE;
        if (r5 != (r10.readShortLe() & UShort.MAX_VALUE)) goto L8;
        if (r0 != 0) goto L8;
        if (r2 != 0) goto L8;
        r10.skip(4);
        return new EocdRecord(r5, 4294967295L & r10.readIntLe(), r10.readShortLe() & UShort.MAX_VALUE);
    L8:
        Model$$ExternalSyntheticBUOutline0.m("unsupported zip: spanned");
        return null;
    }

    private static final void readExtra(BufferedSource r10, int r11, Function2<? super Integer, ? super Long, Unit> r12) {
        long r0 = r11;
    L4:
        if (r0 == 0) goto L26;
        if (r0 < 4) goto L18;
        int r112 = r10.readShortLe() & UShort.MAX_VALUE;
        long r6 = r10.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
        long r02 = r0 - 4;
        if (r02 < r6) goto L16;
        r10.require(r6);
        long r4 = r10.getBuffer().size();
        r12.invoke(Integer.valueOf(r112), Long.valueOf(r6));
        long r8 = (r10.getBuffer().size() + r6) - r4;
        if (r8 < 0) goto L14;
        if (r8 <= 0) goto L13;
        r10.getBuffer().skip(r8);
    L13:
        r0 = r02 - r6;
        goto L4
    L14:
        ZipFilesKt$$ExternalSyntheticBUOutline0.m("unsupported zip: too many bytes processed for ", r112);
        return;
    L16:
        Model$$ExternalSyntheticBUOutline0.m("bad zip: truncated value in extra field");
        return;
    L18:
        Model$$ExternalSyntheticBUOutline0.m("bad zip: truncated header in extra field");
        return;
    }

    public static final ZipEntry readLocalHeader(BufferedSource r0, ZipEntry r1) {
        return readOrSkipLocalHeader(r0, r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final ZipEntry readOrSkipLocalHeader(BufferedSource r7, ZipEntry r8) {
        int r0 = r7.readIntLe();
        if (r0 != 67324752) goto L14;
        r7.skip(2);
        short r02 = r7.readShortLe();
        int r3 = r02 & UShort.MAX_VALUE;
        if ((r02 & 1) != 0) goto L12;
        r7.skip(18);
        long r32 = r7.readShortLe() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
        int r03 = r7.readShortLe() & UShort.MAX_VALUE;
        r7.skip(r32);
        if (r8 != null) goto L10;
        r7.skip(r03);
        return null;
    L10:
        Ref.ObjectRef r1 = new Ref.ObjectRef();
        Ref.ObjectRef r2 = new Ref.ObjectRef();
        Ref.ObjectRef r33 = new Ref.ObjectRef();
        readExtra(r7, r03, new ZipFilesKt$$ExternalSyntheticLambda3(r7, r1, r2, r33));
        return r8.copy$okio((Integer) r1.element, (Integer) r2.element, (Integer) r33.element);
    L12:
        ZipFilesKt$$ExternalSyntheticBUOutline1.m("unsupported zip: general purpose bit flag=", getHex(r3));
        return null;
    L14:
        ZipFilesKt$$ExternalSyntheticBUOutline2.m("bad zip: expected ", getHex(67324752), " but was ", getHex(r0));
        return null;
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource r8, EocdRecord r9) {
        r8.skip(12);
        int r0 = r8.readIntLe();
        int r1 = r8.readIntLe();
        long r3 = r8.readLongLe();
        if (r3 != r8.readLongLe()) goto L8;
        if (r0 != 0) goto L8;
        if (r1 != 0) goto L8;
        r8.skip(8);
        return new EocdRecord(r3, r8.readLongLe(), r9.getCommentByteCount());
    L8:
        Model$$ExternalSyntheticBUOutline0.m("unsupported zip: spanned");
        return null;
    }

    public static final void skipLocalHeader(BufferedSource r1) {
        readOrSkipLocalHeader(r1, null);
    }
}
