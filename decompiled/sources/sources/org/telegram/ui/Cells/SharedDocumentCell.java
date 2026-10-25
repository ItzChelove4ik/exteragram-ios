package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.util.Date;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.DotDividerSpan;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LineProgressView;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.FilteredSearchView;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes6.dex */
public class SharedDocumentCell extends FrameLayout implements DownloadController.FileDownloadProgressListener {
    private int TAG;
    private CharSequence caption;
    private TextView captionTextView;
    private CheckBox2 checkBox;
    private int currentAccount;
    private AnimatedEmojiSpan.TextViewEmojis dateTextView;
    private SpannableStringBuilder dotSpan;
    private long downloadedSize;
    private boolean drawDownloadIcon;
    float enterAlpha;
    private TextView extTextView;
    FlickerLoadingView globalGradientView;
    boolean ignoreRequestLayout;
    private boolean loaded;
    private boolean loading;
    private MessageObject message;
    private TextView nameTextView;
    private boolean needDivider;
    private ImageView placeholderImageView;
    private LineProgressView progressView;
    private final Theme.ResourcesProvider resourcesProvider;
    public TextView rightDateTextView;
    boolean showReorderIcon;
    float showReorderIconProgress;
    private RLottieDrawable statusDrawable;
    private RLottieImageView statusImageView;
    private BackupImageView thumbImageView;
    private int viewType;

    /* renamed from: org.telegram.ui.Cells.SharedDocumentCell$1, reason: invalid class name */
    public class AnonymousClass1 extends BackupImageView {
        final /* synthetic */ SharedDocumentCell this$0;

        public AnonymousClass1(SharedDocumentCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r3) {
            float r1 = 1.0f;
            if (SharedDocumentCell.m7013$$Nest$fgetthumbImageView(this.this$0).getImageReceiver().hasBitmapImage() == false) goto L5;
            r1 = 1.0f - SharedDocumentCell.m7013$$Nest$fgetthumbImageView(this.this$0).getImageReceiver().getCurrentAlpha();
        L5:
            SharedDocumentCell.m7011$$Nest$fgetextTextView(this.this$0).setAlpha(r1);
            SharedDocumentCell.m7012$$Nest$fgetplaceholderImageView(this.this$0).setAlpha(r1);
            super.onDraw(r3);
        }
    }

    /* renamed from: -$$Nest$fgetextTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m7011$$Nest$fgetextTextView(SharedDocumentCell r0) {
        return r0.extTextView;
    }

    /* renamed from: -$$Nest$fgetplaceholderImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m7012$$Nest$fgetplaceholderImageView(SharedDocumentCell r0) {
        return r0.placeholderImageView;
    }

    /* renamed from: -$$Nest$fgetthumbImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m7013$$Nest$fgetthumbImageView(SharedDocumentCell r0) {
        return r0.thumbImageView;
    }

    public SharedDocumentCell(Context r2, int r3) {
        this(r2, r3, null);
    }

    public SharedDocumentCell(Context r29, int r30, Theme.ResourcesProvider r31) {
        super(r29);
        this.drawDownloadIcon = true;
        int r5 = UserConfig.selectedAccount;
        this.currentAccount = r5;
        this.enterAlpha = 1.0f;
        this.resourcesProvider = r31;
        this.viewType = r30;
        this.TAG = DownloadController.getInstance(r5).generateObserverTag();
        ImageView r52 = new ImageView(r29);
        this.placeholderImageView = r52;
        if (r30 != 1) goto L16;
        boolean r11 = LocaleController.isRTL;
        if (r11 == false) goto L7;
        int r12 = 5;
    L8:
        int r15 = r12 | 48;
        if (r11 == false) goto L11;
        float r16 = 0.0f;
    L12:
        if (r11 == false) goto L14;
        float r18 = 15.0f;
    L15:
        addView(r52, LayoutHelper.createFrame(42, 42.0f, r15, r16, 12.0f, r18, 0.0f));
    L28:
        TextView r53 = new TextView(r29);
        this.extTextView = r53;
        r53.setTextColor(getThemedColor(Theme.key_files_iconText));
        this.extTextView.setTextSize(1, 14.0f);
        this.extTextView.setTypeface(AndroidUtilities.bold());
        this.extTextView.setLines(1);
        this.extTextView.setMaxLines(1);
        this.extTextView.setSingleLine(true);
        this.extTextView.setGravity(17);
        TextView r54 = this.extTextView;
        TextUtils.TruncateAt r122 = TextUtils.TruncateAt.END;
        r54.setEllipsize(r122);
        this.extTextView.setImportantForAccessibility(2);
        View r55 = this.extTextView;
        if (r30 != 1) goto L42;
        boolean r152 = LocaleController.isRTL;
        if (r152 == false) goto L33;
        int r162 = 5;
    L34:
        int r19 = r162 | 48;
        if (r152 == false) goto L37;
        float r20 = 0.0f;
    L38:
        if (r152 == false) goto L40;
        float r22 = 20.0f;
    L41:
        addView(r55, LayoutHelper.createFrame(32, -2.0f, r19, r20, 28.0f, r22, 0.0f));
    L54:
        AnonymousClass1 r56 = new AnonymousClass1(this, r29);
        this.thumbImageView = r56;
        r56.setRoundRadius(AndroidUtilities.dp(8.0f));
        View r57 = this.thumbImageView;
        if (r30 != 1) goto L68;
        boolean r7 = LocaleController.isRTL;
        if (r7 == false) goto L59;
        int r163 = 5;
    L60:
        int r192 = r163 | 48;
        if (r7 == false) goto L63;
        float r202 = 0.0f;
    L64:
        if (r7 == false) goto L66;
        float r222 = 16.0f;
    L67:
        addView(r57, LayoutHelper.createFrame(42, 42.0f, r192, r202, 12.0f, r222, 0.0f));
    L80:
        TextView r58 = new TextView(r29);
        this.nameTextView = r58;
        int r72 = Theme.key_windowBackgroundWhiteBlackText;
        r58.setTextColor(getThemedColor(r72));
        this.nameTextView.setTextSize(1, 16.0f);
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        this.nameTextView.setEllipsize(r122);
        TextView r59 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L83;
        int r9 = 5;
    L84:
        r59.setGravity(r9 | 16);
        if (r30 != 1) goto L98;
        this.nameTextView.setLines(1);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        View r6 = this.nameTextView;
        boolean r73 = LocaleController.isRTL;
        if (r73 == false) goto L89;
        int r14 = 5;
    L90:
        int r203 = r14 | 48;
        if (r73 == false) goto L93;
        float r21 = 8.0f;
    L94:
        if (r73 == false) goto L96;
        float r23 = 72.0f;
    L97:
        addView(r6, LayoutHelper.createFrame(-1, -2.0f, r203, r21, 9.0f, r23, 0.0f));
    L142:
        this.statusDrawable = new RLottieDrawable(R.raw.download_arrow, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), true, null);
        RLottieImageView r62 = new RLottieImageView(r29);
        this.statusImageView = r62;
        r62.setAnimation(this.statusDrawable);
        this.statusImageView.setVisibility(4);
        RLottieImageView r63 = this.statusImageView;
        int r112 = Theme.key_sharedMedia_startStopLoadIcon;
        r63.setColorFilter(new PorterDuffColorFilter(getThemedColor(r112), PorterDuff.Mode.SRC_IN));
        View r64 = this.statusImageView;
        if (r30 != 1) goto L156;
        boolean r142 = LocaleController.isRTL;
        if (r142 == false) goto L147;
        int r153 = 5;
    L148:
        int r223 = r153 | 48;
        if (r142 == false) goto L151;
        float r232 = 8.0f;
    L152:
        if (r142 == false) goto L154;
        float r25 = 72.0f;
    L155:
        addView(r64, LayoutHelper.createFrame(14, 14.0f, r223, r232, 37.0f, r25, 0.0f));
    L168:
        AnimatedEmojiSpan.TextViewEmojis r65 = new AnimatedEmojiSpan.TextViewEmojis(r29);
        this.dateTextView = r65;
        r65.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText3));
        this.dateTextView.setLines(1);
        this.dateTextView.setMaxLines(1);
        this.dateTextView.setSingleLine(true);
        this.dateTextView.setEllipsize(r122);
        AnimatedEmojiSpan.TextViewEmojis r66 = this.dateTextView;
        if (LocaleController.isRTL == false) goto L171;
        int r10 = 5;
    L172:
        r66.setGravity(r10 | 16);
        NotificationCenter.listenEmojiLoading(this.dateTextView);
        AnimatedEmojiSpan.TextViewEmojis r67 = this.dateTextView;
        if (r30 != 1) goto L186;
        r67.setTextSize(1, 13.0f);
        View r510 = this.dateTextView;
        boolean r68 = LocaleController.isRTL;
        if (r68 == false) goto L177;
        int r102 = 5;
    L178:
        int r224 = r102 | 48;
        if (r68 == false) goto L181;
        float r233 = 8.0f;
    L182:
        if (r68 == false) goto L184;
        float r252 = 72.0f;
    L185:
        addView(r510, LayoutHelper.createFrame(-1, -2.0f, r224, r233, 34.0f, r252, 0.0f));
    L198:
        LineProgressView r511 = new LineProgressView(r29);
        this.progressView = r511;
        r511.setShowAnimationBehavior(1);
        this.progressView.setHideAnimationBehavior(2);
        this.progressView.setProgressColor(getThemedColor(r112));
        View r512 = this.progressView;
        boolean r69 = LocaleController.isRTL;
        if (r69 == false) goto L201;
        int r103 = 5;
    L202:
        int r212 = r103 | 80;
        if (r69 == false) goto L205;
        float r225 = 0.0f;
    L206:
        if (r69 == false) goto L208;
        float r24 = 72.0f;
    L209:
        addView(r512, LayoutHelper.createFrame(-1, 2.0f, r212, r225, 0.0f, r24, 0.0f));
        CheckBox2 r513 = new CheckBox2(r29, 21, r31);
        this.checkBox = r513;
        r513.setVisibility(4);
        this.checkBox.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        this.checkBox.setDrawUnchecked(false);
        this.checkBox.setDrawBackgroundAsArc(2);
        View r1 = this.checkBox;
        if (r30 != 1) goto L223;
        boolean r3 = LocaleController.isRTL;
        if (r3 == false) goto L214;
        int r164 = 5;
    L215:
        int r193 = r164 | 48;
        if (r3 == false) goto L218;
        float r204 = 0.0f;
    L219:
        if (r3 == false) goto L221;
        float r226 = 38.0f;
    L222:
        addView(r1, LayoutHelper.createFrame(24, 24.0f, r193, r204, 36.0f, r226, 0.0f));
    L235:
        if (r30 != 2) goto L238;
        SpannableStringBuilder r13 = new SpannableStringBuilder(".");
        this.dotSpan = r13;
        r13.setSpan(new DotDividerSpan(), 0, 1, 0);
        return;
    L238:
        return;
    L221:
        r226 = 0.0f;
        goto L222
    L218:
        r204 = 38.0f;
        goto L219
    L214:
        r164 = 3;
        goto L215
    L223:
        boolean r32 = LocaleController.isRTL;
        if (r32 == false) goto L226;
        int r165 = 5;
    L227:
        int r194 = r165 | 48;
        if (r32 == false) goto L230;
        float r205 = 0.0f;
    L231:
        if (r32 == false) goto L233;
        float r227 = 33.0f;
    L234:
        addView(r1, LayoutHelper.createFrame(24, 24.0f, r194, r205, 28.0f, r227, 0.0f));
        goto L235
    L233:
        r227 = 0.0f;
        goto L234
    L230:
        r205 = 33.0f;
        goto L231
    L226:
        r165 = 3;
        goto L227
    L208:
        r24 = 0.0f;
        goto L209
    L205:
        r225 = 72.0f;
        goto L206
    L201:
        r103 = 3;
        goto L202
    L184:
        r252 = 8.0f;
        goto L185
    L181:
        r233 = 72.0f;
        goto L182
    L177:
        r102 = 3;
        goto L178
    L186:
        r67.setTextSize(1, 13.0f);
        View r514 = this.dateTextView;
        boolean r610 = LocaleController.isRTL;
        if (r610 == false) goto L189;
        int r104 = 5;
    L190:
        int r228 = r104 | 48;
        if (r610 == false) goto L193;
        float r234 = 8.0f;
    L194:
        if (r610 == false) goto L196;
        float r253 = 72.0f;
    L197:
        addView(r514, LayoutHelper.createFrame(-1, -2.0f, r228, r234, 30.0f, r253, 0.0f));
        goto L198
    L196:
        r253 = 8.0f;
        goto L197
    L193:
        r234 = 72.0f;
        goto L194
    L189:
        r104 = 3;
        goto L190
    L171:
        r10 = 3;
        goto L172
    L154:
        r25 = 8.0f;
        goto L155
    L151:
        r232 = 70.0f;
        goto L152
    L147:
        r153 = 3;
        goto L148
    L156:
        boolean r143 = LocaleController.isRTL;
        if (r143 == false) goto L159;
        int r154 = 5;
    L160:
        int r229 = r154 | 48;
        if (r143 == false) goto L163;
        float r235 = 8.0f;
    L164:
        if (r143 == false) goto L166;
        float r254 = 72.0f;
    L167:
        addView(r64, LayoutHelper.createFrame(14, 14.0f, r229, r235, 33.0f, r254, 0.0f));
        goto L168
    L166:
        r254 = 8.0f;
        goto L167
    L163:
        r235 = 70.0f;
        goto L164
    L159:
        r154 = 3;
        goto L160
    L96:
        r23 = 8.0f;
        goto L97
    L93:
        r21 = 72.0f;
        goto L94
    L89:
        r14 = 3;
        goto L90
    L98:
        if (r30 != 2) goto L130;
        LinearLayout r105 = new LinearLayout(r29);
        r105.setOrientation(0);
        boolean r195 = LocaleController.isRTL;
        if (r195 == false) goto L102;
        int r206 = 5;
    L103:
        int r236 = r206 | 48;
        if (r195 == false) goto L106;
        float r242 = 16.0f;
    L107:
        if (r195 == false) goto L109;
        float r26 = 72.0f;
    L110:
        addView(r105, LayoutHelper.createFrame(-1, -2.0f, r236, r242, 5.0f, r26, 0.0f));
        TextView r144 = new TextView(r29);
        this.rightDateTextView = r144;
        r144.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText3));
        this.rightDateTextView.setTextSize(1, 14.0f);
        if (LocaleController.isRTL == true) goto L113;
        r105.addView(this.nameTextView, LayoutHelper.createLinear(-2, -2, 1.0f));
        r105.addView(this.rightDateTextView, LayoutHelper.createLinear(-2, -2, 0.0f, 4, 0, 0, 0));
    L114:
        this.nameTextView.setMaxLines(2);
        TextView r611 = new TextView(r29);
        this.captionTextView = r611;
        r611.setTextColor(getThemedColor(r72));
        this.captionTextView.setLines(1);
        this.captionTextView.setMaxLines(1);
        this.captionTextView.setSingleLine(true);
        this.captionTextView.setEllipsize(r122);
        TextView r612 = this.captionTextView;
        if (LocaleController.isRTL == false) goto L117;
        int r74 = 5;
    L118:
        r612.setGravity(r74 | 16);
        this.captionTextView.setTextSize(1, 13.0f);
        View r613 = this.captionTextView;
        boolean r75 = LocaleController.isRTL;
        if (r75 == false) goto L121;
        int r106 = 5;
    L122:
        int r2210 = r106 | 48;
        if (r75 == false) goto L125;
        float r237 = 8.0f;
    L126:
        if (r75 == false) goto L128;
        float r255 = 72.0f;
    L129:
        addView(r613, LayoutHelper.createFrame(-1, -2.0f, r2210, r237, 30.0f, r255, 0.0f));
        this.captionTextView.setVisibility(8);
        goto L142
    L128:
        r255 = 8.0f;
        goto L129
    L125:
        r237 = 72.0f;
        goto L126
    L121:
        r106 = 3;
        goto L122
    L117:
        r74 = 3;
        goto L118
    L113:
        r105.addView(this.rightDateTextView, LayoutHelper.createLinear(-2, -2, 0.0f));
        r105.addView(this.nameTextView, LayoutHelper.createLinear(-2, -2, 1.0f, 0, 0, 4, 0));
        goto L114
    L109:
        r26 = 16.0f;
        goto L110
    L106:
        r242 = 72.0f;
        goto L107
    L102:
        r206 = 3;
        goto L103
    L130:
        this.nameTextView.setMaxLines(1);
        View r614 = this.nameTextView;
        boolean r76 = LocaleController.isRTL;
        if (r76 == false) goto L133;
        int r107 = 5;
    L134:
        int r2211 = r107 | 48;
        if (r76 == false) goto L137;
        float r238 = 8.0f;
    L138:
        if (r76 == false) goto L140;
        float r256 = 72.0f;
    L141:
        addView(r614, LayoutHelper.createFrame(-1, -2.0f, r2211, r238, 5.0f, r256, 0.0f));
        goto L142
    L140:
        r256 = 8.0f;
        goto L141
    L137:
        r238 = 72.0f;
        goto L138
    L133:
        r107 = 3;
        goto L134
    L83:
        r9 = 3;
        goto L84
    L66:
        r222 = 0.0f;
        goto L67
    L63:
        r202 = 16.0f;
        goto L64
    L59:
        r163 = 3;
        goto L60
    L68:
        boolean r77 = LocaleController.isRTL;
        if (r77 == false) goto L71;
        int r17 = 5;
    L72:
        int r207 = r17 | 48;
        if (r77 == false) goto L75;
        float r213 = 0.0f;
    L76:
        if (r77 == false) goto L78;
        float r239 = 12.0f;
    L79:
        addView(r57, LayoutHelper.createFrame(40, 40.0f, r207, r213, 8.0f, r239, 0.0f));
        goto L80
    L78:
        r239 = 0.0f;
        goto L79
    L75:
        r213 = 12.0f;
        goto L76
    L71:
        r17 = 3;
        goto L72
    L40:
        r22 = 0.0f;
        goto L41
    L37:
        r20 = 20.0f;
        goto L38
    L33:
        r162 = 3;
        goto L34
    L42:
        boolean r155 = LocaleController.isRTL;
        if (r155 == false) goto L45;
        int r166 = 5;
    L46:
        int r196 = r166 | 48;
        if (r155 == false) goto L49;
        float r208 = 0.0f;
    L50:
        if (r155 == false) goto L52;
        float r2212 = 16.0f;
    L53:
        addView(r55, LayoutHelper.createFrame(32, -2.0f, r196, r208, 22.0f, r2212, 0.0f));
        goto L54
    L52:
        r2212 = 0.0f;
        goto L53
    L49:
        r208 = 16.0f;
        goto L50
    L45:
        r166 = 3;
        goto L46
    L14:
        r18 = 0.0f;
        goto L15
    L11:
        r16 = 15.0f;
        goto L12
    L7:
        r12 = 3;
        goto L8
    L16:
        boolean r113 = LocaleController.isRTL;
        if (r113 == false) goto L19;
        int r123 = 5;
    L20:
        int r156 = r123 | 48;
        if (r113 == false) goto L23;
        float r167 = 0.0f;
    L24:
        if (r113 == false) goto L26;
        float r182 = 12.0f;
    L27:
        addView(r52, LayoutHelper.createFrame(40, 40.0f, r156, r167, 8.0f, r182, 0.0f));
        goto L28
    L26:
        r182 = 0.0f;
        goto L27
    L23:
        r167 = 12.0f;
        goto L24
    L19:
        r123 = 3;
        goto L20
    }

    private void drawDivider(Canvas r8) {
        if (this.needDivider == false) goto L6;
        r8.drawLine(AndroidUtilities.dp(72.0f), getHeight() - 1, getWidth() - getPaddingRight(), getHeight() - 1, Theme.getThemePaint("paintDivider", this.resourcesProvider));
        return;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void updateDateView() {
        MessageObject r0 = this.message;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getDocument() == null) goto L18;
        MessageObject r02 = this.message;
        long r1 = r02.messageOwner.date * 1000;
        long r3 = this.downloadedSize;
        if (r3 != 0) goto L10;
        String r03 = AndroidUtilities.formatFileSize(r02.getDocument().size);
    L12:
        if (this.viewType != 2) goto L15;
        this.dateTextView.setText(new SpannableStringBuilder().append(r03).append(' ').append(this.dotSpan).append(' ').append(FilteredSearchView.createFromInfoString(this.message, true, 2, this.dateTextView.getPaint())));
        this.rightDateTextView.setText(LocaleController.stringForMessageListDate(this.message.messageOwner.date));
        return;
    L15:
        this.dateTextView.setText(String.format("%s, %s", new Object[]{r03, LocaleController.formatString("formatDateAtTime", R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterYear().format(new Date(r1)), LocaleController.getInstance().getFormatterDay().format(new Date(r1))})}));
        return;
    L10:
        r03 = String.format(Locale.ENGLISH, "%s / %s", new Object[]{AndroidUtilities.formatFileSize(r3), AndroidUtilities.formatFileSize(this.message.getDocument().size)});
        goto L12
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r11) {
        if (this.enterAlpha != 1.0f) goto L5;
    L7:
        drawDivider(r11);
        super.dispatchDraw(r11);
    L8:
        boolean r2 = this.showReorderIcon;
        if (r2 == true) goto L15;
        if (this.showReorderIconProgress != 0.0f) goto L15;
        return;
    L15:
        if (r2 == false) goto L19;
        float r5 = this.showReorderIconProgress;
        if (r5 == 1.0f) goto L19;
        this.showReorderIconProgress = r5 + 0.10666667f;
        invalidate();
    L23:
        this.showReorderIconProgress = Utilities.clamp(this.showReorderIconProgress, 1.0f, 0.0f);
        int r22 = (getMeasuredWidth() - AndroidUtilities.dp(12.0f)) - Theme.dialogs_reorderDrawable.getIntrinsicWidth();
        int r3 = (getMeasuredHeight() - Theme.dialogs_reorderDrawable.getIntrinsicHeight()) >> 1;
        r11.save();
        float r0 = this.showReorderIconProgress;
        r11.scale(r0, r0, r22 + (Theme.dialogs_reorderDrawable.getIntrinsicWidth() / 2.0f), r3 + (Theme.dialogs_reorderDrawable.getIntrinsicHeight() / 2.0f));
        Drawable r02 = Theme.dialogs_reorderDrawable;
        r02.setBounds(r22, r3, r02.getIntrinsicWidth() + r22, Theme.dialogs_reorderDrawable.getIntrinsicHeight() + r3);
        Theme.dialogs_reorderDrawable.draw(r11);
        r11.restore();
        return;
    L19:
        if (r2 == true) goto L23;
        float r23 = this.showReorderIconProgress;
        if (r23 == 0.0f) goto L23;
        this.showReorderIconProgress = r23 - 0.10666667f;
        invalidate();
        goto L23
    L5:
        if (this.globalGradientView == null) goto L7;
        r11.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), (int) ((1.0f - this.enterAlpha) * 255.0f), 31);
        this.globalGradientView.setViewType(3);
        this.globalGradientView.updateColors();
        this.globalGradientView.updateGradient();
        this.globalGradientView.draw(r11);
        r11.restore();
        r11.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), (int) (this.enterAlpha * 255.0f), 31);
        drawDivider(r11);
        super.dispatchDraw(r11);
        r11.restore();
        goto L8
    }

    public BackupImageView getImageView() {
        return this.thumbImageView;
    }

    public MessageObject getMessage() {
        return this.message;
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.TAG;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public boolean isLoading() {
        return this.loading;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.progressView.getVisibility() != 0) goto L6;
        updateFileExistIcon(false);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
        updateFileExistIcon(true);
        this.downloadedSize = 0;
        updateDateView();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        if (this.checkBox.isChecked() == false) goto L6;
        r2.setCheckable(true);
        r2.setChecked(this.checkBox.isChecked());
        return;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        super.onLayout(r2, r3, r4, r5, r6);
        if (this.viewType != 1) goto L5;
        return;
    L5:
        if (this.nameTextView.getLineCount() > 1) goto L10;
        TextView r22 = this.captionTextView;
        if (r22 != null) goto L9;
        return;
    L9:
        if (r22.getVisibility() == 0) goto L10;
        return;
    L10:
        int r23 = this.nameTextView.getMeasuredHeight() - AndroidUtilities.dp(22.0f);
        TextView r32 = this.captionTextView;
        if (r32 != null) goto L13;
    L15:
        AnimatedEmojiSpan.TextViewEmojis r33 = this.dateTextView;
        r33.layout(r33.getLeft(), this.dateTextView.getTop() + r23, this.dateTextView.getRight(), this.dateTextView.getBottom() + r23);
        RLottieImageView r34 = this.statusImageView;
        r34.layout(r34.getLeft(), this.statusImageView.getTop() + r23, this.statusImageView.getRight(), r23 + this.statusImageView.getBottom());
        LineProgressView r24 = this.progressView;
        r24.layout(r24.getLeft(), getMeasuredHeight() - this.progressView.getMeasuredHeight(), this.progressView.getRight(), getMeasuredHeight());
        return;
    L13:
        if (r32.getVisibility() != 0) goto L15;
        TextView r35 = this.captionTextView;
        r35.layout(r35.getLeft(), this.captionTextView.getTop() + r23, this.captionTextView.getRight(), this.captionTextView.getBottom() + r23);
        r23 = r23 + (this.captionTextView.getMeasuredHeight() + AndroidUtilities.dp(3.0f));
        goto L15
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r7, int r8) {
        int r82 = this.viewType;
        if (r82 != 1) goto L7;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r7), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(64.0f) + (this.needDivider ? 1 : 0), TLObject.FLAG_30));
        return;
    L7:
        if (r82 != 0) goto L10;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r7), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
        return;
    L10:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r7), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
        int r72 = (AndroidUtilities.dp(34.0f) + this.nameTextView.getMeasuredHeight()) + (this.needDivider ? 1 : 0);
        if (this.caption != null) goto L13;
    L17:
        setMeasuredDimension(getMeasuredWidth(), r72);
        return;
    L13:
        if (this.captionTextView == null) goto L17;
        if (this.message.hasHighlightedWords() == false) goto L17;
        this.ignoreRequestLayout = true;
        this.captionTextView.setText(AndroidUtilities.ellipsizeCenterEnd(this.caption, this.message.highlightedWords.get(0), this.captionTextView.getMeasuredWidth(), this.captionTextView.getPaint(), 130));
        this.ignoreRequestLayout = false;
        r72 = r72 + (this.captionTextView.getMeasuredHeight() + AndroidUtilities.dp(3.0f));
        goto L17
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r2, long r3, long r5) {
        if (this.progressView.getVisibility() == 0) goto L5;
        updateFileExistIcon(true);
    L5:
        this.downloadedSize = r3;
        updateDateView();
        this.progressView.setProgress(Math.min(1.0f, r3 / r5), true);
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r3) {
        this.progressView.setProgress(1.0f, true);
        updateFileExistIcon(true);
        this.downloadedSize = 0;
        updateDateView();
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreRequestLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void setChecked(boolean r3, boolean r4) {
        if (this.checkBox.getVisibility() == 0) goto L5;
        this.checkBox.setVisibility(0);
    L5:
        this.checkBox.setChecked(r3, r4);
    }

    public void setDocument(MessageObject r15, boolean r16) {
        MessageObject r0 = this.message;
        if (r0 == null) goto L9;
        if (r15 == null) goto L9;
        if (r0.getId() == r15.getId()) goto L9;
        boolean r12 = true;
    L10:
        this.needDivider = r16;
        this.message = r15;
        this.loaded = false;
        this.loading = false;
        if (r12 == true) goto L13;
        this.downloadedSize = 0;
    L13:
        TLRPC.Document r02 = r15.getDocument();
        int r13 = 8;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r02 == null) goto L103;
        String r6 = null;
        if (r15.isMusic() == false) goto L33;
        int r5 = 0;
    L19:
        if (r5 >= r02.attributes.size()) goto L33;
        TLRPC.DocumentAttribute r7 = r02.attributes.get(r5);
        if ((r7 instanceof TLRPC.TL_documentAttributeAudio) == false) goto L31;
        String r8 = r7.performer;
        if (r8 != null) goto L25;
    L26:
        String r72 = r7.title;
        if (r72 == null) goto L31;
        if (r72.length() == 0) goto L31;
    L30:
        r6 = r15.getMusicAuthor() + " - " + r15.getMusicTitle();
        goto L31
    L25:
        if (r8.length() != 0) goto L30;
    L31:
        r5 = r5 + 1;
    L33:
        if (r15.isVideo() == false) goto L35;
    L39:
        String r52 = null;
    L41:
        if (TextUtils.isEmpty(r52) == false) goto L60;
        String r73 = r02.mime_type;
        if (r73 == null) goto L60;
        if (r73.startsWith(MediaStreamTrack.VIDEO_TRACK_KIND) == false) goto L51;
        if (MessageObject.isGifDocument(r02) == false) goto L49;
        r52 = LocaleController.getString(R.string.AttachGif);
        goto L60
    L49:
        r52 = LocaleController.getString(R.string.AttachVideo);
        goto L60
    L51:
        if (r02.mime_type.startsWith("image") == false) goto L57;
        if (MessageObject.isGifDocument(r02) == false) goto L55;
        r52 = LocaleController.getString(R.string.AttachGif);
        goto L60
    L55:
        r52 = LocaleController.getString(R.string.AttachPhoto);
        goto L60
    L57:
        if (r02.mime_type.startsWith(MediaStreamTrack.AUDIO_TRACK_KIND) == false) goto L59;
        r52 = LocaleController.getString(R.string.AttachAudio);
        goto L60
    L59:
        r52 = LocaleController.getString(R.string.AttachDocument);
    L60:
        if (r6 != null) goto L62;
        r6 = r52;
    L62:
        CharSequence r74 = AndroidUtilities.highlightText(r6, r15.highlightedWords, this.resourcesProvider);
        TextView r82 = this.nameTextView;
        if (r74 == null) goto L65;
        r82.setText(r74);
    L66:
        this.placeholderImageView.setVisibility(0);
        this.extTextView.setVisibility(0);
        this.placeholderImageView.setImageResource(AndroidUtilities.getThumbForNameOrMime(r52, r02.mime_type, false));
        TextView r62 = this.extTextView;
        int r75 = r52.lastIndexOf(46);
        if (r75 == (-1)) goto L70;
        r3 = r52.substring(r75 + 1).toLowerCase();
    L70:
        r62.setText(r3);
        TLRPC.PhotoSize r32 = FileLoader.getClosestPhotoSizeWithSize(r02.thumbs, 320);
        TLRPC.PhotoSize r53 = FileLoader.getClosestPhotoSizeWithSize(r02.thumbs, 40);
        if (r53 != r32) goto L74;
        r32 = null;
    L74:
        if ((r53 instanceof TLRPC.TL_photoSizeEmpty) == true) goto L89;
        if (r53 == null) goto L89;
        ImageReceiver r1 = this.thumbImageView.getImageReceiver();
        if (r32 != null) goto L80;
        boolean r2 = true;
    L81:
        r1.setNeedsQualityThumb(r2);
        ImageReceiver r14 = this.thumbImageView.getImageReceiver();
        if (r32 != null) goto L84;
        boolean r22 = true;
    L85:
        r14.setShouldGenerateQualityThumb(r22);
        this.thumbImageView.setVisibility(0);
        BitmapDrawable r17 = r15.strippedThumb;
        BackupImageView r23 = this.thumbImageView;
        if (r17 == null) goto L88;
        r23.setImage(ImageLocation.getForDocument(r32, r02), "40_40", null, null, r15.strippedThumb, null, null, 1, r15);
    L90:
        updateDateView();
        if (r15.hasHighlightedWords() == true) goto L93;
    L100:
        TextView r03 = this.captionTextView;
        if (r03 == null) goto L106;
        r03.setVisibility(8);
    L106:
        setWillNotDraw(!this.needDivider);
        this.progressView.setProgress(0.0f, false);
        updateFileExistIcon(r12);
        return;
    L93:
        if (TextUtils.isEmpty(this.message.messageOwner.message) == true) goto L100;
        CharSequence r04 = AndroidUtilities.highlightText(this.message.messageOwner.message.replace("\n", " ").replaceAll(" +", " ").trim(), this.message.highlightedWords, this.resourcesProvider);
        this.caption = r04;
        TextView r18 = this.captionTextView;
        if (r18 == null) goto L106;
        if (r04 == null) goto L99;
        r13 = 0;
    L99:
        r18.setVisibility(r13);
        goto L106
    L88:
        r23.setImage(ImageLocation.getForDocument(r32, r02), "40_40", ImageLocation.getForDocument(r53, r02), "40_40_b", null, 0, 1, r15);
        goto L90
    L84:
        r22 = false;
        goto L85
    L80:
        r2 = false;
    L89:
        this.thumbImageView.setVisibility(4);
        this.thumbImageView.setImageBitmap(null);
        this.extTextView.setAlpha(1.0f);
        this.placeholderImageView.setAlpha(1.0f);
        goto L90
    L65:
        r82.setText(r6);
        goto L66
    L35:
        if ((r15.messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) == true) goto L39;
        if (MessageObject.isGifDocument(r02) == true) goto L39;
        r52 = FileLoader.getDocumentFileName(r02);
        goto L41
    L103:
        this.nameTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.extTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.dateTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.placeholderImageView.setVisibility(0);
        this.extTextView.setVisibility(0);
        this.extTextView.setAlpha(1.0f);
        this.placeholderImageView.setAlpha(1.0f);
        this.thumbImageView.setVisibility(4);
        this.thumbImageView.setImageBitmap(null);
        this.caption = null;
        TextView r05 = this.captionTextView;
        if (r05 == null) goto L106;
        r05.setVisibility(8);
    L9:
        r12 = false;
        goto L10
    }

    public void setDrawDownloadIcon(boolean r1) {
        this.drawDownloadIcon = r1;
    }

    public void setEnterAnimationAlpha(float r2) {
        if (this.enterAlpha == r2) goto L6;
        this.enterAlpha = r2;
        invalidate();
        return;
    }

    public void setGlobalGradientView(FlickerLoadingView r1) {
        this.globalGradientView = r1;
    }

    public void setPhoto(String r5) {
        if (r5.endsWith("mp4") == false) goto L7;
        this.thumbImageView.setImage("vthumb://0:".concat(r5), null, null);
        this.thumbImageView.setVisibility(0);
        return;
    L7:
        if (r5.endsWith(".jpg") == false) goto L9;
    L17:
        this.thumbImageView.setImage("thumb://0:".concat(r5), null, null);
        this.thumbImageView.setVisibility(0);
        return;
    L9:
        if (r5.endsWith(".jpeg") == true) goto L17;
        if (r5.endsWith(".png") == true) goto L17;
        if (r5.endsWith(".gif") == true) goto L17;
        this.thumbImageView.setVisibility(8);
    }

    public void setPhotoEntry(MediaController.PhotoEntry r9) {
        String r0 = r9.thumbPath;
        if (r0 == null) goto L6;
        this.thumbImageView.setImage(r0, null, Theme.chat_attachEmptyDrawable);
        String r02 = r9.thumbPath;
    L13:
        File r1 = new File(r02);
        this.nameTextView.setText(r1.getName());
        FileLoader.getFileExtension(r1);
        this.extTextView.setVisibility(8);
        StringBuilder r03 = new StringBuilder();
        if (r9.width == 0) goto L22;
        if (r9.height == 0) goto L22;
        if (r03.length() <= 0) goto L20;
        r03.append(", ");
    L20:
        r03.append(String.format(Locale.US, "%dx%d", new Object[]{Integer.valueOf(r9.width), Integer.valueOf(r9.height)}));
    L22:
        if (r9.isVideo == false) goto L28;
        if (r03.length() <= 0) goto L26;
        r03.append(", ");
    L26:
        r03.append(AndroidUtilities.formatShortDuration(r9.duration));
    L28:
        if (r9.size == 0) goto L34;
        if (r03.length() <= 0) goto L32;
        r03.append(", ");
    L32:
        r03.append(AndroidUtilities.formatFileSize(r9.size));
    L34:
        if (r03.length() <= 0) goto L36;
        r03.append(", ");
    L36:
        r03.append(LocaleController.getInstance().getFormatterStats().format(r9.dateTaken));
        this.dateTextView.setText(r03);
        this.placeholderImageView.setVisibility(8);
        return;
    L6:
        if (r9.path == null) goto L12;
        boolean r04 = r9.isVideo;
        BackupImageView r2 = this.thumbImageView;
        if (r04 == false) goto L10;
        r2.setOrientation(0, true);
        this.thumbImageView.setImage("vthumb://" + r9.imageId + ":" + r9.path, null, Theme.chat_attachEmptyDrawable);
    L11:
        r02 = r9.path;
        goto L13
    L10:
        r2.setOrientation(r9.orientation, r9.invert, true);
        this.thumbImageView.setImage("thumb://" + r9.imageId + ":" + r9.path, null, Theme.chat_attachEmptyDrawable);
        goto L11
    L12:
        this.thumbImageView.setImageDrawable(Theme.chat_attachEmptyDrawable);
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    }

    public void setTextAndValueAndTypeAndThumb(String r4, String r5, String r6, String r7, int r8, boolean r9) {
        this.nameTextView.setText(r4);
        this.dateTextView.setText(r5);
        TextView r52 = this.extTextView;
        if (r6 == null) goto L5;
        r52.setVisibility(0);
        this.extTextView.setText(r6.toLowerCase());
    L6:
        this.needDivider = r9;
        ImageView r53 = this.placeholderImageView;
        if (r8 != 0) goto L9;
        r53.setImageResource(AndroidUtilities.getThumbForNameOrMime(r4, r6, false));
        this.placeholderImageView.setVisibility(0);
    L11:
        if (r7 != null) goto L17;
        if (r8 != 0) goto L17;
        this.extTextView.setAlpha(1.0f);
        this.placeholderImageView.setAlpha(1.0f);
        if (this.viewType == 3) goto L36;
        this.thumbImageView.setImageBitmap(null);
        this.thumbImageView.setVisibility(4);
    L36:
        setWillNotDraw(true ^ this.needDivider);
        return;
    L17:
        if (r7 != null) goto L19;
        CombinedDrawable r54 = Theme.createCircleDrawableWithIcon(AndroidUtilities.dp(42.0f), ContextCompat.getDrawable(getContext(), r8).mutate(), 0);
        if (r8 != R.drawable.files_storage) goto L25;
        int r62 = Theme.key_chat_attachLocationBackground;
        int r72 = Theme.key_chat_attachIcon;
    L34:
        Theme.setCombinedDrawableColor(r54, getThemedColor(r62), false);
        Theme.setCombinedDrawableColor(r54, getThemedColor(r72), true);
        this.thumbImageView.setImageDrawable(r54);
    L35:
        this.thumbImageView.setVisibility(0);
        goto L36
    L25:
        if (r8 != R.drawable.files_gallery) goto L28;
        r62 = Theme.key_chat_attachContactBackground;
        r72 = Theme.key_chat_attachIcon;
        goto L34
    L28:
        if (r8 != R.drawable.files_music) goto L31;
        r62 = Theme.key_chat_attachAudioBackground;
        r72 = Theme.key_chat_attachIcon;
        goto L34
    L31:
        if (r8 != R.drawable.files_internal) goto L33;
        r62 = Theme.key_chat_attachGalleryBackground;
        r72 = Theme.key_chat_attachIcon;
        goto L34
    L33:
        r62 = Theme.key_files_folderIconBackground;
        r72 = Theme.key_files_folderIcon;
        goto L34
    L19:
        if (this.viewType == 3) goto L35;
        this.thumbImageView.setImage(r7, "42_42", null);
        goto L35
    L9:
        r53.setVisibility(4);
        goto L11
    L5:
        r52.setVisibility(4);
        goto L6
    }

    public void showReorderIcon(boolean r2, boolean r3) {
        if (this.showReorderIcon != r2) goto L5;
        return;
    L5:
        this.showReorderIcon = r2;
        if (r3 == true) goto L11;
        if (r2 == false) goto L9;
        float r22 = 1.0f;
    L10:
        this.showReorderIconProgress = r22;
        goto L11
    L9:
        r22 = 0.0f;
    L11:
        invalidate();
    }

    public void updateFileExistIcon(boolean r10) {
        if (r10 == false) goto L5;
        TransitionSet r1 = new TransitionSet();
        ChangeBounds r2 = new ChangeBounds();
        r2.setDuration(150);
        r1.addTransition(new Fade().setDuration(150)).addTransition(r2);
        r1.setOrdering(0);
        r1.setInterpolator(CubicBezierInterpolator.DEFAULT);
        TransitionManager.beginDelayedTransition(this, r1);
    L5:
        MessageObject r12 = this.message;
        float r4 = 72.0f;
        float r6 = 8.0f;
        if (r12 != null) goto L8;
    L72:
        this.loading = false;
        this.loaded = true;
        LineProgressView r13 = this.progressView;
        if (r10 == false) goto L75;
        r13.hide();
    L76:
        this.progressView.setProgress(0.0f, false);
        this.statusImageView.setVisibility(4);
        FrameLayout.LayoutParams r102 = (FrameLayout.LayoutParams) this.dateTextView.getLayoutParams();
        if (r102 != null) goto L79;
    L87:
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        return;
    L79:
        if (LocaleController.isRTL == false) goto L81;
        float r0 = 8.0f;
    L82:
        r102.leftMargin = AndroidUtilities.dp(r0);
        if (LocaleController.isRTL == true) goto L86;
        r4 = 8.0f;
    L86:
        r102.rightMargin = AndroidUtilities.dp(r4);
        this.dateTextView.requestLayout();
        goto L87
    L81:
        r0 = 72.0f;
        goto L82
    L75:
        r13.setVisibility(4);
        goto L76
    L8:
        if (r12.messageOwner.media == null) goto L72;
        this.loaded = false;
        if (r12.attachPathExists == false) goto L12;
    L55:
        this.statusImageView.setVisibility(4);
        LineProgressView r14 = this.progressView;
        if (r10 == false) goto L58;
        r14.hide();
    L59:
        FrameLayout.LayoutParams r103 = (FrameLayout.LayoutParams) this.dateTextView.getLayoutParams();
        if (r103 != null) goto L62;
    L70:
        this.loading = false;
        this.loaded = true;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        return;
    L62:
        if (LocaleController.isRTL == false) goto L64;
        float r15 = 8.0f;
    L65:
        r103.leftMargin = AndroidUtilities.dp(r15);
        if (LocaleController.isRTL == true) goto L69;
        r4 = 8.0f;
    L69:
        r103.rightMargin = AndroidUtilities.dp(r4);
        this.dateTextView.requestLayout();
        goto L70
    L64:
        r15 = 72.0f;
        goto L65
    L58:
        r14.setVisibility(4);
        goto L59
    L12:
        if (r12.mediaExists == true) goto L55;
        if (this.drawDownloadIcon == false) goto L55;
        String r16 = FileLoader.getAttachFileName(r12.getDocument());
        DownloadController.getInstance(this.currentAccount).addLoadingFileObserver(r16, this.message, this);
        this.loading = FileLoader.getInstance(this.currentAccount).isLoadingFile(r16);
        this.statusImageView.setVisibility(0);
        RLottieDrawable r42 = this.statusDrawable;
        int r8 = 15;
        if (this.loading == false) goto L19;
        int r7 = 15;
    L20:
        r42.setCustomEndFrame(r7);
        this.statusDrawable.setPlayInDirectionOfCustomEndFrame(true);
        if (r10 == false) goto L23;
        this.statusImageView.playAnimation();
    L28:
        FrameLayout.LayoutParams r3 = (FrameLayout.LayoutParams) this.dateTextView.getLayoutParams();
        if (r3 == null) goto L39;
        if (LocaleController.isRTL == false) goto L33;
        float r43 = 8.0f;
    L34:
        r3.leftMargin = AndroidUtilities.dp(r43);
        if (LocaleController.isRTL == false) goto L37;
        r6 = 86.0f;
    L37:
        r3.rightMargin = AndroidUtilities.dp(r6);
        this.dateTextView.requestLayout();
        goto L39
    L33:
        r43 = 86.0f;
    L39:
        if (this.loading == false) goto L49;
        LineProgressView r32 = this.progressView;
        if (r10 == false) goto L43;
        r32.show();
    L44:
        Float r104 = ImageLoader.getInstance().getFileProgress(r16);
        if (r104 != null) goto L47;
        r104 = Float.valueOf(0.0f);
    L47:
        this.progressView.setProgress(r104.floatValue(), false);
        return;
    L43:
        r32.setVisibility(0);
        goto L44
    L49:
        LineProgressView r9 = this.progressView;
        if (r10 == false) goto L53;
        r9.hide();
        return;
    L53:
        r9.setVisibility(4);
        return;
    L23:
        RLottieDrawable r33 = this.statusDrawable;
        if (this.loading == true) goto L27;
        r8 = 0;
    L27:
        r33.setCurrentFrame(r8);
        this.statusImageView.invalidate();
        goto L28
    L19:
        r7 = 0;
        goto L20
    }
}
