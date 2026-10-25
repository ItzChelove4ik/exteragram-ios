package com.google.firebase.remoteconfig.internal;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ConfigContainer {
    private static final Date DEFAULTS_FETCH_TIME = null;
    private JSONArray abtExperiments;
    private JSONObject configsJson;
    private JSONObject containerJson;
    private Date fetchTime;
    private JSONObject personalizationMetadata;
    private JSONArray rolloutMetadata;
    private long templateVersionNumber;

    /* renamed from: com.google.firebase.remoteconfig.internal.ConfigContainer$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public static class Builder {
        private JSONArray builderAbtExperiments;
        private JSONObject builderConfigsJson;
        private Date builderFetchTime;
        private JSONObject builderPersonalizationMetadata;
        private JSONArray builderRolloutMetadata;
        private long builderTemplateVersionNumber;

        private Builder() {
            this.builderConfigsJson = new JSONObject();
            this.builderFetchTime = ConfigContainer.access$000();
            this.builderAbtExperiments = new JSONArray();
            this.builderPersonalizationMetadata = new JSONObject();
            this.builderTemplateVersionNumber = 0;
            this.builderRolloutMetadata = new JSONArray();
        }

        public /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        public ConfigContainer build() {
            return new ConfigContainer(this.builderConfigsJson, this.builderFetchTime, this.builderAbtExperiments, this.builderPersonalizationMetadata, this.builderTemplateVersionNumber, this.builderRolloutMetadata, null);
        }

        public Builder replaceConfigsWith(JSONObject r2) {
            this.builderConfigsJson = new JSONObject(r2.toString());     // Catch: JSONException -> L4
        L3:
            return this;
        }

        public Builder withAbtExperiments(JSONArray r2) {
            this.builderAbtExperiments = new JSONArray(r2.toString());     // Catch: JSONException -> L4
        L3:
            return this;
        }

        public Builder withFetchTime(Date r1) {
            this.builderFetchTime = r1;
            return this;
        }

        public Builder withPersonalizationMetadata(JSONObject r2) {
            this.builderPersonalizationMetadata = new JSONObject(r2.toString());     // Catch: JSONException -> L4
        L3:
            return this;
        }

        public Builder withRolloutMetadata(JSONArray r2) {
            this.builderRolloutMetadata = new JSONArray(r2.toString());     // Catch: JSONException -> L4
        L3:
            return this;
        }

        public Builder withTemplateVersionNumber(long r1) {
            this.builderTemplateVersionNumber = r1;
            return this;
        }
    }

    static {
        DEFAULTS_FETCH_TIME = new Date(0);
    }

    private ConfigContainer(JSONObject r5, Date r6, JSONArray r7, JSONObject r8, long r9, JSONArray r11) {
        JSONObject r0 = new JSONObject();
        r0.put("configs_key", r5);
        r0.put("fetch_time_key", r6.getTime());
        r0.put("abt_experiments_key", r7);
        r0.put("personalization_metadata_key", r8);
        r0.put("template_version_number_key", r9);
        r0.put("rollout_metadata_key", r11);
        this.configsJson = r5;
        this.fetchTime = r6;
        this.abtExperiments = r7;
        this.personalizationMetadata = r8;
        this.templateVersionNumber = r9;
        this.rolloutMetadata = r11;
        this.containerJson = r0;
    }

    public /* synthetic */ ConfigContainer(JSONObject r1, Date r2, JSONArray r3, JSONObject r4, long r5, JSONArray r7, AnonymousClass1 r8) {
        this(r1, r2, r3, r4, r5, r7);
    }

    public static /* synthetic */ Date access$000() {
        return DEFAULTS_FETCH_TIME;
    }

    public static ConfigContainer copyOf(JSONObject r9) {
        JSONObject r0 = r9.optJSONObject("personalization_metadata_key");
        if (r0 != null) goto L5;
        r0 = new JSONObject();
    L5:
        JSONObject r5 = r0;
        JSONArray r02 = r9.optJSONArray("rollout_metadata_key");
        if (r02 != null) goto L8;
        r02 = new JSONArray();
    L8:
        JSONObject r2 = r9.getJSONObject("configs_key");
        Date r3 = new Date(r9.getLong("fetch_time_key"));
        JSONArray r4 = r9.getJSONArray("abt_experiments_key");
        long r6 = r9.optLong("template_version_number_key");
        return new ConfigContainer(r2, r3, r4, r5, r6, r02);
    }

    private Map<String, JSONObject> createExperimentsMap() {
        HashMap r0 = new HashMap();
        JSONArray r7 = getAbtExperiments();
        int r2 = 0;
    L4:
        if (r2 >= r7.length()) goto L15;
        JSONObject r3 = r7.getJSONObject(r2);
        if (r3.has("affectedParameterKeys") == false) goto L14;
        if (r3.getString("experimentId").startsWith("rollout") == true) goto L14;
        JSONArray r4 = r3.getJSONArray("affectedParameterKeys");
        int r5 = 0;
    L12:
        if (r5 >= r4.length()) goto L14;
        r0.put(r4.getString(r5), r3);
        r5 = r5 + 1;
    L14:
        r2 = r2 + 1;
        goto L4
    L15:
        return r0;
    }

    private Map<String, Map<String, String>> createRolloutParameterKeyMap() {
        HashMap r0 = new HashMap();
        int r2 = 0;
    L4:
        if (r2 >= getRolloutMetadata().length()) goto L16;
        JSONObject r3 = getRolloutMetadata().getJSONObject(r2);
        String r4 = r3.getString("rolloutId");
        String r5 = r3.getString("variantId");
        JSONArray r32 = r3.getJSONArray("affectedParameterKeys");
        int r6 = 0;
    L7:
        if (r6 >= r32.length()) goto L15;
        String r7 = r32.getString(r6);
        if (r0.containsKey(r7) == true) goto L11;
        r0.put(r7, new HashMap());
    L11:
        Map r72 = (Map) r0.get(r7);
        if (r72 == null) goto L14;
        r72.put(r4, r5);
    L14:
        r6 = r6 + 1;
        goto L7
    L15:
        r2 = r2 + 1;
        goto L4
    L16:
        return r0;
    }

    private static ConfigContainer deepCopyOf(JSONObject r1) {
        return copyOf(new JSONObject(r1.toString()));
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof ConfigContainer) == true) goto L10;
        return false;
    L10:
        return this.containerJson.toString().equals(((ConfigContainer) r2).toString());
    }

    public JSONArray getAbtExperiments() {
        return this.abtExperiments;
    }

    public Set<String> getChangedParams(ConfigContainer r11) {
        JSONObject r0 = deepCopyOf(r11.containerJson).getConfigs();
        Map<String, Map<String, String>> r1 = createRolloutParameterKeyMap();
        Map<String, Map<String, String>> r2 = r11.createRolloutParameterKeyMap();
        Map<String, JSONObject> r3 = createExperimentsMap();
        Map<String, JSONObject> r4 = r11.createExperimentsMap();
        HashSet r5 = new HashSet();
        Iterator<String> r6 = getConfigs().keys();
    L4:
        if (r6.hasNext() == false) goto L48;
        String r7 = r6.next();
        if (r11.getConfigs().has(r7) == false) goto L7;
        if (getConfigs().get(r7).equals(r11.getConfigs().get(r7)) == false) goto L10;
        if (getPersonalizationMetadata().has(r7) == false) goto L16;
        if (r11.getPersonalizationMetadata().has(r7) == true) goto L16;
    L19:
        r5.add(r7);
    L16:
        if (getPersonalizationMetadata().has(r7) == true) goto L21;
        if (r11.getPersonalizationMetadata().has(r7) == true) goto L19;
    L21:
        if (getPersonalizationMetadata().has(r7) == false) goto L28;
        if (r11.getPersonalizationMetadata().has(r7) == false) goto L28;
        if (getPersonalizationMetadata().getJSONObject(r7).toString().equals(r11.getPersonalizationMetadata().getJSONObject(r7).toString()) == true) goto L28;
        r5.add(r7);
    L28:
        if (r1.containsKey(r7) != r2.containsKey(r7)) goto L29;
        if (r1.containsKey(r7) == false) goto L38;
        if (r2.containsKey(r7) == false) goto L38;
        if (r1.get(r7).equals(r2.get(r7)) == true) goto L38;
        r5.add(r7);
    L38:
        if (r3.containsKey(r7) != r4.containsKey(r7)) goto L39;
        if (r4.containsKey(r7) == false) goto L47;
        if (r3.containsKey(r7) == false) goto L47;
        if (r4.get(r7).toString().equals(r3.get(r7).toString()) == true) goto L47;
        r5.add(r7);
    L47:
        r0.remove(r7);
        goto L4
    L39:
        r5.add(r7);
        goto L4
    L29:
        r5.add(r7);
        goto L4
    L10:
        r5.add(r7);
        goto L4
    L7:
        r5.add(r7);
        goto L4
    L48:
        Iterator<String> r10 = r0.keys();
    L50:
        if (r10.hasNext() == false) goto L52;
        r5.add(r10.next());
        goto L50
    L52:
        return r5;
    }

    public JSONObject getConfigs() {
        return this.configsJson;
    }

    public Date getFetchTime() {
        return this.fetchTime;
    }

    public JSONObject getPersonalizationMetadata() {
        return this.personalizationMetadata;
    }

    public JSONArray getRolloutMetadata() {
        return this.rolloutMetadata;
    }

    public long getTemplateVersionNumber() {
        return this.templateVersionNumber;
    }

    public int hashCode() {
        return this.containerJson.hashCode();
    }

    public String toString() {
        return this.containerJson.toString();
    }
}
