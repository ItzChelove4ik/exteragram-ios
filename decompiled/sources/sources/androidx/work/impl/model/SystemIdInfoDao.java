package androidx.work.impl.model;

import java.util.List;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H'¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH'¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000b\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H'¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012H'¢\u0006\u0004\b\u0013\u0010\u0014ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0003"}, d2 = {"Landroidx/work/impl/model/SystemIdInfoDao;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/model/SystemIdInfo;", "systemIdInfo", _UrlKt.FRAGMENT_ENCODE_SET, "insertSystemIdInfo", "(Landroidx/work/impl/model/SystemIdInfo;)V", _UrlKt.FRAGMENT_ENCODE_SET, "workSpecId", _UrlKt.FRAGMENT_ENCODE_SET, "generation", "getSystemIdInfo", "(Ljava/lang/String;I)Landroidx/work/impl/model/SystemIdInfo;", "Landroidx/work/impl/model/WorkGenerationalId;", "id", "(Landroidx/work/impl/model/WorkGenerationalId;)Landroidx/work/impl/model/SystemIdInfo;", "removeSystemIdInfo", "(Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "getWorkSpecIds", "()Ljava/util/List;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public interface SystemIdInfoDao {
    default SystemIdInfo getSystemIdInfo(WorkGenerationalId r2) {
        return getSystemIdInfo(r2.getWorkSpecId(), r2.getGeneration());
    }

    SystemIdInfo getSystemIdInfo(String r1, int r2);

    List<String> getWorkSpecIds();

    void insertSystemIdInfo(SystemIdInfo r1);

    void removeSystemIdInfo(String r1);
}
