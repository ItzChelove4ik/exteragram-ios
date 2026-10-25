package com.google.firebase.crashlytics.internal.metadata;

import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class UserMetadata {
    private final CrashlyticsWorkers crashlyticsWorkers;
    private final SerializeableKeysMap customKeys;
    private final SerializeableKeysMap internalKeys;
    private final MetaDataStore metaDataStore;
    private final RolloutAssignmentList rolloutsState;
    private String sessionIdentifier;
    private final AtomicMarkableReference<String> userId;

    public class SerializeableKeysMap {
        private final boolean isInternal;
        final AtomicMarkableReference<KeysMap> map;
        private final AtomicReference<Runnable> queuedSerializer;
        final /* synthetic */ UserMetadata this$0;

        public static /* synthetic */ void $r8$lambda$Tye6__azynwk5CtjZ7JFhmAJpog(SerializeableKeysMap r2) {
            r2.queuedSerializer.set(null);
            r2.serializeIfMarked();
        }

        public SerializeableKeysMap(UserMetadata r2, boolean r3) {
            this.this$0 = r2;
            this.queuedSerializer = new AtomicReference(null);
            this.isInternal = r3;
            if (r3 == false) goto L5;
            int r32 = 8192;
        L6:
            this.map = new AtomicMarkableReference(new KeysMap(64, r32), false);
            return;
        L5:
            r32 = 1024;
            goto L6
        }

        private void scheduleSerializationTaskIfNeeded() {
            UserMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0 r0 = new UserMetadata$SerializeableKeysMap$$ExternalSyntheticLambda0(this);
            if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(this.queuedSerializer, null, r0) == false) goto L6;
            UserMetadata.access$000(this.this$0).diskWrite.submit(r0);
            return;
        }

        private void serializeIfMarked() {
            monitor-enter(this);
        L6:
            th = move-exception;
            throw th;
        L4:
            if (this.map.isMarked() == false) goto L8;
            Map<String, String> r0 = this.map.getReference().getKeys();     // Catch: Throwable -> L6
            AtomicMarkableReference<KeysMap> r1 = this.map;     // Catch: Throwable -> L6
            r1.set(r1.getReference(), false);     // Catch: Throwable -> L6
        L9:
            monitor-exit(this);     // Catch: Throwable -> L6
            if (r0 == null) goto L17;
            UserMetadata.access$200(this.this$0).writeKeyData(UserMetadata.access$100(this.this$0), r0, this.isInternal);
            return;
        L17:
            return;
        L8:
            r0 = null;
            goto L9
        }

        public Map<String, String> getKeys() {
            return this.map.getReference().getKeys();
        }

        public boolean setKey(String r2, String r3) {
            monitor-enter(this);
        L8:
            th = move-exception;
            throw th;
        L4:
            if (this.map.getReference().setKey(r2, r3) == false) goto L6;
            AtomicMarkableReference<KeysMap> r22 = this.map;     // Catch: Throwable -> L8
            r22.set(r22.getReference(), true);     // Catch: Throwable -> L8
            monitor-exit(this);     // Catch: Throwable -> L8
            scheduleSerializationTaskIfNeeded();
            return true;
        L6:
            monitor-exit(this);     // Catch: Throwable -> L8
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$9WBO6JABCheg763bQqAim4lG_Rw(UserMetadata r1, List r2) {
        r1.metaDataStore.writeRolloutState(r1.sessionIdentifier, r2);
    }

    /* renamed from: $r8$lambda$sua7MVTuuODCDLBTra-qW_AyS1g, reason: not valid java name */
    public static /* synthetic */ void m1942$r8$lambda$sua7MVTuuODCDLBTraqW_AyS1g(UserMetadata r2, String r3, Map r4, List r5) {
        if (r2.getUserId() == null) goto L6;
        r2.metaDataStore.writeUserData(r3, r2.getUserId());
    L6:
        if (r4.isEmpty() == true) goto L9;
        r2.metaDataStore.writeKeyData(r3, r4);
    L9:
        if (r5.isEmpty() == true) goto L12;
        r2.metaDataStore.writeRolloutState(r3, r5);
        return;
    }

    public UserMetadata(String r4, FileStore r5, CrashlyticsWorkers r6) {
        this.customKeys = new SerializeableKeysMap(this, false);
        this.internalKeys = new SerializeableKeysMap(this, true);
        this.rolloutsState = new RolloutAssignmentList(128);
        this.userId = new AtomicMarkableReference(null, false);
        this.sessionIdentifier = r4;
        this.metaDataStore = new MetaDataStore(r5);
        this.crashlyticsWorkers = r6;
    }

    public static /* synthetic */ CrashlyticsWorkers access$000(UserMetadata r0) {
        return r0.crashlyticsWorkers;
    }

    public static /* synthetic */ String access$100(UserMetadata r0) {
        return r0.sessionIdentifier;
    }

    public static /* synthetic */ MetaDataStore access$200(UserMetadata r0) {
        return r0.metaDataStore;
    }

    public static UserMetadata loadFromExistingSession(String r3, FileStore r4, CrashlyticsWorkers r5) {
        MetaDataStore r0 = new MetaDataStore(r4);
        UserMetadata r1 = new UserMetadata(r3, r4, r5);
        r1.customKeys.map.getReference().setKeys(r0.readKeyData(r3, false));
        r1.internalKeys.map.getReference().setKeys(r0.readKeyData(r3, true));
        r1.userId.set(r0.readUserId(r3), false);
        r1.rolloutsState.updateRolloutAssignmentList(r0.readRolloutsState(r3));
        return r1;
    }

    public static String readUserId(String r1, FileStore r2) {
        return new MetaDataStore(r2).readUserId(r1);
    }

    public Map<String, String> getCustomKeys(Map<String, String> r7) {
        boolean r0 = r7.isEmpty();
        SerializeableKeysMap r6 = this.customKeys;
        if (r0 == true) goto L5;
        HashMap r02 = new HashMap(r6.getKeys());
        Iterator<Map.Entry<String, String>> r62 = r7.entrySet().iterator();
        int r72 = 0;
    L8:
        if (r62.hasNext() == false) goto L16;
        Map.Entry<String, String> r1 = r62.next();
        String r2 = KeysMap.sanitizeString(r1.getKey(), 1024);
        if (r02.size() < 64) goto L15;
        if (r02.containsKey(r2) == true) goto L15;
        r72 = r72 + 1;
    L15:
        r02.put(r2, KeysMap.sanitizeString(r1.getValue(), 1024));
        goto L8
    L16:
        if (r72 <= 0) goto L19;
        Logger.getLogger().w("Ignored " + r72 + " keys when adding event specific keys. Maximum allowable: 1024");
    L19:
        return Collections.unmodifiableMap(r02);
    L5:
        return r6.getKeys();
    }

    public Map<String, String> getInternalKeys() {
        return this.internalKeys.getKeys();
    }

    public List<CrashlyticsReport.Session.Event.RolloutAssignment> getRolloutsState() {
        return this.rolloutsState.getReportRolloutsState();
    }

    public String getUserId() {
        return this.userId.getReference();
    }

    public boolean setCustomKey(String r1, String r2) {
        return this.customKeys.setKey(r1, r2);
    }

    public boolean setInternalKey(String r1, String r2) {
        return this.internalKeys.setKey(r1, r2);
    }

    public void setNewSession(String r6) {
        String r0 = this.sessionIdentifier;
        monitor-enter(r0);
        this.sessionIdentifier = r6;     // Catch: Throwable -> L7
        Map<String, String> r1 = this.customKeys.getKeys();     // Catch: Throwable -> L7
        List<RolloutAssignment> r2 = this.rolloutsState.getRolloutAssignmentList();     // Catch: Throwable -> L7
        this.crashlyticsWorkers.diskWrite.submit(new UserMetadata$$ExternalSyntheticLambda0(this, r6, r1, r2));     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean updateRolloutsState(List<RolloutAssignment> r4) {
        RolloutAssignmentList r0 = this.rolloutsState;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.rolloutsState.updateRolloutAssignmentList(r4) == false) goto L7;
        List<RolloutAssignment> r42 = this.rolloutsState.getRolloutAssignmentList();     // Catch: Throwable -> L9
        this.crashlyticsWorkers.diskWrite.submit(new UserMetadata$$ExternalSyntheticLambda1(this, r42));     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return true;
    L7:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return false;
    }
}
