package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.TextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
public class SelectPeerView extends FrameLayout {
    private final AvatarDrawable avatarDrawable;
    private final int currentAccount;
    private final BackupImageView imageView;
    private ViewPropertyAnimator showAnimator;
    private final TextView subtitleView;
    private final TextView titleView;

    public static /* synthetic */ void $r8$lambda$5vusYV3hEp_ENTppW9S6QjnmG4A(SelectPeerView r0, boolean r1) {
        r0.lambda$setShowing$0(r1);
    }

    public SelectPeerView(Context r11, int r12) {
        super(r11);
        this.currentAccount = r12;
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r122 = new BackupImageView(r11);
        this.imageView = r122;
        r122.setRoundRadius(AndroidUtilities.dp(15.0f));
        addView(r122, LayoutHelper.createFrame(30, 30.0f, 19, 14.0f, 0.0f, 0.0f, 0.0f));
        TextView r123 = new TextView(r11);
        this.titleView = r123;
        r123.setTextSize(1, 14.0f);
        r123.setTextColor(-1);
        r123.setTypeface(AndroidUtilities.bold());
        r123.setSingleLine();
        r123.setLines(1);
        r123.setEllipsize(TextUtils.TruncateAt.END);
        addView(r123, LayoutHelper.createFrame(-1, -2.0f, 51, 53.0f, 11.33f, 12.0f, 0.0f));
        TextView r124 = new TextView(r11);
        this.subtitleView = r124;
        r124.setTextSize(1, 12.0f);
        r124.setTextColor(Theme.multAlpha(-1, 0.85f));
        addView(r124, LayoutHelper.createFrame(-1, -2.0f, 51, 53.0f, 29.33f, 12.0f, 0.0f));
        r124.setText(AndroidUtilities.replaceArrows(LocaleController.getString(R.string.LiveStoryPeerChange), false, AndroidUtilities.dp(2.6666667f), AndroidUtilities.dp(0.33f), 1.0f));
        set(null);
    }

    private /* synthetic */ void lambda$setShowing$0(boolean r1) {
        if (r1 == true) goto L5;
        setVisibility(8);
        return;
    }

    public void set(TLRPC.InputPeer r5) {
        if (r5 != null) goto L4;
        long r0 = UserConfig.getInstance(this.currentAccount).getClientUserId();
    L5:
        int r2 = this.currentAccount;
        if (r0 < 0) goto L9;
        TLRPC.User r52 = MessagesController.getInstance(r2).getUser(Long.valueOf(r0));
        this.avatarDrawable.setInfo(r52);
        this.imageView.setForUserOrChat(r52, this.avatarDrawable);
        this.titleView.setText(UserObject.getUserName(r52));
        return;
    L9:
        TLRPC.Chat r53 = MessagesController.getInstance(r2).getChat(Long.valueOf(-r0));
        this.avatarDrawable.setInfo(r53);
        this.imageView.setForUserOrChat(r53, this.avatarDrawable);
        TextView r4 = this.titleView;
        if (r53 != null) goto L12;
        String r54 = _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        r4.setText(r54);
        return;
    L12:
        r54 = r53.title;
        goto L13
    L4:
        r0 = DialogObject.getPeerDialogId(r5);
        goto L5
    }

    public void setShowing(boolean r4, boolean r5) {
        ViewPropertyAnimator r0 = this.showAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.showAnimator = null;
    L5:
        float r02 = 0.0f;
        int r2 = 0;
        if (r5 == false) goto L12;
        setVisibility(0);
        ViewPropertyAnimator r52 = animate();
        if (r4 == false) goto L10;
        r02 = 1.0f;
    L10:
        ViewPropertyAnimator r42 = r52.alpha(r02).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).withEndAction(new SelectPeerView$$ExternalSyntheticLambda0(this, r4)).setDuration(320);
        this.showAnimator = r42;
        r42.start();
        return;
    L12:
        if (r4 == true) goto L15;
        r2 = 8;
    L15:
        setVisibility(r2);
        if (r4 == false) goto L18;
        r02 = 1.0f;
    L18:
        setAlpha(r02);
    }
}
