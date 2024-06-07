package com.google.common.hash;

@ElementTypesAreNonnullByDefault
/* loaded from: classes.dex */
interface LongAddable {
    void add(long j);

    void increment();

    long sum();
}
