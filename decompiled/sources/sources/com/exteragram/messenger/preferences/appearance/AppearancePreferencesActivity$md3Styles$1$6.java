package com.exteragram.messenger.preferences.appearance;

import com.exteragram.messenger.ExteraConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* synthetic */ class AppearancePreferencesActivity$md3Styles$1$6 extends MutablePropertyReference0Impl {
    public static final AppearancePreferencesActivity$md3Styles$1$6 INSTANCE = null;

    static {
        INSTANCE = new AppearancePreferencesActivity$md3Styles$1$6();
    }

    public AppearancePreferencesActivity$md3Styles$1$6() {
        super(ExteraConfig.class, "newNavigationBarStyle", "getNewNavigationBarStyle()Z", 1);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
    public Object get() {
        return Boolean.valueOf(ExteraConfig.getNewNavigationBarStyle());
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
    public void set(Object r1) {
        ExteraConfig.setNewNavigationBarStyle(((Boolean) r1).booleanValue());
    }
}
