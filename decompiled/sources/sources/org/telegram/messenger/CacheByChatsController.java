package org.telegram.messenger;

import android.text.TextUtils;
import android.util.LongSparseArray;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class CacheByChatsController {
    public static int KEEP_MEDIA_DELETE = 4;
    public static int KEEP_MEDIA_FOREVER = 2;
    public static int KEEP_MEDIA_ONE_DAY = 3;
    public static int KEEP_MEDIA_ONE_MINUTE = 5;
    public static int KEEP_MEDIA_ONE_MONTH = 1;
    public static int KEEP_MEDIA_ONE_WEEK = 0;
    public static int KEEP_MEDIA_TWO_DAY = 6;
    public static final int KEEP_MEDIA_TYPE_CHANNEL = 2;
    public static final int KEEP_MEDIA_TYPE_GROUP = 1;
    public static final int KEEP_MEDIA_TYPE_STORIES = 3;
    public static final int KEEP_MEDIA_TYPE_USER = 0;
    private final int currentAccount;
    private boolean gotKeepMediaByTypes;
    private final int[] keepMediaByTypes;

    /* loaded from: classes5.dex */
    public static class KeepMediaException {
        public final long dialogId;
        public int keepMedia;

        public KeepMediaException(long r1, int r3) {
            this.dialogId = r1;
            this.keepMedia = r3;
        }
    }

    /* loaded from: classes5.dex */
    public static class KeepMediaFile {
        int dialogType;
        final File file;
        boolean isStory;
        int keepMedia;

        public KeepMediaFile(File r2) {
            this.keepMedia = -1;
            this.dialogType = 2;
            this.file = r2;
        }
    }

    static {
    }

    public CacheByChatsController(int r2) {
        this.gotKeepMediaByTypes = false;
        this.keepMediaByTypes = new int[]{-1, -1, -1, -1};
        this.currentAccount = r2;
    }

    public static long getDaysInSeconds(int r2) {
        if (r2 != KEEP_MEDIA_ONE_WEEK) goto L7;
        return 604800;
    L7:
        if (r2 != KEEP_MEDIA_ONE_MONTH) goto L11;
        return 2592000;
    L11:
        if (r2 != KEEP_MEDIA_ONE_DAY) goto L15;
        return 86400;
    L15:
        if (r2 != KEEP_MEDIA_TWO_DAY) goto L19;
        return 172800;
    L19:
        if (r2 == KEEP_MEDIA_ONE_MINUTE) goto L21;
        return LongCompanionObject.MAX_VALUE;
    L21:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L26;
        return 60;
    L26:
        return LongCompanionObject.MAX_VALUE;
    }

    public static int getDefault(int r1) {
        if (r1 != 0) goto L6;
        return KEEP_MEDIA_FOREVER;
    L6:
        if (r1 != 1) goto L10;
        return KEEP_MEDIA_ONE_MONTH;
    L10:
        if (r1 != 2) goto L14;
        return KEEP_MEDIA_ONE_WEEK;
    L14:
        if (r1 != 3) goto L18;
        return KEEP_MEDIA_TWO_DAY;
    L18:
        return SharedConfig.keepMedia;
    }

    public static String getKeepMediaString(int r4) {
        if (r4 != KEEP_MEDIA_ONE_MINUTE) goto L7;
        return LocaleController.formatPluralString("Minutes", 1, new Object[0]);
    L7:
        if (r4 != KEEP_MEDIA_ONE_DAY) goto L11;
        return LocaleController.formatPluralString("Days", 1, new Object[0]);
    L11:
        if (r4 != KEEP_MEDIA_TWO_DAY) goto L15;
        return LocaleController.formatPluralString("Days", 2, new Object[0]);
    L15:
        if (r4 != KEEP_MEDIA_ONE_WEEK) goto L19;
        return LocaleController.formatPluralString("Weeks", 1, new Object[0]);
    L19:
        if (r4 != KEEP_MEDIA_ONE_MONTH) goto L23;
        return LocaleController.formatPluralString("Months", 1, new Object[0]);
    L23:
        return LocaleController.getString(R.string.AutoDeleteMediaNever);
    }

    public int getKeepMedia(int r6) {
        if (this.gotKeepMediaByTypes == true) goto L8;
        this.gotKeepMediaByTypes = true;
        int r0 = 0;
    L6:
        if (r0 >= 4) goto L8;
        this.keepMediaByTypes[r0] = SharedConfig.getPreferences().getInt("keep_media_type_" + r0, getDefault(r0));
        r0 = r0 + 1;
    L8:
        int r5 = this.keepMediaByTypes[r6];
        if (r5 == (-1)) goto L11;
        return r5;
    L11:
        return SharedConfig.keepMedia;
    }

    public ArrayList<KeepMediaException> getKeepMediaExceptions(int r8) {
        ArrayList<KeepMediaException> r0 = new ArrayList();
        HashSet r1 = new HashSet();
        String r7 = UserConfig.getInstance(this.currentAccount).getPreferences().getString("keep_media_exceptions_" + r8, _UrlKt.FRAGMENT_ENCODE_SET);
        if (TextUtils.isEmpty(r7) == false) goto L5;
        return r0;
    L5:
        ByteBuffer r72 = ByteBuffer.wrap(Utilities.hexToBytes(r7));
        int r82 = r72.getInt();
        int r2 = 0;
    L6:
        if (r2 >= r82) goto L11;
        KeepMediaException r3 = new KeepMediaException(r72.getLong(), r72.getInt());
        if (r1.contains(Long.valueOf(r3.dialogId)) == true) goto L10;
        r1.add(Long.valueOf(r3.dialogId));
        r0.add(r3);
    L10:
        r2 = r2 + 1;
        goto L6
    L11:
        r72.clear();
        return r0;
    }

    public LongSparseArray<KeepMediaException> getKeepMediaExceptionsByDialogs() {
        LongSparseArray<KeepMediaException> r0 = new LongSparseArray();
        int r2 = 0;
    L4:
        if (r2 >= 3) goto L12;
        ArrayList<KeepMediaException> r3 = getKeepMediaExceptions(r2);
        if (r3 == null) goto L11;
        int r4 = 0;
    L9:
        if (r4 >= r3.size()) goto L11;
        r0.put(r3.get(r4).dialogId, r3.get(r4));
        r4 = r4 + 1;
    L11:
        r2 = r2 + 1;
        goto L4
    L12:
        return r0;
    }

    public void lookupFiles(ArrayList<? extends KeepMediaFile> r11) {
        LongSparseArray<ArrayList<KeepMediaFile>> r112 = FileLoader.getInstance(this.currentAccount).getFileDatabase().lookupFiles(r11);
        LongSparseArray<KeepMediaException> r0 = getKeepMediaExceptionsByDialogs();
        int r2 = 0;
    L4:
        if (r2 >= r112.size()) goto L27;
        long r3 = r112.keyAt(r2);
        ArrayList<KeepMediaFile> r5 = r112.valueAt(r2);
        if (r3 < 0) goto L8;
        int r6 = 0;
    L17:
        KeepMediaException r32 = r0.get(r3);
        int r4 = 0;
    L19:
        if (r4 >= r5.size()) goto L26;
        KeepMediaFile r7 = r5.get(r4);
        if (r6 < 0) goto L23;
        r7.dialogType = r6;
    L23:
        if (r32 == null) goto L25;
        r7.keepMedia = r32.keepMedia;
    L25:
        r4 = r4 + 1;
        goto L19
    L26:
        r2 = r2 + 1;
        goto L4
    L8:
        long r72 = -r3;
        TLRPC.Chat r62 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r72));
        if (r62 != null) goto L11;
        r62 = MessagesStorage.getInstance(this.currentAccount).getChatSync(r72);
    L11:
        if (r62 != null) goto L14;
        r6 = -1;
        goto L17
    L14:
        if (ChatObject.isChannel(r62) == false) goto L16;
        r6 = 2;
        goto L17
    L16:
        r6 = 1;
        goto L17
    }

    public void saveKeepMediaExceptions(int r6, ArrayList<KeepMediaException> r7) {
        String r62 = "keep_media_exceptions_" + r6;
        if (r7.isEmpty() == false) goto L6;
        UserConfig.getInstance(this.currentAccount).getPreferences().edit().remove(r62).apply();
        return;
    L6:
        int r0 = r7.size();
        ByteBuffer r1 = ByteBuffer.allocate((r0 * 12) + 4);
        r1.putInt(r0);
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L9;
        r1.putLong(r7.get(r2).dialogId);
        r1.putInt(r7.get(r2).keepMedia);
        r2 = r2 + 1;
        goto L7
    L9:
        UserConfig.getInstance(this.currentAccount).getPreferences().edit().putString(r62, Utilities.bytesToHex(r1.array())).apply();
        r1.clear();
    }

    public void setKeepMedia(int r7, int r8) {
        if (this.gotKeepMediaByTypes == true) goto L8;
        this.gotKeepMediaByTypes = true;
        int r0 = 0;
    L6:
        if (r0 >= 4) goto L8;
        this.keepMediaByTypes[r0] = SharedConfig.getPreferences().getInt("keep_media_type_" + r0, getDefault(r0));
        r0 = r0 + 1;
    L8:
        this.keepMediaByTypes[r7] = r8;
        SharedConfig.getPreferences().edit().putInt("keep_media_type_" + r7, r8).apply();
    }
}
