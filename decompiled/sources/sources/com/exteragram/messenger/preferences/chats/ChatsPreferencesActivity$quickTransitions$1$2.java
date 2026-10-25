package com.exteragram.messenger.preferences.chats;

import com.exteragram.messenger.ExteraConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* synthetic */ class ChatsPreferencesActivity$quickTransitions$1$2 extends MutablePropertyReference0Impl {
    public static final ChatsPreferencesActivity$quickTransitions$1$2 INSTANCE = null;

    static {
        INSTANCE = new ChatsPreferencesActivity$quickTransitions$1$2();
    }

    public ChatsPreferencesActivity$quickTransitions$1$2() {
        super(ExteraConfig.class, "quickTransitionForTopics", "getQuickTransitionForTopics()Z", 1);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
    public Object get() {
        return Boolean.valueOf(ExteraConfig.getQuickTransitionForTopics());
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
    public void set(Object r1) {
        ExteraConfig.setQuickTransitionForTopics(((Boolean) r1).booleanValue());
    }
}
