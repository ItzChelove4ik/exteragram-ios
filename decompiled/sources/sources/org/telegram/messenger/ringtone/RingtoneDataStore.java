package org.telegram.messenger.ringtone;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.NotificationBadge;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.SerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes.dex */
public class RingtoneDataStore {
    private static volatile long lastReloadTimeMs;
    private static volatile long queryHash;
    public static final HashSet<String> ringtoneSupportedMimeType = null;
    private final long clientUserId;
    private final int currentAccount;
    private boolean loaded;
    private int localIds;
    String prefName;
    public final ArrayList<CachedTone> userRingtones;

    public class CachedTone {
        public TLRPC.Document document;
        public int localId;
        public String localUri;
        final /* synthetic */ RingtoneDataStore this$0;
        public boolean uploading;

        public CachedTone(RingtoneDataStore r1) {
            this.this$0 = r1;
        }
    }

    public static /* synthetic */ void $r8$lambda$5YGXjrViwHtJJVJqAUPWHA4T5qY(RingtoneDataStore r0, TLRPC.Document r1) {
        r0.lambda$checkRingtoneSoundsLoaded$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$5_xn2_wdH8wydA5X7RuSQwnoZe8(RingtoneDataStore r0, TLObject r1) {
        r0.lambda$loadUserRingtones$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$dWXM_IuuAUKgUYPTRnnb9Ibffb0(RingtoneDataStore r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadUserRingtones$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$erRlYTrO3rCTH4EfF4iABOiwFI4(RingtoneDataStore r0, ArrayList r1) {
        r0.lambda$checkRingtoneSoundsLoaded$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$rokDH6Up81hrrCmHv_sB3fjzY7I(RingtoneDataStore r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$t8F4Aa-ksIHmROUJZepmIUhQdyE, reason: not valid java name */
    public static /* synthetic */ void m5170$r8$lambda$t8F4AaksIHmROUJZepmIUhQdyE(RingtoneDataStore r0) {
        r0.lambda$loadFromPrefs$3();
    }

    static {
        ringtoneSupportedMimeType = new HashSet(Arrays.asList(new String[]{"audio/mpeg3", "audio/mpeg", "audio/ogg", "audio/m4a"}));
    }

    public RingtoneDataStore(int r6) {
        this.prefName = null;
        this.userRingtones = new ArrayList();
        this.currentAccount = r6;
        this.clientUserId = UserConfig.getInstance(r6).clientUserId;
        SharedPreferences r62 = getSharedPreferences();
        queryHash = r62.getLong("hash", 0);     // Catch: Exception -> L5
        lastReloadTimeMs = r62.getLong("lastReload", 0);     // Catch: Exception -> L5
    L7:
        AndroidUtilities.runOnUIThread(new RingtoneDataStore$$ExternalSyntheticLambda1(this));
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private SharedPreferences getSharedPreferences() {
        if (this.prefName != null) goto L6;
        this.prefName = "ringtones_pref_" + this.clientUserId;
    L6:
        return ApplicationLoader.applicationContext.getSharedPreferences(this.prefName, 0);
    }

    private /* synthetic */ void lambda$checkRingtoneSoundsLoaded$4(TLRPC.Document r2) {
        FileLoader.getInstance(this.currentAccount).loadFile(r2, r2, 0, 0);
    }

    private /* synthetic */ void lambda$checkRingtoneSoundsLoaded$5(ArrayList r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.size()) goto L21;
        CachedTone r1 = (CachedTone) r5.get(r0);
        if (r1 == null) goto L20;
        if (TextUtils.isEmpty(r1.localUri) == false) goto L11;
    L13:
        TLRPC.Document r12 = r1.document;
        if (r12 == null) goto L20;
        File r2 = FileLoader.getInstance(this.currentAccount).getPathToAttach(r12);
        if (r2 != null) goto L18;
    L19:
        AndroidUtilities.runOnUIThread(new RingtoneDataStore$$ExternalSyntheticLambda5(this, r12));
        goto L20
    L18:
        if (r2.exists() == true) goto L20;
    L11:
        if (new File(r1.localUri).exists() == false) goto L13;
    L20:
        r0 = r0 + 1;
        goto L4
    }

    private /* synthetic */ void lambda$loadFromPrefs$3() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.onUserRingtonesUpdated, new Object[0]);
    }

    private /* synthetic */ void lambda$loadUserRingtones$1(TLObject r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if ((r4 instanceof TL_account.TL_savedRingtonesNotModified) == false) goto L7;
        loadFromPrefs(true);
    L9:
        checkRingtoneSoundsLoaded();
        return;
    L7:
        if ((r4 instanceof TL_account.TL_savedRingtones) == false) goto L9;
        TL_account.TL_savedRingtones r42 = (TL_account.TL_savedRingtones) r4;
        saveTones(r42.ringtones);
        SharedPreferences.Editor r0 = getSharedPreferences().edit();
        long r1 = r42.hash;
        queryHash = r1;
        SharedPreferences.Editor r43 = r0.putLong("hash", r1);
        long r02 = System.currentTimeMillis();
        lastReloadTimeMs = r02;
        r43.putLong("lastReload", r02).apply();
        goto L9
    }

    private /* synthetic */ void lambda$loadUserRingtones$2(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new RingtoneDataStore$$ExternalSyntheticLambda3(this, r1));
    }

    private /* synthetic */ void lambda$new$0() {
        loadUserRingtones(false);
    }

    private void loadFromPrefs(boolean r8) {
        SharedPreferences r0 = getSharedPreferences();
        int r2 = 0;
        int r1 = r0.getInt(NotificationBadge.NewHtcHomeBadger.COUNT, 0);
        this.userRingtones.clear();
    L3:
        if (r2 >= r1) goto L13;
        String r3 = r0.getString("tone_document" + r2, _UrlKt.FRAGMENT_ENCODE_SET);
        String r4 = r0.getString("tone_local_path" + r2, _UrlKt.FRAGMENT_ENCODE_SET);
        SerializedData r5 = new SerializedData(Utilities.hexToBytes(r3));
        TLRPC.Document r32 = TLRPC.Document.TLdeserialize(r5, r5.readInt32(true), true);     // Catch: Throwable -> L7
        CachedTone r52 = new CachedTone(this);     // Catch: Throwable -> L7
        r52.document = r32;     // Catch: Throwable -> L7
        r52.localUri = r4;     // Catch: Throwable -> L7
        int r33 = this.localIds;     // Catch: Throwable -> L7
        this.localIds = r33 + 1;     // Catch: Throwable -> L7
        r52.localId = r33;     // Catch: Throwable -> L7
        this.userRingtones.add(r52);     // Catch: Throwable -> L7
    L11:
        r2 = r2 + 1;
    L7:
        th = move-exception;
        if (BuildVars.DEBUG_PRIVATE_VERSION == true) goto L12;
        FileLog.e(th);
        goto L11
    L12:
        throw th;
    L13:
        if (r8 == false) goto L22;
        AndroidUtilities.runOnUIThread(new RingtoneDataStore$$ExternalSyntheticLambda2(this));
        return;
    }

    private void saveTones(ArrayList<TLRPC.Document> r10) {
        if (this.loaded == true) goto L5;
        loadFromPrefs(false);
        this.loaded = true;
    L5:
        HashMap r0 = new HashMap();
        ArrayList<CachedTone> r2 = this.userRingtones;
        int r3 = r2.size();
        int r4 = 0;
    L6:
        if (r4 >= r3) goto L12;
        CachedTone r5 = r2.get(r4);
        r4 = r4 + 1;
        CachedTone r52 = r5;
        if (r52.localUri == null) goto L6;
        TLRPC.Document r6 = r52.document;
        if (r6 == null) goto L6;
        r0.put(Long.valueOf(r6.id), r52.localUri);
        goto L6
    L12:
        this.userRingtones.clear();
        SharedPreferences r22 = getSharedPreferences();
        r22.edit().clear().apply();
        SharedPreferences.Editor r23 = r22.edit();
        r23.putInt(NotificationBadge.NewHtcHomeBadger.COUNT, r10.size());
        int r32 = 0;
    L14:
        if (r32 >= r10.size()) goto L19;
        TLRPC.Document r42 = r10.get(r32);
        String r53 = (String) r0.get(Long.valueOf(r42.id));
        SerializedData r62 = new SerializedData(r42.getObjectSize());
        r42.serializeToStream(r62);
        r23.putString("tone_document" + r32, Utilities.bytesToHex(r62.toByteArray()));
        if (r53 == null) goto L18;
        r23.putString("tone_local_path" + r32, r53);
    L18:
        CachedTone r63 = new CachedTone(this);
        r63.document = r42;
        r63.localUri = r53;
        int r43 = this.localIds;
        this.localIds = r43 + 1;
        r63.localId = r43;
        this.userRingtones.add(r63);
        r32 = r32 + 1;
        goto L14
    L19:
        r23.apply();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.onUserRingtonesUpdated, new Object[0]);
    }

    public void addTone(TLRPC.Document r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (contains(r3.id) == true) goto L9;
        CachedTone r0 = new CachedTone(this);
        r0.document = r3;
        int r32 = this.localIds;
        this.localIds = r32 + 1;
        r0.localId = r32;
        r0.uploading = false;
        this.userRingtones.add(r0);
        saveTones();
        return;
    }

    public void addUploadingTone(String r3) {
        CachedTone r0 = new CachedTone(this);
        r0.localUri = r3;
        int r32 = this.localIds;
        this.localIds = r32 + 1;
        r0.localId = r32;
        r0.uploading = true;
        this.userRingtones.add(r0);
    }

    public void checkRingtoneSoundsLoaded() {
        if (this.loaded == true) goto L5;
        loadFromPrefs(true);
        this.loaded = true;
    L5:
        ArrayList r0 = new ArrayList(this.userRingtones);
        Utilities.globalQueue.postRunnable(new RingtoneDataStore$$ExternalSyntheticLambda4(this, r0));
    }

    public boolean contains(long r1) {
        if (getDocument(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public TLRPC.Document getDocument(long r4) {
        if (this.loaded == true) goto L5;
        loadFromPrefs(true);
        this.loaded = true;
    L5:
        int r0 = 0;
    L21:
    L17:
        e = move-exception;
        FileLog.e(e);
        return null;
    L7:
        if (r0 >= this.userRingtones.size()) goto L28;
        if (this.userRingtones.get(r0) == null) goto L16;
        if (this.userRingtones.get(r0).document == null) goto L16;
        if (this.userRingtones.get(r0).document.id != r4) goto L16;
        return this.userRingtones.get(r0).document;
    L16:
        r0 = r0 + 1;
        goto L21
    L28:
        return null;
    }

    public String getSoundPath(long r4) {
        if (this.loaded == true) goto L5;
        loadFromPrefs(true);
        this.loaded = true;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.userRingtones.size()) goto L19;
        if (this.userRingtones.get(r0).document == null) goto L18;
        if (this.userRingtones.get(r0).document.id != r4) goto L18;
        if (TextUtils.isEmpty(this.userRingtones.get(r0).localUri) == true) goto L17;
        return this.userRingtones.get(r0).localUri;
    L17:
        return FileLoader.getInstance(this.currentAccount).getPathToAttach(this.userRingtones.get(r0).document).toString();
    L18:
        r0 = r0 + 1;
        goto L7
    L19:
        return "NoSound";
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public void loadUserRingtones(boolean r6) {
        if (r6 == false) goto L5;
    L8:
        boolean r62 = true;
    L9:
        TL_account.getSavedRingtones r1 = new TL_account.getSavedRingtones();
        r1.hash = queryHash;
        if (r62 == false) goto L14;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new RingtoneDataStore$$ExternalSyntheticLambda0(this));
        return;
    L14:
        if (this.loaded == true) goto L16;
        loadFromPrefs(true);
        this.loaded = true;
    L16:
        checkRingtoneSoundsLoaded();
        return;
    L5:
        if ((System.currentTimeMillis() - lastReloadTimeMs) > DurationKt.MILLIS_IN_DAY) goto L8;
        r62 = false;
        goto L9
    }

    public void onRingtoneUploaded(String r4, TLRPC.Document r5, boolean r6) {
        boolean r0 = true;
        if (r6 == false) goto L14;
        int r52 = 0;
    L6:
        if (r52 >= this.userRingtones.size()) goto L13;
        if (this.userRingtones.get(r52).uploading == false) goto L12;
        if (r4.equals(this.userRingtones.get(r52).localUri) == false) goto L12;
        this.userRingtones.remove(r52);
    L26:
        if (r0 == false) goto L37;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.onUserRingtonesUpdated, new Object[0]);
        return;
    L37:
        return;
    L12:
        r52 = r52 + 1;
        goto L6
    L13:
        r0 = false;
        goto L26
    L14:
        int r62 = 0;
    L16:
        if (r62 >= this.userRingtones.size()) goto L23;
        if (this.userRingtones.get(r62).uploading == false) goto L22;
        if (r4.equals(this.userRingtones.get(r62).localUri) == false) goto L22;
        this.userRingtones.get(r62).uploading = false;
        this.userRingtones.get(r62).document = r5;
    L24:
        if (r0 == false) goto L26;
        saveTones();
    L22:
        r62 = r62 + 1;
        goto L16
    L23:
        r0 = false;
        goto L24
    }

    public void remove(TLRPC.Document r6) {
        if (r6 != null) goto L5;
        return;
    L5:
        if (this.loaded == true) goto L7;
        loadFromPrefs(true);
        this.loaded = true;
    L7:
        int r0 = 0;
    L9:
        if (r0 >= this.userRingtones.size()) goto L22;
        if (this.userRingtones.get(r0).document == null) goto L16;
        if (this.userRingtones.get(r0).document.id != r6.id) goto L16;
        this.userRingtones.remove(r0);
        return;
    L16:
        r0 = r0 + 1;
        goto L9
    }

    public void saveTones() {
        SharedPreferences r0 = getSharedPreferences();
        r0.edit().clear().apply();
        SharedPreferences.Editor r02 = r0.edit();
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r2 >= this.userRingtones.size()) goto L12;
        if (this.userRingtones.get(r2).uploading == true) goto L11;
        r3 = r3 + 1;
        TLRPC.Document r4 = this.userRingtones.get(r2).document;
        String r5 = this.userRingtones.get(r2).localUri;
        SerializedData r6 = new SerializedData(r4.getObjectSize());
        r4.serializeToStream(r6);
        r02.putString("tone_document" + r2, Utilities.bytesToHex(r6.toByteArray()));
        if (r5 == null) goto L11;
        r02.putString("tone_local_path" + r2, r5);
    L11:
        r2 = r2 + 1;
        goto L4
    L12:
        r02.putInt(NotificationBadge.NewHtcHomeBadger.COUNT, r3);
        r02.apply();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.onUserRingtonesUpdated, new Object[0]);
    }
}
