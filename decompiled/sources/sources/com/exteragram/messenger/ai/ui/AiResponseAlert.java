package com.exteragram.messenger.ai.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.URLSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.tools.r8.RecordTag;
import com.exteragram.messenger.ai.AiConfig;
import com.exteragram.messenger.ai.AiController;
import com.exteragram.messenger.ai.data.Message;
import com.exteragram.messenger.ai.data.Role;
import com.exteragram.messenger.ai.data.Service;
import com.exteragram.messenger.ai.network.Client;
import com.exteragram.messenger.ai.network.Client$ImagePayload$$ExternalSyntheticRecord1;
import com.exteragram.messenger.ai.network.GenerationCallback;
import com.exteragram.messenger.ai.network.backend.OnDeviceAvailability;
import com.exteragram.messenger.ai.ui.GenerateFromMessageBottomSheet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.regex.Matcher;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LinkifyPort;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EllipsizeSpanAnimator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkPath;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.MarkdownParser;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.TypingDotsDrawable;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.iv.RichMessageConvert;

/* loaded from: classes4.dex */
public abstract class AiResponseAlert extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private final PaddedAdapter adapter;
    private final Client client;
    private final View contextDivider;
    private final LinearLayout contextView;
    private String currentRequestId;
    private CharSequence currentResponse;
    private TL_iv.RichMessage currentRichMessage;
    EllipsizeSpanAnimator ellipsizeSpanAnimator;
    private boolean errorState;
    private BaseFragment fragment;
    private boolean generating;
    private final HeaderView headerView;
    private String imagePath;
    private final RecyclerListView listView;
    private final LoadingTextView loadingTextView;
    private final ButtonWithCounterView mainButton;
    private Utilities.Callback3<String, CharSequence, TL_iv.RichMessage> onInsertPress;
    private Utilities.CallbackReturn<URLSpan, Boolean> onLinkPress;
    private int parseGeneration;
    private boolean parsingMarkdown;
    private String pendingMarkdown;
    private final RichMessageLayout.PreviewView previewView;
    private String prompt;
    private final TextView promptTextView;
    private final ResponseContainer responseContainer;
    private final AnimatedFloat sheetTopAnimated;
    private boolean sheetTopNotAnimate;
    private final TextSelectionHelper.ArticleTextSelectionHelper textSelectionHelper;
    private final TextSelectionHelper.TextSelectionOverlay textSelectionOverlay;
    private final ThinkingDotsView thinkingDotsView;
    private boolean thinkingVisible;
    private boolean turnSaved;
    private boolean useHistory;

    /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayout {
        final /* synthetic */ AiResponseAlert this$0;

        public AnonymousClass1(AiResponseAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }
    }

    /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ AiResponseAlert this$0;

        public AnonymousClass2(AiResponseAlert r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r4) {
            if (r4.getAction() != 0) goto L9;
            if (r4.getY() >= (AiResponseAlert.m1063$$Nest$mgetSheetTop(this.this$0) - getTop())) goto L9;
            this.this$0.dismiss();
            return true;
        L9:
            return super.dispatchTouchEvent(r4);
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onRequestFocusInDescendants(int r1, Rect r2) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.ViewParent
        public void requestChildFocus(View r1, View r2) {
        }
    }

    /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ AiResponseAlert this$0;

        public AnonymousClass3(AiResponseAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            if (r3 != 0) goto L5;
            AiResponseAlert.m1055$$Nest$fputsheetTopNotAnimate(this.this$0, false);
        L5:
            if (r3 == 0) goto L9;
            if (r3 == 2) goto L9;
            return;
        L9:
            if (AiResponseAlert.m1064$$Nest$mgetSheetTop(this.this$0, false) > 0.0f) goto L11;
            return;
        L11:
            if (AiResponseAlert.m1064$$Nest$mgetSheetTop(this.this$0, false) < AndroidUtilities.dp(96.0f)) goto L13;
            return;
        L13:
            if (AiResponseAlert.m1045$$Nest$fgetlistView(this.this$0).canScrollVertically(1) == true) goto L15;
            return;
        L15:
            if (AiResponseAlert.m1065$$Nest$mhasEnoughHeight(this.this$0) == false) goto L22;
            AiResponseAlert.m1055$$Nest$fputsheetTopNotAnimate(this.this$0, true);
            AiResponseAlert.m1045$$Nest$fgetlistView(this.this$0).smoothScrollBy(0, (int) AiResponseAlert.m1064$$Nest$mgetSheetTop(this.this$0, false));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            AiResponseAlert.access$000(this.this$0).invalidate();
            AiResponseAlert.m1050$$Nest$fgettextSelectionHelper(this.this$0).onParentScrolled();
        }
    }

    /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$4, reason: invalid class name */
    public class AnonymousClass4 extends DefaultItemAnimator {
        final /* synthetic */ AiResponseAlert this$0;

        public AnonymousClass4(AiResponseAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onChangeAnimationUpdate(RecyclerView.ViewHolder r1) {
            AiResponseAlert.access$100(this.this$0).invalidate();
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            AiResponseAlert.access$200(this.this$0).invalidate();
        }
    }

    /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$5, reason: invalid class name */
    public class AnonymousClass5 implements Theme.ResourcesProvider {
        final /* synthetic */ AiResponseAlert this$0;
        final /* synthetic */ Theme.ResourcesProvider val$parent;

        public AnonymousClass5(AiResponseAlert r1, Theme.ResourcesProvider r2) {
            this.this$0 = r1;
            this.val$parent = r2;
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public void applyServiceShaderMatrix(int r1, int r2, float r3, float r4) {
            Theme.ResourcesProvider r0 = this.val$parent;
            if (r0 != null) goto L6;
            Theme.applyServiceShaderMatrix(r1, r2, r3, r4);
            return;
        L6:
            r0.applyServiceShaderMatrix(r1, r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public int getColor(int r2) {
            if (r2 != Theme.key_chat_messageTextIn) goto L5;
        L12:
            r2 = Theme.key_dialogTextBlack;
        L14:
            return Theme.getColor(r2, this.val$parent);
        L5:
            if (r2 == Theme.key_chat_messageTextOut) goto L12;
            if (r2 != Theme.key_chat_messageLinkIn) goto L10;
        L11:
            r2 = Theme.key_dialogTextLink;
            goto L14
        L10:
            if (r2 != Theme.key_chat_messageLinkOut) goto L14;
            goto L11
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Drawable getDrawable(String r1) {
            Theme.ResourcesProvider r0 = this.val$parent;
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.getDrawable(r1);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Paint getPaint(String r1) {
            Theme.ResourcesProvider r0 = this.val$parent;
            if (r0 != null) goto L7;
            return Theme.getThemePaint(r1);
        L7:
            return r0.getPaint(r1);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public boolean hasGradientService() {
            Theme.ResourcesProvider r0 = this.val$parent;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.hasGradientService() == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public boolean isDark() {
            Theme.ResourcesProvider r0 = this.val$parent;
            if (r0 != null) goto L7;
            return Theme.isCurrentThemeDark();
        L7:
            return r0.isDark();
        }
    }

    /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$6, reason: invalid class name */
    public class AnonymousClass6 extends AiResponseAlert {
        final /* synthetic */ Runnable val$onDismiss;

        public AnonymousClass6(Context r1, Client r2, String r3, String r4, boolean r5, Theme.ResourcesProvider r6, Runnable r7) {
            this.val$onDismiss = r7;
            super(r1, r2, r3, r4, r5, r6, null);
        }

        @Override // com.exteragram.messenger.ai.ui.AiResponseAlert, org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            Runnable r0 = this.val$onDismiss;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }
    }

    /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$7, reason: invalid class name */
    public class AnonymousClass7 implements GenerationCallback {
        final /* synthetic */ AiResponseAlert this$0;

        /* renamed from: $r8$lambda$NkvHoNIwLEn7kQ9kKPYMlUR-ceU, reason: not valid java name */
        public static /* synthetic */ void m1073$r8$lambda$NkvHoNIwLEn7kQ9kKPYMlURceU(AnonymousClass7 r0) {
            r0.lambda$onError$0();
        }

        public AnonymousClass7(AiResponseAlert r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onError$0() {
            AiResponseAlert.m1043$$Nest$fgetheaderView(this.this$0).openModelSelect();
        }

        @Override // com.exteragram.messenger.ai.network.GenerationCallback
        public void onChunk(String r2) {
            if (TextUtils.isEmpty(r2) == true) goto L6;
            AiResponseAlert.m1053$$Nest$fputcurrentResponse(this.this$0, r2);
            AiResponseAlert.m1067$$Nest$msetResponse(this.this$0, r2);
            return;
        }

        @Override // com.exteragram.messenger.ai.network.GenerationCallback
        public void onError(int r5, String r6) {
            AiResponseAlert.m1070$$Nest$mstopThinking(this.this$0);
            if (AiController.isOnDeviceError(r5) == true) goto L5;
        L7:
            AiResponseAlert$7$$ExternalSyntheticLambda0 r0 = null;
        L8:
            AiController.showErrorBulletin(AiResponseAlert.access$300(this.this$0), AiResponseAlert.access$400(this.this$0), r5, r6, r0);
            AiResponseAlert.m1038$$Nest$fgetadapter(this.this$0).updateMainView(AiResponseAlert.m1049$$Nest$fgetresponseContainer(this.this$0));
            AiResponseAlert.m1072$$Nest$mupdateMainButton(this.this$0, false, true);
            return;
        L5:
            if (AiResponseAlert.m1058$$Nest$mavailableServices(this.this$0).size() <= 1) goto L7;
            r0 = new AiResponseAlert$7$$ExternalSyntheticLambda0(this);
            goto L8
        }

        @Override // com.exteragram.messenger.ai.network.GenerationCallback
        public void onResponse(String r3) {
            if (TextUtils.isEmpty(r3) == true) goto L6;
            AiResponseAlert.m1053$$Nest$fputcurrentResponse(this.this$0, r3);
            AiResponseAlert r0 = this.this$0;
            AiResponseAlert.m1056$$Nest$fputturnSaved(r0, AiResponseAlert.m1052$$Nest$fgetuseHistory(r0));
            AiResponseAlert.m1067$$Nest$msetResponse(this.this$0, r3);
            AiResponseAlert.m1071$$Nest$mupdateMainButton(this.this$0, false);
            return;
        }

        @Override // com.exteragram.messenger.ai.network.GenerationCallback
        public void onThinking() {
            AiResponseAlert.m1069$$Nest$mshowThinking(this.this$0);
        }
    }

    public class ContainerView extends FrameLayout {
        private final Paint bgPaint;
        private final Path bgPath;
        private Boolean lightStatusBarFull;
        final /* synthetic */ AiResponseAlert this$0;

        /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$ContainerView$1, reason: invalid class name */
        public class AnonymousClass1 implements Bulletin.Delegate {
            final /* synthetic */ ContainerView this$1;

            public AnonymousClass1(ContainerView r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Components.Bulletin.Delegate
            public int getBottomOffset(int r1) {
                return AndroidUtilities.dp(80.0f);
            }
        }

        public ContainerView(AiResponseAlert r2, Context r3) {
            this.this$0 = r2;
            super(r3);
            this.bgPath = new Path();
            Paint r32 = new Paint(1);
            this.bgPaint = r32;
            r32.setColor(AiResponseAlert.access$3000(r2, Theme.key_dialogBackground));
            Theme.applyDefaultShadow(r32);
        }

        private void updateLightStatusBar(boolean r2) {
            Boolean r0 = this.lightStatusBarFull;
            if (r0 != null) goto L5;
        L8:
            this.lightStatusBarFull = Boolean.valueOf(r2);
            Window r02 = this.this$0.getWindow();
            AiResponseAlert r1 = this.this$0;
            if (r2 == false) goto L11;
            int r12 = AiResponseAlert.access$3100(r1, Theme.key_dialogBackground);
        L13:
            if (AndroidUtilities.computePerceivedBrightness(r12) <= 0.721f) goto L15;
            boolean r13 = true;
        L16:
            AndroidUtilities.setLightStatusBar(r02, r13);
            return;
        L15:
            r13 = false;
            goto L16
        L11:
            r12 = Theme.blendOver(AiResponseAlert.access$3200(r1, Theme.key_actionBarDefault), 855638016);
            goto L13
        L5:
            if (r0.booleanValue() != r2) goto L8;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            float r0 = AiResponseAlert.m1063$$Nest$mgetSheetTop(this.this$0);
            boolean r3 = false;
            float r1 = AndroidUtilities.lerp(0, AndroidUtilities.dp(12.0f), MathUtils.clamp(r0 / AndroidUtilities.dpf2(24.0f), 0.0f, 1.0f));
            AiResponseAlert.m1043$$Nest$fgetheaderView(this.this$0).setTranslationY(Math.max(AndroidUtilities.statusBarHeight, r0));
            if (r0 > (AndroidUtilities.statusBarHeight / 2.0f)) goto L5;
            r3 = true;
        L5:
            updateLightStatusBar(r3);
            this.bgPath.rewind();
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(0.0f, r0, getWidth(), getHeight() + r1);
            this.bgPath.addRoundRect(r2, r1, r1, Path.Direction.CW);
            r7.drawPath(this.bgPath, this.bgPaint);
            super.dispatchDraw(r7);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (AiResponseAlert.m1050$$Nest$fgettextSelectionHelper(this.this$0).isInSelectionMode() == false) goto L9;
            if (AiResponseAlert.m1051$$Nest$fgettextSelectionOverlay(this.this$0).onTouchEvent(r2) == false) goto L9;
            return true;
        L9:
            if (AiResponseAlert.m1051$$Nest$fgettextSelectionOverlay(this.this$0).checkOnTap(r2) == false) goto L12;
            r2.setAction(3);
        L12:
            return super.dispatchTouchEvent(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            Bulletin.addDelegate(this, new AnonymousClass1(this));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            Bulletin.removeDelegate(this);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }
    }

    public class HeaderView extends FrameLayout {
        private final ImageView backButton;
        public final ImageView insertButton;
        private final AnimatedTextView modelSelector;
        public final ActionBarMenuItem optionsButton;
        private final View shadow;
        private final LinearLayout subtitleView;
        final /* synthetic */ AiResponseAlert this$0;
        private final TextView titleTextView;

        /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$HeaderView$1, reason: invalid class name */
        public class AnonymousClass1 extends TextView {
            final /* synthetic */ HeaderView this$1;
            final /* synthetic */ AiResponseAlert val$this$0;

            public AnonymousClass1(HeaderView r1, Context r2, AiResponseAlert r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View
            public void onMeasure(int r1, int r2) {
                super.onMeasure(r1, r2);
                if (LocaleController.isRTL == false) goto L6;
                HeaderView.m1076$$Nest$fgettitleTextView(this.this$1).setPivotX(getMeasuredWidth());
                return;
            }
        }

        /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$HeaderView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatedTextView {
            private final Paint bgPaint;
            private final LinkSpanDrawable.LinkCollector links;
            final /* synthetic */ HeaderView this$1;
            final /* synthetic */ AiResponseAlert val$this$0;

            public AnonymousClass2(HeaderView r1, Context r2, AiResponseAlert r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                this.bgPaint = new Paint(1);
                this.links = new LinkSpanDrawable.LinkCollector();
            }

            @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
            public void onDraw(Canvas r7) {
                RectF r0 = AndroidUtilities.rectTmp;
                r0.set(0.0f, (getHeight() - AndroidUtilities.dp(18.0f)) / 2.0f, width(), (getHeight() + AndroidUtilities.dp(18.0f)) / 2.0f);
                this.bgPaint.setColor(Theme.multAlpha(AiResponseAlert.access$1400(this.this$1.this$0, Theme.key_player_actionBarSubtitle), 0.1175f));
                r7.drawRoundRect(r0, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.bgPaint);
                if (this.links.draw(r7) == false) goto L5;
                invalidate();
            L5:
                super.onDraw(r7);
            }

            @Override // android.view.View
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouchEvent(MotionEvent r9) {
                if (r9.getAction() != 0) goto L7;
                LinkSpanDrawable r0 = new LinkSpanDrawable(null, AiResponseAlert.access$1500(this.this$1.this$0), r9.getX(), r9.getY());
                r0.setColor(Theme.multAlpha(AiResponseAlert.access$1600(this.this$1.this$0, Theme.key_player_actionBarSubtitle), 0.1175f));
                LinkPath r92 = r0.obtainNewPath();
                RectF r2 = AndroidUtilities.rectTmp;
                r2.set(0.0f, (getHeight() - AndroidUtilities.dp(18.0f)) / 2.0f, width(), (getHeight() + AndroidUtilities.dp(18.0f)) / 2.0f);
                r92.addRect(r2, Path.Direction.CW);
                this.links.addLink(r0);
                invalidate();
                return true;
            L7:
                if (r9.getAction() == 1) goto L11;
                if (r9.getAction() == 3) goto L11;
            L15:
                return super.onTouchEvent(r9);
            L11:
                if (r9.getAction() != 1) goto L13;
                performClick();
            L13:
                this.links.clear();
                invalidate();
                goto L15
            }
        }

        /* renamed from: $r8$lambda$046odY6OZ-pKcRwXnfwGmKJi1DQ, reason: not valid java name */
        public static /* synthetic */ void m1074$r8$lambda$046odY6OZpKcRwXnfwGmKJi1DQ(HeaderView r0, ItemOptions r1, Service r2, View r3) {
            r0.lambda$openModelSelect$8(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$3ENC3lU9oGr8T6nVzn6SgsG4dIU(HeaderView r0, View r1) {
            r0.lambda$new$6(r1);
        }

        public static /* synthetic */ void $r8$lambda$6gfZ3Cwr9k9tbYNANIh3GZrI6tg(HeaderView r0, GenerateFromMessageBottomSheet.GenerationData r1) {
            r0.lambda$new$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$FL8CcuL4__QV18xF7A157PNbpgo(HeaderView r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$PBGelh1Vxdd9b69A1c8Q2bIPgA0(HeaderView r0, View r1) {
            r0.lambda$new$7(r1);
        }

        public static /* synthetic */ void $r8$lambda$ehzWdDWzPj5NKShetGW0oLusx_c(HeaderView r0, GenerateFromMessageBottomSheet.GenerationData r1) {
            r0.lambda$new$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$rDmc9u9E9S2EyrGPrE9wSdKcSug(HeaderView r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$xFippfwMossz3PIQ6Sp2ZXlvWZw(HeaderView r0, int r1) {
            r0.lambda$new$5(r1);
        }

        /* renamed from: $r8$lambda$xThZGze-zacKTsjhO-GiQ2GrGxQ, reason: not valid java name */
        public static /* synthetic */ void m1075$r8$lambda$xThZGzezacKTsjhOGiQ2GrGxQ(HeaderView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$ybsKk5QYNDoHkII7Go609LdxeCE(HeaderView r0, Role r1) {
            r0.lambda$openRoleSelect$9(r1);
        }

        /* renamed from: -$$Nest$fgettitleTextView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m1076$$Nest$fgettitleTextView(HeaderView r0) {
            return r0.titleTextView;
        }

        public HeaderView(AiResponseAlert r27, Context r28) {
            this.this$0 = r27;
            super(r28);
            View r2 = new View(r28);
            r2.setBackgroundColor(AiResponseAlert.access$500(r27, Theme.key_dialogBackground));
            addView(r2, LayoutHelper.createFrame(-1, 44.0f, 55, 0.0f, 12.0f, 0.0f, 0.0f));
            ImageView r9 = new ImageView(r28);
            this.backButton = r9;
            ImageView.ScaleType r22 = ImageView.ScaleType.CENTER;
            r9.setScaleType(r22);
            r9.setImageResource(R.drawable.ic_ab_back);
            int r10 = Theme.key_dialogTextBlack;
            int r5 = AiResponseAlert.access$600(r27, r10);
            PorterDuff.Mode r11 = PorterDuff.Mode.MULTIPLY;
            r9.setColorFilter(new PorterDuffColorFilter(r5, r11));
            int r12 = Theme.key_listSelector;
            r9.setBackground(Theme.createSelectorDrawable(AiResponseAlert.access$700(r27, r12)));
            r9.setAlpha(0.0f);
            r9.setOnClickListener(new AiResponseAlert$HeaderView$$ExternalSyntheticLambda1(this));
            addView(r9, LayoutHelper.createFrame(54, 54.0f, 48, 1.0f, 1.0f, 1.0f, 1.0f));
            ImageView r14 = new ImageView(r28);
            this.insertButton = r14;
            ScaleStateListAnimator.apply(r14, 0.15f, 1.5f);
            r14.setScaleType(r22);
            r14.setImageResource(R.drawable.msg_send);
            int r6 = Theme.key_player_actionBarSubtitle;
            r14.setColorFilter(new PorterDuffColorFilter(AiResponseAlert.access$800(r27, r6), r11));
            r14.setBackground(Theme.createSelectorDrawable(AiResponseAlert.access$900(r27, r12)));
            r14.setVisibility(8);
            r14.setOnClickListener(new AiResponseAlert$HeaderView$$ExternalSyntheticLambda2(this));
            addView(r14, LayoutHelper.createFrame(48, 54.0f, 53, 1.0f, 1.0f, 64.0f, 1.0f));
            ActionBarMenuItem r23 = new ActionBarMenuItem(r28, null, 0, r6, false, AiResponseAlert.access$1000(r27));
            this.optionsButton = r23;
            r23.setLongClickEnabled(false);
            r23.setShowSubmenuByMove(false);
            r23.setIcon(R.drawable.ic_ab_other);
            r23.setSubMenuOpenSide(2);
            r23.setVisibility(8);
            r23.setBackground(Theme.createSelectorDrawable(AiResponseAlert.access$1100(r27, r12), 1, AndroidUtilities.dp(18.0f)));
            addView(r23, LayoutHelper.createFrame(48, 54.0f, 53, 1.0f, 1.0f, 16.0f, 1.0f));
            r23.addSubItem(1, R.drawable.msg_copy, LocaleController.getString(R.string.Copy));
            r23.addSubItem(2, R.drawable.msg_retry, LocaleController.getString(R.string.Retry));
            r23.addSubItem(3, R.drawable.msg_edit, LocaleController.getString(R.string.Edit));
            r23.addSubItem(4, R.drawable.msg_discuss, LocaleController.getString(R.string.AIAskMore));
            r23.setSubItemShown(4, false);
            r23.setShowedFromBottom(false);
            r23.setOnClickListener(new AiResponseAlert$HeaderView$$ExternalSyntheticLambda3(this));
            r23.setDelegate(new AiResponseAlert$HeaderView$$ExternalSyntheticLambda4(this));
            r23.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
            AnonymousClass1 r52 = new AnonymousClass1(this, r28, r27);
            this.titleTextView = r52;
            r52.setTextColor(AiResponseAlert.access$1200(r27, r10));
            r52.setTextSize(1, 20.0f);
            r52.setTypeface(AndroidUtilities.bold());
            r52.setSingleLine(true);
            r52.setEllipsize(TextUtils.TruncateAt.END);
            r52.setText(AiConfig.getSelectedRole());
            r52.setIncludeFontPadding(false);
            if (LocaleController.isRTL == false) goto L5;
            int r7 = 5;
        L6:
            r52.setGravity(r7);
            r52.setMaxWidth(((AndroidUtilities.displaySize.x - AndroidUtilities.dp(22.0f)) - AndroidUtilities.dp(112.0f)) - AndroidUtilities.dp(2.0f));
            Drawable r72 = ContextCompat.getDrawable(getContext(), R.drawable.ic_arrow_drop_down);
            if (r72 == null) goto L12;
            Drawable r73 = r72.mutate();
            float r18 = 1.0f;
            r73.setColorFilter(new PorterDuffColorFilter(AiResponseAlert.access$1300(r27, r10), r11));
            r73.setBounds(0, -AndroidUtilities.dp(1.0f), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(21.0f));
            if (LocaleController.isRTL == false) goto L11;
            r52.setCompoundDrawables(r73, null, null, null);
        L13:
            r52.setCompoundDrawablePadding(AndroidUtilities.dp(2.0f));
            r52.setPadding(0, 0, 0, 0);
            r52.setOnClickListener(new AiResponseAlert$HeaderView$$ExternalSyntheticLambda5(this));
            r52.setPivotX(0.0f);
            r52.setPivotY(0.0f);
            if (LocaleController.isRTL == false) goto L16;
            int r8 = 5;
        L17:
            addView(r52, LayoutHelper.createFrame(-2, -2.0f, r8 | 48, 22.0f, 20.0f, 112.0f, 0.0f));
            LinearLayout r4 = new LinearLayout(r28);
            this.subtitleView = r4;
            r4.setPivotX(0.0f);
            r4.setPivotY(0.0f);
            AnonymousClass2 r53 = new AnonymousClass2(this, r28, r27);
            this.modelSelector = r53;
            r53.setAnimationProperties(0.25f, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
            r53.setTextColor(AiResponseAlert.access$1700(r27, r6));
            r53.setTextSize(AndroidUtilities.dp(14.0f));
            r53.setText(AiController.getInstance().getSelected().getShortModel());
            r53.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f));
            r53.setOnClickListener(new AiResponseAlert$HeaderView$$ExternalSyntheticLambda6(this));
            r4.addView(r53, LayoutHelper.createLinear(-2, -2, 16, 0, 0, 3, 0));
            addView(r4, LayoutHelper.createFrame(-1, -2.0f, 55, 22.0f, 43.0f, 22.0f, 0.0f));
            View r42 = new View(r28);
            this.shadow = r42;
            r42.setBackgroundColor(AiResponseAlert.access$1800(r27, Theme.key_divider));
            r42.setAlpha(0.0f);
            addView(r42, LayoutHelper.createFrame(-1, AndroidUtilities.getShadowHeight() / AndroidUtilities.dpf2(r18), 55, 0.0f, 56.0f, 0.0f, 0.0f));
            r9.bringToFront();
            r14.bringToFront();
            r23.bringToFront();
            return;
        L16:
            r8 = 3;
            goto L17
        L11:
            r52.setCompoundDrawables(null, null, r73, null);
            goto L13
        L12:
            r18 = 1.0f;
            goto L13
        L5:
            r7 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            this.this$0.dismiss();
        }

        private /* synthetic */ void lambda$new$1(View r4) {
            AiResponseAlert.m1046$$Nest$fgetonInsertPress(this.this$0).run(AiResponseAlert.m1048$$Nest$fgetprompt(this.this$0), AiResponseAlert.m1061$$Nest$mgetInsertResponse(this.this$0), AiResponseAlert.m1041$$Nest$fgetcurrentRichMessage(this.this$0));
            this.this$0.dismiss();
        }

        private /* synthetic */ void lambda$new$2(View r1) {
            this.optionsButton.toggleSubMenu();
        }

        private /* synthetic */ void lambda$new$3(GenerateFromMessageBottomSheet.GenerationData r3) {
            AiResponseAlert.m1059$$Nest$mdropLastTurnFromHistory(this.this$0);
            this.this$0.setPrompt(r3.prompt());
            AiResponseAlert.m1054$$Nest$fputimagePath(this.this$0, r3.imagePath());
            AiResponseAlert.m1057$$Nest$fputuseHistory(this.this$0, r3.useHistory());
            AiResponseAlert.m1068$$Nest$mshowLoadingView(this.this$0);
            AiResponseAlert.m1060$$Nest$mgenerate(this.this$0);
        }

        private /* synthetic */ void lambda$new$4(GenerateFromMessageBottomSheet.GenerationData r3) {
            this.this$0.setPrompt(r3.prompt());
            AiResponseAlert.m1054$$Nest$fputimagePath(this.this$0, null);
            AiResponseAlert.m1057$$Nest$fputuseHistory(this.this$0, r3.useHistory());
            AiResponseAlert.m1068$$Nest$mshowLoadingView(this.this$0);
            AiResponseAlert.m1060$$Nest$mgenerate(this.this$0);
        }

        private /* synthetic */ void lambda$new$5(int r11) {
            if (r11 != 1) goto L5;
            AiResponseAlert r112 = this.this$0;
            if (AndroidUtilities.addToClipboard(AiResponseAlert.m1062$$Nest$mgetRawResponse(r112, AiResponseAlert.m1040$$Nest$fgetcurrentResponse(r112))) == false) goto L21;
            BulletinFactory.of((FrameLayout) AiResponseAlert.access$2800(this.this$0), AiResponseAlert.access$2900(this.this$0)).createCopyBulletin(LocaleController.getString(R.string.TextCopied)).show();
            return;
        L21:
            return;
        L5:
            if (r11 != 2) goto L7;
            AiResponseAlert.m1066$$Nest$mregenerate(this.this$0);
            return;
        L7:
            if (r11 != 3) goto L9;
            new GenerateFromMessageBottomSheet(AiResponseAlert.m1048$$Nest$fgetprompt(this.this$0), AiResponseAlert.m1044$$Nest$fgetimagePath(this.this$0), AiResponseAlert.m1042$$Nest$fgetfragment(this.this$0), getContext(), new AiResponseAlert$HeaderView$$ExternalSyntheticLambda7(this), AiResponseAlert.m1052$$Nest$fgetuseHistory(this.this$0)).show();
            return;
        L9:
            if (r11 != 4) goto L22;
            new GenerateFromMessageBottomSheet(AiResponseAlert.m1042$$Nest$fgetfragment(this.this$0), getContext(), new AiResponseAlert$HeaderView$$ExternalSyntheticLambda8(this), false).show();
            return;
        }

        private /* synthetic */ void lambda$new$6(View r1) {
            openRoleSelect();
        }

        private /* synthetic */ void lambda$new$7(View r1) {
            openModelSelect();
        }

        private /* synthetic */ void lambda$openModelSelect$8(ItemOptions r1, Service r2, View r3) {
            r1.dismiss();
            if (r2.isSelected() == false) goto L5;
            return;
        L5:
            AiResponseAlert.m1059$$Nest$mdropLastTurnFromHistory(this.this$0);
            AiConfig.setSelectedServices(r2);
            this.modelSelector.setText(r2.getShortModel());
            AiResponseAlert.m1068$$Nest$mshowLoadingView(this.this$0);
            AiResponseAlert.m1060$$Nest$mgenerate(this.this$0);
        }

        private /* synthetic */ void lambda$openRoleSelect$9(Role r3) {
            if (r3.isSelected() == false) goto L5;
            return;
        L5:
            AiResponseAlert.m1059$$Nest$mdropLastTurnFromHistory(this.this$0);
            this.titleTextView.setText(r3.getName());
            AiConfig.setSelectedRole(r3.getName());
            AiResponseAlert.m1068$$Nest$mshowLoadingView(this.this$0);
            AiResponseAlert.m1060$$Nest$mgenerate(this.this$0);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            EllipsizeSpanAnimator r0 = this.this$0.ellipsizeSpanAnimator;
            if (r0 == null) goto L6;
            r0.onAttachedToWindow();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            EllipsizeSpanAnimator r0 = this.this$0.ellipsizeSpanAnimator;
            if (r0 == null) goto L6;
            r0.onDetachedFromWindow();
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(78.0f), TLObject.FLAG_30));
        }

        public void openModelSelect() {
            if (AiResponseAlert.m1039$$Nest$fgetclient(this.this$0).isGenerating() == false) goto L5;
            return;
        L5:
            ItemOptions r0 = ItemOptions.makeOptions(AiResponseAlert.access$1900(this.this$0), AiResponseAlert.access$2000(this.this$0), this.modelSelector, false, false, true).setDrawScrim(false).setDimAlpha(0).setMaxHeight(AndroidUtilities.dp(336.0f));
            if (LocaleController.isRTL == false) goto L8;
            int r2 = 5;
        L9:
            ItemOptions r02 = r0.setGravity(r2);
            if (LocaleController.isRTL == false) goto L13;
            int r22 = AndroidUtilities.dp(8.0f);
        L14:
            ItemOptions r03 = r02.translate(r22, 0.0f);
            ArrayList r23 = AiResponseAlert.m1058$$Nest$mavailableServices(this.this$0);
            int r3 = r23.size();
            int r4 = 0;
        L15:
            if (r4 >= r3) goto L25;
            Object r5 = r23.get(r4);
            r4 = r4 + 1;
            Service r52 = (Service) r5;
            ActionBarMenuSubItem r6 = new ActionBarMenuSubItem(getContext(), 2, false, false, AiResponseAlert.access$2100(this.this$0));
            r6.setText(r52.getModel());
            if (r52.isOnDevice() == false) goto L19;
            String r7 = LocaleController.getString(R.string.AIOnDevice);
        L20:
            r6.setSubtext(r7);
            TextView r72 = r6.subtextView;
            if (r52.isSelected() == false) goto L23;
            int r8 = AndroidUtilities.dp(34.0f);
        L24:
            r72.setPadding(0, 0, r8, 0);
            r6.setMinimumWidth(AndroidUtilities.dp(196.0f));
            r6.setItemHeight(56);
            r6.setChecked(r52.isSelected());
            r6.setOnClickListener(new AiResponseAlert$HeaderView$$ExternalSyntheticLambda0(this, r03, r52));
            r03.add(r6);
            goto L15
        L23:
            r8 = 0;
            goto L24
        L19:
            r7 = r52.getUrl();
            goto L20
        L25:
            r03.show();
            return;
        L13:
            r22 = -AndroidUtilities.dp(8.0f);
            goto L14
        L8:
            r2 = 3;
            goto L9
        }

        public void openRoleSelect() {
            if (AiResponseAlert.m1039$$Nest$fgetclient(this.this$0).isGenerating() == false) goto L5;
            return;
        L5:
            int r1 = 0;
            ItemOptions r0 = ItemOptions.makeOptions(AiResponseAlert.access$2200(this.this$0), AiResponseAlert.access$2300(this.this$0), this.titleTextView).setDrawScrim(false).setDimAlpha(0);
            if (LocaleController.isRTL == false) goto L8;
            int r2 = 5;
        L9:
            ItemOptions r02 = r0.setGravity(r2);
            if (LocaleController.isRTL == false) goto L13;
            int r22 = AndroidUtilities.dp(8.0f);
        L14:
            ItemOptions r03 = r02.translate(r22, 0.0f);
            ArrayList r23 = new ArrayList(AiController.getInstance().getSuggestedRoles());
            Iterator<Role> r3 = AiController.getInstance().getRoles().iterator();
        L16:
            if (r3.hasNext() == false) goto L20;
            Role r4 = r3.next();
            if (r23.contains(r4) == true) goto L16;
            r23.add(r4);
            goto L16
        L20:
            int r32 = r23.size();
        L21:
            if (r1 >= r32) goto L23;
            Object r42 = r23.get(r1);
            r1 = r1 + 1;
            Role r43 = (Role) r42;
            r03.addChecked(r43.isSelected(), r43.getName(), new AiResponseAlert$HeaderView$$ExternalSyntheticLambda9(this, r43));
            goto L21
        L23:
            r03.show();
            return;
        L13:
            r22 = -AndroidUtilities.dp(8.0f);
            goto L14
        L8:
            r2 = 3;
            goto L9
        }

        @Override // android.view.View
        public void setTranslationY(float r11) {
            super.setTranslationY(r11);
            float r112 = MathUtils.clamp((r11 - AndroidUtilities.statusBarHeight) / AndroidUtilities.dp(64.0f), 0.0f, 1.0f);
            if (AiResponseAlert.m1065$$Nest$mhasEnoughHeight(this.this$0) == true) goto L5;
            r112 = 1.0f;
        L5:
            float r113 = CubicBezierInterpolator.EASE_OUT.getInterpolation(r112);
            this.titleTextView.setScaleX(AndroidUtilities.lerp(0.85f, 1.0f, r113));
            this.titleTextView.setScaleY(AndroidUtilities.lerp(0.85f, 1.0f, r113));
            this.titleTextView.setTranslationY(AndroidUtilities.lerp(AndroidUtilities.dpf2(-12.0f), 0.0f, r113));
            this.titleTextView.setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dpf2(50.0f), 0.0f, r113));
            this.subtitleView.setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dpf2(50.0f), 0.0f, r113));
            this.subtitleView.setTranslationY(AndroidUtilities.lerp(AndroidUtilities.dpf2(-22.0f), 0.0f, r113));
            this.backButton.setTranslationX(AndroidUtilities.lerp(0.0f, AndroidUtilities.dpf2(-25.0f), r113));
            float r1 = 1.0f - r113;
            this.backButton.setAlpha(r1);
            this.insertButton.setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dpf2(14.0f), AndroidUtilities.dpf2(8.0f), r113));
            this.insertButton.setTranslationY(AndroidUtilities.lerp(AndroidUtilities.dpf2(0.0f), AndroidUtilities.dpf2(16.0f), r113));
            ImageView r2 = this.insertButton;
            AiResponseAlert r4 = this.this$0;
            int r7 = Theme.key_dialogTextBlack;
            int r42 = AiResponseAlert.access$2400(r4, r7);
            AiResponseAlert r8 = this.this$0;
            int r9 = Theme.key_player_actionBarSubtitle;
            r2.setColorFilter(ColorUtils.blendARGB(r42, AiResponseAlert.access$2500(r8, r9), r113), PorterDuff.Mode.MULTIPLY);
            this.optionsButton.setTranslationX(AndroidUtilities.lerp(AndroidUtilities.dpf2(14.0f), AndroidUtilities.dpf2(8.0f), r113));
            this.optionsButton.setTranslationY(AndroidUtilities.lerp(AndroidUtilities.dpf2(0.0f), AndroidUtilities.dpf2(16.0f), r113));
            this.optionsButton.setIconColor(ColorUtils.blendARGB(AiResponseAlert.access$2600(this.this$0, r7), AiResponseAlert.access$2700(this.this$0, r9), r113));
            this.shadow.setTranslationY(AndroidUtilities.lerp(0.0f, AndroidUtilities.dpf2(22.0f), r113));
            this.shadow.setAlpha(r1);
        }
    }

    public static class LoadingTextView extends TextView {
        private final LoadingDrawable loadingDrawable;
        private final LinkPath path;

        public LoadingTextView(Context r2) {
            super(r2);
            LinkPath r22 = new LinkPath(true);
            this.path = r22;
            LoadingDrawable r0 = new LoadingDrawable();
            this.loadingDrawable = r0;
            r0.usePath(r22);
            r0.setSpeed(0.65f);
            r0.setRadiiDp(4.0f);
            setBackground(r0);
        }

        private void updateDrawable() {
            LinkPath r0 = this.path;
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.loadingDrawable == null) goto L13;
            r0.rewind();
            if (getLayout() == null) goto L10;
            getLayout().getText();
            this.path.setCurrentLayout(getLayout(), 0, getPaddingLeft(), getPaddingTop());
            getLayout().getSelectionPath(0, getLayout().getText().length(), this.path);
        L10:
            this.loadingDrawable.updateBounds();
            return;
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.loadingDrawable.reset();
        }

        @Override // android.widget.TextView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            updateDrawable();
        }

        @Override // android.widget.TextView
        public void setText(CharSequence r1, TextView.BufferType r2) {
            super.setText(r1, r2);
            updateDrawable();
        }

        @Override // android.widget.TextView
        public void setTextColor(int r5) {
            super.setTextColor(Theme.multAlpha(r5, 0.2f));
            this.loadingDrawable.setColors(Theme.multAlpha(r5, 0.03f), Theme.multAlpha(r5, 0.175f), Theme.multAlpha(r5, 0.2f), Theme.multAlpha(r5, 0.45f));
        }
    }

    public static final class OffsetTextLayoutBlock extends RecordTag implements TextSelectionHelper.TextLayoutBlock {
        private final TextSelectionHelper.TextLayoutBlock block;
        private final int offsetX;
        private final int offsetY;

        private OffsetTextLayoutBlock(TextSelectionHelper.TextLayoutBlock r1, int r2, int r3) {
            this.block = r1;
            this.offsetX = r2;
            this.offsetY = r3;
        }

        public /* synthetic */ OffsetTextLayoutBlock(TextSelectionHelper.TextLayoutBlock r1, int r2, int r3, AiResponseAlertIA r4) {
            this(r1, r2, r3);
        }

        public final /* synthetic */ boolean $record$equals(Object r3) {
            if ((r3 instanceof OffsetTextLayoutBlock) == false) goto L12;
            OffsetTextLayoutBlock r32 = (OffsetTextLayoutBlock) r3;
            if (this.offsetX == r32.offsetX) goto L7;
            return false;
        L7:
            if (this.offsetY == r32.offsetY) goto L9;
            return false;
        L9:
            if (Objects.equals(this.block, r32.block) == false) goto L16;
            return true;
        L16:
            return false;
        L12:
            return false;
        }

        public final /* synthetic */ Object[] $record$getFieldsAsObjects() {
            return new Object[]{this.block, Integer.valueOf(this.offsetX), Integer.valueOf(this.offsetY)};
        }

        public final boolean equals(Object r1) {
            return $record$equals(r1);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public Layout getLayout() {
            return this.block.getLayout();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public CharSequence getPrefix() {
            return this.block.getPrefix();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getRow() {
            return this.block.getRow();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public CharSequence getText() {
            return this.block.getText();
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getX() {
            return this.block.getX() + this.offsetX;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getY() {
            return this.block.getY() + this.offsetY;
        }

        public final int hashCode() {
            return AiResponseAlert$OffsetTextLayoutBlock$$ExternalSyntheticRecord0.m(this.offsetX, this.offsetY, this.block);
        }

        public final String toString() {
            return Client$ImagePayload$$ExternalSyntheticRecord1.m($record$getFieldsAsObjects(), OffsetTextLayoutBlock.class, "block;offsetX;offsetY");
        }
    }

    public static class PaddedAdapter extends RecyclerView.Adapter {
        private final Context mContext;
        private View mMainView;
        private int mainViewType;

        /* renamed from: com.exteragram.messenger.ai.ui.AiResponseAlert$PaddedAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ PaddedAdapter this$0;

            public AnonymousClass1(PaddedAdapter r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r3, int r4) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (AndroidUtilities.displaySize.y * 0.4f), TLObject.FLAG_30));
            }
        }

        public PaddedAdapter(Context r2, View r3) {
            this.mainViewType = 1;
            this.mContext = r2;
            this.mMainView = r3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return 2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L6;
            return 0;
        L6:
            return this.mainViewType;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L6;
            return new RecyclerListView.Holder(new AnonymousClass1(this, this.mContext));
        L6:
            return new RecyclerListView.Holder(this.mMainView);
        }

        public void updateMainView(View r3) {
            if (this.mMainView != r3) goto L5;
            return;
        L5:
            this.mainViewType++;
            this.mMainView = r3;
            notifyItemChanged(1);
        }
    }

    public class ResponseContainer extends LinearLayout implements TextSelectionHelper.ArticleSelectableView {
        final /* synthetic */ AiResponseAlert this$0;

        public ResponseContainer(AiResponseAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            setOrientation(1);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.ArticleSelectableView
        public void fillTextLayoutBlocks(ArrayList<TextSelectionHelper.TextLayoutBlock> r7) {
            int r0 = r7.size();
            AiResponseAlert.m1047$$Nest$fgetpreviewView(this.this$0).fillTextLayoutBlocks(r7);
        L4:
            if (r0 >= r7.size()) goto L6;
            r7.set(r0, new OffsetTextLayoutBlock(r7.get(r0), AiResponseAlert.m1047$$Nest$fgetpreviewView(this.this$0).getLeft(), AiResponseAlert.m1047$$Nest$fgetpreviewView(this.this$0).getTop(), null));
            r0 = r0 + 1;
            goto L4
        }

        @Override // android.view.View, org.telegram.ui.Cells.TextSelectionHelper.SelectableView
        public void invalidate() {
            super.invalidate();
            if (AiResponseAlert.m1047$$Nest$fgetpreviewView(this.this$0) == null) goto L6;
            AiResponseAlert.m1047$$Nest$fgetpreviewView(this.this$0).invalidate();
            return;
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }
    }

    public static class ThinkingDotsView extends View {
        private final TypingDotsDrawable drawable;

        public ThinkingDotsView(Context r2, int r3) {
            super(r2);
            TypingDotsDrawable r22 = new TypingDotsDrawable(true);
            this.drawable = r22;
            r22.setCallback(this);
            r22.setIgnoreAnimationLocks();
            r22.setColor(r3);
        }

        @Override // android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.drawable.isStarted() == false) goto L6;
            invalidate();
            return;
        }

        @Override // android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            stop();
        }

        @Override // android.view.View
        public void onDraw(Canvas r2) {
            super.onDraw(r2);
            this.drawable.draw(r2);
            if (this.drawable.isStarted() == false) goto L6;
            invalidate();
            return;
        }

        @Override // android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(42.0f));
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            DrawableUtils.setBounds(this.drawable, AndroidUtilities.dp(22.0f), r2 / 2.0f, 19);
        }

        public void start() {
            if (this.drawable.isStarted() == true) goto L6;
            this.drawable.start();
            return;
        }

        public void stop() {
            this.drawable.stop();
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (super.verifyDrawable(r2) == false) goto L5;
            return true;
        L5:
            if (r2 == this.drawable) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: $r8$lambda$ClGkZkRIIPs7SFBRTM-svn7cIYM, reason: not valid java name */
    public static /* synthetic */ void m1034$r8$lambda$ClGkZkRIIPs7SFBRTMsvn7cIYM(AiResponseAlert r0, String r1, int r2) {
        r0.lambda$parseMarkdownAsync$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JldFLlCjDWLBZYmrbDAPhLByZGs(AiResponseAlert r0, int r1, TL_iv.RichMessage r2) {
        r0.lambda$parseMarkdownAsync$4(r1, r2);
    }

    /* renamed from: $r8$lambda$SHxyXoDJ2Jmjz1CHsn-EHveVcxw, reason: not valid java name */
    public static /* synthetic */ void m1035$r8$lambda$SHxyXoDJ2Jmjz1CHsnEHveVcxw(AiResponseAlert r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ TL_iv.RichText $r8$lambda$aOG8Y5beNb6fm8CWERgVer8mTp0(TL_iv.RichText r0) {
        return linkify(r0);
    }

    /* renamed from: $r8$lambda$dzGrsWFKIFy6-YaXFiFERudGpnk, reason: not valid java name */
    public static /* synthetic */ void m1036$r8$lambda$dzGrsWFKIFy6YaXFiFERudGpnk(AiResponseAlert r0, URLSpan r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$lxIQTOg-m9UvhxscUAS8R6Psx0U, reason: not valid java name */
    public static /* synthetic */ void m1037$r8$lambda$lxIQTOgm9UvhxscUAS8R6Psx0U(AiResponseAlert r0, ArrayList r1, int r2) {
        r0.lambda$formatContextAsync$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$odKt27EbAHNcB9ibgZUsk9ytsag(AiResponseAlert r0, int r1, ArrayList r2) {
        r0.lambda$formatContextAsync$2(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PaddedAdapter m1038$$Nest$fgetadapter(AiResponseAlert r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetclient, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Client m1039$$Nest$fgetclient(AiResponseAlert r0) {
        return r0.client;
    }

    /* renamed from: -$$Nest$fgetcurrentResponse, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m1040$$Nest$fgetcurrentResponse(AiResponseAlert r0) {
        return r0.currentResponse;
    }

    /* renamed from: -$$Nest$fgetcurrentRichMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_iv.RichMessage m1041$$Nest$fgetcurrentRichMessage(AiResponseAlert r0) {
        return r0.currentRichMessage;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m1042$$Nest$fgetfragment(AiResponseAlert r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgetheaderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HeaderView m1043$$Nest$fgetheaderView(AiResponseAlert r0) {
        return r0.headerView;
    }

    /* renamed from: -$$Nest$fgetimagePath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m1044$$Nest$fgetimagePath(AiResponseAlert r0) {
        return r0.imagePath;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m1045$$Nest$fgetlistView(AiResponseAlert r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetonInsertPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback3 m1046$$Nest$fgetonInsertPress(AiResponseAlert r0) {
        return r0.onInsertPress;
    }

    /* renamed from: -$$Nest$fgetpreviewView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichMessageLayout.PreviewView m1047$$Nest$fgetpreviewView(AiResponseAlert r0) {
        return r0.previewView;
    }

    /* renamed from: -$$Nest$fgetprompt, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m1048$$Nest$fgetprompt(AiResponseAlert r0) {
        return r0.prompt;
    }

    /* renamed from: -$$Nest$fgetresponseContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ResponseContainer m1049$$Nest$fgetresponseContainer(AiResponseAlert r0) {
        return r0.responseContainer;
    }

    /* renamed from: -$$Nest$fgettextSelectionHelper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextSelectionHelper.ArticleTextSelectionHelper m1050$$Nest$fgettextSelectionHelper(AiResponseAlert r0) {
        return r0.textSelectionHelper;
    }

    /* renamed from: -$$Nest$fgettextSelectionOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextSelectionHelper.TextSelectionOverlay m1051$$Nest$fgettextSelectionOverlay(AiResponseAlert r0) {
        return r0.textSelectionOverlay;
    }

    /* renamed from: -$$Nest$fgetuseHistory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1052$$Nest$fgetuseHistory(AiResponseAlert r0) {
        return r0.useHistory;
    }

    /* renamed from: -$$Nest$fputcurrentResponse, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1053$$Nest$fputcurrentResponse(AiResponseAlert r0, CharSequence r1) {
        r0.currentResponse = r1;
    }

    /* renamed from: -$$Nest$fputimagePath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1054$$Nest$fputimagePath(AiResponseAlert r0, String r1) {
        r0.imagePath = r1;
    }

    /* renamed from: -$$Nest$fputsheetTopNotAnimate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1055$$Nest$fputsheetTopNotAnimate(AiResponseAlert r0, boolean r1) {
        r0.sheetTopNotAnimate = r1;
    }

    /* renamed from: -$$Nest$fputturnSaved, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1056$$Nest$fputturnSaved(AiResponseAlert r0, boolean r1) {
        r0.turnSaved = r1;
    }

    /* renamed from: -$$Nest$fputuseHistory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1057$$Nest$fputuseHistory(AiResponseAlert r0, boolean r1) {
        r0.useHistory = r1;
    }

    /* renamed from: -$$Nest$mavailableServices, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m1058$$Nest$mavailableServices(AiResponseAlert r0) {
        return r0.availableServices();
    }

    /* renamed from: -$$Nest$mdropLastTurnFromHistory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1059$$Nest$mdropLastTurnFromHistory(AiResponseAlert r0) {
        r0.dropLastTurnFromHistory();
    }

    /* renamed from: -$$Nest$mgenerate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1060$$Nest$mgenerate(AiResponseAlert r0) {
        r0.generate();
    }

    /* renamed from: -$$Nest$mgetInsertResponse, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m1061$$Nest$mgetInsertResponse(AiResponseAlert r0) {
        return r0.getInsertResponse();
    }

    /* renamed from: -$$Nest$mgetRawResponse, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m1062$$Nest$mgetRawResponse(AiResponseAlert r0, CharSequence r1) {
        return r0.getRawResponse(r1);
    }

    /* renamed from: -$$Nest$mgetSheetTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1063$$Nest$mgetSheetTop(AiResponseAlert r0) {
        return r0.getSheetTop();
    }

    /* renamed from: -$$Nest$mgetSheetTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1064$$Nest$mgetSheetTop(AiResponseAlert r0, boolean r1) {
        return r0.getSheetTop(r1);
    }

    /* renamed from: -$$Nest$mhasEnoughHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1065$$Nest$mhasEnoughHeight(AiResponseAlert r0) {
        return r0.hasEnoughHeight();
    }

    /* renamed from: -$$Nest$mregenerate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1066$$Nest$mregenerate(AiResponseAlert r0) {
        r0.regenerate();
    }

    /* renamed from: -$$Nest$msetResponse, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1067$$Nest$msetResponse(AiResponseAlert r0, String r1) {
        r0.setResponse(r1);
    }

    /* renamed from: -$$Nest$mshowLoadingView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1068$$Nest$mshowLoadingView(AiResponseAlert r0) {
        r0.showLoadingView();
    }

    /* renamed from: -$$Nest$mshowThinking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1069$$Nest$mshowThinking(AiResponseAlert r0) {
        r0.showThinking();
    }

    /* renamed from: -$$Nest$mstopThinking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1070$$Nest$mstopThinking(AiResponseAlert r0) {
        r0.stopThinking();
    }

    /* renamed from: -$$Nest$mupdateMainButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1071$$Nest$mupdateMainButton(AiResponseAlert r0, boolean r1) {
        r0.updateMainButton(r1);
    }

    /* renamed from: -$$Nest$mupdateMainButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1072$$Nest$mupdateMainButton(AiResponseAlert r0, boolean r1, boolean r2) {
        r0.updateMainButton(r1, r2);
    }

    private AiResponseAlert(Context r21, Client r22, String r23, String r24, boolean r25, Theme.ResourcesProvider r26) {
        super(r21, false, r26);
        this.client = r22;
        this.imagePath = r24;
        this.useHistory = r25;
        this.backgroundPaddingLeft = 0;
        fixNavigationBar();
        ContainerView r4 = new ContainerView(this, r21);
        this.containerView = r4;
        this.sheetTopAnimated = new AnimatedFloat(r4, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        LoadingTextView r42 = new LoadingTextView(r21);
        this.loadingTextView = r42;
        r42.setPadding(AndroidUtilities.dp(22.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(6.0f));
        r42.setTextSize(1, SharedConfig.fontSize);
        int r6 = Theme.key_dialogTextBlack;
        r42.setTextColor(getThemedColor(r6));
        r42.setTypeface(AndroidUtilities.regular());
        r42.setLinkTextColor(Theme.multAlpha(getThemedColor(r6), 0.2f));
        this.thinkingDotsView = new ThinkingDotsView(r21, getThemedColor(r6));
        TextView r62 = new TextView(r21);
        this.promptTextView = r62;
        r62.setPadding(AndroidUtilities.dp(22.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(22.0f), 0);
        r62.setTextSize(1, SharedConfig.fontSize);
        r62.setTypeface(AndroidUtilities.regular());
        r62.setTextColor(getThemedColor(Theme.key_player_actionBarSubtitle));
        RichMessageLayout.PreviewView r9 = new RichMessageLayout.PreviewView(r21, UserConfig.selectedAccount, createRichResourcesProvider());
        this.previewView = r9;
        r9.setPadding(AndroidUtilities.dp(22.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(6.0f));
        r9.setOnLinkPress(new AiResponseAlert$$ExternalSyntheticLambda2(this));
        AnonymousClass1 r5 = new AnonymousClass1(this, r21);
        this.contextView = r5;
        r5.setOrientation(1);
        r5.setAlpha(0.55f);
        r5.setVisibility(8);
        View r10 = new View(r21);
        this.contextDivider = r10;
        r10.setBackgroundColor(Theme.multAlpha(getThemedColor(Theme.key_divider), 0.75f));
        r10.setVisibility(8);
        ResponseContainer r7 = new ResponseContainer(this, r21);
        this.responseContainer = r7;
        r7.addView(r5, LayoutHelper.createLinear(-1, -2));
        r7.addView(r10, LayoutHelper.createLinear(-1, 1, 22.0f, 10.0f, 22.0f, 0.0f));
        r7.addView(r62, LayoutHelper.createLinear(-1, -2));
        r7.addView(r9, LayoutHelper.createLinear(-1, -2));
        setPrompt(r23.trim());
        AnonymousClass2 r52 = new AnonymousClass2(this, r21, r26);
        this.listView = r52;
        r52.setOverScrollMode(1);
        r52.setPadding(0, AndroidUtilities.statusBarHeight + AndroidUtilities.dp(56.0f), 0, AndroidUtilities.dp(80.0f));
        r52.setClipToPadding(true);
        LinearLayoutManager r63 = new LinearLayoutManager(r21);
        r52.setLayoutManager(r63);
        PaddedAdapter r72 = new PaddedAdapter(r21, r42);
        this.adapter = r72;
        r52.setAdapter(r72);
        r52.setOnScrollListener(new AnonymousClass3(this));
        AnonymousClass4 r43 = new AnonymousClass4(this);
        r43.setDurations(180);
        r43.setInterpolator(new LinearInterpolator());
        r52.setItemAnimator(r43);
        this.containerView.addView(r52, LayoutHelper.createFrame(-1, -2, 80));
        TextSelectionHelper.ArticleTextSelectionHelper r44 = new TextSelectionHelper.ArticleTextSelectionHelper();
        this.textSelectionHelper = r44;
        r44.setParentView(r52);
        r44.layoutManager = r63;
        TextSelectionHelper.TextSelectionOverlay r53 = r44.getOverlayView(r21);
        this.textSelectionOverlay = r53;
        AndroidUtilities.removeFromParent(r53);
        this.containerView.addView(r53, LayoutHelper.createFrame(-1, -1, 119));
        r9.setTextSelectionHelper(r44);
        HeaderView r45 = new HeaderView(this, r21);
        this.headerView = r45;
        this.containerView.addView(r45, LayoutHelper.createFrame(-1, 78, 55));
        ButtonWithCounterView r46 = new ButtonWithCounterView(r21, r26);
        this.mainButton = r46;
        r46.setRound();
        r46.setColor(getThemedColor(Theme.key_featuredStickers_addButton));
        r46.setText(LocaleController.getString(R.string.Stop), false);
        this.generating = true;
        r46.setOnClickListener(new AiResponseAlert$$ExternalSyntheticLambda3(this));
        this.containerView.addView(r46, LayoutHelper.createFrame(-1, 48.0f, 87, 16.0f, 16.0f, 16.0f, 16.0f));
    }

    public /* synthetic */ AiResponseAlert(Context r1, Client r2, String r3, String r4, boolean r5, Theme.ResourcesProvider r6, AiResponseAlertIA r7) {
        this(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ ViewGroup access$000(AiResponseAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$100(AiResponseAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1000(AiResponseAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1100(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1200(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1300(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1400(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1500(AiResponseAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1600(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1700(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1800(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$1900(AiResponseAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$200(AiResponseAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2000(AiResponseAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2100(AiResponseAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$2200(AiResponseAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2300(AiResponseAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2400(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2500(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2600(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2700(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$2800(AiResponseAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2900(AiResponseAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$300(AiResponseAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$3000(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3100(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3200(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(AiResponseAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$500(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$600(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$700(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$800(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$900(AiResponseAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    private ArrayList<Service> availableServices() {
        ArrayList<Service> r3 = new ArrayList();
        Iterator<Service> r0 = AiController.getInstance().getAll().iterator();
    L4:
        if (r0.hasNext() == false) goto L11;
        Service r1 = r0.next();
        if (r1.isOnDevice() == false) goto L10;
        if (OnDeviceAvailability.isReady() == false) goto L4;
    L10:
        r3.add(r1);
        goto L4
    L11:
        return r3;
    }

    private TextView createContextTextView(Message r7) {
        boolean r0 = "assistant".equals(r7.role());
        TextView r1 = new TextView(getContext());
        r1.setTextSize(1, SharedConfig.fontSize);
        r1.setTypeface(AndroidUtilities.regular());
        r1.setMaxLines(3);
        r1.setEllipsize(TextUtils.TruncateAt.END);
        int r3 = AndroidUtilities.dp(22.0f);
        if (r0 == true) goto L5;
        float r4 = 12.0f;
    L6:
        r1.setPadding(r3, AndroidUtilities.dp(r4), AndroidUtilities.dp(22.0f), 0);
        if (r0 == true) goto L9;
        int r2 = Theme.key_player_actionBarSubtitle;
    L10:
        r1.setTextColor(getThemedColor(r2));
        r1.setLinkTextColor(getThemedColor(Theme.key_dialogTextLink));
        if (r0 == true) goto L13;
        CharSequence r6 = formatPromptText(r7.content());
    L14:
        r1.setText(Emoji.replaceEmoji(r6, r1.getPaint().getFontMetricsInt(), false));
        return r1;
    L13:
        r6 = r7.content();
        goto L14
    L9:
        r2 = Theme.key_dialogTextBlack;
        goto L10
    L5:
        r4 = 6.0f;
        goto L6
    }

    private Theme.ResourcesProvider createRichResourcesProvider() {
        return new AnonymousClass5(this, this.resourcesProvider);
    }

    private void dropLastTurnFromHistory() {
        if (this.turnSaved == false) goto L6;
        AiConfig.removeLastFromHistory();
        this.turnSaved = false;
        return;
    }

    private void formatContextAsync(ArrayList<Message> r4) {
        if (r4.isEmpty() == false) goto L5;
        return;
    L5:
        int r0 = this.parseGeneration;
        Utilities.globalQueue.postRunnable(new AiResponseAlert$$ExternalSyntheticLambda1(this, r4, r0));
    }

    private void formatPrompt() {
        if (AiConfig.getShowResponseOnly() == false) goto L5;
    L9:
        this.promptTextView.setVisibility(8);
        this.promptTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L5:
        if (TextUtils.isEmpty(this.prompt) == true) goto L9;
        this.promptTextView.setVisibility(0);
        this.promptTextView.setText(Emoji.replaceEmoji(formatPromptText(this.prompt), this.promptTextView.getPaint().getFontMetricsInt(), false));
    }

    private CharSequence formatPromptText(String r6) {
        ColoredImageSpan r0 = new ColoredImageSpan(R.drawable.msg_mini_arrow_mediathin);
        int r1 = Theme.key_player_actionBarSubtitle;
        r0.setColorKey(r1);
        r0.setScale(1.0f, 1.0f);
        r0.setTranslateY(AndroidUtilities.dpf2(1.5f));
        r0.spaceScaleX = 0.95f;
        SpannableStringBuilder r2 = new SpannableStringBuilder("→ " + r6);
        r2.setSpan(r0, 0, 1, 33);
        r2.setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(4.0f)), 1, 2, 33);
        r2.setSpan(new RelativeSizeSpan(0.8f), 2, r2.length(), 33);
        r2.setSpan(new ForegroundColorSpan(getThemedColor(r1)), 2, r2.length(), 33);
        return r2;
    }

    private void generate() {
        this.currentResponse = null;
        this.currentRichMessage = null;
        this.pendingMarkdown = null;
        this.turnSaved = false;
        this.parseGeneration++;
        updateMainButton(true);
        updateContextView();
        this.textSelectionHelper.clear(true);
        stopThinking();
        if (AiController.getInstance().getSelected().isReasoningEnabled() == true) goto L5;
    L7:
        this.currentRequestId = this.client.getResponse(this.prompt, this.useHistory, AiConfig.getResponseStreaming(), this.imagePath, new AnonymousClass7(this));
        return;
    L5:
        if (AiConfig.getResponseStreaming() == false) goto L7;
        showThinking();
        goto L7
    }

    private CharSequence getInsertResponse() {
        TL_iv.RichMessage r0 = this.currentRichMessage;
        if (r0 == null) goto L5;
        CharSequence r02 = RichMessageConvert.blocksToCharSequence(r0.blocks);
        if (AiConfig.getInsertAsQuote() == false) goto L10;
        r02 = AndroidUtilities.removeSpans(AndroidUtilities.removeSpans(r02, QuoteSpan.QuoteStyleSpan.class), QuoteSpan.class);
    L10:
        if (AiConfig.getShowResponseOnly() == false) goto L12;
    L16:
        return r02;
    L12:
        if (TextUtils.isEmpty(this.prompt) == true) goto L16;
        return new SpannableStringBuilder("→ " + this.prompt + "\n\n").append(r02);
    L5:
        return getRawResponse(this.currentResponse);
    }

    private CharSequence getRawResponse(CharSequence r3) {
        if (AiConfig.getShowResponseOnly() == false) goto L5;
    L9:
        return r3;
    L5:
        if (TextUtils.isEmpty(this.prompt) == true) goto L9;
        return "→ " + this.prompt + "\n\n" + r3;
    }

    private float getSheetTop() {
        return getSheetTop(true);
    }

    private float getSheetTop(boolean r5) {
        float r0 = this.listView.getTop();
        if (this.listView.getChildCount() < 1) goto L5;
        RecyclerListView r1 = this.listView;
        r0 = r0 + Math.max(0, r1.getChildAt(r1.getChildCount() - 1).getTop());
    L5:
        float r02 = Math.max(0.0f, r0 - AndroidUtilities.dp(78.0f));
        if (r5 == false) goto L16;
        AnimatedFloat r52 = this.sheetTopAnimated;
        if (r52 == null) goto L16;
        if (this.listView.scrollingByUser == false) goto L12;
    L15:
        r52.set(r02, true);
        goto L16
    L12:
        if (this.sheetTopNotAnimate == true) goto L15;
        return r52.set(r02);
    L16:
        return r02;
    }

    private boolean hasEnoughHeight() {
        float r0 = 0.0f;
        int r2 = 0;
    L3:
        int r3 = this.listView.getChildCount();
        RecyclerListView r4 = this.listView;
        if (r2 >= r3) goto L10;
        if (this.listView.getChildAdapterPosition(r4.getChildAt(r2)) != 1) goto L8;
        r0 = r0 + r3.getHeight();
    L8:
        r2 = r2 + 1;
        goto L3
    L10:
        if (r0 < ((r4.getHeight() - this.listView.getPaddingTop()) - this.listView.getPaddingBottom())) goto L12;
        return true;
    L12:
        return false;
    }

    private /* synthetic */ void lambda$formatContextAsync$2(int r5, ArrayList r6) {
        if (r5 == this.parseGeneration) goto L5;
        return;
    L5:
        if (this.contextView.getChildCount() != r6.size()) goto L21;
        int r0 = 0;
    L9:
        if (r0 >= r6.size()) goto L22;
        CharSequence r1 = (CharSequence) r6.get(r0);
        if (r1 == null) goto L15;
        View r2 = this.contextView.getChildAt(r0);
        if ((r2 instanceof TextView) == false) goto L15;
        TextView r22 = (TextView) r2;
        r22.setText(Emoji.replaceEmoji(r1, r22.getPaint().getFontMetricsInt(), false));
    L15:
        r0 = r0 + 1;
        goto L9
    L22:
        return;
    }

    private /* synthetic */ void lambda$formatContextAsync$3(ArrayList r7, int r8) {
        ArrayList r0 = new ArrayList(r7.size());
        int r1 = r7.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        Object r3 = r7.get(r2);
        r2 = r2 + 1;
        Message r32 = (Message) r3;
        if ("assistant".equals(r32.role()) == false) goto L7;
        CharSequence r33 = MarkdownPreview.format(r32.content());
    L8:
        r0.add(r33);
        goto L3
    L7:
        r33 = null;
        goto L8
    L9:
        AndroidUtilities.runOnUIThread(new AiResponseAlert$$ExternalSyntheticLambda4(this, r8, r0));
    }

    private /* synthetic */ void lambda$new$0(URLSpan r1) {
        openUrl(r1.getURL());
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        if (this.errorState == false) goto L7;
        regenerate();
        return;
    L7:
        if (this.generating == false) goto L10;
        stopGeneration();
        return;
    L10:
        dismiss();
    }

    private /* synthetic */ void lambda$parseMarkdownAsync$4(int r2, TL_iv.RichMessage r3) {
        this.parsingMarkdown = false;
        if (r2 == this.parseGeneration) goto L5;
        return;
    L5:
        stopThinking();
        this.currentRichMessage = r3;
        this.previewView.set(r3);
        this.adapter.updateMainView(this.responseContainer);
        parseMarkdownAsync();
    }

    private /* synthetic */ void lambda$parseMarkdownAsync$5(String r2, int r3) {
        AndroidUtilities.runOnUIThread(new AiResponseAlert$$ExternalSyntheticLambda6(this, r3, parseMarkdown(r2)));
    }

    private static TL_iv.RichText linkify(TL_iv.RichText r2) {
        if (r2 != null) goto L4;
    L27:
        return r2;
    L4:
        if ((r2 instanceof TL_iv.textEmpty) == true) goto L27;
        if ((r2 instanceof TL_iv.textUrl) == true) goto L27;
        if ((r2 instanceof TL_iv.textEmail) == true) goto L27;
        if ((r2 instanceof TL_iv.textPhone) == true) goto L27;
        if ((r2 instanceof TL_iv.textFixed) == true) goto L27;
        if ((r2 instanceof TL_iv.textMath) == true) goto L27;
        if ((r2 instanceof TL_iv.textImage) == false) goto L19;
        return r2;
    L19:
        if ((r2 instanceof TL_iv.textConcat) == false) goto L23;
        r2.texts.replaceAll(new AiResponseAlert$$ExternalSyntheticLambda0());
        return r2;
    L23:
        if ((r2 instanceof TL_iv.textPlain) == true) goto L25;
        r2.text = linkify(r2.text);
        goto L27
    L25:
        return linkifyPlain((TL_iv.textPlain) r2);
    }

    private static void linkifyBlocks(ArrayList<TL_iv.PageBlock> r11) {
        int r0 = r11.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L38;
        TL_iv.PageBlock r3 = r11.get(r2);
        r2 = r2 + 1;
        TL_iv.PageBlock r32 = r3;
        if ((r32 instanceof TL_iv.pageBlockPreformatted) == true) goto L3;
        if ((r32 instanceof TL_iv.pageBlockDetails) == true) goto L9;
        if ((r32 instanceof TL_iv.pageBlockList) == true) goto L12;
        if ((r32 instanceof TL_iv.pageBlockOrderedList) == true) goto L22;
        if ((r32 instanceof TL_iv.pageBlockTable) == true) goto L32;
        r32.text = linkify(r32.text);
        goto L3
    L32:
        ArrayList<TL_iv.pageTableRow> r33 = ((TL_iv.pageBlockTable) r32).rows;
        int r4 = r33.size();
        int r5 = 0;
    L33:
        if (r5 >= r4) goto L3;
        TL_iv.pageTableRow r6 = r33.get(r5);
        r5 = r5 + 1;
        ArrayList<TL_iv.pageTableCell> r62 = r6.cells;
        int r7 = r62.size();
        int r8 = 0;
    L35:
        if (r8 >= r7) goto L33;
        TL_iv.pageTableCell r9 = r62.get(r8);
        r8 = r8 + 1;
        TL_iv.pageTableCell r92 = r9;
        r92.text = linkify(r92.text);
        goto L35
    L22:
        ArrayList<TL_iv.PageListOrderedItem> r34 = ((TL_iv.pageBlockOrderedList) r32).items;
        int r42 = r34.size();
        int r52 = 0;
    L23:
        if (r52 >= r42) goto L3;
        TL_iv.PageListOrderedItem r63 = r34.get(r52);
        r52 = r52 + 1;
        TL_iv.PageListOrderedItem r64 = r63;
        if ((r64 instanceof TL_iv.TL_pageListOrderedItemText) == true) goto L26;
        if ((r64 instanceof TL_iv.TL_pageListOrderedItemBlocks) == false) goto L23;
        linkifyBlocks(((TL_iv.TL_pageListOrderedItemBlocks) r64).blocks);
        goto L23
    L26:
        TL_iv.TL_pageListOrderedItemText r65 = (TL_iv.TL_pageListOrderedItemText) r64;
        r65.text = linkify(r65.text);
        goto L23
    L12:
        ArrayList<TL_iv.PageListItem> r35 = ((TL_iv.pageBlockList) r32).items;
        int r43 = r35.size();
        int r53 = 0;
    L13:
        if (r53 >= r43) goto L3;
        TL_iv.PageListItem r66 = r35.get(r53);
        r53 = r53 + 1;
        TL_iv.PageListItem r67 = r66;
        if ((r67 instanceof TL_iv.TL_pageListItemText) == true) goto L16;
        if ((r67 instanceof TL_iv.TL_pageListItemBlocks) == false) goto L13;
        linkifyBlocks(((TL_iv.TL_pageListItemBlocks) r67).blocks);
        goto L13
    L16:
        TL_iv.TL_pageListItemText r68 = (TL_iv.TL_pageListItemText) r67;
        r68.text = linkify(r68.text);
        goto L13
    L9:
        TL_iv.pageBlockDetails r36 = (TL_iv.pageBlockDetails) r32;
        r36.title = linkify(r36.title);
        linkifyBlocks(r36.blocks);
        goto L3
    }

    private static TL_iv.RichText linkifyPlain(TL_iv.textPlain r7) {
        if (LinkifyPort.WEB_URL != null) goto L5;
    L25:
        return r7;
    L5:
        if (TextUtils.isEmpty(r7.text) == true) goto L25;
        String r0 = r7.text;
        Matcher r1 = LinkifyPort.WEB_URL.matcher(r0);
        TL_iv.textConcat r2 = null;
        int r3 = 0;
    L9:
        if (r1.find() == false) goto L19;
        String r4 = r1.group();
        if (TextUtils.isEmpty(r4) == true) goto L9;
        if (r2 != null) goto L16;
        r2 = new TL_iv.textConcat();
    L16:
        if (r1.start() <= r3) goto L18;
        r2.texts.add(plainText(r0.substring(r3, r1.start())));
    L18:
        TL_iv.textUrl r32 = new TL_iv.textUrl();
        r32.text = plainText(r4);
        r32.url = r4;
        r2.texts.add(r32);
        r3 = r1.end();
        goto L9
    L19:
        if (r2 != null) goto L22;
        return r7;
    L22:
        if (r3 >= r0.length()) goto L24;
        r2.texts.add(plainText(r0.substring(r3)));
    L24:
        return r2;
    }

    private void openUrl(String r3) {
        if (TextUtils.isEmpty(r3) == true) goto L17;
        Utilities.CallbackReturn<URLSpan, Boolean> r0 = this.onLinkPress;
        if (r0 != null) goto L8;
        BaseFragment r2 = this.fragment;
        if (r2 == null) goto L16;
        AlertsCreator.showOpenUrlAlert(r2, r3, false, false);
        return;
    L16:
        return;
    L8:
        if (r0.run(new URLSpan(r3)).booleanValue() == false) goto L15;
        dismiss();
        return;
    L15:
        return;
    }

    private static TL_iv.RichMessage parseMarkdown(String r2) {
        TL_iv.RichMessage r0 = new TL_iv.RichMessage();
        MarkdownParser.parse(r2, r0.blocks);     // Catch: Throwable -> L5
    L8:
        if (r0.blocks.isEmpty() == false) goto L10;
        TL_iv.pageBlockParagraph r1 = new TL_iv.pageBlockParagraph();
        r1.text = plainText(r2);
        r0.blocks.add(r1);
    L10:
        linkifyBlocks(r0.blocks);
        return r0;
    L5:
        th = move-exception;
        FileLog.e(th);
        r0.blocks.clear();
        goto L8
    }

    private void parseMarkdownAsync() {
        if (this.parsingMarkdown == true) goto L9;
        String r0 = this.pendingMarkdown;
        if (r0 == null) goto L10;
        int r1 = this.parseGeneration;
        this.pendingMarkdown = null;
        this.parsingMarkdown = true;
        Utilities.globalQueue.postRunnable(new AiResponseAlert$$ExternalSyntheticLambda5(this, r0, r1));
        return;
    L10:
        return;
    }

    private static TL_iv.RichText plainText(String r1) {
        TL_iv.textPlain r0 = new TL_iv.textPlain();
        if (r1 != null) goto L5;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        r0.text = r1;
        return r0;
    }

    private void regenerate() {
        dropLastTurnFromHistory();
        showLoadingView();
        generate();
    }

    private void setOnInsertPress(Utilities.Callback3<String, CharSequence, TL_iv.RichMessage> r1) {
        this.onInsertPress = r1;
    }

    private void setResponse(String r1) {
        this.pendingMarkdown = r1;
        parseMarkdownAsync();
    }

    public static AiResponseAlert showAlert(BaseFragment r8, Client r9, String r10, String r11, boolean r12, boolean r13, Utilities.CallbackReturn<URLSpan, Boolean> r14, Runnable r15, Utilities.Callback3<String, CharSequence, TL_iv.RichMessage> r16) {
        AnonymousClass6 r0 = new AnonymousClass6(r8.getContext(), r9, r10, r11, r12, r8.getResourceProvider(), r15);
        r0.setNoforwards(r13);
        r0.setFragment(r8);
        r0.setOnLinkPress(r14);
        r0.setOnInsertPress(r16);
        if (r8.getParentActivity() == null) goto L5;
        r8.showDialog(r0);
    L5:
        r0.generate();
        return r0;
    }

    public static AiResponseAlert showAlert(BaseFragment r9, Client r10, String r11, boolean r12, boolean r13, Utilities.CallbackReturn<URLSpan, Boolean> r14, Runnable r15, Utilities.Callback3<String, CharSequence, TL_iv.RichMessage> r16) {
        return showAlert(r9, r10, r11, null, r12, r13, r14, r15, r16);
    }

    private void showLoadingView() {
        this.textSelectionHelper.clear(true);
        this.adapter.updateMainView(this.loadingTextView);
    }

    private void showThinking() {
        if (this.thinkingVisible == false) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(this.currentResponse) == false) goto L10;
        this.thinkingVisible = true;
        this.thinkingDotsView.start();
        this.adapter.updateMainView(this.thinkingDotsView);
        return;
    }

    private void stopGeneration() {
        this.client.stopRequest(this.currentRequestId);
        this.currentRequestId = null;
        stopThinking();
        if (TextUtils.isEmpty(this.currentResponse) == false) goto L6;
        dismiss();
        return;
    L6:
        this.adapter.updateMainView(this.responseContainer);
        updateMainButton(false);
    }

    private void stopThinking() {
        this.thinkingVisible = false;
        this.thinkingDotsView.stop();
    }

    private void updateContextView() {
        this.contextView.removeAllViews();
        if (this.useHistory == true) goto L5;
    L7:
        ArrayList<Message> r0 = null;
    L8:
        ArrayList<Message> r1 = new ArrayList();
        int r2 = 0;
        if (r0 == null) goto L21;
        int r3 = Math.max(0, r0.size() - 6);
    L12:
        if (r3 >= r0.size()) goto L21;
        Message r4 = r0.get(r3);
        if (r4 == null) goto L19;
        if (TextUtils.isEmpty(r4.content()) == true) goto L19;
        r1.add(r4);
        this.contextView.addView(createContextTextView(r4), LayoutHelper.createLinear(-1, -2));
    L19:
        r3 = r3 + 1;
    L21:
        if (this.contextView.getChildCount() <= 0) goto L23;
        boolean r02 = true;
    L24:
        LinearLayout r32 = this.contextView;
        if (r02 == false) goto L27;
        int r5 = 0;
    L28:
        r32.setVisibility(r5);
        View r33 = this.contextDivider;
        if (r02 == true) goto L32;
        r2 = 8;
    L32:
        r33.setVisibility(r2);
        formatContextAsync(r1);
        return;
    L27:
        r5 = 8;
        goto L28
    L23:
        r02 = false;
        goto L24
    L5:
        if (AiConfig.getShowResponseOnly() == true) goto L7;
        r0 = AiConfig.getConversationHistory();
        goto L8
    }

    private void updateMainButton(boolean r2) {
        updateMainButton(r2, false);
    }

    private void updateMainButton(boolean r4, boolean r5) {
        this.generating = r4;
        this.errorState = r5;
        ButtonWithCounterView r0 = this.mainButton;
        if (r4 == false) goto L5;
        int r1 = R.string.Stop;
    L8:
        r0.setText(LocaleController.getString(r1), true);
        boolean r02 = false;
        if (r4 == true) goto L14;
        if (r5 == true) goto L14;
        if (TextUtils.isEmpty(this.currentResponse) == true) goto L14;
        boolean r42 = true;
    L15:
        ActionBarMenuItem r52 = this.headerView.optionsButton;
        if (this.useHistory == true) goto L18;
    L20:
        r52.setSubItemShown(4, r02);
        AndroidUtilities.updateViewVisibilityAnimated(this.headerView.optionsButton, r42, 0.5f, true);
        if (this.onInsertPress == null) goto L24;
        AndroidUtilities.updateViewVisibilityAnimated(this.headerView.insertButton, r42, 0.5f, true);
        return;
    L24:
        return;
    L18:
        if (this.turnSaved == false) goto L20;
        r02 = true;
    L14:
        r42 = false;
        goto L15
    L5:
        if (r5 == false) goto L7;
        r1 = R.string.Retry;
        goto L8
    L7:
        r1 = R.string.Close;
        goto L8
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L6;
        this.loadingTextView.invalidate();
        this.promptTextView.invalidate();
        this.previewView.invalidate();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        this.client.stopRequest(this.currentRequestId);
        this.currentRequestId = null;
        stopThinking();
        this.pendingMarkdown = null;
        this.parseGeneration++;
        this.textSelectionHelper.clear(true);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
    }

    public void setFragment(BaseFragment r1) {
        this.fragment = r1;
    }

    public void setNoforwards(boolean r3) {
        RichMessageLayout.PreviewView r0 = this.previewView;
        if (r3 == false) goto L5;
        TextSelectionHelper.ArticleTextSelectionHelper r1 = null;
    L6:
        r0.setTextSelectionHelper(r1);
        if (r3 == false) goto L10;
        this.textSelectionHelper.clear(true);
    L10:
        if (getWindow() != null) goto L12;
        return;
    L12:
        if (r3 == false) goto L15;
        getWindow().addFlags(8192);
        return;
    L15:
        getWindow().clearFlags(8192);
        return;
    L5:
        r1 = this.textSelectionHelper;
        goto L6
    }

    public void setOnLinkPress(Utilities.CallbackReturn<URLSpan, Boolean> r1) {
        this.onLinkPress = r1;
    }

    public void setPrompt(String r4) {
        this.prompt = r4;
        LoadingTextView r0 = this.loadingTextView;
        r0.setText(Emoji.replaceEmoji(r4, r0.getPaint().getFontMetricsInt(), true));
        formatPrompt();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
    }
}
