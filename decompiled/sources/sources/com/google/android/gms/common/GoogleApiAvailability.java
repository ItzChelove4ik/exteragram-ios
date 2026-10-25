package com.google.android.gms.common;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.base.R$drawable;
import com.google.android.gms.cast.CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.api.internal.zabr;
import com.google.android.gms.common.api.internal.zabs;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.zaq;
import com.google.android.gms.common.internal.zab;
import com.google.android.gms.common.internal.zaf;
import com.google.android.gms.common.internal.zaj;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.internal.base.zak;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class GoogleApiAvailability extends GoogleApiAvailabilityLight {
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0;
    private static final Object zaa = null;
    private static final GoogleApiAvailability zab = null;
    private String zac;
    private com.google.android.gms.common.internal.zad zad;

    static {
        zaa = new Object();
        zab = new GoogleApiAvailability();
        GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    public GoogleApiAvailability() {
    }

    public static GoogleApiAvailability getInstance() {
        return zab;
    }

    public Dialog getErrorDialog(Activity r8, int r9, int r10, DialogInterface.OnCancelListener r11) {
        return zaa(r8, r9, zaj.zab(r8, getErrorResolutionIntent(r8, r9, "d"), r10), r11, null);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public Intent getErrorResolutionIntent(Context r1, int r2, String r3) {
        return super.getErrorResolutionIntent(r1, r2, r3);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public PendingIntent getErrorResolutionPendingIntent(Context r1, int r2, int r3) {
        return super.getErrorResolutionPendingIntent(r1, r2, r3);
    }

    public PendingIntent getErrorResolutionPendingIntent(Context r2, ConnectionResult r3) {
        if (r3.hasResolution() == false) goto L7;
        return r3.getResolution();
    L7:
        return getErrorResolutionPendingIntent(r2, r3.getErrorCode(), 0);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public final String getErrorString(int r1) {
        return super.getErrorString(r1);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public int isGooglePlayServicesAvailable(Context r1) {
        return super.isGooglePlayServicesAvailable(r1);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public int isGooglePlayServicesAvailable(Context r1, int r2) {
        return super.isGooglePlayServicesAvailable(r1, r2);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public final boolean isUserResolvableError(int r1) {
        return super.isUserResolvableError(r1);
    }

    public boolean showErrorDialogFragment(Activity r1, int r2, int r3, DialogInterface.OnCancelListener r4) {
        Dialog r22 = getErrorDialog(r1, r2, r3, r4);
        if (r22 != null) goto L6;
        return false;
    L6:
        zah(r1, r22, "GooglePlayServicesErrorDialog", r4);
        return true;
    }

    public void showErrorNotification(Context r3, int r4) {
        zac(r3, r4, null, getErrorResolutionPendingIntent(r3, r4, 0, "n"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Dialog zaa(Context r5, int r6, zaj r7, DialogInterface.OnCancelListener r8, DialogInterface.OnClickListener r9) {
        AlertDialog.Builder r4 = null;
        if (r6 != 0) goto L5;
        return null;
    L5:
        TypedValue r0 = new TypedValue();
        r5.getTheme().resolveAttribute(R.attr.alertDialogTheme, r0, true);
        if ("Theme.Dialog.Alert".equals(r5.getResources().getResourceEntryName(r0.resourceId)) == false) goto L8;
        r4 = new AlertDialog.Builder(r5, 5);
    L8:
        if (r4 != null) goto L10;
        r4 = new AlertDialog.Builder(r5);
    L10:
        r4.setMessage(zaf.zac(r5, r6));
        if (r8 == null) goto L13;
        r4.setOnCancelListener(r8);
    L13:
        String r82 = zaf.zae(r5, r6);
        if (r82 == null) goto L18;
        if (r7 != null) goto L17;
        r7 = r9;
    L17:
        r4.setPositiveButton(r82, r7);
    L18:
        String r52 = zaf.zaa(r5, r6);
        if (r52 == null) goto L21;
        r4.setTitle(r52);
    L21:
        Log.w("GoogleApiAvailability", String.format("Creating dialog for Google Play services availability issue. ConnectionResult=%s", new Object[]{Integer.valueOf(r6)}), new IllegalArgumentException());
        return r4.create();
    }

    public final boolean zab(Activity r8, LifecycleFragment r9, int r10, int r11, DialogInterface.OnCancelListener r12) {
        Dialog r7 = zaa(r8, r10, zaj.zad(r9, getErrorResolutionIntent(r8, r10, "d"), 2), r12, null);
        if (r7 != null) goto L6;
        return false;
    L6:
        zah(r8, r7, "GooglePlayServicesErrorDialog", r12);
        return true;
    }

    public final void zac(Context r9, int r10, String r11, PendingIntent r12) {
        Log.w("GoogleApiAvailability", String.format("GMS core API Availability. ConnectionResult=%s, tag=%s", new Object[]{Integer.valueOf(r10), null}), new IllegalArgumentException());
        if (r10 != 18) goto L6;
        zai(r9);
        return;
    L6:
        if (r12 == null) goto L8;
        String r112 = zaf.zab(r9, r10);
        String r0 = zaf.zad(r9, r10);
        Resources r1 = r9.getResources();
        NotificationManager r2 = (NotificationManager) Preconditions.checkNotNull(r9.getSystemService("notification"));
        NotificationCompat.Builder r113 = new NotificationCompat.Builder(r9).setLocalOnly(true).setAutoCancel(true).setContentTitle(r112).setStyle(new NotificationCompat.BigTextStyle().bigText(r0));
        boolean r3 = DeviceProperties.isWearable(r9);
        int r6 = R.drawable.stat_sys_warning;
        if (r3 == false) goto L21;
        int r02 = r9.getApplicationInfo().icon;
        if (r02 == 0) goto L17;
        r6 = r02;
    L17:
        r113.setSmallIcon(r6).setPriority(2);
        if (DeviceProperties.isWearableWithoutPlayStore(r9) == false) goto L20;
        r113.addAction(R$drawable.common_full_open_on_phone, r1.getString(com.google.android.gms.base.R$string.common_open_on_phone), r12);
    L23:
        if (PlatformVersion.isAtLeastO() == false) goto L37;
        Preconditions.checkState(PlatformVersion.isAtLeastO());
        Object r122 = zaa;
        monitor-enter(r122);
        String r8 = this.zac;     // Catch: Throwable -> L46
        monitor-exit(r122);     // Catch: Throwable -> L46
        if (r8 != null) goto L36;
        r8 = "com.google.android.gms.availability";
        NotificationChannel r123 = r2.getNotificationChannel("com.google.android.gms.availability");
        String r92 = r9.getResources().getString(com.google.android.gms.base.R$string.common_google_play_services_notification_channel_name);
        if (r123 != null) goto L34;
        r2.createNotificationChannel(CastRemoteDisplayLocalService$$ExternalSyntheticApiModelOutline0.m("com.google.android.gms.availability", r92, 4));
        goto L36
    L34:
        if (r92.contentEquals(r123.getName()) == true) goto L36;
        r123.setName(r92);
        r2.createNotificationChannel(r123);
    L36:
        r113.setChannelId(r8);
    L46:
        th = move-exception;
        throw th;
    L37:
        Notification r82 = r113.build();
        if (r10 == 1) goto L43;
        if (r10 == 2) goto L43;
        if (r10 == 3) goto L43;
        int r93 = 39789;
    L44:
        r2.notify(r93, r82);
        return;
    L43:
        GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.set(false);
        r93 = 10436;
        goto L44
    L20:
        r113.setContentIntent(r12);
        goto L23
    L21:
        r113.setSmallIcon(R.drawable.stat_sys_warning).setTicker(r1.getString(com.google.android.gms.base.R$string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setContentIntent(r12).setContentText(r0);
        goto L23
    L8:
        if (r10 != 6) goto L51;
        Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
        return;
    }

    public final boolean zad(Context r6, ConnectionResult r7, int r8) {
        if (InstantApps.isInstantApp(r6) == true) goto L9;
        PendingIntent r0 = getErrorResolutionPendingIntent(r6, r7);
        if (r0 == null) goto L9;
        zac(r6, r7.getErrorCode(), null, zak.zaa(r6, 0, GoogleApiActivity.zaa(r6, r0, r8, true), zak.zaa | 134217728));
        zae(r6, r7, false);
        return true;
    L9:
        return false;
    }

    public final void zae(Context r9, ConnectionResult r10, boolean r11) {
        Integer r0 = r10.getClientMethodKey();
        if (r0 != null) goto L6;
        int r02 = -1;
    L5:
        int r2 = r02;
        zab r1 = new zab(r2, r9.getPackageName(), System.currentTimeMillis(), r10.getErrorCode(), r11);
        if (this.zad != null) goto L10;
        this.zad = new zaq(r9);
    L10:
        this.zad.zaa(r1);
        return;
    L6:
        r02 = r0.intValue();
        goto L5
    }

    public final Dialog zaf(Activity r4, DialogInterface.OnCancelListener r5) {
        ProgressBar r0 = new ProgressBar(r4, null, R.attr.progressBarStyleLarge);
        r0.setIndeterminate(true);
        r0.setVisibility(0);
        AlertDialog.Builder r1 = new AlertDialog.Builder(r4);
        r1.setView(r0);
        r1.setMessage(zaf.zac(r4, 18));
        r1.setPositiveButton(_UrlKt.FRAGMENT_ENCODE_SET, null);
        AlertDialog r02 = r1.create();
        zah(r4, r02, "GooglePlayServicesUpdatingDialog", r5);
        return r02;
    }

    public final zabs zag(Context r4, zabr r5) {
        IntentFilter r0 = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        r0.addDataScheme("package");
        zabs r1 = new zabs(r5);
        ContextCompat.registerReceiver(r4, r1, r0, 2);
        r1.zaa(r4);
        if (isUninstalledAppPossiblyUpdating(r4, "com.google.android.gms") == true) goto L6;
        r5.zaa();
        r1.zab();
        return null;
    L6:
        return r1;
    }

    public final void zah(Activity r1, Dialog r2, String r3, DialogInterface.OnCancelListener r4) {
        if ((r1 instanceof FragmentActivity) == false) goto L6;
        FragmentManager r0 = ((FragmentActivity) r1).getSupportFragmentManager();
        SupportErrorDialogFragment.newInstance(r2, r4).show(r0, r3);
        return;
    L6:
        android.app.FragmentManager r02 = r1.getFragmentManager();
        ErrorDialogFragment.newInstance(r2, r4).show(r02, r3);
    }

    public final void zai(Context r4) {
        new zad(this, r4).sendEmptyMessageDelayed(1, 120000);
    }
}
