package org.telegram.ui.Business;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public class ProfileLocationCell extends LinearLayout {
    private final ImageReceiver imageReceiver;
    private boolean needDivider;
    private final Theme.ResourcesProvider resourcesProvider;
    private final TextView textView1;
    private final TextView textView2;
    private final LoadingDrawable thumbDrawable;

    public static final class Factory extends UItem.UItemFactory<ProfileLocationCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            r1.setId(r2.id);
            ((ProfileLocationCell) r1).set((TLRPC.TL_businessLocation) r2.object, r3);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public ProfileLocationCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new ProfileLocationCell(r1, r5);
        }
    }

    public ProfileLocationCell(Context r18, Theme.ResourcesProvider r19) {
        super(r18);
        ImageReceiver r3 = new ImageReceiver(this);
        this.imageReceiver = r3;
        this.resourcesProvider = r19;
        setOrientation(1);
        LoadingDrawable r5 = new LoadingDrawable();
        this.thumbDrawable = r5;
        int r6 = Theme.key_windowBackgroundWhiteBlackText;
        int r7 = Theme.getColor(r6, r19);
        r5.setColors(Theme.multAlpha(r7, 0.05f), Theme.multAlpha(r7, 0.15f), Theme.multAlpha(r7, 0.1f), Theme.multAlpha(r7, 0.3f));
        r5.setRadiiDp(4.0f);
        r5.strokePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        r3.setRoundRadius(AndroidUtilities.dp(4.0f));
        TextView r32 = new TextView(r18);
        this.textView1 = r32;
        int r72 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r52 = 5;
    L6:
        r32.setGravity(r52);
        r32.setTextColor(Theme.getColor(r6, r19));
        r32.setTextSize(1, 16.0f);
        boolean r53 = LocaleController.isRTL;
        if (r53 == false) goto L9;
        int r13 = 70;
    L10:
        if (r53 == false) goto L12;
        int r15 = 18;
    L13:
        addView(r32, LayoutHelper.createLinear(-1, -2, 55, r13, 10, r15, 4));
        TextView r33 = new TextView(r18);
        this.textView2 = r33;
        if (LocaleController.isRTL == false) goto L16;
        r72 = 5;
    L16:
        r33.setGravity(r72);
        r33.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r19));
        r33.setText(LocaleController.getString(R.string.BusinessProfileLocation));
        r33.setTextSize(1, 13.0f);
        boolean r1 = LocaleController.isRTL;
        if (r1 == false) goto L19;
        int r132 = 70;
    L20:
        if (r1 == false) goto L22;
        int r152 = 18;
    L23:
        addView(r33, LayoutHelper.createLinear(-1, -2, 55, r132, 0, r152, 8));
        setWillNotDraw(false);
        return;
    L22:
        r152 = 70;
        goto L23
    L19:
        r132 = 18;
        goto L20
    L12:
        r15 = 70;
        goto L13
    L9:
        r13 = 18;
        goto L10
    L5:
        r52 = 3;
        goto L6
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r8) {
        ImageReceiver r0 = this.imageReceiver;
        if (LocaleController.isRTL == false) goto L6;
        int r1 = AndroidUtilities.dp(16.0f);
    L7:
        r0.setImageCoords(r1, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(44.0f), AndroidUtilities.dp(44.0f));
        this.imageReceiver.draw(r8);
        super.onDraw(r8);
        if (this.needDivider == false) goto L22;
        Paint r02 = Theme.getThemePaint("paintDivider", this.resourcesProvider);
        if (r02 != null) goto L12;
        r02 = Theme.dividerPaint;
    L12:
        Paint r6 = r02;
        float r12 = 21.33f;
        if (LocaleController.isRTL == false) goto L15;
        float r03 = 0.0f;
    L16:
        float r04 = AndroidUtilities.dp(r03);
        float r3 = getMeasuredHeight() - 1;
        int r4 = getWidth();
        if (LocaleController.isRTL == true) goto L20;
        r12 = 0.0f;
    L20:
        r8.drawRect(r04, r3, r4 - AndroidUtilities.dp(r12), getMeasuredHeight(), r6);
        return;
    L15:
        r03 = 21.33f;
        goto L16
    L22:
        return;
    L6:
        r1 = getWidth() - AndroidUtilities.dp(60.0f);
        goto L7
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
    }

    public void set(TLRPC.TL_businessLocation r11, boolean r12) {
        if (r11 == null) goto L7;
        this.textView1.setText(r11.address);
        if (r11.geo_point == null) goto L6;
        this.imageReceiver.setImage(ImageLocation.getForWebFile(WebFile.createWithGeoPoint(r11.geo_point, AndroidUtilities.dp(44.0f), AndroidUtilities.dp(44.0f), 15, Math.min(2, (int) Math.ceil(AndroidUtilities.density)))), "44_44", this.thumbDrawable, 0, null, null, 0);
        goto L7
    L6:
        this.imageReceiver.setImageBitmap(null);
    L7:
        this.needDivider = r12;
        setPadding(0, 0, 0, r12 ? 1 : 0);
        invalidate();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (r2 != this.thumbDrawable) goto L5;
        return true;
    L5:
        if (super.verifyDrawable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }
}
