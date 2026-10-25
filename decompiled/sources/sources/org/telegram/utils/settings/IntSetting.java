package org.telegram.utils.settings;

/* loaded from: classes6.dex */
public final class IntSetting {
    private final int defaultValue;
    private volatile boolean loaded;
    private final String name;
    private volatile int value;

    private IntSetting(String r1, int r2) {
        this.name = r1;
        this.defaultValue = r2;
    }

    public static IntSetting of(String r1, int r2) {
        return new IntSetting(r1, r2);
    }

    public int get() {
        if (this.loaded == true) goto L15;
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L6:
        if (this.loaded == true) goto L10;
        this.value = SettingsPreferences.get().getInt(this.name, this.defaultValue);     // Catch: Throwable -> L8
        this.loaded = true;     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
    L15:
        return this.value;
    }

    public synchronized void set(int r3) {
        monitor-enter(this);
        this.value = r3;     // Catch: Throwable -> L6
        this.loaded = true;     // Catch: Throwable -> L6
        SettingsPreferences.get().edit().putInt(this.name, r3).apply();     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
