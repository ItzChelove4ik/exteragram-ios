package com.exteragram.messenger.updater;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInstaller;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.utils.AppUtils;
import com.exteragram.messenger.utils.network.RemoteUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.XiaomiUtilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.StickerImageView;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes4.dex */
public abstract class UpdaterUtils {

    @SuppressLint({"StaticFieldLeak"})
    private static AlertDialog dialog;

    public static class InstallReceiver extends BroadcastReceiver {
        private final Context context;
        private Intent intent;
        private final CountDownLatch latch;
        private final Runnable onSuccess;
        private final String packageName;

        public static /* synthetic */ Bulletin $r8$lambda$8VpP5GnMgbgrw4pDVy2xfm1rYm4(Intent r3, BulletinFactory r4) {
            return r4.createErrorBulletin(LocaleController.formatString(R.string.UpdateFailedToInstall, new Object[]{Integer.valueOf(r3.getIntExtra("android.content.pm.extra.STATUS", 1))}));
        }

        private InstallReceiver(Context r3, String r4, Runnable r5) {
            this.latch = new CountDownLatch(1);
            this.intent = null;
            this.context = r3;
            this.packageName = r4;
            this.onSuccess = r5;
        }

        public /* synthetic */ InstallReceiver(Context r1, String r2, Runnable r3, UpdaterUtilsIA r4) {
            this(r1, r2, r3);
        }

        private void handleFailure(Intent r3) {
            int r0 = r3.getIntExtra("android.content.pm.extra.SESSION_ID", 0);
            if (r0 <= 0) goto L7;
            PackageInstaller r1 = this.context.getPackageManager().getPackageInstaller();
            PackageInstaller.SessionInfo r02 = r1.getSessionInfo(r0);
            if (r02 == null) goto L7;
            r1.abandonSession(r02.getSessionId());
        L7:
            Context r2 = this.context;
            if ((r2 instanceof LaunchActivity) == false) goto L11;
            ((LaunchActivity) r2).showBulletin(new UpdaterUtils$InstallReceiver$$ExternalSyntheticLambda0(r3));
            return;
        }

        private void handlePackageInstallerResult(Intent r4) {
            int r0 = r4.getIntExtra("android.content.pm.extra.STATUS", 4);
            if (r0 != (-1)) goto L5;
            this.intent = (Intent) r4.getParcelableExtra("android.intent.extra.INTENT");
        L22:
            this.latch.countDown();
            return;
        L5:
            if (r0 != 1) goto L7;
        L16:
            handleFailure(r4);
        L17:
            Runnable r42 = this.onSuccess;
            if (r42 == null) goto L20;
            r42.run();
        L20:
            this.context.unregisterReceiver(this);
            goto L22
        L7:
            if (r0 == 2) goto L16;
            if (r0 == 4) goto L16;
            if (r0 == 5) goto L16;
            if (r0 == 6) goto L16;
            if (r0 == 7) goto L16;
            goto L16
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r2, Intent r3) {
            if ("android.intent.action.PACKAGE_ADDED".equals(r3.getAction()) == false) goto L13;
            Uri r22 = r3.getData();
            if (r22 != null) goto L7;
            return;
        L7:
            if (this.onSuccess != null) goto L10;
            return;
        L10:
            if (r22.getSchemeSpecificPart().equals(this.packageName) == false) goto L16;
            this.onSuccess.run();
            this.context.unregisterReceiver(this);
            return;
        L16:
            return;
        L13:
            handlePackageInstallerResult(r3);
        }

        public Intent waitIntent() {
            this.latch.await(5, TimeUnit.SECONDS);     // Catch: Exception -> L5
        L4:
            return this.intent;
        }
    }

    public static class UpdateReceiver extends BroadcastReceiver {
        public UpdateReceiver() {
        }

        private void launchApp(Context r4) {
            Intent r0 = new Intent(r4, LaunchActivity.class).setFlags(268435456);
            if (Build.VERSION.SDK_INT >= 29) goto L5;
        L9:
            r4.startActivity(r0);
            return;
        L5:
            if (Settings.canDrawOverlays(r4) == true) goto L9;
            showNotification(r4, r0);
        }

        private void showNotification(Context r4, Intent r5) {
            NotificationChannelCompat r3 = new NotificationChannelCompat.Builder("updated", 4).setName(LocaleController.getString(R.string.UpdateApp)).setLightsEnabled(false).setVibrationEnabled(false).setSound(null, null).build();
            NotificationManagerCompat r2 = NotificationManagerCompat.from(r4);
            r2.createNotificationChannel(r3);
            PendingIntent r32 = PendingIntent.getActivity(r4, 0, r5, 201326592);
            r2.notify(8732833, new NotificationCompat.Builder(r4, "updated").setSmallIcon(IconManager.getNotificationIcon()).setColor(AppUtils.getNotificationColor()).setShowWhen(false).setContentText(LocaleController.getString(R.string.UpdateInstalledNotification)).setCategory("status").setContentIntent(r32).build());
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r2, Intent r3) {
            if ("android.intent.action.MY_PACKAGE_REPLACED".equals(r3.getAction()) == false) goto L10;
            String r32 = r2.getPackageName();
            if (r32.equals(r2.getPackageManager().getInstallerPackageName(r32)) == true) goto L8;
            return;
        L8:
            launchApp(r2);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$2UAZ2HqXxk8Nz7zqmbeCwTKXcr0(Utilities.Callback2 r1, TLRPC.messages_Messages r2, TLRPC.TL_error r3) {
        if (r3 != null) goto L12;
        if (r2 == null) goto L12;
        TLRPC.TL_help_appUpdate r22 = parseUpdateResponse(r2);
        if (r22.id <= 0) goto L10;
        r1.run(r22, null);
        return;
    L10:
        TLRPC.TL_error r32 = new TLRPC.TL_error();
        r32.text = "NO_UPDATE_METADATA";
        r1.run(r22, r32);
        return;
    L12:
        r1.run(null, r3);
    }

    /* renamed from: $r8$lambda$J-rnIHfIWjS4z_PFGN622PKVTCU, reason: not valid java name */
    public static /* synthetic */ void m1760$r8$lambda$JrnIHfIWjS4z_PFGN622PKVTCU() {
        AlertDialog r0 = dialog;
        if (r0 == null) goto L6;
        r0.dismiss();
        dialog = null;
        return;
    }

    public static /* synthetic */ void $r8$lambda$QoJtvgafGFst_6huPKkX41WQHeM() {
        AlertDialog r0 = dialog;
        if (r0 == null) goto L6;
        r0.dismiss();
        dialog = null;
        return;
    }

    public static /* synthetic */ void $r8$lambda$zEBo0VGo0yW4IXbbAahjubhZI0U(Activity r1, File r2) {
        InstallReceiver r0 = register(r1, new UpdaterUtils$$ExternalSyntheticLambda1());
        installApk(r1, r2);
        Intent r22 = r0.waitIntent();
        if (r22 == null) goto L6;
        r1.startActivity(r22);
        return;
    }

    public static void getAppUpdate(Utilities.Callback2<TLRPC.TL_help_appUpdate, TLRPC.TL_error> r1) {
        RemoteUtils.getMessages(new UpdaterUtils$$ExternalSyntheticLambda2(r1));
    }

    private static void handleInstallError(Activity r6, File r7, IOException r8) {
        AndroidUtilities.runOnUIThread(new UpdaterUtils$$ExternalSyntheticLambda3());
        AlertsCreator.createSimpleAlert(r6, LocaleController.getString(R.string.ErrorOccurred) + "\n" + r8.getLocalizedMessage()).show();
        AndroidUtilities.openForView(r7, "install.apk", "application/vnd.android.package-archive", r6, null, false);
    }

    private static void installApk(Activity r9, File r10) {
        PendingIntent r0 = PendingIntent.getBroadcast(r9, 0, new Intent(UpdaterUtils.class.getName()).setPackage(r9.getPackageName()), 167772160);
        PackageInstaller r1 = r9.getPackageManager().getPackageInstaller();
        PackageInstaller.SessionParams r2 = new PackageInstaller.SessionParams(1);
        if (Build.VERSION.SDK_INT < 31) goto L44;
        r2.setRequireUserAction(2);
    L44:
        PackageInstaller.Session r3 = r1.openSession(r1.createSession(r2));     // Catch: IOException -> L17
        OutputStream r12 = r3.openWrite(r10.getName(), 0, r10.length());     // Catch: Throwable -> L12
        FileInputStream r22 = new FileInputStream(r10);     // Catch: Throwable -> L19
        transfer(r22, r12);     // Catch: Throwable -> L21
        r22.close();     // Catch: Throwable -> L19
        if (r12 == null) goto L14;
        r12.close();     // Catch: Throwable -> L12
    L14:
        r3.commit(r0.getIntentSender());     // Catch: Throwable -> L12
        r3.close();     // Catch: IOException -> L17
        return;
    L21:
        th = move-exception;
        r22.close();     // Catch: Throwable -> L25
    L53:
        throw th;     // Catch: Throwable -> L19
    L25:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L19
        throw th;     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        if (r12 == null) goto L54;
        r12.close();     // Catch: Throwable -> L31
    L55:
        throw th;     // Catch: Throwable -> L12
    L31:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L12
        throw th;     // Catch: Throwable -> L12
    L54:
        throw th;     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        if (r3 == null) goto L56;
        r3.close();     // Catch: Throwable -> L37
    L57:
        throw th;     // Catch: IOException -> L17
    L37:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L17
        throw th;     // Catch: IOException -> L17
    L56:
        throw th;     // Catch: IOException -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        handleInstallError(r9, r10, e);
    }

    public static void installUpdate(Activity r2, TLRPC.Document r3) {
        if (r2 == null) goto L19;
        if (r3 != null) goto L6;
        return;
    L6:
        if (XiaomiUtilities.isMIUI() == false) goto L9;
        AndroidUtilities.openForView(r3, r2);
        return;
    L9:
        File r32 = FileLoader.getInstance(UserConfig.selectedAccount).getPathToAttach(r3, true);
        if (r32 == null) goto L21;
        AlertDialog r0 = dialog;
        if (r0 != null) goto L15;
    L17:
        showInstallDialog(r2);
        Utilities.globalQueue.postRunnable(new UpdaterUtils$$ExternalSyntheticLambda0(r2, r32));
        return;
    L15:
        if (r0.isShowing() == false) goto L17;
        return;
    L21:
        return;
    }

    private static TLRPC.TL_help_appUpdate parseUpdateResponse(TLRPC.messages_Messages r15) {
        TLRPC.TL_help_appUpdate r0 = new TLRPC.TL_help_appUpdate();
        ArrayList<TLRPC.Message> r1 = r15.messages;
        int r2 = r1.size();
        int r4 = 0;
    L3:
        int r6 = -1;
        if (r4 >= r2) goto L74;
        TLRPC.Message r7 = r1.get(r4);
        r4 = r4 + 1;
        TLRPC.Message r72 = r7;
        if ((r72 instanceof TLRPC.TL_message) == false) goto L3;
        if (r72.message.startsWith("update_test") == false) goto L13;
        if (BadgesController.INSTANCE.isDeveloper() == false) goto L13;
    L36:
        String[] r8 = r72.message.split("\n");
        if (r8.length < 6) goto L3;
        int r12 = r8.length;
        int r22 = 0;
        int r42 = -1;
        int r9 = -1;
        int r10 = -1;
    L40:
        if (r22 >= r12) goto L73;
        String[] r11 = r8[r22].split("=", 2);
        if (r11.length != 2) goto L72;
        String r122 = r11[0].trim();
        char r13 = 1;
        String r112 = r11[1].trim();
        r122.getClass();
        switch(r122.hashCode()) {
            case -1890252483: goto L62;
            case -1085916422: goto L59;
            case 3556653: goto L55;
            case 351608024: goto L51;
            case 861720859: goto L47;
            default: goto L45;
        };
    L45:
        r13 = 65535;
    L65:
        switch(r13) {
            case 0: goto L71;
            case 1: goto L70;
            case 2: goto L69;
            case 3: goto L68;
            case 4: goto L67;
            default: goto L72;
        };
    L67:
        r42 = Integer.parseInt(r112);
        goto L72
    L68:
        r0.version = r112;
        goto L72
    L69:
        r10 = Integer.parseInt(r112);
        goto L72
    L70:
        r0.can_not_skip = Boolean.parseBoolean(r112);
        goto L72
    L71:
        r9 = Integer.parseInt(r112);
        goto L72
    L47:
        if (r122.equals("document") == false) goto L45;
        r13 = 4;
        goto L65
    L51:
        if (r122.equals("version") == false) goto L45;
        r13 = 3;
        goto L65
    L55:
        if (r122.equals("text") == false) goto L45;
        r13 = 2;
        goto L65
    L59:
        if (r122.equals("can_not_skip") == true) goto L65;
    L62:
        if (r122.equals("sticker") == false) goto L45;
        r13 = 0;
    L72:
        r22 = r22 + 1;
        goto L40
    L73:
        r0.id = r72.id;
        r6 = r42;
    L75:
        ArrayList<TLRPC.Message> r152 = r15.messages;
        int r14 = r152.size();
        int r23 = 0;
    L76:
        if (r23 >= r14) goto L97;
        TLRPC.Message r43 = r152.get(r23);
        r23 = r23 + 1;
        TLRPC.Message r44 = r43;
        if ((r44 instanceof TLRPC.TL_message) == false) goto L76;
        if (r44.id == r10) goto L82;
        TLRPC.MessageMedia r73 = r44.media;
        if (r73 == null) goto L76;
        TLRPC.Document r74 = r73.getDocument();
        if (r44.id != r6) goto L93;
        if (r74.attributes.isEmpty() == true) goto L93;
        if (r74.attributes.get(0).file_name.endsWith(".apk") == false) goto L93;
        r0.document = r74;
        r0.flags |= 2;
    L93:
        if (r44.id != r9) goto L76;
        if (MessageObject.isStickerDocument(r74) == false) goto L76;
        r0.sticker = r74;
        r0.flags |= 8;
        goto L76
    L82:
        r0.entities = r44.entities;
        r0.text = r44.message;
        goto L76
    L97:
        return r0;
    L13:
        if (r72.message.startsWith("update_lite_beta") == false) goto L19;
        if (BuildVars.isBetaApp() == false) goto L19;
        if (BuildVars.IS_LITE_VERSION == true) goto L36;
    L19:
        if (r72.message.startsWith("update_lite_stable") == false) goto L25;
        if (BuildVars.isBetaApp() == true) goto L25;
        if (BuildVars.IS_LITE_VERSION == true) goto L36;
    L25:
        if (r72.message.startsWith("update_beta") == false) goto L31;
        if (BuildVars.isBetaApp() == false) goto L31;
        if (BuildVars.IS_LITE_VERSION == false) goto L36;
    L31:
        if (r72.message.startsWith("update_stable") == false) goto L3;
        if (BuildVars.isBetaApp() == true) goto L3;
        if (BuildVars.IS_LITE_VERSION == true) goto L3;
    L74:
        r9 = -1;
        r10 = -1;
        goto L75
    }

    private static InstallReceiver register(Context r3, Runnable r4) {
        InstallReceiver r0 = new InstallReceiver(r3, ApplicationLoader.getApplicationId(), r4, null);
        ContextCompat.registerReceiver(r3, r0, new IntentFilter(UpdaterUtils.class.getName()), 4);
        return r0;
    }

    private static void showInstallDialog(Activity r12) {
        LinearLayout r0 = new LinearLayout(r12);
        r0.setOrientation(1);
        r0.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, 51, 4.0f, 4.0f, 4.0f, 4.0f));
        StickerImageView r2 = new StickerImageView(r12, UserConfig.selectedAccount);
        r2.setStickerPackName("UtyaDuckFull");
        r2.setStickerNum(0);
        r2.getImageReceiver().setAutoRepeat(1);
        r0.addView(r2, LayoutHelper.createLinear(160, 160, 49, 17, 24, 17, 0));
        TextView r22 = new TextView(r12);
        r22.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM));
        r22.setTextSize(1, 16.0f);
        r22.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        r22.setSingleLine(true);
        r22.setEllipsize(TextUtils.TruncateAt.END);
        r22.setText(LocaleController.getString(R.string.UpdateInstalling));
        r0.addView(r22, LayoutHelper.createLinear(-2, -2, 49, 17, 20, 17, 0));
        TextView r23 = new TextView(r12);
        r23.setGravity(17);
        r23.setTextSize(1, 13.0f);
        r23.setTextColor(Theme.getColor(Theme.key_dialogTextGray));
        if (Build.VERSION.SDK_INT >= 29) goto L5;
    L8:
        String r1 = LocaleController.getString(R.string.UpdateInstallingRelaunch);
    L9:
        r23.setText(r1);
        r0.addView(r23, LayoutHelper.createLinear(-2, -2, 49, 17, 4, 17, 24));
        AlertDialog.Builder r13 = new AlertDialog.Builder(r12);
        r13.setView(r0);
        AlertDialog r122 = r13.create();
        dialog = r122;
        r122.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        return;
    L5:
        if (Settings.canDrawOverlays(r12) == true) goto L8;
        r1 = LocaleController.getString(R.string.UpdateInstallingNotification);
        goto L9
    }

    private static void transfer(InputStream r4, OutputStream r5) {
        byte[] r1 = new byte[8192];
    L3:
        int r3 = r4.read(r1, 0, 8192);
        if (r3 < 0) goto L6;
        r5.write(r1, 0, r3);
        goto L3
    }
}
