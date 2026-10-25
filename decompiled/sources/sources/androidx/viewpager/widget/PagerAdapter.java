package androidx.viewpager.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes4.dex */
public abstract class PagerAdapter {
    private final DataSetObservable mObservable;
    private DataSetObserver mViewPagerObserver;

    public PagerAdapter() {
        this.mObservable = new DataSetObservable();
    }

    public abstract void destroyItem(ViewGroup r1, int r2, Object r3);

    @Deprecated
    public void finishUpdate(View r1) {
    }

    public void finishUpdate(ViewGroup r1) {
        finishUpdate(r1);
    }

    public abstract int getCount();

    public int getItemPosition(Object r1) {
        return -1;
    }

    public CharSequence getPageTitle(int r1) {
        return null;
    }

    public float getPageWidth(int r1) {
        return 1.0f;
    }

    public abstract Object instantiateItem(ViewGroup r1, int r2);

    public abstract boolean isViewFromObject(View r1, Object r2);

    public void notifyDataSetChanged() {
        monitor-enter(this);
        DataSetObserver r0 = this.mViewPagerObserver;     // Catch: Throwable -> L6
        if (r0 == null) goto L8;
        r0.onChanged();     // Catch: Throwable -> L6
    L8:
        monitor-exit(this);     // Catch: Throwable -> L6
        this.mObservable.notifyChanged();
        return;
    L6:
        th = move-exception;
        throw th;
    }

    public void registerDataSetObserver(DataSetObserver r1) {
        this.mObservable.registerObserver(r1);
    }

    public void restoreState(Parcelable r1, ClassLoader r2) {
    }

    public Parcelable saveState() {
        return null;
    }

    @Deprecated
    public void setPrimaryItem(View r1, int r2, Object r3) {
    }

    public void setPrimaryItem(ViewGroup r1, int r2, Object r3) {
        setPrimaryItem(r1, r2, r3);
    }

    public void setViewPagerObserver(DataSetObserver r1) {
        monitor-enter(this);
        this.mViewPagerObserver = r1;     // Catch: Throwable -> L6
        monitor-exit(this);     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Deprecated
    public void startUpdate(View r1) {
    }

    public void startUpdate(ViewGroup r1) {
        startUpdate(r1);
    }

    public void unregisterDataSetObserver(DataSetObserver r1) {
        this.mObservable.unregisterObserver(r1);
    }
}
