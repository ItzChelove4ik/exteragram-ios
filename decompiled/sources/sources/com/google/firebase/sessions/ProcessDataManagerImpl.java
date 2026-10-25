package com.google.firebase.sessions;

import android.content.Context;
import android.os.Process;
import com.google.firebase.sessions.ProcessDataManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\u001c\u001a\u00020\u001b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\u001c\u0010 \u001a\u00020\u001b2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001f0\u001eH\u0016J\b\u0010!\u001a\u00020\"H\u0016J*\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001f0\u001e2\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001eH\u0016J\u000e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00160%H\u0002J\u0018\u0010&\u001a\u00020\u001b2\u0006\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\t8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\r\u001a\u0004\b\u0013\u0010\u000bR\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\r\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/google/firebase/sessions/ProcessDataManagerImpl;", "Lcom/google/firebase/sessions/ProcessDataManager;", "appContext", "Landroid/content/Context;", "uuidGenerator", "Lcom/google/firebase/sessions/UuidGenerator;", "<init>", "(Landroid/content/Context;Lcom/google/firebase/sessions/UuidGenerator;)V", "myProcessName", _UrlKt.FRAGMENT_ENCODE_SET, "getMyProcessName", "()Ljava/lang/String;", "myProcessName$delegate", "Lkotlin/Lazy;", "myPid", _UrlKt.FRAGMENT_ENCODE_SET, "getMyPid", "()I", "myUuid", "getMyUuid", "myUuid$delegate", "myProcessDetails", "Lcom/google/firebase/sessions/ProcessDetails;", "getMyProcessDetails", "()Lcom/google/firebase/sessions/ProcessDetails;", "myProcessDetails$delegate", "hasGeneratedSession", _UrlKt.FRAGMENT_ENCODE_SET, "isColdStart", "processDataMap", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/firebase/sessions/ProcessData;", "isMyProcessStale", "onSessionGenerated", _UrlKt.FRAGMENT_ENCODE_SET, "updateProcessDataMap", "getAppProcessDetails", _UrlKt.FRAGMENT_ENCODE_SET, "isProcessStale", "processDetails", "processData", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProcessDataManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessDataManager.kt\ncom/google/firebase/sessions/ProcessDataManagerImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,123:1\n1611#2,9:124\n1863#2:133\n1864#2:135\n1620#2:136\n1734#2,3:137\n1#3:134\n1#3:140\n*S KotlinDebug\n*F\n+ 1 ProcessDataManager.kt\ncom/google/firebase/sessions/ProcessDataManagerImpl\n*L\n78#1:124,9\n78#1:133\n78#1:135\n78#1:136\n83#1:137,3\n78#1:134\n*E\n"})
/* loaded from: classes.dex */
public final class ProcessDataManagerImpl implements ProcessDataManager {
    private final Context appContext;
    private boolean hasGeneratedSession;
    private final int myPid;
    private final Lazy myProcessDetails$delegate;
    private final Lazy myProcessName$delegate;
    private final Lazy myUuid$delegate;

    /* renamed from: $r8$lambda$67jLber-fsQV4Gp3r7jMmg4UtGA, reason: not valid java name */
    public static ProcessDetails m1981$r8$lambda$67jLberfsQV4Gp3r7jMmg4UtGA(ProcessDataManagerImpl r1) {
        return ProcessDetailsProvider.INSTANCE.getMyProcessDetails(r1.appContext);
    }

    /* renamed from: $r8$lambda$_UeSF9Hfqk5X0TmWVjAr-kv5nKY, reason: not valid java name */
    public static String m1982$r8$lambda$_UeSF9Hfqk5X0TmWVjArkv5nKY(ProcessDataManagerImpl r0) {
        return r0.getMyProcessDetails().getProcessName();
    }

    public static String $r8$lambda$cs14fneC4A25JpcnwPq0DmKyQWk(UuidGenerator r0) {
        return r0.next().toString();
    }

    public ProcessDataManagerImpl(Context r1, UuidGenerator r2) {
        this.appContext = r1;
        this.myProcessName$delegate = LazyKt.lazy(new ProcessDataManagerImpl$$ExternalSyntheticLambda0(this));
        this.myPid = Process.myPid();
        this.myUuid$delegate = LazyKt.lazy(new ProcessDataManagerImpl$$ExternalSyntheticLambda1(r2));
        this.myProcessDetails$delegate = LazyKt.lazy(new ProcessDataManagerImpl$$ExternalSyntheticLambda2(this));
    }

    private final List<ProcessDetails> getAppProcessDetails() {
        return ProcessDetailsProvider.INSTANCE.getAppProcessDetails(this.appContext);
    }

    private final ProcessDetails getMyProcessDetails() {
        return (ProcessDetails) this.myProcessDetails$delegate.getValue();
    }

    private final boolean isProcessStale(ProcessDetails r4, ProcessData r5) {
        if (Intrinsics.areEqual(getMyProcessName(), r4.getProcessName()) == false) goto L12;
        if (r4.getPid() == r5.getPid()) goto L7;
    L10:
        return true;
    L7:
        if (Intrinsics.areEqual(getMyUuid(), r5.getUuid()) == false) goto L10;
        return false;
    L12:
        if (r4.getPid() == r5.getPid()) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // com.google.firebase.sessions.ProcessDataManager
    public Map<String, ProcessData> generateProcessDataMap() {
        return ProcessDataManager.DefaultImpls.generateProcessDataMap(this);
    }

    public int getMyPid() {
        return this.myPid;
    }

    @Override // com.google.firebase.sessions.ProcessDataManager
    public String getMyProcessName() {
        return (String) this.myProcessName$delegate.getValue();
    }

    public String getMyUuid() {
        return (String) this.myUuid$delegate.getValue();
    }

    @Override // com.google.firebase.sessions.ProcessDataManager
    public boolean isColdStart(Map<String, ProcessData> r7) {
        if (this.hasGeneratedSession == false) goto L5;
        return false;
    L5:
        List<ProcessDetails> r0 = getAppProcessDetails();
        ArrayList r2 = new ArrayList();
        Iterator<T> r02 = r0.iterator();
    L7:
        if (r02.hasNext() == false) goto L15;
        ProcessDetails r3 = (ProcessDetails) r02.next();
        ProcessData r4 = r7.get(r3.getProcessName());
        if (r4 == null) goto L11;
        Pair r5 = new Pair(r3, r4);
    L12:
        if (r5 == null) goto L7;
        r2.add(r5);
        goto L7
    L11:
        r5 = null;
        goto L12
    L15:
        if (r2.isEmpty() == false) goto L17;
        return true;
    L17:
        int r72 = r2.size();
        int r32 = 0;
    L18:
        if (r32 >= r72) goto L22;
        Object r42 = r2.get(r32);
        r32 = r32 + 1;
        Pair r43 = (Pair) r42;
        if (isProcessStale((ProcessDetails) r43.component1(), (ProcessData) r43.component2()) == true) goto L18;
        return false;
    L22:
        return true;
    }

    @Override // com.google.firebase.sessions.ProcessDataManager
    public boolean isMyProcessStale(Map<String, ProcessData> r4) {
        ProcessData r42 = r4.get(getMyProcessName());
        if (r42 != null) goto L6;
        return true;
    L6:
        if (r42.getPid() == getMyPid()) goto L8;
    L12:
        return true;
    L8:
        if (Intrinsics.areEqual(r42.getUuid(), getMyUuid()) == false) goto L12;
        return false;
    }

    @Override // com.google.firebase.sessions.ProcessDataManager
    public void onSessionGenerated() {
        this.hasGeneratedSession = true;
    }

    @Override // com.google.firebase.sessions.ProcessDataManager
    public Map<String, ProcessData> updateProcessDataMap(Map<String, ProcessData> r5) {
        if (r5 == null) goto L9;
        Map r52 = MapsKt.toMutableMap(r5);
        if (r52 == null) goto L9;
        r52.put(getMyProcessName(), new ProcessData(Process.myPid(), getMyUuid()));
        Map<String, ProcessData> r53 = MapsKt.toMap(r52);
        if (r53 == null) goto L9;
        return r53;
    L9:
        return MapsKt.mapOf(TuplesKt.to(getMyProcessName(), new ProcessData(Process.myPid(), getMyUuid())));
    }
}
