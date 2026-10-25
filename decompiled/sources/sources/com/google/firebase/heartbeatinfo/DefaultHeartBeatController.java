package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.util.Base64OutputStream;
import androidx.core.os.UserManagerCompat;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Lazy;
import com.google.firebase.components.Qualified;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.inject.Provider;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.zip.GZIPOutputStream;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DefaultHeartBeatController implements HeartBeatController, HeartBeatInfo {
    private final Context applicationContext;
    private final Executor backgroundExecutor;
    private final Set<HeartBeatConsumer> consumers;
    private final Provider<HeartBeatInfoStorage> storageProvider;
    private final Provider<UserAgentPublisher> userAgentProvider;

    /* renamed from: $r8$lambda$NKSgqEy24k0xnerWqr-lPNg6qcc, reason: not valid java name */
    public static /* synthetic */ String m1954$r8$lambda$NKSgqEy24k0xnerWqrlPNg6qcc(DefaultHeartBeatController r7) {
        monitor-enter(r7);
        HeartBeatInfoStorage r0 = r7.storageProvider.get();     // Catch: Throwable -> L7
        List<HeartBeatResult> r1 = r0.getAllHeartBeats();     // Catch: Throwable -> L7
        r0.deleteAllHeartBeats();     // Catch: Throwable -> L7
        JSONArray r02 = new JSONArray();     // Catch: Throwable -> L7
        int r2 = 0;
    L5:
        if (r2 >= r1.size()) goto L9;
        HeartBeatResult r3 = r1.get(r2);     // Catch: Throwable -> L7
        JSONObject r4 = new JSONObject();     // Catch: Throwable -> L7
        r4.put("agent", r3.getUserAgent());     // Catch: Throwable -> L7
        r4.put("dates", new JSONArray(r3.getUsedDates()));     // Catch: Throwable -> L7
        r02.put(r4);     // Catch: Throwable -> L7
        r2 = r2 + 1;     // Catch: Throwable -> L7
        goto L5
    L9:
        JSONObject r12 = new JSONObject();     // Catch: Throwable -> L7
        r12.put("heartbeats", r02);     // Catch: Throwable -> L7
        r12.put("version", "2");     // Catch: Throwable -> L7
        ByteArrayOutputStream r03 = new ByteArrayOutputStream();     // Catch: Throwable -> L7
        Base64OutputStream r22 = new Base64OutputStream(r03, 11);     // Catch: Throwable -> L7
        GZIPOutputStream r32 = new GZIPOutputStream(r22);     // Catch: Throwable -> L16
        r32.write(r12.toString().getBytes("UTF-8"));     // Catch: Throwable -> L18
        r32.close();     // Catch: Throwable -> L16
        r22.close();     // Catch: Throwable -> L7
        String r04 = r03.toString("UTF-8");     // Catch: Throwable -> L7
        monitor-exit(r7);     // Catch: Throwable -> L7
        return r04;
    L18:
        th = move-exception;
        r32.close();     // Catch: Throwable -> L21
    L23:
        throw th;     // Catch: Throwable -> L16
    L21:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L16
    L16:
        th = move-exception;
        r22.close();     // Catch: Throwable -> L26
    L28:
        throw th;     // Catch: Throwable -> L7
    L26:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ HeartBeatInfoStorage $r8$lambda$W62DRj4tEJPeea0uD3QbdJLqqVQ(Context r1, String r2) {
        return new HeartBeatInfoStorage(r1, r2);
    }

    public static /* synthetic */ DefaultHeartBeatController $r8$lambda$qqd15T_K2a1gIcWWKlBsXOoLnRU(Qualified r6, ComponentContainer r7) {
        return new DefaultHeartBeatController((Context) r7.get(Context.class), ((FirebaseApp) r7.get(FirebaseApp.class)).getPersistenceKey(), r7.setOf(HeartBeatConsumer.class), r7.getProvider(UserAgentPublisher.class), (Executor) r7.get(r6));
    }

    public static /* synthetic */ Void $r8$lambda$ssxGEaCSc5qotNfnf_nT87dJ5TU(DefaultHeartBeatController r4) {
        monitor-enter(r4);
        r4.storageProvider.get().storeHeartBeat(System.currentTimeMillis(), r4.userAgentProvider.get().getUserAgent());     // Catch: Throwable -> L7
        monitor-exit(r4);     // Catch: Throwable -> L7
        return null;
    L7:
        th = move-exception;
        throw th;
    }

    private DefaultHeartBeatController(Context r3, String r4, Set<HeartBeatConsumer> r5, Provider<UserAgentPublisher> r6, Executor r7) {
        this(new Lazy(new DefaultHeartBeatController$$ExternalSyntheticLambda1(r3, r4)), r5, r7, r6, r3);
    }

    public DefaultHeartBeatController(Provider<HeartBeatInfoStorage> r1, Set<HeartBeatConsumer> r2, Executor r3, Provider<UserAgentPublisher> r4, Context r5) {
        this.storageProvider = r1;
        this.consumers = r2;
        this.backgroundExecutor = r3;
        this.userAgentProvider = r4;
        this.applicationContext = r5;
    }

    public static Component<DefaultHeartBeatController> component() {
        Qualified r0 = Qualified.qualified(Background.class, Executor.class);
        return Component.builder(DefaultHeartBeatController.class, new Class[]{HeartBeatController.class, HeartBeatInfo.class}).add(Dependency.required(Context.class)).add(Dependency.required(FirebaseApp.class)).add(Dependency.setOf(HeartBeatConsumer.class)).add(Dependency.requiredProvider(UserAgentPublisher.class)).add(Dependency.required(r0)).factory(new DefaultHeartBeatController$$ExternalSyntheticLambda0(r0)).build();
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatInfo
    public synchronized HeartBeatInfo.HeartBeat getHeartBeatCode(String r3) {
        monitor-enter(this);
        long r0 = System.currentTimeMillis();     // Catch: Throwable -> L8
        HeartBeatInfoStorage r32 = this.storageProvider.get();     // Catch: Throwable -> L8
        if (r32.shouldSendGlobalHeartBeat(r0) == false) goto L10;
        r32.postHeartBeatCleanUp();     // Catch: Throwable -> L8
        HeartBeatInfo.HeartBeat r33 = HeartBeatInfo.HeartBeat.GLOBAL;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r33;
    L10:
        HeartBeatInfo.HeartBeat r34 = HeartBeatInfo.HeartBeat.NONE;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r34;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // com.google.firebase.heartbeatinfo.HeartBeatController
    public Task<String> getHeartBeatsHeader() {
        if (UserManagerCompat.isUserUnlocked(this.applicationContext) == true) goto L7;
        return Tasks.forResult(_UrlKt.FRAGMENT_ENCODE_SET);
    L7:
        return Tasks.call(this.backgroundExecutor, new DefaultHeartBeatController$$ExternalSyntheticLambda2(this));
    }

    public Task<Void> registerHeartBeat() {
        if (this.consumers.size() > 0) goto L7;
        return Tasks.forResult(null);
    L7:
        if (UserManagerCompat.isUserUnlocked(this.applicationContext) == true) goto L11;
        return Tasks.forResult(null);
    L11:
        return Tasks.call(this.backgroundExecutor, new DefaultHeartBeatController$$ExternalSyntheticLambda3(this));
    }
}
