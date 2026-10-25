package com.google.firebase.sessions;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.google.android.gms.common.util.ProcessUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"Lcom/google/firebase/sessions/ProcessDetailsProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getAppProcessDetails", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/firebase/sessions/ProcessDetails;", "context", "Landroid/content/Context;", "getMyProcessDetails", "getProcessName", _UrlKt.FRAGMENT_ENCODE_SET, "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProcessDetailsProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessDetailsProvider.kt\ncom/google/firebase/sessions/ProcessDetailsProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,84:1\n774#2:85\n865#2,2:86\n1557#2:88\n1628#2,3:89\n1#3:92\n*S KotlinDebug\n*F\n+ 1 ProcessDetailsProvider.kt\ncom/google/firebase/sessions/ProcessDetailsProvider\n*L\n37#1:85\n37#1:86,2\n41#1:88\n41#1:89,3\n*E\n"})
/* loaded from: classes.dex */
public final class ProcessDetailsProvider {
    public static final ProcessDetailsProvider INSTANCE = null;

    static {
        INSTANCE = new ProcessDetailsProvider();
    }

    private ProcessDetailsProvider() {
    }

    private final String getProcessName() {
        int r1 = Build.VERSION.SDK_INT;
        if (r1 <= 33) goto L7;
        return Process.myProcessName();
    L7:
        if (r1 < 28) goto L11;
        String r12 = Application.getProcessName();
        if (r12 == null) goto L11;
        return r12;
    L11:
        String r13 = ProcessUtils.getMyProcessName();
        if (r13 == null) goto L14;
        return r13;
    L14:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public final List<ProcessDetails> getAppProcessDetails(Context r9) {
        int r8 = r9.getApplicationInfo().uid;
        String r0 = r9.getApplicationInfo().processName;
        Object r92 = r9.getSystemService("activity");
        if ((r92 instanceof ActivityManager) == false) goto L5;
        ActivityManager r93 = (ActivityManager) r92;
    L6:
        if (r93 == null) goto L9;
        List<ActivityManager.RunningAppProcessInfo> r94 = r93.getRunningAppProcesses();
        if (r94 == null) goto L9;
    L10:
        List r95 = CollectionsKt.filterNotNull(r94);
        ArrayList r1 = new ArrayList();
        Iterator r96 = r95.iterator();
    L12:
        if (r96.hasNext() == false) goto L16;
        Object r2 = r96.next();
        if (((ActivityManager.RunningAppProcessInfo) r2).uid != r8) goto L12;
        r1.add(r2);
        goto L12
    L16:
        ArrayList r82 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r1, 10));
        int r97 = r1.size();
        int r22 = 0;
    L17:
        if (r22 >= r97) goto L19;
        Object r3 = r1.get(r22);
        r22 = r22 + 1;
        ActivityManager.RunningAppProcessInfo r32 = (ActivityManager.RunningAppProcessInfo) r3;
        String r5 = r32.processName;
        r82.add(new ProcessDetails(r5, r32.pid, r32.importance, Intrinsics.areEqual(r5, r0)));
        goto L17
    L19:
        return r82;
    L9:
        r94 = CollectionsKt.emptyList();
        goto L10
    L5:
        r93 = null;
        goto L6
    }

    public final ProcessDetails getMyProcessDetails(Context r4) {
        int r0 = Process.myPid();
        Iterator<T> r42 = getAppProcessDetails(r4).iterator();
    L4:
        if (r42.hasNext() == false) goto L8;
        Object r1 = r42.next();
        if (((ProcessDetails) r1).getPid() != r0) goto L4;
    L9:
        ProcessDetails r12 = (ProcessDetails) r1;
        if (r12 == null) goto L12;
        return r12;
    L12:
        return new ProcessDetails(getProcessName(), r0, 0, false);
    L8:
        r1 = null;
        goto L9
    }
}
