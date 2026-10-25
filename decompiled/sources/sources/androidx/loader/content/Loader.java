package androidx.loader.content;

import android.content.Context;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public abstract class Loader<D> {
    private boolean mAbandoned;
    private boolean mContentChanged;
    private Context mContext;
    private int mId;
    private OnLoadCompleteListener<D> mListener;
    private boolean mProcessingChange;
    private boolean mReset;
    private boolean mStarted;

    public interface OnLoadCompleteListener<D> {
        void onLoadComplete(Loader<D> r1, D r2);
    }

    public Loader(Context r3) {
        this.mStarted = false;
        this.mAbandoned = false;
        this.mReset = true;
        this.mContentChanged = false;
        this.mProcessingChange = false;
        this.mContext = r3.getApplicationContext();
    }

    public void abandon() {
        this.mAbandoned = true;
        onAbandon();
    }

    public boolean cancelLoad() {
        return onCancelLoad();
    }

    public void commitContentChanged() {
        this.mProcessingChange = false;
    }

    public String dataToString(D r2) {
        StringBuilder r1 = new StringBuilder(64);
        if (r2 != null) goto L5;
        r1.append("null");
    L7:
        return r1.toString();
    L5:
        Class<?> r22 = r2.getClass();
        r1.append(r22.getSimpleName());
        r1.append("{");
        r1.append(Integer.toHexString(System.identityHashCode(r22)));
        r1.append("}");
        goto L7
    }

    public void deliverCancellation() {
    }

    public void deliverResult(D r2) {
        OnLoadCompleteListener<D> r0 = this.mListener;
        if (r0 == null) goto L6;
        r0.onLoadComplete(this, r2);
        return;
    }

    @Deprecated
    public void dump(String r1, FileDescriptor r2, PrintWriter r3, String[] r4) {
        r3.print(r1);
        r3.print("mId=");
        r3.print(this.mId);
        r3.print(" mListener=");
        r3.println(this.mListener);
        if (this.mStarted == false) goto L5;
    L8:
        r3.print(r1);
        r3.print("mStarted=");
        r3.print(this.mStarted);
        r3.print(" mContentChanged=");
        r3.print(this.mContentChanged);
        r3.print(" mProcessingChange=");
        r3.println(this.mProcessingChange);
    L10:
        if (this.mAbandoned == false) goto L12;
    L15:
        r3.print(r1);
        r3.print("mAbandoned=");
        r3.print(this.mAbandoned);
        r3.print(" mReset=");
        r3.println(this.mReset);
        return;
    L12:
        if (this.mReset == true) goto L15;
        return;
    L5:
        if (this.mContentChanged == true) goto L8;
        if (this.mProcessingChange == false) goto L10;
        goto L8
    }

    public void forceLoad() {
        onForceLoad();
    }

    public boolean isAbandoned() {
        return this.mAbandoned;
    }

    public boolean isStarted() {
        return this.mStarted;
    }

    public void onAbandon() {
    }

    public abstract boolean onCancelLoad();

    public void onContentChanged() {
        if (this.mStarted == false) goto L6;
        forceLoad();
        return;
    L6:
        this.mContentChanged = true;
    }

    public void onForceLoad() {
    }

    public void onReset() {
    }

    public abstract void onStartLoading();

    public void onStopLoading() {
    }

    public void registerListener(int r2, OnLoadCompleteListener<D> r3) {
        if (this.mListener != null) goto L6;
        this.mListener = r3;
        this.mId = r2;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("There is already a listener registered");
    }

    public void reset() {
        onReset();
        this.mReset = true;
        this.mStarted = false;
        this.mAbandoned = false;
        this.mContentChanged = false;
        this.mProcessingChange = false;
    }

    public void rollbackContentChanged() {
        if (this.mProcessingChange == false) goto L6;
        onContentChanged();
        return;
    }

    public final void startLoading() {
        this.mStarted = true;
        this.mReset = false;
        this.mAbandoned = false;
        onStartLoading();
    }

    public void stopLoading() {
        this.mStarted = false;
        onStopLoading();
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder(64);
        Class<?> r1 = getClass();
        r0.append(r1.getSimpleName());
        r0.append("{");
        r0.append(Integer.toHexString(System.identityHashCode(r1)));
        r0.append(" id=");
        r0.append(this.mId);
        r0.append("}");
        return r0.toString();
    }

    public void unregisterListener(OnLoadCompleteListener<D> r2) {
        OnLoadCompleteListener<D> r0 = this.mListener;
        if (r0 == null) goto L9;
        if (r0 != r2) goto L7;
        this.mListener = null;
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("Attempting to unregister the wrong listener");
        return;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("No listener register");
    }
}
