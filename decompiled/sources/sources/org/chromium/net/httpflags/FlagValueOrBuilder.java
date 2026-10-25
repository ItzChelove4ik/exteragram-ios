package org.chromium.net.httpflags;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;
import org.chromium.net.httpflags.FlagValue;

/* loaded from: classes5.dex */
public interface FlagValueOrBuilder extends MessageLiteOrBuilder {
    FlagValue.ConstrainedValue getConstrainedValues(int r1);

    int getConstrainedValuesCount();

    List<FlagValue.ConstrainedValue> getConstrainedValuesList();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ MessageLite getDefaultInstanceForType();

    @Override // com.google.protobuf.MessageLiteOrBuilder
    /* synthetic */ boolean isInitialized();
}
