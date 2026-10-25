package com.google.android.gms.location;

import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.location.LocationCompat;
import com.google.android.gms.internal.location.zzen;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* loaded from: classes5.dex */
public abstract class zzaj {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final DecimalFormat zzb = null;
    private static final DecimalFormat zzc = null;

    static {
        Locale r1 = Locale.ROOT;
        zzb = new DecimalFormat(".000000", DecimalFormatSymbols.getInstance(r1));
        DecimalFormat r0 = new DecimalFormat(".##", DecimalFormatSymbols.getInstance(r1));
        zzc = r0;
        r0.setRoundingMode(RoundingMode.DOWN);
    }

    public static StringBuilder zza(Location r6, StringBuilder r7) {
        r7.ensureCapacity(100);
        String r0 = null;
        if (r6 != null) goto L6;
        r7.append(null);
        return r7;
    L6:
        r7.append("{");
        if (TextUtils.isEmpty(r6.getProvider()) == true) goto L10;
        r7.append(r6.getProvider());
        r7.append(", ");
    L10:
        if (LocationCompat.isMock(r6) == false) goto L12;
        r7.append("mock, ");
    L12:
        DecimalFormat r1 = zzb;
        r7.append(r1.format(r6.getLatitude()));
        r7.append(",");
        r7.append(r1.format(r6.getLongitude()));
        if (r6.hasAccuracy() == false) goto L16;
        r7.append("±");
        r7.append(zzc.format(r6.getAccuracy()));
        r7.append("m");
    L16:
        if (r6.hasAltitude() == false) goto L22;
        r7.append(", alt=");
        DecimalFormat r12 = zzc;
        r7.append(r12.format(r6.getAltitude()));
        if (LocationCompat.hasVerticalAccuracy(r6) == false) goto L20;
        r7.append("±");
        r7.append(r12.format(LocationCompat.getVerticalAccuracyMeters(r6)));
    L20:
        r7.append("m");
    L22:
        if (r6.hasSpeed() == false) goto L28;
        r7.append(", spd=");
        DecimalFormat r13 = zzc;
        r7.append(r13.format(r6.getSpeed()));
        if (LocationCompat.hasSpeedAccuracy(r6) == false) goto L26;
        r7.append("±");
        r7.append(r13.format(LocationCompat.getSpeedAccuracyMetersPerSecond(r6)));
    L26:
        r7.append("m/s");
    L28:
        if (r6.hasBearing() == false) goto L33;
        r7.append(", brg=");
        DecimalFormat r14 = zzc;
        r7.append(r14.format(r6.getBearing()));
        if (LocationCompat.hasBearingAccuracy(r6) == false) goto L32;
        r7.append("±");
        r7.append(r14.format(LocationCompat.getBearingAccuracyDegrees(r6)));
    L32:
        r7.append("°");
    L33:
        Bundle r15 = r6.getExtras();
        if (r15 == null) goto L36;
        String r16 = r15.getString("floorLabel");
    L37:
        if (r16 == null) goto L39;
        r7.append(", fl=");
        r7.append(r16);
    L39:
        Bundle r17 = r6.getExtras();
        if (r17 == null) goto L42;
        r0 = r17.getString("levelId");
    L42:
        if (r0 == null) goto L44;
        r7.append(", lv=");
        r7.append(r0);
    L44:
        long r02 = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        r7.append(", ert=");
        r7.append(zzen.zza(LocationCompat.getElapsedRealtimeMillis(r6) + r02));
        r7.append('}');
        return r7;
    L36:
        r16 = null;
        goto L37
    }
}
