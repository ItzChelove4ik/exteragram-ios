package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RecentlyNonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes5.dex */
public class Barcode extends AbstractSafeParcelable {

    @RecentlyNonNull
    public static final Parcelable.Creator<Barcode> CREATOR = null;

    @RecentlyNonNull
    public CalendarEvent calendarEvent;

    @RecentlyNonNull
    public ContactInfo contactInfo;

    @RecentlyNonNull
    public Point[] cornerPoints;

    @RecentlyNonNull
    public String displayValue;

    @RecentlyNonNull
    public DriverLicense driverLicense;

    @RecentlyNonNull
    public Email email;
    public int format;

    @RecentlyNonNull
    public GeoPoint geoPoint;
    public boolean isRecognized;

    @RecentlyNonNull
    public Phone phone;

    @RecentlyNonNull
    public byte[] rawBytes;

    @RecentlyNonNull
    public String rawValue;

    @RecentlyNonNull
    public Sms sms;

    @RecentlyNonNull
    public UrlBookmark url;
    public int valueFormat;

    @RecentlyNonNull
    public WiFi wifi;

    public static class Address extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<Address> CREATOR = null;

        @RecentlyNonNull
        public String[] addressLines;
        public int type;

        static {
            CREATOR = new zza();
        }

        public Address(int r1, @RecentlyNonNull String[] r2) {
            this.type = r1;
            this.addressLines = r2;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r3, int r4) {
            int r42 = SafeParcelWriter.beginObjectHeader(r3);
            SafeParcelWriter.writeInt(r3, 2, this.type);
            SafeParcelWriter.writeStringArray(r3, 3, this.addressLines, false);
            SafeParcelWriter.finishObjectHeader(r3, r42);
        }
    }

    public static class CalendarDateTime extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<CalendarDateTime> CREATOR = null;
        public int day;
        public int hours;
        public boolean isUtc;
        public int minutes;
        public int month;

        @RecentlyNonNull
        public String rawValue;
        public int seconds;
        public int year;

        static {
            CREATOR = new zzd();
        }

        public CalendarDateTime(int r1, int r2, int r3, int r4, int r5, int r6, boolean r7, @RecentlyNonNull String r8) {
            this.year = r1;
            this.month = r2;
            this.day = r3;
            this.hours = r4;
            this.minutes = r5;
            this.seconds = r6;
            this.isUtc = r7;
            this.rawValue = r8;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r3, int r4) {
            int r42 = SafeParcelWriter.beginObjectHeader(r3);
            SafeParcelWriter.writeInt(r3, 2, this.year);
            SafeParcelWriter.writeInt(r3, 3, this.month);
            SafeParcelWriter.writeInt(r3, 4, this.day);
            SafeParcelWriter.writeInt(r3, 5, this.hours);
            SafeParcelWriter.writeInt(r3, 6, this.minutes);
            SafeParcelWriter.writeInt(r3, 7, this.seconds);
            SafeParcelWriter.writeBoolean(r3, 8, this.isUtc);
            SafeParcelWriter.writeString(r3, 9, this.rawValue, false);
            SafeParcelWriter.finishObjectHeader(r3, r42);
        }
    }

    public static class CalendarEvent extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<CalendarEvent> CREATOR = null;

        @RecentlyNonNull
        public String description;

        @RecentlyNonNull
        public CalendarDateTime end;

        @RecentlyNonNull
        public String location;

        @RecentlyNonNull
        public String organizer;

        @RecentlyNonNull
        public CalendarDateTime start;

        @RecentlyNonNull
        public String status;

        @RecentlyNonNull
        public String summary;

        static {
            CREATOR = new zzf();
        }

        public CalendarEvent(@RecentlyNonNull String r1, @RecentlyNonNull String r2, @RecentlyNonNull String r3, @RecentlyNonNull String r4, @RecentlyNonNull String r5, @RecentlyNonNull CalendarDateTime r6, @RecentlyNonNull CalendarDateTime r7) {
            this.summary = r1;
            this.description = r2;
            this.location = r3;
            this.organizer = r4;
            this.status = r5;
            this.start = r6;
            this.end = r7;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r5, int r6) {
            int r0 = SafeParcelWriter.beginObjectHeader(r5);
            SafeParcelWriter.writeString(r5, 2, this.summary, false);
            SafeParcelWriter.writeString(r5, 3, this.description, false);
            SafeParcelWriter.writeString(r5, 4, this.location, false);
            SafeParcelWriter.writeString(r5, 5, this.organizer, false);
            SafeParcelWriter.writeString(r5, 6, this.status, false);
            SafeParcelWriter.writeParcelable(r5, 7, this.start, r6, false);
            SafeParcelWriter.writeParcelable(r5, 8, this.end, r6, false);
            SafeParcelWriter.finishObjectHeader(r5, r0);
        }
    }

    public static class ContactInfo extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<ContactInfo> CREATOR = null;

        @RecentlyNonNull
        public Address[] addresses;

        @RecentlyNonNull
        public Email[] emails;

        @RecentlyNonNull
        public PersonName name;

        @RecentlyNonNull
        public String organization;

        @RecentlyNonNull
        public Phone[] phones;

        @RecentlyNonNull
        public String title;

        @RecentlyNonNull
        public String[] urls;

        static {
            CREATOR = new zze();
        }

        public ContactInfo(@RecentlyNonNull PersonName r1, @RecentlyNonNull String r2, @RecentlyNonNull String r3, @RecentlyNonNull Phone[] r4, @RecentlyNonNull Email[] r5, @RecentlyNonNull String[] r6, @RecentlyNonNull Address[] r7) {
            this.name = r1;
            this.organization = r2;
            this.title = r3;
            this.phones = r4;
            this.emails = r5;
            this.urls = r6;
            this.addresses = r7;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r5, int r6) {
            int r0 = SafeParcelWriter.beginObjectHeader(r5);
            SafeParcelWriter.writeParcelable(r5, 2, this.name, r6, false);
            SafeParcelWriter.writeString(r5, 3, this.organization, false);
            SafeParcelWriter.writeString(r5, 4, this.title, false);
            SafeParcelWriter.writeTypedArray(r5, 5, this.phones, r6, false);
            SafeParcelWriter.writeTypedArray(r5, 6, this.emails, r6, false);
            SafeParcelWriter.writeStringArray(r5, 7, this.urls, false);
            SafeParcelWriter.writeTypedArray(r5, 8, this.addresses, r6, false);
            SafeParcelWriter.finishObjectHeader(r5, r0);
        }
    }

    public static class DriverLicense extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<DriverLicense> CREATOR = null;

        @RecentlyNonNull
        public String addressCity;

        @RecentlyNonNull
        public String addressState;

        @RecentlyNonNull
        public String addressStreet;

        @RecentlyNonNull
        public String addressZip;

        @RecentlyNonNull
        public String birthDate;

        @RecentlyNonNull
        public String documentType;

        @RecentlyNonNull
        public String expiryDate;

        @RecentlyNonNull
        public String firstName;

        @RecentlyNonNull
        public String gender;

        @RecentlyNonNull
        public String issueDate;

        @RecentlyNonNull
        public String issuingCountry;

        @RecentlyNonNull
        public String lastName;

        @RecentlyNonNull
        public String licenseNumber;

        @RecentlyNonNull
        public String middleName;

        static {
            CREATOR = new zzh();
        }

        public DriverLicense(@RecentlyNonNull String r1, @RecentlyNonNull String r2, @RecentlyNonNull String r3, @RecentlyNonNull String r4, @RecentlyNonNull String r5, @RecentlyNonNull String r6, @RecentlyNonNull String r7, @RecentlyNonNull String r8, @RecentlyNonNull String r9, @RecentlyNonNull String r10, @RecentlyNonNull String r11, @RecentlyNonNull String r12, @RecentlyNonNull String r13, @RecentlyNonNull String r14) {
            this.documentType = r1;
            this.firstName = r2;
            this.middleName = r3;
            this.lastName = r4;
            this.gender = r5;
            this.addressStreet = r6;
            this.addressCity = r7;
            this.addressState = r8;
            this.addressZip = r9;
            this.licenseNumber = r10;
            this.issueDate = r11;
            this.expiryDate = r12;
            this.birthDate = r13;
            this.issuingCountry = r14;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r4, int r5) {
            int r52 = SafeParcelWriter.beginObjectHeader(r4);
            SafeParcelWriter.writeString(r4, 2, this.documentType, false);
            SafeParcelWriter.writeString(r4, 3, this.firstName, false);
            SafeParcelWriter.writeString(r4, 4, this.middleName, false);
            SafeParcelWriter.writeString(r4, 5, this.lastName, false);
            SafeParcelWriter.writeString(r4, 6, this.gender, false);
            SafeParcelWriter.writeString(r4, 7, this.addressStreet, false);
            SafeParcelWriter.writeString(r4, 8, this.addressCity, false);
            SafeParcelWriter.writeString(r4, 9, this.addressState, false);
            SafeParcelWriter.writeString(r4, 10, this.addressZip, false);
            SafeParcelWriter.writeString(r4, 11, this.licenseNumber, false);
            SafeParcelWriter.writeString(r4, 12, this.issueDate, false);
            SafeParcelWriter.writeString(r4, 13, this.expiryDate, false);
            SafeParcelWriter.writeString(r4, 14, this.birthDate, false);
            SafeParcelWriter.writeString(r4, 15, this.issuingCountry, false);
            SafeParcelWriter.finishObjectHeader(r4, r52);
        }
    }

    public static class Email extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<Email> CREATOR = null;

        @RecentlyNonNull
        public String address;

        @RecentlyNonNull
        public String body;

        @RecentlyNonNull
        public String subject;
        public int type;

        static {
            CREATOR = new zzg();
        }

        public Email(int r1, @RecentlyNonNull String r2, @RecentlyNonNull String r3, @RecentlyNonNull String r4) {
            this.type = r1;
            this.address = r2;
            this.subject = r3;
            this.body = r4;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r4, int r5) {
            int r52 = SafeParcelWriter.beginObjectHeader(r4);
            SafeParcelWriter.writeInt(r4, 2, this.type);
            SafeParcelWriter.writeString(r4, 3, this.address, false);
            SafeParcelWriter.writeString(r4, 4, this.subject, false);
            SafeParcelWriter.writeString(r4, 5, this.body, false);
            SafeParcelWriter.finishObjectHeader(r4, r52);
        }
    }

    public static class GeoPoint extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<GeoPoint> CREATOR = null;
        public double lat;
        public double lng;

        static {
            CREATOR = new zzj();
        }

        public GeoPoint(double r1, double r3) {
            this.lat = r1;
            this.lng = r3;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r4, int r5) {
            int r52 = SafeParcelWriter.beginObjectHeader(r4);
            SafeParcelWriter.writeDouble(r4, 2, this.lat);
            SafeParcelWriter.writeDouble(r4, 3, this.lng);
            SafeParcelWriter.finishObjectHeader(r4, r52);
        }
    }

    public static class PersonName extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<PersonName> CREATOR = null;

        @RecentlyNonNull
        public String first;

        @RecentlyNonNull
        public String formattedName;

        @RecentlyNonNull
        public String last;

        @RecentlyNonNull
        public String middle;

        @RecentlyNonNull
        public String prefix;

        @RecentlyNonNull
        public String pronunciation;

        @RecentlyNonNull
        public String suffix;

        static {
            CREATOR = new zzi();
        }

        public PersonName(@RecentlyNonNull String r1, @RecentlyNonNull String r2, @RecentlyNonNull String r3, @RecentlyNonNull String r4, @RecentlyNonNull String r5, @RecentlyNonNull String r6, @RecentlyNonNull String r7) {
            this.formattedName = r1;
            this.pronunciation = r2;
            this.prefix = r3;
            this.first = r4;
            this.middle = r5;
            this.last = r6;
            this.suffix = r7;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r4, int r5) {
            int r52 = SafeParcelWriter.beginObjectHeader(r4);
            SafeParcelWriter.writeString(r4, 2, this.formattedName, false);
            SafeParcelWriter.writeString(r4, 3, this.pronunciation, false);
            SafeParcelWriter.writeString(r4, 4, this.prefix, false);
            SafeParcelWriter.writeString(r4, 5, this.first, false);
            SafeParcelWriter.writeString(r4, 6, this.middle, false);
            SafeParcelWriter.writeString(r4, 7, this.last, false);
            SafeParcelWriter.writeString(r4, 8, this.suffix, false);
            SafeParcelWriter.finishObjectHeader(r4, r52);
        }
    }

    public static class Phone extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<Phone> CREATOR = null;

        @RecentlyNonNull
        public String number;
        public int type;

        static {
            CREATOR = new zzl();
        }

        public Phone(int r1, @RecentlyNonNull String r2) {
            this.type = r1;
            this.number = r2;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r3, int r4) {
            int r42 = SafeParcelWriter.beginObjectHeader(r3);
            SafeParcelWriter.writeInt(r3, 2, this.type);
            SafeParcelWriter.writeString(r3, 3, this.number, false);
            SafeParcelWriter.finishObjectHeader(r3, r42);
        }
    }

    public static class Sms extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<Sms> CREATOR = null;

        @RecentlyNonNull
        public String message;

        @RecentlyNonNull
        public String phoneNumber;

        static {
            CREATOR = new zzk();
        }

        public Sms(@RecentlyNonNull String r1, @RecentlyNonNull String r2) {
            this.message = r1;
            this.phoneNumber = r2;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r4, int r5) {
            int r52 = SafeParcelWriter.beginObjectHeader(r4);
            SafeParcelWriter.writeString(r4, 2, this.message, false);
            SafeParcelWriter.writeString(r4, 3, this.phoneNumber, false);
            SafeParcelWriter.finishObjectHeader(r4, r52);
        }
    }

    public static class UrlBookmark extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<UrlBookmark> CREATOR = null;

        @RecentlyNonNull
        public String title;

        @RecentlyNonNull
        public String url;

        static {
            CREATOR = new zzn();
        }

        public UrlBookmark(@RecentlyNonNull String r1, @RecentlyNonNull String r2) {
            this.title = r1;
            this.url = r2;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r4, int r5) {
            int r52 = SafeParcelWriter.beginObjectHeader(r4);
            SafeParcelWriter.writeString(r4, 2, this.title, false);
            SafeParcelWriter.writeString(r4, 3, this.url, false);
            SafeParcelWriter.finishObjectHeader(r4, r52);
        }
    }

    public static class WiFi extends AbstractSafeParcelable {

        @RecentlyNonNull
        public static final Parcelable.Creator<WiFi> CREATOR = null;
        public int encryptionType;

        @RecentlyNonNull
        public String password;

        @RecentlyNonNull
        public String ssid;

        static {
            CREATOR = new zzm();
        }

        public WiFi(@RecentlyNonNull String r1, @RecentlyNonNull String r2, int r3) {
            this.ssid = r1;
            this.password = r2;
            this.encryptionType = r3;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@RecentlyNonNull Parcel r4, int r5) {
            int r52 = SafeParcelWriter.beginObjectHeader(r4);
            SafeParcelWriter.writeString(r4, 2, this.ssid, false);
            SafeParcelWriter.writeString(r4, 3, this.password, false);
            SafeParcelWriter.writeInt(r4, 4, this.encryptionType);
            SafeParcelWriter.finishObjectHeader(r4, r52);
        }
    }

    static {
        CREATOR = new zzb();
    }

    public Barcode(int r1, @RecentlyNonNull String r2, @RecentlyNonNull String r3, int r4, @RecentlyNonNull Point[] r5, @RecentlyNonNull Email r6, @RecentlyNonNull Phone r7, @RecentlyNonNull Sms r8, @RecentlyNonNull WiFi r9, @RecentlyNonNull UrlBookmark r10, @RecentlyNonNull GeoPoint r11, @RecentlyNonNull CalendarEvent r12, @RecentlyNonNull ContactInfo r13, @RecentlyNonNull DriverLicense r14, @RecentlyNonNull byte[] r15, boolean r16) {
        this.format = r1;
        this.rawValue = r2;
        this.rawBytes = r15;
        this.displayValue = r3;
        this.valueFormat = r4;
        this.cornerPoints = r5;
        this.isRecognized = r16;
        this.email = r6;
        this.phone = r7;
        this.sms = r8;
        this.wifi = r9;
        this.url = r10;
        this.geoPoint = r11;
        this.calendarEvent = r12;
        this.contactInfo = r13;
        this.driverLicense = r14;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@RecentlyNonNull Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 2, this.format);
        SafeParcelWriter.writeString(r5, 3, this.rawValue, false);
        SafeParcelWriter.writeString(r5, 4, this.displayValue, false);
        SafeParcelWriter.writeInt(r5, 5, this.valueFormat);
        SafeParcelWriter.writeTypedArray(r5, 6, this.cornerPoints, r6, false);
        SafeParcelWriter.writeParcelable(r5, 7, this.email, r6, false);
        SafeParcelWriter.writeParcelable(r5, 8, this.phone, r6, false);
        SafeParcelWriter.writeParcelable(r5, 9, this.sms, r6, false);
        SafeParcelWriter.writeParcelable(r5, 10, this.wifi, r6, false);
        SafeParcelWriter.writeParcelable(r5, 11, this.url, r6, false);
        SafeParcelWriter.writeParcelable(r5, 12, this.geoPoint, r6, false);
        SafeParcelWriter.writeParcelable(r5, 13, this.calendarEvent, r6, false);
        SafeParcelWriter.writeParcelable(r5, 14, this.contactInfo, r6, false);
        SafeParcelWriter.writeParcelable(r5, 15, this.driverLicense, r6, false);
        SafeParcelWriter.writeByteArray(r5, 16, this.rawBytes, false);
        SafeParcelWriter.writeBoolean(r5, 17, this.isRecognized);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
