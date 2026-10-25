package org.telegram.messenger;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.exteragram.messenger.icons.IconManager;
import com.google.android.gms.cast.MediaError;
import j$.io.FileRetargetClass;
import j$.nio.file.CopyOption;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import java.io.File;
import java.util.ArrayList;
import java.util.stream.Stream;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.StickerImageView;

/* loaded from: classes.dex */
public class FilesMigrationService extends Service {
    public static FilesMigrationBottomSheet filesMigrationBottomSheet = null;
    public static boolean hasOldFolder = false;
    public static boolean isRunning = false;
    private static boolean wasShown = false;
    long lastUpdateTime;
    private int movedFilesCount;
    private int totalFilesCount;

    /* renamed from: org.telegram.messenger.FilesMigrationService$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass1 extends Thread {
        final /* synthetic */ FilesMigrationService this$0;

        public static /* synthetic */ void $r8$lambda$JWK7xPU6lQgwk2wzQbIqvWAuoKA(AnonymousClass1 r0) {
            r0.lambda$run$0();
        }

        public AnonymousClass1(FilesMigrationService r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$run$0() {
            FilesMigrationService.isRunning = false;
            this.this$0.stopForeground(true);
            this.this$0.stopSelf();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.this$0.migrateOldFolder();
            AndroidUtilities.runOnUIThread(new FilesMigrationService$1$$ExternalSyntheticLambda0(this));
        }
    }

    /* loaded from: classes5.dex */
    public static class FilesMigrationBottomSheet extends BottomSheet {
        BaseFragment fragment;

        public static /* synthetic */ void $r8$lambda$pBP7F9Ux5NPpZGjJZfHwwCneVIs(FilesMigrationBottomSheet r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public FilesMigrationBottomSheet(BaseFragment r15) {
            super(r15.getParentActivity(), false);
            this.fragment = r15;
            setCanceledOnTouchOutside(false);
            Activity r152 = r15.getParentActivity();
            LinearLayout r0 = new LinearLayout(r152);
            r0.setOrientation(1);
            StickerImageView r3 = new StickerImageView(r152, this.currentAccount);
            r3.setStickerNum(7);
            r3.getImageReceiver().setAutoRepeat(1);
            r0.addView(r3, LayoutHelper.createLinear(144, 144, 1, 0, 16, 0, 0));
            TextView r32 = new TextView(r152);
            r32.setGravity(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES);
            int r5 = Theme.key_dialogTextBlack;
            r32.setTextColor(Theme.getColor(r5));
            r32.setTextSize(1, 20.0f);
            r32.setTypeface(AndroidUtilities.bold());
            r32.setText(LocaleController.getString(R.string.MigrateOldFolderTitle));
            r0.addView(r32, LayoutHelper.createFrame(-1, -2.0f, 0, 21.0f, 30.0f, 21.0f, 0.0f));
            TextView r33 = new TextView(r152);
            r33.setGravity(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES);
            r33.setTextSize(1, 15.0f);
            r33.setTextColor(Theme.getColor(r5));
            r33.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.MigrateOldFolderDescription)));
            r0.addView(r33, LayoutHelper.createFrame(-1, -2.0f, 0, 21.0f, 15.0f, 21.0f, 16.0f));
            TextView r34 = new TextView(r152);
            r34.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
            r34.setGravity(17);
            r34.setTextSize(1, 14.0f);
            r34.setTypeface(AndroidUtilities.bold());
            r34.setText(LocaleController.getString(R.string.MigrateOldFolderButton));
            r34.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
            r34.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{6.0f}));
            r0.addView(r34, LayoutHelper.createFrame(-1, 48.0f, 0, 16.0f, 15.0f, 16.0f, 16.0f));
            r34.setOnClickListener(new FilesMigrationService$FilesMigrationBottomSheet$$ExternalSyntheticLambda0(this));
            ScrollView r1 = new ScrollView(r152);
            r1.addView(r0);
            setCustomView(r1);
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            migrateOldFolder();
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public boolean canDismissWithSwipe() {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public boolean canDismissWithTouchOutside() {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            FilesMigrationService.filesMigrationBottomSheet = null;
        }

        public void migrateOldFolder() {
            Activity r0 = this.fragment.getParentActivity();
            boolean r3 = false;
            if (r0.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) goto L5;
            boolean r2 = true;
        L6:
            int r5 = Build.VERSION.SDK_INT;
            if (r5 >= 33) goto L9;
        L14:
            if (r5 < 33) goto L16;
        L18:
            if (r3 == false) goto L23;
            if (r2 == false) goto L23;
            FilesMigrationService.start();
            dismiss();
            return;
        L23:
            ArrayList r12 = new ArrayList();
            if (r3 == true) goto L28;
            if (r5 < 33) goto L27;
            r12.add("android.permission.READ_MEDIA_IMAGES");
            r12.add("android.permission.READ_MEDIA_VIDEO");
            r12.add("android.permission.READ_MEDIA_AUDIO");
            goto L28
        L27:
            r12.add("android.permission.READ_EXTERNAL_STORAGE");
        L28:
            if (r2 == true) goto L30;
            r12.add("android.permission.WRITE_EXTERNAL_STORAGE");
        L30:
            r0.requestPermissions((String[]) r12.toArray(new String[r12.size()]), 4);
            return;
        L16:
            if (r0.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) goto L18;
        L17:
            r3 = true;
            goto L18
        L9:
            if (r0.checkSelfPermission("android.permission.READ_MEDIA_IMAGES") != 0) goto L14;
            if (r0.checkSelfPermission("android.permission.READ_MEDIA_VIDEO") != 0) goto L14;
            if (r0.checkSelfPermission("android.permission.READ_MEDIA_AUDIO") == 0) goto L17;
        L5:
            r2 = false;
            goto L6
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public /* bridge */ /* synthetic */ void setLastVisible(boolean r1) {
            super.setLastVisible(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$__4K4ggOqYdh8ZMIW6FvuyZxuxY(FilesMigrationService r0, File r1, Path r2) {
        r0.lambda$moveDirectory$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$f_GcEhT2WGRWhAWWU9TEFpcm5JQ(FilesMigrationService r0, int r1) {
        r0.lambda$updateProgress$1(r1);
    }

    static {
    }

    public FilesMigrationService() {
    }

    public static void checkBottomSheet(BaseFragment r11) {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", 0);
        if (Environment.isExternalStorageLegacy() == true) goto L5;
        return;
    L5:
        if (r0.getBoolean("migration_to_scoped_storage_finished", false) == false) goto L7;
        return;
    L7:
        if (r0.getInt("migration_to_scoped_storage_count", 0) < 5) goto L9;
        return;
    L9:
        if (wasShown == false) goto L11;
        return;
    L11:
        if (filesMigrationBottomSheet == null) goto L13;
        return;
    L13:
        if (isRunning == false) goto L16;
        return;
    L16:
        if (Build.VERSION.SDK_INT < 30) goto L29;
        File r4 = Environment.getExternalStorageDirectory();
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == true) goto L27;
        ArrayList<File> r5 = AndroidUtilities.getRootDirs();
        if (r5 == null) goto L27;
        int r6 = r5.size();
        int r7 = 0;
    L22:
        if (r7 >= r6) goto L27;
        File r8 = r5.get(r7);
        if (r8.getAbsolutePath().startsWith(SharedConfig.storageCacheDir) == true) goto L25;
        r7 = r7 + 1;
        goto L22
    L25:
        r4 = r8;
    L27:
        hasOldFolder = new File(r4, "exteraGram").exists();
    L29:
        if (hasOldFolder == false) goto L32;
        FilesMigrationBottomSheet r1 = new FilesMigrationBottomSheet(r11);
        filesMigrationBottomSheet = r1;
        r1.show();
        wasShown = true;
        r0.edit().putInt("migration_to_scoped_storage_count", r0.getInt("migration_to_scoped_storage_count", 0) + 1).apply();
        return;
    L32:
        r0.edit().putBoolean("migration_to_scoped_storage_finished", true).apply();
    }

    private int getFilesCount(File r4) {
        int r1 = 0;
        if (r4.exists() == true) goto L5;
        return 0;
    L5:
        File[] r42 = r4.listFiles();
        if (r42 == null) goto L16;
        int r0 = 0;
    L9:
        if (r1 >= r42.length) goto L15;
        if (r42[r1].isDirectory() == false) goto L13;
        r0 = r0 + getFilesCount(r42[r1]);
    L14:
        r1 = r1 + 1;
        goto L9
    L13:
        r0 = r0 + 1;
        goto L14
    L15:
        return r0;
    L16:
        return 0;
    }

    private /* synthetic */ void lambda$moveDirectory$0(File r3, Path r4) {
        File r0 = new File(r3, r4.getFileName().toString());
        if (Files.isDirectory(r4, new LinkOption[0]) == false) goto L16;
        moveDirectory(r4.toFile(), r0);
        return;
    L16:
        Files.move(r4, FileRetargetClass.toPath(r0), new CopyOption[0]);     // Catch: Exception -> L8
    L14:
        this.movedFilesCount++;
        updateProgress();
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        r4.toFile().delete();     // Catch: Exception -> L12
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L14
    }

    private /* synthetic */ void lambda$updateProgress$1(int r4) {
        FilesMigrationService$$ExternalSyntheticApiModelOutline1.m();
        Notification r42 = FilesMigrationService$$ExternalSyntheticApiModelOutline0.m(this, NotificationsController.OTHER_NOTIFICATIONS_CHANNEL).setContentTitle(LocaleController.getString(R.string.MigratingFiles)).setContentText(String.format("%s/%s", new Object[]{Integer.valueOf(r4), Integer.valueOf(this.totalFilesCount)})).setSmallIcon(IconManager.getNotificationSystemIcon()).setAutoCancel(false).setProgress(this.totalFilesCount, r4, false).build();
        ((NotificationManager) getSystemService("notification")).notify(MediaError.DetailedErrorCode.SEGMENT_NETWORK, r42);
    }

    private void moveDirectory(File r3, File r4) {
        if (r3.exists() == true) goto L5;
        return;
    L5:
        if (r4.exists() == false) goto L7;
    L34:
        Stream<Path> r0 = Files.list(FileRetargetClass.toPath(r3));     // Catch: Exception -> L13
        r0.forEach(new FilesMigrationService$$ExternalSyntheticLambda2(this, r4));     // Catch: Throwable -> L15
        r0.close();     // Catch: Exception -> L13
    L30:
        r3.delete();     // Catch: Exception -> L25
        return;
    L25:
        e = move-exception;
        FileLog.e(e);
        return;
    L15:
        th = move-exception;
        if (r0 != null) goto L28;
    L21:
        throw th;     // Catch: Exception -> L13
    L28:
        r0.close();     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L13
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L30
    L7:
        if (r4.mkdir() == true) goto L34;
    }

    public static void start() {
        Intent r0 = new Intent(ApplicationLoader.applicationContext, FilesMigrationService.class);
        ApplicationLoader.applicationContext.startService(r0);
    }

    private void updateProgress() {
        if ((System.currentTimeMillis() - this.lastUpdateTime) <= 20) goto L5;
    L8:
        AndroidUtilities.runOnUIThread(new FilesMigrationService$$ExternalSyntheticLambda3(this, this.movedFilesCount));
        return;
    L5:
        if (this.movedFilesCount >= (this.totalFilesCount - 1)) goto L8;
    }

    public void migrateOldFolder() {
        File r0 = Environment.getExternalStorageDirectory();
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == true) goto L12;
        ArrayList<File> r1 = AndroidUtilities.getRootDirs();
        if (r1 == null) goto L12;
        int r3 = r1.size();
        int r4 = 0;
    L7:
        if (r4 >= r3) goto L12;
        File r5 = r1.get(r4);
        if (r5.getAbsolutePath().startsWith(SharedConfig.storageCacheDir) == true) goto L10;
        r4 = r4 + 1;
        goto L7
    L10:
        r0 = r5;
    L12:
        File r32 = new File(ApplicationLoader.applicationContext.getExternalFilesDir(null), "exteraGram");
        File r12 = new File(r0, "exteraGram");
        this.totalFilesCount = getFilesCount(r12);
        long r42 = System.currentTimeMillis();
        if (r12.canRead() == true) goto L15;
    L17:
        FileLog.d("move time = " + (System.currentTimeMillis() - r42));
        ApplicationLoader.applicationContext.getSharedPreferences("systemConfig", 0).edit().putBoolean("migration_to_scoped_storage_finished", true).apply();
        return;
    L15:
        if (r12.canWrite() == false) goto L17;
        moveDirectory(r12, r32);
        goto L17
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent r3, int r4, int r5) {
        NotificationsController.checkOtherNotificationsChannel();
        FilesMigrationService$$ExternalSyntheticApiModelOutline1.m();
        Notification r0 = FilesMigrationService$$ExternalSyntheticApiModelOutline0.m(this, NotificationsController.OTHER_NOTIFICATIONS_CHANNEL).setContentTitle(LocaleController.getString(R.string.MigratingFiles)).setAutoCancel(false).setSmallIcon(IconManager.getNotificationSystemIcon()).build();
        isRunning = true;
        new AnonymousClass1(this).start();
        startForeground(MediaError.DetailedErrorCode.SEGMENT_NETWORK, r0);
        return super.onStartCommand(r3, r4, r5);
    }
}
