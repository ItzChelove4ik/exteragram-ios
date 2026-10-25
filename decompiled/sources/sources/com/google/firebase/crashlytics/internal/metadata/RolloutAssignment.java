package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public abstract class RolloutAssignment {
    public static final DataEncoder ROLLOUT_ASSIGNMENT_JSON_ENCODER = null;

    static {
        ROLLOUT_ASSIGNMENT_JSON_ENCODER = new JsonDataEncoderBuilder().configureWith(AutoRolloutAssignmentEncoder.CONFIG).build();
    }

    public RolloutAssignment() {
    }

    public static RolloutAssignment create(String r7) {
        JSONObject r0 = new JSONObject(r7);
        return create(r0.getString("rolloutId"), r0.getString("parameterKey"), r0.getString("parameterValue"), r0.getString("variantId"), r0.getLong("templateVersion"));
    }

    public static RolloutAssignment create(String r7, String r8, String r9, String r10, long r11) {
        return new AutoValue_RolloutAssignment(r7, r8, validate(r9), r10, r11);
    }

    private static String validate(String r2) {
        if (r2.length() > 256) goto L5;
        return r2;
    L5:
        return r2.substring(0, 256);
    }

    public abstract String getParameterKey();

    public abstract String getParameterValue();

    public abstract String getRolloutId();

    public abstract long getTemplateVersion();

    public abstract String getVariantId();

    public CrashlyticsReport.Session.Event.RolloutAssignment toReportProto() {
        return CrashlyticsReport.Session.Event.RolloutAssignment.builder().setRolloutVariant(CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.builder().setVariantId(getVariantId()).setRolloutId(getRolloutId()).build()).setParameterKey(getParameterKey()).setParameterValue(getParameterValue()).setTemplateVersion(getTemplateVersion()).build();
    }
}
