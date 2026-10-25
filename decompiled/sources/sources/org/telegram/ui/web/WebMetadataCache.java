package org.telegram.ui.web;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader$7$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.InputSerializedData;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public class WebMetadataCache {
    private static WebMetadataCache instance;
    private HashMap<String, WebMetadata> cache;
    private boolean loaded;
    private boolean loading;
    private boolean saving;

    public static final class MetadataFile extends TLObject {
        public final ArrayList<WebMetadata> array;

        private MetadataFile() {
            this.array = new ArrayList();
        }

        public /* synthetic */ MetadataFile(WebMetadataCacheIA r1) {
            this();
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r5, boolean r6) {
            int r0 = r5.readInt32(r6);
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L8;
            WebMetadata r2 = new WebMetadata();
            r2.readParams(r5, r6);
            if (TextUtils.isEmpty(r2.domain) == true) goto L11;
            this.array.add(r2);
            r1 = r1 + 1;
            goto L3
        L11:
            return;
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeInt32(this.array.size());
            int r0 = 0;
        L4:
            if (r0 >= this.array.size()) goto L6;
            this.array.get(r0).serializeToStream(r3);
            r0 = r0 + 1;
            goto L4
        }
    }

    public static class WebMetadata extends TLObject {
        public int actionBarColor;
        public int backgroundColor;
        public String domain;
        public Bitmap favicon;
        public byte[] faviconBytes;
        public String sitename;
        public long time;
        public String title;

        public WebMetadata() {
            this.time = System.currentTimeMillis();
        }

        public static WebMetadata from(BotWebViewContainer.MyWebView r3) {
            WebMetadata r0 = new WebMetadata();
            String r1 = AndroidUtilities.getHostAuthority(r3.getUrl(), true);
            r0.domain = r1;
            if (TextUtils.isEmpty(r1) == false) goto L7;
            return null;
        L7:
            if (r3.lastTitleGot == false) goto L9;
            r0.title = r3.lastTitle;
        L9:
            r0.sitename = r3.lastSiteName;
            if (r3.lastActionBarColorGot == false) goto L13;
            r0.actionBarColor = r3.lastActionBarColor;
        L13:
            if (r3.lastBackgroundColorGot == false) goto L16;
            r0.backgroundColor = r3.lastBackgroundColor;
        L16:
            if (r3.lastFaviconGot == false) goto L18;
            r0.favicon = r3.lastFavicon;
        L18:
            return r0;
        }

        @Override // org.telegram.tgnet.TLObject
        public void readParams(InputSerializedData r3, boolean r4) {
            this.time = r3.readInt64(r4);
            this.domain = r3.readString(r4);
            this.title = r3.readString(r4);
            this.sitename = r3.readString(r4);
            this.actionBarColor = r3.readInt32(r4);
            this.backgroundColor = r3.readInt32(r4);
            if (r3.readInt32(r4) != 1450380236) goto L6;
            this.favicon = null;
            return;
        L6:
            this.faviconBytes = r3.readByteArray(r4);
            this.favicon = BitmapFactory.decodeStream(new ByteArrayInputStream(this.faviconBytes));
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r6) {
            r6.writeInt64(this.time);
            String r0 = this.domain;
            String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r0 != null) goto L5;
            r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            r6.writeString(r0);
            String r02 = this.title;
            if (r02 != null) goto L8;
            r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            r6.writeString(r02);
            String r03 = this.sitename;
            if (r03 == null) goto L12;
            r1 = r03;
        L12:
            r6.writeString(r1);
            r6.writeInt32(this.actionBarColor);
            r6.writeInt32(this.backgroundColor);
            if (this.favicon != null) goto L16;
            r6.writeInt32(TLRPC.TL_null.constructor);
            return;
        L16:
            r6.writeInt32(953850003);
            byte[] r04 = this.faviconBytes;
            if (r04 == null) goto L20;
            r6.writeByteArray(r04);
            return;
        L20:
            ByteArrayOutputStream r05 = new ByteArrayOutputStream();
            int r12 = Build.VERSION.SDK_INT;
            Bitmap r2 = this.favicon;
            if (r12 < 30) goto L23;
            r2.compress(ImageLoader$7$$ExternalSyntheticApiModelOutline0.m(), 80, r05);
        L24:
            byte[] r13 = r05.toByteArray();
            this.faviconBytes = r13;
            r6.writeByteArray(r13);
            r05.close();     // Catch: Exception -> L27
            return;
        L27:
            e = move-exception;
            FileLog.e(e);
            return;
        L23:
            r2.compress(Bitmap.CompressFormat.WEBP, 80, r05);
            goto L24
        }
    }

    public static /* synthetic */ void $r8$lambda$07yg4eLMgfIQESOvIetat6xzchU(WebMetadataCache r0, ArrayList r1) {
        r0.lambda$load$0(r1);
    }

    /* renamed from: $r8$lambda$4FbfxJRJdZn-_XMHXyv9XJorY8o, reason: not valid java name */
    public static /* synthetic */ void m22065$r8$lambda$4FbfxJRJdZn_XMHXyv9XJorY8o(WebMetadataCache r0, ArrayList r1) {
        r0.lambda$save$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$pP4yEBAdm4Nvwoh7ajNQleXHw7k(WebMetadataCache r0) {
        r0.lambda$save$2();
    }

    public static /* synthetic */ void $r8$lambda$puRoMaVSNLacIMTxHwVWwIlOsXk(WebMetadataCache r0) {
        r0.lambda$load$1();
    }

    public WebMetadataCache() {
    }

    public static WebMetadataCache getInstance() {
        if (instance != null) goto L6;
        instance = new WebMetadataCache();
    L6:
        return instance;
    }

    private /* synthetic */ void lambda$load$0(ArrayList r6) {
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L6;
        WebMetadata r2 = (WebMetadata) r6.get(r1);
        this.cache.put(r2.domain, r2);
        r1 = r1 + 1;
        goto L4
    L6:
        this.loaded = true;
        this.loading = false;
    }

    private /* synthetic */ void lambda$load$1() {
        File r0 = getCacheFile();
        if (r0.exists() == true) goto L6;
        this.loaded = true;
        return;
    L6:
        ArrayList r1 = new ArrayList();
        SerializedData r3 = new SerializedData(r0);     // Catch: Exception -> L9
        MetadataFile r02 = new MetadataFile(null);     // Catch: Exception -> L9
        r02.readParams(r3, true);     // Catch: Exception -> L9
        r1.addAll(r02.array);     // Catch: Exception -> L9
    L11:
        AndroidUtilities.runOnUIThread(new WebMetadataCache$$ExternalSyntheticLambda2(this, r1));
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L11
    }

    private /* synthetic */ void lambda$save$2() {
        this.saving = false;
    }

    private /* synthetic */ void lambda$save$3(ArrayList r4) {
        File r0 = getCacheFile();
        if (r0.exists() == false) goto L16;
    L9:
        MetadataFile r1 = new MetadataFile(null);
        r1.array.addAll(r4);
        SerializedData r2 = new SerializedData(r1.getObjectSize());
        r1.serializeToStream(r2);
        FileOutputStream r42 = new FileOutputStream(r0);     // Catch: Exception -> L12
        r42.write(r2.toByteArray());     // Catch: Exception -> L12
        r42.close();     // Catch: Exception -> L12
    L14:
        AndroidUtilities.runOnUIThread(new WebMetadataCache$$ExternalSyntheticLambda4(this));
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L14
    L16:
        r0.createNewFile();     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        this.saving = false;
    }

    public void clear() {
        HashMap<String, WebMetadata> r0 = this.cache;
        if (r0 != null) goto L5;
        this.loading = false;
        this.loaded = true;
        this.cache = new HashMap();
    L6:
        scheduleSave();
        return;
    L5:
        r0.clear();
        goto L6
    }

    public WebMetadata get(String r5) {
        load();
        WebMetadata r52 = this.cache.get(r5);
        if (r52 != null) goto L6;
        return null;
    L6:
        r52.time = Math.max(r52.time, System.currentTimeMillis());
        scheduleSave();
        return r52;
    }

    public File getCacheFile() {
        return new File(FileLoader.getDirectory(4), "webmetacache.dat");
    }

    public void load() {
        if (this.loaded == false) goto L5;
        return;
    L5:
        if (this.loading == true) goto L13;
        this.loading = true;
        if (this.cache != null) goto L10;
        this.cache = new HashMap();
    L10:
        Utilities.globalQueue.postRunnable(new WebMetadataCache$$ExternalSyntheticLambda1(this));
        return;
    }

    public void save() {
        if (this.saving == false) goto L5;
        return;
    L5:
        this.saving = true;
        long r0 = System.currentTimeMillis();
        ArrayList r2 = new ArrayList();
        Iterator<WebMetadata> r3 = this.cache.values().iterator();
    L7:
        if (r3.hasNext() == false) goto L15;
        WebMetadata r4 = r3.next();
        if (TextUtils.isEmpty(r4.domain) == true) goto L7;
        if ((r0 - r4.time) > 604800000) goto L7;
        r2.add(0, r4);
        if (r2.size() < 100) goto L7;
    L15:
        Utilities.globalQueue.postRunnable(new WebMetadataCache$$ExternalSyntheticLambda3(this, r2));
    }

    public void save(WebMetadata r3) {
        if (r3 != null) goto L5;
        return;
    L5:
        if (this.cache != null) goto L8;
        this.cache = new HashMap();
    L8:
        if (TextUtils.isEmpty(r3.domain) == false) goto L10;
        return;
    L10:
        this.cache.put(r3.domain, r3);
        load();
        scheduleSave();
    }

    public void scheduleSave() {
        AndroidUtilities.cancelRunOnUIThread(new WebMetadataCache$$ExternalSyntheticLambda0(this));
        if (this.saving == false) goto L5;
        return;
    L5:
        WebMetadataCache$$ExternalSyntheticLambda0 r0 = new WebMetadataCache$$ExternalSyntheticLambda0(this);
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L8;
        long r1 = 1;
    L9:
        AndroidUtilities.runOnUIThread(r0, r1);
        return;
    L8:
        r1 = 1000;
        goto L9
    }
}
