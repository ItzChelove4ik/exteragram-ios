package org.telegram.ui.Stories.recorder;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingSpan;
import org.telegram.ui.Components.Paint.Views.LinkPreview;
import org.telegram.ui.Components.Paint.Views.StoryLinkPreviewDialog;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes7.dex */
public class StoryLinkSheet extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    private UniversalAdapter adapter;
    private ButtonWithCounterView button;
    private FrameLayout buttonContainer;
    private boolean captionAbove;
    public boolean editing;
    private boolean ignoreUrlEdit;
    private String lastCheckedStr;
    private boolean loading;
    private EditTextCell nameEditText;
    private boolean nameOpen;
    private boolean needRemoveDefPrefix;
    private boolean photoLarge;
    private int reqId;
    private final Runnable requestPreview;
    private EditTextCell urlEditText;
    private Pattern urlPattern;
    private TLRPC.WebPage webpage;
    private long webpageId;
    private Utilities.Callback<LinkPreview.WebPagePreview> whenDone;

    /* renamed from: org.telegram.ui.Stories.recorder.StoryLinkSheet$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ StoryLinkSheet this$0;
        final /* synthetic */ Runnable val$checkPaste;
        final /* synthetic */ String val$def;

        public AnonymousClass1(StoryLinkSheet r1, String r2, Runnable r3) {
            this.this$0 = r1;
            this.val$def = r2;
            this.val$checkPaste = r3;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            this.val$checkPaste.run();
            if (StoryLinkSheet.m20232$$Nest$fgetignoreUrlEdit(this.this$0) == false) goto L6;
            return;
        L6:
            if (StoryLinkSheet.m20233$$Nest$fgetneedRemoveDefPrefix(this.this$0) == false) goto L10;
            if (r4 == null) goto L10;
            String r42 = r4.toString().substring(this.val$def.length());
            StoryLinkSheet.m20235$$Nest$fputignoreUrlEdit(this.this$0, true);
            StoryLinkSheet.m20234$$Nest$fgeturlEditText(this.this$0).editText.setText(r42);
            StoryLinkSheet.m20234$$Nest$fgeturlEditText(this.this$0).editText.setSelection(0, StoryLinkSheet.m20234$$Nest$fgeturlEditText(this.this$0).editText.getText().length());
            StoryLinkSheet.m20235$$Nest$fputignoreUrlEdit(this.this$0, false);
            StoryLinkSheet.m20236$$Nest$fputneedRemoveDefPrefix(this.this$0, false);
            StoryLinkSheet.m20237$$Nest$mcheckEditURL(this.this$0, r42);
            return;
        L10:
            StoryLinkSheet r3 = this.this$0;
            if (r4 != null) goto L13;
            String r43 = null;
        L14:
            StoryLinkSheet.m20237$$Nest$mcheckEditURL(r3, r43);
            return;
        L13:
            r43 = r4.toString();
            goto L14
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r4, int r5, int r6, int r7) {
            if (StoryLinkSheet.m20232$$Nest$fgetignoreUrlEdit(this.this$0) == false) goto L5;
            return;
        L5:
            StoryLinkSheet r62 = this.this$0;
            boolean r0 = false;
            if (r4 != null) goto L8;
        L16:
            StoryLinkSheet.m20236$$Nest$fputneedRemoveDefPrefix(r62, r0);
            return;
        L8:
            if (r5 != this.val$def.length()) goto L16;
            if (r4.subSequence(0, r5).toString().equals(this.val$def) == false) goto L16;
            int r72 = r7 + r5;
            if (r4.length() < r72) goto L16;
            if (r4.subSequence(r5, r72).toString().startsWith(this.val$def) == false) goto L16;
            r0 = true;
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryLinkSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends DefaultItemAnimator {
        final /* synthetic */ StoryLinkSheet this$0;

        public AnonymousClass2(StoryLinkSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onMoveAnimationUpdate(r1);
            StoryLinkSheet.access$000(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.StoryLinkSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends UniversalAdapter {
        final /* synthetic */ StoryLinkSheet this$0;

        public AnonymousClass3(StoryLinkSheet r1, RecyclerListView r2, Context r3, int r4, int r5, boolean r6, Utilities.Callback2 r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.UniversalAdapter
        public int getThemedColor(int r2) {
            if (r2 != Theme.key_dialogBackgroundGray) goto L7;
            return -15921907;
        L7:
            return super.getThemedColor(r2);
        }
    }

    public static class WebpagePreviewView extends FrameLayout {
        private final ImageView closeView;
        private final ImageView imageView;
        private final ImageView loadingView;
        private final SpannableString messageLoading;
        private final AnimatedTextView messageView;
        private final Paint separatorPaint;
        private final SpannableString titleLoading;
        private final AnimatedTextView titleView;

        /* renamed from: org.telegram.ui.Stories.recorder.StoryLinkSheet$WebpagePreviewView$1, reason: invalid class name */
        public class AnonymousClass1 extends CircularProgressDrawable {
            final /* synthetic */ WebpagePreviewView this$0;

            public AnonymousClass1(WebpagePreviewView r1, float r2, float r3, int r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.CircularProgressDrawable, android.graphics.drawable.Drawable
            public int getIntrinsicHeight() {
                return AndroidUtilities.dp(26.0f);
            }

            @Override // org.telegram.ui.Components.CircularProgressDrawable, android.graphics.drawable.Drawable
            public int getIntrinsicWidth() {
                return AndroidUtilities.dp(26.0f);
            }
        }

        public static class Factory extends UItem.UItemFactory<WebpagePreviewView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem item(TLRPC.WebPage r1, View.OnClickListener r2) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.object = r1;
                r0.clickCallback = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                WebpagePreviewView r12 = (WebpagePreviewView) r1;
                Object r0 = r2.object;
                if ((r0 instanceof TLRPC.WebPage) == false) goto L5;
                TLRPC.WebPage r02 = (TLRPC.WebPage) r0;
            L6:
                r12.set(r02, r2.clickCallback, false);
                return;
            L5:
                r02 = null;
                goto L6
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public WebpagePreviewView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new WebpagePreviewView(r1);
            }
        }

        public WebpagePreviewView(Context r18) {
            super(r18);
            Paint r2 = new Paint(1);
            this.separatorPaint = r2;
            setWillNotDraw(false);
            r2.setColor(-16777216);
            ImageView r22 = new ImageView(r18);
            this.imageView = r22;
            ImageView.ScaleType r5 = ImageView.ScaleType.CENTER;
            r22.setScaleType(r5);
            r22.setImageResource(R.drawable.filled_link);
            r22.setColorFilter(new PorterDuffColorFilter(-15033089, PorterDuff.Mode.SRC_IN));
            addView(r22, LayoutHelper.createFrame(48, 48.0f, 19, 9.0f, 0.0f, 0.0f, 0.0f));
            ImageView r23 = new ImageView(r18);
            this.loadingView = r23;
            r23.setBackground(new AnonymousClass1(this, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(2.4f), -15033089));
            addView(r23, LayoutHelper.createFrame(48, 48.0f, 19, 9.0f, 0.0f, 0.0f, 0.0f));
            AnimatedTextView r24 = new AnimatedTextView(r18);
            this.titleView = r24;
            r24.setTextColor(-15033089);
            r24.setTextSize(AndroidUtilities.dp(14.21f));
            r24.setTypeface(AndroidUtilities.bold());
            r24.setEllipsizeByGradient(true);
            r24.getDrawable().setOverrideFullWidth(AndroidUtilities.displaySize.x);
            addView(r24, LayoutHelper.createFrame(-1, 24.0f, 55, 57.0f, 2.33f, 48.0f, 0.0f));
            AnimatedTextView r7 = new AnimatedTextView(r18);
            this.messageView = r7;
            r7.setTextColor(-8355712);
            r7.setTextSize(AndroidUtilities.dp(14.21f));
            r7.setEllipsizeByGradient(true);
            r7.getDrawable().setOverrideFullWidth(AndroidUtilities.displaySize.x);
            addView(r7, LayoutHelper.createFrame(-1, 24.0f, 55, 57.0f, 20.66f, 48.0f, 0.0f));
            int r6 = r24.getTextColor();
            SpannableString r8 = new SpannableString("x");
            this.titleLoading = r8;
            LoadingSpan r10 = new LoadingSpan(r24, AndroidUtilities.dp(200.0f));
            r10.setScaleY(0.8f);
            r10.setColors(Theme.multAlpha(r6, 0.4f), Theme.multAlpha(r6, 0.08f));
            r8.setSpan(r10, 0, r8.length(), 33);
            int r62 = r7.getTextColor();
            SpannableString r82 = new SpannableString("x");
            this.messageLoading = r82;
            LoadingSpan r9 = new LoadingSpan(r7, AndroidUtilities.dp(140.0f));
            r9.setScaleY(0.8f);
            r9.setColors(Theme.multAlpha(r62, 0.4f), Theme.multAlpha(r62, 0.08f));
            r82.setSpan(r9, 0, r82.length(), 33);
            ImageView r25 = new ImageView(r18);
            this.closeView = r25;
            r25.setColorFilter(new PorterDuffColorFilter(1694498815, PorterDuff.Mode.MULTIPLY));
            r25.setImageResource(R.drawable.input_clear);
            r25.setScaleType(r5);
            r25.setBackground(Theme.createSelectorDrawable(436207615, 1, AndroidUtilities.dp(18.0f)));
            addView(r25, LayoutHelper.createFrame(48, 48.0f, 21, 0.0f, 0.0f, 4.0f, 0.0f));
        }

        @Override // android.view.View
        public void onDraw(Canvas r14) {
            super.onDraw(r14);
            r14.drawRect(0.0f, 0.0f, getWidth(), AndroidUtilities.getShadowHeight(), this.separatorPaint);
            r14.drawRect(0.0f, getHeight() - AndroidUtilities.getShadowHeight(), getWidth(), getHeight(), this.separatorPaint);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        public void set(TLRPC.WebPage r9, View.OnClickListener r10, boolean r11) {
            if (r9 != null) goto L4;
        L6:
            boolean r0 = false;
        L7:
            ImageView r1 = this.imageView;
            float r2 = 0.0f;
            float r3 = 0.4f;
            if (r11 == false) goto L34;
            ViewPropertyAnimator r12 = r1.animate();
            if (r0 == false) goto L12;
            float r5 = 1.0f;
        L13:
            ViewPropertyAnimator r13 = r12.alpha(r5);
            if (r0 == false) goto L16;
            float r52 = 1.0f;
        L17:
            ViewPropertyAnimator r14 = r13.scaleX(r52);
            if (r0 == false) goto L20;
            float r53 = 1.0f;
        L21:
            ViewPropertyAnimator r15 = r14.scaleY(r53).setDuration(320);
            CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r15.setInterpolator(r7).start();
            ViewPropertyAnimator r16 = this.loadingView.animate();
            if (r0 == true) goto L25;
            r2 = 1.0f;
        L25:
            ViewPropertyAnimator r17 = r16.alpha(r2);
            if (r0 == false) goto L28;
            float r22 = 0.4f;
        L29:
            ViewPropertyAnimator r18 = r17.scaleX(r22);
            if (r0 == true) goto L33;
            r3 = 1.0f;
        L33:
            r18.scaleY(r3).setDuration(320).setInterpolator(r7).start();
        L58:
            AnimatedTextView r19 = this.titleView;
            if (r0 == true) goto L61;
            r19.setText(this.titleLoading, r11);
            this.messageView.setText(this.messageLoading, r11);
        L66:
            this.closeView.setOnClickListener(r10);
            return;
        L61:
            if (TextUtils.isEmpty(r9.site_name) == false) goto L63;
            String r02 = r9.title;
        L64:
            r19.setText(r02, r11);
            this.messageView.setText(r9.description, r11);
            goto L66
        L63:
            r02 = r9.site_name;
            goto L64
        L28:
            r22 = 1.0f;
            goto L29
        L20:
            r53 = 0.4f;
            goto L21
        L16:
            r52 = 0.4f;
            goto L17
        L12:
            r5 = 0.0f;
            goto L13
        L34:
            if (r0 == false) goto L36;
            float r54 = 1.0f;
        L37:
            r1.setAlpha(r54);
            ImageView r110 = this.imageView;
            if (r0 == false) goto L40;
            float r55 = 1.0f;
        L41:
            r110.setScaleX(r55);
            ImageView r111 = this.imageView;
            if (r0 == false) goto L44;
            float r56 = 1.0f;
        L45:
            r111.setScaleY(r56);
            ImageView r112 = this.loadingView;
            if (r0 == true) goto L49;
            r2 = 1.0f;
        L49:
            r112.setAlpha(r2);
            ImageView r113 = this.loadingView;
            if (r0 == false) goto L52;
            float r23 = 0.4f;
        L53:
            r113.setScaleX(r23);
            ImageView r114 = this.loadingView;
            if (r0 == true) goto L57;
            r3 = 1.0f;
        L57:
            r114.setScaleY(r3);
            goto L58
        L52:
            r23 = 1.0f;
            goto L53
        L44:
            r56 = 0.4f;
            goto L45
        L40:
            r55 = 0.4f;
            goto L41
        L36:
            r54 = 0.0f;
            goto L37
        L4:
            if ((r9 instanceof TLRPC.TL_webPagePending) == true) goto L6;
            r0 = true;
            goto L7
        }
    }

    /* renamed from: $r8$lambda$-fiak-FEk94X7uu_1Nf88IMnwJo, reason: not valid java name */
    public static /* synthetic */ void m20226$r8$lambda$fiakFEk94X7uu_1Nf88IMnwJo(StoryLinkSheet r0) {
        r0.lambda$new$7();
    }

    public static /* synthetic */ void $r8$lambda$8PSoKc41f1atSrfwRdePVSDA6kc(StoryLinkSheet r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$new$6(r1, r2);
    }

    /* renamed from: $r8$lambda$EozNCIjC4FyE7CF-1RdmYkefZvM, reason: not valid java name */
    public static /* synthetic */ void m20227$r8$lambda$EozNCIjC4FyE7CF1RdmYkefZvM(StoryLinkSheet r0, LinkPreview.WebPagePreview r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$HeYk9aFStH3_MKH0O5WihPGhOys(StoryLinkSheet r0) {
        r0.lambda$show$8();
    }

    public static /* synthetic */ void $r8$lambda$PGapftvuihFvsqX5jhjQNJPKXbQ(StoryLinkSheet r0, Context r1, PreviewView r2, View r3, int r4) {
        r0.lambda$new$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$RSwQvt9O42GQfMefAyw0NSvTKAw(StoryLinkSheet r0) {
        r0.processDone();
    }

    /* renamed from: $r8$lambda$S-RcrDTFzEAWko6kiv4w2P0J6So, reason: not valid java name */
    public static /* synthetic */ void m20228$r8$lambda$SRcrDTFzEAWko6kiv4w2P0J6So(StoryLinkSheet r0, View r1) {
        r0.closePreview(r1);
    }

    /* renamed from: $r8$lambda$UCA_kE-DxeKQARc3NffROX5SFk8, reason: not valid java name */
    public static /* synthetic */ void m20229$r8$lambda$UCA_kEDxeKQARc3NffROX5SFk8(StoryLinkSheet r0, String r1, TextView r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$a_mC8mzJ-G5VF1rFs5t6RWobNwk, reason: not valid java name */
    public static /* synthetic */ void m20230$r8$lambda$a_mC8mzJG5VF1rFs5t6RWobNwk(StoryLinkSheet r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$fFNnXdGWMczy73ndPT1HOh0MNdI(StoryLinkSheet r0, Runnable r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$p-c3vNnkgwB-igIBa_BIARYg29A, reason: not valid java name */
    public static /* synthetic */ void m20231$r8$lambda$pc3vNnkgwBigIBa_BIARYg29A(StoryLinkSheet r0, TLObject r1) {
        r0.lambda$new$5(r1);
    }

    /* renamed from: -$$Nest$fgetignoreUrlEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20232$$Nest$fgetignoreUrlEdit(StoryLinkSheet r0) {
        return r0.ignoreUrlEdit;
    }

    /* renamed from: -$$Nest$fgetneedRemoveDefPrefix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20233$$Nest$fgetneedRemoveDefPrefix(StoryLinkSheet r0) {
        return r0.needRemoveDefPrefix;
    }

    /* renamed from: -$$Nest$fgeturlEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextCell m20234$$Nest$fgeturlEditText(StoryLinkSheet r0) {
        return r0.urlEditText;
    }

    /* renamed from: -$$Nest$fputignoreUrlEdit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20235$$Nest$fputignoreUrlEdit(StoryLinkSheet r0, boolean r1) {
        r0.ignoreUrlEdit = r1;
    }

    /* renamed from: -$$Nest$fputneedRemoveDefPrefix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20236$$Nest$fputneedRemoveDefPrefix(StoryLinkSheet r0, boolean r1) {
        r0.needRemoveDefPrefix = r1;
    }

    /* renamed from: -$$Nest$mcheckEditURL, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20237$$Nest$mcheckEditURL(StoryLinkSheet r0, String r1) {
        r0.checkEditURL(r1);
    }

    public StoryLinkSheet(Context r18, Theme.ResourcesProvider r19, PreviewView r20, Utilities.Callback<LinkPreview.WebPagePreview> r21) {
        super(r18, null, true, false, false, true, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r19);
        this.requestPreview = new StoryLinkSheet$$ExternalSyntheticLambda1(this);
        this.whenDone = r21;
        fixNavigationBar();
        setSlidingActionBar();
        this.headerPaddingTop = AndroidUtilities.dp(4.0f);
        this.headerPaddingBottom = AndroidUtilities.dp(-15.0f);
        EditTextCell r0 = new EditTextCell(r18, LocaleController.getString(R.string.StoryLinkURLPlaceholder), true, false, -1, r19);
        this.urlEditText = r0;
        r0.whenHitEnter(new StoryLinkSheet$$ExternalSyntheticLambda2(this));
        this.urlEditText.editText.setHandlesColor(-12476440);
        this.urlEditText.editText.setCursorColor(-11230757);
        this.urlEditText.editText.setText("https://");
        this.urlEditText.editText.setSelection(8);
        TextView r02 = new TextView(getContext());
        r02.setTextSize(1, 12.0f);
        r02.setTypeface(AndroidUtilities.bold());
        r02.setText(LocaleController.getString(R.string.Paste));
        r02.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        r02.setGravity(17);
        int r2 = getThemedColor(Theme.key_windowBackgroundWhiteBlueText2);
        r02.setTextColor(r2);
        r02.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.multAlpha(r2, 0.12f), Theme.multAlpha(r2, 0.15f)));
        ScaleStateListAnimator.apply(r02, 0.1f, 1.5f);
        this.urlEditText.addView(r02, LayoutHelper.createFrame(-2, 26.0f, 21, 0.0f, 4.0f, 24.0f, 3.0f));
        StoryLinkSheet$$ExternalSyntheticLambda3 r22 = new StoryLinkSheet$$ExternalSyntheticLambda3(this, "https://", r02);
        r02.setOnClickListener(new StoryLinkSheet$$ExternalSyntheticLambda4(this, r22));
        r22.run();
        this.urlEditText.editText.addTextChangedListener(new AnonymousClass1(this, "https://", r22));
        EditTextCell r03 = new EditTextCell(r18, LocaleController.getString(R.string.StoryLinkNamePlaceholder), true, false, -1, r19);
        this.nameEditText = r03;
        r03.whenHitEnter(new StoryLinkSheet$$ExternalSyntheticLambda2(this));
        this.buttonContainer = new FrameLayout(r18);
        ButtonWithCounterView r04 = new ButtonWithCounterView(r18, r19);
        this.button = r04;
        r04.setText(LocaleController.getString(R.string.StoryLinkAdd), false);
        this.button.setOnClickListener(new StoryLinkSheet$$ExternalSyntheticLambda5(this));
        this.button.setEnabled(containsURL(this.urlEditText.getText().toString()));
        this.buttonContainer.addView(this.button, LayoutHelper.createFrame(-1, 48.0f, 119, 10.0f, 10.0f, 10.0f, 10.0f));
        this.topPadding = 0.2f;
        this.takeTranslationIntoAccount = true;
        this.smoothKeyboardAnimationEnabled = true;
        this.smoothKeyboardByBottom = true;
        AnonymousClass2 r05 = new AnonymousClass2(this);
        r05.setSupportsChangeAnimations(false);
        r05.setDelayAnimations(false);
        r05.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r05.setDurations(350);
        this.recyclerListView.setItemAnimator(r05);
        RecyclerListView r06 = this.recyclerListView;
        int r23 = this.backgroundPaddingLeft;
        r06.setPadding(r23, 0, r23, 0);
        this.recyclerListView.setOnItemClickListener(new StoryLinkSheet$$ExternalSyntheticLambda6(this, r18, r20));
        UniversalAdapter r07 = this.adapter;
        if (r07 == null) goto L6;
        r07.update(false);
        return;
    }

    public static /* synthetic */ ViewGroup access$000(StoryLinkSheet r0) {
        return r0.containerView;
    }

    private void checkEditURL(String r5) {
        if (r5 != null) goto L5;
        return;
    L5:
        if (TextUtils.equals(r5, this.lastCheckedStr) == false) goto L7;
        return;
    L7:
        this.lastCheckedStr = r5;
        boolean r52 = containsURL(r5);
        AndroidUtilities.cancelRunOnUIThread(this.requestPreview);
        boolean r0 = this.loading;
        if (r52 == false) goto L16;
        if (r0 == true) goto L11;
    L12:
        this.loading = true;
        this.webpage = null;
        UniversalAdapter r02 = this.adapter;
        if (r02 == null) goto L15;
        r02.update(true);
    L15:
        AndroidUtilities.runOnUIThread(this.requestPreview, 700);
    L25:
        this.button.setEnabled(r52);
        return;
    L11:
        if (this.webpage == null) goto L15;
    L16:
        if (r0 == false) goto L18;
    L19:
        this.loading = false;
        this.webpage = null;
        if (this.reqId == 0) goto L22;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
        this.reqId = 0;
    L22:
        UniversalAdapter r03 = this.adapter;
        if (r03 == null) goto L25;
        r03.update(true);
        goto L25
    L18:
        if (this.webpage == null) goto L25;
        goto L19
    }

    private void closePreview(View r4) {
        this.loading = false;
        this.webpage = null;
        if (this.reqId == 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
        this.reqId = 0;
    L5:
        UniversalAdapter r3 = this.adapter;
        if (r3 == null) goto L9;
        r3.update(true);
        return;
    }

    private boolean containsURL(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L7;
        return false;
    L7:
        if (this.urlPattern != null) goto L10;
        this.urlPattern = Pattern.compile("((https?)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?]?.+)");
    L10:
        return this.urlPattern.matcher(r2).find();
    }

    public static boolean isPreviewEmpty(TLRPC.WebPage r1) {
        if ((r1 instanceof TLRPC.TL_webPagePending) == false) goto L5;
        return true;
    L5:
        if (TextUtils.isEmpty(r1.title) == true) goto L7;
        return false;
    L7:
        if (TextUtils.isEmpty(r1.description) == true) goto L14;
        return false;
    L14:
        return true;
    }

    private /* synthetic */ void lambda$new$0(String r3, TextView r4) {
        ClipboardManager r0 = (ClipboardManager) getContext().getSystemService("clipboard");
        if (TextUtils.isEmpty(this.urlEditText.editText.getText()) == false) goto L5;
    L8:
        if (r0 != null) goto L10;
    L12:
        boolean r2 = false;
    L13:
        ViewPropertyAnimator r32 = r4.animate();
        float r42 = 1.0f;
        if (r2 == false) goto L16;
        float r02 = 1.0f;
    L17:
        ViewPropertyAnimator r33 = r32.alpha(r02);
        if (r2 == false) goto L20;
        float r1 = 1.0f;
    L21:
        ViewPropertyAnimator r34 = r33.scaleX(r1);
        if (r2 == true) goto L25;
        r42 = 0.7f;
    L25:
        r34.scaleY(r42).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(300).start();
        return;
    L20:
        r1 = 0.7f;
        goto L21
    L16:
        r02 = 0.0f;
        goto L17
    L10:
        if (r0.hasPrimaryClip() == false) goto L12;
        r2 = true;
        goto L13
    L5:
        if (TextUtils.equals(this.urlEditText.editText.getText(), r3) == true) goto L8;
        if (TextUtils.isEmpty(this.urlEditText.editText.getText().toString()) == false) goto L12;
        goto L8
    }

    private /* synthetic */ void lambda$new$1(Runnable r3, View r4) {
        CharSequence r42 = ((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip().getItemAt(0).coerceToText(getContext());     // Catch: Exception -> L5
    L7:
        if (r42 == null) goto L9;
        this.urlEditText.editText.setText(r42.toString());
        EditTextCaption r2 = this.urlEditText.editText;
        r2.setSelection(0, r2.getText().length());
    L9:
        r3.run();
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        r42 = null;
        goto L7
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        processDone();
    }

    private /* synthetic */ void lambda$new$3(LinkPreview.WebPagePreview r2) {
        if (r2 != null) goto L5;
        closePreview(null);
        return;
    L5:
        this.photoLarge = r2.largePhoto;
        this.captionAbove = r2.captionAbove;
    }

    private /* synthetic */ void lambda$new$4(Context r3, PreviewView r4, View r5, int r6) {
        UItem r62 = this.adapter.getItem(r6 - 1);
        if (r62 != null) goto L6;
        return;
    L6:
        if (r62.instanceOf(WebpagePreviewView.Factory.class) == false) goto L18;
        TLRPC.WebPage r0 = this.webpage;
        if (r0 == null) goto L18;
        if (isPreviewEmpty(r0) == true) goto L18;
        StoryLinkPreviewDialog r52 = new StoryLinkPreviewDialog(r3, this.currentAccount);
        LinkPreview.WebPagePreview r32 = new LinkPreview.WebPagePreview();
        r32.url = this.urlEditText.editText.getText().toString();
        if (this.nameOpen == false) goto L14;
        String r63 = this.nameEditText.editText.getText().toString();
    L15:
        r32.name = r63;
        r32.webpage = this.webpage;
        r32.largePhoto = this.photoLarge;
        r32.captionAbove = this.captionAbove;
        r52.set(r32, new StoryLinkSheet$$ExternalSyntheticLambda8(this));
        r52.setStoryPreviewView(r4);
        r52.show();
        return;
    L14:
        r63 = null;
    L18:
        if (r62.id == 2) goto L20;
        return;
    L20:
        if ((r5 instanceof TextCheckCell) == false) goto L28;
        boolean r33 = !this.nameOpen;
        this.nameOpen = r33;
        ((TextCheckCell) r5).setChecked(r33);
        this.adapter.update(true);
        if (this.nameOpen == false) goto L25;
        this.nameEditText.requestFocus();
        return;
    L25:
        this.urlEditText.requestFocus();
        return;
    }

    private /* synthetic */ void lambda$new$5(TLObject r8) {
        boolean r1 = false;
        if ((r8 instanceof TL_account.webPagePreview) == false) goto L7;
        TL_account.webPagePreview r82 = (TL_account.webPagePreview) r8;
        MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r82.chats, false);
        TLRPC.MessageMedia r83 = r82.media;
        if ((r83 instanceof TLRPC.TL_messageMediaWebPage) == false) goto L7;
        TLRPC.TL_messageMediaWebPage r84 = (TLRPC.TL_messageMediaWebPage) r83;
    L9:
        if (r84 == null) goto L18;
        TLRPC.WebPage r85 = r84.webpage;
        this.webpage = r85;
        if (isPreviewEmpty(r85) == false) goto L17;
        TLRPC.WebPage r86 = this.webpage;
        if (r86 != null) goto L15;
        long r5 = 0;
    L16:
        this.webpageId = r5;
        this.webpage = null;
    L20:
        if (this.webpageId == 0) goto L22;
        r1 = true;
    L22:
        this.loading = r1;
        UniversalAdapter r7 = this.adapter;
        if (r7 == null) goto L26;
        r7.update(true);
        return;
    L26:
        return;
    L15:
        r5 = r86.id;
        goto L16
    L17:
        this.webpageId = 0;
        goto L20
    L18:
        this.webpage = null;
        this.webpageId = 0;
    L7:
        r84 = null;
        goto L9
    }

    private /* synthetic */ void lambda$new$6(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new StoryLinkSheet$$ExternalSyntheticLambda11(this, r1));
    }

    private /* synthetic */ void lambda$new$7() {
        TL_account.getWebPagePreview r0 = new TL_account.getWebPagePreview();
        r0.message = this.urlEditText.editText.getText().toString();
        this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new StoryLinkSheet$$ExternalSyntheticLambda9(this));
    }

    private /* synthetic */ void lambda$show$8() {
        if (isShowing() == true) goto L5;
        return;
    L5:
        this.urlEditText.editText.requestFocus();
        AndroidUtilities.showKeyboard(this.urlEditText.editText);
    }

    private void processDone() {
        if (this.button.isEnabled() == true) goto L6;
        return;
    L6:
        if (this.whenDone == null) goto L12;
        LinkPreview.WebPagePreview r0 = new LinkPreview.WebPagePreview();
        r0.url = this.urlEditText.editText.getText().toString();
        if (this.nameOpen == false) goto L10;
        String r1 = this.nameEditText.editText.getText().toString();
    L11:
        r0.name = r1;
        r0.webpage = this.webpage;
        r0.largePhoto = this.photoLarge;
        r0.captionAbove = this.captionAbove;
        this.whenDone.run(r0);
        this.whenDone = null;
        goto L12
    L10:
        r1 = null;
    L12:
        dismiss();
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r10) {
        AnonymousClass3 r0 = new AnonymousClass3(this, this.recyclerListView, getContext(), this.currentAccount, 0, true, new StoryLinkSheet$$ExternalSyntheticLambda0(this), this.resourcesProvider);
        this.adapter = r0;
        return r0;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r8, int r9, Object... r10) {
        if (r8 == NotificationCenter.didReceivedWebpagesInUpdates) goto L5;
        return;
    L5:
        if (this.webpageId == 0) goto L29;
        LongSparseArray r92 = (LongSparseArray) r10[0];
        int r102 = 0;
    L9:
        if (r102 >= r92.size()) goto L30;
        TLRPC.WebPage r2 = (TLRPC.WebPage) r92.valueAt(r102);
        if (r2 == null) goto L22;
        if (this.webpageId != r2.id) goto L22;
        if (isPreviewEmpty(r2) == false) goto L18;
        r2 = null;
    L18:
        this.webpage = r2;
        this.loading = false;
        this.webpageId = 0;
        UniversalAdapter r7 = this.adapter;
        if (r7 == null) goto L28;
        r7.update(true);
        return;
    L28:
        return;
    L22:
        r102 = r102 + 1;
        goto L9
    L30:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        AndroidUtilities.hideKeyboard(this.urlEditText.editText);
        AndroidUtilities.hideKeyboard(this.nameEditText.editText);
        super.dismiss();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
    }

    public void fillItems(ArrayList<UItem> r3, UniversalAdapter r4) {
        if (this.loading == false) goto L5;
    L6:
        r3.add(WebpagePreviewView.Factory.item(this.webpage, new StoryLinkSheet$$ExternalSyntheticLambda10(this)));
    L7:
        r3.add(UItem.asCustom(this.urlEditText));
        r3.add(UItem.asShadow(1, null));
        r3.add(UItem.asCheck(2, LocaleController.getString(R.string.StoryLinkNameHeader)).setChecked(this.nameOpen));
        if (this.nameOpen == false) goto L10;
        r3.add(UItem.asCustom(this.nameEditText));
    L10:
        r3.add(UItem.asShadow(3, null));
        r3.add(UItem.asCustom(this.buttonContainer));
        return;
    L5:
        if (this.webpage == null) goto L7;
        goto L6
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.StoryLinkCreate);
    }

    public void set(LinkPreview.WebPagePreview r5) {
        this.ignoreUrlEdit = true;
        this.editing = true;
        if (r5 == null) goto L5;
        this.webpage = r5.webpage;
        this.loading = false;
        this.urlEditText.setText(r5.url);
        this.nameEditText.setText(r5.name);
        this.nameOpen = true ^ TextUtils.isEmpty(r5.name);
        this.captionAbove = r5.captionAbove;
        this.photoLarge = r5.largePhoto;
    L6:
        this.button.setText(LocaleController.getString(R.string.StoryLinkEdit), false);
        UniversalAdapter r52 = this.adapter;
        if (r52 == null) goto L9;
        r52.update(false);
    L9:
        this.button.setEnabled(containsURL(this.urlEditText.getText().toString()));
        this.ignoreUrlEdit = false;
        return;
    L5:
        this.urlEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.nameEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.captionAbove = true;
        this.photoLarge = false;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        super.show();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.didReceivedWebpagesInUpdates);
        AndroidUtilities.runOnUIThread(new StoryLinkSheet$$ExternalSyntheticLambda7(this), 150);
    }
}
