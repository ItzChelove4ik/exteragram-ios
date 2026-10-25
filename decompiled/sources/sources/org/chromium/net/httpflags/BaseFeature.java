package org.chromium.net.httpflags;

import com.google.protobuf.ByteString;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.chromium.net.httpflags.BaseFeatureOverrides;
import org.chromium.net.httpflags.ResolvedFlags;

/* loaded from: classes5.dex */
public final class BaseFeature {
    public static final String FLAG_PREFIX = "ChromiumBaseFeature_";
    public static final String PARAM_DELIMITER = "_PARAM_";

    /* renamed from: org.chromium.net.httpflags.BaseFeature$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$chromium$net$httpflags$ResolvedFlags$Value$Type = null;

        static {
            int[] r0 = new int[ResolvedFlags.Value.Type.values().length];
            $SwitchMap$org$chromium$net$httpflags$ResolvedFlags$Value$Type = r0;
            r0[ResolvedFlags.Value.Type.BOOL.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$org$chromium$net$httpflags$ResolvedFlags$Value$Type[ResolvedFlags.Value.Type.INT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$org$chromium$net$httpflags$ResolvedFlags$Value$Type[ResolvedFlags.Value.Type.FLOAT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L22:
            $SwitchMap$org$chromium$net$httpflags$ResolvedFlags$Value$Type[ResolvedFlags.Value.Type.STRING.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L16:
            $SwitchMap$org$chromium$net$httpflags$ResolvedFlags$Value$Type[ResolvedFlags.Value.Type.BYTES.ordinal()] = 5;     // Catch: NoSuchFieldError -> L13
            return;
        }
    }

    public static final class ParsedFlagName {
        public String featureName;
        public String paramName;

        private ParsedFlagName() {
        }

        public /* synthetic */ ParsedFlagName(BaseFeatureIA r1) {
            this();
        }
    }

    private BaseFeature() {
    }

    private static void applyOverride(String r2, ResolvedFlags.Value r3, Map<String, BaseFeatureOverrides.FeatureState.Builder> r4) {
        ParsedFlagName r22 = parseFlagName(r2);
        if (r22 != null) goto L5;
        return;
    L5:
        BaseFeatureOverrides.FeatureState.Builder r0 = r4.get(r22.featureName);
        if (r0 != null) goto L8;
        r0 = BaseFeatureOverrides.FeatureState.newBuilder();
        r4.put(r22.featureName, r0);
    L8:
        String r23 = r22.paramName;
        if (r23 != null) goto L12;
        applyStateOverride(r3, r0);
        return;
    L12:
        applyParamOverride(r23, r3, r0);
    }

    private static void applyParamOverride(String r3, ResolvedFlags.Value r4, BaseFeatureOverrides.FeatureState.Builder r5) {
        ResolvedFlags.Value.Type r0 = r4.getType();
        int r1 = AnonymousClass1.$SwitchMap$org$chromium$net$httpflags$ResolvedFlags$Value$Type[r0.ordinal()];
        if (r1 == 1) goto L19;
        if (r1 != 2) goto L7;
        ByteString r42 = ByteString.copyFrom(Long.toString(r4.getIntValue(), 10), StandardCharsets.UTF_8);
    L23:
        r5.putParams(r3, r42);
        return;
    L7:
        if (r1 != 3) goto L9;
        r42 = ByteString.copyFrom(Float.toString(r4.getFloatValue()), StandardCharsets.UTF_8);
        goto L23
    L9:
        if (r1 != 4) goto L11;
        r42 = ByteString.copyFrom(r4.getStringValue(), StandardCharsets.UTF_8);
        goto L23
    L11:
        if (r1 != 5) goto L14;
        r42 = r4.getBytesValue();
        goto L23
    L14:
        throw new UnsupportedOperationException("Unsupported HTTP flag value type for base::Feature param `" + r3 + "`: " + String.valueOf(r0));
    L19:
        if (r4.getBoolValue() == false) goto L21;
        String r43 = "true";
    L22:
        r42 = ByteString.copyFrom(r43, StandardCharsets.UTF_8);
        goto L23
    L21:
        r43 = "false";
        goto L22
    }

    private static void applyStateOverride(ResolvedFlags.Value r2, BaseFeatureOverrides.FeatureState.Builder r3) {
        ResolvedFlags.Value.Type r0 = r2.getType();
        if (r0 != ResolvedFlags.Value.Type.BOOL) goto L7;
        r3.setEnabled(r2.getBoolValue());
        return;
    L7:
        throw new IllegalArgumentException("HTTP flag has type " + String.valueOf(r0) + ", but only boolean flags are supported as base::Feature overrides");
    }

    public static BaseFeatureOverrides getOverrides(ResolvedFlags r4) {
        HashMap r0 = new HashMap();
        Iterator<Map.Entry<String, ResolvedFlags.Value>> r42 = r4.flags().entrySet().iterator();
    L4:
        if (r42.hasNext() == false) goto L11;
        Map.Entry<String, ResolvedFlags.Value> r1 = r42.next();
        applyOverride(r1.getKey(), r1.getValue(), r0);     // Catch: RuntimeException -> L8
    L8:
        e = move-exception;
        throw new IllegalArgumentException("Could not parse HTTP flag `" + r1.getKey() + "` as a base::Feature override", e);
    L11:
        BaseFeatureOverrides.Builder r43 = BaseFeatureOverrides.newBuilder();
        Iterator r02 = r0.entrySet().iterator();
    L13:
        if (r02.hasNext() == false) goto L16;
        Map.Entry r12 = (Map.Entry) r02.next();
        r43.putFeatureStates((String) r12.getKey(), ((BaseFeatureOverrides.FeatureState.Builder) r12.getValue()).build());
        goto L13
    L16:
        return r43.build();
    }

    private static ParsedFlagName parseFlagName(String r3) {
        if (r3.startsWith(FLAG_PREFIX) == true) goto L5;
        return null;
    L5:
        String r32 = r3.substring(20);
        ParsedFlagName r0 = new ParsedFlagName(null);
        int r1 = r32.indexOf(PARAM_DELIMITER);
        if (r1 >= 0) goto L9;
        r0.featureName = r32;
        return r0;
    L9:
        r0.featureName = r32.substring(0, r1);
        r0.paramName = r32.substring(r1 + 7);
        return r0;
    }
}
