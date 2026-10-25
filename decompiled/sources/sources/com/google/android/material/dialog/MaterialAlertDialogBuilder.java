package com.google.android.material.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import androidx.appcompat.R$attr;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes5.dex */
public class MaterialAlertDialogBuilder extends AlertDialog.Builder {
    private static final int DEF_STYLE_ATTR = 0;
    private static final int DEF_STYLE_RES = 0;
    private static final int MATERIAL_ALERT_DIALOG_THEME_OVERLAY = 0;
    private Drawable background;
    private final Rect backgroundInsets;

    static {
        DEF_STYLE_ATTR = R$attr.alertDialogStyle;
        DEF_STYLE_RES = R.style.MaterialAlertDialog_MaterialComponents;
        MATERIAL_ALERT_DIALOG_THEME_OVERLAY = R.attr.materialAlertDialogTheme;
    }

    public MaterialAlertDialogBuilder(Context r2) {
        this(r2, 0);
    }

    public MaterialAlertDialogBuilder(Context r7, int r8) {
        super(createMaterialAlertDialogThemedContext(r7), getOverridingThemeResId(r7, r8));
        Context r72 = getContext();
        Resources.Theme r82 = r72.getTheme();
        int r0 = DEF_STYLE_ATTR;
        int r1 = DEF_STYLE_RES;
        this.backgroundInsets = MaterialDialogs.getDialogBackgroundInsets(r72, r0, r1);
        int r2 = MaterialColors.getColor(r72, R.attr.colorSurface, getClass().getCanonicalName());
        TypedArray r3 = r72.obtainStyledAttributes(null, R.styleable.MaterialAlertDialog, r0, r1);
        int r22 = r3.getColor(R.styleable.MaterialAlertDialog_backgroundTint, r2);
        r3.recycle();
        MaterialShapeDrawable r32 = new MaterialShapeDrawable(r72, null, r0, r1);
        r32.initializeElevationOverlay(r72);
        r32.setFillColor(ColorStateList.valueOf(r22));
        if (Build.VERSION.SDK_INT < 28) goto L9;
        TypedValue r73 = new TypedValue();
        r82.resolveAttribute(android.R.attr.dialogCornerRadius, r73, true);
        float r83 = r73.getDimension(getContext().getResources().getDisplayMetrics());
        if (r73.type != 5) goto L9;
        if (r83 < 0.0f) goto L9;
        r32.setCornerSize(r83);
    L9:
        this.background = r32;
    }

    private static Context createMaterialAlertDialogThemedContext(Context r4) {
        int r0 = getMaterialAlertDialogThemeOverlay(r4);
        Context r42 = MaterialThemeOverlay.wrap(r4, null, DEF_STYLE_ATTR, DEF_STYLE_RES);
        if (r0 != 0) goto L6;
        return r42;
    L6:
        return new ContextThemeWrapper(r42, r0);
    }

    private static int getMaterialAlertDialogThemeOverlay(Context r1) {
        TypedValue r12 = MaterialAttributes.resolve(r1, MATERIAL_ALERT_DIALOG_THEME_OVERLAY);
        if (r12 != null) goto L7;
        return 0;
    L7:
        return r12.data;
    }

    private static int getOverridingThemeResId(Context r0, int r1) {
        if (r1 == 0) goto L4;
        return r1;
    L4:
        return getMaterialAlertDialogThemeOverlay(r0);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public AlertDialog create() {
        AlertDialog r0 = super.create();
        Window r1 = r0.getWindow();
        View r2 = r1.getDecorView();
        Drawable r3 = this.background;
        if ((r3 instanceof MaterialShapeDrawable) == false) goto L5;
        ((MaterialShapeDrawable) r3).setElevation(r2.getElevation());
    L5:
        r1.setBackgroundDrawable(MaterialDialogs.insetDrawable(this.background, this.backgroundInsets));
        r2.setOnTouchListener(new InsetDialogOnTouchListener(r0, this.backgroundInsets));
        return r0;
    }

    public Drawable getBackground() {
        return this.background;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setAdapter(ListAdapter r1, DialogInterface.OnClickListener r2) {
        return setAdapter(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setAdapter(ListAdapter r1, DialogInterface.OnClickListener r2) {
        return (MaterialAlertDialogBuilder) super.setAdapter(r1, r2);
    }

    public MaterialAlertDialogBuilder setBackground(Drawable r1) {
        this.background = r1;
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetBottom(int r2) {
        this.backgroundInsets.bottom = r2;
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetEnd(int r4) {
        int r0 = getContext().getResources().getConfiguration().getLayoutDirection();
        Rect r1 = this.backgroundInsets;
        if (r0 != 1) goto L6;
        r1.left = r4;
        return this;
    L6:
        r1.right = r4;
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetStart(int r4) {
        int r0 = getContext().getResources().getConfiguration().getLayoutDirection();
        Rect r1 = this.backgroundInsets;
        if (r0 != 1) goto L6;
        r1.right = r4;
        return this;
    L6:
        r1.left = r4;
        return this;
    }

    public MaterialAlertDialogBuilder setBackgroundInsetTop(int r2) {
        this.backgroundInsets.top = r2;
        return this;
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setCancelable(boolean r1) {
        return setCancelable(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setCancelable(boolean r1) {
        return (MaterialAlertDialogBuilder) super.setCancelable(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setCursor(Cursor r1, DialogInterface.OnClickListener r2, String r3) {
        return setCursor(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setCursor(Cursor r1, DialogInterface.OnClickListener r2, String r3) {
        return (MaterialAlertDialogBuilder) super.setCursor(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setCustomTitle(View r1) {
        return setCustomTitle(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setCustomTitle(View r1) {
        return (MaterialAlertDialogBuilder) super.setCustomTitle(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setIcon(int r1) {
        return setIcon(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setIcon(Drawable r1) {
        return setIcon(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setIcon(int r1) {
        return (MaterialAlertDialogBuilder) super.setIcon(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setIcon(Drawable r1) {
        return (MaterialAlertDialogBuilder) super.setIcon(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setIconAttribute(int r1) {
        return setIconAttribute(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setIconAttribute(int r1) {
        return (MaterialAlertDialogBuilder) super.setIconAttribute(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setItems(int r1, DialogInterface.OnClickListener r2) {
        return setItems(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setItems(CharSequence[] r1, DialogInterface.OnClickListener r2) {
        return setItems(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setItems(int r1, DialogInterface.OnClickListener r2) {
        return (MaterialAlertDialogBuilder) super.setItems(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setItems(CharSequence[] r1, DialogInterface.OnClickListener r2) {
        return (MaterialAlertDialogBuilder) super.setItems(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setMessage(int r1) {
        return setMessage(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setMessage(CharSequence r1) {
        return setMessage(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setMessage(int r1) {
        return (MaterialAlertDialogBuilder) super.setMessage(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setMessage(CharSequence r1) {
        return (MaterialAlertDialogBuilder) super.setMessage(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setMultiChoiceItems(int r1, boolean[] r2, DialogInterface.OnMultiChoiceClickListener r3) {
        return setMultiChoiceItems(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setMultiChoiceItems(Cursor r1, String r2, String r3, DialogInterface.OnMultiChoiceClickListener r4) {
        return setMultiChoiceItems(r1, r2, r3, r4);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setMultiChoiceItems(CharSequence[] r1, boolean[] r2, DialogInterface.OnMultiChoiceClickListener r3) {
        return setMultiChoiceItems(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setMultiChoiceItems(int r1, boolean[] r2, DialogInterface.OnMultiChoiceClickListener r3) {
        return (MaterialAlertDialogBuilder) super.setMultiChoiceItems(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setMultiChoiceItems(Cursor r1, String r2, String r3, DialogInterface.OnMultiChoiceClickListener r4) {
        return (MaterialAlertDialogBuilder) super.setMultiChoiceItems(r1, r2, r3, r4);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setMultiChoiceItems(CharSequence[] r1, boolean[] r2, DialogInterface.OnMultiChoiceClickListener r3) {
        return (MaterialAlertDialogBuilder) super.setMultiChoiceItems(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setNegativeButton(int r1, DialogInterface.OnClickListener r2) {
        return setNegativeButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setNegativeButton(CharSequence r1, DialogInterface.OnClickListener r2) {
        return setNegativeButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setNegativeButton(int r1, DialogInterface.OnClickListener r2) {
        return (MaterialAlertDialogBuilder) super.setNegativeButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setNegativeButton(CharSequence r1, DialogInterface.OnClickListener r2) {
        return (MaterialAlertDialogBuilder) super.setNegativeButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setNegativeButtonIcon(Drawable r1) {
        return setNegativeButtonIcon(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setNegativeButtonIcon(Drawable r1) {
        return (MaterialAlertDialogBuilder) super.setNegativeButtonIcon(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setNeutralButton(int r1, DialogInterface.OnClickListener r2) {
        return setNeutralButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setNeutralButton(CharSequence r1, DialogInterface.OnClickListener r2) {
        return setNeutralButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setNeutralButton(int r1, DialogInterface.OnClickListener r2) {
        return (MaterialAlertDialogBuilder) super.setNeutralButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setNeutralButton(CharSequence r1, DialogInterface.OnClickListener r2) {
        return (MaterialAlertDialogBuilder) super.setNeutralButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setNeutralButtonIcon(Drawable r1) {
        return setNeutralButtonIcon(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setNeutralButtonIcon(Drawable r1) {
        return (MaterialAlertDialogBuilder) super.setNeutralButtonIcon(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setOnCancelListener(DialogInterface.OnCancelListener r1) {
        return setOnCancelListener(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener r1) {
        return (MaterialAlertDialogBuilder) super.setOnCancelListener(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setOnDismissListener(DialogInterface.OnDismissListener r1) {
        return setOnDismissListener(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setOnDismissListener(DialogInterface.OnDismissListener r1) {
        return (MaterialAlertDialogBuilder) super.setOnDismissListener(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener r1) {
        return setOnItemSelectedListener(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setOnItemSelectedListener(AdapterView.OnItemSelectedListener r1) {
        return (MaterialAlertDialogBuilder) super.setOnItemSelectedListener(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setOnKeyListener(DialogInterface.OnKeyListener r1) {
        return setOnKeyListener(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setOnKeyListener(DialogInterface.OnKeyListener r1) {
        return (MaterialAlertDialogBuilder) super.setOnKeyListener(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setPositiveButton(int r1, DialogInterface.OnClickListener r2) {
        return setPositiveButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setPositiveButton(CharSequence r1, DialogInterface.OnClickListener r2) {
        return setPositiveButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setPositiveButton(int r1, DialogInterface.OnClickListener r2) {
        return (MaterialAlertDialogBuilder) super.setPositiveButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setPositiveButton(CharSequence r1, DialogInterface.OnClickListener r2) {
        return (MaterialAlertDialogBuilder) super.setPositiveButton(r1, r2);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setPositiveButtonIcon(Drawable r1) {
        return setPositiveButtonIcon(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setPositiveButtonIcon(Drawable r1) {
        return (MaterialAlertDialogBuilder) super.setPositiveButtonIcon(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setSingleChoiceItems(int r1, int r2, DialogInterface.OnClickListener r3) {
        return setSingleChoiceItems(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setSingleChoiceItems(Cursor r1, int r2, String r3, DialogInterface.OnClickListener r4) {
        return setSingleChoiceItems(r1, r2, r3, r4);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setSingleChoiceItems(ListAdapter r1, int r2, DialogInterface.OnClickListener r3) {
        return setSingleChoiceItems(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setSingleChoiceItems(CharSequence[] r1, int r2, DialogInterface.OnClickListener r3) {
        return setSingleChoiceItems(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setSingleChoiceItems(int r1, int r2, DialogInterface.OnClickListener r3) {
        return (MaterialAlertDialogBuilder) super.setSingleChoiceItems(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setSingleChoiceItems(Cursor r1, int r2, String r3, DialogInterface.OnClickListener r4) {
        return (MaterialAlertDialogBuilder) super.setSingleChoiceItems(r1, r2, r3, r4);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setSingleChoiceItems(ListAdapter r1, int r2, DialogInterface.OnClickListener r3) {
        return (MaterialAlertDialogBuilder) super.setSingleChoiceItems(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setSingleChoiceItems(CharSequence[] r1, int r2, DialogInterface.OnClickListener r3) {
        return (MaterialAlertDialogBuilder) super.setSingleChoiceItems(r1, r2, r3);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setTitle(int r1) {
        return setTitle(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setTitle(CharSequence r1) {
        return setTitle(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setTitle(int r1) {
        return (MaterialAlertDialogBuilder) super.setTitle(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setTitle(CharSequence r1) {
        return (MaterialAlertDialogBuilder) super.setTitle(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setView(int r1) {
        return setView(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public /* bridge */ /* synthetic */ AlertDialog.Builder setView(View r1) {
        return setView(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setView(int r1) {
        return (MaterialAlertDialogBuilder) super.setView(r1);
    }

    @Override // androidx.appcompat.app.AlertDialog.Builder
    public MaterialAlertDialogBuilder setView(View r1) {
        return (MaterialAlertDialogBuilder) super.setView(r1);
    }
}
