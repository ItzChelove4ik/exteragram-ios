package androidx.appcompat.widget;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.core.content.res.ResourcesCompat;
import java.io.InputStream;

/* loaded from: classes3.dex */
abstract class ResourcesWrapper extends Resources {
    private final Resources mResources;

    public ResourcesWrapper(Resources r4) {
        super(r4.getAssets(), r4.getDisplayMetrics(), r4.getConfiguration());
        this.mResources = r4;
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getAnimation(int r1) {
        return this.mResources.getAnimation(r1);
    }

    @Override // android.content.res.Resources
    public boolean getBoolean(int r1) {
        return this.mResources.getBoolean(r1);
    }

    @Override // android.content.res.Resources
    public int getColor(int r1) {
        return this.mResources.getColor(r1);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int r1) {
        return this.mResources.getColorStateList(r1);
    }

    @Override // android.content.res.Resources
    public Configuration getConfiguration() {
        return this.mResources.getConfiguration();
    }

    @Override // android.content.res.Resources
    public float getDimension(int r1) {
        return this.mResources.getDimension(r1);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelOffset(int r1) {
        return this.mResources.getDimensionPixelOffset(r1);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelSize(int r1) {
        return this.mResources.getDimensionPixelSize(r1);
    }

    @Override // android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        return this.mResources.getDisplayMetrics();
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int r1, Resources.Theme r2) {
        return ResourcesCompat.getDrawable(this.mResources, r1, r2);
    }

    public final Drawable getDrawableCanonical(int r1) {
        return super.getDrawable(r1);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int r2, int r3) {
        return ResourcesCompat.getDrawableForDensity(this.mResources, r2, r3, null);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int r1, int r2, Resources.Theme r3) {
        return ResourcesCompat.getDrawableForDensity(this.mResources, r1, r2, r3);
    }

    @Override // android.content.res.Resources
    public float getFraction(int r1, int r2, int r3) {
        return this.mResources.getFraction(r1, r2, r3);
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String r1, String r2, String r3) {
        return this.mResources.getIdentifier(r1, r2, r3);
    }

    @Override // android.content.res.Resources
    public int[] getIntArray(int r1) {
        return this.mResources.getIntArray(r1);
    }

    @Override // android.content.res.Resources
    public int getInteger(int r1) {
        return this.mResources.getInteger(r1);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int r1) {
        return this.mResources.getLayout(r1);
    }

    @Override // android.content.res.Resources
    public Movie getMovie(int r1) {
        return this.mResources.getMovie(r1);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int r1, int r2) {
        return this.mResources.getQuantityString(r1, r2);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int r1, int r2, Object... r3) {
        return this.mResources.getQuantityString(r1, r2, r3);
    }

    @Override // android.content.res.Resources
    public CharSequence getQuantityText(int r1, int r2) {
        return this.mResources.getQuantityText(r1, r2);
    }

    @Override // android.content.res.Resources
    public String getResourceEntryName(int r1) {
        return this.mResources.getResourceEntryName(r1);
    }

    @Override // android.content.res.Resources
    public String getResourceName(int r1) {
        return this.mResources.getResourceName(r1);
    }

    @Override // android.content.res.Resources
    public String getResourcePackageName(int r1) {
        return this.mResources.getResourcePackageName(r1);
    }

    @Override // android.content.res.Resources
    public String getResourceTypeName(int r1) {
        return this.mResources.getResourceTypeName(r1);
    }

    @Override // android.content.res.Resources
    public String getString(int r1) {
        return this.mResources.getString(r1);
    }

    @Override // android.content.res.Resources
    public String getString(int r1, Object... r2) {
        return this.mResources.getString(r1, r2);
    }

    @Override // android.content.res.Resources
    public String[] getStringArray(int r1) {
        return this.mResources.getStringArray(r1);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int r1) {
        return this.mResources.getText(r1);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int r1, CharSequence r2) {
        return this.mResources.getText(r1, r2);
    }

    @Override // android.content.res.Resources
    public CharSequence[] getTextArray(int r1) {
        return this.mResources.getTextArray(r1);
    }

    @Override // android.content.res.Resources
    public void getValue(int r1, TypedValue r2, boolean r3) {
        this.mResources.getValue(r1, r2, r3);
    }

    @Override // android.content.res.Resources
    public void getValue(String r1, TypedValue r2, boolean r3) {
        this.mResources.getValue(r1, r2, r3);
    }

    @Override // android.content.res.Resources
    public void getValueForDensity(int r1, int r2, TypedValue r3, boolean r4) {
        this.mResources.getValueForDensity(r1, r2, r3, r4);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getXml(int r1) {
        return this.mResources.getXml(r1);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet r1, int[] r2) {
        return this.mResources.obtainAttributes(r1, r2);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainTypedArray(int r1) {
        return this.mResources.obtainTypedArray(r1);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int r1) {
        return this.mResources.openRawResource(r1);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int r1, TypedValue r2) {
        return this.mResources.openRawResource(r1, r2);
    }

    @Override // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int r1) {
        return this.mResources.openRawResourceFd(r1);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtra(String r1, AttributeSet r2, Bundle r3) {
        this.mResources.parseBundleExtra(r1, r2, r3);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtras(XmlResourceParser r1, Bundle r2) {
        this.mResources.parseBundleExtras(r1, r2);
    }

    @Override // android.content.res.Resources
    public void updateConfiguration(Configuration r1, DisplayMetrics r2) {
        super.updateConfiguration(r1, r2);
        Resources r0 = this.mResources;
        if (r0 == null) goto L6;
        r0.updateConfiguration(r1, r2);
        return;
    }
}
