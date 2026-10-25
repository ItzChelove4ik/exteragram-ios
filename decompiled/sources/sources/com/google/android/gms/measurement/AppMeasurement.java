package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdb;
import com.google.android.gms.measurement.internal.zzic;
import com.google.android.gms.measurement.internal.zzjh;
import com.google.android.gms.measurement.internal.zzlk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Deprecated
/* loaded from: classes5.dex */
public class AppMeasurement {
    private static volatile AppMeasurement zza;
    private final zzc zzb;

    public static class ConditionalUserProperty {

        @Keep
        public boolean mActive;

        @Keep
        public String mAppId;

        @Keep
        public long mCreationTimestamp;

        @Keep
        public String mExpiredEventName;

        @Keep
        public Bundle mExpiredEventParams;

        @Keep
        public String mName;

        @Keep
        public String mOrigin;

        @Keep
        public long mTimeToLive;

        @Keep
        public String mTimedOutEventName;

        @Keep
        public Bundle mTimedOutEventParams;

        @Keep
        public String mTriggerEventName;

        @Keep
        public long mTriggerTimeout;

        @Keep
        public String mTriggeredEventName;

        @Keep
        public Bundle mTriggeredEventParams;

        @Keep
        public long mTriggeredTimestamp;

        @Keep
        public Object mValue;

        public ConditionalUserProperty(Bundle r9) {
            Preconditions.checkNotNull(r9);
            this.mAppId = (String) zzjh.zzb(r9, "app_id", String.class, null);
            this.mOrigin = (String) zzjh.zzb(r9, "origin", String.class, null);
            this.mName = (String) zzjh.zzb(r9, "name", String.class, null);
            this.mValue = zzjh.zzb(r9, "value", Object.class, null);
            this.mTriggerEventName = (String) zzjh.zzb(r9, "trigger_event_name", String.class, null);
            this.mTriggerTimeout = ((Long) zzjh.zzb(r9, "trigger_timeout", Long.class, 0L)).longValue();
            this.mTimedOutEventName = (String) zzjh.zzb(r9, "timed_out_event_name", String.class, null);
            this.mTimedOutEventParams = (Bundle) zzjh.zzb(r9, "timed_out_event_params", Bundle.class, null);
            this.mTriggeredEventName = (String) zzjh.zzb(r9, "triggered_event_name", String.class, null);
            this.mTriggeredEventParams = (Bundle) zzjh.zzb(r9, "triggered_event_params", Bundle.class, null);
            this.mTimeToLive = ((Long) zzjh.zzb(r9, "time_to_live", Long.class, 0L)).longValue();
            this.mExpiredEventName = (String) zzjh.zzb(r9, "expired_event_name", String.class, null);
            this.mExpiredEventParams = (Bundle) zzjh.zzb(r9, "expired_event_params", Bundle.class, null);
            this.mActive = ((Boolean) zzjh.zzb(r9, "active", Boolean.class, Boolean.FALSE)).booleanValue();
            this.mCreationTimestamp = ((Long) zzjh.zzb(r9, "creation_timestamp", Long.class, 0L)).longValue();
            this.mTriggeredTimestamp = ((Long) zzjh.zzb(r9, "triggered_timestamp", Long.class, 0L)).longValue();
        }
    }

    public AppMeasurement(zzic r2) {
        this.zzb = new zza(r2);
    }

    public AppMeasurement(zzlk r2) {
        this.zzb = new zzb(r2);
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    @Keep
    @Deprecated
    public static AppMeasurement getInstance(Context r10) {
        if (zza != null) goto L23;
        monitor-enter(AppMeasurement.class);
    L12:
        th = move-exception;
        throw th;
    L7:
        if (zza == null) goto L10;
    L18:
        monitor-exit(AppMeasurement.class);     // Catch: Throwable -> L12
        goto L23
    L10:
        zzlk r2 = (zzlk) FirebaseAnalytics.class.getDeclaredMethod("getScionFrontendApiImplementation", new Class[]{Context.class, Bundle.class}).invoke(null, new Object[]{r10, null});     // Catch: Throwable -> L12
        if (r2 == null) goto L17;
        zza = new AppMeasurement(r2);     // Catch: Throwable -> L12
        goto L18
    L17:
        zza = new AppMeasurement(zzic.zzy(r10, new zzdb(0, 0, true, null, null), null, null));     // Catch: Throwable -> L12
    L23:
        return zza;
    }

    @Keep
    public void beginAdUnitExposure(String r1) {
        this.zzb.zzm(r1);
    }

    @Keep
    public void clearConditionalUserProperty(String r1, String r2, Bundle r3) {
        this.zzb.zzp(r1, r2, r3);
    }

    @Keep
    public void endAdUnitExposure(String r1) {
        this.zzb.zzn(r1);
    }

    @Keep
    public long generateEventId() {
        return this.zzb.zzl();
    }

    @Keep
    public String getAppInstanceId() {
        return this.zzb.zzj();
    }

    @Keep
    public List<ConditionalUserProperty> getConditionalUserProperties(String r2, String r3) {
        List r1 = this.zzb.zzq(r2, r3);
        if (r1 != null) goto L5;
        int r32 = 0;
    L6:
        ArrayList r22 = new ArrayList(r32);
        Iterator r12 = r1.iterator();
    L8:
        if (r12.hasNext() == false) goto L10;
        r22.add(new ConditionalUserProperty((Bundle) r12.next()));
        goto L8
    L10:
        return r22;
    L5:
        r32 = r1.size();
        goto L6
    }

    @Keep
    public String getCurrentScreenClass() {
        return this.zzb.zzi();
    }

    @Keep
    public String getCurrentScreenName() {
        return this.zzb.zzh();
    }

    @Keep
    public String getGmpAppId() {
        return this.zzb.zzk();
    }

    @Keep
    public int getMaxUserProperties(String r1) {
        return this.zzb.zzr(r1);
    }

    @Keep
    public Map<String, Object> getUserProperties(String r1, String r2, boolean r3) {
        return this.zzb.zzd(r1, r2, r3);
    }

    @Keep
    public void logEventInternal(String r1, String r2, Bundle r3) {
        this.zzb.zza(r1, r2, r3);
    }

    @Keep
    public void setConditionalUserProperty(ConditionalUserProperty r5) {
        Preconditions.checkNotNull(r5);
        Bundle r0 = new Bundle();
        String r1 = r5.mAppId;
        if (r1 == null) goto L5;
        r0.putString("app_id", r1);
    L5:
        String r12 = r5.mOrigin;
        if (r12 == null) goto L8;
        r0.putString("origin", r12);
    L8:
        String r13 = r5.mName;
        if (r13 == null) goto L11;
        r0.putString("name", r13);
    L11:
        Object r14 = r5.mValue;
        if (r14 == null) goto L14;
        zzjh.zza(r0, r14);
    L14:
        String r15 = r5.mTriggerEventName;
        if (r15 == null) goto L17;
        r0.putString("trigger_event_name", r15);
    L17:
        r0.putLong("trigger_timeout", r5.mTriggerTimeout);
        String r16 = r5.mTimedOutEventName;
        if (r16 == null) goto L20;
        r0.putString("timed_out_event_name", r16);
    L20:
        Bundle r17 = r5.mTimedOutEventParams;
        if (r17 == null) goto L23;
        r0.putBundle("timed_out_event_params", r17);
    L23:
        String r18 = r5.mTriggeredEventName;
        if (r18 == null) goto L26;
        r0.putString("triggered_event_name", r18);
    L26:
        Bundle r19 = r5.mTriggeredEventParams;
        if (r19 == null) goto L29;
        r0.putBundle("triggered_event_params", r19);
    L29:
        r0.putLong("time_to_live", r5.mTimeToLive);
        String r110 = r5.mExpiredEventName;
        if (r110 == null) goto L32;
        r0.putString("expired_event_name", r110);
    L32:
        Bundle r111 = r5.mExpiredEventParams;
        if (r111 == null) goto L35;
        r0.putBundle("expired_event_params", r111);
    L35:
        zzc r4 = this.zzb;
        r0.putLong("creation_timestamp", r5.mCreationTimestamp);
        r0.putBoolean("active", r5.mActive);
        r0.putLong("triggered_timestamp", r5.mTriggeredTimestamp);
        r4.zzo(r0);
    }
}
