package com.exteragram.messenger.preferences.appearance;

import com.exteragram.messenger.ExteraConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* synthetic */ class AppearancePreferencesActivity$md3Styles$1$2 extends MutablePropertyReference0Impl {
    public static final AppearancePreferencesActivity$md3Styles$1$2 INSTANCE = null;

    static {
        INSTANCE = new AppearancePreferencesActivity$md3Styles$1$2();
    }

    public AppearancePreferencesActivity$md3Styles$1$2() {
        super(ExteraConfig.class, "newLoadingStyle", "getNewLoadingStyle()Z", 1);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
    public Object get() {
        return Boolean.valueOf(ExteraConfig.getNewLoadingStyle());
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
    public void set(Object r1) {
        ExteraConfig.setNewLoadingStyle(((Boolean) r1).booleanValue());
    }
}
