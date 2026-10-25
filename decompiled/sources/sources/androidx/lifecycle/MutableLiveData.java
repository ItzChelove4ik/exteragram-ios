package androidx.lifecycle;

/* loaded from: classes.dex */
public class MutableLiveData<T> extends LiveData<T> {
    public MutableLiveData() {
    }

    public MutableLiveData(T r1) {
        super(r1);
    }

    @Override // androidx.lifecycle.LiveData
    public void postValue(T r1) {
        super.postValue(r1);
    }

    @Override // androidx.lifecycle.LiveData
    public void setValue(T r1) {
        super.setValue(r1);
    }
}
