package org.telegram.ui.web;

import android.util.LongSparseArray;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.web.WebMetadataCache;

/* loaded from: classes6.dex */
public abstract class BrowserHistory {
    private static ArrayList<Utilities.Callback<ArrayList<Entry>>> callbacks;
    private static ArrayList<Entry> history;
    private static LongSparseArray<Entry> historyById;
    public static boolean historyLoaded;
    public static boolean historyLoading;

    public static class Entry extends TLObject {
        public long id;
        public WebMetadataCache.WebMetadata meta;
        public long time;
        public String url;

        public Entry() {
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.id = r3.readInt64(r4);
            this.time = r3.readInt64(r4);
            this.url = r3.readString(r4);
            WebMetadataCache.WebMetadata r0 = new WebMetadataCache.WebMetadata();
            this.meta = r0;
            r0.readParams(r3, r4);
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt64(this.id);
            r3.writeInt64(this.time);
            String r0 = this.url;
            if (r0 != null) goto L5;
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            r3.writeString(r0);
            this.meta.serializeToStream(r3);
        }
    }

    public static /* synthetic */ void $r8$lambda$Kuxe_91qzCM0G3KWDE6i3os2I0k() {
        saveHistory();
    }

    /* renamed from: $r8$lambda$XLggRB-9xRVBOZxQbci8KT9Gp2I, reason: not valid java name */
    public static /* synthetic */ void m22044$r8$lambda$XLggRB9xRVBOZxQbci8KT9Gp2I() {
        ArrayList r0 = new ArrayList();
        LongSparseArray r1 = new LongSparseArray();
        File r2 = getHistoryFile();     // Catch: Exception -> L10
        if (r2.exists() == false) goto L12;
        SerializedData r3 = new SerializedData(r2);     // Catch: Exception -> L10
        long r4 = r3.readInt64(true);     // Catch: Exception -> L10
        long r6 = 0;
    L7:
        if (r6 >= r4) goto L12;
        Entry r8 = new Entry();     // Catch: Exception -> L10
        r8.readParams(r3, true);     // Catch: Exception -> L10
        r0.add(r8);     // Catch: Exception -> L10
        r1.put(r8.id, r8);     // Catch: Exception -> L10
        r6 = r6 + 1;
    L12:
        AndroidUtilities.runOnUIThread(new BrowserHistory$$ExternalSyntheticLambda3(r0, r1));
        return;
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L12
    }

    public static /* synthetic */ void $r8$lambda$dRU362fkhJ4Ahr_Zn4AAc3sHuCE() {
        File r0 = getHistoryFile();     // Catch: Exception -> L17
        if (r0.exists() == true) goto L5;
        r0.createNewFile();     // Catch: Exception -> L17
    L5:
        long r1 = history.size();     // Catch: Exception -> L17
        SerializedData r3 = new SerializedData(true);     // Catch: Exception -> L17
        r3.writeInt64(r1);     // Catch: Exception -> L17
        ArrayList<Entry> r4 = history;     // Catch: Exception -> L17
        int r5 = r4.size();     // Catch: Exception -> L17
        int r6 = 0;
        int r7 = 0;
    L6:
        if (r7 >= r5) goto L8;
        Entry r8 = r4.get(r7);     // Catch: Exception -> L17
        r7 = r7 + 1;     // Catch: Exception -> L17
        r8.serializeToStream(r3);     // Catch: Exception -> L17
        goto L6
    L8:
        SerializedData r42 = new SerializedData(r3.length());     // Catch: Exception -> L17
        r42.writeInt64(r1);     // Catch: Exception -> L17
        ArrayList<Entry> r12 = history;     // Catch: Exception -> L17
        int r2 = r12.size();     // Catch: Exception -> L17
    L9:
        if (r6 >= r2) goto L21;
        Entry r32 = r12.get(r6);     // Catch: Exception -> L17
        r6 = r6 + 1;     // Catch: Exception -> L17
        r32.serializeToStream(r42);     // Catch: Exception -> L17
        goto L9
    L21:
        FileOutputStream r13 = new FileOutputStream(r0);     // Catch: Exception -> L14
        r13.write(r42.toByteArray());     // Catch: Exception -> L14
        r13.close();     // Catch: Exception -> L14
    L24:
        return;
    L14:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L17
        return;
    L17:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$t5XNsDGR4uk9GQQTvfDDerHU1uc(ArrayList r6, LongSparseArray r7) {
        int r1 = 0;
        history.addAll(0, r6);
        int r0 = 0;
    L4:
        if (r0 >= r7.size()) goto L6;
        historyById.put(r7.keyAt(r0), (Entry) r7.valueAt(r0));
        r0 = r0 + 1;
        goto L4
    L6:
        historyLoaded = true;
        historyLoading = false;
        ArrayList<Utilities.Callback<ArrayList<Entry>>> r72 = callbacks;
        if (r72 == null) goto L15;
        int r02 = r72.size();
    L9:
        if (r1 >= r02) goto L11;
        Utilities.Callback<ArrayList<Entry>> r2 = r72.get(r1);
        r1 = r1 + 1;
        r2.run(r6);
        goto L9
    L11:
        callbacks = null;
        return;
    }

    public static void clearHistory() {
        history.clear();     // Catch: Exception -> L6
        historyById.clear();     // Catch: Exception -> L6
        File r0 = getHistoryFile();     // Catch: Exception -> L6
        if (r0.exists() == false) goto L11;
        r0.delete();     // Catch: Exception -> L6
        return;
    L11:
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
    }

    public static ArrayList<Entry> getHistory() {
        return getHistory(null);
    }

    public static ArrayList<Entry> getHistory(Utilities.Callback<ArrayList<Entry>> r1) {
        if (r1 != null) goto L4;
    L9:
        boolean r12 = false;
    L10:
        preloadHistory();
        if (r12 == false) goto L15;
        return null;
    L15:
        return history;
    L4:
        if (historyLoaded == true) goto L9;
        if (callbacks != null) goto L8;
        callbacks = new ArrayList();
    L8:
        callbacks.add(r1);
        r12 = true;
        goto L10
    }

    public static File getHistoryFile() {
        return new File(FileLoader.getDirectory(4), "webhistory.dat");
    }

    public static void preloadHistory() {
        if (historyLoading == false) goto L5;
        return;
    L5:
        if (historyLoaded == true) goto L10;
        historyLoading = true;
        history = new ArrayList();
        historyById = new LongSparseArray();
        Utilities.globalQueue.postRunnable(new BrowserHistory$$ExternalSyntheticLambda1());
        return;
    }

    public static void pushHistory(Entry r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.meta == null) goto L13;
        preloadHistory();
        Entry r0 = historyById.get(r3.id);
        if (r0 == null) goto L9;
        r0.meta = r3.meta;
    L10:
        scheduleHistorySave();
        return;
    L9:
        history.add(r3);
        historyById.put(r3.id, r3);
        goto L10
    }

    private static void saveHistory() {
        Utilities.globalQueue.postRunnable(new BrowserHistory$$ExternalSyntheticLambda2());
    }

    private static void scheduleHistorySave() {
        AndroidUtilities.cancelRunOnUIThread(new BrowserHistory$$ExternalSyntheticLambda0());
        AndroidUtilities.runOnUIThread(new BrowserHistory$$ExternalSyntheticLambda0(), 1000);
    }
}
