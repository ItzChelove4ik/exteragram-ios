package com.exteragram.messenger.badges;

import android.content.SharedPreferences;
import com.exteragram.messenger.utils.network.RemoteUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.telegram.messenger.FileLog;

/* loaded from: classes.dex */
public final class CachedRemoteSet {
    private volatile Set<Long> cachedValues;
    private SharedPreferences.OnSharedPreferenceChangeListener changeListener;
    private final Set<Long> defaultSet;
    private volatile boolean listenerInitialized;
    private final String remoteKey;

    public static /* synthetic */ void $r8$lambda$RyFSv9iDDI2QzqhBhIKCF5AVOYg(CachedRemoteSet r0, SharedPreferences r1, String r2) {
        r0.lambda$initializeListener$0(r1, r2);
    }

    public CachedRemoteSet(String r2, Set<Long> r3) {
        this.listenerInitialized = false;
        this.remoteKey = r2;
        this.defaultSet = CachedRemoteSet$$ExternalSyntheticBackport0.m(r3);
        initializeListener();
    }

    private Set<Long> getSet() {
        if (this.listenerInitialized == true) goto L5;
        initializeListener();
    L5:
        Set r0 = this.cachedValues;
        if (r0 != null) goto L22;
        SharedPreferences r02 = RemoteUtils.sharedPreferences;
        if (r02 != null) goto L10;
    L19:
        Set r1 = this.defaultSet;
    L20:
        r0 = ConcurrentHashMap.newKeySet(r1.size());
        r0.addAll(r1);
        this.cachedValues = r0;
        goto L22
    L10:
        if (r02.contains(this.remoteKey) == false) goto L19;
        Set<String> r03 = r02.getStringSet(this.remoteKey, Collections.EMPTY_SET);
        r1 = new HashSet(r03.size());
        Iterator<String> r04 = r03.iterator();
    L13:
        if (r04.hasNext() == false) goto L20;
        String r2 = r04.next();
        r1.add(Long.valueOf(Long.parseLong(r2)));     // Catch: NumberFormatException -> L17
    L17:
        e = move-exception;
        FileLog.e("Failed to parse long from remote config for key " + this.remoteKey + ": " + r2, e);
    L22:
        return Collections.unmodifiableSet(r0);
    }

    private void initializeListener() {
        if (this.listenerInitialized == true) goto L8;
        RemoteUtils.initCached();
        if (RemoteUtils.sharedPreferences == null) goto L9;
        CachedRemoteSet$$ExternalSyntheticLambda1 r0 = new CachedRemoteSet$$ExternalSyntheticLambda1(this);
        this.changeListener = r0;
        RemoteUtils.sharedPreferences.registerOnSharedPreferenceChangeListener(r0);
        this.listenerInitialized = true;
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$initializeListener$0(SharedPreferences r1, String r2) {
        if (this.remoteKey.equals(r2) == false) goto L6;
        this.cachedValues = null;
        return;
    }

    public boolean contains(long r1) {
        return getSet().contains(Long.valueOf(r1));
    }
}
