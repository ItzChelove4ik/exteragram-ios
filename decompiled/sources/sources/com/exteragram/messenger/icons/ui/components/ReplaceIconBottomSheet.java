package com.exteragram.messenger.icons.ui.components;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.caverock.androidsvg.SVG;
import com.exteragram.messenger.icons.ExteraResources;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.icons.IconPack;
import com.exteragram.messenger.icons.IconPackStorage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Locale;
import kotlin.Unit;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes4.dex */
public class ReplaceIconBottomSheet extends BottomSheet {
    private final IconPack iconPack;
    private int loadedOriginalHeight;
    private int loadedOriginalWidth;
    private boolean needReset;
    private boolean needSave;
    private Drawable newDrawable;
    private IconInfoView newIconInfoView;
    private String newIconOriginalName;
    private File newIconTempFile;
    private Drawable originalDrawable;
    private IconInfoView originalIconInfoView;
    private final int resId;
    private ButtonWithCounterView resetButton;
    private final String resourceName;
    private int savedCustomFileHeight;
    private int savedCustomFileWidth;
    private boolean waitingForResult;

    public static class ArrowView extends View {
        private final Paint paint;
        private final Path path;

        public ArrowView(Context r2) {
            super(r2);
            Paint r22 = new Paint(1);
            this.paint = r22;
            this.path = new Path();
            r22.setStyle(Paint.Style.STROKE);
            r22.setStrokeWidth(AndroidUtilities.dp(2.0f));
            r22.setStrokeCap(Paint.Cap.ROUND);
            r22.setStrokeJoin(Paint.Join.ROUND);
            r22.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon));
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            float r0 = getWidth() / 2.0f;
            float r2 = getHeight() / 2.0f;
            float r3 = AndroidUtilities.dp(18.0f);
            this.path.reset();
            float r32 = r3 / 2.0f;
            this.path.moveTo(r0 - r32, r2);
            float r02 = r0 + r32;
            this.path.lineTo(r02, r2);
            this.path.moveTo(r02 - AndroidUtilities.dp(7.0f), r2 - AndroidUtilities.dp(7.0f));
            this.path.lineTo(r02, r2);
            this.path.lineTo(r02 - AndroidUtilities.dp(7.0f), r2 + AndroidUtilities.dp(7.0f));
            r7.drawPath(this.path, this.paint);
        }
    }

    public static class BorderedImageView extends ImageView {
        private final Paint bgPaint;
        private final float cornerRadius;
        private final Paint dashedPaint;
        private boolean isDashed;
        private final Path path;
        private final RectF rect;
        private final Paint solidPaint;
        private final float strokeWidth;

        public BorderedImageView(Context r2) {
            this(r2, null);
        }

        public BorderedImageView(Context r7, AttributeSet r8) {
            super(r7, r8);
            this.isDashed = false;
            this.path = new Path();
            this.rect = new RectF();
            this.cornerRadius = AndroidUtilities.dp(12.0f);
            float r82 = AndroidUtilities.dpf2(1.25f);
            this.strokeWidth = r82;
            Paint r0 = new Paint(1);
            this.bgPaint = r0;
            r0.setStyle(Paint.Style.FILL);
            r0.setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            Paint r02 = new Paint(1);
            this.solidPaint = r02;
            Paint.Style r2 = Paint.Style.STROKE;
            r02.setStyle(r2);
            int r3 = Theme.key_windowBackgroundWhiteGrayText;
            r02.setColor(AndroidUtilities.multiplyAlphaComponent(Theme.getColor(r3), 0.3f));
            r02.setStrokeWidth(r82);
            Paint r03 = new Paint(1);
            this.dashedPaint = r03;
            r03.setStyle(r2);
            r03.setColor(AndroidUtilities.multiplyAlphaComponent(Theme.getColor(r3), 0.3f));
            r03.setStrokeWidth(r82);
            r03.setPathEffect(new DashPathEffect(new float[]{AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f)}, 0.0f));
        }

        @Override // android.widget.ImageView, android.view.View
        public void onDraw(Canvas r5) {
            float r0 = this.strokeWidth / 2.0f;
            this.rect.set(r0, r0, getWidth() - r0, getHeight() - r0);
            RectF r02 = this.rect;
            float r1 = this.cornerRadius;
            r5.drawRoundRect(r02, r1, r1, this.bgPaint);
            super.onDraw(r5);
            this.path.reset();
            Path r03 = this.path;
            RectF r12 = this.rect;
            float r2 = this.cornerRadius;
            r03.addRoundRect(r12, r2, r2, Path.Direction.CW);
            Path r04 = this.path;
            if (this.isDashed == false) goto L5;
            Paint r4 = this.dashedPaint;
        L6:
            r5.drawPath(r04, r4);
            return;
        L5:
            r4 = this.solidPaint;
            goto L6
        }

        public void setDashed(boolean r1) {
            this.isDashed = r1;
            invalidate();
        }
    }

    public static class IconInfoView extends LinearLayout {
        private final BorderedImageView iconView;
        private final TextView infoName;
        private final TextView infoResolution;
        private float targetAspectRatio;

        public IconInfoView(Context r11, boolean r12) {
            super(r11);
            this.targetAspectRatio = -1.0f;
            setOrientation(1);
            setGravity(1);
            BorderedImageView r1 = new BorderedImageView(r11);
            this.iconView = r1;
            r1.setDashed(r12);
            r1.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
            r1.setPadding(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));
            r1.setScaleType(ImageView.ScaleType.FIT_CENTER);
            addView(r1, LayoutHelper.createLinear(60, 60));
            TextView r122 = new TextView(r11);
            this.infoName = r122;
            r122.setTextSize(1, 13.0f);
            r122.setTypeface(AndroidUtilities.bold());
            r122.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            r122.setGravity(17);
            r122.setSingleLine(true);
            r122.setEllipsize(TextUtils.TruncateAt.END);
            addView(r122, LayoutHelper.createLinear(-2, -2, 0.0f, 12.0f, 0.0f, 0.0f));
            TextView r123 = new TextView(r11);
            this.infoResolution = r123;
            r123.setTextSize(1, 13.0f);
            r123.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
            r123.setGravity(17);
            addView(r123, LayoutHelper.createLinear(-2, -2, 0.0f, 2.0f, 0.0f, 0.0f));
        }

        private int gcd(int r1, int r2) {
            if (r2 != 0) goto L5;
            return r1;
        L5:
            return gcd(r2, r1 % r2);
        }

        private String getAspectRatioString(int r2, int r3) {
            if (r3 != 0) goto L5;
            return "?";
        L5:
            int r1 = gcd(r2, r3);
            return (r2 / r1) + ":" + (r3 / r1);
        }

        public BorderedImageView getIconView() {
            return this.iconView;
        }

        public void setTargetDimensions(int r1, int r2) {
            if (r1 <= 0) goto L6;
            if (r2 <= 0) goto L6;
            this.targetAspectRatio = r1 / r2;
            return;
        L6:
            this.targetAspectRatio = -1.0f;
        }

        public void update(Drawable r6, String r7, int r8, int r9) {
            if (r6 == null) goto L18;
            if (r8 > 0) goto L7;
            r8 = r6.getIntrinsicWidth();
        L7:
            if (r9 > 0) goto L10;
            r9 = r6.getIntrinsicHeight();
        L10:
            this.infoResolution.setText(String.format("%s (%s)", new Object[]{String.format(Locale.ROOT, "%d×%d", new Object[]{Integer.valueOf(r8), Integer.valueOf(r9)}), getAspectRatioString(r8, r9)}));
            this.infoResolution.setVisibility(0);
            float r2 = this.targetAspectRatio;
            if (r2 <= 0.0f) goto L16;
            if (r9 <= 0) goto L16;
            if (Math.abs((r8 / r9) - r2) <= 0.1f) goto L16;
            this.infoResolution.setTextColor(Theme.getColor(Theme.key_text_RedRegular));
        L17:
            this.iconView.setImageDrawable(r6);
        L19:
            TextView r62 = this.infoName;
            if (r7 == null) goto L23;
            r62.setText(r7);
            this.infoName.setVisibility(0);
            return;
        L23:
            r62.setVisibility(4);
            return;
        L16:
            this.infoResolution.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
            goto L17
        L18:
            this.infoResolution.setVisibility(4);
            this.iconView.setImageDrawable(null);
            goto L19
        }
    }

    public static /* synthetic */ void $r8$lambda$2m2KHb2B0R28moaBXRAaLwwrM1Y(ReplaceIconBottomSheet r0, Context r1) {
        r0.lambda$loadDrawables$1(r1);
    }

    public static /* synthetic */ Unit $r8$lambda$3gICRrgxokS_a7HNwTyltTEm4jI(ReplaceIconBottomSheet r0, Activity r1, Uri r2) {
        return r0.lambda$startPicker$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$J_I7wbGz7KtkKgncwx1oKGHvf1g(ReplaceIconBottomSheet r0, Activity r1) {
        r0.lambda$createView$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$MQzHSdpe3TVVmIqz0Ol2yQyQeW0(ReplaceIconBottomSheet r0, Drawable r1, int r2, int r3, Drawable r4, int r5, int r6) {
        r0.lambda$loadDrawables$0(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$_1aoOANoDwyV4A1dx3xPonfl2LE(ReplaceIconBottomSheet r0, View r1) {
        r0.lambda$createView$7(r1);
    }

    /* renamed from: $r8$lambda$dnjuhuDgwm7BZJ2CRs7x-2uHeQ0, reason: not valid java name */
    public static /* synthetic */ void m1461$r8$lambda$dnjuhuDgwm7BZJ2CRs7x2uHeQ0(ReplaceIconBottomSheet r0, File r1, String r2, Drawable r3, int r4, int r5) {
        r0.lambda$updateNewIconFromFile$9(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$g9D45cAEkQJ6JLFaIhHG9d2TrbA(ReplaceIconBottomSheet r0, ClipboardManager r1, Context r2) {
        r0.lambda$createView$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$lMduCkmS5yGPM0E7HgSBKhcVN7I(ReplaceIconBottomSheet r0, Context r1, Uri r2) {
        r0.lambda$processSelectedImage$11(r1, r2);
    }

    /* renamed from: $r8$lambda$n57nTFdah2MbUbKih-B4WZPHdAs, reason: not valid java name */
    public static /* synthetic */ void m1462$r8$lambda$n57nTFdah2MbUbKihB4WZPHdAs(ReplaceIconBottomSheet r0, Activity r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$oCHjamjmFqxVeXG_l0A66482ZRk(ReplaceIconBottomSheet r0, Context r1, View r2) {
        r0.lambda$createView$5(r1, r2);
    }

    /* renamed from: $r8$lambda$tcFPWEbyhP3h0-GK1IYx10roVO0, reason: not valid java name */
    public static /* synthetic */ void m1463$r8$lambda$tcFPWEbyhP3h0GK1IYx10roVO0(ReplaceIconBottomSheet r0, View r1) {
        r0.lambda$createView$6(r1);
    }

    /* renamed from: $r8$lambda$xOhFrMek8za-mVgappQcgNtHly8, reason: not valid java name */
    public static /* synthetic */ void m1464$r8$lambda$xOhFrMek8zamVgappQcgNtHly8(ReplaceIconBottomSheet r0, CharSequence r1, Context r2) {
        r0.lambda$processClipboardText$10(r1, r2);
    }

    public ReplaceIconBottomSheet(Context r2, int r3, IconPack r4) {
        super(r2, false);
        this.waitingForResult = false;
        this.loadedOriginalWidth = 0;
        this.loadedOriginalHeight = 0;
        this.savedCustomFileWidth = 0;
        this.savedCustomFileHeight = 0;
        this.needSave = false;
        this.needReset = false;
        fixNavigationBar();
        this.resId = r3;
        this.iconPack = r4;
        this.resourceName = r2.getResources().getResourceEntryName(r3);
        setCustomView(createView(r2));
        loadDrawables(r2);
    }

    private View createView(Context r21) {
        LinearLayout r2 = new LinearLayout(r21);
        r2.setOrientation(1);
        r2.setPadding(0, AndroidUtilities.dp(16.0f), 0, 0);
        LinearLayout r5 = new LinearLayout(r21);
        r5.setOrientation(0);
        r5.setGravity(1);
        r5.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        IconInfoView r7 = new IconInfoView(r21, false);
        this.originalIconInfoView = r7;
        r7.update(this.originalDrawable, this.resourceName, this.loadedOriginalWidth, this.loadedOriginalHeight);
        r5.addView(this.originalIconInfoView, LayoutHelper.createLinear(0, -2, 1.0f));
        LinearLayout r72 = new LinearLayout(r21);
        r72.setOrientation(1);
        r72.setGravity(1);
        r72.addView(new ArrowView(r21), LayoutHelper.createLinear(24, 60));
        r5.addView(r72, LayoutHelper.createLinear(-2, -2, 0.0f, 0, 24, 0, 24, 0));
        IconInfoView r73 = new IconInfoView(r21, true);
        this.newIconInfoView = r73;
        r73.setTargetDimensions(this.loadedOriginalWidth, this.loadedOriginalHeight);
        this.newIconInfoView.getIconView().setFocusable(true);
        this.newIconInfoView.getIconView().setOnClickListener(new ReplaceIconBottomSheet$$ExternalSyntheticLambda1(this, r21));
        r5.addView(this.newIconInfoView, LayoutHelper.createLinear(0, -2, 1.0f));
        updateNewInfo(this.newDrawable, this.iconPack.getIcons().get(this.resourceName), this.savedCustomFileWidth, this.savedCustomFileHeight);
        r2.addView(r5, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 24.0f));
        LinearLayout r52 = new LinearLayout(r21);
        r52.setOrientation(1);
        r52.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        ButtonWithCounterView r4 = new ButtonWithCounterView(r21, true, this.resourcesProvider);
        r4.setRound();
        r4.setText(LocaleController.getString(R.string.Save), false);
        r4.setOnClickListener(new ReplaceIconBottomSheet$$ExternalSyntheticLambda2(this));
        r52.addView(r4, LayoutHelper.createLinear(-1, 48));
        ButtonWithCounterView r3 = new ButtonWithCounterView(r21, false, this.resourcesProvider);
        this.resetButton = r3;
        r3.setRound().setNeutral();
        ButtonWithCounterView r1 = this.resetButton;
        if (this.iconPack.getIcons().get(this.resourceName) == null) goto L5;
        int r32 = R.string.Reset;
    L6:
        r1.setText(LocaleController.getString(r32), false);
        this.resetButton.setOnClickListener(new ReplaceIconBottomSheet$$ExternalSyntheticLambda3(this));
        r52.addView(this.resetButton, LayoutHelper.createLinear(-1, 48, 0.0f, 8.0f, 0.0f, 0.0f));
        r2.addView(r52, LayoutHelper.createLinear(-1, -2));
        return r2;
    L5:
        r32 = R.string.Cancel;
        goto L6
    }

    private /* synthetic */ void lambda$createView$2(ClipboardManager r2, Context r3) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.getPrimaryClip() != null) goto L6;
        return;
    L6:
        if (r2.getPrimaryClip().getItemCount() <= 0) goto L17;
        ClipData.Item r22 = r2.getPrimaryClip().getItemAt(0);
        Uri r0 = r22.getUri();
        if (r0 == null) goto L11;
        processSelectedImage(r3, r0);
        return;
    L11:
        CharSequence r23 = r22.getText();
        if (r23 == null) goto L18;
        processClipboardText(r3, r23);
        return;
    L18:
        return;
    }

    private /* synthetic */ void lambda$createView$3(Activity r2) {
        startPicker(r2, false);
    }

    private /* synthetic */ void lambda$createView$4(Activity r2) {
        startPicker(r2, true);
    }

    private /* synthetic */ void lambda$createView$5(Context r9, View r10) {
        if (isDismissed() == true) goto L36;
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r0 == null) goto L34;
        Activity r02 = r0.getParentActivity();
        if (r02 == null) goto L35;
        ClipboardManager r1 = (ClipboardManager) r9.getSystemService("clipboard");
        if (r1 != null) goto L12;
    L31:
        boolean r4 = false;
    L32:
        ItemOptions.makeOptions(this.containerView, r10).addIf(r4, R.drawable.msg_copy, LocaleController.getString(R.string.PasteFromClipboard), new ReplaceIconBottomSheet$$ExternalSyntheticLambda5(this, r1, r9)).add(R.drawable.msg_photos, LocaleController.getString(R.string.SelectFromGallery), new ReplaceIconBottomSheet$$ExternalSyntheticLambda6(this, r02)).add(R.drawable.msg2_folder, LocaleController.getString(R.string.StoryMusicSelectFromFiles), new ReplaceIconBottomSheet$$ExternalSyntheticLambda7(this, r02)).setDrawScrim(false).setOnTopOfScrim().setDimAlpha(0).setGravity(1).show();
        return;
    L12:
        if (r1.hasPrimaryClip() == false) goto L31;
        ClipData r42 = r1.getPrimaryClip();
        if (r42 == null) goto L31;
        if (r42.getItemCount() <= 0) goto L31;
        ClipData.Item r43 = r42.getItemAt(0);
        if (r43.getUri() == null) goto L21;
    L19:
        r4 = true;
        goto L32
    L21:
        if (r43.getText() == null) goto L31;
        String r44 = r43.getText().toString().trim();
        if (r44.isEmpty() == true) goto L31;
        if (r44.contains("<svg") == true) goto L19;
        if (r44.contains("<SVG") == true) goto L19;
        if (r44.startsWith("/") == false) goto L31;
    L35:
        return;
    L34:
        return;
    }

    private /* synthetic */ void lambda$createView$6(View r1) {
        if (this.newIconTempFile == null) goto L5;
        this.needSave = true;
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$createView$7(View r1) {
        if (this.newDrawable == null) goto L5;
        this.needReset = true;
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$loadDrawables$0(Drawable r1, int r2, int r3, Drawable r4, int r5, int r6) {
        this.originalDrawable = r1;
        this.loadedOriginalWidth = r2;
        this.loadedOriginalHeight = r3;
        this.newDrawable = r4;
        this.savedCustomFileWidth = r5;
        this.savedCustomFileHeight = r6;
        IconInfoView r42 = this.originalIconInfoView;
        if (r42 == null) goto L5;
        r42.update(r1, this.resourceName, r2, r3);
    L5:
        ButtonWithCounterView r12 = this.resetButton;
        if (r12 != null) goto L8;
    L12:
        updateNewInfo(this.newDrawable, this.iconPack.getIcons().get(this.resourceName), this.savedCustomFileWidth, this.savedCustomFileHeight);
        return;
    L8:
        if (this.newDrawable == null) goto L10;
        int r22 = R.string.Reset;
    L11:
        r12.setText(LocaleController.getString(r22), false);
        goto L12
    L10:
        r22 = R.string.Cancel;
        goto L11
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v7 */
    private /* synthetic */ void lambda$loadDrawables$1(Context r12) {
        BitmapDrawable r1 = null;
        if ((r12.getResources() instanceof ExteraResources) == true) goto L62;
    L6:
        Drawable r0 = null;
    L7:
        if (r0 != null) goto L9;
        r0 = ResourcesCompat.getDrawable(r12.getResources(), this.resId, r12.getTheme());
    L9:
        Drawable r4 = r0;
        int r2 = 0;
        if (r4 == null) goto L12;
        int r5 = r4.getIntrinsicWidth();
        int r6 = r4.getIntrinsicHeight();
    L13:
        String r02 = this.iconPack.getIcons().get(this.resourceName);
        if (r02 == null) goto L55;
        File r7 = IconPackStorage.INSTANCE.getIconPacksDirectory();
        ?? r8 = new StringBuilder();
        r8.append(this.iconPack.getId());
        r8.append("/");
        r8.append(r02);
        File r3 = new File(r7, r8.toString());
        if (r3.exists() == false) goto L55;
    L45:
        e = e;
        int r72 = 0;
    L50:
        FileLog.e(e);
    L51:
        Bitmap r03 = IconManager.INSTANCE.createBitmapFromFile(r3.getAbsolutePath(), this.resId, AndroidUtilities.displayMetrics.densityDpi, r12.getTheme());
        if (r03 == null) goto L54;
        r1 = new BitmapDrawable(r12.getResources(), r03);
    L54:
        int r82 = r2;
        int r9 = r72;
        BitmapDrawable r73 = r1;
    L56:
        AndroidUtilities.runOnUIThread(new ReplaceIconBottomSheet$$ExternalSyntheticLambda4(this, r4, r5, r6, r73, r82, r9));
        return;
    L64:
        if (r3.getName().toLowerCase().endsWith(".svg") == false) goto L47;
        FileInputStream r74 = new FileInputStream(r3);     // Catch: Exception -> L45
        SVG r04 = SVG.getFromInputStream(r74);     // Catch: Throwable -> L23
        if (r04.getDocumentWidth() <= 0.0f) goto L25;
        float r83 = r04.getDocumentWidth();     // Catch: Throwable -> L23
    L26:
        int r84 = (int) r83;
    L30:
        th = move-exception;
        int r92 = r84;
        Throwable r85 = th;
    L66:
        r74.close();     // Catch: Throwable -> L40
    L69:
        throw r85;     // Catch: Exception -> L43
    L40:
        th = move-exception;
        r85.addSuppressed(th);     // Catch: Exception -> L43
        throw r85;     // Catch: Exception -> L43
    L43:
        e = e;
        r72 = 0;
        r2 = r92;
        goto L50
    L28:
        if (r04.getDocumentHeight() <= 0.0f) goto L32;
        float r05 = r04.getDocumentHeight();     // Catch: Throwable -> L30
    L33:
        int r22 = (int) r05;
        r74.close();     // Catch: Exception -> L36
        r8 = r84;
    L49:
        r72 = r22;
        r2 = r8;
        goto L51
    L32:
        r05 = r04.getDocumentViewBox().height();     // Catch: Throwable -> L30
        goto L33
    L25:
        r83 = r04.getDocumentViewBox().width();     // Catch: Throwable -> L23
    L23:
        th = move-exception;
        r85 = th;
        r92 = 0;
        goto L66
    L47:
        BitmapFactory.Options r06 = new BitmapFactory.Options();     // Catch: Exception -> L45
        r06.inJustDecodeBounds = true;     // Catch: Exception -> L45
        BitmapFactory.decodeFile(r3.getAbsolutePath(), r06);     // Catch: Exception -> L45
        int r86 = r06.outWidth;     // Catch: Exception -> L45
        r22 = r06.outHeight;     // Catch: Exception -> L36
        r8 = r86;
    L36:
        e = e;
        r72 = r2;
        r2 = r8;
    L55:
        r73 = null;
        r82 = 0;
        r9 = 0;
        goto L56
    L12:
        r5 = 0;
        r6 = 0;
        goto L13
    L62:
        r0 = ((ExteraResources) r12.getResources()).getOriginalDrawable(this.resId);     // Catch: Exception -> L58
        goto L7
    }

    private /* synthetic */ void lambda$processClipboardText$10(CharSequence r8, Context r9) {
        String r82 = r8.toString();     // Catch: Exception -> L44
        if (r82.contains("<svg") == false) goto L6;
    L14:
        File r2 = new File(ApplicationLoader.applicationContext.getCacheDir(), "temp_import_" + System.currentTimeMillis() + ".svg");     // Catch: Exception -> L44
        FileOutputStream r1 = new FileOutputStream(r2);     // Catch: Exception -> L44
        r1.write(r82.getBytes());     // Catch: Throwable -> L36
        r1.close();     // Catch: Exception -> L44
        FileInputStream r83 = new FileInputStream(r2);     // Catch: Exception -> L44
        SVG r12 = SVG.getFromInputStream(r83);     // Catch: Throwable -> L20
        if (r12.getDocumentWidth() <= 0.0f) goto L22;
        float r3 = r12.getDocumentWidth();     // Catch: Throwable -> L20
    L23:
        int r32 = (int) r3;     // Catch: Throwable -> L20
        if (r12.getDocumentHeight() <= 0.0f) goto L26;
        float r13 = r12.getDocumentHeight();     // Catch: Throwable -> L20
    L27:
        int r5 = (int) r13;
        r83.close();     // Catch: Exception -> L44
        updateNewIconFromFile(r9, r2, this.resourceName + ".svg", r32, r5);     // Catch: Exception -> L44
        return;
    L26:
        r13 = r12.getDocumentViewBox().height();     // Catch: Throwable -> L20
        goto L27
    L22:
        r3 = r12.getDocumentViewBox().width();     // Catch: Throwable -> L20
    L20:
        th = move-exception;
        r83.close();     // Catch: Throwable -> L32
    L57:
        throw th;     // Catch: Exception -> L44
    L32:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L44
        throw th;     // Catch: Exception -> L44
    L36:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L40
    L58:
        throw th;     // Catch: Exception -> L44
    L40:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L44
        throw th;     // Catch: Exception -> L44
    L6:
        if (r82.contains("<SVG") == true) goto L14;
        if (r82.trim().startsWith("/") == false) goto L55;
        File r0 = new File(r82.trim());     // Catch: Exception -> L44
        if (r0.exists() == false) goto L56;
        processSelectedImage(r9, Uri.fromFile(r0));     // Catch: Exception -> L44
        return;
    L56:
        return;
    L55:
        return;
    L44:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$processSelectedImage$11(Context r12, Uri r13) {
        File r4 = null;
        Cursor r132 = r12.getContentResolver().query(r13, null, null, null, null);     // Catch: Throwable -> L20 Exception -> L22
        if (r132 != null) goto L159;
    L24:
        String r7 = null;
    L25:
        if (r132 == null) goto L28;
        r132.close();     // Catch: Throwable -> L20 Exception -> L22
    L28:
        if (TextUtils.isEmpty(r7) == false) goto L30;
        r7 = "icon_" + System.currentTimeMillis();     // Catch: Throwable -> L20 Exception -> L22
    L30:
        File r133 = new File(ApplicationLoader.applicationContext.getCacheDir(), "temp_import_" + System.currentTimeMillis() + "_raw");     // Catch: Throwable -> L20 Exception -> L22
        InputStream r3 = r12.getContentResolver().openInputStream(r13);     // Catch: Throwable -> L55 Exception -> L57
        FileOutputStream r42 = new FileOutputStream(r133);     // Catch: Throwable -> L49
        if (r3 != null) goto L173;
    L51:
        r42.close();     // Catch: Throwable -> L49
        if (r3 == null) goto L171;
        r3.close();     // Catch: Throwable -> L55 Exception -> L57
    L171:
        FileInputStream r43 = new FileInputStream(r133);     // Catch: Throwable -> L55 Exception -> L84
        byte[] r5 = new byte[1024];     // Catch: Throwable -> L71
        int r8 = r43.read(r5);     // Catch: Throwable -> L71
        if (r8 <= 0) goto L74;
        String r52 = new String(r5, 0, r8).trim().toLowerCase(Locale.ROOT);     // Catch: Throwable -> L71
        if (r52.contains("<svg") == false) goto L67;
    L73:
        boolean r53 = true;
    L168:
        r43.close();     // Catch: Throwable -> L55 Exception -> L77
    L87:
        if (r53 == false) goto L90;
        String r0 = "svg";
    L100:
        if (r7.toLowerCase().endsWith("." + r0) == true) goto L105;
        int r44 = r7.lastIndexOf(46);     // Catch: Throwable -> L55 Exception -> L57
        if (r44 <= 0) goto L104;
        r7 = r7.substring(0, r44);     // Catch: Throwable -> L55 Exception -> L57
    L104:
        r7 = r7 + "." + r0;     // Catch: Throwable -> L55 Exception -> L57
    L105:
        String r45 = r7;
        File r32 = new File(ApplicationLoader.applicationContext.getCacheDir(), "temp_import_" + System.currentTimeMillis() + "." + r0);     // Catch: Throwable -> L55 Exception -> L57
        if (r133.renameTo(r32) == false) goto L131;
        if (r53 == false) goto L128;
        FileInputStream r1 = new FileInputStream(r32);     // Catch: Throwable -> L55 Exception -> L57
        SVG r02 = SVG.getFromInputStream(r1);     // Catch: Throwable -> L112
        if (r02.getDocumentWidth() <= 0.0f) goto L114;
        float r2 = r02.getDocumentWidth();     // Catch: Throwable -> L112
    L115:
        int r22 = (int) r2;     // Catch: Throwable -> L112
        if (r02.getDocumentHeight() <= 0.0f) goto L118;
        float r03 = r02.getDocumentHeight();     // Catch: Throwable -> L112
    L119:
        int r04 = (int) r03;
        r1.close();     // Catch: Throwable -> L55 Exception -> L57
    L129:
        updateNewIconFromFile(r12, r32, r45, r22, r04);     // Catch: Throwable -> L55 Exception -> L57
        goto L131
    L118:
        r03 = r02.getDocumentViewBox().height();     // Catch: Throwable -> L112
        goto L119
    L114:
        r2 = r02.getDocumentViewBox().width();     // Catch: Throwable -> L112
    L112:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L124
        throw th;     // Catch: Throwable -> L55 Exception -> L57
    L124:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L55 Exception -> L57
        throw th;     // Catch: Throwable -> L55 Exception -> L57
    L128:
        BitmapFactory.Options r05 = new BitmapFactory.Options();     // Catch: Throwable -> L55 Exception -> L57
        r05.inJustDecodeBounds = true;     // Catch: Throwable -> L55 Exception -> L57
        BitmapFactory.decodeFile(r32.getAbsolutePath(), r05);     // Catch: Throwable -> L55 Exception -> L57
        r22 = r05.outWidth;     // Catch: Throwable -> L55 Exception -> L57
        r04 = r05.outHeight;     // Catch: Throwable -> L55 Exception -> L57
    L131:
        if (r133.exists() == false) goto L182;
        r133.delete();
        return;
    L182:
        return;
    L90:
        if (r7.toLowerCase().endsWith(".jpg") == false) goto L92;
    L98:
        r0 = "jpg";
        goto L100
    L92:
        if (r7.toLowerCase().endsWith(".jpeg") == true) goto L98;
        if (r7.toLowerCase().endsWith(".webp") == false) goto L97;
        r0 = "webp";
        goto L100
    L97:
        r0 = "png";
    L77:
        e = e;
    L86:
        FileLog.e(e);     // Catch: Throwable -> L55 Exception -> L57
        goto L87
    L67:
        if (r52.startsWith("<?xml") == false) goto L74;
        if (r52.contains("<svg") == true) goto L73;
    L74:
        r53 = false;
    L71:
        th = move-exception;
        r43.close();     // Catch: Throwable -> L81
        throw th;     // Catch: Throwable -> L55 Exception -> L84
    L81:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L55 Exception -> L84
        throw th;     // Catch: Throwable -> L55 Exception -> L84
    L84:
        e = e;
        r53 = false;
        goto L86
    L173:
        byte[] r82 = new byte[4096];     // Catch: Throwable -> L41
    L37:
        int r9 = r3.read(r82);     // Catch: Throwable -> L41
        if (r9 == (-1)) goto L51;
        r42.write(r82, 0, r9);     // Catch: Throwable -> L41
        goto L37
    L41:
        th = move-exception;
        r42.close();     // Catch: Throwable -> L45
    L177:
        throw th;     // Catch: Throwable -> L49
    L45:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L49
        throw th;     // Catch: Throwable -> L49
    L49:
        th = move-exception;
        if (r3 == null) goto L180;
        r3.close();     // Catch: Throwable -> L136
        throw th;     // Catch: Throwable -> L55 Exception -> L57
    L136:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L55 Exception -> L57
        throw th;     // Catch: Throwable -> L55 Exception -> L57
    L180:
        throw th;     // Catch: Throwable -> L55 Exception -> L57
    L57:
        e = move-exception;
        Exception r11 = e;
        r4 = r133;
    L140:
        FileLog.e(r11);     // Catch: Throwable -> L20
        if (r4 != null) goto L143;
        return;
    L143:
        if (r4.exists() == false) goto L185;
        r4.delete();
        return;
    L185:
        return;
    L55:
        th = move-exception;
        Throwable r112 = th;
        r4 = r133;
    L146:
        if (r4 != null) goto L148;
        throw r112;
    L148:
        if (r4.exists() == false) goto L187;
        r4.delete();
        throw r112;
    L187:
        throw r112;
    L159:
    L12:
        th = move-exception;
        r132.close();     // Catch: Throwable -> L16
        throw th;     // Catch: Throwable -> L20 Exception -> L22
    L16:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L20 Exception -> L22
        throw th;     // Catch: Throwable -> L20 Exception -> L22
    L7:
        if (r132.moveToFirst() == false) goto L24;
        int r72 = r132.getColumnIndex("_display_name");     // Catch: Throwable -> L12
        if (r72 == (-1)) goto L24;
        r7 = r132.getString(r72);     // Catch: Throwable -> L12
    L22:
        e = move-exception;
        r11 = e;
    L20:
        th = move-exception;
        r112 = th;
        goto L146
    }

    private /* synthetic */ Unit lambda$startPicker$8(Activity r2, Uri r3) {
        this.waitingForResult = false;
        if (r3 == null) goto L6;
        processSelectedImage(r2, r3);
    L6:
        return Unit.INSTANCE;
    }

    private /* synthetic */ void lambda$updateNewIconFromFile$9(File r2, String r3, Drawable r4, int r5, int r6) {
        if (isDismissed() == false) goto L6;
        r2.delete();
        return;
    L6:
        File r0 = this.newIconTempFile;
        if (r0 != null) goto L9;
    L13:
        this.newIconTempFile = r2;
        this.newIconOriginalName = r3;
        this.newDrawable = r4;
        ButtonWithCounterView r22 = this.resetButton;
        if (r22 == null) goto L16;
        r22.setText(LocaleController.getString(R.string.Reset), false);
    L16:
        updateNewInfo(this.newDrawable, this.newIconOriginalName, r5, r6);
        return;
    L9:
        if (r0.exists() == false) goto L13;
        if (this.newIconTempFile.equals(r2) == true) goto L13;
        this.newIconTempFile.delete();
        goto L13
    }

    private void loadDrawables(Context r3) {
        Utilities.globalQueue.postRunnable(new ReplaceIconBottomSheet$$ExternalSyntheticLambda0(this, r3));
    }

    private void processClipboardText(Context r3, CharSequence r4) {
        Utilities.globalQueue.postRunnable(new ReplaceIconBottomSheet$$ExternalSyntheticLambda9(this, r4, r3));
    }

    private void processSelectedImage(Context r3, Uri r4) {
        Utilities.globalQueue.postRunnable(new ReplaceIconBottomSheet$$ExternalSyntheticLambda8(this, r3, r4));
    }

    private void startPicker(Activity r3, boolean r4) {
        this.waitingForResult = true;
        IconManager.INSTANCE.startIconPicker(r3, r4, new ReplaceIconBottomSheet$$ExternalSyntheticLambda10(this, r3));
    }

    private void updateNewIconFromFile(Context r9, File r10, String r11, int r12, int r13) {
        Bitmap r0 = IconManager.INSTANCE.createBitmapFromFile(r10.getAbsolutePath(), this.resId, AndroidUtilities.displayMetrics.densityDpi, r9.getTheme());
        if (r0 == null) goto L6;
        AndroidUtilities.runOnUIThread(new ReplaceIconBottomSheet$$ExternalSyntheticLambda11(this, r10, r11, new BitmapDrawable(r9.getResources(), r0), r12, r13));
        return;
    }

    private void updateNewInfo(Drawable r4, String r5, int r6, int r7) {
        IconInfoView r0 = this.newIconInfoView;
        if (r0 == null) goto L11;
        int r1 = this.loadedOriginalWidth;
        if (r1 <= 0) goto L9;
        int r2 = this.loadedOriginalHeight;
        if (r2 <= 0) goto L9;
        r0.setTargetDimensions(r1, r2);
    L9:
        this.newIconInfoView.update(r4, r5, r6, r7);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        if (this.waitingForResult == false) goto L5;
        return;
    L5:
        super.dismiss();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        if (this.needSave == true) goto L5;
    L10:
        File r0 = this.newIconTempFile;
        if (r0 == null) goto L16;
        if (r0.exists() == false) goto L16;
        this.newIconTempFile.delete();
    L16:
        if (this.needReset == false) goto L19;
        IconManager.INSTANCE.resetCustomIcon(this.iconPack.getId(), this.resId);
        return;
    L19:
        return;
    L5:
        if (this.newIconTempFile == null) goto L10;
        if (this.needReset == true) goto L10;
        IconManager.INSTANCE.saveCustomIcon(this.iconPack.getId(), this.resId, this.newIconTempFile, this.newIconOriginalName);
    }
}
