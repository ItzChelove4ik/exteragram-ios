package org.telegram.ui.iv;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public abstract class RichMediaClipboard {
    private static final HashMap<Long, TLRPC.Document> documents = null;
    private static final HashMap<Long, TLRPC.Photo> photos = null;

    static {
        photos = new HashMap();
        documents = new HashMap();
    }

    public static synchronized TLRPC.Document document(long r3) {
        monitor-enter(RichMediaClipboard.class);
        if (r3 != 0) goto L13;
        TLRPC.Document r32 = null;
    L8:
        monitor-exit(RichMediaClipboard.class);
        return r32;
    L13:
        r32 = documents.get(Long.valueOf(r3));     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    public static synchronized TLRPC.Photo photo(long r3) {
        monitor-enter(RichMediaClipboard.class);
        if (r3 != 0) goto L13;
        TLRPC.Photo r32 = null;
    L8:
        monitor-exit(RichMediaClipboard.class);
        return r32;
    L13:
        r32 = photos.get(Long.valueOf(r3));     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    public static synchronized void set(List<TLRPC.Photo> r5, List<TLRPC.Document> r6) {
        monitor-enter(RichMediaClipboard.class);
        photos.clear();     // Catch: Throwable -> L12
        documents.clear();     // Catch: Throwable -> L12
        if (r5 == null) goto L14;
        Iterator<TLRPC.Photo> r52 = r5.iterator();     // Catch: Throwable -> L12
    L8:
        if (r52.hasNext() == false) goto L14;
        TLRPC.Photo r1 = r52.next();     // Catch: Throwable -> L12
        if (r1 == null) goto L8;
        photos.put(Long.valueOf(r1.id), r1);     // Catch: Throwable -> L12
    L14:
        if (r6 == null) goto L22;
        Iterator<TLRPC.Document> r53 = r6.iterator();     // Catch: Throwable -> L12
    L16:
        if (r53.hasNext() == false) goto L22;
        TLRPC.Document r62 = r53.next();     // Catch: Throwable -> L12
        if (r62 == null) goto L16;
        documents.put(Long.valueOf(r62.id), r62);     // Catch: Throwable -> L12
    L22:
        monitor-exit(RichMediaClipboard.class);
        return;
    L12:
        th = move-exception;
        throw th;
    }
}
