package com.exteragram.messenger.preferences.chats;

import com.exteragram.messenger.ExteraConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* synthetic */ class ChatsPreferencesActivity$aiFeatures$1$2 extends MutablePropertyReference0Impl {
    public static final ChatsPreferencesActivity$aiFeatures$1$2 INSTANCE = null;

    static {
        INSTANCE = new ChatsPreferencesActivity$aiFeatures$1$2();
    }

    public ChatsPreferencesActivity$aiFeatures$1$2() {
        super(ExteraConfig.class, "telegramAiEditor", "getTelegramAiEditor()Z", 1);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
    public Object get() {
        return Boolean.valueOf(ExteraConfig.getTelegramAiEditor());
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
    public void set(Object r1) {
        ExteraConfig.setTelegramAiEditor(((Boolean) r1).booleanValue());
    }
}
