package com.google.android.gms.cast.internal;

import android.os.SystemClock;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.AdBreakStatus;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLiveSeekableRange;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.internal.media.MediaCommon;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.time.DurationKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class zzar extends zzd {
    public static final String zzb = null;
    final zzav zzc;
    final zzav zzd;
    final zzav zze;
    final zzav zzf;
    final zzav zzg;
    final zzav zzh;
    final zzav zzi;
    final zzav zzj;
    final zzav zzk;
    final zzav zzl;
    final zzav zzm;
    final zzav zzn;
    final zzav zzo;
    final zzav zzp;
    final zzav zzq;
    final zzav zzr;
    final zzav zzs;
    final zzav zzt;
    final zzav zzu;
    private long zzv;
    private MediaStatus zzw;
    private Long zzx;
    private zzao zzy;
    private int zzz;

    static {
        int r0 = CastUtils.zza;
        zzb = "urn:x-cast:com.google.cast.media";
    }

    public zzar(String r22) {
        super(zzb, "MediaControlChannel", null);
        this.zzz = -1;
        zzav r1 = new zzav(DurationKt.MILLIS_IN_DAY, "load");
        this.zzc = r1;
        zzav r4 = new zzav(DurationKt.MILLIS_IN_DAY, "pause");
        this.zzd = r4;
        zzav r5 = new zzav(DurationKt.MILLIS_IN_DAY, "play");
        this.zze = r5;
        zzav r6 = new zzav(DurationKt.MILLIS_IN_DAY, "stop");
        this.zzf = r6;
        zzav r7 = new zzav(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, "seek");
        this.zzg = r7;
        zzav r8 = new zzav(DurationKt.MILLIS_IN_DAY, MediaRouteProviderProtocol.CLIENT_DATA_VOLUME);
        this.zzh = r8;
        zzav r9 = new zzav(DurationKt.MILLIS_IN_DAY, "mute");
        this.zzi = r9;
        zzav r10 = new zzav(DurationKt.MILLIS_IN_DAY, "status");
        this.zzj = r10;
        zzav r11 = new zzav(DurationKt.MILLIS_IN_DAY, "activeTracks");
        this.zzk = r11;
        zzav r12 = new zzav(DurationKt.MILLIS_IN_DAY, "trackStyle");
        this.zzl = r12;
        zzav r13 = new zzav(DurationKt.MILLIS_IN_DAY, "queueInsert");
        this.zzm = r13;
        zzav r14 = new zzav(DurationKt.MILLIS_IN_DAY, "queueUpdate");
        this.zzn = r14;
        zzav r15 = new zzav(DurationKt.MILLIS_IN_DAY, "queueRemove");
        this.zzo = r15;
        zzav r142 = new zzav(DurationKt.MILLIS_IN_DAY, "queueReorder");
        this.zzp = r142;
        zzav r152 = new zzav(DurationKt.MILLIS_IN_DAY, "queueFetchItemIds");
        this.zzq = r152;
        zzav r143 = new zzav(DurationKt.MILLIS_IN_DAY, "queueFetchItemRange");
        this.zzs = r143;
        this.zzr = new zzav(DurationKt.MILLIS_IN_DAY, "queueFetchItems");
        zzav r144 = new zzav(DurationKt.MILLIS_IN_DAY, "setPlaybackRate");
        this.zzt = r144;
        zzav r153 = new zzav(DurationKt.MILLIS_IN_DAY, "skipAd");
        this.zzu = r153;
        zzc(r1);
        zzc(r4);
        zzc(r5);
        zzc(r6);
        zzc(r7);
        zzc(r8);
        zzc(r9);
        zzc(r10);
        zzc(r11);
        zzc(r12);
        zzc(r13);
        zzc(r14);
        zzc(r15);
        zzc(r142);
        zzc(r152);
        zzc(r143);
        zzc(r143);
        zzc(r144);
        zzc(r153);
        zzZ();
    }

    private final long zzR(double r5, long r7, long r9) {
        long r0 = SystemClock.elapsedRealtime() - this.zzv;
        if (r0 >= 0) goto L6;
        r0 = 0;
    L6:
        if (r0 != 0) goto L8;
        return r7;
    L8:
        long r72 = r7 + ((long) (r0 * r5));
        if (r9 <= 0) goto L14;
        if (r72 <= r9) goto L14;
        return r9;
    L14:
        if (r72 < 0) goto L16;
        return r72;
    L16:
        return 0;
    }

    private final boolean zzS() {
        if (this.zzz == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    private final void zzT(JSONObject r3, String r4) {
        if (r3.has("sequenceNumber") == false) goto L6;
        this.zzz = r3.optInt("sequenceNumber", -1);
        return;
    L6:
        this.zza.w(r4.concat(" message is missing a sequence number."), new Object[0]);
    }

    private static int[] zzU(JSONArray r3) {
        if (r3 != null) goto L5;
        return null;
    L5:
        int[] r0 = new int[r3.length()];
        int r1 = 0;
    L7:
        if (r1 >= r3.length()) goto L9;
        r0[r1] = r3.getInt(r1);
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    }

    private final void zzV() {
        zzao r0 = this.zzy;
        if (r0 == null) goto L6;
        r0.zza();
        return;
    }

    private final void zzW() {
        zzao r0 = this.zzy;
        if (r0 == null) goto L6;
        r0.zzb();
        return;
    }

    private final void zzX() {
        zzao r0 = this.zzy;
        if (r0 == null) goto L6;
        r0.zzc();
        return;
    }

    private final void zzY() {
        zzao r0 = this.zzy;
        if (r0 == null) goto L6;
        r0.zzd();
        return;
    }

    private final void zzZ() {
        this.zzv = 0;
        this.zzw = null;
        Iterator r2 = zzb().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        ((zzav) r2.next()).zze(CastStatusCodes.CANCELED);
        goto L4
    }

    private static zzaq zzaa(JSONObject r4) {
        MediaError r0 = MediaError.zza(r4);
        zzaq r1 = new zzaq();
        int r2 = CastUtils.zza;
        if (r4.has("customData") == false) goto L5;
        JSONObject r42 = r4.optJSONObject("customData");
    L6:
        r1.zza = r42;
        r1.zzb = r0;
        return r1;
    L5:
        r42 = null;
        goto L6
    }

    public final MediaStatus zzA() {
        return this.zzw;
    }

    public final MediaInfo zzB() {
        MediaStatus r0 = this.zzw;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getMediaInfo();
    }

    public final long zzC(zzat r8, MediaQueueItem[] r9, int r10, int r11, long r12, JSONObject r14) {
        if (r9 == null) goto L36;
        int r3 = r9.length;
        if (r3 == 0) goto L36;
        if (r10 < 0) goto L34;
        if (r10 >= r3) goto L34;
        if (r12 != (-1)) goto L11;
    L15:
        JSONObject r1 = new JSONObject();
        long r4 = zzg();
        this.zzc.zza(r4, r8);
        r1.put("requestId", r4);     // Catch: JSONException -> L38
        r1.put("type", "QUEUE_LOAD");     // Catch: JSONException -> L38
        JSONArray r82 = new JSONArray();     // Catch: JSONException -> L38
        int r2 = 0;
    L18:
        if (r2 >= r9.length) goto L20;
        r82.put(r2, r9[r2].toJson());     // Catch: JSONException -> L38
        r2 = r2 + 1;     // Catch: JSONException -> L38
        goto L18
    L20:
        r1.put(PluginsConstants.Settings.ITEMS, r82);     // Catch: JSONException -> L38
        String r83 = MediaCommon.zza(Integer.valueOf(r11));     // Catch: JSONException -> L38
        if (r83 == null) goto L30;
        r1.put("repeatMode", r83);     // Catch: JSONException -> L38
        r1.put("startIndex", r10);     // Catch: JSONException -> L38
        if (r12 == (-1)) goto L25;
        r1.put("currentTime", CastUtils.millisecToSec(r12));     // Catch: JSONException -> L38
    L25:
        if (r14 == null) goto L28;
        r1.put("customData", r14);     // Catch: JSONException -> L38
    L28:
        if (zzS() == false) goto L32;
        r1.put("sequenceNumber", this.zzz);     // Catch: JSONException -> L38
        goto L32
    L30:
        StringBuilder r102 = new StringBuilder(String.valueOf(r11).length() + 21);     // Catch: JSONException -> L38
        r102.append("Invalid repeat mode: ");     // Catch: JSONException -> L38
        r102.append(r11);     // Catch: JSONException -> L38
        throw new IllegalArgumentException(r102.toString());     // Catch: JSONException -> L38
    L32:
        zzf(r1.toString(), r4, null);
        return r4;
    L11:
        if (r12 >= 0) goto L15;
        zzar$$ExternalSyntheticBUOutline0.m(String.valueOf(r12).length() + 34, "playPosition can not be negative: ", r12);
        return 0;
    L34:
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r10).length() + 20, "Invalid startIndex: ", r10);
        return 0;
    L36:
        f$$ExternalSyntheticBUOutline1.m("items must not be null or empty.");
        return 0;
    }

    public final long zzD(zzat r8, MediaQueueItem[] r9, int r10, int r11, int r12, long r13, JSONObject r15) {
        if (r9 != null) goto L5;
    L33:
        f$$ExternalSyntheticBUOutline1.m("itemsToInsert must not be null or empty.");
        return 0;
    L5:
        if (r9.length == 0) goto L33;
        if (r13 != (-1)) goto L9;
    L13:
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L35
        r0.put("type", "QUEUE_INSERT");     // Catch: JSONException -> L35
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L35
        JSONArray r3 = new JSONArray();     // Catch: JSONException -> L35
        int r5 = 0;
    L16:
        if (r5 >= r9.length) goto L18;
        r3.put(r5, r9[r5].toJson());     // Catch: JSONException -> L35
        r5 = r5 + 1;     // Catch: JSONException -> L35
        goto L16
    L18:
        r0.put(PluginsConstants.Settings.ITEMS, r3);     // Catch: JSONException -> L35
        if (r10 == 0) goto L22;
        r0.put("insertBefore", r10);     // Catch: JSONException -> L35
    L22:
        if (r12 == (-1)) goto L24;
        r0.put("currentItemIndex", 0);     // Catch: JSONException -> L35
    L24:
        if (r13 == (-1)) goto L26;
        r0.put("currentTime", CastUtils.millisecToSec(r13));     // Catch: JSONException -> L35
    L26:
        if (r15 == null) goto L29;
        r0.put("customData", r15);     // Catch: JSONException -> L35
    L29:
        if (zzS() == false) goto L31;
        r0.put("sequenceNumber", this.zzz);     // Catch: JSONException -> L35
    L31:
        zzf(r0.toString(), r1, null);
        this.zzm.zza(r1, new zzan(this, r8));
        return r1;
    L9:
        if (r13 >= 0) goto L13;
        zzar$$ExternalSyntheticBUOutline0.m(String.valueOf(r13).length() + 34, "playPosition can not be negative: ", r13);
        return 0;
    }

    public final long zzE(zzat r15, int r16, long r17, MediaQueueItem[] r19, int r20, Boolean r21, Integer r22, JSONObject r23) {
        if (r17 != (-1)) goto L5;
    L9:
        JSONObject r8 = new JSONObject();
        long r9 = zzg();
        r8.put("requestId", r9);     // Catch: JSONException -> L37
        r8.put("type", "QUEUE_UPDATE");     // Catch: JSONException -> L37
        r8.put("mediaSessionId", zzM());     // Catch: JSONException -> L37
        if (r16 == 0) goto L13;
        r8.put("currentItemId", r16);     // Catch: JSONException -> L37
    L13:
        if (r20 == 0) goto L15;
        r8.put("jump", r20);     // Catch: JSONException -> L37
    L15:
        if (r19 != null) goto L17;
    L23:
        if (r21 == null) goto L25;
        r8.put("shuffle", r21);     // Catch: JSONException -> L37
    L25:
        String r0 = MediaCommon.zza(r22);     // Catch: JSONException -> L37
        if (r0 == null) goto L28;
        r8.put("repeatMode", r0);     // Catch: JSONException -> L37
    L28:
        if (r17 == (-1)) goto L30;
        r8.put("currentTime", CastUtils.millisecToSec(r17));     // Catch: JSONException -> L37
    L30:
        if (r23 == null) goto L33;
        r8.put("customData", r23);     // Catch: JSONException -> L37
    L33:
        if (zzS() == false) goto L35;
        r8.put("sequenceNumber", this.zzz);     // Catch: JSONException -> L37
        goto L35
    L17:
        if (r19.length <= 0) goto L23;
        JSONArray r02 = new JSONArray();     // Catch: JSONException -> L37
        int r4 = 0;
    L20:
        if (r4 >= r19.length) goto L22;
        r02.put(r4, r19[r4].toJson());     // Catch: JSONException -> L37
        r4 = r4 + 1;     // Catch: JSONException -> L37
        goto L20
    L22:
        r8.put(PluginsConstants.Settings.ITEMS, r02);     // Catch: JSONException -> L37
    L35:
        zzf(r8.toString(), r9, null);
        this.zzn.zza(r9, new zzan(this, r15));
        return r9;
    L5:
        if (r17 >= 0) goto L9;
        zzar$$ExternalSyntheticBUOutline0.m(String.valueOf(r17).length() + 33, "playPosition cannot be negative: ", r17);
        return 0;
    }

    public final long zzF(zzat r7, int[] r8, JSONObject r9) {
        if (r8 != null) goto L4;
    L18:
        f$$ExternalSyntheticBUOutline1.m("itemIdsToRemove must not be null or empty.");
        return 0;
    L4:
        if (r8.length == 0) goto L18;
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L20
        r0.put("type", "QUEUE_REMOVE");     // Catch: JSONException -> L20
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L20
        JSONArray r3 = new JSONArray();     // Catch: JSONException -> L20
        int r4 = 0;
    L8:
        if (r4 >= r8.length) goto L10;
        r3.put(r4, r8[r4]);     // Catch: JSONException -> L20
        r4 = r4 + 1;     // Catch: JSONException -> L20
        goto L8
    L10:
        r0.put("itemIds", r3);     // Catch: JSONException -> L20
        if (r9 == null) goto L14;
        r0.put("customData", r9);     // Catch: JSONException -> L20
    L14:
        if (zzS() == false) goto L16;
        r0.put("sequenceNumber", this.zzz);     // Catch: JSONException -> L20
    L16:
        zzf(r0.toString(), r1, null);
        this.zzo.zza(r1, new zzan(this, r7));
        return r1;
    }

    public final long zzG(zzat r7, int[] r8, int r9, JSONObject r10) {
        if (r8 != null) goto L4;
    L20:
        f$$ExternalSyntheticBUOutline1.m("itemIdsToReorder must not be null or empty.");
        return 0;
    L4:
        if (r8.length == 0) goto L20;
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L22
        r0.put("type", "QUEUE_REORDER");     // Catch: JSONException -> L22
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L22
        JSONArray r3 = new JSONArray();     // Catch: JSONException -> L22
        int r4 = 0;
    L8:
        if (r4 >= r8.length) goto L10;
        r3.put(r4, r8[r4]);     // Catch: JSONException -> L22
        r4 = r4 + 1;     // Catch: JSONException -> L22
        goto L8
    L10:
        r0.put("itemIds", r3);     // Catch: JSONException -> L22
        if (r9 == 0) goto L13;
        r0.put("insertBefore", r9);     // Catch: JSONException -> L22
    L13:
        if (r10 == null) goto L16;
        r0.put("customData", r10);     // Catch: JSONException -> L22
    L16:
        if (zzS() == false) goto L18;
        r0.put("sequenceNumber", this.zzz);     // Catch: JSONException -> L22
    L18:
        zzf(r0.toString(), r1, null);
        this.zzp.zza(r1, new zzan(this, r7));
        return r1;
    }

    public final long zzH(zzat r7) {
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L6
        r0.put("type", "QUEUE_GET_ITEM_IDS");     // Catch: JSONException -> L6
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L6
    L4:
        zzf(r0.toString(), r1, null);
        this.zzq.zza(r1, r7);
        return r1;
    }

    public final long zzI(zzat r7, int r8, int r9, int r10) {
        if (r9 <= 0) goto L6;
        if (r10 != 0) goto L6;
        r10 = 0;
    L8:
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L18
        r0.put("type", "QUEUE_GET_ITEM_RANGE");     // Catch: JSONException -> L18
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L18
        r0.put("itemId", r8);     // Catch: JSONException -> L18
        if (r9 <= 0) goto L12;
        r0.put("nextCount", r9);     // Catch: JSONException -> L18
    L12:
        if (r10 <= 0) goto L14;
        r0.put("prevCount", r10);     // Catch: JSONException -> L18
    L14:
        zzf(r0.toString(), r1, null);
        this.zzs.zza(r1, r7);
        return r1;
    L6:
        if (r9 != 0) goto L16;
        if (r10 > 0) goto L8;
    L16:
        f$$ExternalSyntheticBUOutline1.m("Exactly one of nextCount and prevCount must be positive and the other must be zero");
        return 0;
    }

    public final long zzJ(zzat r8, int[] r9) {
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L9
        r0.put("type", "QUEUE_GET_ITEMS");     // Catch: JSONException -> L9
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L9
        JSONArray r3 = new JSONArray();     // Catch: JSONException -> L9
        int r4 = r9.length;     // Catch: JSONException -> L9
        int r5 = 0;
    L4:
        if (r5 >= r4) goto L6;
        r3.put(r9[r5]);     // Catch: JSONException -> L9
        r5 = r5 + 1;     // Catch: JSONException -> L9
        goto L4
    L6:
        r0.put("itemIds", r3);     // Catch: JSONException -> L9
    L7:
        zzf(r0.toString(), r1, null);
        this.zzr.zza(r1, r8);
        return r1;
    }

    public final long zzK(String r5, List r6) {
        long r0 = zzg();
        JSONObject r62 = new JSONObject();
        r62.put("requestId", r0);     // Catch: JSONException -> L6
        r62.put("type", "PRECACHE");     // Catch: JSONException -> L6
        r62.put("precacheData", r5);     // Catch: JSONException -> L6
    L4:
        zzf(r62.toString(), r0, null);
        return r0;
    }

    public final void zzL(String r12) {
        Logger r2 = this.zza;
        r2.d("message received: %s", new Object[]{r12});
        JSONObject r1 = new JSONObject(r12);     // Catch: JSONException -> L15
        String r3 = r1.getString("type");     // Catch: JSONException -> L15
        long r4 = r1.optLong("requestId", -1);     // Catch: JSONException -> L15
        int r10 = 0;
        switch(r3.hashCode()) {
            case -1830647528: goto L144;
            case -1790231854: goto L133;
            case -1125000185: goto L126;
            case -262628938: goto L122;
            case 66247144: goto L112;
            case 154411710: goto L83;
            case 431600379: goto L76;
            case 823510221: goto L18;
            case 2107149050: goto L8;
            default: goto L177;
        };
    L177:
        return;
    L8:
        if (r3.equals("QUEUE_ITEM_IDS") == false) goto L168;
        this.zzq.zzd(r4, 0, null);     // Catch: JSONException -> L15
        zzT(r1, "QUEUE_ITEM_IDS");     // Catch: JSONException -> L15
        if (this.zzy == null) goto L136;
        int[] r0 = zzU(r1.getJSONArray("itemIds"));     // Catch: JSONException -> L15
        if (r0 == null) goto L160;
        this.zzy.zzg(r0);     // Catch: JSONException -> L15
        return;
    L160:
        return;
    L136:
        return;
    L168:
        return;
    L18:
        if (r3.equals("MEDIA_STATUS") == false) goto L169;
        JSONArray r02 = r1.getJSONArray("status");     // Catch: JSONException -> L15
        if (r02.length() <= 0) goto L69;
        JSONObject r03 = r02.getJSONObject(0);     // Catch: JSONException -> L15
        boolean r13 = this.zzc.zzc(r4);     // Catch: JSONException -> L15
        zzav r22 = this.zzh;     // Catch: JSONException -> L15
        boolean r6 = true;
        if (r22.zzb() == true) goto L24;
    L27:
        zzav r23 = this.zzi;     // Catch: JSONException -> L15
        if (r23.zzb() == true) goto L30;
    L32:
        int r24 = 0;
    L33:
        if (r13 == true) goto L38;
        MediaStatus r14 = this.zzw;     // Catch: JSONException -> L15
        if (r14 == null) goto L38;
        int r04 = r14.zzb(r03, r24);     // Catch: JSONException -> L15
    L40:
        if ((r04 & 1) == 0) goto L42;
        this.zzv = SystemClock.elapsedRealtime();     // Catch: JSONException -> L15
        this.zzz = -1;     // Catch: JSONException -> L15
        boolean r15 = true;
    L44:
        if ((r04 & 2) == 0) goto L46;
        this.zzv = SystemClock.elapsedRealtime();     // Catch: JSONException -> L15
    L48:
        if ((r04 & 128) == 0) goto L51;
        this.zzv = SystemClock.elapsedRealtime();     // Catch: JSONException -> L15
    L51:
        if ((r04 & 4) == 0) goto L54;
        zzW();     // Catch: JSONException -> L15
    L54:
        if ((r04 & 8) == 0) goto L57;
        zzX();     // Catch: JSONException -> L15
    L57:
        if ((r04 & 16) == 0) goto L60;
        zzY();     // Catch: JSONException -> L15
    L60:
        if ((r04 & 32) == 0) goto L65;
        this.zzv = SystemClock.elapsedRealtime();     // Catch: JSONException -> L15
        zzao r16 = this.zzy;     // Catch: JSONException -> L15
        if (r16 == null) goto L65;
        r16.zze();     // Catch: JSONException -> L15
    L65:
        if ((r04 & 64) == 0) goto L67;
        this.zzv = SystemClock.elapsedRealtime();     // Catch: JSONException -> L15
    L68:
        zzV();     // Catch: JSONException -> L15
    L70:
        Iterator r05 = zzb().iterator();     // Catch: JSONException -> L15
    L72:
        if (r05.hasNext() == false) goto L165;
        ((zzav) r05.next()).zzd(r4, 0, null);     // Catch: JSONException -> L15
        goto L72
    L165:
        return;
    L67:
        if (r6 == false) goto L70;
    L46:
        r6 = r15;
        goto L48
    L42:
        r15 = false;
    L38:
        this.zzw = new MediaStatus(r03);     // Catch: JSONException -> L15
        this.zzv = SystemClock.elapsedRealtime();     // Catch: JSONException -> L15
        r04 = 127;
        goto L40
    L30:
        if (r23.zzc(r4) == true) goto L32;
    L26:
        r24 = 1;
        goto L33
    L24:
        if (r22.zzc(r4) == false) goto L26;
    L69:
        this.zzw = null;     // Catch: JSONException -> L15
        zzV();     // Catch: JSONException -> L15
        zzW();     // Catch: JSONException -> L15
        zzX();     // Catch: JSONException -> L15
        zzY();     // Catch: JSONException -> L15
        goto L70
    L169:
        return;
    L76:
        if (r3.equals(MediaError.ERROR_TYPE_INVALID_PLAYER_STATE) == false) goto L170;
        r2.w("received unexpected error: Invalid Player State.", new Object[0]);     // Catch: JSONException -> L15
        Iterator r06 = zzb().iterator();     // Catch: JSONException -> L15
    L79:
        if (r06.hasNext() == false) goto L166;
        ((zzav) r06.next()).zzd(r4, 2100, zzaa(r1));     // Catch: JSONException -> L15
        goto L79
    L166:
        return;
    L170:
        return;
    L83:
        if (r3.equals("QUEUE_CHANGE") == false) goto L171;
        this.zzs.zzd(r4, 0, null);     // Catch: JSONException -> L15
        zzT(r1, "QUEUE_CHANGE");     // Catch: JSONException -> L15
        if (this.zzy == null) goto L161;
        String r25 = r1.getString("changeType");     // Catch: JSONException -> L15
        int[] r32 = zzU(r1.getJSONArray("itemIds"));     // Catch: JSONException -> L15
        int r42 = r1.optInt("insertBefore", 0);     // Catch: JSONException -> L15
        if (r32 != null) goto L89;
        return;
    L89:
        switch(r25.hashCode()) {
            case -2130463047: goto L108;
            case -1881281404: goto L104;
            case -1785516855: goto L96;
            case 1122976047: goto L92;
            default: goto L159;
        };
    L159:
        return;
    L92:
        if (r25.equals("ITEMS_CHANGE") == false) goto L155;
        this.zzy.zzi(r32);     // Catch: JSONException -> L15
        return;
    L155:
        return;
    L96:
        if (r25.equals("UPDATE") == false) goto L156;
        int[] r26 = zzU(r1.getJSONArray("itemIds"));     // Catch: JSONException -> L15
        Preconditions.checkNotNull(r26, "A list of item IDs is expected in a QUEUE UPDATE message.");     // Catch: JSONException -> L15
        JSONArray r33 = r1.optJSONArray("reorderItemIds");     // Catch: JSONException -> L15
        if (r33 == null) goto L101;
        List r27 = CastUtils.zzf(r26);     // Catch: JSONException -> L15
        int r07 = r1.optInt("insertBefore", 0);     // Catch: JSONException -> L15
        this.zzy.zzl(r27, CastUtils.zzf((int[]) Preconditions.checkNotNull(zzU(r33))), r07);     // Catch: JSONException -> L15
        return;
    L101:
        this.zzy.zzg(r26);     // Catch: JSONException -> L15
        return;
    L156:
        return;
    L104:
        if (r25.equals("REMOVE") == false) goto L157;
        this.zzy.zzj(r32);     // Catch: JSONException -> L15
        return;
    L157:
        return;
    L108:
        if (r25.equals("INSERT") == false) goto L158;
        this.zzy.zzh(r32, r42);     // Catch: JSONException -> L15
        return;
    L158:
        return;
    L161:
        return;
    L171:
        return;
    L112:
        if (r3.equals(MediaError.ERROR_TYPE_ERROR) == false) goto L172;
        Iterator r08 = zzb().iterator();     // Catch: JSONException -> L15
    L115:
        if (r08.hasNext() == false) goto L118;
        ((zzav) r08.next()).zzd(r4, 2100, zzaa(r1));     // Catch: JSONException -> L15
        goto L115
    L118:
        if (this.zzy == null) goto L163;
        this.zzy.zzf(MediaError.zza(r1));     // Catch: JSONException -> L15
        return;
    L163:
        return;
    L172:
        return;
    L122:
        if (r3.equals(MediaError.ERROR_TYPE_LOAD_FAILED) == false) goto L173;
        this.zzc.zzd(r4, 2100, zzaa(r1));     // Catch: JSONException -> L15
        return;
    L173:
        return;
    L126:
        if (r3.equals("INVALID_REQUEST") == false) goto L174;
        r2.w("received unexpected error: Invalid Request.", new Object[0]);     // Catch: JSONException -> L15
        Iterator r09 = zzb().iterator();     // Catch: JSONException -> L15
    L129:
        if (r09.hasNext() == false) goto L167;
        ((zzav) r09.next()).zzd(r4, CastStatusCodes.INVALID_REQUEST, zzaa(r1));     // Catch: JSONException -> L15
        goto L129
    L167:
        return;
    L174:
        return;
    L133:
        if (r3.equals("QUEUE_ITEMS") == false) goto L175;
        this.zzr.zzd(r4, 0, null);     // Catch: JSONException -> L15
        zzT(r1, "QUEUE_ITEMS");     // Catch: JSONException -> L15
        if (this.zzy == null) goto L164;
        JSONArray r010 = r1.getJSONArray(PluginsConstants.Settings.ITEMS);     // Catch: JSONException -> L15
        MediaQueueItem[] r17 = new MediaQueueItem[r010.length()];     // Catch: JSONException -> L15
    L139:
        if (r10 >= r010.length()) goto L141;
        r17[r10] = new MediaQueueItem.Builder(r010.getJSONObject(r10)).build();     // Catch: JSONException -> L15
        r10 = r10 + 1;     // Catch: JSONException -> L15
        goto L139
    L141:
        this.zzy.zzk(r17);     // Catch: JSONException -> L15
        return;
    L164:
        return;
    L175:
        return;
    L144:
        if (r3.equals(MediaError.ERROR_TYPE_LOAD_CANCELLED) == false) goto L176;
        this.zzc.zzd(r4, RemoteMediaPlayer.STATUS_CANCELED, zzaa(r1));     // Catch: JSONException -> L15
        return;
    L176:
        return;
    L15:
        e = move-exception;
        this.zza.w("Message is malformed (%s); ignoring: %s", new Object[]{e.getMessage(), r12});
    }

    public final long zzM() {
        MediaStatus r2 = this.zzw;
        if (r2 == null) goto L7;
        return r2.zza();
    L7:
        throw new zzap();
    }

    public final void zzN(long r3, int r5) {
        Iterator r2 = zzb().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        ((zzav) r2.next()).zzd(r3, r5, null);
        goto L4
    }

    public final /* synthetic */ void zzO(Long r1) {
        this.zzx = null;
    }

    public final /* synthetic */ zzao zzP() {
        return this.zzy;
    }

    public final /* synthetic */ int zzQ() {
        return this.zzz;
    }

    @Override // com.google.android.gms.cast.internal.zzq
    public final void zzh() {
        zza();
        zzZ();
    }

    public final void zzi(zzao r1) {
        this.zzy = r1;
    }

    public final long zzj(zzat r5, MediaLoadRequestData r6) {
        if (r6.getMediaInfo() == null) goto L5;
    L9:
        JSONObject r62 = r6.toJson();
        if (r62 == null) goto L15;
        long r0 = zzg();
        r62.put("requestId", r0);     // Catch: JSONException -> L17
        r62.put("type", "LOAD");     // Catch: JSONException -> L17
    L13:
        zzf(r62.toString(), r0, null);
        this.zzc.zza(r0, r5);
        return r0;
    L15:
        f$$ExternalSyntheticBUOutline1.m("Failed to jsonify the load request due to malformed request");
        return 0;
    L5:
        if (r6.getQueueData() != null) goto L9;
        f$$ExternalSyntheticBUOutline1.m("MediaInfo and MediaQueueData should not be both null");
        return 0;
    }

    public final long zzk(zzat r7, JSONObject r8) {
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L8
        r0.put("type", "PAUSE");     // Catch: JSONException -> L8
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L8
        if (r8 == null) goto L6;
        r0.put("customData", r8);     // Catch: JSONException -> L8
    L6:
        zzf(r0.toString(), r1, null);
        this.zzd.zza(r1, r7);
        return r1;
    }

    public final long zzl(zzat r7, JSONObject r8) {
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L8
        r0.put("type", "STOP");     // Catch: JSONException -> L8
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L8
        if (r8 == null) goto L6;
        r0.put("customData", r8);     // Catch: JSONException -> L8
    L6:
        zzf(r0.toString(), r1, null);
        this.zzf.zza(r1, r7);
        return r1;
    }

    public final long zzm(zzat r7, JSONObject r8) {
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L8
        r0.put("type", "PLAY");     // Catch: JSONException -> L8
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L8
        if (r8 == null) goto L6;
        r0.put("customData", r8);     // Catch: JSONException -> L8
    L6:
        zzf(r0.toString(), r1, null);
        this.zze.zza(r1, r7);
        return r1;
    }

    public final long zzn(zzat r9, MediaSeekOptions r10) {
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        if (r10.isSeekToInfinite() == false) goto L5;
        long r3 = 4294967296000L;
    L19:
        r0.put("requestId", r1);     // Catch: JSONException -> L18
        r0.put("type", "SEEK");     // Catch: JSONException -> L18
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L18
        r0.put("currentTime", CastUtils.millisecToSec(r3));     // Catch: JSONException -> L18
        if (r10.getResumeState() != 1) goto L11;
        r0.put("resumeState", "PLAYBACK_START");     // Catch: JSONException -> L18
    L14:
        if (r10.getCustomData() == null) goto L16;
        r0.put("customData", r10.getCustomData());     // Catch: JSONException -> L18
        goto L16
    L11:
        if (r10.getResumeState() != 2) goto L14;
        r0.put("resumeState", "PLAYBACK_PAUSE");     // Catch: JSONException -> L18
    L16:
        zzf(r0.toString(), r1, null);
        this.zzx = Long.valueOf(r3);
        this.zzg.zza(r1, new zzam(this, r9));
        return r1;
    L5:
        r3 = r10.getPosition();
        goto L19
    }

    public final long zzo(zzat r8) {
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L5
        r0.put("type", "SKIP_AD");     // Catch: JSONException -> L5
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L5
    L7:
        zzf(r0.toString(), r1, null);
        this.zzu.zza(r1, r8);
        return r1;
    L5:
        e = move-exception;
        this.zza.w(String.format(Locale.ROOT, "Error creating SkipAd message: %s", new Object[]{e.getMessage()}), new Object[0]);
        goto L7
    }

    public final long zzp(zzat r7, double r8, JSONObject r10) {
        if (Double.isInfinite(r8) == false) goto L5;
    L12:
        StringBuilder r102 = new StringBuilder(String.valueOf(r8).length() + 17);
        r102.append("Volume cannot be ");
        r102.append(r8);
        throw new IllegalArgumentException(r102.toString());
    L5:
        if (Double.isNaN(r8) == true) goto L12;
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L14
        r0.put("type", "SET_VOLUME");     // Catch: JSONException -> L14
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L14
        JSONObject r3 = new JSONObject();     // Catch: JSONException -> L14
        r3.put("level", r8);     // Catch: JSONException -> L14
        r0.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, r3);     // Catch: JSONException -> L14
        if (r10 == null) goto L10;
        r0.put("customData", r10);     // Catch: JSONException -> L14
    L10:
        zzf(r0.toString(), r1, null);
        this.zzh.zza(r1, r7);
        return r1;
    }

    public final long zzq(zzat r7, boolean r8, JSONObject r9) {
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L8
        r0.put("type", "SET_VOLUME");     // Catch: JSONException -> L8
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L8
        JSONObject r3 = new JSONObject();     // Catch: JSONException -> L8
        r3.put("muted", r8);     // Catch: JSONException -> L8
        r0.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, r3);     // Catch: JSONException -> L8
        if (r9 == null) goto L6;
        r0.put("customData", r9);     // Catch: JSONException -> L8
    L6:
        zzf(r0.toString(), r1, null);
        this.zzi.zza(r1, r7);
        return r1;
    }

    public final long zzr(zzat r6, double r7, JSONObject r9) {
        if (this.zzw == null) goto L11;
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L12
        r0.put("type", "SET_PLAYBACK_RATE");     // Catch: JSONException -> L12
        r0.put("playbackRate", r7);     // Catch: JSONException -> L12
        Preconditions.checkNotNull(this.zzw, "mediaStatus should not be null");     // Catch: JSONException -> L12
        r0.put("mediaSessionId", this.zzw.zza());     // Catch: JSONException -> L12
        if (r9 == null) goto L8;
        r0.put("customData", r9);     // Catch: JSONException -> L12
    L8:
        zzf(r0.toString(), r1, null);
        this.zzt.zza(r1, r6);
        return r1;
    L11:
        throw new zzap();
    }

    public final long zzs(zzat r8) {
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L8
        r0.put("type", "GET_STATUS");     // Catch: JSONException -> L8
        MediaStatus r3 = this.zzw;     // Catch: JSONException -> L8
        if (r3 == null) goto L6;
        r0.put("mediaSessionId", r3.zza());     // Catch: JSONException -> L8
    L6:
        zzf(r0.toString(), r1, null);
        this.zzj.zza(r1, r8);
        return r1;
    }

    public final long zzt(zzat r8, long[] r9) {
        if (r9 == null) goto L11;
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L13
        r0.put("type", "EDIT_TRACKS_INFO");     // Catch: JSONException -> L13
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L13
        JSONArray r3 = new JSONArray();     // Catch: JSONException -> L13
        int r4 = 0;
    L6:
        if (r4 >= r9.length) goto L8;
        r3.put(r4, r9[r4]);     // Catch: JSONException -> L13
        r4 = r4 + 1;     // Catch: JSONException -> L13
        goto L6
    L8:
        r0.put("activeTrackIds", r3);     // Catch: JSONException -> L13
    L9:
        zzf(r0.toString(), r1, null);
        this.zzk.zza(r1, r8);
        return r1;
    L11:
        f$$ExternalSyntheticBUOutline1.m("trackIds cannot be null");
        return 0;
    }

    public final long zzu(zzat r6, TextTrackStyle r7) {
        if (r7 == null) goto L7;
        JSONObject r0 = new JSONObject();
        long r1 = zzg();
        r0.put("requestId", r1);     // Catch: JSONException -> L9
        r0.put("type", "EDIT_TRACKS_INFO");     // Catch: JSONException -> L9
        r0.put("textTrackStyle", r7.zza());     // Catch: JSONException -> L9
        r0.put("mediaSessionId", zzM());     // Catch: JSONException -> L9
    L5:
        zzf(r0.toString(), r1, null);
        this.zzl.zza(r1, r6);
        return r1;
    L7:
        f$$ExternalSyntheticBUOutline1.m("trackStyle cannot be null");
        return 0;
    }

    public final long zzv() {
        MediaInfo r0 = zzB();
        if (r0 == null) goto L32;
        MediaStatus r3 = this.zzw;
        if (r3 == null) goto L32;
        Long r4 = this.zzx;
        if (r4 == null) goto L22;
        if (r4.equals(4294967296000L) == false) goto L20;
        if (this.zzw.getLiveSeekableRange() == null) goto L16;
        return Math.min(r4.longValue(), zzx());
    L16:
        if (zzz() < 0) goto L20;
        return Math.min(r4.longValue(), zzz());
    L20:
        return r4.longValue();
    L22:
        if (this.zzv != 0) goto L24;
        return 0;
    L24:
        double r6 = r3.getPlaybackRate();
        long r8 = r3.getStreamPosition();
        int r1 = r3.getPlayerState();
        if (r6 != 0.0d) goto L27;
    L31:
        return r8;
    L27:
        if (r1 != 2) goto L31;
        return zzR(r6, r8, r0.getStreamDuration());
    L32:
        return 0;
    }

    public final long zzw() {
        MediaStatus r0 = this.zzw;
        if (r0 != null) goto L5;
        return 0;
    L5:
        MediaLiveSeekableRange r02 = r0.getLiveSeekableRange();
        if (r02 != null) goto L8;
        return 0;
    L8:
        long r6 = r02.getStartTime();
        if (r02.isMovingWindow() == false) goto L12;
        r6 = zzR(1.0d, r6, -1);
    L12:
        if (r02.isLiveDone() == true) goto L14;
        return r6;
    L14:
        return Math.min(r6, r02.getEndTime());
    }

    public final long zzx() {
        MediaStatus r0 = this.zzw;
        if (r0 != null) goto L5;
        return 0;
    L5:
        MediaLiveSeekableRange r02 = r0.getLiveSeekableRange();
        if (r02 != null) goto L8;
        return 0;
    L8:
        long r6 = r02.getEndTime();
        if (r02.isLiveDone() == false) goto L11;
        return r6;
    L11:
        return zzR(1.0d, r6, -1);
    }

    public final long zzy() {
        if (this.zzv == 0) goto L18;
        MediaStatus r0 = this.zzw;
        if (r0 == null) goto L18;
        AdBreakStatus r1 = r0.getAdBreakStatus();
        if (r1 == null) goto L18;
        double r2 = r0.getPlaybackRate();
        if (r2 != 0.0d) goto L13;
        r2 = 1.0d;
    L13:
        if (r0.getPlayerState() == 2) goto L15;
        double r8 = 0.0d;
    L17:
        return zzR(r8, r1.getCurrentBreakClipTimeInMs(), 0);
    L15:
        r8 = r2;
    L18:
        return 0;
    }

    public final long zzz() {
        MediaInfo r2 = zzB();
        if (r2 != null) goto L5;
        return 0;
    L5:
        return r2.getStreamDuration();
    }
}
