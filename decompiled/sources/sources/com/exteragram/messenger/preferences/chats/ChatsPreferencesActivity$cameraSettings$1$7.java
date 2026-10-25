package com.exteragram.messenger.preferences.chats;

import com.exteragram.messenger.ExteraConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* synthetic */ class ChatsPreferencesActivity$cameraSettings$1$7 extends MutablePropertyReference0Impl {
    public static final ChatsPreferencesActivity$cameraSettings$1$7 INSTANCE = null;

    static {
        INSTANCE = new ChatsPreferencesActivity$cameraSettings$1$7();
    }

    public ChatsPreferencesActivity$cameraSettings$1$7() {
        super(ExteraConfig.class, "cameraMirrorMode", "getCameraMirrorMode()Z", 1);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
    public Object get() {
        return Boolean.valueOf(ExteraConfig.getCameraMirrorMode());
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
    public void set(Object r1) {
        ExteraConfig.setCameraMirrorMode(((Boolean) r1).booleanValue());
    }
}
