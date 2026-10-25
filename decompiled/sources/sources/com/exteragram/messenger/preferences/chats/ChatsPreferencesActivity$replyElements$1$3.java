package com.exteragram.messenger.preferences.chats;

import com.exteragram.messenger.ExteraConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0Impl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* synthetic */ class ChatsPreferencesActivity$replyElements$1$3 extends MutablePropertyReference0Impl {
    public static final ChatsPreferencesActivity$replyElements$1$3 INSTANCE = null;

    static {
        INSTANCE = new ChatsPreferencesActivity$replyElements$1$3();
    }

    public ChatsPreferencesActivity$replyElements$1$3() {
        super(ExteraConfig.class, "replyEmoji", "getReplyEmoji()Z", 1);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
    public Object get() {
        return Boolean.valueOf(ExteraConfig.getReplyEmoji());
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
    public void set(Object r1) {
        ExteraConfig.setReplyEmoji(((Boolean) r1).booleanValue());
    }
}
