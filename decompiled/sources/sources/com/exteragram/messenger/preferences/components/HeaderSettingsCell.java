package com.exteragram.messenger.preferences.components;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.exteragram.messenger.preferences.utils.IconShapeHelper;
import com.exteragram.messenger.utils.ui.MonetUtils;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;

/* loaded from: classes4.dex */
public class HeaderSettingsCell extends LinearLayout implements CustomPreferenceCell {
    public final RLottieImageView imageView;
    private Path shapePath;
    public final TextView subtitleTextView;
    public final TextView titleTextView;

    /* renamed from: com.exteragram.messenger.preferences.components.HeaderSettingsCell$1, reason: invalid class name */
    public class AnonymousClass1 extends RLottieImageView {
        final /* synthetic */ HeaderSettingsCell this$0;

        public AnonymousClass1(HeaderSettingsCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void draw(Canvas r2) {
            r2.save();
            r2.clipPath(HeaderSettingsCell.m1710$$Nest$mgetPath(this.this$0));
            super.draw(r2);
            r2.restore();
        }
    }

    /* renamed from: -$$Nest$mgetPath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m1710$$Nest$mgetPath(HeaderSettingsCell r0) {
        return r0.getPath();
    }

    public HeaderSettingsCell(Context r13) {
        super(r13);
        setOrientation(1);
        setGravity(17);
        Drawable r2 = ContextCompat.getDrawable(r13, R.drawable.ic_foreground).mutate();
        Theme.ThemeInfo r3 = Theme.getActiveTheme();
        int r4 = ContextCompat.getColor(r13, R.color.ic_background);
        if (r3.isMonet() == true) goto L5;
    L15:
        AnonymousClass1 r32 = new AnonymousClass1(this, r13);
        this.imageView = r32;
        r32.setBackgroundColor(r4);
        r32.setScaleType(ImageView.ScaleType.CENTER_CROP);
        r32.setImageDrawable(r2);
        addView(r32, LayoutHelper.createLinear(72, 72, 49, 0, 28, 0, 0));
        TextView r22 = new TextView(r13);
        this.titleTextView = r22;
        r22.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r22.setTypeface(AndroidUtilities.bold());
        r22.setTextSize(1, 20.0f);
        r22.setText(LocaleController.getString(R.string.exteraAppName));
        r22.setLines(1);
        r22.setMaxLines(1);
        r22.setSingleLine(true);
        r22.setPadding(0, 0, 0, 0);
        r22.setGravity(17);
        addView(r22, LayoutHelper.createLinear(-2, -2, 49, 50, 16, 50, 0));
        TextView r23 = new TextView(r13);
        this.subtitleTextView = r23;
        r23.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        r23.setTypeface(AndroidUtilities.bold());
        r23.setTextSize(1, 15.0f);
        r23.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        PackageInfo r132 = ApplicationLoader.applicationContext.getPackageManager().getPackageInfo(ApplicationLoader.applicationContext.getPackageName(), 0);     // Catch: PackageManager.NameNotFoundException -> L22
        StringBuilder r0 = new StringBuilder(BuildVars.BUILD_VERSION_STRING);
        if (r132 == null) goto L20;
        r0.append(" (");
        r0.append(r132.versionCode);
        r0.append(")");
    L20:
        r23.setText(r0);
        r23.setGravity(17);
        r23.setLines(0);
        r23.setMaxLines(0);
        r23.setSingleLine(false);
        r23.setPadding(0, 0, 0, 0);
        addView(r23, LayoutHelper.createLinear(-2, -2, 49, 60, 2, 60, 28));
        return;
    L22:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    L5:
        if (Build.VERSION.SDK_INT < 31) goto L15;
        if (r3.isDark() == false) goto L9;
        String r24 = "a2_800";
    L10:
        r4 = MonetUtils.getColor(r24);
        r2 = ContextCompat.getDrawable(r13, R.drawable.ic_foreground_solid).mutate();
        if (r3.isDark() == false) goto L13;
        String r33 = "a1_200";
    L14:
        r2.setColorFilter(new PorterDuffColorFilter(MonetUtils.getColor(r33), PorterDuff.Mode.MULTIPLY));
        goto L15
    L13:
        r33 = "a1_700";
        goto L14
    L9:
        r24 = "a1_100";
        goto L10
    }

    private Path getPath() {
        if (this.shapePath != null) goto L6;
        this.shapePath = IconShapeHelper.INSTANCE.getFinalIconShapePath(72.0f, 72.0f, 18.0f);
    L6:
        return this.shapePath;
    }

    @Override // com.exteragram.messenger.preferences.components.CustomPreferenceCell
    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof HeaderSettingsCell) == false) goto L14;
        HeaderSettingsCell r52 = (HeaderSettingsCell) r5;
        if (this.imageView.equals(r52.imageView) == false) goto L14;
        if (this.titleTextView.equals(r52.titleTextView) == false) goto L14;
        if (this.subtitleTextView.equals(r52.subtitleTextView) == false) goto L14;
        return true;
    L14:
        return false;
    }

    @Override // android.view.View
    public void invalidate() {
        this.shapePath = null;
        this.imageView.invalidate();
        super.invalidate();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r1), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(0, 0));
    }
}
