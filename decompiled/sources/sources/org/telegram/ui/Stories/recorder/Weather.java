package org.telegram.ui.Stories.recorder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.text.TextUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.core.PillType;
import j$.util.DesugarTimeZone;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.AbstractSerializedData;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.OutputSerializedData;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.DarkThemeResourceProvider;

/* loaded from: classes3.dex */
public abstract class Weather {
    private static String cacheKey;
    private static State cacheValue;
    private static boolean requestingLocation;

    public static class State extends TLObject {
        public String emoji;
        public double lat;
        public double lng;
        public float temperature;

        public State() {
        }

        public static State TLdeserialize(AbstractSerializedData r4) {
            State r0 = new State();
            r0.lat = r4.readDouble(false);
            r0.lng = r4.readDouble(false);
            r0.emoji = r4.readString(false);
            r0.temperature = r4.readFloat(false);
            return r0;
        }

        public String getEmoji() {
            return this.emoji;
        }

        public String getTemperature() {
            return getTemperature(Weather.isDefaultCelsius());
        }

        public String getTemperature(boolean r5) {
            float r4 = this.temperature;
            if (r5 == false) goto L7;
            return Math.round(r4) + "°C";
        L7:
            return ((int) Math.round(((r4 * 9.0d) / 5.0d) + 32.0d)) + "°F";
        }

        @Override // org.telegram.tgnet.TLObject
        public void serializeToStream(OutputSerializedData r3) {
            r3.writeDouble(this.lat);
            r3.writeDouble(this.lng);
            r3.writeString(this.emoji);
            r3.writeFloat(this.temperature);
        }
    }

    public static /* synthetic */ void $r8$lambda$4S3GuGxOiu6SPlKA4XuA0c1MPc8(LocationListener[] r3, LocationManager r4, Runnable[] r5) {
        LocationListener r1 = r3[0];
        if (r1 == null) goto L5;
        r4.removeUpdates(r1);
        r3[0] = null;
    L5:
        Runnable r32 = r5[0];
        if (r32 == null) goto L9;
        AndroidUtilities.cancelRunOnUIThread(r32);
        r5[0] = null;
        return;
    }

    public static /* synthetic */ void $r8$lambda$8smBNWt3aHzArTPuFKHaKWBOLSI(int[] r0, MessagesController r1, TLRPC.User[] r2, Runnable r3, Utilities.Callback r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new Weather$$ExternalSyntheticLambda13(r0, r5, r1, r2, r3, r4));
    }

    public static /* synthetic */ void $r8$lambda$Bublf_kkm1u62mVLvToHW3UqFnA(Runnable r0, DialogInterface r1) {
        r0.run();
    }

    public static /* synthetic */ void $r8$lambda$Fj5il8a0wiK9X50oPwXxScWnjE0(boolean r2, AlertDialog r3, Utilities.Callback r4, State r5) {
        if (r2 == false) goto L4;
        r3.dismissUnless(350);
    L4:
        r4.run(r5);
    }

    public static /* synthetic */ void $r8$lambda$MOxm7U1397fyCjgamNkdPTRLtTo(Context r0, AlertDialog r1, int r2) {
        r0.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));     // Catch: Exception -> L4
        return;
    }

    /* renamed from: $r8$lambda$My5p7ErkTbKx6-HW0WXr0AxDwNU, reason: not valid java name */
    public static /* synthetic */ void m20593$r8$lambda$My5p7ErkTbKx6HW0WXr0AxDwNU(Runnable r1, Utilities.Callback[] r2, Location r3) {
        r1.run();
        Utilities.Callback r0 = r2[0];
        if (r0 == null) goto L6;
        r0.run(r3);
        r2[0] = null;
        return;
    }

    public static /* synthetic */ void $r8$lambda$fvqkoXIkTbli7Ns6qnkozliUScs(Utilities.Callback r9, boolean r10, Boolean r11) {
        requestingLocation = false;
        if (r11.booleanValue() == true) goto L6;
        r9.run(null);
        return;
    L6:
        LocationManager r2 = (LocationManager) ApplicationLoader.applicationContext.getSystemService("location");
        List<String> r3 = r2.getProviders(true);
        int r4 = r3.size() - 1;
        Location r5 = null;
    L7:
        if (r4 < 0) goto L12;
        r5 = r2.getLastKnownLocation(r3.get(r4));
        if (r5 != null) goto L12;
        r4 = r4 - 1;
    L12:
        if (r5 != null) goto L28;
        if (r10 == false) goto L28;
        if (r2.isProviderEnabled("gps") == true) goto L32;
        Context r102 = LaunchActivity.instance;
        if (r102 != null) goto L19;
        r102 = ApplicationLoader.applicationContext;
    L19:
        if (r102 == null) goto L28;
        AlertDialog.Builder r112 = new AlertDialog.Builder(r102);     // Catch: Exception -> L22
        r112.setTopAnimation(R.raw.permission_request_location, 72, false, Theme.getColor(Theme.key_dialogTopBackground));     // Catch: Exception -> L22
        r112.setMessage(LocaleController.getString(R.string.GpsDisabledAlertText));     // Catch: Exception -> L22
        r112.setPositiveButton(LocaleController.getString(R.string.Enable), new Weather$$ExternalSyntheticLambda6(r102));     // Catch: Exception -> L22
        r112.setNegativeButton(LocaleController.getString(R.string.Cancel), null);     // Catch: Exception -> L22
        r112.show();     // Catch: Exception -> L22
    L22:
        e = move-exception;
        FileLog.e(e);
        goto L28
    L32:
        Utilities.Callback[] r103 = {r9};     // Catch: Exception -> L26
        LocationListener[] r32 = {null};     // Catch: Exception -> L26
        Runnable[] r113 = {null};     // Catch: Exception -> L26
        Weather$$ExternalSyntheticLambda7 r42 = new Weather$$ExternalSyntheticLambda7(r32, r2, r113);     // Catch: Exception -> L26
        Weather$$ExternalSyntheticLambda8 r7 = new Weather$$ExternalSyntheticLambda8(r42, r103);     // Catch: Exception -> L26
        Weather$$ExternalSyntheticLambda9 r8 = new Weather$$ExternalSyntheticLambda9(r42, r103);     // Catch: Exception -> L26
        r32[0] = r7;     // Catch: Exception -> L26
        r113[0] = r8;     // Catch: Exception -> L26
        r2.requestLocationUpdates("gps", 1000, 0.0f, r7);     // Catch: Exception -> L26
        AndroidUtilities.runOnUIThread(r8, 15000);     // Catch: Exception -> L26
        return;
    L26:
        e = move-exception;
        FileLog.e(e);
        r9.run(null);
        return;
    L28:
        r9.run(r5);
    }

    public static /* synthetic */ void $r8$lambda$giPJVZXgDvfv1Gjcv1pBtZl327Q(MessagesController r10, TLRPC.User[] r11, double r12, double r14, int[] r16, ConnectionsManager r17, Utilities.Callback r18, String r19) {
        TLRPC.TL_messages_getInlineBotResults r0 = new TLRPC.TL_messages_getInlineBotResults();
        r0.bot = r10.getInputUser(r11[0]);
        r0.query = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.offset = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.flags |= 1;
        TLRPC.TL_inputGeoPoint r102 = new TLRPC.TL_inputGeoPoint();
        r0.geo_point = r102;
        r102.lat = r12;
        r102._long = r14;
        r0.peer = new TLRPC.TL_inputPeerEmpty();
        r16[0] = r17.sendRequest(r0, new Weather$$ExternalSyntheticLambda12(r16, r18, r12, r14, r19));
    }

    public static /* synthetic */ void $r8$lambda$hk9az5DrJFjlueUS6FNBCLlJ_t4(int[] r1, TLObject r2, MessagesController r3, TLRPC.User[] r4, Runnable r5, Utilities.Callback r6) {
        r1[0] = 0;
        if ((r2 instanceof TLRPC.TL_contacts_resolvedPeer) == false) goto L8;
        TLRPC.TL_contacts_resolvedPeer r22 = (TLRPC.TL_contacts_resolvedPeer) r2;
        r3.putUsers(r22.users, false);
        r3.putChats(r22.chats, false);
        TLRPC.User r12 = r3.getUser(Long.valueOf(DialogObject.getPeerDialogId(r22.peer)));
        r4[0] = r12;
        if (r12 == null) goto L8;
        r5.run();
        return;
    L8:
        r6.run(getCached());
    }

    /* renamed from: $r8$lambda$kQ-EEFmTKZQS0Ia-XdAEjQLsMnw, reason: not valid java name */
    public static /* synthetic */ void m20594$r8$lambda$kQEEFmTKZQS0IaXdAEjQLsMnw(int[] r3, ConnectionsManager r4) {
        int r1 = r3[0];
        if (r1 == 0) goto L6;
        r4.cancelRequest(r1, true);
        r3[0] = 0;
        return;
    }

    /* renamed from: $r8$lambda$lwBNB9yWTZ_sQ-q5Y12nEz-3hF4, reason: not valid java name */
    public static /* synthetic */ void m20595$r8$lambda$lwBNB9yWTZ_sQq5Y12nEz3hF4(int r0, int r1, Object[] r2) {
        if (r0 == NotificationCenter.pillStackSettingsChanged) goto L5;
        return;
    L5:
        if (PillStackConfig.shouldUpdatePill(r2, new int[]{PillType.WEATHER.getId()}) == false) goto L10;
        clearCache();
        return;
    }

    public static /* synthetic */ void $r8$lambda$pYcAfzKADThXEZeQ0_S7R5cfPv4(int[] r1, TLObject r2, Utilities.Callback r3, double r4, double r6, String r8) {
        r1[0] = 0;
        if ((r2 instanceof TLRPC.messages_BotResults) == false) goto L12;
        TLRPC.messages_BotResults r22 = (TLRPC.messages_BotResults) r2;
        if (r22.results.isEmpty() == true) goto L12;
        TLRPC.BotInlineResult r12 = r22.results.get(0);
        String r23 = r12.title;
        float r13 = Float.parseFloat(r12.description);     // Catch: Exception -> L10
        State r0 = new State();
        r0.lat = r4;
        r0.lng = r6;
        r0.emoji = r23;
        r0.temperature = r13;
        saveCache(r8, r0);
        r3.run(r0);
        return;
    L10:
        r3.run(getCached());
        return;
    L12:
        r3.run(getCached());
    }

    /* renamed from: $r8$lambda$t3ZkH-izLhHzQ8gS5sVl0ruSWok, reason: not valid java name */
    public static /* synthetic */ void m20596$r8$lambda$t3ZkHizLhHzQ8gS5sVl0ruSWok(Utilities.Callback r5, boolean r6, Location r7) {
        AlertDialog r0 = null;
        if (r7 != null) goto L6;
        r5.run(null);
        return;
    L6:
        Activity r1 = LaunchActivity.instance;
        if (r1 != null) goto L9;
        r1 = AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
    L9:
        if (r1 != null) goto L11;
    L22:
        r5.run(null);
        return;
    L11:
        if (r1.isFinishing() == true) goto L22;
        if (r6 == false) goto L15;
        r0 = new AlertDialog(r1, 3, new DarkThemeResourceProvider());
    L15:
        if (r6 == false) goto L17;
        r0.showDelayed(200);
    L17:
        Runnable r52 = fetch(r7.getLatitude(), r7.getLongitude(), new Weather$$ExternalSyntheticLambda10(r6, r0, r5));
        if (r6 == false) goto L24;
        if (r52 == null) goto L25;
        r0.setOnCancelListener(new Weather$$ExternalSyntheticLambda11(r52));
        return;
    L25:
        return;
    }

    /* renamed from: $r8$lambda$vV-hMXNvxnYiRF_qAQBPejR2K5g, reason: not valid java name */
    public static /* synthetic */ void m20597$r8$lambda$vVhMXNvxnYiRF_qAQBPejR2K5g(int[] r3, Utilities.Callback r4, double r5, double r7, String r9, TLObject r10, TLRPC.TL_error r11) {
        AndroidUtilities.runOnUIThread(new Weather$$ExternalSyntheticLambda14(r3, r10, r4, r5, r7, r9));
    }

    /* renamed from: $r8$lambda$x1i-tkN7KveLOXacSOqWbhVGf2I, reason: not valid java name */
    public static /* synthetic */ void m20598$r8$lambda$x1itkN7KveLOXacSOqWbhVGf2I(Runnable r2, Utilities.Callback[] r3) {
        r2.run();
        Utilities.Callback r0 = r3[0];
        if (r0 == null) goto L6;
        r0.run(null);
        r3[0] = null;
        return;
    }

    static {
        NotificationCenter.getGlobalInstance().addObserver(new Weather$$ExternalSyntheticLambda0(), NotificationCenter.pillStackSettingsChanged);
    }

    public static void clearCache() {
        cacheKey = null;
    }

    public static Runnable fetch(double r12, double r14, Utilities.Callback<State> r16) {
        if (r16 != null) goto L5;
        return null;
    L5:
        Date r1 = new Date();
        Calendar r2 = Calendar.getInstance(DesugarTimeZone.getTimeZone("UTC"));
        r2.setTime(r1);
        String r10 = Math.round(r12 * 1000.0d) + ":" + Math.round(1000.0d * r14) + "at" + (((r2.getTimeInMillis() / 1000) / 60) / 60);
        State r13 = getCached();
        if (r13 != null) goto L8;
    L11:
        int[] r15 = new int[1];
        MessagesController r22 = MessagesController.getInstance(UserConfig.selectedAccount);
        ConnectionsManager r8 = ConnectionsManager.getInstance(UserConfig.selectedAccount);
        String r11 = r22.weatherSearchUsername;
        TLRPC.User[] r3 = {r22.getUser(r11)};
        Weather$$ExternalSyntheticLambda3 r0 = new Weather$$ExternalSyntheticLambda3(r22, r3, r12, r14, r15, r8, r16, r10);
        if (r3[0] != null) goto L14;
        TLRPC.TL_contacts_resolveUsername r132 = new TLRPC.TL_contacts_resolveUsername();
        r132.username = r11;
        r15[0] = r8.sendRequest(r132, new Weather$$ExternalSyntheticLambda4(r15, r22, r3, r0, r16));
    L16:
        return new Weather$$ExternalSyntheticLambda5(r15, r8);
    L14:
        r0.run();
        goto L16
    L8:
        if (TextUtils.equals(cacheKey, r10) == false) goto L11;
        r16.run(r13);
        return null;
    }

    public static void fetch(boolean r1, Utilities.Callback<State> r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        getUserLocation(r1, new Weather$$ExternalSyntheticLambda2(r2, r1));
    }

    public static void fetchExtera(Utilities.Callback<State> r7) {
        if (PillStackConfig.getUseCurrentLocation() == true) goto L5;
        double r1 = 55.7558d;
        double r3 = 37.6173d;
        if (PillStackConfig.getCustomWeatherLocation() != null) goto L16;
    L13:
        fetch(r1, r3, r7);
        return;
    L16:
        TLRPC.GeoPoint r0 = (TLRPC.GeoPoint) ExteraConfig.getGSON().fromJson(PillStackConfig.getCustomWeatherLocation(), TLRPC.TL_geoPoint.class);     // Catch: Exception -> L15
        r1 = r0.lat;     // Catch: Exception -> L15
        r3 = r0._long;     // Catch: Exception -> L15
        goto L13
    L5:
        if (isLocationPermissionGranted() == true) goto L8;
        r7.run(null);
        return;
    L8:
        fetch(false, r7);
    }

    public static State getCached() {
        if (cacheValue != null) goto L11;
        String r0 = PillStackConfig.getPreferences().getString("weatherCacheKey", null);     // Catch: Exception -> L8
        String r1 = PillStackConfig.getPreferences().getString("weatherCacheValue", null);     // Catch: Exception -> L8
        if (r1 == null) goto L11;
        cacheKey = r0;     // Catch: Exception -> L8
        cacheValue = (State) ExteraConfig.getGSON().fromJson(r1, State.class);     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
    L11:
        return cacheValue;
    }

    @SuppressLint({"MissingPermission"})
    public static void getUserLocation(boolean r5, Utilities.Callback<Location> r6) {
        if (r6 != null) goto L5;
        return;
    L5:
        if (requestingLocation == false) goto L8;
        r6.run(null);
        return;
    L8:
        requestingLocation = true;
        PermissionRequest.ensureEitherPermission(R.raw.permission_request_location, R.string.WeatherLocationPermissionNo, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, new String[]{"android.permission.ACCESS_COARSE_LOCATION"}, new Weather$$ExternalSyntheticLambda1(r6, r5));
    }

    public static boolean isDefaultCelsius() {
        String r0 = TimeZone.getDefault().getID();
        if (r0.startsWith("US/") == false) goto L5;
        return false;
    L5:
        if ("America/Nassau".equals(r0) == false) goto L7;
        return false;
    L7:
        if ("America/Belize".equals(r0) == false) goto L9;
        return false;
    L9:
        if ("America/Cayman".equals(r0) == false) goto L11;
        return false;
    L11:
        if ("Pacific/Palau".equals(r0) == true) goto L19;
        return true;
    L19:
        return false;
    }

    public static boolean isLocationEnabled() {
        LocationManager r1 = (LocationManager) ApplicationLoader.applicationContext.getSystemService("location");     // Catch: Exception -> L10
        if (r1 != null) goto L7;
        return false;
    L7:
        if (Build.VERSION.SDK_INT < 28) goto L13;
        return r1.isLocationEnabled();
    L13:
        if (r1.isProviderEnabled("gps") == false) goto L15;
        return true;
    L15:
        if (r1.isProviderEnabled("network") == false) goto L17;
        return true;
    L17:
        if (r1.isProviderEnabled("passive") == true) goto L26;
        return false;
    L26:
        return true;
    L10:
        e = move-exception;
        FileLog.e(e);
        return false;
    }

    public static boolean isLocationPermissionGranted() {
        Context r0 = ApplicationLoader.applicationContext;
        if (r0.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) goto L5;
        return true;
    L5:
        if (r0.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) goto L11;
        return false;
    L11:
        return true;
    }

    private static void saveCache(String r2, State r3) {
        if (r3 != null) goto L9;
        return;
    L9:
        cacheKey = r2;     // Catch: Exception -> L6
        cacheValue = r3;     // Catch: Exception -> L6
        String r32 = ExteraConfig.getGSON().toJson(r3);     // Catch: Exception -> L6
        PillStackConfig.getEditor().putString("weatherCacheKey", r2).apply();     // Catch: Exception -> L6
        PillStackConfig.getEditor().putString("weatherCacheValue", r32).apply();     // Catch: Exception -> L6
        return;
    L6:
        e = move-exception;
        FileLog.e(e);
    }
}
