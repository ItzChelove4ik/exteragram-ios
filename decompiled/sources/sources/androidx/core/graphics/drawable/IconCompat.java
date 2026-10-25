package androidx.core.graphics.drawable;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.core.util.ObjectsCompat;
import androidx.versionedparcelable.CustomVersionedParcelable;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {
    static final PorterDuff.Mode DEFAULT_TINT_MODE = null;
    public byte[] mData;
    public int mInt1;
    public int mInt2;
    Object mObj1;
    public Parcelable mParcelable;
    public String mString1;
    public ColorStateList mTintList;
    PorterDuff.Mode mTintMode;
    public String mTintModeStr;
    public int mType;

    public static class Api23Impl {
        public static int getResId(Object r6) {
            if (Build.VERSION.SDK_INT < 28) goto L21;
            return Api28Impl.getResId(r6);
        L21:
            return ((Integer) r6.getClass().getMethod("getResId", null).invoke(r6, null)).intValue();
        L13:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon resource", e);
            return 0;
        L9:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon resource", e);
            return 0;
        L11:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon resource", e);
            return 0;
        }

        public static String getResPackage(Object r5) {
            if (Build.VERSION.SDK_INT < 28) goto L21;
            return Api28Impl.getResPackage(r5);
        L21:
            return (String) r5.getClass().getMethod("getResPackage", null).invoke(r5, null);
        L13:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon package", e);
            return null;
        L9:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon package", e);
            return null;
        L11:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon package", e);
            return null;
        }

        public static int getType(Object r6) {
            if (Build.VERSION.SDK_INT < 28) goto L21;
            return Api28Impl.getType(r6);
        L21:
            return ((Integer) r6.getClass().getMethod("getType", null).invoke(r6, null)).intValue();
        L13:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon type " + r6, e);
            return -1;
        L9:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon type " + r6, e);
            return -1;
        L11:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon type " + r6, e);
            return -1;
        }

        public static Uri getUri(Object r5) {
            if (Build.VERSION.SDK_INT < 28) goto L21;
            return Api28Impl.getUri(r5);
        L21:
            return (Uri) r5.getClass().getMethod("getUri", null).invoke(r5, null);
        L13:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon uri", e);
            return null;
        L9:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon uri", e);
            return null;
        L11:
            e = move-exception;
            Log.e("IconCompat", "Unable to get icon uri", e);
            return null;
        }

        public static Icon toIcon(IconCompat r5, Context r6) {
            switch(r5.mType) {
                case -1: goto L35;
                case 0: goto L4;
                case 1: goto L26;
                case 2: goto L25;
                case 3: goto L24;
                case 4: goto L23;
                case 5: goto L19;
                case 6: goto L6;
                default: goto L4;
            };
        L4:
            f$$ExternalSyntheticBUOutline1.m("Unknown type");
            return null;
        L6:
            int r0 = Build.VERSION.SDK_INT;
            if (r0 < 30) goto L9;
            Icon r62 = Api30Impl.createWithAdaptiveBitmapContentUri(r5.getUri());
        L27:
            ColorStateList r02 = r5.mTintList;
            if (r02 == null) goto L30;
            r62.setTintList(r02);
        L30:
            PorterDuff.Mode r52 = r5.mTintMode;
            if (r52 == IconCompat.DEFAULT_TINT_MODE) goto L33;
            r62.setTintMode(r52);
        L33:
            return r62;
        L9:
            if (r6 == null) goto L17;
            InputStream r63 = r5.getUriInputStream(r6);
            if (r63 == null) goto L15;
            if (r0 < 26) goto L14;
            r62 = Api26Impl.createWithAdaptiveBitmap(BitmapFactory.decodeStream(r63));
            goto L27
        L14:
            r62 = Icon.createWithBitmap(IconCompat.createLegacyIconFromAdaptiveIcon(BitmapFactory.decodeStream(r63), false));
            goto L27
        L15:
            CstInsn$$ExternalSyntheticBUOutline0.m("Cannot load adaptive icon from uri: ", r5.getUri());
            return null;
        L17:
            Buffer$$ExternalSyntheticBUOutline4.m("Context is required to resolve the file uri of the icon: ", r5.getUri());
            return null;
        L19:
            int r64 = Build.VERSION.SDK_INT;
            Object r03 = r5.mObj1;
            if (r64 < 26) goto L22;
            r62 = Api26Impl.createWithAdaptiveBitmap((Bitmap) r03);
            goto L27
        L22:
            r62 = Icon.createWithBitmap(IconCompat.createLegacyIconFromAdaptiveIcon((Bitmap) r03, false));
            goto L27
        L23:
            r62 = Icon.createWithContentUri((String) r5.mObj1);
            goto L27
        L24:
            r62 = Icon.createWithData((byte[]) r5.mObj1, r5.mInt1, r5.mInt2);
            goto L27
        L25:
            r62 = Icon.createWithResource(r5.getResPackage(), r5.mInt1);
            goto L27
        L26:
            r62 = Icon.createWithBitmap((Bitmap) r5.mObj1);
            goto L27
        L35:
            return (Icon) r5.mObj1;
        }
    }

    public static class Api26Impl {
        public static Icon createWithAdaptiveBitmap(Bitmap r0) {
            return Icon.createWithAdaptiveBitmap(r0);
        }
    }

    /* loaded from: classes3.dex */
    public static class Api28Impl {
        public static int getResId(Object r0) {
            return ((Icon) r0).getResId();
        }

        public static String getResPackage(Object r0) {
            return ((Icon) r0).getResPackage();
        }

        public static int getType(Object r0) {
            return ((Icon) r0).getType();
        }

        public static Uri getUri(Object r0) {
            return ((Icon) r0).getUri();
        }
    }

    /* loaded from: classes3.dex */
    public static class Api30Impl {
        public static Icon createWithAdaptiveBitmapContentUri(Uri r0) {
            return Icon.createWithAdaptiveBitmapContentUri(r0);
        }
    }

    static {
        DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    }

    public IconCompat() {
        this.mType = -1;
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
    }

    public IconCompat(int r3) {
        this.mData = null;
        this.mParcelable = null;
        this.mInt1 = 0;
        this.mInt2 = 0;
        this.mTintList = null;
        this.mTintMode = DEFAULT_TINT_MODE;
        this.mTintModeStr = null;
        this.mType = r3;
    }

    public static IconCompat createFromBundle(Bundle r4) {
        int r0 = r4.getInt("type");
        IconCompat r1 = new IconCompat(r0);
        r1.mInt1 = r4.getInt("int1");
        r1.mInt2 = r4.getInt("int2");
        r1.mString1 = r4.getString("string1");
        if (r4.containsKey("tint_list") == false) goto L6;
        r1.mTintList = (ColorStateList) r4.getParcelable("tint_list");
    L6:
        if (r4.containsKey("tint_mode") == false) goto L9;
        r1.mTintMode = PorterDuff.Mode.valueOf(r4.getString("tint_mode"));
    L9:
        switch(r0) {
            case -1: goto L16;
            case 0: goto L10;
            case 1: goto L16;
            case 2: goto L14;
            case 3: goto L12;
            case 4: goto L14;
            case 5: goto L16;
            case 6: goto L14;
            default: goto L10;
        };
    L10:
        Log.w("IconCompat", "Unknown type " + r0);
        return null;
    L12:
        r1.mObj1 = r4.getByteArray("obj");
        return r1;
    L14:
        r1.mObj1 = r4.getString("obj");
        return r1;
    L16:
        r1.mObj1 = r4.getParcelable("obj");
        return r1;
    }

    public static Bitmap createLegacyIconFromAdaptiveIcon(Bitmap r9, boolean r10) {
        int r0 = (int) (Math.min(r9.getWidth(), r9.getHeight()) * 0.6666667f);
        Bitmap r1 = Bitmap.createBitmap(r0, r0, Bitmap.Config.ARGB_8888);
        Canvas r2 = new Canvas(r1);
        Paint r3 = new Paint(3);
        float r4 = r0;
        float r5 = 0.5f * r4;
        float r6 = 0.9166667f * r5;
        if (r10 == false) goto L5;
        float r102 = 0.010416667f * r4;
        r3.setColor(0);
        r3.setShadowLayer(r102, 0.0f, r4 * 0.020833334f, 1023410176);
        r2.drawCircle(r5, r5, r6, r3);
        r3.setShadowLayer(r102, 0.0f, 0.0f, 503316480);
        r2.drawCircle(r5, r5, r6, r3);
        r3.clearShadowLayer();
    L5:
        r3.setColor(-16777216);
        Shader.TileMode r42 = Shader.TileMode.CLAMP;
        BitmapShader r103 = new BitmapShader(r9, r42, r42);
        Matrix r43 = new Matrix();
        r43.setTranslate((-(r9.getWidth() - r0)) / 2.0f, (-(r9.getHeight() - r0)) / 2.0f);
        r103.setLocalMatrix(r43);
        r3.setShader(r103);
        r2.drawCircle(r5, r5, r6, r3);
        r2.setBitmap(null);
        return r1;
    }

    public static IconCompat createWithAdaptiveBitmap(Bitmap r2) {
        ObjectsCompat.requireNonNull(r2);
        IconCompat r0 = new IconCompat(5);
        r0.mObj1 = r2;
        return r0;
    }

    public static IconCompat createWithBitmap(Bitmap r2) {
        ObjectsCompat.requireNonNull(r2);
        IconCompat r0 = new IconCompat(1);
        r0.mObj1 = r2;
        return r0;
    }

    public static IconCompat createWithContentUri(Uri r0) {
        ObjectsCompat.requireNonNull(r0);
        return createWithContentUri(r0.toString());
    }

    public static IconCompat createWithContentUri(String r2) {
        ObjectsCompat.requireNonNull(r2);
        IconCompat r0 = new IconCompat(4);
        r0.mObj1 = r2;
        return r0;
    }

    public static IconCompat createWithResource(Context r1, int r2) {
        ObjectsCompat.requireNonNull(r1);
        return createWithResource(r1.getResources(), r1.getPackageName(), r2);
    }

    public static IconCompat createWithResource(Resources r3, String r4, int r5) {
        ObjectsCompat.requireNonNull(r4);
        if (r5 == 0) goto L13;
        IconCompat r1 = new IconCompat(2);
        r1.mInt1 = r5;
        if (r3 != null) goto L15;
        r1.mObj1 = r4;
    L11:
        r1.mString1 = r4;
        return r1;
    L15:
        r1.mObj1 = r3.getResourceName(r5);     // Catch: Resources.NotFoundException -> L8
    L8:
        f$$ExternalSyntheticBUOutline1.m("Icon resource cannot be found");
        return null;
    L13:
        f$$ExternalSyntheticBUOutline1.m("Drawable resource ID must not be 0");
        return null;
    }

    public static Resources getResources(Context r2, String r3) {
        if ("android".equals(r3) == true) goto L5;
        PackageManager r22 = r2.getPackageManager();
        ApplicationInfo r0 = r22.getApplicationInfo(r3, 8192);     // Catch: PackageManager.NameNotFoundException -> L11
        if (r0 == null) goto L13;
        return r22.getResourcesForApplication(r0);
    L13:
        return null;
    L11:
        e = move-exception;
        Log.e("IconCompat", String.format("Unable to find pkg=%s for icon", new Object[]{r3}), e);
        return null;
    L5:
        return Resources.getSystem();
    }

    private static String typeToString(int r0) {
        switch(r0) {
            case 1: goto L15;
            case 2: goto L13;
            case 3: goto L11;
            case 4: goto L9;
            case 5: goto L7;
            case 6: goto L5;
            default: goto L3;
        };
    L3:
        return "UNKNOWN";
    L5:
        return "URI_MASKABLE";
    L7:
        return "BITMAP_MASKABLE";
    L9:
        return "URI";
    L11:
        return "DATA";
    L13:
        return "RESOURCE";
    L15:
        return "BITMAP";
    }

    public void addToShortcutIntent(Intent r5, Drawable r6, Context r7) {
        checkResource(r7);
        int r0 = this.mType;
        if (r0 != 1) goto L5;
        Bitmap r4 = (Bitmap) this.mObj1;
        if (r6 == null) goto L31;
        r4 = r4.copy(r4.getConfig(), true);
    L31:
        if (r6 == null) goto L33;
        int r72 = r4.getWidth();
        int r02 = r4.getHeight();
        r6.setBounds(r72 / 2, r02 / 2, r72, r02);
        r6.draw(new Canvas(r4));
    L33:
        r5.putExtra("android.intent.extra.shortcut.ICON", r4);
        return;
    L5:
        if (r0 != 2) goto L7;
        Context r73 = r7.createPackageContext(getResPackage(), 0);     // Catch: PackageManager.NameNotFoundException -> L15
        if (r6 != null) goto L17;
        r5.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(r73, this.mInt1));     // Catch: PackageManager.NameNotFoundException -> L15
        return;
    L17:
        Drawable r03 = ContextCompat.getDrawable(r73, this.mInt1);     // Catch: PackageManager.NameNotFoundException -> L15
        if (r03.getIntrinsicWidth() > 0) goto L20;
    L23:
        int r74 = ((ActivityManager) r73.getSystemService("activity")).getLauncherLargeIconSize();     // Catch: PackageManager.NameNotFoundException -> L15
        Bitmap r75 = Bitmap.createBitmap(r74, r74, Bitmap.Config.ARGB_8888);     // Catch: PackageManager.NameNotFoundException -> L15
    L24:
        r03.setBounds(0, 0, r75.getWidth(), r75.getHeight());     // Catch: PackageManager.NameNotFoundException -> L15
        r03.draw(new Canvas(r75));     // Catch: PackageManager.NameNotFoundException -> L15
        r4 = r75;
        goto L31
    L20:
        if (r03.getIntrinsicHeight() <= 0) goto L23;
        r75 = Bitmap.createBitmap(r03.getIntrinsicWidth(), r03.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);     // Catch: PackageManager.NameNotFoundException -> L15
    L15:
        e = move-exception;
        throw new IllegalArgumentException("Can't find package " + this.mObj1, e);
    L7:
        if (r0 != 5) goto L9;
        r4 = createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, true);
        goto L31
    L9:
        f$$ExternalSyntheticBUOutline1.m("Icon type not supported for intent shortcuts");
    }

    public void checkResource(Context r9) {
        if (this.mType != 2) goto L17;
        Object r0 = this.mObj1;
        if (r0 == null) goto L18;
        String r02 = (String) r0;
        if (r02.contains(":") == false) goto L20;
        String r3 = r02.split(":", -1)[1];
        String r6 = r3.split("/", -1)[0];
        String r32 = r3.split("/", -1)[1];
        String r1 = r02.split(":", -1)[0];
        if ("0_resource_name_obfuscated".equals(r32) == false) goto L13;
        Log.i("IconCompat", "Found obfuscated resource, not trying to update resource id for it");
        return;
    L13:
        String r2 = getResPackage();
        int r92 = getResources(r9, r2).getIdentifier(r32, r6, r1);
        if (this.mInt1 == r92) goto L19;
        Log.i("IconCompat", "Id has changed for " + r2 + " " + r02);
        this.mInt1 = r92;
        return;
    L19:
        return;
    L20:
        return;
    L18:
        return;
    }

    public Bitmap getBitmap() {
        int r0 = this.mType;
        if (r0 != (-1)) goto L11;
        Object r3 = this.mObj1;
        if ((r3 instanceof Bitmap) == true) goto L7;
        return null;
    L7:
        return (Bitmap) r3;
    L11:
        if (r0 != 1) goto L15;
        return (Bitmap) this.mObj1;
    L15:
        if (r0 == 5) goto L17;
        DexMaker$$ExternalSyntheticBUOutline0.m("called getBitmap() on ", this);
        return null;
    L17:
        return createLegacyIconFromAdaptiveIcon((Bitmap) this.mObj1, true);
    }

    public int getResId() {
        int r0 = this.mType;
        if (r0 != (-1)) goto L7;
        return Api23Impl.getResId(this.mObj1);
    L7:
        if (r0 == 2) goto L9;
        DexMaker$$ExternalSyntheticBUOutline0.m("called getResId() on ", this);
        return 0;
    L9:
        return this.mInt1;
    }

    public String getResPackage() {
        int r0 = this.mType;
        if (r0 != (-1)) goto L7;
        return Api23Impl.getResPackage(this.mObj1);
    L7:
        if (r0 != 2) goto L17;
        String r02 = this.mString1;
        if (r02 == null) goto L16;
        if (TextUtils.isEmpty(r02) == true) goto L16;
        return this.mString1;
    L16:
        return ((String) this.mObj1).split(":", -1)[0];
    L17:
        DexMaker$$ExternalSyntheticBUOutline0.m("called getResPackage() on ", this);
        return null;
    }

    public int getType() {
        int r0 = this.mType;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        return Api23Impl.getType(this.mObj1);
    }

    public Uri getUri() {
        int r0 = this.mType;
        if (r0 != (-1)) goto L7;
        return Api23Impl.getUri(this.mObj1);
    L7:
        if (r0 == 4) goto L14;
        if (r0 == 6) goto L14;
        DexMaker$$ExternalSyntheticBUOutline0.m("called getUri() on ", this);
        return null;
    L14:
        return Uri.parse((String) this.mObj1);
    }

    public InputStream getUriInputStream(Context r5) {
        Uri r0 = getUri();
        String r1 = r0.getScheme();
        if ("content".equals(r1) == false) goto L5;
    L17:
        return r5.getContentResolver().openInputStream(r0);
    L13:
        e = move-exception;
        Log.w("IconCompat", "Unable to load image from URI: " + r0, e);
        return null;
    L5:
        if ("file".equals(r1) == true) goto L17;
        return new FileInputStream(new File((String) this.mObj1));
    L9:
        e = move-exception;
        Log.w("IconCompat", "Unable to load image from path: " + r0, e);
        return null;
    }

    public void onPostParceling() {
        this.mTintMode = PorterDuff.Mode.valueOf(this.mTintModeStr);
        switch(this.mType) {
            case -1: goto L19;
            case 0: goto L27;
            case 1: goto L13;
            case 2: goto L7;
            case 3: goto L5;
            case 4: goto L7;
            case 5: goto L13;
            case 6: goto L7;
            default: goto L27;
        };
    L5:
        this.mObj1 = this.mData;
        return;
    L7:
        String r0 = new String(this.mData, Charset.forName("UTF-16"));
        this.mObj1 = r0;
        if (this.mType == 2) goto L10;
        return;
    L10:
        if (this.mString1 != null) goto L26;
        this.mString1 = r0.split(":", -1)[0];
        return;
    L26:
        return;
    L13:
        Parcelable r02 = this.mParcelable;
        if (r02 == null) goto L17;
        this.mObj1 = r02;
        return;
    L17:
        byte[] r03 = this.mData;
        this.mObj1 = r03;
        this.mType = 3;
        this.mInt1 = 0;
        this.mInt2 = r03.length;
        return;
    L19:
        Parcelable r04 = this.mParcelable;
        if (r04 == null) goto L23;
        this.mObj1 = r04;
        return;
    L23:
        f$$ExternalSyntheticBUOutline1.m("Invalid icon");
        return;
    }

    public void onPreParceling(boolean r4) {
        this.mTintModeStr = this.mTintMode.name();
        switch(this.mType) {
            case -1: goto L17;
            case 0: goto L4;
            case 1: goto L11;
            case 2: goto L9;
            case 3: goto L7;
            case 4: goto L5;
            case 5: goto L11;
            case 6: goto L5;
            default: goto L4;
        };
    L4:
        return;
    L5:
        this.mData = this.mObj1.toString().getBytes(Charset.forName("UTF-16"));
        return;
    L7:
        this.mData = (byte[]) this.mObj1;
        return;
    L9:
        this.mData = ((String) this.mObj1).getBytes(Charset.forName("UTF-16"));
        return;
    L11:
        Object r0 = this.mObj1;
        if (r4 == false) goto L15;
        ByteArrayOutputStream r42 = new ByteArrayOutputStream();
        ((Bitmap) r0).compress(Bitmap.CompressFormat.PNG, 90, r42);
        this.mData = r42.toByteArray();
        return;
    L15:
        this.mParcelable = (Parcelable) r0;
        return;
    L17:
        if (r4 == true) goto L20;
        this.mParcelable = (Parcelable) this.mObj1;
        return;
    L20:
        f$$ExternalSyntheticBUOutline1.m("Can't serialize Icon created with IconCompat#createFromIcon");
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        switch(this.mType) {
            case -1: goto L9;
            case 0: goto L4;
            case 1: goto L8;
            case 2: goto L7;
            case 3: goto L6;
            case 4: goto L7;
            case 5: goto L8;
            case 6: goto L7;
            default: goto L4;
        };
    L4:
        f$$ExternalSyntheticBUOutline1.m("Invalid icon");
        return null;
    L6:
        r0.putByteArray("obj", (byte[]) this.mObj1);
    L10:
        r0.putInt("type", this.mType);
        r0.putInt("int1", this.mInt1);
        r0.putInt("int2", this.mInt2);
        r0.putString("string1", this.mString1);
        ColorStateList r1 = this.mTintList;
        if (r1 == null) goto L13;
        r0.putParcelable("tint_list", r1);
    L13:
        PorterDuff.Mode r3 = this.mTintMode;
        if (r3 == DEFAULT_TINT_MODE) goto L16;
        r0.putString("tint_mode", r3.name());
    L16:
        return r0;
    L7:
        r0.putString("obj", (String) this.mObj1);
        goto L10
    L8:
        r0.putParcelable("obj", (Bitmap) this.mObj1);
        goto L10
    L9:
        r0.putParcelable("obj", (Parcelable) this.mObj1);
        goto L10
    }

    @Deprecated
    public Icon toIcon() {
        return toIcon(null);
    }

    public Icon toIcon(Context r1) {
        return Api23Impl.toIcon(this, r1);
    }

    public String toString() {
        if (this.mType == (-1)) goto L5;
        StringBuilder r0 = new StringBuilder("Icon(typ=");
        r0.append(typeToString(this.mType));
        switch(this.mType) {
            case 1: goto L14;
            case 2: goto L13;
            case 3: goto L10;
            case 4: goto L9;
            case 5: goto L14;
            case 6: goto L9;
            default: goto L16;
        };
    L9:
        r0.append(" uri=");
        r0.append(this.mObj1);
        goto L16
    L10:
        r0.append(" len=");
        r0.append(this.mInt1);
        if (this.mInt2 == 0) goto L16;
        r0.append(" off=");
        r0.append(this.mInt2);
        goto L16
    L13:
        r0.append(" pkg=");
        r0.append(this.mString1);
        r0.append(" id=");
        r0.append(String.format("0x%08x", new Object[]{Integer.valueOf(getResId())}));
        goto L16
    L14:
        r0.append(" size=");
        r0.append(((Bitmap) this.mObj1).getWidth());
        r0.append("x");
        r0.append(((Bitmap) this.mObj1).getHeight());
    L16:
        if (this.mTintList == null) goto L19;
        r0.append(" tint=");
        r0.append(this.mTintList);
    L19:
        if (this.mTintMode == DEFAULT_TINT_MODE) goto L21;
        r0.append(" mode=");
        r0.append(this.mTintMode);
    L21:
        r0.append(")");
        return r0.toString();
    L5:
        return String.valueOf(this.mObj1);
    }
}
