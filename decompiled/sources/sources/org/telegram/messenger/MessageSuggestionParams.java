package org.telegram.messenger;

import org.telegram.messenger.utils.tlutils.AmountUtils$Amount;
import org.telegram.messenger.utils.tlutils.AmountUtils$Currency;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public class MessageSuggestionParams {
    public final AmountUtils$Amount amount;
    public final long time;

    private MessageSuggestionParams(AmountUtils$Amount r1, long r2) {
        this.amount = r1;
        this.time = r2;
    }

    public static MessageSuggestionParams empty() {
        return new MessageSuggestionParams(AmountUtils$Amount.fromDecimal(0, AmountUtils$Currency.STARS), 0);
    }

    public static MessageSuggestionParams of(AmountUtils$Amount r1, long r2) {
        return new MessageSuggestionParams(r1, r2);
    }

    public static MessageSuggestionParams of(TLRPC.SuggestedPost r4) {
        if (r4 != null) goto L6;
        return empty();
    L6:
        return new MessageSuggestionParams(AmountUtils$Amount.of(r4.price), r4.schedule_date);
    }

    public static MessageSuggestionParams of(TLRPC.TL_messageActionSuggestedPostApproval r3) {
        return of(AmountUtils$Amount.of(r3.price), r3.schedule_date);
    }

    public boolean isEmpty() {
        AmountUtils$Amount r0 = this.amount;
        if (r0 == null) goto L7;
        if (r0.isZero() == true) goto L7;
        return false;
    L7:
        if (this.time > 0) goto L12;
        return true;
    L12:
        return false;
    }

    public TLRPC.SuggestedPost toTl() {
        TLRPC.SuggestedPost r0 = new TLRPC.SuggestedPost();
        AmountUtils$Amount r1 = this.amount;
        if (r1 != null) goto L5;
    L7:
        long r12 = this.time;
        if (r12 <= 0) goto L10;
        r0.schedule_date = (int) r12;
        r0.flags |= 1;
    L10:
        return r0;
    L5:
        if (r1.isZero() == true) goto L7;
        r0.price = this.amount.toTl();
        goto L7
    }
}
