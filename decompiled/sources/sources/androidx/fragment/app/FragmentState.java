package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.lifecycle.Lifecycle;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator<FragmentState> CREATOR = null;
    final Bundle mArguments;
    final String mClassName;
    final int mContainerId;
    final boolean mDetached;
    final int mFragmentId;
    final boolean mFromLayout;
    final boolean mHidden;
    final int mMaxLifecycleState;
    final boolean mRemoving;
    final boolean mRetainInstance;
    Bundle mSavedFragmentState;
    final String mTag;
    final String mWho;

    /* renamed from: androidx.fragment.app.FragmentState$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<FragmentState> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentState createFromParcel(Parcel r1) {
            return new FragmentState(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ FragmentState createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentState[] newArray(int r1) {
            return new FragmentState[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ FragmentState[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public FragmentState(Parcel r4) {
        this.mClassName = r4.readString();
        this.mWho = r4.readString();
        boolean r1 = false;
        if (r4.readInt() == 0) goto L5;
        boolean r0 = true;
    L6:
        this.mFromLayout = r0;
        this.mFragmentId = r4.readInt();
        this.mContainerId = r4.readInt();
        this.mTag = r4.readString();
        if (r4.readInt() == 0) goto L9;
        boolean r02 = true;
    L10:
        this.mRetainInstance = r02;
        if (r4.readInt() == 0) goto L13;
        boolean r03 = true;
    L14:
        this.mRemoving = r03;
        if (r4.readInt() == 0) goto L17;
        boolean r04 = true;
    L18:
        this.mDetached = r04;
        this.mArguments = r4.readBundle();
        if (r4.readInt() == 0) goto L21;
        r1 = true;
    L21:
        this.mHidden = r1;
        this.mSavedFragmentState = r4.readBundle();
        this.mMaxLifecycleState = r4.readInt();
        return;
    L17:
        r04 = false;
        goto L18
    L13:
        r03 = false;
        goto L14
    L9:
        r02 = false;
        goto L10
    L5:
        r0 = false;
        goto L6
    }

    public FragmentState(Fragment r2) {
        this.mClassName = r2.getClass().getName();
        this.mWho = r2.mWho;
        this.mFromLayout = r2.mFromLayout;
        this.mFragmentId = r2.mFragmentId;
        this.mContainerId = r2.mContainerId;
        this.mTag = r2.mTag;
        this.mRetainInstance = r2.mRetainInstance;
        this.mRemoving = r2.mRemoving;
        this.mDetached = r2.mDetached;
        this.mArguments = r2.mArguments;
        this.mHidden = r2.mHidden;
        this.mMaxLifecycleState = r2.mMaxState.ordinal();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Fragment instantiate(FragmentFactory r2, ClassLoader r3) {
        Fragment r22 = r2.instantiate(r3, this.mClassName);
        Bundle r0 = this.mArguments;
        if (r0 == null) goto L5;
        r0.setClassLoader(r3);
    L5:
        r22.setArguments(this.mArguments);
        r22.mWho = this.mWho;
        r22.mFromLayout = this.mFromLayout;
        r22.mRestored = true;
        r22.mFragmentId = this.mFragmentId;
        r22.mContainerId = this.mContainerId;
        r22.mTag = this.mTag;
        r22.mRetainInstance = this.mRetainInstance;
        r22.mRemoving = this.mRemoving;
        r22.mDetached = this.mDetached;
        r22.mHidden = this.mHidden;
        r22.mMaxState = Lifecycle.State.values()[this.mMaxLifecycleState];
        Bundle r1 = this.mSavedFragmentState;
        if (r1 == null) goto L9;
        r22.mSavedFragmentState = r1;
        return r22;
    L9:
        r22.mSavedFragmentState = new Bundle();
        return r22;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(128);
        r0.append("FragmentState{");
        r0.append(this.mClassName);
        r0.append(" (");
        r0.append(this.mWho);
        r0.append(")}:");
        if (this.mFromLayout == false) goto L6;
        r0.append(" fromLayout");
    L6:
        if (this.mContainerId == 0) goto L8;
        r0.append(" id=0x");
        r0.append(Integer.toHexString(this.mContainerId));
    L8:
        String r1 = this.mTag;
        if (r1 == null) goto L14;
        if (r1.isEmpty() == true) goto L14;
        r0.append(" tag=");
        r0.append(this.mTag);
    L14:
        if (this.mRetainInstance == false) goto L17;
        r0.append(" retainInstance");
    L17:
        if (this.mRemoving == false) goto L20;
        r0.append(" removing");
    L20:
        if (this.mDetached == false) goto L23;
        r0.append(" detached");
    L23:
        if (this.mHidden == false) goto L26;
        r0.append(" hidden");
    L26:
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        r1.writeString(this.mClassName);
        r1.writeString(this.mWho);
        r1.writeInt(this.mFromLayout ? 1 : 0);
        r1.writeInt(this.mFragmentId);
        r1.writeInt(this.mContainerId);
        r1.writeString(this.mTag);
        r1.writeInt(this.mRetainInstance ? 1 : 0);
        r1.writeInt(this.mRemoving ? 1 : 0);
        r1.writeInt(this.mDetached ? 1 : 0);
        r1.writeBundle(this.mArguments);
        r1.writeInt(this.mHidden ? 1 : 0);
        r1.writeBundle(this.mSavedFragmentState);
        r1.writeInt(this.mMaxLifecycleState);
    }
}
