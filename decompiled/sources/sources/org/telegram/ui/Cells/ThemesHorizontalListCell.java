package org.telegram.ui.Cells;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeColors;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MotionBackgroundDrawable;
import org.telegram.ui.Components.RadioButton;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.ThemeSetUrlActivity;

/* loaded from: classes6.dex */
public abstract class ThemesHorizontalListCell extends RecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    public static byte[] bytes;
    private ThemesListAdapter adapter;
    private int currentType;
    private ArrayList<Theme.ThemeInfo> customThemes;
    private ArrayList<Theme.ThemeInfo> defaultThemes;
    private boolean drawDivider;
    private BaseFragment fragment;
    private LinearLayoutManager horizontalLayoutManager;
    private HashMap<String, Theme.ThemeInfo> loadingThemes;
    private HashMap<Theme.ThemeInfo, String> loadingWallpapers;
    private int prevCount;
    private Theme.ThemeInfo prevThemeInfo;

    /* renamed from: org.telegram.ui.Cells.ThemesHorizontalListCell$1, reason: invalid class name */
    public class AnonymousClass1 extends LinearLayoutManager {
        final /* synthetic */ ThemesHorizontalListCell this$0;

        public AnonymousClass1(ThemesHorizontalListCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    public class InnerThemeView extends FrameLayout {
        private ObjectAnimator accentAnimator;
        private boolean accentColorChanged;
        private int accentId;
        private float accentState;
        private int backColor;
        private Drawable backgroundDrawable;
        private Paint bitmapPaint;
        private BitmapShader bitmapShader;
        private RadioButton button;
        private int checkColor;
        private final ArgbEvaluator evaluator;
        private boolean hasWhiteBackground;
        private int inColor;
        private Drawable inDrawable;
        private boolean isFirst;
        private boolean isLast;
        private long lastDrawTime;
        private int loadingColor;
        private Drawable loadingDrawable;
        private int oldBackColor;
        private int oldCheckColor;
        private int oldInColor;
        private int oldOutColor;
        private Drawable optionsDrawable;
        private int outColor;
        private Drawable outDrawable;
        private Paint paint;
        private float placeholderAlpha;
        private boolean pressed;
        private RectF rect;
        private Matrix shaderMatrix;
        private TextPaint textPaint;
        private Theme.ThemeInfo themeInfo;
        final /* synthetic */ ThemesHorizontalListCell this$0;

        public static /* synthetic */ void $r8$lambda$WxnGK25oAud49LlmJOyPibKeEZs(InnerThemeView r0, TLObject r1) {
            r0.lambda$parseTheme$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$jEANsvXhxZfDwzadlqDAJ8Sgbfc(InnerThemeView r0, TLObject r1, TLRPC.TL_error r2) {
            r0.lambda$parseTheme$1(r1, r2);
        }

        /* renamed from: -$$Nest$fgetthemeInfo, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Theme.ThemeInfo m7116$$Nest$fgetthemeInfo(InnerThemeView r0) {
            return r0.themeInfo;
        }

        /* renamed from: -$$Nest$mapplyTheme, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m7117$$Nest$mapplyTheme(InnerThemeView r0) {
            r0.applyTheme();
        }

        /* renamed from: -$$Nest$mparseTheme, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m7118$$Nest$mparseTheme(InnerThemeView r0) {
            return r0.parseTheme();
        }

        public InnerThemeView(ThemesHorizontalListCell r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            this.rect = new RectF();
            this.paint = new Paint(1);
            this.textPaint = new TextPaint(1);
            this.evaluator = new ArgbEvaluator();
            this.bitmapPaint = new Paint(3);
            this.shaderMatrix = new Matrix();
            setWillNotDraw(false);
            this.inDrawable = r9.getResources().getDrawable(R.drawable.minibubble_in).mutate();
            this.outDrawable = r9.getResources().getDrawable(R.drawable.minibubble_out).mutate();
            this.textPaint.setTextSize(AndroidUtilities.dp(13.0f));
            RadioButton r82 = new RadioButton(r9);
            this.button = r82;
            r82.setSize(AndroidUtilities.dp(20.0f));
            addView(this.button, LayoutHelper.createFrame(22, 22.0f, 51, 27.0f, 75.0f, 0.0f, 0.0f));
        }

        private void applyTheme() {
            Drawable r0 = this.inDrawable;
            int r2 = this.themeInfo.getPreviewInColor();
            PorterDuff.Mode r3 = PorterDuff.Mode.MULTIPLY;
            r0.setColorFilter(new PorterDuffColorFilter(r2, r3));
            this.outDrawable.setColorFilter(new PorterDuffColorFilter(this.themeInfo.getPreviewOutColor(), r3));
            double[] r1 = null;
            if (this.themeInfo.pathToFile != null) goto L5;
            updateColors(false);
            this.optionsDrawable = null;
        L6:
            this.bitmapShader = null;
            this.backgroundDrawable = null;
            Theme.ThemeInfo r02 = this.themeInfo;
            int r32 = r02.previewBackgroundGradientColor1;
            if (r32 != 0) goto L9;
        L11:
            if (r32 == 0) goto L14;
            GradientDrawable r03 = new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[]{this.themeInfo.getPreviewBackgroundColor(), this.themeInfo.previewBackgroundGradientColor1});
            r03.setCornerRadius(AndroidUtilities.dp(6.0f));
            this.backgroundDrawable = r03;
            r1 = AndroidUtilities.rgbToHsv(Color.red(this.themeInfo.getPreviewBackgroundColor()), Color.green(this.themeInfo.getPreviewBackgroundColor()), Color.blue(this.themeInfo.getPreviewBackgroundColor()));
        L24:
            if (r1 != null) goto L26;
        L30:
            this.hasWhiteBackground = false;
        L32:
            if (this.themeInfo.getPreviewBackgroundColor() == 0) goto L34;
        L40:
            invalidate();
            return;
        L34:
            if (this.themeInfo.previewParsed == false) goto L40;
            if (this.backgroundDrawable != null) goto L40;
            Drawable r04 = Theme.createDefaultWallpaper(100, 200);
            this.backgroundDrawable = r04;
            if ((r04 instanceof MotionBackgroundDrawable) == false) goto L40;
            ((MotionBackgroundDrawable) r04).setRoundRadius(AndroidUtilities.dp(6.0f));
            goto L40
        L26:
            if (r1[1] > 0.10000000149011612d) goto L30;
            if (r1[2] < 0.9599999785423279d) goto L30;
            this.hasWhiteBackground = true;
            goto L32
        L14:
            if (r02.previewWallpaperOffset <= 0) goto L16;
        L21:
            float r05 = AndroidUtilities.dp(76.0f);
            float r33 = AndroidUtilities.dp(97.0f);
            Theme.ThemeInfo r5 = this.themeInfo;
            Bitmap r06 = AndroidUtilities.getScaledBitmap(r05, r33, r5.pathToWallpaper, r5.pathToFile, r5.previewWallpaperOffset);
            if (r06 == null) goto L24;
            this.backgroundDrawable = new BitmapDrawable(r06);
            Shader.TileMode r34 = Shader.TileMode.CLAMP;
            BitmapShader r12 = new BitmapShader(r06, r34, r34);
            this.bitmapShader = r12;
            this.bitmapPaint.setShader(r12);
            int[] r07 = AndroidUtilities.calcDrawableColor(this.backgroundDrawable);
            r1 = AndroidUtilities.rgbToHsv(Color.red(r07[0]), Color.green(r07[0]), Color.blue(r07[0]));
            goto L24
        L16:
            if (r02.pathToWallpaper != null) goto L21;
            if (r02.getPreviewBackgroundColor() == 0) goto L24;
            r1 = AndroidUtilities.rgbToHsv(Color.red(this.themeInfo.getPreviewBackgroundColor()), Color.green(this.themeInfo.getPreviewBackgroundColor()), Color.blue(this.themeInfo.getPreviewBackgroundColor()));
            goto L24
        L9:
            if (r02.previewBackgroundGradientColor2 == 0) goto L11;
            int r7 = this.themeInfo.getPreviewBackgroundColor();
            Theme.ThemeInfo r08 = this.themeInfo;
            MotionBackgroundDrawable r6 = new MotionBackgroundDrawable(r7, r08.previewBackgroundGradientColor1, r08.previewBackgroundGradientColor2, r08.previewBackgroundGradientColor3, true);
            r6.setRoundRadius(AndroidUtilities.dp(6.0f));
            this.backgroundDrawable = r6;
            r1 = AndroidUtilities.rgbToHsv(Color.red(this.themeInfo.getPreviewBackgroundColor()), Color.green(this.themeInfo.getPreviewBackgroundColor()), Color.blue(this.themeInfo.getPreviewBackgroundColor()));
            goto L24
        L5:
            this.optionsDrawable = getResources().getDrawable(R.drawable.preview_dots).mutate();
            int r09 = this.themeInfo.getPreviewBackgroundColor();
            this.backColor = r09;
            this.oldBackColor = r09;
            goto L6
        }

        private int blend(int r3, int r4) {
            float r0 = this.accentState;
            if (r0 != 1.0f) goto L6;
            return r4;
        L6:
            return ((Integer) this.evaluator.evaluate(r0, Integer.valueOf(r3), Integer.valueOf(r4))).intValue();
        }

        private String getThemeName() {
            String r2 = this.themeInfo.getName();
            if (r2.toLowerCase().endsWith(".attheme") == true) goto L5;
            return r2;
        L5:
            return r2.substring(0, r2.lastIndexOf(46));
        }

        private /* synthetic */ void lambda$parseTheme$0(TLObject r5) {
            if ((r5 instanceof TLRPC.TL_wallPaper) == false) goto L8;
            TLRPC.WallPaper r52 = (TLRPC.WallPaper) r5;
            String r0 = FileLoader.getAttachFileName(r52.document);
            if (ThemesHorizontalListCell.m7113$$Nest$fgetloadingThemes(this.this$0).containsKey(r0) == true) goto L10;
            ThemesHorizontalListCell.m7113$$Nest$fgetloadingThemes(this.this$0).put(r0, this.themeInfo);
            FileLoader.getInstance(this.themeInfo.account).loadFile(r52.document, r52, 1, 1);
            return;
        L10:
            return;
        L8:
            this.themeInfo.badWallpaper = true;
        }

        private /* synthetic */ void lambda$parseTheme$1(TLObject r1, TLRPC.TL_error r2) {
            AndroidUtilities.runOnUIThread(new ThemesHorizontalListCell$InnerThemeView$$ExternalSyntheticLambda1(this, r1));
        }

        private boolean parseTheme() {
            Theme.ThemeInfo r0 = this.themeInfo;
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.pathToFile != null) goto L141;
            return false;
        L141:
            FileInputStream r4 = new FileInputStream(new File(this.themeInfo.pathToFile));     // Catch: Throwable -> L115
            int r02 = 0;
            boolean r5 = false;
        L144:
            int r6 = r4.read(ThemesHorizontalListCell.bytes);     // Catch: Throwable -> L30
            if (r6 == (-1)) goto L113;
            int r10 = r02;
            int r8 = 0;
            int r9 = 0;
        L13:
            if (r8 >= r6) goto L108;
            byte[] r11 = ThemesHorizontalListCell.bytes;     // Catch: Throwable -> L30
            if (r11[r8] != 10) goto L107;
            int r12 = r8 - r9;     // Catch: Throwable -> L30
            int r13 = r12 + 1;     // Catch: Throwable -> L30
            String r14 = new String(r11, r9, r12, "UTF-8");     // Catch: Throwable -> L30
            if (r14.startsWith("WLS=") == false) goto L63;
            String r112 = r14.substring(4);     // Catch: Throwable -> L30
            Uri r122 = Uri.parse(r112);     // Catch: Throwable -> L30
            this.themeInfo.slug = r122.getQueryParameter("slug");     // Catch: Throwable -> L30
            this.themeInfo.pathToWallpaper = new File(ApplicationLoader.getFilesDirFixed(), Utilities.MD5(r112) + ".wp").getAbsolutePath();     // Catch: Throwable -> L30
            String r2 = r122.getQueryParameter("mode");     // Catch: Throwable -> L30
            if (r2 == null) goto L34;
            String[] r22 = r2.toLowerCase().split(" ");     // Catch: Throwable -> L30
            if (r22 == null) goto L34;
            if (r22.length <= 0) goto L34;
            int r7 = 0;
        L26:
            if (r7 >= r22.length) goto L34;
            if ("blur".equals(r22[r7]) == true) goto L29;
            r7 = r7 + 1;     // Catch: Throwable -> L30
            goto L26
        L29:
            this.themeInfo.isBlured = true;     // Catch: Throwable -> L30
        L34:
            if (TextUtils.isEmpty(r122.getQueryParameter("pattern")) == true) goto L106;
            String r23 = r122.getQueryParameter("bg_color");     // Catch: Throwable -> L30 Exception -> L137
            if (TextUtils.isEmpty(r23) == true) goto L142;
            this.themeInfo.patternBgColor = Integer.parseInt(r23.substring(0, 6), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L137
            if (r23.length() < 13) goto L43;
            if (AndroidUtilities.isValidWallChar(r23.charAt(6)) == false) goto L43;
            this.themeInfo.patternBgGradientColor1 = Integer.parseInt(r23.substring(7, 13), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L137
        L43:
            if (r23.length() < 20) goto L48;
            if (AndroidUtilities.isValidWallChar(r23.charAt(13)) == false) goto L48;
            this.themeInfo.patternBgGradientColor2 = Integer.parseInt(r23.substring(14, 20), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L137
        L48:
            if (r23.length() != 27) goto L142;
            if (AndroidUtilities.isValidWallChar(r23.charAt(20)) == false) goto L142;
            this.themeInfo.patternBgGradientColor3 = Integer.parseInt(r23.substring(21), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L137
        L142:
            String r24 = r122.getQueryParameter("rotation");     // Catch: Throwable -> L30 Exception -> L138
            if (TextUtils.isEmpty(r24) == true) goto L55;
            this.themeInfo.patternBgGradientRotation = Utilities.parseInt(r24);     // Catch: Throwable -> L30 Exception -> L138
        L55:
            String r25 = r122.getQueryParameter("intensity");     // Catch: Throwable -> L30
            if (TextUtils.isEmpty(r25) == true) goto L58;
            this.themeInfo.patternIntensity = Utilities.parseInt(r25);     // Catch: Throwable -> L30
        L58:
            Theme.ThemeInfo r26 = this.themeInfo;     // Catch: Throwable -> L30
            if (r26.patternIntensity != 0) goto L106;
            r26.patternIntensity = 50;     // Catch: Throwable -> L30
        L106:
            r9 = r9 + r13;     // Catch: Throwable -> L30
            r10 = r10 + r13;     // Catch: Throwable -> L30
            goto L107
        L63:
            if (r14.startsWith("WPS") == true) goto L64;
            int r27 = r14.indexOf(61);     // Catch: Throwable -> L30
            if (r27 == (-1)) goto L106;
            int r113 = ThemeColors.stringKeyToInt(r14.substring(0, r27));     // Catch: Throwable -> L30
            if (r113 != Theme.key_chat_inBubble) goto L70;
        L79:
            String r28 = r14.substring(r27 + 1);     // Catch: Throwable -> L30
            if (r28.length() > 0) goto L83;
        L87:
            int r29 = Utilities.parseInt(r28);     // Catch: Throwable -> L30
        L89:
            if (r113 != Theme.key_chat_inBubble) goto L92;
            this.themeInfo.setPreviewInColor(r29);     // Catch: Throwable -> L30
            goto L106
        L92:
            if (r113 != Theme.key_chat_outBubble) goto L95;
            this.themeInfo.setPreviewOutColor(r29);     // Catch: Throwable -> L30
            goto L106
        L95:
            if (r113 != Theme.key_chat_wallpaper) goto L98;
            this.themeInfo.setPreviewBackgroundColor(r29);     // Catch: Throwable -> L30
            goto L106
        L98:
            if (r113 != Theme.key_chat_wallpaper_gradient_to1) goto L101;
            this.themeInfo.previewBackgroundGradientColor1 = r29;     // Catch: Throwable -> L30
            goto L106
        L101:
            if (r113 != Theme.key_chat_wallpaper_gradient_to2) goto L104;
            this.themeInfo.previewBackgroundGradientColor2 = r29;     // Catch: Throwable -> L30
            goto L106
        L104:
            if (r113 != Theme.key_chat_wallpaper_gradient_to3) goto L106;
            this.themeInfo.previewBackgroundGradientColor3 = r29;     // Catch: Throwable -> L30
            goto L106
        L83:
            if (r28.charAt(0) != '#') goto L87;
            r29 = Color.parseColor(r28);     // Catch: Throwable -> L30 Exception -> L86
        L86:
            r29 = Utilities.parseInt(r28);     // Catch: Throwable -> L30
            goto L89
        L70:
            if (r113 == Theme.key_chat_outBubble) goto L79;
            if (r113 == Theme.key_chat_wallpaper) goto L79;
            if (r113 == Theme.key_chat_wallpaper_gradient_to1) goto L79;
            if (r113 == Theme.key_chat_wallpaper_gradient_to2) goto L79;
            if (r113 != Theme.key_chat_wallpaper_gradient_to3) goto L106;
        L64:
            this.themeInfo.previewWallpaperOffset = r13 + r10;     // Catch: Throwable -> L30
            r5 = true;
        L107:
            r8 = r8 + 1;     // Catch: Throwable -> L30
        L108:
            if (r5 == true) goto L113;
            if (r02 == r10) goto L113;
            r4.getChannel().position(r10);     // Catch: Throwable -> L30
            r02 = r10;
        L113:
            r4.close();     // Catch: Throwable -> L115
        L123:
            Theme.ThemeInfo r03 = this.themeInfo;
            if (r03.pathToWallpaper != null) goto L126;
        L134:
            this.themeInfo.previewParsed = true;
            return true;
        L126:
            if (r03.badWallpaper == true) goto L134;
            if (new File(this.themeInfo.pathToWallpaper).exists() == true) goto L134;
            if (ThemesHorizontalListCell.m7114$$Nest$fgetloadingWallpapers(this.this$0).containsKey(this.themeInfo) == true) goto L160;
            HashMap r04 = ThemesHorizontalListCell.m7114$$Nest$fgetloadingWallpapers(this.this$0);
            Theme.ThemeInfo r210 = this.themeInfo;
            r04.put(r210, r210.slug);
            TL_account.getWallPaper r05 = new TL_account.getWallPaper();
            TLRPC.TL_inputWallPaperSlug r211 = new TLRPC.TL_inputWallPaperSlug();
            Theme.ThemeInfo r3 = this.themeInfo;
            r211.slug = r3.slug;
            r05.wallpaper = r211;
            ConnectionsManager.getInstance(r3.account).sendRequest(r05, new ThemesHorizontalListCell$InnerThemeView$$ExternalSyntheticLambda0(this));
            return false;
        L160:
            return false;
        L30:
            th = move-exception;
            r4.close();     // Catch: Throwable -> L119
        L159:
            throw th;     // Catch: Throwable -> L115
        L119:
            th = move-exception;
            th.addSuppressed(th);     // Catch: Throwable -> L115
            throw th;     // Catch: Throwable -> L115
        L115:
            th = move-exception;
            FileLog.e(th);
            goto L123
        }

        @Keep
        public float getAccentState() {
            return this.accentState;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (ThemesHorizontalListCell.m7110$$Nest$fgetcurrentType(this.this$0) != 1) goto L5;
            Theme.ThemeInfo r0 = Theme.getCurrentNightTheme();
        L6:
            RadioButton r2 = this.button;
            if (this.themeInfo != r0) goto L9;
            boolean r02 = true;
        L10:
            r2.setChecked(r02, false);
            Theme.ThemeInfo r03 = this.themeInfo;
            if (r03 == null) goto L22;
            TLRPC.TL_theme r22 = r03.info;
            if (r22 != null) goto L15;
            return;
        L15:
            if (r03.themeLoaded == false) goto L17;
            return;
        L17:
            if (ThemesHorizontalListCell.m7113$$Nest$fgetloadingThemes(this.this$0).containsKey(FileLoader.getAttachFileName(r22.document)) == false) goto L19;
            return;
        L19:
            if (ThemesHorizontalListCell.m7114$$Nest$fgetloadingWallpapers(this.this$0).containsKey(this.themeInfo) == true) goto L26;
            this.themeInfo.themeLoaded = true;
            this.placeholderAlpha = 0.0f;
            parseTheme();
            applyTheme();
            return;
        L26:
            return;
        L22:
            return;
        L9:
            r02 = false;
            goto L10
        L5:
            r0 = Theme.getCurrentTheme();
            goto L6
        }

        @Override // android.view.View
        public void onDraw(Canvas r15) {
            if (this.accentId == this.themeInfo.currentAccentId) goto L6;
            updateColors(true);
        L6:
            if (this.isFirst == false) goto L8;
            int r0 = AndroidUtilities.dp(22.0f);
        L9:
            float r5 = r0;
            float r6 = AndroidUtilities.dp(11.0f);
            this.rect.set(r5, r6, AndroidUtilities.dp(76.0f) + r0, r3 + AndroidUtilities.dp(97.0f));
            String r3 = getThemeName();
            int r4 = getMeasuredWidth();
            if (this.isFirst == false) goto L12;
            float r8 = 10.0f;
        L13:
            int r42 = r4 - AndroidUtilities.dp(r8);
            if (this.isLast == false) goto L16;
            int r82 = AndroidUtilities.dp(7.0f);
        L17:
            String r32 = TextUtils.ellipsize(r3, this.textPaint, r42 - r82, TextUtils.TruncateAt.END).toString();
            int r43 = (int) Math.ceil(this.textPaint.measureText(r32));
            this.textPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            r15.drawText(r32, ((AndroidUtilities.dp(76.0f) - r43) / 2) + r0, AndroidUtilities.dp(131.0f), this.textPaint);
            Theme.ThemeInfo r33 = this.themeInfo;
            TLRPC.TL_theme r44 = r33.info;
            if (r44 != null) goto L20;
        L23:
            this.paint.setColor(blend(this.oldBackColor, this.backColor));
            if (this.accentColorChanged == false) goto L26;
            Drawable r34 = this.inDrawable;
            int r10 = blend(this.oldInColor, this.inColor);
            PorterDuff.Mode r11 = PorterDuff.Mode.MULTIPLY;
            r34.setColorFilter(new PorterDuffColorFilter(r10, r11));
            this.outDrawable.setColorFilter(new PorterDuffColorFilter(blend(this.oldOutColor, this.outColor), r11));
            this.accentColorChanged = false;
        L26:
            Drawable r2 = this.backgroundDrawable;
            if (r2 != null) goto L29;
            r15.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.paint);
        L37:
            this.button.setColor(1728053247, -1);
            Theme.ThemeInfo r22 = this.themeInfo;
            if (r22.accentBaseColor == 0) goto L45;
            if ("Day".equals(r22.name) == false) goto L42;
        L43:
            this.button.setColor(-5000269, blend(this.oldCheckColor, this.checkColor));
            Theme.chat_instantViewRectPaint.setColor(733001146);
            r15.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Theme.chat_instantViewRectPaint);
        L47:
            this.inDrawable.setBounds(AndroidUtilities.dp(6.0f) + r0, AndroidUtilities.dp(22.0f), AndroidUtilities.dp(49.0f) + r0, AndroidUtilities.dp(36.0f));
            this.inDrawable.draw(r15);
            this.outDrawable.setBounds(AndroidUtilities.dp(27.0f) + r0, AndroidUtilities.dp(41.0f), r0 + AndroidUtilities.dp(70.0f), AndroidUtilities.dp(55.0f));
            this.outDrawable.draw(r15);
            if (this.optionsDrawable != null) goto L50;
        L52:
            Theme.ThemeInfo r02 = this.themeInfo;
            TLRPC.TL_theme r1 = r02.info;
            if (r1 != null) goto L55;
        L63:
            if (r1 == null) goto L67;
            if (r02.themeLoaded == true) goto L67;
        L68:
            this.button.setAlpha(1.0f - this.placeholderAlpha);
            this.paint.setColor(Theme.getColor(Theme.key_windowBackgroundGray));
            this.paint.setAlpha((int) (this.placeholderAlpha * 255.0f));
            r15.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.paint);
            if (this.loadingDrawable == null) goto L75;
            int r03 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7);
            if (this.loadingColor == r03) goto L73;
            Drawable r12 = this.loadingDrawable;
            this.loadingColor = r03;
            Theme.setDrawableColor(r12, r03);
        L73:
            int r04 = (int) (this.rect.centerX() - (this.loadingDrawable.getIntrinsicWidth() / 2));
            int r13 = (int) (this.rect.centerY() - (this.loadingDrawable.getIntrinsicHeight() / 2));
            this.loadingDrawable.setAlpha((int) (this.placeholderAlpha * 255.0f));
            Drawable r35 = this.loadingDrawable;
            r35.setBounds(r04, r13, r35.getIntrinsicWidth() + r04, this.loadingDrawable.getIntrinsicHeight() + r13);
            this.loadingDrawable.draw(r15);
        L75:
            if (this.themeInfo.themeLoaded == false) goto L86;
            long r05 = SystemClock.elapsedRealtime();
            long r36 = Math.min(17, r05 - this.lastDrawTime);
            this.lastDrawTime = r05;
            float r152 = this.placeholderAlpha - (r36 / 180.0f);
            this.placeholderAlpha = r152;
            if (r152 >= 0.0f) goto L79;
            this.placeholderAlpha = 0.0f;
        L79:
            invalidate();
            return;
        L86:
            return;
        L67:
            if (this.placeholderAlpha > 0.0f) goto L68;
            if (this.button.getAlpha() == 1.0f) goto L87;
            this.button.setAlpha(1.0f);
            return;
        L87:
            return;
        L55:
            if (r1.document != null) goto L63;
            this.button.setAlpha(0.0f);
            Theme.chat_instantViewRectPaint.setColor(733001146);
            r15.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Theme.chat_instantViewRectPaint);
            if (this.loadingDrawable == null) goto L85;
            int r06 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7);
            if (this.loadingColor == r06) goto L61;
            Drawable r14 = this.loadingDrawable;
            this.loadingColor = r06;
            Theme.setDrawableColor(r14, r06);
        L61:
            int r07 = (int) (this.rect.centerX() - (this.loadingDrawable.getIntrinsicWidth() / 2));
            int r16 = (int) (this.rect.centerY() - (this.loadingDrawable.getIntrinsicHeight() / 2));
            Drawable r23 = this.loadingDrawable;
            r23.setBounds(r07, r16, r23.getIntrinsicWidth() + r07, this.loadingDrawable.getIntrinsicHeight() + r16);
            this.loadingDrawable.draw(r15);
            return;
        L85:
            return;
        L50:
            if (ThemesHorizontalListCell.m7110$$Nest$fgetcurrentType(this.this$0) != 0) goto L52;
            int r08 = ((int) this.rect.right) - AndroidUtilities.dp(16.0f);
            int r17 = ((int) this.rect.top) + AndroidUtilities.dp(6.0f);
            Drawable r24 = this.optionsDrawable;
            r24.setBounds(r08, r17, r24.getIntrinsicWidth() + r08, this.optionsDrawable.getIntrinsicHeight() + r17);
            this.optionsDrawable.draw(r15);
            goto L52
        L42:
            if ("Arctic Blue".equals(this.themeInfo.name) == false) goto L47;
        L45:
            if (this.hasWhiteBackground == false) goto L47;
            this.button.setColor(-5000269, r22.getPreviewOutColor());
            Theme.chat_instantViewRectPaint.setColor(733001146);
            r15.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Theme.chat_instantViewRectPaint);
            goto L47
        L29:
            if (this.bitmapShader == null) goto L35;
            BitmapDrawable r25 = (BitmapDrawable) r2;
            float r37 = r25.getBitmap().getWidth();
            float r26 = r25.getBitmap().getHeight();
            float r45 = r37 / this.rect.width();
            float r102 = r26 / this.rect.height();
            this.shaderMatrix.reset();
            float r112 = 1.0f / Math.min(r45, r102);
            float r38 = r37 / r102;
            float r103 = this.rect.width();
            Matrix r122 = this.shaderMatrix;
            if (r38 <= r103) goto L33;
            r122.setTranslate(r5 - ((r38 - this.rect.width()) / 2.0f), r6);
        L34:
            this.shaderMatrix.preScale(r112, r112);
            this.bitmapShader.setLocalMatrix(this.shaderMatrix);
            r15.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), this.bitmapPaint);
            goto L37
        L33:
            r122.setTranslate(r5, r6 - (((r26 / r45) - this.rect.height()) / 2.0f));
            goto L34
        L35:
            RectF r39 = this.rect;
            r2.setBounds((int) r39.left, (int) r39.top, (int) r39.right, (int) r39.bottom);
            this.backgroundDrawable.draw(r15);
            goto L37
        L20:
            if (r44.document == null) goto L52;
            if (r33.themeLoaded == false) goto L52;
        L16:
            r82 = 0;
            goto L17
        L12:
            r8 = 15.0f;
            goto L13
        L8:
            r0 = 0;
            goto L9
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            r3.setText(getThemeName());
            r3.setClassName(Button.class.getName());
            r3.setChecked(this.button.isChecked());
            r3.setCheckable(true);
            r3.setEnabled(true);
            r3.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(32, LocaleController.getString(R.string.AccDescrMoreOptions)));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            int r32 = 22;
            if (this.isLast == false) goto L5;
            int r22 = 22;
        L6:
            int r23 = r22 + 76;
            if (this.isFirst == true) goto L10;
            r32 = 0;
        L10:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r23 + r32), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(148.0f), TLObject.FLAG_30));
            return;
        L5:
            r22 = 15;
            goto L6
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r5) {
            if (this.optionsDrawable == null) goto L30;
            Theme.ThemeInfo r0 = this.themeInfo;
            if (r0 == null) goto L30;
            if (r0.info == null) goto L11;
            if (r0.themeLoaded == false) goto L30;
        L11:
            if (ThemesHorizontalListCell.m7110$$Nest$fgetcurrentType(this.this$0) != 0) goto L30;
            int r02 = r5.getAction();
            if (r02 == 0) goto L16;
            if (r02 == 1) goto L16;
        L28:
            return this.pressed;
        L16:
            float r2 = r5.getX();
            float r52 = r5.getY();
            if (r2 > this.rect.centerX()) goto L19;
        L25:
            if (r02 != 1) goto L28;
            this.pressed = false;
            goto L28
        L19:
            if (r52 >= (this.rect.centerY() - AndroidUtilities.dp(10.0f))) goto L25;
            if (r02 != 0) goto L32;
            this.pressed = true;
            goto L25
        L32:
            performHapticFeedback(3);     // Catch: Exception -> L31
        L24:
            this.this$0.showOptionsForTheme(this.themeInfo);
        L30:
            return super.onTouchEvent(r5);
        }

        @Keep
        public void setAccentState(float r1) {
            this.accentState = r1;
            this.accentColorChanged = true;
            invalidate();
        }

        public void setTheme(Theme.ThemeInfo r2, boolean r3, boolean r4) {
            this.themeInfo = r2;
            this.isFirst = r4;
            this.isLast = r3;
            this.accentId = r2.currentAccentId;
            FrameLayout.LayoutParams r22 = (FrameLayout.LayoutParams) this.button.getLayoutParams();
            if (this.isFirst == false) goto L5;
            float r32 = 49.0f;
        L6:
            r22.leftMargin = AndroidUtilities.dp(r32);
            this.button.setLayoutParams(r22);
            this.placeholderAlpha = 0.0f;
            Theme.ThemeInfo r23 = this.themeInfo;
            if (r23.pathToFile != null) goto L9;
        L25:
            applyTheme();
            return;
        L9:
            if (r23.previewParsed == true) goto L25;
            r23.setPreviewInColor(Theme.getDefaultColor(Theme.key_chat_inBubble));
            this.themeInfo.setPreviewOutColor(Theme.getDefaultColor(Theme.key_chat_outBubble));
            boolean r24 = new File(this.themeInfo.pathToFile).exists();
            if (r24 == true) goto L13;
        L15:
            Theme.ThemeInfo r33 = this.themeInfo;
            TLRPC.TL_theme r42 = r33.info;
            if (r42 == null) goto L25;
            if (r42.document == null) goto L24;
            r33.themeLoaded = false;
            this.placeholderAlpha = 1.0f;
            Drawable r34 = getResources().getDrawable(R.drawable.msg_theme).mutate();
            this.loadingDrawable = r34;
            int r43 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7);
            this.loadingColor = r43;
            Theme.setDrawableColor(r34, r43);
            if (r24 == true) goto L25;
            String r25 = FileLoader.getAttachFileName(this.themeInfo.info.document);
            if (ThemesHorizontalListCell.m7113$$Nest$fgetloadingThemes(this.this$0).containsKey(r25) == true) goto L25;
            ThemesHorizontalListCell.m7113$$Nest$fgetloadingThemes(this.this$0).put(r25, this.themeInfo);
            FileLoader r26 = FileLoader.getInstance(this.themeInfo.account);
            TLRPC.TL_theme r35 = this.themeInfo.info;
            r26.loadFile(r35.document, r35, 1, 1);
            goto L25
        L24:
            Drawable r27 = getResources().getDrawable(R.drawable.preview_custom).mutate();
            this.loadingDrawable = r27;
            int r36 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayText7);
            this.loadingColor = r36;
            Theme.setDrawableColor(r27, r36);
            goto L25
        L13:
            if (parseTheme() == false) goto L15;
            if (r24 == true) goto L25;
        L5:
            r32 = 27.0f;
            goto L6
        }

        public void updateColors(boolean r6) {
            this.oldInColor = this.inColor;
            this.oldOutColor = this.outColor;
            this.oldBackColor = this.backColor;
            this.oldCheckColor = this.checkColor;
            int r1 = 0;
            Theme.ThemeAccent r0 = this.themeInfo.getAccent(false);
            if (r0 == null) goto L12;
            r1 = r0.accentColor;
            int r2 = r0.myMessagesAccentColor;
            if (r2 != 0) goto L8;
            r2 = r1;
        L8:
            int r02 = (int) r0.backgroundOverrideColor;
            if (r02 != 0) goto L13;
            r02 = r1;
        L13:
            Theme.ThemeInfo r3 = this.themeInfo;
            this.inColor = Theme.changeColorAccent(r3, r1, r3.getPreviewInColor());
            Theme.ThemeInfo r12 = this.themeInfo;
            this.outColor = Theme.changeColorAccent(r12, r2, r12.getPreviewOutColor());
            Theme.ThemeInfo r13 = this.themeInfo;
            this.backColor = Theme.changeColorAccent(r13, r02, r13.getPreviewBackgroundColor());
            this.checkColor = this.outColor;
            this.accentId = this.themeInfo.currentAccentId;
            ObjectAnimator r03 = this.accentAnimator;
            if (r03 == null) goto L16;
            r03.cancel();
        L16:
            if (r6 == false) goto L19;
            ObjectAnimator r62 = ObjectAnimator.ofFloat(this, "accentState", new float[]{0.0f, 1.0f});
            this.accentAnimator = r62;
            r62.setDuration(200);
            this.accentAnimator.start();
            return;
        L19:
            setAccentState(1.0f);
            return;
        L12:
            r02 = 0;
            r2 = 0;
            goto L13
        }

        public void updateCurrentThemeCheck() {
            if (ThemesHorizontalListCell.m7110$$Nest$fgetcurrentType(this.this$0) != 1) goto L5;
            Theme.ThemeInfo r0 = Theme.getCurrentNightTheme();
        L6:
            RadioButton r2 = this.button;
            if (this.themeInfo != r0) goto L9;
            boolean r3 = true;
        L10:
            r2.setChecked(r3, true);
            return;
        L9:
            r3 = false;
            goto L10
        L5:
            r0 = Theme.getCurrentTheme();
            goto L6
        }
    }

    public class ThemesListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ ThemesHorizontalListCell this$0;

        public ThemesListAdapter(ThemesHorizontalListCell r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            ThemesHorizontalListCell r0 = this.this$0;
            int r1 = ThemesHorizontalListCell.m7112$$Nest$fgetdefaultThemes(r0).size() + ThemesHorizontalListCell.m7111$$Nest$fgetcustomThemes(this.this$0).size();
            ThemesHorizontalListCell.m7115$$Nest$fputprevCount(r0, r1);
            return r1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
            InnerThemeView r42 = (InnerThemeView) r4.itemView;
            int r0 = ThemesHorizontalListCell.m7112$$Nest$fgetdefaultThemes(this.this$0).size();
            ThemesHorizontalListCell r1 = this.this$0;
            if (r5 >= r0) goto L5;
            ArrayList r02 = ThemesHorizontalListCell.m7112$$Nest$fgetdefaultThemes(r1);
            int r12 = r5;
        L6:
            Theme.ThemeInfo r03 = (Theme.ThemeInfo) r02.get(r12);
            boolean r13 = true;
            if (r5 != (getItemCount() - 1)) goto L9;
            boolean r3 = true;
        L10:
            if (r5 == 0) goto L13;
            r13 = false;
        L13:
            r42.setTheme(r03, r3, r13);
            return;
        L9:
            r3 = false;
            goto L10
        L5:
            r02 = ThemesHorizontalListCell.m7111$$Nest$fgetcustomThemes(r1);
            r12 = r5 - ThemesHorizontalListCell.m7112$$Nest$fgetdefaultThemes(this.this$0).size();
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            return new RecyclerListView.Holder(new InnerThemeView(this.this$0, this.mContext));
        }
    }

    public static /* synthetic */ boolean $r8$lambda$7TtTduTHtgfDGUzQ_I8mZR6Hjpc(ThemesHorizontalListCell r0, View r1, int r2) {
        return r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$T-1j8rh0FDJaA0HOk0B5v83BgWM, reason: not valid java name */
    public static /* synthetic */ void m7109$r8$lambda$T1j8rh0FDJaA0HOk0B5v83BgWM(ThemesHorizontalListCell r0, Theme.ThemeInfo r1) {
        r0.lambda$didReceivedNotification$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$f6OXqxBKM_oOkcRRrc5E532n820(ThemesHorizontalListCell r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$fpIiIZyKkI4D4X0VkZU8uSMBAMg(ThemesHorizontalListCell r0, Theme.ThemeInfo r1, File r2) {
        r0.lambda$didReceivedNotification$3(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7110$$Nest$fgetcurrentType(ThemesHorizontalListCell r0) {
        return r0.currentType;
    }

    /* renamed from: -$$Nest$fgetcustomThemes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m7111$$Nest$fgetcustomThemes(ThemesHorizontalListCell r0) {
        return r0.customThemes;
    }

    /* renamed from: -$$Nest$fgetdefaultThemes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m7112$$Nest$fgetdefaultThemes(ThemesHorizontalListCell r0) {
        return r0.defaultThemes;
    }

    /* renamed from: -$$Nest$fgetloadingThemes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m7113$$Nest$fgetloadingThemes(ThemesHorizontalListCell r0) {
        return r0.loadingThemes;
    }

    /* renamed from: -$$Nest$fgetloadingWallpapers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m7114$$Nest$fgetloadingWallpapers(ThemesHorizontalListCell r0) {
        return r0.loadingWallpapers;
    }

    /* renamed from: -$$Nest$fputprevCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7115$$Nest$fputprevCount(ThemesHorizontalListCell r0, int r1) {
        r0.prevCount = r1;
    }

    static {
        bytes = new byte[1024];
    }

    public ThemesHorizontalListCell(Context r2, BaseFragment r3, int r4, ArrayList<Theme.ThemeInfo> r5, ArrayList<Theme.ThemeInfo> r6) {
        super(r2);
        this.loadingThemes = new HashMap();
        this.loadingWallpapers = new HashMap();
        this.customThemes = r6;
        this.defaultThemes = r5;
        this.currentType = r4;
        this.fragment = r3;
        if (r4 != 2) goto L5;
        setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
    L6:
        setItemAnimator(null);
        setLayoutAnimation(null);
        this.horizontalLayoutManager = new AnonymousClass1(this, r2);
        setPadding(0, 0, 0, 0);
        setClipToPadding(false);
        this.horizontalLayoutManager.setOrientation(0);
        setLayoutManager(this.horizontalLayoutManager);
        ThemesListAdapter r32 = new ThemesListAdapter(this, r2);
        this.adapter = r32;
        setAdapter(r32);
        setOnItemClickListener(new ThemesHorizontalListCell$$ExternalSyntheticLambda0(this));
        setOnItemLongClickListener(new ThemesHorizontalListCell$$ExternalSyntheticLambda1(this));
        return;
    L5:
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        goto L6
    }

    private void checkVisibleTheme(Theme.ThemeInfo r6) {
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        View r2 = getChildAt(r1);
        if ((r2 instanceof InnerThemeView) == false) goto L11;
        InnerThemeView r22 = (InnerThemeView) r2;
        if (InnerThemeView.m7116$$Nest$fgetthemeInfo(r22) != r6) goto L11;
        if (InnerThemeView.m7118$$Nest$mparseTheme(r22) == false) goto L11;
        InnerThemeView.m7116$$Nest$fgetthemeInfo(r22).themeLoaded = true;
        InnerThemeView.m7117$$Nest$mapplyTheme(r22);
    L11:
        r1 = r1 + 1;
        goto L3
    }

    private /* synthetic */ void lambda$didReceivedNotification$2(Theme.ThemeInfo r1) {
        checkVisibleTheme(r1);
    }

    private /* synthetic */ void lambda$didReceivedNotification$3(Theme.ThemeInfo r2, File r3) {
        r2.badWallpaper = !r2.createBackground(r3, r2.pathToWallpaper);
        AndroidUtilities.runOnUIThread(new ThemesHorizontalListCell$$ExternalSyntheticLambda3(this, r2));
    }

    private /* synthetic */ void lambda$new$0(View r2, int r3) {
        selectTheme(InnerThemeView.m7116$$Nest$fgetthemeInfo((InnerThemeView) r2));
        int r32 = r2.getLeft();
        int r22 = r2.getRight();
        if (r32 >= 0) goto L7;
        smoothScrollBy(r32 - AndroidUtilities.dp(8.0f), 0);
        return;
    L7:
        if (r22 <= getMeasuredWidth()) goto L10;
        smoothScrollBy(r22 - getMeasuredWidth(), 0);
        return;
    }

    private /* synthetic */ boolean lambda$new$1(View r1, int r2) {
        showOptionsForTheme(InnerThemeView.m7116$$Nest$fgetthemeInfo((InnerThemeView) r1));
        return true;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.fileLoaded) goto L13;
        String r22 = (String) r4[0];
        File r32 = (File) r4[1];
        Theme.ThemeInfo r42 = this.loadingThemes.get(r22);
        if (r42 == null) goto L16;
        this.loadingThemes.remove(r22);
        if (this.loadingWallpapers.remove(r42) == null) goto L10;
        Utilities.globalQueue.postRunnable(new ThemesHorizontalListCell$$ExternalSyntheticLambda2(this, r42, r32));
        return;
    L10:
        checkVisibleTheme(r42);
        return;
    L16:
        return;
    L13:
        if (r2 != NotificationCenter.fileLoadFailed) goto L17;
        String r23 = (String) r4[0];
        this.loadingThemes.remove(r23);
        return;
    }

    public void notifyDataSetChanged(int r3) {
        if (this.prevCount == this.adapter.getItemCount()) goto L14;
        this.adapter.notifyDataSetChanged();
        if (this.currentType != 1) goto L8;
        Theme.ThemeInfo r0 = Theme.getCurrentNightTheme();
    L10:
        if (this.prevThemeInfo == r0) goto L13;
        scrollToCurrentTheme(r3, false);
        return;
    L13:
        return;
    L8:
        r0 = Theme.getCurrentTheme();
        goto L10
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L6;
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.fileLoadFailed);
        r0 = r0 + 1;
        goto L4
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        int r0 = 0;
    L4:
        if (r0 >= 16) goto L6;
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(r0).removeObserver(this, NotificationCenter.fileLoadFailed);
        r0 = r0 + 1;
        goto L4
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onDraw(Canvas r9) {
        super.onDraw(r9);
        if (this.drawDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r3 = r0;
        float r4 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(20.0f);
    L13:
        r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(20.0f);
        goto L7
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (getParent() == null) goto L8;
        if (getParent().getParent() == null) goto L8;
        getParent().getParent().requestDisallowInterceptTouchEvent(canScrollHorizontally(-1));
    L8:
        return super.onInterceptTouchEvent(r3);
    }

    public void scrollToCurrentTheme(int r3, boolean r4) {
        if (r3 != 0) goto L6;
        View r0 = (View) getParent();
        if (r0 == null) goto L6;
        r3 = r0.getMeasuredWidth();
    L6:
        if (r3 != 0) goto L9;
        return;
    L9:
        if (this.currentType != 1) goto L11;
        Theme.ThemeInfo r02 = Theme.getCurrentNightTheme();
    L12:
        this.prevThemeInfo = r02;
        int r03 = this.defaultThemes.indexOf(r02);
        if (r03 >= 0) goto L17;
        r03 = this.customThemes.indexOf(this.prevThemeInfo) + this.defaultThemes.size();
        if (r03 >= 0) goto L17;
        return;
    L17:
        if (r4 == false) goto L20;
        smoothScrollToPosition(r03);
        return;
    L20:
        this.horizontalLayoutManager.scrollToPositionWithOffset(r03, (r3 - AndroidUtilities.dp(76.0f)) / 2);
        return;
    L11:
        r02 = Theme.getCurrentTheme();
        goto L12
    }

    public void selectTheme(Theme.ThemeInfo r8) {
        TLRPC.TL_theme r0 = r8.info;
        if (r0 != null) goto L5;
    L13:
        int r3 = 0;
        if (TextUtils.isEmpty(r8.assetName) == true) goto L16;
        Theme.PatternsLoader.createLoader(false);
    L16:
        SharedPreferences.Editor r02 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0).edit();
        if (this.currentType != 1) goto L19;
    L22:
        String r4 = "lastDarkTheme";
    L23:
        r02.putString(r4, r8.getKey());
        r02.apply();
        if (this.currentType != 1) goto L30;
        if (r8 == Theme.getCurrentNightTheme()) goto L49;
        Theme.setCurrentNightTheme(r8);
    L33:
        updateRows();
        int r03 = getChildCount();
    L34:
        if (r3 >= r03) goto L39;
        View r1 = getChildAt(r3);
        if ((r1 instanceof InnerThemeView) == false) goto L38;
        ((InnerThemeView) r1).updateCurrentThemeCheck();
    L38:
        r3 = r3 + 1;
        goto L34
    L39:
        EmojiThemes.saveCustomTheme(r8, r8.currentAccentId);
        if (this.currentType == 1) goto L47;
        Theme.turnOffAutoNight(this.fragment);
        return;
    L47:
        return;
    L49:
        return;
    L30:
        if (r8 == Theme.getCurrentTheme()) goto L50;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{r8, Boolean.FALSE, null, -1});
        goto L33
    L50:
        return;
    L19:
        if (r8.isDark() == true) goto L22;
        r4 = "lastDayTheme";
        goto L23
    L5:
        if (r8.themeLoaded == true) goto L8;
        return;
    L8:
        if (r0.document != null) goto L13;
        BaseFragment r7 = this.fragment;
        if (r7 == null) goto L46;
        r7.presentFragment(new ThemeSetUrlActivity(r8, null, true));
        return;
    }

    @Override // android.view.View
    public void setBackgroundColor(int r1) {
        super.setBackgroundColor(r1);
        invalidateViews();
    }

    public void setDrawDivider(boolean r1) {
        this.drawDivider = r1;
    }

    public void showOptionsForTheme(Theme.ThemeInfo r1) {
    }

    public abstract void updateRows();
}
