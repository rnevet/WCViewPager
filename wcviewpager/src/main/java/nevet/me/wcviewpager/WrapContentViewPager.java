package nevet.me.wcviewpager;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

public class WrapContentViewPager extends ViewPager {

    private static final String TAG = WrapContentViewPager.class.getSimpleName();
    private int height = 0;

    public WrapContentViewPager(Context context) {
        super(context);
    }

    public WrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(new PagerAdapterWrapper(adapter));
    }

    /**
     * Allows to redraw the view size to wrap the content of the bigger child.
     *
     * @param widthMeasureSpec  with measured
     * @param heightMeasureSpec height measured
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);

        if (mode == MeasureSpec.UNSPECIFIED || mode == MeasureSpec.AT_MOST) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int position = getCurrentItem();
            View child = getViewAtPosition(position);
            if (child != null) {
                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                height = child.getMeasuredHeight();
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            }
            Log.d(TAG, "current position:" + position + " measured height:" + height);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected View getViewAtPosition(int position) {
        Object objectAtPosition = ((PagerAdapterWrapper)getAdapter()).getObjectAtPosition(position);
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if(getAdapter().isViewFromObject(child, objectAtPosition)) {
                return child;
            }
        }
        return null;
    }


    /**
     * Wrapper for PagerAdapter so we can ask for Object at index
     */
    private class PagerAdapterWrapper extends PagerAdapter {
        private final PagerAdapter innerAdapter;
        private SparseArray<Object> objects;

        public PagerAdapterWrapper(PagerAdapter adapter) {
            this.innerAdapter = adapter;
            this.objects = new SparseArray<>(adapter.getCount());
        }
        

        @Override
        public void startUpdate(ViewGroup container) {
            innerAdapter.startUpdate(container);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object object = innerAdapter.instantiateItem(container, position);
            objects.put(position, object);
            return object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            innerAdapter.destroyItem(container, position, object);
            objects.remove(position);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            innerAdapter.setPrimaryItem(container, position, object);
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            innerAdapter.finishUpdate(container);
        }

        @Override
        public Parcelable saveState() {
            return innerAdapter.saveState();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            innerAdapter.restoreState(state, loader);
        }

        @Override
        public int getItemPosition(Object object) {
            return innerAdapter.getItemPosition(object);
        }

        @Override
        public void notifyDataSetChanged() {
            innerAdapter.notifyDataSetChanged();
        }

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {
            innerAdapter.registerDataSetObserver(observer);
        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {
            innerAdapter.unregisterDataSetObserver(observer);
        }

        @Override
        public float getPageWidth(int position) {
            return innerAdapter.getPageWidth(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return innerAdapter.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return innerAdapter.getCount();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return innerAdapter.isViewFromObject(view, object);
        }

        public Object getObjectAtPosition(int position) {
            return objects.get(position);
        }
    }
}
