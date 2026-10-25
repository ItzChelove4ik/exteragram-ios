package com.google.firebase.crashlytics.internal;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ.\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007J\b\u0010\u0012\u001a\u00020\fH\u0002¨\u0006\u0013"}, d2 = {"Lcom/google/firebase/crashlytics/internal/ProcessDetailsProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getAppProcessDetails", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/firebase/crashlytics/internal/model/CrashlyticsReport$Session$Event$Application$ProcessDetails;", "context", "Landroid/content/Context;", "getCurrentProcessDetails", "buildProcessDetails", "processName", _UrlKt.FRAGMENT_ENCODE_SET, "pid", _UrlKt.FRAGMENT_ENCODE_SET, "importance", "isDefaultProcess", _UrlKt.FRAGMENT_ENCODE_SET, "getProcessName", "com.google.firebase-firebase-crashlytics"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProcessDetailsProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessDetailsProvider.kt\ncom/google/firebase/crashlytics/internal/ProcessDetailsProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,94:1\n774#2:95\n865#2,2:96\n1557#2:98\n1628#2,3:99\n1#3:102\n*S KotlinDebug\n*F\n+ 1 ProcessDetailsProvider.kt\ncom/google/firebase/crashlytics/internal/ProcessDetailsProvider\n*L\n43#1:95\n43#1:96,2\n47#1:98\n47#1:99,3\n*E\n"})
/* loaded from: classes.dex */
public final class ProcessDetailsProvider {
    public static final ProcessDetailsProvider INSTANCE = null;

    static {
        INSTANCE = new ProcessDetailsProvider();
    }

    private ProcessDetailsProvider() {
    }

    public static /* synthetic */ CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails$default(ProcessDetailsProvider r1, String r2, int r3, int r4, boolean r5, int r6, Object r7) {
        if ((r6 & 2) == 0) goto L6;
        r3 = 0;
    L6:
        if ((r6 & 4) == 0) goto L9;
        r4 = 0;
    L9:
        if ((r6 & 8) == 0) goto L12;
        r5 = false;
    L12:
        return r1.buildProcessDetails(r2, r3, r4, r5);
    }

    private final String getProcessName() {
        int r2 = Build.VERSION.SDK_INT;
        if (r2 <= 33) goto L7;
        return Process.myProcessName();
    L7:
        if (r2 < 28) goto L12;
        String r22 = Application.getProcessName();
        if (r22 != null) goto L11;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L11:
        return r22;
    L12:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @JvmOverloads
    public final CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails(String r8, int r9, int r10) {
        return buildProcessDetails$default(this, r8, r9, r10, false, 8, null);
    }

    @JvmOverloads
    public final CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails(String r1, int r2, int r3, boolean r4) {
        return CrashlyticsReport.Session.Event.Application.ProcessDetails.builder().setProcessName(r1).setPid(r2).setImportance(r3).setDefaultProcess(r4).build();
    }

    public final List<CrashlyticsReport.Session.Event.Application.ProcessDetails> getAppProcessDetails(Context r7) {
        int r6 = r7.getApplicationInfo().uid;
        String r0 = r7.getApplicationInfo().processName;
        Object r72 = r7.getSystemService("activity");
        if ((r72 instanceof ActivityManager) == false) goto L5;
        ActivityManager r73 = (ActivityManager) r72;
    L6:
        if (r73 == null) goto L9;
        List<ActivityManager.RunningAppProcessInfo> r74 = r73.getRunningAppProcesses();
        if (r74 == null) goto L9;
    L10:
        List r75 = CollectionsKt.filterNotNull(r74);
        ArrayList r1 = new ArrayList();
        Iterator r76 = r75.iterator();
    L12:
        if (r76.hasNext() == false) goto L16;
        Object r2 = r76.next();
        if (((ActivityManager.RunningAppProcessInfo) r2).uid != r6) goto L12;
        r1.add(r2);
        goto L12
    L16:
        ArrayList r62 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r1, 10));
        int r77 = r1.size();
        int r22 = 0;
    L17:
        if (r22 >= r77) goto L19;
        Object r3 = r1.get(r22);
        r22 = r22 + 1;
        ActivityManager.RunningAppProcessInfo r32 = (ActivityManager.RunningAppProcessInfo) r3;
        r62.add(CrashlyticsReport.Session.Event.Application.ProcessDetails.builder().setProcessName(r32.processName).setPid(r32.pid).setImportance(r32.importance).setDefaultProcess(Intrinsics.areEqual(r32.processName, r0)).build());
        goto L17
    L19:
        return r62;
    L9:
        r74 = CollectionsKt.emptyList();
        goto L10
    L5:
        r73 = null;
        goto L6
    }

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails getCurrentProcessDetails(Context r8) {
        int r2 = Process.myPid();
        Iterator<T> r82 = getAppProcessDetails(r8).iterator();
    L4:
        if (r82.hasNext() == false) goto L8;
        Object r0 = r82.next();
        if (((CrashlyticsReport.Session.Event.Application.ProcessDetails) r0).getPid() != r2) goto L4;
    L9:
        CrashlyticsReport.Session.Event.Application.ProcessDetails r02 = (CrashlyticsReport.Session.Event.Application.ProcessDetails) r0;
        if (r02 == null) goto L12;
        return r02;
    L12:
        return buildProcessDetails$default(this, getProcessName(), r2, 0, false, 12, null);
    L8:
        r0 = null;
        goto L9
    }
}
