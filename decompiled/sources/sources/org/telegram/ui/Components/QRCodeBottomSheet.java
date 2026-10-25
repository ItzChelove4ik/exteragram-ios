package org.telegram.ui.Components;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Outline;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaError;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.HashMap;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.TelegramQRCodeWriter;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class QRCodeBottomSheet extends BottomSheet {
    private TextView button2TextView;
    private final TextView buttonTextView;
    private final TextView help;
    RLottieImageView iconImage;
    int imageSize;
    Bitmap qrCode;

    /* renamed from: org.telegram.ui.Components.QRCodeBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends ImageView {
        final /* synthetic */ QRCodeBottomSheet this$0;

        public AnonymousClass1(QRCodeBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.ImageView, android.view.View
        public void onMeasure(int r2, int r3) {
            int r22 = View.MeasureSpec.getSize(r2);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r22, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r22, TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.QRCodeBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewOutlineProvider {
        final /* synthetic */ QRCodeBottomSheet this$0;

        public AnonymousClass2(QRCodeBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getMeasuredWidth(), r7.getMeasuredHeight(), AndroidUtilities.dp(12.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.QRCodeBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        float lastX;
        final /* synthetic */ QRCodeBottomSheet this$0;
        final /* synthetic */ ImageView val$imageView;

        public AnonymousClass3(QRCodeBottomSheet r1, Context r2, ImageView r3) {
            this.this$0 = r1;
            this.val$imageView = r3;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            float r0 = (this.this$0.imageSize / 768.0f) * this.val$imageView.getMeasuredHeight();
            if (this.lastX == r0) goto L6;
            this.lastX = r0;
            ViewGroup.LayoutParams r1 = this.this$0.iconImage.getLayoutParams();
            int r02 = (int) r0;
            this.this$0.iconImage.getLayoutParams().width = r02;
            r1.height = r02;
            super.onMeasure(r4, r5);
            return;
        }
    }

    /* renamed from: $r8$lambda$QOZPm9hIEuBsBT-y05Gh6cfKH4g, reason: not valid java name */
    public static /* synthetic */ void m12273$r8$lambda$QOZPm9hIEuBsBTy05Gh6cfKH4g(QRCodeBottomSheet r0, Context r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$hGgDgb2IyX0ylcB_79toVXuOp-0, reason: not valid java name */
    public static /* synthetic */ void m12274$r8$lambda$hGgDgb2IyX0ylcB_79toVXuOp0(String r1, Context r2, View r3) {
        Intent r32 = new Intent("android.intent.action.SEND");
        r32.setType("text/plain");
        r32.putExtra("android.intent.extra.TEXT", r1);
        Intent r12 = Intent.createChooser(r32, LocaleController.getString(R.string.ShareLink));
        r12.setFlags(268435456);
        r2.startActivity(r12);
    }

    public QRCodeBottomSheet(Context r8, String r9, String r10, String r11, boolean r12) {
        this(r8, r9, r10, r11, r12, null);
    }

    public QRCodeBottomSheet(Context r19, String r20, String r21, String r22, boolean r23, Theme.ResourcesProvider r24) {
        super(r19, false, r24);
        fixNavigationBar();
        setTitle(r20, true);
        AnonymousClass1 r5 = new AnonymousClass1(this, r19);
        r5.setScaleType(ImageView.ScaleType.FIT_XY);
        r5.setOutlineProvider(new AnonymousClass2(this));
        r5.setClipToOutline(true);
        LinearLayout r6 = new LinearLayout(r19);
        r6.setOrientation(1);
        r6.setPadding(0, AndroidUtilities.dp(16.0f), 0, 0);
        Bitmap r7 = createQR(r19, r21, this.qrCode);
        this.qrCode = r7;
        r5.setImageBitmap(r7);
        RLottieImageView r72 = new RLottieImageView(r19);
        this.iconImage = r72;
        r72.setScaleType(ImageView.ScaleType.FIT_CENTER);
        this.iconImage.setBackgroundColor(-1);
        AnonymousClass3 r73 = new AnonymousClass3(this, r19, r5);
        r73.addView(r5, LayoutHelper.createFrame(-1, -1.0f));
        r73.addView(this.iconImage, LayoutHelper.createFrame(60, 60, 17));
        r6.addView(r73, LayoutHelper.createLinear(Opcodes.REM_INT_LIT8, Opcodes.REM_INT_LIT8, 1, 30, 0, 30, 0));
        TextView r52 = new TextView(r19);
        this.help = r52;
        r52.setTextSize(1, 14.0f);
        r52.setText(r22);
        r52.setGravity(1);
        r6.addView(r52, LayoutHelper.createFrame(-1, -2.0f, 0, 40.0f, 8.0f, 40.0f, 8.0f));
        TextView r53 = new TextView(r19);
        this.buttonTextView = r53;
        r53.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        r53.setGravity(17);
        r53.setTextSize(1, 14.0f);
        r53.setTypeface(AndroidUtilities.bold());
        r53.setText(LocaleController.getString(R.string.ShareQrCode));
        r53.setOnClickListener(new QRCodeBottomSheet$$ExternalSyntheticLambda0(this, r19));
        r6.addView(r53, LayoutHelper.createLinear(-1, 48, 80, 16, 15, 16, 3));
        if (r23 == false) goto L5;
        TextView r54 = new TextView(r19);
        this.button2TextView = r54;
        r54.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        this.button2TextView.setGravity(17);
        this.button2TextView.setTextSize(1, 14.0f);
        this.button2TextView.setText(LocaleController.getString(R.string.ShareLink));
        this.button2TextView.setOnClickListener(new QRCodeBottomSheet$$ExternalSyntheticLambda1(r21, r19));
        r6.addView(this.button2TextView, LayoutHelper.createLinear(-1, 48, 80, 16, 3, 16, 16));
    L5:
        updateColors();
        ScrollView r2 = new ScrollView(r19);
        r2.addView(r6);
        setCustomView(r2);
    }

    private /* synthetic */ void lambda$new$0(Context r3, View r4) {
        Uri r42 = AndroidUtilities.getBitmapShareUri(this.qrCode, "qr_tmp.png", Bitmap.CompressFormat.PNG);
        if (r42 == null) goto L12;
        Intent r0 = new Intent("android.intent.action.SEND");
        r0.setType("image/*");
        r0.putExtra("android.intent.extra.STREAM", r42);
        AndroidUtilities.findActivity(r3).startActivityForResult(Intent.createChooser(r0, getTitleView().getText()), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: ActivityNotFoundException -> L7
        return;
    L7:
        e = move-exception;
        e.printStackTrace();
        return;
    }

    public Bitmap createQR(Context r7, String r8, Bitmap r9) {
        HashMap r4 = new HashMap();     // Catch: Exception -> L4
        r4.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);     // Catch: Exception -> L4
        r4.put(EncodeHintType.MARGIN, 0);     // Catch: Exception -> L4
        TelegramQRCodeWriter r0 = new TelegramQRCodeWriter();     // Catch: Exception -> L4
        Bitmap r72 = r0.encode(r8, 768, 768, r4, r9);     // Catch: Exception -> L4
        this.imageSize = r0.getImageSize();     // Catch: Exception -> L4
        return r72;
    L4:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public void setCenterAnimation(int r3) {
        this.iconImage.setAutoRepeat(true);
        this.iconImage.setAnimation(r3, 60, 60);
        this.iconImage.playAnimation();
    }

    public void setCenterImage(Bitmap r1) {
        this.iconImage.setImageBitmap(r1);
    }

    public void updateColors() {
        this.buttonTextView.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        TextView r0 = this.buttonTextView;
        int r1 = AndroidUtilities.dp(24.0f);
        int r2 = Theme.key_featuredStickers_addButton;
        r0.setBackground(Theme.createSimpleSelectorRoundRectDrawable(r1, getThemedColor(r2), getThemedColor(Theme.key_featuredStickers_addButtonPressed)));
        TextView r02 = this.button2TextView;
        if (r02 == null) goto L5;
        r02.setTextColor(getThemedColor(r2));
        this.button2TextView.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(getThemedColor(r2), Math.min(255, Color.alpha(getThemedColor(Theme.key_listSelector)) * 2)), 7));
    L5:
        TextView r03 = this.help;
        int r12 = Theme.key_windowBackgroundWhiteGrayText;
        r03.setTextColor(getThemedColor(r12));
        this.help.setTextColor(getThemedColor(r12));
        if (getTitleView() == null) goto L8;
        getTitleView().setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
    L8:
        setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
    }
}
