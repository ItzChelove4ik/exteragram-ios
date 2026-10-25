package androidx.activity.result.contract;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.ext.SdkExtensions;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContract;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0017\u0018\u0000 \u00142\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001:\u0004\u0014\u0015\u0016\u0017B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, d2 = {"androidx/activity/result/contract/ActivityResultContracts$PickVisualMedia", "Landroidx/activity/result/contract/ActivityResultContract;", "Landroidx/activity/result/PickVisualMediaRequest;", "Landroid/net/Uri;", "<init>", "()V", "Landroid/content/Context;", "context", PluginsConstants.Settings.TYPE_INPUT, "Landroid/content/Intent;", "createIntent", "(Landroid/content/Context;Landroidx/activity/result/PickVisualMediaRequest;)Landroid/content/Intent;", "Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", "getSynchronousResult", "(Landroid/content/Context;Landroidx/activity/result/PickVisualMediaRequest;)Landroidx/activity/result/contract/ActivityResultContract$SynchronousResult;", _UrlKt.FRAGMENT_ENCODE_SET, "resultCode", "intent", "parseResult", "(ILandroid/content/Intent;)Landroid/net/Uri;", "Companion", "ImageAndVideo", "ImageOnly", "VisualMediaType", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nActivityResultContracts.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ActivityResultContracts.kt\nandroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,959:1\n1#2:960\n*E\n"})
/* loaded from: classes3.dex */
public class ActivityResultContracts$PickVisualMedia extends ActivityResultContract<PickVisualMediaRequest, Uri> {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u000b\u001a\u00020\u0006H\u0001¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\f\u0010\bJ\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0012\u0010\bJ\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0014\u0010\u0010J\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u0016H\u0000¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u00188\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00188\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u001e\u0010\u001d¨\u0006\u001f"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "isPhotoPickerAvailable", "(Landroid/content/Context;)Z", "isSystemPickerAvailable$activity_release", "()Z", "isSystemPickerAvailable", "isSystemFallbackPickerAvailable$activity_release", "isSystemFallbackPickerAvailable", "Landroid/content/pm/ResolveInfo;", "getSystemFallbackPicker$activity_release", "(Landroid/content/Context;)Landroid/content/pm/ResolveInfo;", "getSystemFallbackPicker", "isGmsPickerAvailable$activity_release", "isGmsPickerAvailable", "getGmsPicker$activity_release", "getGmsPicker", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", PluginsConstants.Settings.TYPE_INPUT, _UrlKt.FRAGMENT_ENCODE_SET, "getVisualMimeType$activity_release", "(Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;)Ljava/lang/String;", "getVisualMimeType", "GMS_ACTION_PICK_IMAGES", "Ljava/lang/String;", "GMS_EXTRA_PICK_IMAGES_MAX", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final ResolveInfo getGmsPicker$activity_release(Context r2) {
            return r2.getPackageManager().resolveActivity(new Intent("com.google.android.gms.provider.action.PICK_IMAGES"), 1114112);
        }

        @JvmStatic
        public final ResolveInfo getSystemFallbackPicker$activity_release(Context r2) {
            return r2.getPackageManager().resolveActivity(new Intent("androidx.activity.result.contract.action.PICK_IMAGES"), 1114112);
        }

        public final String getVisualMimeType$activity_release(VisualMediaType r1) {
            if ((r1 instanceof ImageOnly) == false) goto L7;
            return "image/*";
        L7:
            if ((r1 instanceof ImageAndVideo) == false) goto L10;
            return null;
        L10:
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        @JvmStatic
        public final boolean isGmsPickerAvailable$activity_release(Context r1) {
            if (getGmsPicker$activity_release(r1) == null) goto L6;
            return true;
        L6:
            return false;
        }

        @JvmStatic
        @SuppressLint({"ClassVerificationFailure", "NewApi"})
        public final boolean isPhotoPickerAvailable(Context r2) {
            if (isSystemPickerAvailable$activity_release() == false) goto L5;
            return true;
        L5:
            if (isSystemFallbackPickerAvailable$activity_release(r2) == false) goto L7;
            return true;
        L7:
            if (isGmsPickerAvailable$activity_release(r2) == true) goto L14;
            return false;
        L14:
            return true;
        }

        @JvmStatic
        public final boolean isSystemFallbackPickerAvailable$activity_release(Context r1) {
            if (getSystemFallbackPicker$activity_release(r1) == null) goto L6;
            return true;
        L6:
            return false;
        }

        @JvmStatic
        @SuppressLint({"ClassVerificationFailure", "NewApi"})
        public final boolean isSystemPickerAvailable$activity_release() {
            int r3 = Build.VERSION.SDK_INT;
            if (r3 < 33) goto L6;
            return true;
        L6:
            if (r3 >= 30) goto L8;
        L10:
            return false;
        L8:
            if (SdkExtensions.getExtensionVersion(30) < 2) goto L10;
            return true;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$ImageAndVideo;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "()V", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ImageAndVideo implements VisualMediaType {
        public static final ImageAndVideo INSTANCE = null;

        static {
            INSTANCE = new ImageAndVideo();
        }

        private ImageAndVideo() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$ImageOnly;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", "()V", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ImageOnly implements VisualMediaType {
        public static final ImageOnly INSTANCE = null;

        static {
            INSTANCE = new ImageOnly();
        }

        private ImageOnly() {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001\u0082\u0001\u0002\u0002\u0003ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$VisualMediaType;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$ImageAndVideo;", "Landroidx/activity/result/contract/ActivityResultContracts$PickVisualMedia$ImageOnly;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface VisualMediaType {
    }

    static {
        Companion = new Companion(null);
    }

    public ActivityResultContracts$PickVisualMedia() {
    }

    /* renamed from: createIntent, reason: avoid collision after fix types in other method */
    public Intent createIntent2(Context r4, PickVisualMediaRequest r5) {
        Companion r3 = Companion;
        if (r3.isSystemPickerAvailable$activity_release() == false) goto L7;
        Intent r42 = new Intent("android.provider.action.PICK_IMAGES");
        r42.setType(r3.getVisualMimeType$activity_release(r5.getMediaType()));
        return r42;
    L7:
        if (r3.isSystemFallbackPickerAvailable$activity_release(r4) == false) goto L15;
        ResolveInfo r43 = r3.getSystemFallbackPicker$activity_release(r4);
        if (r43 == null) goto L12;
        ActivityInfo r44 = r43.activityInfo;
        Intent r0 = new Intent("androidx.activity.result.contract.action.PICK_IMAGES");
        r0.setClassName(r44.applicationInfo.packageName, r44.name);
        r0.setType(r3.getVisualMimeType$activity_release(r5.getMediaType()));
        return r0;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L15:
        if (r3.isGmsPickerAvailable$activity_release(r4) == false) goto L22;
        ResolveInfo r45 = r3.getGmsPicker$activity_release(r4);
        if (r45 == null) goto L20;
        ActivityInfo r46 = r45.activityInfo;
        Intent r02 = new Intent("com.google.android.gms.provider.action.PICK_IMAGES");
        r02.setClassName(r46.applicationInfo.packageName, r46.name);
        r02.setType(r3.getVisualMimeType$activity_release(r5.getMediaType()));
        return r02;
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L22:
        Intent r47 = new Intent("android.intent.action.OPEN_DOCUMENT");
        r47.setType(r3.getVisualMimeType$activity_release(r5.getMediaType()));
        if (r47.getType() != null) goto L25;
        r47.setType("*/*");
        r47.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "video/*"});
    L25:
        return r47;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public /* bridge */ /* synthetic */ Intent createIntent(Context r1, PickVisualMediaRequest r2) {
        return createIntent2(r1, r2);
    }

    /* renamed from: getSynchronousResult, reason: avoid collision after fix types in other method */
    public final ActivityResultContract.SynchronousResult<Uri> getSynchronousResult2(Context r1, PickVisualMediaRequest r2) {
        return null;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public /* bridge */ /* synthetic */ ActivityResultContract.SynchronousResult<Uri> getSynchronousResult(Context r1, PickVisualMediaRequest r2) {
        return getSynchronousResult2(r1, r2);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.activity.result.contract.ActivityResultContract
    public final Uri parseResult(int r2, Intent r3) {
        if (r2 == (-1)) goto L6;
        r3 = null;
    L6:
        if (r3 == null) goto L11;
        Uri r1 = r3.getData();
        if (r1 == null) goto L10;
        return r1;
    L10:
        return (Uri) CollectionsKt.firstOrNull(ActivityResultContracts$GetMultipleContents.Companion.getClipDataUris$activity_release(r3));
    L11:
        return null;
    }

    @Override // androidx.activity.result.contract.ActivityResultContract
    public /* bridge */ /* synthetic */ Uri parseResult(int r1, Intent r2) {
        return parseResult(r1, r2);
    }
}
