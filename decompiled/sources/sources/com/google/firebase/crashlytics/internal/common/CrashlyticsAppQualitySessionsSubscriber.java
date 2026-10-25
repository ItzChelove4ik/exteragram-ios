package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.sessions.api.SessionSubscriber;

/* loaded from: classes.dex */
public class CrashlyticsAppQualitySessionsSubscriber implements SessionSubscriber {
    private final CrashlyticsAppQualitySessionsStore appQualitySessionsStore;
    private final DataCollectionArbiter dataCollectionArbiter;

    public CrashlyticsAppQualitySessionsSubscriber(DataCollectionArbiter r1, FileStore r2) {
        this.dataCollectionArbiter = r1;
        this.appQualitySessionsStore = new CrashlyticsAppQualitySessionsStore(r2);
    }

    public String getAppQualitySessionId(String r1) {
        return this.appQualitySessionsStore.getAppQualitySessionId(r1);
    }

    @Override // com.google.firebase.sessions.api.SessionSubscriber
    public SessionSubscriber.Name getSessionSubscriberName() {
        return SessionSubscriber.Name.CRASHLYTICS;
    }

    @Override // com.google.firebase.sessions.api.SessionSubscriber
    public boolean isDataCollectionEnabled() {
        return this.dataCollectionArbiter.isAutomaticDataCollectionEnabled();
    }

    @Override // com.google.firebase.sessions.api.SessionSubscriber
    public void onSessionChanged(SessionSubscriber.SessionDetails r4) {
        Logger.getLogger().d("App Quality Sessions session changed: " + r4);
        this.appQualitySessionsStore.rotateAppQualitySessionId(r4.getSessionId());
    }

    public void setSessionId(String r1) {
        this.appQualitySessionsStore.rotateSessionId(r1);
    }
}
