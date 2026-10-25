package org.chromium.net.httpflags;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.Map;

/* loaded from: classes5.dex */
public interface FlagsOrBuilder extends MessageLiteOrBuilder {
    boolean containsFlags(String r1);

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ MessageLite getDefaultInstanceForType();

    @Deprecated
    Map<String, FlagValue> getFlags();

    int getFlagsCount();

    Map<String, FlagValue> getFlagsMap();

    FlagValue getFlagsOrDefault(String r1, FlagValue r2);

    FlagValue getFlagsOrThrow(String r1);

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ boolean isInitialized();
}
