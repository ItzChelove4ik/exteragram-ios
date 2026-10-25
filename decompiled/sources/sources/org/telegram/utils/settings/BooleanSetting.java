package org.telegram.utils.settings;

/* loaded from: classes6.dex */
public final class BooleanSetting {
    private final boolean defaultValue;
    private volatile boolean loaded;
    private final String name;
    private volatile boolean value;

    private BooleanSetting(String r1, boolean r2) {
        this.name = r1;
        this.defaultValue = r2;
    }

    public static BooleanSetting of(String r1, boolean r2) {
        return new BooleanSetting(r1, r2);
    }

    private void setLocked(boolean r2) {
        this.value = r2;
        this.loaded = true;
        SettingsPreferences.get().edit().putBoolean(this.name, r2).apply();
    }

    public boolean get() {
        if (this.loaded == true) goto L15;
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L6:
        if (this.loaded == true) goto L10;
        this.value = SettingsPreferences.get().getBoolean(this.name, this.defaultValue);     // Catch: Throwable -> L8
        this.loaded = true;     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
    L15:
        return this.value;
    }

    public synchronized void set(boolean r1) {
        monitor-enter(this);
        setLocked(r1);     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean toggle() {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.loaded == true) goto L8;
        this.value = SettingsPreferences.get().getBoolean(this.name, this.defaultValue);     // Catch: Throwable -> L6
        this.loaded = true;     // Catch: Throwable -> L6
    L8:
        boolean r0 = !this.value;
        setLocked(r0);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    }
}
