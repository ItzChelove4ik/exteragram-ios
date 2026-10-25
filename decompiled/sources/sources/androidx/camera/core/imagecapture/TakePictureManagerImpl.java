package androidx.camera.core.imagecapture;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import androidx.camera.core.ForwardingImageProxy;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class TakePictureManagerImpl implements TakePictureManager, ForwardingImageProxy.OnImageCloseListener {
    final ImageCaptureControl mImageCaptureControl;
    ImagePipeline mImagePipeline;
    private final List<RequestWithCallback> mIncompleteRequests;
    final Deque<TakePictureRequest> mNewRequests;
    boolean mPaused;

    public TakePictureManagerImpl(ImageCaptureControl r2) {
        this.mNewRequests = new ArrayDeque();
        this.mPaused = false;
        Threads.checkMainThread();
        this.mImageCaptureControl = r2;
        this.mIncompleteRequests = new ArrayList();
    }

    @Override // androidx.camera.core.imagecapture.TakePictureManager
    public void abortRequests() {
        Threads.checkMainThread();
        ImageCaptureException r0 = new ImageCaptureException(3, "Camera is closed.", null);
        Iterator<TakePictureRequest> r1 = this.mNewRequests.iterator();
        if (r1.hasNext() == true) goto L8;
        this.mNewRequests.clear();
        ArrayList r12 = new ArrayList(this.mIncompleteRequests);
        int r4 = r12.size();
        int r2 = 0;
    L5:
        if (r2 >= r4) goto L7;
        Object r3 = r12.get(r2);
        r2 = r2 + 1;
        ((RequestWithCallback) r3).abortAndSendErrorToApp(r0);
        goto L5
    L7:
        return;
    L8:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1.next());
        throw null;
    }

    public boolean hasCapturingRequest() {
        return false;
    }

    public void issueNextRequest() {
        Threads.checkMainThread();
        Log.d("TakePictureManagerImpl", "Issue the next TakePictureRequest.");
        if (hasCapturingRequest() == false) goto L7;
        Log.d("TakePictureManagerImpl", "There is already a request in-flight.");
        return;
    L7:
        if (this.mPaused == false) goto L11;
        Log.d("TakePictureManagerImpl", "The class is paused.");
        return;
    L11:
        if (this.mImagePipeline.getCapacity() != 0) goto L14;
        Log.d("TakePictureManagerImpl", "Too many acquire images. Close image to be able to process next.");
        return;
    L14:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.mNewRequests.poll());
        Log.d("TakePictureManagerImpl", "No new request.");
    }

    @Override // androidx.camera.core.ForwardingImageProxy.OnImageCloseListener
    public void onImageClose(ImageProxy r2) {
        CameraXExecutors.mainThreadExecutor().execute(new TakePictureManagerImpl$$ExternalSyntheticLambda0(this));
    }

    @Override // androidx.camera.core.imagecapture.TakePictureManager
    public void pause() {
        Threads.checkMainThread();
        this.mPaused = true;
    }

    @Override // androidx.camera.core.imagecapture.TakePictureManager
    public void resume() {
        Threads.checkMainThread();
        this.mPaused = false;
        issueNextRequest();
    }

    @Override // androidx.camera.core.imagecapture.TakePictureManager
    public void setImagePipeline(ImagePipeline r1) {
        Threads.checkMainThread();
        this.mImagePipeline = r1;
        r1.setOnImageCloseListener(this);
    }
}
