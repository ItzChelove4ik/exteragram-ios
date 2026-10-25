package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SettingsJsonParser {
    private final CurrentTimeProvider currentTimeProvider;

    public SettingsJsonParser(CurrentTimeProvider r1) {
        this.currentTimeProvider = r1;
    }

    private static SettingsJsonTransform getJsonTransformForVersion(int r3) {
        if (r3 == 3) goto L7;
        Logger.getLogger().e("Could not determine SettingsJsonTransform for settings version " + r3 + ". Using default settings values.");
        return new DefaultSettingsJsonTransform();
    L7:
        return new SettingsV3JsonTransform();
    }

    public Settings parseSettingsJson(JSONObject r2) {
        return getJsonTransformForVersion(r2.getInt("settings_version")).buildFromJson(this.currentTimeProvider, r2);
    }
}
