package androidx.car.app.messaging.model;

import android.net.Uri;
import android.os.Bundle;
import androidx.car.app.model.CarText;
import androidx.core.app.Person;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public class CarMessage {
    private final CarText mBody;
    private final boolean mIsRead;
    private final String mMultimediaMimeType;
    private final Uri mMultimediaUri;
    private final long mReceivedTimeEpochMillis;
    private final Bundle mSender;

    public static final class Builder {
        CarText mBody;
        boolean mIsRead;
        String mMultimediaMimeType;
        Uri mMultimediaUri;
        long mReceivedTimeEpochMillis;
        Person mSender;

        public Builder() {
        }

        public CarMessage build() {
            String r0 = this.mMultimediaMimeType;
            boolean r1 = false;
            if (r0 != null) goto L5;
            boolean r3 = true;
        L7:
            if (this.mMultimediaUri != null) goto L10;
            r1 = true;
        L10:
            if ((r1 ^ r3) == false) goto L12;
            Segment$$ExternalSyntheticBUOutline1.m("Incomplete multimedia data detected in CarMessage. Please be sure to provide both MIME type and URI for multimedia messages.");
            return null;
        L12:
            if (this.mBody != null) goto L19;
            if (r0 != null) goto L19;
            Segment$$ExternalSyntheticBUOutline1.m("Message must have content. Please provide body text, multimedia data (URI + MIME type), or both.");
            return null;
        L19:
            return new CarMessage(this);
        L5:
            r3 = false;
            goto L7
        }

        public Builder setBody(CarText r1) {
            this.mBody = r1;
            return this;
        }

        public Builder setRead(boolean r1) {
            this.mIsRead = r1;
            return this;
        }

        public Builder setReceivedTimeEpochMillis(long r1) {
            this.mReceivedTimeEpochMillis = r1;
            return this;
        }

        public Builder setSender(Person r1) {
            this.mSender = r1;
            return this;
        }
    }

    private CarMessage() {
        this.mSender = null;
        this.mBody = null;
        this.mMultimediaMimeType = null;
        this.mMultimediaUri = null;
        this.mReceivedTimeEpochMillis = 0;
        this.mIsRead = false;
    }

    public CarMessage(Builder r3) {
        Person r0 = r3.mSender;
        if (r0 != null) goto L5;
        Bundle r02 = null;
    L6:
        this.mSender = r02;
        this.mBody = r3.mBody;
        this.mMultimediaMimeType = r3.mMultimediaMimeType;
        this.mMultimediaUri = r3.mMultimediaUri;
        this.mReceivedTimeEpochMillis = r3.mReceivedTimeEpochMillis;
        this.mIsRead = r3.mIsRead;
        return;
    L5:
        r02 = ConversationItem.validateSender(r0).toBundle();
        goto L6
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof CarMessage) == true) goto L8;
        return false;
    L8:
        CarMessage r82 = (CarMessage) r8;
        if (PersonsEqualityHelper.arePersonsEqual(getSender(), r82.getSender()) == true) goto L11;
    L17:
        return false;
    L11:
        if (Objects.equals(this.mBody, r82.mBody) == false) goto L17;
        if (this.mReceivedTimeEpochMillis != r82.mReceivedTimeEpochMillis) goto L17;
        if (this.mIsRead != r82.mIsRead) goto L17;
        return true;
    }

    public CarText getBody() {
        return this.mBody;
    }

    public String getMultimediaMimeType() {
        return this.mMultimediaMimeType;
    }

    public Uri getMultimediaUri() {
        return this.mMultimediaUri;
    }

    public long getReceivedTimeEpochMillis() {
        return this.mReceivedTimeEpochMillis;
    }

    public Person getSender() {
        Bundle r0 = this.mSender;
        if (r0 != null) goto L7;
        return null;
    L7:
        return Person.fromBundle(r0);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(PersonsEqualityHelper.getPersonHashCode(getSender())), this.mBody, Long.valueOf(this.mReceivedTimeEpochMillis), Boolean.valueOf(this.mIsRead)});
    }

    public boolean isRead() {
        return this.mIsRead;
    }
}
