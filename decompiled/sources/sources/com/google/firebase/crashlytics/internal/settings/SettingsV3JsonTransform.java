package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.Settings;
import org.json.JSONObject;

/* loaded from: classes.dex */
class SettingsV3JsonTransform implements SettingsJsonTransform {
    public SettingsV3JsonTransform() {
    }

    private static Settings.FeatureFlagData buildFeatureFlagDataFrom(JSONObject r4) {
        return new Settings.FeatureFlagData(r4.optBoolean("collect_reports", true), r4.optBoolean("collect_anrs", false), r4.optBoolean("collect_build_ids", false));
    }

    private static Settings.SessionData buildSessionDataFrom(JSONObject r2) {
        return new Settings.SessionData(r2.optInt("max_custom_exception_events", 8), 4);
    }

    private static long getExpiresAtFrom(CurrentTimeProvider r4, long r5, JSONObject r7) {
        if (r7.has("expires_at") == false) goto L7;
        return r7.optLong("expires_at");
    L7:
        return r4.getCurrentTimeMillis() + (r5 * 1000);
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsJsonTransform
    public Settings buildFromJson(CurrentTimeProvider r14, JSONObject r15) {
        int r6 = r15.optInt("settings_version", 0);
        int r7 = r15.optInt("cache_duration", 3600);
        double r8 = r15.optDouble("on_demand_upload_rate_per_minute", 10.0d);
        double r10 = r15.optDouble("on_demand_backoff_base", 1.2d);
        int r12 = r15.optInt("on_demand_backoff_step_duration_seconds", 60);
        if (r15.has("session") == false) goto L6;
        Settings.SessionData r13 = buildSessionDataFrom(r15.getJSONObject("session"));
    L8:
        return new Settings(getExpiresAtFrom(r14, r7, r15), r13, buildFeatureFlagDataFrom(r15.getJSONObject("features")), r6, r7, r8, r10, r12);
    L6:
        r13 = buildSessionDataFrom(new JSONObject());
        goto L8
    }
}
