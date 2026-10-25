package org.telegram.utils.settings;

import java.lang.Enum;

/* loaded from: classes6.dex */
public final class EnumSetting<T extends Enum<T>> {
    private final T defaultValue;
    private volatile boolean loaded;
    private final String name;
    private volatile T value;

    private EnumSetting(String r1, T r2) {
        this.name = r1;
        this.defaultValue = r2;
    }

    public static <T extends Enum<T>> EnumSetting<T> of(String r1, T r2) {
        return new EnumSetting(r1, r2);
    }

    private T readValue() {
        String r0 = SettingsPreferences.get().getString(this.name, this.defaultValue.name());
        if (r0 == null) goto L7;
        return (T) Enum.valueOf(this.defaultValue.getDeclaringClass(), r0);
    L7:
        return this.defaultValue;
    }

    public T get() {
        if (this.loaded == true) goto L15;
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L6:
        if (this.loaded == true) goto L10;
        this.value = (T) readValue();     // Catch: Throwable -> L8
        this.loaded = true;     // Catch: Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L8
    L15:
        return this.value;
    }

    public synchronized void set(T r3) {
        monitor-enter(this);
        this.value = r3;     // Catch: Throwable -> L6
        this.loaded = true;     // Catch: Throwable -> L6
        SettingsPreferences.get().edit().putString(this.name, r3.name()).apply();     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
