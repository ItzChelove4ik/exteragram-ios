package com.exteragram.messenger.preferences.chats;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* synthetic */ class ChatsPreferencesActivity$hideReactions$1$1 extends FunctionReferenceImpl implements Function0<Unit> {
    public ChatsPreferencesActivity$hideReactions$1$1(Object r8) {
        super(0, r8, ChatsPreferencesActivity.class, "updateReplySettings", "updateReplySettings()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ChatsPreferencesActivity.access$updateReplySettings((ChatsPreferencesActivity) this.receiver);
    }
}
