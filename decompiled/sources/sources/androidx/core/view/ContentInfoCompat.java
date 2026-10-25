package androidx.core.view;

import android.content.ClipData;
import android.content.ClipDescription;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.ContentInfo;
import androidx.core.util.Preconditions;
import androidx.core.util.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public final class ContentInfoCompat {
    private final Compat mCompat;

    public static final class Builder {
        private final BuilderCompat mBuilderCompat;

        public Builder(ClipData r3, int r4) {
            if (Build.VERSION.SDK_INT < 31) goto L6;
            this.mBuilderCompat = new BuilderCompat31Impl(r3, r4);
            return;
        L6:
            this.mBuilderCompat = new BuilderCompatImpl(r3, r4);
        }

        public Builder(ContentInfoCompat r3) {
            if (Build.VERSION.SDK_INT < 31) goto L6;
            this.mBuilderCompat = new BuilderCompat31Impl(r3);
            return;
        L6:
            this.mBuilderCompat = new BuilderCompatImpl(r3);
        }

        public ContentInfoCompat build() {
            return this.mBuilderCompat.build();
        }

        public Builder setClip(ClipData r2) {
            this.mBuilderCompat.setClip(r2);
            return this;
        }

        public Builder setExtras(Bundle r2) {
            this.mBuilderCompat.setExtras(r2);
            return this;
        }

        public Builder setFlags(int r2) {
            this.mBuilderCompat.setFlags(r2);
            return this;
        }

        public Builder setLinkUri(Uri r2) {
            this.mBuilderCompat.setLinkUri(r2);
            return this;
        }
    }

    public interface BuilderCompat {
        ContentInfoCompat build();

        void setClip(ClipData r1);

        void setExtras(Bundle r1);

        void setFlags(int r1);

        void setLinkUri(Uri r1);
    }

    public static final class BuilderCompat31Impl implements BuilderCompat {
        private final ContentInfo.Builder mPlatformBuilder;

        public BuilderCompat31Impl(ClipData r1, int r2) {
            this.mPlatformBuilder = ContentInfoCompat$BuilderCompat31Impl$$ExternalSyntheticApiModelOutline0.m(r1, r2);
        }

        public BuilderCompat31Impl(ContentInfoCompat r1) {
            ContentInfoCompat$BuilderCompat31Impl$$ExternalSyntheticApiModelOutline2.m();
            this.mPlatformBuilder = ContentInfoCompat$BuilderCompat31Impl$$ExternalSyntheticApiModelOutline1.m(r1.toContentInfo());
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public ContentInfoCompat build() {
            return new ContentInfoCompat(new Compat31Impl(this.mPlatformBuilder.build()));
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public void setClip(ClipData r1) {
            this.mPlatformBuilder.setClip(r1);
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public void setExtras(Bundle r1) {
            this.mPlatformBuilder.setExtras(r1);
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public void setFlags(int r1) {
            this.mPlatformBuilder.setFlags(r1);
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public void setLinkUri(Uri r1) {
            this.mPlatformBuilder.setLinkUri(r1);
        }
    }

    public static final class BuilderCompatImpl implements BuilderCompat {
        ClipData mClip;
        Bundle mExtras;
        int mFlags;
        Uri mLinkUri;
        int mSource;

        public BuilderCompatImpl(ClipData r1, int r2) {
            this.mClip = r1;
            this.mSource = r2;
        }

        public BuilderCompatImpl(ContentInfoCompat r2) {
            this.mClip = r2.getClip();
            this.mSource = r2.getSource();
            this.mFlags = r2.getFlags();
            this.mLinkUri = r2.getLinkUri();
            this.mExtras = r2.getExtras();
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public ContentInfoCompat build() {
            return new ContentInfoCompat(new CompatImpl(this));
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public void setClip(ClipData r1) {
            this.mClip = r1;
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public void setExtras(Bundle r1) {
            this.mExtras = r1;
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public void setFlags(int r1) {
            this.mFlags = r1;
        }

        @Override // androidx.core.view.ContentInfoCompat.BuilderCompat
        public void setLinkUri(Uri r1) {
            this.mLinkUri = r1;
        }
    }

    public interface Compat {
        ClipData getClip();

        Bundle getExtras();

        int getFlags();

        Uri getLinkUri();

        int getSource();

        ContentInfo getWrapped();
    }

    public static final class Compat31Impl implements Compat {
        private final ContentInfo mWrapped;

        public Compat31Impl(ContentInfo r1) {
            this.mWrapped = ContentInfoCompat$$ExternalSyntheticApiModelOutline0.m(Preconditions.checkNotNull(r1));
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public ClipData getClip() {
            return this.mWrapped.getClip();
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public Bundle getExtras() {
            return this.mWrapped.getExtras();
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public int getFlags() {
            return this.mWrapped.getFlags();
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public Uri getLinkUri() {
            return this.mWrapped.getLinkUri();
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public int getSource() {
            return this.mWrapped.getSource();
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public ContentInfo getWrapped() {
            return this.mWrapped;
        }

        public String toString() {
            return "ContentInfoCompat{" + this.mWrapped + "}";
        }
    }

    public static final class CompatImpl implements Compat {
        private final ClipData mClip;
        private final Bundle mExtras;
        private final int mFlags;
        private final Uri mLinkUri;
        private final int mSource;

        public CompatImpl(BuilderCompatImpl r5) {
            this.mClip = (ClipData) Preconditions.checkNotNull(r5.mClip);
            this.mSource = Preconditions.checkArgumentInRange(r5.mSource, 0, 5, "source");
            this.mFlags = Preconditions.checkFlagsArgument(r5.mFlags, 1);
            this.mLinkUri = r5.mLinkUri;
            this.mExtras = r5.mExtras;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public ClipData getClip() {
            return this.mClip;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public Bundle getExtras() {
            return this.mExtras;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public int getFlags() {
            return this.mFlags;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public Uri getLinkUri() {
            return this.mLinkUri;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public int getSource() {
            return this.mSource;
        }

        @Override // androidx.core.view.ContentInfoCompat.Compat
        public ContentInfo getWrapped() {
            return null;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder("ContentInfoCompat{clip=");
            r0.append(this.mClip.getDescription());
            r0.append(", source=");
            r0.append(ContentInfoCompat.sourceToString(this.mSource));
            r0.append(", flags=");
            r0.append(ContentInfoCompat.flagsToString(this.mFlags));
            Uri r1 = this.mLinkUri;
            String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r1 != null) goto L5;
            String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r0.append(r12);
            if (this.mExtras == null) goto L10;
            r2 = ", hasExtras";
        L10:
            r0.append(r2);
            r0.append("}");
            return r0.toString();
        L5:
            r12 = ", hasLinkUri(" + this.mLinkUri.toString().length() + ")";
            goto L6
        }
    }

    public ContentInfoCompat(Compat r1) {
        this.mCompat = r1;
    }

    public static ClipData buildClipData(ClipDescription r2, List<ClipData.Item> r3) {
        ClipData r0 = new ClipData(new ClipDescription(r2), r3.get(0));
        int r22 = 1;
    L4:
        if (r22 >= r3.size()) goto L6;
        r0.addItem(r3.get(r22));
        r22 = r22 + 1;
        goto L4
    L6:
        return r0;
    }

    public static String flagsToString(int r1) {
        if ((r1 & 1) == 0) goto L7;
        return "FLAG_CONVERT_TO_PLAIN_TEXT";
    L7:
        return String.valueOf(r1);
    }

    public static Pair<ClipData, ClipData> partition(ClipData r6, Predicate<ClipData.Item> r7) {
        int r1 = 0;
        ArrayList r2 = null;
        ArrayList r3 = null;
    L4:
        if (r1 >= r6.getItemCount()) goto L14;
        ClipData.Item r4 = r6.getItemAt(r1);
        if (r7.test(r4) == false) goto L10;
        if (r2 != null) goto L9;
        r2 = new ArrayList();
    L9:
        r2.add(r4);
    L13:
        r1 = r1 + 1;
        goto L4
    L10:
        if (r3 != null) goto L12;
        r3 = new ArrayList();
    L12:
        r3.add(r4);
        goto L13
    L14:
        if (r2 == null) goto L16;
        if (r3 != null) goto L21;
        return Pair.create(r6, null);
    L21:
        return Pair.create(buildClipData(r6.getDescription(), r2), buildClipData(r6.getDescription(), r3));
    L16:
        return Pair.create(null, r6);
    }

    public static String sourceToString(int r1) {
        if (r1 != 0) goto L4;
        return "SOURCE_APP";
    L4:
        if (r1 != 1) goto L6;
        return "SOURCE_CLIPBOARD";
    L6:
        if (r1 != 2) goto L8;
        return "SOURCE_INPUT_METHOD";
    L8:
        if (r1 != 3) goto L10;
        return "SOURCE_DRAG_AND_DROP";
    L10:
        if (r1 != 4) goto L12;
        return "SOURCE_AUTOFILL";
    L12:
        if (r1 != 5) goto L14;
        return "SOURCE_PROCESS_TEXT";
    L14:
        return String.valueOf(r1);
    }

    public static ContentInfoCompat toContentInfoCompat(ContentInfo r2) {
        return new ContentInfoCompat(new Compat31Impl(r2));
    }

    public ClipData getClip() {
        return this.mCompat.getClip();
    }

    public Bundle getExtras() {
        return this.mCompat.getExtras();
    }

    public int getFlags() {
        return this.mCompat.getFlags();
    }

    public Uri getLinkUri() {
        return this.mCompat.getLinkUri();
    }

    public int getSource() {
        return this.mCompat.getSource();
    }

    public Pair<ContentInfoCompat, ContentInfoCompat> partition(Predicate<ClipData.Item> r5) {
        ClipData r0 = this.mCompat.getClip();
        if (r0.getItemCount() != 1) goto L12;
        boolean r52 = r5.test(r0.getItemAt(0));
        if (r52 == false) goto L7;
        ContentInfoCompat r02 = this;
    L8:
        if (r52 == false) goto L11;
        ContentInfoCompat this = null;
    L11:
        return Pair.create(r02, this);
    L7:
        r02 = null;
        goto L8
    L12:
        Pair<ClipData, ClipData> r53 = partition(r0, r5);
        if (r53.first != null) goto L17;
        return Pair.create(null, this);
    L17:
        if (r53.second != null) goto L21;
        return Pair.create(this, null);
    L21:
        return Pair.create(new Builder(this).setClip((ClipData) r53.first).build(), new Builder(this).setClip((ClipData) r53.second).build());
    }

    public ContentInfo toContentInfo() {
        ContentInfo r0 = this.mCompat.getWrapped();
        Objects.requireNonNull(r0);
        ContentInfoCompat$$ExternalSyntheticApiModelOutline0.m(r0);
        return r0;
    }

    public String toString() {
        return this.mCompat.toString();
    }
}
