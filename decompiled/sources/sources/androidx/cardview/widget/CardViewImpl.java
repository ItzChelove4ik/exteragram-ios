package androidx.cardview.widget;

import android.content.Context;
import android.content.res.ColorStateList;

/* loaded from: classes3.dex */
interface CardViewImpl {
    ColorStateList getBackgroundColor(CardViewDelegate r1);

    float getElevation(CardViewDelegate r1);

    float getMaxElevation(CardViewDelegate r1);

    float getMinHeight(CardViewDelegate r1);

    float getMinWidth(CardViewDelegate r1);

    float getRadius(CardViewDelegate r1);

    void initStatic();

    void initialize(CardViewDelegate r1, Context r2, ColorStateList r3, float r4, float r5, float r6);

    void onCompatPaddingChanged(CardViewDelegate r1);

    void onPreventCornerOverlapChanged(CardViewDelegate r1);

    void setBackgroundColor(CardViewDelegate r1, ColorStateList r2);

    void setElevation(CardViewDelegate r1, float r2);

    void setMaxElevation(CardViewDelegate r1, float r2);

    void setRadius(CardViewDelegate r1, float r2);

    void updatePadding(CardViewDelegate r1);
}
