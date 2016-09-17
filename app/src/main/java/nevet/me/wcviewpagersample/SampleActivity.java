package nevet.me.wcviewpagersample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.Random;

import nevet.me.wcviewpager.ObjectAtPositionPagerAdapter;


public class SampleActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        // demo tab selection without scrolling
        tabs = (TabLayout) findViewById(R.id.tablayout);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        initTextViewsAdapter();

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_textviews) {
            initTextViewsAdapter();
            return true;
        } else if (id == R.id.action_imageviews) {
            initImageViewsAdapter();
        } else if (id == R.id.action_imageviews_async) {
            initAsyncImageViewsAdapter();
        }

        return super.onOptionsItemSelected(item);
    }

    private void initImageViewsAdapter() {
        ArrayList<Integer> resArrayList = new ArrayList<Integer>();
        resArrayList.add(R.drawable.a100);
        resArrayList.add(R.drawable.a200);
        resArrayList.add(R.drawable.a300);
        resArrayList.add(R.drawable.a400);
        resArrayList.add(R.drawable.a200);
        resArrayList.add(R.drawable.a300);
        resArrayList.add(R.drawable.a100);

        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(resArrayList);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    private void initAsyncImageViewsAdapter() {
        ArrayList<String> resArrayList = new ArrayList<>();
        resArrayList.add("http://dummyimage.com/500x400/000/fff");
        resArrayList.add("http://dummyimage.com/500x300/040/fff");
        resArrayList.add("http://dummyimage.com/500x200/006/fff");
        resArrayList.add("http://dummyimage.com/500x400/700/fff");
        resArrayList.add("http://dummyimage.com/500x100/006/fff");

        AsyncImageViewPagerAdapter adapter = new AsyncImageViewPagerAdapter(resArrayList);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }

    private void initTextViewsAdapter() {
        ArrayList<String> stringArrayList = new ArrayList<String>();
        stringArrayList.add(getString(R.string.lorem_short));
        stringArrayList.add(getString(R.string.lorem_medium));
        stringArrayList.add(getString(R.string.lorem_long));
        stringArrayList.add(getString(R.string.lorem_medium));
        stringArrayList.add(getString(R.string.lorem_short));

        TextViewPagerAdapter adapter = new TextViewPagerAdapter(stringArrayList);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }


    public class TextViewPagerAdapter extends ObjectAtPositionPagerAdapter {

        private final ArrayList<String> strings;

        public TextViewPagerAdapter(ArrayList<String> strings) {
            super();
            this.strings = strings;
        }

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItemObject(ViewGroup container, int position) {
            View view = View.inflate(container.getContext(), android.R.layout.test_list_item, null);
            ((TextView)view.findViewById(android.R.id.text1)).setText(strings.get(position));
            container.addView(view);

            // set Random background
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            view.setBackgroundColor(color);

            return view;
        }

        @Override
        public void destroyItemObject(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "View" + position;
        }
    }

    public class ImageViewPagerAdapter extends ObjectAtPositionPagerAdapter {

        private final ArrayList<Integer> images;

        public ImageViewPagerAdapter(ArrayList<Integer> imageResourceIds) {
            super();
            this.images = imageResourceIds;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItemObject(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setImageResource(images.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItemObject(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "View" + position;
        }
    }

    public class AsyncImageViewPagerAdapter extends ObjectAtPositionPagerAdapter {

        private final ArrayList<String> images;

        public AsyncImageViewPagerAdapter(ArrayList<String> imageUrls) {
            super();
            this.images = imageUrls;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public Object instantiateItemObject(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(view);
            ImageLoader.getInstance().displayImage(images.get(position), view);
            return view;
        }

        @Override
        public void destroyItemObject(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "View" + position;
        }
    }
}
