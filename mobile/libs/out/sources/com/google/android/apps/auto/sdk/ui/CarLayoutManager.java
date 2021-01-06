package com.google.android.apps.auto.sdk.ui;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.SmoothScroller.Action;
import android.support.v7.widget.RecyclerView.State;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import com.google.android.apps.auto.sdk.ui.PagedListView.OnScrollListener;
import java.util.ArrayList;

public class CarLayoutManager extends LayoutManager {
    public static final int ROW_OFFSET_MODE_INDIVIDUAL = 0;
    public static final int ROW_OFFSET_MODE_PAGE = 1;
    private final AccelerateInterpolator a = new AccelerateInterpolator(2.0f);
    /* access modifiers changed from: private */
    public final Context b;
    private boolean c = false;
    private int d = 1;
    private int e = 0;
    private a f;
    private OnScrollListener g;
    private int h = 0;
    private boolean i;
    private int j = 0;
    private int k = -1;
    private int l = -1;
    private int m = -1;
    private int n = -1;
    private int o = 1;
    private int p = 0;
    private LruCache<View, b> q;
    private int r = -1;

    final class a extends LinearSmoothScroller {
        private final Interpolator a = new DecelerateInterpolator(1.8f);
        private final boolean b;
        private final int c;

        public a(Context context, int i) {
            super(context);
            this.c = i;
            this.b = CarLayoutManager.this.b.getResources().getBoolean(R.bool.car_true_for_touch);
        }

        /* access modifiers changed from: protected */
        public final float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 150.0f / ((float) displayMetrics.densityDpi);
        }

        /* access modifiers changed from: protected */
        public final int calculateTimeForDeceleration(int i) {
            int ceil = (int) Math.ceil((double) (((float) calculateTimeForScrolling(i)) / 0.45f));
            return this.b ? ceil : Math.min(ceil, 1000);
        }

        public final PointF computeScrollVectorForPosition(int i) {
            if (getChildCount() == 0) {
                return null;
            }
            return new PointF(0.0f, (float) (this.c < CarLayoutManager.this.getPosition(CarLayoutManager.this.getChildAt(CarLayoutManager.this.getFirstFullyVisibleChildIndex())) ? -1 : 1));
        }

        public final int getTargetPosition() {
            return this.c;
        }

        /* access modifiers changed from: protected */
        public final int getVerticalSnapPreference() {
            return -1;
        }

        /* access modifiers changed from: protected */
        public final void onTargetFound(View view, State state, Action action) {
            int calculateDyToMakeVisible = calculateDyToMakeVisible(view, -1);
            if (calculateDyToMakeVisible != 0) {
                int calculateTimeForDeceleration = calculateTimeForDeceleration(calculateDyToMakeVisible);
                if (calculateTimeForDeceleration > 0) {
                    action.update(0, -calculateDyToMakeVisible, calculateTimeForDeceleration, this.a);
                }
            } else if (Log.isLoggable("CarLayoutManager", 3)) {
                Log.d("CarLayoutManager", "Scroll distance is 0");
            }
        }
    }

    static final class b extends Animation {
        public float a;

        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            transformation.getMatrix().setTranslate(0.0f, this.a);
        }
    }

    public CarLayoutManager(Context context) {
        this.b = context;
    }

    @VisibleForTesting
    private int a(int i2) {
        if (i2 == -1) {
            return -1;
        }
        View findViewByPosition = findViewByPosition(i2);
        int decoratedTop = getDecoratedTop(findViewByPosition);
        int i3 = findViewByPosition.getLayoutParams().topMargin;
        while (i2 > 0) {
            i2--;
            View findViewByPosition2 = findViewByPosition(i2);
            if (findViewByPosition2 == null) {
                return i2 + 1;
            }
            if (getDecoratedTop(findViewByPosition2) - findViewByPosition2.getLayoutParams().topMargin < (decoratedTop - i3) - getHeight()) {
                return i2 + 1;
            }
        }
        return 0;
    }

    private final View a(Recycler recycler, View view, int i2) {
        int decoratedBottom;
        int decoratedMeasuredHeight;
        int position = getPosition(view);
        if (i2 == 0) {
            position--;
        } else if (i2 == 1) {
            position++;
        }
        View viewForPosition = recycler.getViewForPosition(position);
        measureChildWithMargins(viewForPosition, 0, 0);
        LayoutParams layoutParams = viewForPosition.getLayoutParams();
        LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
        int paddingLeft = getPaddingLeft() + layoutParams.leftMargin;
        int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
        if (i2 == 0) {
            decoratedMeasuredHeight = (view.getTop() - layoutParams2.topMargin) - layoutParams.bottomMargin;
            decoratedBottom = decoratedMeasuredHeight - getDecoratedMeasuredHeight(viewForPosition);
        } else {
            decoratedBottom = layoutParams2.bottomMargin + getDecoratedBottom(view) + layoutParams.topMargin;
            decoratedMeasuredHeight = decoratedBottom + getDecoratedMeasuredHeight(viewForPosition);
        }
        layoutDecorated(viewForPosition, paddingLeft, decoratedBottom, decoratedMeasuredWidth + paddingLeft, decoratedMeasuredHeight);
        if (i2 == 0) {
            addView(viewForPosition, 0);
        } else {
            addView(viewForPosition);
        }
        return viewForPosition;
    }

    private final void a() {
        if (getChildCount() != 0) {
            if (Log.isLoggable("CarLayoutManager", 2)) {
                Log.v("CarLayoutManager", String.format(":: #BEFORE updatePageBreakPositions mAnchorPageBreakPosition:%s, mUpperPageBreakPosition:%s, mLowerPageBreakPosition:%s", new Object[]{Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l)}));
            }
            this.j = getPosition(getFirstFullyVisibleChild());
            if (this.j == -1) {
                Log.w("CarLayoutManager", "Unable to update anchor positions. There is no anchor position.");
                return;
            }
            View findViewByPosition = findViewByPosition(this.j);
            if (findViewByPosition != null) {
                int i2 = findViewByPosition.getLayoutParams().topMargin;
                int decoratedTop = getDecoratedTop(findViewByPosition) - i2;
                View findViewByPosition2 = findViewByPosition(this.k);
                int decoratedTop2 = findViewByPosition2 == null ? Integer.MIN_VALUE : getDecoratedTop(findViewByPosition2) - findViewByPosition2.getLayoutParams().topMargin;
                if (Log.isLoggable("CarLayoutManager", 2)) {
                    Log.v("CarLayoutManager", String.format(":: #MID updatePageBreakPositions topMargin:%s, anchorTop:%s mAnchorPageBreakPosition:%s, mUpperPageBreakPosition:%s, mLowerPageBreakPosition:%s", new Object[]{Integer.valueOf(i2), Integer.valueOf(decoratedTop), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l)}));
                }
                if (decoratedTop < getPaddingTop()) {
                    this.k = this.j;
                    this.j = this.l;
                    this.l = b(this.j);
                } else if (this.j <= 0 || decoratedTop2 < getPaddingTop()) {
                    this.k = a(this.j);
                    this.l = b(this.j);
                } else {
                    this.l = this.j;
                    this.j = this.k;
                    this.k = a(this.j);
                }
                if (Log.isLoggable("CarLayoutManager", 2)) {
                    Log.v("CarLayoutManager", String.format(":: #AFTER updatePageBreakPositions mAnchorPageBreakPosition:%s, mUpperPageBreakPosition:%s, mLowerPageBreakPosition:%s", new Object[]{Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l)}));
                }
            }
        } else if (Log.isLoggable("CarLayoutManager", 3)) {
            Log.d("CarLayoutManager", ":: updatePageBreakPosition getChildCount: 0");
        }
    }

    private final void a(View view, float f2) {
        b bVar = (b) this.q.get(view);
        if (bVar == null) {
            bVar = new b(0);
            bVar.setFillEnabled(true);
            bVar.setFillAfter(true);
            bVar.setDuration(0);
            this.q.put(view, bVar);
        } else if (bVar.a == f2) {
            return;
        }
        bVar.reset();
        bVar.a = f2;
        bVar.setStartTime(-1);
        view.setAnimation(bVar);
        bVar.startNow();
    }

    private final boolean a(State state, View view, int i2) {
        int position = getPosition(view);
        if (i2 == 0) {
            if (position == 0) {
                return false;
            }
        } else if (i2 == 1 && position >= state.getItemCount() - 1) {
            return false;
        }
        if (this.f != null) {
            if (i2 == 0 && position >= this.f.getTargetPosition()) {
                return true;
            }
            if (i2 == 1 && position <= this.f.getTargetPosition()) {
                return true;
            }
        }
        View focusedChild = getFocusedChild();
        if (focusedChild != null) {
            int position2 = getPosition(focusedChild);
            if (i2 == 0 && position >= position2 - 2) {
                return true;
            }
            if (i2 == 1 && position <= position2 + 2) {
                return true;
            }
        }
        LayoutParams layoutParams = view.getLayoutParams();
        int decoratedTop = getDecoratedTop(view);
        int i3 = layoutParams.topMargin;
        int decoratedBottom = getDecoratedBottom(view);
        int i4 = layoutParams.bottomMargin;
        if (i2 != 0 || decoratedTop - i3 >= getPaddingTop() - getHeight()) {
            return i2 != 1 || decoratedBottom - i4 <= getHeight() - getPaddingBottom();
        }
        return false;
    }

    private final int b() {
        if (this.n != -1) {
            return this.n;
        }
        int firstFullyVisibleChildIndex = getFirstFullyVisibleChildIndex();
        View childAt = getChildAt(firstFullyVisibleChildIndex);
        View view = (getPosition(childAt) != 0 || firstFullyVisibleChildIndex >= getChildCount() + -1) ? childAt : getChildAt(firstFullyVisibleChildIndex + 1);
        LayoutParams layoutParams = view.getLayoutParams();
        int decoratedMeasuredHeight = layoutParams.bottomMargin + getDecoratedMeasuredHeight(view) + layoutParams.topMargin;
        if (decoratedMeasuredHeight == 0) {
            Log.w("CarLayoutManager", "The sample view has a height of 0. Returning a dummy value for now that won't be cached.");
            return this.b.getResources().getDimensionPixelSize(R.dimen.car_sample_row_height);
        }
        this.n = decoratedMeasuredHeight;
        return decoratedMeasuredHeight;
    }

    @VisibleForTesting
    private int b(int i2) {
        if (i2 == -1) {
            return -1;
        }
        View findViewByPosition = findViewByPosition(i2);
        if (findViewByPosition == null) {
            return i2;
        }
        int decoratedTop = getDecoratedTop(findViewByPosition);
        int i3 = findViewByPosition.getLayoutParams().topMargin;
        int i4 = i2;
        while (i4 < getItemCount() - 1) {
            int i5 = i4 + 1;
            View findViewByPosition2 = findViewByPosition(i5);
            if (findViewByPosition2 == null) {
                return i5 - 1;
            }
            if (getDecoratedTop(findViewByPosition2) - findViewByPosition2.getLayoutParams().topMargin > getHeight() + (decoratedTop - i3)) {
                return i5 + -1 == i2 ? i5 : i5 - 1;
            }
            i4 = i5;
        }
        return i4;
    }

    private final int c() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    public boolean canScrollVertically() {
        return true;
    }

    public int computeVerticalScrollExtent(State state) {
        if (getChildCount() <= 1) {
            return 0;
        }
        int c2 = c() / b();
        if (state.getItemCount() <= c2) {
            return 1000;
        }
        return (c2 * 1000) / state.getItemCount();
    }

    public int computeVerticalScrollOffset(State state) {
        View firstFullyVisibleChild = getFirstFullyVisibleChild();
        if (firstFullyVisibleChild == null) {
            return 0;
        }
        LayoutParams layoutParams = firstFullyVisibleChild.getLayoutParams();
        int position = getPosition(firstFullyVisibleChild);
        float f2 = (float) position;
        float min = f2 - Math.min(((float) (getDecoratedTop(firstFullyVisibleChild) - layoutParams.topMargin)) / ((float) (layoutParams.bottomMargin + (getDecoratedMeasuredHeight(firstFullyVisibleChild) + layoutParams.topMargin))), 1.0f);
        int itemCount = state.getItemCount() - (c() / b());
        if (itemCount <= 0) {
            return 0;
        }
        if (min >= ((float) itemCount)) {
            return 1000;
        }
        return (int) ((min * 1000.0f) / ((float) itemCount));
    }

    public int computeVerticalScrollRange(State state) {
        return 1000;
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    public View getFirstFullyVisibleChild() {
        int firstFullyVisibleChildIndex = getFirstFullyVisibleChildIndex();
        if (firstFullyVisibleChildIndex != -1) {
            return getChildAt(firstFullyVisibleChildIndex);
        }
        return null;
    }

    public int getFirstFullyVisibleChildIndex() {
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= getChildCount()) {
                return -1;
            }
            View childAt = getChildAt(i3);
            if (getDecoratedTop(childAt) - childAt.getLayoutParams().topMargin >= getPaddingTop()) {
                return i3;
            }
            i2 = i3 + 1;
        }
    }

    public int getFirstFullyVisibleChildPosition() {
        View firstFullyVisibleChild = getFirstFullyVisibleChild();
        if (firstFullyVisibleChild == null) {
            return -1;
        }
        return getPosition(firstFullyVisibleChild);
    }

    public int getLastFocusedChildIndexIfVisible() {
        if (this.m == -1) {
            return -1;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= getChildCount()) {
                break;
            }
            View childAt = getChildAt(i2);
            if (getPosition(childAt) == this.m) {
                LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams.bottomMargin + getDecoratedBottom(childAt) <= getHeight() - getPaddingBottom()) {
                    return i2;
                }
            } else {
                i2++;
            }
        }
        return -1;
    }

    public View getLastFullyVisibleChild() {
        int lastFullyVisibleChildIndex = getLastFullyVisibleChildIndex();
        if (lastFullyVisibleChildIndex != -1) {
            return getChildAt(lastFullyVisibleChildIndex);
        }
        return null;
    }

    public int getLastFullyVisibleChildIndex() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams.bottomMargin + getDecoratedBottom(childAt) <= getHeight() - getPaddingBottom()) {
                return childCount;
            }
        }
        return -1;
    }

    public int getLastFullyVisibleChildPosition() {
        View lastFullyVisibleChild = getLastFullyVisibleChild();
        if (lastFullyVisibleChild == null) {
            return -1;
        }
        return getPosition(lastFullyVisibleChild);
    }

    public int getPageDownPosition() {
        return this.l;
    }

    public int getPageUpPosition() {
        return this.k;
    }

    public boolean isAtBottom() {
        int lastFullyVisibleChildIndex = getLastFullyVisibleChildIndex();
        return lastFullyVisibleChildIndex == -1 || getPosition(getChildAt(lastFullyVisibleChildIndex)) == getItemCount() + -1;
    }

    public boolean isAtTop() {
        return getFirstFullyVisibleChildIndex() <= 0;
    }

    public void offsetRows() {
        int i2;
        if (this.c) {
            if (this.d == 1) {
                View findViewByPosition = findViewByPosition(this.j);
                if (findViewByPosition != null) {
                    int decoratedTop = getDecoratedTop(findViewByPosition) - findViewByPosition.getLayoutParams().topMargin;
                    View findViewByPosition2 = findViewByPosition(this.k);
                    int decoratedTop2 = (getDecoratedTop(findViewByPosition2) - findViewByPosition2.getLayoutParams().topMargin) - decoratedTop;
                    int paddingTop = decoratedTop - getPaddingTop();
                    float abs = ((float) (Math.abs(decoratedTop2) - paddingTop)) / ((float) Math.abs(decoratedTop2));
                    if (Log.isLoggable("CarLayoutManager", 2)) {
                        Log.v("CarLayoutManager", String.format(":: offsetRowsByPage scrollDistance:%s, distanceLeft:%s, scrollPercentage:%s", new Object[]{Integer.valueOf(decoratedTop2), Integer.valueOf(paddingTop), Float.valueOf(abs)}));
                    }
                    RecyclerView parent = getChildAt(0).getParent();
                    int[] iArr = new int[2];
                    parent.getLocationInWindow(iArr);
                    int paddingTop2 = iArr[1] + parent.getPaddingTop();
                    int childCount = getChildCount();
                    for (int i3 = 0; i3 < childCount; i3++) {
                        View childAt = getChildAt(i3);
                        int position = getPosition(childAt);
                        if (position < this.k) {
                            childAt.setAlpha(0.0f);
                            a(childAt, (float) (-paddingTop2));
                        } else if (position < this.j) {
                            LayoutParams layoutParams = childAt.getLayoutParams();
                            int i4 = layoutParams.topMargin < 0 ? 0 - layoutParams.topMargin : 0;
                            if (layoutParams.bottomMargin < 0) {
                                i4 -= layoutParams.bottomMargin;
                            }
                            int interpolation = (int) (((float) (paddingTop2 + i4)) * this.a.getInterpolation(abs));
                            childAt.setAlpha(1.0f);
                            a(childAt, (float) (-interpolation));
                        } else {
                            childAt.setAlpha(1.0f);
                            a(childAt, 0.0f);
                        }
                    }
                } else if (Log.isLoggable("CarLayoutManager", 3)) {
                    Log.d("CarLayoutManager", ":: offsetRowsByPage anchorView null");
                }
            } else if (this.d != 0) {
            } else {
                if (getChildCount() != 0) {
                    int childCount2 = getChildCount() - 1;
                    while (true) {
                        if (childCount2 < 0) {
                            i2 = -1;
                            break;
                        }
                        View childAt2 = getChildAt(childCount2);
                        if (getDecoratedTop(childAt2) - childAt2.getLayoutParams().topMargin <= getPaddingTop()) {
                            i2 = childCount2;
                            break;
                        }
                        childCount2--;
                    }
                    this.j = i2;
                    if (Log.isLoggable("CarLayoutManager", 2)) {
                        Log.v("CarLayoutManager", ":: offsetRowsIndividually danglingChildIndex: " + i2);
                    }
                    RecyclerView parent2 = getChildAt(0).getParent();
                    int[] iArr2 = new int[2];
                    parent2.getLocationInWindow(iArr2);
                    int paddingTop3 = iArr2[1] + parent2.getPaddingTop();
                    int childCount3 = getChildCount();
                    for (int i5 = 0; i5 < childCount3; i5++) {
                        View childAt3 = getChildAt(i5);
                        LayoutParams layoutParams2 = childAt3.getLayoutParams();
                        int i6 = layoutParams2.topMargin < 0 ? paddingTop3 - layoutParams2.topMargin : paddingTop3;
                        if (layoutParams2.bottomMargin < 0) {
                            i6 -= layoutParams2.bottomMargin;
                        }
                        if (i5 < i2) {
                            childAt3.setAlpha(0.0f);
                        } else if (i5 > i2) {
                            childAt3.setAlpha(1.0f);
                            a(childAt3, 0.0f);
                        } else {
                            float interpolation2 = this.a.getInterpolation(1.0f - (((float) ((layoutParams2.bottomMargin + getDecoratedBottom(childAt3)) - getPaddingTop())) / ((float) ((getDecoratedMeasuredHeight(childAt3) + layoutParams2.topMargin) + layoutParams2.bottomMargin))));
                            childAt3.setAlpha(1.0f);
                            a(childAt3, -(interpolation2 * ((float) i6)));
                        }
                    }
                } else if (Log.isLoggable("CarLayoutManager", 3)) {
                    Log.d("CarLayoutManager", ":: offsetRowsIndividually getChildCount=0");
                }
            }
        }
    }

    public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i2, int i3) {
        if (getFocusedChild() != null) {
            return false;
        }
        int firstFullyVisibleChildIndex = getFirstFullyVisibleChildIndex();
        if (firstFullyVisibleChildIndex == -1) {
            Log.w("CarLayoutManager", "There is a focused child but no first fully visible child.");
            return false;
        }
        if (getPosition(getChildAt(firstFullyVisibleChildIndex)) > 0 && firstFullyVisibleChildIndex + 1 < getItemCount()) {
            firstFullyVisibleChildIndex++;
        }
        if (i2 == 2) {
            while (firstFullyVisibleChildIndex < getChildCount()) {
                arrayList.add(getChildAt(firstFullyVisibleChildIndex));
                firstFullyVisibleChildIndex++;
            }
            return true;
        } else if (i2 == 1) {
            while (firstFullyVisibleChildIndex >= 0) {
                arrayList.add(getChildAt(firstFullyVisibleChildIndex));
                firstFullyVisibleChildIndex--;
            }
            return true;
        } else {
            if (i2 == 130) {
                int lastFocusedChildIndexIfVisible = getLastFocusedChildIndexIfVisible();
                if (lastFocusedChildIndexIfVisible != -1) {
                    arrayList.add(getChildAt(lastFocusedChildIndexIfVisible));
                    return true;
                }
            }
            return false;
        }
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        CarLayoutManager.super.onAttachedToWindow(recyclerView);
        a();
        offsetRows();
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, Recycler recycler) {
        CarLayoutManager.super.onDetachedFromWindow(recyclerView, recycler);
    }

    public View onFocusSearchFailed(View view, int i2, Recycler recycler, State state) {
        return null;
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        CarLayoutManager.super.onItemsChanged(recyclerView);
        this.n = -1;
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        int i2;
        int i3;
        if (this.r == -1) {
            View firstFullyVisibleChild = getFirstFullyVisibleChild();
            if (firstFullyVisibleChild != null) {
                i2 = getPosition(firstFullyVisibleChild);
                i3 = getDecoratedTop(firstFullyVisibleChild);
            } else {
                i3 = -1;
                i2 = 0;
            }
        } else {
            i2 = this.r;
            this.r = -1;
            this.j = i2;
            this.k = -1;
            this.l = -1;
            i3 = -1;
        }
        if (Log.isLoggable("CarLayoutManager", 2)) {
            Log.v("CarLayoutManager", String.format(":: onLayoutChildren anchorPosition:%s, anchorTop:%s, mPendingScrollPosition: %s, mAnchorPageBreakPosition:%s, mUpperPageBreakPosition:%s, mLowerPageBreakPosition:%s", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(this.r), Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l)}));
        }
        detachAndScrapAttachedViews(recycler);
        int min = Math.min(i2, getItemCount() - 1);
        if (min >= 0) {
            View viewForPosition = recycler.getViewForPosition(min);
            LayoutParams layoutParams = viewForPosition.getLayoutParams();
            measureChildWithMargins(viewForPosition, 0, 0);
            int paddingLeft = getPaddingLeft() + layoutParams.leftMargin;
            if (i3 == -1) {
                i3 = layoutParams.topMargin;
            }
            layoutDecorated(viewForPosition, paddingLeft, i3, paddingLeft + getDecoratedMeasuredWidth(viewForPosition), i3 + getDecoratedMeasuredHeight(viewForPosition));
            addView(viewForPosition);
            View view = viewForPosition;
            while (a(state, view, 0)) {
                view = a(recycler, view, 0);
            }
            while (a(state, viewForPosition, 1)) {
                viewForPosition = a(recycler, viewForPosition, 1);
            }
        }
        a();
        offsetRows();
        if (Log.isLoggable("CarLayoutManager", 2) && getChildCount() > 1) {
            int childCount = getChildCount();
            int position = getPosition(getChildAt(0));
            Log.v("CarLayoutManager", "Currently showing " + childCount + " views " + position + " to " + getPosition(getChildAt(getChildCount() - 1)) + " anchor " + min);
        }
        this.o = Math.max((getLastFullyVisibleChildIndex() + 1) - getFirstFullyVisibleChildIndex(), 1);
        this.p = getFirstFullyVisibleChildPosition() / this.o;
        Log.v("CarLayoutManager", "viewsPerPage " + this.o);
    }

    public boolean onRequestChildFocus(RecyclerView recyclerView, State state, View view, View view2) {
        View childAt;
        if (view == null) {
            Log.w("CarLayoutManager", "onRequestChildFocus with a null child!");
        } else {
            if (Log.isLoggable("CarLayoutManager", 2)) {
                Log.v("CarLayoutManager", String.format(":: onRequestChildFocus child: %s, focused: %s", new Object[]{view, view2}));
            }
            int position = getPosition(view);
            if (position != this.m) {
                this.m = position;
                int c2 = c();
                int decoratedTop = getDecoratedTop(view);
                int decoratedBottom = getDecoratedBottom(view);
                int indexOfChild = recyclerView.indexOfChild(view);
                while (true) {
                    if (indexOfChild < 0) {
                        break;
                    }
                    childAt = getChildAt(indexOfChild);
                    if (childAt == null) {
                        Log.e("CarLayoutManager", "Child is null at index " + indexOfChild);
                    } else if (indexOfChild == 0) {
                        recyclerView.smoothScrollToPosition(getPosition(childAt));
                        break;
                    } else {
                        View childAt2 = getChildAt(indexOfChild - 1);
                        if (childAt2 != null) {
                            int decoratedTop2 = getDecoratedTop(childAt2);
                            int decoratedTop3 = getDecoratedTop(childAt2);
                            if (decoratedTop - decoratedTop2 > c2 / 2 || decoratedBottom - decoratedTop3 > c2) {
                                recyclerView.smoothScrollToPosition(getPosition(childAt));
                            }
                        } else {
                            continue;
                        }
                    }
                    indexOfChild--;
                }
                recyclerView.smoothScrollToPosition(getPosition(childAt));
            }
        }
        return true;
    }

    public void onScrollStateChanged(int i2) {
        if (Log.isLoggable("CarLayoutManager", 2)) {
            Log.v("CarLayoutManager", ":: onScrollStateChanged " + i2);
        }
        if (i2 == 0) {
            View focusedChild = getFocusedChild();
            if (focusedChild != null && (getDecoratedTop(focusedChild) >= getHeight() - getPaddingBottom() || getDecoratedBottom(focusedChild) <= getPaddingTop())) {
                focusedChild.clearFocus();
                requestLayout();
            }
        } else if (i2 == 1) {
            this.h = 0;
        }
        if (i2 != 2) {
            this.f = null;
        }
        this.e = i2;
        a();
    }

    public void scrollToPosition(int i2) {
        this.r = i2;
        requestLayout();
    }

    public int scrollVerticallyBy(int i2, @NonNull Recycler recycler, @NonNull State state) {
        if (getItemCount() == 0) {
            return i2;
        }
        if (getChildCount() <= 1 || i2 == 0) {
            this.i = true;
            return 0;
        }
        View childAt = getChildAt(0);
        if (childAt == null) {
            this.i = true;
            return 0;
        }
        int position = getPosition(childAt);
        int decoratedTop = getDecoratedTop(childAt) - childAt.getLayoutParams().topMargin;
        View childAt2 = getChildAt(getLastFullyVisibleChildIndex());
        if (childAt2 == null) {
            this.i = true;
            return 0;
        }
        boolean z = getPosition(childAt2) == getItemCount() + -1;
        View firstFullyVisibleChild = getFirstFullyVisibleChild();
        if (firstFullyVisibleChild == null) {
            this.i = true;
            return 0;
        }
        int position2 = getPosition(firstFullyVisibleChild);
        int decoratedTop2 = (getDecoratedTop(firstFullyVisibleChild) - firstFullyVisibleChild.getLayoutParams().topMargin) - getPaddingTop();
        if (z && position2 == this.j && i2 > decoratedTop2 && i2 > 0) {
            this.i = true;
            i2 = decoratedTop2;
        } else if (i2 >= 0 || position != 0 || Math.abs(i2) + decoratedTop <= getPaddingTop()) {
            this.i = false;
        } else {
            i2 = decoratedTop - getPaddingTop();
            this.i = true;
        }
        if (this.e == 1) {
            this.h += i2;
        }
        offsetChildrenVertical(-i2);
        View childAt3 = getChildAt(getChildCount() - 1);
        if (childAt3.getTop() < 0) {
            childAt3.setTop(0);
        }
        if (i2 > 0) {
            int paddingTop = getPaddingTop();
            int height = getHeight();
            int i3 = Integer.MAX_VALUE;
            View focusedChild = getFocusedChild();
            if (focusedChild != null) {
                i3 = getPosition(focusedChild);
            }
            int childCount = getChildCount();
            int i4 = 0;
            int i5 = 0;
            while (i4 < childCount) {
                View childAt4 = getChildAt(i4);
                int decoratedBottom = getDecoratedBottom(childAt4);
                int position3 = getPosition(childAt4);
                if (decoratedBottom >= paddingTop - height || position3 >= i3 - 1) {
                    break;
                }
                i4++;
                i5++;
            }
            int i6 = i5;
            while (true) {
                i6--;
                if (i6 < 0) {
                    break;
                }
                removeAndRecycleView(getChildAt(0), recycler);
            }
            View childAt5 = getChildAt(getChildCount() - 1);
            while (a(state, childAt5, 1)) {
                childAt5 = a(recycler, childAt5, 1);
            }
        } else {
            int height2 = getHeight();
            int i7 = -2147483647;
            View focusedChild2 = getFocusedChild();
            if (focusedChild2 != null) {
                i7 = getPosition(focusedChild2);
            }
            int i8 = 0;
            int i9 = 0;
            for (int childCount2 = getChildCount() - 1; childCount2 >= 0; childCount2--) {
                View childAt6 = getChildAt(childCount2);
                int decoratedTop3 = getDecoratedTop(childAt6);
                int position4 = getPosition(childAt6);
                if (decoratedTop3 <= height2 || position4 <= i7 - 1) {
                    break;
                }
                i9++;
                i8 = childCount2;
            }
            int i10 = i9;
            while (true) {
                i10--;
                if (i10 < 0) {
                    break;
                }
                removeAndRecycleView(getChildAt(i8), recycler);
            }
            View childAt7 = getChildAt(0);
            while (a(state, childAt7, 0)) {
                childAt7 = a(recycler, childAt7, 0);
            }
        }
        a();
        offsetRows();
        if (getChildCount() > 1 && Log.isLoggable("CarLayoutManager", 2)) {
            Log.v("CarLayoutManager", String.format("Currently showing  %d views (%d to %d)", new Object[]{Integer.valueOf(getChildCount()), Integer.valueOf(getPosition(getChildAt(0))), Integer.valueOf(getPosition(getChildAt(getChildCount() - 1)))}));
        }
        int firstFullyVisibleChildPosition = getFirstFullyVisibleChildPosition() / this.o;
        if (this.g != null) {
            if (firstFullyVisibleChildPosition > this.p) {
                this.g.onPageDown();
            } else if (firstFullyVisibleChildPosition < this.p) {
                this.g.onPageUp();
            }
        }
        this.p = firstFullyVisibleChildPosition;
        return i2;
    }

    public void setOffsetRows(boolean z) {
        this.c = z;
        if (z) {
            if (this.q == null) {
                this.q = new LruCache<>(30);
            }
            offsetRows();
            return;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            a(getChildAt(i2), 0.0f);
        }
        this.q = null;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.g = onScrollListener;
    }

    public void setRowOffsetMode(int i2) {
        if (i2 != this.d) {
            this.d = i2;
            offsetRows();
        }
    }

    public boolean settleScrollForFling(RecyclerView recyclerView, int i2) {
        if (getChildCount() == 0 || this.i) {
            return false;
        }
        if (Math.abs(i2) < 0 || Math.abs(this.h) < 0) {
            int firstFullyVisibleChildIndex = getFirstFullyVisibleChildIndex();
            if (firstFullyVisibleChildIndex == -1) {
                return false;
            }
            recyclerView.smoothScrollToPosition(getPosition(getChildAt(firstFullyVisibleChildIndex)));
            return true;
        }
        boolean z = i2 > 0 || (i2 == 0 && this.h >= 0);
        boolean z2 = i2 < 0 || (i2 == 0 && this.h < 0);
        if (z && this.l != -1) {
            recyclerView.smoothScrollToPosition(this.j);
            if (this.g != null) {
                this.g.onGestureDown();
            }
            return true;
        } else if (!z2 || this.k == -1) {
            Log.e("CarLayoutManager", "Error setting scroll for fling! flingVelocity: \t" + i2 + "\tlastDragDistance: " + this.h + "\tpageUpAtStartOfDrag: " + this.k + "\tpageDownAtStartOfDrag: " + this.l);
            if (this.f == null) {
                return false;
            }
            recyclerView.smoothScrollToPosition(this.f.getTargetPosition());
            return true;
        } else {
            recyclerView.smoothScrollToPosition(this.k);
            if (this.g != null) {
                this.g.onGestureUp();
            }
            return true;
        }
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, State state, int i2) {
        this.f = new a(this.b, i2);
        this.f.setTargetPosition(i2);
        startSmoothScroll(this.f);
    }
}
