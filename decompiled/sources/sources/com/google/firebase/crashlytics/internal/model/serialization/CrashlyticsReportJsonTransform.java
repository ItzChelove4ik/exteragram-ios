package com.google.firebase.crashlytics.internal.model.serialization;

import android.util.Base64;
import android.util.JsonReader;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.firebase.crashlytics.internal.model.AutoCrashlyticsReportEncoder;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class CrashlyticsReportJsonTransform {
    private static final DataEncoder CRASHLYTICS_REPORT_JSON_ENCODER = null;

    /* loaded from: classes5.dex */
    public interface ObjectParser<T> {
        T parse(JsonReader r1);
    }

    public static /* synthetic */ CrashlyticsReport.Session.Event.RolloutAssignment $r8$lambda$2ml3PKk5fOo6YyWVX0z7YgceQh8(JsonReader r0) {
        return parseEventRolloutsAssignment(r0);
    }

    /* renamed from: $r8$lambda$8ygY50Xbks6pPaUEn6D_6eho4-k, reason: not valid java name */
    public static /* synthetic */ CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch m1943$r8$lambda$8ygY50Xbks6pPaUEn6D_6eho4k(JsonReader r0) {
        return parseBuildIdMappingForArch(r0);
    }

    public static /* synthetic */ CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame $r8$lambda$C_h5D9sGcUO3lfApqv6Mieb48lE(JsonReader r0) {
        return parseEventFrame(r0);
    }

    public static /* synthetic */ CrashlyticsReport.CustomAttribute $r8$lambda$DnHtDhxQVAR4pGbFVVTRcJI4vds(JsonReader r0) {
        return parseCustomAttribute(r0);
    }

    /* renamed from: $r8$lambda$EBvHuWeevbJIi-XdxoUEoqg-0n8, reason: not valid java name */
    public static /* synthetic */ CrashlyticsReport.Session.Event.Application.Execution.BinaryImage m1944$r8$lambda$EBvHuWeevbJIiXdxoUEoqg0n8(JsonReader r0) {
        return parseEventBinaryImage(r0);
    }

    public static /* synthetic */ CrashlyticsReport.Session.Event.Application.Execution.Thread $r8$lambda$culw8GModKGclI4ZdT7wvNuJ36Q(JsonReader r0) {
        return parseEventThread(r0);
    }

    /* renamed from: $r8$lambda$u8IwczHvgExKKbl_iFF_fQ9-1tA, reason: not valid java name */
    public static /* synthetic */ CrashlyticsReport.FilesPayload.File m1945$r8$lambda$u8IwczHvgExKKbl_iFF_fQ91tA(JsonReader r0) {
        return parseFile(r0);
    }

    public static /* synthetic */ CrashlyticsReport.Session.Event.Application.ProcessDetails $r8$lambda$wighd3OHwaVocQpJqaCnt5z6u9U(JsonReader r0) {
        return parseProcessDetails(r0);
    }

    public static /* synthetic */ CrashlyticsReport.Session.Event $r8$lambda$wvmderlseY3ZTTqhsLlVvBGBL5Y(JsonReader r0) {
        return parseEvent(r0);
    }

    static {
        CRASHLYTICS_REPORT_JSON_ENCODER = new JsonDataEncoderBuilder().configureWith(AutoCrashlyticsReportEncoder.CONFIG).ignoreNullValues(true).build();
    }

    public CrashlyticsReportJsonTransform() {
    }

    private static CrashlyticsReport.Session.Application parseApp(JsonReader r4) {
        CrashlyticsReport.Session.Application.Builder r0 = CrashlyticsReport.Session.Application.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L40;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1618432855: goto L29;
            case -519438642: goto L25;
            case 213652010: goto L21;
            case 351608024: goto L17;
            case 719853845: goto L13;
            case 1975623094: goto L9;
            default: goto L32;
        };
    L32:
        switch(r3) {
            case 0: goto L39;
            case 1: goto L38;
            case 2: goto L37;
            case 3: goto L36;
            case 4: goto L35;
            case 5: goto L34;
            default: goto L33;
        };
    L34:
        r0.setDisplayVersion(r4.nextString());
        goto L4
    L35:
        r0.setInstallationUuid(r4.nextString());
        goto L4
    L36:
        r0.setVersion(r4.nextString());
        goto L4
    L37:
        r0.setDevelopmentPlatformVersion(r4.nextString());
        goto L4
    L38:
        r0.setDevelopmentPlatform(r4.nextString());
        goto L4
    L39:
        r0.setIdentifier(r4.nextString());
        goto L4
    L33:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("displayVersion") == false) goto L32;
        r3 = 5;
        goto L32
    L13:
        if (r1.equals("installationUuid") == false) goto L32;
        r3 = 4;
        goto L32
    L17:
        if (r1.equals("version") == false) goto L32;
        r3 = 3;
        goto L32
    L21:
        if (r1.equals("developmentPlatformVersion") == false) goto L32;
        r3 = 2;
        goto L32
    L25:
        if (r1.equals("developmentPlatform") == false) goto L32;
        r3 = 1;
        goto L32
    L29:
        if (r1.equals("identifier") == false) goto L32;
        r3 = 0;
        goto L32
    L40:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.ApplicationExitInfo parseAppExitInfo(JsonReader r4) {
        CrashlyticsReport.ApplicationExitInfo.Builder r0 = CrashlyticsReport.ApplicationExitInfo.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L55;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1516200806: goto L41;
            case 110987: goto L37;
            case 111312: goto L33;
            case 113234: goto L29;
            case 55126294: goto L25;
            case 202325402: goto L21;
            case 722137681: goto L17;
            case 723857505: goto L13;
            case 2125650548: goto L9;
            default: goto L44;
        };
    L44:
        switch(r3) {
            case 0: goto L54;
            case 1: goto L53;
            case 2: goto L52;
            case 3: goto L51;
            case 4: goto L50;
            case 5: goto L49;
            case 6: goto L48;
            case 7: goto L47;
            case 8: goto L46;
            default: goto L45;
        };
    L46:
        r0.setImportance(r4.nextInt());
        goto L4
    L47:
        r0.setTraceFile(r4.nextString());
        goto L4
    L48:
        r0.setReasonCode(r4.nextInt());
        goto L4
    L49:
        r0.setProcessName(r4.nextString());
        goto L4
    L50:
        r0.setTimestamp(r4.nextLong());
        goto L4
    L51:
        r0.setRss(r4.nextLong());
        goto L4
    L52:
        r0.setPss(r4.nextLong());
        goto L4
    L53:
        r0.setPid(r4.nextInt());
        goto L4
    L54:
        r0.setBuildIdMappingForArch(parseArray(r4, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda0()));
        goto L4
    L45:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("importance") == false) goto L44;
        r3 = '\b';
        goto L44
    L13:
        if (r1.equals("traceFile") == false) goto L44;
        r3 = 7;
        goto L44
    L17:
        if (r1.equals("reasonCode") == false) goto L44;
        r3 = 6;
        goto L44
    L21:
        if (r1.equals("processName") == false) goto L44;
        r3 = 5;
        goto L44
    L25:
        if (r1.equals("timestamp") == false) goto L44;
        r3 = 4;
        goto L44
    L29:
        if (r1.equals("rss") == false) goto L44;
        r3 = 3;
        goto L44
    L33:
        if (r1.equals("pss") == false) goto L44;
        r3 = 2;
        goto L44
    L37:
        if (r1.equals("pid") == false) goto L44;
        r3 = 1;
        goto L44
    L41:
        if (r1.equals("buildIdMappingForArch") == false) goto L44;
        r3 = 0;
        goto L44
    L55:
        r4.endObject();
        return r0.build();
    }

    private static <T> List<T> parseArray(JsonReader r2, ObjectParser<T> r3) {
        ArrayList r0 = new ArrayList();
        r2.beginArray();
    L4:
        if (r2.hasNext() == false) goto L6;
        r0.add(r3.parse(r2));
        goto L4
    L6:
        r2.endArray();
        return Collections.unmodifiableList(r0);
    }

    private static CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch parseBuildIdMappingForArch(JsonReader r4) {
        CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.Builder r0 = CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L25;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -609862170: goto L17;
            case 3002454: goto L13;
            case 230943785: goto L9;
            default: goto L20;
        };
    L20:
        switch(r3) {
            case 0: goto L24;
            case 1: goto L23;
            case 2: goto L22;
            default: goto L21;
        };
    L22:
        r0.setBuildId(r4.nextString());
        goto L4
    L23:
        r0.setArch(r4.nextString());
        goto L4
    L24:
        r0.setLibraryName(r4.nextString());
        goto L4
    L21:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("buildId") == false) goto L20;
        r3 = 2;
        goto L20
    L13:
        if (r1.equals("arch") == false) goto L20;
        r3 = 1;
        goto L20
    L17:
        if (r1.equals("libraryName") == false) goto L20;
        r3 = 0;
        goto L20
    L25:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.CustomAttribute parseCustomAttribute(JsonReader r3) {
        CrashlyticsReport.CustomAttribute.Builder r0 = CrashlyticsReport.CustomAttribute.builder();
        r3.beginObject();
    L4:
        if (r3.hasNext() == false) goto L12;
        String r1 = r3.nextName();
        r1.getClass();
        if (r1.equals(PluginsConstants.Settings.KEY) == false) goto L8;
        r0.setKey(r3.nextString());
        goto L4
    L8:
        if (r1.equals("value") == false) goto L9;
        r0.setValue(r3.nextString());
        goto L4
    L9:
        r3.skipValue();
        goto L4
    L12:
        r3.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Device parseDevice(JsonReader r4) {
        CrashlyticsReport.Session.Device.Builder r0 = CrashlyticsReport.Session.Device.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L55;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1981332476: goto L41;
            case -1969347631: goto L37;
            case 112670: goto L33;
            case 3002454: goto L29;
            case 81784169: goto L25;
            case 94848180: goto L21;
            case 104069929: goto L17;
            case 109757585: goto L13;
            case 2078953423: goto L9;
            default: goto L44;
        };
    L44:
        switch(r3) {
            case 0: goto L54;
            case 1: goto L53;
            case 2: goto L52;
            case 3: goto L51;
            case 4: goto L50;
            case 5: goto L49;
            case 6: goto L48;
            case 7: goto L47;
            case 8: goto L46;
            default: goto L45;
        };
    L46:
        r0.setModelClass(r4.nextString());
        goto L4
    L47:
        r0.setState(r4.nextInt());
        goto L4
    L48:
        r0.setModel(r4.nextString());
        goto L4
    L49:
        r0.setCores(r4.nextInt());
        goto L4
    L50:
        r0.setDiskSpace(r4.nextLong());
        goto L4
    L51:
        r0.setArch(r4.nextInt());
        goto L4
    L52:
        r0.setRam(r4.nextLong());
        goto L4
    L53:
        r0.setManufacturer(r4.nextString());
        goto L4
    L54:
        r0.setSimulator(r4.nextBoolean());
        goto L4
    L45:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("modelClass") == false) goto L44;
        r3 = '\b';
        goto L44
    L13:
        if (r1.equals("state") == false) goto L44;
        r3 = 7;
        goto L44
    L17:
        if (r1.equals("model") == false) goto L44;
        r3 = 6;
        goto L44
    L21:
        if (r1.equals("cores") == false) goto L44;
        r3 = 5;
        goto L44
    L25:
        if (r1.equals("diskSpace") == false) goto L44;
        r3 = 4;
        goto L44
    L29:
        if (r1.equals("arch") == false) goto L44;
        r3 = 3;
        goto L44
    L33:
        if (r1.equals("ram") == false) goto L44;
        r3 = 2;
        goto L44
    L37:
        if (r1.equals("manufacturer") == false) goto L44;
        r3 = 1;
        goto L44
    L41:
        if (r1.equals("simulator") == false) goto L44;
        r3 = 0;
        goto L44
    L55:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event parseEvent(JsonReader r4) {
        CrashlyticsReport.Session.Event.Builder r0 = CrashlyticsReport.Session.Event.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L40;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1335157162: goto L29;
            case -259312414: goto L25;
            case 96801: goto L21;
            case 107332: goto L17;
            case 3575610: goto L13;
            case 55126294: goto L9;
            default: goto L32;
        };
    L32:
        switch(r3) {
            case 0: goto L39;
            case 1: goto L38;
            case 2: goto L37;
            case 3: goto L36;
            case 4: goto L35;
            case 5: goto L34;
            default: goto L33;
        };
    L34:
        r0.setTimestamp(r4.nextLong());
        goto L4
    L35:
        r0.setType(r4.nextString());
        goto L4
    L36:
        r0.setLog(parseEventLog(r4));
        goto L4
    L37:
        r0.setApp(parseEventApp(r4));
        goto L4
    L38:
        r0.setRollouts(parseEventRolloutsState(r4));
        goto L4
    L39:
        r0.setDevice(parseEventDevice(r4));
        goto L4
    L33:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("timestamp") == false) goto L32;
        r3 = 5;
        goto L32
    L13:
        if (r1.equals("type") == false) goto L32;
        r3 = 4;
        goto L32
    L17:
        if (r1.equals("log") == false) goto L32;
        r3 = 3;
        goto L32
    L21:
        if (r1.equals(Common.ASSET_APP) == false) goto L32;
        r3 = 2;
        goto L32
    L25:
        if (r1.equals("rollouts") == false) goto L32;
        r3 = 1;
        goto L32
    L29:
        if (r1.equals("device") == false) goto L32;
        r3 = 0;
        goto L32
    L40:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.Application parseEventApp(JsonReader r4) {
        CrashlyticsReport.Session.Event.Application.Builder r0 = CrashlyticsReport.Session.Event.Application.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L45;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1405314732: goto L33;
            case -1332194002: goto L29;
            case -1090974952: goto L25;
            case -80231855: goto L21;
            case 555169704: goto L17;
            case 928737948: goto L13;
            case 1847730860: goto L9;
            default: goto L36;
        };
    L36:
        switch(r3) {
            case 0: goto L44;
            case 1: goto L43;
            case 2: goto L42;
            case 3: goto L41;
            case 4: goto L40;
            case 5: goto L39;
            case 6: goto L38;
            default: goto L37;
        };
    L38:
        r0.setCurrentProcessDetails(parseProcessDetails(r4));
        goto L4
    L39:
        r0.setUiOrientation(r4.nextInt());
        goto L4
    L40:
        r0.setCustomAttributes(parseArray(r4, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda1()));
        goto L4
    L41:
        r0.setInternalKeys(parseArray(r4, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda1()));
        goto L4
    L42:
        r0.setExecution(parseEventExecution(r4));
        goto L4
    L43:
        r0.setBackground(Boolean.valueOf(r4.nextBoolean()));
        goto L4
    L44:
        r0.setAppProcessDetails(parseArray(r4, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda2()));
        goto L4
    L37:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("currentProcessDetails") == false) goto L36;
        r3 = 6;
        goto L36
    L13:
        if (r1.equals("uiOrientation") == false) goto L36;
        r3 = 5;
        goto L36
    L17:
        if (r1.equals("customAttributes") == false) goto L36;
        r3 = 4;
        goto L36
    L21:
        if (r1.equals("internalKeys") == false) goto L36;
        r3 = 3;
        goto L36
    L25:
        if (r1.equals("execution") == false) goto L36;
        r3 = 2;
        goto L36
    L29:
        if (r1.equals("background") == false) goto L36;
        r3 = 1;
        goto L36
    L33:
        if (r1.equals("appProcessDetails") == false) goto L36;
        r3 = 0;
        goto L36
    L45:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.Application.Execution.BinaryImage parseEventBinaryImage(JsonReader r5) {
        CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.Builder r0 = CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.builder();
        r5.beginObject();
    L4:
        if (r5.hasNext() == false) goto L30;
        String r1 = r5.nextName();
        r1.getClass();
        char r4 = 65535;
        switch(r1.hashCode()) {
            case 3373707: goto L21;
            case 3530753: goto L17;
            case 3601339: goto L13;
            case 1153765347: goto L9;
            default: goto L24;
        };
    L24:
        switch(r4) {
            case 0: goto L29;
            case 1: goto L28;
            case 2: goto L27;
            case 3: goto L26;
            default: goto L25;
        };
    L26:
        r0.setBaseAddress(r5.nextLong());
        goto L4
    L27:
        r0.setUuidFromUtf8Bytes(Base64.decode(r5.nextString(), 2));
        goto L4
    L28:
        r0.setSize(r5.nextLong());
        goto L4
    L29:
        r0.setName(r5.nextString());
        goto L4
    L25:
        r5.skipValue();
        goto L4
    L9:
        if (r1.equals("baseAddress") == false) goto L24;
        r4 = 3;
        goto L24
    L13:
        if (r1.equals("uuid") == false) goto L24;
        r4 = 2;
        goto L24
    L17:
        if (r1.equals("size") == false) goto L24;
        r4 = 1;
        goto L24
    L21:
        if (r1.equals("name") == false) goto L24;
        r4 = 0;
        goto L24
    L30:
        r5.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.Device parseEventDevice(JsonReader r4) {
        CrashlyticsReport.Session.Event.Device.Builder r0 = CrashlyticsReport.Session.Event.Device.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L40;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1708606089: goto L29;
            case -1455558134: goto L25;
            case -1439500848: goto L21;
            case 279795450: goto L17;
            case 976541947: goto L13;
            case 1516795582: goto L9;
            default: goto L32;
        };
    L32:
        switch(r3) {
            case 0: goto L39;
            case 1: goto L38;
            case 2: goto L37;
            case 3: goto L36;
            case 4: goto L35;
            case 5: goto L34;
            default: goto L33;
        };
    L34:
        r0.setProximityOn(r4.nextBoolean());
        goto L4
    L35:
        r0.setRamUsed(r4.nextLong());
        goto L4
    L36:
        r0.setDiskUsed(r4.nextLong());
        goto L4
    L37:
        r0.setOrientation(r4.nextInt());
        goto L4
    L38:
        r0.setBatteryVelocity(r4.nextInt());
        goto L4
    L39:
        r0.setBatteryLevel(Double.valueOf(r4.nextDouble()));
        goto L4
    L33:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("proximityOn") == false) goto L32;
        r3 = 5;
        goto L32
    L13:
        if (r1.equals("ramUsed") == false) goto L32;
        r3 = 4;
        goto L32
    L17:
        if (r1.equals("diskUsed") == false) goto L32;
        r3 = 3;
        goto L32
    L21:
        if (r1.equals("orientation") == false) goto L32;
        r3 = 2;
        goto L32
    L25:
        if (r1.equals("batteryVelocity") == false) goto L32;
        r3 = 1;
        goto L32
    L29:
        if (r1.equals("batteryLevel") == false) goto L32;
        r3 = 0;
        goto L32
    L40:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.Application.Execution parseEventExecution(JsonReader r4) {
        CrashlyticsReport.Session.Event.Application.Execution.Builder r0 = CrashlyticsReport.Session.Event.Application.Execution.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L40;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1375141843: goto L29;
            case -1337936983: goto L25;
            case -902467928: goto L21;
            case 937615455: goto L17;
            case 1467719157: goto L13;
            case 1481625679: goto L9;
            default: goto L32;
        };
    L32:
        switch(r3) {
            case 0: goto L39;
            case 1: goto L38;
            case 2: goto L37;
            case 3: goto L36;
            case 4: goto L35;
            case 5: goto L34;
            default: goto L33;
        };
    L34:
        r0.setException(parseEventExecutionException(r4));
        goto L4
    L35:
        r0.setProfilingManagerInfo(parseProfilingManagerInfo(r4));
        goto L4
    L36:
        r0.setBinaries(parseArray(r4, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda7()));
        goto L4
    L37:
        r0.setSignal(parseEventSignal(r4));
        goto L4
    L38:
        r0.setThreads(parseArray(r4, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda6()));
        goto L4
    L39:
        r0.setAppExitInfo(parseAppExitInfo(r4));
        goto L4
    L33:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("exception") == false) goto L32;
        r3 = 5;
        goto L32
    L13:
        if (r1.equals("profilingManagerInfo") == false) goto L32;
        r3 = 4;
        goto L32
    L17:
        if (r1.equals("binaries") == false) goto L32;
        r3 = 3;
        goto L32
    L21:
        if (r1.equals("signal") == false) goto L32;
        r3 = 2;
        goto L32
    L25:
        if (r1.equals("threads") == false) goto L32;
        r3 = 1;
        goto L32
    L29:
        if (r1.equals("appExitInfo") == false) goto L32;
        r3 = 0;
        goto L32
    L40:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.Application.Execution.Exception parseEventExecutionException(JsonReader r4) {
        CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder r0 = CrashlyticsReport.Session.Event.Application.Execution.Exception.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L35;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1266514778: goto L25;
            case -934964668: goto L21;
            case 3575610: goto L17;
            case 91997906: goto L13;
            case 581754413: goto L9;
            default: goto L28;
        };
    L28:
        switch(r3) {
            case 0: goto L34;
            case 1: goto L33;
            case 2: goto L32;
            case 3: goto L31;
            case 4: goto L30;
            default: goto L29;
        };
    L30:
        r0.setOverflowCount(r4.nextInt());
        goto L4
    L31:
        r0.setCausedBy(parseEventExecutionException(r4));
        goto L4
    L32:
        r0.setType(r4.nextString());
        goto L4
    L33:
        r0.setReason(r4.nextString());
        goto L4
    L34:
        r0.setFrames(parseArray(r4, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda8()));
        goto L4
    L29:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("overflowCount") == false) goto L28;
        r3 = 4;
        goto L28
    L13:
        if (r1.equals("causedBy") == false) goto L28;
        r3 = 3;
        goto L28
    L17:
        if (r1.equals("type") == false) goto L28;
        r3 = 2;
        goto L28
    L21:
        if (r1.equals("reason") == false) goto L28;
        r3 = 1;
        goto L28
    L25:
        if (r1.equals("frames") == false) goto L28;
        r3 = 0;
        goto L28
    L35:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame parseEventFrame(JsonReader r4) {
        CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder r0 = CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L35;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1019779949: goto L25;
            case -887523944: goto L21;
            case 3571: goto L17;
            case 3143036: goto L13;
            case 2125650548: goto L9;
            default: goto L28;
        };
    L28:
        switch(r3) {
            case 0: goto L34;
            case 1: goto L33;
            case 2: goto L32;
            case 3: goto L31;
            case 4: goto L30;
            default: goto L29;
        };
    L30:
        r0.setImportance(r4.nextInt());
        goto L4
    L31:
        r0.setFile(r4.nextString());
        goto L4
    L32:
        r0.setPc(r4.nextLong());
        goto L4
    L33:
        r0.setSymbol(r4.nextString());
        goto L4
    L34:
        r0.setOffset(r4.nextLong());
        goto L4
    L29:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("importance") == false) goto L28;
        r3 = 4;
        goto L28
    L13:
        if (r1.equals("file") == false) goto L28;
        r3 = 3;
        goto L28
    L17:
        if (r1.equals("pc") == false) goto L28;
        r3 = 2;
        goto L28
    L21:
        if (r1.equals("symbol") == false) goto L28;
        r3 = 1;
        goto L28
    L25:
        if (r1.equals("offset") == false) goto L28;
        r3 = 0;
        goto L28
    L35:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.Log parseEventLog(JsonReader r3) {
        CrashlyticsReport.Session.Event.Log.Builder r0 = CrashlyticsReport.Session.Event.Log.builder();
        r3.beginObject();
    L4:
        if (r3.hasNext() == false) goto L9;
        if (r3.nextName().equals("content") == true) goto L7;
        r3.skipValue();
        goto L4
    L7:
        r0.setContent(r3.nextString());
        goto L4
    L9:
        r3.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.RolloutAssignment parseEventRolloutsAssignment(JsonReader r4) {
        CrashlyticsReport.Session.Event.RolloutAssignment.Builder r0 = CrashlyticsReport.Session.Event.RolloutAssignment.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L30;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1536268810: goto L21;
            case -1027290370: goto L17;
            case 1098747284: goto L13;
            case 1124454216: goto L9;
            default: goto L24;
        };
    L24:
        switch(r3) {
            case 0: goto L29;
            case 1: goto L28;
            case 2: goto L27;
            case 3: goto L26;
            default: goto L25;
        };
    L26:
        r0.setParameterValue(r4.nextString());
        goto L4
    L27:
        r0.setRolloutVariant(parseRolloutAssignmentRolloutVariant(r4));
        goto L4
    L28:
        r0.setTemplateVersion(r4.nextLong());
        goto L4
    L29:
        r0.setParameterKey(r4.nextString());
        goto L4
    L25:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("parameterValue") == false) goto L24;
        r3 = 3;
        goto L24
    L13:
        if (r1.equals("rolloutVariant") == false) goto L24;
        r3 = 2;
        goto L24
    L17:
        if (r1.equals("templateVersion") == false) goto L24;
        r3 = 1;
        goto L24
    L21:
        if (r1.equals("parameterKey") == false) goto L24;
        r3 = 0;
        goto L24
    L30:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.RolloutsState parseEventRolloutsState(JsonReader r3) {
        CrashlyticsReport.Session.Event.RolloutsState.Builder r0 = CrashlyticsReport.Session.Event.RolloutsState.builder();
        r3.beginObject();
    L4:
        if (r3.hasNext() == false) goto L9;
        String r1 = r3.nextName();
        r1.getClass();
        if (r1.equals("assignments") == false) goto L7;
        r0.setRolloutAssignments(parseArray(r3, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda5()));
        goto L4
    L7:
        r3.skipValue();
        goto L4
    L9:
        r3.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.Application.Execution.Signal parseEventSignal(JsonReader r4) {
        CrashlyticsReport.Session.Event.Application.Execution.Signal.Builder r0 = CrashlyticsReport.Session.Event.Application.Execution.Signal.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L25;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1147692044: goto L17;
            case 3059181: goto L13;
            case 3373707: goto L9;
            default: goto L20;
        };
    L20:
        switch(r3) {
            case 0: goto L24;
            case 1: goto L23;
            case 2: goto L22;
            default: goto L21;
        };
    L22:
        r0.setName(r4.nextString());
        goto L4
    L23:
        r0.setCode(r4.nextString());
        goto L4
    L24:
        r0.setAddress(r4.nextLong());
        goto L4
    L21:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("name") == false) goto L20;
        r3 = 2;
        goto L20
    L13:
        if (r1.equals("code") == false) goto L20;
        r3 = 1;
        goto L20
    L17:
        if (r1.equals("address") == false) goto L20;
        r3 = 0;
        goto L20
    L25:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.Application.Execution.Thread parseEventThread(JsonReader r4) {
        CrashlyticsReport.Session.Event.Application.Execution.Thread.Builder r0 = CrashlyticsReport.Session.Event.Application.Execution.Thread.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L25;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -1266514778: goto L17;
            case 3373707: goto L13;
            case 2125650548: goto L9;
            default: goto L20;
        };
    L20:
        switch(r3) {
            case 0: goto L24;
            case 1: goto L23;
            case 2: goto L22;
            default: goto L21;
        };
    L22:
        r0.setImportance(r4.nextInt());
        goto L4
    L23:
        r0.setName(r4.nextString());
        goto L4
    L24:
        r0.setFrames(parseArray(r4, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda8()));
        goto L4
    L21:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("importance") == false) goto L20;
        r3 = 2;
        goto L20
    L13:
        if (r1.equals("name") == false) goto L20;
        r3 = 1;
        goto L20
    L17:
        if (r1.equals("frames") == false) goto L20;
        r3 = 0;
        goto L20
    L25:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.FilesPayload.File parseFile(JsonReader r3) {
        CrashlyticsReport.FilesPayload.File.Builder r0 = CrashlyticsReport.FilesPayload.File.builder();
        r3.beginObject();
    L4:
        if (r3.hasNext() == false) goto L12;
        String r1 = r3.nextName();
        r1.getClass();
        if (r1.equals("filename") == false) goto L8;
        r0.setFilename(r3.nextString());
        goto L4
    L8:
        if (r1.equals("contents") == false) goto L9;
        r0.setContents(Base64.decode(r3.nextString(), 2));
        goto L4
    L9:
        r3.skipValue();
        goto L4
    L12:
        r3.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.FilesPayload parseNdkPayload(JsonReader r3) {
        CrashlyticsReport.FilesPayload.Builder r0 = CrashlyticsReport.FilesPayload.builder();
        r3.beginObject();
    L4:
        if (r3.hasNext() == false) goto L12;
        String r1 = r3.nextName();
        r1.getClass();
        if (r1.equals("files") == false) goto L8;
        r0.setFiles(parseArray(r3, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda4()));
        goto L4
    L8:
        if (r1.equals("orgId") == false) goto L9;
        r0.setOrgId(r3.nextString());
        goto L4
    L9:
        r3.skipValue();
        goto L4
    L12:
        r3.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.OperatingSystem parseOs(JsonReader r4) {
        CrashlyticsReport.Session.OperatingSystem.Builder r0 = CrashlyticsReport.Session.OperatingSystem.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L30;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -911706486: goto L21;
            case -293026577: goto L17;
            case 351608024: goto L13;
            case 1874684019: goto L9;
            default: goto L24;
        };
    L24:
        switch(r3) {
            case 0: goto L29;
            case 1: goto L28;
            case 2: goto L27;
            case 3: goto L26;
            default: goto L25;
        };
    L26:
        r0.setPlatform(r4.nextInt());
        goto L4
    L27:
        r0.setVersion(r4.nextString());
        goto L4
    L28:
        r0.setJailbroken(r4.nextBoolean());
        goto L4
    L29:
        r0.setBuildVersion(r4.nextString());
        goto L4
    L25:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("platform") == false) goto L24;
        r3 = 3;
        goto L24
    L13:
        if (r1.equals("version") == false) goto L24;
        r3 = 2;
        goto L24
    L17:
        if (r1.equals("jailbroken") == false) goto L24;
        r3 = 1;
        goto L24
    L21:
        if (r1.equals("buildVersion") == false) goto L24;
        r3 = 0;
        goto L24
    L30:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.Application.ProcessDetails parseProcessDetails(JsonReader r4) {
        CrashlyticsReport.Session.Event.Application.ProcessDetails.Builder r0 = CrashlyticsReport.Session.Event.Application.ProcessDetails.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L30;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case 110987: goto L21;
            case 202325402: goto L17;
            case 1694598382: goto L13;
            case 2125650548: goto L9;
            default: goto L24;
        };
    L24:
        switch(r3) {
            case 0: goto L29;
            case 1: goto L28;
            case 2: goto L27;
            case 3: goto L26;
            default: goto L25;
        };
    L26:
        r0.setImportance(r4.nextInt());
        goto L4
    L27:
        r0.setDefaultProcess(r4.nextBoolean());
        goto L4
    L28:
        r0.setProcessName(r4.nextString());
        goto L4
    L29:
        r0.setPid(r4.nextInt());
        goto L4
    L25:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("importance") == false) goto L24;
        r3 = 3;
        goto L24
    L13:
        if (r1.equals("defaultProcess") == false) goto L24;
        r3 = 2;
        goto L24
    L17:
        if (r1.equals("processName") == false) goto L24;
        r3 = 1;
        goto L24
    L21:
        if (r1.equals("pid") == false) goto L24;
        r3 = 0;
        goto L24
    L30:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.ProfilingManagerInfo parseProfilingManagerInfo(JsonReader r3) {
        CrashlyticsReport.ProfilingManagerInfo.Builder r0 = CrashlyticsReport.ProfilingManagerInfo.builder();
        r3.beginObject();
    L4:
        if (r3.hasNext() == false) goto L9;
        String r1 = r3.nextName();
        r1.getClass();
        if (r1.equals("profilingTrigger") == false) goto L7;
        r0.setProfilingTrigger(parseProfilingTrigger(r3));
        goto L4
    L7:
        r3.skipValue();
        goto L4
    L9:
        r3.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger parseProfilingTrigger(JsonReader r3) {
        CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger.Builder r0 = CrashlyticsReport.ProfilingManagerInfo.ProfilingTrigger.builder();
        r3.beginObject();
    L4:
        if (r3.hasNext() == false) goto L9;
        String r1 = r3.nextName();
        r1.getClass();
        if (r1.equals("trigger") == false) goto L7;
        r0.setTrigger(r3.nextInt());
        goto L4
    L7:
        r3.skipValue();
        goto L4
    L9:
        r3.endObject();
        return r0.build();
    }

    private static CrashlyticsReport parseReport(JsonReader r4) {
        CrashlyticsReport.Builder r0 = CrashlyticsReport.builder();
        r4.beginObject();
    L4:
        if (r4.hasNext() == false) goto L70;
        String r1 = r4.nextName();
        r1.getClass();
        char r3 = 65535;
        switch(r1.hashCode()) {
            case -2118372775: goto L53;
            case -1962630338: goto L49;
            case -1907185581: goto L45;
            case -1375141843: goto L41;
            case -911706486: goto L37;
            case -401988390: goto L33;
            case 344431858: goto L29;
            case 719853845: goto L25;
            case 1047652060: goto L21;
            case 1874684019: goto L17;
            case 1975623094: goto L13;
            case 1984987798: goto L9;
            default: goto L56;
        };
    L56:
        switch(r3) {
            case 0: goto L69;
            case 1: goto L68;
            case 2: goto L67;
            case 3: goto L66;
            case 4: goto L65;
            case 5: goto L64;
            case 6: goto L63;
            case 7: goto L62;
            case 8: goto L61;
            case 9: goto L60;
            case 10: goto L59;
            case 11: goto L58;
            default: goto L57;
        };
    L58:
        r0.setSession(parseSession(r4));
        goto L4
    L59:
        r0.setDisplayVersion(r4.nextString());
        goto L4
    L60:
        r0.setPlatform(r4.nextInt());
        goto L4
    L61:
        r0.setFirebaseInstallationId(r4.nextString());
        goto L4
    L62:
        r0.setInstallationUuid(r4.nextString());
        goto L4
    L63:
        r0.setGmpAppId(r4.nextString());
        goto L4
    L64:
        r0.setFirebaseAuthenticationToken(r4.nextString());
        goto L4
    L65:
        r0.setBuildVersion(r4.nextString());
        goto L4
    L66:
        r0.setAppExitInfo(parseAppExitInfo(r4));
        goto L4
    L67:
        r0.setAppQualitySessionId(r4.nextString());
        goto L4
    L68:
        r0.setSdkVersion(r4.nextString());
        goto L4
    L69:
        r0.setNdkPayload(parseNdkPayload(r4));
        goto L4
    L57:
        r4.skipValue();
        goto L4
    L9:
        if (r1.equals("session") == false) goto L56;
        r3 = 11;
        goto L56
    L13:
        if (r1.equals("displayVersion") == false) goto L56;
        r3 = '\n';
        goto L56
    L17:
        if (r1.equals("platform") == false) goto L56;
        r3 = '\t';
        goto L56
    L21:
        if (r1.equals("firebaseInstallationId") == false) goto L56;
        r3 = '\b';
        goto L56
    L25:
        if (r1.equals("installationUuid") == false) goto L56;
        r3 = 7;
        goto L56
    L29:
        if (r1.equals("gmpAppId") == false) goto L56;
        r3 = 6;
        goto L56
    L33:
        if (r1.equals("firebaseAuthenticationToken") == false) goto L56;
        r3 = 5;
        goto L56
    L37:
        if (r1.equals("buildVersion") == false) goto L56;
        r3 = 4;
        goto L56
    L41:
        if (r1.equals("appExitInfo") == false) goto L56;
        r3 = 3;
        goto L56
    L45:
        if (r1.equals("appQualitySessionId") == false) goto L56;
        r3 = 2;
        goto L56
    L49:
        if (r1.equals("sdkVersion") == false) goto L56;
        r3 = 1;
        goto L56
    L53:
        if (r1.equals("ndkPayload") == false) goto L56;
        r3 = 0;
        goto L56
    L70:
        r4.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant parseRolloutAssignmentRolloutVariant(JsonReader r3) {
        CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.Builder r0 = CrashlyticsReport.Session.Event.RolloutAssignment.RolloutVariant.builder();
        r3.beginObject();
    L4:
        if (r3.hasNext() == false) goto L12;
        String r1 = r3.nextName();
        r1.getClass();
        if (r1.equals("variantId") == false) goto L8;
        r0.setVariantId(r3.nextString());
        goto L4
    L8:
        if (r1.equals("rolloutId") == false) goto L9;
        r0.setRolloutId(r3.nextString());
        goto L4
    L9:
        r3.skipValue();
        goto L4
    L12:
        r3.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session parseSession(JsonReader r5) {
        CrashlyticsReport.Session.Builder r0 = CrashlyticsReport.Session.builder();
        r5.beginObject();
    L4:
        if (r5.hasNext() == false) goto L70;
        String r1 = r5.nextName();
        r1.getClass();
        char r4 = 65535;
        switch(r1.hashCode()) {
            case -2128794476: goto L53;
            case -1907185581: goto L49;
            case -1618432855: goto L45;
            case -1606742899: goto L41;
            case -1335157162: goto L37;
            case -1291329255: goto L33;
            case 3556: goto L29;
            case 96801: goto L25;
            case 3599307: goto L21;
            case 286956243: goto L17;
            case 1025385094: goto L13;
            case 2047016109: goto L9;
            default: goto L56;
        };
    L56:
        switch(r4) {
            case 0: goto L69;
            case 1: goto L68;
            case 2: goto L67;
            case 3: goto L66;
            case 4: goto L65;
            case 5: goto L64;
            case 6: goto L63;
            case 7: goto L62;
            case 8: goto L61;
            case 9: goto L60;
            case 10: goto L59;
            case 11: goto L58;
            default: goto L57;
        };
    L58:
        r0.setGeneratorType(r5.nextInt());
        goto L4
    L59:
        r0.setCrashed(r5.nextBoolean());
        goto L4
    L60:
        r0.setGenerator(r5.nextString());
        goto L4
    L61:
        r0.setUser(parseUser(r5));
        goto L4
    L62:
        r0.setApp(parseApp(r5));
        goto L4
    L63:
        r0.setOs(parseOs(r5));
        goto L4
    L64:
        r0.setEvents(parseArray(r5, new CrashlyticsReportJsonTransform$$ExternalSyntheticLambda3()));
        goto L4
    L65:
        r0.setDevice(parseDevice(r5));
        goto L4
    L66:
        r0.setEndedAt(Long.valueOf(r5.nextLong()));
        goto L4
    L67:
        r0.setIdentifierFromUtf8Bytes(Base64.decode(r5.nextString(), 2));
        goto L4
    L68:
        r0.setAppQualitySessionId(r5.nextString());
        goto L4
    L69:
        r0.setStartedAt(r5.nextLong());
        goto L4
    L57:
        r5.skipValue();
        goto L4
    L9:
        if (r1.equals("generatorType") == false) goto L56;
        r4 = 11;
        goto L56
    L13:
        if (r1.equals("crashed") == false) goto L56;
        r4 = '\n';
        goto L56
    L17:
        if (r1.equals("generator") == false) goto L56;
        r4 = '\t';
        goto L56
    L21:
        if (r1.equals("user") == false) goto L56;
        r4 = '\b';
        goto L56
    L25:
        if (r1.equals(Common.ASSET_APP) == false) goto L56;
        r4 = 7;
        goto L56
    L29:
        if (r1.equals("os") == false) goto L56;
        r4 = 6;
        goto L56
    L33:
        if (r1.equals("events") == false) goto L56;
        r4 = 5;
        goto L56
    L37:
        if (r1.equals("device") == false) goto L56;
        r4 = 4;
        goto L56
    L41:
        if (r1.equals("endedAt") == false) goto L56;
        r4 = 3;
        goto L56
    L45:
        if (r1.equals("identifier") == false) goto L56;
        r4 = 2;
        goto L56
    L49:
        if (r1.equals("appQualitySessionId") == false) goto L56;
        r4 = 1;
        goto L56
    L53:
        if (r1.equals("startedAt") == false) goto L56;
        r4 = 0;
        goto L56
    L70:
        r5.endObject();
        return r0.build();
    }

    private static CrashlyticsReport.Session.User parseUser(JsonReader r3) {
        CrashlyticsReport.Session.User.Builder r0 = CrashlyticsReport.Session.User.builder();
        r3.beginObject();
    L4:
        if (r3.hasNext() == false) goto L9;
        if (r3.nextName().equals("identifier") == true) goto L7;
        r3.skipValue();
        goto L4
    L7:
        r0.setIdentifier(r3.nextString());
        goto L4
    L9:
        r3.endObject();
        return r0.build();
    }

    public CrashlyticsReport.Session.Event eventFromJson(String r2) {
        JsonReader r1 = new JsonReader(new StringReader(r2));     // Catch: IllegalStateException -> L12
        CrashlyticsReport.Session.Event r22 = parseEvent(r1);     // Catch: Throwable -> L6
        r1.close();     // Catch: IllegalStateException -> L12
        return r22;
    L6:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L9
    L11:
        throw th;     // Catch: IllegalStateException -> L12
    L9:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IllegalStateException -> L12
    L12:
        e = move-exception;
        throw new IOException(e);
    }

    public String eventToJson(CrashlyticsReport.Session.Event r1) {
        return CRASHLYTICS_REPORT_JSON_ENCODER.encode(r1);
    }

    public CrashlyticsReport.ProfilingManagerInfo profilingManagerInfoFromJson(String r2) {
        JsonReader r1 = new JsonReader(new StringReader(r2));     // Catch: IllegalStateException -> L12
        CrashlyticsReport.ProfilingManagerInfo r22 = parseProfilingManagerInfo(r1);     // Catch: Throwable -> L6
        r1.close();     // Catch: IllegalStateException -> L12
        return r22;
    L6:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L9
    L11:
        throw th;     // Catch: IllegalStateException -> L12
    L9:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IllegalStateException -> L12
    L12:
        e = move-exception;
        throw new IOException(e);
    }

    public String profilingManagerInfoToJson(CrashlyticsReport.ProfilingManagerInfo r1) {
        return CRASHLYTICS_REPORT_JSON_ENCODER.encode(r1);
    }

    public CrashlyticsReport reportFromJson(String r2) {
        JsonReader r1 = new JsonReader(new StringReader(r2));     // Catch: IllegalStateException -> L12
        CrashlyticsReport r22 = parseReport(r1);     // Catch: Throwable -> L6
        r1.close();     // Catch: IllegalStateException -> L12
        return r22;
    L6:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L9
    L11:
        throw th;     // Catch: IllegalStateException -> L12
    L9:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IllegalStateException -> L12
    L12:
        e = move-exception;
        throw new IOException(e);
    }

    public String reportToJson(CrashlyticsReport r1) {
        return CRASHLYTICS_REPORT_JSON_ENCODER.encode(r1);
    }
}
