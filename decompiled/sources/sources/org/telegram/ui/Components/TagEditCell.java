package org.telegram.ui.Components;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.ReplacementSpan;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.PollEditTextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.GradientClip;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.PreviewView;

/* loaded from: classes7.dex */
public class TagEditCell extends LinearLayout {
    private final AvatarDrawable avatarDrawable;
    private final BackupImageView avatarImageView;
    private final SizeNotifierFrameLayout chatView;
    private final ImageView clearImageView;
    private final int currentAccount;
    private final long dialogId;
    private final PollEditTextCell editTextCell;
    private boolean ignoreEdit;
    private boolean isAdmin;
    private boolean isOwner;
    private final AnimatedTextView limitTextView;
    private final ChatMessageCell messageCell;
    private MessageObject messageObject;
    private Utilities.Callback<String> onRankEdited;
    private final Theme.ResourcesProvider resourcesProvider;
    private float shakeDp;

    /* renamed from: org.telegram.ui.Components.TagEditCell$1, reason: invalid class name */
    public class AnonymousClass1 extends SizeNotifierFrameLayout {
        final /* synthetic */ TagEditCell this$0;

        public AnonymousClass1(TagEditCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public Theme.ResourcesProvider getResourceProvider() {
            return TagEditCell.m13211$$Nest$fgetresourcesProvider(this.this$0);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isActionBarVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isStatusBarVisible() {
            return false;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, r3);
            setMeasuredDimension(View.MeasureSpec.getSize(r2), AndroidUtilities.dp(24.0f) + TagEditCell.m13208$$Nest$fgetmessageCell(this.this$0).getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.Components.TagEditCell$10, reason: invalid class name */
    public class AnonymousClass10 extends ViewOutlineProvider {
        public AnonymousClass10() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight(), AndroidUtilities.dp(16.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.TagEditCell$2, reason: invalid class name */
    public class AnonymousClass2 extends ChatMessageCell {
        final /* synthetic */ TagEditCell this$0;

        public AnonymousClass2(TagEditCell r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public int getParentWidth() {
            if (getMeasuredWidth() == 0) goto L7;
            int r2 = getMeasuredWidth();
            int r0 = AndroidUtilities.dp(24.0f);
        L6:
            return r2 - r0;
        L7:
            r2 = AndroidUtilities.displaySize.x;
            r0 = AndroidUtilities.dp(24.0f);
            goto L6
        }

        @Override // android.view.View
        public boolean isPressed() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.TagEditCell$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ TagEditCell this$0;

        public AnonymousClass3(TagEditCell r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r7) {
            if (TagEditCell.m13204$$Nest$fgetignoreEdit(this.this$0) == true) goto L17;
            String r72 = r7.toString().trim();
            int r0 = r72.length();
            TagEditCell r1 = this.this$0;
            if (r0 <= 16) goto L8;
            TagEditCell.m13207$$Nest$fgetlimitTextView(r1).setText("-" + (r72.length() - 16));
            r72 = r72.substring(0, 16);
        L10:
            if (TagEditCell.m13210$$Nest$fgetonRankEdited(this.this$0) == null) goto L13;
            TagEditCell.m13210$$Nest$fgetonRankEdited(this.this$0).run(r72);
        L13:
            if (TagEditCell.m13209$$Nest$fgetmessageObject(this.this$0) == null) goto L16;
            TagEditCell.m13209$$Nest$fgetmessageObject(this.this$0).forceUpdate = true;
            TagEditCell.m13208$$Nest$fgetmessageCell(this.this$0).setMessageObject(TagEditCell.m13209$$Nest$fgetmessageObject(this.this$0), null, false, false, false);
            return;
        L16:
            return;
        L8:
            TagEditCell.m13207$$Nest$fgetlimitTextView(r1).setText(_UrlKt.FRAGMENT_ENCODE_SET);
            goto L10
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.TagEditCell$4, reason: invalid class name */
    public class AnonymousClass4 implements ChatMessageCell.ChatMessageCellDelegate {
        final /* synthetic */ TagEditCell this$0;
        final /* synthetic */ EditTextBoldCursor val$editText;

        public AnonymousClass4(TagEditCell r1, EditTextBoldCursor r2) {
            this.this$0 = r1;
            this.val$editText = r2;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean canPerformActions() {
            return false;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public String getAdminRank(long r2) {
            String r22 = this.val$editText.getText().toString().trim();
            if (r22.length() <= 16) goto L6;
            r22 = r22.substring(0, 16);
        L6:
            if (TagEditCell.m13205$$Nest$fgetisAdmin(this.this$0) == false) goto L8;
        L11:
            return r22;
        L8:
            if (TextUtils.isEmpty(r22) == false) goto L11;
            return null;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean isAdmin(long r1) {
            return TagEditCell.m13205$$Nest$fgetisAdmin(this.this$0);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean isOwner(long r1) {
            return TagEditCell.m13206$$Nest$fgetisOwner(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.TagEditCell$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewOutlineProvider {
        public AnonymousClass5() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight(), AndroidUtilities.dp(16.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.TagEditCell$6, reason: invalid class name */
    public class AnonymousClass6 extends ReplacementSpan {
        private float textWidth;
        final /* synthetic */ Paint val$bgPaint;
        final /* synthetic */ int val$color;
        final /* synthetic */ String val$rankStr;

        public AnonymousClass6(String r1, int r2, Paint r3) {
            this.val$rankStr = r1;
            this.val$color = r2;
            this.val$bgPaint = r3;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r14, CharSequence r15, int r16, int r17, float r18, int r19, int r20, int r21, Paint r22) {
            float r1 = (r19 + r21) / 2.0f;
            float r3 = AndroidUtilities.dp(19.0f);
            r22.setColor(this.val$color);
            float r10 = r3 / 2.0f;
            r14.drawRoundRect(r18, r1 - r10, (r18 + this.textWidth) + AndroidUtilities.dp(11.33f), r1 + r10, r10, r10, this.val$bgPaint);
            r14.drawText(this.val$rankStr, r18 + AndroidUtilities.dpf2(5.66f), r21 - AndroidUtilities.dp(6.0f), r22);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            float r22 = AndroidUtilities.dpf2(11.33f);
            float r12 = r1.measureText(this.val$rankStr);
            this.textWidth = r12;
            return (int) (r22 + r12);
        }
    }

    /* renamed from: org.telegram.ui.Components.TagEditCell$7, reason: invalid class name */
    public class AnonymousClass7 extends ChatMessageCell {
        public AnonymousClass7(Context r1, int r2) {
            super(r1, r2);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public int getParentWidth() {
            return (AndroidUtilities.displaySize.x - AndroidUtilities.dp(128.0f)) / 2;
        }

        @Override // android.view.View
        public boolean isPressed() {
            return false;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public void updateTranslation() {
        }
    }

    /* renamed from: org.telegram.ui.Components.TagEditCell$8, reason: invalid class name */
    public class AnonymousClass8 implements ChatMessageCell.ChatMessageCellDelegate {
        final /* synthetic */ boolean val$forceAdmin;
        final /* synthetic */ boolean val$owner;

        public AnonymousClass8(boolean r1, boolean r2) {
            this.val$forceAdmin = r1;
            this.val$owner = r2;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean canPerformActions() {
            return false;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public String getAdminRank(long r1) {
            if (this.val$forceAdmin == true) goto L5;
            int r0 = R.string.TagInfoMemberTitle;
        L10:
            return LocaleController.getString(r0);
        L5:
            if (this.val$owner == false) goto L7;
            r0 = R.string.TagInfoOwnerTitle;
            goto L10
        L7:
            r0 = R.string.TagInfoAdminTitle;
            goto L10
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean isAdmin(long r1) {
            return this.val$forceAdmin;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean isOwner(long r1) {
            if (this.val$forceAdmin == true) goto L5;
            return false;
        L5:
            if (this.val$owner == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.TagEditCell$9, reason: invalid class name */
    public class AnonymousClass9 extends SizeNotifierFrameLayout {
        private GradientClip clip;
        final /* synthetic */ ChatMessageCell val$messageCell;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass9(Context r1, Theme.ResourcesProvider r2, ChatMessageCell r3) {
            this.val$resourcesProvider = r2;
            this.val$messageCell = r3;
            super(r1);
            this.clip = new GradientClip();
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r9, View r10, long r11) {
            if (r10 != this.val$messageCell) goto L7;
            r9.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            boolean r92 = super.drawChild(r9, r10, r11);
            r9.save();
            RectF r102 = AndroidUtilities.rectTmp;
            r102.set(0.0f, 0.0f, AndroidUtilities.dp(45.0f), getHeight());
            this.clip.draw(r9, r102, 0, 1.0f);
            r9.restore();
            r9.restore();
            return r92;
        L7:
            return super.drawChild(r9, r10, r11);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public Theme.ResourcesProvider getResourceProvider() {
            return this.val$resourcesProvider;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isActionBarVisible() {
            return false;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public boolean isStatusBarVisible() {
            return false;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            this.val$messageCell.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.displaySize.x, TLObject.FLAG_30), r5);
            setMeasuredDimension(View.MeasureSpec.getSize(r4), AndroidUtilities.dp(24.0f) + this.val$messageCell.getMeasuredHeight());
        }
    }

    public static final class LineSpan extends ReplacementSpan {
        private final Paint paint;
        private final int width;

        public LineSpan(int r3) {
            Paint r0 = new Paint(1);
            this.paint = r0;
            this.width = r3;
            r0.setColor(Theme.multAlpha(Theme.getColor(Theme.key_chat_inTimeText), 0.3f));
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            float r22 = ((r6 + r8) / 2.0f) + AndroidUtilities.dp(1.33f);
            float r42 = AndroidUtilities.dp(6.66f);
            RectF r62 = AndroidUtilities.rectTmp;
            float r43 = r42 / 2.0f;
            r62.set(r5, r22 - r43, this.width + r5, r22 + r43);
            r1.drawRoundRect(r62, r43, r43, this.paint);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return this.width;
        }
    }

    public static /* synthetic */ void $r8$lambda$8iP45FHLePS4LayHYMw5FkHDf1w(String[] r1, ButtonWithCounterView r2, boolean r3, boolean r4, String r5) {
        r1[0] = r5;
        if (TextUtils.isEmpty(r5) == false) goto L7;
        if (r3 == true) goto L7;
        if (r4 == false) goto L7;
        int r12 = R.string.MemberTagButtonRemove;
    L10:
        r2.setText(LocaleController.getString(r12), true);
        return;
    L7:
        if (r4 == false) goto L9;
        r12 = R.string.MemberTagButtonEdit;
        goto L10
    L9:
        r12 = R.string.MemberTagButtonAdd;
        goto L10
    }

    public static /* synthetic */ void $r8$lambda$EYaTEK9ekuFJlW_iXKESiNrDaJI(MessagesController r9, long r10, TLRPC.User r12, TLRPC.TL_messages_editChatParticipantRank r13, BottomSheet r14, boolean r15, Theme.ResourcesProvider r16, ButtonWithCounterView r17, TLRPC.Updates r18, TLRPC.TL_error r19) {
        if (r18 == null) goto L13;
        r9.updateRank(-r10, r12.id, r13.rank);
        r9.processUpdates(r18, false);
        r14.dismiss();
        BaseFragment r92 = LaunchActivity.getSafeLastFragment();
        if (TextUtils.isEmpty(r13.rank) == true) goto L16;
        if (r92 == null) goto L17;
        BulletinFactory r93 = BulletinFactory.of(r92);
        int r102 = R.raw.contact_check;
        if (r15 == false) goto L10;
        int r11 = R.string.TagAdded;
    L11:
        r93.createSimpleBulletin(r102, LocaleController.getString(r11), r13.rank).wrapContent().show();
        return;
    L10:
        r11 = R.string.TagEdited;
        goto L11
    L17:
        return;
    L16:
        return;
    L13:
        if (r19 == null) goto L18;
        BulletinFactory.of(r14.topBulletinContainer, r16).showForError(r19);
        r17.setLoading(false);
        return;
    }

    /* renamed from: $r8$lambda$GBlzvwXOHsFrzz-jQGofqQmX7YQ, reason: not valid java name */
    public static /* synthetic */ void m13202$r8$lambda$GBlzvwXOHsFrzzjQGofqQmX7YQ(EditTextBoldCursor r2) {
        r2.requestFocus();
        r2.setSelection(0, r2.length());
        AndroidUtilities.showKeyboard(r2);
    }

    public static /* synthetic */ void $r8$lambda$HI04zyyeNzkwfJyhN6OInlsEOMo(BottomSheet r1, boolean[] r2, View r3) {
        r1.dismiss();
        if (r2[0] == true) goto L6;
        MessagesController.getGlobalMainSettings().edit().putInt("showchattagsinfo", 0).apply();
        r2[0] = true;
        return;
    }

    public static /* synthetic */ void $r8$lambda$LeL6uw2YrMLXmCsAmLU_uC4UrVM(BottomSheet r0, Context r1, int r2, long r3, TLRPC.User r5, String r6, boolean r7, boolean r8, Theme.ResourcesProvider r9, boolean[] r10, View r11) {
        r0.dismiss();
        showSheet(r1, r2, r3, r5, r6, r7, r8, r9);
        if (r10[0] == true) goto L6;
        MessagesController.getGlobalMainSettings().edit().putInt("showchattagsinfo", 0).apply();
        r10[0] = true;
        return;
    }

    public static /* synthetic */ void $r8$lambda$UhXtNvNRNGQtdC6VNwmGEFDiSuc(EditTextBoldCursor r0, View r1) {
        r0.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    public static /* synthetic */ void $r8$lambda$dG9N0iQI2PUPWI4XLG4xt8uuXKI(boolean[] r5) {
        if (r5[0] == true) goto L6;
        SharedPreferences r1 = MessagesController.getGlobalMainSettings();
        r1.edit().putInt("showchattagsinfo", r1.getInt("showchattagsinfo", 3) - 1).apply();
        r5[0] = true;
        return;
    }

    public static /* synthetic */ void $r8$lambda$fr1MKt7PjmshMn150UCDwPHMoK8(ButtonWithCounterView r1, TagEditCell r2, MessagesController r3, long r4, TLRPC.User r6, String[] r7, int r8, BottomSheet r9, boolean r10, Theme.ResourcesProvider r11, View r12) {
        if (r1.isLoading() == false) goto L6;
        return;
    L6:
        if (r2.isOverLimit() == false) goto L8;
        return;
    L8:
        r1.setLoading(true);
        AndroidUtilities.hideKeyboard(r2.editTextCell);
        TLRPC.TL_messages_editChatParticipantRank r82 = new TLRPC.TL_messages_editChatParticipantRank();
        r82.peer = r3.getInputPeer(r4);
        r82.participant = MessagesController.getInputPeer(r6);
        r82.rank = r7[0];
        ConnectionsManager.getInstance(r8).sendRequestTyped(r82, new AiTonesController$$ExternalSyntheticLambda0(), new TagEditCell$$ExternalSyntheticLambda4(r3, r4, r6, r82, r9, r10, r11, r1));
    }

    /* renamed from: $r8$lambda$gsVkXg0ri-EazsWD4syyGudDsSo, reason: not valid java name */
    public static /* synthetic */ void m13203$r8$lambda$gsVkXg0riEazsWD4syyGudDsSo(BottomSheet r0, View r1) {
        r0.dismiss();
    }

    /* renamed from: -$$Nest$fgetignoreEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13204$$Nest$fgetignoreEdit(TagEditCell r0) {
        return r0.ignoreEdit;
    }

    /* renamed from: -$$Nest$fgetisAdmin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13205$$Nest$fgetisAdmin(TagEditCell r0) {
        return r0.isAdmin;
    }

    /* renamed from: -$$Nest$fgetisOwner, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13206$$Nest$fgetisOwner(TagEditCell r0) {
        return r0.isOwner;
    }

    /* renamed from: -$$Nest$fgetlimitTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m13207$$Nest$fgetlimitTextView(TagEditCell r0) {
        return r0.limitTextView;
    }

    /* renamed from: -$$Nest$fgetmessageCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m13208$$Nest$fgetmessageCell(TagEditCell r0) {
        return r0.messageCell;
    }

    /* renamed from: -$$Nest$fgetmessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m13209$$Nest$fgetmessageObject(TagEditCell r0) {
        return r0.messageObject;
    }

    /* renamed from: -$$Nest$fgetonRankEdited, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback m13210$$Nest$fgetonRankEdited(TagEditCell r0) {
        return r0.onRankEdited;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m13211$$Nest$fgetresourcesProvider(TagEditCell r0) {
        return r0.resourcesProvider;
    }

    public TagEditCell(Context r23, int r24, long r25, Theme.ResourcesProvider r27) {
        super(r23);
        this.shakeDp = -6.0f;
        this.currentAccount = r24;
        this.dialogId = r25;
        this.resourcesProvider = r27;
        setOrientation(1);
        AnonymousClass1 r5 = new AnonymousClass1(this, r23);
        this.chatView = r5;
        r5.setBackgroundImage(PreviewView.getBackgroundDrawable(null, r24, r25, Theme.isCurrentThemeDark()), false);
        AnonymousClass2 r9 = new AnonymousClass2(this, r23, r24);
        this.messageCell = r9;
        r5.addView(r9, LayoutHelper.createFrame(-1, -2.0f, 87, 0.0f, 12.0f, 0.0f, 12.0f));
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r1 = new BackupImageView(r23);
        this.avatarImageView = r1;
        r1.setRoundRadius(AndroidUtilities.dp(21.0f));
        r5.addView(r1, LayoutHelper.createFrame(42, 42.0f, 83, 8.0f, 0.0f, 0.0f, 12.0f));
        addView(r5, LayoutHelper.createLinear(-1, -2, 7));
        PollEditTextCell r12 = new PollEditTextCell(r23, false, 0, null, r27);
        this.editTextCell = r12;
        EditTextBoldCursor r3 = r12.getTextView();
        r3.setEnabled(true);
        r3.setSingleLine(true);
        r3.setImeOptions(6);
        r12.setTextRight(114);
        ImageView r4 = new ImageView(r23);
        this.clearImageView = r4;
        r4.setImageResource(R.drawable.menu_delete_old);
        r4.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r27), PorterDuff.Mode.SRC_IN));
        r12.addView(r4, LayoutHelper.createFrame(24, 24.0f, 21, 0.0f, 0.0f, 20.0f, 0.0f));
        ScaleStateListAnimator.apply(r4);
        r4.setOnClickListener(new TagEditCell$$ExternalSyntheticLambda8(r3));
        AnimatedTextView r42 = new AnimatedTextView(r23, false, true, false);
        this.limitTextView = r42;
        r42.adaptWidth = false;
        r42.setTypeface(AndroidUtilities.bold());
        r42.setTextColor(Theme.getColor(Theme.key_text_RedBold, r27));
        r42.setTextSize(AndroidUtilities.dp(14.0f));
        r42.setGravity(17);
        r42.setAllowCancel(true);
        r42.setScaleProperty(0.6f);
        r12.addView(r42, LayoutHelper.createFrame(56, 50.0f, 117, 0.0f, 0.0f, 44.0f, 0.0f));
        r3.addTextChangedListener(new AnonymousClass3(this));
        addView(r12, LayoutHelper.createLinear(-1, -2, 7));
        r9.setDelegate(new AnonymousClass4(this, r3));
    }

    public static void showInfoSheet(Context r30, int r31, long r32, TLRPC.User r34, String r35, boolean r36, boolean r37, boolean r38, Theme.ResourcesProvider r39) {
        int r1 = r31;
        long r2 = r32;
        Theme.ResourcesProvider r8 = r39;
        TLRPC.Chat r4 = MessagesController.getInstance(r1).getChat(Long.valueOf(-r2));
        if (r4 != null) goto L5;
        return;
    L5:
        BottomSheet.Builder r5 = new BottomSheet.Builder(r30, true, r8);
        LinearLayout r6 = new LinearLayout(r30);
        r6.setOrientation(1);
        r5.setCustomView(r6);
        if (r37 == false) goto L8;
        int r9 = Theme.key_chat_tagCreator;
    L11:
        int r92 = Theme.getColor(r9);
        int r10 = Theme.multAlpha(r92, 0.1f);
        BackupImageView r11 = new BackupImageView(r30);
        r11.setImageResource(R.drawable.large_user_tag);
        r11.setColorFilter(new PorterDuffColorFilter(r92, PorterDuff.Mode.SRC_IN));
        r11.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), r10));
        r6.addView(r11, LayoutHelper.createLinear(80, 80, 49, 0, 18, 0, 0));
        int r112 = Theme.key_windowBackgroundWhiteBlackText;
        TextView r13 = TextHelper.makeTextView(r30, 20.0f, r112, true);
        r13.setGravity(17);
        if (r37 == false) goto L14;
        int r15 = R.string.TagInfoOwnerTitle;
    L17:
        r13.setText(LocaleController.getString(r15));
        r6.addView(r13, LayoutHelper.createFrame(-1, -2.0f, 49, 32.0f, 15.0f, 32.0f, 0.0f));
        TextView r113 = TextHelper.makeTextView(r30, 14.0f, r112, false);
        r113.setGravity(17);
        r113.setLineSpacing(AndroidUtilities.dp(3.0f), 1.0f);
        String r132 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r35 != null) goto L25;
        if (r37 == false) goto L22;
        int r14 = R.string.ChatTagOwner;
    L21:
        String r142 = LocaleController.getString(r14);
    L26:
        SpannableStringBuilder r12 = new SpannableStringBuilder(r142);
        if (r37 == true) goto L29;
        if (r36 == true) goto L29;
        r12.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_chat_inTimeText)), 0, r12.length(), 33);
        BottomSheet.Builder r18 = r5;
    L32:
        if (r37 == false) goto L34;
        int r52 = R.string.TagInfoOwnerText;
    L37:
        r113.setText(AndroidUtilities.replaceCharSequence("un1", AndroidUtilities.replaceTags(LocaleController.formatString(r52, new Object[]{UserObject.getFirstName(r34), r4.title})), r12));
        r6.addView(r113, LayoutHelper.createFrame(-1, -2.0f, 49, 32.0f, 10.0f, 32.0f, 25.0f));
        LinearLayout r53 = new LinearLayout(r30);
        r53.setOrientation(0);
        r6.addView(r53, LayoutHelper.createLinear(-1, -2, 7, 16, 0, 16, 16));
        int r143 = 0;
    L39:
        if (r143 >= 2) goto L58;
        AnonymousClass7 r102 = new AnonymousClass7(r30, r1);
        if (r143 != 1) goto L43;
        boolean r114 = true;
    L44:
        r102.setDelegate(new AnonymousClass8(r114, r37));
        AnonymousClass9 r115 = new AnonymousClass9(r30, r8, r102);
        r115.setBackgroundImage(PreviewView.getBackgroundDrawable(null, r1, r2, Theme.isCurrentThemeDark()), false);
        r115.addView(r102, LayoutHelper.createFrame(-1, -2.0f, 87, 0.0f, 12.0f, 0.0f, 12.0f));
        if (r143 != 1) goto L47;
        int r26 = 6;
    L48:
        if (r143 != 0) goto L50;
        int r28 = 6;
    L51:
        r53.addView(r115, LayoutHelper.createLinear(0, -1, 1.0f, 119, r26, 0, r28, 0));
        r115.setClipToOutline(true);
        r115.setOutlineProvider(new AnonymousClass10());
        TLRPC.TL_message r93 = new TLRPC.TL_message();
        String r262 = r132;
        r93.from_id = MessagesController.getInstance(r1).getPeer(r34.id);
        r93.peer_id = MessagesController.getInstance(r1).getPeer(r2);
        r93.message = r262;
        r93.date = ConnectionsManager.getInstance(r1).getCurrentTime();
        r93.out = false;
        MessageObject r133 = new MessageObject(r1, r93, true, false);
        r133.forceAvatar = true;
        SpannableStringBuilder r3 = new SpannableStringBuilder("_\n_  ");
        r3.setSpan(new LineSpan(AndroidUtilities.dp(200.0f)), 0, 1, 33);
        r3.setSpan(new LineSpan(AndroidUtilities.dp(160.0f)), 2, 3, 33);
        r133.messageText = r3;
        r102.isChat = true;
        if (ChatObject.isChannel(r4) == true) goto L54;
    L56:
        boolean r22 = false;
    L57:
        r102.isMegagroup = r22;
        r133.generateLayout(null);
        r102.setMessageObject(r133, null, false, false, false);
        r102.setTranslationX(-AndroidUtilities.dp(140.0f));
        r143 = r143 + 1;
        r1 = r31;
        r2 = r32;
        r132 = r262;
        goto L39
    L54:
        if (r4.megagroup == false) goto L56;
        r22 = true;
        goto L57
    L50:
        r28 = 0;
        goto L51
    L47:
        r26 = 0;
        goto L48
    L43:
        r114 = false;
        goto L44
    L58:
        ButtonWithCounterView r122 = new ButtonWithCounterView(r30, r8).setRound();
        if (ChatObject.canManageTags(r4) == false) goto L66;
        if (r36 == false) goto L69;
        if (r37 == true) goto L64;
        if (r38 == true) goto L69;
    L64:
        if (UserObject.isUserSelf(r34) == false) goto L66;
    L69:
        boolean r144 = true;
    L71:
        if (r144 == false) goto L73;
    L82:
        r6.addView(r122, LayoutHelper.createLinear(-1, 48, 7, 16, 16, 16, 16));
        boolean[] r116 = new boolean[1];
        BottomSheet r16 = r18.create();
        if (r144 == true) goto L87;
        r122.setText(StarGiftSheet.replaceUnderstood(LocaleController.getString(R.string.Understood)));
        r122.setOnClickListener(new TagEditCell$$ExternalSyntheticLambda5(r16, r116));
        BottomSheet r94 = r16;
    L97:
        r94.smoothKeyboardAnimationEnabled = true;
        r94.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, r8));
        r94.setOnDismissListener(new TagEditCell$$ExternalSyntheticLambda7(r116));
        if (MessagesController.getGlobalMainSettings().getInt("showchattagsinfo", 3) > 0) goto L102;
        if (r144 == false) goto L102;
        showSheet(r30, r31, r32, r34, r35, r36, r37, r8);
        return;
    L102:
        r94.show();
        return;
    L87:
        if (UserObject.isUserSelf(r34) == false) goto L93;
        if (TextUtils.isEmpty(r35) == false) goto L91;
        int r23 = R.string.TagInfoButtonAddMyTag;
    L96:
        r122.setText(LocaleController.getString(r23));
        TagEditCell$$ExternalSyntheticLambda6 r0 = new TagEditCell$$ExternalSyntheticLambda6(r16, r30, r31, r32, r34, r35, r36, r37, r8, r116);
        r94 = r16;
        r8 = r8;
        r122.setOnClickListener(r0);
        goto L97
    L91:
        r23 = R.string.TagInfoButtonEditMyTag;
        goto L96
    L93:
        if (TextUtils.isEmpty(r35) == false) goto L95;
        r23 = R.string.TagInfoButtonAddTag;
        goto L96
    L95:
        r23 = R.string.TagInfoButtonEditTag;
        goto L96
    L73:
        if (ChatObject.canManageTags(r4) == true) goto L82;
        if (ChatObject.canManageMyTag(r4) == true) goto L82;
        if (r4.creator == true) goto L82;
        if (r4.admin_rights != null) goto L82;
        if (r37 == true) goto L82;
        TextView r17 = TextHelper.makeTextView(r30, 12.0f, Theme.key_windowBackgroundWhiteGrayText, false);
        r17.setGravity(1);
        r17.setText(LocaleController.getString(R.string.CantEditTagAdmins));
        r6.addView(r17, LayoutHelper.createLinear(-1, -2, 32.0f, 0.0f, 32.0f, 0.0f));
    L66:
        if (ChatObject.canManageMyTag(r4) == true) goto L68;
    L70:
        r144 = false;
        goto L71
    L68:
        if (UserObject.isUserSelf(r34) == false) goto L70;
    L34:
        if (r36 == false) goto L36;
        r52 = R.string.TagInfoAdminText;
        goto L37
    L36:
        r52 = R.string.TagInfoMemberText;
    L29:
        r18 = r5;
        Paint r54 = new Paint(1);
        r54.setColor(r10);
        r12.setSpan(new AnonymousClass6(r142, r92, r54), 0, r12.length(), 33);
        goto L32
    L22:
        if (r36 == false) goto L24;
        r14 = R.string.ChatTagAdmin;
        goto L21
    L24:
        r142 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L26
    L25:
        r142 = r35;
        goto L26
    L14:
        if (r36 == false) goto L16;
        r15 = R.string.TagInfoAdminTitle;
        goto L17
    L16:
        r15 = R.string.TagInfoMemberTitle;
        goto L17
    L8:
        if (r36 == false) goto L10;
        r9 = Theme.key_chat_tagAdmin;
        goto L11
    L10:
        r9 = Theme.key_chat_inAdminText;
        goto L11
    }

    public static void showSheet(Context r24, int r25, long r26, TLRPC.User r28, String r29, boolean r30, boolean r31, Theme.ResourcesProvider r32) {
        MessagesController r7 = MessagesController.getInstance(r25);
        r7.getChat(Long.valueOf(-r26));
        BottomSheet.Builder r8 = new BottomSheet.Builder(r24, true, r32);
        LinearLayout r10 = new LinearLayout(r24);
        r10.setOrientation(1);
        r8.setCustomView(r10);
        LinearLayout r0 = new LinearLayout(r24);
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
        TextView r5 = TextHelper.makeTextView(r24, 20.0f, r2, true);
        r5.setText(LocaleController.getString(R.string.MemberTagTitle));
        r0.addView(r5, LayoutHelper.createLinear(0, -2, 1.0f, 19, 22, 0, 22, 0));
        ImageView r12 = new ImageView(r24);
        r12.setScaleType(ImageView.ScaleType.CENTER);
        r12.setImageResource(R.drawable.ic_close_white);
        r12.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2, r32), PorterDuff.Mode.SRC_IN));
        r12.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, r32), 1, AndroidUtilities.dp(18.0f)));
        r0.addView(r12, LayoutHelper.createLinear(32, 32, 21, 0, 0, 10, 0));
        r10.addView(r0, LayoutHelper.createLinear(-1, -2, 0.0f, 6.0f, 0.0f, 6.0f));
        ButtonWithCounterView r13 = new ButtonWithCounterView(r24, r32).setRound();
        if (TextUtils.isEmpty(r29) == false) goto L7;
        if (r30 == true) goto L7;
        boolean r15 = false;
    L9:
        if (TextUtils.isEmpty(r29) == false) goto L13;
        if (r30 == true) goto L13;
        if (r15 == false) goto L13;
        int r02 = R.string.MemberTagButtonRemove;
    L16:
        r13.setText(LocaleController.getString(r02));
        if (r29 != null) goto L19;
        String r03 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        String[] r04 = {r03};
        TagEditCell r22 = new TagEditCell(r24, r25, r26, r32);
        r22.setClipToOutline(true);
        r22.setOutlineProvider(new AnonymousClass5());
        r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, r32));
        r22.set(r28, r29, r30, r31, new TagEditCell$$ExternalSyntheticLambda0(r04, r13, r30, r15));
        r10.addView(r22, LayoutHelper.createLinear(-1, -2, 7, 12.0f, 12.0f, 12.0f, 1.66f));
        TextInfoPrivacyCell r1 = new TextInfoPrivacyCell(r24, 22, r32);
        if (UserObject.isUserSelf(r28) == false) goto L23;
        String r23 = LocaleController.getString(R.string.MemberTagSelfInfo);
    L24:
        r1.setText(r23);
        r10.addView(r1, LayoutHelper.createLinear(-1, -2, 7, 0, 0, 0, 0));
        r10.addView(r13, LayoutHelper.createLinear(-1, 48, 7, 14, 19, 14, 12));
        BottomSheet r14 = r8.create();
        r14.smoothKeyboardAnimationEnabled = true;
        r14.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, r32));
        if (TextUtils.isEmpty(r29) == false) goto L30;
        if (r30 == true) goto L30;
        if (r31 == true) goto L30;
        boolean r102 = true;
    L31:
        r13.setOnClickListener(new TagEditCell$$ExternalSyntheticLambda1(r13, r22, r7, r26, r28, r04, r25, r14, r102, r32));
        r12.setOnClickListener(new TagEditCell$$ExternalSyntheticLambda2(r14));
        r14.show();
        EditTextBoldCursor r05 = r22.editTextCell.getTextView();
        r05.post(new TagEditCell$$ExternalSyntheticLambda3(r05));
        return;
    L30:
        r102 = false;
        goto L31
    L23:
        r23 = LocaleController.formatString(R.string.MemberTagTheirInfo, new Object[]{UserObject.getUserName(r28)});
        goto L24
    L19:
        r03 = r29;
    L13:
        if (r15 == false) goto L15;
        r02 = R.string.MemberTagButtonEdit;
        goto L16
    L15:
        r02 = R.string.MemberTagButtonAdd;
    L7:
        r15 = true;
        goto L9
    }

    public boolean isOverLimit() {
        EditTextBoldCursor r0 = this.editTextCell.getTextView();
        if (r0.getText().toString().trim().length() > 16) goto L6;
        return false;
    L6:
        float r1 = -this.shakeDp;
        this.shakeDp = r1;
        AndroidUtilities.shakeViewSpring(r0, r1);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return true;
    }

    public void set(TLRPC.User r11, String r12, boolean r13, boolean r14, Utilities.Callback<String> r15) {
        TLRPC.TL_message r0 = new TLRPC.TL_message();
        r0.from_id = MessagesController.getInstance(this.currentAccount).getPeer(r11.id);
        r0.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(this.dialogId);
        r0.message = _UrlKt.FRAGMENT_ENCODE_SET;
        r0.date = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();
        r0.out = false;
        this.isAdmin = r13;
        this.isOwner = r14;
        MessageObject r142 = new MessageObject(this.currentAccount, r0, true, false);
        this.messageObject = r142;
        r142.forceAvatar = true;
        SpannableStringBuilder r143 = new SpannableStringBuilder("_\n_  ");
        r143.setSpan(new LineSpan((int) Math.min(AndroidUtilities.displaySize.x * 0.5f, AndroidUtilities.dp(200.0f))), 0, 1, 33);
        r143.setSpan(new LineSpan((int) Math.min(AndroidUtilities.displaySize.x * 0.44f, AndroidUtilities.dp(160.0f))), 2, 3, 33);
        this.messageObject.messageText = r143;
        TLRPC.Chat r144 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        ChatMessageCell r02 = this.messageCell;
        if (r144 == null) goto L5;
        boolean r2 = true;
    L6:
        r02.isChat = r2;
        if (ChatObject.isChannel(r144) == true) goto L9;
    L11:
        boolean r145 = false;
    L12:
        r02.isMegagroup = r145;
        this.messageObject.generateLayout(null);
        this.messageCell.setMessageObject(this.messageObject, null, false, false, false);
        this.avatarDrawable.setInfo(r11);
        this.avatarImageView.setForUserOrChat(r11, this.avatarDrawable);
        this.onRankEdited = r15;
        this.ignoreEdit = true;
        PollEditTextCell r112 = this.editTextCell;
        if (TextUtils.isEmpty(r12) == false) goto L16;
        if (r13 == true) goto L16;
        int r132 = R.string.MemberTagHintAdd;
    L17:
        r112.setTextAndHint(r12, LocaleController.getString(r132), false);
        this.ignoreEdit = false;
        return;
    L16:
        r132 = R.string.MemberTagHintEdit;
        goto L17
    L9:
        if (r144.megagroup == false) goto L11;
        r145 = true;
        goto L12
    L5:
        r2 = false;
        goto L6
    }
}
