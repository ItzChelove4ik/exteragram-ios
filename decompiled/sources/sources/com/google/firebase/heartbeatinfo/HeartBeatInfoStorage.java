package com.google.firebase.heartbeatinfo;

import android.content.Context;
import android.os.Build;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import com.google.firebase.datastorage.JavaDataStorage;
import com.google.firebase.datastorage.JavaDataStorageKt;
import j$.time.ZoneOffset;
import j$.time.format.DateTimeFormatter;
import j$.util.DateRetargetClass;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
class HeartBeatInfoStorage {
    private static final Preferences.Key<Long> GLOBAL = null;
    private static final Preferences.Key<Long> HEART_BEAT_COUNT_TAG = null;
    private static final Preferences.Key<String> LAST_STORED_DATE = null;
    private final JavaDataStorage firebaseDataStore;

    public static /* synthetic */ Unit $r8$lambda$5pvxTqvYzVSgjYLkNwY7V7Tywa4(long r1, MutablePreferences r3) {
        r3.set(GLOBAL, Long.valueOf(r1));
        return null;
    }

    public static /* synthetic */ Unit $r8$lambda$HdDmHlus2RmE_6N0PPHuiT70IIc(HeartBeatInfoStorage r11, String r12, String r13, Preferences.Key r14, MutablePreferences r15) {
        r11.getClass();
        Preferences.Key<String> r0 = LAST_STORED_DATE;
        if (((String) JavaDataStorageKt.getOrDefault(r15, r0, _UrlKt.FRAGMENT_ENCODE_SET)).equals(r12) == false) goto L12;
        Preferences.Key<Set<String>> r02 = r11.getStoredUserAgentString(r15, r12);
        if (r02 != null) goto L8;
    L9:
        return null;
    L8:
        if (r02.getName().equals(r13) == true) goto L9;
        r11.updateStoredUserAgent(r15, r14, r12);
        return null;
    L12:
        Preferences.Key<Long> r132 = HEART_BEAT_COUNT_TAG;
        long r3 = ((Long) JavaDataStorageKt.getOrDefault(r15, r132, 0L)).longValue();
        if ((r3 + 1) != 30) goto L15;
        r3 = r11.cleanUpStoredHeartBeats(r15);
    L15:
        HashSet r112 = new HashSet((Collection) JavaDataStorageKt.getOrDefault(r15, r14, new HashSet()));
        r112.add(r12);
        r15.set(r14, r112);
        r15.set(r132, Long.valueOf(r3 + 1));
        r15.set(r0, r12);
        return null;
    }

    public static /* synthetic */ Unit $r8$lambda$oWSjuxBuWFTEWQSHNy3S1bm6Uuw(HeartBeatInfoStorage r9, MutablePreferences r10) {
        r9.getClass();
        Iterator<Map.Entry<Preferences.Key<?>, Object>> r0 = r10.asMap().entrySet().iterator();
        long r3 = 0;
    L4:
        if (r0.hasNext() == false) goto L12;
        Map.Entry<Preferences.Key<?>, Object> r5 = r0.next();
        if ((r5.getValue() instanceof Set) == false) goto L4;
        Preferences.Key<?> r6 = r5.getKey();
        Set r52 = (Set) r5.getValue();
        String r7 = r9.getFormattedDate(System.currentTimeMillis());
        if (r52.contains(r7) == true) goto L9;
        r10.remove(r6);
        goto L4
    L9:
        r10.set(r6, HeartBeatInfoStorage$$ExternalSyntheticBackport0.m(r7));
        r3 = r3 + 1;
        goto L4
    L12:
        if (r3 != 0) goto L14;
        r10.remove(HEART_BEAT_COUNT_TAG);
        return null;
    L14:
        r10.set(HEART_BEAT_COUNT_TAG, Long.valueOf(r3));
        return null;
    }

    public static /* synthetic */ Unit $r8$lambda$pOe2GViMywkzZWGROGoUTcj02Nw(HeartBeatInfoStorage r1, String r2, MutablePreferences r3) {
        r1.getClass();
        r3.set(LAST_STORED_DATE, r2);
        r1.removeStoredDate(r3, r2);
        return null;
    }

    static {
        GLOBAL = PreferencesKeys.longKey("fire-global");
        HEART_BEAT_COUNT_TAG = PreferencesKeys.longKey("fire-count");
        LAST_STORED_DATE = PreferencesKeys.stringKey("last-used-date");
    }

    public HeartBeatInfoStorage(Context r4, String r5) {
        this.firebaseDataStore = new JavaDataStorage(r4, "FirebaseHeartBeat" + r5);
    }

    private long cleanUpStoredHeartBeats(MutablePreferences r11) {
        long r0 = ((Long) JavaDataStorageKt.getOrDefault(r11, HEART_BEAT_COUNT_TAG, 0L)).longValue();
        Set r10 = new HashSet();
        Iterator<Map.Entry<Preferences.Key<?>, Object>> r2 = r11.asMap().entrySet().iterator();
        String r3 = null;
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        if (r2.hasNext() == false) goto L15;
        Map.Entry<Preferences.Key<?>, Object> r5 = r2.next();
        if ((r5.getValue() instanceof Set) == false) goto L4;
        Set r6 = (Set) r5.getValue();
        Iterator r7 = r6.iterator();
    L9:
        if (r7.hasNext() == false) goto L4;
        String r8 = (String) r7.next();
        if (r3 == null) goto L14;
        if (r3.compareTo(r8) <= 0) goto L9;
    L14:
        r4 = r5.getKey().getName();
        r10 = r6;
        r3 = r8;
        goto L9
    L15:
        HashSet r22 = new HashSet(r10);
        r22.remove(r3);
        r11.set(PreferencesKeys.stringSetKey(r4), r22);
        long r02 = r0 - 1;
        r11.set(HEART_BEAT_COUNT_TAG, Long.valueOf(r02));
        return r02;
    }

    private String getFormattedDate(long r3) {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return DateRetargetClass.toInstant(new Date(r3)).atOffset(ZoneOffset.UTC).toLocalDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE);
    L7:
        return new SimpleDateFormat("yyyy-MM-dd", Locale.UK).format(new Date(r3));
    }

    private Preferences.Key<Set<String>> getStoredUserAgentString(MutablePreferences r3, String r4) {
        Iterator<Map.Entry<Preferences.Key<?>, Object>> r2 = r3.asMap().entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L14;
        Map.Entry<Preferences.Key<?>, Object> r32 = r2.next();
        if ((r32.getValue() instanceof Set) == false) goto L4;
        Iterator r0 = ((Set) r32.getValue()).iterator();
    L9:
        if (r0.hasNext() == false) goto L4;
        if (r4.equals((String) r0.next()) == false) goto L9;
        return PreferencesKeys.stringSetKey(r32.getKey().getName());
    L14:
        return null;
    }

    private void removeStoredDate(MutablePreferences r3, String r4) {
        Preferences.Key<Set<String>> r2 = getStoredUserAgentString(r3, r4);
        if (r2 != null) goto L5;
        return;
    L5:
        HashSet r0 = new HashSet((Collection) JavaDataStorageKt.getOrDefault(r3, r2, new HashSet()));
        r0.remove(r4);
        if (r0.isEmpty() == false) goto L9;
        r3.remove(r2);
        return;
    L9:
        r3.set(r2, r0);
    }

    private void updateStoredUserAgent(MutablePreferences r2, Preferences.Key<Set<String>> r3, String r4) {
        removeStoredDate(r2, r4);
        HashSet r1 = new HashSet((Collection) JavaDataStorageKt.getOrDefault(r2, r3, new HashSet()));
        r1.add(r4);
        r2.set(r3, r1);
    }

    public synchronized void deleteAllHeartBeats() {
        monitor-enter(this);
        this.firebaseDataStore.editSync(new HeartBeatInfoStorage$$ExternalSyntheticLambda2(this));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized List<HeartBeatResult> getAllHeartBeats() {
        monitor-enter(this);
        ArrayList r0 = new ArrayList();     // Catch: Throwable -> L11
        String r1 = getFormattedDate(System.currentTimeMillis());     // Catch: Throwable -> L11
        Iterator<Map.Entry<Preferences.Key<?>, Object>> r2 = this.firebaseDataStore.getAllSync().entrySet().iterator();     // Catch: Throwable -> L11
    L5:
        if (r2.hasNext() == false) goto L13;
        Map.Entry<Preferences.Key<?>, Object> r3 = r2.next();     // Catch: Throwable -> L11
        if ((r3.getValue() instanceof Set) == false) goto L5;
        HashSet r4 = new HashSet((Set) r3.getValue());     // Catch: Throwable -> L11
        r4.remove(r1);     // Catch: Throwable -> L11
        if (r4.isEmpty() == true) goto L5;
        r0.add(HeartBeatResult.create(r3.getKey().getName(), new ArrayList(r4)));     // Catch: Throwable -> L11
        goto L5
    L13:
        updateGlobalHeartBeat(System.currentTimeMillis());     // Catch: Throwable -> L11
        monitor-exit(this);
        return r0;
    L11:
        th = move-exception;
        throw th;
    }

    public boolean isSameDateUtc(long r1, long r3) {
        return getFormattedDate(r1).equals(getFormattedDate(r3));
    }

    public synchronized void postHeartBeatCleanUp() {
        monitor-enter(this);
        String r0 = getFormattedDate(System.currentTimeMillis());     // Catch: Throwable -> L6
        this.firebaseDataStore.editSync(new HeartBeatInfoStorage$$ExternalSyntheticLambda4(this, r0));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean shouldSendGlobalHeartBeat(long r2) {
        monitor-enter(this);
        boolean r22 = shouldSendSdkHeartBeat(GLOBAL, r2);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r22;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean shouldSendSdkHeartBeat(Preferences.Key<Long> r4, long r5) {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (isSameDateUtc(((Long) this.firebaseDataStore.getSync(r4, -1L)).longValue(), r5) == false) goto L8;
        monitor-exit(this);
        return false;
    L8:
        this.firebaseDataStore.putSync(r4, Long.valueOf(r5));     // Catch: Throwable -> L12
        monitor-exit(this);
        return true;
    }

    public synchronized void storeHeartBeat(long r3, String r5) {
        monitor-enter(this);
        String r32 = getFormattedDate(r3);     // Catch: Throwable -> L6
        Preferences.Key<Set<String>> r4 = PreferencesKeys.stringSetKey(r5);     // Catch: Throwable -> L6
        this.firebaseDataStore.editSync(new HeartBeatInfoStorage$$ExternalSyntheticLambda1(this, r32, r5, r4));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void updateGlobalHeartBeat(long r3) {
        monitor-enter(this);
        this.firebaseDataStore.editSync(new HeartBeatInfoStorage$$ExternalSyntheticLambda3(r3));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
