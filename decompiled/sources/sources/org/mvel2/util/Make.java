package org.mvel2.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes5.dex */
public class Make {

    public static class List<V> {
        private java.util.List<V> listInstance;

        public static <V> List<V> $() {
            return start();
        }

        public List(java.util.List<V> r1) {
            this.listInstance = r1;
        }

        public static <V> List<V> start() {
            return new List(new ArrayList());
        }

        public static <V> List<V> start(Class<? extends java.util.List> r1) {
            return new List(r1.newInstance());
        L4:
            th = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("error creating instance", th);
            return null;
        }

        public List<V> _add(V r2) {
            this.listInstance.add(r2);
            return this;
        }

        public java.util.List<V> _finish() {
            return finish();
        }

        public java.util.List<V> finish() {
            return this.listInstance;
        }
    }

    public static class Map<K, V> {
        private java.util.Map<K, V> mapInstance;

        public static <K, V> Map<K, V> $() {
            return start();
        }

        private Map(java.util.Map<K, V> r1) {
            this.mapInstance = r1;
        }

        public static <K, V> Map<K, V> start() {
            return new Map(new HashMap());
        }

        public static <K, V> Map<K, V> start(Class<? extends java.util.Map> r1) {
            return new Map(r1.newInstance());
        L4:
            th = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("error creating instance", th);
            return null;
        }

        public java.util.Map<K, V> _finish() {
            return finish();
        }

        public Map<K, V> _put(K r2, V r3) {
            this.mapInstance.put(r2, r3);
            return this;
        }

        public java.util.Map<K, V> finish() {
            return this.mapInstance;
        }
    }

    public static class Set<V> {
        private java.util.Set<V> listInstance;

        public static <V> Set<V> $() {
            return start();
        }

        public Set(java.util.Set<V> r1) {
            this.listInstance = r1;
        }

        public static <V> Set<V> start() {
            return new Set(new HashSet());
        }

        public static <V> Set<V> start(Class<? extends java.util.Set> r1) {
            return new Set(r1.newInstance());
        L4:
            th = move-exception;
            Make$Map$$ExternalSyntheticBUOutline0.m("error creating instance", th);
            return null;
        }

        /* renamed from: _, reason: not valid java name */
        public Set<V> m3845_(V r2) {
            this.listInstance.add(r2);
            return this;
        }

        public java.util.Set<V> _finish() {
            return finish();
        }

        public java.util.Set<V> finish() {
            return this.listInstance;
        }
    }

    public static class String {
        private StringBuilder stringAppender;

        public static String $() {
            return start();
        }

        public String(StringBuilder r1) {
            this.stringAppender = r1;
        }

        public static String start() {
            return new String(new StringBuilder());
        }

        public String _append(char r2) {
            this.stringAppender.append(r2);
            return this;
        }

        public String _append(CharSequence r2) {
            this.stringAppender.append(r2);
            return this;
        }

        public String _append(String r2) {
            this.stringAppender.append(r2);
            return this;
        }

        public java.lang.String _finish() {
            return finish();
        }

        public java.lang.String finish() {
            return this.stringAppender.toString();
        }
    }

    public Make() {
    }
}
