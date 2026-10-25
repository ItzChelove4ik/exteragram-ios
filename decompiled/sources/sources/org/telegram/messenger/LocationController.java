package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.SparseIntArray;
import androidx.collection.LongSparseArray;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.ILocationServiceProvider;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.Components.PermissionRequest;

@SuppressLint({"MissingPermission"})
/* loaded from: classes.dex */
public class LocationController extends BaseController implements NotificationCenter.NotificationCenterDelegate, ILocationServiceProvider.IAPIConnectionCallbacks, ILocationServiceProvider.IAPIOnConnectionFailedListener {
    private static final int BACKGROUD_UPDATE_TIME = 30000;
    private static final long FASTEST_INTERVAL = 1000;
    private static final int FOREGROUND_UPDATE_TIME = 20000;
    private static volatile LocationController[] Instance = null;
    private static final int LOCATION_ACQUIRE_TIME = 10000;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final int SEND_NEW_LOCATION_TIME = 2000;
    public static final int TYPE_BIZ = 1;
    public static final int TYPE_STORY = 2;
    private static final long UPDATE_INTERVAL = 1000;
    private static final int WATCH_LOCATION_TIMEOUT = 65000;
    private static HashMap<LocationFetchCallback, Runnable> callbacks;
    public static String[] unnamedRoads;
    private ILocationServiceProvider.IMapApiClient apiClient;
    private LongSparseArray<Boolean> cacheRequests;
    private FusedLocationListener fusedLocationListener;
    private GpsLocationListener gpsLocationListener;
    private Location lastKnownLocation;
    private boolean lastLocationByMaps;
    private long lastLocationSendTime;
    private long lastLocationStartTime;
    private LongSparseArray<Integer> lastReadLocationTime;
    private long locationEndWatchTime;
    private LocationManager locationManager;
    private ILocationServiceProvider.ILocationRequest locationRequest;
    private boolean locationSentSinceLastMapUpdate;
    public LongSparseArray<ArrayList<TLRPC.Message>> locationsCache;
    private GpsLocationListener networkLocationListener;
    private GpsLocationListener passiveLocationListener;
    private SparseIntArray requests;
    private Boolean servicesAvailable;
    private ArrayList<SharingLocationInfo> sharingLocations;
    private LongSparseArray<SharingLocationInfo> sharingLocationsMap;
    private LongSparseArray<SharingLocationInfo> sharingLocationsMapUI;
    public ArrayList<SharingLocationInfo> sharingLocationsUI;
    private boolean started;
    private boolean wasConnectedToPlayServices;

    public class FusedLocationListener implements ILocationServiceProvider.ILocationListener {
        final /* synthetic */ LocationController this$0;

        private FusedLocationListener(LocationController r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ FusedLocationListener(LocationController r1, LocationControllerIA r2) {
            this(r1);
        }

        @Override // org.telegram.messenger.ILocationServiceProvider.ILocationListener
        public void onLocationChanged(Location r1) {
            if (r1 != null) goto L4;
            return;
        L4:
            LocationController.m4259$$Nest$msetLastKnownLocation(this.this$0, r1);
        }
    }

    public class GpsLocationListener implements LocationListener {
        final /* synthetic */ LocationController this$0;

        private GpsLocationListener(LocationController r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ GpsLocationListener(LocationController r1, LocationControllerIA r2) {
            this(r1);
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location r5) {
            if (r5 != null) goto L5;
            return;
        L5:
            if (LocationController.m4254$$Nest$fgetlastKnownLocation(this.this$0) != null) goto L7;
        L16:
            LocationController.m4259$$Nest$msetLastKnownLocation(this.this$0, r5);
            return;
        L7:
            if (this == LocationController.m4255$$Nest$fgetnetworkLocationListener(this.this$0)) goto L11;
            if (this != LocationController.m4256$$Nest$fgetpassiveLocationListener(this.this$0)) goto L16;
        L11:
            if (LocationController.m4257$$Nest$fgetstarted(this.this$0) == false) goto L13;
            return;
        L13:
            if (r5.distanceTo(LocationController.m4254$$Nest$fgetlastKnownLocation(this.this$0)) <= 20.0f) goto L19;
            LocationController.m4259$$Nest$msetLastKnownLocation(this.this$0, r5);
            LocationController.m4258$$Nest$fputlastLocationSendTime(this.this$0, SystemClock.elapsedRealtime() - 25000);
            return;
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String r1) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String r1) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String r1, int r2, Bundle r3) {
        }
    }

    /* loaded from: classes5.dex */
    public interface LocationFetchCallback {
        void onLocationAddressAvailable(String r1, String r2, TLRPC.TL_messageMediaVenue r3, TLRPC.TL_messageMediaVenue r4, Location r5);
    }

    /* loaded from: classes5.dex */
    public static class SharingLocationInfo {
        public int account;
        public long did;
        public int lastSentProximityMeters;
        public MessageObject messageObject;
        public int mid;
        public int period;
        public int proximityMeters;
        public int stopTime;

        public SharingLocationInfo() {
        }
    }

    public static /* synthetic */ void $r8$lambda$4DvdQw0py8Qu7xqzpqODuTUlatk(LocationController r0) {
        r0.lambda$cleanup$9();
    }

    public static /* synthetic */ void $r8$lambda$4YH2GTQKBiobnZSCDLNWYt4_8KA(LocationController r0, Integer r1) {
        r0.lambda$onConnected$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$5lvEcB4p2T9Q5wVWOtGiBoC1b70(LocationController r0) {
        r0.lambda$setProximityLocation$13();
    }

    /* renamed from: $r8$lambda$6Jh_ojb1r-CVk8VQkykWifV3H3Y, reason: not valid java name */
    public static /* synthetic */ void m4245$r8$lambda$6Jh_ojb1rCVk8VQkykWifV3H3Y(LocationController r0, SharingLocationInfo r1) {
        r0.lambda$broadcastLastKnownLocation$6(r1);
    }

    /* renamed from: $r8$lambda$8GrPan-yBjwzrn1SSdNIEjVSGJY, reason: not valid java name */
    public static /* synthetic */ void m4246$r8$lambda$8GrPanyBjwzrn1SSdNIEjVSGJY(LocationController r0, ArrayList r1) {
        r0.lambda$loadSharingLocations$15(r1);
    }

    /* renamed from: $r8$lambda$8phspgl9XnVUhi-I1NM3sbFuOEw, reason: not valid java name */
    public static /* synthetic */ void m4247$r8$lambda$8phspgl9XnVUhiI1NM3sbFuOEw(LocationFetchCallback r1, String r2, String r3, TLRPC.TL_messageMediaVenue r4, TLRPC.TL_messageMediaVenue r5, Location r6) {
        callbacks.remove(r1);
        r1.onLocationAddressAvailable(r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$JLlyc99CxTUrYLLyT-DIYhkbzCU, reason: not valid java name */
    public static /* synthetic */ void m4248$r8$lambda$JLlyc99CxTUrYLLyTDIYhkbzCU(LocationController r0, SharingLocationInfo r1, SharingLocationInfo r2) {
        r0.lambda$addSharingLocation$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JrE4zPRjY3xmcXD5eWXd5jr_C8o(LocationController r0, ArrayList r1) {
        r0.lambda$loadSharingLocations$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$LfvGKVY7kzSlbA31OgX1tCP2_jg(LocationController r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$NUWJvNuWIpVDCz8c-2qSV3OG4Zc, reason: not valid java name */
    public static /* synthetic */ void m4249$r8$lambda$NUWJvNuWIpVDCz8c2qSV3OG4Zc(LocationController r0, SharingLocationInfo r1, int[] r2, TLRPC.TL_messages_editMessage r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$broadcastLastKnownLocation$7(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$TuQEmL46iT-sw_YaouyznRXz6bM, reason: not valid java name */
    public static /* synthetic */ void m4250$r8$lambda$TuQEmL46iTsw_YaouyznRXz6bM(LocationController r0, SharingLocationInfo r1) {
        r0.lambda$update$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$VHxG0XNg4Dq02AR3zOLarHkO_qA(LocationController r0, int r1, SharingLocationInfo r2) {
        r0.lambda$saveSharingLocation$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Vlixib1Hi3PtggUhwbYD6uNIpcQ(LocationController r0, long r1, TLObject r3) {
        r0.lambda$loadLiveLocations$25(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$WLT64Kz9t07IJNybr2OoKYbFkLM(LocationController r0) {
        r0.lambda$removeAllLocationSharings$24();
    }

    public static /* synthetic */ void $r8$lambda$YJjgxzKpOd72q2ngozHzae0KF1c(LocationController r0, Integer r1) {
        r0.lambda$onConnected$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZQUKz_2xVjzHjZ64QCsbkIjhnSs(LocationController r0) {
        r0.lambda$removeAllLocationSharings$23();
    }

    public static /* synthetic */ void $r8$lambda$cPZuJZxMSoSGZrXsUPf7bimSs0A(LocationController r0, ArrayList r1, ArrayList r2, ArrayList r3) {
        r0.lambda$loadSharingLocations$16(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$eM7yAfNMTjnl_IafLz_gxdgphRk(LocationController r0, Integer r1) {
        r0.lambda$onConnected$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$fXuYP3wN9fO8KwYKayZgvrYhSMQ(LocationController r0, long r1) {
        r0.lambda$removeSharingLocation$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$fyW2o33Cdoz3tSvOefvolJa2yBk(LocationController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$markLiveLoactionsAsRead$27(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gV8ntrXleAXIEqTjOiWeetmjQSs(LocationController r0, SharingLocationInfo r1) {
        r0.lambda$removeSharingLocation$20(r1);
    }

    public static /* synthetic */ void $r8$lambda$hlwCjMaYnND9RyhdUsyzeiWAM5g(LocationController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$removeAllLocationSharings$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ilZSNF8nuc1aJ84H5Wc_CjZc4d0(LocationController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$removeSharingLocation$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$itCmQWYSBEb5k9jwGjODcyMYow8(LocationController r0) {
        r0.lambda$loadSharingLocations$17();
    }

    /* renamed from: $r8$lambda$j8uutrs12-o8ZIZ6nltkpQT5fNw, reason: not valid java name */
    public static /* synthetic */ void m4251$r8$lambda$j8uutrs12o8ZIZ6nltkpQT5fNw(LocationController r0, Location r1) {
        r0.setLastKnownLocation(r1);
    }

    public static /* synthetic */ void $r8$lambda$k9HQmbnGhTLQ8xd7PMTxl1f9Ba0(LocationController r0, boolean r1) {
        r0.lambda$startFusedLocationRequest$5(r1);
    }

    /* renamed from: $r8$lambda$lXKdOmA-jJPVMYuvQFFQDQ9nkjw, reason: not valid java name */
    public static /* synthetic */ void m4252$r8$lambda$lXKdOmAjJPVMYuvQFFQDQ9nkjw(LocationController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadLiveLocations$26(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$runN5gam4CIORWIzL4v0VW4xoGc(LocationController r0) {
        r0.lambda$onConnected$3();
    }

    public static /* synthetic */ void $r8$lambda$tXLSdVs9flIfYb3AuIg5sM6SKYk() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.newLocationAvailable, new Object[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v11 */
    /* JADX WARN: Type inference failed for: r16v15 */
    /* JADX WARN: Type inference failed for: r16v16 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r16v7 */
    /* JADX WARN: Type inference failed for: r16v8 */
    /* JADX WARN: Type inference failed for: r16v9 */
    public static /* synthetic */ void $r8$lambda$tlUU8lHFc_rWHayxFAniRRtUDs0(Locale r24, Location r25, int r26, Locale r27, LocationFetchCallback r28) {
        StringBuilder r5 = new StringBuilder();
        TL_stories.TL_geoPointAddress r6 = new TL_stories.TL_geoPointAddress();
        TL_stories.TL_geoPointAddress r7 = new TL_stories.TL_geoPointAddress();
        List<Address> r9 = new Geocoder(ApplicationLoader.applicationContext, r24).getFromLocation(r25.getLatitude(), r25.getLongitude(), 1);     // Catch: Exception -> L286
        if (r26 != 2) goto L11;
        if (r27 != r24) goto L357;
        List<Address> r2 = r9;
    L12:
        boolean r12 = true;
        if (r9.size() <= 0) goto L287;
        int r11 = 0;
        Address r92 = r9.get(0);     // Catch: Exception -> L286
        if (r2 != null) goto L17;
    L20:
        Address r22 = null;
    L22:
        if (r26 != 1) goto L62;
        ArrayList r0 = new ArrayList();     // Catch: Exception -> L10
        String r1 = r92.getAddressLine(0);     // Catch: Exception -> L26
    L28:
        if (TextUtils.isEmpty(r1) == true) goto L355;
        r0.add(r1);     // Catch: Exception -> L10
    L35:
        int r13 = 0;
    L37:
        if (r13 >= r0.size()) goto L48;
        if (r0.get(r13) == null) goto L46;
        String[] r23 = ((String) r0.get(r13)).split(", ");     // Catch: Exception -> L10
        if (r23.length <= 1) goto L46;
        r0.remove(r13);     // Catch: Exception -> L10
        int r4 = 0;
    L44:
        if (r4 >= r23.length) goto L46;
        r0.add(r13, r23[r4]);     // Catch: Exception -> L10
        r13 = r13 + 1;     // Catch: Exception -> L10
        r4 = r4 + 1;     // Catch: Exception -> L10
    L46:
        r13 = r13 + 1;     // Catch: Exception -> L10
    L48:
        if (r11 >= r0.size()) goto L58;
        if (TextUtils.isEmpty((CharSequence) r0.get(r11)) == false) goto L52;
    L55:
        r0.remove(r11);     // Catch: Exception -> L10
        r11 = r11 - 1;
    L56:
        r11 = r11 + 1;     // Catch: Exception -> L10
        goto L48
    L52:
        if (r0.indexOf(r0.get(r11)) != r11) goto L55;
        if (((String) r0.get(r11)).matches("^\\s*\\d{4,}\\s*$") == false) goto L56;
    L58:
        if (r0.isEmpty() == false) goto L60;
        String r02 = null;
    L61:
        String r14 = r02;
        String r29 = null;
        String r42 = null;
        String r8 = null;
        String r10 = null;
        boolean r16 = false;
        String r17 = null;
    L285:
        String r93 = r17;
        ?? r162 = r16;
    L293:
        if (TextUtils.isEmpty(r29) == true) goto L313;
        TLRPC.TL_messageMediaVenue r112 = new TLRPC.TL_messageMediaVenue();     // Catch: Exception -> L348
        TLRPC.TL_geoPoint r15 = new TLRPC.TL_geoPoint();     // Catch: Exception -> L344
        r112.geo = r15;     // Catch: Exception -> L344
        r15.lat = r25.getLatitude();     // Catch: Exception -> L344
        r112.geo._long = r25.getLongitude();     // Catch: Exception -> L344
        r112.query_id = -1;     // Catch: Exception -> L344
        r112.title = r29;     // Catch: Exception -> L344
        if (r12 == false) goto L299;
        String r210 = "https://ss3.4sqi.net/img/categories_v2/building/government_capitolbuilding_64.png";
    L300:
        r112.icon = r210;     // Catch: Exception -> L344
        r112.emoji = countryCodeToEmoji(r10);     // Catch: Exception -> L344
        if (r12 == false) goto L304;
        int r211 = R.string.Country;     // Catch: Exception -> L344
    L305:
        r112.address = LocaleController.getString(r211);     // Catch: Exception -> L344
        r112.geoAddress = r6;     // Catch: Exception -> L344
        r6.country_iso2 = r10;     // Catch: Exception -> L344
        if (r12 == true) goto L316;
        if (TextUtils.isEmpty(r8) == true) goto L311;
        r6.flags |= 1;
        r6.state = r8;     // Catch: Exception -> L344
    L311:
        if (TextUtils.isEmpty(r93) == true) goto L316;
        r6.flags |= 2;
        r6.city = r93;     // Catch: Exception -> L344
    L316:
        if (TextUtils.isEmpty(r42) == true) goto L329;
        TLRPC.TL_messageMediaVenue r212 = new TLRPC.TL_messageMediaVenue();     // Catch: Exception -> L344
        TLRPC.TL_geoPoint r122 = new TLRPC.TL_geoPoint();     // Catch: Exception -> L321
        r212.geo = r122;     // Catch: Exception -> L321
        r122.lat = r25.getLatitude();     // Catch: Exception -> L321
        r212.geo._long = r25.getLongitude();     // Catch: Exception -> L321
        r212.query_id = -1;     // Catch: Exception -> L321
        r212.title = r42;     // Catch: Exception -> L321
        r212.icon = "pin";     // Catch: Exception -> L321
        r212.address = LocaleController.getString(R.string.PassportStreet1);     // Catch: Exception -> L321
        r212.geoAddress = r7;     // Catch: Exception -> L321
        r7.country_iso2 = r10;     // Catch: Exception -> L321
        if (TextUtils.isEmpty(r8) == true) goto L323;
        r7.flags |= 1;
        r7.state = r8;     // Catch: Exception -> L321
    L323:
        if (TextUtils.isEmpty(r93) == true) goto L326;
        r7.flags |= 2;
        r7.city = r93;     // Catch: Exception -> L321
    L326:
        if (TextUtils.isEmpty(r5) == true) goto L328;
        r7.flags |= 4;
        r7.street = r5.toString();     // Catch: Exception -> L321
    L328:
        TLRPC.TL_messageMediaVenue r82 = r212;
    L330:
        if (r112 != null) goto L339;
        if (r82 != null) goto L339;
        String r213 = detectOcean(r25.getLongitude(), r25.getLatitude());     // Catch: Exception -> L338
        if (r213 == null) goto L339;
        TLRPC.TL_messageMediaVenue r43 = new TLRPC.TL_messageMediaVenue();     // Catch: Exception -> L338
        TLRPC.TL_geoPoint r52 = new TLRPC.TL_geoPoint();     // Catch: Exception -> L337
        r43.geo = r52;     // Catch: Exception -> L337
        r52.lat = r25.getLatitude();     // Catch: Exception -> L337
        r43.geo._long = r25.getLongitude();     // Catch: Exception -> L337
        r43.query_id = -1;     // Catch: Exception -> L337
        r43.title = r213;     // Catch: Exception -> L337
        r43.icon = "pin";     // Catch: Exception -> L337
        r43.emoji = "🌊";     // Catch: Exception -> L337
        r43.address = "Ocean";     // Catch: Exception -> L337
        r112 = r43;
    L337:
        TLRPC.TL_messageMediaVenue r163 = r82;
        TLRPC.TL_messageMediaVenue r83 = r43;
    L340:
        String r3 = String.format(Locale.US, "Unknown address (%f,%f)", new Object[]{Double.valueOf(r25.getLatitude()), Double.valueOf(r25.getLongitude())});
        String r44 = r3;
        TLRPC.TL_messageMediaVenue r53 = r83;
        TLRPC.TL_messageMediaVenue r62 = r163;
    L341:
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda10(r28, r3, r44, r53, r62, r25));
        return;
    L338:
        r162 = r82;
    L339:
        r3 = r02;
        r44 = r14;
        r62 = r82;
        r53 = r112;
    L321:
        r162 = r212;
        goto L298
    L329:
        r82 = r162;
        goto L330
    L304:
        r211 = R.string.PassportCity;     // Catch: Exception -> L344
        goto L305
    L299:
        r210 = "https://ss3.4sqi.net/img/categories_v2/travel/hotel_64.png";
    L298:
        r83 = r112;
        r163 = r162;
        goto L340
    L313:
        r112 = r162;
        goto L316
    L60:
        r02 = TextUtils.join(", ", r0);     // Catch: Exception -> L10
    L10:
        r83 = null;
        r163 = null;
        goto L340
    L355:
        r0.add(r92.getSubThoroughfare());     // Catch: Exception -> L345
    L369:
        r0.add(r92.getThoroughfare());     // Catch: Exception -> L346
    L359:
        r0.add(r92.getAdminArea());     // Catch: Exception -> L347
    L365:
        r0.add(r92.getCountryName());     // Catch: Exception -> L343
    L26:
        r1 = null;
        goto L28
    L62:
        StringBuilder r18 = new StringBuilder();     // Catch: Exception -> L286
        StringBuilder r142 = new StringBuilder();     // Catch: Exception -> L286
        StringBuilder r152 = new StringBuilder();     // Catch: Exception -> L286
        r16 = false;
        StringBuilder r84 = new StringBuilder();     // Catch: Exception -> L348
        if (TextUtils.isEmpty(null) == false) goto L68;
        String r172 = r92.getLocality();     // Catch: Exception -> L348
    L70:
        if (TextUtils.isEmpty(r172) == false) goto L73;
        r172 = r92.getAdminArea();     // Catch: Exception -> L348
    L73:
        if (TextUtils.isEmpty(r172) == false) goto L75;
        r172 = r92.getSubAdminArea();     // Catch: Exception -> L348
    L75:
        String r102 = r172;
        if (r22 != null) goto L78;
        String r123 = null;
        r17 = null;
    L90:
        if (TextUtils.isEmpty(null) == true) goto L92;
    L96:
        String r03 = null;
    L98:
        if (TextUtils.isEmpty(r03) == true) goto L100;
    L104:
        String r262 = r03;
    L105:
        String r04 = r262;
    L107:
        if (TextUtils.isEmpty(r04) == true) goto L109;
    L113:
        String r263 = r04;
    L114:
        String r05 = r263;
    L116:
        if (TextUtils.isEmpty(r05) == false) goto L118;
    L125:
        r84 = null;
    L126:
        if (r22 == null) goto L174;
        if (TextUtils.isEmpty(null) == true) goto L130;
    L134:
        String r06 = null;
    L136:
        if (TextUtils.isEmpty(r06) == true) goto L138;
    L142:
        String r264 = r06;
    L143:
        String r07 = r264;
    L145:
        if (TextUtils.isEmpty(r07) == true) goto L147;
    L151:
        String r265 = r07;
    L152:
        String r08 = r265;
    L154:
        if (TextUtils.isEmpty(r08) == false) goto L156;
    L163:
        r5 = null;
    L165:
        if (TextUtils.isEmpty(r5) == true) goto L174;
        int r09 = 0;
    L167:
        String[] r214 = unnamedRoads;     // Catch: Exception -> L348
        if (r09 >= r214.length) goto L174;
        if (r214[r09].equalsIgnoreCase(r5.toString()) == true) goto L171;
        r09 = r09 + 1;     // Catch: Exception -> L348
        goto L167
    L171:
        r5 = null;
        r84 = null;
        goto L174
    L156:
        if (TextUtils.equals(r08, r123) == true) goto L163;
        if (TextUtils.equals(r08, r22.getCountryName()) == true) goto L163;
        if (r5.length() <= 0) goto L162;
        r5.append(", ");     // Catch: Exception -> L348
    L162:
        r5.append(r08);     // Catch: Exception -> L348
        goto L165
    L147:
        if (TextUtils.equals(r22.getLocality(), r102) == true) goto L151;
        r265 = r07;
        if (TextUtils.equals(r22.getLocality(), r22.getCountryName()) == true) goto L152;
        r08 = r22.getLocality();     // Catch: Exception -> L348
        goto L154
    L138:
        if (TextUtils.equals(r22.getSubLocality(), r102) == true) goto L142;
        r264 = r06;
        if (TextUtils.equals(r22.getSubLocality(), r22.getCountryName()) == true) goto L143;
        r07 = r22.getSubLocality();     // Catch: Exception -> L348
        goto L145
    L130:
        if (TextUtils.equals(r22.getThoroughfare(), r102) == true) goto L134;
        if (TextUtils.equals(r22.getThoroughfare(), r22.getCountryName()) == true) goto L134;
        r06 = r22.getThoroughfare();     // Catch: Exception -> L348
    L174:
        if (TextUtils.isEmpty(r102) == false) goto L176;
        boolean r010 = true;
    L186:
        String r215 = r92.getSubThoroughfare();     // Catch: Exception -> L348
        if (TextUtils.isEmpty(r215) == true) goto L189;
        r18.append(r215);     // Catch: Exception -> L348
        boolean r216 = true;
    L190:
        String r103 = r92.getThoroughfare();     // Catch: Exception -> L348
        boolean r266 = r010;
        if (TextUtils.isEmpty(r103) == false) goto L194;
    L197:
        if (r216 == true) goto L210;
        String r104 = r92.getAdminArea();     // Catch: Exception -> L348
        if (TextUtils.isEmpty(r104) == false) goto L201;
    L204:
        String r105 = r92.getSubAdminArea();     // Catch: Exception -> L348
        if (TextUtils.isEmpty(r105) == true) goto L210;
        if (r18.length() <= 0) goto L209;
        r18.append(", ");     // Catch: Exception -> L348
    L209:
        r18.append(r105);     // Catch: Exception -> L348
        goto L210
    L201:
        if (r18.length() <= 0) goto L203;
        r18.append(", ");     // Catch: Exception -> L348
    L203:
        r18.append(r104);     // Catch: Exception -> L348
    L210:
        String r106 = r92.getLocality();     // Catch: Exception -> L348
        if (TextUtils.isEmpty(r106) == false) goto L213;
    L216:
        r10 = r92.getCountryCode();     // Catch: Exception -> L348
        String r113 = r92.getCountryName();     // Catch: Exception -> L348
        if (TextUtils.isEmpty(r113) == false) goto L219;
        StringBuilder r20 = r18;
        boolean r21 = r216;
        StringBuilder r222 = r5;
        StringBuilder r232 = r84;
    L255:
        String r011 = r92.getCountryName();     // Catch: Exception -> L348
        if (TextUtils.isEmpty(r011) == false) goto L258;
    L261:
        String r012 = r92.getLocality();     // Catch: Exception -> L348
        if (TextUtils.isEmpty(r012) == false) goto L264;
    L267:
        if (r21 == true) goto L280;
        String r013 = r92.getAdminArea();     // Catch: Exception -> L348
        if (TextUtils.isEmpty(r013) == false) goto L271;
    L274:
        String r014 = r92.getSubAdminArea();     // Catch: Exception -> L348
        if (TextUtils.isEmpty(r014) == true) goto L280;
        if (r142.length() <= 0) goto L279;
        r142.append(", ");     // Catch: Exception -> L348
    L279:
        r142.append(r014);     // Catch: Exception -> L348
        goto L280
    L271:
        if (r142.length() <= 0) goto L273;
        r142.append(", ");     // Catch: Exception -> L348
    L273:
        r142.append(r013);     // Catch: Exception -> L348
    L280:
        r02 = r20.toString();     // Catch: Exception -> L348
        r14 = r142.toString();     // Catch: Exception -> L348
        r29 = r152.toString();     // Catch: Exception -> L348
        if (r232 != null) goto L283;
        r42 = null;
    L284:
        r8 = r123;
        r5 = r222;
        r12 = r266;
        goto L285
    L283:
        r42 = r232.toString();     // Catch: Exception -> L348
        goto L284
    L264:
        if (r142.length() <= 0) goto L266;
        r142.append(", ");     // Catch: Exception -> L348
    L266:
        r142.append(r012);     // Catch: Exception -> L348
        goto L267
    L258:
        if (r142.length() <= 0) goto L260;
        r142.append(", ");     // Catch: Exception -> L348
    L260:
        r142.append(r011);     // Catch: Exception -> L348
        goto L261
    L219:
        if (r18.length() <= 0) goto L221;
        r18.append(", ");     // Catch: Exception -> L348
    L221:
        r18.append(r113);     // Catch: Exception -> L348
        r20 = r18;
        String r19 = r24.getLanguage();     // Catch: Exception -> L348
        r21 = r216;
        if ("US".equals(r92.getCountryCode()) == true) goto L228;
        r222 = r5;
        r232 = r84;
        if ("AE".equals(r92.getCountryCode()) == false) goto L236;
    L230:
        if ("en".equals(r19) == false) goto L232;
    L243:
        String[] r015 = r113.split(" ");     // Catch: Exception -> L348
        int r217 = r015.length;     // Catch: Exception -> L348
        r113 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r110 = 0;
    L244:
        if (r110 >= r217) goto L251;
        String r45 = r015[r110];     // Catch: Exception -> L348
        if (r45.length() <= 0) goto L249;
        r113 = r113 + r45.charAt(0);     // Catch: Exception -> L348
    L249:
        r110 = r110 + 1;     // Catch: Exception -> L348
    L251:
        if (r152.length() <= 0) goto L253;
        r152.append(", ");     // Catch: Exception -> L348
    L253:
        r152.append(r113);     // Catch: Exception -> L348
        goto L255
    L232:
        if ("uk".equals(r19) == true) goto L243;
        if ("ru".equals(r19) == true) goto L243;
    L236:
        if ("GB".equals(r92.getCountryCode()) == false) goto L241;
        if ("en".equals(r19) == true) goto L243;
    L241:
        if ("US".equals(r92.getCountryCode()) == false) goto L251;
        r113 = "USA";
        goto L251
    L228:
        r222 = r5;
        r232 = r84;
        goto L230
    L213:
        if (r18.length() <= 0) goto L215;
        r18.append(", ");     // Catch: Exception -> L348
    L215:
        r18.append(r106);     // Catch: Exception -> L348
        goto L216
    L194:
        if (r18.length() <= 0) goto L196;
        r18.append(" ");     // Catch: Exception -> L348
    L196:
        r18.append(r103);     // Catch: Exception -> L348
        r216 = true;
        goto L197
    L189:
        r216 = false;
        goto L190
    L176:
        if (r152.length() <= 0) goto L178;
        r152.append(", ");     // Catch: Exception -> L348
    L178:
        r152.append(r102);     // Catch: Exception -> L348
        if (r84 != null) goto L181;
    L184:
        r010 = false;
        goto L186
    L181:
        if (r84.length() <= 0) goto L183;
        r84.append(", ");     // Catch: Exception -> L348
    L183:
        r84.append(r102);     // Catch: Exception -> L348
        goto L184
    L118:
        if (TextUtils.equals(r05, r102) == true) goto L125;
        if (TextUtils.equals(r05, r92.getCountryName()) == true) goto L125;
        if (r84.length() <= 0) goto L124;
        r84.append(", ");     // Catch: Exception -> L348
    L124:
        r84.append(r05);     // Catch: Exception -> L348
        goto L126
    L109:
        if (TextUtils.equals(r92.getLocality(), r102) == true) goto L113;
        r263 = r04;
        if (TextUtils.equals(r92.getLocality(), r92.getCountryName()) == true) goto L114;
        r05 = r92.getLocality();     // Catch: Exception -> L348
        goto L116
    L100:
        if (TextUtils.equals(r92.getSubLocality(), r102) == true) goto L104;
        r262 = r03;
        if (TextUtils.equals(r92.getSubLocality(), r92.getCountryName()) == true) goto L105;
        r04 = r92.getSubLocality();     // Catch: Exception -> L348
        goto L107
    L92:
        if (TextUtils.equals(r92.getThoroughfare(), r102) == true) goto L96;
        if (TextUtils.equals(r92.getThoroughfare(), r92.getCountryName()) == true) goto L96;
        r03 = r92.getThoroughfare();     // Catch: Exception -> L348
        goto L98
    L78:
        if (TextUtils.isEmpty(null) == false) goto L80;
        r17 = r22.getLocality();     // Catch: Exception -> L348
    L82:
        if (TextUtils.isEmpty(r17) == false) goto L85;
        r17 = r22.getAdminArea();     // Catch: Exception -> L348
    L85:
        if (TextUtils.isEmpty(r17) == false) goto L87;
        r17 = r22.getSubAdminArea();     // Catch: Exception -> L348
    L87:
        r123 = r22.getAdminArea();     // Catch: Exception -> L348
        goto L90
    L80:
        r17 = null;
        goto L82
    L68:
        r172 = null;
        goto L70
    L17:
        if (r2.size() < 1) goto L20;
        r22 = r2.get(0);     // Catch: Exception -> L10
        goto L22
    L287:
        r162 = 0;
        if (r26 != 1) goto L290;
        r02 = null;
    L291:
        r14 = r02;
        r29 = null;
        r42 = null;
        r8 = null;
        r93 = null;
        r10 = null;
        r12 = true;
        goto L293
    L290:
        r02 = String.format(Locale.US, "Unknown address (%f,%f)", new Object[]{Double.valueOf(r25.getLatitude()), Double.valueOf(r25.getLongitude())});     // Catch: Exception -> L348
    L67:
        r83 = r162;
        r163 = r162;
        goto L340
    L357:
        r162 = 1;
        r2 = new Geocoder(ApplicationLoader.applicationContext, r27).getFromLocation(r25.getLatitude(), r25.getLongitude(), 1);     // Catch: Exception -> L10
        goto L12
    L11:
        r2 = null;
    L286:
        r162 = 0;
        goto L67
    }

    /* renamed from: $r8$lambda$uWIB7kIfFH-GFhOjAXLn2eN6YcA, reason: not valid java name */
    public static /* synthetic */ void m4253$r8$lambda$uWIB7kIfFHGFhOjAXLn2eN6YcA(LocationController r0, int r1, long r2) {
        r0.lambda$setProximityLocation$12(r1, r2);
    }

    /* renamed from: -$$Nest$fgetlastKnownLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Location m4254$$Nest$fgetlastKnownLocation(LocationController r0) {
        return r0.lastKnownLocation;
    }

    /* renamed from: -$$Nest$fgetnetworkLocationListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GpsLocationListener m4255$$Nest$fgetnetworkLocationListener(LocationController r0) {
        return r0.networkLocationListener;
    }

    /* renamed from: -$$Nest$fgetpassiveLocationListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GpsLocationListener m4256$$Nest$fgetpassiveLocationListener(LocationController r0) {
        return r0.passiveLocationListener;
    }

    /* renamed from: -$$Nest$fgetstarted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m4257$$Nest$fgetstarted(LocationController r0) {
        return r0.started;
    }

    /* renamed from: -$$Nest$fputlastLocationSendTime, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4258$$Nest$fputlastLocationSendTime(LocationController r0, long r1) {
        r0.lastLocationSendTime = r1;
    }

    /* renamed from: -$$Nest$msetLastKnownLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4259$$Nest$msetLastKnownLocation(LocationController r0, Location r1) {
        r0.setLastKnownLocation(r1);
    }

    static {
        Instance = new LocationController[16];
        unnamedRoads = new String[]{"Unnamed Road", "Вulicya bez nazvi", "Нeizvestnaya doroga", "İsimsiz Yol", "Ceļš bez nosaukuma", "Kelias be pavadinimo", "Droga bez nazwy", "Cesta bez názvu", "Silnice bez názvu", "Drum fără nume", "Route sans nom", "Vía sin nombre", "Estrada sem nome", "Οdos xoris onomasia", "Rrugë pa emër", "Пat bez ime", "Нeimenovani put", "Strada senza nome", "Straße ohne Straßennamen"};
        callbacks = new HashMap();
    }

    public LocationController(int r3) {
        super(r3);
        this.sharingLocationsMap = new LongSparseArray();
        this.sharingLocations = new ArrayList();
        this.locationsCache = new LongSparseArray();
        this.lastReadLocationTime = new LongSparseArray();
        this.gpsLocationListener = new GpsLocationListener(this, null);
        this.networkLocationListener = new GpsLocationListener(this, null);
        this.passiveLocationListener = new GpsLocationListener(this, null);
        this.fusedLocationListener = new FusedLocationListener(this, null);
        this.locationSentSinceLastMapUpdate = true;
        this.requests = new SparseIntArray();
        this.cacheRequests = new LongSparseArray();
        this.sharingLocationsUI = new ArrayList();
        this.sharingLocationsMapUI = new LongSparseArray();
        this.locationManager = (LocationManager) ApplicationLoader.applicationContext.getSystemService("location");
        this.apiClient = ApplicationLoader.getLocationServiceProvider().onCreateLocationServicesAPI(ApplicationLoader.applicationContext, this, this);
        ILocationServiceProvider.ILocationRequest r32 = ApplicationLoader.getLocationServiceProvider().onCreateLocationRequest();
        this.locationRequest = r32;
        r32.setPriority(0);
        this.locationRequest.setInterval(1000);
        this.locationRequest.setFastestInterval(1000);
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda20(this));
        loadSharingLocations();
    }

    private void broadcastLastKnownLocation(boolean r14) {
        if (this.lastKnownLocation != null) goto L6;
        return;
    L6:
        if (this.requests.size() == 0) goto L14;
        if (r14 == false) goto L12;
        int r142 = 0;
    L10:
        if (r142 >= this.requests.size()) goto L12;
        getConnectionsManager().cancelRequest(this.requests.keyAt(r142), false);
        r142 = r142 + 1;
    L12:
        this.requests.clear();
    L14:
        if (this.sharingLocations.isEmpty() == true) goto L41;
        int r143 = getConnectionsManager().getCurrentTime();
        float[] r10 = new float[1];
        int r11 = 0;
    L17:
        if (r11 >= this.sharingLocations.size()) goto L41;
        SharingLocationInfo r12 = this.sharingLocations.get(r11);
        TLRPC.Message r2 = r12.messageObject.messageOwner;
        TLRPC.MessageMedia r3 = r2.media;
        if (r3 == null) goto L33;
        TLRPC.GeoPoint r32 = r3.geo;
        if (r32 == null) goto L33;
        if (r12.lastSentProximityMeters != r12.proximityMeters) goto L33;
        int r4 = r2.edit_date;
        if (r4 != 0) goto L29;
        r4 = r2.date;
    L29:
        if (Math.abs(r143 - r4) >= 10) goto L33;
        Location.distanceBetween(r32.lat, r32._long, this.lastKnownLocation.getLatitude(), this.lastKnownLocation.getLongitude(), r10);
        if (r10[0] >= 1.0f) goto L33;
    L40:
        r11 = r11 + 1;
    L33:
        TLRPC.TL_messages_editMessage r22 = new TLRPC.TL_messages_editMessage();
        r22.peer = getMessagesController().getInputPeer(r12.did);
        r22.id = r12.mid;
        r22.flags |= 16384;
        TLRPC.TL_inputMediaGeoLive r33 = new TLRPC.TL_inputMediaGeoLive();
        r22.media = r33;
        r33.stopped = false;
        r33.geo_point = new TLRPC.TL_inputGeoPoint();
        r22.media.geo_point.lat = AndroidUtilities.fixLocationCoord(this.lastKnownLocation.getLatitude());
        r22.media.geo_point._long = AndroidUtilities.fixLocationCoord(this.lastKnownLocation.getLongitude());
        r22.media.geo_point.accuracy_radius = (int) this.lastKnownLocation.getAccuracy();
        TLRPC.InputMedia r34 = r22.media;
        TLRPC.InputGeoPoint r42 = r34.geo_point;
        if (r42.accuracy_radius == 0) goto L36;
        r42.flags |= 1;
    L36:
        int r43 = r12.lastSentProximityMeters;
        int r5 = r12.proximityMeters;
        if (r43 == r5) goto L39;
        r34.proximity_notification_radius = r5;
        r34.flags |= 8;
    L39:
        r34.heading = getHeading(this.lastKnownLocation);
        r22.media.flags |= 4;
        int r23 = getConnectionsManager().sendRequest(r22, new LocationController$$ExternalSyntheticLambda22(this, r12, r3, r22));
        int[] r35 = {r23};
        this.requests.put(r23, 0);
    L41:
        getConnectionsManager().resumeNetworkMaybe();
        if (shouldStopGps() == false) goto L49;
        stop(false);
        return;
    }

    private boolean checkServices() {
        if (this.servicesAvailable != null) goto L6;
        this.servicesAvailable = Boolean.valueOf(ApplicationLoader.getLocationServiceProvider().checkServices());
    L6:
        return this.servicesAvailable.booleanValue();
    }

    public static String countryCodeToEmoji(String r5) {
        if (r5 != null) goto L5;
        return null;
    L5:
        String r52 = r5.toUpperCase();
        int r2 = 0;
        int r1 = r52.codePointCount(0, r52.length());
        if (r1 <= 2) goto L8;
        return null;
    L8:
        StringBuilder r0 = new StringBuilder();
    L9:
        if (r2 >= r1) goto L12;
        r0.append(Character.toChars(Character.codePointAt(r52, r2) - (-127397)));
        r2 = r2 + 1;
        goto L9
    L12:
        return r0.toString();
    }

    public static String detectOcean(double r9, double r11) {
        if (r11 <= 65.0d) goto L7;
        return "Arctic Ocean";
    L7:
        if (r9 <= (-88.0d)) goto L13;
        if (r9 >= 40.0d) goto L13;
        if (r11 <= 0.0d) goto L13;
        return "Atlantic Ocean";
    L13:
        if (r9 <= (-60.0d)) goto L21;
        if (r9 >= 20.0d) goto L21;
        if (r11 > 0.0d) goto L21;
        return "Atlantic Ocean";
    L21:
        if (r11 > 30.0d) goto L29;
        if (r9 < 20.0d) goto L29;
        if (r9 >= 150.0d) goto L29;
        return "Indian Ocean";
    L29:
        if (r9 > 106.0d) goto L33;
        if (r9 < (-60.0d)) goto L33;
    L35:
        if (r9 > 150.0d) goto L39;
        if (r9 < (-60.0d)) goto L39;
        return null;
    L39:
        if (r11 <= 0.0d) goto L47;
        return null;
    L47:
        return "Pacific Ocean";
    L33:
        if (r11 <= 0.0d) goto L35;
        return "Pacific Ocean";
    }

    public static void fetchLocationAddress(Location r13, int r14, LocationFetchCallback r15) {
        if (r15 != null) goto L4;
        return;
    L4:
        Runnable r0 = callbacks.get(r15);
        if (r0 == null) goto L7;
        Utilities.globalQueue.cancelRunnable(r0);
        callbacks.remove(r15);
    L7:
        if (r13 != null) goto L20;
        r15.onLocationAddressAvailable(null, null, null, null, null);
        return;
    L20:
        Locale r152 = LocaleController.getInstance().getCurrentLocale();     // Catch: Exception -> L13
    L12:
        Locale r8 = r152;
        if (r8.getLanguage().contains("en") == false) goto L17;
        Locale r11 = r8;
    L18:
        DispatchQueue r153 = Utilities.globalQueue;
        LocationController$$ExternalSyntheticLambda18 r7 = new LocationController$$ExternalSyntheticLambda18(r8, r13, r14, r11, r15);
        r153.postRunnable(r7, 300);
        callbacks.put(r15, r7);
        return;
    L17:
        r11 = Locale.US;
    L13:
        r152 = LocaleController.getInstance().getSystemDefaultLocale();
        goto L12
    }

    public static void fetchLocationAddress(Location r1, LocationFetchCallback r2) {
        fetchLocationAddress(r1, 0, r2);
    }

    public static int getHeading(Location r1) {
        float r12 = r1.getBearing();
        if (r12 <= 0.0f) goto L13;
        if (r12 >= 1.0f) goto L13;
        if (r12 >= 0.5f) goto L10;
        return 360;
    L10:
        return 1;
    L13:
        return (int) r12;
    }

    public static LocationController getInstance(int r3) {
        LocationController r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(LocationController.class);
        LocationController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        LocationController[] r03 = Instance;     // Catch: Throwable -> L9
        LocationController r2 = new LocationController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(LocationController.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static int getLocationsCount() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= 16) goto L6;
        r1 = r1 + getInstance(r0).sharingLocationsUI.size();
        r0 = r0 + 1;
        goto L4
    L6:
        return r1;
    }

    private /* synthetic */ void lambda$addSharingLocation$11(SharingLocationInfo r3, SharingLocationInfo r4) {
        if (r3 == null) goto L4;
        this.sharingLocationsUI.remove(r3);
    L4:
        this.sharingLocationsUI.add(r4);
        this.sharingLocationsMapUI.put(r4.did, r4);
        startService();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsChanged, new Object[0]);
    }

    private /* synthetic */ void lambda$broadcastLastKnownLocation$6(SharingLocationInfo r4) {
        this.sharingLocationsUI.remove(r4);
        this.sharingLocationsMapUI.remove(r4.did);
        if (this.sharingLocationsUI.isEmpty() == false) goto L5;
        stopService();
    L5:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsChanged, new Object[0]);
    }

    private /* synthetic */ void lambda$broadcastLastKnownLocation$7(SharingLocationInfo r4, int[] r5, TLRPC.TL_messages_editMessage r6, TLObject r7, TLRPC.TL_error r8) {
        if (r8 == null) goto L9;
        if (r8.text.equals("MESSAGE_ID_INVALID") == false) goto L29;
        this.sharingLocations.remove(r4);
        this.sharingLocationsMap.remove(r4.did);
        saveSharingLocation(r4, 1);
        this.requests.delete(r5[0]);
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda12(this, r4));
        return;
    L29:
        return;
    L9:
        if ((r6.flags & 8) == 0) goto L11;
        r4.lastSentProximityMeters = r6.media.proximity_notification_radius;
    L11:
        TLRPC.Updates r72 = (TLRPC.Updates) r7;
        int r52 = 0;
        boolean r62 = false;
    L13:
        if (r52 >= r72.updates.size()) goto L22;
        TLRPC.Update r82 = r72.updates.get(r52);
        if ((r82 instanceof TL_update.TL_updateEditMessage) == false) goto L19;
        r4.messageObject.messageOwner = ((TL_update.TL_updateEditMessage) r82).message;
    L17:
        r62 = true;
    L21:
        r52 = r52 + 1;
        goto L13
    L19:
        if ((r82 instanceof TL_update.TL_updateEditChannelMessage) == false) goto L21;
        r4.messageObject.messageOwner = ((TL_update.TL_updateEditChannelMessage) r82).message;
        goto L17
    L22:
        if (r62 == false) goto L24;
        saveSharingLocation(r4, 0);
    L24:
        getMessagesController().processUpdates(r72, false);
    }

    private /* synthetic */ void lambda$cleanup$9() {
        this.locationEndWatchTime = 0;
        this.requests.clear();
        this.sharingLocationsMap.clear();
        this.sharingLocations.clear();
        setLastKnownLocation(null);
        stop(true);
    }

    private /* synthetic */ void lambda$loadLiveLocations$25(long r6, TLObject r8) {
        this.cacheRequests.delete(r6);
        TLRPC.messages_Messages r82 = (TLRPC.messages_Messages) r8;
        int r1 = 0;
    L4:
        if (r1 >= r82.messages.size()) goto L9;
        if ((r82.messages.get(r1).media instanceof TLRPC.TL_messageMediaGeoLive) == true) goto L8;
        r82.messages.remove(r1);
        r1 = r1 - 1;
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        getMessagesStorage().putUsersAndChats(r82.users, r82.chats, true, true);
        getMessagesController().putUsers(r82.users, false);
        getMessagesController().putChats(r82.chats, false);
        this.locationsCache.put(r6, r82.messages);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsCacheChanged, new Object[]{Long.valueOf(r6), Integer.valueOf(this.currentAccount)});
    }

    private /* synthetic */ void lambda$loadLiveLocations$26(long r1, TLObject r3, TLRPC.TL_error r4) {
        if (r4 == null) goto L4;
        return;
    L4:
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda26(this, r1, r3));
    }

    private /* synthetic */ void lambda$loadSharingLocations$14(ArrayList r7) {
        this.sharingLocationsUI.addAll(r7);
        int r1 = 0;
    L4:
        if (r1 >= r7.size()) goto L6;
        SharingLocationInfo r2 = (SharingLocationInfo) r7.get(r1);
        this.sharingLocationsMapUI.put(r2.did, r2);
        r1 = r1 + 1;
        goto L4
    L6:
        startService();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsChanged, new Object[0]);
    }

    private /* synthetic */ void lambda$loadSharingLocations$15(ArrayList r6) {
        this.sharingLocations.addAll(r6);
        int r0 = 0;
    L4:
        if (r0 >= this.sharingLocations.size()) goto L6;
        SharingLocationInfo r1 = this.sharingLocations.get(r0);
        this.sharingLocationsMap.put(r1.did, r1);
        r0 = r0 + 1;
        goto L4
    L6:
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda28(this, r6));
    }

    private /* synthetic */ void lambda$loadSharingLocations$16(ArrayList r3, ArrayList r4, ArrayList r5) {
        getMessagesController().putUsers(r3, true);
        getMessagesController().putChats(r4, true);
        Utilities.stageQueue.postRunnable(new LocationController$$ExternalSyntheticLambda8(this, r5));
    }

    private /* synthetic */ void lambda$loadSharingLocations$17() {
        ArrayList r0 = new ArrayList();
        ArrayList<TLRPC.User> r1 = new ArrayList();
        ArrayList<TLRPC.Chat> r2 = new ArrayList();
        ArrayList<Long> r3 = new ArrayList();     // Catch: Exception -> L9
        ArrayList r4 = new ArrayList();     // Catch: Exception -> L9
        SQLiteCursor r5 = getMessagesStorage().getDatabase().queryFinalized("SELECT uid, mid, date, period, message, proximity FROM sharing_locations WHERE 1", new Object[0]);     // Catch: Exception -> L9
    L5:
        if (r5.next() == false) goto L23;
        SharingLocationInfo r6 = new SharingLocationInfo();     // Catch: Exception -> L9
        r6.did = r5.longValue(0);     // Catch: Exception -> L9
        r6.mid = r5.intValue(1);     // Catch: Exception -> L9
        r6.stopTime = r5.intValue(2);     // Catch: Exception -> L9
        r6.period = r5.intValue(3);     // Catch: Exception -> L9
        r6.proximityMeters = r5.intValue(5);     // Catch: Exception -> L9
        r6.account = this.currentAccount;     // Catch: Exception -> L9
        NativeByteBuffer r8 = r5.byteBufferValue(4);     // Catch: Exception -> L9
        if (r8 == null) goto L11;
        MessageObject r9 = new MessageObject(this.currentAccount, TLRPC.Message.TLdeserialize(r8, r8.readInt32(false), false), false, false);     // Catch: Exception -> L9
        r6.messageObject = r9;     // Catch: Exception -> L9
        MessagesStorage.addUsersAndChatsFromMessage(r9.messageOwner, r3, r4, null);     // Catch: Exception -> L9
        r8.reuse();     // Catch: Exception -> L9
    L11:
        r0.add(r6);     // Catch: Exception -> L9
        boolean r82 = DialogObject.isChatDialog(r6.did);     // Catch: Exception -> L9
        long r92 = r6.did;
        if (r82 == true) goto L16;
        if (DialogObject.isUserDialog(r92) == false) goto L5;
        if (r3.contains(Long.valueOf(r6.did)) == true) goto L5;
        r3.add(Long.valueOf(r6.did));     // Catch: Exception -> L9
        goto L5
    L16:
        if (r4.contains(Long.valueOf(-r92)) == true) goto L5;
        r4.add(Long.valueOf(-r6.did));     // Catch: Exception -> L9
        goto L5
    L23:
        r5.dispose();     // Catch: Exception -> L9
        if (r4.isEmpty() == true) goto L26;
        getMessagesStorage().getChatsInternal(TextUtils.join(",", r4), r2);     // Catch: Exception -> L9
    L26:
        getMessagesStorage().getUsersInternal(r3, r1);     // Catch: Exception -> L9
    L30:
        if (r0.isEmpty() == true) goto L47;
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda27(this, r1, r2, r0));
        return;
    L47:
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L30
    }

    private /* synthetic */ void lambda$markLiveLoactionsAsRead$27(TLObject r2, TLRPC.TL_error r3) {
        if ((r2 instanceof TLRPC.TL_messages_affectedMessages) == false) goto L6;
        TLRPC.TL_messages_affectedMessages r22 = (TLRPC.TL_messages_affectedMessages) r2;
        getMessagesController().processNewDifferenceParams(-1, r22.pts, -1, r22.pts_count);
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        LocationController r0 = getAccountInstance().getLocationController();
        getNotificationCenter().addObserver(r0, NotificationCenter.didReceiveNewMessages);
        getNotificationCenter().addObserver(r0, NotificationCenter.messagesDeleted);
        getNotificationCenter().addObserver(r0, NotificationCenter.replaceMessagesObjects);
    }

    private /* synthetic */ void lambda$onConnected$1(Integer r2) {
        getNotificationCenter().postNotificationName(NotificationCenter.needShowPlayServicesAlert, new Object[]{r2});
    }

    private /* synthetic */ void lambda$onConnected$2(Integer r2) {
        if (this.sharingLocations.isEmpty() == true) goto L6;
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda9(this, r2));
        return;
    }

    private /* synthetic */ void lambda$onConnected$3() {
        this.servicesAvailable = Boolean.FALSE;
        this.apiClient.disconnect();     // Catch: Throwable -> L5
        start();     // Catch: Throwable -> L5
        return;
    }

    private /* synthetic */ void lambda$onConnected$4(Integer r3) {
        int r0 = r3.intValue();
        if (r0 == 0) goto L12;
        if (r0 != 1) goto L6;
        Utilities.stageQueue.postRunnable(new LocationController$$ExternalSyntheticLambda13(this, r3));
        return;
    L6:
        if (r0 == 2) goto L8;
        return;
    L8:
        Utilities.stageQueue.postRunnable(new LocationController$$ExternalSyntheticLambda14(this));
        return;
    L12:
        startFusedLocationRequest(true);
    }

    private /* synthetic */ void lambda$removeAllLocationSharings$22(TLObject r1, TLRPC.TL_error r2) {
        if (r2 == null) goto L4;
        return;
    L4:
        getMessagesController().processUpdates((TLRPC.Updates) r1, false);
    }

    private /* synthetic */ void lambda$removeAllLocationSharings$23() {
        this.sharingLocationsUI.clear();
        this.sharingLocationsMapUI.clear();
        stopService();
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsChanged, new Object[0]);
    }

    private /* synthetic */ void lambda$removeAllLocationSharings$24() {
        int r0 = 0;
    L3:
        int r1 = this.sharingLocations.size();
        ArrayList<SharingLocationInfo> r2 = this.sharingLocations;
        if (r0 >= r1) goto L6;
        SharingLocationInfo r12 = r2.get(r0);
        TLRPC.TL_messages_editMessage r22 = new TLRPC.TL_messages_editMessage();
        r22.peer = getMessagesController().getInputPeer(r12.did);
        r22.id = r12.mid;
        r22.flags |= 16384;
        TLRPC.TL_inputMediaGeoLive r13 = new TLRPC.TL_inputMediaGeoLive();
        r22.media = r13;
        r13.stopped = true;
        r13.geo_point = new TLRPC.TL_inputGeoPointEmpty();
        getConnectionsManager().sendRequest(r22, new LocationController$$ExternalSyntheticLambda24(this));
        r0 = r0 + 1;
        goto L3
    L6:
        r2.clear();
        this.sharingLocationsMap.clear();
        saveSharingLocation(null, 2);
        stop(true);
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda25(this));
    }

    private /* synthetic */ void lambda$removeSharingLocation$19(TLObject r1, TLRPC.TL_error r2) {
        if (r2 == null) goto L4;
        return;
    L4:
        getMessagesController().processUpdates((TLRPC.Updates) r1, false);
    }

    private /* synthetic */ void lambda$removeSharingLocation$20(SharingLocationInfo r4) {
        this.sharingLocationsUI.remove(r4);
        this.sharingLocationsMapUI.remove(r4.did);
        if (this.sharingLocationsUI.isEmpty() == false) goto L5;
        stopService();
    L5:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsChanged, new Object[0]);
    }

    private /* synthetic */ void lambda$removeSharingLocation$21(long r4) {
        SharingLocationInfo r0 = this.sharingLocationsMap.get(r4);
        this.sharingLocationsMap.remove(r4);
        if (r0 == null) goto L8;
        TLRPC.TL_messages_editMessage r42 = new TLRPC.TL_messages_editMessage();
        r42.peer = getMessagesController().getInputPeer(r0.did);
        r42.id = r0.mid;
        r42.flags |= 16384;
        TLRPC.TL_inputMediaGeoLive r5 = new TLRPC.TL_inputMediaGeoLive();
        r42.media = r5;
        r5.stopped = true;
        r5.geo_point = new TLRPC.TL_inputGeoPointEmpty();
        getConnectionsManager().sendRequest(r42, new LocationController$$ExternalSyntheticLambda15(this));
        this.sharingLocations.remove(r0);
        saveSharingLocation(r0, 1);
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda16(this, r0));
        if (this.sharingLocations.isEmpty() == false) goto L9;
        stop(true);
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$saveSharingLocation$18(int r6, SharingLocationInfo r7) {
        if (r6 != 2) goto L7;
        getMessagesStorage().getDatabase().executeFast("DELETE FROM sharing_locations WHERE 1").stepThis().dispose();     // Catch: Exception -> L16
        return;
    L7:
        if (r6 != 1) goto L12;
        if (r7 == null) goto L13;
        getMessagesStorage().getDatabase().executeFast("DELETE FROM sharing_locations WHERE uid = " + r7.did).stepThis().dispose();     // Catch: Exception -> L16
        return;
    L13:
        return;
    L12:
        if (r7 == null) goto L20;
        SQLitePreparedStatement r5 = getMessagesStorage().getDatabase().executeFast("REPLACE INTO sharing_locations VALUES(?, ?, ?, ?, ?, ?)");     // Catch: Exception -> L16
        r5.requery();     // Catch: Exception -> L16
        NativeByteBuffer r62 = new NativeByteBuffer(r7.messageObject.messageOwner.getObjectSize());     // Catch: Exception -> L16
        r7.messageObject.messageOwner.serializeToStream(r62);     // Catch: Exception -> L16
        r5.bindLong(1, r7.did);     // Catch: Exception -> L16
        r5.bindInteger(2, r7.mid);     // Catch: Exception -> L16
        r5.bindInteger(3, r7.stopTime);     // Catch: Exception -> L16
        r5.bindInteger(4, r7.period);     // Catch: Exception -> L16
        r5.bindByteBuffer(5, r62);     // Catch: Exception -> L16
        r5.bindInteger(6, r7.proximityMeters);     // Catch: Exception -> L16
        r5.step();     // Catch: Exception -> L16
        r5.dispose();     // Catch: Exception -> L16
        r62.reuse();     // Catch: Exception -> L16
        return;
    L20:
        return;
    L16:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$setProximityLocation$12(int r2, long r3) {
        SQLitePreparedStatement r1 = getMessagesStorage().getDatabase().executeFast("UPDATE sharing_locations SET proximity = ? WHERE uid = ?");     // Catch: Exception -> L4
        r1.requery();     // Catch: Exception -> L4
        r1.bindInteger(1, r2);     // Catch: Exception -> L4
        r1.bindLong(2, r3);     // Catch: Exception -> L4
        r1.step();     // Catch: Exception -> L4
        r1.dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$setProximityLocation$13() {
        broadcastLastKnownLocation(true);
    }

    private /* synthetic */ void lambda$startFusedLocationRequest$5(boolean r2) {
        if (r2 == true) goto L5;
        this.servicesAvailable = Boolean.FALSE;
    L5:
        if (this.sharingLocations.isEmpty() == true) goto L16;
        if (r2 == true) goto L14;
        start();
        return;
    L14:
        ApplicationLoader.getLocationServiceProvider().getLastLocation(new LocationController$$ExternalSyntheticLambda7(this));     // Catch: Throwable -> L9
        ApplicationLoader.getLocationServiceProvider().requestLocationUpdates(this.locationRequest, this.fusedLocationListener);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        FileLog.e(th);
        return;
    }

    private /* synthetic */ void lambda$update$8(SharingLocationInfo r4) {
        this.sharingLocationsUI.remove(r4);
        this.sharingLocationsMapUI.remove(r4.did);
        if (this.sharingLocationsUI.isEmpty() == false) goto L5;
        stopService();
    L5:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsChanged, new Object[0]);
    }

    private void loadSharingLocations() {
        getMessagesStorage().getStorageQueue().postRunnable(new LocationController$$ExternalSyntheticLambda6(this));
    }

    private void saveSharingLocation(SharingLocationInfo r3, int r4) {
        getMessagesStorage().getStorageQueue().postRunnable(new LocationController$$ExternalSyntheticLambda21(this, r4, r3));
    }

    private void setLastKnownLocation(Location r5) {
        if (r5 != null) goto L4;
    L6:
        this.lastKnownLocation = r5;
        if (r5 == null) goto L10;
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda11());
        return;
    L10:
        return;
    L4:
        if (((SystemClock.elapsedRealtimeNanos() - r5.getElapsedRealtimeNanos()) / 1000000000) <= 300) goto L6;
    }

    private boolean shouldSendLocationNow() {
        if (shouldStopGps() == true) goto L6;
        return false;
    L6:
        if (Math.abs(this.lastLocationSendTime - SystemClock.elapsedRealtime()) < 2000) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean shouldStopGps() {
        if (SystemClock.elapsedRealtime() <= this.locationEndWatchTime) goto L6;
        return true;
    L6:
        return false;
    }

    private void start() {
        if (this.started == true) goto L44;
        this.lastLocationStartTime = SystemClock.elapsedRealtime();
        this.started = true;
        if (checkServices() == true) goto L38;
    L34:
        this.locationManager.requestLocationUpdates("gps", 1, 0.0f, this.gpsLocationListener);     // Catch: Exception -> L13
    L32:
        this.locationManager.requestLocationUpdates("network", 1, 0.0f, this.networkLocationListener);     // Catch: Exception -> L17
    L40:
        this.locationManager.requestLocationUpdates("passive", 1, 0.0f, this.passiveLocationListener);     // Catch: Exception -> L21
    L24:
        if (this.lastKnownLocation != null) goto L42;
        setLastKnownLocation(this.locationManager.getLastKnownLocation("gps"));     // Catch: Exception -> L29
        if (this.lastKnownLocation != null) goto L43;
        setLastKnownLocation(this.locationManager.getLastKnownLocation("network"));     // Catch: Exception -> L29
        return;
    L43:
        return;
    L29:
        e = move-exception;
        FileLog.e(e);
        return;
    L42:
        return;
    L21:
        e = move-exception;
        FileLog.e(e);
    L17:
        e = move-exception;
        FileLog.e(e);
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L32
    L38:
        this.apiClient.connect();     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        FileLog.e(th);
        goto L34
    }

    private void startService() {
    L10:
        th = move-exception;
        FileLog.e(th);
        return;
    L3:
        if (PermissionRequest.hasPermission("android.permission.ACCESS_COARSE_LOCATION") == false) goto L5;
    L8:
        ApplicationLoader.applicationContext.startService(new Intent(ApplicationLoader.applicationContext, LocationSharingService.class));     // Catch: Throwable -> L10
        return;
    L5:
        if (PermissionRequest.hasPermission("android.permission.ACCESS_FINE_LOCATION") == true) goto L8;
    }

    private void stop(boolean r3) {
        this.started = false;
        if (checkServices() == true) goto L12;
    L8:
        this.locationManager.removeUpdates(this.gpsLocationListener);
        if (r3 == false) goto L14;
        this.locationManager.removeUpdates(this.networkLocationListener);
        this.locationManager.removeUpdates(this.passiveLocationListener);
        return;
    L14:
        return;
    L12:
        ApplicationLoader.getLocationServiceProvider().removeLocationUpdates(this.fusedLocationListener);     // Catch: Throwable -> L6
        this.apiClient.disconnect();     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        FileLog.e(th);
        goto L8
    }

    private void stopService() {
        ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, LocationSharingService.class));
    }

    public void addSharingLocation(TLRPC.Message r7) {
        SharingLocationInfo r0 = new SharingLocationInfo();
        r0.did = r7.dialog_id;
        r0.mid = r7.id;
        TLRPC.MessageMedia r1 = r7.media;
        r0.period = r1.period;
        int r12 = r1.proximity_notification_radius;
        r0.proximityMeters = r12;
        r0.lastSentProximityMeters = r12;
        r0.account = this.currentAccount;
        r0.messageObject = new MessageObject(this.currentAccount, r7, false, false);
        if (r0.period != Integer.MAX_VALUE) goto L5;
        r0.stopTime = Integer.MAX_VALUE;
    L6:
        SharingLocationInfo r72 = this.sharingLocationsMap.get(r0.did);
        this.sharingLocationsMap.put(r0.did, r0);
        if (r72 == null) goto L9;
        this.sharingLocations.remove(r72);
    L9:
        this.sharingLocations.add(r0);
        saveSharingLocation(r0, 0);
        this.lastLocationSendTime = SystemClock.elapsedRealtime() - 25000;
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda17(this, r72, r0));
        return;
    L5:
        r0.stopTime = getConnectionsManager().getCurrentTime() + r0.period;
        goto L6
    }

    public void cleanup() {
        this.sharingLocationsUI.clear();
        this.sharingLocationsMapUI.clear();
        this.locationsCache.clear();
        this.cacheRequests.clear();
        this.lastReadLocationTime.clear();
        stopService();
        Utilities.stageQueue.postRunnable(new LocationController$$ExternalSyntheticLambda4(this));
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r11, int r12, Object... r13) {
        int r2 = 0;
        if (r11 != NotificationCenter.didReceiveNewMessages) goto L37;
        if (((Boolean) r13[2]).booleanValue() == true) goto L110;
        Long r112 = (Long) r13[0];
        long r3 = r112.longValue();
        if (isSharingLocation(r3) == false) goto L111;
        ArrayList<TLRPC.Message> r122 = this.locationsCache.get(r3);
        if (r122 == null) goto L112;
        ArrayList r132 = (ArrayList) r13[1];
        int r0 = 0;
        boolean r32 = false;
    L15:
        if (r0 >= r132.size()) goto L33;
        MessageObject r4 = (MessageObject) r132.get(r0);
        if (r4.isLiveLocation() == false) goto L28;
        int r33 = 0;
    L20:
        if (r33 >= r122.size()) goto L25;
        if (MessageObject.getFromChatId(r122.get(r33)) == r4.getFromChatId()) goto L23;
        r33 = r33 + 1;
        goto L20
    L23:
        r122.set(r33, r4.messageOwner);
    L26:
        r32 = true;
    L32:
        r0 = r0 + 1;
        goto L15
    L25:
        r122.add(r4.messageOwner);
        goto L26
    L28:
        if ((r4.messageOwner.action instanceof TLRPC.TL_messageActionGeoProximityReached) == false) goto L32;
        long r42 = r4.getDialogId();
        if (DialogObject.isUserDialog(r42) == false) goto L32;
        setProximityLocation(r42, 0, false);
        goto L32
    L33:
        if (r32 == false) goto L105;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsCacheChanged, new Object[]{r112, Integer.valueOf(this.currentAccount)});
        return;
    L105:
        return;
    L112:
        return;
    L111:
        return;
    L110:
        return;
    L37:
        if (r11 != NotificationCenter.messagesDeleted) goto L64;
        if (((Boolean) r13[2]).booleanValue() == false) goto L42;
        return;
    L42:
        if (this.sharingLocationsUI.isEmpty() == true) goto L106;
        ArrayList r113 = (ArrayList) r13[0];
        long r123 = ((Long) r13[1]).longValue();
        ArrayList r02 = null;
        int r1 = 0;
    L45:
        if (r1 >= this.sharingLocationsUI.size()) goto L59;
        SharingLocationInfo r34 = this.sharingLocationsUI.get(r1);
        MessageObject r43 = r34.messageObject;
        if (r43 == null) goto L49;
        long r44 = r43.getChannelId();
    L51:
        if (r123 != r44) goto L58;
        if (r113.contains(Integer.valueOf(r34.mid)) == false) goto L58;
        if (r02 != null) goto L57;
        r02 = new ArrayList();
    L57:
        r02.add(Long.valueOf(r34.did));
    L58:
        r1 = r1 + 1;
        goto L45
    L49:
        r44 = 0;
        goto L51
    L59:
        if (r02 != null) goto L61;
        return;
    L61:
        if (r2 >= r02.size()) goto L116;
        removeSharingLocation(((Long) r02.get(r2)).longValue());
        r2 = r2 + 1;
        goto L61
    L116:
        return;
    L106:
        return;
    L64:
        if (r11 != NotificationCenter.replaceMessagesObjects) goto L108;
        Long r114 = (Long) r13[0];
        long r35 = r114.longValue();
        if (isSharingLocation(r35) == false) goto L114;
        ArrayList<TLRPC.Message> r124 = this.locationsCache.get(r35);
        if (r124 == null) goto L115;
        ArrayList r133 = (ArrayList) r13[1];
        int r03 = 0;
        boolean r36 = false;
    L73:
        if (r03 >= r133.size()) goto L86;
        MessageObject r45 = (MessageObject) r133.get(r03);
        int r5 = 0;
    L76:
        if (r5 >= r124.size()) goto L85;
        if (MessageObject.getFromChatId(r124.get(r5)) == r45.getFromChatId()) goto L80;
        r5 = r5 + 1;
        goto L76
    L80:
        if (r45.isLiveLocation() == true) goto L82;
        r124.remove(r5);
    L83:
        r36 = true;
        goto L85
    L82:
        r124.set(r5, r45.messageOwner);
    L85:
        r03 = r03 + 1;
        goto L73
    L86:
        if (r36 == false) goto L109;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsCacheChanged, new Object[]{r114, Integer.valueOf(this.currentAccount)});
        return;
    L109:
        return;
    L115:
        return;
    L114:
        return;
    }

    public Location getLastKnownLocation() {
        return this.lastKnownLocation;
    }

    public SharingLocationInfo getSharingLocationInfo(long r1) {
        return this.sharingLocationsMapUI.get(r1);
    }

    public boolean isSharingLocation(long r1) {
        if (this.sharingLocationsMapUI.indexOfKey(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void loadLiveLocations(long r4) {
        if (this.cacheRequests.indexOfKey(r4) < 0) goto L5;
        return;
    L5:
        this.cacheRequests.put(r4, Boolean.TRUE);
        TLRPC.TL_messages_getRecentLocations r0 = new TLRPC.TL_messages_getRecentLocations();
        r0.peer = getMessagesController().getInputPeer(r4);
        r0.limit = 100;
        getConnectionsManager().sendRequest(r0, new LocationController$$ExternalSyntheticLambda23(this, r4));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [org.telegram.tgnet.TLRPC$TL_channels_readMessageContents] */
    /* JADX WARN: Type inference failed for: r1v6, types: [org.telegram.tgnet.TLRPC$TL_messages_readMessageContents] */
    /* JADX WARN: Type inference failed for: r1v7, types: [org.telegram.tgnet.TLObject] */
    /* JADX WARN: Type inference failed for: r7v2, types: [org.telegram.tgnet.ConnectionsManager] */
    public void markLiveLoactionsAsRead(long r7) {
        if (DialogObject.isEncryptedDialog(r7) == true) goto L31;
        ArrayList<TLRPC.Message> r0 = this.locationsCache.get(r7);
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.isEmpty() == true) goto L32;
        Integer r1 = this.lastReadLocationTime.get(r7);
        int r2 = (int) (SystemClock.elapsedRealtime() / 1000);
        if (r1 != null) goto L13;
    L15:
        this.lastReadLocationTime.put(r7, Integer.valueOf(r2));
        int r22 = 0;
        if (DialogObject.isChatDialog(r7) == false) goto L23;
        long r72 = -r7;
        if (ChatObject.isChannel(r72, this.currentAccount) == false) goto L23;
        ?? r12 = new TLRPC.TL_channels_readMessageContents();
        int r3 = r0.size();
    L20:
        if (r22 >= r3) goto L22;
        r12.id.add(Integer.valueOf(r0.get(r22).id));
        r22 = r22 + 1;
        goto L20
    L22:
        r12.channel = getMessagesController().getInputChannel(r72);
    L26:
        getConnectionsManager().sendRequest(r12, new LocationController$$ExternalSyntheticLambda29(this));
        return;
    L23:
        r12 = new TLRPC.TL_messages_readMessageContents();
        int r73 = r0.size();
    L24:
        if (r22 >= r73) goto L26;
        r12.id.add(Integer.valueOf(r0.get(r22).id));
        r22 = r22 + 1;
        goto L24
    L13:
        if ((r1.intValue() + 60) <= r2) goto L15;
        return;
    L32:
        return;
    }

    @Override // org.telegram.messenger.ILocationServiceProvider.IAPIConnectionCallbacks
    public void onConnected(Bundle r3) {
        this.wasConnectedToPlayServices = true;
        ApplicationLoader.getLocationServiceProvider().checkLocationSettings(this.locationRequest, new LocationController$$ExternalSyntheticLambda5(this));     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        FileLog.e(th);
    }

    @Override // org.telegram.messenger.ILocationServiceProvider.IAPIOnConnectionFailedListener
    public void onConnectionFailed() {
        if (this.wasConnectedToPlayServices == true) goto L10;
        this.servicesAvailable = Boolean.FALSE;
        if (this.started == false) goto L9;
        this.started = false;
        start();
        return;
    L9:
        return;
    }

    @Override // org.telegram.messenger.ILocationServiceProvider.IAPIConnectionCallbacks
    public void onConnectionSuspended(int r1) {
    }

    public void removeAllLocationSharings() {
        Utilities.stageQueue.postRunnable(new LocationController$$ExternalSyntheticLambda30(this));
    }

    public void removeSharingLocation(long r3) {
        Utilities.stageQueue.postRunnable(new LocationController$$ExternalSyntheticLambda2(this, r3));
    }

    public void setMapLocation(Location r6, boolean r7) {
        if (r6 != null) goto L4;
        return;
    L4:
        this.lastLocationByMaps = true;
        if (r7 == true) goto L14;
        Location r72 = this.lastKnownLocation;
        if (r72 == null) goto L12;
        if (r72.distanceTo(r6) >= 20.0f) goto L14;
    L12:
        if (this.locationSentSinceLastMapUpdate == false) goto L15;
        this.lastLocationSendTime = SystemClock.elapsedRealtime() - NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        this.locationSentSinceLastMapUpdate = false;
    L15:
        setLastKnownLocation(r6);
        return;
    L14:
        this.lastLocationSendTime = SystemClock.elapsedRealtime() - NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS;
        this.locationSentSinceLastMapUpdate = false;
        goto L15
    }

    public void setNewLocationEndWatchTime() {
        if (this.sharingLocations.isEmpty() == false) goto L5;
        return;
    L5:
        this.locationEndWatchTime = SystemClock.elapsedRealtime() + 65000;
        start();
    }

    public boolean setProximityLocation(long r4, int r6, boolean r7) {
        SharingLocationInfo r0 = this.sharingLocationsMapUI.get(r4);
        if (r0 == null) goto L5;
        r0.proximityMeters = r6;
    L5:
        getMessagesStorage().getStorageQueue().postRunnable(new LocationController$$ExternalSyntheticLambda0(this, r6, r4));
        if (r7 == false) goto L8;
        Utilities.stageQueue.postRunnable(new LocationController$$ExternalSyntheticLambda1(this));
    L8:
        if (r0 == null) goto L11;
        return true;
    L11:
        return false;
    }

    public void startFusedLocationRequest(boolean r3) {
        Utilities.stageQueue.postRunnable(new LocationController$$ExternalSyntheticLambda19(this, r3));
    }

    public void update() {
        getUserConfig();
        boolean r1 = false;
        if (this.sharingLocations.isEmpty() == true) goto L12;
        int r0 = 0;
    L6:
        if (r0 >= this.sharingLocations.size()) goto L12;
        SharingLocationInfo r3 = this.sharingLocations.get(r0);
        if (r3.stopTime > getConnectionsManager().getCurrentTime()) goto L10;
        this.sharingLocations.remove(r0);
        this.sharingLocationsMap.remove(r3.did);
        saveSharingLocation(r3, 1);
        AndroidUtilities.runOnUIThread(new LocationController$$ExternalSyntheticLambda3(this, r3));
        r0 = r0 - 1;
    L10:
        r0 = r0 + 1;
    L12:
        if (this.started == false) goto L25;
        long r32 = SystemClock.elapsedRealtime();
        if (this.lastLocationByMaps == false) goto L16;
    L19:
        this.lastLocationByMaps = false;
        this.locationSentSinceLastMapUpdate = true;
        if ((SystemClock.elapsedRealtime() - this.lastLocationSendTime) <= 2000) goto L22;
        r1 = true;
    L22:
        this.lastLocationStartTime = r32;
        this.lastLocationSendTime = SystemClock.elapsedRealtime();
        broadcastLastKnownLocation(r1);
        return;
    L16:
        if (Math.abs(this.lastLocationStartTime - r32) > NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L19;
        if (shouldSendLocationNow() == true) goto L19;
        return;
    L25:
        if (this.sharingLocations.isEmpty() == false) goto L27;
        return;
    L27:
        if (Math.abs(this.lastLocationSendTime - SystemClock.elapsedRealtime()) <= NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) goto L35;
        this.lastLocationStartTime = SystemClock.elapsedRealtime();
        start();
        return;
    }
}
