package com.google.firebase.crashlytics.internal.settings;

/* loaded from: classes.dex */
enum SettingsCacheBehavior extends Enum<SettingsCacheBehavior> {
    private static final /* synthetic */ SettingsCacheBehavior[] $VALUES = null;
    public static final SettingsCacheBehavior IGNORE_CACHE_EXPIRATION = null;
    public static final SettingsCacheBehavior SKIP_CACHE_LOOKUP = null;
    public static final SettingsCacheBehavior USE_CACHE = null;

    private static /* synthetic */ SettingsCacheBehavior[] $values() {
        return new SettingsCacheBehavior[]{USE_CACHE, SKIP_CACHE_LOOKUP, IGNORE_CACHE_EXPIRATION};
    }

    static {
        USE_CACHE = new SettingsCacheBehavior("USE_CACHE", 0);
        SKIP_CACHE_LOOKUP = new SettingsCacheBehavior("SKIP_CACHE_LOOKUP", 1);
        IGNORE_CACHE_EXPIRATION = new SettingsCacheBehavior("IGNORE_CACHE_EXPIRATION", 2);
        $VALUES = $values();
    }

    SettingsCacheBehavior(String r1, int r2) {
    }

    public static SettingsCacheBehavior valueOf(String r1) {
        return (SettingsCacheBehavior) Enum.valueOf(SettingsCacheBehavior.class, r1);
    }

    public static SettingsCacheBehavior[] values() {
        return (SettingsCacheBehavior[]) $VALUES.clone();
    }
}
