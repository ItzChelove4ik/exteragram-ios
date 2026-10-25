package com.google.firebase.remoteconfig.internal.rollouts;

import android.util.Log;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsState;
import java.util.HashSet;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class RolloutsStateFactory {
    ConfigCacheClient activatedConfigsCache;
    ConfigCacheClient defaultConfigsCache;

    public RolloutsStateFactory(ConfigCacheClient r1, ConfigCacheClient r2) {
        this.activatedConfigsCache = r1;
        this.defaultConfigsCache = r2;
    }

    public static RolloutsStateFactory create(ConfigCacheClient r1, ConfigCacheClient r2) {
        return new RolloutsStateFactory(r1, r2);
    }

    private String getParameterValue(String r2) {
        String r0 = getStringFromCache(this.activatedConfigsCache, r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        String r1 = getStringFromCache(this.defaultConfigsCache, r2);
        if (r1 == null) goto L8;
        return r1;
    L8:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private static String getStringFromCache(ConfigCacheClient r1, String r2) {
        ConfigContainer r12 = r1.getBlocking();
        if (r12 != null) goto L8;
        return null;
    L8:
        return r12.getConfigs().getString(r2);
    L7:
        return null;
    }

    public RolloutsState getActiveRolloutsState(ConfigContainer r12) {
        JSONArray r0 = r12.getRolloutMetadata();
        long r1 = r12.getTemplateVersionNumber();
        HashSet r122 = new HashSet();
        int r4 = 0;
    L4:
        if (r4 >= r0.length()) goto L14;
        JSONObject r5 = r0.getJSONObject(r4);     // Catch: JSONException -> L10
        String r6 = r5.getString("rolloutId");     // Catch: JSONException -> L10
        JSONArray r7 = r5.getJSONArray("affectedParameterKeys");     // Catch: JSONException -> L10
        if (r7.length() <= 1) goto L8;
        Log.w("FirebaseRemoteConfig", String.format("Rollout has multiple affected parameter keys.Only the first key will be included in RolloutsState. rolloutId: %s, affectedParameterKeys: %s", new Object[]{r6, r7}));     // Catch: JSONException -> L10
    L8:
        String r72 = r7.optString(0, _UrlKt.FRAGMENT_ENCODE_SET);     // Catch: JSONException -> L10
        r122.add(RolloutAssignment.builder().setRolloutId(r6).setVariantId(r5.getString("variantId")).setParameterKey(r72).setParameterValue(getParameterValue(r72)).setTemplateVersion(r1).build());     // Catch: JSONException -> L10
        r4 = r4 + 1;
    L10:
        e = move-exception;
        throw new FirebaseRemoteConfigClientException("Exception parsing rollouts metadata to create RolloutsState.", e);
    L14:
        return RolloutsState.create(r122);
    }
}
