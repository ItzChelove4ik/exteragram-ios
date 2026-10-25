package com.exteragram.messenger.preferences.chats;

import com.exteragram.messenger.ExteraConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* synthetic */ class ChatsPreferencesActivity$pauseOnMinimize$1$3 extends MutablePropertyReference0Impl {
    public static final ChatsPreferencesActivity$pauseOnMinimize$1$3 INSTANCE = null;

    static {
        INSTANCE = new ChatsPreferencesActivity$pauseOnMinimize$1$3();
    }

    public ChatsPreferencesActivity$pauseOnMinimize$1$3() {
        super(ExteraConfig.class, "pauseOnMinimizeRound", "getPauseOnMinimizeRound()Z", 1);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
    public Object get() {
        return Boolean.valueOf(ExteraConfig.getPauseOnMinimizeRound());
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
    public void set(Object r1) {
        ExteraConfig.setPauseOnMinimizeRound(((Boolean) r1).booleanValue());
    }
}
