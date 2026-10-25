package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import org.json.JSONObject;

/* loaded from: classes.dex */
interface SettingsJsonTransform {
    Settings buildFromJson(CurrentTimeProvider r1, JSONObject r2);
}
