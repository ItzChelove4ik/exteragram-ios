package org.telegram.ui.Components;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.BadgesController;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Premium.PremiumGradient;

/* loaded from: classes7.dex */
public class StatusBadgeComponent {
    private final AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable statusDrawable;
    private Drawable verifiedDrawable;

    public StatusBadgeComponent(View r2) {
        this(r2, 18);
    }

    public StatusBadgeComponent(View r2, int r3) {
        this.statusDrawable = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r2, AndroidUtilities.dp(r3));
    }

    public Drawable getDrawable() {
        return this.statusDrawable;
    }

    public void onAttachedToWindow() {
        this.statusDrawable.attach();
    }

    public void onDetachedFromWindow() {
        this.statusDrawable.detach();
    }

    public Drawable updateDrawable(TLObject r3, int r4, boolean r5) {
        if ((r3 instanceof TLRPC.User) == false) goto L7;
        return updateDrawable((TLRPC.User) r3, null, r4, r5);
    L7:
        if ((r3 instanceof TLRPC.Chat) == false) goto L11;
        return updateDrawable(null, (TLRPC.Chat) r3, r4, r5);
    L11:
        return updateDrawable(null, null, r4, r5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Drawable updateDrawable(TLRPC.User r7, TLRPC.Chat r8, int r9, boolean r10) {
        BadgesController r0 = BadgesController.INSTANCE;
        if (r7 != null) goto L5;
        TLRPC.User r1 = r8;
    L6:
        BadgeDTO r02 = r0.getBadge(r1);
        if (r8 == 0) goto L15;
        if (r8.verified == false) goto L15;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r72 = this.statusDrawable;
        Drawable r82 = this.verifiedDrawable;
        if (r82 != null) goto L13;
        r82 = new CombinedDrawable(Theme.dialogs_verifiedDrawable, Theme.dialogs_verifiedCheckDrawable);
    L13:
        this.verifiedDrawable = r82;
        r72.set(r82, r10);
        this.statusDrawable.setColor(null);
    L38:
        return this.statusDrawable;
    L15:
        if (r8 != 0) goto L17;
    L19:
        if (r7 != null) goto L21;
    L26:
        if (r7 != null) goto L28;
    L30:
        if (r02 == null) goto L32;
        this.statusDrawable.set(r02.getDocumentId(), r10);
        this.statusDrawable.setColor(Integer.valueOf(r9));
        goto L38
    L32:
        if (r7 != null) goto L34;
    L36:
        this.statusDrawable.set(null, r10);
        this.statusDrawable.setColor(null);
        goto L38
    L34:
        if (r7.premium == false) goto L36;
        this.statusDrawable.set(PremiumGradient.getInstance().premiumStarDrawableMini, r10);
        this.statusDrawable.setColor(Integer.valueOf(r9));
        goto L38
    L28:
        if (DialogObject.getEmojiStatusDocumentId(r7.emoji_status) == 0) goto L30;
        this.statusDrawable.set(DialogObject.getEmojiStatusDocumentId(r7.emoji_status), r10);
        this.statusDrawable.setColor(Integer.valueOf(r9));
        goto L38
    L21:
        if (r7.verified == false) goto L26;
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r73 = this.statusDrawable;
        Drawable r83 = this.verifiedDrawable;
        if (r83 != null) goto L25;
        r83 = new CombinedDrawable(Theme.dialogs_verifiedDrawable, Theme.dialogs_verifiedCheckDrawable);
    L25:
        this.verifiedDrawable = r83;
        r73.set(r83, r10);
        this.statusDrawable.setColor(null);
        goto L38
    L17:
        if (DialogObject.getEmojiStatusDocumentId(r8.emoji_status) == 0) goto L19;
        this.statusDrawable.set(DialogObject.getEmojiStatusDocumentId(r8.emoji_status), r10);
        this.statusDrawable.setColor(Integer.valueOf(r9));
        goto L38
    L5:
        r1 = r7;
        goto L6
    }
}
