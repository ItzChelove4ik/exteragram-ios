package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class PhotoAttachPermissionCell extends FrameLayout {
    private ImageView imageView;
    private ImageView imageView2;
    private int itemSize;
    private final Theme.ResourcesProvider resourcesProvider;
    private TextView textView;

    public PhotoAttachPermissionCell(Context r12, Theme.ResourcesProvider r13) {
        super(r12);
        this.resourcesProvider = r13;
        ImageView r132 = new ImageView(r12);
        this.imageView = r132;
        ImageView.ScaleType r0 = ImageView.ScaleType.CENTER;
        r132.setScaleType(r0);
        ImageView r133 = this.imageView;
        int r2 = getThemedColor(Theme.key_chat_attachPermissionImage);
        PorterDuff.Mode r3 = PorterDuff.Mode.MULTIPLY;
        r133.setColorFilter(new PorterDuffColorFilter(r2, r3));
        addView(this.imageView, LayoutHelper.createFrame(44, 44.0f, 17, 5.0f, 0.0f, 0.0f, 27.0f));
        ImageView r134 = new ImageView(r12);
        this.imageView2 = r134;
        r134.setScaleType(r0);
        this.imageView2.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_attachPermissionMark), r3));
        addView(this.imageView2, LayoutHelper.createFrame(44, 44.0f, 17, 5.0f, 0.0f, 0.0f, 27.0f));
        TextView r135 = new TextView(r12);
        this.textView = r135;
        r135.setTextColor(getThemedColor(Theme.key_chat_attachPermissionText));
        this.textView.setTextSize(1, 12.0f);
        this.textView.setGravity(17);
        addView(this.textView, LayoutHelper.createFrame(-2, -2.0f, 17, 5.0f, 13.0f, 5.0f, 0.0f));
        this.itemSize = AndroidUtilities.dp(80.0f);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.itemSize, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.itemSize + AndroidUtilities.dp(2.0f), TLObject.FLAG_30));
    }

    public void setItemSize(int r1) {
        this.itemSize = r1;
    }

    public void setType(int r8) {
        ImageView r0 = this.imageView;
        if (r8 != 0) goto L6;
        r0.setImageResource(R.drawable.permissions_camera1);
        this.imageView2.setImageResource(R.drawable.permissions_camera2);
        this.textView.setText(LocaleController.getString(R.string.CameraPermissionText));
        this.imageView.setLayoutParams(LayoutHelper.createFrame(44, 44.0f, 17, 5.0f, 0.0f, 0.0f, 27.0f));
        this.imageView2.setLayoutParams(LayoutHelper.createFrame(44, 44.0f, 17, 5.0f, 0.0f, 0.0f, 27.0f));
        return;
    L6:
        r0.setImageResource(R.drawable.permissions_gallery1);
        this.imageView2.setImageResource(R.drawable.permissions_gallery2);
        this.textView.setText(LocaleController.getString(R.string.GalleryPermissionText));
        this.imageView.setLayoutParams(LayoutHelper.createFrame(44, 44.0f, 17, 0.0f, 0.0f, 2.0f, 27.0f));
        this.imageView2.setLayoutParams(LayoutHelper.createFrame(44, 44.0f, 17, 0.0f, 0.0f, 2.0f, 27.0f));
    }
}
