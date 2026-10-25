package com.google.protobuf;

import c.f$$ExternalSyntheticBUOutline1;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
class GeneratedMessageInfoFactory implements MessageInfoFactory {
    private static final GeneratedMessageInfoFactory instance = null;

    static {
        instance = new GeneratedMessageInfoFactory();
    }

    private GeneratedMessageInfoFactory() {
    }

    public static GeneratedMessageInfoFactory getInstance() {
        return instance;
    }

    @Override // com.google.protobuf.MessageInfoFactory
    public boolean isSupported(Class<?> r1) {
        return GeneratedMessageLite.class.isAssignableFrom(r1);
    }

    @Override // com.google.protobuf.MessageInfoFactory
    public MessageInfo messageInfoFor(Class<?> r3) {
        if (GeneratedMessageLite.class.isAssignableFrom(r3) == true) goto L11;
        f$$ExternalSyntheticBUOutline1.m("Unsupported message type: ".concat(r3.getName()));
        return null;
    L11:
        return (MessageInfo) GeneratedMessageLite.getDefaultInstance(r3.asSubclass(GeneratedMessageLite.class)).buildMessageInfo();
    L6:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Unable to get message info for ".concat(r3.getName()), e);
        return null;
    }
}
