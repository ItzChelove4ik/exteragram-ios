package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_chatlists;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CrossfadeDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FolderBottomSheet;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.QRCodeBottomSheet;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TextStyleSpan;
import org.telegram.ui.Components.spoilers.SpoilersTextView;

/* loaded from: classes6.dex */
public class FilterChatlistActivity extends BaseFragment {
    private ListAdapter adapter;
    private ArrayList<Long> allowedPeers;
    private int chatsEndRow;
    private int chatsHeaderRow;
    private int chatsSectionRow;
    private int chatsStartRow;
    private ActionBarMenuItem doneButton;
    private float doneButtonAlpha;
    private CrossfadeDrawable doneButtonDrawable;
    private ValueAnimator doneButtonDrawableAnimator;
    private Runnable enableDoneLoading;
    MessagesController.DialogFilter filter;
    private FolderBottomSheet.HeaderCell headerCountCell;
    private HintInnerCell hintCountCell;
    private int hintRow;
    TL_chatlists.TL_exportedChatlistInvite invite;
    private long lastClicked;
    private long lastClickedDialogId;
    private int linkHeaderRow;
    private int linkRow;
    private int linkSectionRow;
    private RecyclerListView listView;
    private Utilities.Callback<TL_chatlists.TL_exportedChatlistInvite> onDelete;
    private Utilities.Callback<TL_chatlists.TL_exportedChatlistInvite> onEdit;
    private ArrayList<Long> peers;
    private boolean peersChanged;
    private int rowsCount;
    private boolean saving;
    private int savingTitleReqId;
    private ArrayList<Long> selectedPeers;
    private int shiftDp;
    private boolean titleChanged;

    /* renamed from: org.telegram.ui.FilterChatlistActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ FilterChatlistActivity this$0;

        public AnonymousClass1(FilterChatlistActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 == (-1)) goto L5;
            if (r3 != 1) goto L18;
            float r32 = Math.abs(FilterChatlistActivity.m14727$$Nest$fgetdoneButtonAlpha(this.this$0) - 1.0f);
            FilterChatlistActivity r1 = this.this$0;
            if (r32 >= 0.1f) goto L14;
            FilterChatlistActivity.m14741$$Nest$msave(r1);
            return;
        L14:
            if (Math.abs(FilterChatlistActivity.m14727$$Nest$fgetdoneButtonAlpha(r1) - 0.5f) >= 0.1f) goto L19;
            FilterChatlistActivity.m14743$$Nest$mshakeHeader(this.this$0);
            return;
        L19:
            return;
        L18:
            return;
        L5:
            if (FilterChatlistActivity.m14739$$Nest$mcheckDiscard(this.this$0, true) == false) goto L17;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.FilterChatlistActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ FilterChatlistActivity this$0;

        public AnonymousClass2(FilterChatlistActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean requestFocus(int r1, Rect r2) {
            return false;
        }
    }

    public static class HintInnerCell extends FrameLayout {
        private RLottieImageView imageView;
        private SpoilersTextView subtitleTextView;

        public HintInnerCell(Context r9, int r10) {
            super(r9);
            RLottieImageView r0 = new RLottieImageView(r9);
            this.imageView = r0;
            r0.setAnimation(r10, 90, 90);
            this.imageView.setScaleType(ImageView.ScaleType.CENTER);
            this.imageView.playAnimation();
            this.imageView.setImportantForAccessibility(2);
            addView(this.imageView, LayoutHelper.createFrame(90, 90.0f, 49, 0.0f, 14.0f, 0.0f, 0.0f));
            SpoilersTextView r102 = new SpoilersTextView(r9);
            this.subtitleTextView = r102;
            r102.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
            this.subtitleTextView.setTextSize(1, 14.0f);
            this.subtitleTextView.setGravity(17);
            this.subtitleTextView.setLines(2);
            addView(this.subtitleTextView, LayoutHelper.createFrame(-1, -2.0f, 49, 40.0f, 121.0f, 40.0f, 24.0f));
        }

        public SpoilersTextView getSubtitleTextView() {
            return this.subtitleTextView;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void setText(CharSequence r2, boolean r3) {
            this.subtitleTextView.setText(r2);
            SpoilersTextView r1 = this.subtitleTextView;
            if (r3 == false) goto L5;
            int r22 = 26;
        L6:
            r1.cacheType = r22;
            return;
        L5:
            r22 = 0;
            goto L6
        }
    }

    public static class InviteLinkCell extends FrameLayout {
        private ActionBarPopupWindow actionBarPopupWindow;
        ButtonsBox buttonsBox;
        private float changeAlpha;
        private ValueAnimator changeAnimator;
        TextView copyButton;
        TextView generateButton;
        private String lastUrl;
        FrameLayout linkBox;
        ImageView optionsIcon;
        BaseFragment parentFragment;
        private float[] point;
        TextView shareButton;
        SimpleTextView spoilerTextView;
        SimpleTextView textView;

        /* renamed from: org.telegram.ui.FilterChatlistActivity$InviteLinkCell$1, reason: invalid class name */
        public class AnonymousClass1 extends TextView {
            final /* synthetic */ InviteLinkCell this$0;

            public AnonymousClass1(InviteLinkCell r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec((View.MeasureSpec.getSize(r2) - AndroidUtilities.dp(8.0f)) / 2, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(42.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.FilterChatlistActivity$InviteLinkCell$2, reason: invalid class name */
        public class AnonymousClass2 extends TextView {
            final /* synthetic */ InviteLinkCell this$0;

            public AnonymousClass2(InviteLinkCell r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec((View.MeasureSpec.getSize(r2) - AndroidUtilities.dp(8.0f)) / 2, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(42.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.FilterChatlistActivity$InviteLinkCell$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ InviteLinkCell this$0;
            final /* synthetic */ String val$url;

            public AnonymousClass3(InviteLinkCell r1, String r2) {
                this.this$0 = r1;
                this.val$url = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                String r42 = this.val$url;
                InviteLinkCell r0 = this.this$0;
                if (r42 != null) goto L6;
                r0.generateButton.setVisibility(0);
                this.this$0.optionsIcon.setVisibility(8);
                this.this$0.copyButton.setVisibility(8);
                this.this$0.shareButton.setVisibility(8);
                return;
            L6:
                r0.generateButton.setVisibility(8);
                this.this$0.optionsIcon.setVisibility(0);
                this.this$0.copyButton.setVisibility(0);
                this.this$0.shareButton.setVisibility(0);
            }
        }

        /* renamed from: org.telegram.ui.FilterChatlistActivity$InviteLinkCell$4, reason: invalid class name */
        public class AnonymousClass4 extends View {
            final /* synthetic */ InviteLinkCell this$0;
            final /* synthetic */ FrameLayout val$finalContainer;

            public AnonymousClass4(InviteLinkCell r1, Context r2, FrameLayout r3) {
                this.this$0 = r1;
                this.val$finalContainer = r3;
                super(r2);
            }

            @Override // android.view.View
            public void onDraw(Canvas r6) {
                r6.drawColor(855638016);
                InviteLinkCell r0 = this.this$0;
                InviteLinkCell.m14753$$Nest$mgetPointOnScreen(r0, r0.linkBox, this.val$finalContainer, InviteLinkCell.m14751$$Nest$fgetpoint(r0));
                r6.save();
                float r02 = ((View) this.this$0.linkBox.getParent()).getY() + this.this$0.linkBox.getY();
                if (r02 >= 1.0f) goto L5;
                r6.clipRect(0.0f, (InviteLinkCell.m14751$$Nest$fgetpoint(this.this$0)[1] - r02) + 1.0f, getMeasuredWidth(), getMeasuredHeight());
            L5:
                r6.translate(InviteLinkCell.m14751$$Nest$fgetpoint(this.this$0)[0], InviteLinkCell.m14751$$Nest$fgetpoint(this.this$0)[1]);
                this.this$0.linkBox.draw(r6);
                r6.restore();
            }
        }

        /* renamed from: org.telegram.ui.FilterChatlistActivity$InviteLinkCell$5, reason: invalid class name */
        public class AnonymousClass5 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ InviteLinkCell this$0;
            final /* synthetic */ View val$dimView;

            public AnonymousClass5(InviteLinkCell r1, View r2) {
                this.this$0 = r1;
                this.val$dimView = r2;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                this.val$dimView.invalidate();
                return true;
            }
        }

        /* renamed from: org.telegram.ui.FilterChatlistActivity$InviteLinkCell$6, reason: invalid class name */
        public class AnonymousClass6 implements PopupWindow.OnDismissListener {
            final /* synthetic */ InviteLinkCell this$0;
            final /* synthetic */ View val$dimView;
            final /* synthetic */ FrameLayout val$finalContainer;
            final /* synthetic */ ViewTreeObserver.OnPreDrawListener val$preDrawListener;

            /* renamed from: org.telegram.ui.FilterChatlistActivity$InviteLinkCell$6$1, reason: invalid class name */
            public class AnonymousClass1 extends AnimatorListenerAdapter {
                final /* synthetic */ AnonymousClass6 this$1;

                public AnonymousClass1(AnonymousClass6 r1) {
                    this.this$1 = r1;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r2) {
                    if (this.this$1.val$dimView.getParent() == null) goto L5;
                    AnonymousClass6 r22 = this.this$1;
                    r22.val$finalContainer.removeView(r22.val$dimView);
                L5:
                    this.this$1.val$finalContainer.getViewTreeObserver().removeOnPreDrawListener(this.this$1.val$preDrawListener);
                }
            }

            public AnonymousClass6(InviteLinkCell r1, View r2, FrameLayout r3, ViewTreeObserver.OnPreDrawListener r4) {
                this.this$0 = r1;
                this.val$dimView = r2;
                this.val$finalContainer = r3;
                this.val$preDrawListener = r4;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                InviteLinkCell.m14752$$Nest$fputactionBarPopupWindow(this.this$0, null);
                this.val$dimView.animate().cancel();
                this.val$dimView.animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass1(this));
            }
        }

        public class ButtonsBox extends FrameLayout {
            private Paint paint;
            private Path path;
            private float[] radii;
            private float t;
            final /* synthetic */ InviteLinkCell this$0;

            public ButtonsBox(InviteLinkCell r1, Context r2) {
                this.this$0 = r1;
                super(r2);
                this.paint = new Paint();
                this.radii = new float[8];
                this.path = new Path();
                setWillNotDraw(false);
                this.paint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
            }

            private void setRadii(float r2, float r3) {
                float[] r1 = this.radii;
                r1[7] = r2;
                r1[6] = r2;
                r1[1] = r2;
                r1[0] = r2;
                r1[5] = r3;
                r1[4] = r3;
                r1[3] = r3;
                r1[2] = r3;
            }

            @Override // android.view.View
            public void onDraw(Canvas r10) {
                super.onDraw(r10);
                float r0 = getMeasuredWidth() / 2.0f;
                this.path.rewind();
                RectF r1 = AndroidUtilities.rectTmp;
                r1.set(0.0f, 0.0f, r0 - AndroidUtilities.lerp(0, AndroidUtilities.dp(4.0f), this.t), getMeasuredHeight());
                setRadii(AndroidUtilities.dp(8.0f), AndroidUtilities.lerp(0, AndroidUtilities.dp(8.0f), this.t));
                Path r4 = this.path;
                float[] r7 = this.radii;
                Path.Direction r8 = Path.Direction.CW;
                r4.addRoundRect(r1, r7, r8);
                r10.drawPath(this.path, this.paint);
                this.path.rewind();
                r1.set(r0 + AndroidUtilities.lerp(0, AndroidUtilities.dp(4.0f), this.t), 0.0f, getMeasuredWidth(), getMeasuredHeight());
                setRadii(AndroidUtilities.lerp(0, AndroidUtilities.dp(8.0f), this.t), AndroidUtilities.dp(8.0f));
                this.path.addRoundRect(r1, this.radii, r8);
                r10.drawPath(this.path, this.paint);
            }

            public void setT(float r1) {
                this.t = r1;
                invalidate();
            }
        }

        public static /* synthetic */ void $r8$lambda$5Qfto6hCVNfSUQU0_EYm0rGPigc(InviteLinkCell r0, View r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$5wqwzaQYV8sa2PSJcEvhusUApOI(InviteLinkCell r0, View r1) {
            r0.lambda$new$5(r1);
        }

        /* renamed from: $r8$lambda$Dh-rb8hoqFflfS0qhEIz8ePIK1I, reason: not valid java name */
        public static /* synthetic */ void m14747$r8$lambda$Dhrb8hoqFflfS0qhEIz8ePIK1I(InviteLinkCell r0, View r1) {
            r0.lambda$options$8(r1);
        }

        /* renamed from: $r8$lambda$EynEGIOmOGZxRo0yUteXaoy-3XI, reason: not valid java name */
        public static /* synthetic */ void m14748$r8$lambda$EynEGIOmOGZxRo0yUteXaoy3XI(InviteLinkCell r0, View r1) {
            r0.lambda$new$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$OcgnyDfsUluLWvh4VKPbEen2kVs(InviteLinkCell r0, ValueAnimator r1) {
            r0.lambda$setLink$6(r1);
        }

        /* renamed from: $r8$lambda$VCoUX9zoMk-baLTLcvcTf91sEdc, reason: not valid java name */
        public static /* synthetic */ void m14749$r8$lambda$VCoUX9zoMkbaLTLcvcTf91sEdc(InviteLinkCell r0) {
            r0.lambda$new$1();
        }

        public static /* synthetic */ void $r8$lambda$aDr7HiPkxNoUOYpdNMtdMkBHBjk(InviteLinkCell r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$cP4XGoSv8Wua0pfpMaS0h2YLtxA(InviteLinkCell r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$gPr2KLDfRUdo_QmhxWj1X05Gn70(InviteLinkCell r0, View r1) {
            r0.lambda$options$9(r1);
        }

        public static /* synthetic */ void $r8$lambda$nGjIoteTExRBAemJtGSlMD8QacY(InviteLinkCell r0, KeyEvent r1) {
            r0.lambda$options$10(r1);
        }

        /* renamed from: $r8$lambda$nkJhyQVFsd3Zh7lCYI-VOIJBtmU, reason: not valid java name */
        public static /* synthetic */ void m14750$r8$lambda$nkJhyQVFsd3Zh7lCYIVOIJBtmU(InviteLinkCell r0, View r1) {
            r0.lambda$options$7(r1);
        }

        /* renamed from: -$$Nest$fgetpoint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float[] m14751$$Nest$fgetpoint(InviteLinkCell r0) {
            return r0.point;
        }

        /* renamed from: -$$Nest$fputactionBarPopupWindow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14752$$Nest$fputactionBarPopupWindow(InviteLinkCell r0, ActionBarPopupWindow r1) {
            r0.actionBarPopupWindow = r1;
        }

        /* renamed from: -$$Nest$mgetPointOnScreen, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14753$$Nest$mgetPointOnScreen(InviteLinkCell r0, FrameLayout r1, FrameLayout r2, float[] r3) {
            r0.getPointOnScreen(r1, r2, r3);
        }

        public InviteLinkCell(Context r19, BaseFragment r20) {
            super(r19);
            this.point = new float[2];
            this.parentFragment = r20;
            FrameLayout r3 = new FrameLayout(r19);
            this.linkBox = r3;
            int r5 = AndroidUtilities.dp(8.0f);
            int r6 = Theme.key_graySection;
            r3.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r5, Theme.getColor(r6), Theme.blendOver(Theme.getColor(r6), Theme.getColor(Theme.key_listSelector))));
            this.linkBox.setOnClickListener(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda0(this));
            addView(this.linkBox, LayoutHelper.createFrame(-1, 48.0f, 55, 22.0f, 9.0f, 22.0f, 0.0f));
            SimpleTextView r32 = new SimpleTextView(r19);
            this.spoilerTextView = r32;
            r32.setTextSize(16);
            SimpleTextView r33 = this.spoilerTextView;
            int r62 = Theme.key_windowBackgroundWhiteBlackText;
            r33.setTextColor(Theme.getColor(r62));
            SpannableString r34 = new SpannableString("t.me/folder/N3k/dImA/bIo");
            TextStyleSpan.TextStyleRun r7 = new TextStyleSpan.TextStyleRun();
            r7.flags |= 256;
            r34.setSpan(new TextStyleSpan(r7), 0, r34.length(), 33);
            this.spoilerTextView.setText(r34);
            this.spoilerTextView.setAlpha(1.0f);
            this.linkBox.addView(this.spoilerTextView, LayoutHelper.createFrame(-1, -2.0f, 23, 20.0f, 0.0f, 40.0f, 0.0f));
            SimpleTextView r72 = new SimpleTextView(r19);
            this.textView = r72;
            r72.setTextSize(16);
            this.textView.setTextColor(Theme.getColor(r62));
            this.textView.setText(r34);
            this.textView.setAlpha(0.0f);
            this.linkBox.addView(this.textView, LayoutHelper.createFrame(-1, -2.0f, 23, 20.0f, 0.0f, 40.0f, 0.0f));
            ImageView r35 = new ImageView(r19);
            this.optionsIcon = r35;
            r35.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_ab_other));
            this.optionsIcon.setScaleType(ImageView.ScaleType.CENTER);
            this.optionsIcon.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogTextGray3), PorterDuff.Mode.SRC_IN));
            this.optionsIcon.setAlpha(0.0f);
            this.optionsIcon.setVisibility(8);
            this.optionsIcon.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
            this.optionsIcon.setOnClickListener(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda1(this));
            this.linkBox.addView(this.optionsIcon, LayoutHelper.createFrame(40, 40.0f, 21, 4.0f, 4.0f, 4.0f, 4.0f));
            ButtonsBox r36 = new ButtonsBox(this, r19);
            this.buttonsBox = r36;
            addView(r36, LayoutHelper.createFrame(-1, 42.0f, 55, 22.0f, 69.0f, 22.0f, 0.0f));
            AnonymousClass1 r37 = new AnonymousClass1(this, r19);
            this.copyButton = r37;
            r37.setGravity(17);
            TextView r38 = this.copyButton;
            int r9 = Theme.key_featuredStickers_buttonText;
            r38.setTextColor(Theme.getColor(r9));
            this.copyButton.setBackground(Theme.createRadSelectorDrawable(822083583, 8, 8));
            this.copyButton.setTypeface(AndroidUtilities.bold());
            this.copyButton.setTextSize(14.0f);
            SpannableStringBuilder r39 = new SpannableStringBuilder();
            r39.append("..").setSpan(new ColoredImageSpan(ContextCompat.getDrawable(r19, R.drawable.msg_copy_filled)), 0, 1, 0);
            r39.setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(8.0f)), 1, 2, 0);
            r39.append(LocaleController.getString(R.string.LinkActionCopy));
            r39.append(".").setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(5.0f)), r39.length() - 1, r39.length(), 0);
            this.copyButton.setText(r39);
            this.copyButton.setOnClickListener(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda2(this));
            this.copyButton.setAlpha(0.0f);
            this.copyButton.setVisibility(8);
            this.buttonsBox.addView(this.copyButton, LayoutHelper.createFrame(-1, -1, 3));
            AnonymousClass2 r2 = new AnonymousClass2(this, r19);
            this.shareButton = r2;
            r2.setGravity(17);
            this.shareButton.setTextColor(Theme.getColor(r9));
            this.shareButton.setBackground(Theme.createRadSelectorDrawable(822083583, 8, 8));
            this.shareButton.setTypeface(AndroidUtilities.bold());
            this.shareButton.setTextSize(14.0f);
            SpannableStringBuilder r22 = new SpannableStringBuilder();
            r22.append("..").setSpan(new ColoredImageSpan(ContextCompat.getDrawable(r19, R.drawable.msg_share_filled)), 0, 1, 0);
            r22.setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(8.0f)), 1, 2, 0);
            r22.append(LocaleController.getString(R.string.LinkActionShare));
            r22.append(".").setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(5.0f)), r22.length() - 1, r22.length(), 0);
            this.shareButton.setText(r22);
            this.shareButton.setOnClickListener(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda3(this));
            this.shareButton.setAlpha(0.0f);
            this.shareButton.setVisibility(8);
            this.buttonsBox.addView(this.shareButton, LayoutHelper.createFrame(-1, -1, 5));
            TextView r23 = new TextView(r19);
            this.generateButton = r23;
            r23.setGravity(17);
            this.generateButton.setTextColor(Theme.getColor(r9));
            this.generateButton.setBackground(Theme.createRadSelectorDrawable(822083583, 8, 8));
            this.generateButton.setTypeface(AndroidUtilities.bold());
            this.generateButton.setTextSize(14.0f);
            this.generateButton.setText("Generate Invite Link");
            this.generateButton.setOnClickListener(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda4(this));
            this.generateButton.setAlpha(1.0f);
            this.generateButton.setVisibility(0);
            this.buttonsBox.addView(this.generateButton, LayoutHelper.createFrame(-1, -1.0f));
        }

        private void getPointOnScreen(FrameLayout r3, FrameLayout r4, float[] r5) {
            float r2 = 0.0f;
            View r0 = r3;
            float r32 = 0.0f;
        L3:
            if (r0 == r4) goto L13;
            r2 = r2 + r0.getY();
            r32 = r32 + r0.getX();
            if ((r0 instanceof ScrollView) == false) goto L8;
            r2 = r2 - r0.getScrollY();
        L8:
            if ((r0.getParent() instanceof View) == false) goto L13;
            r0 = (View) r0.getParent();
            if ((r0 instanceof ViewGroup) == true) goto L3;
            return;
        L13:
            r5[0] = r32 - r4.getPaddingLeft();
            r5[1] = r2 - r4.getPaddingTop();
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            copy();
        }

        private /* synthetic */ void lambda$new$1() {
            this.linkBox.getBackground().setState(new int[0]);
        }

        private /* synthetic */ void lambda$new$2(View r3) {
            if ((this.linkBox.getBackground() instanceof RippleDrawable) == false) goto L5;
            this.linkBox.getBackground().setState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled});
            postDelayed(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda5(this), 180);
        L5:
            options();
        }

        private /* synthetic */ void lambda$new$3(View r1) {
            copy();
        }

        private /* synthetic */ void lambda$new$4(View r1) {
            share();
        }

        private /* synthetic */ void lambda$new$5(View r1) {
            generate();
        }

        private /* synthetic */ void lambda$options$10(KeyEvent r3) {
            if (r3.getKeyCode() == 4) goto L5;
            return;
        L5:
            if (r3.getRepeatCount() == 0) goto L7;
            return;
        L7:
            if (this.actionBarPopupWindow.isShowing() == false) goto L12;
            this.actionBarPopupWindow.dismiss(true);
            return;
        }

        private /* synthetic */ void lambda$options$7(View r1) {
            ActionBarPopupWindow r12 = this.actionBarPopupWindow;
            if (r12 == null) goto L5;
            r12.dismiss();
        L5:
            editname();
        }

        private /* synthetic */ void lambda$options$8(View r1) {
            ActionBarPopupWindow r12 = this.actionBarPopupWindow;
            if (r12 == null) goto L5;
            r12.dismiss();
        L5:
            qrcode();
        }

        private /* synthetic */ void lambda$options$9(View r1) {
            ActionBarPopupWindow r12 = this.actionBarPopupWindow;
            if (r12 == null) goto L5;
            r12.dismiss();
        L5:
            deleteLink();
        }

        private /* synthetic */ void lambda$setLink$6(ValueAnimator r1) {
            this.changeAlpha = ((Float) r1.getAnimatedValue()).floatValue();
            updateChangeAlpha();
        }

        private void updateChangeAlpha() {
            this.buttonsBox.setT(this.changeAlpha);
            this.copyButton.setAlpha(this.changeAlpha);
            this.shareButton.setAlpha(this.changeAlpha);
            this.optionsIcon.setAlpha(this.changeAlpha);
            this.generateButton.setAlpha(1.0f - this.changeAlpha);
            this.textView.setAlpha(this.changeAlpha);
            this.spoilerTextView.setAlpha(1.0f - this.changeAlpha);
        }

        public void copy() {
            String r0 = this.lastUrl;
            if (r0 != null) goto L5;
            return;
        L5:
            AndroidUtilities.addToClipboard(r0);
            BulletinFactory.of(this.parentFragment).createCopyBulletin(LocaleController.getString(R.string.LinkCopied)).show();
        }

        public abstract void deleteLink();

        public abstract void editname();

        public void generate() {
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(127.0f), TLObject.FLAG_30));
        }

        public void options() {
            if (this.actionBarPopupWindow == null) goto L5;
            return;
        L5:
            if (this.lastUrl == null) goto L16;
            ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getContext());
            ActionBarMenuSubItem r1 = new ActionBarMenuSubItem(getContext(), true, false);
            r1.setTextAndIcon(LocaleController.getString(R.string.EditName), R.drawable.msg_edit);
            r0.addView(r1, LayoutHelper.createLinear(-1, 48));
            r1.setOnClickListener(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda7(this));
            ActionBarMenuSubItem r12 = new ActionBarMenuSubItem(getContext(), false, false);
            r12.setTextAndIcon(LocaleController.getString(R.string.GetQRCode), R.drawable.msg_qrcode);
            r0.addView(r12, LayoutHelper.createLinear(-1, 48));
            r12.setOnClickListener(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda8(this));
            ActionBarMenuSubItem r13 = new ActionBarMenuSubItem(getContext(), false, true);
            r13.setTextAndIcon(LocaleController.getString(R.string.DeleteLink), R.drawable.msg_delete);
            int r6 = Theme.key_text_RedRegular;
            r13.setColors(Theme.getColor(r6), Theme.getColor(r6));
            r13.setSelectorColor(Theme.multAlpha(Theme.getColor(r6), 0.12f));
            r13.setOnClickListener(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda9(this));
            r0.addView(r13, LayoutHelper.createLinear(-1, 48));
            FrameLayout r14 = this.parentFragment.getParentLayout().getOverlayContainerView();
            if (r14 == null) goto L15;
            getPointOnScreen(this.linkBox, r14, this.point);
            float r5 = this.point[1];
            AnonymousClass4 r62 = new AnonymousClass4(this, getContext(), r14);
            AnonymousClass5 r7 = new AnonymousClass5(this, r62);
            r14.getViewTreeObserver().addOnPreDrawListener(r7);
            r14.addView(r62, LayoutHelper.createFrame(-1, -1.0f));
            float r2 = 0.0f;
            r62.setAlpha(0.0f);
            r62.animate().alpha(1.0f).setDuration(150);
            r0.measure(View.MeasureSpec.makeMeasureSpec(r14.getMeasuredWidth(), 0), View.MeasureSpec.makeMeasureSpec(r14.getMeasuredHeight(), 0));
            ActionBarPopupWindow r8 = new ActionBarPopupWindow(r0, -2, -2);
            this.actionBarPopupWindow = r8;
            r8.setOnDismissListener(new AnonymousClass6(this, r62, r14, r7));
            this.actionBarPopupWindow.setOutsideTouchable(true);
            this.actionBarPopupWindow.setFocusable(true);
            this.actionBarPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.actionBarPopupWindow.setAnimationStyle(R.style.PopupContextAnimation);
            this.actionBarPopupWindow.setInputMethodMode(2);
            this.actionBarPopupWindow.setSoftInputMode(0);
            r0.setDispatchKeyEventListener(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda10(this));
            if (AndroidUtilities.isTablet() == false) goto L12;
            r5 = r5 + r14.getPaddingTop();
            r2 = 0.0f - r14.getPaddingLeft();
        L12:
            this.actionBarPopupWindow.showAtLocation(r14, 0, (int) ((((r14.getMeasuredWidth() - r0.getMeasuredWidth()) - AndroidUtilities.dp(16.0f)) + r14.getX()) + r2), (int) ((r5 + this.linkBox.getMeasuredHeight()) + r14.getY()));
            return;
        L15:
            return;
        }

        public void qrcode() {
            if (this.lastUrl != null) goto L5;
            return;
        L5:
            QRCodeBottomSheet r1 = new QRCodeBottomSheet(getContext(), LocaleController.getString(R.string.InviteByQRCode), this.lastUrl, LocaleController.getString(R.string.QRCodeLinkHelpFolder), false);
            r1.setCenterAnimation(R.raw.qr_code_logo);
            r1.show();
        }

        public void setLink(String r6, boolean r7) {
            this.lastUrl = r6;
            if (r6 != null) goto L5;
        L10:
            this.textView.setText(r6);
            float r1 = this.changeAlpha;
            if (r6 == null) goto L13;
            int r4 = 1;
        L15:
            if (r1 == r4) goto L34;
            ValueAnimator r12 = this.changeAnimator;
            if (r12 == null) goto L19;
            r12.cancel();
            this.changeAnimator = null;
        L19:
            float r13 = 0.0f;
            if (r7 == false) goto L26;
            this.generateButton.setVisibility(0);
            this.optionsIcon.setVisibility(0);
            this.copyButton.setVisibility(0);
            this.shareButton.setVisibility(0);
            float r72 = this.changeAlpha;
            if (r6 == null) goto L24;
            r13 = 1.0f;
        L24:
            ValueAnimator r73 = ValueAnimator.ofFloat(new float[]{r72, r13});
            this.changeAnimator = r73;
            r73.addUpdateListener(new FilterChatlistActivity$InviteLinkCell$$ExternalSyntheticLambda6(this));
            this.changeAnimator.addListener(new AnonymousClass3(this, r6));
            this.changeAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.changeAnimator.setDuration(320);
            this.changeAnimator.start();
            return;
        L26:
            if (r6 == null) goto L28;
            r13 = 1.0f;
        L28:
            this.changeAlpha = r13;
            updateChangeAlpha();
            TextView r74 = this.generateButton;
            if (r6 != null) goto L32;
            r74.setVisibility(0);
            this.optionsIcon.setVisibility(8);
            this.copyButton.setVisibility(8);
            this.shareButton.setVisibility(8);
            return;
        L32:
            r74.setVisibility(8);
            this.optionsIcon.setVisibility(0);
            this.copyButton.setVisibility(0);
            this.shareButton.setVisibility(0);
            return;
        L34:
            return;
        L13:
            r4 = 0;
            goto L15
        L5:
            if (r6.startsWith("http://") == false) goto L8;
            r6 = r6.substring(7);
        L8:
            if (r6.startsWith("https://") == false) goto L10;
            r6 = r6.substring(8);
            goto L10
        }

        public void share() {
            if (this.lastUrl != null) goto L10;
            return;
        L10:
            Intent r0 = new Intent("android.intent.action.SEND");     // Catch: Exception -> L7
            r0.setType("text/plain");     // Catch: Exception -> L7
            r0.putExtra("android.intent.extra.TEXT", this.lastUrl);     // Catch: Exception -> L7
            this.parentFragment.startActivityForResult(Intent.createChooser(r0, LocaleController.getString(R.string.InviteToGroupByLink)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L7
            return;
        L7:
            e = move-exception;
            FileLog.e(e);
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ FilterChatlistActivity this$0;

        /* renamed from: org.telegram.ui.FilterChatlistActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends InviteLinkCell {
            final /* synthetic */ ListAdapter this$1;

            /* renamed from: org.telegram.ui.FilterChatlistActivity$ListAdapter$1$1, reason: invalid class name and collision with other inner class name */
            public class C00651 implements TextWatcher {
                boolean ignoreTextChange;
                final /* synthetic */ AnonymousClass1 this$2;
                final /* synthetic */ EditTextBoldCursor val$editText;

                public C00651(AnonymousClass1 r1, EditTextBoldCursor r2) {
                    this.this$2 = r1;
                    this.val$editText = r2;
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable r3) {
                    if (this.ignoreTextChange == false) goto L6;
                    return;
                L6:
                    if (r3.length() <= 32) goto L14;
                    this.ignoreTextChange = true;
                    r3.delete(32, r3.length());
                    AndroidUtilities.shakeView(this.val$editText);
                    this.val$editText.performHapticFeedback(3, 2);     // Catch: Exception -> L11
                L9:
                    this.ignoreTextChange = false;
                    return;
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
                }
            }

            public static /* synthetic */ void $r8$lambda$5fICjZxpMZE5ABN6OdYgHvD6_SI(EditTextBoldCursor r0, DialogInterface r1) {
                AndroidUtilities.runOnUIThread(new FilterChatlistActivity$ListAdapter$1$$ExternalSyntheticLambda6(r0));
            }

            /* renamed from: $r8$lambda$7YDJLZtC9zlvS_dgDCiC9wZW-Sw, reason: not valid java name */
            public static /* synthetic */ boolean m14754$r8$lambda$7YDJLZtC9zlvS_dgDCiC9wZWSw(AlertDialog.Builder r0, TextView r1, int r2, KeyEvent r3) {
                AndroidUtilities.hideKeyboard(r1);
                r0.create().getButton(-1).callOnClick();
                return false;
            }

            /* renamed from: $r8$lambda$DTSavdvmb-Bt5wWLXoKGUdbeXlU, reason: not valid java name */
            public static /* synthetic */ void m14755$r8$lambda$DTSavdvmbBt5wWLXoKGUdbeXlU(AnonymousClass1 r0, EditTextBoldCursor r1, AlertDialog.Builder r2, AlertDialog r3, int r4) {
                r0.lambda$editname$6(r1, r2, r3, r4);
            }

            public static /* synthetic */ void $r8$lambda$OMUeedhUn3MJzL3eK6XyRJnyWH8(EditTextBoldCursor r0) {
                r0.requestFocus();
                AndroidUtilities.showKeyboard(r0);
            }

            /* renamed from: $r8$lambda$OPzVxP9Hev92SyZPCLM6l6-Y9-k, reason: not valid java name */
            public static /* synthetic */ void m14756$r8$lambda$OPzVxP9Hev92SyZPCLM6l6Y9k(EditTextBoldCursor r0, DialogInterface r1) {
                AndroidUtilities.hideKeyboard(r0);
            }

            /* renamed from: $r8$lambda$luiwMj-RLSEHZCn1j5wDDCostIk, reason: not valid java name */
            public static /* synthetic */ void m14757$r8$lambda$luiwMjRLSEHZCn1j5wDDCostIk(AnonymousClass1 r0, AlertDialog r1) {
                r0.lambda$deleteLink$2(r1);
            }

            public static /* synthetic */ void $r8$lambda$wmj4UTP4egC9fE98nDlSzTUkmjI(AnonymousClass1 r0, AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
                r0.lambda$deleteLink$3(r1, r2, r3);
            }

            public static /* synthetic */ void $r8$lambda$xOwuipkiTvKo9xUDTQkiUktcFMg(EditTextBoldCursor r0, AlertDialog r1, int r2) {
                AndroidUtilities.hideKeyboard(r0);
            }

            public AnonymousClass1(ListAdapter r1, Context r2, BaseFragment r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            private /* synthetic */ void lambda$deleteLink$2(AlertDialog r2) {
                r2.dismiss();
                if (FilterChatlistActivity.m14732$$Nest$fgetonDelete(this.this$1.this$0) == null) goto L5;
                FilterChatlistActivity.m14732$$Nest$fgetonDelete(this.this$1.this$0).run(this.this$1.this$0.invite);
            L5:
                this.this$1.this$0.finishFragment();
            }

            private /* synthetic */ void lambda$deleteLink$3(AlertDialog r1, TLObject r2, TLRPC.TL_error r3) {
                AndroidUtilities.runOnUIThread(new FilterChatlistActivity$ListAdapter$1$$ExternalSyntheticLambda7(this, r1));
            }

            private /* synthetic */ void lambda$editname$6(EditTextBoldCursor r1, AlertDialog.Builder r2, AlertDialog r3, int r4) {
                AndroidUtilities.hideKeyboard(r1);
                r2.getDismissRunnable().run();
                TL_chatlists.TL_exportedChatlistInvite r22 = this.this$1.this$0.invite;
                r22.title = r1.getText().toString();
                FilterChatlistActivity.m14738$$Nest$fputtitleChanged(this.this$1.this$0, true);
                FilterChatlistActivity.m14744$$Nest$mupdateActionBarTitle(this.this$1.this$0, true);
                FilterChatlistActivity.m14742$$Nest$msaveTitle(this.this$1.this$0);
            }

            @Override // org.telegram.ui.FilterChatlistActivity.InviteLinkCell
            public void deleteLink() {
                TL_chatlists.TL_chatlists_deleteExportedInvite r0 = new TL_chatlists.TL_chatlists_deleteExportedInvite();
                TL_chatlists.TL_inputChatlistDialogFilter r1 = new TL_chatlists.TL_inputChatlistDialogFilter();
                r0.chatlist = r1;
                FilterChatlistActivity r2 = this.this$1.this$0;
                r1.filter_id = r2.filter.id;
                r0.slug = FilterChatlistActivity.m14740$$Nest$mgetSlug(r2);
                AlertDialog r12 = new AlertDialog(getContext(), 3);
                r12.showDelayed(180);
                this.this$1.this$0.getConnectionsManager().sendRequest(r0, new FilterChatlistActivity$ListAdapter$1$$ExternalSyntheticLambda5(this, r12));
            }

            @Override // org.telegram.ui.FilterChatlistActivity.InviteLinkCell
            public void editname() {
                TL_chatlists.TL_exportedChatlistInvite r0 = this.this$1.this$0.invite;
                if (r0 != null) goto L5;
                return;
            L5:
                if (r0.url == null) goto L13;
                EditTextBoldCursor r02 = new EditTextBoldCursor(getContext());
                r02.setBackgroundDrawable(Theme.createEditTextDrawable(getContext(), true));
                AlertDialog.Builder r1 = new AlertDialog.Builder(getContext());
                r1.setDialogButtonColorKey(Theme.key_dialogButton);
                r1.setTitle(LocaleController.getString(R.string.FilterInviteEditName));
                r1.setNegativeButton(LocaleController.getString(R.string.Cancel), new FilterChatlistActivity$ListAdapter$1$$ExternalSyntheticLambda0(r02));
                LinearLayout r3 = new LinearLayout(getContext());
                r3.setOrientation(1);
                r1.setView(r3);
                r02.setTextSize(1, 16.0f);
                int r4 = Theme.key_dialogTextBlack;
                r02.setTextColor(Theme.getColor(r4));
                r02.setMaxLines(1);
                r02.setLines(1);
                r02.setInputType(16385);
                r02.setGravity(51);
                r02.setSingleLine(true);
                r02.setImeOptions(6);
                r02.setHint(this.this$1.this$0.filter.name);
                r02.setHintTextColor(Theme.getColor(Theme.key_dialogTextHint));
                r02.setCursorColor(Theme.getColor(r4));
                r02.setCursorSize(AndroidUtilities.dp(20.0f));
                r02.setCursorWidth(1.5f);
                r02.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
                r3.addView(r02, LayoutHelper.createLinear(-1, 36, 51, 24, 6, 24, 0));
                r02.setOnEditorActionListener(new FilterChatlistActivity$ListAdapter$1$$ExternalSyntheticLambda1(r1));
                r02.addTextChangedListener(new C00651(this, r02));
                if (TextUtils.isEmpty(this.this$1.this$0.invite.title) == true) goto L10;
                r02.setText(this.this$1.this$0.invite.title);
                r02.setSelection(r02.length());
            L10:
                r1.setPositiveButton(LocaleController.getString(R.string.Save), new FilterChatlistActivity$ListAdapter$1$$ExternalSyntheticLambda2(this, r02, r1));
                AlertDialog r13 = r1.create();
                r13.setOnShowListener(new FilterChatlistActivity$ListAdapter$1$$ExternalSyntheticLambda3(r02));
                r13.setOnDismissListener(new FilterChatlistActivity$ListAdapter$1$$ExternalSyntheticLambda4(r02));
                r13.show();
                r13.setTextColor(Theme.getColor(r4));
                r02.requestFocus();
                return;
            }
        }

        public ListAdapter(FilterChatlistActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return FilterChatlistActivity.m14734$$Nest$fgetrowsCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != 0) goto L6;
            return 0;
        L6:
            if (r3 != FilterChatlistActivity.m14725$$Nest$fgetchatsSectionRow(this.this$0)) goto L8;
            return 2;
        L8:
            if (r3 != FilterChatlistActivity.m14731$$Nest$fgetlinkSectionRow(this.this$0)) goto L11;
            return 2;
        L11:
            if (r3 != FilterChatlistActivity.m14730$$Nest$fgetlinkRow(this.this$0)) goto L15;
            return 3;
        L15:
            if (r3 < FilterChatlistActivity.m14726$$Nest$fgetchatsStartRow(this.this$0)) goto L21;
            if (r3 >= FilterChatlistActivity.m14723$$Nest$fgetchatsEndRow(this.this$0)) goto L21;
            return 4;
        L21:
            if (r3 != FilterChatlistActivity.m14724$$Nest$fgetchatsHeaderRow(this.this$0)) goto L23;
            return 5;
        L23:
            if (r3 == FilterChatlistActivity.m14729$$Nest$fgetlinkHeaderRow(this.this$0)) goto L30;
            return 0;
        L30:
            return 5;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 4) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            int r0 = r10.getItemViewType();
            if (r0 != 0) goto L7;
            FilterChatlistActivity.m14737$$Nest$fputhintCountCell(this.this$0, (HintInnerCell) r10.itemView);
            FilterChatlistActivity.m14746$$Nest$mupdateHintCell(this.this$0, false);
            return;
        L7:
            if (r0 != 2) goto L25;
            TextInfoPrivacyCell r102 = (TextInfoPrivacyCell) r10.itemView;
            Context r02 = this.this$0.getContext();
            if (r11 != FilterChatlistActivity.m14725$$Nest$fgetchatsSectionRow(this.this$0)) goto L11;
            int r2 = R.drawable.greydivider_bottom;
        L12:
            r102.setBackground(Theme.getThemedDrawableByKey(r02, r2, Theme.key_windowBackgroundGrayShadow));
            if (r11 != FilterChatlistActivity.m14725$$Nest$fgetchatsSectionRow(this.this$0)) goto L23;
            r102.setFixedSize(0);
            FilterChatlistActivity r9 = this.this$0;
            if (r9.invite != null) goto L17;
        L21:
            r102.setText(LocaleController.getString(R.string.FilterInviteHintNo));
            return;
        L17:
            if (FilterChatlistActivity.m14722$$Nest$fgetallowedPeers(r9).isEmpty() == true) goto L21;
            r102.setText(LocaleController.getString(R.string.FilterInviteHint));
            return;
        L23:
            r102.setFixedSize(12);
            return;
        L11:
            r2 = R.drawable.greydivider;
            goto L12
        L25:
            String r3 = null;
            if (r0 != 3) goto L34;
            InviteLinkCell r103 = (InviteLinkCell) r10.itemView;
            TL_chatlists.TL_exportedChatlistInvite r92 = this.this$0.invite;
            if (r92 == null) goto L31;
            r3 = r92.url;
        L31:
            r103.setLink(r3, false);
            return;
        L34:
            if (r0 != 4) goto L73;
            GroupCreateUserCell r104 = (GroupCreateUserCell) r10.itemView;
            Long r112 = (Long) FilterChatlistActivity.m14733$$Nest$fgetpeers(this.this$0).get(r11 - FilterChatlistActivity.m14726$$Nest$fgetchatsStartRow(this.this$0));
            long r4 = r112.longValue();
            FilterChatlistActivity r22 = this.this$0;
            if (r4 < 0) goto L41;
            TLRPC.User r03 = r22.getMessagesController().getUser(r112);
            if (r03 == null) goto L40;
            String r23 = UserObject.getUserName(r03);
            TLRPC.Chat r04 = r03;
        L56:
            if (FilterChatlistActivity.m14722$$Nest$fgetallowedPeers(this.this$0).contains(r112) == false) goto L58;
            r104.setForbiddenCheck(false);
            r104.setChecked(FilterChatlistActivity.m14735$$Nest$fgetselectedPeers(this.this$0).contains(r112), false);
        L70:
            r104.setTag(r112);
            r104.setObject(r04, r23, r3);
            return;
        L58:
            r104.setForbiddenCheck(true);
            r104.setChecked(false, false);
            if ((r04 instanceof TLRPC.User) == false) goto L65;
            if (((TLRPC.User) r04).bot == false) goto L63;
            r3 = LocaleController.getString(R.string.FilterInviteBot);
            goto L70
        L63:
            r3 = LocaleController.getString(R.string.FilterInviteUser);
            goto L70
        L65:
            if ((r04 instanceof TLRPC.Chat) == false) goto L70;
            if (ChatObject.isChannelAndNotMegaGroup(r04) == false) goto L69;
            r3 = LocaleController.getString(R.string.FilterInviteChannel);
            goto L70
        L69:
            r3 = LocaleController.getString(R.string.FilterInviteGroup);
            goto L70
        L40:
            r23 = null;
            r04 = r03;
            goto L56
        L41:
            TLRPC.Chat r05 = r22.getMessagesController().getChat(Long.valueOf(-r4));
            if (r05 == null) goto L53;
            r3 = r05.title;
            if (r05.participants_count == 0) goto L50;
            boolean r24 = ChatObject.isChannelAndNotMegaGroup(r05);
            int r42 = r05.participants_count;
            if (r24 == false) goto L48;
            String r25 = LocaleController.formatPluralStringComma("Subscribers", r42);
        L54:
            String r8 = r3;
            r3 = r25;
            r23 = r8;
            r04 = r05;
            goto L56
        L48:
            r25 = LocaleController.formatPluralStringComma("Members", r42);
            goto L54
        L50:
            if (ChatObject.isChannelAndNotMegaGroup(r05) == false) goto L52;
            r25 = LocaleController.getString("ChannelPublic");
            goto L54
        L52:
            r25 = LocaleController.getString("MegaPublic");
            goto L54
        L53:
            r25 = null;
            goto L54
        L73:
            if (r0 != 5) goto L90;
            FolderBottomSheet.HeaderCell r105 = (FolderBottomSheet.HeaderCell) r10.itemView;
            if (r105 != FilterChatlistActivity.m14728$$Nest$fgetheaderCountCell(this.this$0)) goto L78;
            FilterChatlistActivity.m14736$$Nest$fputheaderCountCell(this.this$0, null);
        L78:
            if (r11 != FilterChatlistActivity.m14729$$Nest$fgetlinkHeaderRow(this.this$0)) goto L81;
            r105.setText(LocaleController.getString(R.string.InviteLink), false);
            r105.setAction(_UrlKt.FRAGMENT_ENCODE_SET, null);
            return;
        L81:
            FilterChatlistActivity.m14736$$Nest$fputheaderCountCell(this.this$0, r105);
            FilterChatlistActivity r113 = this.this$0;
            if (r113.invite != null) goto L84;
        L88:
            r105.setText(LocaleController.getString(R.string.FilterInviteHeaderChatsNo), false);
            r105.setAction(_UrlKt.FRAGMENT_ENCODE_SET, null);
            return;
        L84:
            if (FilterChatlistActivity.m14722$$Nest$fgetallowedPeers(r113).isEmpty() == true) goto L88;
            FilterChatlistActivity.m14745$$Nest$mupdateHeaderCell(this.this$0, false);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L5;
            View r22 = new HintInnerCell(this.this$0.getContext(), R.raw.folder_share);
        L18:
            return new RecyclerListView.Holder(r22);
        L5:
            if (r3 != 2) goto L8;
            r22 = new TextInfoPrivacyCell(this.this$0.getContext());
            goto L18
        L8:
            if (r3 != 3) goto L11;
            r22 = new AnonymousClass1(this, this.this$0.getContext(), this.this$0);
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L18
        L11:
            if (r3 != 4) goto L14;
            r22 = new GroupCreateUserCell(this.this$0.getContext(), 1, 0, false);
            r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L18
        L14:
            if (r3 != 5) goto L16;
            r22 = new FolderBottomSheet.HeaderCell(this.this$0.getContext());
            r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L18
        L16:
            r22 = null;
            goto L18
        }
    }

    public static /* synthetic */ void $r8$lambda$C0q2qnooAooQFm1HV_YMLS3a8K8(FilterChatlistActivity r0, boolean r1) {
        r0.lambda$updateHeaderCell$5(r1);
    }

    /* renamed from: $r8$lambda$G6bf4gqRQ-igrd_Ym7922R6mqbs, reason: not valid java name */
    public static /* synthetic */ void m14716$r8$lambda$G6bf4gqRQigrd_Ym7922R6mqbs(FilterChatlistActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Gtw3YwBbK5Snc1sY_IQ9Vvoqae0(FilterChatlistActivity r0, TLRPC.TL_error r1) {
        r0.lambda$saveTitle$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$UQ3kPql6Q2iMbBK41livF9nsDoA(FilterChatlistActivity r0, FolderBottomSheet.HeaderCell r1, boolean r2) {
        r0.lambda$deselectAll$6(r1, r2);
    }

    /* renamed from: $r8$lambda$X5qsVXwpe-I3ZM3aSCZSnzXXm24, reason: not valid java name */
    public static /* synthetic */ void m14717$r8$lambda$X5qsVXwpeI3ZM3aSCZSnzXXm24(FilterChatlistActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$saveTitle$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$YZ6LoVJ_Q1rwMQzHTnl3E3TAKZ8(FilterChatlistActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkDiscard$10(r1, r2);
    }

    /* renamed from: $r8$lambda$o2k5g2WvsFma4jEiKuXAZo6-FLM, reason: not valid java name */
    public static /* synthetic */ void m14718$r8$lambda$o2k5g2WvsFma4jEiKuXAZo6FLM(FilterChatlistActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$save$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$pcx9l8DU1Pq2l9CSrWln3kxWBlI(FilterChatlistActivity r0, ValueAnimator r1) {
        r0.lambda$updateDoneProgress$8(r1);
    }

    /* renamed from: $r8$lambda$pfnoQvr-Zd01Lngaubs5FDFbN5g, reason: not valid java name */
    public static /* synthetic */ void m14719$r8$lambda$pfnoQvrZd01Lngaubs5FDFbN5g(FilterChatlistActivity r0, TLRPC.TL_error r1) {
        r0.lambda$save$1(r1);
    }

    /* renamed from: $r8$lambda$uQVsNDh-wMJBbEr2daxPVay_pmQ, reason: not valid java name */
    public static /* synthetic */ void m14720$r8$lambda$uQVsNDhwMJBbEr2daxPVay_pmQ(FilterChatlistActivity r0) {
        r0.lambda$new$7();
    }

    /* renamed from: $r8$lambda$yR5j-SQvpijXN9VAbwI3rU-Escs, reason: not valid java name */
    public static /* synthetic */ void m14721$r8$lambda$yR5jSQvpijXN9VAbwI3rUEscs(FilterChatlistActivity r0, View r1, int r2) {
        r0.lambda$createView$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetallowedPeers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m14722$$Nest$fgetallowedPeers(FilterChatlistActivity r0) {
        return r0.allowedPeers;
    }

    /* renamed from: -$$Nest$fgetchatsEndRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14723$$Nest$fgetchatsEndRow(FilterChatlistActivity r0) {
        return r0.chatsEndRow;
    }

    /* renamed from: -$$Nest$fgetchatsHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14724$$Nest$fgetchatsHeaderRow(FilterChatlistActivity r0) {
        return r0.chatsHeaderRow;
    }

    /* renamed from: -$$Nest$fgetchatsSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14725$$Nest$fgetchatsSectionRow(FilterChatlistActivity r0) {
        return r0.chatsSectionRow;
    }

    /* renamed from: -$$Nest$fgetchatsStartRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14726$$Nest$fgetchatsStartRow(FilterChatlistActivity r0) {
        return r0.chatsStartRow;
    }

    /* renamed from: -$$Nest$fgetdoneButtonAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m14727$$Nest$fgetdoneButtonAlpha(FilterChatlistActivity r0) {
        return r0.doneButtonAlpha;
    }

    /* renamed from: -$$Nest$fgetheaderCountCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FolderBottomSheet.HeaderCell m14728$$Nest$fgetheaderCountCell(FilterChatlistActivity r0) {
        return r0.headerCountCell;
    }

    /* renamed from: -$$Nest$fgetlinkHeaderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14729$$Nest$fgetlinkHeaderRow(FilterChatlistActivity r0) {
        return r0.linkHeaderRow;
    }

    /* renamed from: -$$Nest$fgetlinkRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14730$$Nest$fgetlinkRow(FilterChatlistActivity r0) {
        return r0.linkRow;
    }

    /* renamed from: -$$Nest$fgetlinkSectionRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14731$$Nest$fgetlinkSectionRow(FilterChatlistActivity r0) {
        return r0.linkSectionRow;
    }

    /* renamed from: -$$Nest$fgetonDelete, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback m14732$$Nest$fgetonDelete(FilterChatlistActivity r0) {
        return r0.onDelete;
    }

    /* renamed from: -$$Nest$fgetpeers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m14733$$Nest$fgetpeers(FilterChatlistActivity r0) {
        return r0.peers;
    }

    /* renamed from: -$$Nest$fgetrowsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14734$$Nest$fgetrowsCount(FilterChatlistActivity r0) {
        return r0.rowsCount;
    }

    /* renamed from: -$$Nest$fgetselectedPeers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m14735$$Nest$fgetselectedPeers(FilterChatlistActivity r0) {
        return r0.selectedPeers;
    }

    /* renamed from: -$$Nest$fputheaderCountCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14736$$Nest$fputheaderCountCell(FilterChatlistActivity r0, FolderBottomSheet.HeaderCell r1) {
        r0.headerCountCell = r1;
    }

    /* renamed from: -$$Nest$fputhintCountCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14737$$Nest$fputhintCountCell(FilterChatlistActivity r0, HintInnerCell r1) {
        r0.hintCountCell = r1;
    }

    /* renamed from: -$$Nest$fputtitleChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14738$$Nest$fputtitleChanged(FilterChatlistActivity r0, boolean r1) {
        r0.titleChanged = r1;
    }

    /* renamed from: -$$Nest$mcheckDiscard, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14739$$Nest$mcheckDiscard(FilterChatlistActivity r0, boolean r1) {
        return r0.checkDiscard(r1);
    }

    /* renamed from: -$$Nest$mgetSlug, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m14740$$Nest$mgetSlug(FilterChatlistActivity r0) {
        return r0.getSlug();
    }

    /* renamed from: -$$Nest$msave, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14741$$Nest$msave(FilterChatlistActivity r0) {
        r0.save();
    }

    /* renamed from: -$$Nest$msaveTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14742$$Nest$msaveTitle(FilterChatlistActivity r0) {
        r0.saveTitle();
    }

    /* renamed from: -$$Nest$mshakeHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14743$$Nest$mshakeHeader(FilterChatlistActivity r0) {
        r0.shakeHeader();
    }

    /* renamed from: -$$Nest$mupdateActionBarTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14744$$Nest$mupdateActionBarTitle(FilterChatlistActivity r0, boolean r1) {
        r0.updateActionBarTitle(r1);
    }

    /* renamed from: -$$Nest$mupdateHeaderCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14745$$Nest$mupdateHeaderCell(FilterChatlistActivity r0, boolean r1) {
        r0.updateHeaderCell(r1);
    }

    /* renamed from: -$$Nest$mupdateHintCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14746$$Nest$mupdateHintCell(FilterChatlistActivity r0, boolean r1) {
        r0.updateHintCell(r1);
    }

    public FilterChatlistActivity(MessagesController.DialogFilter r2, TL_chatlists.TL_exportedChatlistInvite r3) {
        this.selectedPeers = new ArrayList();
        this.allowedPeers = new ArrayList();
        this.peers = new ArrayList();
        this.shiftDp = -5;
        this.saving = false;
        this.rowsCount = 0;
        this.hintRow = -1;
        this.linkRow = -1;
        this.linkHeaderRow = -1;
        this.linkSectionRow = -1;
        this.chatsHeaderRow = -1;
        this.chatsStartRow = -1;
        this.chatsEndRow = -1;
        this.chatsSectionRow = -1;
        this.enableDoneLoading = new FilterChatlistActivity$$ExternalSyntheticLambda2(this);
        this.doneButtonAlpha = 1.0f;
        this.filter = r2;
        this.invite = r3;
    }

    private boolean checkDiscard(boolean r3) {
        if (this.selectedPeers.isEmpty() == false) goto L6;
        return true;
    L6:
        if (this.peersChanged == false) goto L11;
        if (r3 == false) goto L12;
        AlertDialog.Builder r32 = new AlertDialog.Builder(getParentActivity());
        r32.setTitle(LocaleController.getString(R.string.UnsavedChanges));
        r32.setMessage(LocaleController.getString(R.string.UnsavedChangesMessage));
        r32.setPositiveButton(LocaleController.getString(R.string.ApplyTheme), new FilterChatlistActivity$$ExternalSyntheticLambda4(this));
        r32.setNegativeButton(LocaleController.getString(R.string.PassportDiscard), new FilterChatlistActivity$$ExternalSyntheticLambda5(this));
        showDialog(r32.create());
        return false;
    L12:
        return false;
    L11:
        return true;
    }

    private void checkDoneButton() {
        boolean r0 = this.peersChanged;
        boolean r1 = this.selectedPeers.isEmpty();
        if (r0 == false) goto L7;
        if (r1 == true) goto L6;
        float r02 = 1.0f;
    L9:
        if (Math.abs(this.doneButtonAlpha - r02) <= 0.1f) goto L12;
        this.doneButton.clearAnimation();
        ViewPropertyAnimator r12 = this.doneButton.animate();
        this.doneButtonAlpha = r02;
        r12.alpha(r02).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        return;
    L12:
        return;
    L6:
        r02 = 0.5f;
        goto L9
    L7:
        r02 = 0.0f;
        goto L9
    }

    private void checkPeersChanged() {
        TL_chatlists.TL_exportedChatlistInvite r0 = this.invite;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.url != null) goto L7;
        return;
    L7:
        if (this.peersChanged == false) goto L28;
        boolean r3 = true;
        if (this.selectedPeers.size() == this.invite.peers.size()) goto L11;
        boolean r02 = true;
    L12:
        if (r02 == true) goto L20;
        int r1 = 0;
    L15:
        if (r1 >= this.invite.peers.size()) goto L20;
        if (this.selectedPeers.contains(Long.valueOf(DialogObject.getPeerDialogId(this.invite.peers.get(r1)))) == false) goto L21;
        r1 = r1 + 1;
    L21:
        if (r3 == true) goto L29;
        this.peersChanged = false;
        checkDoneButton();
        return;
    L29:
        return;
    L20:
        r3 = r02;
        goto L21
    L11:
        r02 = false;
        goto L12
    }

    private void deselectAll(FolderBottomSheet.HeaderCell r6, boolean r7) {
        this.selectedPeers.clear();
        int r0 = 0;
        if (r7 == true) goto L6;
        this.selectedPeers.addAll(this.allowedPeers.subList(0, Math.min(getMaxChats(), this.allowedPeers.size())));
    L6:
        if (this.selectedPeers.size() < Math.min(getMaxChats(), this.allowedPeers.size())) goto L9;
        int r1 = R.string.DeselectAll;
    L8:
        String r12 = LocaleController.getString(r1);
        r6.setAction(r12, new FilterChatlistActivity$$ExternalSyntheticLambda9(this, r6, r7));
        this.peersChanged = true;
        checkPeersChanged();
        checkDoneButton();
        updateHeaderCell(true);
        updateHintCell(true);
    L12:
        if (r0 >= this.listView.getChildCount()) goto L19;
        View r72 = this.listView.getChildAt(r0);
        if ((r72 instanceof GroupCreateUserCell) == false) goto L18;
        Object r13 = r72.getTag();
        if ((r13 instanceof Long) == false) goto L18;
        ((GroupCreateUserCell) r72).setChecked(this.selectedPeers.contains((Long) r13), true);
    L18:
        r0 = r0 + 1;
        goto L12
    L19:
        return;
    L9:
        r1 = R.string.SelectAll;
        goto L8
    }

    private int getMaxChats() {
        boolean r0 = getUserConfig().isPremium();
        MessagesController r1 = getMessagesController();
        if (r0 == false) goto L7;
        return r1.dialogFiltersChatsLimitPremium;
    L7:
        return r1.dialogFiltersChatsLimitDefault;
    }

    private String getSlug() {
        TL_chatlists.TL_exportedChatlistInvite r1 = this.invite;
        if (r1 == null) goto L9;
        String r12 = r1.url;
        if (r12 != null) goto L8;
        return null;
    L8:
        return r12.substring(r12.lastIndexOf(47) + 1);
    L9:
        return null;
    }

    private /* synthetic */ void lambda$checkDiscard$10(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$checkDiscard$9(AlertDialog r1, int r2) {
        save();
    }

    private /* synthetic */ void lambda$createView$0(View r11, int r12) {
        if (getParentActivity() != null) goto L6;
        return;
    L6:
        if ((r11 instanceof GroupCreateUserCell) == false) goto L44;
        Long r122 = this.peers.get(r12 - this.chatsStartRow);
        long r0 = r122.longValue();
        if (this.selectedPeers.contains(r122) == false) goto L11;
        this.selectedPeers.remove(r122);
        this.peersChanged = true;
        checkDoneButton();
        ((GroupCreateUserCell) r11).setChecked(false, true);
        FilterChatlistActivity r5 = this;
    L17:
        r5.checkPeersChanged();
        r5.updateHeaderCell(true);
        r5.updateHintCell(true);
        return;
    L11:
        if (this.allowedPeers.contains(r122) == true) goto L13;
        int r10 = -this.shiftDp;
        this.shiftDp = r10;
        AndroidUtilities.shakeViewSpring(r11, r10);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        ArrayList r102 = new ArrayList();
        if (r0 < 0) goto L27;
        r102.add(getMessagesController().getUser(r122));
        TLRPC.User r112 = getMessagesController().getUser(r122);
        if (r112 != null) goto L24;
    L26:
        String r113 = LocaleController.getString(R.string.FilterInviteUserToast);
    L39:
        if (this.lastClickedDialogId == r0) goto L41;
    L42:
        this.lastClickedDialogId = r0;
        this.lastClicked = System.currentTimeMillis();
        BulletinFactory.of(this).createChatsBulletin(r102, r113, null).show();
        return;
    L41:
        if ((System.currentTimeMillis() - this.lastClicked) > 1500) goto L42;
        return;
    L24:
        if (r112.bot == false) goto L26;
        r113 = LocaleController.getString(R.string.FilterInviteBotToast);
        goto L39
    L27:
        TLRPC.Chat r114 = getMessagesController().getChat(Long.valueOf(-r0));
        if (ChatObject.isChannelAndNotMegaGroup(r114) == false) goto L34;
        if (ChatObject.isPublic(r114) == false) goto L32;
        String r123 = LocaleController.getString(R.string.FilterInviteChannelToast);
    L37:
        r102.add(r114);
        r113 = r123;
        goto L39
    L32:
        r123 = LocaleController.getString(R.string.FilterInvitePrivateChannelToast);
        goto L37
    L34:
        if (ChatObject.isPublic(r114) == false) goto L36;
        r123 = LocaleController.getString(R.string.FilterInviteGroupToast);
        goto L37
    L36:
        r123 = LocaleController.getString(R.string.FilterInvitePrivateGroupToast);
        goto L37
    L13:
        if ((this.selectedPeers.size() + 1) <= getMaxChats()) goto L16;
        showDialog(new LimitReachedBottomSheet(this, getContext(), 4, this.currentAccount, null));
        return;
    L16:
        r5 = this;
        r5.selectedPeers.add(r122);
        r5.peersChanged = true;
        r5.checkDoneButton();
        ((GroupCreateUserCell) r11).setChecked(true, true);
        goto L17
    }

    private /* synthetic */ void lambda$deselectAll$6(FolderBottomSheet.HeaderCell r1, boolean r2) {
        deselectAll(r1, !r2);
    }

    private /* synthetic */ void lambda$new$7() {
        updateDoneProgress(true);
    }

    private /* synthetic */ void lambda$save$1(TLRPC.TL_error r14) {
        updateDoneProgress(false);
        this.saving = false;
        if (r14 == null) goto L9;
        if ("INVITES_TOO_MUCH".equals(r14.text) == false) goto L9;
        showDialog(new LimitReachedBottomSheet(this, getContext(), 12, this.currentAccount, null));
        return;
    L9:
        if (r14 != null) goto L11;
    L14:
        if (r14 != null) goto L16;
    L19:
        finishFragment();
        return;
    L16:
        if ("CHATLISTS_TOO_MUCH".equals(r14.text) == false) goto L19;
        showDialog(new LimitReachedBottomSheet(this, getContext(), 13, this.currentAccount, null));
        return;
    L11:
        if ("INVITE_PEERS_TOO_MUCH".equals(r14.text) == false) goto L14;
        showDialog(new LimitReachedBottomSheet(this, getContext(), 4, this.currentAccount, null));
    }

    private /* synthetic */ void lambda$save$2(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new FilterChatlistActivity$$ExternalSyntheticLambda10(this, r2));
    }

    private /* synthetic */ void lambda$saveTitle$3(TLRPC.TL_error r2) {
        this.savingTitleReqId = 0;
        if (r2 != null) goto L6;
        BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.FilterInviteNameEdited)).show();
        return;
    }

    private /* synthetic */ void lambda$saveTitle$4(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new FilterChatlistActivity$$ExternalSyntheticLambda1(this, r2));
    }

    private /* synthetic */ void lambda$updateDoneProgress$8(ValueAnimator r2) {
        this.doneButtonDrawable.setProgress(((Float) r2.getAnimatedValue()).floatValue());
        this.doneButtonDrawable.invalidateSelf();
    }

    private /* synthetic */ void lambda$updateHeaderCell$5(boolean r2) {
        deselectAll(this.headerCountCell, r2);
    }

    private void save() {
        if (this.invite != null) goto L5;
        return;
    L5:
        if (this.saving == false) goto L7;
        return;
    L7:
        if (this.peersChanged == false) goto L26;
        updateDoneProgress(true);
        this.saving = true;
        this.invite.peers.clear();
        int r0 = 0;
        int r1 = 0;
    L11:
        if (r1 >= this.selectedPeers.size()) goto L13;
        this.invite.peers.add(getMessagesController().getPeer(this.selectedPeers.get(r1).longValue()));
        r1 = r1 + 1;
        goto L11
    L13:
        TL_chatlists.TL_chatlists_editExportedInvite r12 = new TL_chatlists.TL_chatlists_editExportedInvite();
        TL_chatlists.TL_inputChatlistDialogFilter r2 = new TL_chatlists.TL_inputChatlistDialogFilter();
        r12.chatlist = r2;
        r2.filter_id = this.filter.id;
        r12.slug = getSlug();
        r12.revoked = this.invite.revoked;
        r12.flags |= 4;
    L15:
        if (r0 >= this.selectedPeers.size()) goto L17;
        r12.peers.add(getMessagesController().getInputPeer(this.selectedPeers.get(r0).longValue()));
        r0 = r0 + 1;
        goto L15
    L17:
        getConnectionsManager().sendRequest(r12, new FilterChatlistActivity$$ExternalSyntheticLambda8(this));
        Utilities.Callback<TL_chatlists.TL_exportedChatlistInvite> r02 = this.onEdit;
        if (r02 == null) goto L25;
        r02.run(this.invite);
        return;
    L25:
        return;
    }

    private void saveTitle() {
        if (this.savingTitleReqId == 0) goto L5;
        getConnectionsManager().cancelRequest(this.savingTitleReqId, true);
        this.savingTitleReqId = 0;
    L5:
        TL_chatlists.TL_chatlists_editExportedInvite r0 = new TL_chatlists.TL_chatlists_editExportedInvite();
        TL_chatlists.TL_inputChatlistDialogFilter r1 = new TL_chatlists.TL_inputChatlistDialogFilter();
        r0.chatlist = r1;
        r1.filter_id = this.filter.id;
        r0.slug = getSlug();
        TL_chatlists.TL_exportedChatlistInvite r12 = this.invite;
        r0.revoked = r12.revoked;
        r0.flags |= 2;
        r0.title = r12.title;
        this.savingTitleReqId = getConnectionsManager().sendRequest(r0, new FilterChatlistActivity$$ExternalSyntheticLambda0(this));
        Utilities.Callback<TL_chatlists.TL_exportedChatlistInvite> r02 = this.onEdit;
        if (r02 == null) goto L9;
        r02.run(this.invite);
        return;
    }

    private void shakeHeader() {
        int r0 = 0;
    L4:
        if (r0 >= this.listView.getChildCount()) goto L12;
        View r1 = this.listView.getChildAt(r0);
        if (this.listView.getChildAdapterPosition(r1) != this.chatsHeaderRow) goto L11;
        if ((r1 instanceof FolderBottomSheet.HeaderCell) == false) goto L11;
        int r02 = -this.shiftDp;
        this.shiftDp = r02;
        AndroidUtilities.shakeViewSpring(r1, r02);
        return;
    L11:
        r0 = r0 + 1;
        goto L4
    }

    private void updateActionBarTitle(boolean r4) {
        TL_chatlists.TL_exportedChatlistInvite r0 = this.invite;
        if (r0 != null) goto L5;
        String r02 = null;
    L7:
        if (TextUtils.isEmpty(r02) == false) goto L9;
        String r03 = LocaleController.getString(R.string.FilterShare);
    L10:
        ActionBar r3 = this.actionBar;
        if (r4 == false) goto L14;
        r3.setTitleAnimated(r03, false, 220);
        return;
    L14:
        r3.setTitle(r03);
        return;
    L9:
        r03 = this.invite.title;
        goto L10
    L5:
        r02 = r0.title;
        goto L7
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
        r03.addUpdateListener(new FilterChatlistActivity$$ExternalSyntheticLambda7(this));
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

    private void updateHeaderCell(boolean r6) {
        FolderBottomSheet.HeaderCell r0 = this.headerCountCell;
        if (r0 == null) goto L25;
        boolean r2 = false;
        if (this.selectedPeers.size() > 0) goto L8;
        String r1 = LocaleController.getString("FilterInviteHeaderChatsEmpty");
    L9:
        r0.setText(r1, r6);
        if (this.allowedPeers.size() > 1) goto L12;
        this.headerCountCell.setAction(_UrlKt.FRAGMENT_ENCODE_SET, null);
    L21:
        if (r6 == false) goto L24;
        AndroidUtilities.makeAccessibilityAnnouncement(this.headerCountCell.textView.getText() + ", " + this.headerCountCell.actionTextView.getText());
        return;
    L24:
        return;
    L12:
        if (this.selectedPeers.size() < Math.min(getMaxChats(), this.allowedPeers.size())) goto L14;
        r2 = true;
    L14:
        FolderBottomSheet.HeaderCell r02 = this.headerCountCell;
        if (r2 == true) goto L18;
        int r12 = R.string.SelectAll;
    L19:
        r02.setAction(LocaleController.getString(r12), new FilterChatlistActivity$$ExternalSyntheticLambda6(this, r2));
        goto L21
    L18:
        r12 = R.string.DeselectAll;
        goto L19
    L8:
        r1 = LocaleController.formatPluralString("FilterInviteHeaderChats", this.selectedPeers.size(), new Object[0]);
        goto L9
    }

    private void updateHintCell(boolean r5) {
        HintInnerCell r52 = this.hintCountCell;
        if (r52 != null) goto L6;
        return;
    L6:
        if (this.invite != null) goto L9;
        r52.setText(LocaleController.getString(R.string.FilterInviteHeaderNo), false);
        return;
    L9:
        Paint.FontMetricsInt r53 = r52.getSubtitleTextView().getPaint().getFontMetricsInt();
        Spannable r54 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(this.filter.name, r53, false), this.filter.entities, r53);
        this.hintCountCell.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralSpannable("FilterInviteHeader", this.selectedPeers.size(), new CharSequence[]{r54})), this.filter.title_noanimate);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean canBeginSlide() {
        return checkDiscard(true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r9) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        int r0 = 0;
        updateActionBarTitle(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r2 = this.actionBar.createMenu();
        Drawable r3 = r9.getResources().getDrawable(R.drawable.ic_ab_done).mutate();
        int r5 = Theme.key_actionBarDefaultIcon;
        r3.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r5), PorterDuff.Mode.MULTIPLY));
        CrossfadeDrawable r4 = new CrossfadeDrawable(r3, new CircularProgressDrawable(Theme.getColor(r5)));
        this.doneButtonDrawable = r4;
        this.doneButton = r2.addItemWithWidth(1, r4, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        checkDoneButton();
        FrameLayout r22 = new FrameLayout(r9);
        this.fragmentView = r22;
        r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        AnonymousClass2 r32 = new AnonymousClass2(this, r9);
        this.listView = r32;
        r32.setLayoutManager(new LinearLayoutManager(r9, 1, false));
        this.listView.setVerticalScrollBarEnabled(false);
        r22.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        RecyclerListView r92 = this.listView;
        ListAdapter r23 = new ListAdapter(this);
        this.adapter = r23;
        r92.setAdapter(r23);
        this.listView.setOnItemClickListener(new FilterChatlistActivity$$ExternalSyntheticLambda3(this));
        getMessagesController().updateFilterDialogs(this.filter);
        this.peers.clear();
        if (this.invite == null) goto L8;
        int r93 = 0;
    L6:
        if (r93 >= this.invite.peers.size()) goto L8;
        long r24 = DialogObject.getPeerDialogId(this.invite.peers.get(r93));
        this.peers.add(Long.valueOf(r24));
        this.selectedPeers.add(Long.valueOf(r24));
        this.allowedPeers.add(Long.valueOf(r24));
        r93 = r93 + 1;
    L8:
        int r94 = 0;
    L10:
        if (r94 >= this.filter.dialogs.size()) goto L28;
        TLRPC.Dialog r25 = this.filter.dialogs.get(r94);
        if (r25 == null) goto L26;
        if (DialogObject.isEncryptedDialog(r25.id) == true) goto L26;
        if (this.peers.contains(Long.valueOf(r25.id)) == true) goto L26;
        long r33 = r25.id;
        if (r33 >= 0) goto L20;
        boolean r7 = true;
    L22:
        if (r33 >= 0) goto L24;
        r7 = FilterCreateActivity.canAddToFolder(getMessagesController().getChat(Long.valueOf(-r25.id)));
    L24:
        if (r7 == false) goto L26;
        this.peers.add(Long.valueOf(r25.id));
        this.allowedPeers.add(Long.valueOf(r25.id));
        goto L26
    L20:
        r7 = false;
    L26:
        r94 = r94 + 1;
    L28:
        if (r0 >= this.filter.dialogs.size()) goto L39;
        TLRPC.Dialog r95 = this.filter.dialogs.get(r0);
        if (r95 == null) goto L38;
        if (DialogObject.isEncryptedDialog(r95.id) == true) goto L38;
        if (this.peers.contains(Long.valueOf(r95.id)) == true) goto L38;
        if (this.allowedPeers.contains(Long.valueOf(r95.id)) == true) goto L38;
        this.peers.add(Long.valueOf(r95.id));
    L38:
        r0 = r0 + 1;
        goto L28
    L39:
        updateRows();
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r1) {
        return checkDiscard(r1);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.savingTitleReqId == 0) goto L6;
        getConnectionsManager().cancelRequest(this.savingTitleReqId, true);
        this.savingTitleReqId = 0;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
    }

    public void setOnDelete(Utilities.Callback<TL_chatlists.TL_exportedChatlistInvite> r1) {
        this.onDelete = r1;
    }

    public void setOnEdit(Utilities.Callback<TL_chatlists.TL_exportedChatlistInvite> r1) {
        this.onEdit = r1;
    }

    public void updateRows() {
        this.rowsCount = 1;
        this.hintRow = 0;
        TL_chatlists.TL_exportedChatlistInvite r1 = this.invite;
        if (r1 == null) goto L5;
        int r3 = 1 + 1;
        this.linkHeaderRow = 1;
        int r4 = r3 + 1;
        this.linkRow = r3;
        this.rowsCount = r3 + 2;
        this.linkSectionRow = r4;
    L6:
        if (r1 == null) goto L8;
    L10:
        int r12 = this.rowsCount;
        int r2 = r12 + 1;
        this.chatsHeaderRow = r12;
        int r13 = r12 + 2;
        this.rowsCount = r13;
        this.chatsStartRow = r2;
        int r14 = r13 + (this.peers.size() - 1);
        this.chatsEndRow = r14;
        this.rowsCount = r14 + 1;
        this.chatsSectionRow = r14;
    L11:
        ListAdapter r5 = this.adapter;
        if (r5 == null) goto L15;
        r5.notifyDataSetChanged();
        return;
    L15:
        return;
    L8:
        if (this.peers.isEmpty() == false) goto L10;
        this.chatsHeaderRow = -1;
        this.chatsStartRow = -1;
        this.chatsEndRow = -1;
        this.chatsSectionRow = -1;
        goto L11
    L5:
        this.linkHeaderRow = -1;
        this.linkRow = -1;
        this.linkSectionRow = -1;
        goto L6
    }
}
