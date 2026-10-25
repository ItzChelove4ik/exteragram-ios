package org.telegram.ui;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.HideViewAfterAnimation;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes6.dex */
public abstract class MessageAuthorView extends FrameLayout {
    int currentAccount;
    FlickerLoadingView flickerLoadingView;
    boolean ignoreLayout;
    boolean isVoice;
    LinkSpanDrawable.LinksTextView titleView;
    public TLRPC.User user;

    /* renamed from: $r8$lambda$-qG5qOq-9XJyOy4SMZTBirDjlac, reason: not valid java name */
    public static /* synthetic */ void m16105$r8$lambda$qG5qOq9XJyOy4SMZTBirDjlac(MessageAuthorView r0, TLObject r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$983fjVw_lADBGmuEQK1SqTn-zfU, reason: not valid java name */
    public static /* synthetic */ void m16106$r8$lambda$983fjVw_lADBGmuEQK1SqTnzfU(MessageAuthorView r0, long r1) {
        r0.lambda$updateView$2(r1);
    }

    /* renamed from: $r8$lambda$i-kcB4ULHe_uYPvPNph2QN6kvME, reason: not valid java name */
    public static /* synthetic */ void m16107$r8$lambda$ikcB4ULHe_uYPvPNph2QN6kvME(MessageAuthorView r0, int r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public MessageAuthorView(Context r10, int r11, MessageObject r12, TLRPC.Chat r13) {
        super(r10);
        this.user = null;
        this.currentAccount = r11;
        if (r12.isRoundVideo() == false) goto L5;
    L8:
        boolean r132 = true;
    L9:
        this.isVoice = r132;
        FlickerLoadingView r133 = new FlickerLoadingView(r10);
        this.flickerLoadingView = r133;
        r133.setColors(Theme.key_actionBarDefaultSubmenuBackground, Theme.key_listSelector, -1);
        this.flickerLoadingView.setViewType(13);
        this.flickerLoadingView.setIsSingleCell(false);
        addView(this.flickerLoadingView, LayoutHelper.createFrame(-2, -1.0f));
        LinkSpanDrawable.LinksTextView r134 = new LinkSpanDrawable.LinksTextView(r10);
        this.titleView = r134;
        r134.setTextSize(1, 14.0f);
        this.titleView.setGravity(19);
        this.titleView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        this.titleView.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn));
        this.titleView.setEllipsize(TextUtils.TruncateAt.END);
        this.titleView.setSingleLine();
        this.titleView.setLines(1);
        this.titleView.setMaxLines(1);
        addView(this.titleView, LayoutHelper.createFrame(-1, -2.0f, 19, 12.0f, 0.0f, 12.0f, 0.0f));
        TLRPC.TL_channels_getMessageAuthor r102 = new TLRPC.TL_channels_getMessageAuthor();
        r102.channel = MessagesController.getInstance(r11).getInputChannel(-r12.getDialogId());
        r102.id = r12.getId();
        this.titleView.setAlpha(0.0f);
        ConnectionsManager.getInstance(r11).sendRequest(r102, new MessageAuthorView$$ExternalSyntheticLambda0(this, r11));
        setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector), 6, 0));
        setEnabled(false);
        return;
    L5:
        if (r12.isVoice() == true) goto L8;
        r132 = false;
        goto L9
    }

    private /* synthetic */ void lambda$new$0(TLObject r2, int r3) {
        if ((r2 instanceof TLRPC.User) == false) goto L5;
        this.user = (TLRPC.User) r2;
        MessagesController.getInstance(r3).putUser(this.user, false);
    L5:
        updateView();
    }

    private /* synthetic */ void lambda$new$1(int r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MessageAuthorView$$ExternalSyntheticLambda1(this, r2, r1));
    }

    private /* synthetic */ void lambda$updateView$2(long r1) {
        openUser(r1);
    }

    private void updateView() {
        if (this.user == null) goto L5;
        boolean r0 = true;
    L6:
        setEnabled(r0);
        TLRPC.User r02 = this.user;
        if (r02 == null) goto L9;
        long r1 = r02.id;
        this.titleView.setText(AndroidUtilities.premiumText(LocaleController.formatString(R.string.MessageAuthorSentBy, new Object[]{UserObject.getUserName(r02)}), new MessageAuthorView$$ExternalSyntheticLambda2(this, r1)));
    L9:
        this.titleView.animate().alpha(1.0f).setDuration(220).start();
        this.flickerLoadingView.animate().alpha(0.0f).setDuration(220).setListener(new HideViewAfterAnimation(this.flickerLoadingView)).start();
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        View r62 = (View) getParent();
        if (r62 != null) goto L5;
    L7:
        int r63 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(36.0f), TLObject.FLAG_30);
        boolean r0 = true;
        this.ignoreLayout = true;
        if (this.flickerLoadingView.getVisibility() == 0) goto L11;
        r0 = false;
    L11:
        this.titleView.setVisibility(8);
        if (r0 == false) goto L14;
        this.flickerLoadingView.setVisibility(8);
    L14:
        super.onMeasure(r5, r63);
        if (r0 == false) goto L17;
        this.flickerLoadingView.getLayoutParams().width = getMeasuredWidth();
        this.flickerLoadingView.setVisibility(0);
    L17:
        this.titleView.setVisibility(0);
        this.titleView.getLayoutParams().width = getMeasuredWidth() - AndroidUtilities.dp(24.0f);
        this.ignoreLayout = false;
        super.onMeasure(r5, r63);
        return;
    L5:
        if (r62.getWidth() <= 0) goto L7;
        r5 = View.MeasureSpec.makeMeasureSpec(r62.getWidth(), TLObject.FLAG_30);
        goto L7
    }

    public abstract void openUser(long r1);

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }
}
