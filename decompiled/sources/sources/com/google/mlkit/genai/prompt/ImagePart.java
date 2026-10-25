package com.google.mlkit.genai.prompt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\bJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/google/mlkit/genai/prompt/ImagePart;", "Lcom/google/mlkit/genai/prompt/Part;", "Landroid/graphics/Bitmap;", "bitmap", "<init>", "(Landroid/graphics/Bitmap;)V", _UrlKt.FRAGMENT_ENCODE_SET, "blob", "([B)V", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "Companion", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ImagePart extends Part {
    public static final Companion Companion = null;
    private static final Lazy zzd = null;
    private final Bitmap zza;
    private final int zzb;
    private final int zzc;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001¨\u0006\u0011"}, d2 = {"Lcom/google/mlkit/genai/prompt/ImagePart$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context$delegate", "Lkotlin/Lazy;", "bytesToBitmap", "Landroid/graphics/Bitmap;", "blob", _UrlKt.FRAGMENT_ENCODE_SET, "uriToBitmap", "uri", "Landroid/net/Uri;", "java.com.google.android.libraries.mlkit.granules.genai.prompt_mlkit_genai_prompt"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
        }
    }

    static {
        Companion = new Companion(null);
        zzd = LazyKt.lazy(zze.zza);
    }

    public ImagePart(Bitmap r2) {
        super(null);
        this.zza = r2;
        this.zzb = r2.getWidth();
        this.zzc = r2.getHeight();
    }

    public ImagePart(byte[] r3) {
        Bitmap r32 = BitmapFactory.decodeByteArray(r3, 0, r3.length);
        if (r32 == null) goto L6;
        this(r32);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Could not decode byte array to bitmap.");
        throw null;
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L5;
        return true;
    L5:
        if (r3 == null) goto L7;
        Class<?> r0 = r3.getClass();
    L9:
        if (Intrinsics.areEqual(ImagePart.class, r0) == true) goto L13;
        return false;
    L13:
        return this.zza.sameAs(((ImagePart) r3).zza);
    L7:
        r0 = null;
        goto L9
    }

    public final Bitmap getBitmap() {
        return this.zza;
    }

    public int hashCode() {
        Bitmap r2 = this.zza;
        int r0 = ((((r2.getWidth() + 527) * 31) + r2.getHeight()) * 31) + r2.getByteCount();
        Bitmap.Config r22 = r2.getConfig();
        if (r22 == null) goto L5;
        int r23 = r22.hashCode();
    L7:
        return (r0 * 31) + r23;
    L5:
        r23 = 0;
        goto L7
    }

    public String toString() {
        Bitmap r2 = this.zza;
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 18);
        r1.append("ImagePart(bitmap=");
        r1.append(r2);
        r1.append(")");
        return r1.toString();
    }
}
