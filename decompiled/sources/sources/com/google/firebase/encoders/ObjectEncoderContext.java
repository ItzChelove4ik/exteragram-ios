package com.google.firebase.encoders;

/* loaded from: classes.dex */
public interface ObjectEncoderContext {
    ObjectEncoderContext add(FieldDescriptor r1, double r2);

    ObjectEncoderContext add(FieldDescriptor r1, int r2);

    ObjectEncoderContext add(FieldDescriptor r1, long r2);

    ObjectEncoderContext add(FieldDescriptor r1, Object r2);

    ObjectEncoderContext add(FieldDescriptor r1, boolean r2);
}
