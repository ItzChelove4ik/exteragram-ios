package org.telegram.ui.Business;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.cast.MediaError;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.AlertDialogDecor;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.URLSpanCopyToClipboard;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalFragment;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes6.dex */
public class BusinessLinksActivity extends UniversalFragment implements NotificationCenter.NotificationCenterDelegate {
    private static AlertDialog currentDialog;

    /* renamed from: org.telegram.ui.Business.BusinessLinksActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextBoldCursor {
        AnimatedTextView.AnimatedTextDrawable limit;
        AnimatedColor limitColor;
        private int limitCount;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass1(Context r8, Theme.ResourcesProvider r9) {
            this.val$resourcesProvider = r9;
            super(r8);
            this.limitColor = new AnimatedColor(this);
            AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.limit = r0;
            r0.setAnimationProperties(0.2f, 0, 160, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.limit.setTextSize(AndroidUtilities.dp(15.33f));
            this.limit.setCallback(this);
            this.limit.setGravity(5);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            AnimatedTextView.AnimatedTextDrawable r0 = this.limit;
            AnimatedColor r1 = this.limitColor;
            if (this.limitCount >= 0) goto L5;
            int r2 = Theme.key_text_RedRegular;
        L6:
            r0.setTextColor(r1.set(Theme.getColor(r2, this.val$resourcesProvider)));
            this.limit.setBounds(getScrollX(), 0, getScrollX() + getWidth(), getHeight());
            this.limit.draw(r6);
            return;
        L5:
            r2 = Theme.key_dialogSearchHint;
            goto L6
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(36.0f), TLObject.FLAG_30));
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            super.onTextChanged(r1, r2, r3, r4);
            if (this.limit == null) goto L10;
            this.limitCount = 32 - r1.length();
            this.limit.cancelAnimation();
            AnimatedTextView.AnimatedTextDrawable r12 = this.limit;
            int r22 = this.limitCount;
            String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r22 > 4) goto L8;
            r42 = _UrlKt.FRAGMENT_ENCODE_SET + this.limitCount;
        L8:
            r12.setText(r42);
            return;
        }

        @Override // android.widget.TextView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.limit) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    @SuppressLint({"ViewConstructor"})
    public static class BusinessLinkView extends FrameLayout {
        private TL_account.TL_businessChatLink businessLink;
        private final SimpleTextView clicksCountTextView;
        private final ImageView imageView;
        private final SpoilersTextView messagePreviewTextView;
        private boolean needDivider;
        private final Theme.ResourcesProvider resourcesProvider;
        private final SimpleTextView titleTextView;

        public static /* synthetic */ void $r8$lambda$mjyhnEHKUI1WCdXyxFiVy3231iQ(BusinessLinkView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public BusinessLinkView(Context r14, Theme.ResourcesProvider r15) {
            super(r14);
            this.resourcesProvider = r15;
            setWillNotDraw(false);
            ImageView r1 = new ImageView(r14);
            this.imageView = r1;
            r1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            r1.setImageResource(R.drawable.msg_limit_links);
            r1.setPadding(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f));
            r1.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
            r1.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(36.0f), Theme.getColor(Theme.key_featuredStickers_addButton)));
            r1.setOnClickListener(new BusinessLinksActivity$BusinessLinkView$$ExternalSyntheticLambda0(this));
            addView(r1, LayoutHelper.createFrameRelatively(36.0f, 36.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 14.0f, 0.0f, 14.0f, 0.0f));
            SimpleTextView r12 = new SimpleTextView(r14);
            this.titleTextView = r12;
            r12.setTextSize(15);
            r12.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            int r3 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r2 = 5;
        L6:
            r12.setGravity(r2);
            addView(r12, LayoutHelper.createFrameRelatively(-1.0f, 20.0f, 55, 64.0f, 10.0f, 14.0f, 0.0f));
            SimpleTextView r13 = new SimpleTextView(r14);
            this.clicksCountTextView = r13;
            r13.setTextSize(14);
            int r22 = Theme.key_windowBackgroundWhiteGrayText2;
            r13.setTextColor(Theme.getColor(r22));
            if (LocaleController.isRTL == false) goto L9;
            int r5 = 3;
        L10:
            r13.setGravity(r5);
            addView(r13, LayoutHelper.createFrameRelatively(-1.0f, 18.0f, 55, 64.0f, 10.66f, 14.0f, 0.0f));
            SpoilersTextView r16 = new SpoilersTextView(r14);
            this.messagePreviewTextView = r16;
            r16.setTextSize(1, 13.0f);
            r16.setMaxLines(1);
            r16.setEllipsize(TextUtils.TruncateAt.END);
            r16.setTextColor(Theme.getColor(r22, r15));
            if (LocaleController.isRTL == false) goto L13;
            r3 = 5;
        L13:
            r16.setGravity(r3);
            r16.allowClickSpoilers = false;
            r16.setUseAlphaForEmoji(false);
            NotificationCenter.listenEmojiLoading(r16);
            addView(r16, LayoutHelper.createFrameRelatively(-1.0f, 20.0f, 87, 64.0f, 0.0f, 14.0f, 6.0f));
            return;
        L9:
            r5 = 5;
            goto L10
        L5:
            r2 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            TL_account.TL_businessChatLink r0 = this.businessLink;
            if (r0 == null) goto L6;
            AndroidUtilities.addToClipboard(r0.link);
            BulletinFactory.of(LaunchActivity.getLastFragment()).createCopyLinkBulletin().show();
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            if (this.needDivider == false) goto L17;
            Paint r0 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
            if (r0 != null) goto L7;
            r0 = Theme.dividerPaint;
        L7:
            Paint r6 = r0;
            float r1 = 64.0f;
            if (LocaleController.isRTL == false) goto L10;
            float r02 = 0.0f;
        L11:
            float r03 = AndroidUtilities.dp(r02);
            float r3 = getMeasuredHeight() - 1;
            int r4 = getWidth();
            if (LocaleController.isRTL == true) goto L15;
            r1 = 0.0f;
        L15:
            r8.drawRect(r03, r3, r4 - AndroidUtilities.dp(r1), getMeasuredHeight(), r6);
            return;
        L10:
            r02 = 64.0f;
            goto L11
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            boolean r12 = LocaleController.isRTL;
            SimpleTextView r22 = this.titleTextView;
            if (r12 == false) goto L6;
            r22.setPadding(this.clicksCountTextView.getTextWidth(), 0, 0, 0);
            return;
        L6:
            r22.setPadding(0, 0, this.clicksCountTextView.getTextWidth(), 0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        }

        public void set(BusinessLinkWrapper r3, boolean r4) {
            this.needDivider = r4;
            TL_account.TL_businessChatLink r32 = r3.link;
            this.businessLink = r32;
            boolean r33 = TextUtils.isEmpty(r32.title);
            SimpleTextView r42 = this.titleTextView;
            if (r33 == true) goto L5;
            r42.setText(this.businessLink.title);
        L6:
            SpannableStringBuilder r34 = new SpannableStringBuilder(this.businessLink.message);
            TL_account.TL_businessChatLink r43 = this.businessLink;
            MediaDataController.addTextStyleRuns(r43.entities, r43.message, r34);
            CharSequence r35 = Emoji.replaceEmoji(r34, this.messagePreviewTextView.getPaint().getFontMetricsInt(), false);
            MessageObject.replaceAnimatedEmoji(r35, this.businessLink.entities, this.messagePreviewTextView.getPaint().getFontMetricsInt());
            this.messagePreviewTextView.setText(r35);
            int r36 = this.businessLink.views;
            SimpleTextView r44 = this.clicksCountTextView;
            if (r36 != 0) goto L9;
            r44.setText(LocaleController.formatString(R.string.NoClicks, new Object[0]));
        L10:
            this.clicksCountTextView.requestLayout();
            invalidate();
            return;
        L9:
            r44.setText(LocaleController.formatPluralString("Clicks", r36, new Object[0]));
            goto L10
        L5:
            r42.setText(BusinessLinksController.stripHttps(this.businessLink.link));
            goto L6
        }
    }

    /* loaded from: classes3.dex */
    public static class BusinessLinkWrapper {
        TL_account.TL_businessChatLink link;

        public BusinessLinkWrapper(TL_account.TL_businessChatLink r1) {
            this.link = r1;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L21:
            return false;
        L8:
            if (getClass() != r7.getClass()) goto L21;
            BusinessLinkWrapper r72 = (BusinessLinkWrapper) r7;
            TL_account.TL_businessChatLink r2 = this.link;
            int r3 = r2.views;
            TL_account.TL_businessChatLink r4 = r72.link;
            if (r3 != r4.views) goto L21;
            if (TextUtils.equals(r2.link, r4.link) == false) goto L21;
            if (TextUtils.equals(this.link.title, r72.link.title) == false) goto L21;
            if (TextUtils.equals(this.link.message, r72.link.message) == false) goto L21;
            if (MediaDataController.entitiesEqual(this.link.entities, r72.link.entities) == false) goto L21;
            return true;
        }
    }

    /* renamed from: $r8$lambda$-s76pfG_e-3TpcmUNSe5AOG-wUs, reason: not valid java name */
    public static /* synthetic */ void m6328$r8$lambda$s76pfG_e3TpcmUNSe5AOGwUs(EditTextBoldCursor r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$22WngckNHnN_AZ8f8CZXwLcfKho(TL_account.TL_businessChatLink r0) {
        AndroidUtilities.addToClipboard(r0.link);
        BulletinFactory.of(LaunchActivity.getLastFragment()).createCopyLinkBulletin().show();
    }

    public static /* synthetic */ void $r8$lambda$43vbUSKuOYhm3fuj6s_4BSjIXOk(BusinessLinksActivity r0, TL_account.TL_businessChatLink r1) {
        r0.lambda$onLongClick$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$98ugssKQCmWhkA4RCaJsBF3QDQw(BusinessLinksActivity r0, TL_account.TL_businessChatLink r1, AlertDialog r2, int r3) {
        r0.lambda$onLongClick$11(r1, r2, r3);
    }

    public static /* synthetic */ boolean $r8$lambda$DtBQAB1IVKEeN5sJZ6_I4zgsmCc(EditTextBoldCursor r2, int r3, TL_account.TL_businessChatLink r4, AlertDialog[] r5, View r6, TextView r7, int r8, KeyEvent r9) {
        if (r8 != 6) goto L17;
        String r72 = r2.getText().toString();
        if (r72.length() <= 32) goto L8;
        AndroidUtilities.shakeView(r2);
        return true;
    L8:
        BusinessLinksController.getInstance(r3).editLinkTitle(r4.link, r72);
        AlertDialog r22 = r5[0];
        if (r22 == null) goto L12;
        r22.dismiss();
    L12:
        if (r5[0] != currentDialog) goto L14;
        currentDialog = null;
    L14:
        if (r6 == null) goto L16;
        r6.requestFocus();
    L16:
        return true;
    L17:
        return false;
    }

    public static /* synthetic */ void $r8$lambda$JbvWftK4TSlZUFN3lwTgZOzHk8g(View r0, EditTextBoldCursor r1, Runnable r2) {
        if (r0 == null) goto L4;
        r0.requestFocus();
    L4:
        AndroidUtilities.hideKeyboard(r1);
        AndroidUtilities.runOnUIThread(r2, 80);
    }

    public static /* synthetic */ void $r8$lambda$UcDw_lm1x7o2YELRhLAwjiy5CUg(View r0, EditTextBoldCursor r1, DialogInterface r2) {
        if (r0 == null) goto L4;
        r0.clearFocus();
    L4:
        r1.requestFocus();
        AndroidUtilities.showKeyboard(r1);
    }

    public static /* synthetic */ void $r8$lambda$cxMI8a8s6tOBqxsSlMOAHU39TGw(EditTextBoldCursor r2, int r3, TL_account.TL_businessChatLink r4, AlertDialog r5, int r6) {
        String r62 = r2.getText().toString();
        if (r62.length() <= 32) goto L6;
        AndroidUtilities.shakeView(r2);
        return;
    L6:
        BusinessLinksController.getInstance(r3).editLinkTitle(r4.link, r62);
        r5.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$eXSlxsr6uwgQBylJn33LWb3y9IU(BusinessLinksActivity r0, TL_account.TL_businessChatLink r1) {
        r0.lambda$onLongClick$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$ogzs8p3rkQ6KL6sX0TTlfCl8vOQ(BusinessLinksActivity r0, TL_account.TL_businessChatLink r1) {
        r0.lambda$onLongClick$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$t_s9S_xqs15twGJzXMf4uaFDLhs(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$uViQvlykjsAe5Vxc8gEsQJWvtWI(EditTextBoldCursor r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$vhM1hkg1IjU1VS1KeY8WYqWMkdE(View r0, DialogInterface r1) {
        currentDialog = null;
        if (r0 == null) goto L6;
        r0.requestFocus();
        return;
    }

    public BusinessLinksActivity() {
    }

    public static boolean closeRenameAlert(boolean r1) {
        AlertDialog r0 = currentDialog;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isShowing() == false) goto L13;
        if (r1 == false) goto L12;
        currentDialog.dismiss();
        return true;
    L12:
        return true;
    L13:
        return false;
    }

    private static int getPrivacyType(ArrayList<TLRPC.PrivacyRule> r9) {
        char r3 = 65535;
        int r2 = 0;
        boolean r4 = false;
    L4:
        if (r2 >= r9.size()) goto L29;
        TLRPC.PrivacyRule r5 = r9.get(r2);
        if ((r5 instanceof TLRPC.TL_privacyValueAllowChatParticipants) == true) goto L28;
        if ((r5 instanceof TLRPC.TL_privacyValueDisallowChatParticipants) == false) goto L12;
    L10:
        r4 = true;
        goto L28
    L12:
        if ((r5 instanceof TLRPC.TL_privacyValueAllowUsers) == true) goto L28;
        if ((r5 instanceof TLRPC.TL_privacyValueDisallowUsers) == true) goto L10;
        if ((r5 instanceof TLRPC.TL_privacyValueAllowPremium) == true) goto L28;
        if (r3 != 65535) goto L28;
        if ((r5 instanceof TLRPC.TL_privacyValueAllowAll) == false) goto L25;
        r3 = 0;
        goto L28
    L25:
        if ((r5 instanceof TLRPC.TL_privacyValueDisallowAll) == false) goto L27;
        r3 = 1;
        goto L28
    L27:
        r3 = 2;
    L28:
        r2 = r2 + 1;
        goto L4
    L29:
        if (r3 == 0) goto L36;
        if (r3 != 65535) goto L33;
        if (r4 == true) goto L36;
    L33:
        if (r3 != 2) goto L35;
        return 2;
    L35:
        return 1;
    L36:
        return 0;
    }

    private /* synthetic */ void lambda$onLongClick$10(TL_account.TL_businessChatLink r4) {
        openRenameAlert(getContext(), this.currentAccount, r4, this.resourceProvider, false);
    }

    private /* synthetic */ void lambda$onLongClick$11(TL_account.TL_businessChatLink r1, AlertDialog r2, int r3) {
        BusinessLinksController.getInstance(this.currentAccount).deleteLinkUndoable(this, r1.link);
    }

    private /* synthetic */ void lambda$onLongClick$12(TL_account.TL_businessChatLink r4) {
        AlertDialog r42 = new AlertDialog.Builder(getContext(), getResourceProvider()).setTitle(LocaleController.getString(R.string.BusinessLinksDeleteTitle)).setMessage(LocaleController.getString(R.string.BusinessLinksDeleteMessage)).setPositiveButton(LocaleController.getString(R.string.Remove), new BusinessLinksActivity$$ExternalSyntheticLambda12(this, r4)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        showDialog(r42);
        TextView r43 = (TextView) r42.getButton(-1);
        if (r43 == null) goto L6;
        r43.setTextColor(getThemedColor(Theme.key_text_RedBold));
        return;
    }

    private /* synthetic */ void lambda$onLongClick$9(TL_account.TL_businessChatLink r4) {
        Intent r0 = new Intent(getContext(), LaunchActivity.class);
        r0.setAction("android.intent.action.SEND");
        r0.setType("text/plain");
        r0.putExtra("android.intent.extra.TEXT", r4.link);
        startActivityForResult(r0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
    }

    public static void openRenameAlert(Context r19, int r20, TL_account.TL_businessChatLink r21, Theme.ResourcesProvider r22, boolean r23) {
        BaseFragment r2 = LaunchActivity.getLastFragment();
        Activity r4 = AndroidUtilities.findActivity(r19);
        if (r4 == null) goto L5;
        View r42 = r4.getCurrentFocus();
    L7:
        if (r2 != null) goto L9;
    L15:
        boolean r8 = false;
    L14:
        View r24 = r42;
        AlertDialog[] r43 = new AlertDialog[1];
        if (r8 == false) goto L19;
        AlertDialog.Builder r9 = new AlertDialogDecor.Builder(r19, r22);
    L20:
        r9.setTitle(LocaleController.getString(R.string.BusinessLinksRenameTitle));
        AnonymousClass1 r10 = new AnonymousClass1(r19, r22);
        MediaDataController.getInstance(r20).fetchNewEmojiKeywords(AndroidUtilities.getCurrentKeyboardLanguage(), true);
        r10.setInputType(49153);
        r10.setTextSize(1, 18.0f);
        r10.setText(r21.title);
        int r11 = Theme.key_dialogTextBlack;
        r10.setTextColor(Theme.getColor(r11, r22));
        r10.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, r22));
        r10.setCursorColor(Theme.getColor(Theme.key_chat_messagePanelCursor));
        r10.setHintText(LocaleController.getString(R.string.BusinessLinksNamePlaceholder));
        r10.setSingleLine(true);
        r10.setFocusable(true);
        r10.setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField, r22), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, r22), Theme.getColor(Theme.key_text_RedRegular, r22));
        r10.setImeOptions(6);
        r10.setBackgroundDrawable(null);
        r10.setPadding(0, 0, AndroidUtilities.dp(42.0f), 0);
        LinearLayout r5 = new LinearLayout(r19);
        r5.setOrientation(1);
        TextView r12 = new TextView(r19);
        r12.setTextColor(Theme.getColor(r11, r22));
        r12.setTextSize(1, 16.0f);
        r12.setText(LocaleController.getString(R.string.BusinessLinksRenameMessage));
        r5.addView(r12, LayoutHelper.createLinear(-1, -2, 24.0f, 5.0f, 24.0f, 12.0f));
        r5.addView(r10, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 10.0f));
        r9.setView(r5);
        r9.setWidth(AndroidUtilities.dp(292.0f));
        r10.setOnEditorActionListener(new BusinessLinksActivity$$ExternalSyntheticLambda4(r10, r20, r21, r43, r24));
        r9.setPositiveButton(LocaleController.getString(R.string.Done), new BusinessLinksActivity$$ExternalSyntheticLambda5(r10, r20, r21));
        r9.setNegativeButton(LocaleController.getString(R.string.Cancel), new BusinessLinksActivity$$ExternalSyntheticLambda6());
        if (r8 == false) goto L23;
        AlertDialog r0 = r9.create();
        currentDialog = r0;
        r43[0] = r0;
        r0.setOnDismissListener(new BusinessLinksActivity$$ExternalSyntheticLambda7(r24));
        currentDialog.setOnShowListener(new BusinessLinksActivity$$ExternalSyntheticLambda8(r10));
        currentDialog.showDelayed(250);
    L24:
        r43[0].setDismissDialogByButtons(false);
        r10.setSelection(r10.getText().length());
        return;
    L23:
        r9.overrideDismissListener(new BusinessLinksActivity$$ExternalSyntheticLambda9(r24, r10));
        AlertDialog r02 = r9.create();
        r43[0] = r02;
        r02.setOnDismissListener(new BusinessLinksActivity$$ExternalSyntheticLambda10(r10));
        r43[0].setOnShowListener(new BusinessLinksActivity$$ExternalSyntheticLambda11(r24, r10));
        r43[0].show();
        goto L24
    L19:
        r9 = new AlertDialog.Builder(r19, r22);
        goto L20
    L9:
        if ((r2.getFragmentView() instanceof SizeNotifierFrameLayout) == false) goto L15;
        if (((SizeNotifierFrameLayout) r2.getFragmentView()).measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L15;
        if (r23 == true) goto L15;
        r8 = true;
        goto L14
    L5:
        r42 = null;
        goto L7
    }

    @Override // org.telegram.ui.Components.UniversalFragment, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r3) {
        super.createView(r3);
        this.listView.setSections();
        this.listView.adapter.setApplyBackground(false);
        this.actionBar.setAdaptiveBackground(this.listView, true);
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.businessLinksUpdated) goto L5;
    L15:
        UniversalRecyclerView r1 = this.listView;
        if (r1 == null) goto L22;
        UniversalAdapter r12 = r1.adapter;
        if (r12 == null) goto L23;
        r12.update(true);
        return;
    L23:
        return;
    L22:
        return;
    L5:
        if (r2 == NotificationCenter.privacyRulesUpdated) goto L15;
        if (r2 != NotificationCenter.businessLinkCreated) goto L12;
        TL_account.TL_businessChatLink r22 = (TL_account.TL_businessChatLink) r4[0];
        Bundle r32 = new Bundle();
        r32.putInt("chatMode", 6);
        r32.putString("business_link", r22.link);
        presentFragment(new ChatActivity(r32));
        return;
    L12:
        if (r2 != NotificationCenter.needDeleteBusinessLink) goto L21;
        TL_account.TL_businessChatLink r23 = (TL_account.TL_businessChatLink) r4[0];
        BusinessLinksController.getInstance(this.currentAccount).deleteLinkUndoable(this, r23.link);
        return;
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void fillItems(ArrayList<UItem> r10, UniversalAdapter r11) {
        r10.add(UItem.asTopView(LocaleController.getString(R.string.BusinessLinks), LocaleController.getString(R.string.BusinessLinksInfo), R.raw.biz_links));
        r11.whiteSectionStart();
        if (BusinessLinksController.getInstance(this.currentAccount).canAddNew() == false) goto L5;
        r10.add(UItem.asButton(1, R.drawable.menu_link_create, LocaleController.getString(R.string.BusinessLinksAdd)).accent());
    L5:
        ArrayList<TL_account.TL_businessChatLink> r0 = BusinessLinksController.getInstance(this.currentAccount).links;
        int r2 = r0.size();
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L8;
        TL_account.TL_businessChatLink r5 = r0.get(r4);
        r4 = r4 + 1;
        r10.add(UItem.asBusinessChatLink(new BusinessLinkWrapper(r5)));
        goto L6
    L8:
        r11.whiteSectionEnd();
        TLRPC.User r112 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        String r02 = MessagesController.getInstance(this.currentAccount).linkPrefix + "/";
        ArrayList r22 = new ArrayList(2);
        String r52 = UserObject.getPublicUsername(r112);
        if (r52 == null) goto L11;
        r22.add(r02.concat(r52));
    L11:
        ArrayList<TLRPC.PrivacyRule> r53 = ContactsController.getInstance(this.currentAccount).getPrivacyRules(6);
        ArrayList<TLRPC.PrivacyRule> r6 = ContactsController.getInstance(this.currentAccount).getPrivacyRules(7);
        if (TextUtils.isEmpty(r112.phone) == true) goto L21;
        if (r53 == null) goto L21;
        if (r6 == null) goto L21;
        if (getPrivacyType(r53) == 1) goto L18;
    L19:
        r22.add(r02 + "+" + r112.phone);
        goto L21
    L18:
        if (getPrivacyType(r6) != 2) goto L19;
    L21:
        if (r22.isEmpty() == false) goto L23;
        return;
    L23:
        if (r22.size() != 2) goto L25;
        String r113 = LocaleController.formatString(R.string.BusinessLinksFooterTwoLinks, new Object[]{r22.get(0), r22.get(1)});
    L26:
        SpannableString r03 = new SpannableString(r113);
        int r1 = r22.size();
    L27:
        if (r3 >= r1) goto L31;
        Object r42 = r22.get(r3);
        r3 = r3 + 1;
        String r43 = (String) r42;
        int r54 = r113.indexOf(r43);
        if (r54 <= (-1)) goto L27;
        r03.setSpan(new URLSpanCopyToClipboard("https://" + r43, this), r54, r43.length() + r54, 33);
        goto L27
    L31:
        r10.add(UItem.asShadow(r03));
        return;
    L25:
        r113 = LocaleController.formatString(R.string.BusinessLinksFooterOneLink, new Object[]{r22.get(0)});
        goto L26
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.BusinessLinks);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        AlertDialog r0 = currentDialog;
        if (r0 == null) goto L11;
        if (r0.isShowing() == false) goto L11;
        if (r2 == false) goto L12;
        currentDialog.dismiss();
        return false;
    L12:
        return false;
    L11:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        if (r1.id != 1) goto L7;
        BusinessLinksController.getInstance(this.currentAccount).createEmptyLink();
        return;
    L7:
        if (r1.viewType != 29) goto L12;
        Object r12 = r1.object;
        if ((r12 instanceof BusinessLinkWrapper) == false) goto L13;
        Bundle r22 = new Bundle();
        r22.putInt("chatMode", 6);
        r22.putString("business_link", ((BusinessLinkWrapper) r12).link.link);
        presentFragment(new ChatActivity(r22));
        return;
    L13:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getNotificationCenter().addObserver(this, NotificationCenter.businessLinksUpdated);
        getNotificationCenter().addObserver(this, NotificationCenter.businessLinkCreated);
        getNotificationCenter().addObserver(this, NotificationCenter.needDeleteBusinessLink);
        getNotificationCenter().addObserver(this, NotificationCenter.privacyRulesUpdated);
        BusinessLinksController.getInstance(this.currentAccount).load(true);
        ContactsController.getInstance(this.currentAccount).loadPrivacySettings();
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getNotificationCenter().removeObserver(this, NotificationCenter.businessLinksUpdated);
        getNotificationCenter().removeObserver(this, NotificationCenter.businessLinkCreated);
        getNotificationCenter().removeObserver(this, NotificationCenter.needDeleteBusinessLink);
        getNotificationCenter().removeObserver(this, NotificationCenter.privacyRulesUpdated);
        Bulletin.hideVisible();
        super.onFragmentDestroy();
    }

    @Override // org.telegram.ui.Components.UniversalFragment
    public boolean onLongClick(UItem r2, View r3, int r4, float r5, float r6) {
        if (r2.viewType != 29) goto L8;
        Object r22 = r2.object;
        if ((r22 instanceof BusinessLinkWrapper) == false) goto L10;
        TL_account.TL_businessChatLink r23 = ((BusinessLinkWrapper) r22).link;
        ItemOptions r42 = ItemOptions.makeOptions(this, r3);
        r42.add(R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new BusinessLinksActivity$$ExternalSyntheticLambda0(r23));
        r42.add(R.drawable.msg_share, LocaleController.getString(R.string.LinkActionShare), new BusinessLinksActivity$$ExternalSyntheticLambda1(this, r23));
        r42.add(R.drawable.msg_edit, LocaleController.getString(R.string.Rename), new BusinessLinksActivity$$ExternalSyntheticLambda2(this, r23));
        r42.add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new BusinessLinksActivity$$ExternalSyntheticLambda3(this, r23));
        r42.setScrimViewBackground(this.listView.getClipBackground(r3));
        r42.show();
        return true;
    L10:
        return false;
    L8:
        return false;
    }
}
