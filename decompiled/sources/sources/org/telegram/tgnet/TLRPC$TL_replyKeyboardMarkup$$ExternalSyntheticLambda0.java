package org.telegram.tgnet;

import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_keyboard;

/* loaded from: classes.dex */
public final /* synthetic */ class TLRPC$TL_replyKeyboardMarkup$$ExternalSyntheticLambda0 implements Vector.TLDeserializer {
    public /* synthetic */ TLRPC$TL_replyKeyboardMarkup$$ExternalSyntheticLambda0() {
    }

    @Override // org.telegram.tgnet.Vector.TLDeserializer
    public final TLObject deserialize(InputSerializedData r1, int r2, boolean r3) {
        return TL_keyboard.KeyboardButtonRow.TLdeserialize(r1, r2, r3);
    }
}
