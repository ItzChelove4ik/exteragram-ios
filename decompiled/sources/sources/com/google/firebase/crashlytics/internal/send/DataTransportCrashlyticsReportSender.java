package com.google.firebase.crashlytics.internal.send;

import android.content.Context;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.common.OnDemandCounter;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class DataTransportCrashlyticsReportSender {
    private static final String CRASHLYTICS_API_KEY = null;
    private static final String CRASHLYTICS_ENDPOINT = null;
    private static final Transformer<CrashlyticsReport, byte[]> DEFAULT_TRANSFORM = null;
    private static final CrashlyticsReportJsonTransform TRANSFORM = null;
    private final ReportQueue reportQueue;
    private final Transformer<CrashlyticsReport, byte[]> transportTransform;

    public static /* synthetic */ byte[] $r8$lambda$attPQxgaGsTF0cmNu7MI4T0zqoY(CrashlyticsReport r1) {
        return TRANSFORM.reportToJson(r1).getBytes(Charset.forName("UTF-8"));
    }

    static {
        TRANSFORM = new CrashlyticsReportJsonTransform();
        CRASHLYTICS_ENDPOINT = mergeStrings("hts/cahyiseot-agolai.o/1frlglgc/aclg", "tp:/rsltcrprsp.ogepscmv/ieo/eaybtho");
        CRASHLYTICS_API_KEY = mergeStrings("AzSBpY4F0rHiHFdinTvM", "IayrSTFL9eJ69YeSUO2");
        DEFAULT_TRANSFORM = new DataTransportCrashlyticsReportSender$$ExternalSyntheticLambda0();
    }

    public DataTransportCrashlyticsReportSender(ReportQueue r1, Transformer<CrashlyticsReport, byte[]> r2) {
        this.reportQueue = r1;
        this.transportTransform = r2;
    }

    public static DataTransportCrashlyticsReportSender create(Context r4, SettingsProvider r5, OnDemandCounter r6) {
        TransportRuntime.initialize(r4);
        TransportFactory r42 = TransportRuntime.getInstance().newFactory(new CCTDestination(CRASHLYTICS_ENDPOINT, CRASHLYTICS_API_KEY));
        Encoding r0 = Encoding.of("json");
        Transformer<CrashlyticsReport, byte[]> r1 = DEFAULT_TRANSFORM;
        return new DataTransportCrashlyticsReportSender(new ReportQueue(r42.getTransport("FIREBASE_CRASHLYTICS_REPORT", CrashlyticsReport.class, r0, r1), r5.getSettingsSync(), r6), r1);
    }

    private static String mergeStrings(String r3, String r4) {
        int r0 = r3.length() - r4.length();
        if (r0 >= 0) goto L5;
    L15:
        f$$ExternalSyntheticBUOutline1.m("Invalid input received");
        return null;
    L5:
        if (r0 > 1) goto L15;
        StringBuilder r02 = new StringBuilder(r3.length() + r4.length());
        int r1 = 0;
    L8:
        if (r1 >= r3.length()) goto L14;
        r02.append(r3.charAt(r1));
        if (r4.length() <= r1) goto L12;
        r02.append(r4.charAt(r1));
    L12:
        r1 = r1 + 1;
        goto L8
    L14:
        return r02.toString();
    }

    public Task<CrashlyticsReportWithSessionId> enqueueReport(CrashlyticsReportWithSessionId r1, boolean r2) {
        return this.reportQueue.enqueueReport(r1, r2).getTask();
    }
}
