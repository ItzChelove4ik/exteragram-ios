package androidx.car.app.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface ICarHardwareResultTypes extends IInterface {
    public static final String DESCRIPTOR = null;
    public static final int TYPE_INFO_ENERGY_LEVEL = 4;
    public static final int TYPE_INFO_ENERGY_PROFILE = 2;
    public static final int TYPE_INFO_EV_STATUS = 7;
    public static final int TYPE_INFO_MILEAGE = 6;
    public static final int TYPE_INFO_MODEL = 1;
    public static final int TYPE_INFO_SPEED = 5;
    public static final int TYPE_INFO_TOLL = 3;
    public static final int TYPE_SENSOR_ACCELEROMETER = 20;
    public static final int TYPE_SENSOR_CAR_LOCATION = 23;
    public static final int TYPE_SENSOR_COMPASS = 21;
    public static final int TYPE_SENSOR_GYROSCOPE = 22;
    public static final int TYPE_UNKNOWN = 0;

    public static class Default implements ICarHardwareResultTypes {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarHardwareResultTypes {

        public static class Proxy implements ICarHardwareResultTypes {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return ICarHardwareResultTypes.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, ICarHardwareResultTypes.DESCRIPTOR);
        }

        public static ICarHardwareResultTypes asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(ICarHardwareResultTypes.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof ICarHardwareResultTypes) == false) goto L12;
            return (ICarHardwareResultTypes) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r3, Parcel r4, Parcel r5, int r6) {
            String r0 = ICarHardwareResultTypes.DESCRIPTOR;
            if (r3 != 1598968902) goto L7;
            r5.writeString(r0);
            return true;
        L7:
            return super.onTransact(r3, r4, r5, r6);
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$hardware$ICarHardwareResultTypes".replace(Typography.dollar, '.');
    }
}
