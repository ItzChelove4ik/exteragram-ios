package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.TintTypedArray;
import com.google.android.material.R;

/* loaded from: classes5.dex */
public class TabItem extends View {
    public final int customLayout;
    public final Drawable icon;
    public final CharSequence text;

    public TabItem(Context r2) {
        this(r2, null);
    }

    public TabItem(Context r2, AttributeSet r3) {
        super(r2, r3);
        TintTypedArray r22 = TintTypedArray.obtainStyledAttributes(r2, r3, R.styleable.TabItem);
        this.text = r22.getText(R.styleable.TabItem_android_text);
        this.icon = r22.getDrawable(R.styleable.TabItem_android_icon);
        this.customLayout = r22.getResourceId(R.styleable.TabItem_android_layout, 0);
        r22.recycle();
    }
}
