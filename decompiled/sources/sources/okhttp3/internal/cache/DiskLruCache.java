package okhttp3.internal.cache;

import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.Closeable;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import okhttp3.HttpUrl$Builder$$ExternalSyntheticBUOutline0;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal._UtilJvmKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.concurrent.Lockable;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.Sink;
import okio.Source;
import okio.ZipFileSystem$$ExternalSyntheticBUOutline0;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u0083\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010)\n\u0002\b\u0005*\u00019\u0018\u0000 a2\u00020\u00012\u00020\u00022\u00020\u0003:\u0004^_`aB7\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010;\u001a\u00020<J\b\u0010=\u001a\u00020<H\u0002J\b\u0010>\u001a\u00020!H\u0002J\u0010\u0010?\u001a\u00020<2\u0006\u0010@\u001a\u00020$H\u0002J\b\u0010A\u001a\u00020<H\u0002J\r\u0010B\u001a\u00020<H\u0000¢\u0006\u0002\bCJ\u0017\u0010D\u001a\b\u0018\u00010ER\u00020\u00002\u0006\u0010F\u001a\u00020$H\u0086\u0002J \u0010G\u001a\b\u0018\u00010HR\u00020\u00002\u0006\u0010F\u001a\u00020$2\b\b\u0002\u0010I\u001a\u00020\fH\u0007J\u0006\u0010\u001f\u001a\u00020\fJ!\u0010J\u001a\u00020<2\n\u0010K\u001a\u00060HR\u00020\u00002\u0006\u0010L\u001a\u00020+H\u0000¢\u0006\u0002\bMJ\b\u0010N\u001a\u00020+H\u0002J\u000e\u0010O\u001a\u00020+2\u0006\u0010F\u001a\u00020$J\u0019\u0010P\u001a\u00020+2\n\u0010Q\u001a\u00060%R\u00020\u0000H\u0000¢\u0006\u0002\bRJ\b\u0010S\u001a\u00020<H\u0002J\b\u0010T\u001a\u00020<H\u0016J\u0006\u0010U\u001a\u00020+J\b\u0010V\u001a\u00020<H\u0016J\u0006\u0010W\u001a\u00020<J\b\u0010X\u001a\u00020+H\u0002J\u0006\u0010Y\u001a\u00020<J\u0006\u0010Z\u001a\u00020<J\u0010\u0010[\u001a\u00020<2\u0006\u0010F\u001a\u00020$H\u0002J\u0010\u0010\\\u001a\f\u0012\b\u0012\u00060ER\u00020\u00000]R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R$\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R8\u0010\"\u001a&\u0012\u0004\u0012\u00020$\u0012\b\u0012\u00060%R\u00020\u00000#j\u0012\u0012\u0004\u0012\u00020$\u0012\b\u0012\u00060%R\u00020\u0000`&X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u000e\u0010)\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u00020+X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u000e\u00103\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u000209X\u0082\u0004¢\u0006\u0004\n\u0002\u0010:¨\u0006b"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "Lokhttp3/internal/concurrent/Lockable;", "fileSystem", "Lokio/FileSystem;", "directory", "Lokio/Path;", "appVersion", _UrlKt.FRAGMENT_ENCODE_SET, "valueCount", "maxSize", _UrlKt.FRAGMENT_ENCODE_SET, "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "<init>", "(Lokio/FileSystem;Lokio/Path;IIJLokhttp3/internal/concurrent/TaskRunner;)V", "getDirectory", "()Lokio/Path;", "getValueCount$okhttp", "()I", "getFileSystem$okhttp", "()Lokio/FileSystem;", "value", "getMaxSize", "()J", "setMaxSize", "(J)V", "journalFile", "journalFileTmp", "journalFileBackup", "size", "journalWriter", "Lokio/BufferedSink;", "lruEntries", "Ljava/util/LinkedHashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lkotlin/collections/LinkedHashMap;", "getLruEntries$okhttp", "()Ljava/util/LinkedHashMap;", "redundantOpCount", "hasJournalErrors", _UrlKt.FRAGMENT_ENCODE_SET, "civilizedFileSystem", "initialized", "closed", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "mostRecentTrimFailed", "mostRecentRebuildFailed", "nextSequenceNumber", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/cache/DiskLruCache$cleanupTask$1", "Lokhttp3/internal/cache/DiskLruCache$cleanupTask$1;", "initialize", _UrlKt.FRAGMENT_ENCODE_SET, "readJournal", "newJournalWriter", "readJournalLine", "line", "processJournal", "rebuildJournal", "rebuildJournal$okhttp", "get", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", PluginsConstants.Settings.KEY, "edit", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "expectedSequenceNumber", "completeEdit", "editor", "success", "completeEdit$okhttp", "journalRebuildRequired", "remove", "removeEntry", "entry", "removeEntry$okhttp", "checkNotClosed", "flush", "isClosed", "close", "trimToSize", "removeOldestEntry", "delete", "evictAll", "validateKey", "snapshots", _UrlKt.FRAGMENT_ENCODE_SET, "Snapshot", "Editor", "Entry", "Companion", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDiskLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n+ 4 Okio.kt\nokio/Okio__OkioKt\n+ 5 FileSystem.kt\nokio/FileSystem\n+ 6 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,1120:1\n1#2:1121\n55#3,4:1122\n55#3,4:1189\n72#4:1126\n58#4,4:1128\n66#4,10:1133\n62#4,3:1143\n77#4,3:1146\n58#4,4:1152\n66#4,10:1157\n62#4,18:1167\n73#5:1127\n74#5:1132\n86#5:1149\n191#5:1150\n95#5:1151\n96#5:1156\n37#6,2:1185\n37#6,2:1187\n*S KotlinDebug\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache\n*L\n230#1:1122,4\n324#1:1189,4\n279#1:1126\n279#1:1128,4\n279#1:1133,10\n279#1:1143,3\n279#1:1146,3\n411#1:1152,4\n411#1:1157,10\n411#1:1167,18\n279#1:1127\n279#1:1132\n411#1:1149\n411#1:1150\n411#1:1151\n411#1:1156\n715#1:1185,2\n765#1:1187,2\n*E\n"})
/* loaded from: classes5.dex */
public final class DiskLruCache implements Closeable, Flushable, Lockable {

    @JvmField
    public static final long ANY_SEQUENCE_NUMBER = 0;

    @JvmField
    public static final String CLEAN = null;
    public static final Companion Companion = null;

    @JvmField
    public static final String DIRTY = null;

    @JvmField
    public static final String JOURNAL_FILE = null;

    @JvmField
    public static final String JOURNAL_FILE_BACKUP = null;

    @JvmField
    public static final String JOURNAL_FILE_TEMP = null;

    @JvmField
    public static final Regex LEGAL_KEY_PATTERN = null;

    @JvmField
    public static final String MAGIC = null;

    @JvmField
    public static final String READ = null;

    @JvmField
    public static final String REMOVE = null;

    @JvmField
    public static final String VERSION_1 = null;
    private final int appVersion;
    private boolean civilizedFileSystem;
    private final TaskQueue cleanupQueue;
    private final DiskLruCache$cleanupTask$1 cleanupTask;
    private boolean closed;
    private final Path directory;
    private final FileSystem fileSystem;
    private boolean hasJournalErrors;
    private boolean initialized;
    private final Path journalFile;
    private final Path journalFileBackup;
    private final Path journalFileTmp;
    private BufferedSink journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries;
    private long maxSize;
    private boolean mostRecentRebuildFailed;
    private boolean mostRecentTrimFailed;
    private long nextSequenceNumber;
    private int redundantOpCount;
    private long size;
    private final int valueCount;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00058\u0006X\u0087D¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "JOURNAL_FILE", _UrlKt.FRAGMENT_ENCODE_SET, "JOURNAL_FILE_TEMP", "JOURNAL_FILE_BACKUP", "MAGIC", "VERSION_1", "ANY_SEQUENCE_NUMBER", _UrlKt.FRAGMENT_ENCODE_SET, "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "CLEAN", "DIRTY", "REMOVE", "READ", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0015\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0018\u001a\u00020\u0010J\u0006\u0010\u0019\u001a\u00020\u0010R\u0018\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Editor;", _UrlKt.FRAGMENT_ENCODE_SET, "entry", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lokhttp3/internal/cache/DiskLruCache;", "<init>", "(Lokhttp3/internal/cache/DiskLruCache;Lokhttp3/internal/cache/DiskLruCache$Entry;)V", "getEntry$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Entry;", "written", _UrlKt.FRAGMENT_ENCODE_SET, "getWritten$okhttp", "()[Z", "done", _UrlKt.FRAGMENT_ENCODE_SET, "detach", _UrlKt.FRAGMENT_ENCODE_SET, "detach$okhttp", "newSource", "Lokio/Source;", "index", _UrlKt.FRAGMENT_ENCODE_SET, "newSink", "Lokio/Sink;", "commit", "abort", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class Editor {
        private boolean done;
        private final Entry entry;
        final /* synthetic */ DiskLruCache this$0;
        private final boolean[] written;

        public static /* synthetic */ Unit $r8$lambda$A4Vm2KUtMTjVaHywVLE7iXxpmms(DiskLruCache r0, Editor r1, IOException r2) {
            return newSink$lambda$0$0(r0, r1, r2);
        }

        public Editor(DiskLruCache r1, Entry r2) {
            this.this$0 = r1;
            this.entry = r2;
            if (r2.getReadable$okhttp() == false) goto L5;
            boolean[] r12 = null;
        L6:
            this.written = r12;
            return;
        L5:
            r12 = new boolean[r1.getValueCount$okhttp()];
            goto L6
        }

        private static final Unit newSink$lambda$0$0(DiskLruCache r0, Editor r1, IOException r2) {
            monitor-enter(r0);
            r1.detach$okhttp();     // Catch: Throwable -> L7
            monitor-exit(r0);
            return Unit.INSTANCE;
        L7:
            th = move-exception;
            throw th;
        }

        public final void abort() {
            DiskLruCache r0 = this.this$0;
            monitor-enter(r0);
        L9:
            th = move-exception;
            throw th;
        L5:
            if (this.done == true) goto L15;
            if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) == false) goto L11;
            r0.completeEdit$okhttp(this, false);     // Catch: Throwable -> L9
        L11:
            this.done = true;     // Catch: Throwable -> L9
            Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L9
            monitor-exit(r0);
            return;
        L15:
            throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L9
        }

        public final void commit() {
            DiskLruCache r0 = this.this$0;
            monitor-enter(r0);
        L9:
            th = move-exception;
            throw th;
        L5:
            if (this.done == true) goto L15;
            if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) == false) goto L11;
            r0.completeEdit$okhttp(this, true);     // Catch: Throwable -> L9
        L11:
            this.done = true;     // Catch: Throwable -> L9
            Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L9
            monitor-exit(r0);
            return;
        L15:
            throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L9
        }

        public final void detach$okhttp() {
            if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) == true) goto L5;
            return;
        L5:
            if (DiskLruCache.access$getCivilizedFileSystem$p(this.this$0) == false) goto L8;
            this.this$0.completeEdit$okhttp(this, false);
            return;
        L8:
            this.entry.setZombie$okhttp(true);
        }

        public final Entry getEntry$okhttp() {
            return this.entry;
        }

        public final boolean[] getWritten$okhttp() {
            return this.written;
        }

        public final Sink newSink(int r4) {
            DiskLruCache r0 = this.this$0;
            monitor-enter(r0);
        L11:
            th = move-exception;
            throw th;
        L5:
            if (this.done == true) goto L25;
            if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) == true) goto L14;
            Sink r3 = Okio.blackhole();     // Catch: Throwable -> L11
            monitor-exit(r0);
            return r3;
        L14:
            if (this.entry.getReadable$okhttp() == true) goto L16;
            this.written[r4] = true;     // Catch: Throwable -> L11
        L16:
            Path r42 = this.entry.getDirtyFiles$okhttp().get(r4);     // Catch: Throwable -> L11
            Sink r43 = r0.getFileSystem$okhttp().sink(r42);     // Catch: Throwable -> L11 FileNotFoundException -> L21
            FaultHidingSink r1 = new FaultHidingSink(r43, new DiskLruCache$Editor$$ExternalSyntheticLambda0(r0, this));     // Catch: Throwable -> L11
            monitor-exit(r0);
            return r1;
        L23:
            return Okio.blackhole();
        L25:
            throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L11
        }

        public final Source newSource(int r4) {
            DiskLruCache r0 = this.this$0;
            monitor-enter(r0);
        L15:
            th = move-exception;
            throw th;
        L5:
            if (this.done == true) goto L22;
            Source r2 = null;
            if (this.entry.getReadable$okhttp() == true) goto L9;
        L19:
            monitor-exit(r0);
            return null;
        L9:
            if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this) == false) goto L19;
            if (this.entry.getZombie$okhttp() == true) goto L19;
            r2 = r0.getFileSystem$okhttp().source(this.entry.getCleanFiles$okhttp().get(r4));     // Catch: Throwable -> L15 FileNotFoundException -> L25
        L17:
            monitor-exit(r0);
            return r2;
        L22:
            throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L15
        }
    }

    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010/\u001a\u0002002\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000302H\u0000¢\u0006\u0002\b3J\u0015\u00104\u001a\u0002002\u0006\u00105\u001a\u000206H\u0000¢\u0006\u0002\b7J\u0016\u00108\u001a\u0002092\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000302H\u0002J\u0013\u0010:\u001a\b\u0018\u00010;R\u00020\u001eH\u0000¢\u0006\u0002\b<J\u0010\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020$H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0013\u001a\u00020\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R \u0010\u001c\u001a\b\u0018\u00010\u001dR\u00020\u001eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u0006@"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Entry;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;)V", "getKey$okhttp", "()Ljava/lang/String;", "lengths", _UrlKt.FRAGMENT_ENCODE_SET, "getLengths$okhttp", "()[J", "cleanFiles", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/Path;", "getCleanFiles$okhttp", "()Ljava/util/List;", "dirtyFiles", "getDirtyFiles$okhttp", "readable", _UrlKt.FRAGMENT_ENCODE_SET, "getReadable$okhttp", "()Z", "setReadable$okhttp", "(Z)V", "zombie", "getZombie$okhttp", "setZombie$okhttp", "currentEditor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getCurrentEditor$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Editor;", "setCurrentEditor$okhttp", "(Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "lockingSourceCount", _UrlKt.FRAGMENT_ENCODE_SET, "getLockingSourceCount$okhttp", "()I", "setLockingSourceCount$okhttp", "(I)V", "sequenceNumber", _UrlKt.FRAGMENT_ENCODE_SET, "getSequenceNumber$okhttp", "()J", "setSequenceNumber$okhttp", "(J)V", "setLengths", _UrlKt.FRAGMENT_ENCODE_SET, "strings", _UrlKt.FRAGMENT_ENCODE_SET, "setLengths$okhttp", "writeLengths", "writer", "Lokio/BufferedSink;", "writeLengths$okhttp", "invalidLengths", _UrlKt.FRAGMENT_ENCODE_SET, "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "snapshot$okhttp", "newSource", "Lokio/Source;", "index", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nDiskLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache$Entry\n+ 2 Lockable.kt\nokhttp3/internal/concurrent/LockableKt\n*L\n1#1,1120:1\n55#2,4:1121\n*S KotlinDebug\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache$Entry\n*L\n1045#1:1121,4\n*E\n"})
    public final class Entry {
        private final List<Path> cleanFiles;
        private Editor currentEditor;
        private final List<Path> dirtyFiles;
        private final String key;
        private final long[] lengths;
        private int lockingSourceCount;
        private boolean readable;
        private long sequenceNumber;
        final /* synthetic */ DiskLruCache this$0;
        private boolean zombie;

        public Entry(DiskLruCache r6, String r7) {
            this.this$0 = r6;
            this.key = r7;
            this.lengths = new long[r6.getValueCount$okhttp()];
            this.cleanFiles = new ArrayList();
            this.dirtyFiles = new ArrayList();
            StringBuilder r0 = new StringBuilder(r7);
            r0.append('.');
            int r72 = r0.length();
            int r62 = r6.getValueCount$okhttp();
            int r1 = 0;
        L3:
            if (r1 >= r62) goto L5;
            r0.append(r1);
            this.cleanFiles.add(this.this$0.getDirectory().resolve(r0.toString()));
            r0.append(".tmp");
            this.dirtyFiles.add(this.this$0.getDirectory().resolve(r0.toString()));
            r0.setLength(r72);
            r1 = r1 + 1;
            goto L3
        }

        private final Void invalidLengths(List<String> r3) {
            throw new IOException("unexpected journal line: " + r3);
        }

        private final Source newSource(int r3) {
            Source r32 = this.this$0.getFileSystem$okhttp().source(this.cleanFiles.get(r3));
            if (DiskLruCache.access$getCivilizedFileSystem$p(this.this$0) == false) goto L5;
            return r32;
        L5:
            this.lockingSourceCount++;
            return new DiskLruCache$Entry$newSource$1(r32, this.this$0, this);
        }

        public final List<Path> getCleanFiles$okhttp() {
            return this.cleanFiles;
        }

        public final Editor getCurrentEditor$okhttp() {
            return this.currentEditor;
        }

        public final List<Path> getDirtyFiles$okhttp() {
            return this.dirtyFiles;
        }

        public final String getKey$okhttp() {
            return this.key;
        }

        public final long[] getLengths$okhttp() {
            return this.lengths;
        }

        public final int getLockingSourceCount$okhttp() {
            return this.lockingSourceCount;
        }

        public final boolean getReadable$okhttp() {
            return this.readable;
        }

        public final long getSequenceNumber$okhttp() {
            return this.sequenceNumber;
        }

        public final boolean getZombie$okhttp() {
            return this.zombie;
        }

        public final void setCurrentEditor$okhttp(Editor r1) {
            this.currentEditor = r1;
        }

        public final void setLengths$okhttp(List<String> r6) {
            if (r6.size() == this.this$0.getValueCount$okhttp()) goto L13;
            invalidLengths(r6);
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return;
        L13:
            int r0 = r6.size();     // Catch: NumberFormatException -> L9
            int r1 = 0;
        L5:
            if (r1 >= r0) goto L8;
            this.lengths[r1] = Long.parseLong(r6.get(r1));     // Catch: NumberFormatException -> L9
            r1 = r1 + 1;
            goto L5
        L8:
            return;
        L9:
            invalidLengths(r6);
            InstantKt$$ExternalSyntheticBUOutline0.m();
        }

        public final void setLockingSourceCount$okhttp(int r1) {
            this.lockingSourceCount = r1;
        }

        public final void setReadable$okhttp(boolean r1) {
            this.readable = r1;
        }

        public final void setSequenceNumber$okhttp(long r1) {
            this.sequenceNumber = r1;
        }

        public final void setZombie$okhttp(boolean r1) {
            this.zombie = r1;
        }

        public final Snapshot snapshot$okhttp() {
            DiskLruCache r0 = this.this$0;
            if (_UtilJvmKt.assertionsEnabled == false) goto L10;
            if (Thread.holdsLock(r0) == true) goto L10;
            _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", r0);
            return null;
        L10:
            if (this.readable == true) goto L13;
            return null;
        L13:
            if (DiskLruCache.access$getCivilizedFileSystem$p(this.this$0) == false) goto L15;
        L19:
            ArrayList r7 = new ArrayList();
            long[] r8 = (long[]) this.lengths.clone();
            int r02 = 0;
            int r2 = this.this$0.getValueCount$okhttp();     // Catch: FileNotFoundException -> L25
            int r3 = 0;
        L21:
            if (r3 >= r2) goto L23;
            r7.add(newSource(r3));     // Catch: FileNotFoundException -> L25
            r3 = r3 + 1;     // Catch: FileNotFoundException -> L25
            goto L21
        L23:
            return new Snapshot(this.this$0, this.key, this.sequenceNumber, r7, r8);
        L25:
            int r22 = r7.size();
        L26:
            if (r02 >= r22) goto L33;
            Object r32 = r7.get(r02);
            r02 = r02 + 1;
            _UtilCommonKt.closeQuietly((Source) r32);
            goto L26
        L33:
            this.this$0.removeEntry$okhttp(this);     // Catch: IOException -> L30
        L29:
            return null;
        L15:
            if (this.currentEditor == null) goto L17;
        L18:
            return null;
        L17:
            if (this.zombie == false) goto L19;
            goto L18
        }

        public final void writeLengths$okhttp(BufferedSink r6) {
            long[] r5 = this.lengths;
            int r0 = r5.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            long r2 = r5[r1];
            r6.writeByte(32).writeDecimalLong(r2);
            r1 = r1 + 1;
            goto L3
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u0002\u001a\u00020\u0003J\f\u0010\r\u001a\b\u0018\u00010\u000eR\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "sequenceNumber", _UrlKt.FRAGMENT_ENCODE_SET, "sources", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/Source;", "lengths", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;JLjava/util/List;[J)V", "edit", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getSource", "index", _UrlKt.FRAGMENT_ENCODE_SET, "getLength", "close", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final List<Source> sources;
        final /* synthetic */ DiskLruCache this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public Snapshot(DiskLruCache r1, String r2, long r3, List<? extends Source> r5, long[] r6) {
            this.this$0 = r1;
            this.key = r2;
            this.sequenceNumber = r3;
            this.sources = r5;
            this.lengths = r6;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Iterator<Source> r1 = this.sources.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            _UtilCommonKt.closeQuietly(r1.next());
            goto L4
        }

        public final Editor edit() {
            return this.this$0.edit(this.key, this.sequenceNumber);
        }

        public final long getLength(int r3) {
            return this.lengths[r3];
        }

        public final Source getSource(int r1) {
            return this.sources.get(r1);
        }

        public final String key() {
            return this.key;
        }
    }

    @Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0010)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\r\u0010\f\u001a\u00060\u0002R\u00020\u0003H\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016R.\u0010\u0004\u001a\"\u0012\u001e\u0012\u001c \u0007*\r\u0018\u00010\u0005R\u00020\u0003¢\u0006\u0002\b\u00060\u0005R\u00020\u0003¢\u0006\u0002\b\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0018\u00010\u0002R\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0018\u00010\u0002R\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"okhttp3/internal/cache/DiskLruCache$snapshots$1", _UrlKt.FRAGMENT_ENCODE_SET, "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Lokhttp3/internal/cache/DiskLruCache;", "delegate", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lkotlin/jvm/internal/EnhancedNullability;", "kotlin.jvm.PlatformType", "nextSnapshot", "removeSnapshot", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "remove", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nDiskLruCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DiskLruCache.kt\nokhttp3/internal/cache/DiskLruCache$snapshots$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1120:1\n1#2:1121\n*E\n"})
    /* renamed from: okhttp3.internal.cache.DiskLruCache$snapshots$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Iterator<Snapshot>, KMutableIterator {
        private final Iterator<Entry> delegate;
        private Snapshot nextSnapshot;
        private Snapshot removeSnapshot;
        final /* synthetic */ DiskLruCache this$0;

        public AnonymousClass1(DiskLruCache r2) {
            this.this$0 = r2;
            this.delegate = new ArrayList(r2.getLruEntries$okhttp().values()).iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.nextSnapshot == null) goto L5;
            return true;
        L5:
            DiskLruCache r0 = this.this$0;
            monitor-enter(r0);
        L22:
            th = move-exception;
            throw th;
        L9:
            if (r0.getClosed$okhttp() == false) goto L13;
            monitor-exit(r0);
            return false;
        L13:
            if (this.delegate.hasNext() == false) goto L24;
            Entry r2 = this.delegate.next();     // Catch: Throwable -> L22
            if (r2 == null) goto L13;
            Snapshot r22 = r2.snapshot$okhttp();     // Catch: Throwable -> L22
            if (r22 == null) goto L13;
            this.nextSnapshot = r22;     // Catch: Throwable -> L22
            monitor-exit(r0);
            return true;
        L24:
            Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L22
            monitor-exit(r0);
            return false;
        }

        @Override // java.util.Iterator
        public /* bridge */ /* synthetic */ Snapshot next() {
            return next2();
        }

        @Override // java.util.Iterator
        /* renamed from: next, reason: avoid collision after fix types in other method */
        public Snapshot next2() {
            if (hasNext() == false) goto L6;
            Snapshot r0 = this.nextSnapshot;
            this.removeSnapshot = r0;
            this.nextSnapshot = null;
            return r0;
        L6:
            Utils$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            Snapshot r0 = this.removeSnapshot;
            if (r0 != null) goto L15;
            Segment$$ExternalSyntheticBUOutline1.m("remove() before next()");
            return;
        L15:
            this.this$0.remove(r0.key());     // Catch: Throwable -> L8 IOException -> L11
            this.removeSnapshot = null;
            return;
        L11:
            this.removeSnapshot = null;
            return;
        L8:
            th = move-exception;
            this.removeSnapshot = null;
            throw th;
        }
    }

    public static Unit $r8$lambda$VuSpHTv0MA2f3ewEQIlkNS7IWTk(DiskLruCache r1, IOException r2) {
        if (_UtilJvmKt.assertionsEnabled == true) goto L5;
    L9:
        r1.hasJournalErrors = true;
        return Unit.INSTANCE;
    L5:
        if (Thread.holdsLock(r1) == true) goto L9;
        _UtilJvmKt$$ExternalSyntheticBUOutline1.m(Thread.currentThread().getName(), " MUST hold lock on ", r1);
        return null;
    }

    static {
        Companion = new Companion(null);
        JOURNAL_FILE = "journal";
        JOURNAL_FILE_TEMP = "journal.tmp";
        JOURNAL_FILE_BACKUP = "journal.bkp";
        MAGIC = "libcore.io.DiskLruCache";
        VERSION_1 = "1";
        ANY_SEQUENCE_NUMBER = -1;
        LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");
        CLEAN = "CLEAN";
        DIRTY = "DIRTY";
        REMOVE = "REMOVE";
        READ = "READ";
    }

    public DiskLruCache(FileSystem r3, Path r4, int r5, int r6, long r7, TaskRunner r9) {
        this.directory = r4;
        this.appVersion = r5;
        this.valueCount = r6;
        this.fileSystem = new DiskLruCache$fileSystem$1(r3);
        this.maxSize = r7;
        this.lruEntries = new LinkedHashMap(0, 0.75f, true);
        this.cleanupQueue = r9.newQueue();
        this.cleanupTask = new DiskLruCache$cleanupTask$1(this, _UtilJvmKt.okHttpName + " Cache");
        if (r7 <= 0) goto L9;
        if (r6 <= 0) goto L7;
        this.journalFile = r4.resolve(JOURNAL_FILE);
        this.journalFileTmp = r4.resolve(JOURNAL_FILE_TEMP);
        this.journalFileBackup = r4.resolve(JOURNAL_FILE_BACKUP);
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("valueCount <= 0");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("maxSize <= 0");
        throw null;
    }

    public static final /* synthetic */ boolean access$getCivilizedFileSystem$p(DiskLruCache r0) {
        return r0.civilizedFileSystem;
    }

    public static final /* synthetic */ boolean access$getInitialized$p(DiskLruCache r0) {
        return r0.initialized;
    }

    public static final /* synthetic */ BufferedSink access$getJournalWriter$p(DiskLruCache r0) {
        return r0.journalWriter;
    }

    public static final /* synthetic */ boolean access$journalRebuildRequired(DiskLruCache r0) {
        return r0.journalRebuildRequired();
    }

    public static final /* synthetic */ void access$setJournalWriter$p(DiskLruCache r0, BufferedSink r1) {
        r0.journalWriter = r1;
    }

    public static final /* synthetic */ void access$setMostRecentRebuildFailed$p(DiskLruCache r0, boolean r1) {
        r0.mostRecentRebuildFailed = r1;
    }

    public static final /* synthetic */ void access$setMostRecentTrimFailed$p(DiskLruCache r0, boolean r1) {
        r0.mostRecentTrimFailed = r1;
    }

    public static final /* synthetic */ void access$setRedundantOpCount$p(DiskLruCache r0, int r1) {
        r0.redundantOpCount = r1;
    }

    private final synchronized void checkNotClosed() {
        monitor-enter(this);
    L9:
        th = move-exception;
        throw th;
    L4:
        if (this.closed == true) goto L8;
        monitor-exit(this);
        return;
    L8:
        throw new IllegalStateException("cache is closed");     // Catch: Throwable -> L9
    }

    public static /* synthetic */ Editor edit$default(DiskLruCache r0, String r1, long r2, int r4, Object r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = ANY_SEQUENCE_NUMBER;
    L6:
        return r0.edit(r1, r2);
    }

    private final boolean journalRebuildRequired() {
        int r0 = this.redundantOpCount;
        if (r0 >= 2000) goto L5;
        return false;
    L5:
        if (r0 < this.lruEntries.size()) goto L10;
        return true;
    L10:
        return false;
    }

    private final BufferedSink newJournalWriter() {
        return Okio.buffer(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile), new DiskLruCache$$ExternalSyntheticLambda0(this)));
    }

    private final void processJournal() {
        _UtilCommonKt.deleteIfExists(this.fileSystem, this.journalFileTmp);
        Iterator<Entry> r0 = this.lruEntries.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L14;
        Entry r1 = r0.next();
        int r3 = 0;
        if (r1.getCurrentEditor$okhttp() == null) goto L7;
        r1.setCurrentEditor$okhttp(null);
        int r2 = this.valueCount;
    L11:
        if (r3 >= r2) goto L13;
        _UtilCommonKt.deleteIfExists(this.fileSystem, r1.getCleanFiles$okhttp().get(r3));
        _UtilCommonKt.deleteIfExists(this.fileSystem, r1.getDirtyFiles$okhttp().get(r3));
        r3 = r3 + 1;
        goto L11
    L13:
        r0.remove();
        goto L4
    L7:
        int r22 = this.valueCount;
    L8:
        if (r3 >= r22) goto L4;
        this.size += r1.getLengths$okhttp()[r3];
        r3 = r3 + 1;
        goto L8
    }

    private final void readJournal() {
        BufferedSource r2 = Okio.buffer(this.fileSystem.source(this.journalFile));
        String r3 = r2.readUtf8LineStrict();     // Catch: Throwable -> L16
        String r4 = r2.readUtf8LineStrict();     // Catch: Throwable -> L16
        String r5 = r2.readUtf8LineStrict();     // Catch: Throwable -> L16
        String r6 = r2.readUtf8LineStrict();     // Catch: Throwable -> L16
        String r7 = r2.readUtf8LineStrict();     // Catch: Throwable -> L16
        if (Intrinsics.areEqual(MAGIC, r3) == false) goto L33;
        if (Intrinsics.areEqual(VERSION_1, r4) == false) goto L33;
        if (Intrinsics.areEqual(String.valueOf(this.appVersion), r5) == false) goto L33;
        if (Intrinsics.areEqual(String.valueOf(this.valueCount), r6) == false) goto L33;
        if (r7.length() > 0) goto L33;
        int r0 = 0;
    L42:
        readJournalLine(r2.readUtf8LineStrict());     // Catch: Throwable -> L16 EOFException -> L18
        r0 = r0 + 1;
        goto L42
    L18:
        this.redundantOpCount = r0 - this.lruEntries.size();     // Catch: Throwable -> L16
        if (r2.exhausted() == true) goto L21;
        rebuildJournal$okhttp();     // Catch: Throwable -> L16
    L25:
        Unit r9 = Unit.INSTANCE;     // Catch: Throwable -> L16
        if (r2 != null) goto L44;
    L31:
        th = null;
    L39:
        if (th != null) goto L41;
        return;
    L41:
        throw th;
    L44:
        r2.close();     // Catch: Throwable -> L29
    L29:
        th = th;
        goto L39
    L21:
        BufferedSink r02 = this.journalWriter;     // Catch: Throwable -> L16
        if (r02 == null) goto L24;
        _UtilCommonKt.closeQuietly(r02);     // Catch: Throwable -> L16
    L24:
        this.journalWriter = newJournalWriter();     // Catch: Throwable -> L16
    L33:
        throw new IOException("unexpected journal header: [" + r3 + ", " + r4 + ", " + r6 + ", " + r7 + ']');     // Catch: Throwable -> L16
    L16:
        th = th;
        if (r2 == null) goto L39;
        r2.close();     // Catch: Throwable -> L37
    L37:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);
        goto L39
    }

    private final void readJournalLine(String r18) {
        int r7 = StringsKt.indexOf$default(r18, ' ', 0, false, 6, null);
        if (r7 == (-1)) goto L38;
        int r3 = r7 + 1;
        int r2 = StringsKt.indexOf$default(r18, ' ', r3, false, 4, null);
        if (r2 != (-1)) goto L12;
        String r32 = r18.substring(r3);
        String r10 = REMOVE;
        if (r7 == r10.length()) goto L9;
    L13:
        Entry r102 = this.lruEntries.get(r32);
        if (r102 != null) goto L16;
        r102 = new Entry(this, r32);
        this.lruEntries.put(r32, r102);
    L16:
        if (r2 == (-1)) goto L23;
        String r33 = CLEAN;
        if (r7 != r33.length()) goto L23;
        if (StringsKt.startsWith$default(r18, r33, false, 2, null) == false) goto L23;
        List<String> r1 = StringsKt.split$default(r18.substring(r2 + 1), new char[]{' '}, false, 0, 6, null);
        r102.setReadable$okhttp(true);
        r102.setCurrentEditor$okhttp(null);
        r102.setLengths$okhttp(r1);
        return;
    L23:
        if (r2 != (-1)) goto L30;
        String r34 = DIRTY;
        if (r7 != r34.length()) goto L30;
        if (StringsKt.startsWith$default(r18, r34, false, 2, null) == false) goto L30;
        r102.setCurrentEditor$okhttp(new Editor(this, r102));
        return;
    L30:
        if (r2 != (-1)) goto L36;
        String r0 = READ;
        if (r7 != r0.length()) goto L36;
        if (StringsKt.startsWith$default(r18, r0, false, 2, null) == false) goto L36;
        return;
    L36:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("unexpected journal line: ", r18);
        return;
    L9:
        if (StringsKt.startsWith$default(r18, r10, false, 2, null) == false) goto L13;
        this.lruEntries.remove(r32);
        return;
    L12:
        r32 = r18.substring(r3, r2);
        goto L13
    L38:
        ZipFileSystem$$ExternalSyntheticBUOutline0.m("unexpected journal line: ", r18);
    }

    private final boolean removeOldestEntry() {
        Iterator<Entry> r0 = this.lruEntries.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        Entry r1 = r0.next();
        if (r1.getZombie$okhttp() == true) goto L4;
        removeEntry$okhttp(r1);
        return true;
    L9:
        return false;
    }

    private final void validateKey(String r2) {
        if (LEGAL_KEY_PATTERN.matches(r2) == false) goto L5;
        return;
    L5:
        HttpUrl$Builder$$ExternalSyntheticBUOutline0.m("keys must match regex [a-z0-9_-]{1,120}: \"", r2, 34);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        monitor-enter(this);
    L15:
        th = move-exception;
        throw th;
    L4:
        if (this.initialized == true) goto L6;
    L24:
        this.closed = true;     // Catch: Throwable -> L15
        monitor-exit(this);
        return;
    L6:
        if (this.closed == true) goto L24;
        int r2 = 0;
        Entry[] r0 = (Entry[]) this.lruEntries.values().toArray(new Entry[0]);     // Catch: Throwable -> L15
        int r3 = r0.length;     // Catch: Throwable -> L15
    L9:
        if (r2 >= r3) goto L18;
        Entry r4 = r0[r2];     // Catch: Throwable -> L15
        if (r4.getCurrentEditor$okhttp() == null) goto L17;
        Editor r42 = r4.getCurrentEditor$okhttp();     // Catch: Throwable -> L15
        if (r42 == null) goto L17;
        r42.detach$okhttp();     // Catch: Throwable -> L15
    L17:
        r2 = r2 + 1;     // Catch: Throwable -> L15
        goto L9
    L18:
        trimToSize();     // Catch: Throwable -> L15
        BufferedSink r02 = this.journalWriter;     // Catch: Throwable -> L15
        if (r02 == null) goto L21;
        _UtilCommonKt.closeQuietly(r02);     // Catch: Throwable -> L15
    L21:
        this.journalWriter = null;     // Catch: Throwable -> L15
        this.closed = true;     // Catch: Throwable -> L15
        monitor-exit(this);
    }

    public final synchronized void completeEdit$okhttp(Editor r9, boolean r10) {
        monitor-enter(this);
        Entry r0 = r9.getEntry$okhttp();     // Catch: Throwable -> L18
        if (Intrinsics.areEqual(r0.getCurrentEditor$okhttp(), r9) == false) goto L59;
        int r1 = 0;
        if (r10 == true) goto L8;
    L23:
        int r92 = this.valueCount;     // Catch: Throwable -> L18
    L24:
        if (r1 >= r92) goto L38;
        Path r2 = r0.getDirtyFiles$okhttp().get(r1);     // Catch: Throwable -> L18
        if (r10 == true) goto L28;
    L36:
        _UtilCommonKt.deleteIfExists(this.fileSystem, r2);     // Catch: Throwable -> L18
    L37:
        r1 = r1 + 1;     // Catch: Throwable -> L18
        goto L24
    L28:
        if (r0.getZombie$okhttp() == true) goto L36;
        if (this.fileSystem.exists(r2) == false) goto L37;
        Path r3 = r0.getCleanFiles$okhttp().get(r1);     // Catch: Throwable -> L18
        this.fileSystem.atomicMove(r2, r3);     // Catch: Throwable -> L18
        long r4 = r0.getLengths$okhttp()[r1];     // Catch: Throwable -> L18
        Long r22 = this.fileSystem.metadata(r3).getSize();     // Catch: Throwable -> L18
        if (r22 == null) goto L34;
        long r23 = r22.longValue();     // Catch: Throwable -> L18
    L35:
        r0.getLengths$okhttp()[r1] = r23;     // Catch: Throwable -> L18
        this.size = (this.size - r4) + r23;     // Catch: Throwable -> L18
        goto L37
    L34:
        r23 = 0;
        goto L35
    L38:
        r0.setCurrentEditor$okhttp(null);     // Catch: Throwable -> L18
        if (r0.getZombie$okhttp() == false) goto L43;
        removeEntry$okhttp(r0);     // Catch: Throwable -> L18
        monitor-exit(this);
        return;
    L43:
        this.redundantOpCount++;
        BufferedSink r93 = this.journalWriter;     // Catch: Throwable -> L18
        if (r0.getReadable$okhttp() == true) goto L48;
        if (r10 == true) goto L48;
        this.lruEntries.remove(r0.getKey$okhttp());     // Catch: Throwable -> L18
        r93.writeUtf8(REMOVE).writeByte(32);     // Catch: Throwable -> L18
        r93.writeUtf8(r0.getKey$okhttp());     // Catch: Throwable -> L18
        r93.writeByte(10);     // Catch: Throwable -> L18
    L51:
        r93.flush();     // Catch: Throwable -> L18
        if (this.size <= this.maxSize) goto L54;
    L55:
        TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0, 2, null);     // Catch: Throwable -> L18
    L56:
        monitor-exit(this);
        return;
    L54:
        if (journalRebuildRequired() == false) goto L56;
    L48:
        r0.setReadable$okhttp(true);     // Catch: Throwable -> L18
        r93.writeUtf8(CLEAN).writeByte(32);     // Catch: Throwable -> L18
        r93.writeUtf8(r0.getKey$okhttp());     // Catch: Throwable -> L18
        r0.writeLengths$okhttp(r93);     // Catch: Throwable -> L18
        r93.writeByte(10);     // Catch: Throwable -> L18
        if (r10 == false) goto L51;
        long r12 = this.nextSequenceNumber;     // Catch: Throwable -> L18
        this.nextSequenceNumber = 1 + r12;     // Catch: Throwable -> L18
        r0.setSequenceNumber$okhttp(r12);     // Catch: Throwable -> L18
        goto L51
    L8:
        if (r0.getReadable$okhttp() == true) goto L23;
        int r24 = this.valueCount;     // Catch: Throwable -> L18
        int r32 = 0;
    L10:
        if (r32 >= r24) goto L23;
        if (r9.getWritten$okhttp()[r32] == false) goto L21;
        if (this.fileSystem.exists(r0.getDirtyFiles$okhttp().get(r32)) == false) goto L15;
        r32 = r32 + 1;
        goto L10
    L15:
        r9.abort();     // Catch: Throwable -> L18
        monitor-exit(this);
        return;
    L21:
        r9.abort();     // Catch: Throwable -> L18
        throw new IllegalStateException("Newly created entry didn't create value for index " + r32);     // Catch: Throwable -> L18
    L59:
        throw new IllegalStateException("Check failed.");     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        throw th;
    }

    public final void delete() {
        close();
        _UtilCommonKt.deleteContents(this.fileSystem, this.directory);
    }

    @JvmOverloads
    public final Editor edit(String r7) {
        return edit$default(this, r7, 0, 2, null);
    }

    @JvmOverloads
    public final synchronized Editor edit(String r10, long r11) {
        monitor-enter(this);
        initialize();     // Catch: Throwable -> L10
        checkNotClosed();     // Catch: Throwable -> L10
        validateKey(r10);     // Catch: Throwable -> L10
        Entry r0 = this.lruEntries.get(r10);     // Catch: Throwable -> L10
        if (r11 == ANY_SEQUENCE_NUMBER) goto L14;
        if (r0 != null) goto L8;
    L12:
        monitor-exit(this);
        return null;
    L8:
        if (r0.getSequenceNumber$okhttp() != r11) goto L12;
    L14:
        if (r0 == null) goto L17;
        Editor r112 = r0.getCurrentEditor$okhttp();     // Catch: Throwable -> L10
    L18:
        if (r112 == null) goto L21;
        monitor-exit(this);
        return null;
    L21:
        if (r0 == null) goto L27;
        if (r0.getLockingSourceCount$okhttp() == 0) goto L27;
        monitor-exit(this);
        return null;
    L27:
        if (this.mostRecentTrimFailed == false) goto L29;
    L40:
        TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0, 2, null);     // Catch: Throwable -> L10
        monitor-exit(this);
        return null;
    L29:
        if (this.mostRecentRebuildFailed == true) goto L40;
        BufferedSink r113 = this.journalWriter;     // Catch: Throwable -> L10
        r113.writeUtf8(DIRTY).writeByte(32).writeUtf8(r10).writeByte(10);     // Catch: Throwable -> L10
        r113.flush();     // Catch: Throwable -> L10
        if (this.hasJournalErrors == false) goto L35;
        monitor-exit(this);
        return null;
    L35:
        if (r0 != null) goto L37;
        r0 = new Entry(this, r10);     // Catch: Throwable -> L10
        this.lruEntries.put(r10, r0);     // Catch: Throwable -> L10
    L37:
        Editor r102 = new Editor(this, r0);     // Catch: Throwable -> L10
        r0.setCurrentEditor$okhttp(r102);     // Catch: Throwable -> L10
        monitor-exit(this);
        return r102;
    L17:
        r112 = null;
    L10:
        th = move-exception;
        throw th;
    }

    public final synchronized void evictAll() {
        monitor-enter(this);
        initialize();     // Catch: Throwable -> L6
        Entry[] r0 = (Entry[]) this.lruEntries.values().toArray(new Entry[0]);     // Catch: Throwable -> L6
        int r2 = r0.length;     // Catch: Throwable -> L6
        int r3 = 0;
    L4:
        if (r3 >= r2) goto L8;
        removeEntry$okhttp(r0[r3]);     // Catch: Throwable -> L6
        r3 = r3 + 1;     // Catch: Throwable -> L6
        goto L4
    L8:
        this.mostRecentTrimFailed = false;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // java.io.Flushable
    public synchronized void flush() {
        monitor-enter(this);
    L10:
        th = move-exception;
        throw th;
    L4:
        if (this.initialized == true) goto L7;
        monitor-exit(this);
        return;
    L7:
        checkNotClosed();     // Catch: Throwable -> L10
        trimToSize();     // Catch: Throwable -> L10
        this.journalWriter.flush();     // Catch: Throwable -> L10
        monitor-exit(this);
    }

    public final synchronized Snapshot get(String r8) {
        monitor-enter(this);
        initialize();     // Catch: Throwable -> L16
        checkNotClosed();     // Catch: Throwable -> L16
        validateKey(r8);     // Catch: Throwable -> L16
        Entry r0 = this.lruEntries.get(r8);     // Catch: Throwable -> L16
        if (r0 != null) goto L8;
        monitor-exit(this);
        return null;
    L8:
        Snapshot r02 = r0.snapshot$okhttp();     // Catch: Throwable -> L16
        if (r02 != null) goto L12;
        monitor-exit(this);
        return null;
    L12:
        this.redundantOpCount++;
        this.journalWriter.writeUtf8(READ).writeByte(32).writeUtf8(r8).writeByte(10);     // Catch: Throwable -> L16
        if (journalRebuildRequired() == false) goto L18;
        TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0, 2, null);     // Catch: Throwable -> L16
    L18:
        monitor-exit(this);
        return r02;
    L16:
        th = move-exception;
        throw th;
    }

    public final boolean getClosed$okhttp() {
        return this.closed;
    }

    public final Path getDirectory() {
        return this.directory;
    }

    public final FileSystem getFileSystem$okhttp() {
        return this.fileSystem;
    }

    public final LinkedHashMap<String, Entry> getLruEntries$okhttp() {
        return this.lruEntries;
    }

    public final synchronized long getMaxSize() {
        monitor-enter(this);
        long r0 = this.maxSize;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final int getValueCount$okhttp() {
        return this.valueCount;
    }

    public final synchronized void initialize() {
        monitor-enter(this);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (_UtilJvmKt.assertionsEnabled == false) goto L14;
        if (Thread.holdsLock(this) == true) goto L14;
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);     // Catch: Throwable -> L11
    L14:
        if (this.initialized == false) goto L18;
        monitor-exit(this);
        return;
    L18:
        if (this.fileSystem.exists(this.journalFileBackup) == false) goto L24;
        boolean r1 = this.fileSystem.exists(this.journalFile);     // Catch: Throwable -> L11
        FileSystem r2 = this.fileSystem;
        if (r1 == false) goto L23;
        r2.delete(this.journalFileBackup);     // Catch: Throwable -> L11
        goto L24
    L23:
        r2.atomicMove(this.journalFileBackup, this.journalFile);     // Catch: Throwable -> L11
    L24:
        this.civilizedFileSystem = _UtilCommonKt.isCivilized(this.fileSystem, this.journalFileBackup);     // Catch: Throwable -> L11
        if (this.fileSystem.exists(this.journalFile) == true) goto L44;
    L38:
        rebuildJournal$okhttp();     // Catch: Throwable -> L11
        this.initialized = true;     // Catch: Throwable -> L11
        monitor-exit(this);
        return;
    L44:
        readJournal();     // Catch: Throwable -> L11 IOException -> L30
        processJournal();     // Catch: Throwable -> L11 IOException -> L30
        this.initialized = true;     // Catch: Throwable -> L11 IOException -> L30
        monitor-exit(this);
        return;
    L30:
        e = move-exception;
        Platform.Companion.get().log("DiskLruCache " + this.directory + " is corrupt: " + e.getMessage() + ", removing", 5, e);     // Catch: Throwable -> L11
        delete();     // Catch: Throwable -> L35
        this.closed = false;     // Catch: Throwable -> L11
        goto L38
    L35:
        th = move-exception;
        this.closed = false;     // Catch: Throwable -> L11
        throw th;     // Catch: Throwable -> L11
    }

    public final synchronized boolean isClosed() {
        monitor-enter(this);
        boolean r0 = this.closed;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized void rebuildJournal$okhttp() {
        monitor-enter(this);
        BufferedSink r0 = this.journalWriter;     // Catch: Throwable -> L6
        if (r0 == null) goto L8;
        r0.close();     // Catch: Throwable -> L6
    L8:
        BufferedSink r02 = Okio.buffer(this.fileSystem.sink(this.journalFileTmp, false));     // Catch: Throwable -> L6
        r02.writeUtf8(MAGIC).writeByte(10);     // Catch: Throwable -> L15
        r02.writeUtf8(VERSION_1).writeByte(10);     // Catch: Throwable -> L15
        r02.writeDecimalLong(this.appVersion).writeByte(10);     // Catch: Throwable -> L15
        r02.writeDecimalLong(this.valueCount).writeByte(10);     // Catch: Throwable -> L15
        r02.writeByte(10);     // Catch: Throwable -> L15
        Iterator<Entry> r1 = this.lruEntries.values().iterator();     // Catch: Throwable -> L15
    L11:
        if (r1.hasNext() == false) goto L18;
        Entry r4 = r1.next();     // Catch: Throwable -> L15
        if (r4.getCurrentEditor$okhttp() != null) goto L14;
        r02.writeUtf8(CLEAN).writeByte(32);     // Catch: Throwable -> L15
        r02.writeUtf8(r4.getKey$okhttp());     // Catch: Throwable -> L15
        r4.writeLengths$okhttp(r02);     // Catch: Throwable -> L15
        r02.writeByte(10);     // Catch: Throwable -> L15
        goto L11
    L14:
        r02.writeUtf8(DIRTY).writeByte(32);     // Catch: Throwable -> L15
        r02.writeUtf8(r4.getKey$okhttp());     // Catch: Throwable -> L15
        r02.writeByte(10);     // Catch: Throwable -> L15
        goto L11
    L18:
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L15
        if (r02 != null) goto L50;
    L24:
        Throwable th = null;
    L31:
        if (th != null) goto L43;
        boolean r03 = this.fileSystem.exists(this.journalFile);     // Catch: Throwable -> L6
        FileSystem r13 = this.fileSystem;
        if (r03 == false) goto L36;
        r13.atomicMove(this.journalFile, this.journalFileBackup);     // Catch: Throwable -> L6
        this.fileSystem.atomicMove(this.journalFileTmp, this.journalFile);     // Catch: Throwable -> L6
        _UtilCommonKt.deleteIfExists(this.fileSystem, this.journalFileBackup);     // Catch: Throwable -> L6
    L37:
        BufferedSink r04 = this.journalWriter;     // Catch: Throwable -> L6
        if (r04 == null) goto L40;
        _UtilCommonKt.closeQuietly(r04);     // Catch: Throwable -> L6
    L40:
        this.journalWriter = newJournalWriter();     // Catch: Throwable -> L6
        this.hasJournalErrors = false;     // Catch: Throwable -> L6
        this.mostRecentRebuildFailed = false;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L36:
        r13.atomicMove(this.journalFileTmp, this.journalFile);     // Catch: Throwable -> L6
        goto L37
    L43:
        throw th;     // Catch: Throwable -> L6
    L50:
        r02.close();     // Catch: Throwable -> L22
    L22:
        th = th;
        goto L31
    L15:
        th = move-exception;
        if (r02 != null) goto L46;
    L30:
        th = th;
        goto L31
    L46:
        r02.close();     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        ExceptionsKt.addSuppressed(th, th);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized boolean remove(String r6) {
        monitor-enter(this);
        initialize();     // Catch: Throwable -> L14
        checkNotClosed();     // Catch: Throwable -> L14
        validateKey(r6);     // Catch: Throwable -> L14
        Entry r62 = this.lruEntries.get(r6);     // Catch: Throwable -> L14
        if (r62 != null) goto L8;
        monitor-exit(this);
        return false;
    L8:
        boolean r63 = removeEntry$okhttp(r62);     // Catch: Throwable -> L14
        if (r63 == true) goto L11;
    L16:
        monitor-exit(this);
        return r63;
    L11:
        if (this.size > this.maxSize) goto L16;
        this.mostRecentTrimFailed = false;     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;
    }

    public final boolean removeEntry$okhttp(Entry r11) {
        if (this.civilizedFileSystem == false) goto L5;
    L15:
        Editor r0 = r11.getCurrentEditor$okhttp();
        if (r0 == null) goto L18;
        r0.detach$okhttp();
    L18:
        int r02 = this.valueCount;
        int r4 = 0;
    L19:
        if (r4 >= r02) goto L21;
        _UtilCommonKt.deleteIfExists(this.fileSystem, r11.getCleanFiles$okhttp().get(r4));
        this.size -= r11.getLengths$okhttp()[r4];
        r11.getLengths$okhttp()[r4] = 0;
        r4 = r4 + 1;
        goto L19
    L21:
        this.redundantOpCount++;
        BufferedSink r03 = this.journalWriter;
        if (r03 == null) goto L24;
        r03.writeUtf8(REMOVE);
        r03.writeByte(32);
        r03.writeUtf8(r11.getKey$okhttp());
        r03.writeByte(10);
    L24:
        this.lruEntries.remove(r11.getKey$okhttp());
        if (journalRebuildRequired() == false) goto L27;
        TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0, 2, null);
    L27:
        return true;
    L5:
        if (r11.getLockingSourceCount$okhttp() <= 0) goto L10;
        BufferedSink r04 = this.journalWriter;
        if (r04 == null) goto L10;
        r04.writeUtf8(DIRTY);
        r04.writeByte(32);
        r04.writeUtf8(r11.getKey$okhttp());
        r04.writeByte(10);
        r04.flush();
    L10:
        if (r11.getLockingSourceCount$okhttp() <= 0) goto L12;
    L13:
        r11.setZombie$okhttp(true);
        return true;
    L12:
        if (r11.getCurrentEditor$okhttp() == null) goto L15;
        goto L13
    }

    public final void setClosed$okhttp(boolean r1) {
        this.closed = r1;
    }

    public final synchronized void setMaxSize(long r7) {
        monitor-enter(this);
        this.maxSize = r7;     // Catch: Throwable -> L7
        if (this.initialized == false) goto L9;
        TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0, 2, null);     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final synchronized long size() {
        monitor-enter(this);
        initialize();     // Catch: Throwable -> L6
        long r0 = this.size;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final synchronized Iterator<Snapshot> snapshots() {
        monitor-enter(this);
        initialize();     // Catch: Throwable -> L6
        AnonymousClass1 r0 = new AnonymousClass1(this);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public final void trimToSize() {
    L3:
        if (this.size <= this.maxSize) goto L7;
        if (removeOldestEntry() == true) goto L3;
        return;
    L7:
        this.mostRecentTrimFailed = false;
    }
}
