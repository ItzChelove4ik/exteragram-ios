package com.exteragram.messenger.preferences.chats;

import com.exteragram.messenger.ExteraConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* synthetic */ class ChatsPreferencesActivity$pauseOnMinimize$1$1 extends MutablePropertyReference0Impl {
    public static final ChatsPreferencesActivity$pauseOnMinimize$1$1 INSTANCE = null;

    static {
        INSTANCE = new ChatsPreferencesActivity$pauseOnMinimize$1$1();
    }

    public ChatsPreferencesActivity$pauseOnMinimize$1$1() {
        super(ExteraConfig.class, "pauseOnMinimizeVideo", "getPauseOnMinimizeVideo()Z", 1);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
    public Object get() {
        return Boolean.valueOf(ExteraConfig.getPauseOnMinimizeVideo());
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
    public void set(Object r1) {
        ExteraConfig.setPauseOnMinimizeVideo(((Boolean) r1).booleanValue());
    }
}
