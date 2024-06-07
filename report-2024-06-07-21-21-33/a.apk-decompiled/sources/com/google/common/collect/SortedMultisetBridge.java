package com.google.common.collect;

import com.android.tools.r8.annotations.SynthesizedClass;
import java.util.SortedSet;

@ElementTypesAreNonnullByDefault
/* loaded from: classes.dex */
interface SortedMultisetBridge<E> extends Multiset<E> {
    @Override // com.google.common.collect.Multiset
    SortedSet<E> elementSet();

    @SynthesizedClass(kind = "$-CC")
    /* renamed from: com.google.common.collect.SortedMultisetBridge$-CC, reason: invalid class name */
    /* loaded from: classes.dex */
    public final /* synthetic */ class CC<E> {
    }
}
