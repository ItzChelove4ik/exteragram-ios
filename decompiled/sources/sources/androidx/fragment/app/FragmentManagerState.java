package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = null;
    ArrayList<String> mActive;
    ArrayList<String> mAdded;
    BackStackRecordState[] mBackStack;
    int mBackStackIndex;
    ArrayList<String> mBackStackStateKeys;
    ArrayList<BackStackState> mBackStackStates;
    ArrayList<FragmentManager.LaunchedFragmentInfo> mLaunchedFragments;
    String mPrimaryNavActiveWho;

    /* renamed from: androidx.fragment.app.FragmentManagerState$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<FragmentManagerState> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState createFromParcel(Parcel r1) {
            return new FragmentManagerState(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ FragmentManagerState createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FragmentManagerState[] newArray(int r1) {
            return new FragmentManagerState[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ FragmentManagerState[] newArray(int r1) {
            return newArray(r1);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public FragmentManagerState() {
        this.mPrimaryNavActiveWho = null;
        this.mBackStackStateKeys = new ArrayList();
        this.mBackStackStates = new ArrayList();
    }

    public FragmentManagerState(Parcel r2) {
        this.mPrimaryNavActiveWho = null;
        this.mBackStackStateKeys = new ArrayList();
        this.mBackStackStates = new ArrayList();
        this.mActive = r2.createStringArrayList();
        this.mAdded = r2.createStringArrayList();
        this.mBackStack = (BackStackRecordState[]) r2.createTypedArray(BackStackRecordState.CREATOR);
        this.mBackStackIndex = r2.readInt();
        this.mPrimaryNavActiveWho = r2.readString();
        this.mBackStackStateKeys = r2.createStringArrayList();
        this.mBackStackStates = r2.createTypedArrayList(BackStackState.CREATOR);
        this.mLaunchedFragments = r2.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r2, int r3) {
        r2.writeStringList(this.mActive);
        r2.writeStringList(this.mAdded);
        r2.writeTypedArray(this.mBackStack, r3);
        r2.writeInt(this.mBackStackIndex);
        r2.writeString(this.mPrimaryNavActiveWho);
        r2.writeStringList(this.mBackStackStateKeys);
        r2.writeTypedList(this.mBackStackStates);
        r2.writeTypedList(this.mLaunchedFragments);
    }
}
