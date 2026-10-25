package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
import okio.Segment$$ExternalSyntheticBUOutline1;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
final class BackStackRecordState implements Parcelable {
    public static final Parcelable.Creator<BackStackRecordState> CREATOR = null;
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int[] mCurrentMaxLifecycleStates;
    final ArrayList<String> mFragmentWhos;
    final int mIndex;
    final String mName;
    final int[] mOldMaxLifecycleStates;
    final int[] mOps;
    final boolean mReorderingAllowed;
    final ArrayList<String> mSharedElementSourceNames;
    final ArrayList<String> mSharedElementTargetNames;
    final int mTransition;

    /* renamed from: androidx.fragment.app.BackStackRecordState$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<BackStackRecordState> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackRecordState createFromParcel(Parcel r1) {
            return new BackStackRecordState(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ BackStackRecordState createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackRecordState[] newArray(int r1) {
            return new BackStackRecordState[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ BackStackRecordState[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public BackStackRecordState(Parcel r3) {
        this.mOps = r3.createIntArray();
        this.mFragmentWhos = r3.createStringArrayList();
        this.mOldMaxLifecycleStates = r3.createIntArray();
        this.mCurrentMaxLifecycleStates = r3.createIntArray();
        this.mTransition = r3.readInt();
        this.mName = r3.readString();
        this.mIndex = r3.readInt();
        this.mBreadCrumbTitleRes = r3.readInt();
        Parcelable.Creator r0 = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.mBreadCrumbTitleText = (CharSequence) r0.createFromParcel(r3);
        this.mBreadCrumbShortTitleRes = r3.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence) r0.createFromParcel(r3);
        this.mSharedElementSourceNames = r3.createStringArrayList();
        this.mSharedElementTargetNames = r3.createStringArrayList();
        if (r3.readInt() == 0) goto L5;
        boolean r32 = true;
    L6:
        this.mReorderingAllowed = r32;
        return;
    L5:
        r32 = false;
        goto L6
    }

    public BackStackRecordState(BackStackRecord r10) {
        int r0 = r10.mOps.size();
        this.mOps = new int[r0 * 6];
        if (r10.mAddToBackStack == false) goto L13;
        this.mFragmentWhos = new ArrayList(r0);
        this.mOldMaxLifecycleStates = new int[r0];
        this.mCurrentMaxLifecycleStates = new int[r0];
        int r1 = 0;
        int r3 = 0;
    L5:
        if (r1 >= r0) goto L11;
        FragmentTransaction.Op r4 = r10.mOps.get(r1);
        int r6 = r3 + 1;
        this.mOps[r3] = r4.mCmd;
        ArrayList<String> r5 = this.mFragmentWhos;
        Fragment r7 = r4.mFragment;
        if (r7 == null) goto L9;
        String r72 = r7.mWho;
    L10:
        r5.add(r72);
        int[] r52 = this.mOps;
        r52[r6] = r4.mFromExpandedOp ? 1 : 0;
        r52[r3 + 2] = r4.mEnterAnim;
        r52[r3 + 3] = r4.mExitAnim;
        int r62 = r3 + 5;
        r52[r3 + 4] = r4.mPopEnterAnim;
        r3 = r3 + 6;
        r52[r62] = r4.mPopExitAnim;
        this.mOldMaxLifecycleStates[r1] = r4.mOldMaxState.ordinal();
        this.mCurrentMaxLifecycleStates[r1] = r4.mCurrentMaxState.ordinal();
        r1 = r1 + 1;
        goto L5
    L9:
        r72 = null;
        goto L10
    L11:
        this.mTransition = r10.mTransition;
        this.mName = r10.mName;
        this.mIndex = r10.mIndex;
        this.mBreadCrumbTitleRes = r10.mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = r10.mBreadCrumbTitleText;
        this.mBreadCrumbShortTitleRes = r10.mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = r10.mBreadCrumbShortTitleText;
        this.mSharedElementSourceNames = r10.mSharedElementSourceNames;
        this.mSharedElementTargetNames = r10.mSharedElementTargetNames;
        this.mReorderingAllowed = r10.mReorderingAllowed;
        return;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("Not on back stack");
        throw null;
    }

    private void fillInBackStackRecord(BackStackRecord r10) {
        int r1 = 0;
        int r2 = 0;
    L3:
        boolean r4 = true;
        if (r1 >= this.mOps.length) goto L13;
        FragmentTransaction.Op r3 = new FragmentTransaction.Op();
        int r6 = r1 + 1;
        r3.mCmd = this.mOps[r1];
        if (FragmentManager.isLoggingEnabled(2) == false) goto L8;
        Log.v("FragmentManager", "Instantiate " + r10 + " op #" + r2 + " base fragment #" + this.mOps[r6]);
    L8:
        r3.mOldMaxState = Lifecycle.State.values()[this.mOldMaxLifecycleStates[r2]];
        r3.mCurrentMaxState = Lifecycle.State.values()[this.mCurrentMaxLifecycleStates[r2]];
        int[] r5 = this.mOps;
        int r7 = r1 + 2;
        if (r5[r6] != 0) goto L12;
        r4 = false;
    L12:
        r3.mFromExpandedOp = r4;
        int r62 = r5[r7];
        r3.mEnterAnim = r62;
        int r42 = r5[r1 + 3];
        r3.mExitAnim = r42;
        int r8 = r1 + 5;
        int r72 = r5[r1 + 4];
        r3.mPopEnterAnim = r72;
        r1 = r1 + 6;
        int r52 = r5[r8];
        r3.mPopExitAnim = r52;
        r10.mEnterAnim = r62;
        r10.mExitAnim = r42;
        r10.mPopEnterAnim = r72;
        r10.mPopExitAnim = r52;
        r10.addOp(r3);
        r2 = r2 + 1;
        goto L3
    L13:
        r10.mTransition = this.mTransition;
        r10.mName = this.mName;
        r10.mAddToBackStack = true;
        r10.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
        r10.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
        r10.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
        r10.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
        r10.mSharedElementSourceNames = this.mSharedElementSourceNames;
        r10.mSharedElementTargetNames = this.mSharedElementTargetNames;
        r10.mReorderingAllowed = this.mReorderingAllowed;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BackStackRecord instantiate(FragmentManager r5) {
        BackStackRecord r0 = new BackStackRecord(r5);
        fillInBackStackRecord(r0);
        r0.mIndex = this.mIndex;
        int r1 = 0;
    L4:
        if (r1 >= this.mFragmentWhos.size()) goto L9;
        String r2 = this.mFragmentWhos.get(r1);
        if (r2 == null) goto L8;
        FragmentTransaction.Op r3 = r0.mOps.get(r1);
        r3.mFragment = r5.findActiveFragment(r2);
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        r0.bumpBackStackNesting(1);
        return r0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r2, int r3) {
        r2.writeIntArray(this.mOps);
        r2.writeStringList(this.mFragmentWhos);
        r2.writeIntArray(this.mOldMaxLifecycleStates);
        r2.writeIntArray(this.mCurrentMaxLifecycleStates);
        r2.writeInt(this.mTransition);
        r2.writeString(this.mName);
        r2.writeInt(this.mIndex);
        r2.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, r2, 0);
        r2.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, r2, 0);
        r2.writeStringList(this.mSharedElementSourceNames);
        r2.writeStringList(this.mSharedElementTargetNames);
        r2.writeInt(this.mReorderingAllowed ? 1 : 0);
    }
}
