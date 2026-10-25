package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.AdminedChannelCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.RadioButtonCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextBlockCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.EditTextEmoji;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkActionView;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.OutlineTextContainerView;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TypefaceSpan;

/* loaded from: classes.dex */
public class ChannelCreateActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, ImageUpdater.ImageUpdaterDelegate {
    private ArrayList<AdminedChannelCell> adminedChannelCells;
    private TextInfoPrivacyCell adminedInfoCell;
    private LinearLayout adminnedChannelsLayout;
    private TLRPC.FileLocation avatar;
    private AnimatorSet avatarAnimation;
    private TLRPC.FileLocation avatarBig;
    private AvatarDrawable avatarDrawable;
    private RLottieImageView avatarEditor;
    private BackupImageView avatarImage;
    private View avatarOverlay;
    private RadialProgressView avatarProgressView;
    private RLottieDrawable cameraDrawable;
    private boolean canCreatePublic;
    private AlertDialog cancelDialog;
    private long chatId;
    private int checkReqId;
    private Runnable checkRunnable;
    private TextView checkTextView;
    private boolean createAfterUpload;
    private int currentStep;
    private OutlineTextContainerView descriptionFieldContainer;
    private EditTextBoldCursor descriptionTextView;
    private View doneButton;
    private CrossfadeDrawable doneButtonDrawable;
    private ValueAnimator doneButtonDrawableAnimator;
    private boolean donePressed;
    private Integer doneRequestId;
    private EditTextBoldCursor editText;
    private Runnable enableDoneLoading;
    private Boolean forcePublic;
    private HeaderCell headerCell;
    private HeaderCell headerCell2;
    private TextView helpTextView;
    private ImageUpdater imageUpdater;
    private TLRPC.VideoSize inputEmojiMarkup;
    private TLRPC.InputFile inputPhoto;
    private TLRPC.InputFile inputVideo;
    private String inputVideoPath;
    private TLRPC.TL_chatInviteExported invite;
    private boolean isGroup;
    private boolean isPrivate;
    private String lastCheckName;
    private boolean lastNameAvailable;
    private LinearLayout linearLayout;
    private LinearLayout linearLayout2;
    private LinearLayout linkContainer;
    private LoadingCell loadingAdminedCell;
    private boolean loadingAdminedChannels;
    private boolean loadingInvite;
    private EditTextEmoji nameTextView;
    private String nameToSet;
    private Utilities.Callback2<BaseFragment, Long> onFinishListener;
    private LinkActionView permanentLinkView;
    private LinearLayout privateContainer;
    private LinearLayout publicContainer;
    private RadioButtonCell radioButtonCell1;
    private RadioButtonCell radioButtonCell2;
    private ShadowSectionCell sectionCell;
    private TextInfoPrivacyCell typeInfoCell;
    private double videoTimestamp;

    /* renamed from: org.telegram.ui.ChannelCreateActivity$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChannelCreateActivity this$0;

        public static /* synthetic */ void $r8$lambda$9wX63CPdiATJLBPlNLL5ANkuUCY(AnonymousClass1 r0) {
            r0.lambda$onItemClick$1();
        }

        public static /* synthetic */ void $r8$lambda$h7_ovFgPO4RTLvyCVQ4IGkuBEP8(AnonymousClass1 r0) {
            r0.lambda$onItemClick$0();
        }

        public AnonymousClass1(ChannelCreateActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0() {
            ChannelCreateActivity.m7337$$Nest$mupdateDoneProgress(this.this$0, false);
            if (ChannelCreateActivity.m7330$$Nest$fgetonFinishListener(this.this$0) == null) goto L6;
            Utilities.Callback2 r0 = ChannelCreateActivity.m7330$$Nest$fgetonFinishListener(this.this$0);
            ChannelCreateActivity r3 = this.this$0;
            r0.run(r3, Long.valueOf(ChannelCreateActivity.m7319$$Nest$fgetchatId(r3)));
            return;
        }

        private /* synthetic */ void lambda$onItemClick$1() {
            ChannelCreateActivity.m7337$$Nest$mupdateDoneProgress(this.this$0, false);
            if (ChannelCreateActivity.m7330$$Nest$fgetonFinishListener(this.this$0) == null) goto L6;
            Utilities.Callback2 r0 = ChannelCreateActivity.m7330$$Nest$fgetonFinishListener(this.this$0);
            ChannelCreateActivity r3 = this.this$0;
            r0.run(r3, Long.valueOf(ChannelCreateActivity.m7319$$Nest$fgetchatId(r3)));
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r14) {
            if (r14 != (-1)) goto L11;
            boolean r142 = ChannelCreateActivity.m7323$$Nest$fgetdonePressed(this.this$0);
            ChannelCreateActivity r13 = this.this$0;
            if (r142 == false) goto L8;
            ChannelCreateActivity.m7336$$Nest$mshowDoneCancelDialog(r13);
            return;
        L8:
            r13.finishFragment();
            return;
        L11:
            if (r14 != 1) goto L51;
            int r143 = ChannelCreateActivity.m7321$$Nest$fgetcurrentStep(this.this$0);
            ChannelCreateActivity r1 = this.this$0;
            if (r143 != 0) goto L32;
            if (r1.getParentActivity() == null) goto L54;
            boolean r144 = ChannelCreateActivity.m7323$$Nest$fgetdonePressed(this.this$0);
            ChannelCreateActivity r12 = this.this$0;
            if (r144 == false) goto L21;
            ChannelCreateActivity.m7336$$Nest$mshowDoneCancelDialog(r12);
            return;
        L21:
            int r145 = ChannelCreateActivity.m7329$$Nest$fgetnameTextView(r12).length();
            ChannelCreateActivity r15 = this.this$0;
            if (r145 != 0) goto L25;
            ChannelCreateActivity.m7329$$Nest$fgetnameTextView(r15).performHapticFeedback(3, 2);
            AndroidUtilities.shakeView(ChannelCreateActivity.m7329$$Nest$fgetnameTextView(this.this$0));
            return;
        L25:
            ChannelCreateActivity.m7333$$Nest$fputdonePressed(r15, true);
            AndroidUtilities.runOnUIThread(ChannelCreateActivity.m7324$$Nest$fgetenableDoneLoading(this.this$0), 200);
            boolean r146 = ChannelCreateActivity.m7325$$Nest$fgetimageUpdater(this.this$0).isUploadingImage();
            ChannelCreateActivity r16 = this.this$0;
            if (r146 == false) goto L29;
            ChannelCreateActivity.m7332$$Nest$fputcreateAfterUpload(r16, true);
            return;
        L29:
            ChannelCreateActivity.m7334$$Nest$fputdoneRequestId(r16, Integer.valueOf(MessagesController.getInstance(ChannelCreateActivity.access$000(r16)).createChat(ChannelCreateActivity.m7329$$Nest$fgetnameTextView(this.this$0).getText().toString(), new ArrayList(), ChannelCreateActivity.m7322$$Nest$fgetdescriptionTextView(this.this$0).getText().toString(), 2, false, null, null, -1, this.this$0)));
            return;
        L54:
            return;
        L32:
            if (ChannelCreateActivity.m7321$$Nest$fgetcurrentStep(r1) != 1) goto L52;
            boolean r147 = ChannelCreateActivity.m7326$$Nest$fgetisPrivate(this.this$0);
            ChannelCreateActivity r17 = this.this$0;
            if (r147 == true) goto L45;
            int r148 = ChannelCreateActivity.m7322$$Nest$fgetdescriptionTextView(r17).length();
            ChannelCreateActivity r18 = this.this$0;
            if (r148 != 0) goto L39;
            AlertDialog.Builder r149 = new AlertDialog.Builder(r18.getParentActivity());
            r149.setTitle(LocaleController.getString(R.string.ChannelPublicEmptyUsernameTitle));
            r149.setMessage(LocaleController.getString(R.string.ChannelPublicEmptyUsername));
            r149.setPositiveButton(LocaleController.getString(R.string.Close), null);
            this.this$0.showDialog(r149.create());
            return;
        L39:
            boolean r1410 = ChannelCreateActivity.m7328$$Nest$fgetlastNameAvailable(r18);
            ChannelCreateActivity r19 = this.this$0;
            if (r1410 == true) goto L43;
            ChannelCreateActivity.m7320$$Nest$fgetcheckTextView(r19).performHapticFeedback(3, 2);
            AndroidUtilities.shakeView(ChannelCreateActivity.m7320$$Nest$fgetcheckTextView(this.this$0));
            return;
        L43:
            AndroidUtilities.runOnUIThread(ChannelCreateActivity.m7324$$Nest$fgetenableDoneLoading(r19), 200);
            MessagesController r6 = MessagesController.getInstance(ChannelCreateActivity.access$100(this.this$0));
            ChannelCreateActivity r7 = this.this$0;
            r6.updateChannelUserName(r7, ChannelCreateActivity.m7319$$Nest$fgetchatId(r7), ChannelCreateActivity.m7327$$Nest$fgetlastCheckName(this.this$0), new ChannelCreateActivity$1$$ExternalSyntheticLambda0(this), new ChannelCreateActivity$1$$ExternalSyntheticLambda1(this));
        L48:
            if (ChannelCreateActivity.m7330$$Nest$fgetonFinishListener(this.this$0) != null) goto L53;
            Bundle r1411 = new Bundle();
            r1411.putInt("step", 2);
            r1411.putLong("chatId", ChannelCreateActivity.m7319$$Nest$fgetchatId(this.this$0));
            r1411.putInt("chatType", 2);
            this.this$0.presentFragment(new GroupCreateActivity(r1411), true);
            return;
        L53:
            return;
        L45:
            if (ChannelCreateActivity.m7330$$Nest$fgetonFinishListener(r17) == null) goto L48;
            Utilities.Callback2 r1412 = ChannelCreateActivity.m7330$$Nest$fgetonFinishListener(this.this$0);
            ChannelCreateActivity r110 = this.this$0;
            r1412.run(r110, Long.valueOf(ChannelCreateActivity.m7319$$Nest$fgetchatId(r110)));
            goto L48
        L52:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChannelCreateActivity$10, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ ChannelCreateActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass10(ChannelCreateActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            ChannelCreateActivity.m7331$$Nest$fputavatarAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (ChannelCreateActivity.m7314$$Nest$fgetavatarAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (ChannelCreateActivity.m7315$$Nest$fgetavatarEditor(this.this$0) == null) goto L14;
            boolean r32 = this.val$show;
            ChannelCreateActivity r0 = this.this$0;
            if (r32 == false) goto L10;
            ChannelCreateActivity.m7315$$Nest$fgetavatarEditor(r0).setVisibility(4);
        L11:
            ChannelCreateActivity.m7331$$Nest$fputavatarAnimation(this.this$0, null);
            return;
        L10:
            ChannelCreateActivity.m7318$$Nest$fgetavatarProgressView(r0).setVisibility(4);
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.ChannelCreateActivity$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        private boolean ignoreLayout;
        final /* synthetic */ ChannelCreateActivity this$0;

        public AnonymousClass2(ChannelCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
            int r112 = getChildCount();
            int r0 = measureKeyboardHeight();
            int r2 = 0;
            if (r0 <= AndroidUtilities.dp(20.0f)) goto L5;
        L9:
            int r1 = 0;
        L10:
            setBottomClip(r1);
        L11:
            if (r2 >= r112) goto L48;
            View r3 = getChildAt(r2);
            if (r3.getVisibility() == 8) goto L47;
            FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) r3.getLayoutParams();
            int r5 = r3.getMeasuredWidth();
            int r6 = r3.getMeasuredHeight();
            int r7 = r4.gravity;
            if (r7 != (-1)) goto L18;
            r7 = 51;
        L18:
            int r8 = r7 & 112;
            int r72 = r7 & 7;
            if (r72 != 1) goto L21;
            int r73 = (((r14 - r12) - r5) / 2) + r4.leftMargin;
            int r9 = r4.rightMargin;
        L24:
            int r74 = r73 - r9;
        L27:
            if (r8 != 16) goto L29;
            int r82 = ((((r15 - r1) - r13) - r6) / 2) + r4.topMargin;
            int r42 = r4.bottomMargin;
        L34:
            int r43 = r82 - r42;
        L38:
            if (ChannelCreateActivity.m7329$$Nest$fgetnameTextView(this.this$0) != null) goto L40;
        L46:
            r3.layout(r74, r43, r5 + r74, r6 + r43);
            goto L47
        L40:
            if (ChannelCreateActivity.m7329$$Nest$fgetnameTextView(this.this$0).isPopupView(r3) == false) goto L46;
            if (AndroidUtilities.isTablet() == false) goto L45;
            int r44 = getMeasuredHeight();
            int r83 = r3.getMeasuredHeight();
        L44:
            r43 = r44 - r83;
            goto L46
        L45:
            r44 = getMeasuredHeight() + r0;
            r83 = r3.getMeasuredHeight();
            goto L44
        L29:
            if (r8 != 48) goto L31;
            r43 = r4.topMargin + getPaddingTop();
            goto L38
        L31:
            if (r8 == 80) goto L33;
            r43 = r4.topMargin;
            goto L38
        L33:
            r82 = ((r15 - r1) - r13) - r6;
            r42 = r4.bottomMargin;
            goto L34
        L21:
            if (r72 == 5) goto L23;
            r74 = r4.leftMargin;
            goto L27
        L23:
            r73 = r14 - r5;
            r9 = r4.rightMargin;
        L47:
            r2 = r2 + 1;
            goto L11
        L48:
            notifyHeightChanged();
            return;
        L5:
            if (AndroidUtilities.isInMultiwindow == true) goto L9;
            if (AndroidUtilities.isTablet() == true) goto L9;
            r1 = ChannelCreateActivity.m7329$$Nest$fgetnameTextView(this.this$0).getEmojiPadding();
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r12, int r13) {
            int r6 = View.MeasureSpec.getSize(r12);
            int r1 = View.MeasureSpec.getSize(r13);
            setMeasuredDimension(r6, r1);
            int r7 = r1 - getPaddingTop();
            measureChildWithMargins(ChannelCreateActivity.access$200(this.this$0), r12, 0, r13, 0);
            if (measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L5;
            this.ignoreLayout = true;
            ChannelCreateActivity.m7329$$Nest$fgetnameTextView(this.this$0).hideEmojiView();
            this.ignoreLayout = false;
        L5:
            int r8 = getChildCount();
            int r9 = 0;
        L6:
            if (r9 >= r8) goto L34;
            View r14 = getChildAt(r9);
            if (r14 == null) goto L33;
            if (r14.getVisibility() == 8) goto L33;
            if (r14 == ChannelCreateActivity.access$300(this.this$0)) goto L33;
            if (ChannelCreateActivity.m7329$$Nest$fgetnameTextView(this.this$0) != null) goto L17;
        L32:
            measureChildWithMargins(r14, r12, 0, r13, 0);
            goto L33
        L17:
            if (ChannelCreateActivity.m7329$$Nest$fgetnameTextView(this.this$0).isPopupView(r14) == false) goto L32;
            if (AndroidUtilities.isInMultiwindow == true) goto L25;
            if (AndroidUtilities.isTablet() == true) goto L25;
            r14.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r14.getLayoutParams().height, TLObject.FLAG_30));
        L25:
            if (AndroidUtilities.isTablet() == false) goto L31;
            int r2 = View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30);
            if (AndroidUtilities.isTablet() == false) goto L29;
            float r4 = 200.0f;
        L30:
            r14.measure(r2, View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(r4), (r7 - AndroidUtilities.statusBarHeight) + getPaddingTop()), TLObject.FLAG_30));
            goto L33
        L29:
            r4 = 320.0f;
            goto L30
        L31:
            r14.measure(View.MeasureSpec.makeMeasureSpec(r6, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r7 - AndroidUtilities.statusBarHeight) + getPaddingTop(), TLObject.FLAG_30));
        L33:
            r9 = r9 + 1;
            goto L6
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.ChannelCreateActivity$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends BackupImageView {
        final /* synthetic */ ChannelCreateActivity this$0;

        public AnonymousClass3(ChannelCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            if (ChannelCreateActivity.m7317$$Nest$fgetavatarOverlay(this.this$0) == null) goto L5;
            ChannelCreateActivity.m7317$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        L5:
            super.invalidate();
        }

        @Override // android.view.View
        public void invalidate(int r2, int r3, int r4, int r5) {
            if (ChannelCreateActivity.m7317$$Nest$fgetavatarOverlay(this.this$0) == null) goto L5;
            ChannelCreateActivity.m7317$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        L5:
            super.invalidate(r2, r3, r4, r5);
        }
    }

    /* renamed from: org.telegram.ui.ChannelCreateActivity$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends View {
        final /* synthetic */ ChannelCreateActivity this$0;
        final /* synthetic */ Paint val$paint;

        public AnonymousClass4(ChannelCreateActivity r1, Context r2, Paint r3) {
            this.this$0 = r1;
            this.val$paint = r3;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            if (ChannelCreateActivity.m7316$$Nest$fgetavatarImage(this.this$0) != null) goto L5;
            return;
        L5:
            if (ChannelCreateActivity.m7316$$Nest$fgetavatarImage(this.this$0).getImageReceiver().hasNotThumb() == false) goto L9;
            this.val$paint.setAlpha((int) ((ChannelCreateActivity.m7316$$Nest$fgetavatarImage(this.this$0).getImageReceiver().getCurrentAlpha() * 85.0f) * ChannelCreateActivity.m7318$$Nest$fgetavatarProgressView(this.this$0).getAlpha()));
            r10.drawRoundRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), ExteraConfig.getAvatarCorners(getMeasuredWidth(), true), ExteraConfig.getAvatarCorners(getMeasuredWidth(), true), this.val$paint);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ChannelCreateActivity$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends RLottieImageView {
        final /* synthetic */ ChannelCreateActivity this$0;

        public AnonymousClass5(ChannelCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            ChannelCreateActivity.m7317$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        }

        @Override // android.view.View
        public void invalidate(int r1, int r2, int r3, int r4) {
            super.invalidate(r1, r2, r3, r4);
            ChannelCreateActivity.m7317$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.ChannelCreateActivity$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 extends RadialProgressView {
        final /* synthetic */ ChannelCreateActivity this$0;

        public AnonymousClass6(ChannelCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RadialProgressView, android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            ChannelCreateActivity.m7317$$Nest$fgetavatarOverlay(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.ChannelCreateActivity$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 implements TextWatcher {
        final /* synthetic */ ChannelCreateActivity this$0;

        public AnonymousClass7(ChannelCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.ChannelCreateActivity$8, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass8 implements TextWatcher {
        final /* synthetic */ ChannelCreateActivity this$0;

        public AnonymousClass8(ChannelCreateActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            ChannelCreateActivity r0 = this.this$0;
            ChannelCreateActivity.m7335$$Nest$mcheckUserName(r0, ChannelCreateActivity.m7322$$Nest$fgetdescriptionTextView(r0).getText().toString());
        }
    }

    /* renamed from: org.telegram.ui.ChannelCreateActivity$9, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass9 extends LinkSpanDrawable.LinksTextView {
        final /* synthetic */ ChannelCreateActivity this$0;

        /* renamed from: org.telegram.ui.ChannelCreateActivity$9$1, reason: invalid class name */
        public class AnonymousClass1 extends ClickableSpan {
            final /* synthetic */ AnonymousClass9 this$1;
            final /* synthetic */ String val$username;

            public AnonymousClass1(AnonymousClass9 r1, String r2) {
                this.this$1 = r1;
                this.val$username = r2;
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View r3) {
                Browser.openUrl(this.this$1.getContext(), "https://fragment.com/username/" + this.val$username);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint r1) {
                super.updateDrawState(r1);
                r1.setUnderlineText(false);
            }
        }

        public AnonymousClass9(ChannelCreateActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v0, types: [org.telegram.ui.ChannelCreateActivity$9, org.telegram.ui.Components.LinkSpanDrawable$LinksTextView] */
        /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.CharSequence] */
        /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.CharSequence] */
        /* JADX WARN: Type inference failed for: r8v3, types: [android.text.SpannableStringBuilder] */
        @Override // org.telegram.ui.Components.LinkSpanDrawable.LinksTextView, android.widget.TextView
        public void setText(CharSequence r8, TextView.BufferType r9) {
            if (r8 == 0) goto L16;
            r8 = AndroidUtilities.replaceTags(r8.toString());
            int r0 = r8.toString().indexOf(10);
            int r2 = 0;
            if (r0 < 0) goto L6;
            r8.replace(r0, r0 + 1, " ");
            r8.setSpan(new ForegroundColorSpan(this.this$0.getThemedColor(Theme.key_text_RedRegular)), 0, r0, 33);
        L6:
            TypefaceSpan[] r02 = (TypefaceSpan[]) r8.getSpans(0, r8.length(), TypefaceSpan.class);
            if (ChannelCreateActivity.m7322$$Nest$fgetdescriptionTextView(this.this$0) != null) goto L9;
        L12:
            String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        L14:
            if (r2 >= r02.length) goto L16;
            r8.setSpan(new AnonymousClass1(this, r3), r8.getSpanStart(r02[r2]), r8.getSpanEnd(r02[r2]), 33);
            r8.removeSpan(r02[r2]);
            r2 = r2 + 1;
            goto L14
        L9:
            if (ChannelCreateActivity.m7322$$Nest$fgetdescriptionTextView(this.this$0).getText() == null) goto L12;
            r3 = ChannelCreateActivity.m7322$$Nest$fgetdescriptionTextView(this.this$0).getText().toString();
        L16:
            super.setText(r8, r9);
        }
    }

    /* renamed from: $r8$lambda$2iEpsN-UEN-pHTcEa505Y_J3C9I, reason: not valid java name */
    public static /* synthetic */ void m7308$r8$lambda$2iEpsNUENpHTcEa505Y_J3C9I(ChannelCreateActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadAdminedChannels$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$BEZNGkHrn6KYUFmkFkxXyA3rLbk(ChannelCreateActivity r0, View r1) {
        r0.lambda$createView$12(r1);
    }

    /* renamed from: $r8$lambda$BzYt-VP_nEu3ivrQTtF7Ng-u_YQ, reason: not valid java name */
    public static /* synthetic */ void m7309$r8$lambda$BzYtVP_nEu3ivrQTtF7Ngu_YQ(ChannelCreateActivity r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ void $r8$lambda$Dg1oRm0qrLdOlq3X6axGd7qSuCA(ChannelCreateActivity r0, TLObject r1) {
        r0.lambda$loadAdminedChannels$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$GRfvKRZPq_k3VmpndUFEzdJi2IA(ChannelCreateActivity r0, View r1) {
        r0.lambda$createView$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$Gr3a0RFoFZdzJZWqeYDb8D4yzOc(ChannelCreateActivity r0, String r1, TLRPC.TL_channels_checkUsername r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$checkUserName$24(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$HH9zCp236zw0bDcUEp1Q7jRnnNs(ChannelCreateActivity r0, View r1) {
        r0.lambda$loadAdminedChannels$20(r1);
    }

    /* renamed from: $r8$lambda$IR6h2U6YrKRVXAnQyimYc-9gLak, reason: not valid java name */
    public static /* synthetic */ boolean m7310$r8$lambda$IR6h2U6YrKRVXAnQyimYc9gLak(ChannelCreateActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$JJ0fFx7q0NildyuumxdlbvndBsc(ChannelCreateActivity r0, TLRPC.TL_error r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$NPCbFzt1N7mNzXjPAfezAQbp4yo(ChannelCreateActivity r0, String r1, TLRPC.TL_error r2, TLObject r3, TLRPC.TL_channels_checkUsername r4) {
        r0.lambda$checkUserName$23(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Q0MnuvWwhcXmxyjld0CuOfJTepg(ChannelCreateActivity r0, View r1) {
        r0.lambda$createView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$QRw5Vdad3WzZoG5D9o2toFLjgxM(ChannelCreateActivity r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$generateLink$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$RlCad8X76ggDBwTLFqeakN4pXL8(ChannelCreateActivity r0, ValueAnimator r1) {
        r0.lambda$updateDoneProgress$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$SUGT3rUGfesleBFUQnytxbOxSio(ChannelCreateActivity r0, AlertDialog r1, int r2) {
        r0.lambda$showDoneCancelDialog$2(r1, r2);
    }

    /* renamed from: $r8$lambda$YWhCKOgeWAGiYybyB85hfnW-D3Q, reason: not valid java name */
    public static /* synthetic */ void m7311$r8$lambda$YWhCKOgeWAGiYybyB85hfnWD3Q(ChannelCreateActivity r0, DialogInterface r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$YoicgIBJAfSh8idBObzovhQu3_Q(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$_8dxAKx2y4dmUENdNS3EqjQD7LQ(ChannelCreateActivity r0, View r1, boolean r2) {
        r0.lambda$createView$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bO0BhO22gAk0ion1GqsYgUJrM9c(ChannelCreateActivity r0) {
        r0.lambda$createView$6();
    }

    public static /* synthetic */ void $r8$lambda$fQ5O4aCn3kLN22muFjkobOLGToI(ChannelCreateActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadAdminedChannels$22(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$i8ypgU_U4Y4tKybS6_glKZwT1Qs(ChannelCreateActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$iUPEmCHZad1Itguhmfn9G4j7XyQ(ChannelCreateActivity r0, String r1) {
        r0.lambda$checkUserName$25(r1);
    }

    public static /* synthetic */ void $r8$lambda$ibh8vbWGWD1Q4l_LmB7K04bOu5g(ChannelCreateActivity r0, TLRPC.Chat r1, AlertDialog r2, int r3) {
        r0.lambda$loadAdminedChannels$19(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$jtuvZCyAZsoxHKmyL2U2M-6WjP0, reason: not valid java name */
    public static /* synthetic */ void m7312$r8$lambda$jtuvZCyAZsoxHKmyL2U2M6WjP0(ChannelCreateActivity r0, TLRPC.InputFile r1, TLRPC.InputFile r2, TLRPC.VideoSize r3, String r4, double r5, TLRPC.PhotoSize r7, TLRPC.PhotoSize r8) {
        r0.lambda$didUploadPhoto$16(r1, r2, r3, r4, r5, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$uEKqJSIC7NeOJG9LObQJgh3OuO0(ChannelCreateActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$generateLink$15(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$ux00iXudH4CsWk0QWScnvhhRA6k(ChannelCreateActivity r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$createView$11(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$xUmhcq0C_eLIFAToVaWPj9aTajs(ChannelCreateActivity r0) {
        r0.lambda$getThemeDescriptions$27();
    }

    /* renamed from: $r8$lambda$xXrLO15eNEh10qQJz6xG-885w5A, reason: not valid java name */
    public static /* synthetic */ void m7313$r8$lambda$xXrLO15eNEh10qQJz6xG885w5A(ChannelCreateActivity r0) {
        r0.lambda$loadAdminedChannels$17();
    }

    public static /* synthetic */ void $r8$lambda$yoWrO8ksd6VW8o2TFDD4v3zMvCw(ChannelCreateActivity r0) {
        r0.lambda$showPremiumIncreaseLimitDialog$26();
    }

    /* renamed from: -$$Nest$fgetavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m7314$$Nest$fgetavatarAnimation(ChannelCreateActivity r0) {
        return r0.avatarAnimation;
    }

    /* renamed from: -$$Nest$fgetavatarEditor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m7315$$Nest$fgetavatarEditor(ChannelCreateActivity r0) {
        return r0.avatarEditor;
    }

    /* renamed from: -$$Nest$fgetavatarImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m7316$$Nest$fgetavatarImage(ChannelCreateActivity r0) {
        return r0.avatarImage;
    }

    /* renamed from: -$$Nest$fgetavatarOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m7317$$Nest$fgetavatarOverlay(ChannelCreateActivity r0) {
        return r0.avatarOverlay;
    }

    /* renamed from: -$$Nest$fgetavatarProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m7318$$Nest$fgetavatarProgressView(ChannelCreateActivity r0) {
        return r0.avatarProgressView;
    }

    /* renamed from: -$$Nest$fgetchatId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m7319$$Nest$fgetchatId(ChannelCreateActivity r2) {
        return r2.chatId;
    }

    /* renamed from: -$$Nest$fgetcheckTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m7320$$Nest$fgetcheckTextView(ChannelCreateActivity r0) {
        return r0.checkTextView;
    }

    /* renamed from: -$$Nest$fgetcurrentStep, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7321$$Nest$fgetcurrentStep(ChannelCreateActivity r0) {
        return r0.currentStep;
    }

    /* renamed from: -$$Nest$fgetdescriptionTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m7322$$Nest$fgetdescriptionTextView(ChannelCreateActivity r0) {
        return r0.descriptionTextView;
    }

    /* renamed from: -$$Nest$fgetdonePressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7323$$Nest$fgetdonePressed(ChannelCreateActivity r0) {
        return r0.donePressed;
    }

    /* renamed from: -$$Nest$fgetenableDoneLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m7324$$Nest$fgetenableDoneLoading(ChannelCreateActivity r0) {
        return r0.enableDoneLoading;
    }

    /* renamed from: -$$Nest$fgetimageUpdater, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageUpdater m7325$$Nest$fgetimageUpdater(ChannelCreateActivity r0) {
        return r0.imageUpdater;
    }

    /* renamed from: -$$Nest$fgetisPrivate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7326$$Nest$fgetisPrivate(ChannelCreateActivity r0) {
        return r0.isPrivate;
    }

    /* renamed from: -$$Nest$fgetlastCheckName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m7327$$Nest$fgetlastCheckName(ChannelCreateActivity r0) {
        return r0.lastCheckName;
    }

    /* renamed from: -$$Nest$fgetlastNameAvailable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7328$$Nest$fgetlastNameAvailable(ChannelCreateActivity r0) {
        return r0.lastNameAvailable;
    }

    /* renamed from: -$$Nest$fgetnameTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextEmoji m7329$$Nest$fgetnameTextView(ChannelCreateActivity r0) {
        return r0.nameTextView;
    }

    /* renamed from: -$$Nest$fgetonFinishListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback2 m7330$$Nest$fgetonFinishListener(ChannelCreateActivity r0) {
        return r0.onFinishListener;
    }

    /* renamed from: -$$Nest$fputavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7331$$Nest$fputavatarAnimation(ChannelCreateActivity r0, AnimatorSet r1) {
        r0.avatarAnimation = r1;
    }

    /* renamed from: -$$Nest$fputcreateAfterUpload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7332$$Nest$fputcreateAfterUpload(ChannelCreateActivity r0, boolean r1) {
        r0.createAfterUpload = r1;
    }

    /* renamed from: -$$Nest$fputdonePressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7333$$Nest$fputdonePressed(ChannelCreateActivity r0, boolean r1) {
        r0.donePressed = r1;
    }

    /* renamed from: -$$Nest$fputdoneRequestId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7334$$Nest$fputdoneRequestId(ChannelCreateActivity r0, Integer r1) {
        r0.doneRequestId = r1;
    }

    /* renamed from: -$$Nest$mcheckUserName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7335$$Nest$mcheckUserName(ChannelCreateActivity r0, String r1) {
        return r0.checkUserName(r1);
    }

    /* renamed from: -$$Nest$mshowDoneCancelDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7336$$Nest$mshowDoneCancelDialog(ChannelCreateActivity r0) {
        r0.showDoneCancelDialog();
    }

    /* renamed from: -$$Nest$mupdateDoneProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7337$$Nest$mupdateDoneProgress(ChannelCreateActivity r0, boolean r1) {
        r0.updateDoneProgress(r1);
    }

    public ChannelCreateActivity(Bundle r5) {
        super(r5);
        this.adminedChannelCells = new ArrayList();
        this.canCreatePublic = true;
        this.enableDoneLoading = new ChannelCreateActivity$$ExternalSyntheticLambda1(this);
        this.currentStep = r5.getInt("step", 0);
        if (r5.containsKey("forcePublic") == false) goto L5;
        this.forcePublic = Boolean.valueOf(r5.getBoolean("forcePublic", false));
    L5:
        int r1 = this.currentStep;
        if (r1 != 0) goto L9;
        this.avatarDrawable = new AvatarDrawable();
        this.imageUpdater = new ImageUpdater(true, 1, true);
        TLRPC.TL_channels_checkUsername r52 = new TLRPC.TL_channels_checkUsername();
        r52.username = "1";
        r52.channel = new TLRPC.TL_inputChannelEmpty();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r52, new ChannelCreateActivity$$ExternalSyntheticLambda2(this));
        return;
    L9:
        if (r1 != 1) goto L13;
        boolean r0 = r5.getBoolean("canCreatePublic", true);
        this.canCreatePublic = r0;
        this.isPrivate = !r0;
        if (r0 == true) goto L13;
        loadAdminedChannels();
    L13:
        this.chatId = r5.getLong("chat_id", 0);
    }

    public static /* synthetic */ int access$000(ChannelCreateActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(ChannelCreateActivity r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ActionBar access$200(ChannelCreateActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(ChannelCreateActivity r0) {
        return r0.actionBar;
    }

    private boolean checkUserName(String r7) {
        if (r7 != null) goto L5;
    L7:
        this.checkTextView.setVisibility(8);
    L8:
        Runnable r1 = this.checkRunnable;
        if (r1 == null) goto L13;
        AndroidUtilities.cancelRunOnUIThread(r1);
        this.checkRunnable = null;
        this.lastCheckName = null;
        if (this.checkReqId == 0) goto L13;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.checkReqId, true);
    L13:
        this.lastNameAvailable = false;
        if (r7 != null) goto L16;
    L46:
        if (r7 != null) goto L48;
    L56:
        this.checkTextView.setText(LocaleController.getString(R.string.LinkInvalidShort));
        TextView r72 = this.checkTextView;
        int r12 = Theme.key_text_RedRegular;
        r72.setTag(Integer.valueOf(r12));
        this.checkTextView.setTextColor(Theme.getColor(r12));
        return false;
    L48:
        if (r7.length() < 4) goto L56;
        int r13 = r7.length();
        TextView r3 = this.checkTextView;
        if (r13 <= 32) goto L54;
        r3.setText(LocaleController.getString(R.string.LinkInvalidLong));
        TextView r73 = this.checkTextView;
        int r14 = Theme.key_text_RedRegular;
        r73.setTag(Integer.valueOf(r14));
        this.checkTextView.setTextColor(Theme.getColor(r14));
        return false;
    L54:
        r3.setText(LocaleController.getString(R.string.LinkChecking));
        TextView r0 = this.checkTextView;
        int r15 = Theme.key_windowBackgroundWhiteGrayText8;
        r0.setTag(Integer.valueOf(r15));
        this.checkTextView.setTextColor(Theme.getColor(r15));
        this.lastCheckName = r7;
        ChannelCreateActivity$$ExternalSyntheticLambda21 r02 = new ChannelCreateActivity$$ExternalSyntheticLambda21(this, r7);
        this.checkRunnable = r02;
        AndroidUtilities.runOnUIThread(r02, 300);
        return true;
    L16:
        if (r7.startsWith("_") == false) goto L18;
    L44:
        this.checkTextView.setText(LocaleController.getString(R.string.LinkInvalid));
        TextView r74 = this.checkTextView;
        int r16 = Theme.key_text_RedRegular;
        r74.setTag(Integer.valueOf(r16));
        this.checkTextView.setTextColor(Theme.getColor(r16));
        return false;
    L18:
        if (r7.endsWith("_") == true) goto L44;
        int r17 = 0;
    L22:
        if (r17 >= r7.length()) goto L46;
        char r32 = r7.charAt(r17);
        if (r17 != 0) goto L29;
        if (r32 < '0') goto L29;
        if (r32 > '9') goto L29;
        this.checkTextView.setText(LocaleController.getString(R.string.LinkInvalidStartNumber));
        TextView r75 = this.checkTextView;
        int r18 = Theme.key_text_RedRegular;
        r75.setTag(Integer.valueOf(r18));
        this.checkTextView.setTextColor(Theme.getColor(r18));
        return false;
    L29:
        if (r32 < '0') goto L32;
        if (r32 > '9') goto L32;
    L43:
        r17 = r17 + 1;
    L32:
        if (r32 < 'a') goto L36;
        if (r32 <= 'z') goto L43;
    L36:
        if (r32 < 'A') goto L40;
        if (r32 <= 'Z') goto L43;
    L40:
        if (r32 == '_') goto L43;
        this.checkTextView.setText(LocaleController.getString(R.string.LinkInvalid));
        TextView r76 = this.checkTextView;
        int r19 = Theme.key_text_RedRegular;
        r76.setTag(Integer.valueOf(r19));
        this.checkTextView.setTextColor(Theme.getColor(r19));
        return false;
    L5:
        if (r7.length() <= 0) goto L7;
        this.checkTextView.setVisibility(0);
        goto L8
    }

    private void generateLink() {
        if (this.loadingInvite == false) goto L5;
        return;
    L5:
        if (this.invite != null) goto L16;
        TLRPC.ChatFull r0 = getMessagesController().getChatFull(this.chatId);
        if (r0 == null) goto L11;
        this.invite = r0.exported_invite;
    L11:
        if (this.invite != null) goto L17;
        this.loadingInvite = true;
        TLRPC.TL_messages_getExportedChatInvites r1 = new TLRPC.TL_messages_getExportedChatInvites();
        r1.peer = getMessagesController().getInputPeer(-this.chatId);
        r1.admin_id = getMessagesController().getInputUser(getUserConfig().getCurrentUser());
        r1.limit = 1;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r1, new ChannelCreateActivity$$ExternalSyntheticLambda4(this));
        return;
    L17:
        return;
    }

    private /* synthetic */ void lambda$checkUserName$23(String r3, TLRPC.TL_error r4, TLObject r5, TLRPC.TL_channels_checkUsername r6) {
        this.checkReqId = 0;
        String r1 = this.lastCheckName;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.equals(r3) == false) goto L34;
        if (r4 != null) goto L12;
        if ((r5 instanceof TLRPC.TL_boolTrue) == false) goto L12;
        this.checkTextView.setText(LocaleController.formatString("LinkAvailable", R.string.LinkAvailable, new Object[]{r3}));
        TextView r32 = this.checkTextView;
        int r42 = Theme.key_windowBackgroundWhiteGreenText;
        r32.setTag(Integer.valueOf(r42));
        this.checkTextView.setTextColor(Theme.getColor(r42));
        this.lastNameAvailable = true;
        return;
    L12:
        if (r4 != null) goto L14;
    L18:
        if (r4 != null) goto L20;
    L26:
        if (r4 != null) goto L28;
    L30:
        this.checkTextView.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        this.checkTextView.setText(LocaleController.getString(R.string.LinkInUse));
    L31:
        this.lastNameAvailable = false;
        return;
    L28:
        if ("CHANNELS_ADMIN_PUBLIC_TOO_MUCH".equals(r4.text) == false) goto L30;
        this.checkTextView.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        this.canCreatePublic = false;
        showPremiumIncreaseLimitDialog();
        goto L31
    L20:
        if ("USERNAME_PURCHASE_AVAILABLE".equals(r4.text) == false) goto L26;
        int r43 = r6.username.length();
        TextView r52 = this.checkTextView;
        if (r43 != 4) goto L24;
        r52.setText(LocaleController.getString(R.string.UsernameInvalidShortPurchase));
    L25:
        this.checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText8));
        goto L31
    L24:
        r52.setText(LocaleController.getString(R.string.UsernameInUsePurchase));
        goto L25
    L14:
        if ("USERNAME_INVALID".equals(r4.text) == false) goto L18;
        if (r6.username.length() != 4) goto L18;
        this.checkTextView.setText(LocaleController.getString(R.string.UsernameInvalidShort));
        this.checkTextView.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        goto L31
    }

    private /* synthetic */ void lambda$checkUserName$24(String r7, TLRPC.TL_channels_checkUsername r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new ChannelCreateActivity$$ExternalSyntheticLambda24(this, r7, r10, r9, r8));
    }

    private /* synthetic */ void lambda$checkUserName$25(String r5) {
        TLRPC.TL_channels_checkUsername r0 = new TLRPC.TL_channels_checkUsername();
        r0.username = r5;
        r0.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(this.chatId);
        this.checkReqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ChannelCreateActivity$$ExternalSyntheticLambda23(this, r5, r0), 2);
    }

    private /* synthetic */ void lambda$createView$10(View r1, boolean r2) {
        OutlineTextContainerView r0 = this.descriptionFieldContainer;
        if (r2 == false) goto L5;
        float r12 = 1.0f;
    L6:
        r0.animateSelection(r12);
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    private /* synthetic */ boolean lambda$createView$11(TextView r1, int r2, KeyEvent r3) {
        if (r2 != 6) goto L8;
        View r0 = this.doneButton;
        if (r0 == null) goto L10;
        r0.performClick();
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    private /* synthetic */ void lambda$createView$12(View r1) {
        if (this.canCreatePublic == true) goto L7;
        showPremiumIncreaseLimitDialog();
        return;
    L7:
        if (this.isPrivate == true) goto L9;
        return;
    L9:
        this.isPrivate = false;
        updatePrivatePublic();
    }

    private /* synthetic */ void lambda$createView$13(View r1) {
        if (this.isPrivate == false) goto L5;
        return;
    L5:
        this.isPrivate = true;
        updatePrivatePublic();
    }

    private /* synthetic */ void lambda$createView$6() {
        this.avatar = null;
        this.avatarBig = null;
        this.inputPhoto = null;
        this.inputVideo = null;
        this.inputVideoPath = null;
        this.inputEmojiMarkup = null;
        this.videoTimestamp = 0.0d;
        showAvatarProgress(false, true);
        this.avatarImage.setImage(null, null, this.avatarDrawable, null);
        this.avatarEditor.setAnimation(this.cameraDrawable);
        this.cameraDrawable.setCurrentFrame(0);
    }

    private /* synthetic */ void lambda$createView$7(DialogInterface r2) {
        boolean r22 = this.imageUpdater.isUploadingImage();
        RLottieDrawable r0 = this.cameraDrawable;
        if (r22 == true) goto L6;
        r0.setCustomEndFrame(86);
        this.avatarEditor.playAnimation();
        return;
    L6:
        r0.setCurrentFrame(0, false);
    }

    private /* synthetic */ void lambda$createView$8(View r5) {
        ImageUpdater r52 = this.imageUpdater;
        if (this.avatar == null) goto L5;
        boolean r0 = true;
    L6:
        r52.openMenu(r0, new ChannelCreateActivity$$ExternalSyntheticLambda17(this), new ChannelCreateActivity$$ExternalSyntheticLambda18(this), 0);
        this.cameraDrawable.setCurrentFrame(0);
        this.cameraDrawable.setCustomEndFrame(43);
        this.avatarEditor.playAnimation();
        return;
    L5:
        r0 = false;
        goto L6
    }

    private /* synthetic */ boolean lambda$createView$9(TextView r1, int r2, KeyEvent r3) {
        if (r2 == 5) goto L5;
        return false;
    L5:
        if (TextUtils.isEmpty(this.nameTextView.getEditText().getText()) == true) goto L10;
        this.descriptionTextView.requestFocus();
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$didUploadPhoto$16(TLRPC.InputFile r4, TLRPC.InputFile r5, TLRPC.VideoSize r6, String r7, double r8, TLRPC.PhotoSize r10, TLRPC.PhotoSize r11) {
        if (r4 != null) goto L8;
        if (r5 != null) goto L8;
        TLRPC.FileLocation r42 = r10.location;
        this.avatar = r42;
        this.avatarBig = r11.location;
        this.avatarImage.setImage(ImageLocation.getForLocal(r42), "50_50", this.avatarDrawable, null);
        showAvatarProgress(true, false);
        return;
    L8:
        this.inputPhoto = r4;
        this.inputVideo = r5;
        this.inputEmojiMarkup = r6;
        this.inputVideoPath = r7;
        this.videoTimestamp = r8;
        if (this.createAfterUpload == false) goto L17;
        AlertDialog r43 = this.cancelDialog;
        if (r43 != null) goto L19;
    L16:
        updateDoneProgress(false);
        this.donePressed = false;
        this.doneButton.performClick();
        goto L17
    L19:
        r43.dismiss();     // Catch: Exception -> L14
        this.cancelDialog = null;     // Catch: Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
    L17:
        showAvatarProgress(false, true);
        this.avatarEditor.setImageDrawable(null);
    }

    private /* synthetic */ void lambda$generateLink$14(TLRPC.TL_error r2, TLObject r3) {
        if (r2 != null) goto L5;
        this.invite = (TLRPC.TL_chatInviteExported) ((TLRPC.TL_messages_exportedChatInvites) r3).invites.get(0);
    L5:
        this.loadingInvite = false;
        LinkActionView r22 = this.permanentLinkView;
        TLRPC.TL_chatInviteExported r1 = this.invite;
        if (r1 == null) goto L8;
        String r12 = r1.link;
    L9:
        r22.setLink(r12);
        return;
    L8:
        r12 = null;
        goto L9
    }

    private /* synthetic */ void lambda$generateLink$15(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ChannelCreateActivity$$ExternalSyntheticLambda13(this, r3, r2));
    }

    private /* synthetic */ void lambda$getThemeDescriptions$27() {
        LinearLayout r0 = this.adminnedChannelsLayout;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r1 = 0;
    L5:
        if (r1 >= r02) goto L14;
        View r2 = this.adminnedChannelsLayout.getChildAt(r1);
        if ((r2 instanceof AdminedChannelCell) == false) goto L9;
        ((AdminedChannelCell) r2).update();
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    private /* synthetic */ void lambda$loadAdminedChannels$17() {
        this.canCreatePublic = true;
        if (this.descriptionTextView.length() <= 0) goto L5;
        checkUserName(this.descriptionTextView.getText().toString());
    L5:
        updatePrivatePublic();
    }

    private /* synthetic */ void lambda$loadAdminedChannels$18(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        AndroidUtilities.runOnUIThread(new ChannelCreateActivity$$ExternalSyntheticLambda27(this));
        return;
    }

    private /* synthetic */ void lambda$loadAdminedChannels$19(TLRPC.Chat r1, AlertDialog r2, int r3) {
        TLRPC.TL_channels_updateUsername r22 = new TLRPC.TL_channels_updateUsername();
        r22.channel = MessagesController.getInputChannel(r1);
        r22.username = _UrlKt.FRAGMENT_ENCODE_SET;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r22, new ChannelCreateActivity$$ExternalSyntheticLambda26(this), 64);
    }

    private /* synthetic */ void lambda$loadAdminedChannels$20(View r6) {
        TLRPC.Chat r62 = ((AdminedChannelCell) r6.getParent()).getCurrentChannel();
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.AppName));
        boolean r1 = r62.megagroup;
        int r2 = this.currentAccount;
        if (r1 == false) goto L5;
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("RevokeLinkAlert", R.string.RevokeLinkAlert, new Object[]{MessagesController.getInstance(r2).linkPrefix + "/" + ChatObject.getPublicUsername(r62), r62.title})));
    L6:
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString(R.string.RevokeButton), new ChannelCreateActivity$$ExternalSyntheticLambda25(this, r62));
        showDialog(r0.create());
        return;
    L5:
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString("RevokeLinkAlertChannel", R.string.RevokeLinkAlertChannel, new Object[]{MessagesController.getInstance(r2).linkPrefix + "/" + ChatObject.getPublicUsername(r62), r62.title})));
        goto L6
    }

    private /* synthetic */ void lambda$loadAdminedChannels$21(TLObject r7) {
        this.loadingAdminedChannels = false;
        if (r7 != null) goto L5;
        return;
    L5:
        if (getParentActivity() == null) goto L26;
        int r1 = 0;
    L9:
        if (r1 >= this.adminedChannelCells.size()) goto L11;
        this.linearLayout.removeView(this.adminedChannelCells.get(r1));
        r1 = r1 + 1;
        goto L9
    L11:
        this.adminedChannelCells.clear();
        TLRPC.TL_messages_chats r72 = (TLRPC.TL_messages_chats) r7;
        int r12 = 0;
    L13:
        if (r12 >= r72.chats.size()) goto L19;
        AdminedChannelCell r2 = new AdminedChannelCell(getParentActivity(), new ChannelCreateActivity$$ExternalSyntheticLambda22(this), false, 0);
        TLRPC.Chat r3 = r72.chats.get(r12);
        boolean r5 = true;
        if (r12 == (r72.chats.size() - 1)) goto L18;
        r5 = false;
    L18:
        r2.setChannel(r3, r5);
        this.adminedChannelCells.add(r2);
        this.adminnedChannelsLayout.addView(r2, LayoutHelper.createLinear(-1, 72));
        r12 = r12 + 1;
        goto L13
    L19:
        updatePrivatePublic();
        return;
    }

    private /* synthetic */ void lambda$loadAdminedChannels$22(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChannelCreateActivity$$ExternalSyntheticLambda20(this, r1));
    }

    private /* synthetic */ void lambda$new$0(TLRPC.TL_error r2) {
        if (r2 != null) goto L4;
    L7:
        boolean r22 = true;
    L8:
        this.canCreatePublic = r22;
        return;
    L4:
        if (r2.text.equals("CHANNELS_ADMIN_PUBLIC_TOO_MUCH") == false) goto L7;
        r22 = false;
        goto L8
    }

    private /* synthetic */ void lambda$new$1(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new ChannelCreateActivity$$ExternalSyntheticLambda14(this, r2));
    }

    private /* synthetic */ void lambda$new$3() {
        updateDoneProgress(true);
    }

    private /* synthetic */ void lambda$showDoneCancelDialog$2(AlertDialog r4, int r5) {
        this.donePressed = false;
        this.createAfterUpload = false;
        if (this.doneRequestId == null) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.doneRequestId.intValue(), true);
        this.doneRequestId = null;
    L5:
        updateDoneProgress(false);
        r4.dismiss();
    }

    private /* synthetic */ void lambda$showPremiumIncreaseLimitDialog$26() {
        this.canCreatePublic = true;
        updatePrivatePublic();
    }

    private /* synthetic */ void lambda$updateDoneProgress$4(ValueAnimator r2) {
        this.doneButtonDrawable.setProgress(((Float) r2.getAnimatedValue()).floatValue());
        this.doneButtonDrawable.invalidateSelf();
    }

    private void loadAdminedChannels() {
        if (this.loadingAdminedChannels == false) goto L5;
        return;
    L5:
        this.loadingAdminedChannels = true;
        updatePrivatePublic();
        TLRPC.TL_channels_getAdminedPublicChannels r0 = new TLRPC.TL_channels_getAdminedPublicChannels();
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new ChannelCreateActivity$$ExternalSyntheticLambda15(this));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r10v2 ??, still in use, count: 2, list:
          (r10v2 ?? I:kotlin.jvm.internal.Intrinsics) from 0x001c: INVOKE (r10v2 ?? I:kotlin.jvm.internal.Intrinsics), (null java.lang.Object), (null java.lang.String) DIRECT call: kotlin.jvm.internal.Intrinsics.checkNotNullParameter(java.lang.Object, java.lang.String):void A[MD:(java.lang.Object, java.lang.String):void (m)]
          (r10v2 ?? I:android.animation.AnimatorSet) from 0x001f: IPUT (r10v2 ?? I:android.animation.AnimatorSet), (r8v0 'this' org.telegram.ui.ChannelCreateActivity A[IMMUTABLE_TYPE, THIS]) org.telegram.ui.ChannelCreateActivity.avatarAnimation android.animation.AnimatorSet
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
        */
    /* JADX WARN: Type inference failed for: r10v2, types: [android.animation.AnimatorSet, kotlin.jvm.internal.Intrinsics] */
    private void showAvatarProgress(boolean r9, boolean r10) {
        org.telegram.ui.Components.RLottieImageView r0 = r8.avatarEditor;
        if (r0 != null) goto L5;
        return;
    L5:
        android.animation.AnimatorSet r0 = r8.avatarAnimation;
        if (r0 == null) goto L9;
        r0.removeAllListeners();
        android.animation.AnimatorSet r0 = r8.avatarAnimation;
        r0.cancel();
        r8.avatarAnimation = null;
    L9:
        if (r10 == false) goto L19;
        r10.checkNotNullParameter(null, null);
        r8.avatarAnimation = r10;
        if (r9 == false) goto L13;
        org.telegram.ui.Components.RadialProgressView r4 = r8.avatarProgressView;
        r4.setVisibility(0);
        android.animation.AnimatorSet r4 = r8.avatarAnimation;
        org.telegram.ui.Components.RLottieImageView r5 = r8.avatarEditor;
        android.util.Property r6 = android.view.View.ALPHA;
        float[] r7 = new float[1];
        r7[0] = 0.0f;
        android.animation.ObjectAnimator r0 = android.animation.ObjectAnimator.ofFloat(r5, r6, r7);
        org.telegram.ui.Components.RadialProgressView r5 = r8.avatarProgressView;
        float[] r7 = new float[1];
        r7[0] = 1.0f;
        android.animation.ObjectAnimator r2 = android.animation.ObjectAnimator.ofFloat(r5, r6, r7);
        android.animation.Animator[] r10 = new android.animation.Animator[2];
        r10[0] = r0;
        r10[1] = r2;
        r4.playTogether(r10);
    L17:
        android.animation.AnimatorSet r10 = r8.avatarAnimation;
        r10.setDuration(180);
        android.animation.AnimatorSet r10 = r8.avatarAnimation;
        org.telegram.ui.ChannelCreateActivity.AnonymousClass10 r0 = new org.telegram.ui.ChannelCreateActivity.AnonymousClass10(r8, r9);
        r10.addListener(r0);
        android.animation.AnimatorSet r8 = r8.avatarAnimation;
        r8.start();
        return;
    L13:
        org.telegram.ui.Components.RLottieImageView r4 = r8.avatarEditor;
        int r4 = r4.getVisibility();
        if (r4 == 0) goto L16;
        org.telegram.ui.Components.RLottieImageView r4 = r8.avatarEditor;
        r4.setAlpha(0.0f);
    L16:
        org.telegram.ui.Components.RLottieImageView r4 = r8.avatarEditor;
        r4.setVisibility(0);
        android.animation.AnimatorSet r4 = r8.avatarAnimation;
        org.telegram.ui.Components.RLottieImageView r5 = r8.avatarEditor;
        android.util.Property r6 = android.view.View.ALPHA;
        float[] r7 = new float[1];
        r7[0] = 1.0f;
        android.animation.ObjectAnimator r2 = android.animation.ObjectAnimator.ofFloat(r5, r6, r7);
        org.telegram.ui.Components.RadialProgressView r5 = r8.avatarProgressView;
        float[] r7 = new float[1];
        r7[0] = 0.0f;
        android.animation.ObjectAnimator r0 = android.animation.ObjectAnimator.ofFloat(r5, r6, r7);
        android.animation.Animator[] r10 = new android.animation.Animator[2];
        r10[0] = r2;
        r10[1] = r0;
        r4.playTogether(r10);
        goto L17
    L19:
        org.telegram.ui.Components.RLottieImageView r10 = r8.avatarEditor;
        if (r9 == false) goto L23;
        r10.setAlpha(1.0f);
        org.telegram.ui.Components.RLottieImageView r9 = r8.avatarEditor;
        r9.setVisibility(4);
        org.telegram.ui.Components.RadialProgressView r9 = r8.avatarProgressView;
        r9.setAlpha(1.0f);
        org.telegram.ui.Components.RadialProgressView r8 = r8.avatarProgressView;
        r8.setVisibility(0);
        return;
    L23:
        r10.setAlpha(1.0f);
        org.telegram.ui.Components.RLottieImageView r9 = r8.avatarEditor;
        r9.setVisibility(0);
        org.telegram.ui.Components.RadialProgressView r9 = r8.avatarProgressView;
        r9.setAlpha(0.0f);
        org.telegram.ui.Components.RadialProgressView r8 = r8.avatarProgressView;
        r8.setVisibility(4);
    }

    private void showDoneCancelDialog() {
        if (this.cancelDialog == null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.StopLoadingTitle));
        r0.setMessage(LocaleController.getString(R.string.StopLoading));
        r0.setPositiveButton(LocaleController.getString(R.string.WaitMore), null);
        r0.setNegativeButton(LocaleController.getString(R.string.Stop), new ChannelCreateActivity$$ExternalSyntheticLambda19(this));
        this.cancelDialog = r0.show();
    }

    private void showPremiumIncreaseLimitDialog() {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        LimitReachedBottomSheet r1 = new LimitReachedBottomSheet(this, getParentActivity(), 2, this.currentAccount, null);
        r1.parentIsChannel = true;
        r1.onSuccessRunnable = new ChannelCreateActivity$$ExternalSyntheticLambda16(this);
        showDialog(r1);
    }

    private void updateDoneProgress(boolean r7) {
        if (r7 == true) goto L5;
        AndroidUtilities.cancelRunOnUIThread(this.enableDoneLoading);
    L5:
        if (this.doneButtonDrawable == null) goto L18;
        ValueAnimator r0 = this.doneButtonDrawableAnimator;
        if (r0 == null) goto L9;
        r0.cancel();
    L9:
        float r02 = this.doneButtonDrawable.getProgress();
        float r1 = 0.0f;
        if (r7 == false) goto L12;
        float r3 = 1.0f;
    L13:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{r02, r3});
        this.doneButtonDrawableAnimator = r03;
        r03.addUpdateListener(new ChannelCreateActivity$$ExternalSyntheticLambda3(this));
        ValueAnimator r04 = this.doneButtonDrawableAnimator;
        float r32 = this.doneButtonDrawable.getProgress();
        if (r7 == false) goto L16;
        r1 = 1.0f;
    L16:
        r04.setDuration((long) (Math.abs(r32 - r1) * 200.0f));
        this.doneButtonDrawableAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.doneButtonDrawableAnimator.start();
        return;
    L12:
        r3 = 0.0f;
        goto L13
    }

    private void updatePrivatePublic() {
        if (this.sectionCell != null) goto L5;
        return;
    L5:
        int r1 = 8;
        if (this.isPrivate == false) goto L8;
    L13:
        TextInfoPrivacyCell r0 = this.typeInfoCell;
        int r3 = Theme.key_windowBackgroundWhiteGrayText4;
        r0.setTag(Integer.valueOf(r3));
        this.typeInfoCell.setTextColor(Theme.getColor(r3));
        this.sectionCell.setVisibility(0);
        this.adminedInfoCell.setVisibility(8);
        this.adminnedChannelsLayout.setVisibility(8);
        TextInfoPrivacyCell r02 = this.typeInfoCell;
        r02.setBackgroundDrawable(Theme.getThemedDrawableByKey(r02.getContext(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.linkContainer.setVisibility(0);
        this.loadingAdminedCell.setVisibility(8);
        boolean r03 = this.isGroup;
        TextInfoPrivacyCell r32 = this.typeInfoCell;
        if (r03 == false) goto L27;
        if (this.isPrivate == false) goto L19;
        int r04 = R.string.MegaPrivateLinkHelp;
    L20:
        r32.setText(LocaleController.getString(r04));
        HeaderCell r05 = this.headerCell;
        if (this.isPrivate == false) goto L24;
        int r33 = R.string.ChannelInviteLinkTitle;
    L25:
        r05.setText(LocaleController.getString(r33));
    L37:
        LinearLayout r06 = this.publicContainer;
        if (this.isPrivate == false) goto L40;
        int r34 = 8;
    L41:
        r06.setVisibility(r34);
        LinearLayout r07 = this.privateContainer;
        if (this.isPrivate == false) goto L44;
        int r35 = 0;
    L45:
        r07.setVisibility(r35);
        LinearLayout r08 = this.linkContainer;
        if (this.isPrivate == false) goto L48;
        int r36 = 0;
    L49:
        r08.setPadding(0, 0, 0, r36);
        LinkActionView r09 = this.permanentLinkView;
        TLRPC.TL_chatInviteExported r37 = this.invite;
        if (r37 == null) goto L52;
        String r38 = r37.link;
    L53:
        r09.setLink(r38);
        TextView r010 = this.checkTextView;
        if (this.isPrivate == false) goto L56;
    L58:
        r010.setVisibility(r1);
    L59:
        this.radioButtonCell1.setChecked(!this.isPrivate, true);
        this.radioButtonCell2.setChecked(this.isPrivate, true);
        this.descriptionTextView.clearFocus();
        AndroidUtilities.hideKeyboard(this.descriptionTextView);
        return;
    L56:
        if (r010.length() == 0) goto L58;
        r1 = 0;
        goto L58
    L52:
        r38 = null;
        goto L53
    L48:
        r36 = AndroidUtilities.dp(7.0f);
        goto L49
    L44:
        r35 = 8;
        goto L45
    L40:
        r34 = 0;
        goto L41
    L24:
        r33 = R.string.ChannelLinkTitle;
        goto L25
    L19:
        r04 = R.string.MegaUsernameHelp;
        goto L20
    L27:
        if (this.isPrivate == false) goto L30;
        int r011 = R.string.ChannelPrivateLinkHelp;
    L31:
        r32.setText(LocaleController.getString(r011));
        HeaderCell r012 = this.headerCell;
        if (this.isPrivate == false) goto L35;
        int r39 = R.string.ChannelInviteLinkTitle;
    L36:
        r012.setText(LocaleController.getString(r39));
        goto L37
    L35:
        r39 = R.string.ChannelLinkTitle;
        goto L36
    L30:
        r011 = R.string.ChannelUsernameHelp;
        goto L31
    L8:
        if (this.canCreatePublic == true) goto L13;
        this.typeInfoCell.setText(LocaleController.getString(R.string.ChangePublicLimitReached));
        TextInfoPrivacyCell r013 = this.typeInfoCell;
        int r310 = Theme.key_text_RedRegular;
        r013.setTag(Integer.valueOf(r310));
        this.typeInfoCell.setTextColor(Theme.getColor(r310));
        this.linkContainer.setVisibility(8);
        this.sectionCell.setVisibility(8);
        if (this.loadingAdminedChannels == false) goto L12;
        this.loadingAdminedCell.setVisibility(0);
        this.adminnedChannelsLayout.setVisibility(8);
        TextInfoPrivacyCell r014 = this.typeInfoCell;
        r014.setBackgroundDrawable(Theme.getThemedDrawableByKey(r014.getContext(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        this.adminedInfoCell.setVisibility(8);
        goto L59
    L12:
        TextInfoPrivacyCell r015 = this.typeInfoCell;
        r015.setBackgroundDrawable(Theme.getThemedDrawableByKey(r015.getContext(), R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        this.loadingAdminedCell.setVisibility(8);
        this.adminnedChannelsLayout.setVisibility(0);
        this.adminedInfoCell.setVisibility(0);
        goto L59
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r28) {
        ChannelCreateActivity r2 = this;
        EditTextEmoji r0 = r2.nameTextView;
        if (r0 == null) goto L5;
        r0.onDestroy();
    L5:
        r2.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        r2.actionBar.setAllowOverlayTitle(true);
        r2.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(r2));
        ActionBarMenu r02 = r2.actionBar.createMenu();
        Drawable r3 = r28.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r5 = Theme.key_actionBarDefaultIcon;
        r3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r5), PorterDuff.Mode.MULTIPLY));
        CrossfadeDrawable r4 = new CrossfadeDrawable(r3, new CircularProgressDrawable(Theme.getColor(r5)));
        r2.doneButtonDrawable = r4;
        r2.doneButton = r02.addItemWithWidth(1, r4, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        int r03 = r2.currentStep;
        if (r03 != 0) goto L75;
        r2.actionBar.setTitle(LocaleController.getString(R.string.NewChannel));
        AnonymousClass2 r04 = new AnonymousClass2(r2, r28);
        r04.setOnTouchListener(new ChannelCreateActivity$$ExternalSyntheticLambda5());
        r2.fragmentView = r04;
        int r32 = Theme.key_windowBackgroundWhite;
        r04.setTag(Integer.valueOf(r32));
        r2.fragmentView.setBackgroundColor(Theme.getColor(r32));
        LinearLayout r33 = new LinearLayout(r28);
        r2.linearLayout = r33;
        r33.setOrientation(1);
        r04.addView(r2.linearLayout, new FrameLayout.LayoutParams(-1, -2));
        FrameLayout r7 = new FrameLayout(r28);
        r2.linearLayout.addView(r7, LayoutHelper.createLinear(-1, -2));
        AnonymousClass3 r34 = new AnonymousClass3(r2, r28);
        r2.avatarImage = r34;
        r34.setRoundRadius(ExteraConfig.getAvatarCorners(56.0f));
        r2.avatarDrawable.setInfo(5, null, null);
        r2.avatarImage.setImageDrawable(r2.avatarDrawable);
        BackupImageView r35 = r2.avatarImage;
        boolean r9 = LocaleController.isRTL;
        if (r9 == false) goto L10;
        int r10 = 5;
    L11:
        int r20 = r10 | 48;
        if (r9 == false) goto L14;
        float r21 = 0.0f;
    L15:
        if (r9 == false) goto L17;
        float r23 = 24.0f;
    L18:
        r7.addView(r35, LayoutHelper.createFrame(56, 56.0f, r20, r21, 20.0f, r23, 12.0f));
        Paint r36 = new Paint(1);
        r36.setColor(1426063360);
        AnonymousClass4 r92 = new AnonymousClass4(r2, r28, r36);
        r2.avatarOverlay = r92;
        r92.setContentDescription(LocaleController.getString(R.string.ChatSetPhotoOrVideo));
        View r37 = r2.avatarOverlay;
        boolean r93 = LocaleController.isRTL;
        if (r93 == false) goto L21;
        int r18 = 5;
    L22:
        int r212 = r18 | 48;
        if (r93 == false) goto L25;
        float r22 = 0.0f;
    L26:
        if (r93 == false) goto L28;
        float r24 = 24.0f;
    L29:
        r7.addView(r37, LayoutHelper.createFrame(56, 56.0f, r212, r22, 20.0f, r24, 12.0f));
        r2.avatarOverlay.setOnClickListener(new ChannelCreateActivity$$ExternalSyntheticLambda6(r2));
        r2.cameraDrawable = new RLottieDrawable(R.raw.camera, AndroidUtilities.dp(52.0f), AndroidUtilities.dp(52.0f), false, null);
        AnonymousClass5 r38 = new AnonymousClass5(r2, r28);
        r2.avatarEditor = r38;
        r38.setScaleType(ImageView.ScaleType.CENTER);
        r2.avatarEditor.setAnimation(r2.cameraDrawable);
        r2.avatarEditor.setEnabled(false);
        r2.avatarEditor.setClickable(false);
        r2.avatarEditor.setPadding(AndroidUtilities.dp(0.0f), 0, 0, AndroidUtilities.dp(1.0f));
        RLottieImageView r39 = r2.avatarEditor;
        boolean r94 = LocaleController.isRTL;
        if (r94 == false) goto L32;
        int r102 = 5;
    L33:
        int r222 = r102 | 48;
        if (r94 == false) goto L36;
        float r232 = 0.0f;
    L37:
        if (r94 == false) goto L39;
        float r25 = 24.0f;
    L40:
        r7.addView(r39, LayoutHelper.createFrame(56, 56.0f, r222, r232, 20.0f, r25, 12.0f));
        AnonymousClass6 r310 = new AnonymousClass6(r2, r28);
        r2.avatarProgressView = r310;
        r310.setSize(AndroidUtilities.dp(30.0f));
        r2.avatarProgressView.setProgressColor(-1);
        r2.avatarProgressView.setNoProgress(false);
        RadialProgressView r311 = r2.avatarProgressView;
        boolean r95 = LocaleController.isRTL;
        if (r95 == false) goto L43;
        int r103 = 5;
    L44:
        int r223 = r103 | 48;
        if (r95 == false) goto L47;
        float r233 = 0.0f;
    L48:
        if (r95 == false) goto L50;
        float r252 = 24.0f;
    L51:
        r7.addView(r311, LayoutHelper.createFrame(56, 56.0f, r223, r233, 20.0f, r252, 12.0f));
        r2.showAvatarProgress(false, false);
        EditTextEmoji r05 = new EditTextEmoji(r28, r04, this, 0, false);
        r2 = this;
        r2.nameTextView = r05;
        r05.setHint(LocaleController.getString(R.string.EnterChannelName));
        String r06 = r2.nameToSet;
        if (r06 == null) goto L54;
        r2.nameTextView.setText(r06);
        r2.nameToSet = null;
    L54:
        r2.nameTextView.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(100)});
        r2.nameTextView.getEditText().setSingleLine(true);
        r2.nameTextView.getEditText().setImeOptions(5);
        r2.nameTextView.getEditText().setOnEditorActionListener(new ChannelCreateActivity$$ExternalSyntheticLambda7(r2));
        EditTextEmoji r07 = r2.nameTextView;
        boolean r312 = LocaleController.isRTL;
        if (r312 == false) goto L57;
        float r213 = 24.0f;
    L58:
        if (r312 == false) goto L60;
        float r234 = 96.0f;
    L61:
        r7.addView(r07, LayoutHelper.createFrame(-1, -2.0f, 16, r213, 0.0f, r234, 0.0f));
        OutlineTextContainerView r08 = new OutlineTextContainerView(r28);
        r2.descriptionFieldContainer = r08;
        r08.setText(LocaleController.getString(R.string.DescriptionPlaceholder));
        r2.linearLayout.addView(r2.descriptionFieldContainer, LayoutHelper.createLinear(-1, -2, 1, 24, 14, 24, 0));
        EditTextBoldCursor r09 = new EditTextBoldCursor(r28);
        r2.descriptionTextView = r09;
        r09.setTextSize(1, 18.0f);
        EditTextBoldCursor r010 = r2.descriptionTextView;
        int r313 = Theme.key_windowBackgroundWhiteBlackText;
        r010.setTextColor(Theme.getColor(r313));
        r2.descriptionTextView.setBackground(null);
        int r011 = AndroidUtilities.dp(16.0f);
        r2.descriptionTextView.setPadding(r011, r011, r011, r011);
        EditTextBoldCursor r012 = r2.descriptionTextView;
        if (LocaleController.isRTL == false) goto L64;
        int r42 = 5;
    L65:
        r012.setGravity(r42);
        r2.descriptionTextView.setInputType(180225);
        r2.descriptionTextView.setImeOptions(6);
        r2.descriptionTextView.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(120)});
        r2.descriptionTextView.setCursorColor(Theme.getColor(r313));
        r2.descriptionTextView.setCursorSize(AndroidUtilities.dp(20.0f));
        r2.descriptionTextView.setCursorWidth(1.5f);
        r2.descriptionTextView.setOnFocusChangeListener(new ChannelCreateActivity$$ExternalSyntheticLambda8(r2));
        r2.descriptionFieldContainer.addView(r2.descriptionTextView, LayoutHelper.createFrame(-1, -2.0f));
        r2.descriptionFieldContainer.attachEditText(r2.descriptionTextView);
        r2.descriptionTextView.setOnEditorActionListener(new ChannelCreateActivity$$ExternalSyntheticLambda9(r2));
        r2.descriptionTextView.addTextChangedListener(new AnonymousClass7(r2));
        TextView r013 = new TextView(r28);
        r2.helpTextView = r013;
        r013.setTextSize(1, 15.0f);
        r2.helpTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText8));
        TextView r014 = r2.helpTextView;
        if (LocaleController.isRTL == false) goto L68;
        int r1 = 5;
    L69:
        r014.setGravity(r1);
        r2.helpTextView.setText(LocaleController.getString(R.string.DescriptionInfo));
        LinearLayout r015 = r2.linearLayout;
        TextView r12 = r2.helpTextView;
        if (LocaleController.isRTL == false) goto L72;
        int r6 = 5;
    L73:
        r015.addView(r12, LayoutHelper.createLinear(-2, -2, r6, 24, 10, 24, 20));
    L132:
        return r2.fragmentView;
    L72:
        r6 = 3;
        goto L73
    L68:
        r1 = 3;
        goto L69
    L64:
        r42 = 3;
        goto L65
    L60:
        r234 = 24.0f;
        goto L61
    L57:
        r213 = 96.0f;
        goto L58
    L50:
        r252 = 0.0f;
        goto L51
    L47:
        r233 = 24.0f;
        goto L48
    L43:
        r103 = 3;
        goto L44
    L39:
        r25 = 0.0f;
        goto L40
    L36:
        r232 = 24.0f;
        goto L37
    L32:
        r102 = 3;
        goto L33
    L28:
        r24 = 0.0f;
        goto L29
    L25:
        r22 = 24.0f;
        goto L26
    L21:
        r18 = 3;
        goto L22
    L17:
        r23 = 0.0f;
        goto L18
    L14:
        r21 = 24.0f;
        goto L15
    L10:
        r10 = 3;
        goto L11
    L75:
        if (r03 != 1) goto L132;
        ScrollView r016 = new ScrollView(r28);
        r2.fragmentView = r016;
        r016.setFillViewport(true);
        LinearLayout r314 = new LinearLayout(r28);
        r2.linearLayout = r314;
        r314.setOrientation(1);
        r016.addView(r2.linearLayout, new FrameLayout.LayoutParams(-1, -2));
        TLRPC.Chat r017 = r2.getMessagesController().getChat(Long.valueOf(r2.chatId));
        if (r017 != null) goto L79;
    L83:
        boolean r018 = false;
    L84:
        r2.isGroup = r018;
        ActionBar r315 = r2.actionBar;
        if (r018 == false) goto L88;
        int r019 = R.string.GroupSettingsTitle;
    L89:
        r315.setTitle(LocaleController.getString(r019));
        View r020 = r2.fragmentView;
        int r316 = Theme.key_windowBackgroundGray;
        r020.setTag(Integer.valueOf(r316));
        r2.fragmentView.setBackgroundColor(Theme.getColor(r316));
        HeaderCell r021 = new HeaderCell(r28, 23);
        r2.headerCell2 = r021;
        r021.setHeight(46);
        HeaderCell r022 = r2.headerCell2;
        int r104 = Theme.key_windowBackgroundWhite;
        r022.setBackgroundColor(Theme.getColor(r104));
        HeaderCell r023 = r2.headerCell2;
        if (r2.isGroup == false) goto L93;
        int r317 = R.string.GroupTypeHeader;
    L94:
        r023.setText(LocaleController.getString(r317));
        r2.linearLayout.addView(r2.headerCell2);
        LinearLayout r024 = new LinearLayout(r28);
        r2.linearLayout2 = r024;
        r024.setOrientation(1);
        r2.linearLayout2.setBackgroundColor(Theme.getColor(r104));
        r2.linearLayout.addView(r2.linearLayout2, LayoutHelper.createLinear(-1, -2));
        RadioButtonCell r025 = new RadioButtonCell(r28);
        r2.radioButtonCell1 = r025;
        r025.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        Boolean r026 = r2.forcePublic;
        if (r026 != null) goto L97;
    L99:
        boolean r027 = r2.isGroup;
        RadioButtonCell r318 = r2.radioButtonCell1;
        if (r027 == false) goto L102;
        r318.setTextAndValue(LocaleController.getString(R.string.MegaPublic), LocaleController.getString(R.string.MegaPublicInfo), false, !r2.isPrivate);
    L103:
        r2.radioButtonCell1.setOnClickListener(new ChannelCreateActivity$$ExternalSyntheticLambda10(r2));
        Boolean r028 = r2.forcePublic;
        if (r028 != null) goto L106;
    L107:
        r2.linearLayout2.addView(r2.radioButtonCell1, LayoutHelper.createLinear(-1, -2));
    L108:
        RadioButtonCell r029 = new RadioButtonCell(r28);
        r2.radioButtonCell2 = r029;
        r029.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        Boolean r030 = r2.forcePublic;
        if (r030 != null) goto L111;
    L113:
        boolean r031 = r2.isGroup;
        RadioButtonCell r319 = r2.radioButtonCell2;
        if (r031 == false) goto L116;
        r319.setTextAndValue(LocaleController.getString(R.string.MegaPrivate), LocaleController.getString(R.string.MegaPrivateInfo), false, r2.isPrivate);
    L117:
        r2.radioButtonCell2.setOnClickListener(new ChannelCreateActivity$$ExternalSyntheticLambda11(r2));
        Boolean r032 = r2.forcePublic;
        if (r032 != null) goto L120;
    L121:
        r2.linearLayout2.addView(r2.radioButtonCell2, LayoutHelper.createLinear(-1, -2));
    L122:
        ShadowSectionCell r033 = new ShadowSectionCell(r28);
        r2.sectionCell = r033;
        r2.linearLayout.addView(r033, LayoutHelper.createLinear(-1, -2));
        LinearLayout r034 = new LinearLayout(r28);
        r2.linkContainer = r034;
        r034.setOrientation(1);
        r2.linkContainer.setBackgroundColor(Theme.getColor(r104));
        r2.linearLayout.addView(r2.linkContainer, LayoutHelper.createLinear(-1, -2));
        HeaderCell r035 = new HeaderCell(r28);
        r2.headerCell = r035;
        r2.linkContainer.addView(r035);
        LinearLayout r036 = new LinearLayout(r28);
        r2.publicContainer = r036;
        r036.setOrientation(0);
        r2.linkContainer.addView(r2.publicContainer, LayoutHelper.createLinear(-1, 36, 21.0f, 7.0f, 21.0f, 0.0f));
        EditTextBoldCursor r037 = new EditTextBoldCursor(r28);
        r2.editText = r037;
        r037.setText(MessagesController.getInstance(r2.currentAccount).linkPrefix + "/");
        r2.editText.setTextSize(1, 18.0f);
        EditTextBoldCursor r038 = r2.editText;
        int r320 = Theme.key_windowBackgroundWhiteHintText;
        r038.setHintTextColor(Theme.getColor(r320));
        EditTextBoldCursor r039 = r2.editText;
        int r43 = Theme.key_windowBackgroundWhiteBlackText;
        r039.setTextColor(Theme.getColor(r43));
        r2.editText.setMaxLines(1);
        r2.editText.setLines(1);
        r2.editText.setEnabled(false);
        r2.editText.setBackground(null);
        r2.editText.setPadding(0, 0, 0, 0);
        r2.editText.setSingleLine(true);
        r2.editText.setInputType(163840);
        r2.editText.setImeOptions(6);
        r2.publicContainer.addView(r2.editText, LayoutHelper.createLinear(-2, 36));
        EditTextBoldCursor r040 = new EditTextBoldCursor(r28);
        r2.descriptionTextView = r040;
        r040.setTextSize(1, 18.0f);
        r2.descriptionTextView.setHintTextColor(Theme.getColor(r320));
        r2.descriptionTextView.setTextColor(Theme.getColor(r43));
        r2.descriptionTextView.setMaxLines(1);
        r2.descriptionTextView.setLines(1);
        r2.descriptionTextView.setBackground(null);
        r2.descriptionTextView.setPadding(0, 0, 0, 0);
        r2.descriptionTextView.setSingleLine(true);
        r2.descriptionTextView.setInputType(163872);
        r2.descriptionTextView.setImeOptions(6);
        r2.descriptionTextView.setHint(LocaleController.getString(R.string.ChannelUsernamePlaceholder));
        r2.descriptionTextView.setCursorColor(Theme.getColor(r43));
        r2.descriptionTextView.setCursorSize(AndroidUtilities.dp(20.0f));
        r2.descriptionTextView.setCursorWidth(1.5f);
        r2.publicContainer.addView(r2.descriptionTextView, LayoutHelper.createLinear(-1, 36));
        r2.descriptionTextView.addTextChangedListener(new AnonymousClass8(r2));
        LinearLayout r041 = new LinearLayout(r28);
        r2.privateContainer = r041;
        r041.setOrientation(1);
        r2.linkContainer.addView(r2.privateContainer, LayoutHelper.createLinear(-1, -2));
        LinkActionView r042 = new LinkActionView(r28, r2, null, r2.chatId, true, ChatObject.isChannel(r2.getMessagesController().getChat(Long.valueOf(r2.chatId))));
        r2.permanentLinkView = r042;
        r042.hideRevokeOption(true);
        r2.permanentLinkView.setUsers(0, null);
        r2.privateContainer.addView(r2.permanentLinkView);
        AnonymousClass9 r043 = new AnonymousClass9(r2, r28);
        r2.checkTextView = r043;
        r043.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        r2.checkTextView.setHighlightColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkSelection));
        r2.checkTextView.setTextSize(1, 15.0f);
        TextView r044 = r2.checkTextView;
        if (LocaleController.isRTL == false) goto L125;
        int r321 = 5;
    L126:
        r044.setGravity(r321);
        r2.checkTextView.setVisibility(8);
        r2.checkTextView.setPadding(AndroidUtilities.dp(3.0f), 0, AndroidUtilities.dp(3.0f), 0);
        LinearLayout r045 = r2.linkContainer;
        TextView r322 = r2.checkTextView;
        if (LocaleController.isRTL == false) goto L129;
        int r224 = 5;
    L130:
        r045.addView(r322, LayoutHelper.createLinear(-2, -2, r224, 18, 3, 18, 7));
        TextInfoPrivacyCell r046 = new TextInfoPrivacyCell(r28);
        r2.typeInfoCell = r046;
        int r323 = R.drawable.greydivider_bottom;
        int r44 = Theme.key_windowBackgroundGrayShadow;
        r046.setBackgroundDrawable(Theme.getThemedDrawableByKey(r28, r323, r44));
        r2.linearLayout.addView(r2.typeInfoCell, LayoutHelper.createLinear(-1, -2));
        LoadingCell r047 = new LoadingCell(r28);
        r2.loadingAdminedCell = r047;
        r2.linearLayout.addView(r047, LayoutHelper.createLinear(-1, -2));
        LinearLayout r048 = new LinearLayout(r28);
        r2.adminnedChannelsLayout = r048;
        r048.setBackgroundColor(Theme.getColor(r104));
        r2.adminnedChannelsLayout.setOrientation(1);
        r2.linearLayout.addView(r2.adminnedChannelsLayout, LayoutHelper.createLinear(-1, -2));
        TextInfoPrivacyCell r049 = new TextInfoPrivacyCell(r28);
        r2.adminedInfoCell = r049;
        r049.setBackgroundDrawable(Theme.getThemedDrawableByKey(r28, R.drawable.greydivider_bottom, r44));
        r2.linearLayout.addView(r2.adminedInfoCell, LayoutHelper.createLinear(-1, -2));
        r2.updatePrivatePublic();
        goto L132
    L129:
        r224 = 3;
        goto L130
    L125:
        r321 = 3;
        goto L126
    L120:
        if (r032.booleanValue() == true) goto L122;
    L116:
        r319.setTextAndValue(LocaleController.getString(R.string.ChannelPrivate), LocaleController.getString(R.string.ChannelPrivateInfo), false, r2.isPrivate);
        goto L117
    L111:
        if (r030.booleanValue() == false) goto L113;
        r2.isPrivate = false;
        goto L113
    L106:
        if (r028.booleanValue() == false) goto L108;
    L102:
        r318.setTextAndValue(LocaleController.getString(R.string.ChannelPublic), LocaleController.getString(R.string.ChannelPublicInfo), false, !r2.isPrivate);
        goto L103
    L97:
        if (r026.booleanValue() == true) goto L99;
        r2.isPrivate = true;
        goto L99
    L93:
        r317 = R.string.ChannelTypeHeader;
        goto L94
    L88:
        r019 = R.string.ChannelSettingsTitle;
        goto L89
    L79:
        if (ChatObject.isChannel(r017) == true) goto L81;
    L82:
        r018 = true;
        goto L84
    L81:
        if (ChatObject.isMegagroup(r017) == false) goto L83;
        goto L82
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r17, int r18, Object... r19) {
        if (r17 != NotificationCenter.chatDidFailCreate) goto L12;
        AlertDialog r0 = this.cancelDialog;
        if (r0 != null) goto L33;
    L10:
        updateDoneProgress(false);
        this.donePressed = false;
        return;
    L33:
        r0.dismiss();     // Catch: Exception -> L8
        this.cancelDialog = null;     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    L12:
        if (r17 != NotificationCenter.chatDidCreated) goto L36;
        AlertDialog r02 = this.cancelDialog;
        if (r02 != null) goto L31;
    L19:
        long r3 = ((Long) r19[0]).longValue();
        Bundle r03 = new Bundle();
        r03.putInt("step", 1);
        r03.putLong("chat_id", r3);
        r03.putBoolean("canCreatePublic", this.canCreatePublic);
        Boolean r2 = this.forcePublic;
        if (r2 == null) goto L23;
        r03.putBoolean("forcePublic", r2.booleanValue());
    L23:
        if (this.inputPhoto == null) goto L25;
    L28:
        MessagesController.getInstance(this.currentAccount).changeChatAvatar(r3, null, this.inputPhoto, this.inputVideo, this.inputEmojiMarkup, this.videoTimestamp, this.inputVideoPath, this.avatar, this.avatarBig, null);
    L29:
        ChannelCreateActivity r22 = new ChannelCreateActivity(r03);
        r22.setOnFinishListener(this.onFinishListener);
        presentFragment(r22, true);
        return;
    L25:
        if (this.inputVideo != null) goto L28;
        if (this.inputEmojiMarkup == null) goto L29;
    L31:
        r02.dismiss();     // Catch: Exception -> L17
        this.cancelDialog = null;     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L19
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didStartUpload(boolean r1, boolean r2) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(0.0f);
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didUploadPhoto(TLRPC.InputFile r5, TLRPC.InputFile r6, double r7, String r9, TLRPC.PhotoSize r10, TLRPC.PhotoSize r11, boolean r12, TLRPC.VideoSize r13) {
        AndroidUtilities.runOnUIThread(new ChannelCreateActivity$$ExternalSyntheticLambda12(this, r5, r6, r13, r9, r7, r11, r10));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void dismissCurrentDialog() {
        ImageUpdater r0 = this.imageUpdater;
        if (r0 != null) goto L5;
    L7:
        super.dismissCurrentDialog();
        return;
    L5:
        if (r0.dismissCurrentDialog(this.visibleDialog) == false) goto L7;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean dismissDialogOnPause(Dialog r2) {
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L7;
        if (r0.dismissDialogOnPause(r2) == true) goto L7;
        return false;
    L7:
        if (super.dismissDialogOnPause(r2) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public String getInitialSearchString() {
        return this.nameTextView.getText().toString();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ChannelCreateActivity$$ExternalSyntheticLambda0 r8 = new ChannelCreateActivity$$ExternalSyntheticLambda0(this);
        View r10 = this.fragmentView;
        int r11 = ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG;
        int r19 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r10, r11, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        EditTextEmoji r12 = this.nameTextView;
        int r13 = ThemeDescription.FLAG_TEXTCOLOR;
        int r28 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(r12, r13, null, null, null, null, r28));
        EditTextEmoji r2 = this.nameTextView;
        int r22 = ThemeDescription.FLAG_HINTTEXTCOLOR;
        int r16 = Theme.key_windowBackgroundWhiteHintText;
        r1.add(new ThemeDescription(r2, r22, null, null, null, null, r16));
        EditTextEmoji r23 = this.nameTextView;
        int r222 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r36 = Theme.key_windowBackgroundWhiteInputField;
        r1.add(new ThemeDescription(r23, r222, null, null, null, null, r36));
        EditTextEmoji r24 = this.nameTextView;
        int r223 = ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE;
        int r27 = Theme.key_windowBackgroundWhiteInputFieldActivated;
        r1.add(new ThemeDescription(r24, r223, null, null, null, null, r27));
        r1.add(new ThemeDescription(this.descriptionTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r28));
        r1.add(new ThemeDescription(this.descriptionTextView, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.descriptionTextView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r36));
        r1.add(new ThemeDescription(this.descriptionTextView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, r27));
        TextView r102 = this.helpTextView;
        int r112 = ThemeDescription.FLAG_TEXTCOLOR;
        int r162 = Theme.key_windowBackgroundWhiteGrayText8;
        r1.add(new ThemeDescription(r102, r112, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.linearLayout2, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r19));
        r1.add(new ThemeDescription(this.linkContainer, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r19));
        ShadowSectionCell r4 = this.sectionCell;
        int r224 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r163 = Theme.key_windowBackgroundGrayShadow;
        r1.add(new ThemeDescription(r4, r224, null, null, null, null, r163));
        int r17 = Theme.key_windowBackgroundWhiteBlueHeader;
        r1.add(new ThemeDescription(this.headerCell, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.headerCell2, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r28));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, r16));
        TextView r113 = this.checkTextView;
        int r122 = ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG;
        int r172 = Theme.key_text_RedRegular;
        r1.add(new ThemeDescription(r113, r122, null, null, null, null, r172));
        r1.add(new ThemeDescription(this.checkTextView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.checkTextView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, null, null, null, null, Theme.key_windowBackgroundWhiteGreenText));
        r1.add(new ThemeDescription(this.typeInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r163));
        r1.add(new ThemeDescription(this.typeInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.typeInfoCell, ThemeDescription.FLAG_CHECKTAG, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, r172));
        r1.add(new ThemeDescription(this.adminedInfoCell, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{TextInfoPrivacyCell.class}, null, null, null, r163));
        r1.add(new ThemeDescription(this.adminnedChannelsLayout, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r19));
        LinearLayout r14 = this.privateContainer;
        int r15 = ThemeDescription.FLAG_SELECTOR;
        int r232 = Theme.key_listSelector;
        r1.add(new ThemeDescription(r14, r15, null, null, null, null, r232));
        r1.add(new ThemeDescription(this.privateContainer, 0, new Class[]{TextBlockCell.class}, new String[]{"textView"}, null, null, null, r28));
        r1.add(new ThemeDescription(this.loadingAdminedCell, 0, new Class[]{LoadingCell.class}, new String[]{"progressBar"}, null, null, null, Theme.key_progressCircle));
        r1.add(new ThemeDescription(this.radioButtonCell1, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r232));
        int r173 = Theme.key_radioBackground;
        r1.add(new ThemeDescription(this.radioButtonCell1, ThemeDescription.FLAG_CHECKBOX, new Class[]{RadioButtonCell.class}, new String[]{"radioButton"}, null, null, null, r173));
        int r18 = Theme.key_radioBackgroundChecked;
        r1.add(new ThemeDescription(this.radioButtonCell1, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{RadioButtonCell.class}, new String[]{"radioButton"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.radioButtonCell1, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{RadioButtonCell.class}, new String[]{"textView"}, null, null, null, r28));
        int r182 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(this.radioButtonCell1, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{RadioButtonCell.class}, new String[]{"valueTextView"}, null, null, null, r182));
        r1.add(new ThemeDescription(this.radioButtonCell2, ThemeDescription.FLAG_SELECTOR, null, null, null, null, r232));
        r1.add(new ThemeDescription(this.radioButtonCell2, ThemeDescription.FLAG_CHECKBOX, new Class[]{RadioButtonCell.class}, new String[]{"radioButton"}, null, null, null, r173));
        r1.add(new ThemeDescription(this.radioButtonCell2, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{RadioButtonCell.class}, new String[]{"radioButton"}, null, null, null, r18));
        r1.add(new ThemeDescription(this.radioButtonCell2, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{RadioButtonCell.class}, new String[]{"textView"}, null, null, null, r28));
        r1.add(new ThemeDescription(this.radioButtonCell2, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{RadioButtonCell.class}, new String[]{"valueTextView"}, null, null, null, r182));
        r1.add(new ThemeDescription(this.adminnedChannelsLayout, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{AdminedChannelCell.class}, new String[]{"nameTextView"}, null, null, null, r28));
        int r174 = Theme.key_windowBackgroundWhiteGrayText;
        r1.add(new ThemeDescription(this.adminnedChannelsLayout, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{AdminedChannelCell.class}, new String[]{"statusTextView"}, null, null, null, r174));
        r1.add(new ThemeDescription(this.adminnedChannelsLayout, ThemeDescription.FLAG_LINKCOLOR, new Class[]{AdminedChannelCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        r1.add(new ThemeDescription(this.adminnedChannelsLayout, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{AdminedChannelCell.class}, new String[]{"deleteButton"}, null, null, null, r174));
        r1.add(new ThemeDescription(null, 0, null, null, Theme.avatarDrawables, r8, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r1, int r2, Intent r3) {
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L6;
        r0.onActivityResult(r1, r2, r3);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        EditTextEmoji r0 = this.nameTextView;
        if (r0 != null) goto L5;
    L10:
        return true;
    L5:
        if (r0.isPopupShowing() == false) goto L10;
        if (r3 == false) goto L11;
        this.nameTextView.hidePopup(true);
        return false;
    L11:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatDidCreated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatDidFailCreate);
        if (this.currentStep != 1) goto L5;
        generateLink();
    L5:
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L9;
        r0.parentFragment = this;
        r0.setDelegate(this);
    L9:
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.doneRequestId == null) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.doneRequestId.intValue(), true);
        this.doneRequestId = null;
    L5:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatDidCreated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatDidFailCreate);
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L8;
        r0.clear();
    L8:
        AndroidUtilities.removeAdjustResize(getParentActivity(), this.classGuid);
        EditTextEmoji r3 = this.nameTextView;
        if (r3 == null) goto L12;
        r3.onDestroy();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        EditTextEmoji r0 = this.nameTextView;
        if (r0 == null) goto L5;
        r0.onPause();
    L5:
        ImageUpdater r1 = this.imageUpdater;
        if (r1 == null) goto L9;
        r1.onPause();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L6;
        r0.onRequestPermissionsResultFragment(r1, r2, r3);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        EditTextEmoji r0 = this.nameTextView;
        if (r0 == null) goto L5;
        r0.onResume();
    L5:
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        ImageUpdater r2 = this.imageUpdater;
        if (r2 == null) goto L9;
        r2.onResume();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r1, boolean r2) {
        if (r1 == true) goto L4;
        return;
    L4:
        if (this.currentStep == 1) goto L8;
        this.nameTextView.requestFocus();
        this.nameTextView.openKeyboard();
        return;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void onUploadProgressChanged(float r1) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void restoreSelfArgs(Bundle r3) {
        if (this.currentStep != 0) goto L15;
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L7;
        r0.currentPicturePath = r3.getString("path");
    L7:
        String r32 = r3.getString("nameTextView");
        if (r32 == null) goto L16;
        EditTextEmoji r02 = this.nameTextView;
        if (r02 == null) goto L13;
        r02.setText(r32);
        return;
    L13:
        this.nameToSet = r32;
        return;
    L16:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void saveSelfArgs(Bundle r3) {
        if (this.currentStep != 0) goto L15;
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L9;
        String r02 = r0.currentPicturePath;
        if (r02 == null) goto L9;
        r3.putString("path", r02);
    L9:
        EditTextEmoji r2 = this.nameTextView;
        if (r2 == null) goto L16;
        String r22 = r2.getText().toString();
        if (r22.length() == 0) goto L17;
        r3.putString("nameTextView", r22);
        return;
    L17:
        return;
    L16:
        return;
    }

    public void setOnFinishListener(Utilities.Callback2<BaseFragment, Long> r1) {
        this.onFinishListener = r1;
    }
}
