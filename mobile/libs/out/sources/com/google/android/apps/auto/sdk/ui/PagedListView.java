package com.google.android.apps.auto.sdk.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;

public class PagedListView extends FrameLayout {
    public static final int DEFAULT_MAX_CLICKS = 6;
    protected static final int PAGINATION_HOLD_DELAY_MS = 400;
    private final boolean a;
    private final boolean b;
    /* access modifiers changed from: private */
    public final PagedScrollBarView c;
    private int d;
    private int e;
    private Decoration f;
    private int g;
    private int h;
    private boolean i;
    private float j;
    private float k;
    private boolean l;
    private boolean m;
    protected Adapter<? extends ViewHolder> mAdapter;
    protected final Handler mHandler;
    protected final CarLayoutManager mLayoutManager;
    protected OnScrollListener mOnScrollListener;
    protected final Runnable mPaginationRunnable;
    protected final CarRecyclerView mRecyclerView;
    private final android.support.v7.widget.RecyclerView.OnScrollListener n;
    private final Runnable o;

    public static class Decoration extends ItemDecoration {
        private final boolean a;
        protected final Context mContext;
        protected final int mDividerHeight;
        protected final Paint mPaint;

        public Decoration(Context context) {
            this(context, true);
        }

        public Decoration(Context context, boolean z) {
            this.mContext = context;
            this.a = z;
            this.mPaint = new Paint();
            updateDividerColor();
            this.mDividerHeight = this.mContext.getResources().getDimensionPixelSize(R.dimen.car_divider_height);
        }

        private final TextView a(View view) {
            if (view == null) {
                return null;
            }
            if (view instanceof TextView) {
                return (TextView) view;
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    TextView a2 = a(viewGroup.getChildAt(i));
                    if (a2 != null) {
                        return a2;
                    }
                }
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public int getLeft(View view) {
            if (view == null) {
                return 0;
            }
            TextView a2 = a(view);
            View view2 = a2 == null ? view : a2;
            int i = 0;
            while (view2 != null && view2 != view) {
                i += view2.getLeft();
                view2 = (View) view2.getParent();
            }
            return i;
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, State state) {
            int left = getLeft(recyclerView.getChildAt(0));
            int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            if (this.a) {
                canvas.drawRect((float) left, 0.0f, (float) width, (float) this.mDividerHeight, this.mPaint);
            }
            int childCount = recyclerView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = recyclerView.getChildAt(i);
                int bottom = childAt.getBottom() - childAt.getLayoutParams().bottomMargin;
                int i2 = bottom - this.mDividerHeight;
                if (i2 > 0) {
                    canvas.drawRect((float) left, (float) i2, (float) width, (float) bottom, this.mPaint);
                }
            }
        }

        public void updateDividerColor() {
            this.mPaint.setColor(this.mContext.getResources().getColor(R.color.car_list_divider));
        }
    }

    public interface ItemCap {
        void setMaxItems(int i);
    }

    public interface ItemPositionOffset {
        void setPositionOffset(int i);
    }

    public static class OnScrollListener {
        public void onGestureDown() {
        }

        public void onGestureUp() {
        }

        public void onLeaveBottom() {
        }

        public void onPageDown() {
        }

        public void onPageUp() {
        }

        public void onReachBottom() {
        }

        public void onScrollDownButtonClicked() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        }

        public void onScrollUpButtonClicked() {
        }

        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        }
    }

    public PagedListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public PagedListView(Context context, AttributeSet attributeSet, int i2) {
        this(context, attributeSet, i2, 0);
    }

    public PagedListView(Context context, AttributeSet attributeSet, int i2, int i3) {
        this(context, attributeSet, i2, i3, 0);
    }

    public PagedListView(Context context, AttributeSet attributeSet, int i2, int i3, int i4) {
        super(context, attributeSet, i2, i3);
        this.mHandler = new Handler();
        this.d = -1;
        this.e = -1;
        this.f = new Decoration(getContext());
        this.g = 6;
        this.h = 0;
        this.n = new f(this);
        this.mPaginationRunnable = new g(this);
        this.o = new h(this);
        if (i4 == 0) {
            i4 = R.layout.car_paged_recycler_view;
        }
        LayoutInflater.from(context).inflate(i4, this, true);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.max_width_layout);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PagedListView, i2, i3);
        this.mRecyclerView = (CarRecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerView.setFadeLastItem(obtainStyledAttributes.getBoolean(R.styleable.PagedListView_fadeLastItem, false));
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.PagedListView_offsetRows, false);
        this.e = getDefaultMaxPages();
        this.mLayoutManager = new CarLayoutManager(context);
        this.mLayoutManager.setOffsetRows(z);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.addItemDecoration(this.f);
        this.mRecyclerView.setOnScrollListener(this.n);
        this.mRecyclerView.getRecycledViewPool().setMaxRecycledViews(0, 12);
        this.mRecyclerView.setItemAnimator(new a(this.mLayoutManager));
        setClickable(true);
        setFocusable(false);
        this.a = obtainStyledAttributes.getBoolean(R.styleable.PagedListView_scrollBarEnabled, true);
        this.c = (PagedScrollBarView) findViewById(R.id.paged_scroll_view);
        this.c.setPaginationListener(new e(this));
        this.c.setVisibility(this.a ? 0 : 8);
        this.b = obtainStyledAttributes.getBoolean(R.styleable.PagedListView_rightGutterEnabled, false);
        if (this.b || !this.a) {
            LayoutParams layoutParams = (LayoutParams) frameLayout.getLayoutParams();
            if (this.b) {
                layoutParams.rightMargin = getResources().getDimensionPixelSize(R.dimen.car_card_margin);
            }
            if (!this.a) {
                layoutParams.setMarginStart(0);
            }
            frameLayout.setLayoutParams(layoutParams);
        }
        setDayNightStyle(0);
        obtainStyledAttributes.recycle();
    }

    public void addItemDecoration(@NonNull ItemDecoration itemDecoration) {
        this.mRecyclerView.addItemDecoration(itemDecoration);
    }

    public void addOnItemTouchListener(@NonNull OnItemTouchListener onItemTouchListener) {
        this.mRecyclerView.addOnItemTouchListener(onItemTouchListener);
    }

    /* access modifiers changed from: protected */
    public int calculateMaxItemCount() {
        View childAt = this.mLayoutManager.getChildAt(0);
        if (childAt == null || childAt.getHeight() == 0 || this.e < 0) {
            return -1;
        }
        return this.d * this.e;
    }

    public View findViewByPosition(int i2) {
        return this.mLayoutManager.findViewByPosition(i2);
    }

    @Nullable
    public Adapter<? extends ViewHolder> getAdapter() {
        return this.mRecyclerView.getAdapter();
    }

    /* access modifiers changed from: protected */
    public int getDefaultMaxPages() {
        return this.g - 1;
    }

    public int getFirstFullyVisibleChildPosition() {
        return this.mLayoutManager.getFirstFullyVisibleChildPosition();
    }

    public int getLastFullyVisibleChildPosition() {
        return this.mLayoutManager.getLastFullyVisibleChildPosition();
    }

    @Nullable
    public ViewHolder getLastViewHolder() {
        View lastFullyVisibleChild = this.mLayoutManager.getLastFullyVisibleChild();
        if (lastFullyVisibleChild == null) {
            return null;
        }
        int position = this.mLayoutManager.getPosition(lastFullyVisibleChild);
        ViewHolder findViewHolderForAdapterPosition = getRecyclerView().findViewHolderForAdapterPosition(position + 1);
        return findViewHolderForAdapterPosition == null ? getRecyclerView().findViewHolderForAdapterPosition(position) : findViewHolderForAdapterPosition;
    }

    public int getMaxPages() {
        return this.e;
    }

    public int getPage(int i2) {
        if (this.d == -1) {
            return -1;
        }
        if (this.d == 0) {
            return 0;
        }
        return i2 / this.d;
    }

    @NonNull
    public CarRecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public int getRowsPerPage() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeCallbacks(this.o);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0072  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onGenericMotionEvent(android.view.MotionEvent r13) {
        /*
            r12 = this;
            r11 = 1112014848(0x42480000, float:50.0)
            r10 = 0
            r3 = 1
            r1 = 0
            java.lang.String r0 = "PagedListView"
            java.lang.String r2 = "onGenericMotionEvent"
            android.util.Log.v(r0, r2)
            android.content.res.Resources r0 = r12.getResources()
            int r2 = com.google.android.apps.auto.sdk.ui.R.bool.car_true_for_touch
            boolean r0 = r0.getBoolean(r2)
            if (r0 != 0) goto L_0x0024
            android.content.res.Resources r0 = r12.getResources()
            int r2 = com.google.android.apps.auto.sdk.ui.R.bool.has_wheel
            boolean r0 = r0.getBoolean(r2)
            if (r0 == 0) goto L_0x0027
        L_0x0024:
            r0 = r1
        L_0x0025:
            r1 = r0
        L_0x0026:
            return r1
        L_0x0027:
            int r4 = r13.getAction()
            r0 = 2
            if (r4 != r0) goto L_0x0107
            float r0 = r13.getY()
            float r2 = r12.j
            float r2 = r0 - r2
            float r0 = r13.getY()
            float r5 = r12.k
            float r5 = r0 - r5
            boolean r0 = r12.l
            if (r0 == 0) goto L_0x0076
            double r6 = (double) r2
            r8 = 0
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x0076
            float r0 = java.lang.Math.signum(r2)
            int r0 = (int) r0
            com.google.android.apps.auto.sdk.ui.CarRecyclerView r6 = r12.mRecyclerView
            android.view.View r6 = r6.getFocusedChild()
            if (r6 == 0) goto L_0x00fe
            com.google.android.apps.auto.sdk.ui.CarLayoutManager r7 = r12.mLayoutManager
            int r6 = r7.getPosition(r6)
            int r0 = r0 + r6
            com.google.android.apps.auto.sdk.ui.CarLayoutManager r7 = r12.mLayoutManager
            int r7 = r7.getItemCount()
            int r7 = r7 + -1
            int r0 = java.lang.Math.min(r0, r7)
            int r0 = java.lang.Math.max(r0, r1)
            if (r0 == r6) goto L_0x00fe
            r0 = r3
        L_0x0070:
            if (r0 == 0) goto L_0x0074
            r12.m = r3
        L_0x0074:
            r12.l = r1
        L_0x0076:
            boolean r0 = r12.m
            if (r0 == 0) goto L_0x0101
            float r0 = java.lang.Math.abs(r2)
            r6 = 1097859072(0x41700000, float:15.0)
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 < 0) goto L_0x0101
            r0 = r3
        L_0x0085:
            int r6 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r6 < 0) goto L_0x008d
            int r6 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r6 >= 0) goto L_0x0095
        L_0x008d:
            int r6 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r6 > 0) goto L_0x0103
            int r5 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r5 > 0) goto L_0x0103
        L_0x0095:
            float r5 = r12.k
            float r6 = r12.j
            float r5 = r5 - r6
            float r5 = r5 / r11
            int r5 = (int) r5
            float r6 = r2 / r11
            int r6 = (int) r6
            if (r6 == r5) goto L_0x0105
            float r2 = java.lang.Math.signum(r2)
            int r2 = (int) r2
            com.google.android.apps.auto.sdk.ui.CarRecyclerView r5 = r12.mRecyclerView
            android.view.View r5 = r5.getFocusedChild()
            if (r5 == 0) goto L_0x0105
            com.google.android.apps.auto.sdk.ui.CarLayoutManager r6 = r12.mLayoutManager
            int r5 = r6.getPosition(r5)
            int r2 = r2 + r5
            com.google.android.apps.auto.sdk.ui.CarLayoutManager r6 = r12.mLayoutManager
            int r6 = r6.getItemCount()
            int r6 = r6 + -1
            int r2 = java.lang.Math.min(r2, r6)
            int r2 = java.lang.Math.max(r2, r1)
            if (r2 == r5) goto L_0x0105
            com.google.android.apps.auto.sdk.ui.CarRecyclerView r5 = r12.mRecyclerView
            com.google.android.apps.auto.sdk.ui.CarLayoutManager r6 = r12.mLayoutManager
            com.google.android.apps.auto.sdk.ui.CarLayoutManager r7 = r12.mLayoutManager
            android.view.View r7 = r7.getChildAt(r1)
            int r6 = r6.getPosition(r7)
            int r2 = r2 - r6
            android.view.View r2 = r5.getChildAt(r2)
            if (r2 == 0) goto L_0x0105
            r2.requestFocus()
            r2 = r1
        L_0x00e0:
            float r5 = r13.getY()
            r12.k = r5
        L_0x00e6:
            if (r2 != 0) goto L_0x00ea
            if (r4 != 0) goto L_0x0025
        L_0x00ea:
            float r2 = r13.getY()
            r12.j = r2
            float r2 = r13.getY()
            r12.k = r2
            r12.l = r3
            r12.m = r1
            if (r4 != 0) goto L_0x0025
            goto L_0x0026
        L_0x00fe:
            r0 = r1
            goto L_0x0070
        L_0x0101:
            r0 = r1
            goto L_0x0085
        L_0x0103:
            r2 = r3
            goto L_0x00e0
        L_0x0105:
            r2 = r1
            goto L_0x00e0
        L_0x0107:
            r0 = r1
            r2 = r1
            goto L_0x00e6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.auto.sdk.ui.PagedListView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mLayoutManager.setRowOffsetMode(1);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        View focusedChild = this.mLayoutManager.getFocusedChild();
        View childAt = this.mLayoutManager.getChildAt(0);
        super.onLayout(z, i2, i3, i4, i5);
        if (this.mAdapter != null) {
            int itemCount = this.mAdapter.getItemCount();
            if (Log.isLoggable("PagedListView", 3)) {
                Log.d("PagedListView", String.format("onLayout hasFocus: %s, mLastItemCount: %s, itemCount: %s, focusedChild: %s, firstBorn: %s, isInTouchMode: %s, mNeedsFocus: %s", new Object[]{Boolean.valueOf(hasFocus()), Integer.valueOf(this.h), Integer.valueOf(itemCount), focusedChild, childAt, Boolean.valueOf(isInTouchMode()), Boolean.valueOf(this.i)}));
            }
            updateMaxItems();
            if (this.i && itemCount > 0) {
                if (focusedChild == null) {
                    requestFocus();
                }
                this.i = false;
            }
            if (itemCount > this.h && focusedChild == childAt && getContext().getResources().getBoolean(R.bool.has_wheel)) {
                requestFocus();
            }
            this.h = itemCount;
        }
        updatePaginationButtons(false);
    }

    public int positionOf(@Nullable View view) {
        if (view == null || view.getParent() != this.mRecyclerView) {
            return -1;
        }
        return this.mLayoutManager.getPosition(view);
    }

    public void removeDefaultItemDecoration() {
        this.mRecyclerView.removeItemDecoration(this.f);
    }

    public void removeItemDecoration(@NonNull ItemDecoration itemDecoration) {
        this.mRecyclerView.removeItemDecoration(itemDecoration);
    }

    public void removeOnItemTouchListener(@NonNull OnItemTouchListener onItemTouchListener) {
        this.mRecyclerView.removeOnItemTouchListener(onItemTouchListener);
    }

    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        this.mLayoutManager.setRowOffsetMode(0);
    }

    public boolean requestFocus(int i2, Rect rect) {
        if (getContext().getResources().getBoolean(R.bool.has_wheel)) {
            this.i = true;
        }
        return super.requestFocus(i2, rect);
    }

    public void resetMaxPages() {
        this.e = getDefaultMaxPages();
        updateMaxItems();
    }

    public void scrollToPosition(int i2) {
        this.mLayoutManager.scrollToPosition(i2);
        this.mHandler.post(this.o);
    }

    public void setAdapter(@NonNull Adapter<? extends ViewHolder> adapter) {
        if (!(adapter instanceof ItemCap)) {
            String canonicalName = adapter.getClass().getCanonicalName();
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(canonicalName).length() + 40).append("ERROR: adapter [").append(canonicalName).append("] MUST implement ItemCap").toString());
        }
        this.mAdapter = adapter;
        this.mRecyclerView.setAdapter(adapter);
        updateMaxItems();
    }

    @Deprecated
    public void setAutoDayNightMode() {
        setDayNightStyle(0);
    }

    @Deprecated
    public void setDarkMode() {
        setDayNightStyle(3);
    }

    public void setDayNightStyle(int i2) {
        this.c.setDayNightStyle(i2);
        this.f.updateDividerColor();
    }

    public void setDefaultItemDecoration(Decoration decoration) {
        removeDefaultItemDecoration();
        this.f = decoration;
        addItemDecoration(this.f);
    }

    public void setDefaultMaxPages(int i2) {
        this.g = i2;
    }

    @Deprecated
    public void setLightMode() {
        setDayNightStyle(2);
    }

    public void setListViewStartEndPadding(@Px int i2, @Px int i3) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.car_card_margin);
        int max = Math.max(i2 - (this.a ? dimensionPixelSize : 0), 0);
        if (!this.b) {
            dimensionPixelSize = 0;
        }
        this.mRecyclerView.setPaddingRelative(max, this.mRecyclerView.getPaddingTop(), Math.max(i3 - dimensionPixelSize, 0), this.mRecyclerView.getPaddingBottom());
        this.mRecyclerView.setClipToPadding(this.mRecyclerView.getClipChildren());
    }

    public void setMaxPages(int i2) {
        this.e = i2;
        updateMaxItems();
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
        this.mLayoutManager.setOnScrollListener(this.mOnScrollListener);
    }

    /* access modifiers changed from: protected */
    public boolean shouldEnablePageDownButton() {
        return !this.mLayoutManager.isAtBottom();
    }

    /* access modifiers changed from: protected */
    public boolean shouldEnablePageUpButton() {
        return !this.mLayoutManager.isAtTop();
    }

    /* access modifiers changed from: protected */
    @UiThread
    public void updateMaxItems() {
        if (this.mAdapter != null) {
            int itemCount = this.mAdapter.getItemCount();
            updateRowsPerPage();
            this.mAdapter.setMaxItems(calculateMaxItemCount());
            int itemCount2 = this.mAdapter.getItemCount();
            if (itemCount2 == itemCount) {
                return;
            }
            if (itemCount2 < itemCount) {
                this.mAdapter.notifyItemRangeRemoved(itemCount2, itemCount - itemCount2);
            } else {
                this.mAdapter.notifyItemRangeInserted(itemCount, itemCount2 - itemCount);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updatePaginationButtons(boolean z) {
        if (this.a) {
            if ((!this.mLayoutManager.isAtTop() || !this.mLayoutManager.isAtBottom()) && this.mLayoutManager.getItemCount() != 0) {
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(4);
            }
            this.c.setUpEnabled(shouldEnablePageUpButton());
            this.c.setDownEnabled(shouldEnablePageDownButton());
            this.c.setParameters(this.mRecyclerView.computeVerticalScrollRange(), this.mRecyclerView.computeVerticalScrollOffset(), this.mRecyclerView.computeVerticalScrollExtent(), z);
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void updateRowsPerPage() {
        View childAt = this.mLayoutManager.getChildAt(0);
        if (childAt == null || childAt.getHeight() == 0) {
            this.d = 1;
        } else {
            this.d = Math.max(1, (getHeight() - getPaddingTop()) / childAt.getHeight());
        }
    }
}
