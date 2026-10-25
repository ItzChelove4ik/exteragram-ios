package com.exteragram.messenger.icons;

import android.graphics.Bitmap;
import android.system.Os;
import android.system.StructStat;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J/\u0010\r\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J/\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0015J5\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0013¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u001f¨\u0006 "}, d2 = {"Lcom/exteragram/messenger/icons/DecodedIconStore;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Ljava/io/File;", "directory", "()Ljava/io/File;", "source", _UrlKt.FRAGMENT_ENCODE_SET, "width", "height", "density", _UrlKt.FRAGMENT_ENCODE_SET, "entryName", "(Ljava/io/File;III)Ljava/lang/String;", "dir", _UrlKt.FRAGMENT_ENCODE_SET, "prune", "(Ljava/io/File;)V", "Landroid/graphics/Bitmap;", "get", "(Ljava/io/File;III)Landroid/graphics/Bitmap;", "bitmap", "put", "(Ljava/io/File;IIILandroid/graphics/Bitmap;)V", "Ljava/util/concurrent/atomic/AtomicLong;", "writtenSincePrune", "Ljava/util/concurrent/atomic/AtomicLong;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "pruning", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/io/File;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDecodedIconStore.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DecodedIconStore.kt\ncom/exteragram/messenger/icons/DecodedIconStore\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Bitmap.kt\nandroidx/core/graphics/BitmapKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,149:1\n1#2:150\n83#3,6:151\n6309#4,2:157\n*S KotlinDebug\n*F\n+ 1 DecodedIconStore.kt\ncom/exteragram/messenger/icons/DecodedIconStore\n*L\n82#1:151,6\n135#1:157,2\n*E\n"})
/* loaded from: classes4.dex */
public final class DecodedIconStore {
    public static final DecodedIconStore INSTANCE = null;
    private static volatile File directory;
    private static final AtomicBoolean pruning = null;
    private static final AtomicLong writtenSincePrune = null;

    public static void $r8$lambda$_B2fBz2Y6FxyvOHlxtFJxOHt_Jw(File r1) {
        INSTANCE.prune(r1);
    }

    static {
        INSTANCE = new DecodedIconStore();
        writtenSincePrune = new AtomicLong(0);
        pruning = new AtomicBoolean(false);
    }

    private DecodedIconStore() {
    }

    private final File directory() {
        File r3 = directory;
        if (r3 == null) goto L17;
        return r3;
    L17:
        File r0 = new File(ApplicationLoader.applicationContext.getCacheDir(), "icon_bitmaps");     // Catch: Exception -> L11
        if (r0.isDirectory() == false) goto L9;
    L13:
        directory = r0;     // Catch: Exception -> L11
        return r0;
    L9:
        if (r0.mkdirs() == true) goto L13;
        return null;
    L11:
        e = move-exception;
        FileLog.e("Failed to open the decoded icon cache", e);
        return null;
    }

    private final String entryName(File r10, int r11, int r12, int r13) {
        StructStat r9 = Os.stat(r10.getPath());     // Catch: Throwable -> L4
        long r0 = r9.st_mtime;     // Catch: Throwable -> L4
        long r2 = r9.st_size;     // Catch: Throwable -> L4
    L5:
        long r4 = 1125899906842597L;
        int r6 = 0;
    L7:
        if (r6 >= r10.getPath().length()) goto L10;
        r4 = r9.charAt(r6) + (31 * r4);
        r6 = r6 + 1;
        goto L7
    L10:
        return Long.toHexString((31 * ((((((((r4 * 31) + r0) * 31) + r2) * 31) + r11) * 31) + r12)) + r13);
    L4:
        r0 = r10.lastModified();
        r2 = r10.length();
        goto L5
    }

    private final void prune(File r8) {
        AtomicBoolean r7 = pruning;
        if (r7.compareAndSet(false, true) == true) goto L37;
        return;
    L37:
        File[] r82 = r8.listFiles();     // Catch: Throwable -> L13 Exception -> L15
        if (r82 != null) goto L9;
        r7.set(false);
        return;
    L9:
        int r72 = r82.length;     // Catch: Throwable -> L13 Exception -> L15
        long r2 = 0;
        int r4 = 0;
    L10:
        if (r4 >= r72) goto L18;
        r2 = r2 + r82[r4].length();
        r4 = r4 + 1;
        goto L10
    L18:
        if (r2 > 33554432) goto L22;
    L19:
        pruning.set(false);
        return;
    L22:
        if (r82.length <= 1) goto L24;
        ArraysKt.sortWith(r82, new DecodedIconStore$prune$$inlined$sortBy$1());     // Catch: Throwable -> L13 Exception -> L15
    L24:
        int r73 = r82.length;     // Catch: Throwable -> L13 Exception -> L15
        int r1 = 0;
    L25:
        if (r1 >= r73) goto L19;
        File r42 = r82[r1];     // Catch: Throwable -> L13 Exception -> L15
        if (r2 <= 25165824) goto L19;
        long r5 = r42.length();     // Catch: Throwable -> L13 Exception -> L15
        if (r42.delete() == false) goto L31;
        r2 = r2 - r5;
    L31:
        r1 = r1 + 1;
        goto L25
    L15:
        e = move-exception;
        FileLog.e("Failed to prune the decoded icon cache", e);     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        pruning.set(false);
        throw th;
    }

    public final Bitmap get(File r7, int r8, int r9, int r10) {
        File r0 = directory();
        if (r0 != null) goto L5;
        return null;
    L5:
        File r2 = new File(r0, entryName(r7, r8, r9, r10));
        long r6 = ((r8 * r9) * 4) + 16;
        RandomAccessFile r02 = new RandomAccessFile(r2, "r");     // Catch: Throwable -> L41 FileNotFoundException -> L46
        FileChannel r3 = r02.getChannel();     // Catch: Throwable -> L20
        if (r3.size() == r6) goto L13;
        CloseableKt.closeFinally(r02, null);     // Catch: Throwable -> L41 FileNotFoundException -> L46
        return null;
    L13:
        ByteBuffer r62 = ByteBuffer.allocateDirect((int) r6).order(ByteOrder.nativeOrder());     // Catch: Throwable -> L20
    L15:
        if (r62.hasRemaining() == false) goto L22;
        if (r3.read(r62) >= 0) goto L15;
        CloseableKt.closeFinally(r02, null);     // Catch: Throwable -> L41 FileNotFoundException -> L46
        return null;
    L22:
        r62.flip();     // Catch: Throwable -> L20
        if (r62.getInt() == 1163151665) goto L28;
        CloseableKt.closeFinally(r02, null);     // Catch: Throwable -> L41 FileNotFoundException -> L46
        return null;
    L28:
        if (r62.getInt() == r8) goto L30;
    L35:
        CloseableKt.closeFinally(r02, null);     // Catch: Throwable -> L41 FileNotFoundException -> L46
        return null;
    L30:
        if (r62.getInt() != r9) goto L35;
        r62.getInt();     // Catch: Throwable -> L20
        Bitmap r72 = Bitmap.createBitmap(r8, r9, Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L20
        r72.copyPixelsFromBuffer(r62);     // Catch: Throwable -> L20
        r72.setDensity(r10);     // Catch: Throwable -> L20
        CloseableKt.closeFinally(r02, null);     // Catch: Throwable -> L41 FileNotFoundException -> L46
        return r72;
    L20:
        th = move-exception;
        throw th;     // Catch: Throwable -> L38
    L38:
        th = move-exception;
        CloseableKt.closeFinally(r02, th);     // Catch: Throwable -> L41 FileNotFoundException -> L46
        throw th;     // Catch: Throwable -> L41 FileNotFoundException -> L46
    L41:
        Result.Companion r63 = Result.Companion;     // Catch: Throwable -> L43
        Result.m2018constructorimpl(Boolean.valueOf(r2.delete()));     // Catch: Throwable -> L43
    L43:
        th = move-exception;
        Result.Companion r73 = Result.Companion;
        Result.m2018constructorimpl(ResultKt.createFailure(th));
    L45:
        return null;
    }

    public final void put(File r5, int r6, int r7, int r8, Bitmap r9) {
        if (r9.getWidth() == r6) goto L5;
        return;
    L5:
        if (r9.getHeight() == r7) goto L8;
        return;
    L8:
        if (r9.getConfig() != Bitmap.Config.ARGB_8888) goto L56;
        File r0 = directory();
        if (r0 == null) goto L57;
        File r1 = new File(r0, entryName(r5, r6, r7, r8));
        if (r1.isFile() == true) goto L58;
        File r4 = new File(r0, r1.getName() + ".tmp");
        int r52 = ((r6 * r7) * 4) + 16;
        ByteBuffer r2 = ByteBuffer.allocateDirect(r52).order(ByteOrder.nativeOrder());     // Catch: Throwable -> L38
        r2.putInt(1163151665);     // Catch: Throwable -> L38
        r2.putInt(r6);     // Catch: Throwable -> L38
        r2.putInt(r7);     // Catch: Throwable -> L38
        r2.putInt(r8);     // Catch: Throwable -> L38
        r9.copyPixelsToBuffer(r2);     // Catch: Throwable -> L38
        r2.flip();     // Catch: Throwable -> L38
        RandomAccessFile r62 = new RandomAccessFile(r4, "rw");     // Catch: Throwable -> L38
        FileChannel r72 = r62.getChannel();     // Catch: Throwable -> L22
    L20:
        if (r2.hasRemaining() == false) goto L24;
        r72.write(r2);     // Catch: Throwable -> L22
        goto L20
    L24:
        Unit r73 = Unit.INSTANCE;     // Catch: Throwable -> L22
    L26:
        CloseableKt.closeFinally(r62, null);     // Catch: Throwable -> L38
        if (r4.renameTo(r1) == true) goto L30;
        r4.delete();     // Catch: Throwable -> L38
        return;
    L30:
        AtomicLong r63 = writtenSincePrune;     // Catch: Throwable -> L38
        if (r63.addAndGet(r52) < 4194304) goto L54;
        r63.set(0);     // Catch: Throwable -> L38
        Utilities.globalQueue.postRunnable(new DecodedIconStore$$ExternalSyntheticLambda0(r0));     // Catch: Throwable -> L38
        return;
    L54:
        return;
    L22:
        th = move-exception;
        throw th;     // Catch: Throwable -> L35
    L35:
        th = move-exception;
        CloseableKt.closeFinally(r62, th);     // Catch: Throwable -> L38
        throw th;     // Catch: Throwable -> L38
    L43:
        th = move-exception;
        Result.Companion r53 = Result.Companion;
        Result.m2018constructorimpl(ResultKt.createFailure(th));
        return;
    L38:
        Result.Companion r54 = Result.Companion;     // Catch: Throwable -> L43
        if (r4.exists() == false) goto L41;
        r4.delete();     // Catch: Throwable -> L43
    L41:
        Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L43
        return;
    L58:
        return;
    L57:
        return;
    }
}
