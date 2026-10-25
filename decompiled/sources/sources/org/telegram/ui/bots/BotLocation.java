package org.telegram.ui.bots;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.util.Pair;
import android.view.View;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AttachableDrawable;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.PermissionRequest;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class BotLocation {
    private static final HashMap<Pair<Integer, Long>, BotLocation> instances = null;
    public final long botId;
    public final Context context;
    public final int currentAccount;
    public boolean granted;
    private final HashSet<Runnable> listeners;
    public boolean requested;

    /* renamed from: org.telegram.ui.bots.BotLocation$1, reason: invalid class name */
    public class AnonymousClass1 implements LocationListener {
        final /* synthetic */ BotLocation this$0;
        final /* synthetic */ LocationListener[] val$listener;
        final /* synthetic */ LocationManager val$lm;
        final /* synthetic */ Utilities.Callback val$whenDone;

        public AnonymousClass1(BotLocation r1, LocationManager r2, LocationListener[] r3, Utilities.Callback r4) {
            this.this$0 = r1;
            this.val$lm = r2;
            this.val$listener = r3;
            this.val$whenDone = r4;
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location r4) {
            this.val$lm.removeUpdates(this.val$listener[0]);
            this.val$whenDone.run(BotLocation.m21324$$Nest$mlocationObject(this.this$0, r4));
        }
    }

    public static class BotUserLocationDrawable extends Drawable implements AttachableDrawable {
        private final Paint arrowPaint;
        private final Paint bgPaint;
        private final ImageReceiver botImageReceiver;
        private final Drawable locationDrawable;
        private final RectF rect;
        private final ImageReceiver userImageReceiver;
        private final Paint whitePaint;

        public BotUserLocationDrawable(Context r7, TLRPC.User r8, TLRPC.User r9) {
            Paint r0 = new Paint(1);
            this.arrowPaint = r0;
            this.bgPaint = new Paint(1);
            Paint r2 = new Paint(1);
            this.whitePaint = r2;
            ImageReceiver r1 = new ImageReceiver();
            this.userImageReceiver = r1;
            ImageReceiver r3 = new ImageReceiver();
            this.botImageReceiver = r3;
            this.rect = new RectF();
            r0.setColor(-1);
            r0.setStyle(Paint.Style.STROKE);
            r0.setStrokeWidth(AndroidUtilities.dp(2.0f));
            r0.setStrokeJoin(Paint.Join.ROUND);
            r0.setStrokeCap(Paint.Cap.ROUND);
            r2.setColor(-1);
            Drawable r72 = r7.getResources().getDrawable(R.drawable.filled_location).mutate();
            this.locationDrawable = r72;
            r72.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogTopBackground), PorterDuff.Mode.SRC_IN));
            AvatarDrawable r6 = new AvatarDrawable();
            r6.setInfo(r8);
            r1.setForUserOrChat(r8, r6);
            r1.setRoundRadius(AndroidUtilities.dp(25.0f));
            AvatarDrawable r73 = new AvatarDrawable();
            r73.setInfo(r9);
            r3.setForUserOrChat(r9, r73);
            r3.setRoundRadius(AndroidUtilities.dp(25.0f));
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r14) {
            Rect r7 = getBounds();
            this.bgPaint.setColor(Theme.getColor(Theme.key_dialogTopBackground));
            float r8 = AndroidUtilities.dp(136.0f) / 2.0f;
            this.userImageReceiver.setImageCoords(r7.centerX() - r8, r7.centerY() - AndroidUtilities.dp(25.0f), AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f));
            this.userImageReceiver.draw(r14);
            float r2 = (r7.centerX() - r8) + AndroidUtilities.dp(41.0f);
            float r3 = r7.centerY() + AndroidUtilities.dp(16.0f);
            r14.drawCircle(r2, r3, AndroidUtilities.dp(14.0f), this.bgPaint);
            r14.drawCircle(r2, r3, AndroidUtilities.dp(12.0f), this.whitePaint);
            this.locationDrawable.setBounds((int) (r2 - AndroidUtilities.dp(9.0f)), (int) (r3 - AndroidUtilities.dp(9.0f)), (int) (r2 + AndroidUtilities.dp(9.0f)), (int) (r3 + AndroidUtilities.dp(9.0f)));
            this.locationDrawable.draw(r14);
            r14.drawLine(r7.centerX() - AndroidUtilities.dp(3.33f), r7.centerY() - AndroidUtilities.dp(7.0f), r7.centerX() + AndroidUtilities.dp(3.33f), r7.centerY(), this.arrowPaint);
            r14.drawLine(r7.centerX() - AndroidUtilities.dp(3.33f), r7.centerY() + AndroidUtilities.dp(7.0f), r7.centerX() + AndroidUtilities.dp(3.33f), r7.centerY(), this.arrowPaint);
            this.botImageReceiver.setImageCoords((r7.centerX() + r8) - AndroidUtilities.dp(50.0f), r7.centerY() - AndroidUtilities.dp(25.0f), AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f));
            this.botImageReceiver.draw(r14);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // org.telegram.ui.Components.AttachableDrawable
        public void onAttachedToWindow(ImageReceiver r1) {
            this.userImageReceiver.onAttachedToWindow();
            this.botImageReceiver.onAttachedToWindow();
        }

        @Override // org.telegram.ui.Components.AttachableDrawable
        public void onDetachedFromWindow(ImageReceiver r1) {
            this.userImageReceiver.onDetachedFromWindow();
            this.botImageReceiver.onDetachedFromWindow();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }

        @Override // org.telegram.ui.Components.AttachableDrawable
        public void setParent(View r2) {
            this.botImageReceiver.setParentView(r2);
            this.userImageReceiver.setParentView(r2);
        }
    }

    public static /* synthetic */ void $r8$lambda$8Xj70piI7CK4FZg3bmehdrwtO2k(Activity r1, AlertDialog r2, int r3) {
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        r1.startActivity(r32);     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$Cs6EYv90_2jY_nwBZVrPOxzCmwQ(BotLocation r0, Runnable r1, int[] r2) {
        r0.lambda$setGranted$1(r1, r2);
    }

    /* renamed from: $r8$lambda$E6uO6k12hl9-pxGJdT2i3ulJdmE, reason: not valid java name */
    public static /* synthetic */ void m21322$r8$lambda$E6uO6k12hl9pxGJdT2i3ulJdmE(BotLocation r0, Utilities.Callback2 r1, int[] r2) {
        r0.lambda$request$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NrUSfLmt5mpXY0LtaVefOUiGIzk(Activity r1, boolean[] r2, Utilities.Callback2 r3, AlertDialog r4, int r5) {
        Intent r52 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L5
        r52.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L5
        r1.startActivity(r52);     // Catch: Exception -> L5
    L7:
        r2[0] = true;
        if (r3 == null) goto L13;
        Boolean r12 = Boolean.FALSE;
        r3.run(r12, r12);
        return;
    L13:
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$SHFj6JBjPYOxo3tZDVtqZS6gYNY(BotLocation r0, Runnable r1, AlertDialog r2, int r3) {
        r0.lambda$setGranted$3(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$fL8GKJuXgOm8W80t5JGMMl9sxC4(BotLocation r0, Runnable r1, AlertDialog r2, int r3) {
        r0.lambda$setGranted$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$lgcxrN8MzSUAmiPmj8ePr94QpqA(BotLocation r0, boolean[] r1, Utilities.Callback2 r2, DialogInterface r3) {
        r0.lambda$request$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$qJHgkl1tPqRlCtQ2sSNQxurrX1Y(BotLocation r0, boolean[] r1, Utilities.Callback2 r2, AlertDialog r3, int r4) {
        r0.lambda$request$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$xMdacKvXihdvFUeezED7uvxOYbE(Context r0, AlertDialog r1, int r2) {
        r0.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));     // Catch: Exception -> L4
        return;
    }

    /* renamed from: $r8$lambda$xn5ekFxvkPkdPXxVVVv-qdsdF8I, reason: not valid java name */
    public static /* synthetic */ void m21323$r8$lambda$xn5ekFxvkPkdPXxVVVvqdsdF8I(BotLocation r0, boolean[] r1, Utilities.Callback2 r2, AlertDialog r3, int r4) {
        r0.lambda$request$7(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mlocationObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ JSONObject m21324$$Nest$mlocationObject(BotLocation r0, Location r1) {
        return r0.locationObject(r1);
    }

    static {
        instances = new HashMap();
    }

    private BotLocation(Context r2, int r3, long r4) {
        this.listeners = new HashSet();
        this.context = r2;
        this.currentAccount = r3;
        this.botId = r4;
        load();
    }

    private boolean appHasPermission() {
        Activity r1 = getActivity();
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) goto L7;
        return true;
    L7:
        if (r1.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) goto L13;
        return true;
    L13:
        return false;
    }

    public static void clear() {
        Context r0 = ApplicationLoader.applicationContext;
        if (r0 != null) goto L5;
        return;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= 16) goto L9;
        r0.getSharedPreferences("botlocation_" + r2, 0).edit().clear().apply();
        r2 = r2 + 1;
        goto L7
    L9:
        instances.clear();
    }

    private boolean deviceHasLocation() {
        if (getActivity() != null) goto L5;
        return false;
    L5:
        if (getActivity().getPackageManager().hasSystemFeature("android.hardware.location.gps") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static BotLocation get(Context r3, int r4, long r5) {
        Pair<Integer, Long> r0 = new Pair(Integer.valueOf(r4), Long.valueOf(r5));
        HashMap<Pair<Integer, Long>, BotLocation> r1 = instances;
        BotLocation r2 = r1.get(r0);
        if (r2 != null) goto L6;
        BotLocation r22 = new BotLocation(r3, r4, r5);
        r1.put(r0, r22);
        return r22;
    L6:
        return r2;
    }

    private Activity getActivity() {
        Activity r0 = LaunchActivity.instance;
        if (r0 != null) goto L5;
        r0 = AndroidUtilities.findActivity(this.context);
    L5:
        if (r0 == null) goto L7;
        return r0;
    L7:
        return AndroidUtilities.findActivity(ApplicationLoader.applicationContext);
    }

    private /* synthetic */ void lambda$request$5(Utilities.Callback2 r5, int[] r6) {
        int r0 = 0;
        boolean r1 = false;
    L4:
        if (r0 >= r6.length) goto L9;
        if (r6[r0] != 0) goto L8;
        r1 = true;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        this.requested = true;
        this.granted = true;
        save();
        Iterator<Runnable> r4 = this.listeners.iterator();
    L11:
        if (r4.hasNext() == false) goto L13;
        r4.next().run();
        goto L11
    L13:
        if (r5 == null) goto L20;
        r5.run(Boolean.TRUE, Boolean.valueOf(r1));
        return;
    }

    private /* synthetic */ void lambda$request$6(boolean[] r1, Utilities.Callback2 r2, AlertDialog r3, int r4) {
        r1[0] = true;
        if (appHasPermission() == true) goto L6;
        PermissionRequest.requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, new BotLocation$$ExternalSyntheticLambda8(this, r2));
        return;
    L6:
        this.requested = true;
        this.granted = true;
        save();
        Iterator<Runnable> r0 = this.listeners.iterator();
    L8:
        if (r0.hasNext() == false) goto L10;
        r0.next().run();
        goto L8
    L10:
        if (r2 == null) goto L14;
        Boolean r02 = Boolean.TRUE;
        r2.run(r02, r02);
        return;
    }

    private /* synthetic */ void lambda$request$7(boolean[] r1, Utilities.Callback2 r2, AlertDialog r3, int r4) {
        if (r1[0] == true) goto L14;
        r1[0] = true;
        this.requested = true;
        this.granted = false;
        save();
        Iterator<Runnable> r0 = this.listeners.iterator();
    L7:
        if (r0.hasNext() == false) goto L9;
        r0.next().run();
        goto L7
    L9:
        if (r2 == null) goto L13;
        r2.run(Boolean.TRUE, Boolean.FALSE);
        return;
    L13:
        return;
    }

    private /* synthetic */ void lambda$request$8(boolean[] r3, Utilities.Callback2 r4, DialogInterface r5) {
        if (r3[0] == true) goto L13;
        this.requested = true;
        this.granted = false;
        save();
        Iterator<Runnable> r2 = this.listeners.iterator();
    L6:
        if (r2.hasNext() == false) goto L8;
        r2.next().run();
        goto L6
    L8:
        r3[0] = true;
        if (r4 == null) goto L14;
        r4.run(Boolean.TRUE, Boolean.FALSE);
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$setGranted$1(Runnable r4, int[] r5) {
        int r0 = 0;
        boolean r1 = false;
    L4:
        if (r0 >= r5.length) goto L9;
        if (r5[r0] != 0) goto L8;
        r1 = true;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        this.requested = r1;
        this.granted = r1;
        save();
        Iterator<Runnable> r3 = this.listeners.iterator();
    L11:
        if (r3.hasNext() == false) goto L13;
        r3.next().run();
        goto L11
    L13:
        if (r4 == null) goto L20;
        r4.run();
        return;
    }

    private /* synthetic */ void lambda$setGranted$2(Runnable r1, AlertDialog r2, int r3) {
        if (appHasPermission() == true) goto L6;
        PermissionRequest.requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, new BotLocation$$ExternalSyntheticLambda9(this, r1));
        return;
    L6:
        this.requested = true;
        this.granted = true;
        save();
        Iterator<Runnable> r0 = this.listeners.iterator();
    L8:
        if (r0.hasNext() == false) goto L10;
        r0.next().run();
        goto L8
    }

    private /* synthetic */ void lambda$setGranted$3(Runnable r1, AlertDialog r2, int r3) {
        this.requested = true;
        this.granted = false;
        save();
        Iterator<Runnable> r0 = this.listeners.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().run();
        goto L4
    L6:
        if (r1 == null) goto L10;
        r1.run();
        return;
    }

    private JSONObject locationObject(Location r7) {
        JSONObject r6 = new JSONObject();
        if (r7 == null) goto L6;
        boolean r1 = true;
    L36:
        r6.put("available", r1);     // Catch: Exception -> L14
        if (r7 != null) goto L10;
        return r6;
    L10:
        r6.put("latitude", r7.getLatitude());     // Catch: Exception -> L14
        r6.put("longitude", r7.getLongitude());     // Catch: Exception -> L14
        int r0 = Build.VERSION.SDK_INT;     // Catch: Exception -> L14
        if (r0 < 26) goto L16;
        r6.put("horizontal_accuracy", r7.getAccuracy());     // Catch: Exception -> L14
    L17:
        r6.put("altitude", r7.getAltitude());     // Catch: Exception -> L14
        if (r0 < 26) goto L21;
        r6.put("vertical_accuracy", r7.getVerticalAccuracyMeters());     // Catch: Exception -> L14
    L22:
        r6.put("course", r7.getBearing());     // Catch: Exception -> L14
        if (r0 < 26) goto L26;
        r6.put("course_accuracy", r7.getBearingAccuracyDegrees());     // Catch: Exception -> L14
    L27:
        r6.put("speed", r7.getSpeed());     // Catch: Exception -> L14
        if (r0 < 26) goto L32;
        r6.put("speed_accuracy", r7.getSpeedAccuracyMetersPerSecond());     // Catch: Exception -> L14
        return r6;
    L32:
        r6.put("speed_accuracy", null);     // Catch: Exception -> L14
        return r6;
    L26:
        r6.put("course_accuracy", null);     // Catch: Exception -> L14
        goto L27
    L21:
        r6.put("vertical_accuracy", null);     // Catch: Exception -> L14
        goto L22
    L16:
        r6.put("horizontal_accuracy", null);     // Catch: Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
        return r6;
    L6:
        r1 = false;
        goto L36
    }

    private boolean needToOpenSettings() {
        Activity r2 = getActivity();
        if (r2 != null) goto L6;
        return false;
    L6:
        if (r2.shouldShowRequestPermissionRationale("android.permission.ACCESS_COARSE_LOCATION") == true) goto L8;
        return true;
    L8:
        if (r2.shouldShowRequestPermissionRationale("android.permission.ACCESS_FINE_LOCATION") == false) goto L13;
        return false;
    L13:
        return true;
    }

    public boolean asked() {
        return this.requested;
    }

    public JSONObject checkObject() {
        JSONObject r0 = new JSONObject();
        r0.put("available", deviceHasLocation());     // Catch: Exception -> L12
        if (deviceHasLocation() == false) goto L16;
        r0.put("access_requested", this.requested);     // Catch: Exception -> L12
        if (this.requested == false) goto L16;
        if (this.granted == true) goto L10;
    L14:
        boolean r3 = false;
    L15:
        r0.put("access_granted", r3);     // Catch: Exception -> L12
        goto L16
    L10:
        if (appHasPermission() == false) goto L14;
        r3 = true;
    L16:
        return r0;
    L12:
        e = move-exception;
        FileLog.e(e);
        return r0;
    }

    public boolean granted() {
        if (appHasPermission() == true) goto L5;
        return false;
    L5:
        if (this.granted == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void listen(Runnable r1) {
        this.listeners.add(r1);
    }

    public void load() {
        SharedPreferences r0 = this.context.getSharedPreferences("botlocation_" + this.currentAccount, 0);
        this.requested = r0.getBoolean(this.botId + "_requested", false);
        boolean r02 = r0.getBoolean(this.botId + "_granted", false);
        this.granted = r02;
        if (r02 == true) goto L5;
        return;
    L5:
        if (appHasPermission() == true) goto L12;
        this.granted = false;
        this.requested = false;
        save();
        Iterator<Runnable> r5 = this.listeners.iterator();
    L8:
        if (r5.hasNext() == false) goto L13;
        r5.next().run();
        goto L8
    L13:
        return;
    }

    public void request(Utilities.Callback2<Boolean, Boolean> r8) {
        Activity r0 = getActivity();
        if (r0 != null) goto L6;
        return;
    L6:
        if (deviceHasLocation() == true) goto L11;
        if (r8 == null) goto L28;
        Boolean r7 = Boolean.FALSE;
        r8.run(r7, r7);
        return;
    L28:
        return;
    L11:
        if (appHasPermission() == true) goto L13;
    L20:
        boolean[] r1 = new boolean[1];
        TLRPC.User r2 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId));
        AlertDialog.Builder r3 = new AlertDialog.Builder(r0, null);
        r3.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotLocationPermissionRequest, new Object[]{UserObject.getUserName(r2), UserObject.getUserName(r2)})));
        r3.setTopImage(new BotUserLocationDrawable(this.context, UserConfig.getInstance(this.currentAccount).getCurrentUser(), r2), Theme.getColor(Theme.key_dialogTopBackground));
        if (appHasPermission() == false) goto L23;
    L25:
        r3.setPositiveButton(LocaleController.getString(R.string.BotLocationPermissionAllow), new BotLocation$$ExternalSyntheticLambda5(this, r1, r8));
    L26:
        r3.setNegativeButton(LocaleController.getString(R.string.BotLocationPermissionDecline), new BotLocation$$ExternalSyntheticLambda6(this, r1, r8));
        r3.setOnDismissListener(new BotLocation$$ExternalSyntheticLambda7(this, r1, r8));
        r3.show();
        return;
    L23:
        if (needToOpenSettings() == false) goto L25;
        r3.setPositiveButton(LocaleController.getString(R.string.BotLocationPermissionSettings), new BotLocation$$ExternalSyntheticLambda4(r0, r1, r8));
        goto L26
    L13:
        if (this.requested == false) goto L15;
    L17:
        if (r8 == null) goto L29;
        r8.run(Boolean.FALSE, Boolean.TRUE);
        return;
    L29:
        return;
    L15:
        if (this.granted == true) goto L17;
        goto L17
    }

    public void requestObject(Utilities.Callback<JSONObject> r11) {
        if (r11 != null) goto L4;
        return;
    L4:
        JSONObject r1 = new JSONObject();
        if (this.granted == true) goto L7;
    L45:
        r1.put("available", false);     // Catch: Exception -> L39
    L41:
        r11.run(r1);
        return;
    L39:
        e = move-exception;
        FileLog.e(e);
        goto L41
    L7:
        if (appHasPermission() == false) goto L45;
        if (deviceHasLocation() == false) goto L45;
        LocationManager r3 = (LocationManager) ApplicationLoader.applicationContext.getSystemService("location");
        List<String> r12 = r3.getProviders(true);
        int r4 = r12.size() - 1;
        Location r5 = null;
    L11:
        if (r4 < 0) goto L16;
        r5 = r3.getLastKnownLocation(r12.get(r4));
        if (r5 != null) goto L16;
        r4 = r4 - 1;
    L16:
        if (r5 == null) goto L18;
    L29:
        if (r5 == null) goto L47;
        r11.run(locationObject(r5));
        return;
    L47:
        AnonymousClass1 r8 = new AnonymousClass1(this, r3, new LocationListener[]{r8}, r11);     // Catch: Exception -> L34
        r3.requestLocationUpdates("gps", 1, 0.0f, r8);     // Catch: Exception -> L34
        return;
    L34:
        e = move-exception;
        FileLog.e(e);
        r11.run(locationObject(null));
        return;
    L18:
        if (r3.isProviderEnabled("gps") == true) goto L29;
        Context r0 = LaunchActivity.instance;
        if (r0 != null) goto L22;
        r0 = ApplicationLoader.applicationContext;
    L22:
        if (r0 != null) goto L43;
    L27:
        r11.run(locationObject(null));
        return;
    L43:
        AlertDialog.Builder r13 = new AlertDialog.Builder(r0);     // Catch: Exception -> L25
        r13.setTopAnimation(R.raw.permission_request_location, 72, false, Theme.getColor(Theme.key_dialogTopBackground));     // Catch: Exception -> L25
        r13.setMessage(LocaleController.getString(R.string.GpsDisabledAlertText));     // Catch: Exception -> L25
        r13.setPositiveButton(LocaleController.getString(R.string.Enable), new BotLocation$$ExternalSyntheticLambda3(r0));     // Catch: Exception -> L25
        r13.setNegativeButton(LocaleController.getString(R.string.Cancel), null);     // Catch: Exception -> L25
        r13.show();     // Catch: Exception -> L25
    L25:
        e = move-exception;
        FileLog.e(e);
        goto L27
    }

    public void save() {
        SharedPreferences.Editor r0 = this.context.getSharedPreferences("botlocation_" + this.currentAccount, 0).edit();
        r0.putBoolean(this.botId + "_granted", this.granted);
        r0.putBoolean(this.botId + "_requested", this.requested);
        r0.apply();
    }

    public void setGranted(boolean r6, Runnable r7) {
        this.requested = true;
        if (r6 == true) goto L5;
    L14:
        this.granted = r6;
        Iterator<Runnable> r62 = this.listeners.iterator();
    L16:
        if (r62.hasNext() == false) goto L18;
        r62.next().run();
        goto L16
    L18:
        if (r7 == null) goto L20;
        r7.run();
    L20:
        save();
        return;
    L5:
        if (appHasPermission() == true) goto L14;
        Activity r63 = getActivity();
        if (r63 != null) goto L9;
        return;
    L9:
        TLRPC.User r0 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.botId));
        AlertDialog.Builder r1 = new AlertDialog.Builder(getActivity(), null);
        r1.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotLocationPermissionRequest, new Object[]{UserObject.getUserName(r0), UserObject.getUserName(r0)})));
        r1.setTopImage(new BotUserLocationDrawable(this.context, UserConfig.getInstance(this.currentAccount).getCurrentUser(), r0), Theme.getColor(Theme.key_dialogTopBackground));
        if (needToOpenSettings() == false) goto L12;
        r1.setPositiveButton(LocaleController.getString(R.string.BotLocationPermissionSettings), new BotLocation$$ExternalSyntheticLambda0(r63));
    L13:
        r1.setNegativeButton(LocaleController.getString(R.string.BotLocationPermissionDecline), new BotLocation$$ExternalSyntheticLambda2(this, r7));
        r1.show();
        goto L20
    L12:
        r1.setPositiveButton(LocaleController.getString(R.string.BotLocationPermissionAllow), new BotLocation$$ExternalSyntheticLambda1(this, r7));
        goto L13
    }

    public void unlisten(Runnable r1) {
        this.listeners.remove(r1);
    }
}
