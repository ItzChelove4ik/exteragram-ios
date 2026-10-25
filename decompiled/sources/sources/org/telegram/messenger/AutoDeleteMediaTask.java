package org.telegram.messenger;

import android.util.SparseArray;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.LongCompanionObject;
import org.telegram.messenger.CacheByChatsController;

/* loaded from: classes.dex */
public class AutoDeleteMediaTask {
    public static Set<String> usingFilePaths;

    /* loaded from: classes5.dex */
    public static class FileInfoInternal extends CacheByChatsController.KeepMediaFile {
        final long lastUsageDate;

        private FileInfoInternal(File r3) {
            super(r3);
            this.lastUsageDate = Utilities.getLastUsageFileTime(r3.getAbsolutePath());
        }

        public /* synthetic */ FileInfoInternal(File r1, AutoDeleteMediaTaskIA r2) {
            this(r1);
        }
    }

    public static /* synthetic */ int $r8$lambda$8dg4GbivFh1IfgXiPENigECyG2w(FileInfoInternal r2, FileInfoInternal r3) {
        long r0 = r3.lastUsageDate;
        long r22 = r2.lastUsageDate;
        if (r0 <= r22) goto L6;
        return -1;
    L6:
        if (r0 >= r22) goto L9;
        return 1;
    L9:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$ap5ZJdebehBgIX4zKtBVK_9N3_k(int r29, File r30) {
        int r1 = r29;
        long r2 = System.currentTimeMillis();
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("checkKeepMedia start task");
    L5:
        ArrayList r4 = new ArrayList();
        int r0 = 0;
        boolean r6 = false;
    L6:
        int r8 = 1;
        if (r0 >= 16) goto L14;
        if (UserConfig.getInstance(r0).isClientActivated() == false) goto L13;
        CacheByChatsController r7 = UserConfig.getInstance(r0).getMessagesController().getCacheByChatsController();
        r4.add(r7);
        if (r7.getKeepMediaExceptionsByDialogs().size() <= 0) goto L13;
        r6 = true;
    L13:
        r0 = r0 + 1;
        goto L6
    L14:
        int r72 = 4;
        int[] r9 = new int[4];
        boolean r14 = true;
        int r02 = 0;
        long r12 = LongCompanionObject.MAX_VALUE;
    L15:
        if (r02 >= 4) goto L24;
        int r10 = SharedConfig.getPreferences().getInt("keep_media_type_" + r02, CacheByChatsController.getDefault(r02));
        r9[r02] = r10;
        if (r10 == CacheByChatsController.KEEP_MEDIA_FOREVER) goto L19;
        r14 = false;
    L19:
        long r102 = CacheByChatsController.getDaysInSeconds(r10);
        if (r102 >= r12) goto L22;
        r12 = r102;
    L22:
        r02 = r02 + 1;
        goto L15
    L24:
        if (r6 == false) goto L26;
        r14 = false;
    L26:
        SparseArray<File> r62 = ImageLoader.getInstance().createMediaPaths();
        int r103 = 0;
        int r15 = 0;
        long r20 = 0;
    L28:
        if (r15 >= r62.size()) goto L107;
        if (r14 == false) goto L37;
        if (r62.keyAt(r15) != r8) goto L34;
    L35:
        long r27 = r2;
    L106:
        r15 = r15 + 1;
        r1 = r29;
        r2 = r27;
        r72 = 4;
        r8 = 1;
        goto L28
    L34:
        if (r62.keyAt(r15) == 3) goto L35;
    L37:
        if (r62.keyAt(r15) != r72) goto L39;
        int r22 = r8;
    L161:
        File[] r03 = r62.valueAt(r15).listFiles();     // Catch: Throwable -> L52
        ArrayList<? extends CacheByChatsController.KeepMediaFile> r73 = new ArrayList();     // Catch: Throwable -> L52
        if (r03 == null) goto L55;
        int r11 = 0;
    L45:
        if (r11 >= r03.length) goto L55;
        if (r03[r11].isDirectory() == true) goto L54;
        if (usingFilePaths.contains(r03[r11].getAbsolutePath()) == true) goto L54;
        r73.add(new CacheByChatsController.KeepMediaFile(r03[r11]));     // Catch: Throwable -> L52
    L54:
        r11 = r11 + 1;     // Catch: Throwable -> L52
    L55:
        int r04 = 0;
    L57:
        if (r04 >= r4.size()) goto L59;
        ((CacheByChatsController) r4.get(r04)).lookupFiles(r73);     // Catch: Throwable -> L52
        r04 = r04 + 1;     // Catch: Throwable -> L52
        goto L57
    L59:
        int r5 = 0;
    L61:
        if (r5 >= r73.size()) goto L35;
        CacheByChatsController.KeepMediaFile r05 = (CacheByChatsController.KeepMediaFile) r73.get(r5);     // Catch: Throwable -> L52
        if (r05.isStory == false) goto L67;
        long r25 = CacheByChatsController.getDaysInSeconds(r9[3]);     // Catch: Throwable -> L52
        r27 = r2;
    L66:
        long r23 = r1 - r25;
        ArrayList<? extends CacheByChatsController.KeepMediaFile> r112 = r73;
        long r74 = Utilities.getLastUsageFileTime(r05.file.getAbsolutePath());     // Catch: Throwable -> L73
        if (r74 > 316000000) goto L88;
    L103:
        int r24 = r5;
    L104:
        r5 = r24 + 1;
        r1 = r29;
        r73 = r112;
        r2 = r27;
        goto L61
    L88:
        if (r74 >= r23) goto L103;
        r24 = r5;
        if (usingFilePaths.contains(r05.file.getPath()) == true) goto L104;
        if (BuildVars.LOGS_ENABLED == false) goto L97;
        r103 = r103 + 1;     // Catch: Throwable -> L73 Exception -> L94
        r20 = r20 + r05.file.length();     // Catch: Throwable -> L73 Exception -> L94
    L97:
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L99;
        FileLog.d("delete file " + r05.file.getPath() + " last_usage_time=" + r74 + " time_local=" + r23 + " story=" + r05.isStory);     // Catch: Throwable -> L73 Exception -> L94
    L99:
        r05.file.delete();     // Catch: Throwable -> L73 Exception -> L94
        goto L104
    L94:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L73
    L73:
        th = th;
    L105:
        FileLog.e(th);
        goto L106
    L67:
        r27 = r2;
        int r26 = r05.keepMedia;     // Catch: Throwable -> L73
        if (r26 != CacheByChatsController.KEEP_MEDIA_FOREVER) goto L71;
    L70:
        r24 = r5;
        r112 = r73;
        goto L104
    L71:
        if (r26 < 0) goto L75;
        long r28 = CacheByChatsController.getDaysInSeconds(r26);     // Catch: Throwable -> L73
    L82:
        if (r28 == LongCompanionObject.MAX_VALUE) goto L70;
        r25 = r28;
        goto L66
    L75:
        int r210 = r05.dialogType;     // Catch: Throwable -> L73
        if (r210 < 0) goto L78;
        r28 = CacheByChatsController.getDaysInSeconds(r9[r210]);     // Catch: Throwable -> L73
        goto L82
    L78:
        if (r22 != 0) goto L70;
        r28 = r12;
    L52:
        th = th;
        r27 = r2;
        goto L105
    L39:
        r22 = 0;
        goto L161
    L107:
        long r272 = r2;
        int r06 = SharedConfig.getPreferences().getInt("cache_limit", Integer.MAX_VALUE);
        if (r06 != Integer.MAX_VALUE) goto L110;
    L142:
        long r52 = 0;
        int r42 = 0;
        int r92 = 0;
    L143:
        File r07 = new File(r30, "acache");
        if (r07.exists() == true) goto L163;
    L150:
        MessagesController.getGlobalMainSettings().edit().putInt("lastKeepMediaCheckTime", SharedConfig.lastKeepMediaCheckTime).apply();
        if (BuildVars.LOGS_ENABLED == false) goto L194;
        FileLog.d("checkKeepMedia task end time " + (System.currentTimeMillis() - r272) + " auto deleted info: files " + r103 + " size " + AndroidUtilities.formatFileSize(r20) + "   deleted by size limit info: files " + r42 + " size " + AndroidUtilities.formatFileSize(r52) + " unknownTimeFiles " + r92);
        return;
    L194:
        return;
    L163:
        Utilities.clearDir(r07.getAbsolutePath(), 0, r29 - 86400, false);     // Catch: Throwable -> L148
    L148:
        th = move-exception;
        FileLog.e(th);
        goto L150
    L110:
        if (r06 != 1) goto L112;
        long r08 = 314572800;
    L113:
        long r75 = 0;
        int r211 = 0;
    L115:
        if (r211 >= r62.size()) goto L118;
        r75 = r75 + Utilities.getDirSize(r62.valueAt(r211).getAbsolutePath(), 0, true);
        r211 = r211 + 1;
        goto L115
    L118:
        if (r75 <= r08) goto L142;
        ArrayList<? extends CacheByChatsController.KeepMediaFile> r212 = new ArrayList();
        int r93 = 0;
    L121:
        if (r93 >= r62.size()) goto L123;
        fillFilesRecursive(r62.valueAt(r93), r212);
        r93 = r93 + 1;
        goto L121
    L123:
        int r94 = 0;
    L125:
        if (r94 >= r4.size()) goto L127;
        ((CacheByChatsController) r4.get(r94)).lookupFiles(r212);
        r94 = r94 + 1;
        goto L125
    L127:
        Collections.sort(r212, new AutoDeleteMediaTask$$ExternalSyntheticLambda1());
        r52 = 0;
        int r3 = 0;
        r42 = 0;
        int r95 = 0;
    L129:
        if (r95 >= r212.size()) goto L140;
        if (((FileInfoInternal) r212.get(r95)).keepMedia == CacheByChatsController.KEEP_MEDIA_FOREVER) goto L141;
        if (((FileInfoInternal) r212.get(r95)).lastUsageDate > 0) goto L136;
        r3 = r3 + 1;
        goto L141
    L136:
        long r113 = ((FileInfoInternal) r212.get(r95)).file.length();
        r75 = r75 - r113;
        r42 = r42 + 1;
        r52 = r52 + r113;
        ((FileInfoInternal) r212.get(r95)).file.delete();     // Catch: Exception -> L154
    L139:
        if (r75 < r08) goto L140;
    L141:
        r95 = r95 + 1;
    L140:
        r92 = r3;
        goto L143
    L112:
        r08 = r06 * 1048576000;
        goto L113
    }

    static {
        usingFilePaths = Collections.newSetFromMap(new ConcurrentHashMap());
    }

    public AutoDeleteMediaTask() {
    }

    private static void fillFilesRecursive(File r5, ArrayList<FileInfoInternal> r6) {
        if (r5 == null) goto L20;
        File[] r52 = r5.listFiles();
        if (r52 == null) goto L26;
        int r0 = r52.length;
        int r1 = 0;
    L8:
        if (r1 >= r0) goto L27;
        File r2 = r52[r1];
        if (r2.isDirectory() == false) goto L13;
        fillFilesRecursive(r2, r6);
    L19:
        r1 = r1 + 1;
        goto L8
    L13:
        if (r2.getName().equals(".nomedia") == true) goto L19;
        if (usingFilePaths.contains(r2.getAbsolutePath()) == true) goto L19;
        r6.add(new FileInfoInternal(r2, null));
        goto L19
    L27:
        return;
    L26:
        return;
    }

    public static void lockFile(File r0) {
        if (r0 != null) goto L4;
        return;
    L4:
        lockFile(r0.getAbsolutePath());
    }

    public static void lockFile(String r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        usingFilePaths.add(r1);
    }

    public static void run() {
        int r0 = (int) (System.currentTimeMillis() / 1000);
        if (Math.abs(r0 - SharedConfig.lastKeepMediaCheckTime) >= 86400) goto L5;
        return;
    L5:
        SharedConfig.lastKeepMediaCheckTime = r0;
        File r1 = FileLoader.checkDirectory(4);
        Utilities.cacheClearQueue.postRunnable(new AutoDeleteMediaTask$$ExternalSyntheticLambda0(r0, r1));
    }

    public static void unlockFile(File r0) {
        if (r0 != null) goto L4;
        return;
    L4:
        unlockFile(r0.getAbsolutePath());
    }

    public static void unlockFile(String r1) {
        if (r1 != null) goto L4;
        return;
    L4:
        usingFilePaths.remove(r1);
    }
}
