package androidx.room;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class BaseRoomConnectionManager$DriverWrapper$openLocked$2 implements Function1 {
    final /* synthetic */ String $filename;

    public BaseRoomConnectionManager$DriverWrapper$openLocked$2(String r1) {
        this.$filename = r1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object r1) {
        return invoke((Throwable) r1);
    }

    public final Void invoke(Throwable r4) {
        throw new IllegalStateException("Unable to open database '" + this.$filename + "'. Was a proper path / name used in Room's database builder?", r4);
    }
}
